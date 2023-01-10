package cn.missfresh.ad.a;

import android.text.TextUtils;
import cn.missfresh.ad.b.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: MFADHttpManager */
public class b {
    private static volatile b b;
    private final String a = b.class.getSimpleName();
    private OkHttpClient c = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).addNetworkInterceptor(this.d).retryOnConnectionFailure(true).build();
    private c d = new c();

    /* compiled from: MFADHttpManager */
    public interface a {
        void a(d dVar, String str);

        void a(String str);
    }

    static /* synthetic */ void a(b bVar, a aVar, d dVar, String str) {
        AppMethodBeat.i(6084, false);
        bVar.a(aVar, dVar, str);
        AppMethodBeat.o(6084);
    }

    private b() {
        AppMethodBeat.i(6065, false);
        AppMethodBeat.o(6065);
    }

    public static b a() {
        AppMethodBeat.i(6068, false);
        if (b == null) {
            synchronized (b.class) {
                try {
                    if (b == null) {
                        b = new b();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(6068);
                    throw th;
                }
            }
        }
        b bVar = b;
        AppMethodBeat.o(6068);
        return bVar;
    }

    public void a(d dVar, a aVar) {
        AppMethodBeat.i(6072, false);
        a(1, dVar, aVar);
        AppMethodBeat.o(6072);
    }

    public void a(int i, d dVar, a aVar) {
        Request request;
        AppMethodBeat.i(6076, false);
        c.a(aVar, "IMFReqADInfoListener should not be null!");
        c.a(dVar.d(), "The url of AD should not be null!");
        Request.Builder builder = new Request.Builder();
        e.a(builder, dVar.b());
        this.d.a(null);
        if (i == 0) {
            request = builder.url(e.a(dVar.d(), dVar.c())).get().build();
        } else {
            HttpUrl a2 = e.a(dVar.d(), dVar.c());
            request = builder.url(a2).post(e.a(dVar.a())).build();
        }
        cn.missfresh.ad.b.a.a("MFADSDK", "Request: body " + request.toString());
        cn.missfresh.ad.b.a.a("MFADSDK", "Request: header " + request.headers().toString() + " header params: " + dVar.b().toString());
        this.c.newCall(request).enqueue(new AnonymousClass1(aVar, dVar));
        AppMethodBeat.o(6076);
    }

    /* compiled from: MFADHttpManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ad.a.b$1  reason: invalid class name */
    public class AnonymousClass1 implements Callback {
        final /* synthetic */ a a;
        final /* synthetic */ d b;

        AnonymousClass1(a aVar, d dVar) {
            this.a = aVar;
            this.b = dVar;
        }

        public void onFailure(Call call, IOException iOException) {
            AppMethodBeat.i(6041, false);
            b.a(b.this, this.a, this.b, iOException.getMessage());
            AppMethodBeat.o(6041);
        }

        public void onResponse(Call call, Response response) throws IOException {
            AppMethodBeat.i(6042, false);
            if (call.isCanceled()) {
                String str = b.this.a;
                cn.missfresh.ad.b.a.a(str, "AD Info request is canceled, params : " + this.b.toString());
            }
            if (!response.isSuccessful() || response.body() == null) {
                b.a(b.this, this.a, this.b, "request failed");
            } else {
                String string = response.body().string();
                if (!TextUtils.isEmpty(string)) {
                    this.a.a(string);
                } else {
                    b.a(b.this, this.a, this.b, "data empty");
                }
            }
            AppMethodBeat.o(6042);
        }
    }

    public void a(String str, String str2, g gVar) {
        long j;
        AppMethodBeat.i(6078, false);
        c.a(gVar, "ProgressResponseListener should not be null!");
        c.a(str, "DownLoad url should not be null!");
        c.a(str2, "DownLoad filePath should not be null!");
        cn.missfresh.ad.b.a.a(this.a, "start download ad");
        this.d.a(gVar);
        File file = new File(str2);
        if (file.exists()) {
            File file2 = new File(file.getParent());
            if (!file2.exists()) {
                file2.mkdirs();
            }
            j = file.length();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                String str3 = this.a;
                cn.missfresh.ad.b.a.a(str3, "\u4e0b\u8f7d\uff1a\u521b\u5efa\u6587\u4ef6\u5931\u8d25\uff1a " + e.getMessage());
            }
            j = 0;
        }
        Request.Builder url = new Request.Builder().url(str);
        Request build = url.addHeader("RANGE", "bytes=" + j + "-").build();
        gVar.a();
        this.c.newCall(build).enqueue(new AnonymousClass2(gVar, file, j, str));
        AppMethodBeat.o(6078);
    }

    /* compiled from: MFADHttpManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ad.a.b$2  reason: invalid class name */
    public class AnonymousClass2 implements Callback {
        final /* synthetic */ g a;
        final /* synthetic */ File b;
        final /* synthetic */ long c;
        final /* synthetic */ String d;

        AnonymousClass2(g gVar, File file, long j, String str) {
            this.a = gVar;
            this.b = file;
            this.c = j;
            this.d = str;
        }

        public void onFailure(Call call, IOException iOException) {
            AppMethodBeat.i(6048, false);
            this.a.a(iOException.getMessage());
            AppMethodBeat.o(6048);
        }

        /* JADX WARNING: Removed duplicated region for block: B:31:0x00ea A[Catch:{ IOException -> 0x00f3 }] */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x00ef A[Catch:{ IOException -> 0x00f3 }] */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x013b A[Catch:{ IOException -> 0x0144 }] */
        /* JADX WARNING: Removed duplicated region for block: B:46:0x0140 A[Catch:{ IOException -> 0x0144 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onResponse(okhttp3.Call r19, okhttp3.Response r20) throws java.io.IOException {
            /*
            // Method dump skipped, instructions count: 357
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.ad.a.b.AnonymousClass2.onResponse(okhttp3.Call, okhttp3.Response):void");
        }
    }

    private void a(a aVar, d dVar, String str) {
        AppMethodBeat.i(6080, false);
        aVar.a(dVar, str);
        AppMethodBeat.o(6080);
    }
}
