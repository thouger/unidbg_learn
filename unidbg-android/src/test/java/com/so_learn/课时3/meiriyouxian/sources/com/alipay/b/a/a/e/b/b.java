package com.alipay.b.a.a.e.b;

import android.content.Context;
import com.alipay.b.a.a.e.a;
import com.alipay.b.a.a.e.a.c;
import com.alipay.b.a.a.e.d;

public class b implements a {
    private static a a;
    private static a b;

    public static a a(Context context, String str) {
        if (context == null) {
            return null;
        }
        if (a == null) {
            b = d.a(context, str);
            a = new b();
        }
        return a;
    }

    @Override // com.alipay.b.a.a.e.b.a
    public c a(com.alipay.b.a.a.e.a.d dVar) {
        return com.alipay.b.a.a.e.a.b.a(b.a(com.alipay.b.a.a.e.a.b.a(dVar)));
    }

    @Override // com.alipay.b.a.a.e.b.a
    public boolean a(String str) {
        return b.a(str);
    }
}
