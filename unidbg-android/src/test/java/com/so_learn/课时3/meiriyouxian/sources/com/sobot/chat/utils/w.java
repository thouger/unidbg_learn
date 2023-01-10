package com.sobot.chat.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: SobotExecutorService */
public class w {
    private static ExecutorService a;

    public static ExecutorService a() {
        if (a == null) {
            synchronized (w.class) {
                if (a == null) {
                    a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), a("sobot_Thread", false));
                }
            }
        }
        return a;
    }

    /* compiled from: SobotExecutorService */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.utils.w$1  reason: invalid class name */
    public static class AnonymousClass1 implements ThreadFactory {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        AnonymousClass1(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.a);
            thread.setDaemon(this.b);
            return thread;
        }
    }

    public static ThreadFactory a(String str, boolean z) {
        return new AnonymousClass1(str, z);
    }
}
