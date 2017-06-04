package com.chaika.modulos;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo para glide, queda pendiente de desarrollar.
 */
@Module(includes = ContextModulo.class)
public class GlideModuleDagger {

    @Provides
    public RequestManager glide(Context context){
        return Glide.with(context);
    }

}//fin clase
