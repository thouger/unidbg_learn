package cn.missfresh.module.base.utils;

import android.os.Handler;
import android.os.Looper;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.concurrent.CountDownLatch;

/* compiled from: TaskQueeManager */
public class au {
    private final int a = 5000;
    private CountDownLatch b;
    private Handler c = new Handler(Looper.getMainLooper());
    private boolean d = false;

    public au() {
        AppMethodBeat.i(23521, false);
        AppMethodBeat.o(23521);
    }

    public void a() {
        AppMethodBeat.i(23522, false);
        this.b.countDown();
        AppMethodBeat.o(23522);
    }

    public void a(Runnable runnable, int i) throws Exception {
        AppMethodBeat.i(23525, false);
        if (runnable != null) {
            c();
            this.b = new CountDownLatch(i);
            new a(runnable).start();
            AppMethodBeat.o(23525);
            return;
        }
        Exception exc = new Exception("\u56de\u8c03\u7ed3\u679c\u4e0d\u80fd\u4e3a\u7a7a");
        AppMethodBeat.o(23525);
        throw exc;
    }

    private void c() {
        this.d = false;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean b() {
        return this.d;
    }

    /* compiled from: TaskQueeManager */
    public class a extends Thread {
        private Runnable b;

        public a(Runnable runnable) {
            this.b = runnable;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
            if (r6.b == null) goto L_0x0043;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0043, code lost:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(23520);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x001e, code lost:
            if (r6.b != null) goto L_0x0020;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0020, code lost:
            r6.a.c.post(r6.b);
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r6 = this;
                r0 = 0
                r1 = 23520(0x5be0, float:3.2959E-41)
                cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
                super.run()
                cn.missfresh.module.base.utils.au r2 = cn.missfresh.module.base.utils.au.this     // Catch:{ InterruptedException -> 0x002e }
                java.util.concurrent.CountDownLatch r2 = cn.missfresh.module.base.utils.au.a(r2)     // Catch:{ InterruptedException -> 0x002e }
                r3 = 5000(0x1388, double:2.4703E-320)
                java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x002e }
                r2.await(r3, r5)     // Catch:{ InterruptedException -> 0x002e }
                cn.missfresh.module.base.utils.au r2 = cn.missfresh.module.base.utils.au.this     // Catch:{ InterruptedException -> 0x002e }
                r3 = 1
                cn.missfresh.module.base.utils.au.a(r2, r3)     // Catch:{ InterruptedException -> 0x002e }
                java.lang.Runnable r0 = r6.b
                if (r0 == 0) goto L_0x0043
            L_0x0020:
                cn.missfresh.module.base.utils.au r0 = cn.missfresh.module.base.utils.au.this
                android.os.Handler r0 = cn.missfresh.module.base.utils.au.b(r0)
                java.lang.Runnable r2 = r6.b
                r0.post(r2)
                goto L_0x0043
            L_0x002c:
                r0 = move-exception
                goto L_0x0047
            L_0x002e:
                r2 = move-exception
                r2.printStackTrace()     // Catch:{ all -> 0x002c }
                cn.missfresh.module.base.utils.au r2 = cn.missfresh.module.base.utils.au.this     // Catch:{ all -> 0x002c }
                cn.missfresh.module.base.utils.au.a(r2, r0)     // Catch:{ all -> 0x002c }
                java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x002c }
                r0.interrupt()     // Catch:{ all -> 0x002c }
                java.lang.Runnable r0 = r6.b
                if (r0 == 0) goto L_0x0043
                goto L_0x0020
            L_0x0043:
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
                return
            L_0x0047:
                java.lang.Runnable r2 = r6.b
                if (r2 == 0) goto L_0x0056
                cn.missfresh.module.base.utils.au r2 = cn.missfresh.module.base.utils.au.this
                android.os.Handler r2 = cn.missfresh.module.base.utils.au.b(r2)
                java.lang.Runnable r3 = r6.b
                r2.post(r3)
            L_0x0056:
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.utils.au.a.run():void");
        }
    }
}
