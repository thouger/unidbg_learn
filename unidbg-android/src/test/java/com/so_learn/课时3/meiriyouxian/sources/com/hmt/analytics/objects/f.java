package com.hmt.analytics.objects;

import android.content.Context;
import com.hmt.analytics.android.a;

/* compiled from: PostObjAction */
public class f {
    private static final String a = f.class.getSimpleName();
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public f(String str, String str2, Context context) {
        this.b = str;
        this.c = str2;
        this.d = a.a();
        this.e = a.a(context, 1);
        this.g = a.d(context);
        this.f = a.m(context);
    }

    public f(String str, String str2, String str3, String str4, String str5, String str6) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
    }

    public boolean a() {
        if (!d().contains("-") && d() != null && !d().equals("")) {
            return true;
        }
        a.a(a, d());
        return false;
    }

    public String b() {
        return this.e;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        String str = this.c;
        int i4 = 0;
        if (str == null) {
            i = 0;
        } else {
            i = str.hashCode();
        }
        int i5 = (i + 31) * 31;
        String str2 = this.e;
        if (str2 == null) {
            i2 = 0;
        } else {
            i2 = str2.hashCode();
        }
        int i6 = (i5 + i2) * 31;
        String str3 = this.g;
        int hashCode = (i6 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.b;
        if (str4 == null) {
            i3 = 0;
        } else {
            i3 = str4.hashCode();
        }
        int i7 = (hashCode + i3) * 31;
        String str5 = this.d;
        int hashCode2 = (i7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.f;
        if (str6 != null) {
            i4 = str6.hashCode();
        }
        return hashCode2 + i4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        String str = this.c;
        if (str == null) {
            if (fVar.c != null) {
                return false;
            }
        } else if (!str.equals(fVar.c)) {
            return false;
        }
        String str2 = this.e;
        if (str2 == null) {
            if (fVar.e != null) {
                return false;
            }
        } else if (!str2.equals(fVar.e)) {
            return false;
        }
        String str3 = this.g;
        if (str3 == null) {
            if (fVar.g != null) {
                return false;
            }
        } else if (!str3.equals(fVar.g)) {
            return false;
        }
        String str4 = this.b;
        if (str4 == null) {
            if (fVar.b != null) {
                return false;
            }
        } else if (!str4.equals(fVar.b)) {
            return false;
        }
        String str5 = this.d;
        if (str5 == null) {
            if (fVar.d != null) {
                return false;
            }
        } else if (!str5.equals(fVar.d)) {
            return false;
        }
        String str6 = this.f;
        if (str6 == null) {
            if (fVar.f != null) {
                return false;
            }
        } else if (!str6.equals(fVar.f)) {
            return false;
        }
        return true;
    }
}
