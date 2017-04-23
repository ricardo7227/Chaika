package com.chaika.Pruebas;


import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
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





    }

}//fin clase

