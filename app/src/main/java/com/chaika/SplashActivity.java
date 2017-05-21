package com.chaika;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.chaika.application.ApplicationConfig;
import com.orhanobut.logger.Logger;

/**
 * Created by Gato on 20/05/2017.
 * Esta actividad comprueba si temos los datos del usuario
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
