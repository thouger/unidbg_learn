package com.sijla.f.a.b;

import android.os.IBinder;
import android.os.Parcel;

public final class a$a implements a {
    private IBinder a;

    public a$a(IBinder iBinder) {
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
            obtain.writeInterfaceToken("com.asus.msa.SupplementaryDID.IDidAidlInterface");
            this.a.transact(3, obtain, obtain2, 0);
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
