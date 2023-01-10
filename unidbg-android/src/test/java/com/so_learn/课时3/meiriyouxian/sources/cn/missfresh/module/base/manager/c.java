package cn.missfresh.module.base.manager;

import cn.missfresh.module.base.base.b;
import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;

/* compiled from: AppAddressManager */
public class c extends b {
    public static boolean a;
    private static volatile c b;
    private UserAddress c;
    private String d;
    private String e;

    private c() {
    }

    public static String a() {
        AppMethodBeat.i(14030, false);
        String g = b.g();
        AppMethodBeat.o(14030);
        return g;
    }

    public static void b() {
        AppMethodBeat.i(14031, false);
        a = true;
        b.b("110114", "\u5317\u4eac\u5e02", "\u5317\u4eac\u5e02", "40.249725", "116.2697");
        b("\u5341\u4e09\u9675\u6c34\u5e93\u7ba1\u7406\u5904\u7eff\u5316\u7ba1\u7406\u6240");
        n();
        AppMethodBeat.o(14031);
    }

    public static void a(String str, String str2, String str3, String str4, String str5) {
        AppMethodBeat.i(14034, false);
        a = false;
        b.b(str, str2, str3, str4, str5);
        AppMethodBeat.o(14034);
    }

    public static void b(String str, String str2, String str3, String str4, String str5) {
        AppMethodBeat.i(14037, false);
        a = false;
        b.a(str, str2, str3, str4, str5);
        AppMethodBeat.o(14037);
    }

    public static void a(String str) {
        AppMethodBeat.i(14039, false);
        b.j(str);
        AppMethodBeat.o(14039);
    }

    public static boolean c() {
        AppMethodBeat.i(14041, false);
        boolean h = b.h();
        AppMethodBeat.o(14041);
        return h;
    }

    public static String f() {
        AppMethodBeat.i(14043, false);
        String b2 = b.b();
        AppMethodBeat.o(14043);
        return b2;
    }

    public static String g() {
        AppMethodBeat.i(14044, false);
        String c = b.c();
        AppMethodBeat.o(14044);
        return c;
    }

    public static String h() {
        AppMethodBeat.i(14046, false);
        String d = b.d();
        AppMethodBeat.o(14046);
        return d;
    }

    public static String i() {
        AppMethodBeat.i(14048, false);
        String a2 = b.a();
        AppMethodBeat.o(14048);
        return a2;
    }

    public static void b(String str) {
        AppMethodBeat.i(14050, false);
        b.a(str);
        AppMethodBeat.o(14050);
    }

    public static UserAddress j() {
        AppMethodBeat.i(14052, false);
        UserAddress m = b.m();
        AppMethodBeat.o(14052);
        return m;
    }

    public static void a(UserAddress userAddress) {
        AppMethodBeat.i(14054, false);
        a = false;
        b.a(userAddress);
        AppMethodBeat.o(14054);
    }

    public UserAddress k() {
        return this.c;
    }

    public void b(UserAddress userAddress) {
        this.c = userAddress;
    }

    public static int l() {
        AppMethodBeat.i(14057, false);
        int k = b.k();
        AppMethodBeat.o(14057);
        return k;
    }

    public static String m() {
        String str;
        AppMethodBeat.i(14058, false);
        c q = q();
        if (q == null || q.c == null) {
            str = "-1";
        } else {
            str = q.c.id + "";
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("lat", f());
        jSONObject.put("lng", g());
        jSONObject.put("address_code", h());
        jSONObject.put("address_name", i());
        jSONObject.put("station_code", o());
        jSONObject.put("city_name", a());
        jSONObject.put("delivery_type", Integer.valueOf(l()));
        jSONObject.put("type", Integer.valueOf(b.l()));
        jSONObject.put("addressId", str);
        String jSONString = jSONObject.toJSONString();
        AppMethodBeat.o(14058);
        return jSONString;
    }

    public static void n() {
        AppMethodBeat.i(14059, false);
        b.n();
        AppMethodBeat.o(14059);
    }

    public static String o() {
        AppMethodBeat.i(14060, false);
        String e = b.e();
        AppMethodBeat.o(14060);
        return e;
    }

    public static String p() {
        AppMethodBeat.i(14061, false);
        String f = b.f();
        AppMethodBeat.o(14061);
        return f;
    }

    public static c q() {
        AppMethodBeat.i(14062, false);
        if (b == null) {
            synchronized (c.class) {
                try {
                    if (b == null) {
                        b = new c();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(14062);
                    throw th;
                }
            }
        }
        c cVar = b;
        AppMethodBeat.o(14062);
        return cVar;
    }

    public static String r() {
        AppMethodBeat.i(14063, false);
        String i = b.i();
        AppMethodBeat.o(14063);
        return i;
    }

    public static String s() {
        AppMethodBeat.i(14064, false);
        String j = b.j();
        AppMethodBeat.o(14064);
        return j;
    }

    public void t() {
        b = null;
    }

    public void c(String str) {
        this.d = str;
    }

    public void d(String str) {
        this.e = str;
    }

    public String u() {
        return this.d;
    }

    public String v() {
        return this.e;
    }
}
