package com.chaika;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaika.estructuraDatos.api.Credentials;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.estructuraDatos.search.AnimeSearch;
import com.chaika.interfaces.ApiResult;
import com.chaika.llamadasAPI.RestApiMal;
import com.chaika.utilidades.BlurBuilder;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.orhanobut.logger.Logger;

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
    ImageView backgroundImage;
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

        login_header = (TextView)findViewById(R.id.login_header);
        submit = (TextView) findViewById(R.id.login);
        submit.setVisibility(View.GONE);
        submit.setOnClickListener(this);
        //creamos efecto blur en el fondo del login
        Bitmap bitmapBackgroud = BitmapFactory.decodeResource(getResources(),R.drawable.background_login);
        Bitmap blur = BlurBuilder.blur( this, bitmapBackgroud );

        backgroundImage = (ImageView) findViewById(R.id.image_background);
        backgroundImage.setImageBitmap(blur);

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
        }
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
        login_header.setText("comprobado!!!");

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
        Logger.d(error.getMessage());
    }
}//fin clase