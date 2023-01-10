package com.sijla.f.a.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;

public abstract class c$a extends Binder implements c {

    public static class a implements c {
        private IBinder a;

        public IBinder asBinder() {
            return null;
        }

        public a(IBinder iBinder) {
            this.a = iBinder;
        }

        /* JADX INFO: finally extract failed */
        public String a() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                obtain2.recycle();
                obtain.recycle();
                return readString;
            } catch (Exception e) {
                e.printStackTrace();
                obtain2.recycle();
                obtain.recycle();
                return null;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
                throw th;
            }
        }

        /* JADX INFO: finally extract failed */
        public String a(String str) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                this.a.transact(4, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                obtain2.recycle();
                obtain.recycle();
                return readString;
            } catch (Exception e) {
                e.printStackTrace();
                obtain2.recycle();
                obtain.recycle();
                return null;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
                throw th;
            }
        }

        /* JADX INFO: finally extract failed */
        public String b() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                this.a.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                obtain2.recycle();
                obtain.recycle();
                return readString;
            } catch (Exception e) {
                e.printStackTrace();
                obtain2.recycle();
                obtain.recycle();
                return null;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
                throw th;
            }
        }

        /* JADX INFO: finally extract failed */
        public String b(String str) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                this.a.transact(5, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                obtain2.recycle();
                obtain.recycle();
                return readString;
            } catch (Exception e) {
                e.printStackTrace();
                obtain2.recycle();
                obtain.recycle();
                return null;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
                throw th;
            }
        }

        public boolean c() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            boolean z = false;
            try {
                obtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                this.a.transact(3, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    z = true;
                }
                obtain2.recycle();
                obtain.recycle();
            } catch (Throwable unused) {
                obtain2.recycle();
                obtain.recycle();
            }
            return z;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
            String a2 = a();
            parcel2.writeNoException();
            parcel2.writeString(a2);
            return true;
        } else if (i == 2) {
            parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
            String b = b();
            parcel2.writeNoException();
            parcel2.writeString(b);
            return true;
        } else if (i == 3) {
            parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
            boolean c = c();
            parcel2.writeNoException();
            parcel2.writeInt(c ? 1 : 0);
            return true;
        } else if (i == 4) {
            parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
            String a3 = a(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeString(a3);
            return true;
        } else if (i == 5) {
            parcel.enforceInterface("com.zui.deviceidservice.IDeviceidInterface");
            String b2 = b(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeString(b2);
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("com.zui.deviceidservice.IDeviceidInterface");
            return true;
        }
    }
}
