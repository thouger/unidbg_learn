package cn.missfresh.module.base.manager;

import android.content.Context;
import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.bean.UserLocationBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSON;

/* compiled from: AddressPersistence */
public class b {
    private static i a = null;
    private static volatile b b = null;
    private static String c = "mf_address_name";
    private static String d = "mf_latitude";
    private static String e = "mf_longitude";
    private static String f = "mf_address_code";
    private static String g = "mf_station_code";
    private static String h = "mf_big_ware_house";
    private static boolean i;
    private static UserAddress j;

    private b(Context context) {
        AppMethodBeat.i(13958, false);
        a = new i(context, "mryx_address");
        AppMethodBeat.o(13958);
    }

    public static void a(Context context) {
        int i2 = 13960;
        AppMethodBeat.i(13960, false);
        if (b == null) {
            synchronized (b.class) {
                try {
                    if (b == null) {
                        b = new b(context);
                    }
                } finally {
                    AppMethodBeat.o(i2);
                }
            }
        }
    }

    public static String a() {
        AppMethodBeat.i(13962, false);
        String a2 = a.a(c, "");
        AppMethodBeat.o(13962);
        return a2;
    }

    public static void a(String str) {
        AppMethodBeat.i(13963, false);
        a.b(c, str);
        AppMethodBeat.o(13963);
    }

    public static String b() {
        AppMethodBeat.i(13965, false);
        String a2 = a.a(d, "");
        AppMethodBeat.o(13965);
        return a2;
    }

    public static void b(String str) {
        AppMethodBeat.i(13967, false);
        a.b(d, str);
        AppMethodBeat.o(13967);
    }

    public static String c() {
        AppMethodBeat.i(13969, false);
        String a2 = a.a(e, "");
        AppMethodBeat.o(13969);
        return a2;
    }

    public static void c(String str) {
        AppMethodBeat.i(13971, false);
        a.b(e, str);
        AppMethodBeat.o(13971);
    }

    public static String d() {
        AppMethodBeat.i(13973, false);
        String a2 = a.a(f, "");
        AppMethodBeat.o(13973);
        return a2;
    }

    public static void d(String str) {
        AppMethodBeat.i(13976, false);
        a.b(f, str);
        AppMethodBeat.o(13976);
    }

    public static String e() {
        AppMethodBeat.i(13979, false);
        String a2 = a.a(g, "");
        AppMethodBeat.o(13979);
        return a2;
    }

    public static void e(String str) {
        AppMethodBeat.i(13980, false);
        a.b(g, str);
        AppMethodBeat.o(13980);
    }

    public static void f(String str) {
        AppMethodBeat.i(13982, false);
        a.b(h, str);
        AppMethodBeat.o(13982);
    }

    public static String f() {
        AppMethodBeat.i(13985, false);
        String a2 = a.a(h, "");
        AppMethodBeat.o(13985);
        return a2;
    }

    public static String g() {
        AppMethodBeat.i(13987, false);
        String a2 = a.a("city_name", "");
        AppMethodBeat.o(13987);
        return a2;
    }

    public static void g(String str) {
        AppMethodBeat.i(13989, false);
        a.b("city_name", str);
        AppMethodBeat.o(13989);
    }

    public static boolean h() {
        return i;
    }

    public static void a(boolean z) {
        i = z;
    }

    public static void a(UserLocationBean userLocationBean) {
        AppMethodBeat.i(13994, false);
        if (userLocationBean == null) {
            AppMethodBeat.o(13994);
            return;
        }
        a(!cn.missfresh.utils.b.a(userLocationBean.stationCode));
        e(userLocationBean.stationCode);
        b(userLocationBean.deliveryType);
        c(userLocationBean.type);
        a(userLocationBean.regionId);
        h(userLocationBean.sellerId);
        if (userLocationBean.sellerInfoList != null) {
            i(JSON.toJSONString(userLocationBean.sellerInfoList));
        }
        f(userLocationBean.bigWarehouse);
        if (userLocationBean.assistLocationFlag == 1) {
            d(userLocationBean.areaCode);
            g(userLocationBean.city);
            a(userLocationBean.district);
            b(userLocationBean.lat);
            c(userLocationBean.lng);
        }
        AppMethodBeat.o(13994);
    }

    public static void a(int i2) {
        AppMethodBeat.i(13997, false);
        a.b("regionId", i2);
        AppMethodBeat.o(13997);
    }

    public static void h(String str) {
        AppMethodBeat.i(13999, false);
        a.b("sellerId", str);
        AppMethodBeat.o(13999);
    }

    public static String i() {
        AppMethodBeat.i(14001, false);
        String a2 = a.a("sellerId", "");
        AppMethodBeat.o(14001);
        return a2;
    }

    public static void i(String str) {
        AppMethodBeat.i(14003, false);
        a.b("sellerInfoList", str);
        AppMethodBeat.o(14003);
    }

    public static String j() {
        AppMethodBeat.i(14004, false);
        String a2 = a.a("sellerInfoList", "");
        AppMethodBeat.o(14004);
        return a2;
    }

    public static int k() {
        AppMethodBeat.i(14006, false);
        int a2 = a.a("delivery_type", 0);
        AppMethodBeat.o(14006);
        return a2;
    }

    public static void b(int i2) {
        AppMethodBeat.i(14008, false);
        a.b("delivery_type", i2);
        AppMethodBeat.o(14008);
    }

    public static void c(int i2) {
        AppMethodBeat.i(14010, false);
        a.b("type", i2);
        AppMethodBeat.o(14010);
    }

    public static int l() {
        AppMethodBeat.i(14012, false);
        int a2 = a.a("type", 1);
        AppMethodBeat.o(14012);
        return a2;
    }

    public static UserAddress m() {
        return j;
    }

    public static void a(UserAddress userAddress) {
        j = userAddress;
    }

    public static void n() {
        j = null;
    }

    public static void a(String str, String str2, String str3, String str4, String str5) {
        AppMethodBeat.i(14017, false);
        d(str);
        e(str2);
        g(str3);
        b(str4);
        c(str5);
        AppMethodBeat.o(14017);
    }

    public static void b(String str, String str2, String str3, String str4, String str5) {
        AppMethodBeat.i(14019, false);
        a(str3);
        a(str, "", str2, str4, str5);
        AppMethodBeat.o(14019);
    }

    public static void j(String str) {
        AppMethodBeat.i(14021, false);
        d.c("AddressPersistence", str + " Address code " + d() + " Station code " + e() + " City name " + g() + " Lat " + b() + " Lng " + c() + " Address name " + a());
        AppMethodBeat.o(14021);
    }
}
