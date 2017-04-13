package com.chaika.llamadasAPI;

import com.chaika.estructuraDatos.MyAnimeList;
import com.chaika.interfaces.InterfacesComunes;
import com.chaika.interfaces.MalClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Gato on 13/04/2017.
 */

public class DataUserProfile implements InterfacesComunes {

    private static Retrofit retrofit = null;


    @Override
    public void getMalUserProfile() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(UrlAPIs.BASE_URL).addConverterFactory(SimpleXmlConverterFactory.create()).build();
        }
        MalClient malClient = retrofit.create(MalClient.class);

        Call<MyAnimeList> llamada = malClient.getUserData();
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
}//fin clase

