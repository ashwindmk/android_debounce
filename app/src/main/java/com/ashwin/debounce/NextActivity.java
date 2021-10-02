package com.ashwin.debounce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class NextActivity extends AppCompatActivity {
    private static final String SUB_TAG = NextActivity.class.getSimpleName();

    public static final String PARAM_1 = "param_1";
    public static final String PARAM_2 = "param_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Constant.APP_TAG, SUB_TAG + ": onCreate");
        setContentView(R.layout.activity_next);

        if (getIntent() != null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String param1 = extras.getString(PARAM_1, "default");
                Log.d(Constant.APP_TAG, SUB_TAG + ": extras: " + PARAM_1 + " = " + param1);
            } else {
                Log.d(Constant.APP_TAG, SUB_TAG + ": extras = null");
            }
        } else {
            Log.d(Constant.APP_TAG, SUB_TAG + ": intent = null");
        }
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constant.APP_TAG, SUB_TAG + ": onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constant.APP_TAG, SUB_TAG + ": onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constant.APP_TAG, SUB_TAG + ": onDestroy");
    }
}
