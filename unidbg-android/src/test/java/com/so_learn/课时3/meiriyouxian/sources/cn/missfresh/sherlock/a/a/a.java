package cn.missfresh.sherlock.a.a;

import android.app.Application;
import cn.missfresh.sherlock.trace.AppActiveMatrixDelegate;
import cn.missfresh.sherlock.trace.tracer.d;

/* compiled from: Plugin */
public abstract class a implements d {
    private b a;
    private Application b;
    private boolean c = true;
    private int d = 0;

    public void a(Application application, b bVar) {
        if (this.b == null && this.a == null) {
            this.d = 1;
            this.b = application;
            this.a = bVar;
            AppActiveMatrixDelegate.INSTANCE.init(this.b);
            return;
        }
        throw new RuntimeException("plugin duplicate init, application or plugin listener is not null");
    }

    @Override // cn.missfresh.sherlock.trace.tracer.d
    public void a(boolean z) {
    }

    public void b() {
        if (f()) {
            throw new RuntimeException("plugin start, but plugin has been already destroyed");
        } else if (!e()) {
            this.d = 2;
            b bVar = this.a;
            if (bVar != null) {
                bVar.a(this);
                return;
            }
            throw new RuntimeException("plugin start, plugin listener is null");
        } else {
            throw new RuntimeException("plugin start, but plugin has been already started");
        }
    }

    public void c() {
        if (f()) {
            throw new RuntimeException("plugin stop, but plugin has been already destroyed");
        } else if (e()) {
            this.d = 4;
            b bVar = this.a;
            if (bVar != null) {
                bVar.b(this);
                return;
            }
            throw new RuntimeException("plugin stop, plugin listener is null");
        } else {
            throw new RuntimeException("plugin stop, but plugin is never started");
        }
    }

    public abstract String d();

    public boolean e() {
        return this.d == 2;
    }

    public boolean f() {
        return this.d == 8;
    }

    public boolean g() {
        return this.c;
    }

    public void h() {
        this.c = false;
    }

    public Application a() {
        return this.b;
    }
}
