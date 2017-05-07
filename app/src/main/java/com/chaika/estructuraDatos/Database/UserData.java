package com.chaika.estructuraDatos.Database;

import android.content.ContentValues;
import android.util.Log;

import com.chaika.databases.MalDBHelper;
import com.chaika.estructuraDatos.malAppInfo.MyInfo;

/**
 * Created by Gato on 30/04/2017.
 */

public class UserData {
    private MyInfo myInfo;
    private int rewwatched;
    private String urlPerfilUsuario;
    private String urlPerfilBackground;
    private String gender;
    private String joined;
    private String lastUpdate;

    public UserData() {
    }

    public MyInfo getMyInfo() {
        return myInfo;
    }

    public void setMyInfo(MyInfo myInfo) {
        this.myInfo = myInfo;
    }

    public int getRewwatched() {
        return rewwatched;
    }

    public void setRewwatched(int rewwatched) {
        this.rewwatched = rewwatched;
    }

    public String getUrlPerfilUsuario() {
        return urlPerfilUsuario;
    }

    public void setUrlPerfilUsuario(String urlPerfilUsuario) {
        this.urlPerfilUsuario = urlPerfilUsuario;
    }

    public String getUrlPerfilBackground() {
        return urlPerfilBackground;
    }

    public void setUrlPerfilBackground(String urlPerfilBackground) {
        this.urlPerfilBackground = urlPerfilBackground;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJoined() {
        return joined;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    public static String[] getProjection(){
        String[] projection = {
                MalDBHelper.UserProfileEntry.COLUMN_MAL_ID,
                MalDBHelper.UserProfileEntry.COLUMN_NAME,
                MalDBHelper.UserProfileEntry.COLUMN_WATCHING,
                MalDBHelper.UserProfileEntry. COLUMN_COMPLETED,
                MalDBHelper.UserProfileEntry. COLUMN_ON_HOLD,
                MalDBHelper.UserProfileEntry. COLUMN_DROPPED,
                MalDBHelper.UserProfileEntry. COLUMN_PLAN_TO_WATCH,
                MalDBHelper.UserProfileEntry. COLUMN_DAYS_SPENT_WATCHING,
                MalDBHelper.UserProfileEntry. COLUMN_REWATCHED,
                MalDBHelper.UserProfileEntry. COLUMN_URL_PERFIL_FOTO,
                MalDBHelper.UserProfileEntry. COLUMN_URL_BACKGROUND_FOTO,
                MalDBHelper.UserProfileEntry. COLUMN_GENDER,
                MalDBHelper.UserProfileEntry. COLUMN_JOINED,
                MalDBHelper.UserProfileEntry. COLUMN_LAST_UPDATE,

        };

        return projection;
    }

    public ContentValues getContentValues(){
        try {
            ContentValues values = new ContentValues();
            values.put(MalDBHelper.UserProfileEntry.COLUMN_MAL_ID, getMyInfo().getUser_id());
            values.put(MalDBHelper.UserProfileEntry.COLUMN_NAME, getMyInfo().getUser_name());
            values.put(MalDBHelper.UserProfileEntry.COLUMN_WATCHING, getMyInfo().getUser_watching());
            values.put(MalDBHelper.UserProfileEntry.COLUMN_COMPLETED, getMyInfo().getUser_completed());
            values.put(MalDBHelper.UserProfileEntry.COLUMN_ON_HOLD, getMyInfo().getUser_onhold());
            values.put(MalDBHelper.UserProfileEntry.COLUMN_DROPPED, getMyInfo().getUser_dropped());
            values.put(MalDBHelper.UserProfileEntry.COLUMN_PLAN_TO_WATCH, getMyInfo().getUser_plantowatch());
            values.put(MalDBHelper.UserProfileEntry.COLUMN_DAYS_SPENT_WATCHING, getMyInfo().getUser_days_spent_watching());
            values.put(MalDBHelper.UserProfileEntry.COLUMN_REWATCHED, getRewwatched());
            values.put(MalDBHelper.UserProfileEntry.COLUMN_URL_PERFIL_FOTO, getUrlPerfilUsuario());
            values.put(MalDBHelper.UserProfileEntry.COLUMN_URL_BACKGROUND_FOTO, getUrlPerfilBackground());
            values.put(MalDBHelper.UserProfileEntry.COLUMN_GENDER, getGender());
            values.put(MalDBHelper.UserProfileEntry.COLUMN_JOINED, getJoined());
            values.put(MalDBHelper.UserProfileEntry.COLUMN_LAST_UPDATE, getLastUpdate());

            return values;
        }catch (Exception e){
            Log.e("UserData",e.getMessage());
        }
        return null;
    }

}//fin clase
