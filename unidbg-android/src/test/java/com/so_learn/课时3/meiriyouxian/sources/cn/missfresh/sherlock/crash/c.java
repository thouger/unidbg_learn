package cn.missfresh.sherlock.crash;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import xcrash.ICrashCallback;
import xcrash.ILibLoader;
import xcrash.NativeHandler;
import xcrash.Util;

/* compiled from: XCrash */
public final class c {
    private static boolean a;
    private static String b;
    private static String c;

    /* compiled from: XCrash */
    public static class a {
        boolean A = true;
        int B = 50;
        int C = 50;
        int D = 200;
        boolean E = true;
        ICrashCallback F = null;
        String a = null;
        String b = null;
        ILibLoader c = null;
        boolean d = true;
        int e = 10;
        int f = 50;
        int g = 50;
        int h = 200;
        boolean i = true;
        int j = 0;
        String[] k = null;
        ICrashCallback l = null;
        boolean m = true;
        boolean n = true;
        int o = 50;
        int p = 50;
        int q = 200;
        boolean r = true;
        boolean s = true;
        boolean t = true;
        boolean u = true;
        int v = 0;
        String[] w = null;
        ICrashCallback x = null;
        boolean y = true;
        boolean z = true;

        public a a(int i) {
            this.h = i;
            return this;
        }

        public a b(int i) {
            if (i < 0) {
                i = 0;
            }
            this.j = i;
            return this;
        }

        public a c(int i) {
            return this;
        }

        public a d(int i) {
            if (i < 0) {
                i = 0;
            }
            this.v = i;
            return this;
        }

        public a a(String[] strArr) {
            this.k = strArr;
            return this;
        }

        public a b(String[] strArr) {
            this.w = strArr;
            return this;
        }

        public a a(boolean z) {
            this.n = z;
            return this;
        }

        public a a(ICrashCallback iCrashCallback) {
            this.x = iCrashCallback;
            return this;
        }
    }

    public static synchronized int a(Context context, a aVar) {
        a aVar2;
        ICrashCallback iCrashCallback;
        boolean z;
        synchronized (c.class) {
            if (a) {
                return 0;
            }
            a = true;
            if (context == null) {
                return -1;
            }
            Context applicationContext = context.getApplicationContext();
            Context context2 = applicationContext != null ? applicationContext : context;
            a aVar3 = aVar == null ? new a() : aVar;
            if (TextUtils.isEmpty(aVar3.a)) {
                aVar3.a = Util.getAppVersion(context2);
            }
            if (TextUtils.isEmpty(aVar3.b)) {
                aVar3.b = context2.getFilesDir() + "/sherlock";
            }
            b = context2.getPackageName();
            if (TextUtils.isEmpty(b)) {
                b = "unknown";
            }
            c = aVar3.a;
            String str = aVar3.b;
            if (aVar3.d) {
                aVar2 = aVar3;
                b.a().a(context2, b, aVar3.a, aVar3.b, aVar3.e, aVar3.f, aVar3.g, aVar3.h, aVar3.i, aVar3.j, aVar3.k, aVar3.l);
            } else {
                aVar2 = aVar3;
            }
            if (aVar2.m) {
                NativeHandler instance = NativeHandler.getInstance();
                ILibLoader iLibLoader = aVar2.c;
                String str2 = b;
                String str3 = aVar2.a;
                String str4 = aVar2.b;
                boolean z2 = aVar2.m;
                boolean z3 = aVar2.n;
                int i = aVar2.o;
                int i2 = aVar2.p;
                int i3 = aVar2.q;
                boolean z4 = aVar2.r;
                boolean z5 = aVar2.s;
                boolean z6 = aVar2.t;
                boolean z7 = aVar2.u;
                int i4 = aVar2.v;
                String[] strArr = aVar2.w;
                ICrashCallback iCrashCallback2 = aVar2.x;
                if (aVar2.y) {
                    iCrashCallback = iCrashCallback2;
                    if (Build.VERSION.SDK_INT >= 21) {
                        z = true;
                        instance.initialize(context2, iLibLoader, str2, str3, str4, z2, z3, i, i2, i3, z4, z5, z6, z7, i4, strArr, iCrashCallback, z, aVar2.z, aVar2.A, aVar2.B, aVar2.C, aVar2.D, aVar2.E, aVar2.F);
                    }
                } else {
                    iCrashCallback = iCrashCallback2;
                }
                z = false;
                instance.initialize(context2, iLibLoader, str2, str3, str4, z2, z3, i, i2, i3, z4, z5, z6, z7, i4, strArr, iCrashCallback, z, aVar2.z, aVar2.A, aVar2.B, aVar2.C, aVar2.D, aVar2.E, aVar2.F);
            }
            return 0;
        }
    }

    static String b() {
        return c;
    }

    static String a() {
        return b;
    }
}
