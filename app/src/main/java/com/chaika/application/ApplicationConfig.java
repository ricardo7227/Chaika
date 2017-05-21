package com.chaika.application;

import android.app.Activity;
import android.content.Context;

import com.chaika.interfaces.ApiResult;

/**
 * Created by Gato on 15/05/2017.
 */

public class ApplicationConfig {
    private static ApplicationConfig instance;
    private Activity activity;
    private Context context;

    private ApiResult apiResult;

    private String Username;
    private String password;


    public static ApplicationConfig getInstance(){
        if (instance == null){
            instance = new ApplicationConfig();
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

    public ApiResult getApiResult() {
        return apiResult;
    }

    public void setApiResult(ApiResult apiResult) {
        this.apiResult = apiResult;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}//fin clase
