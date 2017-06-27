package com.chaika.search.content_provider;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Creado para implementar historial en la barra de búsqueda
 * Created by Gato on 27/06/2017.
 */

public class Provider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.chaika.Provider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public Provider() {
        setupSuggestions(AUTHORITY, MODE);
    }



}//fin clase