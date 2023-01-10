package com.sobot.chat.core.http.d;

import com.sobot.chat.core.http.a;
import com.sobot.chat.core.http.c.b;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: RequestCall */
public class e {
    private b a;
    private Request b;
    private Call c;
    private long d;
    private long e;
    private long f;
    private OkHttpClient g;

    public e(b bVar) {
        this.a = bVar;
    }

    public e a(long j) {
        this.d = j;
        return this;
    }

    public e b(long j) {
        this.e = j;
        return this;
    }

    public e c(long j) {
        this.f = j;
        return this;
    }

    public Call a(b bVar) {
        this.b = c(bVar);
        if (this.d > 0 || this.e > 0 || this.f > 0) {
            long j = this.d;
            if (j <= 0) {
                j = 10000;
            }
            this.d = j;
            long j2 = this.e;
            if (j2 <= 0) {
                j2 = 10000;
            }
            this.e = j2;
            long j3 = this.f;
            if (j3 <= 0) {
                j3 = 10000;
            }
            this.f = j3;
            this.g = a.a().c().newBuilder().readTimeout(this.d, TimeUnit.MILLISECONDS).writeTimeout(this.e, TimeUnit.MILLISECONDS).connectTimeout(this.f, TimeUnit.MILLISECONDS).build();
            this.c = this.g.newCall(this.b);
        } else {
            this.c = a.a().c().newCall(this.b);
        }
        return this.c;
    }

    private Request c(b bVar) {
        return this.a.a(bVar);
    }

    public void b(b bVar) {
        a(bVar);
        if (bVar != null) {
            bVar.a(this.b);
        }
        a.a().a(this, bVar);
    }

    public Call a() {
        return this.c;
    }

    public b b() {
        return this.a;
    }

    public Response c() throws IOException {
        a((b) null);
        return this.c.execute();
    }
}
