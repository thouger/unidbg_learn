package cn.missfresh.module.base.network;

import android.os.Handler;
import cn.missfresh.module.base.network.a.f;
import cn.missfresh.module.base.network.a.g;
import cn.missfresh.module.base.network.a.i;
import cn.missfresh.module.base.network.a.j;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.bangcle.andjni.JniLib;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: HttpManagerNew */
public class c {
    private static c a;
    private static OkHttpClient b;
    private static OkHttpClient c;
    private static a d;
    private static boolean e;
    private final b f;
    private Handler g;

    /* compiled from: HttpManagerNew */
    /* renamed from: cn.missfresh.module.base.network.c$1  reason: invalid class name */
    class AnonymousClass1 implements b {
        final /* synthetic */ c a;

        AnonymousClass1(c cVar) {
            JniLib.cV(this, cVar, 100);
        }

        @Override // cn.missfresh.module.base.network.c.b
        public void a() {
            JniLib.cV(this, 97);
        }

        @Override // cn.missfresh.module.base.network.c.b
        public void a(int i) {
        }

        @Override // cn.missfresh.module.base.network.c.b
        public void a(String str) {
        }

        @Override // cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            JniLib.cV(this, request, exc, 98);
        }

        @Override // cn.missfresh.module.base.network.c.b
        public void b() {
            JniLib.cV(this, 99);
        }

        @Override // cn.missfresh.module.base.network.c.b
        public void b(String str) {
        }
    }

    /* compiled from: HttpManagerNew */
    /* renamed from: cn.missfresh.module.base.network.c$2  reason: invalid class name */
    class AnonymousClass2 implements Callback {
        final /* synthetic */ Request a;
        final /* synthetic */ b b;
        final /* synthetic */ c c;

        AnonymousClass2(c cVar, Request request, b bVar) {
            JniLib.cV(this, cVar, request, bVar, 103);
        }

        public void onFailure(Call call, IOException iOException) {
            JniLib.cV(this, call, iOException, 101);
        }

        public void onResponse(Call call, Response response) throws IOException {
            JniLib.cV(this, call, response, 102);
        }
    }

    /* compiled from: HttpManagerNew */
    public interface b {
        void a();

        void a(int i);

        void a(String str);

        void a(Request request, Exception exc);

        void b();

        void b(String str);
    }

    private c() {
        JniLib.cV(this, 116);
    }

    public static String a(String str, Map<String, String> map) {
        return (String) JniLib.cL(str, map, 117);
    }

    public static synchronized void a() {
        JniLib.cV(118);
    }

    private void a(b bVar) {
        JniLib.cV(this, bVar, 119);
    }

    private void a(b bVar, Request request) {
        JniLib.cV(this, bVar, request, 120);
    }

    public static void a(Object obj, String str, Map<String, String> map, b bVar) {
        JniLib.cV(obj, str, map, bVar, 121);
    }

    public static void a(Object obj, String str, Map<String, String> map, JSONObject jSONObject, b bVar) {
        JniLib.cV(obj, str, map, jSONObject, bVar, 122);
    }

    public static void a(Object obj, String str, Map<String, String> map, JSONObject jSONObject, Request.Builder builder, b bVar) {
        JniLib.cV(obj, str, map, jSONObject, builder, bVar, 123);
    }

    private void a(String str, MissFreshResponse missFreshResponse, b bVar) {
        JniLib.cV(this, str, missFreshResponse, bVar, 124);
    }

    private void a(Request request, Exception exc, b bVar) {
        JniLib.cV(this, request, exc, bVar, 125);
    }

    private void a(Request request, Callback callback) {
        JniLib.cV(this, request, callback, 126);
    }

    private boolean a(MissFreshResponse missFreshResponse, String str) {
        return JniLib.cZ(this, missFreshResponse, str, 127);
    }

    private void b() {
        JniLib.cV(this, 128);
    }

    public static void b(Object obj, String str, Map<String, String> map, b bVar) {
        JniLib.cV(obj, str, map, bVar, 129);
    }

    public static void b(Object obj, String str, Map<String, String> map, JSONObject jSONObject, b bVar) {
        JniLib.cV(obj, str, map, jSONObject, bVar, 130);
    }

    private boolean b(MissFreshResponse missFreshResponse, String str) {
        return JniLib.cZ(this, missFreshResponse, str, 131);
    }

    public static void c(Object obj, String str, Map<String, String> map, b bVar) {
        JniLib.cV(obj, str, map, bVar, 132);
    }

    public static void c(Object obj, String str, Map<String, String> map, JSONObject jSONObject, b bVar) {
        JniLib.cV(obj, str, map, jSONObject, bVar, 133);
    }

    static /* synthetic */ void a(c cVar) {
        AppMethodBeat.i(15313, false);
        cVar.b();
        AppMethodBeat.o(15313);
    }

    static /* synthetic */ void a(c cVar, b bVar, Request request) {
        AppMethodBeat.i(15314, false);
        cVar.a(bVar, request);
        AppMethodBeat.o(15314);
    }

    static /* synthetic */ void a(c cVar, String str, MissFreshResponse missFreshResponse, b bVar) {
        AppMethodBeat.i(15311, false);
        cVar.a(str, missFreshResponse, bVar);
        AppMethodBeat.o(15311);
    }

    static /* synthetic */ void a(c cVar, Request request, Exception exc, b bVar) {
        AppMethodBeat.i(15310, false);
        cVar.a(request, exc, bVar);
        AppMethodBeat.o(15310);
    }

    private static OkHttpClient a(long j, long j2, long j3) {
        AppMethodBeat.i(15270, false);
        OkHttpClient.Builder eventListenerFactory = new OkHttpClient.Builder().connectTimeout(j, TimeUnit.SECONDS).readTimeout(j2, TimeUnit.SECONDS).writeTimeout(j3, TimeUnit.SECONDS).retryOnConnectionFailure(false).addNetworkInterceptor(new cn.missfresh.module.base.network.a.c()).addNetworkInterceptor(new i()).addNetworkInterceptor(new g()).addNetworkInterceptor(new f()).addNetworkInterceptor(new j()).eventListenerFactory(b.a);
        e.a(eventListenerFactory);
        OkHttpClient build = eventListenerFactory.build();
        AppMethodBeat.o(15270);
        return build;
    }

    public static void a(Object obj) {
        AppMethodBeat.i(15283, false);
        if (obj == null) {
            AppMethodBeat.o(15283);
            return;
        }
        Dispatcher dispatcher = (e ? c : b).dispatcher();
        List<Call> queuedCalls = dispatcher.queuedCalls();
        List<Call> runningCalls = dispatcher.runningCalls();
        for (Call call : queuedCalls) {
            if (at.a(obj, call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call2 : runningCalls) {
            if (at.a(obj, call2.request().tag())) {
                call2.cancel();
            }
        }
        AppMethodBeat.o(15283);
    }

    public static Map<String, String> a(String... strArr) {
        HashMap hashMap;
        AppMethodBeat.i(15284, false);
        if (strArr == null || strArr.length % 2 != 0) {
            hashMap = null;
        } else {
            hashMap = new HashMap();
            for (int i = 0; i < strArr.length; i += 2) {
                hashMap.put(strArr[i], strArr[i + 1]);
            }
        }
        AppMethodBeat.o(15284);
        return hashMap;
    }

    public static JSONObject b(String... strArr) {
        JSONObject jSONObject;
        AppMethodBeat.i(15288, false);
        if (strArr == null || strArr.length % 2 != 0) {
            jSONObject = null;
        } else {
            jSONObject = new JSONObject();
            for (int i = 0; i < strArr.length; i += 2) {
                try {
                    jSONObject.put(strArr[i], strArr[i + 1]);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        AppMethodBeat.o(15288);
        return jSONObject;
    }

    /* compiled from: HttpManagerNew */
    /* renamed from: cn.missfresh.module.base.network.c$3  reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ b a;
        final /* synthetic */ Request b;
        final /* synthetic */ Exception c;
        final /* synthetic */ c d;

        AnonymousClass3(c cVar, b bVar, Request request, Exception exc) {
            JniLib.cV(this, cVar, bVar, request, exc, 104);
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(15223, false);
            this.a.a(this.b, this.c);
            AppMethodBeat.o(15223);
        }
    }

    /* compiled from: HttpManagerNew */
    /* renamed from: cn.missfresh.module.base.network.c$4  reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ b a;
        final /* synthetic */ c b;

        AnonymousClass4(c cVar, b bVar) {
            JniLib.cV(this, cVar, bVar, 105);
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(15225, false);
            this.a.b();
            AppMethodBeat.o(15225);
        }
    }

    /* compiled from: HttpManagerNew */
    /* renamed from: cn.missfresh.module.base.network.c$5  reason: invalid class name */
    class AnonymousClass5 implements Runnable {
        final /* synthetic */ b a;
        final /* synthetic */ MissFreshResponse b;
        final /* synthetic */ c c;

        AnonymousClass5(c cVar, b bVar, MissFreshResponse missFreshResponse) {
            JniLib.cV(this, cVar, bVar, missFreshResponse, 106);
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(15227, false);
            this.a.b(this.b.content);
            AppMethodBeat.o(15227);
        }
    }

    /* compiled from: HttpManagerNew */
    /* renamed from: cn.missfresh.module.base.network.c$6  reason: invalid class name */
    class AnonymousClass6 implements Runnable {
        final /* synthetic */ b a;
        final /* synthetic */ MissFreshResponse b;
        final /* synthetic */ c c;

        AnonymousClass6(c cVar, b bVar, MissFreshResponse missFreshResponse) {
            JniLib.cV(this, cVar, bVar, missFreshResponse, 107);
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(15231, false);
            this.a.a(this.b.content);
            AppMethodBeat.o(15231);
        }
    }

    /* compiled from: HttpManagerNew */
    /* renamed from: cn.missfresh.module.base.network.c$7  reason: invalid class name */
    class AnonymousClass7 implements Runnable {
        final /* synthetic */ b a;
        final /* synthetic */ MissFreshResponse b;
        final /* synthetic */ c c;

        AnonymousClass7(c cVar, b bVar, MissFreshResponse missFreshResponse) {
            JniLib.cV(this, cVar, bVar, missFreshResponse, 108);
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(15239, false);
            this.a.a(this.b.code);
            AppMethodBeat.o(15239);
        }
    }

    /* compiled from: HttpManagerNew */
    /* renamed from: cn.missfresh.module.base.network.c$8  reason: invalid class name */
    class AnonymousClass8 implements Runnable {
        final /* synthetic */ b a;
        final /* synthetic */ c b;

        AnonymousClass8(c cVar, b bVar) {
            JniLib.cV(this, cVar, bVar, 109);
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(15242, false);
            c.a(this.b);
            this.a.a();
            AppMethodBeat.o(15242);
        }
    }

    /* compiled from: HttpManagerNew */
    private class a {
        final /* synthetic */ c a;
        private final MediaType b;

        private a(c cVar) {
            JniLib.cV(this, cVar, 113);
        }

        private Request a(int i, Object obj, String str, Map<String, String> map, JSONObject jSONObject, Request.Builder builder, boolean z, b bVar) {
            return (Request) JniLib.cL(this, Integer.valueOf(i), obj, str, map, jSONObject, builder, Boolean.valueOf(z), bVar, 114);
        }

        private Request b(int i, Object obj, String str, Map<String, String> map, JSONObject jSONObject, boolean z, b bVar) {
            return (Request) JniLib.cL(this, Integer.valueOf(i), obj, str, map, jSONObject, Boolean.valueOf(z), bVar, 115);
        }

        public void a(int i, Object obj, String str, Map<String, String> map, JSONObject jSONObject, b bVar) {
            JniLib.cV(this, Integer.valueOf(i), obj, str, map, jSONObject, bVar, 110);
        }

        public void a(int i, Object obj, String str, Map<String, String> map, JSONObject jSONObject, boolean z, b bVar) {
            JniLib.cV(this, Integer.valueOf(i), obj, str, map, jSONObject, Boolean.valueOf(z), bVar, 111);
        }

        public void a(int i, Object obj, String str, Map<String, String> map, JSONObject jSONObject, boolean z, Request.Builder builder, b bVar) {
            JniLib.cV(this, Integer.valueOf(i), obj, str, map, jSONObject, Boolean.valueOf(z), builder, bVar, 112);
        }

        /* synthetic */ a(c cVar, AnonymousClass1 r2) {
            this(cVar);
        }
    }
}
