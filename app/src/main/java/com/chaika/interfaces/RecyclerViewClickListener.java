package com.chaika.interfaces;

import android.view.View;

/**
 * interface creada para detectar la posición del elemento de la lista sobre el cual se ejecuta un click.
 *
 * Created by ricardo on 25/5/17.
 */

public interface RecyclerViewClickListener {

    void onClick(View view, int position);

}
