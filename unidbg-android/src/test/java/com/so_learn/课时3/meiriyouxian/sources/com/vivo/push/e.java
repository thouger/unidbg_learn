package com.vivo.push;

import android.content.Context;
import com.vivo.push.b.n;

/* compiled from: PushClientTask */
public abstract class e implements Runnable {
    protected Context a;
    int b = -1;
    private g c;

    public abstract void a(g gVar);

    public e(g gVar) {
        this.c = gVar;
        this.b = gVar.a;
        if (this.b >= 0) {
            this.a = d.a().e;
            return;
        }
        throw new IllegalArgumentException("PushTask need a > 0 task id.");
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context = this.a;
        if (context != null && !(this.c instanceof n)) {
            com.vivo.push.util.n.a(context, "[\u6267\u884c\u6307\u4ee4]" + this.c);
        }
        a(this.c);
    }

    @Override // java.lang.Object
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{");
        g gVar = this.c;
        sb.append(gVar == null ? "[null]" : gVar.toString());
        sb.append("}");
        return sb.toString();
    }
}
