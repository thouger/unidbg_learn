package com.sobot.chat.core.a.a;

import com.sobot.chat.core.a.b.a;
import java.util.Arrays;

/* compiled from: SocketResponsePacket */
public class j {
    final j a = this;
    private byte[] b;
    private String c;
    private byte[] d;
    private byte[] e;
    private byte[] f;
    private boolean g;
    private int h;
    private int i;

    public boolean a(byte[] bArr) {
        return Arrays.equals(a(), bArr);
    }

    public void a(String str) {
        if (a() != null) {
            b(a.a(a(), str));
            a(a()[0]);
            b(a()[1]);
        }
    }

    public j b(byte[] bArr) {
        this.b = bArr;
        return this;
    }

    public byte[] a() {
        return this.b;
    }

    public j b(String str) {
        this.c = str;
        return this;
    }

    public String b() {
        return this.c;
    }

    public j c(byte[] bArr) {
        this.d = bArr;
        return this;
    }

    public j d(byte[] bArr) {
        this.e = bArr;
        return this;
    }

    public j e(byte[] bArr) {
        this.f = bArr;
        return this;
    }

    public j a(boolean z) {
        this.g = z;
        return this;
    }

    public boolean c() {
        return this.g;
    }

    public void a(int i) {
        this.h = i;
    }

    public int d() {
        return this.i;
    }

    public void b(int i) {
        this.i = i;
    }
}
