package com.chaika.fragmentos.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaika.R;
import com.chaika.estructuraDatos.Database.AnimeData;

import java.util.List;

/**
 * Created by Gato on 14/05/2017.
 */
// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class RecyclerViewAdaptador extends
        RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView title_series;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            title_series = (TextView) itemView.findViewById(R.id.title_series);

        }
    }

    // Store a member variable for the contacts
    private List<AnimeData> animeList;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public RecyclerViewAdaptador(Context context, List<AnimeData> animes) {
        animeList = animes;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public RecyclerViewAdaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.serie_item_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecyclerViewAdaptador.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        AnimeData animeData = animeList.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.title_series;
        textView.setText(animeData.getAnimeMalinfo().getSeries_title());

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return animeList.size();
    }

}//fin clase