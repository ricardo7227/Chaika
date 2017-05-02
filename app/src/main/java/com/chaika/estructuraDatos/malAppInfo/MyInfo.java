package com.chaika.estructuraDatos.malAppInfo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "myinfo",strict = false)
public class MyInfo {
    @Element(name = "user_id")
    public long user_id;
    @Element(name = "user_name")
    public String user_name;
    @Element(name = "user_watching")
    public int user_watching;
    @Element(name = "user_completed")
    public long user_completed;
    @Element(name = "user_onhold")
    private int user_onhold;
    @Element(name = "user_plantowatch")
    private int user_plantowatch;
    @Element(name = "user_dropped")
    private int user_dropped;
    @Element(name = "user_days_spent_watching")
    private long user_days_spent_watching;

    public MyInfo(){}

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

    public int getUser_watching() {
        return user_watching;
    }

    public void setUser_watching(int user_watching) {
        this.user_watching = user_watching;
    }

    public long getUser_completed() {
        return user_completed;
    }

    public void setUser_completed(long user_completed) {
        this.user_completed = user_completed;
    }

    public int getUser_onhold() {
        return user_onhold;
    }

    public void setUser_onhold(int user_onhold) {
        this.user_onhold = user_onhold;
    }

    public int getUser_plantowatch() {
        return user_plantowatch;
    }

    public void setUser_plantowatch(int user_plantowatch) {
        this.user_plantowatch = user_plantowatch;
    }

    public int getUser_dropped() {
        return user_dropped;
    }

    public void setUser_dropped(int user_dropped) {
        this.user_dropped = user_dropped;
    }

    public long getUser_days_spent_watching() {
        return user_days_spent_watching;
    }

    public void setUser_days_spent_watching(long user_days_spent_watching) {
        this.user_days_spent_watching = user_days_spent_watching;
    }
}//fin clase

