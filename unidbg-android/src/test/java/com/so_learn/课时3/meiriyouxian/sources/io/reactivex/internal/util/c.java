package io.reactivex.internal.util;

import io.reactivex.e.a;
import io.reactivex.internal.schedulers.g;

/* compiled from: BlockingHelper */
public final class c {
    public static void a() {
        if (!a.a()) {
            return;
        }
        if ((Thread.currentThread() instanceof g) || a.b()) {
            throw new IllegalStateException("Attempt to block on a Scheduler " + Thread.currentThread().getName() + " that doesn't support blocking operators as they may lead to deadlock");
        }
    }
}
