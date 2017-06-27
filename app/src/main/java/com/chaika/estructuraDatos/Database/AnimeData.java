package com.chaika.estructuraDatos.Database;

import com.chaika.estructuraDatos.malAppInfo.Anime;
import com.chaika.estructuraDatos.search.Entry;

import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_ANIMEDB_ID;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_BROADCAST;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_DURATION;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_END_DATE;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_ENGLISH;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_EPISODES;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_IMAGE;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_JAPANESE;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_LAST_UPDATE;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_RATING;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_RELATIVES;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_SCORE;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_SEASON;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_SINOPSIS;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_SOURCE;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_START_DATE;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_STATUS;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_SYNONYMS;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_TITLE;
import static com.chaika.databases.MalDBHelper.AnimeEntry.COLUMN_TYPE;

/**
 * Objeto utilizado en la recuperación de información de la base de datos.
 *
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
    private String title_japanese;
    private String lastUpdate;
    private boolean relatives;


    public AnimeData() {
    }

    public AnimeData(Entry animeSearch) {
        this.animeSearch = animeSearch;
    }

    public AnimeData(Anime animeMalinfo, Entry animeSearch, String season, String broadcast, String source, String duration, String rating, String title_japanese, String lastUpdate, boolean relatives) {
        this.animeMalinfo = animeMalinfo;
        this.animeSearch = animeSearch;
        this.season = season;
        Broadcast = broadcast;
        this.source = source;
        this.duration = duration;
        this.rating = rating;
        this.title_japanese = title_japanese;
        this.lastUpdate = lastUpdate;
        this.relatives = relatives;
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

    public String getTitle_japanese() {
        return title_japanese;
    }

    public void setTitle_japanese(String title_japanese) {
        this.title_japanese = title_japanese;
    }

    public static String[] getProjection(){
        String[] projection = {
         COLUMN_ANIMEDB_ID,
         COLUMN_TITLE,
         COLUMN_SYNONYMS,
         COLUMN_SINOPSIS,
         COLUMN_ENGLISH,
         COLUMN_JAPANESE,
         COLUMN_TYPE,
         COLUMN_EPISODES,
         COLUMN_STATUS,
         COLUMN_START_DATE,
         COLUMN_END_DATE,
         COLUMN_IMAGE,
         COLUMN_SCORE,
         COLUMN_SEASON,
         COLUMN_BROADCAST,
         COLUMN_SOURCE,
         COLUMN_DURATION,
         COLUMN_RATING,
         COLUMN_LAST_UPDATE,
         COLUMN_RELATIVES,
        };

        return projection;
    }


}//fin clase
