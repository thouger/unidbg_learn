package io.reactivex.processors;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.a;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import org.a.c;
import org.a.d;

public final class BehaviorProcessor<T> extends a<T> {
    static final Object[] c = new Object[0];
    static final BehaviorSubscription[] d = new BehaviorSubscription[0];
    static final BehaviorSubscription[] e = new BehaviorSubscription[0];
    final AtomicReference<BehaviorSubscription<T>[]> b;
    final Lock f;
    final Lock g;
    final AtomicReference<Object> h;
    final AtomicReference<Throwable> i;
    long j;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        BehaviorSubscription behaviorSubscription = new BehaviorSubscription(cVar, this);
        cVar.onSubscribe(behaviorSubscription);
        if (!a(behaviorSubscription)) {
            Throwable th = this.i.get();
            if (th == ExceptionHelper.a) {
                cVar.onComplete();
            } else {
                cVar.onError(th);
            }
        } else if (behaviorSubscription.cancelled) {
            b(behaviorSubscription);
        } else {
            behaviorSubscription.emitFirst();
        }
    }

    @Override // io.reactivex.j
    public void onSubscribe(d dVar) {
        if (this.i.get() != null) {
            dVar.cancel();
        } else {
            dVar.request(Long.MAX_VALUE);
        }
    }

    public void onNext(T t) {
        a.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.i.get() == null) {
            Object next = NotificationLite.next(t);
            c(next);
            for (BehaviorSubscription<T> behaviorSubscription : this.b.get()) {
                behaviorSubscription.emitNext(next, this.j);
            }
        }
    }

    public void onError(Throwable th) {
        a.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.i.compareAndSet(null, th)) {
            io.reactivex.e.a.a(th);
            return;
        }
        Object error = NotificationLite.error(th);
        for (BehaviorSubscription<T> behaviorSubscription : b(error)) {
            behaviorSubscription.emitNext(error, this.j);
        }
    }

    public void onComplete() {
        if (this.i.compareAndSet(null, ExceptionHelper.a)) {
            Object complete = NotificationLite.complete();
            for (BehaviorSubscription<T> behaviorSubscription : b(complete)) {
                behaviorSubscription.emitNext(complete, this.j);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(BehaviorSubscription<T> behaviorSubscription) {
        BehaviorSubscription<T>[] behaviorSubscriptionArr;
        BehaviorSubscription<T>[] behaviorSubscriptionArr2;
        do {
            behaviorSubscriptionArr = this.b.get();
            if (behaviorSubscriptionArr == e) {
                return false;
            }
            int length = behaviorSubscriptionArr.length;
            behaviorSubscriptionArr2 = new BehaviorSubscription[(length + 1)];
            System.arraycopy(behaviorSubscriptionArr, 0, behaviorSubscriptionArr2, 0, length);
            behaviorSubscriptionArr2[length] = behaviorSubscription;
        } while (!this.b.compareAndSet(behaviorSubscriptionArr, behaviorSubscriptionArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(BehaviorSubscription<T> behaviorSubscription) {
        BehaviorSubscription<T>[] behaviorSubscriptionArr;
        BehaviorSubscription<T>[] behaviorSubscriptionArr2;
        do {
            behaviorSubscriptionArr = this.b.get();
            int length = behaviorSubscriptionArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (behaviorSubscriptionArr[i2] == behaviorSubscription) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        behaviorSubscriptionArr2 = d;
                    } else {
                        BehaviorSubscription<T>[] behaviorSubscriptionArr3 = new BehaviorSubscription[(length - 1)];
                        System.arraycopy(behaviorSubscriptionArr, 0, behaviorSubscriptionArr3, 0, i);
                        System.arraycopy(behaviorSubscriptionArr, i + 1, behaviorSubscriptionArr3, i, (length - i) - 1);
                        behaviorSubscriptionArr2 = behaviorSubscriptionArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.b.compareAndSet(behaviorSubscriptionArr, behaviorSubscriptionArr2));
    }

    /* access modifiers changed from: package-private */
    public BehaviorSubscription<T>[] b(Object obj) {
        BehaviorSubscription<T>[] behaviorSubscriptionArr = this.b.get();
        BehaviorSubscription<T>[] behaviorSubscriptionArr2 = e;
        if (!(behaviorSubscriptionArr == behaviorSubscriptionArr2 || (behaviorSubscriptionArr = this.b.getAndSet(behaviorSubscriptionArr2)) == e)) {
            c(obj);
        }
        return behaviorSubscriptionArr;
    }

    /* access modifiers changed from: package-private */
    public void c(Object obj) {
        Lock lock = this.g;
        lock.lock();
        this.j++;
        this.h.lazySet(obj);
        lock.unlock();
    }

    /* access modifiers changed from: package-private */
    public static final class BehaviorSubscription<T> extends AtomicLong implements a.AbstractC0213a<Object>, d {
        private static final long serialVersionUID = 3293175281126227086L;
        volatile boolean cancelled;
        final c<? super T> downstream;
        boolean emitting;
        boolean fastPath;
        long index;
        boolean next;
        io.reactivex.internal.util.a<Object> queue;
        final BehaviorProcessor<T> state;

        BehaviorSubscription(c<? super T> cVar, BehaviorProcessor<T> behaviorProcessor) {
            this.downstream = cVar;
            this.state = behaviorProcessor;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this, j);
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.b((BehaviorSubscription) this);
            }
        }

        /* access modifiers changed from: package-private */
        public void emitFirst() {
            Object obj;
            if (!this.cancelled) {
                synchronized (this) {
                    if (!this.cancelled) {
                        if (!this.next) {
                            BehaviorProcessor<T> behaviorProcessor = this.state;
                            Lock lock = behaviorProcessor.f;
                            lock.lock();
                            this.index = behaviorProcessor.j;
                            obj = behaviorProcessor.h.get();
                            lock.unlock();
                            this.emitting = obj != null;
                            this.next = true;
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
                if (obj != null && !test(obj)) {
                    emitLoop();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void emitNext(Object obj, long j) {
            if (!this.cancelled) {
                if (!this.fastPath) {
                    synchronized (this) {
                        if (!this.cancelled) {
                            if (this.index != j) {
                                if (this.emitting) {
                                    io.reactivex.internal.util.a<Object> aVar = this.queue;
                                    if (aVar == null) {
                                        aVar = new io.reactivex.internal.util.a<>(4);
                                        this.queue = aVar;
                                    }
                                    aVar.a((io.reactivex.internal.util.a<Object>) obj);
                                    return;
                                }
                                this.next = true;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    this.fastPath = true;
                }
                test(obj);
            }
        }

        @Override // io.reactivex.internal.util.a.AbstractC0213a, io.reactivex.c.k
        public boolean test(Object obj) {
            if (this.cancelled) {
                return true;
            }
            if (NotificationLite.isComplete(obj)) {
                this.downstream.onComplete();
                return true;
            } else if (NotificationLite.isError(obj)) {
                this.downstream.onError(NotificationLite.getError(obj));
                return true;
            } else {
                long j = get();
                if (j != 0) {
                    this.downstream.onNext(NotificationLite.getValue(obj));
                    if (j == Long.MAX_VALUE) {
                        return false;
                    }
                    decrementAndGet();
                    return false;
                }
                cancel();
                this.downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public void emitLoop() {
            io.reactivex.internal.util.a<Object> aVar;
            while (!this.cancelled) {
                synchronized (this) {
                    aVar = this.queue;
                    if (aVar == null) {
                        this.emitting = false;
                        return;
                    }
                    this.queue = null;
                }
                aVar.a((a.AbstractC0213a<? super Object>) this);
            }
        }

        public boolean isFull() {
            return get() == 0;
        }
    }
}
