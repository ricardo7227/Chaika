package com.chaika.Pruebas;

/**
 * Created by Gato on 23/04/2017.
 */


    public interface Observer<T> {
        void onNext(T t); // called for each "emitted" item
        void onCompleted(); // will not be called if onError() is called
        void onError(Throwable e); // called if there's an error
    }

