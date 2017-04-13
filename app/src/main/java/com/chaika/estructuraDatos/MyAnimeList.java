package com.chaika.estructuraDatos;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Gato on 13/04/2017.
 */
@Root(name = "myanimelist",strict = false)
public class MyAnimeList{
    @Element(name = "myinfo")
    public MalUserData malUserData;

}
