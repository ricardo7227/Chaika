package com.chaika.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaika.DetailSerie;
import com.chaika.R;
import com.chaika.application.ApplicationConfig;
import com.chaika.application.ChaikaApplication;
import com.chaika.estructuraDatos.Database.AnimeData;
import com.chaika.estructuraDatos.api.Credentials;
import com.chaika.estructuraDatos.malAppInfo.Anime;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.estructuraDatos.search.AnimeSearch;
import com.chaika.fragmentos.adaptadores.RecyclerViewAdaptador;
import com.chaika.interfaces.ApiResult;
import com.chaika.interfaces.RecyclerViewClickListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.chaika.MainActivity.progressBar;

/**
 * Primer fragmento que se carga en el MainActivity, este implementa la interface de respuestas del servidor necesaria en el primer arraque
 * de la aplicación, porque se encarga de mostrar toda la información recibida y agregarla a la base de datos local.
 *
 * Esta hará uso de un adaptador para Recycler, clase que nos permite presentar información en forma de lista por pantalla.
 *
 * Created by Gato on 14/05/2017.
 */

public class AllSeriesFragment extends Fragment implements ApiResult{

    RecyclerViewAdaptador adapter;
    RecyclerView rvSeries;
    ArrayList<AnimeData> animeList;// array con la información de todas las series

    RecyclerViewClickListener listener; //interface con el que conocenos la posición del elemento seleccionado
    int malIDPosition; //Identificador de la Serie seleccionada
    int positionArray; //posición de una serie dentro del array


    /**
     * Instancia única
     */
    private static AllSeriesFragment instance;

    public static AllSeriesFragment instance() {
        if (instance == null) {
            instance = new AllSeriesFragment();
        }
        return instance;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_series_fragment, container, false);

        //ubicamos el Xml  del RecyclerView
         rvSeries = (RecyclerView) view.findViewById(R.id.rvAnimeList);


        rvSeries.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvSeries.setLayoutManager(layoutManager);

        //recogemos las series de la base de datos
        animeList = ChaikaApplication.get(ApplicationConfig.getInstance().getActivity()).component().getData().getMyAnimeList();


        //recoge la posicion del click en la lista cargada de la vista
        listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) { //pendiente
                Logger.d(view.getId() + ": " + position);
                positionArray = position;
                //recoge la ID de la serie.
                 malIDPosition = getID(animeList,position);
                //lanza una nueva actividad sobre el item
                ApplicationConfig.getInstance().getActivity().startActivity(new Intent(getContext(), DetailSerie.class).putExtra("animeId", malIDPosition));
            }
        };

        if (animeList != null){
            progressBar.setVisibility(View.GONE);
            adapter = new RecyclerViewAdaptador(getContext(), animeList,listener);}


        // definimos el adaptador en el RecyclerView
        rvSeries.setAdapter(adapter);

        return view;
    }

    /***
     *  método implementado en onClick para devolver el identificador de la serie sobre la serie presionada
     * @param animeList Array con todas la series cargadas
     * @param position
     * @return int ID identificador de la serie
     */
    public int getID(ArrayList<AnimeData> animeList,int position){
        int id = 0;
        if (animeList != null){
             id = (int) animeList.get(position).getAnimeMalinfo().getSeries_animedb_id();
        }
        return id;
    }


    /*
    *Resutados de las llamadas al API
    *
     */


    /**
     *  Recibe todas la series del servidor y las trata para mostrarse por pantalla y agregarlas a la base de datos
     * @param myAnimeList
     */
    @Override
    public void SuccessCall(MyAnimeList myAnimeList) {

        List<Anime> lista = myAnimeList.getAnimes();

        List<AnimeData> animeDataList = new ArrayList<>();

        AnimeData item;
        for (Anime info: lista) {
            item = new AnimeData();
            item.setAnimeMalinfo(info);
            animeDataList.add(item);
        }
        progressBar.setVisibility(View.GONE);

        //cargamos la información en el adaptador
        animeList = new ArrayList<AnimeData>(animeDataList);
        adapter = new RecyclerViewAdaptador(getContext(),animeList,listener);

        rvSeries.setAdapter(adapter);

        //Creamos un hilo para añadir el array de series en la base de datos.
        Observable.fromArray(myAnimeList)
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<MyAnimeList>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull MyAnimeList myAnimeList) {
                //añadimos la lista a base de datos
                ChaikaApplication.get(ApplicationConfig.getInstance().getActivity()).component().getData().upsert(myAnimeList);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void SuccessCall(Credentials credentials) {
    //nada aquí
    }

    @Override
    public void SuccessCall(AnimeSearch animeSearch) {
    //nada aquí
    }

    /***
     * Respuestas que recibe del servidor sobre las acciones de borrar y actualizar información que se realizan desde la actividad DetailSerie.
     *
     * @param response
     */
    @Override
    public void genericResponse(String response) {
        Logger.d(response);
        if (response.equals("Deleted")){    //elimina la serie de la lista si esta ha sido eliminada del sistema

            animeList.remove(positionArray);
            adapter.notifyDataSetChanged();

        }else if (response.equals("Updated")){      //Actualiza la posible información mostrada en la lista
            //pendiente
            adapter.notifyItemChanged(positionArray,malIDPosition);

            //adapter.notifyDataSetChanged();

        }

    }

    @Override
    public void ErrorCall(Throwable error,String data) {

    }

}//fin clase

