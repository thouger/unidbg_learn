package cn.missfresh.sherlock.d;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import cn.missfresh.sherlock.c;
import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.tool.j;

/* compiled from: TimerManager */
public class e {
    private boolean a;
    private HandlerThread b;
    private Handler c;

    /* compiled from: TimerManager */
    /* access modifiers changed from: package-private */
    public class a extends Handler {
        final /* synthetic */ long a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        a(Looper looper, long j) {
            super(looper);
            this.a = j;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message != null) {
                try {
                    Thread.sleep(this.a);
                    if (!e.this.a) {
                        c.a().b();
                    }
                    sendMessage(Message.obtain());
                } catch (InterruptedException unused) {
                    j.b("TimerManager", "interrupted exception");
                }
            }
        }
    }

    /* compiled from: TimerManager */
    public static class b {
        private static final e a = new e(null);
    }

    /* synthetic */ e(a aVar) {
        this();
    }

    public static e a() {
        return b.a;
    }

    private e() {
        this.a = true;
    }

    public void b() {
        j.b("TimerManager", "init timer manager");
        this.b = new HandlerThread("SherlockHandler");
        this.b.start();
        this.c = new a(this.b.getLooper(), (long) (Config.getInstance().timeStep * 1000));
        this.c.sendMessage(Message.obtain());
    }

    public void a(boolean z) {
        j.a("TimerManager", "pause timer :" + z);
        this.a = z;
    }
}
