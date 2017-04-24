package com.chaika.Pruebas;


import android.util.Log;
import android.widget.TextView;

import com.chaika.MainActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

/**
 * Created by Gato on 23/04/2017.
 */


public class Test {

    public static void obs(){
        final String[] val = {};
        Observable.just("1", "2", "3");
        ArrayList<String> elem = new ArrayList<String>();
        elem.add("tres");
        elem.add("dos");
        elem.add("uno");

        Flowable.just("Hello world","GBI","ASUKA").subscribe(v -> Log.i("clase",v.toLowerCase()));
        int i;
//        Flowable.fromArray(elem).subscribe(c -> {
//            c.forEach(s -> {
//                Log.i("array", s.toUpperCase());
//            });
//            });
        Disposable sd = Flowable.just("hi","ni","mo").subscribe(s -> Log.i("Dis",s.toUpperCase()));
        sd.dispose();
        //emite un rango de enteros de 0 a 500
        Observable<Integer> observable = Observable.range(0, 500);
        Observer<Integer> observerint = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("TEST","Subscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d("TEST",integer.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("TEST","CompletedINT");

            }
        };
        observable.subscribe(observerint);

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

            }

            @Override
            public void onError(@NonNull Throwable e) {


            }

            @Override
            public void onComplete() {
                Log.d("TEST","CompletedINT");
            }
        } ;
        observableInf.subscribe(observerLong);

        Observable<String> flowman = Observable.just("texto","que es","emitido desde","flowable");

        Observer<String> observerman = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("TEST","Subscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d("TEST",s.toUpperCase());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("TEST","Error");
            }

            @Override
            public void onComplete() {
                Log.d("TEST","Completed");

            }
        };

        //la fusión

        flowman.subscribe(observerman);





    }

}//fin clase

