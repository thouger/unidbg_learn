package cn.com.chinatelecom.account.api.c;

import android.media.TtmlUtils;
import android.os.Build;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.ClientUtils;
import cn.com.chinatelecom.account.api.CtAuth;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

public class e {
    private String a = "1.2";
    private String b = a(this.v);
    private String c = "";
    private String d = CtAuth.mAppId;
    private String e = "";
    private String f = "";
    private String g = Build.BRAND;
    private String h = Build.MODEL;
    private String i = "Android";
    private String j = Build.VERSION.RELEASE;
    private String k;
    private String l;
    private String m = "";
    private String n = "";
    private int o;
    private String p = "";
    private long q;
    private long r;
    private String s;
    private int t;
    private StringBuffer u = new StringBuffer();
    private long v = System.currentTimeMillis();
    private long w;

    public e(String str) {
        AppMethodBeat.i(8241, false);
        this.k = ClientUtils.getSdkType() == 1 ? "SDK-HY-v3.7.0" : "SDK-API-v3.7.0";
        this.l = str;
        this.s = "0";
        AppMethodBeat.o(8241);
    }

    public static String a(long j) {
        AppMethodBeat.i(8243, false);
        try {
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA).format(new Date(j));
            AppMethodBeat.o(8243);
            return format;
        } catch (Throwable th) {
            th.printStackTrace();
            AppMethodBeat.o(8243);
            return "";
        }
    }

    public e a(int i) {
        this.o = i;
        return this;
    }

    public e a(String str) {
        this.e = str;
        return this;
    }

    public String a() {
        return this.l;
    }

    public e b(int i) {
        this.t = i;
        return this;
    }

    public e b(long j) {
        this.q = j;
        return this;
    }

    public e b(String str) {
        this.f = str;
        return this;
    }

    public void b() {
        AppMethodBeat.i(8261, false);
        this.w = System.currentTimeMillis();
        this.r = this.w - this.v;
        AppMethodBeat.o(8261);
    }

    public e c(String str) {
        this.m = str;
        return this;
    }

    public e d(String str) {
        this.n = str;
        return this;
    }

    public e e(String str) {
        this.p = str;
        return this;
    }

    public e f(String str) {
        AppMethodBeat.i(8256, false);
        if (!TextUtils.isEmpty(str)) {
            this.s = str;
        }
        AppMethodBeat.o(8256);
        return this;
    }

    public e g(String str) {
        AppMethodBeat.i(8259, false);
        if (str != null) {
            StringBuffer stringBuffer = this.u;
            stringBuffer.append(str);
            stringBuffer.append(";");
        }
        AppMethodBeat.o(8259);
        return this;
    }

    public String toString() {
        AppMethodBeat.i(8265, false);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("v", this.a);
            jSONObject.put("t", this.b);
            jSONObject.put("tag", this.c);
            jSONObject.put("ai", this.d);
            jSONObject.put("di", this.e);
            jSONObject.put("ns", this.f);
            jSONObject.put(TtmlUtils.TAG_BR, this.g);
            jSONObject.put("ml", this.h);
            jSONObject.put("os", this.i);
            jSONObject.put("ov", this.j);
            jSONObject.put("sv", this.k);
            jSONObject.put("ri", this.l);
            jSONObject.put("api", this.m);
            jSONObject.put("p", this.n);
            jSONObject.put("rt", this.o);
            jSONObject.put("msg", this.p);
            jSONObject.put("st", this.q);
            jSONObject.put(TtmlUtils.TAG_TT, this.r);
            jSONObject.put("ot", this.s);
            jSONObject.put("rec", this.t);
            jSONObject.put("ep", this.u.toString());
            String jSONObject2 = jSONObject.toString();
            AppMethodBeat.o(8265);
            return jSONObject2;
        } catch (Throwable th) {
            th.printStackTrace();
            AppMethodBeat.o(8265);
            return "";
        }
    }
}
