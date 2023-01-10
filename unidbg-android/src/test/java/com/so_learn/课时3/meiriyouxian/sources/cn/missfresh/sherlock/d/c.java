package cn.missfresh.sherlock.d;

import cn.missfresh.sherlock.b.a;
import cn.missfresh.sherlock.tool.j;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.Util;

/* compiled from: Dispatcher */
public final class c implements d {
    private ExecutorService a;

    private ExecutorService a() {
        if (this.a == null) {
            this.a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("Sherlock Dispatcher", false));
        }
        return this.a;
    }

    public void a(b bVar) {
        if (bVar != null && bVar.b() != null) {
            j.b("Dispatcher", "enqueue async sender, sender size: " + bVar.b().getData().size() + ", fromDB :" + bVar.a());
            bVar.a(true);
            a().execute(bVar);
        }
    }

    @Override // cn.missfresh.sherlock.d.d
    public void b(b bVar) {
        j.b("Dispatcher", "success");
    }

    public void a(Runnable runnable) {
        if (runnable != null) {
            j.b("Dispatcher", "save data to db");
            a().execute(runnable);
        }
    }

    @Override // cn.missfresh.sherlock.d.d
    public void c(b bVar) {
        j.b("Dispatcher", "failure");
        a.a().a(bVar.b());
    }

    @Override // cn.missfresh.sherlock.d.d
    public void a(b bVar, Exception exc) {
        j.b("Dispatcher", "exception message: " + exc.getMessage());
        a.a().a(bVar.b());
    }

    public void b(Runnable runnable) {
        if (runnable != null) {
            j.b("Dispatcher", "exec data");
            a().execute(runnable);
        }
    }
}
