package cn.missfresh.sherlock.trace.core;

import android.os.Build;
import android.os.Looper;
import android.os.MessageQueue;
import android.util.Printer;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.j;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: LooperMonitor */
public class a implements MessageQueue.IdleHandler {
    private static final HashSet<b> a = new HashSet<>();
    private static Printer b;

    /* compiled from: LooperMonitor */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.sherlock.trace.core.a$a  reason: collision with other inner class name */
    public static class C0049a implements Printer {
        boolean a = false;
        boolean b = false;

        C0049a() {
        }

        @Override // android.util.Printer
        public void println(String str) {
            boolean z = true;
            if (!this.a) {
                this.b = str.charAt(0) == '>' || str.charAt(0) == '<';
                this.a = true;
                if (!this.b) {
                    j.e("LooperMonitor", "printer inValid, " + str);
                }
            }
            if (this.b) {
                if (str.charAt(0) != '>') {
                    z = false;
                }
                a.b(z);
            }
        }
    }

    /* compiled from: LooperMonitor */
    public static abstract class b {
        boolean a = false;

        /* access modifiers changed from: package-private */
        public boolean a() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.a = true;
        }

        /* access modifiers changed from: package-private */
        public void c() {
            this.a = false;
        }
    }

    static {
        new a();
    }

    private a() {
        a();
        int i = Build.VERSION.SDK_INT;
        if (i >= 23) {
            j.b("LooperMonitor", "Looper Monitor init M");
            Looper.getMainLooper().getQueue().addIdleHandler(this);
        } else if (i >= 21) {
            try {
                j.b("LooperMonitor", "Looper Monitor init L");
                MessageQueue messageQueue = (MessageQueue) Utils.a(Looper.getMainLooper(), "mQueue");
                if (messageQueue != null) {
                    messageQueue.addIdleHandler(this);
                }
            } catch (Exception e) {
                j.e("LooperMonitor", "get mQueue failed e:" + e.getMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(boolean z) {
        Iterator<b> it2 = a.iterator();
        while (it2.hasNext()) {
            b next = it2.next();
            if (next.a()) {
                if (z) {
                    if (!next.a) {
                        next.b();
                    }
                } else if (next.a) {
                    next.c();
                }
            } else if (!z && next.a) {
                next.c();
            }
        }
    }

    @Override // android.os.MessageQueue.IdleHandler
    public boolean queueIdle() {
        a();
        return true;
    }

    private static void a() {
        try {
            if (((Printer) Utils.a(Looper.getMainLooper(), "mLogging")) != b || b == null) {
                Looper mainLooper = Looper.getMainLooper();
                C0049a aVar = new C0049a();
                b = aVar;
                mainLooper.setMessageLogging(aVar);
            }
        } catch (Exception unused) {
        }
    }

    public static void a(b bVar) {
        synchronized (a) {
            a.add(bVar);
        }
    }
}
