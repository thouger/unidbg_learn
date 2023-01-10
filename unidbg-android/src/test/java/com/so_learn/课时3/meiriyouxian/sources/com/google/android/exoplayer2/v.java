package com.google.android.exoplayer2;

/* compiled from: RendererConfiguration */
public final class v {
    public static final v a = new v(0);
    public final int b;

    public v(int i) {
        this.b = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.b == ((v) obj).b;
    }

    public int hashCode() {
        return this.b;
    }
}
