package com.alipay.sdk.g;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.app.a.b;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.l;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.umeng.analytics.pro.ai;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public final String a;
    public final long b;
    public final int c;
    public final String d;
    public final b e;
    private String f = "";
    private String g = "";
    private Context h = null;
    private final ActivityInfo i;

    public static a a() {
        return null;
    }

    /* renamed from: com.alipay.sdk.g.a$a  reason: collision with other inner class name */
    public static final class C0066a {
        private static final HashMap<UUID, a> a = new HashMap<>();
        private static final HashMap<String, a> b = new HashMap<>();

        public static void a(a aVar, Intent intent) {
            if (aVar != null && intent != null) {
                UUID randomUUID = UUID.randomUUID();
                a.put(randomUUID, aVar);
                intent.putExtra("i_uuid_b_c", randomUUID);
            }
        }

        public static a a(Intent intent) {
            if (intent == null) {
                return null;
            }
            Serializable serializableExtra = intent.getSerializableExtra("i_uuid_b_c");
            if (serializableExtra instanceof UUID) {
                return a.remove((UUID) serializableExtra);
            }
            return null;
        }

        public static void a(a aVar, String str) {
            if (aVar != null && !TextUtils.isEmpty(str)) {
                b.put(str, aVar);
            }
        }

        public static a a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return b.remove(str);
        }
    }

    public a(Context context, String str, String str2) {
        String str3;
        boolean isEmpty = TextUtils.isEmpty(str2);
        this.e = new b(context, isEmpty);
        this.a = c(str, this.g);
        this.b = SystemClock.elapsedRealtime();
        this.c = l.d();
        this.i = l.h(context);
        this.d = str2;
        if (!isEmpty) {
            com.alipay.sdk.app.a.a.b(this, "biz", "eptyp", str2 + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + this.a);
            if (this.i != null) {
                str3 = this.i.name + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + this.i.launchMode;
            } else {
                str3 = "null";
            }
            com.alipay.sdk.app.a.a.b(this, "biz", "actInfo", str3);
            com.alipay.sdk.app.a.a.b(this, "biz", "sys", l.a(this));
        }
        try {
            this.h = context.getApplicationContext();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.f = packageInfo.versionName;
            this.g = packageInfo.packageName;
        } catch (Exception e) {
            e.a(e);
        }
        if (!isEmpty) {
            com.alipay.sdk.app.a.a.a(this, "biz", ai.aE + l.d());
            com.alipay.sdk.app.a.a.b(this, "biz", "PgApiInvoke", "" + SystemClock.elapsedRealtime());
            com.alipay.sdk.app.a.a.a(context, this, str, this.a);
        }
        if (!isEmpty && com.alipay.sdk.b.a.p().n()) {
            com.alipay.sdk.b.a.p().a(this, this.h);
        }
    }

    public String b() {
        return this.g;
    }

    public String c() {
        return this.f;
    }

    public Context d() {
        return this.h;
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("new_external_info==")) {
            return str;
        }
        if (b(str)) {
            return c(str);
        }
        return d(str);
    }

    private boolean b(String str) {
        return !str.contains("\"&");
    }

    private String c(String str) {
        try {
            String a = a(str, "&", "bizcontext=");
            if (TextUtils.isEmpty(a)) {
                return str + "&" + b("bizcontext=", "");
            }
            int indexOf = str.indexOf(a);
            String substring = str.substring(0, indexOf);
            String substring2 = str.substring(indexOf + a.length());
            return substring + a(a, "bizcontext=", "", true) + substring2;
        } catch (Throwable unused) {
            return str;
        }
    }

    private String d(String str) {
        try {
            String a = a(str, "\"&", "bizcontext=\"");
            if (TextUtils.isEmpty(a)) {
                return str + "&" + b("bizcontext=\"", "\"");
            }
            if (!a.endsWith("\"")) {
                a = a + "\"";
            }
            int indexOf = str.indexOf(a);
            return str.substring(0, indexOf) + a(a, "bizcontext=\"", "\"", false) + str.substring(indexOf + a.length());
        } catch (Throwable unused) {
            return str;
        }
    }

    private String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(str2);
        for (int i = 0; i < split.length; i++) {
            if (!TextUtils.isEmpty(split[i]) && split[i].startsWith(str3)) {
                return split[i];
            }
        }
        return null;
    }

    private String b(String str, String str2) throws JSONException, UnsupportedEncodingException {
        String a = a("", "");
        return str + a + str2;
    }

    public String a(String str, String str2) {
        String str3;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appkey", "2014052600006128");
            jSONObject.put("ty", "and_lite");
            jSONObject.put("sv", "h.a.3.7.9");
            if (!this.g.contains("setting") || !l.b(this.h)) {
                jSONObject.put("an", this.g);
            }
            jSONObject.put("av", this.f);
            jSONObject.put("sdk_start_time", System.currentTimeMillis());
            jSONObject.put("extInfo", e());
            if (this.i != null) {
                str3 = this.i.name + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + this.i.launchMode;
            } else {
                str3 = "null";
            }
            jSONObject.put("act_info", str3);
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(str, str2);
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            e.a(th);
            return "";
        }
    }

    private String a(String str, String str2, String str3, boolean z) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject;
        String substring = str.substring(str2.length());
        boolean z2 = false;
        String substring2 = substring.substring(0, substring.length() - str3.length());
        if (substring2.length() < 2 || !substring2.startsWith("\"") || !substring2.endsWith("\"")) {
            jSONObject = new JSONObject(substring2);
        } else {
            jSONObject = new JSONObject(substring2.substring(1, substring2.length() - 1));
            z2 = true;
        }
        if (!jSONObject.has("appkey")) {
            jSONObject.put("appkey", "2014052600006128");
        }
        if (!jSONObject.has("ty")) {
            jSONObject.put("ty", "and_lite");
        }
        if (!jSONObject.has("sv")) {
            jSONObject.put("sv", "h.a.3.7.9");
        }
        if (!jSONObject.has("an") && (!this.g.contains("setting") || !l.b(this.h))) {
            jSONObject.put("an", this.g);
        }
        if (!jSONObject.has("av")) {
            jSONObject.put("av", this.f);
        }
        if (!jSONObject.has("sdk_start_time")) {
            jSONObject.put("sdk_start_time", System.currentTimeMillis());
        }
        if (!jSONObject.has("extInfo")) {
            jSONObject.put("extInfo", e());
        }
        String jSONObject2 = jSONObject.toString();
        if (z2) {
            jSONObject2 = "\"" + jSONObject2 + "\"";
        }
        return str2 + jSONObject2 + str3;
    }

    private JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ap_link_token", this.a);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0011: APUT  (r2v1 java.lang.Object[]), (0 ??[int, short, byte, char]), (r7v2 java.lang.String) */
    private static String c(String str, String str2) {
        try {
            Locale locale = Locale.getDefault();
            Object[] objArr = new Object[4];
            if (str == null) {
                str = "";
            }
            objArr[0] = str;
            if (str2 == null) {
                str2 = "";
            }
            objArr[1] = str2;
            objArr[2] = Long.valueOf(System.currentTimeMillis());
            objArr[3] = UUID.randomUUID().toString();
            return String.format("EP%s%s_%s", "1", l.f(String.format(locale, "%s%s%d%s", objArr)), Long.valueOf(System.currentTimeMillis()));
        } catch (Throwable unused) {
            return "-";
        }
    }

    public static HashMap<String, String> a(a aVar) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (aVar != null) {
            hashMap.put(HiAnalyticsConstant.BI_KEY_SDK_VER, "15.7.9");
            hashMap.put("app_name", aVar.g);
            hashMap.put("token", aVar.a);
            hashMap.put("call_type", aVar.d);
            hashMap.put("ts_api_invoke", String.valueOf(aVar.b));
        }
        return hashMap;
    }
}
