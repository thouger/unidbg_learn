package com.alipay.b.a.a.d;

/* access modifiers changed from: package-private */
public final class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.a.b();
        } catch (Exception e) {
            d.a(e);
        }
    }
}
