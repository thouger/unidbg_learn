package cn.missfresh.basiclib.thread;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: MFPoolExecutor */
public class a extends ThreadPoolExecutor {
    private ConcurrentLinkedQueue a = new ConcurrentLinkedQueue();
    private String b = "";

    public a(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory, rejectedExecutionHandler);
        AppMethodBeat.i(3930, false);
        AppMethodBeat.o(3930);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.ThreadPoolExecutor
    public void beforeExecute(Thread thread, Runnable runnable) {
        AppMethodBeat.i(3933, false);
        super.beforeExecute(thread, runnable);
        this.a.add(runnable);
        AppMethodBeat.o(3933);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.ThreadPoolExecutor
    public void afterExecute(Runnable runnable, Throwable th) {
        AppMethodBeat.i(3936, false);
        super.afterExecute(runnable, th);
        this.a.remove(runnable);
        AppMethodBeat.o(3936);
    }

    public void a(String str) {
        this.b = str;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.lang.Object
    public String toString() {
        AppMethodBeat.i(4010, false);
        String str = this.b + ":" + super.toString();
        AppMethodBeat.o(4010);
        return str;
    }
}
