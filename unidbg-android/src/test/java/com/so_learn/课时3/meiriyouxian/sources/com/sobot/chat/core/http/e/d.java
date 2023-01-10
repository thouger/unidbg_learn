package com.sobot.chat.core.http.e;

import android.os.Handler;
import android.os.Looper;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: XExecutor */
public class d extends ThreadPoolExecutor {
    private Handler a = new Handler(Looper.getMainLooper());
    private List<b> b;
    private List<a> c;

    /* compiled from: XExecutor */
    public interface a {
        void a();
    }

    /* compiled from: XExecutor */
    public interface b {
        void a(Runnable runnable);
    }

    public d(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory, rejectedExecutionHandler);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.ThreadPoolExecutor
    public void afterExecute(Runnable runnable, Throwable th) {
        List<a> list;
        super.afterExecute(runnable, th);
        List<b> list2 = this.b;
        if (list2 != null && list2.size() > 0) {
            for (b bVar : this.b) {
                this.a.post(new AnonymousClass1(bVar, runnable));
            }
        }
        if (getActiveCount() == 1 && getQueue().size() == 0 && (list = this.c) != null && list.size() > 0) {
            for (a aVar : this.c) {
                this.a.post(new AnonymousClass2(aVar));
            }
        }
    }

    /* compiled from: XExecutor */
    /* renamed from: com.sobot.chat.core.http.e.d$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ b a;
        final /* synthetic */ Runnable b;

        AnonymousClass1(b bVar, Runnable runnable) {
            this.a = bVar;
            this.b = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b);
        }
    }

    /* compiled from: XExecutor */
    /* renamed from: com.sobot.chat.core.http.e.d$2  reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ a a;

        AnonymousClass2(a aVar) {
            this.a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a();
        }
    }
}
