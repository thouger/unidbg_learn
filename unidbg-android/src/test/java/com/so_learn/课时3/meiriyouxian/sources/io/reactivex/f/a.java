package io.reactivex.f;

import io.reactivex.internal.schedulers.j;
import io.reactivex.internal.schedulers.k;
import io.reactivex.w;
import java.util.concurrent.Callable;

/* compiled from: Schedulers */
public final class a {
    static final w a = io.reactivex.e.a.d(new h());
    static final w b = io.reactivex.e.a.a(new b());
    static final w c = io.reactivex.e.a.b(new c());
    static final w d = k.c();
    static final w e = io.reactivex.e.a.c(new f());

    /* compiled from: Schedulers */
    /* access modifiers changed from: package-private */
    /* renamed from: io.reactivex.f.a$a  reason: collision with other inner class name */
    public static final class C0205a {
        static final w a = new io.reactivex.internal.schedulers.a();
    }

    /* compiled from: Schedulers */
    /* access modifiers changed from: package-private */
    public static final class d {
        static final w a = new io.reactivex.internal.schedulers.d();
    }

    /* compiled from: Schedulers */
    /* access modifiers changed from: package-private */
    public static final class e {
        static final w a = new io.reactivex.internal.schedulers.e();
    }

    /* compiled from: Schedulers */
    /* access modifiers changed from: package-private */
    public static final class g {
        static final w a = new j();
    }

    public static w a() {
        return io.reactivex.e.a.a(b);
    }

    public static w b() {
        return io.reactivex.e.a.b(c);
    }

    public static w c() {
        return io.reactivex.e.a.c(e);
    }

    public static w d() {
        return io.reactivex.e.a.d(a);
    }

    /* compiled from: Schedulers */
    static final class c implements Callable<w> {
        c() {
        }

        /* renamed from: a */
        public w call() throws Exception {
            return d.a;
        }
    }

    /* compiled from: Schedulers */
    static final class f implements Callable<w> {
        f() {
        }

        /* renamed from: a */
        public w call() throws Exception {
            return e.a;
        }
    }

    /* compiled from: Schedulers */
    static final class h implements Callable<w> {
        h() {
        }

        /* renamed from: a */
        public w call() throws Exception {
            return g.a;
        }
    }

    /* compiled from: Schedulers */
    static final class b implements Callable<w> {
        b() {
        }

        /* renamed from: a */
        public w call() throws Exception {
            return C0205a.a;
        }
    }
}
