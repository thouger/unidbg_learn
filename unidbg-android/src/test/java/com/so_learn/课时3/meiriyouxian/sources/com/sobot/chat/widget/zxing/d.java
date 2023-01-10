package com.sobot.chat.widget.zxing;

/* compiled from: RGBLuminanceSource */
public final class d extends c {
    private final byte[] a;
    private final int b;
    private final int c;
    private final int d = 0;
    private final int e = 0;

    public d(int i, int i2, int[] iArr) {
        super(i, i2);
        this.b = i;
        this.c = i2;
        int i3 = i * i2;
        this.a = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = iArr[i4];
            this.a[i4] = (byte) (((((i5 >> 16) & 255) + ((i5 >> 7) & 510)) + (i5 & 255)) / 4);
        }
    }

    @Override // com.sobot.chat.widget.zxing.c
    public byte[] a(int i, byte[] bArr) {
        if (i < 0 || i >= c()) {
            throw new IllegalArgumentException("Requested row is outside the image: " + i);
        }
        int b = b();
        if (bArr == null || bArr.length < b) {
            bArr = new byte[b];
        }
        System.arraycopy(this.a, ((i + this.e) * this.b) + this.d, bArr, 0, b);
        return bArr;
    }

    @Override // com.sobot.chat.widget.zxing.c
    public byte[] a() {
        int b = b();
        int c = c();
        if (b == this.b && c == this.c) {
            return this.a;
        }
        int i = b * c;
        byte[] bArr = new byte[i];
        int i2 = this.e;
        int i3 = this.b;
        int i4 = (i2 * i3) + this.d;
        if (b == i3) {
            System.arraycopy(this.a, i4, bArr, 0, i);
            return bArr;
        }
        for (int i5 = 0; i5 < c; i5++) {
            System.arraycopy(this.a, i4, bArr, i5 * b, b);
            i4 += this.b;
        }
        return bArr;
    }
}
