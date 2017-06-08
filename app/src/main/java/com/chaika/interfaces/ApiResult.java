package com.chaika.interfaces;

import com.chaika.estructuraDatos.api.Credentials;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.estructuraDatos.search.AnimeSearch;

/**
 * Interface encargada de gestionar todas las posibles respuestas a las llamadas del servidor.
 *
 * Created by Gato on 13/04/2017.
 */

public interface ApiResult {
    //lo que recibe la llamada de MALAPPINFO.php
    void SuccessCall(MyAnimeList myAnimeList);

    //lo recibido de la llamada para verificar credenciales
    void SuccessCall(Credentials credentials);

    //la respuesta de las llamadas a search
    void SuccessCall(AnimeSearch animeSearch);

    //lo que recibo en llamadas como añadir, eliminar o actualizar
    void genericResponse(String response);

    //Posibles errores en las llamadas
    void ErrorCall(Throwable error,String data);


}//fin clase
