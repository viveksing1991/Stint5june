package com.chromeinfo.stint.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by root on 1/5/17.
 */

/*Class provide utility methods for all the other class*/

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


    /**
     * This method is created to check the email . The format of the mail is right or wrong
     *
     * @param email pass email addresss here
     * @return
     */
    public static final boolean isValidEmail(String email) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    /*Method is used to show the no internet dialog */
    public static void noInternetDailog(Context context) {
        AlertDialog.Builder noInternet = new AlertDialog.Builder(context);
        TextView title = new TextView(context);
        title.setText("Stint");
        title.setPadding(10, 10, 10, 10);
        title.setGravity(17);
        title.setTextColor(-1);
        title.setTextSize(20.0f);
        noInternet.setCustomTitle(title);
        noInternet.setMessage("Network connection error.\n\nPlease verify your internet connection.");
        noInternet.setPositiveButton("Ok", new DialogChecker());
        noInternet.create();
        noInternet.show();
    }

    /*Class is used for dialog */
    private static class DialogChecker implements DialogInterface.OnClickListener {
        DialogChecker() {
        }

        public void onClick(DialogInterface dialog, int id) {
            dialog.dismiss();
        }
    }

    /*Method is used for common dialog for all the app where  it is used */
    public static void commonDialog(Context context, String message) {
        AlertDialog.Builder al = new AlertDialog.Builder(context);
        al.setMessage(message);
        al.setPositiveButton("ok", new DialogChecker());
        al.create();
        al.show();
    }

    /*Method is used to check the internet*/
    public static boolean checkInternet(Context context) {

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

    /*This method is used to show tje dialo,g box*/
    public static void showDialog(Context context, String title, String msg) {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(context);
        alertDialog.setMessage(msg);
        TextView title_text = new TextView(context);
        title_text.setText(title);
        title_text.setPadding(10, 10, 10, 10);
        title_text.setGravity(17);
        title_text.setTextColor(-1);
        title_text.setTextSize(20.0f);
        alertDialog.setCustomTitle(title_text);
        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton("OK", new DialogChecker());
        try {
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
