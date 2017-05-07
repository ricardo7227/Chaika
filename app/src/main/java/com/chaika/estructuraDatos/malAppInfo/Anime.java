package com.chaika.estructuraDatos.malAppInfo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Gato on 30/04/2017.
 */
@Root(name = "anime",strict = false)
public class Anime
{
    @Element(name = "series_type")
    private int series_type;

    @Element(name = "series_status")
    private int series_status;

    @Element(name = "series_synonyms", required = false)
    private String series_synonyms;

    @Element(name = "my_watched_episodes")
    private int my_watched_episodes;

    @Element(name = "series_image")
    private String series_image;

    @Element(name = "my_status")
    private int my_status;

    @Element(name = "series_title")
    private String series_title;

    @Element(name = "my_rewatching", required = false)
    private int my_rewatching;

    @Element(name = "my_finish_date")
    private String my_finish_date;

    @Element(name = "series_start")
    private String series_start;

    @Element(name = "series_animedb_id")
    private long series_animedb_id;

    @Element(name = "my_id")
    private String my_id;

    @Element(name = "my_start_date")
    private String my_start_date;

    @Element(name = "series_end")
    private String series_end;

    @Element(name = "my_last_updated")
    private String my_last_updated;

    @Element(name = "my_tags",required = false)
    private String my_tags;

    @Element(name = "my_score")
    private float my_score;

    @Element(name = "series_episodes")
    private int series_episodes;

    @Element(name = "my_rewatching_ep")
    private int my_rewatching_ep;

    public Anime() {
    }

    public int getSeries_type() {
        return series_type;
    }

    public void setSeries_type(int series_type) {
        this.series_type = series_type;
    }

    public int getSeries_status() {
        return series_status;
    }

    public void setSeries_status(int series_status) {
        this.series_status = series_status;
    }

    public String getSeries_synonyms() {
        return series_synonyms;
    }

    public void setSeries_synonyms(String series_synonyms) {
        this.series_synonyms = series_synonyms;
    }

    public int getMy_watched_episodes() {
        return my_watched_episodes;
    }

    public void setMy_watched_episodes(int my_watched_episodes) {
        this.my_watched_episodes = my_watched_episodes;
    }

    public String getSeries_image() {
        return series_image;
    }

    public void setSeries_image(String series_image) {
        this.series_image = series_image;
    }

    public int getMy_status() {
        return my_status;
    }

    public void setMy_status(int my_status) {
        this.my_status = my_status;
    }

    public String getSeries_title() {
        return series_title;
    }

    public void setSeries_title(String series_title) {
        this.series_title = series_title;
    }

    public int getMy_rewatching() {
        return my_rewatching;
    }

    public void setMy_rewatching(int my_rewatching) {
        this.my_rewatching = my_rewatching;
    }

    public String getMy_finish_date() {
        return my_finish_date;
    }

    public void setMy_finish_date(String my_finish_date) {
        this.my_finish_date = my_finish_date;
    }

    public String getSeries_start() {
        return series_start;
    }

    public void setSeries_start(String series_start) {
        this.series_start = series_start;
    }

    public long getSeries_animedb_id() {
        return series_animedb_id;
    }

    public void setSeries_animedb_id(long series_animedb_id) {
        this.series_animedb_id = series_animedb_id;
    }

    public String getMy_id() {
        return my_id;
    }

    public void setMy_id(String my_id) {
        this.my_id = my_id;
    }

    public String getMy_start_date() {
        return my_start_date;
    }

    public void setMy_start_date(String my_start_date) {
        this.my_start_date = my_start_date;
    }

    public String getSeries_end() {
        return series_end;
    }

    public void setSeries_end(String series_end) {
        this.series_end = series_end;
    }

    public String getMy_last_updated() {
        return my_last_updated;
    }

    public void setMy_last_updated(String my_last_updated) {
        this.my_last_updated = my_last_updated;
    }

    public String getMy_tags() {
        return my_tags;
    }

    public void setMy_tags(String my_tags) {
        this.my_tags = my_tags;
    }

    public float getMy_score() {
        return my_score;
    }

    public void setMy_score(float my_score) {
        this.my_score = my_score;
    }

    public int getSeries_episodes() {
        return series_episodes;
    }

    public void setSeries_episodes(int series_episodes) {
        this.series_episodes = series_episodes;
    }

    public int getMy_rewatching_ep() {
        return my_rewatching_ep;
    }

    public void setMy_rewatching_ep(int my_rewatching_ep) {
        this.my_rewatching_ep = my_rewatching_ep;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "series_type=" + series_type +
                ", series_status=" + series_status +
                ", series_synonyms='" + series_synonyms + '\'' +
                ", my_watched_episodes=" + my_watched_episodes +
                ", series_image='" + series_image + '\'' +
                ", my_status=" + my_status +
                ", series_title='" + series_title + '\'' +
                ", my_rewatching=" + my_rewatching +
                ", my_finish_date='" + my_finish_date + '\'' +
                ", series_start='" + series_start + '\'' +
                ", series_animedb_id=" + series_animedb_id +
                ", my_id='" + my_id + '\'' +
                ", my_start_date='" + my_start_date + '\'' +
                ", series_end='" + series_end + '\'' +
                ", my_last_updated='" + my_last_updated + '\'' +
                ", my_tags='" + my_tags + '\'' +
                ", my_score=" + my_score +
                ", series_episodes=" + series_episodes +
                ", my_rewatching_ep=" + my_rewatching_ep +
                '}';
    }
}
