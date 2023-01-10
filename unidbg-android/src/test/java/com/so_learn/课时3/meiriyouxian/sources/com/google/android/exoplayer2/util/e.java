package com.google.android.exoplayer2.util;

/* compiled from: ConditionVariable */
public final class e {
    private boolean a;

    public synchronized boolean a() {
        if (this.a) {
            return false;
        }
        this.a = true;
        notifyAll();
        return true;
    }

    public synchronized boolean b() {
        boolean z;
        z = this.a;
        this.a = false;
        return z;
    }

    public synchronized void c() throws InterruptedException {
        while (!this.a) {
            wait();
        }
    }
}
