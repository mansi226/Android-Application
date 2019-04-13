package com.example.atishay.event_lister;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.google.gson.Gson;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Config {
    static  String pref="pref_detail";
    public static void finishAllOpenNewActivity(Context context, AppCompatActivity activity) {
        // clear all and open new Activity
        Intent intent = new Intent(context, activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
        //((AppCompatActivity) context).finish();

    }

    public static void openActivity(Context context, AppCompatActivity activity) {
        Intent intent = new Intent(context, activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
    public static boolean isOnline(Context context) {
        InetAddress inetAddress = null;
        try {
            Future<InetAddress> future = Executors.newSingleThreadExecutor().submit(new Callable<InetAddress>() {
                @Override
                public InetAddress call() {
                    try {
                        return InetAddress.getByName("google.com");
                    } catch (UnknownHostException e) {
                        return null;
                    }
                }
            });
            inetAddress = future.get(3000, TimeUnit.MILLISECONDS);
            future.cancel(true);
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        } catch (TimeoutException e) {

           // Toast.makeText(context, "Connection timeout !", Toast.LENGTH_SHORT).show();
        }
        return inetAddress!=null && !inetAddress.equals("");
    }
    public static void putSharedPreferences(Context context, String preferences, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static String getSharedPreferences(Context context, String preferences, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferences, Context.MODE_PRIVATE);
        String defvalue = sharedPreferences.getString(key, value);
        return defvalue;
    }
    public static void clearSharedPreferences(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
