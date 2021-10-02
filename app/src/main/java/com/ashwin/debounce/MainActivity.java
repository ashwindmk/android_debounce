package com.ashwin.debounce;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String SUB_TAG = MainActivity.class.getSimpleName();

    private Runnable debouncedRunnable;

    private int flags = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Constant.APP_TAG, SUB_TAG + ": onCreate");
        setContentView(R.layout.activity_main);

//        debouncedRunnable = new DebouncedQueueFirstRunnable(this::openNext, "open-next", 500L);
        debouncedRunnable = new DebouncedDropRunnable(this::openNext, "open-next", 500L);

        Button test1Button = findViewById(R.id.test_1_button);
        test1Button.setOnClickListener(v -> {
            flags = flags | 1;
        });

        Button test2Button = findViewById(R.id.test_2_button);
        test2Button.setOnClickListener(v -> {
            Log.d(Constant.APP_TAG, SUB_TAG + ": flag 1 : " + ((flags & 1) != 0));
        });

        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> {
//            openNext();
            debouncedRunnable.run();
            SystemClock.sleep(100L);
            debouncedRunnable.run();
            SystemClock.sleep(100L);
            debouncedRunnable.run();
        });

        Button closeButton = findViewById(R.id.close_button);
        closeButton.setOnClickListener(v -> {
            finish();
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(Constant.APP_TAG, SUB_TAG + ": onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(Constant.APP_TAG, SUB_TAG + ": onRestart");
        ActivityHelper.logIsFinishing(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constant.APP_TAG, SUB_TAG + ": onStart");
        ActivityHelper.logIsScreenOn(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ActivityHelper.logIsScreenOn(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d(Constant.APP_TAG, SUB_TAG + ": onPostCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(Constant.APP_TAG, SUB_TAG + ": onResume");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d(Constant.APP_TAG, SUB_TAG + ": onPostResume");
        ActivityHelper.logIsFinishing(this);
    }

    private void openNext() {
        Log.d(Constant.APP_TAG, SUB_TAG + ": openNext");

        Bundle extras = new Bundle();
        extras.putString(NextActivity.PARAM_1, "v1");

        Intent i = new Intent(MainActivity.this, NextActivity.class);
        i.putExtras(extras);
        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(Constant.APP_TAG, SUB_TAG + ": onPause");
        ActivityHelper.logIsFinishing(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constant.APP_TAG, SUB_TAG + ": onStop");
        ActivityHelper.logIsFinishing(this);
        ActivityHelper.logIsScreenOn(this);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ActivityHelper.logIsScreenOn(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constant.APP_TAG, SUB_TAG + ": onDestroy");
        ActivityHelper.logIsFinishing(this);
    }
}
