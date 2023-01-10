package com.sobot.chat.core.http.e;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: PriorityBlockingQueue */
public class a<E> extends AbstractQueue<E> implements Serializable, BlockingQueue<E> {
    private static final long b = -6903933977591709194L;
    transient a<E>.b a;
    private final int c;
    private final AtomicInteger d;
    private transient a<E>.b e;
    private final ReentrantLock f;
    private final Condition g;
    private final ReentrantLock h;
    private final Condition i;

    private void c() {
        ReentrantLock reentrantLock = this.f;
        reentrantLock.lock();
        try {
            this.g.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    private void d() {
        ReentrantLock reentrantLock = this.h;
        reentrantLock.lock();
        try {
            this.i.signal();
        } finally {
            reentrantLock.unlock();
        }
    }

    private synchronized E a(a<E>.b bVar) {
        if (bVar == null) {
            return (E) e();
        }
        b(bVar);
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.sobot.chat.core.http.e.a<E>$b<E> */
    /* JADX WARN: Multi-variable type inference failed */
    private void b(a<E>.b bVar) {
        boolean z;
        b bVar2 = this.a;
        while (true) {
            if (bVar2.a == null) {
                z = false;
                break;
            }
            a<E>.b bVar3 = bVar2.a;
            if (bVar3.a() < bVar.a()) {
                bVar2.a = bVar;
                bVar.a = bVar3;
                z = true;
                break;
            }
            bVar2 = bVar2.a;
        }
        if (!z) {
            this.e.a = bVar;
            this.e = bVar;
        }
    }

    private E e() {
        b bVar = (a<E>.b) this.a;
        a<E>.b bVar2 = (a<E>.b) bVar.a;
        bVar.a = bVar;
        this.a = bVar2;
        E e = (E) bVar2.b();
        bVar2.a(null);
        return e;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.h.lock();
        this.f.lock();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.f.unlock();
        this.h.unlock();
    }

    public a() {
        this(Integer.MAX_VALUE);
    }

    public a(int i) {
        this.d = new AtomicInteger();
        this.f = new ReentrantLock();
        this.g = this.f.newCondition();
        this.h = new ReentrantLock();
        this.i = this.h.newCondition();
        if (i > 0) {
            this.c = i;
            a<E>.b bVar = new b<>(null);
            this.a = bVar;
            this.e = bVar;
            return;
        }
        throw new IllegalArgumentException();
    }

    public a(Collection<? extends E> collection) {
        this(Integer.MAX_VALUE);
        ReentrantLock reentrantLock = this.h;
        reentrantLock.lock();
        int i = 0;
        try {
            for (Object obj : collection) {
                if (obj == null) {
                    throw new NullPointerException();
                } else if (i != this.c) {
                    a(new b(obj));
                    i++;
                } else {
                    throw new IllegalStateException("Queue full");
                }
            }
            this.d.set(i);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.d.get();
    }

    @Override // java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return this.c - this.d.get();
    }

    @Override // java.util.concurrent.BlockingQueue
    public void put(E e) throws InterruptedException {
        if (e != null) {
            b bVar = new b(e);
            ReentrantLock reentrantLock = this.h;
            AtomicInteger atomicInteger = this.d;
            reentrantLock.lockInterruptibly();
            while (atomicInteger.get() == this.c) {
                try {
                    this.i.await();
                } finally {
                    reentrantLock.unlock();
                }
            }
            a(bVar);
            int andIncrement = atomicInteger.getAndIncrement();
            if (andIncrement + 1 < this.c) {
                this.i.signal();
            }
            if (andIncrement == 0) {
                c();
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.BlockingQueue
    public boolean offer(E e, long j, TimeUnit timeUnit) throws InterruptedException {
        if (e != null) {
            long nanos = timeUnit.toNanos(j);
            ReentrantLock reentrantLock = this.h;
            AtomicInteger atomicInteger = this.d;
            reentrantLock.lockInterruptibly();
            while (atomicInteger.get() == this.c) {
                try {
                    if (nanos <= 0) {
                        return false;
                    }
                    nanos = this.i.awaitNanos(nanos);
                } finally {
                    reentrantLock.unlock();
                }
            }
            a(new b(e));
            int andIncrement = atomicInteger.getAndIncrement();
            if (andIncrement + 1 < this.c) {
                this.i.signal();
            }
            reentrantLock.unlock();
            if (andIncrement != 0) {
                return true;
            }
            c();
            return true;
        }
        throw new NullPointerException();
    }

    @Override // java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean offer(E e) {
        if (e != null) {
            AtomicInteger atomicInteger = this.d;
            if (atomicInteger.get() == this.c) {
                return false;
            }
            int i = -1;
            b bVar = new b(e);
            ReentrantLock reentrantLock = this.h;
            reentrantLock.lock();
            try {
                if (atomicInteger.get() < this.c) {
                    a(bVar);
                    i = atomicInteger.getAndIncrement();
                    if (i + 1 < this.c) {
                        this.i.signal();
                    }
                }
                if (i == 0) {
                    c();
                }
                if (i >= 0) {
                    return true;
                }
                return false;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new NullPointerException();
        }
    }

    /* JADX INFO: finally extract failed */
    @Override // java.util.concurrent.BlockingQueue
    public E take() throws InterruptedException {
        AtomicInteger atomicInteger = this.d;
        ReentrantLock reentrantLock = this.f;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == 0) {
            try {
                this.g.await();
            } catch (Throwable th) {
                reentrantLock.unlock();
                throw th;
            }
        }
        E e = (E) a((b<Object>) null);
        int andDecrement = atomicInteger.getAndDecrement();
        if (andDecrement > 1) {
            this.g.signal();
        }
        reentrantLock.unlock();
        if (andDecrement == this.c) {
            d();
        }
        return e;
    }

    @Override // java.util.concurrent.BlockingQueue
    public E poll(long j, TimeUnit timeUnit) throws InterruptedException {
        long nanos = timeUnit.toNanos(j);
        AtomicInteger atomicInteger = this.d;
        ReentrantLock reentrantLock = this.f;
        reentrantLock.lockInterruptibly();
        while (atomicInteger.get() == 0) {
            try {
                if (nanos <= 0) {
                    return null;
                }
                nanos = this.g.awaitNanos(nanos);
            } finally {
                reentrantLock.unlock();
            }
        }
        E e = (E) a((b<Object>) null);
        int andDecrement = atomicInteger.getAndDecrement();
        if (andDecrement > 1) {
            this.g.signal();
        }
        reentrantLock.unlock();
        if (andDecrement == this.c) {
            d();
        }
        return e;
    }

    /* JADX INFO: finally extract failed */
    @Override // java.util.Queue
    public E poll() {
        AtomicInteger atomicInteger = this.d;
        E e = null;
        if (atomicInteger.get() == 0) {
            return null;
        }
        int i = -1;
        ReentrantLock reentrantLock = this.f;
        reentrantLock.lock();
        try {
            if (atomicInteger.get() > 0) {
                e = (E) a((b<Object>) null);
                i = atomicInteger.getAndDecrement();
                if (i > 1) {
                    this.g.signal();
                }
            }
            reentrantLock.unlock();
            if (i == this.c) {
                d();
            }
            return e;
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // java.util.Queue
    public E peek() {
        if (this.d.get() == 0) {
            return null;
        }
        ReentrantLock reentrantLock = this.f;
        reentrantLock.lock();
        try {
            a<E>.b bVar = this.a.a;
            if (bVar == 0) {
                return null;
            }
            E e = (E) bVar.b();
            reentrantLock.unlock();
            return e;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(a<E>.b bVar, a<E>.b bVar2) {
        bVar.a(null);
        bVar2.a = bVar.a;
        if (this.e == bVar) {
            this.e = bVar2;
        }
        if (this.d.getAndDecrement() == this.c) {
            this.i.signal();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r2v0. Raw type applied. Possible types: com.sobot.chat.core.http.e.a<E>$b<T> */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean remove(Object obj) {
        if (obj == null) {
            return false;
        }
        a();
        try {
            b bVar = this.a;
            do {
                bVar = bVar.a;
                if (bVar == null) {
                    b();
                    return false;
                }
            } while (!obj.equals(bVar.b()));
            a(bVar, bVar);
            return true;
        } finally {
            b();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingQueue
    public boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        a();
        try {
            b bVar = this.a;
            do {
                bVar = bVar.a;
                if (bVar == null) {
                    b();
                    return false;
                }
            } while (!obj.equals(bVar.b()));
            return true;
        } finally {
            b();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        a();
        try {
            Object[] objArr = new Object[this.d.get()];
            int i = 0;
            b bVar = this.a.a;
            while (bVar != null) {
                int i2 = i + 1;
                objArr[i] = bVar.b();
                bVar = bVar.a;
                i = i2;
            }
            return objArr;
        } finally {
            b();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v11, resolved type: T[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        a();
        try {
            int i = this.d.get();
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            int i2 = 0;
            a<E>.b bVar = this.a.a;
            while (bVar != null) {
                tArr[i2] = bVar.b();
                bVar = bVar.a;
                i2++;
            }
            if (tArr.length > i2) {
                tArr[i2] = null;
            }
            return tArr;
        } finally {
            b();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v2. Raw type applied. Possible types: com.sobot.chat.core.http.e.a$b, com.sobot.chat.core.http.e.a<E>$b<T> */
    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        a();
        try {
            b bVar = (a<E>.b) this.a;
            while (true) {
                b bVar2 = bVar.a;
                if (bVar2 == null) {
                    break;
                }
                bVar.a = bVar;
                bVar2.a(null);
                bVar = (a<E>.b) bVar2;
            }
            this.a = this.e;
            if (this.d.getAndSet(0) == this.c) {
                this.i.signal();
            }
        } finally {
            b();
        }
    }

    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection) {
        return drainTo(collection, Integer.MAX_VALUE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r6v0. Raw type applied. Possible types: ? super E */
    /* JADX DEBUG: Type inference failed for r3v7. Raw type applied. Possible types: com.sobot.chat.core.http.e.a<E>$b<T> */
    @Override // java.util.concurrent.BlockingQueue
    public int drainTo(Collection<? super E> collection, int i) {
        if (collection == null) {
            throw new NullPointerException();
        } else if (collection != this) {
            boolean z = false;
            if (i <= 0) {
                return 0;
            }
            ReentrantLock reentrantLock = this.f;
            reentrantLock.lock();
            try {
                int min = Math.min(i, this.d.get());
                b bVar = (a<E>.b) this.a;
                int i2 = 0;
                while (i2 < min) {
                    try {
                        b bVar2 = bVar.a;
                        collection.add((Object) bVar2.b());
                        bVar2.a(null);
                        bVar.a = (a<E>.b) bVar;
                        i2++;
                        bVar = (a<E>.b) bVar2;
                    } catch (Throwable th) {
                        if (i2 > 0) {
                            this.a = (a<E>.b) bVar;
                            if (this.d.getAndAdd(-i2) == this.c) {
                            }
                        }
                        throw th;
                    }
                }
                if (i2 > 0) {
                    this.a = (a<E>.b) bVar;
                    if (this.d.getAndAdd(-i2) == this.c) {
                        z = true;
                    }
                }
                return min;
            } finally {
                reentrantLock.unlock();
                if (z) {
                    d();
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new C0144a();
    }

    /* compiled from: PriorityBlockingQueue */
    /* renamed from: com.sobot.chat.core.http.e.a$a  reason: collision with other inner class name */
    private class C0144a implements Iterator<E> {
        private a<E>.b b;
        private a<E>.b c;
        private E d;

        C0144a() {
            a.this.a();
            try {
                this.b = (a<E>.b) a.this.a.a;
                if (this.b != null) {
                    this.d = (E) this.b.b();
                }
            } finally {
                a.this.b();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.b != null;
        }

        private a<E>.b a(a<E>.b bVar) {
            a<E>.b bVar2;
            while (true) {
                bVar2 = (a<E>.b) bVar.a;
                if (bVar2 == bVar) {
                    return (a<E>.b) a.this.a.a;
                }
                if (bVar2 == null || bVar2.b() != null) {
                    break;
                }
                bVar = bVar2;
            }
            return bVar2;
        }

        @Override // java.util.Iterator
        public E next() {
            a.this.a();
            try {
                if (this.b != null) {
                    E e = this.d;
                    this.c = this.b;
                    this.b = a(this.b);
                    this.d = this.b == null ? null : (E) this.b.b();
                    return e;
                }
                throw new NoSuchElementException();
            } finally {
                a.this.b();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX DEBUG: Type inference failed for r2v0. Raw type applied. Possible types: com.sobot.chat.core.http.e.a<E>$b<T> */
        @Override // java.util.Iterator
        public void remove() {
            if (this.c != null) {
                a.this.a();
                try {
                    b bVar = this.c;
                    this.c = null;
                    b bVar2 = a.this.a;
                    while (true) {
                        bVar2 = bVar2.a;
                        if (bVar2 != null) {
                            if (bVar2 == bVar) {
                                a.this.a(bVar2, bVar2);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                } finally {
                    a.this.b();
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        a();
        try {
            objectOutputStream.defaultWriteObject();
            b bVar = this.a;
            while (true) {
                bVar = bVar.a;
                if (bVar != null) {
                    objectOutputStream.writeObject(bVar.b());
                } else {
                    objectOutputStream.writeObject(null);
                    return;
                }
            }
        } finally {
            b();
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.d.set(0);
        a<E>.b bVar = new b<>(null);
        this.a = bVar;
        this.e = bVar;
        while (true) {
            Object readObject = objectInputStream.readObject();
            if (readObject != null) {
                add(readObject);
            } else {
                return;
            }
        }
    }

    /* compiled from: PriorityBlockingQueue */
    /* access modifiers changed from: package-private */
    public class b<T> {
        a<E>.b a;
        private boolean c = false;
        private b<?> d;

        b(T t) {
            a(t);
        }

        public int a() {
            return this.d.a;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [T, E] */
        public T b() {
            T t = (T) this.d;
            if (t == null) {
                return null;
            }
            if (this.c) {
                return t;
            }
            return t.b;
        }

        public void a(T t) {
            if (t == null) {
                this.d = null;
            } else if (t instanceof b) {
                this.d = t;
                this.c = true;
            } else {
                this.d = new b<>(0, t);
            }
        }
    }
}
