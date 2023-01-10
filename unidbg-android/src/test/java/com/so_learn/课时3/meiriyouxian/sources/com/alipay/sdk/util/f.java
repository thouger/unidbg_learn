package com.alipay.sdk.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.AlipayResultActivity;
import com.alipay.sdk.b.a;
import com.alipay.sdk.g.a;
import com.alipay.sdk.util.l;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.json.JSONObject;

public class f {
    private Activity a;
    private volatile IAlixPay b;
    private final Object c = IAlixPay.class;
    private boolean d;
    private c e;
    private final com.alipay.sdk.g.a f;
    private String g = null;

    public interface c {
        void a();

        void b();
    }

    public f(Activity activity, com.alipay.sdk.g.a aVar, c cVar) {
        this.a = activity;
        this.f = aVar;
        this.e = cVar;
    }

    public String a(String str) {
        String str2 = "";
        PackageInfo packageInfo = null;
        try {
            List<a.C0063a> o = com.alipay.sdk.b.a.p().o();
            if (!com.alipay.sdk.b.a.p().a || o == null) {
                o = com.alipay.sdk.app.a.a;
            }
            l.a a2 = l.a(this.f, this.a, o);
            if (a2 == null || a2.a(this.f) || a2.a() || l.a(a2.a)) {
                return "failed";
            }
            if (a2.a == null || "com.eg.android.AlipayGphone".equals(a2.a.packageName)) {
                str2 = l.a();
            } else {
                str2 = a2.a.packageName;
            }
            if (a2.a != null) {
                packageInfo = a2.a;
            }
            if (!com.alipay.sdk.b.a.p().m()) {
                a(a2);
            }
            return a(str, str2, packageInfo);
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.a(this.f, "biz", "CheckClientSignEx", th);
        }
    }

    private void a(l.a aVar) throws InterruptedException {
        PackageInfo packageInfo;
        if (aVar != null && (packageInfo = aVar.a) != null) {
            String str = packageInfo.packageName;
            Intent intent = new Intent();
            intent.setClassName(str, "com.alipay.android.app.TransProcessPayActivity");
            try {
                this.a.startActivity(intent);
            } catch (Throwable th) {
                com.alipay.sdk.app.a.a.a(this.f, "biz", "StartLaunchAppTransEx", th);
            }
            Thread.sleep(200);
        }
    }

    private String a(String str, String str2, PackageInfo packageInfo) {
        String str3;
        String str4;
        com.alipay.sdk.g.a aVar;
        int i = packageInfo != null ? packageInfo.versionCode : 0;
        if (packageInfo != null) {
            str3 = packageInfo.versionName;
        } else {
            str3 = "";
        }
        e.b("mspl", "pay bind or scheme");
        com.alipay.sdk.g.a aVar2 = this.f;
        com.alipay.sdk.app.a.a.b(aVar2, "biz", "PgWltVer", str2 + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + str3);
        if (l.e()) {
            com.alipay.sdk.app.a.a.a(this.f, "biz", "BindSkipByModel");
            str4 = "failed";
        } else {
            Pair<String, Boolean> a2 = a(str, str2, this.f);
            String str5 = a2.first;
            try {
                if ("failed".equals(str5) && a2.second.booleanValue() && com.alipay.sdk.b.a.p().l()) {
                    com.alipay.sdk.app.a.a.a(this.f, "biz", "BindRetry");
                    str4 = a(str, str2, this.f).first;
                }
            } catch (Throwable th) {
                com.alipay.sdk.app.a.a.a(this.f, "biz", "BindRetryEx", th);
            }
            str4 = str5;
        }
        e.b("mspl", "pay bind result: " + str4);
        Activity activity = this.a;
        com.alipay.sdk.g.a aVar3 = this.f;
        com.alipay.sdk.app.a.a.a(activity, aVar3, str, aVar3.a);
        if ("failed".equals(str4)) {
            if (!com.alipay.sdk.b.a.p().c()) {
                com.alipay.sdk.app.a.a.b(this.f, "biz", "BSPNotStartByConfig", "");
                return str4;
            } else if (!"com.eg.android.AlipayGphone".equals(str2) || i <= 125) {
                com.alipay.sdk.g.a aVar4 = this.f;
                com.alipay.sdk.app.a.a.b(aVar4, "biz", "BSPNotStartByPkg", str2 + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + i);
            } else if (!com.alipay.sdk.b.a.p().i() || (aVar = this.f) == null || l.b(aVar.c) == 0) {
                Activity activity2 = this.a;
                if (activity2 == null || !a(str2, activity2, this.f)) {
                    return "scheme_failed";
                }
                return a(str, str2);
            } else {
                com.alipay.sdk.app.a.a.a(this.f, "biz", "BSPNotStartByUsr");
                return str4;
            }
        }
        return str4;
    }

    private String a(String str, String str2) {
        String str3;
        String str4;
        String str5;
        String str6;
        JSONObject jSONObject;
        String str7 = str;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        String a2 = l.a(32);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        com.alipay.sdk.app.a.a.b(this.f, "biz", "BSPStart", a2 + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + elapsedRealtime);
        a.C0066a.a(this.f, a2);
        AlipayResultActivity.a.put(a2, new AnonymousClass1(countDownLatch));
        try {
            String[] split = str7.split("&", -1);
            int length = split.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    str4 = "";
                    str5 = str4;
                    str6 = null;
                    jSONObject = null;
                    break;
                }
                str6 = split[i];
                if (str6.startsWith("bizcontext=")) {
                    String substring = str6.substring(str6.indexOf("{"), str6.lastIndexOf("}") + 1);
                    int indexOf = str6.indexOf(substring);
                    str5 = str6.substring(0, indexOf);
                    str4 = str6.substring(indexOf + substring.length());
                    jSONObject = new JSONObject(substring);
                    if (jSONObject.optString("sc").equals("h5tonative")) {
                        jSONObject.put("sc", "h5tonative_scheme");
                    } else {
                        jSONObject.put("sc", "h5tonative_sdkscheme");
                    }
                } else {
                    i++;
                }
            }
            if (TextUtils.isEmpty(str6)) {
                throw new RuntimeException("empty ctx_args");
            } else if (str7.indexOf(str6) == str7.lastIndexOf(str6)) {
                str7 = str7.replace(str6, str5 + jSONObject.toString() + str4);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("sourcePid", Binder.getCallingPid());
                jSONObject2.put("external_info", str7);
                jSONObject2.put("pkgName", this.a.getPackageName());
                jSONObject2.put(com.umeng.analytics.pro.c.aw, a2);
                String encodeToString = Base64.encodeToString(jSONObject2.toString().getBytes("UTF-8"), 2);
                Uri.Builder appendQueryParameter = new Uri.Builder().scheme("alipays").authority("platformapi").path("startapp").appendQueryParameter("appId", "20000125");
                appendQueryParameter.appendQueryParameter("mqpSchemePay", encodeToString);
                try {
                    HashMap<String, String> a3 = com.alipay.sdk.g.a.a(this.f);
                    a3.put("ts_scheme", String.valueOf(elapsedRealtime));
                    appendQueryParameter.appendQueryParameter("mqpLoc", new JSONObject(a3).toString());
                } catch (Throwable th) {
                    com.alipay.sdk.app.a.a.a(this.f, "biz", "BSPLocEx", th);
                }
                String uri = appendQueryParameter.build().toString();
                Intent intent = new Intent();
                intent.setPackage(str2);
                intent.addFlags(268435456);
                intent.setData(Uri.parse(uri));
                com.alipay.sdk.app.a.a.a(this.a, this.f, str7, this.f.a);
                this.a.startActivity(intent);
                com.alipay.sdk.b.a.p().a(this.f, this.a.getApplicationContext());
                e.b("mspl", "pay scheme waiting " + uri);
                countDownLatch.await();
                String str8 = this.g;
                try {
                    str3 = j.a(this.f, str8).get("resultStatus");
                    if (str3 == null) {
                        str3 = "null";
                    }
                } catch (Throwable th2) {
                    str3 = "unknown";
                    com.alipay.sdk.app.a.a.a(this.f, "biz", "BSPStatEx", th2);
                }
                com.alipay.sdk.app.a.a.a(this.f, "biz", "BSPDone-" + str3);
                if (!TextUtils.isEmpty(str8)) {
                    return str8;
                }
                com.alipay.sdk.app.a.a.a(this.f, "biz", "BSPEmpty");
                return "scheme_failed";
            } else {
                throw new RuntimeException("multi ctx_args");
            }
        } catch (Exception e) {
            try {
                com.alipay.sdk.app.a.a.a(this.f, "biz", "BSPSCReplaceEx", e, Base64.encodeToString(str.getBytes(), 2));
            } catch (InterruptedException e2) {
                com.alipay.sdk.app.a.a.a(this.f, "biz", "BSPWaiting", e2);
                return com.alipay.sdk.app.b.a(com.alipay.sdk.app.c.PAY_WAITTING.a(), com.alipay.sdk.app.c.PAY_WAITTING.b(), "");
            } catch (Throwable th3) {
                com.alipay.sdk.app.a.a.a(this.f, "biz", "BSPEx", th3);
                return "scheme_failed";
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.alipay.sdk.util.f$1  reason: invalid class name */
    public class AnonymousClass1 implements AlipayResultActivity.a {
        final /* synthetic */ CountDownLatch a;

        AnonymousClass1(CountDownLatch countDownLatch) {
            this.a = countDownLatch;
        }

        @Override // com.alipay.sdk.app.AlipayResultActivity.a
        public void a(int i, String str, String str2) {
            f.this.g = com.alipay.sdk.app.b.a(i, str, str2);
            this.a.countDown();
        }
    }

    private static boolean a(String str, Context context, com.alipay.sdk.g.a aVar) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN, (Uri) null);
            intent.setClassName(str, "com.alipay.android.msp.ui.views.MspContainerActivity");
            if (intent.resolveActivityInfo(context.getPackageManager(), 0) != null) {
                return true;
            }
            com.alipay.sdk.app.a.a.a(aVar, "biz", "BSPDetectFail");
            return false;
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.a(aVar, "biz", "BSPDetectFail", th);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:115:0x02a8 A[SYNTHETIC, Splitter:B:115:0x02a8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Pair<java.lang.String, java.lang.Boolean> a(java.lang.String r18, java.lang.String r19, com.alipay.sdk.g.a r20) {
        /*
        // Method dump skipped, instructions count: 878
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.f.a(java.lang.String, java.lang.String, com.alipay.sdk.g.a):android.util.Pair");
    }

    public void a() {
        this.a = null;
        this.e = null;
    }

    /* access modifiers changed from: private */
    public class b implements ServiceConnection {
        private b() {
        }

        /* synthetic */ b(f fVar, AnonymousClass1 r2) {
            this();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            com.alipay.sdk.app.a.a.a(f.this.f, "biz", "srvDis");
            f.this.b = null;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            com.alipay.sdk.app.a.a.a(f.this.f, "biz", "srvCon");
            synchronized (f.this.c) {
                f.this.b = IAlixPay.Stub.asInterface(iBinder);
                f.this.c.notify();
            }
        }
    }

    /* access modifiers changed from: private */
    public class a extends IRemoteServiceCallback.Stub {
        @Override // com.alipay.android.app.IRemoteServiceCallback
        public int getVersion() throws RemoteException {
            return 3;
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public boolean isHideLoadingScreen() throws RemoteException {
            return false;
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void payEnd(boolean z, String str) throws RemoteException {
        }

        private a() {
        }

        /* synthetic */ a(f fVar, AnonymousClass1 r2) {
            this();
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void r03(String str, String str2, Map map) throws RemoteException {
            com.alipay.sdk.app.a.a.b(f.this.f, "wlt", str, str2);
        }

        @Override // com.alipay.android.app.IRemoteServiceCallback
        public void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
            Intent intent = new Intent(Intent.ACTION_MAIN, (Uri) null);
            if (bundle == null) {
                bundle = new Bundle();
            }
            try {
                bundle.putInt("CallingPid", i);
                intent.putExtras(bundle);
            } catch (Exception e) {
                com.alipay.sdk.app.a.a.a(f.this.f, "biz", "ErrIntentEx", e);
            }
            intent.setClassName(str, str2);
            try {
                if (Build.VERSION.SDK_INT >= 16) {
                    ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                    ActivityManager.getMyMemoryState(runningAppProcessInfo);
                    com.alipay.sdk.g.a aVar = f.this.f;
                    com.alipay.sdk.app.a.a.b(aVar, "biz", "isFg", runningAppProcessInfo.processName + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + runningAppProcessInfo.importance + HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
                }
            } catch (Throwable unused) {
            }
            try {
                if (f.this.a != null) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    f.this.a.startActivity(intent);
                    com.alipay.sdk.g.a aVar2 = f.this.f;
                    com.alipay.sdk.app.a.a.b(aVar2, "biz", "stAct2", "" + (SystemClock.elapsedRealtime() - elapsedRealtime));
                } else {
                    com.alipay.sdk.app.a.a.a(f.this.f, "biz", "ErrActNull", "");
                    Context d = f.this.f.d();
                    if (d != null) {
                        d.startActivity(intent);
                    }
                }
                f.this.e.b();
            } catch (Throwable th) {
                com.alipay.sdk.app.a.a.a(f.this.f, "biz", "ErrActNull", th);
                throw th;
            }
        }
    }
}
