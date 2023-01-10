package com.google.common.util.concurrent;

import android.net.wifi.WifiEnterpriseConfig;
import com.google.common.base.m;
import com.google.common.base.r;
import com.google.common.util.concurrent.a.b;
import com.umeng.message.common.inter.ITagManager;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* compiled from: AbstractFuture */
public abstract class a<V> extends com.google.common.util.concurrent.a.a implements p<V> {
    private static final boolean a = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", ITagManager.STATUS_FALSE));
    private static final Logger b = Logger.getLogger(a.class.getName());
    private static final AbstractC0111a c;
    private static final Object d = new Object();
    private volatile Object e;
    private volatile d f;
    private volatile k g;

    /* compiled from: AbstractFuture */
    /* access modifiers changed from: package-private */
    public interface h<V> extends p<V> {
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: protected */
    public void c() {
    }

    static {
        AbstractC0111a aVar;
        Throwable th;
        Throwable th2 = null;
        try {
            aVar = new j();
            th = null;
        } catch (Throwable th3) {
            th2 = th3;
            th = th;
            aVar = new g();
        }
        c = aVar;
        if (th2 != null) {
            b.log(Level.SEVERE, "UnsafeAtomicHelper is broken!", th);
            b.log(Level.SEVERE, "SafeAtomicHelper is broken!", th2);
        }
    }

    /* compiled from: AbstractFuture */
    /* access modifiers changed from: package-private */
    public static abstract class i<V> extends a<V> implements h<V> {
        i() {
        }

        @Override // com.google.common.util.concurrent.a, java.util.concurrent.Future
        public final V get() throws InterruptedException, ExecutionException {
            return (V) a.super.get();
        }

        @Override // com.google.common.util.concurrent.a, java.util.concurrent.Future
        public final V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return (V) a.super.get(j, timeUnit);
        }

        @Override // com.google.common.util.concurrent.a, java.util.concurrent.Future
        public final boolean isDone() {
            return a.super.isDone();
        }

        @Override // com.google.common.util.concurrent.a, java.util.concurrent.Future
        public final boolean isCancelled() {
            return a.super.isCancelled();
        }

        @Override // com.google.common.util.concurrent.a, com.google.common.util.concurrent.p
        public final void a(Runnable runnable, Executor executor) {
            a.super.a(runnable, executor);
        }

        @Override // com.google.common.util.concurrent.a, java.util.concurrent.Future
        public final boolean cancel(boolean z) {
            return a.super.cancel(z);
        }
    }

    /* compiled from: AbstractFuture */
    /* access modifiers changed from: private */
    public static final class k {
        static final k a = new k(false);
        volatile Thread b;
        volatile k c;

        k(boolean z) {
        }

        k() {
            a.c.a(this, Thread.currentThread());
        }

        /* access modifiers changed from: package-private */
        public void a(k kVar) {
            a.c.a(this, kVar);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            Thread thread = this.b;
            if (thread != null) {
                this.b = null;
                LockSupport.unpark(thread);
            }
        }
    }

    private void a(k kVar) {
        kVar.b = null;
        while (true) {
            k kVar2 = this.g;
            if (kVar2 != k.a) {
                k kVar3 = null;
                while (kVar2 != null) {
                    k kVar4 = kVar2.c;
                    if (kVar2.b != null) {
                        kVar3 = kVar2;
                    } else if (kVar3 != null) {
                        kVar3.c = kVar4;
                        if (kVar3.b == null) {
                        }
                    } else if (!c.a((a<?>) this, kVar2, kVar4)) {
                    }
                    kVar2 = kVar4;
                }
                return;
            }
            return;
        }
    }

    /* compiled from: AbstractFuture */
    /* access modifiers changed from: private */
    public static final class d {
        static final d a = new d(null, null);
        final Runnable b;
        final Executor c;
        d d;

        d(Runnable runnable, Executor executor) {
            this.b = runnable;
            this.c = executor;
        }
    }

    /* compiled from: AbstractFuture */
    /* access modifiers changed from: private */
    public static final class c {
        static final c a = new c(new AbstractFuture$Failure$1("Failure occurred while trying to finish a future."));
        final Throwable b;

        c(Throwable th) {
            this.b = (Throwable) m.a(th);
        }
    }

    /* compiled from: AbstractFuture */
    /* access modifiers changed from: private */
    public static final class b {
        static final b a;
        static final b b;
        final boolean c;
        final Throwable d;

        static {
            if (a.a) {
                b = null;
                a = null;
                return;
            }
            b = new b(false, null);
            a = new b(true, null);
        }

        b(boolean z, Throwable th) {
            this.c = z;
            this.d = th;
        }
    }

    /* compiled from: AbstractFuture */
    /* access modifiers changed from: private */
    public static final class f<V> implements Runnable {
        final a<V> a;
        final p<? extends V> b;

        f(a<V> aVar, p<? extends V> pVar) {
            this.a = aVar;
            this.b = pVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((a) this.a).e == this) {
                if (a.c.a((a<?>) this.a, (Object) this, a.c((p<?>) this.b))) {
                    a.e(this.a);
                }
            }
        }
    }

    protected a() {
    }

    @Override // java.util.concurrent.Future
    public V get(long j2, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        long nanos = timeUnit.toNanos(j2);
        if (!Thread.interrupted()) {
            Object obj = this.e;
            if ((obj != null) && (!(obj instanceof f))) {
                return (V) b(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0;
            if (nanos >= 1000) {
                k kVar = this.g;
                if (kVar != k.a) {
                    k kVar2 = new k();
                    do {
                        kVar2.a(kVar);
                        if (c.a((a<?>) this, kVar, kVar2)) {
                            do {
                                LockSupport.parkNanos(this, nanos);
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.e;
                                    if ((obj2 != null) && (!(obj2 instanceof f))) {
                                        return (V) b(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    a(kVar2);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            a(kVar2);
                        } else {
                            kVar = this.g;
                        }
                    } while (kVar != k.a);
                }
                return (V) b(this.e);
            }
            while (nanos > 0) {
                Object obj3 = this.e;
                if ((obj3 != null) && (!(obj3 instanceof f))) {
                    return (V) b(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String aVar = toString();
            String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
            String str = "Waited " + j2 + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + timeUnit.toString().toLowerCase(Locale.ROOT);
            if (nanos + 1000 < 0) {
                String str2 = str + " (plus ";
                long j3 = -nanos;
                long convert = timeUnit.convert(j3, TimeUnit.NANOSECONDS);
                long nanos2 = j3 - timeUnit.toNanos(convert);
                int i2 = (convert > 0 ? 1 : (convert == 0 ? 0 : -1));
                boolean z = i2 == 0 || nanos2 > 1000;
                if (i2 > 0) {
                    String str3 = str2 + convert + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + lowerCase;
                    if (z) {
                        str3 = str3 + Constants.ACCEPT_TIME_SEPARATOR_SP;
                    }
                    str2 = str3 + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER;
                }
                if (z) {
                    str2 = str2 + nanos2 + " nanoseconds ";
                }
                str = str2 + "delay)";
            }
            if (isDone()) {
                throw new TimeoutException(str + " but future completed as timeout expired");
            }
            throw new TimeoutException(str + " for " + aVar);
        }
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.Future
    public V get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.e;
            if ((obj2 != null) && (!(obj2 instanceof f))) {
                return (V) b(obj2);
            }
            k kVar = this.g;
            if (kVar != k.a) {
                k kVar2 = new k();
                do {
                    kVar2.a(kVar);
                    if (c.a((a<?>) this, kVar, kVar2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.e;
                            } else {
                                a(kVar2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof f))));
                        return (V) b(obj);
                    }
                    kVar = this.g;
                } while (kVar != k.a);
            }
            return (V) b(this.e);
        }
        throw new InterruptedException();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    private V b(Object obj) throws ExecutionException {
        if (obj instanceof b) {
            throw a("Task was cancelled.", ((b) obj).d);
        } else if (obj instanceof c) {
            throw new ExecutionException(((c) obj).b);
        } else if (obj == d) {
            return null;
        } else {
            return obj;
        }
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        Object obj = this.e;
        return (!(obj instanceof f)) & (obj != null);
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.e instanceof b;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        Object obj = this.e;
        if (!(obj == null) && !(obj instanceof f)) {
            return false;
        }
        b bVar = a ? new b(z, new CancellationException("Future.cancel() was called.")) : z ? b.a : b.b;
        Object obj2 = obj;
        boolean z2 = false;
        a<V> aVar = this;
        while (true) {
            if (c.a((a<?>) aVar, obj2, (Object) bVar)) {
                if (z) {
                    aVar.a();
                }
                e(aVar);
                if (!(obj2 instanceof f)) {
                    return true;
                }
                p<? extends V> pVar = ((f) obj2).b;
                if (pVar instanceof h) {
                    aVar = (a) pVar;
                    obj2 = aVar.e;
                    if (!(obj2 == null) && !(obj2 instanceof f)) {
                        return true;
                    }
                    z2 = true;
                } else {
                    pVar.cancel(z);
                    return true;
                }
            } else {
                obj2 = aVar.e;
                if (!(obj2 instanceof f)) {
                    return z2;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean b() {
        Object obj = this.e;
        return (obj instanceof b) && ((b) obj).c;
    }

    @Override // com.google.common.util.concurrent.p
    public void a(Runnable runnable, Executor executor) {
        d dVar;
        m.a(runnable, "Runnable was null.");
        m.a(executor, "Executor was null.");
        if (isDone() || (dVar = this.f) == d.a) {
            b(runnable, executor);
        }
        d dVar2 = new d(runnable, executor);
        do {
            dVar2.d = dVar;
            if (!c.a((a<?>) this, dVar, dVar2)) {
                dVar = this.f;
            } else {
                return;
            }
        } while (dVar != d.a);
        b(runnable, executor);
    }

    /* access modifiers changed from: protected */
    public boolean a(V v) {
        if (v == null) {
            v = (V) d;
        }
        if (!c.a((a<?>) this, (Object) null, (Object) v)) {
            return false;
        }
        e(this);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(Throwable th) {
        if (!c.a((a<?>) this, (Object) null, (Object) new c((Throwable) m.a(th)))) {
            return false;
        }
        e(this);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean a(p<? extends V> pVar) {
        f fVar;
        c cVar;
        m.a(pVar);
        Object obj = this.e;
        if (obj == null) {
            if (pVar.isDone()) {
                if (!c.a((a<?>) this, (Object) null, c((p<?>) pVar))) {
                    return false;
                }
                e(this);
                return true;
            }
            fVar = new f(this, pVar);
            if (c.a((a<?>) this, (Object) null, (Object) fVar)) {
                try {
                    pVar.a(fVar, DirectExecutor.INSTANCE);
                } catch (Throwable unused) {
                    cVar = c.a;
                }
                return true;
            }
            obj = this.e;
        }
        if (obj instanceof b) {
            pVar.cancel(((b) obj).c);
        }
        return false;
        c.a((a<?>) this, (Object) fVar, (Object) cVar);
        return true;
    }

    /* access modifiers changed from: private */
    public static Object c(p<?> pVar) {
        Throwable a2;
        if (pVar instanceof h) {
            Object obj = ((a) pVar).e;
            if (!(obj instanceof b)) {
                return obj;
            }
            b bVar = (b) obj;
            if (bVar.c) {
                return bVar.d != null ? new b(false, bVar.d) : b.b;
            }
            return obj;
        } else if ((pVar instanceof com.google.common.util.concurrent.a.a) && (a2 = b.a((com.google.common.util.concurrent.a.a) pVar)) != null) {
            return new c(a2);
        } else {
            boolean isCancelled = pVar.isCancelled();
            if ((!a) && isCancelled) {
                return b.b;
            }
            try {
                Object b2 = b((Future<Object>) pVar);
                if (isCancelled) {
                    return new b(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + pVar));
                } else if (b2 == null) {
                    return d;
                } else {
                    return b2;
                }
            } catch (ExecutionException e2) {
                if (!isCancelled) {
                    return new c(e2.getCause());
                }
                return new b(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + pVar, e2));
            } catch (CancellationException e3) {
                if (isCancelled) {
                    return new b(false, e3);
                }
                return new c(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + pVar, e3));
            } catch (Throwable th) {
                return new c(th);
            }
        }
    }

    private static <V> V b(Future<V> future) throws ExecutionException {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:13:0x0001 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.google.common.util.concurrent.a<?>] */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.common.util.concurrent.a] */
    /* JADX WARN: Type inference failed for: r4v6, types: [com.google.common.util.concurrent.a, com.google.common.util.concurrent.a<V>] */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.google.common.util.concurrent.a$a] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void e(com.google.common.util.concurrent.a<?> r4) {
        /*
            r0 = 0
        L_0x0001:
            r4.h()
            r4.c()
            com.google.common.util.concurrent.a$d r4 = r4.a(r0)
        L_0x000b:
            if (r4 == 0) goto L_0x0033
            com.google.common.util.concurrent.a$d r0 = r4.d
            java.lang.Runnable r1 = r4.b
            boolean r2 = r1 instanceof com.google.common.util.concurrent.a.f
            if (r2 == 0) goto L_0x002c
            com.google.common.util.concurrent.a$f r1 = (com.google.common.util.concurrent.a.f) r1
            com.google.common.util.concurrent.a<V> r4 = r1.a
            java.lang.Object r2 = r4.e
            if (r2 != r1) goto L_0x0031
            com.google.common.util.concurrent.p<? extends V> r2 = r1.b
            java.lang.Object r2 = c(r2)
            com.google.common.util.concurrent.a$a r3 = com.google.common.util.concurrent.a.c
            boolean r1 = r3.a(r4, r1, r2)
            if (r1 == 0) goto L_0x0031
            goto L_0x0001
        L_0x002c:
            java.util.concurrent.Executor r4 = r4.c
            b(r1, r4)
        L_0x0031:
            r4 = r0
            goto L_0x000b
        L_0x0033:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.a.e(com.google.common.util.concurrent.a):void");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.a.a
    public final Throwable d() {
        if (!(this instanceof h)) {
            return null;
        }
        Object obj = this.e;
        if (obj instanceof c) {
            return ((c) obj).b;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void a(Future<?> future) {
        if ((future != null) && isCancelled()) {
            future.cancel(b());
        }
    }

    private void h() {
        k kVar;
        do {
            kVar = this.g;
        } while (!c.a((a<?>) this, kVar, k.a));
        while (kVar != null) {
            kVar.a();
            kVar = kVar.c;
        }
    }

    private d a(d dVar) {
        d dVar2;
        do {
            dVar2 = this.f;
        } while (!c.a((a<?>) this, dVar2, d.a));
        d dVar3 = dVar;
        d dVar4 = dVar2;
        while (dVar4 != null) {
            d dVar5 = dVar4.d;
            dVar4.d = dVar3;
            dVar3 = dVar4;
            dVar4 = dVar5;
        }
        return dVar3;
    }

    @Override // java.lang.Object
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (isCancelled()) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            a(sb);
        } else {
            try {
                str = e();
            } catch (RuntimeException e2) {
                str = "Exception thrown from implementation: " + e2.getClass();
            }
            if (str != null && !str.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(str);
                sb.append("]");
            } else if (isDone()) {
                a(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String e() {
        Object obj = this.e;
        if (obj instanceof f) {
            return "setFuture=[" + c((Object) ((f) obj).b) + "]";
        } else if (!(this instanceof ScheduledFuture)) {
            return null;
        } else {
            return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
        }
    }

    private void a(StringBuilder sb) {
        try {
            Object b2 = b((Future<Object>) this);
            sb.append("SUCCESS, result=[");
            sb.append(c(b2));
            sb.append("]");
        } catch (ExecutionException e2) {
            sb.append("FAILURE, cause=[");
            sb.append(e2.getCause());
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e3) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e3.getClass());
            sb.append(" thrown from get()]");
        }
    }

    private String c(Object obj) {
        return obj == this ? "this future" : String.valueOf(obj);
    }

    private static void b(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e2) {
            Logger logger = b;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e2);
        }
    }

    /* compiled from: AbstractFuture */
    /* access modifiers changed from: private */
    /* renamed from: com.google.common.util.concurrent.a$a  reason: collision with other inner class name */
    public static abstract class AbstractC0111a {
        /* access modifiers changed from: package-private */
        public abstract void a(k kVar, k kVar2);

        /* access modifiers changed from: package-private */
        public abstract void a(k kVar, Thread thread);

        /* access modifiers changed from: package-private */
        public abstract boolean a(a<?> aVar, d dVar, d dVar2);

        /* access modifiers changed from: package-private */
        public abstract boolean a(a<?> aVar, k kVar, k kVar2);

        /* access modifiers changed from: package-private */
        public abstract boolean a(a<?> aVar, Object obj, Object obj2);

        private AbstractC0111a() {
        }
    }

    /* compiled from: AbstractFuture */
    private static final class j extends AbstractC0111a {
        static final Unsafe a;
        static final long b;
        static final long c;
        static final long d;
        static final long e;
        static final long f;

        private j() {
            super();
        }

        static {
            Unsafe unsafe;
            try {
                unsafe = Unsafe.getUnsafe();
            } catch (SecurityException unused) {
                try {
                    unsafe = (Unsafe) AccessController.doPrivileged(new AnonymousClass1());
                } catch (PrivilegedActionException e2) {
                    throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
                }
            }
            try {
                c = unsafe.objectFieldOffset(a.class.getDeclaredField("waiters"));
                b = unsafe.objectFieldOffset(a.class.getDeclaredField("listeners"));
                d = unsafe.objectFieldOffset(a.class.getDeclaredField("value"));
                e = unsafe.objectFieldOffset(k.class.getDeclaredField("b"));
                f = unsafe.objectFieldOffset(k.class.getDeclaredField("c"));
                a = unsafe;
            } catch (Exception e3) {
                r.a(e3);
                throw new RuntimeException(e3);
            }
        }

        /* compiled from: AbstractFuture */
        /* renamed from: com.google.common.util.concurrent.a$j$1  reason: invalid class name */
        static class AnonymousClass1 implements PrivilegedExceptionAction<Unsafe> {
            AnonymousClass1() {
            }

            /* renamed from: a */
            public Unsafe run() throws Exception {
                Field[] declaredFields = Unsafe.class.getDeclaredFields();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    Object obj = field.get(null);
                    if (Unsafe.class.isInstance(obj)) {
                        return (Unsafe) Unsafe.class.cast(obj);
                    }
                }
                throw new NoSuchFieldError("the Unsafe");
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public void a(k kVar, Thread thread) {
            a.putObject(kVar, e, thread);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public void a(k kVar, k kVar2) {
            a.putObject(kVar, f, kVar2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public boolean a(a<?> aVar, k kVar, k kVar2) {
            return a.compareAndSwapObject(aVar, c, kVar, kVar2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public boolean a(a<?> aVar, d dVar, d dVar2) {
            return a.compareAndSwapObject(aVar, b, dVar, dVar2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public boolean a(a<?> aVar, Object obj, Object obj2) {
            return a.compareAndSwapObject(aVar, d, obj, obj2);
        }
    }

    /* compiled from: AbstractFuture */
    private static final class e extends AbstractC0111a {
        final AtomicReferenceFieldUpdater<k, Thread> a;
        final AtomicReferenceFieldUpdater<k, k> b;
        final AtomicReferenceFieldUpdater<a, k> c;
        final AtomicReferenceFieldUpdater<a, d> d;
        final AtomicReferenceFieldUpdater<a, Object> e;

        e(AtomicReferenceFieldUpdater<k, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<k, k> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<a, k> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<a, d> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<a, Object> atomicReferenceFieldUpdater5) {
            super();
            this.a = atomicReferenceFieldUpdater;
            this.b = atomicReferenceFieldUpdater2;
            this.c = atomicReferenceFieldUpdater3;
            this.d = atomicReferenceFieldUpdater4;
            this.e = atomicReferenceFieldUpdater5;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public void a(k kVar, Thread thread) {
            this.a.lazySet(kVar, thread);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public void a(k kVar, k kVar2) {
            this.b.lazySet(kVar, kVar2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public boolean a(a<?> aVar, k kVar, k kVar2) {
            return this.c.compareAndSet(aVar, kVar, kVar2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public boolean a(a<?> aVar, d dVar, d dVar2) {
            return this.d.compareAndSet(aVar, dVar, dVar2);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public boolean a(a<?> aVar, Object obj, Object obj2) {
            return this.e.compareAndSet(aVar, obj, obj2);
        }
    }

    /* compiled from: AbstractFuture */
    private static final class g extends AbstractC0111a {
        private g() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public void a(k kVar, Thread thread) {
            kVar.b = thread;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public void a(k kVar, k kVar2) {
            kVar.c = kVar2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public boolean a(a<?> aVar, k kVar, k kVar2) {
            synchronized (aVar) {
                if (((a) aVar).g != kVar) {
                    return false;
                }
                ((a) aVar).g = kVar2;
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public boolean a(a<?> aVar, d dVar, d dVar2) {
            synchronized (aVar) {
                if (((a) aVar).f != dVar) {
                    return false;
                }
                ((a) aVar).f = dVar2;
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.a.AbstractC0111a
        public boolean a(a<?> aVar, Object obj, Object obj2) {
            synchronized (aVar) {
                if (((a) aVar).e != obj) {
                    return false;
                }
                ((a) aVar).e = obj2;
                return true;
            }
        }
    }

    private static CancellationException a(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }
}
