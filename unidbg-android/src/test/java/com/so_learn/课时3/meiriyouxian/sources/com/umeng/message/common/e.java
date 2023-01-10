package com.umeng.message.common;

import com.umeng.commonsdk.debug.UMLog;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: UmengThreadPoolExecutorFactory */
public class e {
    private static final String a = e.class.getName();
    private static volatile ScheduledThreadPoolExecutor b;
    private static final ThreadFactory c = new AnonymousClass1();

    private static ScheduledThreadPoolExecutor a() {
        if (b == null) {
            synchronized (e.class) {
                if (b == null) {
                    b = new ScheduledThreadPoolExecutor(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors(), 4)), c);
                }
            }
        }
        return b;
    }

    public static void a(Runnable runnable) {
        try {
            a().execute(runnable);
        } catch (Throwable unused) {
            UMLog.mutlInfo(a, 0, "UmengThreadPoolExecutorFactory execute exception");
        }
    }

    public static void a(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            a().schedule(runnable, j, timeUnit);
        } catch (Throwable unused) {
            UMLog.mutlInfo(a, 0, "UmengThreadPoolExecutorFactory schedule exception");
        }
    }

    /* compiled from: UmengThreadPoolExecutorFactory */
    /* renamed from: com.umeng.message.common.e$1  reason: invalid class name */
    static class AnonymousClass1 implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(0);

        AnonymousClass1() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("UMThreadPoolExecutor" + this.a.addAndGet(1));
            return thread;
        }
    }
}
