package com.chaika;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chaika.Pruebas.Test;
import com.chaika.llamadasAPI.DataUserProfile;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static TextView pantalla;
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
///////////////////////////////////////////////////////////


        //emite enteros infinitamente cada segundo
        Observable<Long> observableInf = Observable.interval(1, TimeUnit.SECONDS);
        Observer<Long> observerLong = new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("TEST","Subscribe");
            }

            @Override
            public void onNext(@NonNull Long aLong) {
                Log.d("TEST",aLong.toString());
                pantalla.setText(aLong.toString());

            }

            @Override
            public void onError(@NonNull Throwable e) {


            }

            @Override
            public void onComplete() {
                Log.d("TEST","CompletedINT");
            }
        } ;
        //muestra por pantalla un numero ascendente infinitamente
        observableInf.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observerLong);


    }//fin onCreate
    private void showText(String val){
        pantalla.setText("hi");
    }
}//fin clase
