package com.example.studentmanagersystem.Utils;

import android.util.Log;


public class LogUtil {

    public static boolean isShowLog = true;

    public static void i(String TAG, String log){
        if(isShowLog){
            Log.i(TAG, log);
        }
    }

    public static void d(String TAG, String log) {
        if (isShowLog) {
            Log.d(TAG, log);
        }
    }

    public static void w(String TAG, String log) {
        if (isShowLog) {
            Log.w(TAG, log);
        }
    }

    public static void e(String TAG, String log) {
        if (isShowLog) {
            Log.e(TAG, log);
        }
    }

    public static void v(String TAG, String log) {
        if (isShowLog) {
            Log.v(TAG, log);
        }
    }
}


