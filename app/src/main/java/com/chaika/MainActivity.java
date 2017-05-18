package com.chaika;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chaika.application.AplicationConfig;
import com.chaika.application.ChaikaApplication;
import com.chaika.componentes.AppComponent;
import com.chaika.componentes.DaggerAppComponent;
import com.chaika.databases.Data;
import com.chaika.estructuraDatos.Database.AnimeData;
import com.chaika.estructuraDatos.malAppInfo.Anime;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.fragmentos.adaptadores.RecyclerViewAdaptador;
import com.chaika.fragmentos.adaptadores.SmartFragmentStatePagerAdapter;
import com.chaika.interfaces.ApiResult;
import com.chaika.llamadasAPI.RestApiMal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ApiResult {


    AppComponent component;

    private SmartFragmentStatePagerAdapter adapterViewPager;

    @Inject
    Data data;

    RecyclerViewAdaptador adapter;// = new RecyclerViewAdaptador();
    RecyclerView rvSeries;
    ArrayList<AnimeData> animeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_series_fragment);
/*
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new AdaptadorFragmentos(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
*/
        AplicationConfig.getInstance().setActivity(this);
        AplicationConfig.getInstance().setContext(this);
        AplicationConfig.getInstance().setApiResult(this);

        //////////dagger2
        component = DaggerAppComponent.builder()
               .databaseApplicationComponent(ChaikaApplication.get(this).component())
                .build();

        component.injectMain(this);

        // Lookup the recycler_view in activity layout
        rvSeries = (RecyclerView) findViewById(R.id.rvAnimeList);



        animeList = new ArrayList<AnimeData>();


        rvSeries.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvSeries.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdaptador(this, animeList);
        // Attach the adapter to the recycler_view to populate items
        rvSeries.setAdapter(adapter);

        RestApiMal.getInstance().getMalUserProfile("ricardoAlexis","all","anime", this);


    }//fin onCreate

    @Override
    public void SucessCall(MyAnimeList myAnimeList) {
        List<Anime> lista = myAnimeList.getAnimes();

        List<AnimeData> animeDataList = new ArrayList<>();
        AnimeData ad;
        for (Anime a:
                lista) {
            ad = new AnimeData();
            ad.setAnimeMalinfo(a);
            animeDataList.add(ad);
        }
        animeList = new ArrayList<AnimeData>(animeDataList);
        adapter = new RecyclerViewAdaptador(this, animeDataList);

        rvSeries.setAdapter(adapter);

    }







/* pantalla = (TextView) findViewById(R.id.pantalla);
        boton = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.text);
        editText = (EditText) findViewById(R.id.editText);
        //RXbin...
        RxTextView.textChanges(editText).subscribe(charSequence -> {textView.setText(charSequence);});
        String val ="valor";
        boton.setOnClickListener(view -> showText(val));
*/

        //soporte Lambda Expressions
        //boton.setOnClickListener(v -> pantalla.setText("click"));

        //new RestApiMal().getMalUserProfile("ricardo7227");
        //crashea cuando no envia una password correcto, a revisar


        //new RestApiMal().getCredentials("ricardoAlexis","alexss00my**");
        //pendiente controlar los strings que recibe, espacios en blanco
        //new RestApiMal().getAnimeSearch("Asterisk");


}//fin clase
