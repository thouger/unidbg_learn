package com.google.android.exoplayer2.audio;

import android.media.AudioAttributes;
import com.android.internal.logging.nano.MetricsProto;

/* compiled from: AudioAttributes */
public final class b {
    public static final b a = new a().a();
    public final int b;
    public final int c;
    public final int d;
    private AudioAttributes e;

    /* compiled from: AudioAttributes */
    public static final class a {
        private int a = 0;
        private int b = 0;
        private int c = 1;

        public a a(int i) {
            this.a = i;
            return this;
        }

        public a b(int i) {
            this.c = i;
            return this;
        }

        public b a() {
            return new b(this.a, this.b, this.c);
        }
    }

    private b(int i, int i2, int i3) {
        this.b = i;
        this.c = i2;
        this.d = i3;
    }

    public AudioAttributes a() {
        if (this.e == null) {
            this.e = new AudioAttributes.Builder().setContentType(this.b).setFlags(this.c).setUsage(this.d).build();
        }
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.b == bVar.b && this.c == bVar.c && this.d == bVar.d;
    }

    public int hashCode() {
        return ((((MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE + this.b) * 31) + this.c) * 31) + this.d;
    }
}
