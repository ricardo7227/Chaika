package com.chaika.fragmentos.adaptadores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.chaika.fragmentos.AllSeriesFragment;
import com.chaika.fragmentos.SecondFragment;

/**
 * Created by Gato on 14/05/2017.
 */

public class AdaptadorFragmentos extends SmartFragmentStatePagerAdapter {
    private static int NUM_ITEMS = 3;


    public AdaptadorFragmentos(FragmentManager fragmentManager) {
        super(fragmentManager);

    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show AllSeriesFragment
                return AllSeriesFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 0 - This will show AllSeriesFragment different title
                return AllSeriesFragment.newInstance(1, "Page # 2");
            case 2: // Fragment # 1 - This will show SecondFragment
                return SecondFragment.newInstance(2, "Page # 3");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }


}//fin clase