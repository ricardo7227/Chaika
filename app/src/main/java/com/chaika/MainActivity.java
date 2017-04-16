package com.chaika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.chaika.llamadasAPI.DataUserProfile;

public class MainActivity extends AppCompatActivity {

    TextView pantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //pantalla = (TextView) findViewById(R.id.pantalla);

        //new DataUserProfile().getMalUserProfile("ricardo7227");
        new DataUserProfile().getCredentials("ricardoAlexis","**");
        //pendiente controlar los strings que recibe, espacios en blanco
        new DataUserProfile().getAnimeSearch("Asterisk");



    }//fin onCreate
}//fin clase
