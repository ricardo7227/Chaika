package com.chaika;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaika.application.ChaikaApplication;
import com.chaika.componentes.AppComponent;
import com.chaika.componentes.DaggerAppComponent;
import com.chaika.databases.Data;
import com.chaika.fragmentos.adaptadores.MyPagerAdapter;
import com.chaika.fragmentos.adaptadores.SmartFragmentStatePagerAdapter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    public static TextView pantalla,textView;
    EditText editText;
    Button boton;
    AppComponent component;

    private SmartFragmentStatePagerAdapter adapterViewPager;
    @Inject
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_main);
       /* pantalla = (TextView) findViewById(R.id.pantalla);
        boton = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.text);
        editText = (EditText) findViewById(R.id.editText);
        //RXbin...
        RxTextView.textChanges(editText).subscribe(charSequence -> {textView.setText(charSequence);});
        String val ="valor";
        boton.setOnClickListener(view -> showText(val));
*/
        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);


        //////////dagger2
        component = DaggerAppComponent.builder()
               .databaseApplicationComponent(ChaikaApplication.get(this).component())
                .build();

        component.injectMain(this);




    }//fin onCreate


    private void showText(String val){
        pantalla.setText("hi");
    }






        //soporte Lambda Expressions
        //boton.setOnClickListener(v -> pantalla.setText("click"));

        //new RestApiMal().getMalUserProfile("ricardo7227");
        //crashea cuando no envia una password correcto, a revisar


        //new RestApiMal().getCredentials("ricardoAlexis","alexss00my**");
        //pendiente controlar los strings que recibe, espacios en blanco
        //new RestApiMal().getAnimeSearch("Asterisk");


}//fin clase
