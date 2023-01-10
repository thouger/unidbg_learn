package io.reactivex.internal.util;

import io.reactivex.c.e;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.a;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import org.a.c;
import org.a.d;

/* compiled from: QueueDrainHelper */
public final class h {
    public static <T> io.reactivex.internal.a.h<T> a(int i) {
        if (i < 0) {
            return new a(-i);
        }
        return new SpscArrayQueue(i);
    }

    public static void a(d dVar, int i) {
        dVar.request(i < 0 ? Long.MAX_VALUE : (long) i);
    }

    public static <T> boolean a(long j, c<? super T> cVar, Queue<T> queue, AtomicLong atomicLong, e eVar) {
        long j2;
        do {
            j2 = atomicLong.get();
        } while (!atomicLong.compareAndSet(j2, b.a(Long.MAX_VALUE & j2, j) | (j2 & Long.MIN_VALUE)));
        if (j2 != Long.MIN_VALUE) {
            return false;
        }
        b(j | Long.MIN_VALUE, cVar, queue, atomicLong, eVar);
        return true;
    }

    static boolean a(e eVar) {
        try {
            return eVar.getAsBoolean();
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            return true;
        }
    }

    static <T> boolean b(long j, c<? super T> cVar, Queue<T> queue, AtomicLong atomicLong, e eVar) {
        long j2 = j & Long.MIN_VALUE;
        while (true) {
            if (j2 != j) {
                if (a(eVar)) {
                    return true;
                }
                T poll = queue.poll();
                if (poll == null) {
                    cVar.onComplete();
                    return true;
                }
                cVar.onNext(poll);
                j2++;
            } else if (a(eVar)) {
                return true;
            } else {
                if (queue.isEmpty()) {
                    cVar.onComplete();
                    return true;
                }
                j = atomicLong.get();
                if (j == j2) {
                    long addAndGet = atomicLong.addAndGet(-(j2 & Long.MAX_VALUE));
                    if ((Long.MAX_VALUE & addAndGet) == 0) {
                        return false;
                    }
                    j = addAndGet;
                    j2 = addAndGet & Long.MIN_VALUE;
                } else {
                    continue;
                }
            }
        }
    }

    public static <T> void a(c<? super T> cVar, Queue<T> queue, AtomicLong atomicLong, e eVar) {
        long j;
        long j2;
        if (queue.isEmpty()) {
            cVar.onComplete();
        } else if (!b(atomicLong.get(), cVar, queue, atomicLong, eVar)) {
            do {
                j = atomicLong.get();
                if ((j & Long.MIN_VALUE) == 0) {
                    j2 = j | Long.MIN_VALUE;
                } else {
                    return;
                }
            } while (!atomicLong.compareAndSet(j, j2));
            if (j != 0) {
                b(j2, cVar, queue, atomicLong, eVar);
            }
        }
    }
}
