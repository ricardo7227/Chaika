package com.chaika;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaika.application.ApplicationConfig;
import com.chaika.application.ChaikaApplication;
import com.chaika.estructuraDatos.Database.AnimeData;
import com.chaika.estructuraDatos.EntryAnimeValues;
import com.chaika.estructuraDatos.api.Credentials;
import com.chaika.estructuraDatos.malAppInfo.Anime;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.estructuraDatos.search.AnimeSearch;
import com.chaika.fragmentos.AllSeriesFragment;
import com.chaika.interfaces.ApiResult;
import com.chaika.llamadasAPI.RestApiMal;
import com.chaika.utilidades.AnimeValuesXMLtoString;
import com.chaika.utilidades.Utilidades;
import com.orhanobut.logger.Logger;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Actividad lanzanda para ver en más detalle información personalizada de una serie.
 * Esta muestra una imagen difuminada de fondo, la sinopsis de la serie, la nota que este tiene en la comunidad, el número de episodios
 * vistos por usuario y su nota personal.
 * Además tiene dos botones en la parte superior que le permiten actualizar y borrar la serie de su cuenta.
 *
 *
 * Created by ricardo on 25/5/17.
 */

public class DetailSerie extends AppCompatActivity implements View.OnClickListener,ApiResult {

    ImageView cover;
    TextView title,sinopsis,score;
    EditText myEpisodes;
    AnimeData animeData;
    Anime myAnime;
    Button mScore;
    ArrayAdapter<String> adapter;
    String[] items;
    ProgressBar scoreProgress, sinopsisProgress;
    Toolbar toolbar;
    RelativeLayout baseLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        Intent intent = getIntent();
        int animeId = intent.getIntExtra("animeId",0); //recupera el identificador de la serie envia desde la actividad anterior.
        Logger.d(animeId);

        cover = (ImageView) findViewById(R.id.image_background_details);
        title = (TextView) findViewById(R.id.title_details);
        score = (TextView) findViewById(R.id.score_details);
        sinopsis = (TextView) findViewById(R.id.sinopsis);
        sinopsis.setMovementMethod(new ScrollingMovementMethod());
        myEpisodes = (EditText) findViewById(R.id.my_watched_episodes);
        scoreProgress = (ProgressBar) findViewById(R.id.progress_score);
        sinopsisProgress = (ProgressBar) findViewById(R.id.progress_sinopsis);
        toolbar = (Toolbar) findViewById(R.id.toolbar_details);
        toolbar.setTitle("");
        baseLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        setSupportActionBar(toolbar);

        mScore = (Button) findViewById(R.id.my_score_spiner);
        mScore.setOnClickListener(this);

        //recogemos los datos de DB
        animeData = ChaikaApplication.get(ApplicationConfig.getInstance().getActivity()).component().getData().getAnimeById(animeId);
        myAnime = ChaikaApplication.get(this).component().getData().getMyAnimeById(animeId);

        getInfoWeb(String.valueOf(animeData.getAnimeMalinfo().getSeries_animedb_id()));

        pintarPantalla();


    }//fin onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
        return true;
    }

    /**
     * se encarga de cargar toda la información en pantalla.
     */
    public void pintarPantalla(){

        title.setText(animeData.getAnimeMalinfo().getSeries_title());
        sinopsis.setText(animeData.getAnimeSearch().getAnime_sinopsis());
        myEpisodes.setText(String.valueOf(myAnime.getMy_watched_episodes()), TextView.BufferType.EDITABLE);
        Logger.d("episodios vistos: " + myAnime.getMy_watched_episodes());
        Logger.d("my score: " + myAnime.getMy_score());
        int miNota = (int) myAnime.getMy_score();

        items = getResources().getStringArray(R.array.my_score_spiner);
       adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, items);


        if (miNota < 1 || miNota > 10){
            mScore.setText(getString(R.string.details_activity_my_score));

        }else {
            mScore.setText(String.valueOf(miNota));
        }

        setBackgroudImage(Utilidades.getInstance().getLargeCoverVersion(animeData.getAnimeMalinfo().getSeries_image()));


    }

    /**
     * Define la imagen de fondo de la pantalla.
     * @param urlImage
     */
    public void setBackgroudImage(String urlImage){
        try {
            URL url = new URL(urlImage);

            RestApiMal.getInstance().getBitmapByUrl(url,getApplication())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ByteArrayOutputStream>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull ByteArrayOutputStream byteArrayOutputStream) {
                            Glide.with(getApplicationContext())
                                    .load(
                                            byteArrayOutputStream.toByteArray()
                                    )
                                    .centerCrop()
                                    .error(R.drawable.background_series)
                                    .crossFade(500)
                                    .into(cover);

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Glide.with(getApplicationContext())
                                    .load(
                                            R.drawable.background_series
                                    )
                                    .centerCrop()
                                    .crossFade(500)
                                    .into(cover);

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } catch(IOException e) {
            Logger.e(e.getMessage());
        }
    }


    /**
     *  Recoge la información relevante de la serie por WebScraping
     * @param animeId
     */
    public void getInfoWeb(String animeId){

        RestApiMal.getInstance().getInfoAnimePage(animeId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Document>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Document document) {
                //Seleciona la información que se mostrará por pantalla
                Elements scoreScrap = document.select("span[itemprop=\"ratingValue\"]"); //score webScraping
                Elements sinopsisScrap = document.select("span[itemprop=\"description\"]"); //sinopsis webScraping
                Elements usersCountScrap = document.select("span[itemprop=\"ratingCount\"]"); //users webScraping
                Logger.d(scoreScrap.text());
                Logger.d(sinopsisScrap.text());
                String scoreOriginal = String.format(getString(R.string.details_activity_score),scoreScrap.text(),usersCountScrap.text());
                SpannableString ss1=  new SpannableString(scoreOriginal);
                ss1.setSpan(new RelativeSizeSpan(2f),0,5,0);
                scoreProgress.setVisibility(View.GONE);
                score.setText(ss1);
                sinopsisProgress.setVisibility(View.GONE);
                sinopsis.setText(sinopsisScrap.text());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Logger.e("Error recuperando información: " + e.getCause());
            }

            @Override
            public void onComplete() {

            }
        });



    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.my_score_spiner){
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.details_activity_my_score_prompt))
                    .setAdapter(adapter, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            mScore.setText(items[which]);

                            dialog.dismiss();
                        }
                    }).create().show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_update:
                updateSerie(myEpisodes.getText().toString(),mScore.getText().toString());
                return true;
            case R.id.menu_delete:
                deleteSerie(String.valueOf(animeData.getAnimeMalinfo().getSeries_animedb_id()));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Realiza una llamada para borrar una serie de la lista.
     * @param malId
     */
    private void deleteSerie(String malId) {

        RestApiMal.getInstance().deleteAnimeMal(malId,
                ApplicationConfig.getInstance().getUsername(),
                ApplicationConfig.getInstance().getPassword(),this);



    }

    /**
     * Realiza una llamada para actualizar la información de una serie.
     * @param episodes
     * @param score
     */
    private void updateSerie(String episodes,String score) {
        EntryAnimeValues values = new EntryAnimeValues();
        values.setEpisode(episodes);
        values.setScore(score);
        String updateSerie = AnimeValuesXMLtoString.getInstance().convert(values);
        RestApiMal.getInstance().updateAnimeMal(String.valueOf(animeData.getAnimeMalinfo().getSeries_animedb_id()),updateSerie,
                ApplicationConfig.getInstance().getUsername(),ApplicationConfig.getInstance().getPassword(),this);

        }


    /****
     *
     * Respuestas del servidor
     *
     */
    @Override
    public void SuccessCall(MyAnimeList myAnimeList) {
        //nada aquí
    }

    @Override
    public void SuccessCall(Credentials credentials) {
//nada aquí
    }

    @Override
    public void SuccessCall(AnimeSearch animeSearch) {
//nada aquí
    }

    @Override
    public void genericResponse(String response) {
        Logger.d(response);
        //code
        if (response.equals("Deleted")){
            ChaikaApplication.get(this).component().getData()
                    .deleteById(String.valueOf(animeData.getAnimeMalinfo().getSeries_animedb_id()));

            Snackbar snackbar = Snackbar
                    .make(baseLayout,  getString(R.string.response_deleted,animeData.getAnimeMalinfo().getSeries_title()), Snackbar.LENGTH_LONG);
            snackbar.show();

        }else if (response.equals("Updated")){
            Anime animeUpdated = new Anime();
            animeUpdated.setSeries_animedb_id(animeData.getAnimeMalinfo().getSeries_animedb_id());
            animeUpdated.setMy_score(Float.parseFloat(mScore.getText().toString()));
            animeUpdated.setMy_watched_episodes(Integer.parseInt(myEpisodes.getText().toString()));

            ChaikaApplication.get(this).component().getData()
                    .updateSerieState(animeUpdated);

            Snackbar snackbar = Snackbar
                    .make(baseLayout, getString(R.string.response_updated,animeData.getAnimeMalinfo().getSeries_title()), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        //avisamos al fragmento anterior que hemos actualizado o borrado una serie.
        AllSeriesFragment.instance().genericResponse(response);

    }

    @Override
    public void ErrorCall(Throwable error) {
//nada aquí
    }
}//fin clase


