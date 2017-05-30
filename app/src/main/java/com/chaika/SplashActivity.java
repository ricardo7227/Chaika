package com.chaika;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.chaika.application.ApplicationConfig;
import com.orhanobut.logger.Logger;

/**
 *
 * Esta actividad comprueba si tenemos los datos del usuario.
 *
 * Comprueba si existen datos del usuario en la aplicación
 * Si no existen lanza la actividad de login
 *
 *  Created by Gato on 20/05/2017.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ///condición de Arranque
        if (!getSessionMal()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /***
     * Verifica los datos del usuario en la aplicación
     * @return true - datos en el sistema, false - no existen datos
     */
    public boolean getSessionMal(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = preferences.getString("Username", null);
        String password = preferences.getString("Password", null);
        boolean flag = false;

        if (username != null && password != null && password.length() > 3 && username.length() > 1){
            flag = true;
            //defino los datos del usuario en la sesion
            ApplicationConfig.getInstance().setUsername(username);
            ApplicationConfig.getInstance().setPassword(password);
            Logger.d("User: " + username + "\n Pass: " + password);
        }
        return flag;
    }
}//fin clase
