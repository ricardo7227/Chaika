package com.chaika.estructuraDatos.malAppInfo;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Objeto utilizado para deserializar la respuesta a la llamada que devuelve toda la información de la cuenta.
 *
 * Created by Gato on 13/04/2017.
 */
@Root(name = "myanimelist",strict = false)
public class MyAnimeList{

    @Element(name = "myinfo")
    public MyInfo myInfo;
    @ElementList(name = "anime",inline = true)
    private List<Anime> animes;

    public MyAnimeList() {
    }

    public MyInfo getMyInfo() {
        return myInfo;
    }

    public void setMyInfo(MyInfo myInfo) {
        this.myInfo = myInfo;
    }

    public List<Anime> getAnimes() {
        return animes;
    }

    public void setAnimes(List<Anime> animes) {
        this.animes = animes;
    }

    @Override
    public String toString() {
        return "MyAnimeList{" +
                "myInfo=" + myInfo +
                ", animes=" + animes +
                '}';
    }
}
