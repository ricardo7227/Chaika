package com.chaika;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chaika.estructuraDatos.Database.AnimeData;
import com.chaika.estructuraDatos.search.AnimeSearch;
import com.chaika.estructuraDatos.search.Entry;
import com.chaika.fragmentos.adaptadores.RecyclerViewAdaptador;

import java.util.ArrayList;

/**
 * Actividad en la que se muestra el resultado de una busqueda
 * Created by Gato on 27/06/2017.
 */

public class SearchActivity extends AppCompatActivity{

    RecyclerView rvSeries;
    RecyclerViewAdaptador adapter;
    ArrayList<AnimeData> animeList;// array con la información de todas las series

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_series_fragment);

        rvSeries = (RecyclerView) findViewById(R.id.rvAnimeList);

        //recibimos la respuesta del servidor
        Bundle bundle = getIntent().getExtras();
        AnimeSearch results = bundle.getParcelable("results");


        rvSeries.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvSeries.setLayoutManager(layoutManager);

        animeList = new ArrayList<>();
        //pendiente cambiar el Adaptador por uno nuevo 

        for (Entry item: results.getEntradas()){

            animeList.add(new AnimeData(item));

        }

        adapter = new RecyclerViewAdaptador(getApplicationContext(), animeList,null);

        // definimos el adaptador en el RecyclerView
        rvSeries.setAdapter(adapter);
    }//fin oncreate



}//fin clase
