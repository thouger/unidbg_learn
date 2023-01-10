package com.umeng.commonsdk.statistics.proto;

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

/* compiled from: ImprintValue */
public class e implements aq<e, EnumC0189e>, Serializable, Cloneable {
    public static final Map<EnumC0189e, bc> d;
    private static final long e = 7501688097813630241L;
    private static final bu f = new bu("ImprintValue");
    private static final bk g = new bk("value", (byte) 11, 1);
    private static final bk h = new bk("ts", (byte) 10, 2);
    private static final bk i = new bk("guid", (byte) 11, 3);
    private static final Map<Class<? extends bx>, by> j = new HashMap();
    private static final int k = 0;
    public String a;
    public long b;
    public String c;
    private byte l;
    private EnumC0189e[] m;

    static {
        j.put(bz.class, new b());
        j.put(ca.class, new d());
        EnumMap enumMap = new EnumMap(EnumC0189e.class);
        enumMap.put((EnumMap) EnumC0189e.VALUE, (EnumC0189e) new bc("value", (byte) 2, new bd((byte) 11)));
        enumMap.put((EnumMap) EnumC0189e.TS, (EnumC0189e) new bc("ts", (byte) 1, new bd((byte) 10)));
        enumMap.put((EnumMap) EnumC0189e.GUID, (EnumC0189e) new bc("guid", (byte) 1, new bd((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        bc.a(e.class, d);
    }

    /* compiled from: ImprintValue */
    /* renamed from: com.umeng.commonsdk.statistics.proto.e$e  reason: collision with other inner class name */
    public enum EnumC0189e implements ax {
        VALUE(1, "value"),
        TS(2, "ts"),
        GUID(3, "guid");
        
        private static final Map<String, EnumC0189e> d = new HashMap();
        private final short e;
        private final String f;

        static {
            Iterator it2 = EnumSet.allOf(EnumC0189e.class).iterator();
            while (it2.hasNext()) {
                EnumC0189e eVar = (EnumC0189e) it2.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static EnumC0189e a(int i) {
            if (i == 1) {
                return VALUE;
            }
            if (i == 2) {
                return TS;
            }
            if (i != 3) {
                return null;
            }
            return GUID;
        }

        public static EnumC0189e b(int i) {
            EnumC0189e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static EnumC0189e a(String str) {
            return d.get(str);
        }

        private EnumC0189e(short s, String str) {
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

    public e() {
        this.l = 0;
        this.m = new EnumC0189e[]{EnumC0189e.VALUE};
    }

    public e(long j2, String str) {
        this();
        this.b = j2;
        b(true);
        this.c = str;
    }

    public e(e eVar) {
        this.l = 0;
        this.m = new EnumC0189e[]{EnumC0189e.VALUE};
        this.l = eVar.l;
        if (eVar.d()) {
            this.a = eVar.a;
        }
        this.b = eVar.b;
        if (eVar.j()) {
            this.c = eVar.c;
        }
    }

    /* renamed from: a */
    public e deepCopy() {
        return new e(this);
    }

    @Override // com.umeng.analytics.pro.aq
    public void clear() {
        this.a = null;
        b(false);
        this.b = 0;
        this.c = null;
    }

    public String b() {
        return this.a;
    }

    public e a(String str) {
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

    public e a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public void f() {
        this.l = an.b(this.l, 0);
    }

    public boolean g() {
        return an.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = an.a(this.l, 0, z);
    }

    public String h() {
        return this.c;
    }

    public e b(String str) {
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

    /* renamed from: a */
    public EnumC0189e fieldForId(int i2) {
        return EnumC0189e.a(i2);
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
        boolean z;
        StringBuilder sb = new StringBuilder("ImprintValue(");
        if (d()) {
            sb.append("value:");
            String str = this.a;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("guid:");
        String str2 = this.c;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(l.t);
        return sb.toString();
    }

    public void k() throws aw {
        if (this.c == null) {
            throw new bq("Required field 'guid' was not present! Struct: " + toString());
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

    /* compiled from: ImprintValue */
    private static class b implements by {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* compiled from: ImprintValue */
    /* access modifiers changed from: private */
    public static class a extends bz<e> {
        private a() {
        }

        /* renamed from: a */
        public void b(bp bpVar, e eVar) throws aw {
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
                            eVar.c = bpVar.z();
                            eVar.c(true);
                        } else {
                            bs.a(bpVar, l.b);
                        }
                    } else if (l.b == 10) {
                        eVar.b = bpVar.x();
                        eVar.b(true);
                    } else {
                        bs.a(bpVar, l.b);
                    }
                } else if (l.b == 11) {
                    eVar.a = bpVar.z();
                    eVar.a(true);
                } else {
                    bs.a(bpVar, l.b);
                }
                bpVar.m();
            }
            bpVar.k();
            if (eVar.g()) {
                eVar.k();
                return;
            }
            throw new bq("Required field 'ts' was not found in serialized data! Struct: " + toString());
        }

        /* renamed from: b */
        public void a(bp bpVar, e eVar) throws aw {
            eVar.k();
            bpVar.a(e.f);
            if (eVar.a != null && eVar.d()) {
                bpVar.a(e.g);
                bpVar.a(eVar.a);
                bpVar.c();
            }
            bpVar.a(e.h);
            bpVar.a(eVar.b);
            bpVar.c();
            if (eVar.c != null) {
                bpVar.a(e.i);
                bpVar.a(eVar.c);
                bpVar.c();
            }
            bpVar.d();
            bpVar.b();
        }
    }

    /* compiled from: ImprintValue */
    private static class d implements by {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* compiled from: ImprintValue */
    /* access modifiers changed from: private */
    public static class c extends ca<e> {
        private c() {
        }

        public void a(bp bpVar, e eVar) throws aw {
            bv bvVar = (bv) bpVar;
            bvVar.a(eVar.b);
            bvVar.a(eVar.c);
            BitSet bitSet = new BitSet();
            if (eVar.d()) {
                bitSet.set(0);
            }
            bvVar.a(bitSet, 1);
            if (eVar.d()) {
                bvVar.a(eVar.a);
            }
        }

        public void b(bp bpVar, e eVar) throws aw {
            bv bvVar = (bv) bpVar;
            eVar.b = bvVar.x();
            eVar.b(true);
            eVar.c = bvVar.z();
            eVar.c(true);
            if (bvVar.b(1).get(0)) {
                eVar.a = bvVar.z();
                eVar.a(true);
            }
        }
    }
}
