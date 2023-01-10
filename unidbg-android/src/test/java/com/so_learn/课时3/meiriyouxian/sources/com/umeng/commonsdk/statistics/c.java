package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.analytics.pro.at;
import com.umeng.analytics.pro.bi;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.b;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.idtracking.e;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.internal.a;
import com.umeng.commonsdk.statistics.internal.d;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.statistics.proto.Response;
import java.io.File;

/* compiled from: NetWorkManager */
public class c {
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final String o = "thtstart";
    private static final String p = "gkvc";
    private static final String q = "ekvc";
    String a = null;
    private final int e = 1;
    private com.umeng.commonsdk.statistics.internal.c f;
    private ImprintHandler g;
    private e h = null;
    private ImprintHandler.a i = null;
    private ABTest j = null;
    private Defcon k = null;
    private long l = 0;
    private int m = 0;
    private int n = 0;
    private Context r;

    public c(Context context) {
        this.r = context;
        this.i = ImprintHandler.getImprintService(this.r).c();
        this.k = Defcon.getService(this.r);
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.r);
        this.l = sharedPreferences.getLong(o, 0);
        this.m = sharedPreferences.getInt(p, 0);
        this.n = sharedPreferences.getInt(q, 0);
        this.a = UMEnvelopeBuild.imprintProperty(this.r, "track_list", null);
        this.g = ImprintHandler.getImprintService(this.r);
        this.g.a(new AnonymousClass1());
        if (!UMConfigure.needSendZcfgEnv(this.r)) {
            this.h = e.a(this.r);
        }
        this.f = new com.umeng.commonsdk.statistics.internal.c(this.r);
        this.f.a(StatTracer.getInstance(this.r));
    }

    /* compiled from: NetWorkManager */
    /* renamed from: com.umeng.commonsdk.statistics.c$1  reason: invalid class name */
    class AnonymousClass1 implements d {
        AnonymousClass1() {
        }

        @Override // com.umeng.commonsdk.statistics.internal.d
        public void onImprintChanged(ImprintHandler.a aVar) {
            c.this.k.onImprintChanged(aVar);
            c cVar = c.this;
            cVar.a = UMEnvelopeBuild.imprintProperty(cVar.r, "track_list", null);
        }
    }

    public boolean a(File file) {
        String str;
        int i;
        if (file == null) {
            return false;
        }
        try {
            byte[] byteArray = UMFrUtils.toByteArray(file.getPath());
            if (byteArray == null) {
                return false;
            }
            String name = file.getName();
            if (TextUtils.isEmpty(name)) {
                return false;
            }
            a a = a.a(this.r);
            a.d(name);
            boolean a2 = a.a(name);
            boolean b2 = a.b(name);
            boolean c2 = a.c(name);
            String d2 = com.umeng.commonsdk.stateless.d.d(name);
            if (!TextUtils.isEmpty(d2)) {
                str = com.umeng.commonsdk.stateless.d.c(d2);
            } else if (c2) {
                str = UMServerURL.ZCFG_PATH;
            } else {
                str = UMServerURL.PATH_ANALYTICS;
            }
            byte[] a3 = this.f.a(byteArray, a2, c2, str);
            if (a3 == null) {
                i = 1;
            } else {
                i = a(a3);
            }
            if (UMConfigure.isDebugLog()) {
                if (c2 && i == 2) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "Zero req: succeed.");
                } else if (b2 && i == 2) {
                    MLog.d("\u672c\u6b21\u542f\u52a8\u6570\u636e: \u53d1\u9001\u6210\u529f!");
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "Send instant data: succeed.");
                } else if (!a2 || i != 2) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "Inner req: succeed.");
                } else {
                    MLog.d("\u666e\u901a\u7edf\u8ba1\u6570\u636e: \u53d1\u9001\u6210\u529f!");
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "Send analytics data: succeed.");
                }
            }
            if (i != 1) {
                if (i == 2) {
                    if (this.h != null) {
                        this.h.e();
                    }
                    StatTracer.getInstance(this.r).saveSate();
                } else if (i == 3) {
                    StatTracer.getInstance(this.r).saveSate();
                    if (c2) {
                        FieldManager.a().a(this.r);
                        UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> \u96f6\u53f7\u62a5\u6587\u5e94\u7b54\u5185\u5bb9\u62a5\u9519!!! \uff0c\u7279\u6b8a\u5904\u7406!\uff0c\u7ee7\u7eed\u6b63\u5e38\u6d41\u7a0b\u3002");
                        UMWorkDispatch.sendEvent(this.r, com.umeng.commonsdk.internal.a.s, b.a(this.r).a(), null);
                        return true;
                    }
                }
            }
            if (i == 2) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.r, th);
            return false;
        }
    }

    private int a(byte[] bArr) {
        Response response = new Response();
        try {
            new at(new bi.a()).a(response, bArr);
            if (response.resp_code == 1) {
                this.g.b(response.getImprint());
                this.g.d();
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.r, th);
        }
        return response.resp_code == 1 ? 2 : 3;
    }
}
