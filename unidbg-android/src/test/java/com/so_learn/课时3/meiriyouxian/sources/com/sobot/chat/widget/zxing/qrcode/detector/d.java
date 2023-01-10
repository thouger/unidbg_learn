package com.sobot.chat.widget.zxing.qrcode.detector;

import com.sobot.chat.widget.zxing.f;

/* compiled from: FinderPattern */
public final class d extends f {
    private final float a;
    private final int b;

    d(float f, float f2, float f3) {
        this(f, f2, f3, 1);
    }

    private d(float f, float f2, float f3, int i) {
        super(f, f2);
        this.a = f3;
        this.b = i;
    }

    public float c() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public boolean a(float f, float f2, float f3) {
        if (Math.abs(f2 - b()) > f || Math.abs(f3 - a()) > f) {
            return false;
        }
        float abs = Math.abs(f - this.a);
        if (abs <= 1.0f || abs <= this.a) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public d b(float f, float f2, float f3) {
        int i = this.b;
        int i2 = i + 1;
        float a = (((float) i) * a()) + f2;
        float f4 = (float) i2;
        return new d(a / f4, ((((float) this.b) * b()) + f) / f4, ((((float) this.b) * this.a) + f3) / f4, i2);
    }
}
