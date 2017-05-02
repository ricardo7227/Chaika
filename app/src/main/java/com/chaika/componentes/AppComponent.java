package com.chaika.componentes;

import com.chaika.MainActivity;
import com.chaika.databases.MalDBHelper;
import com.chaika.modulos.AppModulo;
import com.chaika.scopes.AppScope;

import dagger.Component;

/**
 * Created by Gato on 02/05/2017.
 */
@AppScope
@Component(modules = AppModulo.class, dependencies = DatabaseComponent.class)
public interface AppComponent {

    //MalDBHelper malDbHelper();
    void injectMain(MainActivity mainActivity);


}
