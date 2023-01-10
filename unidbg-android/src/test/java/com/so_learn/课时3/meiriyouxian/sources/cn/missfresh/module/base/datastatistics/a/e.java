package cn.missfresh.module.base.datastatistics.a;

/* compiled from: ScmConfigHolder */
/* access modifiers changed from: package-private */
public class e {
    private static d a;

    static synchronized void a(d dVar) {
        synchronized (e.class) {
            a = dVar;
        }
    }

    static synchronized d a() {
        d dVar;
        synchronized (e.class) {
            dVar = a;
        }
        return dVar;
    }
}
