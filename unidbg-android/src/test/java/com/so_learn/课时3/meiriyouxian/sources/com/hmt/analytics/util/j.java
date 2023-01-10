package com.hmt.analytics.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: ThreadPoolUtils */
public class j {
    private static ExecutorService a = Executors.newSingleThreadExecutor();
    private static ExecutorService b = Executors.newFixedThreadPool(5);

    public static ExecutorService a() {
        return a;
    }

    public static ExecutorService b() {
        return b;
    }
}
