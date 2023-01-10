package com.sobot.chat.widget.zxing.qrcode.detector;

import com.sobot.chat.widget.zxing.f;

/* compiled from: AlignmentPattern */
public final class a extends f {
    private final float a;

    a(float f, float f2, float f3) {
        super(f, f2);
        this.a = f3;
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
    public a b(float f, float f2, float f3) {
        return new a((a() + f2) / 2.0f, (b() + f) / 2.0f, (this.a + f3) / 2.0f);
    }
}
