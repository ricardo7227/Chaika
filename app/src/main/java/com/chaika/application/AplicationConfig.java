package com.chaika.application;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Gato on 15/05/2017.
 */

public class AplicationConfig{
    private static AplicationConfig instance;
    private Activity activity;
    private Context context;


    public static AplicationConfig getInstance(){
        if (instance == null){
            instance = new AplicationConfig();
        }
        return instance;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}//fin clase
