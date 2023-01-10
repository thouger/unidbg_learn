package com.vivo.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.util.n;
import com.vivo.push.util.r;
import com.vivo.push.util.y;
import com.vivo.vms.IPCInvoke;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: IPCManager */
public final class b implements ServiceConnection {
    private static final Object d = new Object();
    private static Map<String, b> e = new HashMap();
    public boolean a;
    public String b = null;
    public Context c;
    private AtomicInteger f;
    private volatile IPCInvoke g;
    private Object h = new Object();
    private String i;
    private Handler j = null;

    static /* synthetic */ void b(b bVar) {
        AppMethodBeat.i(3142, false);
        bVar.a(1);
        AppMethodBeat.o(3142);
    }

    static /* synthetic */ void c(b bVar) {
        AppMethodBeat.i(3146, false);
        bVar.d();
        AppMethodBeat.o(3146);
    }

    static {
        AppMethodBeat.i(3149, false);
        AppMethodBeat.o(3149);
    }

    private b(Context context, String str) {
        boolean z = false;
        AppMethodBeat.i(3083, false);
        this.c = context;
        this.i = str;
        this.f = new AtomicInteger(1);
        this.j = new Handler(Looper.getMainLooper(), new AnonymousClass1());
        this.b = r.b(context);
        if (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.i)) {
            Context context2 = this.c;
            n.c(context2, "init error : push pkgname is " + this.b + " ; action is " + this.i);
            this.a = false;
            AppMethodBeat.o(3083);
            return;
        }
        this.a = y.a(context, this.b) >= 1260 ? true : z;
        a();
        AppMethodBeat.o(3083);
    }

    public static b a(Context context, String str) {
        int i = 3085;
        AppMethodBeat.i(3085, false);
        b bVar = e.get(str);
        if (bVar == null) {
            synchronized (d) {
                try {
                    bVar = e.get(str);
                    if (bVar == null) {
                        bVar = new b(context, str);
                        e.put(str, bVar);
                    }
                } finally {
                    AppMethodBeat.o(i);
                }
            }
        }
        return bVar;
    }

    /* compiled from: IPCManager */
    /* renamed from: com.vivo.push.b$1  reason: invalid class name */
    class AnonymousClass1 implements Handler.Callback {
        AnonymousClass1() {
        }

        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            AppMethodBeat.i(3075, false);
            if (message == null) {
                n.a("AidlManager", "handleMessage error : msg is null");
                AppMethodBeat.o(3075);
                return false;
            }
            int i = message.what;
            if (i == 1) {
                n.a("AidlManager", "In connect, bind core service time out");
                if (b.this.f.get() == 2) {
                    b.b(b.this);
                }
            } else if (i != 2) {
                n.b("AidlManager", "unknow msg what [" + message.what + "]");
            } else {
                if (b.this.f.get() == 4) {
                    b.c(b.this);
                }
                b.b(b.this);
            }
            AppMethodBeat.o(3075);
            return true;
        }
    }

    private void a() {
        AppMethodBeat.i(3089, false);
        int i = this.f.get();
        n.d("AidlManager", "Enter connect, Connection Status: " + i);
        if (i == 4 || i == 2 || i == 3 || i == 5) {
            AppMethodBeat.o(3089);
            return;
        }
        if (this.a) {
            a(2);
            if (!b()) {
                a(1);
                n.a("AidlManager", "bind core service fail");
                AppMethodBeat.o(3089);
                return;
            }
            this.j.removeMessages(1);
            this.j.sendEmptyMessageDelayed(1, 3000);
        }
        AppMethodBeat.o(3089);
    }

    private boolean b() {
        AppMethodBeat.i(3096, false);
        Intent intent = new Intent(this.i);
        intent.setPackage(this.b);
        try {
            boolean bindService = this.c.bindService(intent, this, 1);
            AppMethodBeat.o(3096);
            return bindService;
        } catch (Exception e2) {
            n.a("AidlManager", "bind core error", e2);
            AppMethodBeat.o(3096);
            return false;
        }
    }

    private void a(int i) {
        AppMethodBeat.i(3102, false);
        this.f.set(i);
        AppMethodBeat.o(3102);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        int i = 3108;
        AppMethodBeat.i(3108, false);
        c();
        this.g = IPCInvoke.Stub.asInterface(iBinder);
        if (this.g == null) {
            n.d("AidlManager", "onServiceConnected error : aidl must not be null.");
            d();
            this.f.set(1);
            AppMethodBeat.o(3108);
            return;
        }
        if (this.f.get() == 2) {
            a(4);
        } else if (this.f.get() != 4) {
            d();
        }
        synchronized (this.h) {
            try {
                this.h.notifyAll();
            } finally {
                AppMethodBeat.o(i);
            }
        }
    }

    private void c() {
        AppMethodBeat.i(3112, false);
        this.j.removeMessages(1);
        AppMethodBeat.o(3112);
    }

    private void d() {
        AppMethodBeat.i(3119, false);
        try {
            this.c.unbindService(this);
            AppMethodBeat.o(3119);
        } catch (Exception e2) {
            n.a("AidlManager", "On unBindServiceException:" + e2.getMessage());
            AppMethodBeat.o(3119);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        AppMethodBeat.i(3123, false);
        this.g = null;
        a(1);
        AppMethodBeat.o(3123);
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        AppMethodBeat.i(3128, false);
        n.b("AidlManager", "onBindingDied : " + componentName);
        AppMethodBeat.o(3128);
    }

    public final boolean a(Bundle bundle) {
        AppMethodBeat.i(3133, false);
        a();
        if (this.f.get() == 2) {
            synchronized (this.h) {
                try {
                    this.h.wait(2000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                } catch (Throwable th) {
                    AppMethodBeat.o(3133);
                    throw th;
                }
            }
        }
        try {
            int i = this.f.get();
            if (i == 4) {
                this.j.removeMessages(2);
                this.j.sendEmptyMessageDelayed(2, 30000);
                this.g.asyncCall(bundle, null);
                AppMethodBeat.o(3133);
                return true;
            }
            n.d("AidlManager", "invoke error : connect status = " + i);
            AppMethodBeat.o(3133);
            return false;
        } catch (Exception e3) {
            n.a("AidlManager", "invoke error ", e3);
            int i2 = this.f.get();
            n.d("AidlManager", "Enter disconnect, Connection Status: " + i2);
            if (i2 != 1) {
                if (i2 == 2) {
                    c();
                    a(1);
                } else if (i2 == 3) {
                    a(1);
                } else if (i2 == 4) {
                    a(1);
                    d();
                }
            }
        }
    }
}
