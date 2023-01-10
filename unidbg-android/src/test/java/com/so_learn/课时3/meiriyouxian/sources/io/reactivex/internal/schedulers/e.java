package io.reactivex.internal.schedulers;

import io.reactivex.w;
import java.util.concurrent.ThreadFactory;

/* compiled from: NewThreadScheduler */
public final class e extends w {
    private static final RxThreadFactory c = new RxThreadFactory("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())));
    final ThreadFactory b;

    public e() {
        this(c);
    }

    public e(ThreadFactory threadFactory) {
        this.b = threadFactory;
    }

    @Override // io.reactivex.w
    public w.c a() {
        return new f(this.b);
    }
}
