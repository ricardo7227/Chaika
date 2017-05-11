package com.chaika.llamadasAPI;

import com.chaika.estructuraDatos.malAppInfo.Anime;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.interfaces.MalClient;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

import static com.chaika.llamadasAPI.ServiceGenerator.apiBaseUrl;

/**
 * Created by Gato on 13/04/2017.
 */


public class RestApiMal {

    private final String TAG = getClass().getName();

    private static Retrofit retrofit = null;

    private MalClient malClient;

    private static  RestApiMal instance;

    public static RestApiMal getInstance(){
        if (instance == null){
            instance = new RestApiMal();
        }
        return instance;
    }


    public void getMalUserProfile(String userName,String status,String type){
        //cambio a la URL correspondiente
        ServiceGenerator.changeApiBaseUrl(UrlAPIs.BASE_URL_MALAPPINFO);
        String apiBase = apiBaseUrl;
        //acceso al servicio
        MalClient malClient = ServiceGenerator.createService(MalClient.class);
        if (malClient == null){
            Logger.e("nulo");
        }
        Observable<MyAnimeList> myAnimeListObservable = malClient.getUserData(userName,status,type);

        myAnimeListObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyAnimeList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Logger.d("onSubscribe: " + d.toString());
                    }

                    @Override
                    public void onNext(@NonNull MyAnimeList myAnimeList) {
                        Logger.d("onNext");
                        Logger.d(myAnimeList.getMyInfo().toString());
                        List<Anime> animeList = myAnimeList.getAnimes();
                        for (Anime a: animeList) {
                            Logger.d(a.toString());
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Logger.e(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");
                    }
                });
    }
    public void addAnimeMal(String malId, String entryAnimeValues,String username,String password){
        MalClient malClient = ServiceGenerator.createService(MalClient.class,username,password);

        Observable<ResponseBody> entryAnimeValuesObservable = malClient.addAnime(malId,entryAnimeValues);

        entryAnimeValuesObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Logger.d("la subcribe");

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        Logger.d(responseBody.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e(e.getMessage() );

                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("la complete");
                    }
                });
/*
        entryAnimeValuesObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EntryAnimeValues>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Logger.d("onSubscribe");
                    }

                    @Override
                    public void onNext(@NonNull EntryAnimeValues entryAnimeValues) {
                        Logger.d(entryAnimeValues.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e(e.getMessage() );

                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete");

                    }
                });*/
    }




   /* @Override
    public void getMalUserProfile(String user) {
        //cambio a la URL correspondiente
        ServiceGenerator.changeApiBaseUrl(UrlAPIs.BASE_URL_MALAPPINFO);
        String apiBase = apiBaseUrl;
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
        Call<AnimeSearch> llamada = malClient.getAnimeSearch(query);
        String urlcall = llamada.request().url().toString();
        llamada.enqueue(new Callback<AnimeSearch>() {
            @Override
            public void onResponse(Call<AnimeSearch> call, Response<AnimeSearch> response) {
                if (response.isSuccessful()){
                    AnimeSearch entradas = response.body();

                }
            }

            @Override
            public void onFailure(Call<AnimeSearch> call, Throwable t) {

            }
        });

    }*/
}//fin clase

