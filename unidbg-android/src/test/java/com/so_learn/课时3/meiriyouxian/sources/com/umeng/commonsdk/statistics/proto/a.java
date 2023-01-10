package com.umeng.commonsdk.statistics.proto;

import anet.channel.strategy.dispatch.DispatchConstants;
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
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: IdJournal */
public class a implements aq<a, e>, Serializable, Cloneable {
    public static final Map<e, bc> e;
    private static final long f = 9132678615281394583L;
    private static final bu g = new bu("IdJournal");
    private static final bk h = new bk(DispatchConstants.DOMAIN, (byte) 11, 1);
    private static final bk i = new bk("old_id", (byte) 11, 2);
    private static final bk j = new bk("new_id", (byte) 11, 3);
    private static final bk k = new bk("ts", (byte) 10, 4);
    private static final Map<Class<? extends bx>, by> l = new HashMap();
    private static final int m = 0;
    public String a;
    public String b;
    public String c;
    public long d;
    private byte n;
    private e[] o;

    static {
        l.put(bz.class, new b());
        l.put(ca.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.DOMAIN, (e) new bc(DispatchConstants.DOMAIN, (byte) 1, new bd((byte) 11)));
        enumMap.put((EnumMap) e.OLD_ID, (e) new bc("old_id", (byte) 2, new bd((byte) 11)));
        enumMap.put((EnumMap) e.NEW_ID, (e) new bc("new_id", (byte) 1, new bd((byte) 11)));
        enumMap.put((EnumMap) e.TS, (e) new bc("ts", (byte) 1, new bd((byte) 10)));
        e = Collections.unmodifiableMap(enumMap);
        bc.a(a.class, e);
    }

    /* compiled from: IdJournal */
    public enum e implements ax {
        DOMAIN(1, DispatchConstants.DOMAIN),
        OLD_ID(2, "old_id"),
        NEW_ID(3, "new_id"),
        TS(4, "ts");
        
        private static final Map<String, e> e = new HashMap();
        private final short f;
        private final String g;

        static {
            Iterator it2 = EnumSet.allOf(e.class).iterator();
            while (it2.hasNext()) {
                e eVar = (e) it2.next();
                e.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            if (i == 1) {
                return DOMAIN;
            }
            if (i == 2) {
                return OLD_ID;
            }
            if (i == 3) {
                return NEW_ID;
            }
            if (i != 4) {
                return null;
            }
            return TS;
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return e.get(str);
        }

        private e(short s, String str) {
            this.f = s;
            this.g = str;
        }

        @Override // com.umeng.analytics.pro.ax
        public short a() {
            return this.f;
        }

        @Override // com.umeng.analytics.pro.ax
        public String b() {
            return this.g;
        }
    }

    public a() {
        this.n = 0;
        this.o = new e[]{e.OLD_ID};
    }

    public a(String str, String str2, long j2) {
        this();
        this.a = str;
        this.c = str2;
        this.d = j2;
        d(true);
    }

    public a(a aVar) {
        this.n = 0;
        this.o = new e[]{e.OLD_ID};
        this.n = aVar.n;
        if (aVar.d()) {
            this.a = aVar.a;
        }
        if (aVar.g()) {
            this.b = aVar.b;
        }
        if (aVar.j()) {
            this.c = aVar.c;
        }
        this.d = aVar.d;
    }

    /* renamed from: a */
    public a deepCopy() {
        return new a(this);
    }

    @Override // com.umeng.analytics.pro.aq
    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
    }

    public String b() {
        return this.a;
    }

    public a a(String str) {
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

    public String e() {
        return this.b;
    }

    public a b(String str) {
        this.b = str;
        return this;
    }

    public void f() {
        this.b = null;
    }

    public boolean g() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public String h() {
        return this.c;
    }

    public a c(String str) {
        this.c = str;
        return this;
    }

    public void i() {
        this.c = null;
    }

    public boolean j() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public long k() {
        return this.d;
    }

    public a a(long j2) {
        this.d = j2;
        d(true);
        return this;
    }

    public void l() {
        this.n = an.b(this.n, 0);
    }

    public boolean m() {
        return an.a(this.n, 0);
    }

    public void d(boolean z) {
        this.n = an.a(this.n, 0, z);
    }

    /* renamed from: a */
    public e fieldForId(int i2) {
        return e.a(i2);
    }

    @Override // com.umeng.analytics.pro.aq
    public void read(bp bpVar) throws aw {
        l.get(bpVar.D()).b().b(bpVar, this);
    }

    @Override // com.umeng.analytics.pro.aq
    public void write(bp bpVar) throws aw {
        l.get(bpVar.D()).b().a(bpVar, this);
    }

    @Override // java.lang.Object
    public String toString() {
        StringBuilder sb = new StringBuilder("IdJournal(");
        sb.append("domain:");
        String str = this.a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        if (g()) {
            sb.append(", ");
            sb.append("old_id:");
            String str2 = this.b;
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(str2);
            }
        }
        sb.append(", ");
        sb.append("new_id:");
        String str3 = this.c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.d);
        sb.append(l.t);
        return sb.toString();
    }

    public void n() throws aw {
        if (this.a == null) {
            throw new bq("Required field 'domain' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new bq("Required field 'new_id' was not present! Struct: " + toString());
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
            this.n = 0;
            read(new bj(new cb(objectInputStream)));
        } catch (aw e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdJournal */
    private static class b implements by {
        private b() {
        }

        /* renamed from: a */
        public C0185a b() {
            return new C0185a();
        }
    }

    /* compiled from: IdJournal */
    /* access modifiers changed from: private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.a$a  reason: collision with other inner class name */
    public static class C0185a extends bz<a> {
        private C0185a() {
        }

        /* renamed from: a */
        public void b(bp bpVar, a aVar) throws aw {
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
                            if (s != 4) {
                                bs.a(bpVar, l.b);
                            } else if (l.b == 10) {
                                aVar.d = bpVar.x();
                                aVar.d(true);
                            } else {
                                bs.a(bpVar, l.b);
                            }
                        } else if (l.b == 11) {
                            aVar.c = bpVar.z();
                            aVar.c(true);
                        } else {
                            bs.a(bpVar, l.b);
                        }
                    } else if (l.b == 11) {
                        aVar.b = bpVar.z();
                        aVar.b(true);
                    } else {
                        bs.a(bpVar, l.b);
                    }
                } else if (l.b == 11) {
                    aVar.a = bpVar.z();
                    aVar.a(true);
                } else {
                    bs.a(bpVar, l.b);
                }
                bpVar.m();
            }
            bpVar.k();
            if (aVar.m()) {
                aVar.n();
                return;
            }
            throw new bq("Required field 'ts' was not found in serialized data! Struct: " + toString());
        }

        /* renamed from: b */
        public void a(bp bpVar, a aVar) throws aw {
            aVar.n();
            bpVar.a(a.g);
            if (aVar.a != null) {
                bpVar.a(a.h);
                bpVar.a(aVar.a);
                bpVar.c();
            }
            if (aVar.b != null && aVar.g()) {
                bpVar.a(a.i);
                bpVar.a(aVar.b);
                bpVar.c();
            }
            if (aVar.c != null) {
                bpVar.a(a.j);
                bpVar.a(aVar.c);
                bpVar.c();
            }
            bpVar.a(a.k);
            bpVar.a(aVar.d);
            bpVar.c();
            bpVar.d();
            bpVar.b();
        }
    }

    /* compiled from: IdJournal */
    private static class d implements by {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* compiled from: IdJournal */
    /* access modifiers changed from: private */
    public static class c extends ca<a> {
        private c() {
        }

        public void a(bp bpVar, a aVar) throws aw {
            bv bvVar = (bv) bpVar;
            bvVar.a(aVar.a);
            bvVar.a(aVar.c);
            bvVar.a(aVar.d);
            BitSet bitSet = new BitSet();
            if (aVar.g()) {
                bitSet.set(0);
            }
            bvVar.a(bitSet, 1);
            if (aVar.g()) {
                bvVar.a(aVar.b);
            }
        }

        public void b(bp bpVar, a aVar) throws aw {
            bv bvVar = (bv) bpVar;
            aVar.a = bvVar.z();
            aVar.a(true);
            aVar.c = bvVar.z();
            aVar.c(true);
            aVar.d = bvVar.x();
            aVar.d(true);
            if (bvVar.b(1).get(0)) {
                aVar.b = bvVar.z();
                aVar.b(true);
            }
        }
    }
}
