package com.sobot.chat.utils;

import android.media.MediaPlayer;

/* compiled from: AudioTools */
public class b {
    private static MediaPlayer a;

    public static MediaPlayer a() {
        if (a == null) {
            a = new MediaPlayer();
        }
        return a;
    }

    public static void b() {
        if (a != null && a().isPlaying()) {
            a().stop();
        }
    }

    public static void c() {
        b();
        a = null;
    }

    public static boolean d() {
        if (a != null) {
            return a().isPlaying();
        }
        return false;
    }
}
