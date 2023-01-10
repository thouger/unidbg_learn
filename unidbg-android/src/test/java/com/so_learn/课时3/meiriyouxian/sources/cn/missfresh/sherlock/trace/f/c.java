package cn.missfresh.sherlock.trace.f;

/* compiled from: LooperObserver */
public abstract class c {
    private boolean a = false;

    public void a(long j, long j2, long j3) {
        this.a = true;
    }

    public void a(String str, long j, long j2, long j3) {
    }

    public void a(long j, long j2, long j3, long j4, long j5) {
        this.a = false;
    }

    public boolean a() {
        return this.a;
    }
}
