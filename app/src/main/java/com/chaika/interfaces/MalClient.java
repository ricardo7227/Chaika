package com.chaika.interfaces;

import com.chaika.estructuraDatos.MalUserData;
import com.chaika.estructuraDatos.MyAnimeList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Gato on 13/04/2017.
 */

public interface MalClient {
    @GET("?u=ricardo7227")
    Call<MyAnimeList> getUserData();
}//fin clase
