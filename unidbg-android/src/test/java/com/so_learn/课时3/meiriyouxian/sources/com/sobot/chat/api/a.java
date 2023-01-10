package com.sobot.chat.api;

import android.content.Context;

/* compiled from: ZhiChiApiFactory */
public class a {
    public static ZhiChiApi a(Context context) {
        if (context != null) {
            return new ZhiChiApiImpl(context.getApplicationContext());
        }
        throw new IllegalArgumentException("The context can not be null");
    }
}
