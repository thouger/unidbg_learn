package com.google.android.exoplayer2.audio;

import com.android.internal.logging.nano.MetricsProto;

/* compiled from: AuxEffectInfo */
public final class i {
    public final int a;
    public final float b;

    public i(int i, float f) {
        this.a = i;
        this.b = f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        i iVar = (i) obj;
        return this.a == iVar.a && Float.compare(iVar.b, this.b) == 0;
    }

    public int hashCode() {
        return ((MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE + this.a) * 31) + Float.floatToIntBits(this.b);
    }
}
