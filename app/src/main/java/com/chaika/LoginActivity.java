package com.chaika;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaika.application.ApplicationConfig;
import com.chaika.estructuraDatos.api.Credentials;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.estructuraDatos.search.AnimeSearch;
import com.chaika.interfaces.ApiResult;
import com.chaika.llamadasAPI.RestApiMal;
import com.chaika.utilidades.BlurBuilder;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.orhanobut.logger.Logger;

import java.io.ByteArrayOutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

import static android.text.TextUtils.isEmpty;

/**
 * Created by Gato on 20/05/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,ApiResult{

    EditText username,password;
    TextView submit,login_header;

    ImageView backgroundImage,logo;
    Timer timer = null;
    int i = 0;
    Bitmap photoArray[];
    int resourceArray [];
    Bitmap bitmapBackgroud;
    Bitmap blurBitmapBackground;

    ByteArrayOutputStream stream;

    //el observer trata lo emitido por los observables
    private DisposableSubscriber<Boolean> disposableObserver = null;

    //Observables encargados de emitir cadenas de caracteres
    private Flowable<CharSequence> userNameChangeObservable;
    private Flowable<CharSequence> passwordChangeObservable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        logo = (ImageView) findViewById(R.id.logo);

        login_header = (TextView)findViewById(R.id.login_header);
        submit = (TextView) findViewById(R.id.login);
        submit.setVisibility(View.GONE);
        submit.setOnClickListener(this);

        resourceArray = new int[]{R.drawable.background_login,R.drawable.background_login2,R.drawable.background_login3,
                R.drawable.background_login4,R.drawable.background_login5,R.drawable.background_login6};
        backgroundImage = (ImageView) findViewById(R.id.image_background);




        timer = new Timer("RefreshBackground");
        timer.schedule(updateBackGround, 1000L, 10000L);//here 6000L is starting //delay and 3000L is periodic delay after starting delay



        userNameChangeObservable =
                RxTextView.textChanges(username).skip(1).toFlowable(BackpressureStrategy.LATEST);
        passwordChangeObservable =
                RxTextView.textChanges(password).skip(5).toFlowable(BackpressureStrategy.LATEST);

        combineEvents();


    }//fin on create

    private void combineEvents() {

        disposableObserver =
                new DisposableSubscriber<Boolean>() {
                    @Override
                    public void onNext(Boolean formValid) {
                        if (formValid) {
                            submit.setVisibility(View.VISIBLE);
                            login_header.setText(getResources().getText(R.string.login_activity_ready_header));
                        } else {
                            submit.setVisibility(View.GONE);
                            login_header.setText(getResources().getText(R.string.login_activity_error_header));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                        Logger.d("complete login");
                    }
                };

        Flowable.combineLatest(
                userNameChangeObservable,
                passwordChangeObservable,
                (newUserName, newPassword) -> {
            boolean usernameValid = !isEmpty(newUserName) && newUserName.length() > 1 && newUserName.length() < 17 &&
                    Pattern.matches("([A-Za-z0-9_-]+)",newUserName);
            if (!usernameValid) {
                username.setError(getResources().getText(R.string.login_activity_error_username));
            }

            boolean passValid = !isEmpty(newPassword) && newPassword.length() > 5;
            if (!passValid) {
                password.setError(getResources().getText(R.string.login_activity_error_password));
            }

            return usernameValid && passValid;
        } )
                .subscribe(disposableObserver);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.login){
            Logger.d(username.getText().toString() + ":" +password.getText().toString());
            //llamada para comprobar la credenciales del usuario
            RestApiMal.getInstance().getCredentials(username.getText().toString().trim(),password.getText().toString().trim(),this);

            submit.setText(getResources().getText(R.string.login_activity_button_checking));

            //rotación del logo
            RotateAnimation rotateAnimation = new RotateAnimation(30, 90,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setDuration(500);
            rotateAnimation.setRepeatCount(Animation.INFINITE);

            logo.startAnimation(rotateAnimation);
        }
    }

    /**
     * Realiza un bucle para el fondo, recorre un array de imágenes, agrega un efecto blur y va rotando
     */
    private TimerTask updateBackGround = new TimerTask() {

        @Override
        public void run() {

            LoginActivity.this.runOnUiThread(new Runnable() {

                @Override
                public void run() { // TODO Auto-generated method stub
                    bitmapBackgroud = BitmapFactory.decodeResource(getResources(),resourceArray[i]);
                    //agrega el efecto blur
                    blurBitmapBackground = BlurBuilder.blur(getApplication(), bitmapBackgroud);

                    //Glide no soporta bitmap, esto es un rodeo
                    stream = new ByteArrayOutputStream();
                    blurBitmapBackground.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    Glide.with(getApplicationContext())
                            .load(stream.toByteArray())
                            .crossFade(600)
                            .into(backgroundImage);
                    i++;
                    if (i > 5)
                    {
                        i = 0;
                    }
                }

            });
        }
    };

    /**
     * Define las credenciales del usuario necesarias para el uso de la aplicación
     * @param username
     * @param password
     */
    public void setCredentialsSystem(String username,String password){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Username",username);
        editor.putString("Password",password);

        ApplicationConfig.getInstance().setUsername(username);
        ApplicationConfig.getInstance().setPassword(password);

        Logger.d("User: " + username + "\n Pass: " + password);
        editor.apply();
    }


    /**
     *
     *Respuestas del Servidor
     */
    @Override
    public void SuccessCall(MyAnimeList myAnimeList) {
        //nada aquí

    }

    @Override
    public void SuccessCall(Credentials credentials) {
        login_header.setTextSize(12);
        login_header.setText(getResources().getText(R.string.login_activity_ok_header_llamada));
        logo.clearAnimation();
        submit.setText(getResources().getText(R.string.app_ok));
        //definimos las credenciales del usuario en el entorno
        setCredentialsSystem(username.getText().toString().trim(),password.getText().toString().trim());
        //lanzamos MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void SuccessCall(AnimeSearch animeSearch) {
        //nada aquí
    }

    @Override
    public void genericResponse(String response) {
        //nada aquí
    }

    @Override
    public void ErrorCall(Throwable error) {
        login_header.setTextSize(13);
        login_header.setText(getResources().getText(R.string.login_activity_error_header_llamada));
        logo.clearAnimation();

        submit.setText(getResources().getText(R.string.login_activity_button_login));

        Logger.d(error.getMessage());
    }
}//fin clase