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

    public Entry(){}


}//fin clase
