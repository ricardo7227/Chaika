package com.chaika.llamadasAPI;

import android.text.TextUtils;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Gato on 16/04/2017.
 */

public class ServiceGenerator {

    public static String apiBaseUrl = UrlAPIs.BASE_URL_MALAPI;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(apiBaseUrl)
                    .addConverterFactory(SimpleXmlConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }
    //metodo para el login
    public static <S> S createService(
            Class<S> serviceClass, String username, String password) {
       if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);
            return createService(serviceClass, authToken);
        }

        return retrofit.create(serviceClass);
    }
    //metodo para el authtoken
    public static <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }

    //cambia la url de la API por una distinta
    public static void changeApiBaseUrl(String newApiBaseUrl) {
        apiBaseUrl = newApiBaseUrl;
        builder = new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl(apiBaseUrl);
        retrofit = builder.build();
    }

}//fin clase
