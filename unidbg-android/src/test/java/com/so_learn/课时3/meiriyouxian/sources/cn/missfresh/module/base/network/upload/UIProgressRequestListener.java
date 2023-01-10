package cn.missfresh.module.base.network.upload;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.missfresh.module.base.network.upload.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;
import java.io.Serializable;
import java.lang.ref.WeakReference;

public abstract class UIProgressRequestListener implements a.AbstractC0024a {
    private final Handler a;

    public static class ProgressModel implements Serializable {
        public final long contentLength;
        public final long currentBytes;
        public final boolean done;

        public ProgressModel(long j, long j2, boolean z) {
            JniLib.cV(this, Long.valueOf(j), Long.valueOf(j2), Boolean.valueOf(z), 188);
        }
    }

    public UIProgressRequestListener() {
        JniLib.cV(this, 193);
    }

    public void a() {
        JniLib.cV(this, 190);
    }

    @Override // cn.missfresh.module.base.network.upload.a.AbstractC0024a
    public void a(long j, long j2, boolean z) {
        JniLib.cV(this, Long.valueOf(j), Long.valueOf(j2), Boolean.valueOf(z), 191);
    }

    public void b() {
        JniLib.cV(this, 192);
    }

    public abstract void b(long j, long j2, boolean z);

    public abstract void c();

    public abstract void d();

    private static class a extends Handler {
        private final WeakReference<UIProgressRequestListener> a;

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            JniLib.cV(this, message, 189);
        }

        public a(Looper looper, UIProgressRequestListener uIProgressRequestListener) {
            super(looper);
            AppMethodBeat.i(16065, false);
            this.a = new WeakReference<>(uIProgressRequestListener);
            AppMethodBeat.o(16065);
        }
    }
}
