package com.google.common.util.concurrent;

import com.google.common.base.q;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class Striped<L> {
    private static final q<ReadWriteLock> a = new AnonymousClass1();
    private static final q<ReadWriteLock> b = new AnonymousClass2();

    /* renamed from: com.google.common.util.concurrent.Striped$1  reason: invalid class name */
    static class AnonymousClass1 implements q<ReadWriteLock> {
        AnonymousClass1() {
        }

        /* renamed from: a */
        public ReadWriteLock get() {
            return new ReentrantReadWriteLock();
        }
    }

    /* renamed from: com.google.common.util.concurrent.Striped$2  reason: invalid class name */
    static class AnonymousClass2 implements q<ReadWriteLock> {
        AnonymousClass2() {
        }

        /* renamed from: a */
        public ReadWriteLock get() {
            return new c();
        }
    }

    /* access modifiers changed from: private */
    public static final class c implements ReadWriteLock {
        private final ReadWriteLock a = new ReentrantReadWriteLock();

        c() {
        }

        @Override // java.util.concurrent.locks.ReadWriteLock
        public Lock readLock() {
            return new b(this.a.readLock(), this);
        }

        @Override // java.util.concurrent.locks.ReadWriteLock
        public Lock writeLock() {
            return new b(this.a.writeLock(), this);
        }
    }

    private static final class b extends j {
        private final Lock a;
        private final c b;

        b(Lock lock, c cVar) {
            this.a = lock;
            this.b = cVar;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.j
        public Lock a() {
            return this.a;
        }

        @Override // com.google.common.util.concurrent.j, java.util.concurrent.locks.Lock
        public Condition newCondition() {
            return new a(this.a.newCondition(), this.b);
        }
    }

    private static final class a extends i {
        private final Condition a;
        private final c b;

        a(Condition condition, c cVar) {
            this.a = condition;
            this.b = cVar;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.i
        public Condition a() {
            return this.a;
        }
    }

    private static class PaddedLock extends ReentrantLock {
        long unused1;
        long unused2;
        long unused3;

        PaddedLock() {
            super(false);
        }
    }

    private static class PaddedSemaphore extends Semaphore {
        long unused1;
        long unused2;
        long unused3;

        PaddedSemaphore(int i) {
            super(i, false);
        }
    }
}
