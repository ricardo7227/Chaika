package com.chaika.llamadasAPI;

import com.chaika.databases.Data;
import com.chaika.estructuraDatos.Database.UserData;
import com.chaika.estructuraDatos.api.Credentials;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.estructuraDatos.search.AnimeSearch;
import com.chaika.estructuraDatos.search.Entry;
import com.chaika.interfaces.MalClient;
import com.chaika.utilidades.ResposeBodyReader;
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



    //instancia única de la clase
    public static RestApiMal getInstance(){
        if (instance == null){
            instance = new RestApiMal();
        }
        return instance;
    }

    /**
     * llamada que recibe toda la información del usuario, su perfil y su lista de series
     * @param userName - nombre del usuario en el sitio web
     * @param status - all - String, es el único que funciona, watching ---> X_X
     * @param type  - anime - manga, String, dos listas distintas
     */
    public void getMalUserProfile(String userName, String status, String type,Data data){
        //cambio a la URL correspondiente
        ServiceGenerator.changeApiBaseUrl(UrlAPIs.BASE_URL_MALAPPINFO);
        String apiBase = apiBaseUrl;
        //acceso al servicio
        MalClient malClient = ServiceGenerator.createService(MalClient.class);

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
                        Logger.d("onNext MALAPPINFO");

                        //creo el objeto a agregar en la DB
                        UserData userData = new UserData();
                        userData.setMyInfo(myAnimeList.getMyInfo());
                        //información del usuario
                        data.upsert(userData);
                        //lista de series
                        data.upsert(myAnimeList);

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

    /***
     * añade una nueva serie a la lista
     * @param malId String ID de la serie en MAL
     * @param entryAnimeValues - String - XML parseado a String con los datos de la serie añadida
     * @param username - String nick del usuario,
     * @param password  - String - contraseña del perfil
     *                  Cada petición para modificar la lista del usuario requiere identificación
     */
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
                        String respuesta = ResposeBodyReader.instance().getResponse(responseBody);
                        Logger.d(respuesta);
                        if (respuesta == "Created"){

                        }else {
                            //problemas añadiendo serie
                        }
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

    }//addAnime

    /**
     * Actualiza la información del estado de una serie.
     * @param malId - ID de la serie en MAL
     * @param entryAnimeValues - XML parseado con los datos a actualizar
     * @param username
     * @param password
     */
    public void updateAnimeMal(String malId, String entryAnimeValues,String username,String password){
        MalClient malClient = ServiceGenerator.createService(MalClient.class,username,password);

        Observable<ResponseBody> entryAnimeValuesObservable = malClient.updateAnime(malId,entryAnimeValues);

        entryAnimeValuesObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Logger.d("la onSubscribe");

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        String respuesta = ResposeBodyReader.instance().getResponse(responseBody);
                        Logger.d(respuesta);
                        if (respuesta == "Updated"){

                        }else {
                            //problemas actualizando serie
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e(e.getMessage() );

                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("la onComplete");
                    }
                });

    }//updateAnime


    /***
     * Borra la serie de la lista personal del usuario
     * @param malId - ID de la serie en MAL
     * @param username
     * @param password
     */
    public void deleteAnimeMal(String malId,String username,String password){
        MalClient malClient = ServiceGenerator.createService(MalClient.class,username,password);

        Observable<ResponseBody> entryAnimeValuesObservable = malClient.deleteAnime(malId);

        entryAnimeValuesObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Logger.d("la onSubscribe");

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        String respuesta = ResposeBodyReader.instance().getResponse(responseBody);
                        Logger.d(respuesta);
                        if (respuesta == "Deleted"){

                        }else {
                            //problemas eliminando serie
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e(e.getMessage() );

                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("la onComplete");
                    }
                });

    }//deleteAnime


    /***
     *  Obtiene las credenciales del usuario
     *  Utilizado para validar el login de la aplicación
     * @param user
     * @param password
     */
    public void getCredentials(String user, String password) {

        MalClient malClient = ServiceGenerator.createService(MalClient.class,user,password);
        Observable<Credentials> credentialsObservable = malClient.getCredentials();

        credentialsObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Credentials>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Logger.d("onSubscribe getCredentials");
                    }

                    @Override
                    public void onNext(@NonNull Credentials credentials) {
                        Logger.d(credentials.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e("onError getCredentials: ",e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete getCredentials");
                    }
                });
    }//fin getCredentials

    /***
     *
     * @param query - termino a buscar en MAL
     * @param username
     * @param password
     */
    public void getAnimeSearch(String query,String username,String password) {

        MalClient malClient = ServiceGenerator.createService(MalClient.class,username,password);
        Observable<AnimeSearch> animeSearchObservable = malClient.getAnimeSearch(query);

        animeSearchObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AnimeSearch>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Logger.d("onSubscribe getAnimeSearch");
                    }

                    @Override
                    public void onNext(@NonNull AnimeSearch animeSearch) {
                        Logger.d(animeSearch);

                        List<Entry> resultados = animeSearch.getEntradas();
                        for (Entry a: resultados) {
                            Logger.d(a.toString());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.e("onError getAnimeSearch: ",e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Logger.d("onComplete getAnimeSearch");
                    }
                });
    }//fin getAnimeSearch

}//fin clase

