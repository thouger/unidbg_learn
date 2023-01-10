package com.sobot.chat.utils;

import android.content.Context;
import android.content.Intent;

/* compiled from: StServiceUtils */
public class ab {
    public static void a(Context context, Intent intent) {
        try {
            context.startService(intent);
        } catch (Throwable unused) {
        }
    }
}
