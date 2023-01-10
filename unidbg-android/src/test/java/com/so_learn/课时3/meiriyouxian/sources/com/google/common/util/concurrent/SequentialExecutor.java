package com.google.common.util.concurrent;

import com.google.common.base.m;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Logger;

/* access modifiers changed from: package-private */
public final class SequentialExecutor implements Executor {
    private static final Logger a = Logger.getLogger(SequentialExecutor.class.getName());
    private final Executor b;
    private final Deque<Runnable> c;
    private WorkerRunningState d;
    private long e;
    private final a f;

    /* access modifiers changed from: package-private */
    public enum WorkerRunningState {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING
    }

    static /* synthetic */ long c(SequentialExecutor sequentialExecutor) {
        long j = sequentialExecutor.e;
        sequentialExecutor.e = 1 + j;
        return j;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        long j;
        AnonymousClass1 r3;
        m.a(runnable);
        synchronized (this.c) {
            if (this.d != WorkerRunningState.RUNNING) {
                if (this.d != WorkerRunningState.QUEUED) {
                    j = this.e;
                    r3 = new AnonymousClass1(runnable);
                    this.c.add(r3);
                    this.d = WorkerRunningState.QUEUING;
                }
            }
            this.c.add(runnable);
            return;
        }
        boolean z = true;
        try {
            this.b.execute(this.f);
            if (this.d == WorkerRunningState.QUEUING) {
                z = false;
            }
            if (!z) {
                synchronized (this.c) {
                    if (this.e == j && this.d == WorkerRunningState.QUEUING) {
                        this.d = WorkerRunningState.QUEUED;
                    }
                }
            }
        } catch (Error | RuntimeException e) {
            synchronized (this.c) {
                if ((this.d != WorkerRunningState.IDLE && this.d != WorkerRunningState.QUEUING) || !this.c.removeLastOccurrence(r3)) {
                    z = false;
                }
                if (!(e instanceof RejectedExecutionException) || z) {
                    throw e;
                }
            }
        }
    }

    /* renamed from: com.google.common.util.concurrent.SequentialExecutor$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ Runnable a;

        AnonymousClass1(Runnable runnable) {
            this.a = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.run();
        }
    }

    private final class a implements Runnable {
        final /* synthetic */ SequentialExecutor a;

        @Override // java.lang.Runnable
        public void run() {
            try {
                a();
            } catch (Error e) {
                synchronized (this.a.c) {
                    this.a.d = WorkerRunningState.IDLE;
                    throw e;
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
            java.lang.Thread.currentThread().interrupt();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
            if (r1 == false) goto L_?;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void a() {
            /*
                r8 = this;
                r0 = 0
                r1 = r0
            L_0x0002:
                com.google.common.util.concurrent.SequentialExecutor r2 = r8.a     // Catch:{ all -> 0x0077 }
                java.util.Deque r2 = com.google.common.util.concurrent.SequentialExecutor.a(r2)     // Catch:{ all -> 0x0077 }
                monitor-enter(r2)     // Catch:{ all -> 0x0077 }
                if (r0 != 0) goto L_0x002d
                com.google.common.util.concurrent.SequentialExecutor r0 = r8.a     // Catch:{ all -> 0x0074 }
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r0 = com.google.common.util.concurrent.SequentialExecutor.b(r0)     // Catch:{ all -> 0x0074 }
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING     // Catch:{ all -> 0x0074 }
                if (r0 != r3) goto L_0x0020
                monitor-exit(r2)     // Catch:{ all -> 0x0074 }
                if (r1 == 0) goto L_0x001f
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x001f:
                return
            L_0x0020:
                com.google.common.util.concurrent.SequentialExecutor r0 = r8.a
                com.google.common.util.concurrent.SequentialExecutor.c(r0)
                com.google.common.util.concurrent.SequentialExecutor r0 = r8.a
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.RUNNING
                com.google.common.util.concurrent.SequentialExecutor.a(r0, r3)
                r0 = 1
            L_0x002d:
                com.google.common.util.concurrent.SequentialExecutor r3 = r8.a
                java.util.Deque r3 = com.google.common.util.concurrent.SequentialExecutor.a(r3)
                java.lang.Object r3 = r3.poll()
                java.lang.Runnable r3 = (java.lang.Runnable) r3
                if (r3 != 0) goto L_0x004d
                com.google.common.util.concurrent.SequentialExecutor r0 = r8.a
                com.google.common.util.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.common.util.concurrent.SequentialExecutor.WorkerRunningState.IDLE
                com.google.common.util.concurrent.SequentialExecutor.a(r0, r3)
                monitor-exit(r2)
                if (r1 == 0) goto L_0x004c
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                r0.interrupt()
            L_0x004c:
                return
            L_0x004d:
                monitor-exit(r2)
                boolean r2 = java.lang.Thread.interrupted()
                r1 = r1 | r2
                r3.run()     // Catch:{ RuntimeException -> 0x0057 }
                goto L_0x0002
            L_0x0057:
                r2 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.SequentialExecutor.a()
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                java.lang.String r7 = "Exception while executing runnable "
                r6.append(r7)
                r6.append(r3)
                java.lang.String r3 = r6.toString()
                r4.log(r5, r3, r2)
                goto L_0x0002
            L_0x0074:
                r0 = move-exception
                monitor-exit(r2)
                throw r0
            L_0x0077:
                r0 = move-exception
                if (r1 == 0) goto L_0x0081
                java.lang.Thread r1 = java.lang.Thread.currentThread()
                r1.interrupt()
            L_0x0081:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SequentialExecutor.a.a():void");
        }
    }
}
