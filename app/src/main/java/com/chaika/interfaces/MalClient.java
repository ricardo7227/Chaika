package com.chaika.interfaces;

import com.chaika.estructuraDatos.api.Credentials;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.estructuraDatos.search.AnimeSearch;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Gato on 13/04/2017.
 */

public interface MalClient {

       //https://myanimelist.net/malappinfo.php?u=ricardoAlexis&status=all&type=anime
    //RXjava
    // acceso a la lista de toda la colección del usuario
    @GET("malappinfo.php/")
    Observable<MyAnimeList> getUserData(
            @Query("u") String username, @Query("status") String status, @Query("type") String type);

    //verifica la credenciales del usuario, necesario en la creación del login
    @GET("account/verify_credentials.xml")
    Observable<Credentials> getCredentials();

    //realiza busqueda de una serie
    @GET("anime/search.xml")
    Observable<AnimeSearch> getAnimeSearch(@Query("q") String query);

    //api/animelist/add/id.xml
    @Headers({
            "Content-Type: application/xml; charset=utf-8",
    })
    @GET("animelist/add/{id}.xml")
    Observable<ResponseBody> addAnime(@Path("id") String malId, @Query("data") String entryAnimeValues );

    ///api/animelist/update/id.xml
    @Headers({
            "Content-Type: application/xml; charset=utf-8",
    })
    @GET("animelist/update/{id}.xml")
    Observable<ResponseBody> updateAnime(@Path("id") String malId, @Query("data") String entryAnimeValues );

    //api/animelist/delete/id.xml
    @DELETE("animelist/delete/{id}.xml")
    Observable<ResponseBody> deleteAnime(@Path("id") String malId);

}//fin clase
