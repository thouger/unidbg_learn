package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.a.e;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowablePublishMulticast<T, R> extends a<T, R> {
    final h<? super g<T>, ? extends org.a.b<? extends R>> c;
    final int d;
    final boolean e;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super R> cVar) {
        a aVar = new a(this.d, this.e);
        try {
            ((org.a.b) io.reactivex.internal.functions.a.a(this.c.apply(aVar), "selector returned a null Publisher")).subscribe(new b(cVar, aVar));
            this.b.a((j) aVar);
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }

    static final class b<R> implements j<R>, d {
        final c<? super R> a;
        final a<?> b;
        d c;

        b(c<? super R> cVar, a<?> aVar) {
            this.a = cVar;
            this.b = aVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.c, dVar)) {
                this.c = dVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(R r) {
            this.a.onNext(r);
        }

        public void onError(Throwable th) {
            this.a.onError(th);
            this.b.dispose();
        }

        public void onComplete() {
            this.a.onComplete();
            this.b.dispose();
        }

        public void request(long j) {
            this.c.request(j);
        }

        public void cancel() {
            this.c.cancel();
            this.b.dispose();
        }
    }

    static final class a<T> extends g<T> implements io.reactivex.disposables.b, j<T> {
        static final MulticastSubscription[] b = new MulticastSubscription[0];
        static final MulticastSubscription[] c = new MulticastSubscription[0];
        final AtomicInteger d = new AtomicInteger();
        final AtomicReference<MulticastSubscription<T>[]> e = new AtomicReference<>(b);
        final int f;
        final int g;
        final boolean h;
        final AtomicReference<d> i = new AtomicReference<>();
        volatile io.reactivex.internal.a.h<T> j;
        int k;
        volatile boolean l;
        Throwable m;
        int n;

        a(int i, boolean z) {
            this.f = i;
            this.g = i - (i >> 2);
            this.h = z;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this.i, dVar)) {
                if (dVar instanceof e) {
                    e eVar = (e) dVar;
                    int requestFusion = eVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.k = requestFusion;
                        this.j = eVar;
                        this.l = true;
                        g();
                        return;
                    } else if (requestFusion == 2) {
                        this.k = requestFusion;
                        this.j = eVar;
                        io.reactivex.internal.util.h.a(dVar, this.f);
                        return;
                    }
                }
                this.j = io.reactivex.internal.util.h.a(this.f);
                io.reactivex.internal.util.h.a(dVar, this.f);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            io.reactivex.internal.a.h<T> hVar;
            SubscriptionHelper.cancel(this.i);
            if (this.d.getAndIncrement() == 0 && (hVar = this.j) != null) {
                hVar.clear();
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.i.get() == SubscriptionHelper.CANCELLED;
        }

        public void onNext(T t) {
            if (!this.l) {
                if (this.k != 0 || this.j.offer(t)) {
                    g();
                    return;
                }
                this.i.get().cancel();
                onError(new MissingBackpressureException());
            }
        }

        public void onError(Throwable th) {
            if (this.l) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.m = th;
            this.l = true;
            g();
        }

        public void onComplete() {
            if (!this.l) {
                this.l = true;
                g();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(MulticastSubscription<T> multicastSubscription) {
            MulticastSubscription<T>[] multicastSubscriptionArr;
            MulticastSubscription<T>[] multicastSubscriptionArr2;
            do {
                multicastSubscriptionArr = this.e.get();
                if (multicastSubscriptionArr == c) {
                    return false;
                }
                int length = multicastSubscriptionArr.length;
                multicastSubscriptionArr2 = new MulticastSubscription[(length + 1)];
                System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr2, 0, length);
                multicastSubscriptionArr2[length] = multicastSubscription;
            } while (!this.e.compareAndSet(multicastSubscriptionArr, multicastSubscriptionArr2));
            return true;
        }

        /* access modifiers changed from: package-private */
        public void b(MulticastSubscription<T> multicastSubscription) {
            MulticastSubscription<T>[] multicastSubscriptionArr;
            MulticastSubscription<T>[] multicastSubscriptionArr2;
            do {
                multicastSubscriptionArr = this.e.get();
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
                        if (length == 1) {
                            multicastSubscriptionArr2 = b;
                        } else {
                            MulticastSubscription<T>[] multicastSubscriptionArr3 = new MulticastSubscription[(length - 1)];
                            System.arraycopy(multicastSubscriptionArr, 0, multicastSubscriptionArr3, 0, i);
                            System.arraycopy(multicastSubscriptionArr, i + 1, multicastSubscriptionArr3, i, (length - i) - 1);
                            multicastSubscriptionArr2 = multicastSubscriptionArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.e.compareAndSet(multicastSubscriptionArr, multicastSubscriptionArr2));
        }

        /* access modifiers changed from: protected */
        @Override // io.reactivex.g
        public void a(c<? super T> cVar) {
            MulticastSubscription multicastSubscription = new MulticastSubscription(cVar, this);
            cVar.onSubscribe(multicastSubscription);
            if (!a(multicastSubscription)) {
                Throwable th = this.m;
                if (th != null) {
                    cVar.onError(th);
                } else {
                    cVar.onComplete();
                }
            } else if (multicastSubscription.isCancelled()) {
                b(multicastSubscription);
            } else {
                g();
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:125:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:126:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:74:0x00fa, code lost:
            if (r7 != 0) goto L_0x012c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:76:0x0100, code lost:
            if (isDisposed() == false) goto L_0x0106;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:77:0x0102, code lost:
            r0.clear();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x0105, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:79:0x0106, code lost:
            r5 = r24.l;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:0x0108, code lost:
            if (r5 == false) goto L_0x0116;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:82:0x010c, code lost:
            if (r24.h != false) goto L_0x0116;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:83:0x010e, code lost:
            r6 = r24.m;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:84:0x0110, code lost:
            if (r6 == null) goto L_0x0116;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:0x0112, code lost:
            a(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:86:0x0115, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:87:0x0116, code lost:
            if (r5 == false) goto L_0x012c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:89:0x011c, code lost:
            if (r0.isEmpty() == false) goto L_0x012c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:90:0x011e, code lost:
            r0 = r24.m;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:91:0x0120, code lost:
            if (r0 == null) goto L_0x0126;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:92:0x0122, code lost:
            a(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:93:0x0126, code lost:
            h();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void g() {
            /*
            // Method dump skipped, instructions count: 328
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowablePublishMulticast.a.g():void");
        }

        /* access modifiers changed from: package-private */
        public void a(Throwable th) {
            MulticastSubscription<T>[] andSet = this.e.getAndSet(c);
            for (MulticastSubscription<T> multicastSubscription : andSet) {
                if (multicastSubscription.get() != Long.MIN_VALUE) {
                    multicastSubscription.downstream.onError(th);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void h() {
            MulticastSubscription<T>[] andSet = this.e.getAndSet(c);
            for (MulticastSubscription<T> multicastSubscription : andSet) {
                if (multicastSubscription.get() != Long.MIN_VALUE) {
                    multicastSubscription.downstream.onComplete();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class MulticastSubscription<T> extends AtomicLong implements d {
        private static final long serialVersionUID = 8664815189257569791L;
        final c<? super T> downstream;
        long emitted;
        final a<T> parent;

        MulticastSubscription(c<? super T> cVar, a<T> aVar) {
            this.downstream = cVar;
            this.parent = aVar;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.b(this, j);
                this.parent.g();
            }
        }

        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.b(this);
                this.parent.g();
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }
    }
}
