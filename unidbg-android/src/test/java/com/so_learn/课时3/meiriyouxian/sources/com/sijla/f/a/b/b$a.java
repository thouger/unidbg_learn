package com.sijla.f.a.b;

import android.os.IBinder;
import android.os.Parcel;

public final class b$a implements b {
    private IBinder a;

    public b$a(IBinder iBinder) {
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
            obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
            this.a.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            str = obtain2.readString();
        } catch (Throwable th) {
            obtain.recycle();
            obtain2.recycle();
            th.printStackTrace();
            str = null;
        }
        obtain.recycle();
        obtain2.recycle();
        return str;
    }
}
