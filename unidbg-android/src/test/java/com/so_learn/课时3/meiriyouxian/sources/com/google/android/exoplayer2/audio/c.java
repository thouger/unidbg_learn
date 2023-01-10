package com.google.android.exoplayer2.audio;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import java.util.Arrays;

/* compiled from: AudioCapabilities */
public final class c {
    public static final c a = new c(new int[]{2}, 8);
    private final int[] b;
    private final int c;

    public static c a(Context context) {
        return a(context.registerReceiver(null, new IntentFilter(AudioManager.ACTION_HDMI_AUDIO_PLUG)));
    }

    static c a(Intent intent) {
        if (intent == null || intent.getIntExtra(AudioManager.EXTRA_AUDIO_PLUG_STATE, 0) == 0) {
            return a;
        }
        return new c(intent.getIntArrayExtra(AudioManager.EXTRA_ENCODINGS), intent.getIntExtra(AudioManager.EXTRA_MAX_CHANNEL_COUNT, 8));
    }

    public c(int[] iArr, int i) {
        if (iArr != null) {
            this.b = Arrays.copyOf(iArr, iArr.length);
            Arrays.sort(this.b);
        } else {
            this.b = new int[0];
        }
        this.c = i;
    }

    public boolean a(int i) {
        return Arrays.binarySearch(this.b, i) >= 0;
    }

    public int a() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return Arrays.equals(this.b, cVar.b) && this.c == cVar.c;
    }

    public int hashCode() {
        return this.c + (Arrays.hashCode(this.b) * 31);
    }

    public String toString() {
        return "AudioCapabilities[maxChannelCount=" + this.c + ", supportedEncodings=" + Arrays.toString(this.b) + "]";
    }
}
