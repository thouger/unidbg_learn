package com.hmt.analytics;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.hmt.analytics.objects.b;
import com.hmt.analytics.objects.d;
import com.hmt.analytics.objects.f;
import com.hmt.analytics.util.j;
import com.hmt.analytics.util.l;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* compiled from: HMTAgent */
public class a {
    public static boolean a = false;
    public static Handler b;
    private static final String c = a.class.getSimpleName();
    private static a d = new a();
    private static AtomicBoolean e = new AtomicBoolean(true);
    private static AtomicBoolean f = new AtomicBoolean(true);
    private static d g = new d();
    private static b h = new b();
    private static l i = new l(l.a.HMT);
    private static boolean j = false;

    static {
        try {
            b = new Handler();
        } catch (Exception e2) {
            com.hmt.analytics.android.a.a(c, "Collected:" + e2.getMessage());
        }
    }

    private a() {
    }

    public static void a(Context context, String str, com.hmt.analytics.util.a aVar) {
        a(context, str, 1, aVar, null);
    }

    public static void a(Context context, String str, int i2, com.hmt.analytics.util.a aVar, com.hmt.analytics.a.a aVar2) {
        a(context, str, i2, aVar, aVar2, null, null);
    }

    /* compiled from: HMTAgent */
    /* access modifiers changed from: package-private */
    /* renamed from: com.hmt.analytics.a$1  reason: invalid class name */
    public static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;
        final /* synthetic */ com.hmt.analytics.util.a d;
        final /* synthetic */ com.hmt.analytics.a.a e;
        final /* synthetic */ String f;
        final /* synthetic */ JSONObject g;

        AnonymousClass1(Context context, String str, int i, com.hmt.analytics.util.a aVar, com.hmt.analytics.a.a aVar2, String str2, JSONObject jSONObject) {
            this.a = context;
            this.b = str;
            this.c = i;
            this.d = aVar;
            this.e = aVar2;
            this.f = str2;
            this.g = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.b(this.a, this.b, this.c, this.d, this.e, this.f, this.g);
        }
    }

    public static void a(Context context, String str, int i2, com.hmt.analytics.util.a aVar, com.hmt.analytics.a.a aVar2, String str2, JSONObject jSONObject) {
        j.a().execute(new AnonymousClass1(context, str, i2, aVar, aVar2, str2, jSONObject));
    }

    public static void b(Context context, String str, int i2, com.hmt.analytics.util.a aVar, com.hmt.analytics.a.a aVar2, String str2, JSONObject jSONObject) {
        f fVar;
        if (!TextUtils.isEmpty(str2)) {
            fVar = new f(str, i2 + "", com.hmt.analytics.android.a.a(), str2, com.hmt.analytics.android.a.m(context), com.hmt.analytics.android.a.d(context));
        } else {
            fVar = new f(str, i2 + "", context);
        }
        com.hmt.analytics.objects.a.a(context, fVar, aVar, aVar2, null, jSONObject);
    }
}
