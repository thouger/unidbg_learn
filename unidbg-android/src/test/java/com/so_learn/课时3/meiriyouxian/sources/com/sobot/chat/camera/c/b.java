package com.sobot.chat.camera.c;

import java.util.Locale;

/* compiled from: AudioUtil */
public class b {
    public static String a(long j) {
        long j2 = j / 1000;
        long j3 = j2 / 60;
        long j4 = j2 % 60;
        if (j3 < 60) {
            return String.format(Locale.getDefault(), "%01d:%02d", Long.valueOf(j3), Long.valueOf(j4));
        }
        return String.format(Locale.getDefault(), "%d:%02d:%02d", Long.valueOf(j3 / 60), Long.valueOf(j3 % 60), Long.valueOf(j4));
    }
}
