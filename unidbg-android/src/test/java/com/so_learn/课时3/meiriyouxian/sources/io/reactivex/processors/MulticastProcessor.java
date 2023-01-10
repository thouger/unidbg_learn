package io.reactivex.processors;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.a.e;
import io.reactivex.internal.a.h;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class MulticastProcessor<T> extends a<T> {
    static final MulticastSubscription[] n = new MulticastSubscription[0];
    static final MulticastSubscription[] o = new MulticastSubscription[0];
    final AtomicInteger b;
    final AtomicReference<d> c;
    final AtomicReference<MulticastSubscription<T>[]> d;
    final AtomicBoolean e;
    final int f;
    final int g;
    final boolean h;
    volatile h<T> i;
    volatile boolean j;
    volatile Throwable k;
    int l;
    int m;

    @Override // io.reactivex.j
    public void onSubscribe(d dVar) {
        if (SubscriptionHelper.setOnce(this.c, dVar)) {
            if (dVar instanceof e) {
                e eVar = (e) dVar;
                int requestFusion = eVar.requestFusion(3);
                if (requestFusion == 1) {
                    this.m = requestFusion;
                    this.i = eVar;
                    this.j = true;
                    h();
                    return;
                } else if (requestFusion == 2) {
                    this.m = requestFusion;
                    this.i = eVar;
                    dVar.request((long) this.f);
                    return;
                }
            }
            this.i = new SpscArrayQueue(this.f);
            dVar.request((long) this.f);
        }
    }

    public void onNext(T t) {
        if (!this.e.get()) {
            if (this.m == 0) {
                a.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
                if (!this.i.offer(t)) {
                    SubscriptionHelper.cancel(this.c);
                    onError(new MissingBackpressureException());
                    return;
                }
            }
            h();
        }
    }

    public void onError(Throwable th) {
        a.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.e.compareAndSet(false, true)) {
            this.k = th;
            this.j = true;
            h();
            return;
        }
        io.reactivex.e.a.a(th);
    }

    public void onComplete() {
        if (this.e.compareAndSet(false, true)) {
            this.j = true;
            h();
        }
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        Throwable th;
        MulticastSubscription multicastSubscription = new MulticastSubscription(cVar, this);
        cVar.onSubscribe(multicastSubscription);
        if (a(multicastSubscription)) {
            if (multicastSubscription.get() == Long.MIN_VALUE) {
                b(multicastSubscription);
            } else {
                h();
            }
        } else if ((this.e.get() || !this.h) && (th = this.k) != null) {
            cVar.onError(th);
        } else {
            cVar.onComplete();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(MulticastSubscription<T> multicastSubscription) {
        MulticastSubscription<T>[] multicastSubscriptionArr;
        MulticastSubscription<T>[] multicastSubscriptionArr2;
        do {
            multicastSubscriptionArr = this.d.get();
            if (multicastSubscriptionArr == o) {
                return false;
            }
            int length = multicastSubscriptionArr.length;
            multicastSubscriptionArr2 = new MulticastSubscription[(length + 1)];
            System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr2, 0, length);
            multicastSubscriptionArr2[length] = multicastSubscription;
        } while (!this.d.compareAndSet(multicastSubscriptionArr, multicastSubscriptionArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(MulticastSubscription<T> multicastSubscription) {
        while (true) {
            MulticastSubscription<T>[] multicastSubscriptionArr = this.d.get();
            int length = multicastSubscriptionArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (multicastSubscriptionArr[i2] == multicastSubscription) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length != 1) {
                        MulticastSubscription<T>[] multicastSubscriptionArr2 = new MulticastSubscription[(length - 1)];
                        System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr2, 0, i);
                        System.arraycopy(multicastSubscriptionArr, i + 1, multicastSubscriptionArr2, i, (length - i) - 1);
                        if (this.d.compareAndSet(multicastSubscriptionArr, multicastSubscriptionArr2)) {
                            return;
                        }
                    } else if (this.h) {
                        if (this.d.compareAndSet(multicastSubscriptionArr, o)) {
                            SubscriptionHelper.cancel(this.c);
                            this.e.set(true);
                            return;
                        }
                    } else if (this.d.compareAndSet(multicastSubscriptionArr, n)) {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void h() {
        int i;
        boolean z;
        Object obj;
        if (this.b.getAndIncrement() == 0) {
            AtomicReference<MulticastSubscription<T>[]> atomicReference = this.d;
            int i2 = this.l;
            int i3 = this.g;
            int i4 = this.m;
            int i5 = 1;
            while (true) {
                h<T> hVar = this.i;
                if (hVar != null) {
                    MulticastSubscription<T>[] multicastSubscriptionArr = atomicReference.get();
                    if (multicastSubscriptionArr.length != 0) {
                        int length = multicastSubscriptionArr.length;
                        long j = -1;
                        long j2 = -1;
                        int i6 = 0;
                        while (i6 < length) {
                            MulticastSubscription<T> multicastSubscription = multicastSubscriptionArr[i6];
                            long j3 = multicastSubscription.get();
                            if (j3 >= 0) {
                                if (j2 == j) {
                                    j2 = j3 - multicastSubscription.emitted;
                                } else {
                                    j2 = Math.min(j2, j3 - multicastSubscription.emitted);
                                }
                            }
                            i6++;
                            j = -1;
                        }
                        int i7 = i2;
                        while (true) {
                            i = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
                            if (i <= 0) {
                                break;
                            }
                            MulticastSubscription<T>[] multicastSubscriptionArr2 = atomicReference.get();
                            if (multicastSubscriptionArr2 == o) {
                                hVar.clear();
                                return;
                            } else if (multicastSubscriptionArr != multicastSubscriptionArr2) {
                                break;
                            } else {
                                try {
                                    z = this.j;
                                    obj = hVar.poll();
                                } catch (Throwable th) {
                                    io.reactivex.exceptions.a.b(th);
                                    SubscriptionHelper.cancel(this.c);
                                    obj = null;
                                    this.k = th;
                                    this.j = true;
                                    z = true;
                                }
                                boolean z2 = obj == null;
                                if (z && z2) {
                                    Throwable th2 = this.k;
                                    if (th2 != null) {
                                        for (MulticastSubscription<T> multicastSubscription2 : atomicReference.getAndSet(o)) {
                                            multicastSubscription2.onError(th2);
                                        }
                                        return;
                                    }
                                    for (MulticastSubscription<T> multicastSubscription3 : atomicReference.getAndSet(o)) {
                                        multicastSubscription3.onComplete();
                                    }
                                    return;
                                } else if (z2) {
                                    break;
                                } else {
                                    for (MulticastSubscription<T> multicastSubscription4 : multicastSubscriptionArr) {
                                        multicastSubscription4.onNext(obj);
                                    }
                                    j2--;
                                    if (i4 != 1) {
                                        int i8 = i7 + 1;
                                        if (i8 == i3) {
                                            this.c.get().request((long) i3);
                                            i7 = 0;
                                        } else {
                                            i7 = i8;
                                        }
                                    }
                                }
                            }
                        }
                        if (i == 0) {
                            MulticastSubscription<T>[] multicastSubscriptionArr3 = atomicReference.get();
                            if (multicastSubscriptionArr3 == o) {
                                hVar.clear();
                                return;
                            } else if (multicastSubscriptionArr != multicastSubscriptionArr3) {
                                i2 = i7;
                            } else if (this.j && hVar.isEmpty()) {
                                Throwable th3 = this.k;
                                if (th3 != null) {
                                    for (MulticastSubscription<T> multicastSubscription5 : atomicReference.getAndSet(o)) {
                                        multicastSubscription5.onError(th3);
                                    }
                                    return;
                                }
                                for (MulticastSubscription<T> multicastSubscription6 : atomicReference.getAndSet(o)) {
                                    multicastSubscription6.onComplete();
                                }
                                return;
                            }
                        }
                        i2 = i7;
                    }
                }
                i5 = this.b.addAndGet(-i5);
                if (i5 == 0) {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class MulticastSubscription<T> extends AtomicLong implements d {
        private static final long serialVersionUID = -363282618957264509L;
        final c<? super T> downstream;
        long emitted;
        final MulticastProcessor<T> parent;

        MulticastSubscription(c<? super T> cVar, MulticastProcessor<T> multicastProcessor) {
            this.downstream = cVar;
            this.parent = multicastProcessor;
        }

        public void request(long j) {
            long j2;
            long j3;
            if (SubscriptionHelper.validate(j)) {
                do {
                    j2 = get();
                    if (j2 != Long.MIN_VALUE) {
                        j3 = Long.MAX_VALUE;
                        if (j2 != Long.MAX_VALUE) {
                            long j4 = j2 + j;
                            if (j4 >= 0) {
                                j3 = j4;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(j2, j3));
                this.parent.h();
            }
        }

        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.b(this);
            }
        }

        /* access modifiers changed from: package-private */
        public void onNext(T t) {
            if (get() != Long.MIN_VALUE) {
                this.emitted++;
                this.downstream.onNext(t);
            }
        }

        /* access modifiers changed from: package-private */
        public void onError(Throwable th) {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onError(th);
            }
        }

        /* access modifiers changed from: package-private */
        public void onComplete() {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onComplete();
            }
        }
    }
}
