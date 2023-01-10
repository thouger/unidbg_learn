package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.pro.d;
import com.umeng.analytics.pro.h;
import com.umeng.analytics.pro.x;
import com.umeng.analytics.process.UMProcessDBDatasSender;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.commonsdk.utils.b;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* compiled from: SessionTracker */
public class t implements x.a {
    public static final String a = "session_start_time";
    public static final String b = "session_end_time";
    public static final String c = "session_id";
    public static final String d = "pre_session_id";
    public static final String e = "a_start_time";
    public static final String f = "a_end_time";
    public static final String g = "fg_count";
    private static String h = null;
    private static Context i = null;
    private static boolean j = false;
    private static long k = 0;
    private static boolean l = true;
    private static long m;

    private t() {
        x.a().a(this);
    }

    /* compiled from: SessionTracker */
    /* access modifiers changed from: private */
    public static class a {
        private static final t a = new t();

        private a() {
        }
    }

    public static t a() {
        return a.a;
    }

    private void d(Context context) {
        try {
            SharedPreferences.Editor edit = PreferenceWrapper.getDefault(context).edit();
            edit.putLong(g, 0);
            edit.commit();
        } catch (Throwable unused) {
        }
    }

    public static long a(Context context) {
        try {
            return PreferenceWrapper.getDefault(context).getLong(g, 0);
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static void b(Context context) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(i);
        if (sharedPreferences != null) {
            long j2 = sharedPreferences.getLong(g, 0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.putLong(g, j2 + 1);
                edit.commit();
            }
        }
    }

    public void a(Context context, long j2) {
        SharedPreferences.Editor edit;
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(i);
        if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
            edit.putLong(a, j2);
            edit.commit();
        }
    }

    public void a(Context context, Object obj) {
        SharedPreferences.Editor edit;
        try {
            if (i == null && context != null) {
                i = context.getApplicationContext();
            }
            long longValue = ((Long) obj).longValue();
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(i);
            if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
                String string = sharedPreferences.getString(c.az, "");
                String appVersionName = UMUtils.getAppVersionName(i);
                if (TextUtils.isEmpty(string)) {
                    edit.putInt("versioncode", Integer.parseInt(UMUtils.getAppVersionCode(context)));
                    edit.putString(c.az, appVersionName);
                    edit.commit();
                } else if (!string.equals(appVersionName)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onStartSessionInternal: upgrade version: " + string + "-> " + appVersionName);
                    int i2 = sharedPreferences.getInt("versioncode", 0);
                    String string2 = sharedPreferences.getString("pre_date", "");
                    String string3 = sharedPreferences.getString("pre_version", "");
                    String string4 = sharedPreferences.getString(c.az, "");
                    edit.putInt("versioncode", Integer.parseInt(UMUtils.getAppVersionCode(context)));
                    edit.putString(c.az, appVersionName);
                    edit.putString("vers_date", string2);
                    edit.putString("vers_pre_version", string3);
                    edit.putString("cur_version", string4);
                    edit.putInt("vers_code", i2);
                    edit.putString("vers_name", string);
                    edit.commit();
                    if (l) {
                        l = false;
                    }
                    if (j) {
                        j = false;
                        b(i, longValue, true);
                        b(i, longValue);
                        return;
                    }
                    return;
                }
                if (j) {
                    j = false;
                    if (l) {
                        l = false;
                    }
                    h = e(context);
                    MLog.d("\u521b\u5efa\u65b0\u4f1a\u8bdd: " + h);
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "mSessionChanged flag has been set, Start new session: " + h);
                    return;
                }
                h = sharedPreferences.getString("session_id", null);
                edit.putLong(e, longValue);
                edit.putLong(f, 0);
                edit.commit();
                MLog.d("\u5ef6\u7eed\u4e0a\u4e00\u4e2a\u4f1a\u8bdd: " + h);
                UMRTLog.i(UMRTLog.RTLOG_TAG, "Extend current session: " + h);
                if (l) {
                    l = false;
                    if (FieldManager.allow(b.E)) {
                        UMWorkDispatch.sendEventEx(i, 8213, CoreProtocol.getInstance(i), null, 0);
                    }
                }
                f(context);
                n.a(i).a(false);
            }
        } catch (Throwable unused) {
        }
    }

    public void b(Context context, Object obj) {
        long j2;
        try {
            if (i == null) {
                i = UMGlobalContext.getAppContext(context);
            }
            if (obj == null) {
                j2 = System.currentTimeMillis();
            } else {
                j2 = ((Long) obj).longValue();
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(i);
            if (sharedPreferences != null) {
                k = sharedPreferences.getLong(f, 0);
                UMRTLog.i(UMRTLog.RTLOG_TAG, "------>>> lastActivityEndTime: " + k);
                String string = sharedPreferences.getString(c.az, "");
                String appVersionName = UMUtils.getAppVersionName(i);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                if (edit != null) {
                    if (!TextUtils.isEmpty(string) && !string.equals(appVersionName)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> requestNewInstantSessionIf: version upgrade");
                        edit.putLong(a, j2);
                        edit.commit();
                        n.a(i).a((Object) null, true);
                        String c2 = x.a().c(i);
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> force generate new session: session id = " + c2);
                        j = true;
                        a(i, j2, true);
                    } else if (x.a().e(i)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> More then 30 sec from last session.");
                        j = true;
                        edit.putLong(a, j2);
                        edit.commit();
                        a(i, j2, false);
                    } else {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> less then 30 sec from last session, do nothing.");
                        j = false;
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public String a(Context context, long j2, boolean z) {
        String b2 = x.a().b(context);
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onInstantSessionInternal: current session id = " + b2);
        if (TextUtils.isEmpty(b2)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("__e", j2);
            JSONObject b3 = com.umeng.analytics.b.a().b();
            if (b3 != null && b3.length() > 0) {
                jSONObject.put("__sp", b3);
            }
            JSONObject c2 = com.umeng.analytics.b.a().c();
            if (c2 != null && c2.length() > 0) {
                jSONObject.put("__pp", c2);
            }
            h.a(context).a(b2, jSONObject, h.a.INSTANTSESSIONBEGIN);
            n.a(context).a(jSONObject, z);
        } catch (Throwable unused) {
        }
        return b2;
    }

    public void c(Context context, Object obj) {
        try {
            if (i == null && context != null) {
                i = context.getApplicationContext();
            }
            long longValue = ((Long) obj).longValue();
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (sharedPreferences != null) {
                if (sharedPreferences.getLong(e, 0) == 0) {
                    MLog.e("onPause called before onResume");
                    return;
                }
                SharedPreferences.Editor edit = sharedPreferences.edit();
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onEndSessionInternal: write activity end time = " + longValue);
                edit.putLong(f, longValue);
                edit.putLong(b, longValue);
                edit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    private String e(Context context) {
        if (i == null && context != null) {
            i = context.getApplicationContext();
        }
        String d2 = x.a().d(i);
        try {
            f(context);
            n.a(i).d((Object) null);
        } catch (Throwable unused) {
        }
        return d2;
    }

    private void f(Context context) {
        n.a(context).b(context);
        n.a(context).d();
    }

    public boolean b(Context context, long j2, boolean z) {
        String a2;
        long j3;
        boolean z2 = false;
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (sharedPreferences == null || (a2 = x.a().a(i)) == null) {
                return false;
            }
            long j4 = sharedPreferences.getLong(e, 0);
            long j5 = sharedPreferences.getLong(f, 0);
            if (j4 > 0 && j5 == 0) {
                z2 = true;
                if (z) {
                    if (k == 0) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "------>>> lastActivityEndTime = 0, In-app upgrade, use currentTime: = " + j2);
                        j3 = j2;
                    } else {
                        j3 = k;
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "------>>> lastActivityEndTime != 0, app upgrade, use lastActivityEndTime: = " + k);
                    }
                    c(i, Long.valueOf(j3));
                } else {
                    c(i, Long.valueOf(j2));
                    j3 = j2;
                }
                JSONObject jSONObject = new JSONObject();
                if (z) {
                    jSONObject.put(d.C0178d.a.g, j3);
                } else {
                    jSONObject.put(d.C0178d.a.g, j2);
                }
                JSONObject b2 = com.umeng.analytics.b.a().b();
                if (b2 != null && b2.length() > 0) {
                    jSONObject.put("__sp", b2);
                }
                JSONObject c2 = com.umeng.analytics.b.a().c();
                if (c2 != null && c2.length() > 0) {
                    jSONObject.put("__pp", c2);
                }
                if (FieldManager.allow(b.E)) {
                    UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>>*** foregroundCount = " + m);
                    jSONObject.put(d.C0178d.a.h, m);
                    m = 0;
                } else {
                    jSONObject.put(d.C0178d.a.h, 0L);
                }
                h.a(context).a(a2, jSONObject, h.a.END);
                n.a(i).e();
            }
            return z2;
        } catch (Throwable unused) {
        }
    }

    public void b(Context context, long j2) {
        if (PreferenceWrapper.getDefault(context) != null) {
            try {
                n.a(i).c((Object) null);
            } catch (Throwable unused) {
            }
        }
    }

    public String b() {
        return h;
    }

    public String c(Context context) {
        try {
            if (h == null) {
                return PreferenceWrapper.getDefault(context).getString("session_id", null);
            }
        } catch (Throwable unused) {
        }
        return h;
    }

    public String c() {
        return c(i);
    }

    @Override // com.umeng.analytics.pro.x.a
    public void a(String str, String str2, long j2, long j3, long j4) {
        a(i, str2, j2, j3, j4);
        UMRTLog.i(UMRTLog.RTLOG_TAG, "saveSessionToDB: complete");
        if (AnalyticsConstants.SUB_PROCESS_EVENT) {
            Context context = i;
            UMWorkDispatch.sendEvent(context, 36945, UMProcessDBDatasSender.getInstance(context), Long.valueOf(System.currentTimeMillis()));
        }
    }

    @Override // com.umeng.analytics.pro.x.a
    public void a(String str, long j2, long j3, long j4) {
        if (!TextUtils.isEmpty(str)) {
            a(str, j2);
        }
    }

    private void a(Context context, String str, long j2, long j3, long j4) {
        if (TextUtils.isEmpty(h)) {
            h = x.a().a(i);
        }
        if (!TextUtils.isEmpty(str) && !str.equals(h)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(d.C0178d.a.g, j3);
                jSONObject.put(d.C0178d.a.h, j4);
                JSONObject b2 = com.umeng.analytics.b.a().b();
                if (b2 != null && b2.length() > 0) {
                    jSONObject.put("__sp", b2);
                }
                JSONObject c2 = com.umeng.analytics.b.a().c();
                if (c2 != null && c2.length() > 0) {
                    jSONObject.put("__pp", c2);
                }
                h.a(context).a(h, jSONObject, h.a.END);
            } catch (Exception unused) {
            }
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("__e", j2);
                h.a(context).a(str, jSONObject2, h.a.BEGIN);
                if (FieldManager.allow(b.E)) {
                    m = j4;
                    d(context);
                    UMWorkDispatch.sendEventEx(i, 8213, CoreProtocol.getInstance(i), null, 0);
                }
            } catch (Exception unused2) {
            }
            h = str;
        }
    }

    private void a(String str, long j2) {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(i);
        if (sharedPreferences != null) {
            long j3 = sharedPreferences.getLong(b, 0);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("__ii", str);
                jSONObject.put("__e", j2);
                jSONObject.put(d.C0178d.a.g, j3);
                double[] location = AnalyticsConfig.getLocation();
                if (location != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("lat", location[0]);
                    jSONObject2.put("lng", location[1]);
                    jSONObject2.put("ts", System.currentTimeMillis());
                    jSONObject.put(d.C0178d.a.e, jSONObject2);
                }
                Class<?> cls = Class.forName("android.net.TrafficStats");
                Method method = cls.getMethod("getUidRxBytes", Integer.TYPE);
                Method method2 = cls.getMethod("getUidTxBytes", Integer.TYPE);
                int i2 = i.getApplicationInfo().uid;
                if (i2 != -1) {
                    long longValue = ((Long) method.invoke(null, Integer.valueOf(i2))).longValue();
                    long longValue2 = ((Long) method2.invoke(null, Integer.valueOf(i2))).longValue();
                    if (longValue > 0) {
                        if (longValue2 > 0) {
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put(c.H, longValue);
                            jSONObject3.put(c.G, longValue2);
                            jSONObject.put(d.C0178d.a.d, jSONObject3);
                        }
                    }
                    h.a(i).a(str, jSONObject, h.a.NEWSESSION);
                    u.a(i);
                    k.c(i);
                }
            } catch (Throwable unused) {
            }
        }
    }
}
