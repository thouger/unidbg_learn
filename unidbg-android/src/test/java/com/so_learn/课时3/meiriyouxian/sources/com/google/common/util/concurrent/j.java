package com.google.common.util.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* compiled from: ForwardingLock */
abstract class j implements Lock {
    /* access modifiers changed from: package-private */
    public abstract Lock a();

    j() {
    }

    @Override // java.util.concurrent.locks.Lock
    public void lock() {
        a().lock();
    }

    @Override // java.util.concurrent.locks.Lock
    public void lockInterruptibly() throws InterruptedException {
        a().lockInterruptibly();
    }

    @Override // java.util.concurrent.locks.Lock
    public boolean tryLock() {
        return a().tryLock();
    }

    @Override // java.util.concurrent.locks.Lock
    public boolean tryLock(long j, TimeUnit timeUnit) throws InterruptedException {
        return a().tryLock(j, timeUnit);
    }

    @Override // java.util.concurrent.locks.Lock
    public void unlock() {
        a().unlock();
    }

    @Override // java.util.concurrent.locks.Lock
    public Condition newCondition() {
        return a().newCondition();
    }
}
