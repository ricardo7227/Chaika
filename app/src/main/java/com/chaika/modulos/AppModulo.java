package com.chaika.modulos;

import com.chaika.MainActivity;
import com.chaika.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Gato on 02/05/2017.
 */
@Module
public class AppModulo {

    private final MainActivity mainActivity;

    public AppModulo (MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Provides
    @AppScope
    public MainActivity mainActivity(){
        return mainActivity;
    }



}
