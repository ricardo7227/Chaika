package com.chaika.modulos;

import android.content.Context;

import com.chaika.databases.Data;
import com.chaika.databases.MalDBHelper;
import com.chaika.scopes.DatabaseScope;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo que provee de acceso a la base de datos para Dagger2.
 *
 * Created by Gato on 01/05/2017.
 */
@Module(includes = ContextModulo.class)
public class DatabaseModulo {

    @Provides
    @DatabaseScope
    public MalDBHelper malDBHelper (Context context){
        return new MalDBHelper(context.getApplicationContext());
    }
    @Provides
    @DatabaseScope
    public Data data(MalDBHelper malDBHelper){
        return new Data(malDBHelper);
    }
}//fin clase
