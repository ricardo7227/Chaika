package com.chaika.componentes;

import com.chaika.databases.MalDBHelper;
import com.chaika.modulos.DatabaseModulo;
import com.chaika.scopes.DatabaseScope;

import dagger.Component;

/**
 * Created by Gato on 01/05/2017.
 */
@DatabaseScope
@Component(modules = DatabaseModulo.class)
public interface DatabaseComponent {

    MalDBHelper getMalDbHelper();

}
