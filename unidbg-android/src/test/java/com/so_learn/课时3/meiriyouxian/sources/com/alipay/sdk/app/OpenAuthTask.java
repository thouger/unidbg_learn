package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.SearchIndexablesContract;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.b.a;
import com.alipay.sdk.g.a;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.l;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.pro.c;
import com.umeng.message.common.inter.ITagManager;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public final class OpenAuthTask {
    private static final Map<String, a> a = new ConcurrentHashMap();
    private static long b = -1;
    private volatile boolean c = false;
    private final Activity d;
    private a e;
    private final Handler f = new Handler(Looper.getMainLooper());

    public interface a {
        void a(int i, String str, Bundle bundle);
    }

    public enum BizType {
        Invoice("20000920"),
        AccountAuth("20000067"),
        Deduct("60000157");
        
        private String appId;

        private BizType(String str) {
            this.appId = str;
        }
    }

    public OpenAuthTask(Activity activity) {
        this.d = activity;
        com.alipay.sdk.g.b.a().a(activity);
    }

    public void a(String str, BizType bizType, Map<String, String> map, a aVar, boolean z) {
        Activity activity = this.d;
        String valueOf = String.valueOf(map);
        com.alipay.sdk.g.a aVar2 = new com.alipay.sdk.g.a(activity, valueOf, "oa-" + bizType);
        this.e = aVar;
        if (a(aVar2, str, bizType, map, z)) {
            com.alipay.sdk.app.a.a.b(this.d, aVar2, "", aVar2.a);
        }
    }

    private boolean a(com.alipay.sdk.g.a aVar, String str, BizType bizType, Map<String, String> map, boolean z) {
        if (this.c) {
            this.f.post(new b(this, 4000, "\u8be5 OpenAuthTask \u5df2\u5728\u6267\u884c", null, null));
            return true;
        }
        this.c = true;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - b <= 3000) {
            this.f.post(new b(this, 5000, "3s \u5185\u91cd\u590d\u652f\u4ed8", null, null));
            return true;
        }
        b = elapsedRealtime;
        a.a("");
        String a2 = l.a(32);
        HashMap hashMap = new HashMap(map);
        hashMap.put("mqpPkgName", this.d.getPackageName());
        hashMap.put("mqpScene", "sdk");
        List<a.C0063a> o = com.alipay.sdk.b.a.p().o();
        if (!com.alipay.sdk.b.a.p().a || o == null) {
            o = a.a;
        }
        l.a a3 = l.a(aVar, this.d, o);
        if (a3 != null && !a3.a(aVar) && !a3.a() && a3.a != null && a3.a.versionCode >= 122) {
            try {
                HashMap<String, String> a4 = com.alipay.sdk.g.a.a(aVar);
                a4.put("ts_scheme", String.valueOf(SystemClock.elapsedRealtime()));
                hashMap.put("mqpLoc", new JSONObject(a4).toString());
            } catch (Throwable unused) {
                this.f.post(new b(this, 4000, "\u4e1a\u52a1\u53c2\u6570\u9519\u8bef", null, null));
                return true;
            }
            String a5 = a(bizType, hashMap);
            a.put(a2, this.e);
            String str2 = null;
            try {
                str2 = a(elapsedRealtime, a2, bizType, a5);
            } catch (JSONException e) {
                com.alipay.sdk.app.a.a.a(aVar, "biz", "JSONEx", e);
            }
            if (TextUtils.isEmpty(str2)) {
                this.f.post(new b(this, 4000, "\u53c2\u6570\u9519\u8bef", null, null));
                return true;
            }
            Intent intent = new Intent("android.intent.action.VIEW", new Uri.Builder().scheme("alipays").authority("platformapi").path("startapp").appendQueryParameter("appId", "20001129").appendQueryParameter(SearchIndexablesContract.RawData.PAYLOAD, str2).build());
            intent.addFlags(268435456);
            intent.setPackage(a3.a.packageName);
            try {
                com.alipay.sdk.app.a.a.b(aVar, "biz", "PgOpenStarting", "" + elapsedRealtime);
                a.C0066a.a(aVar, a2);
                this.d.startActivity(intent);
            } catch (Throwable th) {
                com.alipay.sdk.app.a.a.a(aVar, "biz", "StartWalletEx", th);
            }
            return false;
        } else if (z) {
            hashMap.put("mqpScheme", String.valueOf(str));
            hashMap.put("mqpNotifyName", a2);
            hashMap.put("mqpScene", "landing");
            String a6 = a(bizType, hashMap);
            Intent intent2 = new Intent(this.d, H5OpenAuthActivity.class);
            intent2.putExtra("url", String.format("https://render.alipay.com/p/s/i?scheme=%s", Uri.encode(a6)));
            a.C0066a.a(aVar, intent2);
            this.d.startActivity(intent2);
            return false;
        } else {
            this.f.post(new b(this, 4001, "\u652f\u4ed8\u5b9d\u672a\u5b89\u88c5\u6216\u7b7e\u540d\u9519\u8bef", null, null));
            return true;
        }
    }

    private String a(BizType bizType, Map<String, String> map) {
        if (bizType != null) {
            Uri.Builder appendQueryParameter = new Uri.Builder().scheme("alipays").authority("platformapi").path("startapp").appendQueryParameter("appId", bizType.appId);
            if (AnonymousClass1.a[bizType.ordinal()] == 1) {
                appendQueryParameter.appendQueryParameter("appClearTop", ITagManager.STATUS_FALSE).appendQueryParameter("startMultApp", "YES");
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                appendQueryParameter.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            return appendQueryParameter.build().toString();
        }
        throw new RuntimeException("missing bizType");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.alipay.sdk.app.OpenAuthTask$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[BizType.values().length];

        static {
            try {
                a[BizType.Deduct.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[BizType.AccountAuth.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[BizType.Invoice.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private String a(long j, String str, BizType bizType, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("startTime", String.valueOf(j));
        jSONObject.put(c.aw, str);
        jSONObject.put("package", this.d.getPackageName());
        if (bizType != null) {
            jSONObject.put("appId", bizType.appId);
        }
        jSONObject.put(Constants.KEY_SDK_VERSION, "h.a.3.7.9");
        jSONObject.put("mqpURL", str2);
        return Base64.encodeToString(jSONObject.toString().getBytes(Charset.forName("UTF-8")), 2);
    }

    static void a(String str, int i, String str2, Bundle bundle) {
        a remove = a.remove(str);
        if (remove != null) {
            try {
                remove.a(i, str2, bundle);
            } catch (Throwable th) {
                e.a(th);
            }
        }
    }

    /* access modifiers changed from: private */
    public final class b implements Runnable {
        final int a;
        final String b;
        final Bundle c;

        /* synthetic */ b(OpenAuthTask openAuthTask, int i, String str, Bundle bundle, AnonymousClass1 r5) {
            this(i, str, bundle);
        }

        private b(int i, String str, Bundle bundle) {
            this.a = i;
            this.b = str;
            this.c = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (OpenAuthTask.this.e != null) {
                OpenAuthTask.this.e.a(this.a, this.b, this.c);
            }
        }
    }
}
