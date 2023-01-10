package com.google.common.util.concurrent;

import com.google.common.base.m;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.r;
import com.google.common.util.concurrent.s;

/* compiled from: AbstractService */
public abstract class b implements Service {
    private static final r.a<Object> a = new AnonymousClass1();
    private static final r.a<Object> b = new AnonymousClass2();
    private static final r.a<Object> c = b(Service.State.STARTING);
    private static final r.a<Object> d = b(Service.State.RUNNING);
    private static final r.a<Object> e = a(Service.State.NEW);
    private static final r.a<Object> f = a(Service.State.STARTING);
    private static final r.a<Object> g = a(Service.State.RUNNING);
    private static final r.a<Object> h = a(Service.State.STOPPING);
    private final s i = new s();
    private final s.a j = new C0112b();
    private final s.a k = new c();
    private final s.a l = new a();
    private final s.a m = new d();
    private final r<Object> n = new r<>();
    private volatile e o = new e(Service.State.NEW);

    /* compiled from: AbstractService */
    /* renamed from: com.google.common.util.concurrent.b$1  reason: invalid class name */
    static class AnonymousClass1 implements r.a<Object> {
        public String toString() {
            return "starting()";
        }

        AnonymousClass1() {
        }
    }

    /* compiled from: AbstractService */
    /* renamed from: com.google.common.util.concurrent.b$2  reason: invalid class name */
    static class AnonymousClass2 implements r.a<Object> {
        public String toString() {
            return "running()";
        }

        AnonymousClass2() {
        }
    }

    /* compiled from: AbstractService */
    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.util.concurrent.b$3  reason: invalid class name */
    public static class AnonymousClass3 implements r.a<Object> {
        final /* synthetic */ Service.State a;

        AnonymousClass3(Service.State state) {
            this.a = state;
        }

        public String toString() {
            return "terminated({from = " + this.a + "})";
        }
    }

    private static r.a<Object> a(Service.State state) {
        return new AnonymousClass3(state);
    }

    /* compiled from: AbstractService */
    /* access modifiers changed from: package-private */
    /* renamed from: com.google.common.util.concurrent.b$4  reason: invalid class name */
    public static class AnonymousClass4 implements r.a<Object> {
        final /* synthetic */ Service.State a;

        AnonymousClass4(Service.State state) {
            this.a = state;
        }

        public String toString() {
            return "stopping({from = " + this.a + "})";
        }
    }

    private static r.a<Object> b(Service.State state) {
        return new AnonymousClass4(state);
    }

    /* compiled from: AbstractService */
    /* renamed from: com.google.common.util.concurrent.b$b  reason: collision with other inner class name */
    private final class C0112b extends s.a {
        C0112b() {
            super(b.this.i);
        }
    }

    /* compiled from: AbstractService */
    private final class c extends s.a {
        c() {
            super(b.this.i);
        }
    }

    /* compiled from: AbstractService */
    private final class a extends s.a {
        a() {
            super(b.this.i);
        }
    }

    /* compiled from: AbstractService */
    private final class d extends s.a {
        d() {
            super(b.this.i);
        }
    }

    protected b() {
    }

    public final Service.State a() {
        return this.o.a();
    }

    public String toString() {
        return getClass().getSimpleName() + " [" + a() + "]";
    }

    /* compiled from: AbstractService */
    /* access modifiers changed from: private */
    public static final class e {
        final Service.State a;
        final boolean b;
        final Throwable c;

        e(Service.State state) {
            this(state, false, null);
        }

        e(Service.State state, boolean z, Throwable th) {
            boolean z2 = false;
            m.a(!z || state == Service.State.STARTING, "shutdownWhenStartupFinishes can only be set if state is STARTING. Got %s instead.", state);
            m.a(!((state == Service.State.FAILED ? true : z2) ^ (th != null)), "A failure cause should be set if and only if the state is failed.  Got %s and %s instead.", state, th);
            this.a = state;
            this.b = z;
            this.c = th;
        }

        /* access modifiers changed from: package-private */
        public Service.State a() {
            if (!this.b || this.a != Service.State.STARTING) {
                return this.a;
            }
            return Service.State.STOPPING;
        }
    }
}
