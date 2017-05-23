package com.chaika.databases;


import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.chaika.estructuraDatos.Database.AnimeData;
import com.chaika.estructuraDatos.Database.UserData;
import com.chaika.estructuraDatos.malAppInfo.Anime;
import com.chaika.estructuraDatos.malAppInfo.MyAnimeList;
import com.chaika.estructuraDatos.malAppInfo.MyInfo;
import com.chaika.estructuraDatos.search.Entry;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Gato on 01/05/2017.
 */

public class Data {
    private final String TAG = getClass().getName();


    private MalDBHelper malDBHelper;
    private static Data instance;
    @Inject
    public Data(MalDBHelper malDBHelper){

        this.malDBHelper = malDBHelper;
    }
    public Data(){}

    public static Data instance (){
        if (instance == null){
            instance = new Data();
        }
        return instance;
    }



    /**
     * Agrega los datos del usuario en la base de datos
     * @param userData
     */
    public void upsert (UserData userData){
        SQLiteDatabase db = malDBHelper.getWritableDatabase();
        try {
            db.insert(MalDBHelper.UserProfileEntry.TABLE_NAME,null,userData.getContentValues());
            Logger.d(userData.getMyInfo().toString());
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }finally {
            malDBHelper.close();
            db.close();
            db = null;
        }
    }

    /***
     * Añade la lista de series
      * @param myAnimeList
     */
    public void upsert(MyAnimeList myAnimeList){
        SQLiteDatabase db = malDBHelper.getWritableDatabase();
        try{
            for (Anime a: myAnimeList.getAnimes()) {
                Logger.d(a.toString());
                db.insert(MalDBHelper.AnimeEntry.TABLE_NAME,null,a.getContentValuesSerie());
            }
        }catch (Exception e){
            Logger.e(e.getMessage());
        }finally {
            malDBHelper.close();
            db.close();
            db = null;
        }

    }//fin upsert

    /**
     * Añade una única serie a la base de datos
     * @param anime
     */
    public void upsert(Anime anime){
        SQLiteDatabase db = malDBHelper.getWritableDatabase();
        try{
            Logger.d(anime.toString());
            db.insert(MalDBHelper.AnimeEntry.TABLE_NAME,null,anime.getContentValuesSerie());

        }catch (Exception e){
            Logger.e(e.getMessage());
        }finally {
            malDBHelper.close();
            db.close();
            db = null;
        }

    }//fin upsert


    public void upsert(Entry anime){
        SQLiteDatabase db = malDBHelper.getWritableDatabase();
        try{
            Logger.d(anime.toString());
            db.insert(MalDBHelper.AnimeEntry.TABLE_NAME,null,anime.getContentValuesSerie());

        }catch (Exception e){
            Logger.e(e.getMessage());
        }finally {
            malDBHelper.close();
            db.close();
            db = null;
        }

    }//fin upsert

//FIN UPSERTS


    /***
     *
     * GETTERS
     *
     */

    public UserData getUser(){
        SQLiteDatabase db = malDBHelper.getReadableDatabase();
        UserData userData = null;

        String[] whereValues =  null;
        String where = null;

        String sortOrder = null;

        Cursor c = null;
        try {
            c = db.query(
                    MalDBHelper.UserProfileEntry.TABLE_NAME,  // The table to query
                    UserData.getProjection(),                         // The columns to return
                    where,                                     // The columns for the WHERE clause
                    whereValues,                              // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    sortOrder                                 // The sort order
            );

            if (c != null && c.getCount() > 0) {
                int _id     = c.getColumnIndex(MalDBHelper.UserProfileEntry._ID);
                int id      = c.getColumnIndex(MalDBHelper.UserProfileEntry.COLUMN_MAL_ID);
                int name    = c.getColumnIndex(MalDBHelper.UserProfileEntry.COLUMN_NAME);
                int watching   = c.getColumnIndex(MalDBHelper.UserProfileEntry.COLUMN_WATCHING);
                int completed  = c.getColumnIndex(MalDBHelper.UserProfileEntry.COLUMN_COMPLETED);
                int onHold  = c.getColumnIndex(MalDBHelper.UserProfileEntry.COLUMN_ON_HOLD);
                int dropped  = c.getColumnIndex(MalDBHelper.UserProfileEntry.COLUMN_DROPPED);
                int planToWatch  = c.getColumnIndex(MalDBHelper.UserProfileEntry.COLUMN_PLAN_TO_WATCH);
                int daysSpent = c.getColumnIndex(MalDBHelper.UserProfileEntry.COLUMN_DAYS_SPENT_WATCHING);
                int rewatched = c.getColumnIndex(MalDBHelper.UserProfileEntry.COLUMN_REWATCHED);
                int urlPerfil = c.getColumnIndex(MalDBHelper.UserProfileEntry.COLUMN_URL_PERFIL_FOTO);
                int urlBackground = c.getColumnIndex(MalDBHelper.UserProfileEntry.COLUMN_URL_BACKGROUND_FOTO);
                int joined = c.getColumnIndex(MalDBHelper.UserProfileEntry.COLUMN_JOINED);
                int lastUpdate = c.getColumnIndex(MalDBHelper.UserProfileEntry.COLUMN_LAST_UPDATE);

                if (c != null && c.moveToNext()) {
                    userData = new UserData();
                    MyInfo myInfo = new MyInfo();

                    myInfo.setUser_id(c.getLong(id));
                    myInfo.setUser_name(c.getString(name));
                    myInfo.setUser_watching(c.getInt(watching));
                    myInfo.setUser_completed(c.getInt(completed));
                    myInfo.setUser_onhold(c.getInt(onHold));
                    myInfo.setUser_dropped(c.getInt(dropped));
                    myInfo.setUser_plantowatch(c.getInt(planToWatch));
                    myInfo.setUser_days_spent_watching(c.getLong(daysSpent));

                    userData.setMyInfo(myInfo);

                    userData.setRewwatched(c.getInt(rewatched));
                    userData.setUrlPerfilUsuario(c.getString(urlPerfil));
                    userData.setUrlPerfilBackground(c.getString(urlBackground));
                    userData.setJoined(c.getString(joined));
                    userData.setLastUpdate(c.getString(lastUpdate));


                }

            }
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }finally {
            if( c != null) c.close();
            malDBHelper.close();
            db.close();
            db = null;
        }

        return userData;

    }//fin getUser

    /**
     *
     * @return tamaño de la lista de series
     */
    public int getSizeListMal(){
        SQLiteDatabase db = malDBHelper.getReadableDatabase();
        Cursor c = null;
        int listSize = -1;
        try{

            listSize = (int) DatabaseUtils.queryNumEntries(db, MalDBHelper.AnimeEntry.TABLE_NAME);

        }catch (Exception e){
            Logger.e(e.getMessage());
        }finally {
            malDBHelper.close();
            db.close();
            db = null;
        }
        return listSize;
    }//fin upsert


    /**
     * Recupera los datos de una serie según su ID
     *
     * @param animeIdMal
     * @return
     */
    public AnimeData getAnimeById(int animeIdMal){
        SQLiteDatabase db = malDBHelper.getReadableDatabase();
        AnimeData animeData = null;
        Anime anime;
        Entry entry;
        Cursor c = null;
        try{
            String[] whereValues =  new String[]{String.valueOf(animeIdMal)};
            String where = MalDBHelper.AnimeEntry.COLUMN_ANIMEDB_ID + " = ?";

            String sortOrder = null;

                c = db.query(
                        MalDBHelper.AnimeEntry.TABLE_NAME,  // The table to query
                        AnimeData.getProjection(),                         // The columns to return
                        where,                                     // The columns for the WHERE clause
                        whereValues,                              // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        sortOrder                                 // The sort order
                );
            if (c != null && c.moveToNext()) {
                long animeID = c.getLong(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_ANIMEDB_ID));
                String title = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_TITLE));
                String synonyms = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_SYNONYMS));
                String sinopsis = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_SINOPSIS));
                String title_english = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_ENGLISH));
                String title_japanese = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_JAPANESE));
                int type = c.getInt(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_TYPE));
                int episodes = c.getInt(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_EPISODES));
                int status = c.getInt(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_STATUS));
                String startDate = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_START_DATE));
                String endDate = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_END_DATE));
                String image = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_IMAGE));
                float score = c.getFloat(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_SCORE));
                String season = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_SEASON));
                String broadcast = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_BROADCAST));
                String source = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_SOURCE));
                String duration = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_DURATION));
                String rating = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_RATING));
                String last_update = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_LAST_UPDATE));
                boolean relatives = c.getInt(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_RELATIVES)) > 0;


                anime = new Anime(type, status, synonyms, image,title, startDate, animeID, endDate, episodes);

                entry = new Entry(animeID,title,sinopsis,endDate,score,synonyms,image,title_english,episodes,startDate);

                animeData = new AnimeData(anime,entry,season,broadcast,source,duration,rating,title_japanese,last_update,relatives);

            }



        }catch (Exception e){
            Logger.e(e.getMessage());
        }finally {
            malDBHelper.close();
            db.close();
            db = null;
        }
        return animeData;
    }

    /***
     *
     * Devuelve una lista con todas las series almacenadas en la base de datos
     *
     * @return array
     */
    public List<AnimeData> getMyAnimeList(){
        SQLiteDatabase db = malDBHelper.getReadableDatabase();
        List<AnimeData> myAnimeList = null;
        AnimeData animeData = null;
        Anime anime;
        Entry entry;
        Cursor c = null;
        try{
            String[] whereValues =  null;
            String where = null;

            String sortOrder = null;

                c = db.query(
                        MalDBHelper.AnimeEntry.TABLE_NAME,  // The table to query
                        AnimeData.getProjection(),                         // The columns to return
                        where,                                     // The columns for the WHERE clause
                        whereValues,                              // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        sortOrder                                 // The sort order
                );
            if (c != null && c.moveToNext()) {
                c.moveToFirst();
                myAnimeList = new ArrayList<>();
                do {
                    long animeID = c.getLong(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_ANIMEDB_ID));
                    String title = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_TITLE));
                    String synonyms = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_SYNONYMS));
                    String sinopsis = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_SINOPSIS));
                    String title_english = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_ENGLISH));
                    String title_japanese = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_JAPANESE));
                    int type = c.getInt(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_TYPE));
                    int episodes = c.getInt(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_EPISODES));
                    int status = c.getInt(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_STATUS));
                    String startDate = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_START_DATE));
                    String endDate = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_END_DATE));
                    String image = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_IMAGE));
                    float score = c.getFloat(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_SCORE));
                    String season = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_SEASON));
                    String broadcast = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_BROADCAST));
                    String source = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_SOURCE));
                    String duration = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_DURATION));
                    String rating = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_RATING));
                    String last_update = c.getString(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_LAST_UPDATE));
                    boolean relatives = c.getInt(c.getColumnIndex(MalDBHelper.AnimeEntry.COLUMN_RELATIVES)) > 0;


                    anime = new Anime(type, status, synonyms, image, title, startDate, animeID, endDate, episodes);

                    entry = new Entry(animeID, title, sinopsis, endDate, score, synonyms, image, title_english, episodes, startDate);

                    animeData = new AnimeData(anime, entry, season, broadcast, source, duration, rating, title_japanese, last_update, relatives);

                    myAnimeList.add(animeData);

                }while (c.moveToNext());

            }



        }catch (Exception e){
            Logger.e(e.getMessage());
        }finally {
            malDBHelper.close();
            db.close();
            db = null;
        }
        return myAnimeList;
    }




}//fin clase
