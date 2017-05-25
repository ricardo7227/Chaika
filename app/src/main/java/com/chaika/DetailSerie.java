package com.chaika;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

/**
 * Created by ricardo on 25/5/17.
 */

public class DetailSerie extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int animeId = intent.getIntExtra("animeId",0);
        Logger.d(animeId);
    }


}//fin clase

