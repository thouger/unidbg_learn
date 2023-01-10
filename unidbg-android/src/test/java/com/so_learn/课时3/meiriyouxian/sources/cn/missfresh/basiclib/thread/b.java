package cn.missfresh.basiclib.thread;

import android.mtp.MtpConstants;
import android.os.Process;
import android.util.Log;
import cn.missfresh.basiclib.thread.b.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: MFThreadPool */
public class b {
    private static final int a = Runtime.getRuntime().availableProcessors();
    private static final int b = Math.max(2, Math.min(a - 1, 4));
    private static final int c = ((a * 2) + 1);
    private static Map<String, b> g = new HashMap();
    private ThreadPoolExecutor d;
    private boolean e;
    private final String f;

    static {
        AppMethodBeat.i(4128, false);
        g.put("default", new b("default"));
        AppMethodBeat.o(4128);
    }

    /* compiled from: MFThreadPool */
    /* renamed from: cn.missfresh.basiclib.thread.b$1  reason: invalid class name */
    static class AnonymousClass1 implements ThreadFactory {
        final /* synthetic */ String a;
        private final AtomicInteger b;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            AppMethodBeat.i(4021, false);
            Thread thread = new Thread(runnable, this.a + "_ThreadPool #" + this.b.getAndIncrement());
            thread.setPriority(5);
            AppMethodBeat.o(4021);
            return thread;
        }
    }

    public static b a() {
        AppMethodBeat.i(4089, false);
        b a2 = a("default");
        AppMethodBeat.o(4089);
        return a2;
    }

    public static b a(String str) {
        AppMethodBeat.i(4096, false);
        if (g.containsKey(str)) {
            b bVar = g.get(str);
            AppMethodBeat.o(4096);
            return bVar;
        }
        b bVar2 = new b(str);
        g.put(str, bVar2);
        AppMethodBeat.o(4096);
        return bVar2;
    }

    private b(String str) {
        this(str, b, c);
    }

    private b(String str, int i, int i2) {
        AppMethodBeat.i(4100, false);
        this.f = str;
        a(i, i2);
        AppMethodBeat.o(4100);
    }

    /* compiled from: MFThreadPool */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.basiclib.thread.b$2  reason: invalid class name */
    public class AnonymousClass2 implements ThreadFactory {
        private final AtomicInteger b = new AtomicInteger(1);

        AnonymousClass2() {
            AppMethodBeat.i(4029, false);
            AppMethodBeat.o(4029);
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            AppMethodBeat.i(4035, false);
            b bVar = b.this;
            C0018b bVar2 = new C0018b(bVar, runnable, b.this.f + "_ThreadPool #" + this.b.getAndIncrement(), null);
            bVar2.setPriority(1);
            AppMethodBeat.o(4035);
            return bVar2;
        }
    }

    private void a(int i, int i2) {
        AppMethodBeat.i(4105, false);
        this.d = new a(i, i2, 20, TimeUnit.SECONDS, new ThreadQueue(), new AnonymousClass2(), new a());
        this.e = true;
        ((a) this.d).a(this.f);
        if (d.a) {
            Log.i("MFThreadPool", "create instance:" + toString());
        }
        AppMethodBeat.o(4105);
    }

    public void a(a aVar) {
        AppMethodBeat.i(MtpConstants.OPERATION_SEND_OBJECT_INFO, false);
        if (!this.e || aVar == null) {
            if (d.a) {
                Log.i("MFThreadPool", "addTask failed! thread pool running is false!");
            }
            AppMethodBeat.o(MtpConstants.OPERATION_SEND_OBJECT_INFO);
            return;
        }
        this.d.execute(aVar);
        AppMethodBeat.o(MtpConstants.OPERATION_SEND_OBJECT_INFO);
    }

    /* compiled from: MFThreadPool */
    /* renamed from: cn.missfresh.basiclib.thread.b$b  reason: collision with other inner class name */
    private class C0018b extends Thread {
        /* synthetic */ C0018b(b bVar, Runnable runnable, String str, AnonymousClass1 r4) {
            this(runnable, str);
        }

        private C0018b(Runnable runnable, String str) {
            super(runnable, str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            AppMethodBeat.i(4068, false);
            Process.setThreadPriority(10);
            super.run();
            AppMethodBeat.o(4068);
        }
    }

    /* compiled from: MFThreadPool */
    /* access modifiers changed from: private */
    public static class a implements RejectedExecutionHandler {
        a() {
        }

        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            if (threadPoolExecutor instanceof a) {
                a aVar = (a) threadPoolExecutor;
            }
        }
    }
}
