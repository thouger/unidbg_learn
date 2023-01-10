package cn.missfresh.sherlock.d;

import cn.missfresh.sherlock.a;
import cn.missfresh.sherlock.to.ApmTO;
import cn.missfresh.sherlock.tool.e;

/* compiled from: AsyncSender */
public class b implements Runnable {
    private boolean a = false;
    private final d b;
    private final ApmTO c;

    public b(d dVar, ApmTO apmTO) {
        this.b = dVar;
        this.c = apmTO;
    }

    public void a(boolean z) {
    }

    public boolean a() {
        return this.a;
    }

    public void b(boolean z) {
        this.a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.c != null) {
                if (a.a(e.a.toJson(this.c), true).execute().isSuccessful()) {
                    this.b.b(this);
                } else {
                    this.b.c(this);
                }
            }
        } catch (Exception e) {
            this.b.a(this, e);
        }
    }

    public ApmTO b() {
        return this.c;
    }
}
