package com.google.common.collect;

import com.google.common.base.j;
import java.util.Map;

/* compiled from: ForwardingMapEntry */
public abstract class w<K, V> extends z implements Map.Entry<K, V> {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Map.Entry<K, V> delegate();

    protected w() {
    }

    @Override // java.util.Map.Entry
    public K getKey() {
        return delegate().getKey();
    }

    @Override // java.util.Map.Entry
    public V getValue() {
        return delegate().getValue();
    }

    @Override // java.util.Map.Entry
    public V setValue(V v) {
        return delegate().setValue(v);
    }

    @Override // java.util.Map.Entry, java.lang.Object
    public boolean equals(Object obj) {
        return delegate().equals(obj);
    }

    @Override // java.util.Map.Entry, java.lang.Object
    public int hashCode() {
        return delegate().hashCode();
    }

    /* access modifiers changed from: protected */
    public boolean a(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (!j.a(getKey(), entry.getKey()) || !j.a(getValue(), entry.getValue())) {
            return false;
        }
        return true;
    }
}
