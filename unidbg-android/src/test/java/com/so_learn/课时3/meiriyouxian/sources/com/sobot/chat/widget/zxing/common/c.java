package com.sobot.chat.widget.zxing.common;

import java.util.List;

/* compiled from: DecoderResult */
public final class c {
    private final byte[] a;
    private int b;
    private final String c;
    private final List<byte[]> d;
    private final String e;
    private Object f;
    private final int g;
    private final int h;

    public c(byte[] bArr, String str, List<byte[]> list, String str2, int i, int i2) {
        int i3;
        this.a = bArr;
        if (bArr == null) {
            i3 = 0;
        } else {
            i3 = bArr.length * 8;
        }
        this.b = i3;
        this.c = str;
        this.d = list;
        this.e = str2;
        this.g = i2;
        this.h = i;
    }

    public byte[] a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public List<byte[]> c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public Object e() {
        return this.f;
    }

    public void a(Object obj) {
        this.f = obj;
    }

    public boolean f() {
        return this.g >= 0 && this.h >= 0;
    }

    public int g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }
}
