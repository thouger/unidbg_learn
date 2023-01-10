package cn.missfresh.module.base.support.view.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.ref.WeakReference;

/* compiled from: SnackBarManager */
class b {
    private static b a;
    private final Object b = new Object();
    private final Handler c = new Handler(Looper.getMainLooper(), new AnonymousClass1());
    private C0031b d;
    private C0031b e;

    /* compiled from: SnackBarManager */
    /* access modifiers changed from: package-private */
    public interface a {
        void a();

        void a(int i);
    }

    static b a() {
        AppMethodBeat.i(22889, false);
        if (a == null) {
            a = new b();
        }
        b bVar = a;
        AppMethodBeat.o(22889);
        return bVar;
    }

    private b() {
        AppMethodBeat.i(22890, false);
        AppMethodBeat.o(22890);
    }

    /* compiled from: SnackBarManager */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.b$1  reason: invalid class name */
    class AnonymousClass1 implements Handler.Callback {
        AnonymousClass1() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            AppMethodBeat.i(22886, false);
            if (message.what != 0) {
                AppMethodBeat.o(22886);
                return false;
            }
            b.this.a((C0031b) message.obj);
            AppMethodBeat.o(22886);
            return true;
        }
    }

    public void a(int i, a aVar) {
        int i2 = 22891;
        AppMethodBeat.i(22891, false);
        synchronized (this.b) {
            try {
                if (f(aVar)) {
                    this.d.b = i;
                    this.c.removeCallbacksAndMessages(this.d);
                    b(this.d);
                    return;
                }
                if (g(aVar)) {
                    this.e.b = i;
                } else {
                    this.e = new C0031b(i, aVar);
                }
                if (this.d == null || !a(this.d, 4)) {
                    this.d = null;
                    b();
                    AppMethodBeat.o(22891);
                    return;
                }
                AppMethodBeat.o(22891);
            } finally {
                AppMethodBeat.o(i2);
            }
        }
    }

    public void a(a aVar, int i) {
        int i2 = 22892;
        AppMethodBeat.i(22892, false);
        synchronized (this.b) {
            try {
                if (f(aVar)) {
                    a(this.d, i);
                } else if (g(aVar)) {
                    a(this.e, i);
                }
            } finally {
                AppMethodBeat.o(i2);
            }
        }
    }

    public void a(a aVar) {
        int i = 22893;
        AppMethodBeat.i(22893, false);
        synchronized (this.b) {
            try {
                if (f(aVar)) {
                    this.d = null;
                    if (this.e != null) {
                        b();
                    }
                }
            } finally {
                AppMethodBeat.o(i);
            }
        }
    }

    public void b(a aVar) {
        int i = 22894;
        AppMethodBeat.i(22894, false);
        synchronized (this.b) {
            try {
                if (f(aVar)) {
                    b(this.d);
                }
            } finally {
                AppMethodBeat.o(i);
            }
        }
    }

    public void c(a aVar) {
        int i = 22895;
        AppMethodBeat.i(22895, false);
        synchronized (this.b) {
            try {
                if (f(aVar) && !this.d.c) {
                    this.d.c = true;
                    this.c.removeCallbacksAndMessages(this.d);
                }
            } finally {
                AppMethodBeat.o(i);
            }
        }
    }

    public void d(a aVar) {
        int i = 22896;
        AppMethodBeat.i(22896, false);
        synchronized (this.b) {
            try {
                if (f(aVar) && this.d.c) {
                    this.d.c = false;
                    b(this.d);
                }
            } finally {
                AppMethodBeat.o(i);
            }
        }
    }

    public boolean e(a aVar) {
        boolean z = false;
        int i = 22898;
        AppMethodBeat.i(22898, false);
        synchronized (this.b) {
            try {
                if (f(aVar) || g(aVar)) {
                    z = true;
                }
            } finally {
                AppMethodBeat.o(i);
            }
        }
        return z;
    }

    /* compiled from: SnackBarManager */
    /* access modifiers changed from: private */
    /* renamed from: cn.missfresh.module.base.support.view.snackbar.b$b  reason: collision with other inner class name */
    public static class C0031b {
        final WeakReference<a> a;
        int b;
        boolean c;

        C0031b(int i, a aVar) {
            AppMethodBeat.i(22887, false);
            this.a = new WeakReference<>(aVar);
            this.b = i;
            AppMethodBeat.o(22887);
        }

        /* access modifiers changed from: package-private */
        public boolean a(a aVar) {
            boolean z = false;
            AppMethodBeat.i(22888, false);
            if (aVar != null && this.a.get() == aVar) {
                z = true;
            }
            AppMethodBeat.o(22888);
            return z;
        }
    }

    private void b() {
        AppMethodBeat.i(22899, false);
        C0031b bVar = this.e;
        if (bVar != null) {
            this.d = bVar;
            this.e = null;
            a aVar = this.d.a.get();
            if (aVar != null) {
                aVar.a();
            } else {
                this.d = null;
            }
        }
        AppMethodBeat.o(22899);
    }

    private boolean a(C0031b bVar, int i) {
        AppMethodBeat.i(22900, false);
        a aVar = bVar.a.get();
        if (aVar != null) {
            this.c.removeCallbacksAndMessages(bVar);
            aVar.a(i);
            AppMethodBeat.o(22900);
            return true;
        }
        AppMethodBeat.o(22900);
        return false;
    }

    private boolean f(a aVar) {
        boolean z = false;
        AppMethodBeat.i(22901, false);
        C0031b bVar = this.d;
        if (bVar != null && bVar.a(aVar)) {
            z = true;
        }
        AppMethodBeat.o(22901);
        return z;
    }

    private boolean g(a aVar) {
        boolean z = false;
        AppMethodBeat.i(22902, false);
        C0031b bVar = this.e;
        if (bVar != null && bVar.a(aVar)) {
            z = true;
        }
        AppMethodBeat.o(22902);
        return z;
    }

    private void b(C0031b bVar) {
        AppMethodBeat.i(22903, false);
        if (bVar.b == -2) {
            AppMethodBeat.o(22903);
            return;
        }
        int i = 2750;
        if (bVar.b > 0) {
            i = bVar.b;
        } else if (bVar.b == -1) {
            i = 1500;
        }
        this.c.removeCallbacksAndMessages(bVar);
        Handler handler = this.c;
        handler.sendMessageDelayed(Message.obtain(handler, 0, bVar), (long) i);
        AppMethodBeat.o(22903);
    }

    /* access modifiers changed from: package-private */
    public void a(C0031b bVar) {
        int i = 22904;
        AppMethodBeat.i(22904, false);
        synchronized (this.b) {
            try {
                if (this.d == bVar || this.e == bVar) {
                    a(bVar, 2);
                }
            } finally {
                AppMethodBeat.o(i);
            }
        }
    }
}
