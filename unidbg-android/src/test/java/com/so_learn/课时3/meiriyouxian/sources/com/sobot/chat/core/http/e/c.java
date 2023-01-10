package com.sobot.chat.core.http.e;

/* compiled from: PriorityRunnable */
public class c extends b<Runnable> implements Runnable {
    public c(int i, Runnable runnable) {
        super(i, runnable);
    }

    @Override // java.lang.Runnable
    public void run() {
        ((Runnable) this.b).run();
    }
}
