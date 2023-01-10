package com.hmt.analytics.android;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;

/* compiled from: OpenUDID_service_hmt */
public class n extends Service {

    /* compiled from: OpenUDID_service_hmt */
    /* renamed from: com.hmt.analytics.android.n$1  reason: invalid class name */
    class AnonymousClass1 extends Binder {
        AnonymousClass1() {
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            SharedPreferences sharedPreferences = n.this.getSharedPreferences("openudid_prefs", 0);
            parcel2.writeInt(parcel.readInt());
            parcel2.writeString(sharedPreferences.getString("openudid", null));
            return true;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new AnonymousClass1();
    }
}
