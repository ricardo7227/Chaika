package com.chaika.estructuraDatos.search;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Gato on 16/04/2017.
 */
@Root(name = "entry",strict = false)
public class Entry {
    @Element(name = "id")
    public long anime_id;
    @Element(name = "title")
    public String anime_title;
    @Element(name = "synopsis")
    public String anime_sinopsis;
    @Element(name = "end_date")
    private String end_date;
    @Element(name = "status")
    private String status;
    @Element(name = "score")
    private float score;
    @Element(name = "synonyms")
    private String synonyms;
    @Element(name = "image")
    private String image_path;
    @Element(name = "type")
    private String type;
    @Element(name = "english")
    private String title_english;
    @Element(name = "episodes")
    private int episodes;
    @Element(name = "start_date")
    private String start_date;

    public Entry(){}

    public long getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(long anime_id) {
        this.anime_id = anime_id;
    }

    public String getAnime_title() {
        return anime_title;
    }

    public void setAnime_title(String anime_title) {
        this.anime_title = anime_title;
    }

    public String getAnime_sinopsis() {
        return anime_sinopsis;
    }

    public void setAnime_sinopsis(String anime_sinopsis) {
        this.anime_sinopsis = anime_sinopsis;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle_english() {
        return title_english;
    }

    public void setTitle_english(String title_english) {
        this.title_english = title_english;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
}//fin clase
