package com.umeng.commonsdk.statistics.proto;

import com.umeng.analytics.pro.an;
import com.umeng.analytics.pro.aq;
import com.umeng.analytics.pro.aw;
import com.umeng.analytics.pro.ax;
import com.umeng.analytics.pro.bc;
import com.umeng.analytics.pro.bd;
import com.umeng.analytics.pro.bf;
import com.umeng.analytics.pro.bh;
import com.umeng.analytics.pro.bj;
import com.umeng.analytics.pro.bk;
import com.umeng.analytics.pro.bm;
import com.umeng.analytics.pro.bp;
import com.umeng.analytics.pro.bq;
import com.umeng.analytics.pro.bs;
import com.umeng.analytics.pro.bu;
import com.umeng.analytics.pro.bv;
import com.umeng.analytics.pro.bx;
import com.umeng.analytics.pro.by;
import com.umeng.analytics.pro.bz;
import com.umeng.analytics.pro.ca;
import com.umeng.analytics.pro.cb;
import com.umeng.message.proguard.l;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: Imprint */
public class d implements aq<d, e>, Serializable, Cloneable {
    public static final Map<e, bc> d;
    private static final long e = 2846460275012375038L;
    private static final bu f = new bu("Imprint");
    private static final bk g = new bk("property", (byte) 13, 1);
    private static final bk h = new bk("version", (byte) 8, 2);
    private static final bk i = new bk("checksum", (byte) 11, 3);
    private static final Map<Class<? extends bx>, by> j = new HashMap();
    private static final int k = 0;
    public Map<String, e> a;
    public int b;
    public String c;
    private byte l;

    static {
        j.put(bz.class, new b());
        j.put(ca.class, new C0188d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.PROPERTY, (e) new bc("property", (byte) 1, new bf((byte) 13, new bd((byte) 11), new bh((byte) 12, e.class))));
        enumMap.put((EnumMap) e.VERSION, (e) new bc("version", (byte) 1, new bd((byte) 8)));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new bc("checksum", (byte) 1, new bd((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        bc.a(d.class, d);
    }

    /* compiled from: Imprint */
    public enum e implements ax {
        PROPERTY(1, "property"),
        VERSION(2, "version"),
        CHECKSUM(3, "checksum");
        
        private static final Map<String, e> d = new HashMap();
        private final short e;
        private final String f;

        static {
            Iterator it2 = EnumSet.allOf(e.class).iterator();
            while (it2.hasNext()) {
                e eVar = (e) it2.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            if (i == 1) {
                return PROPERTY;
            }
            if (i == 2) {
                return VERSION;
            }
            if (i != 3) {
                return null;
            }
            return CHECKSUM;
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return d.get(str);
        }

        private e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        @Override // com.umeng.analytics.pro.ax
        public short a() {
            return this.e;
        }

        @Override // com.umeng.analytics.pro.ax
        public String b() {
            return this.f;
        }
    }

    public d() {
        this.l = 0;
    }

    public d(Map<String, e> map, int i2, String str) {
        this();
        this.a = map;
        this.b = i2;
        b(true);
        this.c = str;
    }

    public d(d dVar) {
        this.l = 0;
        this.l = dVar.l;
        if (dVar.e()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, e> entry : dVar.a.entrySet()) {
                hashMap.put(entry.getKey(), new e(entry.getValue()));
            }
            this.a = hashMap;
        }
        this.b = dVar.b;
        if (dVar.k()) {
            this.c = dVar.c;
        }
    }

    /* renamed from: a */
    public d deepCopy() {
        return new d(this);
    }

    @Override // com.umeng.analytics.pro.aq
    public void clear() {
        this.a = null;
        b(false);
        this.b = 0;
        this.c = null;
    }

    public int b() {
        Map<String, e> map = this.a;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public void a(String str, e eVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, eVar);
    }

    public Map<String, e> c() {
        return this.a;
    }

    public d a(Map<String, e> map) {
        this.a = map;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public int f() {
        return this.b;
    }

    public d a(int i2) {
        this.b = i2;
        b(true);
        return this;
    }

    public void g() {
        this.l = an.b(this.l, 0);
    }

    public boolean h() {
        return an.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = an.a(this.l, 0, z);
    }

    public String i() {
        return this.c;
    }

    public d a(String str) {
        this.c = str;
        return this;
    }

    public void j() {
        this.c = null;
    }

    public boolean k() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    /* renamed from: b */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    @Override // com.umeng.analytics.pro.aq
    public void read(bp bpVar) throws aw {
        j.get(bpVar.D()).b().b(bpVar, this);
    }

    @Override // com.umeng.analytics.pro.aq
    public void write(bp bpVar) throws aw {
        j.get(bpVar.D()).b().a(bpVar, this);
    }

    @Override // java.lang.Object
    public String toString() {
        StringBuilder sb = new StringBuilder("Imprint(");
        sb.append("property:");
        Map<String, e> map = this.a;
        if (map == null) {
            sb.append("null");
        } else {
            sb.append(map);
        }
        sb.append(", ");
        sb.append("version:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("checksum:");
        String str = this.c;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(l.t);
        return sb.toString();
    }

    public void l() throws aw {
        if (this.a == null) {
            throw new bq("Required field 'property' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new bq("Required field 'checksum' was not present! Struct: " + toString());
        }
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new bj(new cb(objectOutputStream)));
        } catch (aw e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.l = 0;
            read(new bj(new cb(objectInputStream)));
        } catch (aw e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: Imprint */
    private static class b implements by {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* compiled from: Imprint */
    /* access modifiers changed from: private */
    public static class a extends bz<d> {
        private a() {
        }

        /* renamed from: a */
        public void b(bp bpVar, d dVar) throws aw {
            bpVar.j();
            while (true) {
                bk l = bpVar.l();
                if (l.b == 0) {
                    break;
                }
                short s = l.c;
                if (s != 1) {
                    if (s != 2) {
                        if (s != 3) {
                            bs.a(bpVar, l.b);
                        } else if (l.b == 11) {
                            dVar.c = bpVar.z();
                            dVar.c(true);
                        } else {
                            bs.a(bpVar, l.b);
                        }
                    } else if (l.b == 8) {
                        dVar.b = bpVar.w();
                        dVar.b(true);
                    } else {
                        bs.a(bpVar, l.b);
                    }
                } else if (l.b == 13) {
                    bm n = bpVar.n();
                    dVar.a = new HashMap(n.c * 2);
                    for (int i = 0; i < n.c; i++) {
                        String z = bpVar.z();
                        e eVar = new e();
                        eVar.read(bpVar);
                        dVar.a.put(z, eVar);
                    }
                    bpVar.o();
                    dVar.a(true);
                } else {
                    bs.a(bpVar, l.b);
                }
                bpVar.m();
            }
            bpVar.k();
            if (dVar.h()) {
                dVar.l();
                return;
            }
            throw new bq("Required field 'version' was not found in serialized data! Struct: " + toString());
        }

        /* renamed from: b */
        public void a(bp bpVar, d dVar) throws aw {
            dVar.l();
            bpVar.a(d.f);
            if (dVar.a != null) {
                bpVar.a(d.g);
                bpVar.a(new bm((byte) 11, (byte) 12, dVar.a.size()));
                for (Map.Entry<String, e> entry : dVar.a.entrySet()) {
                    bpVar.a(entry.getKey());
                    entry.getValue().write(bpVar);
                }
                bpVar.e();
                bpVar.c();
            }
            bpVar.a(d.h);
            bpVar.a(dVar.b);
            bpVar.c();
            if (dVar.c != null) {
                bpVar.a(d.i);
                bpVar.a(dVar.c);
                bpVar.c();
            }
            bpVar.d();
            bpVar.b();
        }
    }

    /* compiled from: Imprint */
    /* renamed from: com.umeng.commonsdk.statistics.proto.d$d  reason: collision with other inner class name */
    private static class C0188d implements by {
        private C0188d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* compiled from: Imprint */
    /* access modifiers changed from: private */
    public static class c extends ca<d> {
        private c() {
        }

        public void a(bp bpVar, d dVar) throws aw {
            bv bvVar = (bv) bpVar;
            bvVar.a(dVar.a.size());
            for (Map.Entry<String, e> entry : dVar.a.entrySet()) {
                bvVar.a(entry.getKey());
                entry.getValue().write(bvVar);
            }
            bvVar.a(dVar.b);
            bvVar.a(dVar.c);
        }

        public void b(bp bpVar, d dVar) throws aw {
            bv bvVar = (bv) bpVar;
            bm bmVar = new bm((byte) 11, (byte) 12, bvVar.w());
            dVar.a = new HashMap(bmVar.c * 2);
            for (int i = 0; i < bmVar.c; i++) {
                String z = bvVar.z();
                e eVar = new e();
                eVar.read(bvVar);
                dVar.a.put(z, eVar);
            }
            dVar.a(true);
            dVar.b = bvVar.w();
            dVar.b(true);
            dVar.c = bvVar.z();
            dVar.c(true);
        }
    }
}
