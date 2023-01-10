package com.bun.miitmdid.supplier.sumsung;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SumsungCore {
    private static boolean DBG = false;
    private static String SAMSUNGTAG = "Samsung_DeviceIdService";
    private static String TAG = "SumsungCore library";
    private a mCallerCallBack = null;
    private ServiceConnection mConnection;
    private Context mContext = null;
    private com.e.a.a.a mDeviceidInterface;

    /* renamed from: com.bun.miitmdid.supplier.sumsung.SumsungCore$1  reason: invalid class name */
    class AnonymousClass1 implements ServiceConnection {
        AnonymousClass1() {
        }

        @Override // android.content.ServiceConnection
        public native synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder);

        @Override // android.content.ServiceConnection
        public native void onServiceDisconnected(ComponentName componentName);
    }

    public interface a {
    }

    public SumsungCore(Context context, a aVar) {
        AppMethodBeat.i(5361, false);
        if (context != null) {
            this.mContext = context;
            this.mCallerCallBack = aVar;
            this.mConnection = new AnonymousClass1();
            Intent intent = new Intent();
            intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
            if (this.mContext.bindService(intent, this.mConnection, 1)) {
                com.bun.miitmdid.utils.a.a(TAG, "bindService Successful!");
                Log.d(SAMSUNGTAG, "bindService Successful!");
            } else {
                com.bun.miitmdid.utils.a.a(TAG, "bindService Failed!");
            }
            AppMethodBeat.o(5361);
            return;
        }
        NullPointerException nullPointerException = new NullPointerException("Context can not be null.");
        AppMethodBeat.o(5361);
        throw nullPointerException;
    }

    static native /* synthetic */ com.e.a.a.a access$002(SumsungCore sumsungCore, com.e.a.a.a aVar);

    static native /* synthetic */ a access$100(SumsungCore sumsungCore);

    static native /* synthetic */ String access$200();

    public native String getAAID();

    public native String getOAID();

    public native String getUDID();

    public native String getVAID();

    public native boolean isSupported();

    public native void shutdown();
}
