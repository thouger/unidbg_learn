package com.vivo.push;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.util.n;

/* compiled from: PushClientThread */
public final class f {
    private static final Handler a = new Handler(Looper.getMainLooper());
    private static final HandlerThread b;
    private static final Handler c = new AnonymousClass1(b.getLooper());

    static {
        AppMethodBeat.i(2788, false);
        HandlerThread handlerThread = new HandlerThread("push_client_thread");
        b = handlerThread;
        handlerThread.start();
        AppMethodBeat.o(2788);
    }

    /* compiled from: PushClientThread */
    /* renamed from: com.vivo.push.f$1  reason: invalid class name */
    static class AnonymousClass1 extends Handler {
        AnonymousClass1(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            AppMethodBeat.i(2767, false);
            Object obj = message.obj;
            if (obj instanceof e) {
                e eVar = (e) obj;
                n.c("PushClientThread", "PushClientThread-handleMessage, task = " + eVar);
                eVar.run();
            }
            AppMethodBeat.o(2767);
        }
    }

    public static void a(e eVar) {
        AppMethodBeat.i(2776, false);
        if (eVar == null) {
            n.a("PushClientThread", "client thread error, task is null!");
            AppMethodBeat.o(2776);
            return;
        }
        int i = eVar.b;
        Message message = new Message();
        message.what = i;
        message.obj = eVar;
        c.sendMessageDelayed(message, 0);
        AppMethodBeat.o(2776);
    }

    public static void a(Runnable runnable) {
        AppMethodBeat.i(2780, false);
        c.removeCallbacks(runnable);
        c.postDelayed(runnable, 15000);
        AppMethodBeat.o(2780);
    }

    public static void b(Runnable runnable) {
        AppMethodBeat.i(2784, false);
        a.post(runnable);
        AppMethodBeat.o(2784);
    }
}
