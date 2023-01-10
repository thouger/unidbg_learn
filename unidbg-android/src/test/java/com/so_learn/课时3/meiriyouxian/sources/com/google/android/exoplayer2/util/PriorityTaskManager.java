package com.google.android.exoplayer2.util;

import java.io.IOException;
import java.util.PriorityQueue;

public final class PriorityTaskManager {
    private final Object a;
    private final PriorityQueue<Integer> b;
    private int c;

    public static class PriorityTooLowException extends IOException {
        public PriorityTooLowException(int i, int i2) {
            super("Priority too low [priority=" + i + ", highest=" + i2 + "]");
        }
    }

    public void a(int i) {
        synchronized (this.a) {
            this.b.add(Integer.valueOf(i));
            this.c = Math.max(this.c, i);
        }
    }

    public void b(int i) {
        synchronized (this.a) {
            this.b.remove(Integer.valueOf(i));
            this.c = this.b.isEmpty() ? Integer.MIN_VALUE : ((Integer) z.a(this.b.peek())).intValue();
            this.a.notifyAll();
        }
    }
}
