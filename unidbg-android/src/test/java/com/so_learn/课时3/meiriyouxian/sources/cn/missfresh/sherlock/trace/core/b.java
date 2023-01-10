package cn.missfresh.sherlock.trace.core;

import android.os.Looper;
import android.os.SystemClock;
import android.view.Choreographer;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.j;
import cn.missfresh.sherlock.trace.core.a;
import cn.missfresh.sherlock.trace.f.c;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: UIThreadMonitor */
public class b implements Runnable {
    private static final b a = new b();
    private HashSet<c> b = new HashSet<>();
    private volatile boolean c = false;
    private long[] d = new long[4];
    private volatile long e = 0;
    private boolean f = false;
    private Object g;
    private Object[] h;
    private Method i;
    private Choreographer j;
    private long k = 16666666;
    private boolean l = false;
    private boolean m = false;

    /* compiled from: UIThreadMonitor */
    /* access modifiers changed from: package-private */
    public class a extends a.b {
        a() {
        }

        @Override // cn.missfresh.sherlock.trace.core.a.b
        public boolean a() {
            return b.this.c;
        }

        @Override // cn.missfresh.sherlock.trace.core.a.b
        public void b() {
            super.b();
            b.this.g();
        }

        @Override // cn.missfresh.sherlock.trace.core.a.b
        public void c() {
            super.c();
            b.this.h();
        }
    }

    public static b a() {
        return a;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void g() {
        long[] jArr = this.d;
        long uptimeMillis = SystemClock.uptimeMillis();
        jArr[0] = uptimeMillis;
        this.e = uptimeMillis;
        this.d[2] = SystemClock.currentThreadTimeMillis();
        synchronized (this.b) {
            Iterator<c> it2 = this.b.iterator();
            while (it2.hasNext()) {
                c next = it2.next();
                if (!next.a()) {
                    next.a(this.d[0], this.d[2], this.e);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void h() {
        if (this.f) {
            a(this.e);
        }
        char c = 3;
        this.d[3] = SystemClock.currentThreadTimeMillis();
        char c2 = 1;
        this.d[1] = SystemClock.uptimeMillis();
        synchronized (this.b) {
            Iterator<c> it2 = this.b.iterator();
            while (it2.hasNext()) {
                c next = it2.next();
                if (next.a()) {
                    next.a(this.d[0], this.d[2], this.d[c2], this.d[c], this.e);
                    c = 3;
                    c2 = 1;
                }
            }
        }
    }

    private void i() {
        synchronized (this) {
            this.l = false;
        }
    }

    public synchronized void d() {
        if (!this.m) {
            throw new RuntimeException("never init!");
        } else if (!this.c) {
            j.b("UIThreadMonitor", "onStart");
            this.c = true;
            a(this, true);
        }
    }

    public synchronized void e() {
        if (!this.m) {
            throw new RuntimeException("UIThreadMonitor is never init!");
        } else if (this.c) {
            this.c = false;
            j.b("UIThreadMonitor", String.format("[onStop] %s", Utils.f()));
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f = true;
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0044: APUT  
      (r4v1 java.lang.Object[])
      (0 ??[int, short, byte, char])
      (wrap: java.lang.Long : 0x0040: INVOKE  (r9v2 java.lang.Long) = (r5v0 long) type: STATIC call: java.lang.Long.valueOf(long):java.lang.Long)
     */
    private synchronized void a(Runnable runnable, boolean z) {
        if (this.l) {
            j.b("UIThreadMonitor", "input type callback has exist!");
        } else if (!this.c) {
            j.b("UIThreadMonitor", "is not alive!");
        } else {
            try {
                if (this.g == null) {
                    this.g = new Object();
                }
                synchronized (this.g) {
                    Method method = this.i;
                    Object obj = this.h[0];
                    Object[] objArr = new Object[3];
                    objArr[0] = Long.valueOf(!z ? SystemClock.uptimeMillis() : -1);
                    objArr[1] = runnable;
                    objArr[2] = null;
                    method.invoke(obj, objArr);
                    this.l = true;
                }
            } catch (Exception e) {
                j.e("UIThreadMonitor", e.toString());
            }
        }
    }

    public boolean b() {
        return this.m;
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0076: APUT  
      (r1v15 java.lang.Object[])
      (0 ??[int, short, byte, char])
      (wrap: java.lang.Boolean : 0x0072: INVOKE  (r5v4 java.lang.Boolean) = (r5v3 boolean) type: STATIC call: java.lang.Boolean.valueOf(boolean):java.lang.Boolean)
     */
    public void c() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            boolean z = true;
            this.m = true;
            this.j = Choreographer.getInstance();
            this.g = Utils.a(this.j, "mLock");
            this.h = (Object[]) Utils.a(this.j, "mCallbackQueues");
            Object[] objArr = this.h;
            if (objArr != null) {
                this.i = a(objArr[0], "addCallbackLocked", Long.TYPE, Object.class, Object.class);
            }
            this.k = ((Long) Utils.a(this.j, "mFrameIntervalNanos")).longValue();
            a.a(new a());
            Object[] objArr2 = new Object[4];
            objArr2[0] = Boolean.valueOf(this.g == null);
            objArr2[1] = Boolean.valueOf(this.h == null);
            if (this.i != null) {
                z = false;
            }
            objArr2[2] = Boolean.valueOf(z);
            objArr2[3] = Long.valueOf(this.k);
            j.b("UIThreadMonitor", String.format("[UIThreadMonitor] %s %s %s frameIntervalNanos: %s", objArr2));
            return;
        }
        throw new AssertionError("must be init in main thread!");
    }

    public long f() {
        return this.k;
    }

    public void a(c cVar) {
        if (!this.c) {
            d();
        }
        synchronized (this.b) {
            this.b.add(cVar);
        }
    }

    private Method a(Object obj, String str, Class<?>... clsArr) {
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Exception e) {
            j.e("UIThreadMonitor", e.toString());
            return null;
        }
    }

    private void a(long j) {
        i();
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this.b) {
            Iterator<c> it2 = this.b.iterator();
            while (it2.hasNext()) {
                c next = it2.next();
                if (next.a()) {
                    next.a(cn.missfresh.sherlock.d.a.b(), j, uptimeMillis, uptimeMillis - j);
                }
            }
        }
        a(this, true);
        this.f = false;
    }

    public void b(c cVar) {
        synchronized (this.b) {
            this.b.remove(cVar);
            if (this.b.isEmpty()) {
                e();
            }
        }
    }
}
