package com.sijla.f.a.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;

public abstract class g$a extends Binder implements g {

    public static class a implements g {
        private IBinder a;

        public a(IBinder iBinder) {
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
                obtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
                this.a.transact(3, obtain, obtain2, 0);
                obtain2.readException();
                str = obtain2.readString();
            } catch (Throwable unused) {
                obtain2.recycle();
                obtain.recycle();
                str = null;
            }
            obtain2.recycle();
            obtain.recycle();
            return str;
        }
    }
}
