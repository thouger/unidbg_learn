package com.sobot.chat.core.http;

import android.os.Handler;
import android.os.Looper;
import com.sobot.chat.api.apiUtils.SobotBaseUrl;
import com.sobot.chat.core.http.a.c;
import com.sobot.chat.core.http.c.b;
import com.sobot.chat.core.http.d.e;
import com.sobot.chat.utils.m;
import java.io.IOException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* compiled from: OkHttpUtils */
public class a {
    private static a a;
    private OkHttpClient b;
    private Handler c;

    public a(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(new b());
            this.c = new Handler(Looper.getMainLooper());
            builder.hostnameVerifier(new AnonymousClass1());
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance.init((KeyStore) null);
                TrustManager[] trustManagers = instance.getTrustManagers();
                if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                    m.c("Unexpected default trust managers:" + Arrays.toString(trustManagers));
                }
                X509TrustManager x509TrustManager = (X509TrustManager) trustManagers[0];
                builder.sslSocketFactory(a(x509TrustManager), x509TrustManager);
                this.b = builder.build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.b = okHttpClient;
        }
    }

    /* compiled from: OkHttpUtils */
    /* renamed from: com.sobot.chat.core.http.a$1  reason: invalid class name */
    class AnonymousClass1 implements HostnameVerifier {
        AnonymousClass1() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            if (SobotBaseUrl.getApi_Host().contains(str)) {
                return true;
            }
            return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
        }
    }

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(null);
                }
            }
        }
        return a;
    }

    public Handler b() {
        return this.c;
    }

    public OkHttpClient c() {
        return this.b;
    }

    public static com.sobot.chat.core.http.a.a d() {
        return new com.sobot.chat.core.http.a.a();
    }

    public static c e() {
        return new c();
    }

    public void a(e eVar, b bVar) {
        if (bVar == null) {
            bVar = b.d;
        }
        eVar.a().enqueue(new AnonymousClass2(bVar));
    }

    /* compiled from: OkHttpUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.a$2  reason: invalid class name */
    public class AnonymousClass2 implements Callback {
        final /* synthetic */ b a;

        AnonymousClass2(b bVar) {
            this.a = bVar;
        }

        public void onFailure(Call call, IOException iOException) {
            a.this.a(call, iOException, this.a);
        }

        public void onResponse(Call call, Response response) {
            if (!response.isSuccessful()) {
                try {
                    a.this.a(call, new RuntimeException(response.body().string()), this.a);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    a.this.a(this.a.a(response), this.a);
                } catch (Exception e2) {
                    a.this.a(call, e2, this.a);
                }
            }
        }
    }

    public void a(Call call, Exception exc, b bVar) {
        if (bVar != null && !call.isCanceled()) {
            this.c.post(new AnonymousClass3(bVar, call, exc));
        }
    }

    /* compiled from: OkHttpUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.a$3  reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ b a;
        final /* synthetic */ Call b;
        final /* synthetic */ Exception c;

        AnonymousClass3(b bVar, Call call, Exception exc) {
            this.a = bVar;
            this.b = call;
            this.c = exc;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b, this.c);
            this.a.a();
        }
    }

    /* compiled from: OkHttpUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.http.a$4  reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ b a;
        final /* synthetic */ Object b;

        AnonymousClass4(b bVar, Object obj) {
            this.a = bVar;
            this.b = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a((b) this.b);
            this.a.a();
        }
    }

    public void a(Object obj, b bVar) {
        if (bVar != null) {
            this.c.post(new AnonymousClass4(bVar, obj));
        }
    }

    public static void a(Runnable runnable) {
        a().c.post(runnable);
    }

    public void a(Object obj) {
        for (Call call : this.b.dispatcher().queuedCalls()) {
            if (obj.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call2 : this.b.dispatcher().runningCalls()) {
            if (obj.equals(call2.request().tag())) {
                call2.cancel();
            }
        }
    }

    private static SSLSocketFactory a(X509TrustManager x509TrustManager) {
        try {
            SSLContext instance = SSLContext.getInstance(org.apache.http.conn.ssl.SSLSocketFactory.TLS);
            instance.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
            return instance.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
