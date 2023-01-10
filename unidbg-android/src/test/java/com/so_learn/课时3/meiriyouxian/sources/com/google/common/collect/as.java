package com.google.common.collect;

import com.google.common.collect.aj;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/* compiled from: Serialization */
final class as {
    static int a(ObjectInputStream objectInputStream) throws IOException {
        return objectInputStream.readInt();
    }

    static <K, V> void a(Map<K, V> map, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(map.size());
        for (Map.Entry<K, V> entry : map.entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    static <K, V> void a(Map<K, V> map, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        a(map, objectInputStream, objectInputStream.readInt());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.Map<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    static <K, V> void a(Map<K, V> map, ObjectInputStream objectInputStream, int i) throws IOException, ClassNotFoundException {
        for (int i2 = 0; i2 < i; i2++) {
            map.put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    static <E> void a(aj<E> ajVar, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(ajVar.entrySet().size());
        for (aj.a<E> aVar : ajVar.entrySet()) {
            objectOutputStream.writeObject(aVar.getElement());
            objectOutputStream.writeInt(aVar.getCount());
        }
    }

    static <E> void a(aj<E> ajVar, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        a(ajVar, objectInputStream, objectInputStream.readInt());
    }

    static <E> void a(aj<E> ajVar, ObjectInputStream objectInputStream, int i) throws IOException, ClassNotFoundException {
        for (int i2 = 0; i2 < i; i2++) {
            ajVar.add(objectInputStream.readObject(), objectInputStream.readInt());
        }
    }

    static <K, V> void a(ai<K, V> aiVar, ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(aiVar.asMap().size());
        for (Map.Entry<K, Collection<V>> entry : aiVar.asMap().entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeInt(entry.getValue().size());
            for (V v : entry.getValue()) {
                objectOutputStream.writeObject(v);
            }
        }
    }

    static <K, V> void a(ai<K, V> aiVar, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        a(aiVar, objectInputStream, objectInputStream.readInt());
    }

    static <K, V> void a(ai<K, V> aiVar, ObjectInputStream objectInputStream, int i) throws IOException, ClassNotFoundException {
        for (int i2 = 0; i2 < i; i2++) {
            Collection<V> collection = aiVar.get(objectInputStream.readObject());
            int readInt = objectInputStream.readInt();
            for (int i3 = 0; i3 < readInt; i3++) {
                collection.add((V) objectInputStream.readObject());
            }
        }
    }

    static <T> a<T> a(Class<T> cls, String str) {
        try {
            return new a<>(cls.getDeclaredField(str));
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    /* compiled from: Serialization */
    /* access modifiers changed from: package-private */
    public static final class a<T> {
        private final Field a;

        private a(Field field) {
            this.a = field;
            field.setAccessible(true);
        }

        /* access modifiers changed from: package-private */
        public void a(T t, Object obj) {
            try {
                this.a.set(t, obj);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }

        /* access modifiers changed from: package-private */
        public void a(T t, int i) {
            try {
                this.a.set(t, Integer.valueOf(i));
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }
}
