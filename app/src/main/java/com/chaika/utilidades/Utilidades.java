package com.chaika.utilidades;

import com.orhanobut.logger.Logger;

/**
 *
 * Clase con ditintas utilidades para utilizar en el proyecto
 * Created by Gato on 27/05/2017.
 */

public class Utilidades {

    private static  Utilidades instance;

    //instancia única de la clase
    public static Utilidades getInstance(){
        if (instance == null){
            instance = new Utilidades();
        }
        return instance;
    }

    /**
     * Recibe la url con imagen de la portada de una serie y la cambia por su versión más grande
     *
     * @param urlImage
     * @return String - url
     */
    public String getLargeCoverVersion(String urlImage){

        StringBuilder backgroundImage = new StringBuilder();
        backgroundImage.append(urlImage);
        backgroundImage.insert(urlImage.length()-4,"l");
        Logger.d(backgroundImage);

        return backgroundImage.toString();
    }
}//fin clase
