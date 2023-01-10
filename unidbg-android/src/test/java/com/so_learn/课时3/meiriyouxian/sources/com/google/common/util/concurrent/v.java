package com.google.common.util.concurrent;

import com.google.common.base.m;
import java.lang.Thread;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: ThreadFactoryBuilder */
public final class v {
    private String a = null;
    private Boolean b = null;
    private Integer c = null;
    private Thread.UncaughtExceptionHandler d = null;
    private ThreadFactory e = null;

    public v a(String str) {
        b(str, 0);
        this.a = str;
        return this;
    }

    public v a(int i) {
        boolean z = false;
        m.a(i >= 1, "Thread priority (%s) must be >= %s", i, 1);
        if (i <= 10) {
            z = true;
        }
        m.a(z, "Thread priority (%s) must be <= %s", i, 10);
        this.c = Integer.valueOf(i);
        return this;
    }

    public ThreadFactory a() {
        return a(this);
    }

    private static ThreadFactory a(v vVar) {
        String str = vVar.a;
        Boolean bool = vVar.b;
        Integer num = vVar.c;
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = vVar.d;
        ThreadFactory threadFactory = vVar.e;
        if (threadFactory == null) {
            threadFactory = Executors.defaultThreadFactory();
        }
        return new AnonymousClass1(threadFactory, str, str != null ? new AtomicLong(0) : null, bool, num, uncaughtExceptionHandler);
    }

    /* compiled from: ThreadFactoryBuilder */
    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.util.concurrent.v$1  reason: invalid class name */
    public static class AnonymousClass1 implements ThreadFactory {
        final /* synthetic */ ThreadFactory a;
        final /* synthetic */ String b;
        final /* synthetic */ AtomicLong c;
        final /* synthetic */ Boolean d;
        final /* synthetic */ Integer e;
        final /* synthetic */ Thread.UncaughtExceptionHandler f;

        AnonymousClass1(ThreadFactory threadFactory, String str, AtomicLong atomicLong, Boolean bool, Integer num, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.a = threadFactory;
            this.b = str;
            this.c = atomicLong;
            this.d = bool;
            this.e = num;
            this.f = uncaughtExceptionHandler;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread newThread = this.a.newThread(runnable);
            String str = this.b;
            if (str != null) {
                newThread.setName(v.b(str, Long.valueOf(this.c.getAndIncrement())));
            }
            Boolean bool = this.d;
            if (bool != null) {
                newThread.setDaemon(bool.booleanValue());
            }
            Integer num = this.e;
            if (num != null) {
                newThread.setPriority(num.intValue());
            }
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f;
            if (uncaughtExceptionHandler != null) {
                newThread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
            }
            return newThread;
        }
    }

    /* access modifiers changed from: private */
    public static String b(String str, Object... objArr) {
        return String.format(Locale.ROOT, str, objArr);
    }
}
