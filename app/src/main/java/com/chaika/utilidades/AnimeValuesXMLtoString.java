package com.chaika.utilidades;

import com.chaika.estructuraDatos.EntryAnimeValues;

/**
 * Created by Gato on 13/05/2017.
 * modelo
 *
 <?xml version="1.0" encoding="UTF-8"?>
 <entry>
 <episode>11</episode>
 <status>1</status>
 <score>7</score>
 <storage_type></storage_type>
 <storage_value></storage_value>
 <times_rewatched></times_rewatched>
 <rewatch_value></rewatch_value>
 <date_start></date_start>
 <date_finish></date_finish>
 <priority></priority>
 <enable_discussion></enable_discussion>
 <enable_rewatching></enable_rewatching>
 <comments></comments>
 <tags>test tag, 2nd tag</tags>
 </entry>
 *
 */


public class AnimeValuesXMLtoString {

    private static AnimeValuesXMLtoString instance;

    //instancia única de la clase
    public static AnimeValuesXMLtoString getInstance(){
        if (instance == null){
            instance = new AnimeValuesXMLtoString();
        }
        return instance;
    }

    /***
     *
     * @param entryAnimeValues
     * @return String con los datos disponibles
     */
    public String convert(EntryAnimeValues entryAnimeValues){

        StringBuilder entryValues = new StringBuilder();
        entryValues.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        entryValues.append("<entry>");
        if (entryAnimeValues.getEpisode() != null){
            entryValues.append(
                    "<episode>" + entryAnimeValues.getEpisode() + "</episode>");
        }
        if (entryAnimeValues.getStatus() != null){
            entryValues.append(
                    "<status>" + entryAnimeValues.getStatus() + "</status>");
        }
        if (entryAnimeValues.getScore() != null){
            entryValues.append(
                    "<score>" + entryAnimeValues.getScore() + "</score>");
        }
        if (entryAnimeValues.getStorage_type() != null){
            entryValues.append(
                    "<storage_type>" + entryAnimeValues.getStorage_type() + "</storage_type>");
        }
        if (entryAnimeValues.getStorage_value() != null){
            entryValues.append(
                    "<storage_value>" + entryAnimeValues.getStorage_value() + "</storage_value>");
        }
        if (entryAnimeValues.getTimes_rewatched() != null){
            entryValues.append(
                    "<times_rewatched>" + entryAnimeValues.getTimes_rewatched() + "</times_rewatched>");
        }
        if (entryAnimeValues.getRewatch_value() != null){
            entryValues.append(
                    "<rewatch_value>" + entryAnimeValues.getRewatch_value() + "</rewatch_value>");
        }
        if (entryAnimeValues.getDate_start() != null){
            entryValues.append(
                    "<date_start>" + entryAnimeValues.getDate_start() + "</date_start>");
        }
        if (entryAnimeValues.getDate_finish() != null){
            entryValues.append(
                    "<date_finish>" + entryAnimeValues.getDate_finish() + "</date_finish>");
        }
        if (entryAnimeValues.getPriority() != null){
            entryValues.append(
                    "<priority>" + entryAnimeValues.getPriority() + "</priority>");
        }
        if (entryAnimeValues.getEnable_discussion() != null){
            entryValues.append(
                    "<enable_discussion>" + entryAnimeValues.getEnable_discussion() + "</enable_discussion>");
        }
        if (entryAnimeValues.getEnable_rewatching() != null){
            entryValues.append(
                    "<enable_rewatching>" + entryAnimeValues.getEnable_rewatching() + "</enable_rewatching>");
        }
        if (entryAnimeValues.getComments() != null){
            entryValues.append(
                    "<comments>" + entryAnimeValues.getComments() + "</comments>");
        }
        if (entryAnimeValues.getTags() != null){
            entryValues.append(
                    "<tags>" + entryAnimeValues.getTags() +"</tags>");
        }
        entryValues.append("</entry>");

        return entryValues.toString();

    }

}//fin clase
