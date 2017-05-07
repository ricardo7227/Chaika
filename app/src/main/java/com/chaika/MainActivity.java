package com.chaika;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaika.application.ChaikaApplication;
import com.chaika.componentes.AppComponent;
import com.chaika.componentes.DaggerAppComponent;
import com.chaika.databases.Data;
import com.jakewharton.rxbinding2.widget.RxTextView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    public static TextView pantalla,textView;
    EditText editText;
    Button boton;

    @Inject
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantalla = (TextView) findViewById(R.id.pantalla);
        boton = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.text);
        editText = (EditText) findViewById(R.id.editText);
        //RXbin...
        RxTextView.textChanges(editText).subscribe(charSequence -> {textView.setText(charSequence);});

        //soporte Lambda Expressions
        //boton.setOnClickListener(v -> pantalla.setText("click"));
        String val ="valor";
        boton.setOnClickListener(view -> showText(val));

        //new RestApiMal().getMalUserProfile("ricardo7227");
        //crashea cuando no envia una password correcto, a revisar


        //new RestApiMal().getCredentials("ricardoAlexis","alexss00my**");
        //pendiente controlar los strings que recibe, espacios en blanco
        //new RestApiMal().getAnimeSearch("Asterisk");

      //A realizar: modulizar la aplicación a estilo de Dagger2

        //////////dagger2
       AppComponent component = DaggerAppComponent.builder()
               .databaseApplicationComponent(ChaikaApplication.get(this).component())
                .build();

        component.injectMain(this);
        //accedo a la base de datos, modo prueba




    }//fin onCreate


    private void showText(String val){
        pantalla.setText("hi");
    }
}//fin clase
