package io.reactivex.internal.operators.flowable;

import io.reactivex.c.g;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.j;
import org.a.c;

/* compiled from: FlowableDoOnEach */
public final class b<T> extends a<T, T> {
    final g<? super T> c;
    final g<? super Throwable> d;
    final io.reactivex.c.a e;
    final io.reactivex.c.a f;

    public b(io.reactivex.g<T> gVar, g<? super T> gVar2, g<? super Throwable> gVar3, io.reactivex.c.a aVar, io.reactivex.c.a aVar2) {
        super(gVar);
        this.c = gVar2;
        this.d = gVar3;
        this.e = aVar;
        this.f = aVar2;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        if (cVar instanceof io.reactivex.internal.a.a) {
            this.b.a((j) new a((io.reactivex.internal.a.a) cVar, this.c, this.d, this.e, this.f));
        } else {
            this.b.a((j) new C0207b(cVar, this.c, this.d, this.e, this.f));
        }
    }

    /* compiled from: FlowableDoOnEach */
    /* renamed from: io.reactivex.internal.operators.flowable.b$b  reason: collision with other inner class name */
    static final class C0207b<T> extends io.reactivex.internal.subscribers.b<T, T> {
        final g<? super T> a;
        final g<? super Throwable> b;
        final io.reactivex.c.a c;
        final io.reactivex.c.a d;

        C0207b(c<? super T> cVar, g<? super T> gVar, g<? super Throwable> gVar2, io.reactivex.c.a aVar, io.reactivex.c.a aVar2) {
            super(cVar);
            this.a = gVar;
            this.b = gVar2;
            this.c = aVar;
            this.d = aVar2;
        }

        public void onNext(T t) {
            if (!this.h) {
                if (this.i != 0) {
                    this.e.onNext((Object) null);
                    return;
                }
                try {
                    this.a.accept(t);
                    this.e.onNext(t);
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        @Override // io.reactivex.internal.subscribers.b
        public void onError(Throwable th) {
            if (this.h) {
                io.reactivex.e.a.a(th);
                return;
            }
            boolean z = true;
            this.h = true;
            try {
                this.b.accept(th);
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.e.onError(new CompositeException(th, th2));
                z = false;
            }
            if (z) {
                this.e.onError(th);
            }
            try {
                this.d.a();
            } catch (Throwable th3) {
                io.reactivex.exceptions.a.b(th3);
                io.reactivex.e.a.a(th3);
            }
        }

        @Override // io.reactivex.internal.subscribers.b
        public void onComplete() {
            if (!this.h) {
                try {
                    this.c.a();
                    this.h = true;
                    this.e.onComplete();
                    try {
                        this.d.a();
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        io.reactivex.e.a.a(th);
                    }
                } catch (Throwable th2) {
                    a(th2);
                }
            }
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            return a(i);
        }

        @Override // io.reactivex.internal.a.h
        public T poll() throws Exception {
            try {
                T poll = this.g.poll();
                if (poll != null) {
                    try {
                        this.a.accept(poll);
                        this.d.a();
                    } catch (Throwable th) {
                        throw new CompositeException(th, th);
                    }
                } else if (this.i == 1) {
                    this.c.a();
                    this.d.a();
                }
                return poll;
            } catch (Throwable th2) {
                throw new CompositeException(th, th2);
            }
        }
    }

    /* compiled from: FlowableDoOnEach */
    static final class a<T> extends io.reactivex.internal.subscribers.a<T, T> {
        final g<? super T> a;
        final g<? super Throwable> b;
        final io.reactivex.c.a c;
        final io.reactivex.c.a d;

        a(io.reactivex.internal.a.a<? super T> aVar, g<? super T> gVar, g<? super Throwable> gVar2, io.reactivex.c.a aVar2, io.reactivex.c.a aVar3) {
            super(aVar);
            this.a = gVar;
            this.b = gVar2;
            this.c = aVar2;
            this.d = aVar3;
        }

        public void onNext(T t) {
            if (!this.h) {
                if (this.i != 0) {
                    this.e.onNext(null);
                    return;
                }
                try {
                    this.a.accept(t);
                    this.e.onNext(t);
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        @Override // io.reactivex.internal.a.a
        public boolean tryOnNext(T t) {
            if (this.h) {
                return false;
            }
            try {
                this.a.accept(t);
                return this.e.tryOnNext(t);
            } catch (Throwable th) {
                a(th);
                return false;
            }
        }

        @Override // io.reactivex.internal.subscribers.a
        public void onError(Throwable th) {
            if (this.h) {
                io.reactivex.e.a.a(th);
                return;
            }
            boolean z = true;
            this.h = true;
            try {
                this.b.accept(th);
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.e.onError(new CompositeException(th, th2));
                z = false;
            }
            if (z) {
                this.e.onError(th);
            }
            try {
                this.d.a();
            } catch (Throwable th3) {
                io.reactivex.exceptions.a.b(th3);
                io.reactivex.e.a.a(th3);
            }
        }

        @Override // io.reactivex.internal.subscribers.a
        public void onComplete() {
            if (!this.h) {
                try {
                    this.c.a();
                    this.h = true;
                    this.e.onComplete();
                    try {
                        this.d.a();
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        io.reactivex.e.a.a(th);
                    }
                } catch (Throwable th2) {
                    a(th2);
                }
            }
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            return a(i);
        }

        @Override // io.reactivex.internal.a.h
        public T poll() throws Exception {
            try {
                T poll = this.g.poll();
                if (poll != null) {
                    try {
                        this.a.accept(poll);
                        this.d.a();
                    } catch (Throwable th) {
                        throw new CompositeException(th, th);
                    }
                } else if (this.i == 1) {
                    this.c.a();
                    this.d.a();
                }
                return poll;
            } catch (Throwable th2) {
                throw new CompositeException(th, th2);
            }
        }
    }
}
