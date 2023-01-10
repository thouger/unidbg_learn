package com.sobot.chat.core.http.b;

/* compiled from: StHttpException */
public class b extends RuntimeException {
    private static final long a = 8773734741709178425L;
    private int b;
    private String c;

    public b(String str) {
        super(str);
    }

    public int a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public static b c() {
        return new b("network error! http response code is 404 or 5xx!");
    }

    public static b a(String str) {
        return new b(str);
    }
}
