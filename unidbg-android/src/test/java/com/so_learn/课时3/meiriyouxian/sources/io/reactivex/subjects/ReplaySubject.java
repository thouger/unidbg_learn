package io.reactivex.subjects;

import io.reactivex.disposables.b;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.v;
import io.reactivex.w;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ReplaySubject<T> extends b<T> {
    static final ReplayDisposable[] c = new ReplayDisposable[0];
    static final ReplayDisposable[] d = new ReplayDisposable[0];
    private static final Object[] f = new Object[0];
    final a<T> a;
    final AtomicReference<ReplayDisposable<T>[]> b;
    boolean e;

    /* access modifiers changed from: package-private */
    public interface a<T> {
        void add(T t);

        void addFinal(Object obj);

        boolean compareAndSet(Object obj, Object obj2);

        void replay(ReplayDisposable<T> replayDisposable);
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        ReplayDisposable replayDisposable = new ReplayDisposable(vVar, this);
        vVar.onSubscribe(replayDisposable);
        if (replayDisposable.cancelled) {
            return;
        }
        if (!a(replayDisposable) || !replayDisposable.cancelled) {
            this.a.replay(replayDisposable);
        } else {
            b(replayDisposable);
        }
    }

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        if (this.e) {
            bVar.dispose();
        }
    }

    @Override // io.reactivex.v
    public void onNext(T t) {
        io.reactivex.internal.functions.a.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.e) {
            a<T> aVar = this.a;
            aVar.add(t);
            for (ReplayDisposable<T> replayDisposable : this.b.get()) {
                aVar.replay(replayDisposable);
            }
        }
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        io.reactivex.internal.functions.a.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.e) {
            io.reactivex.e.a.a(th);
            return;
        }
        this.e = true;
        Object error = NotificationLite.error(th);
        a<T> aVar = this.a;
        aVar.addFinal(error);
        for (ReplayDisposable<T> replayDisposable : d(error)) {
            aVar.replay(replayDisposable);
        }
    }

    @Override // io.reactivex.v
    public void onComplete() {
        if (!this.e) {
            this.e = true;
            Object complete = NotificationLite.complete();
            a<T> aVar = this.a;
            aVar.addFinal(complete);
            for (ReplayDisposable<T> replayDisposable : d(complete)) {
                aVar.replay(replayDisposable);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(ReplayDisposable<T> replayDisposable) {
        ReplayDisposable<T>[] replayDisposableArr;
        ReplayDisposable<T>[] replayDisposableArr2;
        do {
            replayDisposableArr = this.b.get();
            if (replayDisposableArr == d) {
                return false;
            }
            int length = replayDisposableArr.length;
            replayDisposableArr2 = new ReplayDisposable[(length + 1)];
            System.arraycopy(replayDisposableArr, 0, replayDisposableArr2, 0, length);
            replayDisposableArr2[length] = replayDisposable;
        } while (!this.b.compareAndSet(replayDisposableArr, replayDisposableArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(ReplayDisposable<T> replayDisposable) {
        ReplayDisposable<T>[] replayDisposableArr;
        ReplayDisposable<T>[] replayDisposableArr2;
        do {
            replayDisposableArr = this.b.get();
            if (replayDisposableArr != d && replayDisposableArr != c) {
                int length = replayDisposableArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (replayDisposableArr[i2] == replayDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        replayDisposableArr2 = c;
                    } else {
                        ReplayDisposable<T>[] replayDisposableArr3 = new ReplayDisposable[(length - 1)];
                        System.arraycopy(replayDisposableArr, 0, replayDisposableArr3, 0, i);
                        System.arraycopy(replayDisposableArr, i + 1, replayDisposableArr3, i, (length - i) - 1);
                        replayDisposableArr2 = replayDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.b.compareAndSet(replayDisposableArr, replayDisposableArr2));
    }

    /* access modifiers changed from: package-private */
    public ReplayDisposable<T>[] d(Object obj) {
        if (this.a.compareAndSet(null, obj)) {
            return this.b.getAndSet(d);
        }
        return d;
    }

    /* access modifiers changed from: package-private */
    public static final class ReplayDisposable<T> extends AtomicInteger implements b {
        private static final long serialVersionUID = 466549804534799122L;
        volatile boolean cancelled;
        final v<? super T> downstream;
        Object index;
        final ReplaySubject<T> state;

        ReplayDisposable(v<? super T> vVar, ReplaySubject<T> replaySubject) {
            this.downstream = vVar;
            this.state = replaySubject;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.state.b((ReplayDisposable) this);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
        }
    }

    static final class UnboundedReplayBuffer<T> extends AtomicReference<Object> implements a<T> {
        private static final long serialVersionUID = -733876083048047795L;
        final List<Object> buffer;
        volatile boolean done;
        volatile int size;

        public void trimHead() {
        }

        UnboundedReplayBuffer(int i) {
            this.buffer = new ArrayList(io.reactivex.internal.functions.a.a(i, "capacityHint"));
        }

        @Override // io.reactivex.subjects.ReplaySubject.a
        public void add(T t) {
            this.buffer.add(t);
            this.size++;
        }

        @Override // io.reactivex.subjects.ReplaySubject.a
        public void addFinal(Object obj) {
            this.buffer.add(obj);
            trimHead();
            this.size++;
            this.done = true;
        }

        public T getValue() {
            int i = this.size;
            if (i == 0) {
                return null;
            }
            List<Object> list = this.buffer;
            T t = (T) list.get(i - 1);
            if (!NotificationLite.isComplete(t) && !NotificationLite.isError(t)) {
                return t;
            }
            if (i == 1) {
                return null;
            }
            return (T) list.get(i - 2);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r7v10, resolved type: T[] */
        /* JADX WARN: Multi-variable type inference failed */
        public T[] getValues(T[] tArr) {
            int i = this.size;
            if (i == 0) {
                if (tArr.length != 0) {
                    tArr[0] = null;
                }
                return tArr;
            }
            List<Object> list = this.buffer;
            Object obj = list.get(i - 1);
            if ((NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) && i - 1 == 0) {
                if (tArr.length != 0) {
                    tArr[0] = null;
                }
                return tArr;
            }
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            for (int i2 = 0; i2 < i; i2++) {
                tArr[i2] = list.get(i2);
            }
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }

        @Override // io.reactivex.subjects.ReplaySubject.a
        public void replay(ReplayDisposable<T> replayDisposable) {
            int i;
            if (replayDisposable.getAndIncrement() == 0) {
                List<Object> list = this.buffer;
                v<? super T> vVar = replayDisposable.downstream;
                Integer num = (Integer) replayDisposable.index;
                int i2 = 0;
                if (num != null) {
                    i2 = num.intValue();
                } else {
                    replayDisposable.index = 0;
                }
                int i3 = 1;
                while (!replayDisposable.cancelled) {
                    int i4 = this.size;
                    while (i4 != i2) {
                        if (replayDisposable.cancelled) {
                            replayDisposable.index = null;
                            return;
                        }
                        Object obj = list.get(i2);
                        if (this.done && (i = i2 + 1) == i4 && i == (i4 = this.size)) {
                            if (NotificationLite.isComplete(obj)) {
                                vVar.onComplete();
                            } else {
                                vVar.onError(NotificationLite.getError(obj));
                            }
                            replayDisposable.index = null;
                            replayDisposable.cancelled = true;
                            return;
                        }
                        vVar.onNext(obj);
                        i2++;
                    }
                    if (i2 == this.size) {
                        replayDisposable.index = Integer.valueOf(i2);
                        i3 = replayDisposable.addAndGet(-i3);
                        if (i3 == 0) {
                            return;
                        }
                    }
                }
                replayDisposable.index = null;
            }
        }

        public int size() {
            int i = this.size;
            if (i == 0) {
                return 0;
            }
            int i2 = i - 1;
            Object obj = this.buffer.get(i2);
            return (NotificationLite.isComplete(obj) || NotificationLite.isError(obj)) ? i2 : i;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class Node<T> extends AtomicReference<Node<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        final T value;

        Node(T t) {
            this.value = t;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class TimedNode<T> extends AtomicReference<TimedNode<T>> {
        private static final long serialVersionUID = 6404226426336033100L;
        final long time;
        final T value;

        TimedNode(T t, long j) {
            this.value = t;
            this.time = j;
        }
    }

    static final class SizeBoundReplayBuffer<T> extends AtomicReference<Object> implements a<T> {
        private static final long serialVersionUID = 1107649250281456395L;
        volatile boolean done;
        volatile Node<Object> head;
        final int maxSize;
        int size;
        Node<Object> tail;

        SizeBoundReplayBuffer(int i) {
            this.maxSize = io.reactivex.internal.functions.a.a(i, "maxSize");
            Node<Object> node = new Node<>(null);
            this.tail = node;
            this.head = node;
        }

        /* access modifiers changed from: package-private */
        public void trim() {
            int i = this.size;
            if (i > this.maxSize) {
                this.size = i - 1;
                this.head = this.head.get();
            }
        }

        @Override // io.reactivex.subjects.ReplaySubject.a
        public void add(T t) {
            Node<Object> node = new Node<>(t);
            Node<Object> node2 = this.tail;
            this.tail = node;
            this.size++;
            node2.set(node);
            trim();
        }

        @Override // io.reactivex.subjects.ReplaySubject.a
        public void addFinal(Object obj) {
            Node<Object> node = new Node<>(obj);
            Node<Object> node2 = this.tail;
            this.tail = node;
            this.size++;
            node2.lazySet(node);
            trimHead();
            this.done = true;
        }

        public void trimHead() {
            Node<Object> node = this.head;
            if (node.value != null) {
                Node<Object> node2 = new Node<>(null);
                node2.lazySet(node.get());
                this.head = node2;
            }
        }

        public T getValue() {
            Node<Object> node = this.head;
            Node<Object> node2 = null;
            while (true) {
                Node<T> node3 = node.get();
                if (node3 == null) {
                    break;
                }
                node2 = node;
                node = node3;
            }
            T t = node.value;
            if (t == null) {
                return null;
            }
            if (NotificationLite.isComplete(t) || NotificationLite.isError(t)) {
                return node2.value;
            }
            return t;
        }

        public T[] getValues(T[] tArr) {
            Node<T> node = this.head;
            int size = size();
            if (size != 0) {
                if (tArr.length < size) {
                    tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
                }
                for (int i = 0; i != size; i++) {
                    node = node.get();
                    tArr[i] = node.value;
                }
                if (tArr.length > size) {
                    tArr[size] = null;
                }
            } else if (tArr.length != 0) {
                tArr[0] = null;
            }
            return tArr;
        }

        @Override // io.reactivex.subjects.ReplaySubject.a
        public void replay(ReplayDisposable<T> replayDisposable) {
            if (replayDisposable.getAndIncrement() == 0) {
                v<? super T> vVar = replayDisposable.downstream;
                Node<T> node = (Node) replayDisposable.index;
                if (node == null) {
                    node = this.head;
                }
                int i = 1;
                while (!replayDisposable.cancelled) {
                    Node<T> node2 = node.get();
                    if (node2 != null) {
                        T t = node2.value;
                        if (!this.done || node2.get() != null) {
                            vVar.onNext(t);
                            node = node2;
                        } else {
                            if (NotificationLite.isComplete(t)) {
                                vVar.onComplete();
                            } else {
                                vVar.onError(NotificationLite.getError(t));
                            }
                            replayDisposable.index = null;
                            replayDisposable.cancelled = true;
                            return;
                        }
                    } else if (node.get() != null) {
                        continue;
                    } else {
                        replayDisposable.index = node;
                        i = replayDisposable.addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    }
                }
                replayDisposable.index = null;
            }
        }

        public int size() {
            Node<Object> node = this.head;
            int i = 0;
            while (i != Integer.MAX_VALUE) {
                Node<T> node2 = node.get();
                if (node2 == null) {
                    T t = node.value;
                    return (NotificationLite.isComplete(t) || NotificationLite.isError(t)) ? i - 1 : i;
                }
                i++;
                node = node2;
            }
            return i;
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends AtomicReference<Object> implements a<T> {
        private static final long serialVersionUID = -8056260896137901749L;
        volatile boolean done;
        volatile TimedNode<Object> head;
        final long maxAge;
        final int maxSize;
        final w scheduler;
        int size;
        TimedNode<Object> tail;
        final TimeUnit unit;

        SizeAndTimeBoundReplayBuffer(int i, long j, TimeUnit timeUnit, w wVar) {
            this.maxSize = io.reactivex.internal.functions.a.a(i, "maxSize");
            this.maxAge = io.reactivex.internal.functions.a.a(j, "maxAge");
            this.unit = (TimeUnit) io.reactivex.internal.functions.a.a(timeUnit, "unit is null");
            this.scheduler = (w) io.reactivex.internal.functions.a.a(wVar, "scheduler is null");
            TimedNode<Object> timedNode = new TimedNode<>(null, 0);
            this.tail = timedNode;
            this.head = timedNode;
        }

        /* access modifiers changed from: package-private */
        public void trim() {
            int i = this.size;
            if (i > this.maxSize) {
                this.size = i - 1;
                this.head = this.head.get();
            }
            long a = this.scheduler.a(this.unit) - this.maxAge;
            TimedNode<Object> timedNode = this.head;
            while (this.size > 1) {
                TimedNode<T> timedNode2 = timedNode.get();
                if (timedNode2 == null) {
                    this.head = timedNode;
                    return;
                } else if (timedNode2.time > a) {
                    this.head = timedNode;
                    return;
                } else {
                    this.size--;
                    timedNode = timedNode2;
                }
            }
            this.head = timedNode;
        }

        /* access modifiers changed from: package-private */
        public void trimFinal() {
            long a = this.scheduler.a(this.unit) - this.maxAge;
            TimedNode<Object> timedNode = this.head;
            while (true) {
                TimedNode<T> timedNode2 = timedNode.get();
                if (timedNode2.get() == null) {
                    if (timedNode.value != null) {
                        TimedNode<Object> timedNode3 = new TimedNode<>(null, 0);
                        timedNode3.lazySet(timedNode.get());
                        this.head = timedNode3;
                        return;
                    }
                    this.head = timedNode;
                    return;
                } else if (timedNode2.time <= a) {
                    timedNode = timedNode2;
                } else if (timedNode.value != null) {
                    TimedNode<Object> timedNode4 = new TimedNode<>(null, 0);
                    timedNode4.lazySet(timedNode.get());
                    this.head = timedNode4;
                    return;
                } else {
                    this.head = timedNode;
                    return;
                }
            }
        }

        @Override // io.reactivex.subjects.ReplaySubject.a
        public void add(T t) {
            TimedNode<Object> timedNode = new TimedNode<>(t, this.scheduler.a(this.unit));
            TimedNode<Object> timedNode2 = this.tail;
            this.tail = timedNode;
            this.size++;
            timedNode2.set(timedNode);
            trim();
        }

        @Override // io.reactivex.subjects.ReplaySubject.a
        public void addFinal(Object obj) {
            TimedNode<Object> timedNode = new TimedNode<>(obj, Long.MAX_VALUE);
            TimedNode<Object> timedNode2 = this.tail;
            this.tail = timedNode;
            this.size++;
            timedNode2.lazySet(timedNode);
            trimFinal();
            this.done = true;
        }

        public void trimHead() {
            TimedNode<Object> timedNode = this.head;
            if (timedNode.value != null) {
                TimedNode<Object> timedNode2 = new TimedNode<>(null, 0);
                timedNode2.lazySet(timedNode.get());
                this.head = timedNode2;
            }
        }

        public T getValue() {
            T t;
            TimedNode<Object> timedNode = this.head;
            TimedNode<Object> timedNode2 = null;
            while (true) {
                TimedNode<T> timedNode3 = timedNode.get();
                if (timedNode3 == null) {
                    break;
                }
                timedNode2 = timedNode;
                timedNode = timedNode3;
            }
            if (timedNode.time < this.scheduler.a(this.unit) - this.maxAge || (t = timedNode.value) == null) {
                return null;
            }
            if (NotificationLite.isComplete(t) || NotificationLite.isError(t)) {
                return timedNode2.value;
            }
            return t;
        }

        /* access modifiers changed from: package-private */
        public TimedNode<Object> getHead() {
            TimedNode<Object> timedNode = this.head;
            long a = this.scheduler.a(this.unit) - this.maxAge;
            TimedNode<T> timedNode2 = timedNode.get();
            while (true) {
                timedNode = timedNode2;
                if (timedNode == null || timedNode.time > a) {
                    break;
                }
                timedNode2 = timedNode.get();
            }
            return timedNode;
        }

        public T[] getValues(T[] tArr) {
            TimedNode<T> head = getHead();
            int size = size(head);
            if (size != 0) {
                if (tArr.length < size) {
                    tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
                }
                for (int i = 0; i != size; i++) {
                    head = head.get();
                    tArr[i] = head.value;
                }
                if (tArr.length > size) {
                    tArr[size] = null;
                }
            } else if (tArr.length != 0) {
                tArr[0] = null;
            }
            return tArr;
        }

        @Override // io.reactivex.subjects.ReplaySubject.a
        public void replay(ReplayDisposable<T> replayDisposable) {
            if (replayDisposable.getAndIncrement() == 0) {
                v<? super T> vVar = replayDisposable.downstream;
                TimedNode<T> timedNode = (TimedNode) replayDisposable.index;
                if (timedNode == null) {
                    timedNode = getHead();
                }
                int i = 1;
                while (!replayDisposable.cancelled) {
                    while (!replayDisposable.cancelled) {
                        TimedNode<T> timedNode2 = timedNode.get();
                        if (timedNode2 != null) {
                            T t = timedNode2.value;
                            if (!this.done || timedNode2.get() != null) {
                                vVar.onNext(t);
                                timedNode = timedNode2;
                            } else {
                                if (NotificationLite.isComplete(t)) {
                                    vVar.onComplete();
                                } else {
                                    vVar.onError(NotificationLite.getError(t));
                                }
                                replayDisposable.index = null;
                                replayDisposable.cancelled = true;
                                return;
                            }
                        } else if (timedNode.get() == null) {
                            replayDisposable.index = timedNode;
                            i = replayDisposable.addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        }
                    }
                    replayDisposable.index = null;
                    return;
                }
                replayDisposable.index = null;
            }
        }

        public int size() {
            return size(getHead());
        }

        /* access modifiers changed from: package-private */
        public int size(TimedNode<Object> timedNode) {
            int i = 0;
            while (i != Integer.MAX_VALUE) {
                TimedNode<T> timedNode2 = timedNode.get();
                if (timedNode2 == null) {
                    T t = timedNode.value;
                    return (NotificationLite.isComplete(t) || NotificationLite.isError(t)) ? i - 1 : i;
                }
                i++;
                timedNode = timedNode2;
            }
            return i;
        }
    }
}
