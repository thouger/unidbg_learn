package com.sobot.chat.utils;

/* compiled from: FastClickUtils */
public class g {
    private static long a;

    public static boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - a >= 3000;
        a = currentTimeMillis;
        return z;
    }
}
