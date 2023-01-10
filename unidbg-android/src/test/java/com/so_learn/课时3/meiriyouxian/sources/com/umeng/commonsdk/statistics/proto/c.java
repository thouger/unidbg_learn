package com.umeng.commonsdk.statistics.proto;

import com.umeng.analytics.pro.aq;
import com.umeng.analytics.pro.aw;
import com.umeng.analytics.pro.ax;
import com.umeng.analytics.pro.bc;
import com.umeng.analytics.pro.bd;
import com.umeng.analytics.pro.be;
import com.umeng.analytics.pro.bf;
import com.umeng.analytics.pro.bh;
import com.umeng.analytics.pro.bj;
import com.umeng.analytics.pro.bk;
import com.umeng.analytics.pro.bl;
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
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: IdTracking */
public class c implements aq<c, e>, Serializable, Cloneable {
    public static final Map<e, bc> d;
    private static final long e = -5764118265293965743L;
    private static final bu f = new bu("IdTracking");
    private static final bk g = new bk("snapshots", (byte) 13, 1);
    private static final bk h = new bk("journals", (byte) 15, 2);
    private static final bk i = new bk("checksum", (byte) 11, 3);
    private static final Map<Class<? extends bx>, by> j = new HashMap();
    public Map<String, b> a;
    public List<a> b;
    public String c;
    private e[] k;

    static {
        j.put(bz.class, new b());
        j.put(ca.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.SNAPSHOTS, (e) new bc("snapshots", (byte) 1, new bf((byte) 13, new bd((byte) 11), new bh((byte) 12, b.class))));
        enumMap.put((EnumMap) e.JOURNALS, (e) new bc("journals", (byte) 2, new be((byte) 15, new bh((byte) 12, a.class))));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new bc("checksum", (byte) 2, new bd((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        bc.a(c.class, d);
    }

    /* compiled from: IdTracking */
    public enum e implements ax {
        SNAPSHOTS(1, "snapshots"),
        JOURNALS(2, "journals"),
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
                return SNAPSHOTS;
            }
            if (i == 2) {
                return JOURNALS;
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

    public c() {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
    }

    public c(Map<String, b> map) {
        this();
        this.a = map;
    }

    public c(c cVar) {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
        if (cVar.e()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, b> entry : cVar.a.entrySet()) {
                hashMap.put(entry.getKey(), new b(entry.getValue()));
            }
            this.a = hashMap;
        }
        if (cVar.j()) {
            ArrayList arrayList = new ArrayList();
            for (a aVar : cVar.b) {
                arrayList.add(new a(aVar));
            }
            this.b = arrayList;
        }
        if (cVar.m()) {
            this.c = cVar.c;
        }
    }

    /* renamed from: a */
    public c deepCopy() {
        return new c(this);
    }

    @Override // com.umeng.analytics.pro.aq
    public void clear() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public int b() {
        Map<String, b> map = this.a;
        if (map == null) {
            return 0;
        }
        return map.size();
    }

    public void a(String str, b bVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, bVar);
    }

    public Map<String, b> c() {
        return this.a;
    }

    public c a(Map<String, b> map) {
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
        List<a> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Iterator<a> g() {
        List<a> list = this.b;
        if (list == null) {
            return null;
        }
        return list.iterator();
    }

    public void a(a aVar) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(aVar);
    }

    public List<a> h() {
        return this.b;
    }

    public c a(List<a> list) {
        this.b = list;
        return this;
    }

    public void i() {
        this.b = null;
    }

    public boolean j() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public String k() {
        return this.c;
    }

    public c a(String str) {
        this.c = str;
        return this;
    }

    public void l() {
        this.c = null;
    }

    public boolean m() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    /* renamed from: a */
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
        StringBuilder sb = new StringBuilder("IdTracking(");
        sb.append("snapshots:");
        Map<String, b> map = this.a;
        if (map == null) {
            sb.append("null");
        } else {
            sb.append(map);
        }
        if (j()) {
            sb.append(", ");
            sb.append("journals:");
            List<a> list = this.b;
            if (list == null) {
                sb.append("null");
            } else {
                sb.append(list);
            }
        }
        if (m()) {
            sb.append(", ");
            sb.append("checksum:");
            String str = this.c;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
        }
        sb.append(l.t);
        return sb.toString();
    }

    public void n() throws aw {
        if (this.a == null) {
            throw new bq("Required field 'snapshots' was not present! Struct: " + toString());
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
            read(new bj(new cb(objectInputStream)));
        } catch (aw e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdTracking */
    private static class b implements by {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* compiled from: IdTracking */
    /* access modifiers changed from: private */
    public static class a extends bz<c> {
        private a() {
        }

        /* renamed from: a */
        public void b(bp bpVar, c cVar) throws aw {
            bpVar.j();
            while (true) {
                bk l = bpVar.l();
                if (l.b == 0) {
                    bpVar.k();
                    cVar.n();
                    return;
                }
                short s = l.c;
                int i = 0;
                if (s != 1) {
                    if (s != 2) {
                        if (s != 3) {
                            bs.a(bpVar, l.b);
                        } else if (l.b == 11) {
                            cVar.c = bpVar.z();
                            cVar.c(true);
                        } else {
                            bs.a(bpVar, l.b);
                        }
                    } else if (l.b == 15) {
                        bl p = bpVar.p();
                        cVar.b = new ArrayList(p.b);
                        while (i < p.b) {
                            a aVar = new a();
                            aVar.read(bpVar);
                            cVar.b.add(aVar);
                            i++;
                        }
                        bpVar.q();
                        cVar.b(true);
                    } else {
                        bs.a(bpVar, l.b);
                    }
                } else if (l.b == 13) {
                    bm n = bpVar.n();
                    cVar.a = new HashMap(n.c * 2);
                    while (i < n.c) {
                        String z = bpVar.z();
                        b bVar = new b();
                        bVar.read(bpVar);
                        cVar.a.put(z, bVar);
                        i++;
                    }
                    bpVar.o();
                    cVar.a(true);
                } else {
                    bs.a(bpVar, l.b);
                }
                bpVar.m();
            }
        }

        /* renamed from: b */
        public void a(bp bpVar, c cVar) throws aw {
            cVar.n();
            bpVar.a(c.f);
            if (cVar.a != null) {
                bpVar.a(c.g);
                bpVar.a(new bm((byte) 11, (byte) 12, cVar.a.size()));
                for (Map.Entry<String, b> entry : cVar.a.entrySet()) {
                    bpVar.a(entry.getKey());
                    entry.getValue().write(bpVar);
                }
                bpVar.e();
                bpVar.c();
            }
            if (cVar.b != null && cVar.j()) {
                bpVar.a(c.h);
                bpVar.a(new bl((byte) 12, cVar.b.size()));
                for (a aVar : cVar.b) {
                    aVar.write(bpVar);
                }
                bpVar.f();
                bpVar.c();
            }
            if (cVar.c != null && cVar.m()) {
                bpVar.a(c.i);
                bpVar.a(cVar.c);
                bpVar.c();
            }
            bpVar.d();
            bpVar.b();
        }
    }

    /* compiled from: IdTracking */
    private static class d implements by {
        private d() {
        }

        /* renamed from: a */
        public C0187c b() {
            return new C0187c();
        }
    }

    /* compiled from: IdTracking */
    /* access modifiers changed from: private */
    /* renamed from: com.umeng.commonsdk.statistics.proto.c$c  reason: collision with other inner class name */
    public static class C0187c extends ca<c> {
        private C0187c() {
        }

        public void a(bp bpVar, c cVar) throws aw {
            bv bvVar = (bv) bpVar;
            bvVar.a(cVar.a.size());
            for (Map.Entry<String, b> entry : cVar.a.entrySet()) {
                bvVar.a(entry.getKey());
                entry.getValue().write(bvVar);
            }
            BitSet bitSet = new BitSet();
            if (cVar.j()) {
                bitSet.set(0);
            }
            if (cVar.m()) {
                bitSet.set(1);
            }
            bvVar.a(bitSet, 2);
            if (cVar.j()) {
                bvVar.a(cVar.b.size());
                for (a aVar : cVar.b) {
                    aVar.write(bvVar);
                }
            }
            if (cVar.m()) {
                bvVar.a(cVar.c);
            }
        }

        public void b(bp bpVar, c cVar) throws aw {
            bv bvVar = (bv) bpVar;
            bm bmVar = new bm((byte) 11, (byte) 12, bvVar.w());
            cVar.a = new HashMap(bmVar.c * 2);
            for (int i = 0; i < bmVar.c; i++) {
                String z = bvVar.z();
                b bVar = new b();
                bVar.read(bvVar);
                cVar.a.put(z, bVar);
            }
            cVar.a(true);
            BitSet b = bvVar.b(2);
            if (b.get(0)) {
                bl blVar = new bl((byte) 12, bvVar.w());
                cVar.b = new ArrayList(blVar.b);
                for (int i2 = 0; i2 < blVar.b; i2++) {
                    a aVar = new a();
                    aVar.read(bvVar);
                    cVar.b.add(aVar);
                }
                cVar.b(true);
            }
            if (b.get(1)) {
                cVar.c = bvVar.z();
                cVar.c(true);
            }
        }
    }
}
