package cn.missfresh.module.search.interfaces;

/* compiled from: OnItemDelayCallback */
public abstract class c implements b {
    private long a;

    /* access modifiers changed from: protected */
    public abstract void a(int i, int i2, int i3, int i4);

    public void a(int i, int i2, int i3, int i4, boolean z) {
        if (z) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.a > 800) {
                a(i, i2, i3, i4);
            }
            this.a = currentTimeMillis;
            return;
        }
        a(i, i2, i3, i4);
    }
}
