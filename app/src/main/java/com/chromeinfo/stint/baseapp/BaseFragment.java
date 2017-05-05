package com.chromeinfo.stint.baseapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * Created by root on 4/5/17.
 */

public abstract class BaseFragment extends Fragment {

    public abstract void setViewsReferences(View view);  /* This method is used to set the references of views*/

    public abstract void setViewsListeners();  /*This method is used to set the listeners on views*/

    public abstract void initilizer();           /*This method is used to initiize the data member */
}
