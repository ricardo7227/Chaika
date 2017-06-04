package com.chaika.modulos;

import android.content.Context;

import com.chaika.scopes.DatabaseScope;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo que provee de un contexto para usar Dagger2.
 *
 * Created by Gato on 01/05/2017.
 */
@Module
public class ContextModulo {
    private final Context context;

    public ContextModulo(Context context){
        this.context = context.getApplicationContext();
    }

    @Provides
    @DatabaseScope
    public Context context(){
        return context;
    }
}
