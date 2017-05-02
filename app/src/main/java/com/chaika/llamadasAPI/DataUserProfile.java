package com.chaika.llamadasAPI;

import com.chaika.estructuraDatos.search.Anime;
import com.chaika.estructuraDatos.api.Credentials;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.interfaces.InterfacesComunes;
import com.chaika.interfaces.MalClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Gato on 13/04/2017.
 */

public class DataUserProfile implements InterfacesComunes {

   private static Retrofit retrofit = null;


    @Override
    public void getMalUserProfile(String user) {
        //cambio a la URL correspondiente
        ServiceGenerator.changeApiBaseUrl(UrlAPIs.BASE_URL_MALAPPINFO);
        String apiBase = ServiceGenerator.apiBaseUrl;
        //acceso al servicio
        MalClient malClient = ServiceGenerator.createService(MalClient.class);

        Call<MyAnimeList> llamada = malClient.getUserData(user);
        //la url que consulta
        String urlcall = llamada.request().url().toString();
        llamada.enqueue(new Callback<MyAnimeList>() {
            @Override
            public void onResponse(Call<MyAnimeList> call, Response<MyAnimeList> response) {
                if (response.isSuccessful()){
                    MyAnimeList datosUsuario = response.body();
                }
            }

            @Override
            public void onFailure(Call<MyAnimeList> call, Throwable t) {
                String val = "Error de conexión o formación";

            }
        });


    }

    @Override
    public void getCredentials(String user, String password) {

        MalClient malClient = ServiceGenerator.createService(MalClient.class,user,password);

        Call<Credentials> llamada = malClient.getCredentials();
        llamada.enqueue(new Callback<Credentials>() {
            @Override
            public void onResponse(Call<Credentials> call, Response<Credentials> response) {
                if (response.isSuccessful()){
                    Credentials cr = response.body();
                }
            }

            @Override
            public void onFailure(Call<Credentials> call, Throwable t) {

            }
        });


    }

    @Override
    public void getAnimeSearch(String query) {

        MalClient malClient = ServiceGenerator.createService(MalClient.class);
        Call<Anime> llamada = malClient.getAnimeSearch(query);
        String urlcall = llamada.request().url().toString();
        llamada.enqueue(new Callback<Anime>() {
            @Override
            public void onResponse(Call<Anime> call, Response<Anime> response) {
                if (response.isSuccessful()){
                    Anime entradas = response.body();

                }
            }

            @Override
            public void onFailure(Call<Anime> call, Throwable t) {

            }
        });

    }
}//fin clase

