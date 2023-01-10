package com.sobot.chat.core.http.b;

/* compiled from: StException */
public class a extends Exception {
    private static final long a = -8641198158155821498L;

    public a(String str) {
        super(str);
    }

    public static a a() {
        return new a("unknown exception!");
    }

    public static a b() {
        return new a("breakpoint file does not exist!");
    }

    public static a c() {
        return new a("breakpoint file has expired!");
    }
}
