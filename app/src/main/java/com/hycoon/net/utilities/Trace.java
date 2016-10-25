package com.hycoon.net.utilities;

import android.util.Log;

/**
 * 类 名： com.hycoon.net.utilities
 * 功 能：
 * 作 者： Administrator
 * 时 间： 2016/10/18 19 47
 * 邮 箱： yuhaikun19920202@gmail.com
 */
public class Trace {

    private static final String TAG = "hycoon";

    public static void d(String msg) {

        Log.d(TAG, msg);

    }


    public static void e(String msg) {

        Log.e(TAG, msg);

    }

}
