package com.chaika.application;

import android.app.Activity;
import android.app.Application;


import com.chaika.componentes.DaggerDatabaseComponent;
import com.chaika.componentes.DatabaseComponent;

import com.chaika.databases.MalDBHelper;
import com.chaika.modulos.ContextModulo;


/**
 * Created by Gato on 02/05/2017.
 */

public class ChaikaApplication extends Application{

    private DatabaseComponent component;

    private MalDBHelper malDBHelper;

    public static ChaikaApplication get(Activity activity){
        return (ChaikaApplication) activity.getApplication();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        component = DaggerDatabaseComponent.builder()
                .contextModulo(new ContextModulo(this))
                .build();

        malDBHelper = component.getMalDbHelper();



    }

    public DatabaseComponent component(){
        return component;
    }

}//fin clase
