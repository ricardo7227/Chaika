package com.chaika.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import javax.inject.Inject;

/**
 * Created by Gato on 30/04/2017.
 */

public class MalDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MAL-bd";

    private static final String TEXT_TYPE       = " TEXT";
    private static final String INTEGER_TYPE    = " INTEGER";
    private static final String REAL_TYPE       = " REAL";

    private static final String COMMA_SEP = ",";


    private static MalDBHelper sInstance;


    public static abstract class UserProfileEntry implements BaseColumns {
        public static final String TABLE_NAME = "USER";
        public static final String COLUMN_MAL_ID = "MAL_ID";
        public static final String COLUMN_NAME = "NAME";
        public static final String COLUMN_WATCHING = "WATCHING";
        public static final String COLUMN_COMPLETED = "COMPLETED";
        public static final String COLUMN_ON_HOLD = "ON_HOLD";
        public static final String COLUMN_DROPPED = "DROPPED";
        public static final String COLUMN_PLAN_TO_WATCH = "PLAN_TO_WATCH";
        public static final String COLUMN_DAYS_SPENT_WATCHING = "DAYS_SPENT_WATCHING";
        public static final String COLUMN_REWATCHED = "REWATCHED";
        public static final String COLUMN_URL_PERFIL_FOTO = "URL_PERFIL_FOTO";
        public static final String COLUMN_URL_BACKGROUND_FOTO = "URL_BACKGROUND_FOTO";
        public static final String COLUMN_GENDER = "GENDER";
        public static final String COLUMN_JOINED = "JOINED";
        public static final String COLUMN_LAST_UPDATE = "LAST_UPDATE";

    }
    public static abstract class AnimeEntry implements BaseColumns {
        public static final String TABLE_NAME = "ANIME";
        public static final String COLUMN_ANIMEDB_ID = "ANIMEDB_ID";
        public static final String COLUMN_TITLE = "ANIME_TITLE";
        public static final String COLUMN_SYNONYMS = "ANIME_SYNONYMS";
        public static final String COLUMN_SYNOPSIS = "ANIME_SYNOPSIS";
        public static final String COLUMN_ENGLISH = "ANIME_ENGLISH";
        public static final String COLUMN_JAPANESE = "ANIME_JAPANESE";
        public static final String COLUMN_TYPE = "ANIME_TYPE";
        public static final String COLUMN_EPISODES = "ANIME_EPISODES";
        public static final String COLUMN_STATUS = "ANIME_STATUS";
        public static final String COLUMN_START_DATE = "ANIME_START_DATE";
        public static final String COLUMN_END_DATE = "ANIME_END_DATE";
        public static final String COLUMN_IMAGE = "ANIME_IMAGE";
        public static final String COLUMN_SCORE = "ANIME_SCORE";
        public static final String COLUMN_SEASON = "ANIME_SEASON";
        public static final String COLUMN_BROADCAST = "ANIME_BROADCAST";
        public static final String COLUMN_SOURCE = "ANIME_SOURCE";
        public static final String COLUMN_DURATION = "ANIME_DURATION";
        public static final String COLUMN_LAST_UPDATE = "ANIME_LAST_UDDATE";
        public static final String COLUMN_RELATIVES = "ANIME_RELATIVES";

    }

    private static final String SQL_CREATE_USER_ENTRIES =
            "CREATE TABLE " + UserProfileEntry.TABLE_NAME + " (" +
                    UserProfileEntry._ID        + TEXT_TYPE +"  PRIMARY KEY," +
                    UserProfileEntry.COLUMN_MAL_ID + INTEGER_TYPE + COMMA_SEP +
                    UserProfileEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    UserProfileEntry.COLUMN_WATCHING + INTEGER_TYPE + COMMA_SEP +
                    UserProfileEntry.COLUMN_COMPLETED + INTEGER_TYPE + COMMA_SEP +
                    UserProfileEntry.COLUMN_ON_HOLD + INTEGER_TYPE + COMMA_SEP +
                    UserProfileEntry.COLUMN_DROPPED + INTEGER_TYPE + COMMA_SEP +
                    UserProfileEntry.COLUMN_DAYS_SPENT_WATCHING + REAL_TYPE + COMMA_SEP +
                    UserProfileEntry.COLUMN_REWATCHED + INTEGER_TYPE + COMMA_SEP +
                    UserProfileEntry.COLUMN_PLAN_TO_WATCH + INTEGER_TYPE +COMMA_SEP +
                    UserProfileEntry.COLUMN_URL_PERFIL_FOTO + TEXT_TYPE +COMMA_SEP +
                    UserProfileEntry.COLUMN_URL_BACKGROUND_FOTO + TEXT_TYPE +COMMA_SEP +
                    UserProfileEntry.COLUMN_GENDER + TEXT_TYPE +COMMA_SEP +
                    UserProfileEntry.COLUMN_JOINED + TEXT_TYPE +COMMA_SEP +
                    UserProfileEntry.COLUMN_LAST_UPDATE + TEXT_TYPE +
                    " )";

    private static final String SQL_CREATE_ANIME_ENTRIES =
            "CREATE TABLE " + AnimeEntry.TABLE_NAME + " (" +
                    AnimeEntry._ID        + TEXT_TYPE +"  PRIMARY KEY," +
                    AnimeEntry.COLUMN_ANIMEDB_ID + INTEGER_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_SYNONYMS + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_SYNOPSIS + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_ENGLISH + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_JAPANESE + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_TYPE + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_EPISODES + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_STATUS + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_START_DATE + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_END_DATE + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_IMAGE + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_SCORE + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_SEASON + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_BROADCAST + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_SOURCE + REAL_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_DURATION + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_LAST_UPDATE + TEXT_TYPE + COMMA_SEP +
                    AnimeEntry.COLUMN_RELATIVES + INTEGER_TYPE +
                    " )";



    private static final String SQL_DELETE_USER_ENTRIES =
            "DROP TABLE IF EXISTS " + UserProfileEntry.TABLE_NAME;

    private static final String SQL_DELETE_ANIME_ENTRIES =
            "DROP TABLE IF EXISTS " + AnimeEntry.TABLE_NAME;

    public static synchronized MalDBHelper getInstance(Context context){
        if (sInstance == null){
            sInstance = new MalDBHelper(context.getApplicationContext());
        }
        return sInstance;
    }
    @Inject
    public MalDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER_ENTRIES);
        db.execSQL(SQL_CREATE_ANIME_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_USER_ENTRIES);
        db.execSQL(SQL_DELETE_ANIME_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    //---deletes a particular title---

}
