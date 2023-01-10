package cn.missfresh.sherlock.tool;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Printer;
import cn.missfresh.sherlock.trace.AppActiveMatrixDelegate;
import cn.missfresh.sherlock.trace.tracer.d;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: HandlerThreadUtil */
public class g {
    public static boolean a = false;
    private static volatile HandlerThread b;
    private static volatile Handler c;
    private static volatile Handler d = new Handler(Looper.getMainLooper());
    private static HashSet<HandlerThread> e = new HashSet<>();

    /* compiled from: HandlerThreadUtil */
    private static final class a implements Printer, d {
        private ConcurrentHashMap<String, b> a = new ConcurrentHashMap<>();
        private boolean b;

        /* compiled from: HandlerThreadUtil */
        /* renamed from: cn.missfresh.sherlock.tool.g$a$a  reason: collision with other inner class name */
        class C0044a implements Comparator<b> {
            C0044a(a aVar) {
            }

            /* renamed from: a */
            public int compare(b bVar, b bVar2) {
                return bVar2.b - bVar.b;
            }
        }

        /* compiled from: HandlerThreadUtil */
        /* access modifiers changed from: package-private */
        public class b {
            String a;
            int b;

            b(a aVar) {
            }

            public String toString() {
                return this.a + ":" + this.b;
            }
        }

        a() {
            AppActiveMatrixDelegate.INSTANCE.addListener(this);
            this.b = AppActiveMatrixDelegate.INSTANCE.isAppForeground();
        }

        @Override // cn.missfresh.sherlock.trace.tracer.d
        public void a(boolean z) {
            this.b = z;
            j.b("HandlerThreadUtil", "onForeground:%s", Boolean.valueOf(z));
            if (z) {
                long currentTimeMillis = System.currentTimeMillis();
                LinkedList linkedList = new LinkedList();
                for (b bVar : this.a.values()) {
                    if (bVar.b > 1) {
                        linkedList.add(bVar);
                    }
                }
                Collections.sort(linkedList, new C0044a(this));
                this.a.clear();
                if (!linkedList.isEmpty()) {
                    j.a("HandlerThreadUtil", "default thread has exec in background! %s cost:%s", linkedList, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    return;
                }
                return;
            }
            this.a.clear();
        }

        @Override // android.util.Printer
        public void println(String str) {
            if (!this.b && str.charAt(0) == '>') {
                int indexOf = str.indexOf("} ");
                int indexOf2 = str.indexOf("@", indexOf);
                if (indexOf >= 0 && indexOf2 >= 0) {
                    String substring = str.substring(indexOf, indexOf2);
                    b bVar = this.a.get(substring);
                    if (bVar == null) {
                        bVar = new b(this);
                        bVar.a = substring;
                        this.a.put(substring, bVar);
                    }
                    bVar.b++;
                }
            }
        }
    }

    public static Handler a() {
        return d;
    }

    public static HandlerThread b() {
        HandlerThread handlerThread;
        synchronized (g.class) {
            if (b == null) {
                b = new HandlerThread("default_sherlock_thread");
                b.start();
                c = new Handler(b.getLooper());
                b.getLooper().setMessageLogging(a ? new a() : null);
                j.d("HandlerThreadUtil", "create default handler thread, we should use these thread normal");
            }
            handlerThread = b;
        }
        return handlerThread;
    }

    public static Handler c() {
        return c;
    }

    public static HandlerThread a(String str) {
        Iterator<HandlerThread> it2 = e.iterator();
        while (it2.hasNext()) {
            if (!it2.next().isAlive()) {
                it2.remove();
                j.d("HandlerThreadUtil", String.format("warning: remove dead handler thread with name %s", str));
            }
        }
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        e.add(handlerThread);
        j.d("HandlerThreadUtil", String.format("warning: create new handler thread with name %s, alive thread size:%d", str, Integer.valueOf(e.size())));
        return handlerThread;
    }
}
