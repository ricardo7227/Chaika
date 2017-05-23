package com.chaika;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.chaika.application.ApplicationConfig;
import com.chaika.application.ChaikaApplication;
import com.chaika.componentes.AppComponent;
import com.chaika.componentes.DaggerAppComponent;
import com.chaika.databases.Data;
import com.chaika.estructuraDatos.Database.AnimeData;
import com.chaika.fragmentos.AllSeriesFragment;
import com.chaika.fragmentos.SecondFragment;
import com.chaika.fragmentos.adaptadores.ViewPagerAdapter;
import com.chaika.llamadasAPI.RestApiMal;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {


    AppComponent component;

    public static ProgressBar progressBar;


    @Inject
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_main);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        ApplicationConfig.getInstance().setActivity(this);
        ApplicationConfig.getInstance().setContext(this);



        //////////dagger2
        component = DaggerAppComponent.builder()
               .databaseApplicationComponent(ChaikaApplication.get(this).component())
                .build();

        component.injectMain(this);

        int sizeList = ChaikaApplication.get(this).component().getData().getSizeListMal();
        if (sizeList == 0) {
            RestApiMal.getInstance().getMalUserProfile(ApplicationConfig.getInstance().getUsername(),"all","anime", AllSeriesFragment.instance());
        }
        AnimeData animeData = ChaikaApplication.get(this).component().getData().getAnimeById(16397);

        initViewPagerAndTabs();

    }//fin onCreate



    private void initViewPagerAndTabs() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFrag(AllSeriesFragment.instance(),getString(R.string.app_main_tab_series));
        pagerAdapter.addFrag(SecondFragment.newInstance(2,"page"),"VISTOS");

        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }



}//fin clase

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
