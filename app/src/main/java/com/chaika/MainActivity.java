package com.chaika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chaika.Pruebas.Test;
import com.chaika.llamadasAPI.DataUserProfile;

public class MainActivity extends AppCompatActivity {

    TextView pantalla;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantalla = (TextView) findViewById(R.id.pantalla);
        boton = (Button) findViewById(R.id.button);
        //soporte Lambda Expressions
        //boton.setOnClickListener(v -> pantalla.setText("click"));
        String val ="valor";
        boton.setOnClickListener(view -> showText(val));

        //new DataUserProfile().getMalUserProfile("ricardo7227");
        //crashea cuando no envia una password correcto, a revisar
        new DataUserProfile().getCredentials("ricardoAlexis","alexss00my**");
        //pendiente controlar los strings que recibe, espacios en blanco
        new DataUserProfile().getAnimeSearch("Asterisk");

        Test.obs();



    }//fin onCreate
    private void showText(String val){
        pantalla.setText("hi");
    }
}//fin clase
