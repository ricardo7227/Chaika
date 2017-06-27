package com.chaika.estructuraDatos.search;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Objeto utilizado para deserializar la respuesta de la llamada a una petición de búsqueda.
 *
 * Created by Gato on 16/04/2017.
 */
@Root(name = "anime",strict = false)
public class AnimeSearch implements Parcelable {
    @ElementList(name = "entry",inline = true)
    public List<Entry> entradas;

    public AnimeSearch() {
    }

    protected AnimeSearch(Parcel in) {
        entradas = in.readArrayList(Entry.class.getClassLoader());
    }

    public static final Creator<AnimeSearch> CREATOR = new Creator<AnimeSearch>() {
        @Override
        public AnimeSearch createFromParcel(Parcel in) {
            return new AnimeSearch(in);
        }

        @Override
        public AnimeSearch[] newArray(int size) {
            return new AnimeSearch[size];
        }
    };

    public List<Entry> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Entry> entradas) {
        this.entradas = entradas;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(entradas);
    }
}//fin clase
