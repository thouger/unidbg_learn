package com.sobot.chat.core.http.c;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: Callback */
public abstract class b<T> {
    public static b d = new AnonymousClass1();

    public abstract T a(Response response) throws Exception;

    public void a() {
    }

    public void a(float f) {
    }

    public abstract void a(T t);

    public abstract void a(Call call, Exception exc);

    public void a(Request request) {
    }

    /* compiled from: Callback */
    /* renamed from: com.sobot.chat.core.http.c.b$1  reason: invalid class name */
    static class AnonymousClass1 extends b {
        @Override // com.sobot.chat.core.http.c.b
        public Object a(Response response) throws Exception {
            return null;
        }

        @Override // com.sobot.chat.core.http.c.b
        public void a(Object obj) {
        }

        @Override // com.sobot.chat.core.http.c.b
        public void a(Call call, Exception exc) {
        }

        AnonymousClass1() {
        }
    }
}
