package cn.missfresh.basiclib.thread;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadQueue extends LinkedBlockingQueue<Runnable> {
    public static int MAIN_QUEUE_POOL_SIZE = 300;
    private AtomicInteger maxRuntimePoolSize = new AtomicInteger(0);
    private boolean overflow = false;

    @Override // java.util.concurrent.LinkedBlockingQueue, java.util.concurrent.BlockingQueue, java.util.Queue
    public /* synthetic */ boolean offer(Object obj) {
        AppMethodBeat.i(4187, false);
        boolean offer = offer((Runnable) obj);
        AppMethodBeat.o(4187);
        return offer;
    }

    ThreadQueue() {
        super(MAIN_QUEUE_POOL_SIZE);
        AppMethodBeat.i(4174, false);
        AppMethodBeat.o(4174);
    }

    public boolean offer(Runnable runnable) {
        AppMethodBeat.i(4177, false);
        boolean offer = super.offer((ThreadQueue) runnable);
        int size = size();
        if (this.maxRuntimePoolSize.get() < size) {
            this.maxRuntimePoolSize.set(size);
        }
        AppMethodBeat.o(4177);
        return offer;
    }

    @Override // java.util.concurrent.LinkedBlockingQueue, java.util.concurrent.BlockingQueue
    public Runnable poll(long j, TimeUnit timeUnit) throws InterruptedException {
        AppMethodBeat.i(4179, false);
        this.overflow = true;
        Runnable runnable = (Runnable) super.poll(j, timeUnit);
        AppMethodBeat.o(4179);
        return runnable;
    }

    /* access modifiers changed from: package-private */
    public int getMaxRuntimePoolSize() {
        AppMethodBeat.i(4182, false);
        int i = this.maxRuntimePoolSize.get();
        AppMethodBeat.o(4182);
        return i;
    }

    /* access modifiers changed from: package-private */
    public boolean isOverflow() {
        return this.overflow;
    }
}
