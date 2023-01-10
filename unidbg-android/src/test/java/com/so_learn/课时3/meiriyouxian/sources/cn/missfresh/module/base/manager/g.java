package cn.missfresh.module.base.manager;

import android.graphics.Bitmap;
import cn.missfresh.module.base.bean.ConfigurationBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.HashMap;

/* compiled from: GlobalDataManager */
public class g {
    public static String a = "";
    public static ConfigurationBean.SkipInfo b = null;
    private static HashMap<String, Object> c = null;
    private static g d = null;
    private static boolean e = true;
    private boolean f = false;
    private int g = 0;
    private boolean h = false;
    private String i = "";
    private String j = "";
    private boolean k = false;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private String p;
    private boolean q;

    public boolean b() {
        return true;
    }

    private g() {
        AppMethodBeat.i(14461, false);
        if (c == null) {
            c = new HashMap<>();
        }
        AppMethodBeat.o(14461);
    }

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            AppMethodBeat.i(14462, false);
            if (d == null) {
                d = new g();
            }
            gVar = d;
            AppMethodBeat.o(14462);
        }
        return gVar;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public int c() {
        return this.g;
    }

    public void b(boolean z) {
        this.h = z;
    }

    public void a(Bitmap bitmap) {
        AppMethodBeat.i(14470, false);
        c.put("product_share_img", bitmap);
        AppMethodBeat.o(14470);
    }

    public Bitmap d() {
        AppMethodBeat.i(14471, false);
        Bitmap bitmap = (Bitmap) c.get("product_share_img");
        AppMethodBeat.o(14471);
        return bitmap;
    }

    public String e() {
        return this.p;
    }

    public void a(String str) {
        this.p = str;
    }

    public boolean f() {
        return this.q;
    }

    public void c(boolean z) {
        this.q = z;
    }

    public void b(String str) {
        this.i = str;
    }

    public String g() {
        return this.i;
    }

    public void c(String str) {
        this.j = str;
    }

    public String h() {
        return this.j;
    }

    public int i() {
        return this.l;
    }

    public void a(int i) {
        this.l = i;
    }

    public int j() {
        AppMethodBeat.i(14478, false);
        int i = this.m;
        if (i == 0) {
            int i2 = i();
            AppMethodBeat.o(14478);
            return i2;
        }
        AppMethodBeat.o(14478);
        return i;
    }

    public void b(int i) {
        this.m = i;
    }

    public int k() {
        AppMethodBeat.i(14479, false);
        int i = this.n;
        if (i == 0) {
            int i2 = i();
            AppMethodBeat.o(14479);
            return i2;
        }
        AppMethodBeat.o(14479);
        return i;
    }

    public void c(int i) {
        this.n = i;
    }

    public int l() {
        return this.o;
    }

    public void d(int i) {
        this.o = i;
    }
}
