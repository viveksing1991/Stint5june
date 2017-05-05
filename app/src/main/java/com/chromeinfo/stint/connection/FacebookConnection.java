package com.chromeinfo.stint.connection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ProgressBar;

/**
 * Created by root on 3/5/17.
 */

public class FacebookConnection {
    private Activity mActivity;
    private Context mContext;
    private String mMessage;
    private ProgressBar mProgressBar;
    private Bitmap mScreen;


    public FacebookConnection(Activity activity, Context context, String text, ProgressBar progressBar, Bitmap bmp) {
        this.mMessage = "";
        this.mContext = context;
        this.mActivity = activity;
        this.mProgressBar = progressBar;
        this.mScreen = bmp;
        this.mMessage = text;
    }

    private static String APP_ID = null;

    private static final String EXPIRES = "expires_in";

    private static final String TOKEN = "access_token";

    private static final String KEY = "facebook_credantials";

    private static String permissions[];

    static {
        APP_ID = "";
        permissions = new String[]{"public_profile"};
    }


}
