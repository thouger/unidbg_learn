package com.vivo.push;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.umeng.message.proguard.l;
import com.vivo.push.util.n;

/* compiled from: Worker */
public abstract class i {
    protected Context a;
    protected Handler b;
    private final Object c = new Object();

    public abstract void b(Message message);

    public i() {
        HandlerThread handlerThread = new HandlerThread(getClass().getSimpleName(), 1);
        handlerThread.start();
        this.b = new a(handlerThread.getLooper());
    }

    public final void a(Context context) {
        this.a = context;
    }

    public final void a(Message message) {
        synchronized (this.c) {
            if (this.b == null) {
                String simpleName = getClass().getSimpleName();
                n.e(simpleName, ("Dead worker dropping a message: " + message.what) + " (Thread " + Thread.currentThread().getId() + l.t);
            } else {
                this.b.sendMessage(message);
            }
        }
    }

    /* compiled from: Worker */
    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            AppMethodBeat.i(2943, false);
            i.this.b(message);
            AppMethodBeat.o(2943);
        }
    }
}
