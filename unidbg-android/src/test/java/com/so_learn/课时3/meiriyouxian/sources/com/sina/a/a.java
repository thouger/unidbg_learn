package com.sina.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: RemoteSSO */
public interface a extends IInterface {
    String a() throws RemoteException;

    String b() throws RemoteException;

    String c() throws RemoteException;

    /* compiled from: RemoteSSO */
    /* renamed from: com.sina.a.a$a  reason: collision with other inner class name */
    public static abstract class AbstractBinderC0132a extends Binder implements a {
        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.sina.sso.RemoteSSO");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof a)) {
                return new C0133a(iBinder);
            }
            return (a) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.sina.sso.RemoteSSO");
                String a = a();
                parcel2.writeNoException();
                parcel2.writeString(a);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.sina.sso.RemoteSSO");
                String b = b();
                parcel2.writeNoException();
                parcel2.writeString(b);
                return true;
            } else if (i == 3) {
                parcel.enforceInterface("com.sina.sso.RemoteSSO");
                String c = c();
                parcel2.writeNoException();
                parcel2.writeString(c);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.sina.sso.RemoteSSO");
                return true;
            }
        }

        /* compiled from: RemoteSSO */
        /* access modifiers changed from: private */
        /* renamed from: com.sina.a.a$a$a  reason: collision with other inner class name */
        public static class C0133a implements a {
            private IBinder a;

            C0133a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }

            @Override // com.sina.a.a
            public String a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sina.sso.RemoteSSO");
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sina.a.a
            public String b() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sina.sso.RemoteSSO");
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.sina.a.a
            public String c() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sina.sso.RemoteSSO");
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
