package com.chaika.componentes;

import com.chaika.databases.Data;
import com.chaika.modulos.DatabaseModulo;
import com.chaika.scopes.DatabaseScope;

import dagger.Component;

/**
 * Componente de Dagger2 que da acceso a la base de datos
 * Created by Gato on 01/05/2017.
 */
@DatabaseScope
@Component(modules = {DatabaseModulo.class})
public interface DatabaseApplicationComponent {

    //MalDBHelper getMalDbHelper();

    Data getData();
    //para Glide
    //RequestManager getRequestManager();


}
