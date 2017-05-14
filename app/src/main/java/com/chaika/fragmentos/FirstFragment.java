package com.chaika.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaika.R;
import com.chaika.estructuraDatos.Database.AnimeData;
import com.chaika.estructuraDatos.malAppInfo.Anime;
import com.chaika.fragmentos.adaptadores.RecyclerViewAdaptador;

import java.util.ArrayList;

/**
 * Created by Gato on 14/05/2017.
 */

public class FirstFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;




    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_series_fragment, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.textV);
        tvLabel.setText(page + " -- " + title);



        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) view.findViewById(R.id.rvAnimeList);

        // Initialize animeList
        AnimeData a = new AnimeData();
        Anime amala = new Anime();
        amala.setSeries_title("asterisk War");
        a.setAnimeMalinfo(amala);
        a.setSeason("Spring 2013");
        a.setSource("Novela Ligera");

        AnimeData b = new AnimeData();
        Anime amalb = new Anime();
        amalb.setSeries_title("asterisk War Second Strike");
        b.setAnimeMalinfo(amala);
        b.setSeason("Spring 2014");
        b.setSource("Novela Ligeras");

        AnimeData c = new AnimeData();
        Anime amalc = new Anime();
        amalc.setSeries_title("asterisk War Pi Strike");
        c.setAnimeMalinfo(amala);
        c.setSeason("Spring 2017");
        c.setSource("Light Novel");
        ArrayList<AnimeData> animeList = new ArrayList<AnimeData>();

        animeList.add(a);
        animeList.add(b);
        animeList.add(c);

        // Create adapter passing in the sample user data
        RecyclerViewAdaptador adapter = new RecyclerViewAdaptador(getContext(), animeList);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(getContext()));
        // That's all!


        return view;
    }
}