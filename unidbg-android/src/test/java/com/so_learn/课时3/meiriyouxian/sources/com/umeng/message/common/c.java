package com.umeng.message.common;

import android.content.Context;
import android.os.Build;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import com.umeng.analytics.pro.ai;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.proguard.k;
import org.json.JSONObject;

/* compiled from: Header */
public class c {
    public static final String a = "device_id";
    private static final String ak = "Android";
    public static final String b = "idmd5";
    public static final String c = "din";
    public static final String d = "android_id";
    public static final String e = "serial_number";
    public static final String f = "umid";
    public static final String g = "Android";
    private static final String h = c.class.getName();
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private int F;
    private String G;
    private String H;
    private String I;
    private String J;
    private String K;
    private String L;
    private String M;
    private final String N = "appkey";
    private final String O = "channel";
    private final String P = ai.A;
    private final String Q = "push_switch";
    private final String R = ai.W;
    private final String S = "device_model";
    private final String T = "os";
    private final String U = ai.y;
    private final String V = "resolution";
    private final String W = ai.w;
    private final String X = "gpu_vender";
    private final String Y = "gpu_renderer";
    private final String Z = "app_version";
    private final String aa = "version_code";
    private final String ab = "package_name";
    private final String ac = ai.u;
    private final String ad = "sdk_version";
    private final String ae = "timezone";
    private final String af = "country";
    private final String ag = "language";
    private final String ah = ai.Q;
    private final String ai = ai.R;
    private final String aj = "carrier";
    private final String al = "wrapper_type";
    private final String am = "wrapper_version";
    private Context an;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private long q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public c(Context context) {
        this.an = context;
    }

    public c(String str, String str2) {
        this.i = str;
        this.j = str2;
    }

    private void d(JSONObject jSONObject) throws Exception {
        this.i = jSONObject.getString("appkey");
        this.k = jSONObject.getString("device_id");
        this.l = jSONObject.getString("idmd5");
        if (jSONObject.has(ai.A)) {
            this.m = jSONObject.getString(ai.A);
        }
        if (jSONObject.has("channel")) {
            this.j = jSONObject.getString("channel");
        }
        if (jSONObject.has(ai.W)) {
            this.q = jSONObject.getLong(ai.W);
        }
    }

    private void e(JSONObject jSONObject) throws Exception {
        String str = null;
        this.t = jSONObject.has("device_model") ? jSONObject.getString("device_model") : null;
        this.u = jSONObject.has("os") ? jSONObject.getString("os") : null;
        this.v = jSONObject.has(ai.y) ? jSONObject.getString(ai.y) : null;
        this.w = jSONObject.has("resolution") ? jSONObject.getString("resolution") : null;
        this.x = jSONObject.has(ai.w) ? jSONObject.getString(ai.w) : null;
        this.y = jSONObject.has("gpu_vender") ? jSONObject.getString("gpu_vender") : null;
        this.z = jSONObject.has("gpu_renderer") ? jSONObject.getString("gpu_renderer") : null;
        this.r = jSONObject.has("android_id") ? jSONObject.getString("android_id") : null;
        if (jSONObject.has("serial_number")) {
            str = jSONObject.getString("serial_number");
        }
        this.s = str;
    }

    private void f(JSONObject jSONObject) throws Exception {
        String str = null;
        this.A = jSONObject.has("app_version") ? jSONObject.getString("app_version") : null;
        this.B = jSONObject.has("version_code") ? jSONObject.getString("version_code") : null;
        if (jSONObject.has("package_name")) {
            str = jSONObject.getString("package_name");
        }
        this.C = str;
    }

    private void g(JSONObject jSONObject) throws Exception {
        this.D = jSONObject.getString(ai.u);
        this.E = jSONObject.getString("sdk_version");
    }

    private void h(JSONObject jSONObject) throws Exception {
        this.F = jSONObject.has("timezone") ? jSONObject.getInt("timezone") : 8;
        String str = null;
        this.G = jSONObject.has("country") ? jSONObject.getString("country") : null;
        if (jSONObject.has("language")) {
            str = jSONObject.getString("language");
        }
        this.H = str;
    }

    private void i(JSONObject jSONObject) throws Exception {
        String str = null;
        this.I = jSONObject.has(ai.Q) ? jSONObject.getString(ai.Q) : null;
        this.J = jSONObject.has(ai.R) ? jSONObject.getString(ai.R) : null;
        if (jSONObject.has("carrier")) {
            str = jSONObject.getString("carrier");
        }
        this.K = str;
    }

    private void j(JSONObject jSONObject) throws Exception {
        String str = null;
        this.L = jSONObject.has("wrapper_type") ? jSONObject.getString("wrapper_type") : null;
        if (jSONObject.has("wrapper_version")) {
            str = jSONObject.getString("wrapper_version");
        }
        this.M = str;
    }

    public void a(JSONObject jSONObject) throws Exception {
        if (jSONObject != null) {
            d(jSONObject);
            e(jSONObject);
            f(jSONObject);
            g(jSONObject);
            h(jSONObject);
            i(jSONObject);
            j(jSONObject);
        }
    }

    private void k(JSONObject jSONObject) throws Exception {
        jSONObject.put("appkey", this.i);
        String str = this.i;
        if (str == null || 24 != str.length()) {
            this.k = com.umeng.message.proguard.c.a(UmengMessageDeviceConfig.getDeviceId(this.an), FileOpt.ENCODE_STR);
            this.o = com.umeng.message.proguard.c.a(UmengMessageDeviceConfig.getDIN(this.an), FileOpt.ENCODE_STR);
        } else {
            this.k = com.umeng.message.proguard.c.a(UmengMessageDeviceConfig.getDeviceId(this.an), FileOpt.ENCODE_STR, this.i.substring(0, 16));
            this.o = com.umeng.message.proguard.c.a(UmengMessageDeviceConfig.getDIN(this.an), FileOpt.ENCODE_STR, this.i.substring(0, 16));
        }
        jSONObject.put("device_id", this.k);
        jSONObject.put("idmd5", UmengMessageDeviceConfig.getDeviceIdMD5(this.an));
        String str2 = this.j;
        if (str2 != null) {
            jSONObject.put("channel", str2);
        }
        String str3 = this.m;
        if (str3 != null) {
            jSONObject.put(ai.A, str3);
        }
        long j = this.q;
        if (j > 0) {
            jSONObject.put(ai.W, j);
        }
        if (UmengMessageDeviceConfig.getAndroidId(this.an) != null) {
            jSONObject.put("android_id", UmengMessageDeviceConfig.getAndroidId(this.an));
        }
        if (UmengMessageDeviceConfig.getSerial_number() != null) {
            jSONObject.put("serial_number", UmengMessageDeviceConfig.getSerial_number());
        }
        jSONObject.put("umid", this.n);
        jSONObject.put(c, this.o);
        jSONObject.put("push_switch", this.p);
    }

    private void l(JSONObject jSONObject) throws Exception {
        jSONObject.put("appkey", this.i);
        String str = this.i;
        if (str == null || 24 != str.length()) {
            this.o = com.umeng.message.proguard.c.a(UmengMessageDeviceConfig.getDIN(this.an), FileOpt.ENCODE_STR);
        } else {
            this.o = com.umeng.message.proguard.c.a(UmengMessageDeviceConfig.getDIN(this.an), FileOpt.ENCODE_STR, this.i.substring(0, 16));
        }
        String str2 = this.j;
        if (str2 != null) {
            jSONObject.put("channel", str2);
        }
        jSONObject.put("umid", this.n);
        jSONObject.put(c, this.o);
        jSONObject.put("push_switch", this.p);
    }

    private void m(JSONObject jSONObject) throws Exception {
        String str = this.t;
        if (str != null) {
            jSONObject.put("device_model", str);
        }
        String str2 = this.u;
        if (str2 != null) {
            jSONObject.put("os", str2);
        }
        String str3 = this.v;
        if (str3 != null) {
            jSONObject.put(ai.y, str3);
        }
        String str4 = this.w;
        if (str4 != null) {
            jSONObject.put("resolution", str4);
        }
        String str5 = this.x;
        if (str5 != null) {
            jSONObject.put(ai.w, str5);
        }
        String str6 = this.y;
        if (str6 != null) {
            jSONObject.put("gpu_vender", str6);
        }
        String str7 = this.z;
        if (str7 != null) {
            jSONObject.put("gpu_vender", str7);
        }
    }

    private void n(JSONObject jSONObject) throws Exception {
        String str = this.t;
        if (str != null) {
            jSONObject.put("device_model", str);
        }
        String str2 = this.u;
        if (str2 != null) {
            jSONObject.put("os", str2);
        }
        String str3 = this.v;
        if (str3 != null) {
            jSONObject.put(ai.y, str3);
        }
    }

    private void o(JSONObject jSONObject) throws Exception {
        String str = this.A;
        if (str != null) {
            jSONObject.put("app_version", str);
        }
        String str2 = this.B;
        if (str2 != null) {
            jSONObject.put("version_code", str2);
        }
        String str3 = this.C;
        if (str3 != null) {
            jSONObject.put("package_name", str3);
        }
    }

    private void p(JSONObject jSONObject) throws Exception {
        String str = this.A;
        if (str != null) {
            jSONObject.put("app_version", str);
        }
        String str2 = this.B;
        if (str2 != null) {
            jSONObject.put("version_code", str2);
        }
    }

    private void q(JSONObject jSONObject) throws Exception {
        jSONObject.put(ai.u, this.D);
        jSONObject.put("sdk_version", this.E);
    }

    private void r(JSONObject jSONObject) throws Exception {
        jSONObject.put("timezone", this.F);
        String str = this.G;
        if (str != null) {
            jSONObject.put("country", str);
        }
        String str2 = this.H;
        if (str2 != null) {
            jSONObject.put("language", str2);
        }
    }

    private void s(JSONObject jSONObject) throws Exception {
        String str = this.I;
        if (str != null) {
            jSONObject.put(ai.Q, str);
        }
        String str2 = this.J;
        if (str2 != null) {
            jSONObject.put(ai.R, str2);
        }
        String str3 = this.K;
        if (str3 != null) {
            jSONObject.put("carrier", str3);
        }
    }

    private void t(JSONObject jSONObject) throws Exception {
        String str = this.L;
        if (str != null) {
            jSONObject.put("wrapper_type", str);
        }
        String str2 = this.M;
        if (str2 != null) {
            jSONObject.put("wrapper_version", str2);
        }
    }

    public void b(JSONObject jSONObject) throws Exception {
        k(jSONObject);
        m(jSONObject);
        o(jSONObject);
        q(jSONObject);
        r(jSONObject);
        s(jSONObject);
        t(jSONObject);
    }

    public void c(JSONObject jSONObject) throws Exception {
        l(jSONObject);
        n(jSONObject);
        p(jSONObject);
        q(jSONObject);
        s(jSONObject);
    }

    public boolean a() {
        if (this.i == null) {
            UMLog.mutlInfo(h, 0, "missing appkey");
            return false;
        } else if (this.k != null && this.l != null) {
            return true;
        } else {
            UMLog.mutlInfo(h, 0, "missing device id");
            return false;
        }
    }

    public void a(Context context, String... strArr) {
        if (strArr != null && strArr.length == 2) {
            this.i = strArr[0];
            this.j = strArr[1];
        }
        if (this.i == null) {
            this.i = PushAgent.getInstance(context).getMessageAppkey();
        }
        if (this.j == null) {
            this.j = PushAgent.getInstance(context).getMessageChannel();
        }
        this.k = UmengMessageDeviceConfig.getDeviceId(context);
        this.l = UmengMessageDeviceConfig.getDeviceIdMD5(context);
        this.m = UmengMessageDeviceConfig.getDummyId(context);
        this.o = UmengMessageDeviceConfig.getDIN(context);
        this.n = UmengMessageDeviceConfig.getUmid(context);
        this.p = UmengMessageDeviceConfig.isNotificationEnabled(context);
        if (ITagManager.STATUS_FALSE.equals(this.p)) {
            UMLog.aq(k.c, 0, "\\|");
        }
    }

    private void a(Context context) {
        this.t = Build.MODEL;
        this.u = "Android";
        this.v = Build.VERSION.RELEASE;
        this.w = UmengMessageDeviceConfig.getResolution(context);
        this.x = UmengMessageDeviceConfig.getCPU();
        this.r = UmengMessageDeviceConfig.getAndroidId(context);
        this.s = UmengMessageDeviceConfig.getSerial_number();
    }

    private void b(Context context) {
        this.A = UmengMessageDeviceConfig.getAppVersionName(context);
        this.B = UmengMessageDeviceConfig.getAppVersionCode(context);
        this.C = UmengMessageDeviceConfig.getPackageName(context);
    }

    private void c(Context context) {
        this.D = "Android";
        this.E = MsgConstant.SDK_VERSION;
    }

    private void d(Context context) {
        this.F = UmengMessageDeviceConfig.getTimeZone(context);
        String[] localeInfo = UmengMessageDeviceConfig.getLocaleInfo(context);
        this.G = localeInfo[0];
        this.H = localeInfo[1];
    }

    private void e(Context context) {
        String[] networkAccessMode = UmengMessageDeviceConfig.getNetworkAccessMode(context);
        this.I = networkAccessMode[0];
        this.J = networkAccessMode[1];
        this.K = UmengMessageDeviceConfig.getOperator(context);
    }

    public void b(Context context, String... strArr) {
        a(context, strArr);
        a(context);
        b(context);
        c(context);
        d(context);
        e(context);
    }

    public void c(Context context, String... strArr) {
        a(context, strArr);
        a(context);
        b(context);
        c(context);
        e(context);
    }

    public boolean b() {
        return (this.i == null || this.k == null) ? false : true;
    }
}
