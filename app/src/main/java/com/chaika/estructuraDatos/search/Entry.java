package com.chaika.estructuraDatos.search;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.chaika.databases.MalDBHelper;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Objeto utilizado en la deserialización de todos los resultados en la llamada al API de busqueda.
 * Created by Gato on 16/04/2017.
 */
@Root(name = "entry",strict = false)
public class Entry implements Parcelable{
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
    @Element(name = "synonyms", required = false)
    private String synonyms;
    @Element(name = "image")
    private String image_path;
    @Element(name = "type")
    private String type;
    @Element(name = "english", required = false)
    private String title_english;
    @Element(name = "episodes")
    private int episodes;
    @Element(name = "start_date")
    private String start_date;

    public Entry(){}


    public Entry(long anime_id, String anime_title, String anime_sinopsis, String end_date, float score, String synonyms, String image_path, String title_english, int episodes, String start_date) {
        this.anime_id = anime_id;
        this.anime_title = anime_title;
        this.anime_sinopsis = anime_sinopsis;
        this.end_date = end_date;
        this.score = score;
        this.synonyms = synonyms;
        this.image_path = image_path;
        this.title_english = title_english;
        this.episodes = episodes;
        this.start_date = start_date;
    }

    public Entry(long anime_id, String anime_title, String anime_sinopsis, String end_date, String status, float score, String synonyms, String image_path, String type, String title_english, int episodes, String start_date) {
        this.anime_id = anime_id;
        this.anime_title = anime_title;
        this.anime_sinopsis = anime_sinopsis;
        this.end_date = end_date;
        this.status = status;
        this.score = score;
        this.synonyms = synonyms;
        this.image_path = image_path;
        this.type = type;
        this.title_english = title_english;
        this.episodes = episodes;
        this.start_date = start_date;
    }

    protected Entry(Parcel in) {
        anime_id = in.readLong();
        anime_title = in.readString();
        anime_sinopsis = in.readString();
        end_date = in.readString();
        status = in.readString();
        score = in.readFloat();
        synonyms = in.readString();
        image_path = in.readString();
        type = in.readString();
        title_english = in.readString();
        episodes = in.readInt();
        start_date = in.readString();
    }

    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        @Override
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };

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

    @Override
    public String toString() {
        return "Entry{" +
                "anime_id=" + anime_id +
                ", anime_title='" + anime_title + '\'' +
                ", anime_sinopsis='" + anime_sinopsis + '\'' +
                ", end_date='" + end_date + '\'' +
                ", status='" + status + '\'' +
                ", score=" + score +
                ", synonyms='" + synonyms + '\'' +
                ", image_path='" + image_path + '\'' +
                ", type='" + type + '\'' +
                ", title_english='" + title_english + '\'' +
                ", episodes=" + episodes +
                ", start_date='" + start_date + '\'' +
                '}';
    }

    public ContentValues getContentValuesSerie(){
        try {
            ContentValues values = new ContentValues();

            values.put(MalDBHelper.AnimeEntry.COLUMN_SINOPSIS, getAnime_sinopsis());
            values.put(MalDBHelper.AnimeEntry.COLUMN_ENGLISH ,getTitle_english());
            values.put(MalDBHelper.AnimeEntry.COLUMN_SCORE ,getScore());


            return values;
        }catch (Exception e){
            Log.e("UserData Entry",e.getMessage());
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(anime_id);
        parcel.writeString(anime_title);
        parcel.writeString(anime_sinopsis);
        parcel.writeString(end_date);
        parcel.writeString(status);
        parcel.writeFloat(score);
        parcel.writeString(synonyms);
        parcel.writeString(image_path);
        parcel.writeString(type);
        parcel.writeString(title_english);
        parcel.writeInt(episodes);
        parcel.writeString(start_date);

    }
}//fin clase
