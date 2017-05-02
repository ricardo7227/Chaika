package com.chaika.estructuraDatos.Database;

import com.chaika.estructuraDatos.malAppInfo.Anime;
import com.chaika.estructuraDatos.search.Entry;

/**
 * Created by Gato on 30/04/2017.
 */

public class AnimeData {
    //fusión de la información de dos objetos que complementan su información
    //usado para la base de datos
    private Anime animeMalinfo;
    private Entry animeSearch;
    private String season;
    private String Broadcast;
    private String source;
    private String duration;
    private String rating;
    private String lastUpdate;
    private boolean relatives;


    public AnimeData() {
    }

    public Anime getAnimeMalinfo() {
        return animeMalinfo;
    }

    public void setAnimeMalinfo(Anime animeMalinfo) {
        this.animeMalinfo = animeMalinfo;
    }

    public Entry getAnimeSearch() {
        return animeSearch;
    }

    public void setAnimeSearch(Entry animeSearch) {
        this.animeSearch = animeSearch;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getBroadcast() {
        return Broadcast;
    }

    public void setBroadcast(String broadcast) {
        Broadcast = broadcast;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isRelatives() {
        return relatives;
    }

    public void setRelatives(boolean relatives) {
        this.relatives = relatives;
    }
}//fin clase
