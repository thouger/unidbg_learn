package com.sobot.chat.api.apiUtils;

import android.content.Context;

public class SobotApp {
    private static Context mApplicationContext;

    public static Context getApplicationContext() {
        return mApplicationContext;
    }

    public static void setApplicationContext(Context context) {
        mApplicationContext = context;
    }
}
