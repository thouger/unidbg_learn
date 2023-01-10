package com.umeng.analytics.pro;

import android.net.wifi.WifiEnterpriseConfig;
import com.umeng.analytics.pro.ax;
import com.umeng.analytics.pro.ba;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: TUnion */
public abstract class ba<T extends ba<?, ?>, F extends ax> implements aq<T, F> {
    private static final Map<Class<? extends bx>, by> c = new HashMap();
    protected Object a;
    protected F b;

    /* access modifiers changed from: protected */
    public abstract F a(short s);

    /* access modifiers changed from: protected */
    public abstract Object a(bp bpVar, bk bkVar) throws aw;

    /* access modifiers changed from: protected */
    public abstract Object a(bp bpVar, short s) throws aw;

    /* access modifiers changed from: protected */
    public abstract void a(bp bpVar) throws aw;

    /* access modifiers changed from: protected */
    public abstract void b(F f, Object obj) throws ClassCastException;

    /* access modifiers changed from: protected */
    public abstract void b(bp bpVar) throws aw;

    /* access modifiers changed from: protected */
    public abstract bk c(F f);

    /* access modifiers changed from: protected */
    public abstract bu d();

    protected ba() {
        this.b = null;
        this.a = null;
    }

    static {
        c.put(bz.class, new b());
        c.put(ca.class, new d());
    }

    protected ba(F f, Object obj) {
        a((ba<T, F>) f, obj);
    }

    protected ba(ba<T, F> baVar) {
        if (baVar.getClass().equals(getClass())) {
            this.b = baVar.b;
            this.a = a(baVar.a);
            return;
        }
        throw new ClassCastException();
    }

    private static Object a(Object obj) {
        if (obj instanceof aq) {
            return ((aq) obj).deepCopy();
        }
        if (obj instanceof ByteBuffer) {
            return ar.d((ByteBuffer) obj);
        }
        if (obj instanceof List) {
            return a((List) obj);
        }
        if (obj instanceof Set) {
            return a((Set) obj);
        }
        return obj instanceof Map ? a((Map<Object, Object>) ((Map) obj)) : obj;
    }

    private static Map a(Map<Object, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            hashMap.put(a(entry.getKey()), a(entry.getValue()));
        }
        return hashMap;
    }

    private static Set a(Set set) {
        HashSet hashSet = new HashSet();
        for (Object obj : set) {
            hashSet.add(a(obj));
        }
        return hashSet;
    }

    private static List a(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Object obj : list) {
            arrayList.add(a(obj));
        }
        return arrayList;
    }

    public F a() {
        return this.b;
    }

    public Object b() {
        return this.a;
    }

    public Object a(F f) {
        if (f == this.b) {
            return b();
        }
        throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.b);
    }

    public Object a(int i) {
        return a((ba<T, F>) a((short) i));
    }

    public boolean c() {
        return this.b != null;
    }

    public boolean b(F f) {
        return this.b == f;
    }

    public boolean b(int i) {
        return b((ba<T, F>) a((short) i));
    }

    @Override // com.umeng.analytics.pro.aq
    public void read(bp bpVar) throws aw {
        c.get(bpVar.D()).b().b(bpVar, this);
    }

    public void a(F f, Object obj) {
        b(f, obj);
        this.b = f;
        this.a = obj;
    }

    public void a(int i, Object obj) {
        a((ba<T, F>) a((short) i), obj);
    }

    @Override // com.umeng.analytics.pro.aq
    public void write(bp bpVar) throws aw {
        c.get(bpVar.D()).b().a(bpVar, this);
    }

    @Override // java.lang.Object
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getClass().getSimpleName());
        sb.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
        if (a() != null) {
            Object b2 = b();
            sb.append(c(a()).a);
            sb.append(":");
            if (b2 instanceof ByteBuffer) {
                ar.a((ByteBuffer) b2, sb);
            } else {
                sb.append(b2.toString());
            }
        }
        sb.append(">");
        return sb.toString();
    }

    @Override // com.umeng.analytics.pro.aq
    public final void clear() {
        this.b = null;
        this.a = null;
    }

    /* compiled from: TUnion */
    private static class b implements by {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* compiled from: TUnion */
    /* access modifiers changed from: private */
    public static class a extends bz<ba> {
        private a() {
        }

        /* renamed from: a */
        public void b(bp bpVar, ba baVar) throws aw {
            baVar.b = null;
            baVar.a = null;
            bpVar.j();
            bk l = bpVar.l();
            baVar.a = baVar.a(bpVar, l);
            if (baVar.a != null) {
                baVar.b = (F) baVar.a(l.c);
            }
            bpVar.m();
            bpVar.l();
            bpVar.k();
        }

        /* renamed from: b */
        public void a(bp bpVar, ba baVar) throws aw {
            if (baVar.a() == null || baVar.b() == null) {
                throw new bq("Cannot write a TUnion with no set value!");
            }
            bpVar.a(baVar.d());
            bpVar.a(baVar.c(baVar.b));
            baVar.a(bpVar);
            bpVar.c();
            bpVar.d();
            bpVar.b();
        }
    }

    /* compiled from: TUnion */
    private static class d implements by {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* compiled from: TUnion */
    /* access modifiers changed from: private */
    public static class c extends ca<ba> {
        private c() {
        }

        /* renamed from: a */
        public void b(bp bpVar, ba baVar) throws aw {
            baVar.b = null;
            baVar.a = null;
            short v = bpVar.v();
            baVar.a = baVar.a(bpVar, v);
            if (baVar.a != null) {
                baVar.b = (F) baVar.a(v);
            }
        }

        /* renamed from: b */
        public void a(bp bpVar, ba baVar) throws aw {
            if (baVar.a() == null || baVar.b() == null) {
                throw new bq("Cannot write a TUnion with no set value!");
            }
            bpVar.a(baVar.b.a());
            baVar.b(bpVar);
        }
    }
}
