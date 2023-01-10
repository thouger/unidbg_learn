package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.base.q;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class MultimapBuilder<K0, V0> {

    private static final class ArrayListSupplier<V> implements q<List<V>>, Serializable {
        private final int expectedValuesPerKey;

        ArrayListSupplier(int i) {
            this.expectedValuesPerKey = n.a(i, "expectedValuesPerKey");
        }

        @Override // com.google.common.base.q
        public List<V> get() {
            return new ArrayList(this.expectedValuesPerKey);
        }
    }

    private enum LinkedListSupplier implements q<List<Object>> {
        INSTANCE;

        public static <V> q<List<V>> instance() {
            return INSTANCE;
        }

        @Override // com.google.common.base.q
        public List<Object> get() {
            return new LinkedList();
        }
    }

    private static final class HashSetSupplier<V> implements q<Set<V>>, Serializable {
        private final int expectedValuesPerKey;

        HashSetSupplier(int i) {
            this.expectedValuesPerKey = n.a(i, "expectedValuesPerKey");
        }

        @Override // com.google.common.base.q
        public Set<V> get() {
            return ao.c(this.expectedValuesPerKey);
        }
    }

    private static final class LinkedHashSetSupplier<V> implements q<Set<V>>, Serializable {
        private final int expectedValuesPerKey;

        LinkedHashSetSupplier(int i) {
            this.expectedValuesPerKey = n.a(i, "expectedValuesPerKey");
        }

        @Override // com.google.common.base.q
        public Set<V> get() {
            return ao.d(this.expectedValuesPerKey);
        }
    }

    private static final class TreeSetSupplier<V> implements q<SortedSet<V>>, Serializable {
        private final Comparator<? super V> comparator;

        TreeSetSupplier(Comparator<? super V> comparator) {
            this.comparator = (Comparator) m.a(comparator);
        }

        @Override // com.google.common.base.q
        public SortedSet<V> get() {
            return new TreeSet(this.comparator);
        }
    }

    private static final class EnumSetSupplier<V extends Enum<V>> implements q<Set<V>>, Serializable {
        private final Class<V> clazz;

        EnumSetSupplier(Class<V> cls) {
            this.clazz = (Class) m.a(cls);
        }

        @Override // com.google.common.base.q
        public Set<V> get() {
            return EnumSet.noneOf(this.clazz);
        }
    }
}
