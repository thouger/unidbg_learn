package cn.missfresh.sherlock.trace;

import android.app.Application;
import android.os.Build;
import android.os.Looper;
import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.tool.g;
import cn.missfresh.sherlock.tool.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.sherlock.trace.f.e;
import cn.missfresh.sherlock.trace.tracer.FrameTracer;
import cn.missfresh.sherlock.trace.tracer.f;

/* compiled from: TracePlugin */
public class b extends cn.missfresh.sherlock.a.a.a {
    private static volatile b a;
    private final cn.missfresh.sherlock.trace.a.a b;
    private cn.missfresh.sherlock.trace.tracer.c c;
    private f d;
    private FrameTracer e;
    private cn.missfresh.sherlock.trace.tracer.a f;

    /* compiled from: TracePlugin */
    /* access modifiers changed from: package-private */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (!cn.missfresh.sherlock.trace.core.b.a().b()) {
                    cn.missfresh.sherlock.trace.core.b.a().c();
                }
                cn.missfresh.sherlock.trace.core.b.a().d();
                if (Config.getInstance().enableSlowFunctionSwitch()) {
                    AppMethodBeat.getInstance().onStart();
                    b.this.c.d();
                } else {
                    j.b("TracePlugin", "sherlock slow function disable");
                }
                b.this.f.d();
                if (Config.getInstance().enableFpsSwitch()) {
                    b.this.e.d();
                } else {
                    j.b("TracePlugin", "sherlock fps function disable");
                }
            } catch (RuntimeException e) {
                j.d("TracePlugin", "[start] RuntimeException:%s", e);
            }
        }
    }

    /* compiled from: TracePlugin */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.sherlock.trace.b$b  reason: collision with other inner class name */
    public class RunnableC0048b implements Runnable {
        RunnableC0048b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.d.d();
        }
    }

    /* compiled from: TracePlugin */
    /* access modifiers changed from: package-private */
    public class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                AppMethodBeat.getInstance().onStop();
                cn.missfresh.sherlock.trace.core.b.a().e();
                b.this.f.e();
                b.this.e.e();
                b.this.c.e();
                b.this.d.e();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private b(cn.missfresh.sherlock.trace.a.a aVar, Application application) {
        this.b = aVar;
        a(application, new e(application));
    }

    public static b i() {
        if (a != null) {
            return a;
        }
        throw new RuntimeException("you must init TracePlugin first");
    }

    @Override // cn.missfresh.sherlock.a.a.a
    public void b() {
        super.b();
        if (!g()) {
            j.d("TracePlugin", "[start] Plugin is unSupported!");
            return;
        }
        a aVar = new a();
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            aVar.run();
            return;
        }
        j.d("TracePlugin", String.format("start TracePlugin in Thread[%s] but not in mainThread!", Long.valueOf(Thread.currentThread().getId())));
        g.a().post(aVar);
    }

    @Override // cn.missfresh.sherlock.a.a.a
    public void c() {
        super.c();
        if (!g()) {
            j.d("TracePlugin", "[stop] Plugin is unSupported!");
            return;
        }
        j.d("TracePlugin", "stop!");
        c cVar = new c();
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            cVar.run();
            return;
        }
        j.d("TracePlugin", String.format("stop TracePlugin in Thread[%s] but not in mainThread!", Long.valueOf(Thread.currentThread().getId())));
        g.a().post(cVar);
    }

    @Override // cn.missfresh.sherlock.a.a.a
    public String d() {
        return "Trace";
    }

    public void j() {
        if (!g()) {
            j.d("TracePlugin", "[startStartUpTracer] Plugin is unSupported!");
            return;
        }
        j.d("TracePlugin", "startStartUpTracer!");
        RunnableC0048b bVar = new RunnableC0048b();
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            bVar.run();
            return;
        }
        j.d("TracePlugin", String.format("startStartUpTracer TracePlugin in Thread[%s] but not in mainThread!", Long.valueOf(Thread.currentThread().getId())));
        g.a().post(bVar);
    }

    public static b a(cn.missfresh.sherlock.trace.a.a aVar, Application application) {
        synchronized (b.class) {
            if (a == null) {
                a = new b(aVar, application);
            } else {
                j.e("TracePlugin", "TracePlugin instance is already set. this invoking will be ignored");
            }
        }
        return a;
    }

    @Override // cn.missfresh.sherlock.a.a.a
    public void a(Application application, cn.missfresh.sherlock.a.a.b bVar) {
        super.a(application, bVar);
        j.a("TracePlugin", String.format("trace plugin init, trace config: %s", this.b.toString()));
        if (Build.VERSION.SDK_INT < 21) {
            j.e("TracePlugin", "[FrameBeat] API is low Build.VERSION_CODES.JELLY_BEAN(16), TracePlugin is not supported");
            h();
            return;
        }
        this.f = new cn.missfresh.sherlock.trace.tracer.a(this.b);
        this.e = new FrameTracer(this.b);
        this.c = new cn.missfresh.sherlock.trace.tracer.c(this.b);
        this.d = new f(this.b);
    }

    @Override // cn.missfresh.sherlock.a.a.a, cn.missfresh.sherlock.trace.tracer.d
    public void a(boolean z) {
        FrameTracer frameTracer;
        super.a(z);
        if (g() && (frameTracer = this.e) != null) {
            frameTracer.a(z);
        }
    }
}
