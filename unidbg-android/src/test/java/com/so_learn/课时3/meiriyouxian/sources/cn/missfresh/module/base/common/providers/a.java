package cn.missfresh.module.base.common.providers;

import android.os.Handler;

/* compiled from: ClockService */
public abstract class a implements d, Runnable {
    private int a = 1000;
    private long b;
    private boolean c = false;
    private Handler d = new Handler();

    public abstract void a(long j);

    @Override // java.lang.Runnable
    public void run() {
        synchronized (a.class) {
            if (this.c) {
                this.b++;
                a(this.b);
                a();
            }
        }
    }

    public synchronized void a() {
        this.d.postDelayed(this, (long) this.a);
    }

    @Override // cn.missfresh.module.base.common.providers.d
    public void b(long j) {
        this.b = j;
    }
}
