package io.reactivex.internal.operators.parallel;

import io.reactivex.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.parallel.a;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class ParallelSortedJoin<T> extends g<T> {
    final a<List<T>> b;
    final Comparator<? super T> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        SortedJoinSubscription sortedJoinSubscription = new SortedJoinSubscription(cVar, this.b.a(), this.c);
        cVar.onSubscribe(sortedJoinSubscription);
        this.b.a((c<? super List<T>>[]) sortedJoinSubscription.subscribers);
    }

    /* access modifiers changed from: package-private */
    public static final class SortedJoinSubscription<T> extends AtomicInteger implements d {
        private static final long serialVersionUID = 3481980673745556697L;
        volatile boolean cancelled;
        final Comparator<? super T> comparator;
        final c<? super T> downstream;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final int[] indexes;
        final List<T>[] lists;
        final AtomicInteger remaining = new AtomicInteger();
        final AtomicLong requested = new AtomicLong();
        final SortedJoinInnerSubscriber<T>[] subscribers;

        SortedJoinSubscription(c<? super T> cVar, int i, Comparator<? super T> comparator) {
            this.downstream = cVar;
            this.comparator = comparator;
            SortedJoinInnerSubscriber<T>[] sortedJoinInnerSubscriberArr = new SortedJoinInnerSubscriber[i];
            for (int i2 = 0; i2 < i; i2++) {
                sortedJoinInnerSubscriberArr[i2] = new SortedJoinInnerSubscriber<>(this, i2);
            }
            this.subscribers = sortedJoinInnerSubscriberArr;
            this.lists = new List[i];
            this.indexes = new int[i];
            this.remaining.lazySet(i);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                if (this.remaining.get() == 0) {
                    drain();
                }
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    Arrays.fill(this.lists, (Object) null);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void cancelAll() {
            for (SortedJoinInnerSubscriber<T> sortedJoinInnerSubscriber : this.subscribers) {
                sortedJoinInnerSubscriber.cancel();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerNext(List<T> list, int i) {
            this.lists[i] = list;
            if (this.remaining.decrementAndGet() == 0) {
                drain();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable th) {
            if (this.error.compareAndSet(null, th)) {
                drain();
            } else if (th != this.error.get()) {
                io.reactivex.e.a.a(th);
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a3, code lost:
            if (r13 != 0) goto L_0x00df;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a7, code lost:
            if (r18.cancelled == false) goto L_0x00ad;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a9, code lost:
            java.util.Arrays.fill(r3, (java.lang.Object) null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ac, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ad, code lost:
            r5 = r18.error.get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b5, code lost:
            if (r5 == null) goto L_0x00c1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b7, code lost:
            cancelAll();
            java.util.Arrays.fill(r3, (java.lang.Object) null);
            r2.onError(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c0, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c1, code lost:
            r5 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c2, code lost:
            if (r5 >= r4) goto L_0x00d4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x00cc, code lost:
            if (r0[r5] == r3[r5].size()) goto L_0x00d1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ce, code lost:
            r16 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x00d1, code lost:
            r5 = r5 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x00d4, code lost:
            r16 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x00d6, code lost:
            if (r16 == false) goto L_0x00df;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x00d8, code lost:
            java.util.Arrays.fill(r3, (java.lang.Object) null);
            r2.onComplete();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x00de, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x00e3, code lost:
            if (r11 == 0) goto L_0x00f4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x00ec, code lost:
            if (r7 == Long.MAX_VALUE) goto L_0x00f4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x00ee, code lost:
            r18.requested.addAndGet(-r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x00f4, code lost:
            r5 = get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x00f8, code lost:
            if (r5 != r6) goto L_0x0102;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x00fa, code lost:
            r5 = addAndGet(-r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x00ff, code lost:
            if (r5 != 0) goto L_0x0102;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x0101, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drain() {
            /*
            // Method dump skipped, instructions count: 261
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelSortedJoin.SortedJoinSubscription.drain():void");
        }
    }

    /* access modifiers changed from: package-private */
    public static final class SortedJoinInnerSubscriber<T> extends AtomicReference<d> implements j<List<T>> {
        private static final long serialVersionUID = 6751017204873808094L;
        final int index;
        final SortedJoinSubscription<T> parent;

        public void onComplete() {
        }

        SortedJoinInnerSubscriber(SortedJoinSubscription<T> sortedJoinSubscription, int i) {
            this.parent = sortedJoinSubscription;
            this.index = i;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.setOnce(this, dVar, Long.MAX_VALUE);
        }

        public void onNext(List<T> list) {
            this.parent.innerNext(list, this.index);
        }

        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        /* access modifiers changed from: package-private */
        public void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }
}
