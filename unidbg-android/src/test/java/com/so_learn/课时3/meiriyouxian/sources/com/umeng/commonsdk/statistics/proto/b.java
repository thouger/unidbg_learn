package com.umeng.commonsdk.statistics.proto;

import android.net.wifi.WifiEnterpriseConfig;
import com.umeng.analytics.pro.an;
import com.umeng.analytics.pro.aq;
import com.umeng.analytics.pro.aw;
import com.umeng.analytics.pro.ax;
import com.umeng.analytics.pro.bc;
import com.umeng.analytics.pro.bd;
import com.umeng.analytics.pro.bj;
import com.umeng.analytics.pro.bk;
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

/* compiled from: IdSnapshot */
public class b implements aq<b, e>, Serializable, Cloneable {
    public static final Map<e, bc> d;
    private static final long e = -6496538196005191531L;
    private static final bu f = new bu("IdSnapshot");
    private static final bk g = new bk(WifiEnterpriseConfig.IDENTITY_KEY, (byte) 11, 1);
    private static final bk h = new bk("ts", (byte) 10, 2);
    private static final bk i = new bk("version", (byte) 8, 3);
    private static final Map<Class<? extends bx>, by> j = new HashMap();
    private static final int k = 0;
    private static final int l = 1;
    public String a;
    public long b;
    public int c;
    private byte m;

    static {
        j.put(bz.class, new C0186b());
        j.put(ca.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.IDENTITY, (e) new bc(WifiEnterpriseConfig.IDENTITY_KEY, (byte) 1, new bd((byte) 11)));
        enumMap.put((EnumMap) e.TS, (e) new bc("ts", (byte) 1, new bd((byte) 10)));
        enumMap.put((EnumMap) e.VERSION, (e) new bc("version", (byte) 1, new bd((byte) 8)));
        d = Collections.unmodifiableMap(enumMap);
        bc.a(b.class, d);
    }

    /* compiled from: IdSnapshot */
    public enum e implements ax {
        IDENTITY(1, WifiEnterpriseConfig.IDENTITY_KEY),
        TS(2, "ts"),
        VERSION(3, "version");
        
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
                return IDENTITY;
            }
            if (i == 2) {
                return TS;
            }
            if (i != 3) {
                return null;
            }
            return VERSION;
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

    public b() {
        this.m = 0;
    }

    public b(String str, long j2, int i2) {
        this();
        this.a = str;
        this.b = j2;
        b(true);
        this.c = i2;
        c(true);
    }

    public b(b bVar) {
        this.m = 0;
        this.m = bVar.m;
        if (bVar.d()) {
            this.a = bVar.a;
        }
        this.b = bVar.b;
        this.c = bVar.c;
    }

    /* renamed from: a */
    public b deepCopy() {
        return new b(this);
    }

    @Override // com.umeng.analytics.pro.aq
    public void clear() {
        this.a = null;
        b(false);
        this.b = 0;
        c(false);
        this.c = 0;
    }

    public String b() {
        return this.a;
    }

    public b a(String str) {
        this.a = str;
        return this;
    }

    public void c() {
        this.a = null;
    }

    public boolean d() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public long e() {
        return this.b;
    }

    public b a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public void f() {
        this.m = an.b(this.m, 0);
    }

    public boolean g() {
        return an.a(this.m, 0);
    }

    public void b(boolean z) {
        this.m = an.a(this.m, 0, z);
    }

    public int h() {
        return this.c;
    }

    public b a(int i2) {
        this.c = i2;
        c(true);
        return this;
    }

    public void i() {
        this.m = an.b(this.m, 1);
    }

    public boolean j() {
        return an.a(this.m, 1);
    }

    public void c(boolean z) {
        this.m = an.a(this.m, 1, z);
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
        StringBuilder sb = new StringBuilder("IdSnapshot(");
        sb.append("identity:");
        String str = this.a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("version:");
        sb.append(this.c);
        sb.append(l.t);
        return sb.toString();
    }

    public void k() throws aw {
        if (this.a == null) {
            throw new bq("Required field 'identity' was not present! Struct: " + toString());
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
            this.m = 0;
            read(new bj(new cb(objectInputStream)));
        } catch (aw e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdSnapshot */
    /* renamed from: com.umeng.commonsdk.statistics.proto.b$b  reason: collision with other inner class name */
    private static class C0186b implements by {
        private C0186b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* compiled from: IdSnapshot */
    /* access modifiers changed from: private */
    public static class a extends bz<b> {
        private a() {
        }

        /* renamed from: a */
        public void b(bp bpVar, b bVar) throws aw {
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
                        } else if (l.b == 8) {
                            bVar.c = bpVar.w();
                            bVar.c(true);
                        } else {
                            bs.a(bpVar, l.b);
                        }
                    } else if (l.b == 10) {
                        bVar.b = bpVar.x();
                        bVar.b(true);
                    } else {
                        bs.a(bpVar, l.b);
                    }
                } else if (l.b == 11) {
                    bVar.a = bpVar.z();
                    bVar.a(true);
                } else {
                    bs.a(bpVar, l.b);
                }
                bpVar.m();
            }
            bpVar.k();
            if (!bVar.g()) {
                throw new bq("Required field 'ts' was not found in serialized data! Struct: " + toString());
            } else if (bVar.j()) {
                bVar.k();
            } else {
                throw new bq("Required field 'version' was not found in serialized data! Struct: " + toString());
            }
        }

        /* renamed from: b */
        public void a(bp bpVar, b bVar) throws aw {
            bVar.k();
            bpVar.a(b.f);
            if (bVar.a != null) {
                bpVar.a(b.g);
                bpVar.a(bVar.a);
                bpVar.c();
            }
            bpVar.a(b.h);
            bpVar.a(bVar.b);
            bpVar.c();
            bpVar.a(b.i);
            bpVar.a(bVar.c);
            bpVar.c();
            bpVar.d();
            bpVar.b();
        }
    }

    /* compiled from: IdSnapshot */
    private static class d implements by {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* compiled from: IdSnapshot */
    /* access modifiers changed from: private */
    public static class c extends ca<b> {
        private c() {
        }

        public void a(bp bpVar, b bVar) throws aw {
            bv bvVar = (bv) bpVar;
            bvVar.a(bVar.a);
            bvVar.a(bVar.b);
            bvVar.a(bVar.c);
        }

        public void b(bp bpVar, b bVar) throws aw {
            bv bvVar = (bv) bpVar;
            bVar.a = bvVar.z();
            bVar.a(true);
            bVar.b = bvVar.x();
            bVar.b(true);
            bVar.c = bvVar.w();
            bVar.c(true);
        }
    }
}
