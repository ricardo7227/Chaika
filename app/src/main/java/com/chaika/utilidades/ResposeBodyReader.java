package com.chaika.utilidades;

import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;

/**
 * Clase que recibe un objeto ResponseBody y los transforma en un String legible.
 *
 * Created by Gato on 13/05/2017.
 */

public class ResposeBodyReader {
    private static ResposeBodyReader instance;

    //instancia única de la clase
    public static ResposeBodyReader instance(){
        if (instance == null){
            instance = new ResposeBodyReader();
        }
        return instance;
    }

    /***
     *
     * @param responseBody
     * @return String mensaje recibido en la clase ResponseBody
     */
    public String getResponse(ResponseBody responseBody){

        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {

            reader = new BufferedReader(new InputStreamReader( responseBody.byteStream()));

            String line;

            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
        return sb.toString();
    }

}//fin clase
