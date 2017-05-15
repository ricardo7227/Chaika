package com.chaika.application;

import android.app.Activity;
import android.app.Application;

import com.bumptech.glide.RequestManager;
import com.chaika.componentes.DaggerDatabaseApplicationComponent;
import com.chaika.componentes.DatabaseApplicationComponent;
import com.chaika.databases.MalDBHelper;
import com.chaika.estructuraDatos.EntryAnimeValues;
import com.chaika.fragmentos.AllSeriesFragment;
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



        //nueva forma de hacer llamadas
        //RestApiMal.getInstance().getMalUserProfile("ricardoAlexis","all","anime",component.getData());
        RestApiMal.getInstance().getMalUserProfile("ricardoAlexis","all","anime", AllSeriesFragment.instance());
        String animeMalId = "34591";
        String username = "ricardoAlexis";
        String pasword = "alexss00my**";






        EntryAnimeValues valores = new EntryAnimeValues();
        valores.setEpisode("5");
        valores.setStatus("1");
        valores.setScore("8");
        //añade una nueva serie a la lista
        //RestApiMal.getInstance().addAnimeMal(animeMalId, AnimeValuesXMLtoString.getInstance().convert(valores),username,pasword);
        //RestApiMal.getInstance().updateAnimeMal(animeMalId,entryValues.toString(),username,pasword);
        //RestApiMal.getInstance().deleteAnimeMal(animeMalId,username,pasword);



    }

    public DatabaseApplicationComponent component(){
        return component;
    }

}//fin clase
