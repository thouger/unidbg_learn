package com.sobot.chat.widget.zxing;

import java.util.EnumMap;
import java.util.Map;

/* compiled from: Result */
public final class e {
    private final String a;
    private final byte[] b;
    private final int c;
    private f[] d;
    private final BarcodeFormat e;
    private Map<ResultMetadataType, Object> f;
    private final long g;

    public e(String str, byte[] bArr, f[] fVarArr, BarcodeFormat barcodeFormat) {
        this(str, bArr, fVarArr, barcodeFormat, System.currentTimeMillis());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public e(String str, byte[] bArr, f[] fVarArr, BarcodeFormat barcodeFormat, long j) {
        this(str, bArr, bArr == null ? 0 : bArr.length * 8, fVarArr, barcodeFormat, j);
    }

    public e(String str, byte[] bArr, int i, f[] fVarArr, BarcodeFormat barcodeFormat, long j) {
        this.a = str;
        this.b = bArr;
        this.c = i;
        this.d = fVarArr;
        this.e = barcodeFormat;
        this.f = null;
        this.g = j;
    }

    public String a() {
        return this.a;
    }

    public byte[] b() {
        return this.b;
    }

    public Map<ResultMetadataType, Object> c() {
        return this.f;
    }

    public void a(ResultMetadataType resultMetadataType, Object obj) {
        if (this.f == null) {
            this.f = new EnumMap(ResultMetadataType.class);
        }
        this.f.put(resultMetadataType, obj);
    }

    public String toString() {
        return this.a;
    }
}
