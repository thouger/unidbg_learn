package com.hmt.analytics.android;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/* compiled from: OpenUDID_manager_hmt */
public class j implements ServiceConnection {
    private static final boolean a = g.b;
    private static String g = null;
    private static boolean h = false;
    private final Context b;
    private List<ResolveInfo> c;
    private Map<String, Integer> d = new HashMap();
    private final SharedPreferences e;
    private final Random f = new Random();

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }

    private j(Context context) {
        this.e = context.getSharedPreferences("openudid_prefs", 0);
        this.b = context;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        String readString;
        try {
            Parcel obtain = Parcel.obtain();
            obtain.writeInt(this.f.nextInt());
            Parcel obtain2 = Parcel.obtain();
            iBinder.transact(1, Parcel.obtain(), obtain2, 0);
            if (obtain.readInt() == obtain2.readInt() && (readString = obtain2.readString()) != null) {
                if (a) {
                    Log.d("OpenUDID", "Received " + readString);
                }
                if (this.d.containsKey(readString)) {
                    this.d.put(readString, Integer.valueOf(this.d.get(readString).intValue() + 1));
                } else {
                    this.d.put(readString, 1);
                }
            }
        } catch (RemoteException e) {
            if (a) {
                a.a("OpenUDID", "Collected:" + e.getMessage());
            }
        }
        this.b.unbindService(this);
        a.a("service", "unbind");
        e();
    }

    private void c() {
        SharedPreferences.Editor edit = this.e.edit();
        edit.putString("openudid", g);
        edit.commit();
    }

    private void d() {
        if (a) {
            Log.d("OpenUDID", "Generating openUDID");
        }
        g = Settings.Secure.getString(this.b.getContentResolver(), "android_id");
        String str = g;
        if (str == null || str.equals("9774d56d682e549c") || g.length() < 15) {
            g = new BigInteger(64, new SecureRandom()).toString(16);
        }
    }

    private void e() {
        if (this.c.size() > 0) {
            if (a) {
                Log.d("OpenUDID", "Trying service " + ((Object) this.c.get(0).loadLabel(this.b.getPackageManager())));
            }
            ServiceInfo serviceInfo = this.c.get(0).serviceInfo;
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(serviceInfo.applicationInfo.packageName, serviceInfo.name));
            this.c.remove(0);
            try {
                if (!this.b.bindService(intent, this, 1)) {
                    a.a("openUDID", "bind opendudid service faill_hmt");
                    this.b.unbindService(this);
                    e();
                    return;
                }
                a.a("openUDID", "bind opendudid service success_hmt");
            } catch (SecurityException unused) {
                e();
            } catch (NullPointerException e) {
                a.a("OpenUDID", "Collected:" + e.getMessage());
            } catch (IllegalArgumentException e2) {
                a.a("OpenUDID", "Collected:" + e2.getMessage());
            }
        } else {
            f();
            if (g == null) {
                d();
            }
            if (a) {
                Log.d("OpenUDID", "OpenUDID: " + g);
            }
            c();
            h = true;
        }
    }

    private void f() {
        if (!this.d.isEmpty()) {
            TreeMap treeMap = new TreeMap(new a());
            treeMap.putAll(this.d);
            g = (String) treeMap.firstKey();
        }
    }

    public static String a() {
        if (!h) {
            a.a("OpenUDID", "Initialisation isn't done");
        }
        return g;
    }

    public static boolean b() {
        return h;
    }

    public static void a(Context context) {
        j jVar = new j(context);
        g = jVar.e.getString("openudid", null);
        if (g == null) {
            jVar.c = context.getPackageManager().queryIntentServices(new Intent("org.OpenUDID.GETUDID"), 0);
            if (a) {
                Log.d("OpenUDID", jVar.c.size() + " services matches OpenUDID");
            }
            if (jVar.c != null) {
                jVar.e();
                return;
            }
            return;
        }
        if (a) {
            Log.d("OpenUDID", "OpenUDID: " + g);
        }
        h = true;
    }

    /* compiled from: OpenUDID_manager_hmt */
    /* access modifiers changed from: private */
    public class a implements Comparator {
        private a() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (((Integer) j.this.d.get(obj)).intValue() < ((Integer) j.this.d.get(obj2)).intValue()) {
                return 1;
            }
            return j.this.d.get(obj) == j.this.d.get(obj2) ? 0 : -1;
        }
    }
}
