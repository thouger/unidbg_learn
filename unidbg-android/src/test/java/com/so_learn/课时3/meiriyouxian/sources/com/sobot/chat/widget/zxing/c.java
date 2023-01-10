package com.sobot.chat.widget.zxing;

/* compiled from: LuminanceSource */
public abstract class c {
    private final int a;
    private final int b;

    public abstract byte[] a();

    public abstract byte[] a(int i, byte[] bArr);

    protected c(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final int b() {
        return this.a;
    }

    public final int c() {
        return this.b;
    }

    public final String toString() {
        int i = this.a;
        StringBuilder sb = new StringBuilder(this.b * (i + 1));
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < this.b; i2++) {
            bArr = a(i2, bArr);
            for (int i3 = 0; i3 < this.a; i3++) {
                int i4 = bArr[i3] & 255;
                sb.append(i4 < 64 ? '#' : i4 < 128 ? '+' : i4 < 192 ? '.' : ' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
