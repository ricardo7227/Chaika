package com.chaika.estructuraDatos.Database;

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

}//fin clase
