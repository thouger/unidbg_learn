package cn.missfresh.sherlock.trace.tracer;

import cn.missfresh.sherlock.tool.j;
import cn.missfresh.sherlock.trace.f.c;

/* compiled from: Tracer */
public abstract class h extends c implements e {
    private volatile boolean a = false;
    private volatile boolean b;

    @Override // cn.missfresh.sherlock.trace.tracer.d
    public void a(boolean z) {
        this.b = z;
    }

    /* access modifiers changed from: protected */
    public void b() {
        j.b("Tracer", "[onAlive] " + getClass().getName());
    }

    /* access modifiers changed from: protected */
    public void c() {
        j.b("Tracer", "[onDead] " + getClass().getName());
    }

    public final synchronized void d() {
        if (!this.a) {
            this.a = true;
            b();
        }
    }

    public final synchronized void e() {
        if (this.a) {
            this.a = false;
            c();
        }
    }

    public boolean f() {
        return this.b;
    }
}
