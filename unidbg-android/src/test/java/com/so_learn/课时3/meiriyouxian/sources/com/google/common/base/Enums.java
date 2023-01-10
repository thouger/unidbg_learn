package com.google.common.base;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public final class Enums {
    private static final Map<Class<? extends Enum<?>>, Map<String, WeakReference<? extends Enum<?>>>> a = new WeakHashMap();

    private static final class StringConverter<T extends Enum<T>> extends Converter<String, T> implements Serializable {
        private static final long serialVersionUID = 0;
        private final Class<T> enumClass;

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public /* bridge */ /* synthetic */ Object doBackward(Object obj) {
            return doBackward((StringConverter<T>) ((Enum) obj));
        }

        StringConverter(Class<T> cls) {
            this.enumClass = (Class) m.a(cls);
        }

        /* access modifiers changed from: protected */
        public T doForward(String str) {
            return (T) Enum.valueOf(this.enumClass, str);
        }

        /* access modifiers changed from: protected */
        public String doBackward(T t) {
            return t.name();
        }

        @Override // com.google.common.base.Converter, com.google.common.base.g, java.lang.Object
        public boolean equals(Object obj) {
            if (obj instanceof StringConverter) {
                return this.enumClass.equals(((StringConverter) obj).enumClass);
            }
            return false;
        }

        @Override // java.lang.Object
        public int hashCode() {
            return this.enumClass.hashCode();
        }

        @Override // java.lang.Object
        public String toString() {
            return "Enums.stringConverter(" + this.enumClass.getName() + ".class)";
        }
    }
}
