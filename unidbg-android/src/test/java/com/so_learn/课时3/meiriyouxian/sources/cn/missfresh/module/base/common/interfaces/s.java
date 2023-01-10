package cn.missfresh.module.base.common.interfaces;

/* compiled from: ItemDelayCallback */
public abstract class s implements r {
    private long a;

    /* access modifiers changed from: protected */
    public abstract void b(int i, int i2, Object obj);

    @Override // cn.missfresh.module.base.common.interfaces.r
    public void a(int i, int i2, Object obj) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.a > 800) {
            b(i, i2, obj);
        }
        this.a = currentTimeMillis;
    }
}
