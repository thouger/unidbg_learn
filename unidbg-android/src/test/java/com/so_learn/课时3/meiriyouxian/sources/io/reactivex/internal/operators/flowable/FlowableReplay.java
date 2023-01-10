package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.disposables.c;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.j;
import io.reactivex.w;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

public final class FlowableReplay<T> extends io.reactivex.b.a<T> implements c {
    static final Callable f = new a();
    final g<T> b;
    final AtomicReference<ReplaySubscriber<T>> c;
    final Callable<? extends b<T>> d;
    final org.a.b<T> e;

    interface b<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerSubscription<T> innerSubscription);
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(org.a.c<? super T> cVar) {
        this.e.subscribe(cVar);
    }

    @Override // io.reactivex.internal.disposables.c
    public void a(io.reactivex.disposables.b bVar) {
        this.c.compareAndSet((ReplaySubscriber) bVar, null);
    }

    @Override // io.reactivex.b.a
    public void c(io.reactivex.c.g<? super io.reactivex.disposables.b> gVar) {
        ReplaySubscriber<T> replaySubscriber;
        while (true) {
            replaySubscriber = this.c.get();
            if (replaySubscriber != null && !replaySubscriber.isDisposed()) {
                break;
            }
            try {
                ReplaySubscriber<T> replaySubscriber2 = new ReplaySubscriber<>((b) this.d.call());
                if (this.c.compareAndSet(replaySubscriber, replaySubscriber2)) {
                    replaySubscriber = replaySubscriber2;
                    break;
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                throw ExceptionHelper.a(th);
            }
        }
        boolean z = !replaySubscriber.shouldConnect.get() && replaySubscriber.shouldConnect.compareAndSet(false, true);
        try {
            gVar.accept(replaySubscriber);
            if (z) {
                this.b.a((j) replaySubscriber);
            }
        } catch (Throwable th2) {
            if (z) {
                replaySubscriber.shouldConnect.compareAndSet(true, false);
            }
            io.reactivex.exceptions.a.b(th2);
            throw ExceptionHelper.a(th2);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ReplaySubscriber<T> extends AtomicReference<d> implements io.reactivex.disposables.b, j<T> {
        static final InnerSubscription[] EMPTY = new InnerSubscription[0];
        static final InnerSubscription[] TERMINATED = new InnerSubscription[0];
        private static final long serialVersionUID = 7224554242710036740L;
        final b<T> buffer;
        boolean done;
        final AtomicInteger management = new AtomicInteger();
        long maxChildRequested;
        long maxUpstreamRequested;
        final AtomicBoolean shouldConnect = new AtomicBoolean();
        final AtomicReference<InnerSubscription<T>[]> subscribers = new AtomicReference<>(EMPTY);

        ReplaySubscriber(b<T> bVar) {
            this.buffer = bVar;
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.subscribers.set(TERMINATED);
            SubscriptionHelper.cancel(this);
        }

        /* access modifiers changed from: package-private */
        public boolean add(InnerSubscription<T> innerSubscription) {
            InnerSubscription<T>[] innerSubscriptionArr;
            InnerSubscription<T>[] innerSubscriptionArr2;
            if (innerSubscription != null) {
                do {
                    innerSubscriptionArr = this.subscribers.get();
                    if (innerSubscriptionArr == TERMINATED) {
                        return false;
                    }
                    int length = innerSubscriptionArr.length;
                    innerSubscriptionArr2 = new InnerSubscription[(length + 1)];
                    System.arraycopy(innerSubscriptionArr, 0, innerSubscriptionArr2, 0, length);
                    innerSubscriptionArr2[length] = innerSubscription;
                } while (!this.subscribers.compareAndSet(innerSubscriptionArr, innerSubscriptionArr2));
                return true;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        public void remove(InnerSubscription<T> innerSubscription) {
            InnerSubscription<T>[] innerSubscriptionArr;
            InnerSubscription<T>[] innerSubscriptionArr2;
            do {
                innerSubscriptionArr = this.subscribers.get();
                int length = innerSubscriptionArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (innerSubscriptionArr[i2].equals(innerSubscription)) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            innerSubscriptionArr2 = EMPTY;
                        } else {
                            InnerSubscription<T>[] innerSubscriptionArr3 = new InnerSubscription[(length - 1)];
                            System.arraycopy(innerSubscriptionArr, 0, innerSubscriptionArr3, 0, i);
                            System.arraycopy(innerSubscriptionArr, i + 1, innerSubscriptionArr3, i, (length - i) - 1);
                            innerSubscriptionArr2 = innerSubscriptionArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(innerSubscriptionArr, innerSubscriptionArr2));
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this, dVar)) {
                manageRequests();
                for (InnerSubscription<T> innerSubscription : this.subscribers.get()) {
                    this.buffer.replay(innerSubscription);
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                this.buffer.next(t);
                for (InnerSubscription<T> innerSubscription : this.subscribers.get()) {
                    this.buffer.replay(innerSubscription);
                }
            }
        }

        public void onError(Throwable th) {
            if (!this.done) {
                this.done = true;
                this.buffer.error(th);
                for (InnerSubscription<T> innerSubscription : this.subscribers.getAndSet(TERMINATED)) {
                    this.buffer.replay(innerSubscription);
                }
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.buffer.complete();
                for (InnerSubscription<T> innerSubscription : this.subscribers.getAndSet(TERMINATED)) {
                    this.buffer.replay(innerSubscription);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void manageRequests() {
            if (this.management.getAndIncrement() == 0) {
                int i = 1;
                while (!isDisposed()) {
                    InnerSubscription<T>[] innerSubscriptionArr = this.subscribers.get();
                    long j = this.maxChildRequested;
                    long j2 = j;
                    for (InnerSubscription<T> innerSubscription : innerSubscriptionArr) {
                        j2 = Math.max(j2, innerSubscription.totalRequested.get());
                    }
                    long j3 = this.maxUpstreamRequested;
                    d dVar = get();
                    long j4 = j2 - j;
                    if (j4 != 0) {
                        this.maxChildRequested = j2;
                        if (dVar == null) {
                            long j5 = j3 + j4;
                            if (j5 < 0) {
                                j5 = Long.MAX_VALUE;
                            }
                            this.maxUpstreamRequested = j5;
                        } else if (j3 != 0) {
                            this.maxUpstreamRequested = 0;
                            dVar.request(j3 + j4);
                        } else {
                            dVar.request(j4);
                        }
                    } else if (!(j3 == 0 || dVar == null)) {
                        this.maxUpstreamRequested = 0;
                        dVar.request(j3);
                    }
                    i = this.management.addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class InnerSubscription<T> extends AtomicLong implements io.reactivex.disposables.b, d {
        static final long CANCELLED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        final org.a.c<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final ReplaySubscriber<T> parent;
        final AtomicLong totalRequested = new AtomicLong();

        InnerSubscription(ReplaySubscriber<T> replaySubscriber, org.a.c<? super T> cVar) {
            this.parent = replaySubscriber;
            this.child = cVar;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j) && io.reactivex.internal.util.b.b(this, j) != Long.MIN_VALUE) {
                io.reactivex.internal.util.b.a(this.totalRequested, j);
                this.parent.manageRequests();
                this.parent.buffer.replay(this);
            }
        }

        public long produced(long j) {
            return io.reactivex.internal.util.b.d(this, j);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == Long.MIN_VALUE;
        }

        public void cancel() {
            dispose();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.manageRequests();
                this.index = null;
            }
        }

        /* access modifiers changed from: package-private */
        public <U> U index() {
            return (U) this.index;
        }
    }

    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements b<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        volatile int size;

        UnboundedReplayBuffer(int i) {
            super(i);
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.b
        public void next(T t) {
            add(NotificationLite.next(t));
            this.size++;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.b
        public void error(Throwable th) {
            add(NotificationLite.error(th));
            this.size++;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.b
        public void complete() {
            add(NotificationLite.complete());
            this.size++;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.b
        public void replay(InnerSubscription<T> innerSubscription) {
            synchronized (innerSubscription) {
                if (innerSubscription.emitting) {
                    innerSubscription.missed = true;
                    return;
                }
                innerSubscription.emitting = true;
            }
            org.a.c<? super T> cVar = innerSubscription.child;
            while (!innerSubscription.isDisposed()) {
                int i = this.size;
                Integer num = (Integer) innerSubscription.index();
                int intValue = num != null ? num.intValue() : 0;
                long j = innerSubscription.get();
                long j2 = j;
                long j3 = 0;
                while (j2 != 0 && intValue < i) {
                    Object obj = get(intValue);
                    try {
                        if (!NotificationLite.accept(obj, cVar) && !innerSubscription.isDisposed()) {
                            intValue++;
                            j2--;
                            j3++;
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        innerSubscription.dispose();
                        if (!NotificationLite.isError(obj) && !NotificationLite.isComplete(obj)) {
                            cVar.onError(th);
                            return;
                        }
                        return;
                    }
                }
                if (j3 != 0) {
                    innerSubscription.index = Integer.valueOf(intValue);
                    if (j != Long.MAX_VALUE) {
                        innerSubscription.produced(j3);
                    }
                }
                synchronized (innerSubscription) {
                    if (!innerSubscription.missed) {
                        innerSubscription.emitting = false;
                        return;
                    }
                    innerSubscription.missed = false;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        final long index;
        final Object value;

        Node(Object obj, long j) {
            this.value = obj;
            this.index = j;
        }
    }

    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements b<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        long index;
        int size;
        Node tail;

        /* access modifiers changed from: package-private */
        public Object enterTransform(Object obj) {
            return obj;
        }

        /* access modifiers changed from: package-private */
        public Object leaveTransform(Object obj) {
            return obj;
        }

        /* access modifiers changed from: package-private */
        public void truncate() {
        }

        BoundedReplayBuffer() {
            Node node = new Node(null, 0);
            this.tail = node;
            set(node);
        }

        /* access modifiers changed from: package-private */
        public final void addLast(Node node) {
            this.tail.set(node);
            this.tail = node;
            this.size++;
        }

        /* access modifiers changed from: package-private */
        public final void removeFirst() {
            Node node = get().get();
            if (node != null) {
                this.size--;
                setFirst(node);
                return;
            }
            throw new IllegalStateException("Empty list!");
        }

        /* access modifiers changed from: package-private */
        public final void removeSome(int i) {
            Node node = get();
            while (i > 0) {
                node = node.get();
                i--;
                this.size--;
            }
            setFirst(node);
            Node node2 = get();
            if (node2.get() == null) {
                this.tail = node2;
            }
        }

        /* access modifiers changed from: package-private */
        public final void setFirst(Node node) {
            set(node);
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.b
        public final void next(T t) {
            Object enterTransform = enterTransform(NotificationLite.next(t));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncate();
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.b
        public final void error(Throwable th) {
            Object enterTransform = enterTransform(NotificationLite.error(th));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncateFinal();
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.b
        public final void complete() {
            Object enterTransform = enterTransform(NotificationLite.complete());
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(enterTransform, j));
            truncateFinal();
        }

        /* access modifiers changed from: package-private */
        public final void trimHead() {
            Node node = get();
            if (node.value != null) {
                Node node2 = new Node(null, 0);
                node2.lazySet(node.get());
                set(node2);
            }
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.b
        public final void replay(InnerSubscription<T> innerSubscription) {
            Node node;
            synchronized (innerSubscription) {
                if (innerSubscription.emitting) {
                    innerSubscription.missed = true;
                    return;
                }
                innerSubscription.emitting = true;
            }
            while (!innerSubscription.isDisposed()) {
                long j = innerSubscription.get();
                boolean z = j == Long.MAX_VALUE;
                Node node2 = (Node) innerSubscription.index();
                if (node2 == null) {
                    node2 = getHead();
                    innerSubscription.index = node2;
                    io.reactivex.internal.util.b.a(innerSubscription.totalRequested, node2.index);
                }
                long j2 = 0;
                while (j != 0 && (node = node2.get()) != null) {
                    Object leaveTransform = leaveTransform(node.value);
                    try {
                        if (NotificationLite.accept(leaveTransform, innerSubscription.child)) {
                            innerSubscription.index = null;
                            return;
                        }
                        j2++;
                        j--;
                        if (innerSubscription.isDisposed()) {
                            innerSubscription.index = null;
                            return;
                        }
                        node2 = node;
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        innerSubscription.index = null;
                        innerSubscription.dispose();
                        if (!NotificationLite.isError(leaveTransform) && !NotificationLite.isComplete(leaveTransform)) {
                            innerSubscription.child.onError(th);
                            return;
                        }
                        return;
                    }
                }
                if (j2 != 0) {
                    innerSubscription.index = node2;
                    if (!z) {
                        innerSubscription.produced(j2);
                    }
                }
                synchronized (innerSubscription) {
                    if (!innerSubscription.missed) {
                        innerSubscription.emitting = false;
                        return;
                    }
                    innerSubscription.missed = false;
                }
            }
            innerSubscription.index = null;
        }

        /* access modifiers changed from: package-private */
        public void truncateFinal() {
            trimHead();
        }

        /* access modifiers changed from: package-private */
        public final void collect(Collection<? super T> collection) {
            Node head = getHead();
            while (true) {
                head = head.get();
                if (head != null) {
                    Object leaveTransform = leaveTransform(head.value);
                    if (!NotificationLite.isComplete(leaveTransform) && !NotificationLite.isError(leaveTransform)) {
                        collection.add((Object) NotificationLite.getValue(leaveTransform));
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean hasError() {
            return this.tail.value != null && NotificationLite.isError(leaveTransform(this.tail.value));
        }

        /* access modifiers changed from: package-private */
        public boolean hasCompleted() {
            return this.tail.value != null && NotificationLite.isComplete(leaveTransform(this.tail.value));
        }

        /* access modifiers changed from: package-private */
        public Node getHead() {
            return get();
        }
    }

    static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        SizeBoundReplayBuffer(int i) {
            this.limit = i;
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.BoundedReplayBuffer
        public void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAge;
        final w scheduler;
        final TimeUnit unit;

        SizeAndTimeBoundReplayBuffer(int i, long j, TimeUnit timeUnit, w wVar) {
            this.scheduler = wVar;
            this.limit = i;
            this.maxAge = j;
            this.unit = timeUnit;
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.BoundedReplayBuffer
        public Object enterTransform(Object obj) {
            return new io.reactivex.f.b(obj, this.scheduler.a(this.unit), this.unit);
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.BoundedReplayBuffer
        public Object leaveTransform(Object obj) {
            return ((io.reactivex.f.b) obj).a();
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.BoundedReplayBuffer
        public void truncate() {
            long a = this.scheduler.a(this.unit) - this.maxAge;
            Node node = (Node) get();
            Node node2 = node.get();
            int i = 0;
            while (true) {
                node = node2;
                if (node == null) {
                    break;
                } else if (this.size <= this.limit || this.size <= 1) {
                    if (((io.reactivex.f.b) node.value).b() > a) {
                        break;
                    }
                    i++;
                    this.size--;
                    node2 = node.get();
                } else {
                    i++;
                    this.size--;
                    node2 = node.get();
                }
            }
            if (i != 0) {
                setFirst(node);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.BoundedReplayBuffer
        public void truncateFinal() {
            long a = this.scheduler.a(this.unit) - this.maxAge;
            Node node = (Node) get();
            Node node2 = node.get();
            int i = 0;
            while (true) {
                node = node2;
                if (node == null || this.size <= 1 || ((io.reactivex.f.b) node.value).b() > a) {
                    break;
                }
                i++;
                this.size--;
                node2 = node.get();
            }
            if (i != 0) {
                setFirst(node);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.BoundedReplayBuffer
        public Node getHead() {
            long a = this.scheduler.a(this.unit) - this.maxAge;
            Node node = (Node) get();
            Node node2 = node.get();
            while (true) {
                node = node2;
                if (node != null) {
                    io.reactivex.f.b bVar = (io.reactivex.f.b) node.value;
                    if (NotificationLite.isComplete(bVar.a()) || NotificationLite.isError(bVar.a()) || bVar.b() > a) {
                        break;
                    }
                    node2 = node.get();
                } else {
                    break;
                }
            }
            return node;
        }
    }

    static final class a implements Callable<Object> {
        a() {
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            return new UnboundedReplayBuffer(16);
        }
    }
}
