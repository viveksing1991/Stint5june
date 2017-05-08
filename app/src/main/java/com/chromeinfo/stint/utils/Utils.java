package com.chromeinfo.stint.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

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


    /**
     * This method is created to check the email . The format of the mail is right or wrong
     *
     * @param email pass email addresss here
     * @return
     */
    public static final boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email).matches();
    }


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

    private static class DialogChecker implements DialogInterface.OnClickListener {
        DialogChecker() {
        }

        public void onClick(DialogInterface dialog, int id) {
            dialog.dismiss();
        }
    }

    public static void commonDialog(Context context, String message) {
        AlertDialog.Builder al = new AlertDialog.Builder(context);
        al.setMessage(message);
        al.setPositiveButton("ok", new DialogChecker());
        al.create();
        al.show();
    }



}
