package com.chaika;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaika.application.ApplicationConfig;
import com.chaika.application.ChaikaApplication;
import com.chaika.estructuraDatos.Database.AnimeData;
import com.chaika.estructuraDatos.api.Credentials;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.estructuraDatos.search.AnimeSearch;
import com.chaika.estructuraDatos.search.Entry;
import com.chaika.interfaces.ApiResult;
import com.chaika.llamadasAPI.RestApiMal;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by ricardo on 25/5/17.
 */

public class DetailSerie extends AppCompatActivity implements ApiResult{

    ImageView cover;
    TextView title,sinopsis;
    AnimeData animeData;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        Intent intent = getIntent();
        int animeId = intent.getIntExtra("animeId",0);
        Logger.d(animeId);

        cover = (ImageView) findViewById(R.id.cover_details);
        title = (TextView) findViewById(R.id.title_details);
        sinopsis = (TextView) findViewById(R.id.sinopsis);

        //recogemos los datos de DB
        animeData = ChaikaApplication.get(ApplicationConfig.getInstance().getActivity()).component().getData().getAnimeById(animeId);

        Glide.with(ApplicationConfig.getInstance().getContext()).load(animeData.getAnimeMalinfo().getSeries_image()).into(cover);
        title.setText(animeData.getAnimeMalinfo().getSeries_title());
        sinopsis.setText(animeData.getAnimeSearch().getAnime_sinopsis());

        //llamada para pedir datos adicionales
        RestApiMal.getInstance().getAnimeSearch(animeData.getAnimeMalinfo().getSeries_title(),
                ApplicationConfig.getInstance().getUsername(),ApplicationConfig.getInstance().getPassword(),this);

    }


    @Override
    public void SuccessCall(MyAnimeList myAnimeList) {
//nada aquí
    }

    @Override
    public void SuccessCall(Credentials credentials) {
//nada aquí
    }

    @Override
    public void SuccessCall(AnimeSearch animeSearch) {
        List <Entry> a = animeSearch.getEntradas();
        try {
            sinopsis.setText(a.get(0).getAnime_sinopsis());
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void genericResponse(String response) {
//nada aquí
    }

    @Override
    public void ErrorCall(Throwable error) {
//nada aquí
    }
}//fin clase

