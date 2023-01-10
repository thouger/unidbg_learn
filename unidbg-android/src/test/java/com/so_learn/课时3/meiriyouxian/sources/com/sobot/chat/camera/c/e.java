package com.sobot.chat.camera.c;

import android.os.Build;

/* compiled from: DeviceUtil */
public class e {
    private static String[] a = {"hwH60", "hwPE", "hwH30", "hwHol", "hwG750", "hw7D", "hwChe2"};

    public static String a() {
        return Build.DEVICE;
    }

    public static boolean b() {
        int length = a.length;
        for (int i = 0; i < length; i++) {
            if (a[i].equals(a())) {
                return true;
            }
        }
        return false;
    }
}
