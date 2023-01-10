package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.alipay.sdk.util.e;
import com.bangcle.andjni.JniLib;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public final class PayResultActivity extends Activity {
    public static final HashMap<String, Object> a = new HashMap<>();
    private com.alipay.sdk.g.a b = null;

    public static final class a {
        public static volatile String a;
        public static volatile String b;
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.cV(this, bundle, 953);
    }

    private static void a(Activity activity, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            Intent intent = new Intent();
            try {
                intent.setPackage("hk.alipay.wallet");
                intent.setData(Uri.parse("alipayhk://platformapi/startApp?appId=20000125&schemePaySession=" + URLEncoder.encode(str, "UTF-8") + "&orderSuffix=" + URLEncoder.encode(str2, "UTF-8") + "&packageName=" + URLEncoder.encode(str3, "UTF-8") + "&externalPkgName=" + URLEncoder.encode(str3, "UTF-8")));
            } catch (UnsupportedEncodingException e) {
                e.a(e);
            }
            if (activity != null) {
                try {
                    activity.startActivity(intent);
                } catch (Throwable unused) {
                    activity.finish();
                }
            }
        }
    }

    private static void a(String str) {
        a.b = b.c();
        a(a, str);
    }

    private static void a(String str, String str2) {
        a.b = str;
        a(a, str2);
    }

    private static void a(Activity activity, int i) {
        new Handler().postDelayed(new AnonymousClass1(activity), (long) i);
    }

    /* renamed from: com.alipay.sdk.app.PayResultActivity$1  reason: invalid class name */
    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Activity a;

        AnonymousClass1(Activity activity) {
            JniLib.cV(this, activity, 952);
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.finish();
        }
    }

    private static boolean a(HashMap<String, Object> hashMap, String str) {
        Object obj;
        if (hashMap == null || str == null || (obj = hashMap.get(str)) == null) {
            return false;
        }
        synchronized (obj) {
            obj.notifyAll();
        }
        return true;
    }
}
