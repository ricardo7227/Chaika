package com.chaika.databases;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.chaika.estructuraDatos.Database.UserData;
import com.chaika.estructuraDatos.malAppInfo.MyInfo;

import javax.inject.Inject;

/**
 * Created by Gato on 01/05/2017.
 */

public class Data {
    private static final String TAG = "Data";


    private MalDBHelper malDBHelper;
    @Inject
    public Data(MalDBHelper malDBHelper){

        this.malDBHelper = malDBHelper;
    }

    public  UserData getUser(){
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


    }




}//fin clase
