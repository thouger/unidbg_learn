package com.sijla.f.a.b;

import android.os.IBinder;
import android.os.Parcel;

public class f$a implements f {
    private IBinder a;

    public f$a(IBinder iBinder) {
        this.a = iBinder;
    }

    public IBinder asBinder() {
        return this.a;
    }

    public String a() {
        String str;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.samsung.android.deviceidservice.IDeviceIdService");
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            str = obtain2.readString();
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            th.printStackTrace();
            str = null;
        }
        obtain2.recycle();
        obtain.recycle();
        return str;
    }
}
