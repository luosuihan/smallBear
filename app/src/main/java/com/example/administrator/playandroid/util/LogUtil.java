package com.example.administrator.playandroid.util;

import android.util.Log;

/**
 * Created by Administrator on 2018/8/23.
 * log
 */

public class LogUtil {
    private static final boolean DEBUG = true;
    private static final String TAG = "luosuihan";
    public static void e(String tag,String message){
        if (DEBUG)
        Log.e(tag, message);
    }
    public static void i(String tag,String message){
        if (DEBUG)
            Log.i(tag, message);
    }
    public static void w(String tag,String message){
        if (DEBUG)
            Log.w(tag, message);
    }
    public static void d(String tag,String message){
        if (DEBUG)
            Log.d(tag, message);
    }
   /* -----------------------------------------------------------------------------------*/
   public static void e(String message){
       if (DEBUG)
           Log.e(TAG, message);
   }
    public static void i(String message){
        if (DEBUG)
            Log.i(TAG, message);
    }
    public static void w(String message){
        if (DEBUG)
            Log.w(TAG, message);
    }
    public static void d(String message){
        if (DEBUG)
            Log.d(TAG, message);
    }
}
