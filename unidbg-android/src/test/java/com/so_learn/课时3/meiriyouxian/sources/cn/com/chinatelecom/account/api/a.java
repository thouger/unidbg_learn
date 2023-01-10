package cn.com.chinatelecom.account.api;

import android.app.backup.FullBackup;
import android.content.Context;
import android.net.Network;
import android.os.Build;
import cn.com.chinatelecom.account.api.b.d;
import cn.com.chinatelecom.account.api.b.e;
import cn.com.chinatelecom.account.api.b.g;
import cn.com.chinatelecom.account.api.c.f;
import cn.com.chinatelecom.account.api.c.h;
import cn.com.chinatelecom.account.api.c.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.qcloud.tim.uikit.component.video.JCameraView;
import java.util.concurrent.Future;
import org.json.JSONObject;

public final class a {
    private static final String a = a.class.getSimpleName();
    private boolean b = false;

    /* access modifiers changed from: package-private */
    /* renamed from: cn.com.chinatelecom.account.api.a$1  reason: invalid class name */
    public class AnonymousClass1 extends g.a {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ CtSetting e;
        final /* synthetic */ String f;
        final /* synthetic */ String g;
        final /* synthetic */ ResultListener h;

        AnonymousClass1(Context context, String str, String str2, String str3, CtSetting ctSetting, String str4, String str5, ResultListener resultListener) {
            this.a = context;
            this.b = str;
            this.c = str2;
            this.d = str3;
            this.e = ctSetting;
            this.f = str4;
            this.g = str5;
            this.h = resultListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(7625, false);
            String a = a.a(a.this, this.a, this.b, this.c, this.d, this.e, null, this.f, this.g);
            if (!a()) {
                CtAuth.postResultOnMainThread(this.a, this.f, a, this.h);
            }
            AppMethodBeat.o(7625);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.com.chinatelecom.account.api.a$2  reason: invalid class name */
    public class AnonymousClass2 implements e.a {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ CtSetting e;
        final /* synthetic */ String f;
        final /* synthetic */ String g;
        final /* synthetic */ ResultListener h;
        private boolean j = false;
        private boolean k = false;

        AnonymousClass2(Context context, String str, String str2, String str3, CtSetting ctSetting, String str4, String str5, ResultListener resultListener) {
            this.a = context;
            this.b = str;
            this.c = str2;
            this.d = str3;
            this.e = ctSetting;
            this.f = str4;
            this.g = str5;
            this.h = resultListener;
        }

        @Override // cn.com.chinatelecom.account.api.b.e.a
        public synchronized void a() {
            int i = 7637;
            AppMethodBeat.i(7637, false);
            this.j = true;
            synchronized (a.this) {
                try {
                    a.this.b = true;
                } finally {
                    AppMethodBeat.o(i);
                }
            }
            if (!this.k) {
                f.a(this.f).a(JCameraView.MEDIA_QUALITY_SORRY).e("\u8bf7\u6c42\u8d85\u65f6");
                CtAuth.postResultOnMainThread(this.a, this.f, j.c(), this.h);
            }
        }

        @Override // cn.com.chinatelecom.account.api.b.e.a
        public synchronized void a(int i, String str, long j) {
            AppMethodBeat.i(7635, false);
            if (!this.j) {
                if (!this.k) {
                    this.k = true;
                    f.a(this.f).g("Switching network failed (L)").a(i).e(str).b(j);
                    CtAuth.postResultOnMainThread(this.a, this.f, j.a(i, str), this.h);
                    String str2 = a.a;
                    CtAuth.info(str2, "Switching network failed (L), errorMsg :" + str + " , expendTime \uff1a" + j);
                    AppMethodBeat.o(7635);
                    return;
                }
            }
            AppMethodBeat.o(7635);
        }

        @Override // cn.com.chinatelecom.account.api.b.e.a
        public void a(Network network, long j) {
            int i = 7633;
            AppMethodBeat.i(7633, false);
            String str = a.a;
            CtAuth.info(str, "Switching network successfully (L) , expendTime \uff1a" + j);
            if (this.j || this.k) {
                AppMethodBeat.o(7633);
                return;
            }
            String a = a.a(a.this, this.a, this.b, this.c, this.d, this.e, network, this.f, this.g);
            synchronized (this) {
                try {
                    if (!this.j) {
                        if (!this.k) {
                            this.k = true;
                            f.a(this.f).b(j);
                            CtAuth.postResultOnMainThread(this.a, this.f, a, this.h);
                            AppMethodBeat.o(7633);
                        }
                    }
                } finally {
                    AppMethodBeat.o(i);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.com.chinatelecom.account.api.a$3  reason: invalid class name */
    public class AnonymousClass3 extends g.a {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ CtSetting e;
        final /* synthetic */ String f;
        final /* synthetic */ String g;
        final /* synthetic */ ResultListener h;

        AnonymousClass3(Context context, String str, String str2, String str3, CtSetting ctSetting, String str4, String str5, ResultListener resultListener) {
            this.a = context;
            this.b = str;
            this.c = str2;
            this.d = str3;
            this.e = ctSetting;
            this.f = str4;
            this.g = str5;
            this.h = resultListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(7645, false);
            e eVar = new e();
            Context context = this.a;
            if (eVar.a(context, h.a(context))) {
                if (a()) {
                    AppMethodBeat.o(7645);
                    return;
                }
                String a = a.a(a.this, this.a, this.b, this.c, this.d, this.e, null, this.f, this.g);
                if (a()) {
                    AppMethodBeat.o(7645);
                    return;
                }
                CtAuth.postResultOnMainThread(this.a, this.f, a, this.h);
            } else if (a()) {
                AppMethodBeat.o(7645);
                return;
            } else {
                f.a(this.f).g("Switching network failed (4.x)").a(80800).e("WIFI\u5207\u6362\u8d85\u65f6(4.x)");
                CtAuth.info(a.a, "\u5207\u6362\u7f51\u7edc\u8d85\u65f6(4.x)");
                CtAuth.postResultOnMainThread(this.a, this.f, j.f(), this.h);
            }
            AppMethodBeat.o(7645);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.com.chinatelecom.account.api.a$4  reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ Future a;
        final /* synthetic */ int b;
        final /* synthetic */ g.a c;
        final /* synthetic */ String d;
        final /* synthetic */ Context e;
        final /* synthetic */ ResultListener f;

        AnonymousClass4(Future future, int i, g.a aVar, String str, Context context, ResultListener resultListener) {
            this.a = future;
            this.b = i;
            this.c = aVar;
            this.d = str;
            this.e = context;
            this.f = resultListener;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a0, code lost:
            if (r2.isDone() == false) goto L_0x001b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0019, code lost:
            if (r2.isDone() == false) goto L_0x001b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x001b, code lost:
            r6.a.cancel(true);
         */
        @Override // java.lang.Runnable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r6 = this;
                r0 = 7655(0x1de7, float:1.0727E-41)
                r1 = 0
                cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
                r1 = 1
                java.util.concurrent.Future r2 = r6.a     // Catch:{ all -> 0x0022 }
                int r3 = r6.b     // Catch:{ all -> 0x0022 }
                long r3 = (long) r3     // Catch:{ all -> 0x0022 }
                java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x0022 }
                r2.get(r3, r5)     // Catch:{ all -> 0x0022 }
                java.util.concurrent.Future r2 = r6.a
                if (r2 == 0) goto L_0x00a4
                boolean r2 = r2.isDone()
                if (r2 != 0) goto L_0x00a4
            L_0x001b:
                java.util.concurrent.Future r2 = r6.a
                r2.cancel(r1)
                goto L_0x00a4
            L_0x0022:
                r2 = move-exception
                cn.com.chinatelecom.account.api.b.g$a r3 = r6.c     // Catch:{ all -> 0x00ae }
                r3.a(r1)     // Catch:{ all -> 0x00ae }
                cn.com.chinatelecom.account.api.a r3 = cn.com.chinatelecom.account.api.a.this     // Catch:{ all -> 0x00ae }
                monitor-enter(r3)     // Catch:{ all -> 0x00ae }
                cn.com.chinatelecom.account.api.a r4 = cn.com.chinatelecom.account.api.a.this     // Catch:{ all -> 0x00a8 }
                cn.com.chinatelecom.account.api.a.a(r4, r1)     // Catch:{ all -> 0x00a8 }
                monitor-exit(r3)     // Catch:{ all -> 0x00a8 }
                boolean r3 = r2 instanceof java.util.concurrent.TimeoutException
                if (r3 == 0) goto L_0x0056
                java.lang.String r2 = r6.d
                cn.com.chinatelecom.account.api.c.e r2 = cn.com.chinatelecom.account.api.c.f.a(r2)
                r3 = 80000(0x13880, float:1.12104E-40)
                cn.com.chinatelecom.account.api.c.e r2 = r2.a(r3)
                java.lang.String r3 = "\u8bf7\u6c42\u8d85\u65f6"
                r2.e(r3)
                android.content.Context r2 = r6.e
                java.lang.String r3 = r6.d
                java.lang.String r4 = cn.com.chinatelecom.account.api.c.j.c()
                cn.com.chinatelecom.account.api.ResultListener r5 = r6.f
            L_0x0052:
                cn.com.chinatelecom.account.api.CtAuth.postResultOnMainThread(r2, r3, r4, r5)
                goto L_0x0098
            L_0x0056:
                java.lang.String r3 = cn.com.chinatelecom.account.api.a.a()
                java.lang.String r4 = "submitOnTimeoutInterrupted other exception"
                cn.com.chinatelecom.account.api.CtAuth.warn(r3, r4, r2)
                java.lang.String r3 = r6.d
                cn.com.chinatelecom.account.api.c.e r3 = cn.com.chinatelecom.account.api.c.f.a(r3)
                r4 = 80102(0x138e6, float:1.12247E-40)
                cn.com.chinatelecom.account.api.c.e r3 = r3.a(r4)
                java.lang.String r4 = "\u9884\u767b\u5f55\u5f02\u5e38"
                cn.com.chinatelecom.account.api.c.e r3 = r3.e(r4)
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "submitOnTimeoutInterrupted other exception "
                r4.append(r5)
                java.lang.String r2 = r2.getMessage()
                r4.append(r2)
                java.lang.String r2 = r4.toString()
                r3.g(r2)
                android.content.Context r2 = r6.e
                java.lang.String r3 = r6.d
                java.lang.String r4 = cn.com.chinatelecom.account.api.c.j.g()
                cn.com.chinatelecom.account.api.ResultListener r5 = r6.f
                goto L_0x0052
            L_0x0098:
                java.util.concurrent.Future r2 = r6.a
                if (r2 == 0) goto L_0x00a4
                boolean r2 = r2.isDone()
                if (r2 != 0) goto L_0x00a4
                goto L_0x001b
            L_0x00a4:
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
                return
            L_0x00a8:
                r2 = move-exception
                monitor-exit(r3)
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
                throw r2
            L_0x00ae:
                r2 = move-exception
                java.util.concurrent.Future r3 = r6.a
                if (r3 == 0) goto L_0x00be
                boolean r3 = r3.isDone()
                if (r3 != 0) goto L_0x00be
                java.util.concurrent.Future r3 = r6.a
                r3.cancel(r1)
            L_0x00be:
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.api.a.AnonymousClass4.run():void");
        }
    }

    static {
        AppMethodBeat.i(7691, false);
        AppMethodBeat.o(7691);
    }

    private String a(Context context, String str, String str2, String str3, CtSetting ctSetting, Network network, String str4, String str5) {
        String str6;
        Throwable th;
        boolean z;
        AppMethodBeat.i(7681, false);
        try {
            long a2 = cn.com.chinatelecom.account.api.c.a.a(context);
            String a3 = h.a(context);
            JSONObject jSONObject = new JSONObject(h.a(context, str, str2, str3, a2, ""));
            String optString = jSONObject.optString("p");
            String optString2 = jSONObject.optString(FullBackup.KEY_VALUE_DATA_TOKEN);
            cn.com.chinatelecom.account.api.b.f a4 = d.a(context, a3, optString, ctSetting, network, true, 0, str5, str4, network != null, false);
            if (a4.c) {
                String a5 = a(context, str4, str, str2, str3, ctSetting, network);
                AppMethodBeat.o(7681);
                return a5;
            }
            if (a4.e) {
                synchronized (this) {
                    try {
                        z = this.b;
                    } catch (Throwable th2) {
                        AppMethodBeat.o(7681);
                        throw th2;
                    }
                }
                if (!z) {
                    a4 = d.a(context, a3, optString, ctSetting, network, true, 0, str5, str4, false, true);
                    f.a(str4).b(1);
                }
            }
            str6 = cn.com.chinatelecom.account.api.c.a.a(context, a4, optString2, network, true, str4);
            try {
                f.b(str4, str6, optString);
            } catch (Throwable th3) {
                th = th3;
            }
            AppMethodBeat.o(7681);
            return str6;
        } catch (Throwable th4) {
            th = th4;
            String g = j.g();
            CtAuth.warn(a, "AuthManager getPreMobile() exception", th);
            cn.com.chinatelecom.account.api.c.e a6 = f.a(str4);
            a6.g("AuthManager getPreMobile() exception \uff1a" + th.getMessage()).a(80102).e("\u9884\u767b\u5f55\u5f02\u5e38");
            str6 = g;
            AppMethodBeat.o(7681);
            return str6;
        }
    }

    private String a(Context context, String str, String str2, String str3, String str4, CtSetting ctSetting, Network network) {
        String str5;
        Throwable th;
        AppMethodBeat.i(7685, false);
        try {
            long a2 = cn.com.chinatelecom.account.api.c.a.a(context);
            String a3 = h.a(context);
            JSONObject jSONObject = new JSONObject(h.a(context, str2, str3, str4, a2, ""));
            String optString = jSONObject.optString("p");
            str5 = cn.com.chinatelecom.account.api.c.a.a(context, d.a(context, a3, optString, ctSetting, network, true, 0, "preAuth", str, false, false), jSONObject.optString(FullBackup.KEY_VALUE_DATA_TOKEN), network, true, str);
            try {
                f.b(str, str5, optString);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            str5 = j.g();
            CtAuth.warn(a, "AuthManager switchProtocalRequest() exception", th);
            cn.com.chinatelecom.account.api.c.e a4 = f.a(str);
            a4.g("AuthManager switchProtocalRequest() exception \uff1a" + th.getMessage()).a(80102).e("\u9884\u767b\u5f55\u5f02\u5e38");
            AppMethodBeat.o(7685);
            return str5;
        }
        AppMethodBeat.o(7685);
        return str5;
    }

    static /* synthetic */ String a(a aVar, Context context, String str, String str2, String str3, CtSetting ctSetting, Network network, String str4, String str5) {
        AppMethodBeat.i(7687, false);
        String a2 = aVar.a(context, str, str2, str3, ctSetting, network, str4, str5);
        AppMethodBeat.o(7687);
        return a2;
    }

    private void a(Context context, String str, g.a aVar, int i, ResultListener resultListener) {
        AppMethodBeat.i(7675, false);
        g.a(new AnonymousClass4(g.b(aVar), i, aVar, str, context, resultListener));
        AppMethodBeat.o(7675);
    }

    public void a(Context context, String str, String str2, String str3, CtSetting ctSetting, ResultListener resultListener) {
        AppMethodBeat.i(7668, false);
        int totalTimeout = CtSetting.getTotalTimeout(ctSetting);
        String a2 = cn.com.chinatelecom.account.api.c.d.a();
        String a3 = cn.com.chinatelecom.account.api.c.d.a(context);
        String c = cn.com.chinatelecom.account.api.c.a.c(context);
        f.a(a2).a(a3).c(c).b(cn.com.chinatelecom.account.api.c.g.e(context));
        a(context, a2, new AnonymousClass1(context, str, str2, str3, ctSetting, a2, c, resultListener), totalTimeout, resultListener);
        AppMethodBeat.o(7668);
    }

    public void b(Context context, String str, String str2, String str3, CtSetting ctSetting, ResultListener resultListener) {
        AppMethodBeat.i(7671, false);
        int totalTimeout = CtSetting.getTotalTimeout(ctSetting);
        String a2 = cn.com.chinatelecom.account.api.c.d.a();
        String a3 = cn.com.chinatelecom.account.api.c.d.a(context);
        String c = cn.com.chinatelecom.account.api.c.a.c(context);
        f.a(a2).a(a3).c(c).b("BOTH");
        if (Build.VERSION.SDK_INT >= 21) {
            e eVar = new e();
            eVar.a(context, new AnonymousClass2(context, str, str2, str3, ctSetting, a2, c, resultListener));
            eVar.a(totalTimeout);
        } else {
            a(context, a2, new AnonymousClass3(context, str, str2, str3, ctSetting, a2, c, resultListener), totalTimeout, resultListener);
        }
        AppMethodBeat.o(7671);
    }
}
