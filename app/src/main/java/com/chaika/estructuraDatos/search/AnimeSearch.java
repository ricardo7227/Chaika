package com.chaika.estructuraDatos.search;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Gato on 16/04/2017.
 */
@Root(name = "anime",strict = false)
public class AnimeSearch {
    @ElementList(name = "entry",inline = true)
    public List<Entry> entradas;

    public AnimeSearch() {
    }

    public List<Entry> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Entry> entradas) {
        this.entradas = entradas;
    }
}//fin clase
