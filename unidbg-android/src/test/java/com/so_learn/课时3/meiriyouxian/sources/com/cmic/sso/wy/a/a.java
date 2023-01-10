package com.cmic.sso.wy.a;

import android.os.Build;

/* compiled from: BrandUtil */
public class a {

    /* compiled from: BrandUtil */
    /* renamed from: com.cmic.sso.wy.a.a$a  reason: collision with other inner class name */
    public enum EnumC0072a {
        UNKNOWN,
        SAMSUNG,
        HUAWEI
    }

    private static EnumC0072a b() {
        String str = Build.BRAND;
        if (str.equalsIgnoreCase("samsung")) {
            return EnumC0072a.SAMSUNG;
        }
        if (str.equalsIgnoreCase("Huawei")) {
            return EnumC0072a.HUAWEI;
        }
        return EnumC0072a.UNKNOWN;
    }

    static int a() {
        return a(b());
    }

    /* compiled from: BrandUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.a.a$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[EnumC0072a.values().length];

        static {
            try {
                a[EnumC0072a.HUAWEI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[EnumC0072a.SAMSUNG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static int a(EnumC0072a aVar) {
        int i = AnonymousClass1.a[aVar.ordinal()];
        if (i != 1) {
            return i != 2 ? -1 : 1;
        }
        return 0;
    }
}
