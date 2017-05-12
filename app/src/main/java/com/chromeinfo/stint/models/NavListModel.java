package com.chromeinfo.stint.models;

import android.graphics.drawable.Drawable;

/**
 * Created by root on 11/5/17.
 */

/*Class is a model for Nav List*/
public class NavListModel {

    private String name;

    public int getDrawable() {
        return drawable;
    }

    private int drawable;

    public String getName() {
        return name;
    }


    public NavListModel(String name, int drawable) {
        this.name = name;
        this.drawable = drawable;
    }

}

