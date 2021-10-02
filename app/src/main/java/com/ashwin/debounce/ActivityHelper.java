package com.ashwin.debounce;

import android.app.Activity;
import android.os.PowerManager;
import android.util.Log;

public class ActivityHelper {
    private static final String SUB_TAG = ActivityHelper.class.getSimpleName();

    public static void logIsFinishing(Activity activity) {
        Log.d(Constant.APP_TAG, SUB_TAG + ": logIsFinishing: " + activity.getClass().getSimpleName() + ": " + activity.isFinishing());
    }

    public static void logIsScreenOn(Activity activity) {
        PowerManager powermanager = (PowerManager) activity.getSystemService(activity.POWER_SERVICE);
        Log.d(Constant.APP_TAG, SUB_TAG + ": logIsScreenOn: isScreenOn: " + powermanager.isScreenOn());
        Log.d(Constant.APP_TAG, SUB_TAG + ": logIsScreenOn: isInteractive: " + powermanager.isInteractive());
    }
}
