package com.google.android.exoplayer2.util;

import android.os.Handler;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: EventDispatcher */
public final class f<T> {
    private final CopyOnWriteArrayList<b<T>> a = new CopyOnWriteArrayList<>();

    /* compiled from: EventDispatcher */
    public interface a<T> {
        void sendTo(T t);
    }

    public void a(Handler handler, T t) {
        a.a((handler == null || t == null) ? false : true);
        a((f<T>) t);
        this.a.add(new b<>(handler, t));
    }

    public void a(T t) {
        Iterator<b<T>> it2 = this.a.iterator();
        while (it2.hasNext()) {
            b<T> next = it2.next();
            if (((b) next).b == t) {
                next.a();
                this.a.remove(next);
            }
        }
    }

    public void a(a<T> aVar) {
        Iterator<b<T>> it2 = this.a.iterator();
        while (it2.hasNext()) {
            it2.next().a(aVar);
        }
    }

    /* compiled from: EventDispatcher */
    /* access modifiers changed from: private */
    public static final class b<T> {
        private final Handler a;
        private final T b;
        private boolean c;

        public b(Handler handler, T t) {
            this.a = handler;
            this.b = t;
        }

        public void a() {
            this.c = true;
        }

        public void a(a<T> aVar) {
            this.a.post(new $$Lambda$f$b$hGN2WkDAge8pXb8fkLjmbmX8hcY(this, aVar));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(a aVar) {
            if (!this.c) {
                aVar.sendTo(this.b);
            }
        }
    }
}
