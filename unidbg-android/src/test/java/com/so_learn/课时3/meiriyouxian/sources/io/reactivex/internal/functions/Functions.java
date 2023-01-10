package io.reactivex.internal.functions;

import io.reactivex.exceptions.OnErrorNotImplementedException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public final class Functions {
    static final io.reactivex.c.h<Object, Object> a = new k();
    public static final Runnable b = new h();
    public static final io.reactivex.c.a c = new e();
    static final io.reactivex.c.g<Object> d = new f();
    public static final io.reactivex.c.g<Throwable> e = new i();
    public static final io.reactivex.c.g<Throwable> f = new p();
    public static final io.reactivex.c.j g = new g();
    static final io.reactivex.c.k<Object> h = new q();
    static final io.reactivex.c.k<Object> i = new j();
    static final Callable<Object> j = new o();
    static final Comparator<Object> k = new n();
    public static final io.reactivex.c.g<org.a.d> l = new m();

    public static <T1, T2, R> io.reactivex.c.h<Object[], R> a(io.reactivex.c.c<? super T1, ? super T2, ? extends R> cVar) {
        a.a(cVar, "f is null");
        return new a(cVar);
    }

    public static <T1, T2, T3, R> io.reactivex.c.h<Object[], R> a(io.reactivex.c.i<T1, T2, T3, R> iVar) {
        a.a(iVar, "f is null");
        return new b(iVar);
    }

    public static <T> io.reactivex.c.h<T, T> a() {
        return (io.reactivex.c.h<T, T>) a;
    }

    public static <T> io.reactivex.c.g<T> b() {
        return (io.reactivex.c.g<T>) d;
    }

    /* access modifiers changed from: package-private */
    public static final class l<T, U> implements io.reactivex.c.h<T, U>, Callable<U> {
        final U a;

        l(U u) {
            this.a = u;
        }

        @Override // java.util.concurrent.Callable
        public U call() throws Exception {
            return this.a;
        }

        @Override // io.reactivex.c.h
        public U apply(T t) throws Exception {
            return this.a;
        }
    }

    public static <T> Callable<T> a(T t) {
        return new l(t);
    }

    public static <T, U> io.reactivex.c.h<T, U> b(U u) {
        return new l(u);
    }

    /* access modifiers changed from: package-private */
    public static final class c<T, U> implements io.reactivex.c.h<T, U> {
        final Class<U> a;

        c(Class<U> cls) {
            this.a = cls;
        }

        @Override // io.reactivex.c.h
        public U apply(T t) throws Exception {
            return this.a.cast(t);
        }
    }

    public static <T, U> io.reactivex.c.h<T, U> a(Class<U> cls) {
        return new c(cls);
    }

    enum HashSetCallable implements Callable<Set<Object>> {
        INSTANCE;

        @Override // java.util.concurrent.Callable
        public Set<Object> call() throws Exception {
            return new HashSet();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class d<T, U> implements io.reactivex.c.k<T> {
        final Class<U> a;

        d(Class<U> cls) {
            this.a = cls;
        }

        @Override // io.reactivex.c.k
        public boolean test(T t) throws Exception {
            return this.a.isInstance(t);
        }
    }

    public static <T, U> io.reactivex.c.k<T> b(Class<U> cls) {
        return new d(cls);
    }

    enum NaturalComparator implements Comparator<Object> {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class a<T1, T2, R> implements io.reactivex.c.h<Object[], R> {
        final io.reactivex.c.c<? super T1, ? super T2, ? extends R> a;

        a(io.reactivex.c.c<? super T1, ? super T2, ? extends R> cVar) {
            this.a = cVar;
        }

        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 2) {
                return (R) this.a.apply(objArr[0], objArr[1]);
            }
            throw new IllegalArgumentException("Array of size 2 expected but got " + objArr.length);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class b<T1, T2, T3, R> implements io.reactivex.c.h<Object[], R> {
        final io.reactivex.c.i<T1, T2, T3, R> a;

        b(io.reactivex.c.i<T1, T2, T3, R> iVar) {
            this.a = iVar;
        }

        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 3) {
                return (R) this.a.a(objArr[0], objArr[1], objArr[2]);
            }
            throw new IllegalArgumentException("Array of size 3 expected but got " + objArr.length);
        }
    }

    static final class k implements io.reactivex.c.h<Object, Object> {
        @Override // io.reactivex.c.h
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }

        k() {
        }
    }

    static final class h implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
        }

        @Override // java.lang.Object
        public String toString() {
            return "EmptyRunnable";
        }

        h() {
        }
    }

    static final class e implements io.reactivex.c.a {
        @Override // io.reactivex.c.a
        public void a() {
        }

        public String toString() {
            return "EmptyAction";
        }

        e() {
        }
    }

    static final class f implements io.reactivex.c.g<Object> {
        @Override // io.reactivex.c.g
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }

        f() {
        }
    }

    static final class i implements io.reactivex.c.g<Throwable> {
        i() {
        }

        /* renamed from: a */
        public void accept(Throwable th) {
            io.reactivex.e.a.a(th);
        }
    }

    static final class p implements io.reactivex.c.g<Throwable> {
        p() {
        }

        /* renamed from: a */
        public void accept(Throwable th) {
            io.reactivex.e.a.a(new OnErrorNotImplementedException(th));
        }
    }

    static final class g implements io.reactivex.c.j {
        g() {
        }
    }

    static final class q implements io.reactivex.c.k<Object> {
        @Override // io.reactivex.c.k
        public boolean test(Object obj) {
            return true;
        }

        q() {
        }
    }

    static final class j implements io.reactivex.c.k<Object> {
        @Override // io.reactivex.c.k
        public boolean test(Object obj) {
            return false;
        }

        j() {
        }
    }

    static final class o implements Callable<Object> {
        @Override // java.util.concurrent.Callable
        public Object call() {
            return null;
        }

        o() {
        }
    }

    static final class n implements Comparator<Object> {
        n() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    static final class m implements io.reactivex.c.g<org.a.d> {
        m() {
        }

        /* renamed from: a */
        public void accept(org.a.d dVar) throws Exception {
            dVar.request(Long.MAX_VALUE);
        }
    }
}
