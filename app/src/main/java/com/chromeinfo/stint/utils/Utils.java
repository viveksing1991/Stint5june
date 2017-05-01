package com.example.root.stint.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by root on 1/5/17.
 */

public class Utils {

    /*Data member initilize */

    private static boolean LOG = true;

    /*End*/

    /**
     * @param context set the context
     * @param message set the message here
     */
    public static void createShortToast(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT);

    }

    /**
     * @param context set the context
     * @param message set the message here
     */
    public static void createLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG);
    }

    /**
     * @param tag     set the context
     * @param message set the message here
     */
    public static void logError(String tag, String message) {

        if (LOG) {
            Log.e(tag, message);
        }
    }

    /**
     * @param tag     Acivity tag here
     * @param message Message you want to print in the log
     */
    public static void logDebug(String tag, String message) {

        if (LOG) {
            Log.d(tag, message);
        }
    }


    /**
     * @param tag     Acivity tag here
     * @param message Message you want to print in the log
     */
    public static void logWarn(String tag, String message) {

        if (LOG) {
            Log.w(tag, message);
        }
    }



}
