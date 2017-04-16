package com.chaika.estructuraDatos.usuario;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "myinfo",strict = false)
public class MalUserData {
    @Element(name = "user_id")
    public long user_id;
    @Element(name = "user_name")
    public String user_name;
    @Element(name = "user_watching")
    public long user_watching;
    @Element(name = "user_completed")
    public long user_completed;

    public MalUserData(long user_id, String user_name, long user_watching, long user_completed) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_watching = user_watching;
        this.user_completed = user_completed;
    }
    public MalUserData(){}

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public long getUser_watching() {
        return user_watching;
    }

    public void setUser_watching(long user_watching) {
        this.user_watching = user_watching;
    }

    public long getUser_completed() {
        return user_completed;
    }

    public void setUser_completed(long user_completed) {
        this.user_completed = user_completed;
    }
}//fin clase

