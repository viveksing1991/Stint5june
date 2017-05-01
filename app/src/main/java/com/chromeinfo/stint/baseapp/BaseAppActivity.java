package com.chromeinfo.stint.baseapp;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by root on 1/5/17.
 */
/*Class is used as Base for all activity and the class contains all the common methods
 * The class which extends this class have to implement the methods or declared itself as abstract
  * */
public abstract class BaseAppActivity extends AppCompatActivity {

    public abstract void setViewsReferences();  /* This method is used to set the references of views*/

    public abstract void setViewsListeners();  /*This method is used to set the listeners on views*/

    public abstract void initilizer();           /*This method is used to initiize the data member */


}
