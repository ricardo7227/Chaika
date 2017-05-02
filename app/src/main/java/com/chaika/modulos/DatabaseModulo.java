package com.chaika.modulos;

import android.content.Context;

import com.chaika.databases.MalDBHelper;
import com.chaika.scopes.DatabaseScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Gato on 01/05/2017.
 */
@Module(includes = ContextModulo.class)
public class DatabaseModulo {

    @Provides
    @DatabaseScope
    public MalDBHelper malDBHelper (Context context){
        return new MalDBHelper(context.getApplicationContext());
    }
}//fin clase
