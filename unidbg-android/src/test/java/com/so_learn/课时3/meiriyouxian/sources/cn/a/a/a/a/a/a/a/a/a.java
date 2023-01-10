package cn.a.a.a.a.a.a.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.Map;

public interface a extends IInterface {

    /* renamed from: cn.a.a.a.a.a.a.a.a.a$a  reason: collision with other inner class name */
    public static abstract class AbstractBinderC0011a extends Binder implements a {

        /* renamed from: cn.a.a.a.a.a.a.a.a.a$a$a  reason: collision with other inner class name */
        private static class C0012a implements a {
            private IBinder a;

            C0012a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // cn.a.a.a.a.a.a.a.a.a
            public final String a(String str) throws RemoteException {
                int i = 9767;
                AppMethodBeat.i(9767, false);
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeString(str);
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                    AppMethodBeat.o(i);
                }
            }

            @Override // cn.a.a.a.a.a.a.a.a.a
            public final Map a(String str, String str2) throws RemoteException {
                int i = 9771;
                AppMethodBeat.i(9771, false);
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                    AppMethodBeat.o(i);
                }
            }

            @Override // cn.a.a.a.a.a.a.a.a.a
            public final boolean a() throws RemoteException {
                boolean z = false;
                int i = 9768;
                AppMethodBeat.i(9768, false);
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                    AppMethodBeat.o(i);
                }
            }

            @Override // cn.a.a.a.a.a.a.a.a.a
            public final byte[] a(byte[] bArr) throws RemoteException {
                int i = 9765;
                AppMethodBeat.i(9765, false);
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeByteArray(bArr);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                    AppMethodBeat.o(i);
                }
            }

            @Override // cn.a.a.a.a.a.a.a.a.a
            public final byte[] a(byte[] bArr, String str) throws RemoteException {
                int i = 9772;
                AppMethodBeat.i(9772, false);
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str);
                    this.a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                    AppMethodBeat.o(i);
                }
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.a;
            }

            @Override // cn.a.a.a.a.a.a.a.a.a
            public final boolean b() throws RemoteException {
                boolean z = false;
                int i = 9770;
                AppMethodBeat.i(9770, false);
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                    AppMethodBeat.o(i);
                }
            }

            @Override // cn.a.a.a.a.a.a.a.a.a
            public final boolean b(String str) throws RemoteException {
                boolean z = false;
                int i = 9773;
                AppMethodBeat.i(9773, false);
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeString(str);
                    this.a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                    AppMethodBeat.o(i);
                }
            }

            @Override // cn.a.a.a.a.a.a.a.a.a
            public final byte[] b(byte[] bArr) throws RemoteException {
                int i = 9766;
                AppMethodBeat.i(9766, false);
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    obtain.writeByteArray(bArr);
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                    AppMethodBeat.o(i);
                }
            }

            @Override // cn.a.a.a.a.a.a.a.a.a
            public final String c() throws RemoteException {
                int i = 9774;
                AppMethodBeat.i(9774, false);
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    this.a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                    AppMethodBeat.o(i);
                }
            }

            @Override // cn.a.a.a.a.a.a.a.a.a
            public final String d() throws RemoteException {
                int i = 9776;
                AppMethodBeat.i(9776, false);
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                    this.a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                    AppMethodBeat.o(i);
                }
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new C0012a(iBinder) : (a) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        byte[] a = a(parcel.createByteArray());
                        parcel2.writeNoException();
                        parcel2.writeByteArray(a);
                        return true;
                    case 2:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        byte[] b = b(parcel.createByteArray());
                        parcel2.writeNoException();
                        parcel2.writeByteArray(b);
                        return true;
                    case 3:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        String a2 = a(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeString(a2);
                        return true;
                    case 4:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        boolean a3 = a();
                        parcel2.writeNoException();
                        parcel2.writeInt(a3 ? 1 : 0);
                        return true;
                    case 5:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        boolean b2 = b();
                        parcel2.writeNoException();
                        parcel2.writeInt(b2 ? 1 : 0);
                        return true;
                    case 6:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        Map a4 = a(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeMap(a4);
                        return true;
                    case 7:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        byte[] a5 = a(parcel.createByteArray(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeByteArray(a5);
                        return true;
                    case 8:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        boolean b3 = b(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(b3 ? 1 : 0);
                        return true;
                    case 9:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        String c = c();
                        parcel2.writeNoException();
                        parcel2.writeString(c);
                        return true;
                    case 10:
                        parcel.enforceInterface("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                        String d = d();
                        parcel2.writeNoException();
                        parcel2.writeString(d);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("cn.gov.pbc.tsm.client.mobile.android.bank.service.IServiceForBank");
                return true;
            }
        }
    }

    String a(String str) throws RemoteException;

    Map a(String str, String str2) throws RemoteException;

    boolean a() throws RemoteException;

    byte[] a(byte[] bArr) throws RemoteException;

    byte[] a(byte[] bArr, String str) throws RemoteException;

    boolean b() throws RemoteException;

    boolean b(String str) throws RemoteException;

    byte[] b(byte[] bArr) throws RemoteException;

    String c() throws RemoteException;

    String d() throws RemoteException;
}
