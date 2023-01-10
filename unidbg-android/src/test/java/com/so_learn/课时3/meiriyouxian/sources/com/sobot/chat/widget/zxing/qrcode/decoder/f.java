package com.sobot.chat.widget.zxing.qrcode.decoder;

/* compiled from: QRCodeDecoderMetaData */
public final class f {
    private final boolean a;

    f(boolean z) {
        this.a = z;
    }

    public void a(com.sobot.chat.widget.zxing.f[] fVarArr) {
        if (this.a && fVarArr != null && fVarArr.length >= 3) {
            com.sobot.chat.widget.zxing.f fVar = fVarArr[0];
            fVarArr[0] = fVarArr[2];
            fVarArr[2] = fVar;
        }
    }
}
