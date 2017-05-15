package com.chaika.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaika.R;
import com.chaika.estructuraDatos.Database.AnimeData;
import com.chaika.estructuraDatos.malAppInfo.Anime;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.fragmentos.adaptadores.RecyclerViewAdaptador;
import com.chaika.interfaces.ApiResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gato on 14/05/2017.
 */

public class AllSeriesFragment extends Fragment implements ApiResult{
    // Store instance variables
    private String title;
    private int page;
    RecyclerViewAdaptador adapter = new RecyclerViewAdaptador();
    RecyclerView rvSeries;
    ArrayList<AnimeData> animeList;

    // newInstance constructor for creating fragment with arguments
    public static AllSeriesFragment newInstance(int page, String title) {
        AllSeriesFragment fragmentFirst = new AllSeriesFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);

        return fragmentFirst;
    }


    private static AllSeriesFragment instance;

    // newInstance constructor for creating fragment with arguments
    public static AllSeriesFragment instance() {
        if (instance == null) {
            instance = new AllSeriesFragment();
        }
        return instance;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

        //RestApiMal.getInstance().getMalUserProfile("ricardoAlexis","all","anime",component.getData());
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_series_fragment, container, false);

//        TextView tvLabel = (TextView) view.findViewById(R.id.textV);
//        tvLabel.setText(page + " -- " + title);


        // Lookup the recycler_view in activity layout
         rvSeries = (RecyclerView) view.findViewById(R.id.rvAnimeList);

        // Initialize animeList
        AnimeData a = new AnimeData();
        Anime amala = new Anime();
        amala.setSeries_title("asterisk War");
        amala.setSeries_image("https://myanimelist.cdn-dena.com/images/anime/13/6441.jpg");
        a.setAnimeMalinfo(amala);
        a.setSeason("Spring 2013");
        a.setSource("Novela Ligera");

        AnimeData b = new AnimeData();
        Anime amalb = new Anime();
        amalb.setSeries_title("asterisk War Second Strike");
        amalb.setSeries_image("https://myanimelist.cdn-dena.com/images/anime/7/6803.jpg");
        b.setAnimeMalinfo(amalb);
        b.setSeason("Spring 2014");
        b.setSource("Novela Ligeras");

        AnimeData c = new AnimeData();
        Anime amalc = new Anime();
        amalc.setSeries_title("asterisk War Pi Strike");
        amalc.setSeries_image("https://myanimelist.cdn-dena.com/images/anime/10/75815.jpg");
        c.setAnimeMalinfo(amalc);
        c.setSeason("Spring 2017");
        c.setSource("Light Novel");

        AnimeData d = new AnimeData();
        Anime amald = new Anime();
        amald.setSeries_title("asterisk War Pi Strike");
        amald.setSeries_image("https://myanimelist.cdn-dena.com/images/anime/10/75815.jpg");
        d.setAnimeMalinfo(amald);
        d.setSeason("Spring 2017");
        d.setSource("Light Novel");

         animeList = new ArrayList<AnimeData>();
/*
        animeList.add(a);
        animeList.add(b);
        animeList.add(c);
        animeList.add(d);
*/
        // Create adapter passing in the sample user data
         //adapter = new RecyclerViewAdaptador(getContext(), animeList);
        // Attach the adapter to the recycler_view to populate items
        rvSeries.setAdapter(adapter);

//        RecyclerView.ItemDecoration decoration = new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//                outRect.top = 0;
//                outRect.bottom = 0;
//            }
//        };
//        rvSeries.addItemDecoration(decoration);
        // Set layout manager to position the items
        rvSeries.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

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
        // Create adapter passing in the sample user data
        //adapter = new RecyclerViewAdaptador(getContext(), animeDataList);

        adapter.setListMAL(animeList);

        // Attach the adapter to the recycler_view to populate items
        //rvSeries.setAdapter(adapter);



    }
}

