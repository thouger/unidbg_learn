package com.umeng.analytics;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.pro.c;
import com.umeng.analytics.pro.h;
import com.umeng.analytics.pro.i;
import com.umeng.analytics.pro.j;
import com.umeng.analytics.pro.k;
import com.umeng.analytics.pro.l;
import com.umeng.analytics.pro.m;
import com.umeng.analytics.pro.n;
import com.umeng.analytics.pro.o;
import com.umeng.analytics.pro.r;
import com.umeng.analytics.pro.s;
import com.umeng.analytics.pro.t;
import com.umeng.analytics.pro.u;
import com.umeng.analytics.pro.x;
import com.umeng.common.ISysListener;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: InternalAgent */
public class b implements m, s {
    private static final String A = "umsp_2";
    private static final String B = "umsp_3";
    private static final String C = "umsp_4";
    private static final String D = "umsp_5";
    private static Context a = null;
    private static final String h = "sp_uapp";
    private static final String i = "prepp_uapp";
    private static final int o = 128;
    private static final int p = 256;
    private static String q = "";
    private static String r = "";
    private static final String s = "ekv_bl";
    private static final String t = "ekv_bl_ver";
    private static final String v = "ekv_wl";
    private static final String w = "ekv_wl_ver";
    private static final String z = "umsp_1";
    private ISysListener b;
    private o c;
    private u d;
    private j e;
    private t f;
    private k g;
    private boolean j;
    private volatile JSONObject k;
    private volatile JSONObject l;
    private volatile JSONObject m;
    private boolean n;
    private com.umeng.analytics.filter.a u;
    private com.umeng.analytics.filter.b x;
    private l y;

    static {
        Context appContext = UMGlobalContext.getAppContext();
        if (appContext != null) {
            a = appContext.getApplicationContext();
        }
    }

    private b() {
        this.c = new o();
        this.d = new u();
        this.e = new j();
        this.f = t.a();
        this.g = null;
        this.j = false;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.u = null;
        this.x = null;
        this.y = null;
        this.c.a(this);
    }

    /* compiled from: InternalAgent */
    /* access modifiers changed from: private */
    public static class a {
        private static final b a = new b();

        private a() {
        }
    }

    public static b a() {
        return a.a;
    }

    public void a(Context context) {
        if (context != null) {
            try {
                if (a == null) {
                    a = context.getApplicationContext();
                }
                if (this.u == null) {
                    this.u = new com.umeng.analytics.filter.a("ekv_bl", "ekv_bl_ver");
                    this.u.register(a);
                }
                if (this.x == null) {
                    this.x = new com.umeng.analytics.filter.b("ekv_wl", "ekv_wl_ver");
                    this.x.register(a);
                }
                if (UMUtils.isMainProgress(a)) {
                    if (!this.j) {
                        this.j = true;
                        i(a);
                    }
                    if (Build.VERSION.SDK_INT > 13) {
                        synchronized (this) {
                            if (!this.n) {
                                this.g = k.a(context);
                                if (this.g.a()) {
                                    this.n = true;
                                }
                                this.y = l.a();
                                try {
                                    l.a(context);
                                    this.y.a(this);
                                } catch (Throwable unused) {
                                }
                            }
                        }
                    } else {
                        this.n = true;
                    }
                    if (UMConfigure.isDebugLog()) {
                        UMLog.mutlInfo(i.B, 3, "", null, null);
                    }
                    UMWorkDispatch.registerConnStateObserver(CoreProtocol.getInstance(a));
                }
            } catch (Throwable unused2) {
            }
        }
    }

    private void i(Context context) {
        if (context == null) {
            try {
                MLog.e("unexpected null context in getNativeSuperProperties");
            } catch (Throwable unused) {
            }
        } else {
            if (a == null) {
                a = context.getApplicationContext();
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (this.k == null) {
                this.k = new JSONObject();
            }
            if (this.l == null) {
                this.l = new JSONObject();
            }
            String string = sharedPreferences.getString(i, null);
            if (!TextUtils.isEmpty(string)) {
                try {
                    this.m = new JSONObject(string);
                } catch (JSONException unused2) {
                }
            }
            if (this.m == null) {
                this.m = new JSONObject();
            }
        }
    }

    public JSONObject b() {
        return this.k;
    }

    public JSONObject c() {
        return this.m;
    }

    public JSONObject d() {
        return this.l;
    }

    public void e() {
        this.l = null;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        if (!UMUtils.isMainProgress(a)) {
            MLog.e("onPageStart can not be called in child process");
            return;
        }
        try {
            if (k.b != MobclickAgent.PageMode.LEGACY_AUTO) {
                this.d.a(str);
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        if (!UMUtils.isMainProgress(a)) {
            MLog.e("onPageEnd can not be called in child process");
            return;
        }
        try {
            if (k.b != MobclickAgent.PageMode.LEGACY_AUTO) {
                this.d.b(str);
            }
        } catch (Throwable unused) {
        }
    }

    public void a(ISysListener iSysListener) {
        if (!UMUtils.isMainProgress(a)) {
            MLog.e("setSysListener can not be called in child process");
        } else {
            this.b = iSysListener;
        }
    }

    public void a(Context context, int i2) {
        if (context == null) {
            MLog.e("unexpected null context in setVerticalType");
            return;
        }
        if (a == null) {
            a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(a)) {
            MLog.e("setVerticalType can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(a);
        }
        AnalyticsConfig.a(a, i2);
    }

    public String f() {
        if (UMUtils.isMainProgress(a)) {
            return q;
        }
        MLog.e("getOnResumedActivityName can not be called in child process");
        return null;
    }

    public String g() {
        if (UMUtils.isMainProgress(a)) {
            return r;
        }
        MLog.e("getOnPausedActivityName can not be called in child process");
        return null;
    }

    /* access modifiers changed from: package-private */
    public void b(Context context) {
        if (context == null) {
            MLog.e("unexpected null context in onResume");
        } else if (k.b != MobclickAgent.PageMode.AUTO) {
            if (a == null) {
                a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(a)) {
                MLog.e("onResume can not be called in child process");
                return;
            }
            if (UMConfigure.isDebugLog() && !(context instanceof Activity)) {
                UMLog.aq(i.o, 2, "\\|");
            }
            try {
                if (!this.j || !this.n) {
                    a(context);
                }
                if (k.b != MobclickAgent.PageMode.LEGACY_MANUAL) {
                    this.e.a(context.getClass().getName());
                }
                h();
                if (UMConfigure.isDebugLog() && (context instanceof Activity)) {
                    q = context.getClass().getName();
                }
            } catch (Throwable th) {
                MLog.e("Exception occurred in Mobclick.onResume(). ", th);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c(Context context) {
        if (context == null) {
            UMLog.aq(i.p, 0, "\\|");
        } else if (k.b != MobclickAgent.PageMode.AUTO) {
            if (a == null) {
                a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(a)) {
                MLog.e("onPause can not be called in child process");
                return;
            }
            if (UMConfigure.isDebugLog() && !(context instanceof Activity)) {
                UMLog.aq(i.q, 2, "\\|");
            }
            try {
                if (!this.j || !this.n) {
                    a(context);
                }
                if (k.b != MobclickAgent.PageMode.LEGACY_MANUAL) {
                    this.e.b(context.getClass().getName());
                }
                i();
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e("Exception occurred in Mobclick.onRause(). ", th);
                }
            }
            if (UMConfigure.isDebugLog() && (context instanceof Activity)) {
                r = context.getClass().getName();
            }
        }
    }

    public void a(Context context, String str, HashMap<String, Object> hashMap) {
        if (context != null) {
            try {
                if (a == null) {
                    a = context.getApplicationContext();
                }
                if (!UMUtils.isMainProgress(a)) {
                    MLog.e("onGKVEvent can not be called in child process");
                    return;
                }
                if (!this.j || !this.n) {
                    a(a);
                }
                String str2 = "";
                if (this.k == null) {
                    this.k = new JSONObject();
                } else {
                    str2 = this.k.toString();
                }
                r.a(a).a(str, hashMap, str2);
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, String str) {
        if (context == null) {
            UMLog.aq(i.w, 0, "\\|");
            return;
        }
        if (a == null) {
            a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(a)) {
            MLog.e("reportError can not be called in child process");
        } else if (!TextUtils.isEmpty(str)) {
            try {
                if (!this.j || !this.n) {
                    a(a);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(c.Q, 2);
                jSONObject.put(c.R, str);
                jSONObject.put("__ii", this.f.c());
                UMWorkDispatch.sendEvent(a, 4106, CoreProtocol.getInstance(a), jSONObject);
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                }
            }
        } else if (UMConfigure.isDebugLog()) {
            UMLog.aq(i.x, 0, "\\|");
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, Throwable th) {
        if (context == null || th == null) {
            UMLog.aq(i.y, 0, "\\|");
            return;
        }
        if (a == null) {
            a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(a)) {
            MLog.e("reportError can not be called in child process");
            return;
        }
        try {
            if (!this.j || !this.n) {
                a(a);
            }
            a(a, DataHelper.convertExceptionToString(th));
        } catch (Exception e) {
            if (MLog.DEBUG) {
                MLog.e(e);
            }
        }
    }

    public void h() {
        try {
            if (a != null) {
                if (!UMUtils.isMainProgress(a)) {
                    MLog.e("onStartSessionInternal can not be called in child process");
                    return;
                }
                if (UMConfigure.isDebugLog() && !UMConfigure.getInitStatus()) {
                    UMLog.mutlInfo(i.H, 3, "", null, null);
                }
                long currentTimeMillis = System.currentTimeMillis();
                UMWorkDispatch.sendEvent(a, 4352, CoreProtocol.getInstance(a), Long.valueOf(currentTimeMillis));
                UMWorkDispatch.sendEvent(a, 4103, CoreProtocol.getInstance(a), Long.valueOf(currentTimeMillis));
            }
            if (this.b != null) {
                this.b.onAppResume();
            }
        } catch (Throwable unused) {
        }
    }

    public void i() {
        try {
            if (a != null) {
                if (!UMUtils.isMainProgress(a)) {
                    MLog.e("onEndSessionInternal can not be called in child process");
                    return;
                }
                UMWorkDispatch.sendEvent(a, 4104, CoreProtocol.getInstance(a), Long.valueOf(System.currentTimeMillis()));
                UMWorkDispatch.sendEvent(a, 4100, CoreProtocol.getInstance(a), null);
                UMWorkDispatch.sendEvent(a, 4099, CoreProtocol.getInstance(a), null);
                UMWorkDispatch.sendEvent(a, 4105, CoreProtocol.getInstance(a), null);
            }
        } catch (Throwable unused) {
        }
        ISysListener iSysListener = this.b;
        if (iSysListener != null) {
            iSysListener.onAppPause();
        }
    }

    public void b(Context context, String str) {
        if (context == null) {
            try {
                UMLog.aq(i.N, 0, "\\|");
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                }
            }
        } else {
            if (a == null) {
                a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(a)) {
                MLog.e("onDeepLinkReceived can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(a);
            }
            if (!TextUtils.isEmpty(str)) {
                HashMap hashMap = new HashMap();
                hashMap.put(c.aE, str);
                a(a, c.aD, (Map<String, Object>) hashMap, -1L, false);
                return;
            }
            UMLog.aq(i.O, 0, "\\|");
        }
    }

    private boolean c(String str) {
        if (this.u.enabled() && this.u.matchHit(str)) {
            return true;
        }
        if (!this.x.enabled()) {
            return false;
        }
        if (!this.x.matchHit(str)) {
            return true;
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> white list match! id = " + str);
        return false;
    }

    public void a(Context context, String str, String str2, long j, int i2) {
        if (context != null) {
            try {
                if (a == null) {
                    a = context.getApplicationContext();
                }
                if (!this.j || !this.n) {
                    a(a);
                }
                if (c(str)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> filter ekv [" + str + "].");
                    return;
                }
                String str3 = "";
                if (this.k == null) {
                    this.k = new JSONObject();
                } else {
                    str3 = this.k.toString();
                }
                r.a(a).a(str, str2, j, i2, str3);
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, String str, Map<String, Object> map, long j) {
        try {
            if (TextUtils.isEmpty(str)) {
                UMLog.aq(i.c, 0, "\\|");
            } else if (Arrays.asList(c.aF).contains(str)) {
                UMLog.aq(i.b, 0, "\\|");
            } else if (map.isEmpty()) {
                UMLog.aq(i.d, 0, "\\|");
            } else {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    if (Arrays.asList(c.aF).contains(entry.getKey())) {
                        UMLog.aq(i.e, 0, "\\|");
                        return;
                    }
                }
                a(context, str, map, j, false);
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    public void a(Context context, String str, Map<String, Object> map) {
        a(context, str, map, -1L, true);
    }

    private void a(Context context, String str, Map<String, Object> map, long j, boolean z2) {
        if (context == null) {
            try {
                MLog.e("context is null in onEventNoCheck, please check!");
            } catch (Throwable th) {
                if (MLog.DEBUG) {
                    MLog.e(th);
                }
            }
        } else {
            if (a == null) {
                a = context.getApplicationContext();
            }
            if (!this.j || !this.n) {
                a(a);
            }
            if (c(str)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> filter ekv [" + str + "].");
                return;
            }
            String str2 = "";
            if (this.k == null) {
                this.k = new JSONObject();
            } else {
                str2 = this.k.toString();
            }
            r.a(a).a(str, map, j, str2, z2);
        }
    }

    /* access modifiers changed from: package-private */
    public void d(Context context) {
        if (context != null) {
            try {
                if (a == null) {
                    a = context.getApplicationContext();
                }
                if (!UMUtils.isMainProgress(a)) {
                    MLog.e("onKillProcess can not be called in child process");
                    return;
                }
                if (this.g != null) {
                    this.g.c();
                }
                k.a(context, "onKillProcess");
                if (this.e != null) {
                    this.e.b();
                }
                if (this.d != null) {
                    this.d.b();
                }
                if (a != null) {
                    if (this.f != null) {
                        this.f.c(a, Long.valueOf(System.currentTimeMillis()));
                    }
                    n.a(a).d();
                    u.a(a);
                    if (k.b == MobclickAgent.PageMode.AUTO) {
                        k.c(a);
                    }
                    PreferenceWrapper.getDefault(a).edit().commit();
                }
            } catch (Throwable unused) {
            }
        }
    }

    @Override // com.umeng.analytics.pro.s
    public void a(Throwable th) {
        try {
            if (a != null) {
                if (!UMUtils.isMainProgress(a)) {
                    MLog.e("onAppCrash can not be called in child process");
                } else if (AnalyticsConfig.enable) {
                    if (this.d != null) {
                        this.d.b();
                    }
                    k.a(a, "onAppCrash");
                    if (this.e != null) {
                        this.e.b();
                    }
                    if (this.g != null) {
                        this.g.c();
                    }
                    if (this.f != null) {
                        this.f.c(a, Long.valueOf(System.currentTimeMillis()));
                    }
                    if (th != null) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("ts", System.currentTimeMillis());
                        jSONObject.put(c.Q, 1);
                        jSONObject.put(c.R, DataHelper.convertExceptionToString(th));
                        h.a(a).a(this.f.c(), jSONObject.toString(), 1);
                    }
                    n.a(a).d();
                    u.a(a);
                    if (k.b == MobclickAgent.PageMode.AUTO) {
                        k.c(a);
                    }
                    PreferenceWrapper.getDefault(a).edit().commit();
                }
            }
        } catch (Exception e) {
            if (MLog.DEBUG) {
                MLog.e("Exception in onAppCrash", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2) {
        try {
            if (a != null) {
                if (!UMUtils.isMainProgress(a)) {
                    MLog.e("onProfileSignIn can not be called in child process");
                    return;
                }
                long currentTimeMillis = System.currentTimeMillis();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(c.M, str);
                jSONObject.put("uid", str2);
                jSONObject.put("ts", currentTimeMillis);
                UMWorkDispatch.sendEvent(a, 4101, CoreProtocol.getInstance(a), jSONObject);
                UMWorkDispatch.sendEvent(a, 4356, CoreProtocol.getInstance(a), jSONObject);
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(" Excepthon  in  onProfileSignIn", th);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void j() {
        try {
            if (a != null) {
                if (!UMUtils.isMainProgress(a)) {
                    MLog.e("onProfileSignOff can not be called in child process");
                    return;
                }
                long currentTimeMillis = System.currentTimeMillis();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ts", currentTimeMillis);
                UMWorkDispatch.sendEvent(a, 4102, CoreProtocol.getInstance(a), jSONObject);
                UMWorkDispatch.sendEvent(a, 4356, CoreProtocol.getInstance(a), jSONObject);
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(" Excepthon  in  onProfileSignOff", th);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z2) {
        Context context = a;
        if (context != null) {
            if (!UMUtils.isMainProgress(context)) {
                MLog.e("setCatchUncaughtExceptions can not be called in child process");
            } else if (!AnalyticsConfig.CHANGE_CATCH_EXCEPTION_NOTALLOW) {
                AnalyticsConfig.CATCH_EXCEPTION = z2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(GL10 gl10) {
        String[] gpu = UMUtils.getGPU(gl10);
        if (gpu.length == 2) {
            AnalyticsConfig.GPU_VENDER = gpu[0];
            AnalyticsConfig.GPU_RENDERER = gpu[1];
        }
    }

    /* access modifiers changed from: package-private */
    public void a(MobclickAgent.PageMode pageMode) {
        Context context = a;
        if (context != null) {
            if (!UMUtils.isMainProgress(context)) {
                MLog.e("setPageCollectionMode can not be called in child process");
            } else {
                k.b = pageMode;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(double d, double d2) {
        Context context = a;
        if (context != null) {
            if (!UMUtils.isMainProgress(context)) {
                MLog.e("setLocation can not be called in child process");
                return;
            }
            if (AnalyticsConfig.a == null) {
                AnalyticsConfig.a = new double[2];
            }
            AnalyticsConfig.a[0] = d;
            AnalyticsConfig.a[1] = d2;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, MobclickAgent.EScenarioType eScenarioType) {
        if (context == null) {
            MLog.e("unexpected null context in setScenarioType");
            return;
        }
        if (a == null) {
            a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(a)) {
            MLog.e("setScenarioType can not be called in child process");
            return;
        }
        if (eScenarioType != null) {
            a(a, eScenarioType.toValue());
        }
        if (!this.j || !this.n) {
            a(a);
        }
    }

    /* access modifiers changed from: package-private */
    public void c(Context context, String str) {
        if (context == null) {
            UMLog.aq(i.z, 0, "\\|");
            return;
        }
        if (a == null) {
            a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(a)) {
            MLog.e("setSecret can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(a);
        }
        AnalyticsConfig.a(a, str);
    }

    /* access modifiers changed from: package-private */
    public void a(long j) {
        Context context = a;
        if (context != null) {
            if (!UMUtils.isMainProgress(context)) {
                MLog.e("setSessionContinueMillis can not be called in child process");
                return;
            }
            AnalyticsConfig.kContinueSessionMillis = j;
            x.a().a(AnalyticsConfig.kContinueSessionMillis);
        }
    }

    public synchronized void a(Context context, String str, Object obj) {
        int i2 = 0;
        if (context == null) {
            try {
                UMLog.aq(i.af, 0, "\\|");
            } catch (Throwable unused) {
            }
        } else {
            if (a == null) {
                a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(a)) {
                MLog.e("registerSuperProperty can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(a);
            }
            if (TextUtils.isEmpty(str) || obj == null) {
                UMLog.aq(i.ag, 0, "\\|");
            } else if (!str.equals(z) && !str.equals(A) && !str.equals(B) && !str.equals(C) && !str.equals(D)) {
                MLog.e("property name is " + str + ", please check key, must be correct!");
            } else if (!(obj instanceof String) || HelperUtils.checkStrLen(obj.toString(), 256)) {
                try {
                    if (this.k == null) {
                        this.k = new JSONObject();
                    }
                    if (obj.getClass().isArray()) {
                        if (obj instanceof String[]) {
                            String[] strArr = (String[]) obj;
                            if (strArr.length > 10) {
                                MLog.e("please check value, size is " + strArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray = new JSONArray();
                            while (i2 < strArr.length) {
                                if (strArr[i2] == null || !HelperUtils.checkStrLen(strArr[i2], 256)) {
                                    MLog.e("please check value, length is " + strArr[i2].length() + ", overlength 256!");
                                    return;
                                }
                                jSONArray.put(strArr[i2]);
                                i2++;
                            }
                            this.k.put(str, jSONArray);
                        } else if (obj instanceof long[]) {
                            long[] jArr = (long[]) obj;
                            if (jArr.length > 10) {
                                MLog.e("please check value, size is " + jArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray2 = new JSONArray();
                            while (i2 < jArr.length) {
                                jSONArray2.put(jArr[i2]);
                                i2++;
                            }
                            this.k.put(str, jSONArray2);
                        } else if (obj instanceof int[]) {
                            int[] iArr = (int[]) obj;
                            if (iArr.length > 10) {
                                MLog.e("please check value, size is " + iArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray3 = new JSONArray();
                            while (i2 < iArr.length) {
                                jSONArray3.put(iArr[i2]);
                                i2++;
                            }
                            this.k.put(str, jSONArray3);
                        } else if (obj instanceof float[]) {
                            float[] fArr = (float[]) obj;
                            if (fArr.length > 10) {
                                MLog.e("please check value, size is " + fArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray4 = new JSONArray();
                            while (i2 < fArr.length) {
                                jSONArray4.put((double) fArr[i2]);
                                i2++;
                            }
                            this.k.put(str, jSONArray4);
                        } else if (obj instanceof double[]) {
                            double[] dArr = (double[]) obj;
                            if (dArr.length > 10) {
                                MLog.e("please check value, size is " + dArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray5 = new JSONArray();
                            while (i2 < dArr.length) {
                                jSONArray5.put(dArr[i2]);
                                i2++;
                            }
                            this.k.put(str, jSONArray5);
                        } else if (obj instanceof short[]) {
                            short[] sArr = (short[]) obj;
                            if (sArr.length > 10) {
                                MLog.e("please check value, size is " + sArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray6 = new JSONArray();
                            while (i2 < sArr.length) {
                                jSONArray6.put((int) sArr[i2]);
                                i2++;
                            }
                            this.k.put(str, jSONArray6);
                        } else {
                            MLog.e("please check value, illegal type!");
                            return;
                        }
                    } else if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                        this.k.put(str, obj);
                    } else {
                        MLog.e("please check value, illegal type!");
                        return;
                    }
                } catch (Throwable unused2) {
                }
                UMWorkDispatch.sendEvent(a, 8195, CoreProtocol.getInstance(a), this.k.toString());
            } else {
                MLog.e("property value is " + obj + ", please check value, lawless!");
            }
        }
    }

    private void a(String str, Object obj) {
        try {
            if (this.k == null) {
                this.k = new JSONObject();
            }
            int i2 = 0;
            if (obj.getClass().isArray()) {
                if (obj instanceof String[]) {
                    String[] strArr = (String[]) obj;
                    if (strArr.length <= 10) {
                        JSONArray jSONArray = new JSONArray();
                        while (i2 < strArr.length) {
                            if (strArr[i2] != null && !HelperUtils.checkStrLen(strArr[i2], 256)) {
                                jSONArray.put(strArr[i2]);
                            }
                            i2++;
                        }
                        this.k.put(str, jSONArray);
                    }
                } else if (obj instanceof long[]) {
                    long[] jArr = (long[]) obj;
                    JSONArray jSONArray2 = new JSONArray();
                    while (i2 < jArr.length) {
                        jSONArray2.put(jArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray2);
                } else if (obj instanceof int[]) {
                    int[] iArr = (int[]) obj;
                    JSONArray jSONArray3 = new JSONArray();
                    while (i2 < iArr.length) {
                        jSONArray3.put(iArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray3);
                } else if (obj instanceof float[]) {
                    float[] fArr = (float[]) obj;
                    JSONArray jSONArray4 = new JSONArray();
                    while (i2 < fArr.length) {
                        jSONArray4.put((double) fArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray4);
                } else if (obj instanceof double[]) {
                    double[] dArr = (double[]) obj;
                    JSONArray jSONArray5 = new JSONArray();
                    while (i2 < dArr.length) {
                        jSONArray5.put(dArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray5);
                } else if (obj instanceof short[]) {
                    short[] sArr = (short[]) obj;
                    JSONArray jSONArray6 = new JSONArray();
                    while (i2 < sArr.length) {
                        jSONArray6.put((int) sArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray6);
                }
            } else if (obj instanceof List) {
                List list = (List) obj;
                JSONArray jSONArray7 = new JSONArray();
                while (i2 < list.size()) {
                    Object obj2 = list.get(i2);
                    if ((obj2 instanceof String) || (obj2 instanceof Long) || (obj2 instanceof Integer) || (obj2 instanceof Float) || (obj2 instanceof Double) || (obj2 instanceof Short)) {
                        jSONArray7.put(list.get(i2));
                    }
                    i2++;
                }
                this.k.put(str, jSONArray7);
            } else if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                this.k.put(str, obj);
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized void a(Object obj) {
        try {
            if (a != null) {
                if (!UMUtils.isMainProgress(a)) {
                    MLog.e("registerSuperPropertyByCoreProtocol can not be called in child process");
                    return;
                }
                if (obj != null) {
                    String str = (String) obj;
                    SharedPreferences.Editor edit = PreferenceWrapper.getDefault(a).edit();
                    if (edit != null && !TextUtils.isEmpty(str)) {
                        edit.putString(h, this.k.toString()).commit();
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized void d(Context context, String str) {
        if (context == null) {
            try {
                UMLog.aq(i.ah, 0, "\\|");
                return;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } else {
            if (a == null) {
                a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(a)) {
                MLog.e("unregisterSuperProperty can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(a);
            }
            if (TextUtils.isEmpty(str)) {
                UMLog.aq(i.ag, 0, "\\|");
                return;
            } else if (str.equals(z) || str.equals(A) || str.equals(B) || str.equals(C) || str.equals(D)) {
                if (this.k == null) {
                    this.k = new JSONObject();
                }
                if (this.k.has(str)) {
                    this.k.remove(str);
                    UMWorkDispatch.sendEvent(a, 8197, CoreProtocol.getInstance(a), str);
                }
                return;
            } else {
                MLog.e("please check key or value, must be correct!");
                return;
            }
        }
    }

    public synchronized void k() {
        try {
            if (a != null) {
                if (!UMUtils.isMainProgress(a)) {
                    MLog.e("unregisterSuperPropertyByCoreProtocol can not be called in child process");
                    return;
                }
                if (this.k != null) {
                    SharedPreferences.Editor edit = PreferenceWrapper.getDefault(a).edit();
                    edit.putString(h, this.k.toString());
                    edit.commit();
                } else {
                    this.k = new JSONObject();
                }
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized Object e(Context context, String str) {
        if (context == null) {
            try {
                UMLog.aq(i.ai, 0, "\\|");
                return null;
            } catch (Throwable unused) {
            }
        } else {
            if (a == null) {
                a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(a)) {
                MLog.e("getSuperProperty can not be called in child process");
                return null;
            } else if (TextUtils.isEmpty(str)) {
                UMLog.aq(i.ag, 0, "\\|");
                return null;
            } else if (str.equals(z) || str.equals(A) || str.equals(B) || str.equals(C) || str.equals(D)) {
                if (this.k == null) {
                    this.k = new JSONObject();
                } else if (this.k.has(str)) {
                    return this.k.opt(str);
                }
                return null;
            } else {
                MLog.e("please check key or value, must be correct!");
                return null;
            }
        }
    }

    public synchronized String e(Context context) {
        if (context == null) {
            try {
                UMLog.aq(i.ai, 0, "\\|");
                return null;
            } catch (Throwable unused) {
            }
        } else {
            if (a == null) {
                a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(a)) {
                MLog.e("getSuperProperties can not be called in child process");
                return null;
            } else if (this.k != null) {
                return this.k.toString();
            } else {
                this.k = new JSONObject();
                return null;
            }
        }
    }

    public synchronized JSONObject l() {
        try {
            if (a == null) {
                return null;
            }
            if (!UMUtils.isMainProgress(a)) {
                MLog.e("getSuperPropertiesJSONObject can not be called in child process");
                return null;
            }
            if (this.k == null) {
                this.k = new JSONObject();
            }
            return this.k;
        } catch (Throwable unused) {
        }
    }

    public synchronized void f(Context context) {
        if (context == null) {
            UMLog.aq(i.ah, 0, "\\|");
            return;
        }
        if (a == null) {
            a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(a)) {
            MLog.e("clearSuperProperties can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(a);
        }
        this.k = new JSONObject();
        UMWorkDispatch.sendEvent(a, 8196, CoreProtocol.getInstance(a), null);
    }

    public synchronized void m() {
        try {
            if (a != null) {
                if (!UMUtils.isMainProgress(a)) {
                    MLog.e("clearSuperPropertiesByCoreProtocol can not be called in child process");
                    return;
                }
                SharedPreferences.Editor edit = PreferenceWrapper.getDefault(a).edit();
                edit.remove(h);
                edit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized void a(Context context, List<String> list) {
        if (context == null) {
            try {
                UMLog.aq(i.aj, 0, "\\|");
                return;
            } catch (Throwable th) {
                MLog.e(th);
            }
        } else {
            if (a == null) {
                a = context.getApplicationContext();
            }
            if (!UMUtils.isMainProgress(a)) {
                MLog.e("setFirstLaunchEvent can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(a);
            }
            r.a(a).a(list);
            return;
        }
    }

    public synchronized void a(Context context, JSONObject jSONObject) {
        if (context == null) {
            UMLog.aq(i.al, 0, "\\|");
            return;
        }
        if (a == null) {
            a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(a)) {
            MLog.e("registerPreProperties can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(a);
        }
        if (this.m == null) {
            this.m = new JSONObject();
        }
        if (jSONObject == null || jSONObject.length() <= 0) {
            UMLog.aq(i.am, 0, "\\|");
            return;
        }
        JSONObject jSONObject2 = null;
        try {
            jSONObject2 = new JSONObject(this.m.toString());
        } catch (Exception unused) {
        }
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        Iterator<String> keys = jSONObject.keys();
        if (keys != null) {
            while (keys.hasNext()) {
                try {
                    String obj = keys.next().toString();
                    Object obj2 = jSONObject.get(obj);
                    if (b(obj, obj2)) {
                        jSONObject2.put(obj, obj2);
                        if (jSONObject2.length() > 10) {
                            MLog.e("please check propertics, size overlength!");
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Exception unused2) {
                }
            }
        }
        this.m = jSONObject2;
        if (this.m.length() > 0) {
            UMWorkDispatch.sendEvent(a, 8199, CoreProtocol.getInstance(a), this.m.toString());
        }
    }

    public synchronized void f(Context context, String str) {
        if (context == null) {
            UMLog.aq(i.an, 0, "\\|");
            return;
        }
        if (a == null) {
            a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(a)) {
            MLog.e("unregisterPreProperty can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(a);
        }
        if (this.m == null) {
            this.m = new JSONObject();
        }
        if (str == null || str.length() <= 0) {
            MLog.e("please check propertics, property is null!");
            return;
        }
        if (this.m.has(str)) {
            this.m.remove(str);
            UMWorkDispatch.sendEvent(a, 8200, CoreProtocol.getInstance(a), this.m.toString());
        } else if (UMConfigure.isDebugLog()) {
            UMLog.aq(i.ao, 0, "\\|");
        }
    }

    public synchronized void g(Context context) {
        if (context == null) {
            UMLog.aq(i.ap, 0, "\\|");
            return;
        }
        if (a == null) {
            a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(a)) {
            MLog.e("clearPreProperties can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(a);
        }
        if (this.m.length() > 0) {
            UMWorkDispatch.sendEvent(a, 8201, CoreProtocol.getInstance(a), null);
        }
        this.m = new JSONObject();
    }

    public synchronized JSONObject h(Context context) {
        if (context == null) {
            UMLog.aq(i.aq, 0, "\\|");
            return null;
        }
        if (a == null) {
            a = context.getApplicationContext();
        }
        if (!UMUtils.isMainProgress(a)) {
            MLog.e("getPreProperties can not be called in child process");
            return null;
        }
        if (!this.j || !this.n) {
            a(a);
        }
        if (this.m == null) {
            this.m = new JSONObject();
        }
        JSONObject jSONObject = new JSONObject();
        if (this.m.length() > 0) {
            try {
                jSONObject = new JSONObject(this.m.toString());
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    public synchronized void b(Object obj) {
        try {
            if (a != null) {
                if (!UMUtils.isMainProgress(a)) {
                    MLog.e("updateNativePrePropertiesByCoreProtocol can not be called in child process");
                    return;
                }
                SharedPreferences.Editor edit = PreferenceWrapper.getDefault(a).edit();
                if (obj != null) {
                    String str = (String) obj;
                    if (edit != null && !TextUtils.isEmpty(str)) {
                        edit.putString(i, str).commit();
                    }
                } else if (edit != null) {
                    edit.remove(i).commit();
                }
            }
        } catch (Throwable unused) {
        }
    }

    private boolean b(String str, Object obj) {
        int i2;
        try {
            if (TextUtils.isEmpty(str)) {
                MLog.e("key is " + str + ", please check key, illegal");
                return false;
            }
            try {
                i2 = str.getBytes("UTF-8").length;
            } catch (UnsupportedEncodingException unused) {
                i2 = 0;
            }
            if (i2 > 128) {
                MLog.e("key length is " + i2 + ", please check key, illegal");
                return false;
            } else if (obj instanceof String) {
                if (((String) obj).getBytes("UTF-8").length <= 256) {
                    return true;
                }
                MLog.e("value length is " + ((String) obj).getBytes("UTF-8").length + ", please check value, illegal");
                return false;
            } else if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof Float)) {
                return true;
            } else {
                MLog.e("value is " + obj + ", please check value, type illegal");
                return false;
            }
        } catch (Throwable unused2) {
        }
    }

    @Override // com.umeng.analytics.pro.m
    public void n() {
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onIntoBackground triggered.");
        if (AnalyticsConfig.enable && FieldManager.b()) {
            if (!FieldManager.allow(com.umeng.commonsdk.utils.b.D)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> \u9000\u51fa\u53d1\u9001\u7b56\u7565: \u4e91\u63a7\u63a7\u5236\u5b57\u5173\u95ed\u3002\u529f\u80fd\u4e0d\u751f\u6548");
            } else if (!UMWorkDispatch.eventHasExist(8210)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> \u9000\u51fa\u65f6\u53d1\u9001\u7b56\u7565 \u88ab\u89e6\u53d1\uff01");
                Context context = a;
                UMWorkDispatch.sendEvent(context, 8210, CoreProtocol.getInstance(context), null);
            }
        }
    }
}
