package com.umeng.commonsdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.pro.ak;
import com.umeng.analytics.pro.al;
import com.umeng.analytics.pro.am;
import com.umeng.analytics.pro.z;
import com.umeng.commonsdk.UMConfigureImpl;
import com.umeng.commonsdk.a;
import com.umeng.commonsdk.config.FieldManager;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.listener.OnGetOaidListener;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.b;
import com.umeng.commonsdk.statistics.idtracking.i;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.commonsdk.utils.onMessageSendListener;
import java.io.File;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* compiled from: UMInternalDataProtocol */
public class c implements UMLogDataProtocol {
    private static int b = 1;
    private static final String c = "info";
    private static final String d = "stat";
    private static Class<?> e;
    private static Method f;
    private static Method g;
    private static Method h;
    private static boolean i;
    private Context a;

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void removeCacheData(Object obj) {
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public JSONObject setupReportData(long j) {
        return null;
    }

    static {
        c();
    }

    private static void c() {
        try {
            Class<?> cls = Class.forName("com.umeng.umzid.ZIDManager");
            if (cls != null) {
                e = cls;
                Method declaredMethod = e.getDeclaredMethod("getInstance", new Class[0]);
                if (declaredMethod != null) {
                    f = declaredMethod;
                }
                Method declaredMethod2 = e.getDeclaredMethod("getZID", Context.class);
                if (declaredMethod2 != null) {
                    g = declaredMethod2;
                }
                Method declaredMethod3 = e.getDeclaredMethod("getSDKVersion", new Class[0]);
                if (declaredMethod3 != null) {
                    h = declaredMethod3;
                }
            }
        } catch (Throwable unused) {
        }
    }

    public String a() {
        Method method;
        Class<?> cls = e;
        if (cls == null || (method = f) == null || g == null) {
            return "";
        }
        try {
            Object invoke = method.invoke(cls, new Object[0]);
            if (invoke != null) {
                return (String) g.invoke(invoke, this.a);
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String b() {
        Method method;
        Class<?> cls = e;
        if (cls == null || (method = f) == null || h == null) {
            return "";
        }
        try {
            Object invoke = method.invoke(cls, new Object[0]);
            if (invoke != null) {
                return (String) h.invoke(invoke, new Object[0]);
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public c(Context context) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
    }

    private void a(Context context) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", UMGlobalContext.getInstance(context).getAppkey());
            jSONObject.put("app_version", UMGlobalContext.getInstance(context).getAppVersion());
            jSONObject.put("os", "Android");
            JSONObject buildZeroEnvelopeWithExtHeader = UMEnvelopeBuild.buildZeroEnvelopeWithExtHeader(context, jSONObject, null, UMServerURL.ZCFG_PATH);
            if (buildZeroEnvelopeWithExtHeader == null || !buildZeroEnvelopeWithExtHeader.has("exception")) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> \u6784\u5efa\u96f6\u53f7\u62a5\u6587 \u6210\u529f!!!");
            } else {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> \u6784\u5efa\u96f6\u53f7\u62a5\u6587\u5931\u8d25.");
            }
        } catch (Throwable unused) {
        }
    }

    private void d() {
        ak a = ak.a(this.a);
        al a2 = a.a(am.c);
        if (a2 != null) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u4e8c\u7ea7\u7f13\u5b58\u8bb0\u5f55\u6784\u5efa\u6210\u771f\u6b63\u4fe1\u5c01\u3002");
            try {
                String str = a2.a;
                String str2 = a2.b;
                JSONObject a3 = new b().a(this.a.getApplicationContext(), new JSONObject(a2.c), new JSONObject(a2.d), a2.e, str2, a2.f);
                if (a3 == null || !a3.has("exception")) {
                    UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u4e8c\u7ea7\u7f13\u5b58\u8bb0\u5f55\u6784\u5efa\u771f\u6b63\u4fe1\u5c01 \u6210\u529f! \u5220\u9664\u4e8c\u7ea7\u7f13\u5b58\u8bb0\u5f55\u3002");
                } else {
                    UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u4e8c\u7ea7\u7f13\u5b58\u8bb0\u5f55\u6784\u5efa\u771f\u6b63\u4fe1\u5c01 \u5931\u8d25\u3002\u5220\u9664\u4e8c\u7ea7\u7f13\u5b58\u8bb0\u5f55");
                }
                a.a(am.c, str);
                a.b();
            } catch (Throwable unused) {
            }
        }
    }

    /* compiled from: UMInternalDataProtocol */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.internal.c$1  reason: invalid class name */
    public static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass1(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                SharedPreferences sharedPreferences = this.a.getSharedPreferences(i.a, 0);
                long currentTimeMillis = System.currentTimeMillis();
                String a = z.a(this.a);
                long currentTimeMillis2 = System.currentTimeMillis();
                if (!TextUtils.isEmpty(a) && sharedPreferences != null) {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(i.c, (currentTimeMillis2 - currentTimeMillis) + "");
                    edit.commit();
                }
                if (sharedPreferences != null) {
                    SharedPreferences.Editor edit2 = sharedPreferences.edit();
                    edit2.putString(i.b, a);
                    edit2.commit();
                }
                if (Build.VERSION.SDK_INT > 28) {
                    UMConfigureImpl.removeInterruptFlag();
                }
            } catch (Exception unused) {
            }
        }
    }

    private static void b(Context context) {
        new Thread(new AnonymousClass1(context)).start();
    }

    /* compiled from: UMInternalDataProtocol */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.internal.c$2  reason: invalid class name */
    public static class AnonymousClass2 implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ OnGetOaidListener b;

        AnonymousClass2(Context context, OnGetOaidListener onGetOaidListener) {
            this.a = context;
            this.b = onGetOaidListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            String a = z.a(this.a);
            OnGetOaidListener onGetOaidListener = this.b;
            if (onGetOaidListener != null) {
                onGetOaidListener.onGetOaid(a);
            }
        }
    }

    private static void a(Context context, OnGetOaidListener onGetOaidListener) {
        if (context != null) {
            new Thread(new AnonymousClass2(context.getApplicationContext(), onGetOaidListener)).start();
        }
    }

    private static void c(Context context) {
        if (FieldManager.allow(com.umeng.commonsdk.utils.b.G) && Build.VERSION.SDK_INT > 28) {
            a(context, new AnonymousClass3(context));
        }
    }

    /* compiled from: UMInternalDataProtocol */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.internal.c$3  reason: invalid class name */
    public static class AnonymousClass3 implements OnGetOaidListener {
        final /* synthetic */ Context a;

        AnonymousClass3(Context context) {
            this.a = context;
        }

        @Override // com.umeng.commonsdk.listener.OnGetOaidListener
        public void onGetOaid(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    SharedPreferences sharedPreferences = this.a.getSharedPreferences(i.a, 0);
                    if (sharedPreferences != null && !sharedPreferences.getString(i.b, "").equalsIgnoreCase(str)) {
                        UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> \u66f4\u65b0\u672c\u5730\u7f13\u5b58OAID");
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString(i.b, str);
                        edit.commit();
                    }
                } catch (Throwable unused) {
                }
            }
        }
    }

    private void e() {
        if (!i) {
            if (FieldManager.allow(com.umeng.commonsdk.utils.b.G) && Build.VERSION.SDK_INT > 28) {
                i = true;
                a(this.a, new AnonymousClass4());
            }
        } else if (!FieldManager.allow(com.umeng.commonsdk.utils.b.G)) {
            i = false;
        }
    }

    /* compiled from: UMInternalDataProtocol */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.internal.c$4  reason: invalid class name */
    public class AnonymousClass4 implements OnGetOaidListener {
        AnonymousClass4() {
        }

        @Override // com.umeng.commonsdk.listener.OnGetOaidListener
        public void onGetOaid(String str) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> OAID\u4e91\u63a7\u53c2\u6570\u66f4\u65b0(\u4e0d\u91c7\u96c6->\u91c7\u96c6)\uff1a\u91c7\u96c6\u5b8c\u6210");
            if (TextUtils.isEmpty(str)) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> oaid\u8fd4\u56denull\u6216\u8005\u7a7a\u4e32\uff0c\u4e0d\u9700\u8981 \u4f2a\u51b7\u542f\u52a8\u3002");
                return;
            }
            try {
                SharedPreferences sharedPreferences = c.this.a.getSharedPreferences(i.a, 0);
                if (sharedPreferences != null) {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(i.b, str);
                    edit.commit();
                }
            } catch (Throwable unused) {
            }
            UMWorkDispatch.sendEvent(c.this.a, 32788, b.a(c.this.a).a(), null);
        }
    }

    private void f() {
        if (FieldManager.allow(com.umeng.commonsdk.utils.b.G) && Build.VERSION.SDK_INT > 28) {
            i = true;
            UMConfigureImpl.registerInterruptFlag();
            UMConfigureImpl.init(this.a);
            b++;
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> \u8981\u8bfb\u53d6 oaid\uff0c\u9700\u7b49\u5f85\u8bfb\u53d6\u7ed3\u679c.");
            UMConfigureImpl.registerMessageSendListener(new AnonymousClass5());
            b(this.a);
        }
    }

    /* compiled from: UMInternalDataProtocol */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.internal.c$5  reason: invalid class name */
    public class AnonymousClass5 implements onMessageSendListener {
        AnonymousClass5() {
        }

        @Override // com.umeng.commonsdk.utils.onMessageSendListener
        public void onMessageSend() {
            if (c.this.a != null) {
                UMWorkDispatch.sendEvent(c.this.a, a.x, b.a(c.this.a).a(), null);
            }
            UMConfigureImpl.removeMessageSendListener(this);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00d6, code lost:
        if (r8.isDirectory() != false) goto L_0x00da;
     */
    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void workEvent(java.lang.Object r8, int r9) {
        /*
        // Method dump skipped, instructions count: 788
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.c.workEvent(java.lang.Object, int):void");
    }

    private static Class<?> a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    private void d(Context context) {
        Object invoke;
        Method declaredMethod;
        Context applicationContext = context.getApplicationContext();
        String appkey = UMUtils.getAppkey(context);
        try {
            Class<?> a = a("com.umeng.umzid.ZIDManager");
            Method declaredMethod2 = a.getDeclaredMethod("getInstance", new Class[0]);
            if (declaredMethod2 != null && (invoke = declaredMethod2.invoke(a, new Object[0])) != null && (declaredMethod = a.getDeclaredMethod("init", Context.class, String.class, a("com.umeng.umzid.IZIDCompletionCallback"))) != null) {
                declaredMethod.invoke(invoke, applicationContext, appkey, null);
            }
        } catch (Throwable unused) {
        }
    }

    private void g() {
        if (b <= 0) {
            h();
            d(this.a);
        }
    }

    private static void e(Context context) {
        File filesDir = context.getFilesDir();
        File file = new File(filesDir.getAbsolutePath() + File.separator + am.l);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Throwable unused) {
            }
        }
    }

    private void h() {
        UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> \u771f\u5b9e\u6784\u5efa\u6761\u4ef6\u6ee1\u8db3\uff0c\u5f00\u59cb\u6784\u5efa\u4e1a\u52a1\u4fe1\u5c01\u3002");
        if (UMUtils.isMainProgress(this.a)) {
            e(this.a);
            a.a(this.a);
            Context context = this.a;
            UMWorkDispatch.sendEvent(context, 8208, CoreProtocol.getInstance(context), null);
            Context context2 = this.a;
            UMWorkDispatch.sendEvent(context2, 32785, b.a(context2).a(), null);
        }
    }
}
