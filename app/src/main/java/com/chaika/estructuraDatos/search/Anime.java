package com.chaika.estructuraDatos.search;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Gato on 16/04/2017.
 */
@Root(name = "anime",strict = false)
public class Anime {
    @ElementList(name = "entry",inline = true)
    public List<Entry> entradas;


}//fin clase
