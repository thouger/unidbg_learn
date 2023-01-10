package com.sobot.chat.widget.zxing;

import com.sobot.chat.widget.zxing.common.a;

/* compiled from: BinaryBitmap */
public final class b {
    private final a a;
    private a b;

    public b(a aVar) {
        if (aVar != null) {
            this.a = aVar;
            return;
        }
        throw new IllegalArgumentException("Binarizer must be non-null.");
    }

    public a a() throws NotFoundException {
        if (this.b == null) {
            this.b = this.a.b();
        }
        return this.b;
    }

    public String toString() {
        try {
            return a().toString();
        } catch (NotFoundException unused) {
            return "";
        }
    }
}
