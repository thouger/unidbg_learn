package com.sobot.chat.core.a.a;

import com.sobot.chat.core.a.b.a;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: SocketPacket */
public class h {
    private static final AtomicInteger b = new AtomicInteger();
    private final h a;
    private final int c;
    private byte[] d;
    private String e;
    private boolean f;
    private byte[] g;
    private byte[] h;
    private byte[] i;

    public h(byte[] bArr) {
        this(bArr, false);
    }

    public h(byte[] bArr, boolean z) {
        this.a = this;
        this.c = b.getAndIncrement();
        this.d = Arrays.copyOf(bArr, bArr.length);
        this.f = z;
    }

    public void a(String str) {
        if (b() != null) {
            this.d = a.a(b(), str);
        }
    }

    public byte[] a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }

    public h a(byte[] bArr) {
        this.g = bArr;
        return this;
    }

    public h b(byte[] bArr) {
        this.h = bArr;
        return this;
    }

    public h c(byte[] bArr) {
        this.i = bArr;
        return this;
    }
}
