package cn.missfresh.module.base.common.providers;

import android.os.Handler;

/* compiled from: TimerService */
public abstract class e implements Runnable {
    private int a = 1000;
    private int b;
    private boolean c = false;
    private Handler d = new Handler();

    public abstract void a(int i);

    public void d() {
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (e.class) {
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

    public synchronized void b() {
        this.d.postDelayed(this, (long) this.a);
        this.c = true;
    }

    public synchronized void c() {
        this.c = false;
        this.d.removeCallbacks(this);
        d();
    }

    public void b(int i) {
        if (i <= 0) {
            i = 1000;
        }
        this.a = i;
    }
}
