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

import java.util.ArrayList;

/**
 * Created by ricardo on 24/5/17.
 */

public class ShowListByStatus extends Fragment{


    RecyclerViewAdaptador adapter;
    RecyclerView rvSeries;
    ArrayList<AnimeData> animeList;
    //Estatus de la lista creada
    int myStatus;


    private static ShowListByStatus instance;
//pendiente reutilizar fragmento
    // newInstance constructor for creating fragment with arguments
    public static ShowListByStatus newInstance(int myStatus) {
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
        myStatus = getArguments().getInt("myStatus", 0);


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

        if (animeList != null){
            adapter = new RecyclerViewAdaptador(getContext(), animeList);}

        // definimos el adaptador en el RecyclerView
        rvSeries.setAdapter(adapter);

        return view;
    }


}//fin clase
