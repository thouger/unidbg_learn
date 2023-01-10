package com.umeng.analytics.pro;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.umeng.analytics.pro.a;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: SamsungDeviceIdSupplier */
public class ae implements y {
    private static final String a = "DeviceIdService";
    private static final String b = "com.samsung.android.deviceidservice";
    private static final String c = "com.samsung.android.deviceidservice.DeviceIdService";
    private String d = "";
    private CountDownLatch e;
    private final ServiceConnection f = new AnonymousClass1();

    /* JADX INFO: finally extract failed */
    @Override // com.umeng.analytics.pro.y
    public String a(Context context) {
        this.e = new CountDownLatch(1);
        try {
            b(context);
            if (!this.e.await(500, TimeUnit.MILLISECONDS)) {
                Log.e(a, "getOAID time-out");
            }
            String str = this.d;
            c(context);
            return str;
        } catch (InterruptedException e) {
            Log.e(a, "getOAID interrupted. e=" + e.getMessage());
            c(context);
            return null;
        } catch (Throwable th) {
            c(context);
            throw th;
        }
    }

    private void b(Context context) {
        try {
            Intent intent = new Intent();
            intent.setClassName(b, c);
            if (!context.bindService(intent, this.f, 1)) {
                throw new UnsupportedOperationException("not supported service");
            }
        } catch (Error | Exception e) {
            Log.e(a, "bindService failed. e=" + e.getMessage());
            this.e.countDown();
        }
    }

    private void c(Context context) {
        try {
            context.unbindService(this.f);
        } catch (Error | Exception e) {
            Log.e(a, "unbindService failed. e=" + e.getMessage());
        }
    }

    /* compiled from: SamsungDeviceIdSupplier */
    /* renamed from: com.umeng.analytics.pro.ae$1  reason: invalid class name */
    class AnonymousClass1 implements ServiceConnection {
        AnonymousClass1() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                a a = a.AbstractBinderC0174a.a(iBinder);
                ae.this.d = a.a();
                Log.d(ae.a, "onServiceConnected");
            } catch (RemoteException | NullPointerException e) {
                Log.e(ae.a, "onServiceConnected failed e=" + e.getMessage());
            }
            ae.this.e.countDown();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(ae.a, "onServiceDisconnected");
        }
    }
}
