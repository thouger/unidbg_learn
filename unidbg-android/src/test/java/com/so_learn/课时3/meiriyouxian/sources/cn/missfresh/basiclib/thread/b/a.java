package cn.missfresh.basiclib.thread.b;

import cn.missfresh.utils.a.d;

/* compiled from: MFRunnable */
public abstract class a implements Runnable {
    private cn.missfresh.basiclib.thread.a.a monitor;
    private Object para;
    private int retryCount;

    public abstract void runInTryCatch();

    public int getRetryCount() {
        return this.retryCount;
    }

    public void setRetryCount(int i) {
        this.retryCount = i;
    }

    public a() {
        this(null, null);
    }

    public a(cn.missfresh.basiclib.thread.a.a aVar) {
        this(aVar, null);
    }

    public a(Object obj) {
        this(null, obj);
    }

    public a(cn.missfresh.basiclib.thread.a.a aVar, Object obj) {
        this.retryCount = 0;
        this.monitor = aVar;
        this.para = obj;
    }

    /* JADX INFO: finally extract failed */
    @Override // java.lang.Runnable
    public final void run() {
        cn.missfresh.basiclib.thread.a.a aVar;
        cn.missfresh.basiclib.thread.a.a aVar2 = this.monitor;
        if (aVar2 != null) {
            aVar2.a(this, this.para);
        }
        try {
            runInTryCatch();
            aVar = this.monitor;
            if (aVar == null) {
                return;
            }
        } catch (Exception e) {
            if (this.monitor != null) {
                this.monitor.a(this, e, this.para);
            } else {
                d.b("MFThreadPool", "", e);
            }
            aVar = this.monitor;
            if (aVar == null) {
                return;
            }
        } catch (Throwable th) {
            cn.missfresh.basiclib.thread.a.a aVar3 = this.monitor;
            if (aVar3 != null) {
                aVar3.b(this, this.para);
            }
            throw th;
        }
        aVar.b(this, this.para);
    }

    public void setMonitor(cn.missfresh.basiclib.thread.a.a aVar) {
        this.monitor = aVar;
    }

    public void setInputParameter(Object obj) {
        this.para = obj;
    }

    public Object getPara() {
        return this.para;
    }

    @Override // java.lang.Object
    public String toString() {
        return getClass().toString() + ":" + String.valueOf(this.para);
    }
}
