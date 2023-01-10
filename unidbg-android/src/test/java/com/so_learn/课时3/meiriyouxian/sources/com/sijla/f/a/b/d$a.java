package com.sijla.f.a.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import com.sijla.f.a.b.e$a;

public abstract class d$a extends Binder implements e {

    public static class a implements e {
        public IBinder a;

        public a(IBinder iBinder) {
            this.a = iBinder;
        }

        /* JADX INFO: finally extract failed */
        public String a(String str, String str2, String str3) {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                obtain.writeString(str);
                obtain.writeString(str2);
                obtain.writeString(str3);
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                obtain.recycle();
                obtain2.recycle();
                return readString;
            } catch (Exception e) {
                e.printStackTrace();
                obtain.recycle();
                obtain2.recycle();
                return null;
            } catch (Throwable th) {
                obtain.recycle();
                obtain2.recycle();
                throw th;
            }
        }

        public IBinder asBinder() {
            return this.a;
        }
    }

    public static d a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        d queryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
        if (queryLocalInterface == null || !(queryLocalInterface instanceof d)) {
            return new e$a.a(iBinder);
        }
        return queryLocalInterface;
    }
}
