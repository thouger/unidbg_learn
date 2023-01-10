package com.sina.weibo.sdk.statistic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: WBAgentExecutor */
/* access modifiers changed from: package-private */
public class f {
    private static ExecutorService a = Executors.newSingleThreadExecutor();
    private static long b = 5;

    public static synchronized void a(Runnable runnable) {
        synchronized (f.class) {
            if (a.isShutdown()) {
                a = Executors.newSingleThreadExecutor();
            }
            a.execute(runnable);
        }
    }
}
