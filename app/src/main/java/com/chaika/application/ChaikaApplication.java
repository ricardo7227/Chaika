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
        String animeMalId = "34591";
        String username = "ricardoAlexis";
        String pasword = "alexss00my**";

        StringBuilder entryValues = new StringBuilder();
        entryValues.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        entryValues.append("<entry>");
        entryValues.append("<episode>1</episode>");
        entryValues.append("<status>1</status>");
        entryValues.append("</entry>");
        /***
         * <?xml version="1.0" encoding="UTF-8"?>
         <entry>
         <episode>11</episode>
         <status>1</status>
         <score>7</score>
         <storage_type></storage_type>
         <storage_value></storage_value>
         <times_rewatched></times_rewatched>
         <rewatch_value></rewatch_value>
         <date_start></date_start>
         <date_finish></date_finish>
         <priority></priority>
         <enable_discussion></enable_discussion>
         <enable_rewatching></enable_rewatching>
         <comments></comments>
         <tags>test tag, 2nd tag</tags>
         </entry>
         */

        RestApiMal.getInstance().addAnimeMal(animeMalId,entryValues.toString(),username,pasword);



    }

    public DatabaseApplicationComponent component(){
        return component;
    }

}//fin clase
