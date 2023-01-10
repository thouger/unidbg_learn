package cn.missfresh.sherlock.trace.f;

import android.os.Handler;
import cn.missfresh.sherlock.tool.g;

/* compiled from: IDoFrameListener */
public class b {
    private Handler a;

    public Handler a() {
        if (this.a == null) {
            this.a = new Handler(g.b().getLooper());
        }
        return this.a;
    }

    public void a(String str, long j, int i) {
    }

    public void b(String str, long j, int i) {
    }
}
