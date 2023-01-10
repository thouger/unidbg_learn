package com.alipay.apmobilesecuritysdk.f;

import android.os.Process;

final class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Process.setThreadPriority(0);
            while (!b.a(this.a).isEmpty()) {
                Runnable runnable = (Runnable) b.a(this.a).get(0);
                b.a(this.a).remove(0);
                if (runnable != null) {
                    runnable.run();
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            b.b(this.a);
            throw th;
        }
        b.b(this.a);
    }
}
