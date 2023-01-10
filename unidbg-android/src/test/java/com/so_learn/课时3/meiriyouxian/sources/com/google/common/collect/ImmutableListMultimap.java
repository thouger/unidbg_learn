package com.google.common.collect;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.as;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

public class ImmutableListMultimap<K, V> extends ImmutableMultimap<K, V> implements ah<K, V> {
    private static final long serialVersionUID = 0;
    private transient ImmutableListMultimap<V, K> inverse;

    public static <K, V> ImmutableListMultimap<K, V> of() {
        return EmptyImmutableListMultimap.INSTANCE;
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k, V v) {
        a builder = builder();
        builder.b(k, v);
        return builder.b();
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k, V v, K k2, V v2) {
        a builder = builder();
        builder.b(k, v);
        builder.b(k2, v2);
        return builder.b();
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3) {
        a builder = builder();
        builder.b(k, v);
        builder.b(k2, v2);
        builder.b(k3, v3);
        return builder.b();
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        a builder = builder();
        builder.b(k, v);
        builder.b(k2, v2);
        builder.b(k3, v3);
        builder.b(k4, v4);
        return builder.b();
    }

    public static <K, V> ImmutableListMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        a builder = builder();
        builder.b(k, v);
        builder.b(k2, v2);
        builder.b(k3, v3);
        builder.b(k4, v4);
        builder.b(k5, v5);
        return builder.b();
    }

    public static <K, V> a<K, V> builder() {
        return new a<>();
    }

    public static final class a<K, V> extends ImmutableMultimap.a<K, V> {
        /* renamed from: a */
        public a<K, V> b(K k, V v) {
            super.b(k, v);
            return this;
        }

        /* renamed from: a */
        public a<K, V> b(Map.Entry<? extends K, ? extends V> entry) {
            super.b(entry);
            return this;
        }

        /* renamed from: a */
        public a<K, V> b(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            super.b(iterable);
            return this;
        }

        /* renamed from: a */
        public ImmutableListMultimap<K, V> b() {
            return (ImmutableListMultimap) super.b();
        }
    }

    public static <K, V> ImmutableListMultimap<K, V> copyOf(ai<? extends K, ? extends V> aiVar) {
        if (aiVar.isEmpty()) {
            return of();
        }
        if (aiVar instanceof ImmutableListMultimap) {
            ImmutableListMultimap<K, V> immutableListMultimap = (ImmutableListMultimap) aiVar;
            if (!immutableListMultimap.isPartialView()) {
                return immutableListMultimap;
            }
        }
        return fromMapEntries(aiVar.asMap().entrySet(), null);
    }

    public static <K, V> ImmutableListMultimap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        return new a().b(iterable).b();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: com.google.common.collect.ImmutableMap$a */
    /* JADX WARN: Multi-variable type inference failed */
    static <K, V> ImmutableListMultimap<K, V> fromMapEntries(Collection<? extends Map.Entry<? extends K, ? extends Collection<? extends V>>> collection, Comparator<? super V> comparator) {
        ImmutableList immutableList;
        if (collection.isEmpty()) {
            return of();
        }
        ImmutableMap.a aVar = new ImmutableMap.a(collection.size());
        int i = 0;
        Iterator<? extends Map.Entry<? extends K, ? extends Collection<? extends V>>> it2 = collection.iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            Object key = entry.getKey();
            Collection collection2 = (Collection) entry.getValue();
            if (comparator == null) {
                immutableList = ImmutableList.copyOf(collection2);
            } else {
                immutableList = ImmutableList.sortedCopyOf(comparator, collection2);
            }
            if (!immutableList.isEmpty()) {
                aVar.b(key, immutableList);
                i += immutableList.size();
            }
        }
        return new ImmutableListMultimap<>(aVar.b(), i);
    }

    ImmutableListMultimap(ImmutableMap<K, ImmutableList<V>> immutableMap, int i) {
        super(immutableMap, i);
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.ai, com.google.common.collect.ah
    public ImmutableList<V> get(K k) {
        ImmutableList<V> immutableList = (ImmutableList) this.map.get(k);
        return immutableList == null ? ImmutableList.of() : immutableList;
    }

    @Override // com.google.common.collect.ImmutableMultimap
    public ImmutableListMultimap<V, K> inverse() {
        ImmutableListMultimap<V, K> immutableListMultimap = this.inverse;
        if (immutableListMultimap != null) {
            return immutableListMultimap;
        }
        ImmutableListMultimap<V, K> invert = invert();
        this.inverse = invert;
        return invert;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.ImmutableListMultimap<K, V> */
    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: com.google.common.collect.ImmutableListMultimap$a */
    /* JADX WARN: Multi-variable type inference failed */
    private ImmutableListMultimap<V, K> invert() {
        a builder = builder();
        bf it2 = entries().iterator();
        while (it2.hasNext()) {
            Map.Entry next = it2.next();
            builder.b(next.getValue(), next.getKey());
        }
        ImmutableListMultimap<V, K> a2 = builder.b();
        a2.inverse = this;
        return a2;
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.ai, com.google.common.collect.ah
    @Deprecated
    public ImmutableList<V> removeAll(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.c, com.google.common.collect.ai, com.google.common.collect.ah
    @Deprecated
    public ImmutableList<V> replaceValues(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        as.a(this, objectOutputStream);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: com.google.common.collect.ImmutableMap$a */
    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            ImmutableMap.a builder = ImmutableMap.builder();
            int i = 0;
            for (int i2 = 0; i2 < readInt; i2++) {
                Object readObject = objectInputStream.readObject();
                int readInt2 = objectInputStream.readInt();
                if (readInt2 > 0) {
                    ImmutableList.a builder2 = ImmutableList.builder();
                    for (int i3 = 0; i3 < readInt2; i3++) {
                        builder2.b(objectInputStream.readObject());
                    }
                    builder.b(readObject, builder2.a());
                    i += readInt2;
                } else {
                    throw new InvalidObjectException("Invalid value count " + readInt2);
                }
            }
            try {
                ImmutableMultimap.b.a.a((as.a<ImmutableMultimap>) this, (Object) builder.b());
                ImmutableMultimap.b.b.a((as.a<ImmutableMultimap>) this, i);
            } catch (IllegalArgumentException e) {
                throw ((InvalidObjectException) new InvalidObjectException(e.getMessage()).initCause(e));
            }
        } else {
            throw new InvalidObjectException("Invalid key count " + readInt);
        }
    }
}
