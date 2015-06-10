package com.alfco.myrsspodcast.tools;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.apache.commons.lang3.text.WordUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by angel on 6/7/15.
 */
public class Util {

    public static final String appName = "MyRssPodcast";

    //****************************************************************************************//
    //									  Log												  //
    //																						  //
    //****************************************************************************************//

    private static final boolean debug = true;
    private static final String strHighLighter = "**********";

    public static void li(String message){
        if (debug)
        {
            Log.i(appName, message);
        }
    }

    public static void ld(String message){
        if (debug)
        {
            Log.d(appName, message);
        }
    }

    public static void ld(String message, boolean highLight){
        if (debug)
        {
            message = highLight ? strHighLighter+message+strHighLighter: message;
            Log.d(appName, message);
        }
    }

    public static void le(String message, boolean highLight){
        if (debug)
        {
            message = highLight ? strHighLighter+message+strHighLighter: message;
            Log.e(appName, message);
        }
    }

    public static void lw(String message, boolean highLight){
        if (debug)
        {
            message = highLight ? strHighLighter+message+strHighLighter: message;
            Log.w(appName, message);
        }
    }

    public static void lwtf(String message, boolean highLight){
        if (debug)
        {
            message = highLight ? strHighLighter+message+strHighLighter: message;
            Log.wtf(appName, message);
        }
    }

    public static void li(String message, boolean highLight){
        if (debug)
        {
            message = highLight ? strHighLighter+message+strHighLighter: message;
            Log.i(appName, message);
        }
    }

    public static void le(String message){
        if (debug)
        {
            Log.e(appName, message);
        }
    }

    public static void lw(String message){
        if (debug)
        {
            Log.w(appName, message);
        }
    }

    public static void lwtf(String message){
        if (debug)
        {
            Log.wtf(appName, message);
        }
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getCurrentFocus() != null){
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void hideView(View view){
        view.setVisibility(View.GONE);
    }

    public static void showView(View view){
        view.setVisibility(View.VISIBLE);
    }

    public static boolean isOnline(Context context) {
        boolean connected = false;
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < 2; i++) {

            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }

    public static boolean isEmpty(String string){
        return string.equals("");
    }

    public static String formatDate(String dateString){
        String outputDate="";

        dateString=WordUtils.capitalize(dateString);
        DateFormat formatDate=new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.getDefault());

        try {
            le(dateString);
            Date date=formatDate.parse(dateString);
            outputDate=(new SimpleDateFormat("d MMM yy",Locale.getDefault())).format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDate;
    }
    public static String formatDate(String date, String newFormat, String oldFormat) {
        if (date == null||date.isEmpty()) return "";
        DateFormat inputFormat = new SimpleDateFormat(oldFormat, Locale.getDefault());
        Date parsed = new Date();
        try {
            parsed = inputFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat outputFormat = new SimpleDateFormat(newFormat, Locale.getDefault());
        String outputText = outputFormat.format(parsed);
        return outputText;
    }
    public static boolean isValidURL(String url){
        if(!url.contains("http://"))
            url="http://"+url;
        String regex="(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]" +
                "+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?";


        if(url.matches(regex))
            return true;
        else
            return false;

    }


}
