package com.umeng.message.inapp;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/* compiled from: UmengCountDownTimer */
abstract class c {
    private static final String a = c.class.getName();
    private static final int g = 1;
    private static final int h = 2;
    private final long b;
    private final long c;
    private long d;
    private long e;
    private boolean f = false;
    private Handler i = new AnonymousClass1();

    public abstract void a(long j);

    public abstract void e();

    public c(long j, long j2) {
        this.b = j;
        this.c = j2;
    }

    public final synchronized void a() {
        this.f = true;
        this.i.removeMessages(1);
    }

    public final synchronized c b() {
        this.f = false;
        if (this.b <= 0) {
            e();
            return this;
        }
        this.d = SystemClock.elapsedRealtime() + this.b;
        this.i.sendMessage(this.i.obtainMessage(1));
        return this;
    }

    public final synchronized c c() {
        this.f = false;
        this.e = this.d - SystemClock.elapsedRealtime();
        if (this.e <= 0) {
            return this;
        }
        this.i.removeMessages(1);
        this.i.sendMessageAtFrontOfQueue(this.i.obtainMessage(2));
        return this;
    }

    public final synchronized c d() {
        this.f = false;
        if (this.e <= 0) {
            return this;
        }
        this.i.removeMessages(2);
        this.d = this.e + SystemClock.elapsedRealtime();
        this.i.sendMessageAtFrontOfQueue(this.i.obtainMessage(1));
        return this;
    }

    /* compiled from: UmengCountDownTimer */
    /* renamed from: com.umeng.message.inapp.c$1  reason: invalid class name */
    class AnonymousClass1 extends Handler {
        AnonymousClass1() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            synchronized (c.this) {
                if (message.what == 1) {
                    if (!c.this.f) {
                        long elapsedRealtime = c.this.d - SystemClock.elapsedRealtime();
                        if (elapsedRealtime <= 0) {
                            c.this.e();
                        } else if (elapsedRealtime < c.this.c) {
                            c.this.a(elapsedRealtime);
                            sendMessageDelayed(obtainMessage(1), elapsedRealtime);
                        } else {
                            long elapsedRealtime2 = SystemClock.elapsedRealtime();
                            c.this.a(elapsedRealtime);
                            long elapsedRealtime3 = (elapsedRealtime2 + c.this.c) - SystemClock.elapsedRealtime();
                            while (elapsedRealtime3 < 0) {
                                elapsedRealtime3 += c.this.c;
                            }
                            sendMessageDelayed(obtainMessage(1), elapsedRealtime3);
                        }
                    }
                }
            }
        }
    }
}
