package com.d.a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public interface a extends IInterface {

    /* renamed from: com.d.a.a$a  reason: collision with other inner class name */
    public static abstract class AbstractBinderC0076a extends Binder implements a {

        /* access modifiers changed from: private */
        /* renamed from: com.d.a.a$a$a  reason: collision with other inner class name */
        public static class C0077a implements a {
            public static a a;
            private IBinder b;

            C0077a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // com.d.a.a
            public void a(Bundle bundle) {
                int i = 12754;
                AppMethodBeat.i(12754, false);
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.mcs.aidl.IMcsSdkService");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.b.transact(1, obtain, obtain2, 0) || AbstractBinderC0076a.a() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        AppMethodBeat.o(12754);
                        return;
                    }
                    AbstractBinderC0076a.a().a(bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                    AppMethodBeat.o(i);
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.b;
            }
        }

        public static a a() {
            return C0077a.a;
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.mcs.aidl.IMcsSdkService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new C0077a(iBinder) : (a) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("com.mcs.aidl.IMcsSdkService");
                a(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.mcs.aidl.IMcsSdkService");
                return true;
            }
        }
    }

    void a(Bundle bundle);
}
