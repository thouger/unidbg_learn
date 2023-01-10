package com.umeng.commonsdk.statistics.idtracking;

import com.umeng.commonsdk.statistics.proto.b;
import com.umeng.commonsdk.statistics.proto.c;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: AbstractIdTracker */
public abstract class a {
    private final int a = 10;
    private final int b = 100;
    private final String c;
    private List<com.umeng.commonsdk.statistics.proto.a> d;
    private b e;

    public abstract String f();

    public a(String str) {
        this.c = str;
    }

    public boolean a() {
        return g();
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        b bVar = this.e;
        return bVar == null || bVar.h() <= 100;
    }

    private boolean g() {
        String str;
        int i;
        b bVar = this.e;
        if (bVar == null) {
            str = null;
        } else {
            str = bVar.b();
        }
        if (bVar == null) {
            i = 0;
        } else {
            i = bVar.h();
        }
        String a = a(f());
        if (a == null || a.equals(str)) {
            return false;
        }
        if (bVar == null) {
            bVar = new b();
        }
        bVar.a(a);
        bVar.a(System.currentTimeMillis());
        bVar.a(i + 1);
        com.umeng.commonsdk.statistics.proto.a aVar = new com.umeng.commonsdk.statistics.proto.a();
        aVar.a(this.c);
        aVar.c(a);
        aVar.b(str);
        aVar.a(bVar.e());
        if (this.d == null) {
            this.d = new ArrayList(2);
        }
        this.d.add(aVar);
        if (this.d.size() > 10) {
            this.d.remove(0);
        }
        this.e = bVar;
        return true;
    }

    public b d() {
        return this.e;
    }

    public void a(b bVar) {
        this.e = bVar;
    }

    public List<com.umeng.commonsdk.statistics.proto.a> e() {
        return this.d;
    }

    public void a(List<com.umeng.commonsdk.statistics.proto.a> list) {
        this.d = list;
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() != 0 && !"0".equals(trim) && !"unknown".equals(trim.toLowerCase(Locale.US))) {
            return trim;
        }
        return null;
    }

    public void a(c cVar) {
        this.e = cVar.c().get(this.c);
        List<com.umeng.commonsdk.statistics.proto.a> h = cVar.h();
        if (h != null && h.size() > 0) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            for (com.umeng.commonsdk.statistics.proto.a aVar : h) {
                if (this.c.equals(aVar.a)) {
                    this.d.add(aVar);
                }
            }
        }
    }
}
