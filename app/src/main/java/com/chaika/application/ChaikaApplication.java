package com.chaika.application;

import android.app.Activity;
import android.app.Application;

import com.bumptech.glide.RequestManager;
import com.chaika.componentes.DaggerDatabaseApplicationComponent;
import com.chaika.componentes.DatabaseApplicationComponent;
import com.chaika.databases.MalDBHelper;
import com.chaika.estructuraDatos.EntryAnimeValues;
import com.chaika.llamadasAPI.RestApiMal;
import com.chaika.modulos.ContextModulo;


/**
 * Created by Gato on 02/05/2017.
 */

public class ChaikaApplication extends Application{

    private DatabaseApplicationComponent component;

    private MalDBHelper malDBHelper;
    private RequestManager requestManager;

    public static ChaikaApplication get(Activity activity){
        return (ChaikaApplication) activity.getApplication();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        component = DaggerDatabaseApplicationComponent.builder()
                .contextModulo(new ContextModulo(this))
                .build();

        //malDBHelper = component.getMalDbHelper();
        //nueva forma de hacer llamadas
        //RestApiMal.getInstance().getMalUserProfile("ricardo7227","all","anime");
        EntryAnimeValues animeValues = new EntryAnimeValues();
        animeValues.setEpisode("2");
        animeValues.setStatus("1");
        String animeMalId = "10";
        String username = "ricardoAlexis";
        String pasword = "alexss00my**";

        StringBuilder entryValues = new StringBuilder();
        entryValues.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        entryValues.append("<entry>");
        entryValues.append("\t<episode>11</episode>\n");
        entryValues.append("</entry>");

        RestApiMal.getInstance().addAnimeMal(animeMalId,entryValues,username,pasword);



    }

    public DatabaseApplicationComponent component(){
        return component;
    }

}//fin clase
