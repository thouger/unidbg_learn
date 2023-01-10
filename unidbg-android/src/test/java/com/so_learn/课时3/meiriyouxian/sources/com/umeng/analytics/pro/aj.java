package com.umeng.analytics.pro;

import com.umeng.message.proguard.l;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: UMEnvelope */
public class aj implements aq<aj, e>, Serializable, Cloneable {
    private static final int A = 2;
    private static final int B = 3;
    public static final Map<e, bc> k;
    private static final long l = 420342210744516016L;
    private static final bu m = new bu("UMEnvelope");
    private static final bk n = new bk("version", (byte) 11, 1);
    private static final bk o = new bk("address", (byte) 11, 2);
    private static final bk p = new bk("signature", (byte) 11, 3);
    private static final bk q = new bk("serial_num", (byte) 8, 4);
    private static final bk r = new bk("ts_secs", (byte) 8, 5);
    private static final bk s = new bk("length", (byte) 8, 6);
    private static final bk t = new bk("entity", (byte) 11, 7);
    private static final bk u = new bk("guid", (byte) 11, 8);
    private static final bk v = new bk("checksum", (byte) 11, 9);
    private static final bk w = new bk("codex", (byte) 8, 10);
    private static final Map<Class<? extends bx>, by> x = new HashMap();
    private static final int y = 0;
    private static final int z = 1;
    private byte C;
    private e[] D;
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;

    static {
        x.put(bz.class, new b());
        x.put(ca.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.VERSION, (e) new bc("version", (byte) 1, new bd((byte) 11)));
        enumMap.put((EnumMap) e.ADDRESS, (e) new bc("address", (byte) 1, new bd((byte) 11)));
        enumMap.put((EnumMap) e.SIGNATURE, (e) new bc("signature", (byte) 1, new bd((byte) 11)));
        enumMap.put((EnumMap) e.SERIAL_NUM, (e) new bc("serial_num", (byte) 1, new bd((byte) 8)));
        enumMap.put((EnumMap) e.TS_SECS, (e) new bc("ts_secs", (byte) 1, new bd((byte) 8)));
        enumMap.put((EnumMap) e.LENGTH, (e) new bc("length", (byte) 1, new bd((byte) 8)));
        enumMap.put((EnumMap) e.ENTITY, (e) new bc("entity", (byte) 1, new bd((byte) 11, true)));
        enumMap.put((EnumMap) e.GUID, (e) new bc("guid", (byte) 1, new bd((byte) 11)));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new bc("checksum", (byte) 1, new bd((byte) 11)));
        enumMap.put((EnumMap) e.CODEX, (e) new bc("codex", (byte) 2, new bd((byte) 8)));
        k = Collections.unmodifiableMap(enumMap);
        bc.a(aj.class, k);
    }

    /* compiled from: UMEnvelope */
    public enum e implements ax {
        VERSION(1, "version"),
        ADDRESS(2, "address"),
        SIGNATURE(3, "signature"),
        SERIAL_NUM(4, "serial_num"),
        TS_SECS(5, "ts_secs"),
        LENGTH(6, "length"),
        ENTITY(7, "entity"),
        GUID(8, "guid"),
        CHECKSUM(9, "checksum"),
        CODEX(10, "codex");
        
        private static final Map<String, e> k = new HashMap();
        private final short l;
        private final String m;

        static {
            Iterator it2 = EnumSet.allOf(e.class).iterator();
            while (it2.hasNext()) {
                e eVar = (e) it2.next();
                k.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
                default:
                    return null;
            }
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return k.get(str);
        }

        private e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        @Override // com.umeng.analytics.pro.ax
        public short a() {
            return this.l;
        }

        @Override // com.umeng.analytics.pro.ax
        public String b() {
            return this.m;
        }
    }

    public aj() {
        this.C = 0;
        this.D = new e[]{e.CODEX};
    }

    public aj(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        d(true);
        this.e = i2;
        e(true);
        this.f = i3;
        f(true);
        this.g = byteBuffer;
        this.h = str4;
        this.i = str5;
    }

    public aj(aj ajVar) {
        this.C = 0;
        this.D = new e[]{e.CODEX};
        this.C = ajVar.C;
        if (ajVar.d()) {
            this.a = ajVar.a;
        }
        if (ajVar.g()) {
            this.b = ajVar.b;
        }
        if (ajVar.j()) {
            this.c = ajVar.c;
        }
        this.d = ajVar.d;
        this.e = ajVar.e;
        this.f = ajVar.f;
        if (ajVar.w()) {
            this.g = ar.d(ajVar.g);
        }
        if (ajVar.z()) {
            this.h = ajVar.h;
        }
        if (ajVar.C()) {
            this.i = ajVar.i;
        }
        this.j = ajVar.j;
    }

    /* renamed from: a */
    public aj deepCopy() {
        return new aj(this);
    }

    @Override // com.umeng.analytics.pro.aq
    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
        e(false);
        this.e = 0;
        f(false);
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        j(false);
        this.j = 0;
    }

    public String b() {
        return this.a;
    }

    public aj a(String str) {
        this.a = str;
        return this;
    }

    public void c() {
        this.a = null;
    }

    public boolean d() {
        return this.a != null;
    }

    public void a(boolean z2) {
        if (!z2) {
            this.a = null;
        }
    }

    public String e() {
        return this.b;
    }

    public aj b(String str) {
        this.b = str;
        return this;
    }

    public void f() {
        this.b = null;
    }

    public boolean g() {
        return this.b != null;
    }

    public void b(boolean z2) {
        if (!z2) {
            this.b = null;
        }
    }

    public String h() {
        return this.c;
    }

    public aj c(String str) {
        this.c = str;
        return this;
    }

    public void i() {
        this.c = null;
    }

    public boolean j() {
        return this.c != null;
    }

    public void c(boolean z2) {
        if (!z2) {
            this.c = null;
        }
    }

    public int k() {
        return this.d;
    }

    public aj a(int i) {
        this.d = i;
        d(true);
        return this;
    }

    public void l() {
        this.C = an.b(this.C, 0);
    }

    public boolean m() {
        return an.a(this.C, 0);
    }

    public void d(boolean z2) {
        this.C = an.a(this.C, 0, z2);
    }

    public int n() {
        return this.e;
    }

    public aj b(int i) {
        this.e = i;
        e(true);
        return this;
    }

    public void o() {
        this.C = an.b(this.C, 1);
    }

    public boolean p() {
        return an.a(this.C, 1);
    }

    public void e(boolean z2) {
        this.C = an.a(this.C, 1, z2);
    }

    public int q() {
        return this.f;
    }

    public aj c(int i) {
        this.f = i;
        f(true);
        return this;
    }

    public void r() {
        this.C = an.b(this.C, 2);
    }

    public boolean s() {
        return an.a(this.C, 2);
    }

    public void f(boolean z2) {
        this.C = an.a(this.C, 2, z2);
    }

    public byte[] t() {
        a(ar.c(this.g));
        ByteBuffer byteBuffer = this.g;
        if (byteBuffer == null) {
            return null;
        }
        return byteBuffer.array();
    }

    public ByteBuffer u() {
        return this.g;
    }

    public aj a(byte[] bArr) {
        a(bArr == null ? null : ByteBuffer.wrap(bArr));
        return this;
    }

    public aj a(ByteBuffer byteBuffer) {
        this.g = byteBuffer;
        return this;
    }

    public void v() {
        this.g = null;
    }

    public boolean w() {
        return this.g != null;
    }

    public void g(boolean z2) {
        if (!z2) {
            this.g = null;
        }
    }

    public String x() {
        return this.h;
    }

    public aj d(String str) {
        this.h = str;
        return this;
    }

    public void y() {
        this.h = null;
    }

    public boolean z() {
        return this.h != null;
    }

    public void h(boolean z2) {
        if (!z2) {
            this.h = null;
        }
    }

    public String A() {
        return this.i;
    }

    public aj e(String str) {
        this.i = str;
        return this;
    }

    public void B() {
        this.i = null;
    }

    public boolean C() {
        return this.i != null;
    }

    public void i(boolean z2) {
        if (!z2) {
            this.i = null;
        }
    }

    public int D() {
        return this.j;
    }

    public aj d(int i) {
        this.j = i;
        j(true);
        return this;
    }

    public void E() {
        this.C = an.b(this.C, 3);
    }

    public boolean F() {
        return an.a(this.C, 3);
    }

    public void j(boolean z2) {
        this.C = an.a(this.C, 3, z2);
    }

    /* renamed from: e */
    public e fieldForId(int i) {
        return e.a(i);
    }

    @Override // com.umeng.analytics.pro.aq
    public void read(bp bpVar) throws aw {
        x.get(bpVar.D()).b().b(bpVar, this);
    }

    @Override // com.umeng.analytics.pro.aq
    public void write(bp bpVar) throws aw {
        x.get(bpVar.D()).b().a(bpVar, this);
    }

    @Override // java.lang.Object
    public String toString() {
        StringBuilder sb = new StringBuilder("UMEnvelope(");
        sb.append("version:");
        String str = this.a;
        if (str == null) {
            sb.append("null");
        } else {
            sb.append(str);
        }
        sb.append(", ");
        sb.append("address:");
        String str2 = this.b;
        if (str2 == null) {
            sb.append("null");
        } else {
            sb.append(str2);
        }
        sb.append(", ");
        sb.append("signature:");
        String str3 = this.c;
        if (str3 == null) {
            sb.append("null");
        } else {
            sb.append(str3);
        }
        sb.append(", ");
        sb.append("serial_num:");
        sb.append(this.d);
        sb.append(", ");
        sb.append("ts_secs:");
        sb.append(this.e);
        sb.append(", ");
        sb.append("length:");
        sb.append(this.f);
        sb.append(", ");
        sb.append("entity:");
        ByteBuffer byteBuffer = this.g;
        if (byteBuffer == null) {
            sb.append("null");
        } else {
            ar.a(byteBuffer, sb);
        }
        sb.append(", ");
        sb.append("guid:");
        String str4 = this.h;
        if (str4 == null) {
            sb.append("null");
        } else {
            sb.append(str4);
        }
        sb.append(", ");
        sb.append("checksum:");
        String str5 = this.i;
        if (str5 == null) {
            sb.append("null");
        } else {
            sb.append(str5);
        }
        if (F()) {
            sb.append(", ");
            sb.append("codex:");
            sb.append(this.j);
        }
        sb.append(l.t);
        return sb.toString();
    }

    public void G() throws aw {
        if (this.a == null) {
            throw new bq("Required field 'version' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new bq("Required field 'address' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new bq("Required field 'signature' was not present! Struct: " + toString());
        } else if (this.g == null) {
            throw new bq("Required field 'entity' was not present! Struct: " + toString());
        } else if (this.h == null) {
            throw new bq("Required field 'guid' was not present! Struct: " + toString());
        } else if (this.i == null) {
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
            this.C = 0;
            read(new bj(new cb(objectInputStream)));
        } catch (aw e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: UMEnvelope */
    private static class b implements by {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* compiled from: UMEnvelope */
    /* access modifiers changed from: private */
    public static class a extends bz<aj> {
        private a() {
        }

        /* renamed from: a */
        public void b(bp bpVar, aj ajVar) throws aw {
            bpVar.j();
            while (true) {
                bk l = bpVar.l();
                if (l.b == 0) {
                    bpVar.k();
                    if (!ajVar.m()) {
                        throw new bq("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    } else if (!ajVar.p()) {
                        throw new bq("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    } else if (ajVar.s()) {
                        ajVar.G();
                        return;
                    } else {
                        throw new bq("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                } else {
                    switch (l.c) {
                        case 1:
                            if (l.b != 11) {
                                bs.a(bpVar, l.b);
                                break;
                            } else {
                                ajVar.a = bpVar.z();
                                ajVar.a(true);
                                break;
                            }
                        case 2:
                            if (l.b != 11) {
                                bs.a(bpVar, l.b);
                                break;
                            } else {
                                ajVar.b = bpVar.z();
                                ajVar.b(true);
                                break;
                            }
                        case 3:
                            if (l.b != 11) {
                                bs.a(bpVar, l.b);
                                break;
                            } else {
                                ajVar.c = bpVar.z();
                                ajVar.c(true);
                                break;
                            }
                        case 4:
                            if (l.b != 8) {
                                bs.a(bpVar, l.b);
                                break;
                            } else {
                                ajVar.d = bpVar.w();
                                ajVar.d(true);
                                break;
                            }
                        case 5:
                            if (l.b != 8) {
                                bs.a(bpVar, l.b);
                                break;
                            } else {
                                ajVar.e = bpVar.w();
                                ajVar.e(true);
                                break;
                            }
                        case 6:
                            if (l.b != 8) {
                                bs.a(bpVar, l.b);
                                break;
                            } else {
                                ajVar.f = bpVar.w();
                                ajVar.f(true);
                                break;
                            }
                        case 7:
                            if (l.b != 11) {
                                bs.a(bpVar, l.b);
                                break;
                            } else {
                                ajVar.g = bpVar.A();
                                ajVar.g(true);
                                break;
                            }
                        case 8:
                            if (l.b != 11) {
                                bs.a(bpVar, l.b);
                                break;
                            } else {
                                ajVar.h = bpVar.z();
                                ajVar.h(true);
                                break;
                            }
                        case 9:
                            if (l.b != 11) {
                                bs.a(bpVar, l.b);
                                break;
                            } else {
                                ajVar.i = bpVar.z();
                                ajVar.i(true);
                                break;
                            }
                        case 10:
                            if (l.b != 8) {
                                bs.a(bpVar, l.b);
                                break;
                            } else {
                                ajVar.j = bpVar.w();
                                ajVar.j(true);
                                break;
                            }
                        default:
                            bs.a(bpVar, l.b);
                            break;
                    }
                    bpVar.m();
                }
            }
        }

        /* renamed from: b */
        public void a(bp bpVar, aj ajVar) throws aw {
            ajVar.G();
            bpVar.a(aj.m);
            if (ajVar.a != null) {
                bpVar.a(aj.n);
                bpVar.a(ajVar.a);
                bpVar.c();
            }
            if (ajVar.b != null) {
                bpVar.a(aj.o);
                bpVar.a(ajVar.b);
                bpVar.c();
            }
            if (ajVar.c != null) {
                bpVar.a(aj.p);
                bpVar.a(ajVar.c);
                bpVar.c();
            }
            bpVar.a(aj.q);
            bpVar.a(ajVar.d);
            bpVar.c();
            bpVar.a(aj.r);
            bpVar.a(ajVar.e);
            bpVar.c();
            bpVar.a(aj.s);
            bpVar.a(ajVar.f);
            bpVar.c();
            if (ajVar.g != null) {
                bpVar.a(aj.t);
                bpVar.a(ajVar.g);
                bpVar.c();
            }
            if (ajVar.h != null) {
                bpVar.a(aj.u);
                bpVar.a(ajVar.h);
                bpVar.c();
            }
            if (ajVar.i != null) {
                bpVar.a(aj.v);
                bpVar.a(ajVar.i);
                bpVar.c();
            }
            if (ajVar.F()) {
                bpVar.a(aj.w);
                bpVar.a(ajVar.j);
                bpVar.c();
            }
            bpVar.d();
            bpVar.b();
        }
    }

    /* compiled from: UMEnvelope */
    private static class d implements by {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* compiled from: UMEnvelope */
    /* access modifiers changed from: private */
    public static class c extends ca<aj> {
        private c() {
        }

        public void a(bp bpVar, aj ajVar) throws aw {
            bv bvVar = (bv) bpVar;
            bvVar.a(ajVar.a);
            bvVar.a(ajVar.b);
            bvVar.a(ajVar.c);
            bvVar.a(ajVar.d);
            bvVar.a(ajVar.e);
            bvVar.a(ajVar.f);
            bvVar.a(ajVar.g);
            bvVar.a(ajVar.h);
            bvVar.a(ajVar.i);
            BitSet bitSet = new BitSet();
            if (ajVar.F()) {
                bitSet.set(0);
            }
            bvVar.a(bitSet, 1);
            if (ajVar.F()) {
                bvVar.a(ajVar.j);
            }
        }

        public void b(bp bpVar, aj ajVar) throws aw {
            bv bvVar = (bv) bpVar;
            ajVar.a = bvVar.z();
            ajVar.a(true);
            ajVar.b = bvVar.z();
            ajVar.b(true);
            ajVar.c = bvVar.z();
            ajVar.c(true);
            ajVar.d = bvVar.w();
            ajVar.d(true);
            ajVar.e = bvVar.w();
            ajVar.e(true);
            ajVar.f = bvVar.w();
            ajVar.f(true);
            ajVar.g = bvVar.A();
            ajVar.g(true);
            ajVar.h = bvVar.z();
            ajVar.h(true);
            ajVar.i = bvVar.z();
            ajVar.i(true);
            if (bvVar.b(1).get(0)) {
                ajVar.j = bvVar.w();
                ajVar.j(true);
            }
        }
    }
}
