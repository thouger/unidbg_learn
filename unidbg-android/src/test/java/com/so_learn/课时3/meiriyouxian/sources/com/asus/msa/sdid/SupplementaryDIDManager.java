package com.asus.msa.sdid;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.asus.msa.a.a;

public class SupplementaryDIDManager {
    public static boolean DEBUG = false;
    public static final String TAG = "SupplementaryDIDManager";
    public boolean isBinded = false;
    public Context mContext;
    public a mDidService;
    public a mListener;
    public ServiceConnection mServiceConnection = new AnonymousClass1();

    /* renamed from: com.asus.msa.sdid.SupplementaryDIDManager$1  reason: invalid class name */
    public class AnonymousClass1 implements ServiceConnection {
        public AnonymousClass1() {
            AppMethodBeat.i(4882, false);
            AppMethodBeat.o(4882);
        }

        @Override // android.content.ServiceConnection
        public native void onServiceConnected(ComponentName componentName, IBinder iBinder);

        @Override // android.content.ServiceConnection
        public native void onServiceDisconnected(ComponentName componentName);
    }

    public SupplementaryDIDManager(Context context) {
        AppMethodBeat.i(4886, false);
        this.mContext = context;
        AppMethodBeat.o(4886);
    }

    public static native /* synthetic */ boolean access$000();

    public static native /* synthetic */ a access$102(SupplementaryDIDManager supplementaryDIDManager, a aVar);

    public static native /* synthetic */ void access$200(SupplementaryDIDManager supplementaryDIDManager, boolean z);

    private native void notifyAllListeners(boolean z);

    public native void deInit();

    public native void init(a aVar);

    public native void showLog(boolean z);
}
