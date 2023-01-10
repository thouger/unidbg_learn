package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.primitives.a;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class MutableClassToInstanceMap<B> extends v<Class<? extends B>, B> implements m<B>, Serializable {
    private final Map<Class<? extends B>, B> delegate;

    @Override // com.google.common.collect.v, java.util.Map
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return put((Class<? extends Class>) ((Class) obj), (Class) obj2);
    }

    public static <B> MutableClassToInstanceMap<B> create() {
        return new MutableClassToInstanceMap<>(new HashMap());
    }

    public static <B> MutableClassToInstanceMap<B> create(Map<Class<? extends B>, B> map) {
        return new MutableClassToInstanceMap<>(map);
    }

    private MutableClassToInstanceMap(Map<Class<? extends B>, B> map) {
        this.delegate = (Map) m.a(map);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.collect.v, com.google.common.collect.z
    public Map<Class<? extends B>, B> delegate() {
        return this.delegate;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.MutableClassToInstanceMap$1  reason: invalid class name */
    public static class AnonymousClass1 extends w<Class<? extends B>, B> {
        final /* synthetic */ Map.Entry a;

        AnonymousClass1(Map.Entry entry) {
            this.a = entry;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.w
        /* renamed from: a */
        public Map.Entry<Class<? extends B>, B> delegate() {
            return this.a;
        }

        @Override // com.google.common.collect.w, java.util.Map.Entry
        public B setValue(B b) {
            return (B) super.setValue(MutableClassToInstanceMap.cast(getKey(), b));
        }
    }

    static <B> Map.Entry<Class<? extends B>, B> checkedEntry(Map.Entry<Class<? extends B>, B> entry) {
        return new AnonymousClass1(entry);
    }

    /* renamed from: com.google.common.collect.MutableClassToInstanceMap$2  reason: invalid class name */
    class AnonymousClass2 extends ab<Map.Entry<Class<? extends B>, B>> {
        AnonymousClass2() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.collect.ab, com.google.common.collect.s, com.google.common.collect.z
        public Set<Map.Entry<Class<? extends B>, B>> delegate() {
            return MutableClassToInstanceMap.this.delegate().entrySet();
        }

        /* renamed from: com.google.common.collect.MutableClassToInstanceMap$2$1  reason: invalid class name */
        class AnonymousClass1 extends bc<Map.Entry<Class<? extends B>, B>, Map.Entry<Class<? extends B>, B>> {
            AnonymousClass1(Iterator it2) {
                super(it2);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.collect.bc
            public /* bridge */ /* synthetic */ Object a(Object obj) {
                return a((Map.Entry) ((Map.Entry) obj));
            }

            /* access modifiers changed from: package-private */
            public Map.Entry<Class<? extends B>, B> a(Map.Entry<Class<? extends B>, B> entry) {
                return MutableClassToInstanceMap.checkedEntry(entry);
            }
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<Class<? extends B>, B>> iterator() {
            return new AnonymousClass1(delegate().iterator());
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.s, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }
    }

    @Override // com.google.common.collect.v, java.util.Map
    public Set<Map.Entry<Class<? extends B>, B>> entrySet() {
        return new AnonymousClass2();
    }

    public B put(Class<? extends B> cls, B b) {
        return (B) super.put((MutableClassToInstanceMap<B>) cls, (Class<? extends B>) cast(cls, b));
    }

    @Override // com.google.common.collect.v, java.util.Map
    public void putAll(Map<? extends Class<? extends B>, ? extends B> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            cast((Class) entry.getKey(), entry.getValue());
        }
        super.putAll(linkedHashMap);
    }

    public <T extends B> T putInstance(Class<T> cls, T t) {
        return (T) cast(cls, put((Class<? extends Class<T>>) cls, (Class<T>) t));
    }

    public <T extends B> T getInstance(Class<T> cls) {
        return (T) cast(cls, get(cls));
    }

    /* access modifiers changed from: private */
    public static <B, T extends B> T cast(Class<T> cls, B b) {
        return (T) a.a(cls).cast(b);
    }

    private Object writeReplace() {
        return new SerializedForm(delegate());
    }

    private static final class SerializedForm<B> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Map<Class<? extends B>, B> backingMap;

        SerializedForm(Map<Class<? extends B>, B> map) {
            this.backingMap = map;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return MutableClassToInstanceMap.create(this.backingMap);
        }
    }
}
