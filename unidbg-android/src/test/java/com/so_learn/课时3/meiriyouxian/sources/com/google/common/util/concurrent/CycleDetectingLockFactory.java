package com.google.common.util.concurrent;

import com.google.common.base.m;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CycleDetectingLockFactory {
    private static final ConcurrentMap<Class<? extends Enum>, Map<? extends Enum, b>> b = new MapMaker().d().g();
    private static final Logger c = Logger.getLogger(CycleDetectingLockFactory.class.getName());
    private static final ThreadLocal<ArrayList<b>> d = new AnonymousClass1();
    final c a;

    /* access modifiers changed from: private */
    public interface a {
        b getLockGraphNode();

        boolean isAcquiredByCurrentThread();
    }

    public interface c {
        void handlePotentialDeadlock(PotentialDeadlockException potentialDeadlockException);
    }

    public static final class d<E extends Enum<E>> extends CycleDetectingLockFactory {
    }

    public enum Policies implements c {
        THROW {
            @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.c
            public void handlePotentialDeadlock(PotentialDeadlockException potentialDeadlockException) {
                throw potentialDeadlockException;
            }
        },
        WARN {
            @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.c
            public void handlePotentialDeadlock(PotentialDeadlockException potentialDeadlockException) {
                CycleDetectingLockFactory.c.log(Level.SEVERE, "Detected potential deadlock", (Throwable) potentialDeadlockException);
            }
        },
        DISABLED {
            @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.c
            public void handlePotentialDeadlock(PotentialDeadlockException potentialDeadlockException) {
            }
        };

        /* synthetic */ Policies(AnonymousClass1 r3) {
            this();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.util.concurrent.CycleDetectingLockFactory$1  reason: invalid class name */
    public static class AnonymousClass1 extends ThreadLocal<ArrayList<b>> {
        AnonymousClass1() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public ArrayList<b> initialValue() {
            return Lists.b(3);
        }
    }

    /* access modifiers changed from: private */
    public static class ExampleStackTrace extends IllegalStateException {
        static final StackTraceElement[] EMPTY_STACK_TRACE = new StackTraceElement[0];
        static final ImmutableSet<String> EXCLUDED_CLASS_NAMES = ImmutableSet.of(CycleDetectingLockFactory.class.getName(), ExampleStackTrace.class.getName(), b.class.getName());

        ExampleStackTrace(b bVar, b bVar2) {
            super(bVar.a() + " -> " + bVar2.a());
            StackTraceElement[] stackTrace = getStackTrace();
            int length = stackTrace.length;
            for (int i = 0; i < length; i++) {
                if (d.class.getName().equals(stackTrace[i].getClassName())) {
                    setStackTrace(EMPTY_STACK_TRACE);
                    return;
                } else if (!EXCLUDED_CLASS_NAMES.contains(stackTrace[i].getClassName())) {
                    setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i, length));
                    return;
                }
            }
        }
    }

    public static final class PotentialDeadlockException extends ExampleStackTrace {
        private final ExampleStackTrace conflictingStackTrace;

        /* synthetic */ PotentialDeadlockException(b bVar, b bVar2, ExampleStackTrace exampleStackTrace, AnonymousClass1 r4) {
            this(bVar, bVar2, exampleStackTrace);
        }

        private PotentialDeadlockException(b bVar, b bVar2, ExampleStackTrace exampleStackTrace) {
            super(bVar, bVar2);
            this.conflictingStackTrace = exampleStackTrace;
            initCause(exampleStackTrace);
        }

        public ExampleStackTrace getConflictingStackTrace() {
            return this.conflictingStackTrace;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            StringBuilder sb = new StringBuilder(super.getMessage());
            for (Throwable th = this.conflictingStackTrace; th != null; th = th.getCause()) {
                sb.append(", ");
                sb.append(th.getMessage());
            }
            return sb.toString();
        }
    }

    /* access modifiers changed from: private */
    public static class b {
        final Map<b, ExampleStackTrace> a;
        final Map<b, PotentialDeadlockException> b;
        final String c;

        /* access modifiers changed from: package-private */
        public String a() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public void a(c cVar, List<b> list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                a(cVar, list.get(i));
            }
        }

        /* access modifiers changed from: package-private */
        public void a(c cVar, b bVar) {
            m.b(this != bVar, "Attempted to acquire multiple locks with the same rank %s", bVar.a());
            if (!this.a.containsKey(bVar)) {
                PotentialDeadlockException potentialDeadlockException = this.b.get(bVar);
                if (potentialDeadlockException != null) {
                    cVar.handlePotentialDeadlock(new PotentialDeadlockException(bVar, this, potentialDeadlockException.getConflictingStackTrace(), null));
                    return;
                }
                ExampleStackTrace a = bVar.a(this, Sets.d());
                if (a == null) {
                    this.a.put(bVar, new ExampleStackTrace(bVar, this));
                    return;
                }
                PotentialDeadlockException potentialDeadlockException2 = new PotentialDeadlockException(bVar, this, a, null);
                this.b.put(bVar, potentialDeadlockException2);
                cVar.handlePotentialDeadlock(potentialDeadlockException2);
            }
        }

        private ExampleStackTrace a(b bVar, Set<b> set) {
            if (!set.add(this)) {
                return null;
            }
            ExampleStackTrace exampleStackTrace = this.a.get(bVar);
            if (exampleStackTrace != null) {
                return exampleStackTrace;
            }
            for (Map.Entry<b, ExampleStackTrace> entry : this.a.entrySet()) {
                b key = entry.getKey();
                ExampleStackTrace a = key.a(bVar, set);
                if (a != null) {
                    ExampleStackTrace exampleStackTrace2 = new ExampleStackTrace(key, this);
                    exampleStackTrace2.setStackTrace(entry.getValue().getStackTrace());
                    exampleStackTrace2.initCause(a);
                    return exampleStackTrace2;
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(a aVar) {
        if (!aVar.isAcquiredByCurrentThread()) {
            ArrayList<b> arrayList = d.get();
            b lockGraphNode = aVar.getLockGraphNode();
            lockGraphNode.a(this.a, arrayList);
            arrayList.add(lockGraphNode);
        }
    }

    /* access modifiers changed from: private */
    public static void c(a aVar) {
        if (!aVar.isAcquiredByCurrentThread()) {
            ArrayList<b> arrayList = d.get();
            b lockGraphNode = aVar.getLockGraphNode();
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (arrayList.get(size) == lockGraphNode) {
                    arrayList.remove(size);
                    return;
                }
            }
        }
    }

    final class CycleDetectingReentrantLock extends ReentrantLock implements a {
        private final b lockGraphNode;

        /* synthetic */ CycleDetectingReentrantLock(CycleDetectingLockFactory cycleDetectingLockFactory, b bVar, boolean z, AnonymousClass1 r4) {
            this(bVar, z);
        }

        private CycleDetectingReentrantLock(b bVar, boolean z) {
            super(z);
            this.lockGraphNode = (b) m.a(bVar);
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.a
        public b getLockGraphNode() {
            return this.lockGraphNode;
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.a
        public boolean isAcquiredByCurrentThread() {
            return isHeldByCurrentThread();
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public void lock() {
            CycleDetectingLockFactory.this.b(this);
            try {
                super.lock();
            } finally {
                CycleDetectingLockFactory.c(this);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public void lockInterruptibly() throws InterruptedException {
            CycleDetectingLockFactory.this.b(this);
            try {
                super.lockInterruptibly();
            } finally {
                CycleDetectingLockFactory.c(this);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public boolean tryLock() {
            CycleDetectingLockFactory.this.b(this);
            try {
                return super.tryLock();
            } finally {
                CycleDetectingLockFactory.c(this);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public boolean tryLock(long j, TimeUnit timeUnit) throws InterruptedException {
            CycleDetectingLockFactory.this.b(this);
            try {
                return super.tryLock(j, timeUnit);
            } finally {
                CycleDetectingLockFactory.c(this);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantLock, java.util.concurrent.locks.Lock
        public void unlock() {
            try {
                super.unlock();
            } finally {
                CycleDetectingLockFactory.c(this);
            }
        }
    }

    final class CycleDetectingReentrantReadWriteLock extends ReentrantReadWriteLock implements a {
        private final b lockGraphNode;
        private final CycleDetectingReentrantReadLock readLock;
        private final CycleDetectingReentrantWriteLock writeLock;

        /* synthetic */ CycleDetectingReentrantReadWriteLock(CycleDetectingLockFactory cycleDetectingLockFactory, b bVar, boolean z, AnonymousClass1 r4) {
            this(bVar, z);
        }

        private CycleDetectingReentrantReadWriteLock(b bVar, boolean z) {
            super(z);
            this.readLock = new CycleDetectingReentrantReadLock(this);
            this.writeLock = new CycleDetectingReentrantWriteLock(this);
            this.lockGraphNode = (b) m.a(bVar);
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock, java.util.concurrent.locks.ReadWriteLock
        public ReentrantReadWriteLock.ReadLock readLock() {
            return this.readLock;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock, java.util.concurrent.locks.ReadWriteLock
        public ReentrantReadWriteLock.WriteLock writeLock() {
            return this.writeLock;
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.a
        public b getLockGraphNode() {
            return this.lockGraphNode;
        }

        @Override // com.google.common.util.concurrent.CycleDetectingLockFactory.a
        public boolean isAcquiredByCurrentThread() {
            return isWriteLockedByCurrentThread() || getReadHoldCount() > 0;
        }
    }

    /* access modifiers changed from: private */
    public class CycleDetectingReentrantReadLock extends ReentrantReadWriteLock.ReadLock {
        final CycleDetectingReentrantReadWriteLock readWriteLock;

        CycleDetectingReentrantReadLock(CycleDetectingReentrantReadWriteLock cycleDetectingReentrantReadWriteLock) {
            super(cycleDetectingReentrantReadWriteLock);
            this.readWriteLock = cycleDetectingReentrantReadWriteLock;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public void lock() {
            CycleDetectingLockFactory.this.b(this.readWriteLock);
            try {
                super.lock();
            } finally {
                CycleDetectingLockFactory.c(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public void lockInterruptibly() throws InterruptedException {
            CycleDetectingLockFactory.this.b(this.readWriteLock);
            try {
                super.lockInterruptibly();
            } finally {
                CycleDetectingLockFactory.c(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public boolean tryLock() {
            CycleDetectingLockFactory.this.b(this.readWriteLock);
            try {
                return super.tryLock();
            } finally {
                CycleDetectingLockFactory.c(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public boolean tryLock(long j, TimeUnit timeUnit) throws InterruptedException {
            CycleDetectingLockFactory.this.b(this.readWriteLock);
            try {
                return super.tryLock(j, timeUnit);
            } finally {
                CycleDetectingLockFactory.c(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock, java.util.concurrent.locks.Lock
        public void unlock() {
            try {
                super.unlock();
            } finally {
                CycleDetectingLockFactory.c(this.readWriteLock);
            }
        }
    }

    /* access modifiers changed from: private */
    public class CycleDetectingReentrantWriteLock extends ReentrantReadWriteLock.WriteLock {
        final CycleDetectingReentrantReadWriteLock readWriteLock;

        CycleDetectingReentrantWriteLock(CycleDetectingReentrantReadWriteLock cycleDetectingReentrantReadWriteLock) {
            super(cycleDetectingReentrantReadWriteLock);
            this.readWriteLock = cycleDetectingReentrantReadWriteLock;
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public void lock() {
            CycleDetectingLockFactory.this.b(this.readWriteLock);
            try {
                super.lock();
            } finally {
                CycleDetectingLockFactory.c(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public void lockInterruptibly() throws InterruptedException {
            CycleDetectingLockFactory.this.b(this.readWriteLock);
            try {
                super.lockInterruptibly();
            } finally {
                CycleDetectingLockFactory.c(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public boolean tryLock() {
            CycleDetectingLockFactory.this.b(this.readWriteLock);
            try {
                return super.tryLock();
            } finally {
                CycleDetectingLockFactory.c(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public boolean tryLock(long j, TimeUnit timeUnit) throws InterruptedException {
            CycleDetectingLockFactory.this.b(this.readWriteLock);
            try {
                return super.tryLock(j, timeUnit);
            } finally {
                CycleDetectingLockFactory.c(this.readWriteLock);
            }
        }

        @Override // java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock, java.util.concurrent.locks.Lock
        public void unlock() {
            try {
                super.unlock();
            } finally {
                CycleDetectingLockFactory.c(this.readWriteLock);
            }
        }
    }
}
