package io.reactivex.internal.schedulers;

import com.umeng.message.common.inter.ITagManager;
import io.reactivex.c.h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: SchedulerPoolFactory */
public final class i {
    public static final boolean a;
    public static final int b;
    static final AtomicReference<ScheduledExecutorService> c = new AtomicReference<>();
    static final Map<ScheduledThreadPoolExecutor, Object> d = new ConcurrentHashMap();

    static {
        b bVar = new b();
        a = a(true, "rx2.purge-enabled", true, true, (h<String, String>) bVar);
        b = a(a, "rx2.purge-period-seconds", 1, 1, bVar);
        a();
    }

    public static void a() {
        a(a);
    }

    static void a(boolean z) {
        if (z) {
            while (true) {
                ScheduledExecutorService scheduledExecutorService = c.get();
                if (scheduledExecutorService == null) {
                    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, new RxThreadFactory("RxSchedulerPurge"));
                    if (c.compareAndSet(scheduledExecutorService, newScheduledThreadPool)) {
                        a aVar = new a();
                        int i = b;
                        newScheduledThreadPool.scheduleAtFixedRate(aVar, (long) i, (long) i, TimeUnit.SECONDS);
                        return;
                    }
                    newScheduledThreadPool.shutdownNow();
                } else {
                    return;
                }
            }
        }
    }

    static int a(boolean z, String str, int i, int i2, h<String, String> hVar) {
        if (!z) {
            return i2;
        }
        try {
            String str2 = (String) hVar.apply(str);
            if (str2 == null) {
                return i;
            }
            return Integer.parseInt(str2);
        } catch (Throwable unused) {
            return i;
        }
    }

    static boolean a(boolean z, String str, boolean z2, boolean z3, h<String, String> hVar) {
        if (!z) {
            return z3;
        }
        try {
            String str2 = (String) hVar.apply(str);
            if (str2 == null) {
                return z2;
            }
            return ITagManager.STATUS_TRUE.equals(str2);
        } catch (Throwable unused) {
            return z2;
        }
    }

    /* compiled from: SchedulerPoolFactory */
    static final class b implements h<String, String> {
        b() {
        }

        /* renamed from: a */
        public String apply(String str) throws Exception {
            return System.getProperty(str);
        }
    }

    public static ScheduledExecutorService a(ThreadFactory threadFactory) {
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);
        a(a, newScheduledThreadPool);
        return newScheduledThreadPool;
    }

    static void a(boolean z, ScheduledExecutorService scheduledExecutorService) {
        if (z && (scheduledExecutorService instanceof ScheduledThreadPoolExecutor)) {
            d.put((ScheduledThreadPoolExecutor) scheduledExecutorService, scheduledExecutorService);
        }
    }

    /* compiled from: SchedulerPoolFactory */
    /* access modifiers changed from: package-private */
    public static final class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it2 = new ArrayList(i.d.keySet()).iterator();
            while (it2.hasNext()) {
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) it2.next();
                if (scheduledThreadPoolExecutor.isShutdown()) {
                    i.d.remove(scheduledThreadPoolExecutor);
                } else {
                    scheduledThreadPoolExecutor.purge();
                }
            }
        }
    }
}
