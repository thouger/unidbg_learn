package com.sobot.chat.core.a.a;

import com.sobot.chat.core.a.b.b;
import java.net.InetSocketAddress;

/* compiled from: SocketClientAddress */
public class a {
    final a a;
    private a b;
    private String c;
    private String d;
    private int e;

    public a() {
        this(null, null);
    }

    public a(String str, String str2) {
        this(str, str2, 15000);
    }

    public a(String str, String str2, int i) {
        this.a = this;
        this.c = str;
        this.d = str2;
        this.e = i;
    }

    public a a() {
        a aVar = new a(e(), f(), g());
        aVar.a(this);
        return aVar;
    }

    public void b() {
        if (!b.a(e(), "^(25[0-5]|2[0-4][0-9]|1{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|1{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|1{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|1{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$")) {
            throw new IllegalArgumentException("we need a correct remote IP to connect. Current is " + e());
        } else if (!b.a(f(), "^6553[0-5]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{0,3}$")) {
            throw new IllegalArgumentException("we need a correct remote port to connect. Current is " + f());
        } else if (g() < 0) {
            throw new IllegalArgumentException("we need connectionTimeout > 0. Current is " + g());
        }
    }

    public int c() {
        if (f() == null) {
            return 0;
        }
        return Integer.valueOf(f()).intValue();
    }

    public InetSocketAddress d() {
        return new InetSocketAddress(e(), c());
    }

    /* access modifiers changed from: protected */
    public a a(a aVar) {
        this.b = aVar;
        return this;
    }

    public a a(String str) {
        this.c = str;
        return this;
    }

    public String e() {
        return this.c;
    }

    public a b(String str) {
        this.d = str;
        return this;
    }

    public String f() {
        return this.d;
    }

    public a a(int i) {
        this.e = i;
        return this;
    }

    public int g() {
        return this.e;
    }
}
