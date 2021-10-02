package com.ashwin.debounce;

import android.util.Log;

public class DebouncedDropRunnable implements Runnable {
    private static final String SUB_TAG = DebouncedQueueFirstRunnable.class.getSimpleName();

    private final Runnable operation;
    private final String name;
    private final long delayMillis;

    // state
    private long lastRunTime = -1;

    public DebouncedDropRunnable(Runnable operation, String name, long delayMillis) {
        this.operation = operation;
        this.name = name;
        this.delayMillis = delayMillis;
    }

    public synchronized void run() {
        long currentTime = System.currentTimeMillis();
        if (shouldRunNow(currentTime)) {
            lastRunTime = currentTime;
            Log.d(Constant.APP_TAG, SUB_TAG + ": calling " + name + " immediately");
            operation.run();
        } else {
            Log.d(Constant.APP_TAG, SUB_TAG + ": dropping " + name);
        }
    }

    private boolean shouldRunNow(long currentTime) {
        return lastRunTime == -1 || lastRunTime + delayMillis < currentTime;
    }
}
