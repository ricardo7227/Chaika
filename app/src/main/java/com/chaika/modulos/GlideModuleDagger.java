package com.chaika.modulos;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModulo.class)
public class GlideModuleDagger {

    @Provides
    public RequestManager glide(Context context){
        return Glide.with(context);
    }

}//fin clase
