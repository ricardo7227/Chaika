package com.chaika.interfaces;

import com.chaika.estructuraDatos.api.Credentials;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.estructuraDatos.search.AnimeSearch;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Gato on 13/04/2017.
 */

public interface MalClient {
    /*
    @GET("malappinfo.php/")
    Call<MyAnimeList> getUserData(
            @Query("u") String username);

    @GET("account/verify_credentials.xml")
    Call<Credentials> getCredentials();

    @GET("anime/search.xml")
    Call<AnimeSearch> getAnimeSearch(@Query("q") String query);
    */
    //RXjava
    @GET("malappinfo.php/")
    Observable<MyAnimeList> getUserData(
            @Query("u") String username);

    @GET("account/verify_credentials.xml")
    Observable<Credentials> getCredentials();

    @GET("anime/search.xml")
    Observable<AnimeSearch> getAnimeSearch(@Query("q") String query);


}//fin clase
