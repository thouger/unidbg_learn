package com.hmt.analytics.util;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.hmt.analytics.android.a;
import com.umeng.message.common.inter.ITagManager;
import java.util.Locale;

/* compiled from: ParamTemp */
public class h {
    private static h J = new h();
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private boolean I = false;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public String a(Context context) {
        if (TextUtils.isEmpty(this.g)) {
            this.g = a.o(context);
        }
        return this.g;
    }

    public String b(Context context) {
        if (TextUtils.isEmpty(this.d)) {
            this.d = a.w(context);
        }
        return this.d;
    }

    public String c(Context context) {
        if (TextUtils.isEmpty(this.e)) {
            this.e = a.p(context);
        }
        return this.e;
    }

    public String d(Context context) {
        if (TextUtils.isEmpty(this.b)) {
            this.b = a.d(context);
        }
        return this.b;
    }

    public String e(Context context) {
        if (TextUtils.isEmpty(this.m)) {
            this.m = a.s(context);
        }
        return this.m;
    }

    public String f(Context context) {
        if (TextUtils.isEmpty(this.h)) {
            this.h = a.o(context);
        }
        return this.h;
    }

    public String g(Context context) {
        if (TextUtils.isEmpty(this.f)) {
            this.f = a.C(context);
        }
        return this.f;
    }

    public String h(Context context) {
        if (TextUtils.isEmpty(this.l)) {
            this.l = a.r(context);
        }
        return this.l;
    }

    public String i(Context context) {
        if (TextUtils.isEmpty(this.r)) {
            this.r = a.t(context);
        }
        return this.r;
    }

    public String j(Context context) {
        if (TextUtils.isEmpty(this.u)) {
            this.u = a.m(context);
        }
        return this.u;
    }

    public String k(Context context) {
        if (TextUtils.isEmpty(this.t)) {
            this.t = a.e(context);
        }
        return this.t;
    }

    public String l(Context context) {
        if (TextUtils.isEmpty(this.s)) {
            this.s = a.g(context);
        }
        return this.s;
    }

    public String a() {
        if (TextUtils.isEmpty(this.k)) {
            this.k = a.c();
        }
        return this.k;
    }

    public String b() {
        if (TextUtils.isEmpty(this.C)) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            StringBuilder sb = new StringBuilder();
            sb.append(defaultAdapter != null);
            sb.append("");
            this.C = sb.toString();
        }
        return this.C;
    }

    public String m(Context context) {
        if (TextUtils.isEmpty(this.D)) {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            StringBuilder sb = new StringBuilder();
            sb.append(locationManager != null);
            sb.append("");
            this.D = sb.toString();
        }
        return this.D;
    }

    public String n(Context context) {
        if (TextUtils.isEmpty(this.E)) {
            this.E = a.l(context) + "";
        }
        return this.E;
    }

    public String o(Context context) {
        if (TextUtils.isEmpty(this.c)) {
            this.c = a.B(context);
        }
        return this.c;
    }

    public String p(Context context) {
        if (TextUtils.isEmpty(this.F)) {
            this.F = a.A(context);
        }
        return this.F;
    }

    public String c() {
        if (TextUtils.isEmpty(this.H)) {
            this.H = a.d() + "";
        }
        return this.H;
    }

    public String d() {
        if (TextUtils.isEmpty(this.G)) {
            this.G = ITagManager.STATUS_TRUE;
        }
        return this.G;
    }

    public String e() {
        if (TextUtils.isEmpty(this.x)) {
            this.x = Locale.getDefault().getLanguage();
        }
        return this.x;
    }

    public String f() {
        if (TextUtils.isEmpty(this.o)) {
            this.o = Build.MANUFACTURER;
        }
        return this.o;
    }

    public String q(Context context) {
        if (TextUtils.isEmpty(this.B)) {
            this.B = a.k(context);
        }
        return this.B;
    }

    public String g() {
        if (TextUtils.isEmpty(this.p)) {
            this.p = Build.MODEL;
        }
        return this.p;
    }

    public String h() {
        if (TextUtils.isEmpty(this.a)) {
            this.a = "0";
        }
        return this.a;
    }

    public String r(Context context) {
        if (TextUtils.isEmpty(this.i)) {
            this.i = a.f(context);
        }
        return this.i;
    }

    public String s(Context context) {
        if (TextUtils.isEmpty(this.q)) {
            this.q = context.getPackageName();
        }
        return this.q;
    }

    public String i() {
        if (TextUtils.isEmpty(this.n)) {
            this.n = Build.PRODUCT;
        }
        return this.n;
    }

    public String t(Context context) {
        if (TextUtils.isEmpty(this.w)) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
            int G = a.G(context);
            if (G == 0) {
                this.w = displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
            } else if (G == 1) {
                this.w = displayMetrics.heightPixels + "x" + displayMetrics.widthPixels;
            } else if (G == 2) {
                this.w = displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
            } else if (G == 3) {
                this.w = displayMetrics.heightPixels + "x" + displayMetrics.widthPixels;
            }
        }
        return this.w;
    }

    public boolean j() {
        return this.I;
    }

    private h() {
    }

    public static synchronized void u(Context context) {
        synchronized (h.class) {
            boolean z = true;
            J.I = true;
            J.a = "0";
            J.b = a.d(context);
            J.s = a.g(context);
            J.t = a.e(context);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
            J.w = displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
            J.e = a.p(context);
            J.d = a.w(context);
            J.g = a.o(context);
            J.c = a.B(context);
            J.f = a.C(context);
            J.h = a.o(context);
            J.m = a.s(context);
            J.i = a.f(context);
            J.r = a.t(context);
            J.u = a.m(context);
            J.l = a.r(context);
            J.j = a.q(context);
            J.k = a.c();
            J.x = Locale.getDefault().getLanguage();
            J.n = Build.PRODUCT;
            J.o = Build.MANUFACTURER;
            J.p = Build.MODEL;
            J.q = context.getPackageName();
            J.B = a.k(context);
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            h hVar = J;
            StringBuilder sb = new StringBuilder();
            sb.append(defaultAdapter != null);
            sb.append("");
            hVar.C = sb.toString();
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            h hVar2 = J;
            StringBuilder sb2 = new StringBuilder();
            if (locationManager == null) {
                z = false;
            }
            sb2.append(z);
            sb2.append("");
            hVar2.D = sb2.toString();
            J.E = a.l(context) + "";
            J.F = a.A(context);
            J.G = ITagManager.STATUS_TRUE;
            J.H = a.d() + "";
            J.z = a.g();
            J.y = a.f();
            J.A = a.h();
            J.v = a.e();
        }
    }

    public static synchronized h k() {
        h hVar;
        synchronized (h.class) {
            if (!J.I) {
                a.a(h.class.getSimpleName(), "not initialized, call initializeInstance(..) method first.");
            }
            hVar = J;
        }
        return hVar;
    }
}
