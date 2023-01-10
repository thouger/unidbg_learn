package com.q.m;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.sijla.callback.QtCallBack;
import com.sijla.common.c;
import com.sijla.g.a.a;

public class QS extends Service {
    private static String a;
    private static String b;
    private static boolean c;
    private static boolean d;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        c.a(getApplication(), true);
    }

    /* renamed from: com.q.m.QS$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ Intent a;

        AnonymousClass1(Intent intent) {
            this.a = intent;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.a != null) {
                    this.a.getStringExtra("src");
                    String f = a.f(QS.this.getApplicationContext());
                    String unused = QS.a = this.a.getStringExtra("channel");
                    String unused2 = QS.b = this.a.getStringExtra("uid3");
                    boolean unused3 = QS.c = this.a.getBooleanExtra("allowUseNetWork", true);
                    boolean unused4 = QS.d = this.a.getBooleanExtra("isCallinApplicationOnCreate", true);
                    c.a(QS.this.getApplication(), QS.a, QS.b, f, QS.c, (QtCallBack) null, QS.d);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        com.sijla.a.a.a(new AnonymousClass1(intent));
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        c.c(this);
        c.a(getApplication(), a, b, c, d);
    }
}
