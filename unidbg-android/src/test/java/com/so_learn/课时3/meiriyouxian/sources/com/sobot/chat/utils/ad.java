package com.sobot.chat.utils;

import android.media.MediaPlayer;

/* compiled from: TimeTools */
public class ad {
    public static ad a = new ad();

    static {
        if (a == null) {
        }
    }

    public String a(int i) {
        int i2 = i / 3600000;
        int i3 = i - (((i2 * 60) * 60) * 1000);
        int i4 = i3 / MediaPlayer.ProvisioningThread.TIMEOUT_MS;
        int i5 = (i3 - ((i4 * 60) * 1000)) / 1000;
        if (i5 >= 60) {
            i5 %= 60;
            i4 += i5 / 60;
        }
        if (i4 >= 60) {
            i4 %= 60;
            i2 += i4 / 60;
        }
        if (i2 < 10) {
            String str = "00:0" + String.valueOf(i2);
        } else {
            String.valueOf(i2);
        }
        if (i4 < 10) {
            String str2 = "0" + String.valueOf(i4);
        } else {
            String.valueOf(i4);
        }
        if (i5 < 10) {
            return "00:0" + String.valueOf(i5);
        }
        return "00:" + String.valueOf(i5);
    }
}
