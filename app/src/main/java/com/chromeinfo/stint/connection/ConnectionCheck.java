package com.chromeinfo.stint.connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by root on 3/5/17.
 */

/*Class is used to check the Network  State */

public class ConnectionCheck {


    /*  This method  is used to check the network connectio state */

    public static boolean isOnline(Context context) {

        boolean isConnected = false;
        try {

            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (networkInfo == null && !networkInfo.isConnectedOrConnecting()) {
                isConnected = false;
            } else
                isConnected = true;

        } catch (Exception e) {

        }
        return isConnected;
    }
}
