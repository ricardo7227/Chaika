package com.chaika.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaika.R;
import com.chaika.application.ApplicationConfig;
import com.chaika.application.ChaikaApplication;
import com.chaika.estructuraDatos.Database.AnimeData;
import com.chaika.fragmentos.adaptadores.RecyclerViewAdaptador;
import com.chaika.interfaces.RecyclerViewClickListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Fragmento genérico que será reutilizado para mostrar distintas series según el estaus que el usuario la tenga en su perfil.
 *
 * Created by ricardo on 24/5/17.
 */

public class ShowListByStatus extends Fragment{


    RecyclerViewAdaptador adapter;
    RecyclerView rvSeries;
    ArrayList<AnimeData> animeList;
    //Estatus de la lista creada
    int myStatus;

    RecyclerViewClickListener listener;

    private static ShowListByStatus instance;

    // newInstance constructor for creating fragment with arguments
    public static ShowListByStatus newInstance(int myStatus) { //recibe el status que se quiere mostrar
        ShowListByStatus fragmentFirst = new ShowListByStatus();
        Bundle args = new Bundle();
        args.putInt("myStatus", myStatus);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }
    public void setAnimeList(ArrayList<AnimeData> myAnimeList){
        animeList = myAnimeList;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myStatus = getArguments().getInt("myStatus", 0); //captura el status


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
        animeList = ChaikaApplication.get(ApplicationConfig.getInstance().getActivity()).component().getData().getMyAnimeListbyStatus(myStatus);

        listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Logger.d(view.getId() + " : " + position);
            }
        };

        if (animeList != null){
            adapter = new RecyclerViewAdaptador(getContext(), animeList,listener);}

        // definimos el adaptador en el RecyclerView
        rvSeries.setAdapter(adapter);

        return view;
    }


}//fin clase
