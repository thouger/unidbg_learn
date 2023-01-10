package com.cmic.sso.wy.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import anet.channel.util.HttpConstant;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* compiled from: WifiNetworkUtils */
public class b {
    static ConnectivityManager a;
    private static b b;
    private Network c;
    private ConnectivityManager.NetworkCallback d;
    private boolean e;

    /* compiled from: WifiNetworkUtils */
    public interface a {
        void a(Network network);
    }

    private b(Context context) {
        a = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static b a(Context context) {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new b(context);
                }
            }
        }
        return b;
    }

    /* access modifiers changed from: package-private */
    public void a(a aVar) {
        NetworkInfo networkInfo;
        if (Build.VERSION.SDK_INT >= 21) {
            Network network = this.c;
            if (network == null || this.e || (networkInfo = a.getNetworkInfo(network)) == null || !networkInfo.isAvailable()) {
                ConnectivityManager.NetworkCallback networkCallback = this.d;
                if (networkCallback != null) {
                    try {
                        a.unregisterNetworkCallback(networkCallback);
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.d = null;
                    }
                    g.a("HttpUtils", "clear: ");
                }
                NetworkRequest build = new NetworkRequest.Builder().addCapability(12).addTransportType(0).build();
                this.d = new AnonymousClass1(aVar);
                a.requestNetwork(build, this.d);
                return;
            }
            aVar.a(this.c);
        }
    }

    /* compiled from: WifiNetworkUtils */
    /* renamed from: com.cmic.sso.wy.utils.b$1  reason: invalid class name */
    class AnonymousClass1 extends ConnectivityManager.NetworkCallback {
        final /* synthetic */ a a;

        AnonymousClass1(a aVar) {
            this.a = aVar;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            b.this.c = network;
            this.a.a(network);
            b.this.e = false;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            b.this.e = true;
        }
    }

    public void a() {
        try {
            if (Build.VERSION.SDK_INT >= 21 && a != null) {
                if (this.d != null) {
                    a.unregisterNetworkCallback(this.d);
                    this.d = null;
                    this.c = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int a(String str) {
        try {
            byte[] address = InetAddress.getByName(str).getAddress();
            return (address[0] & 255) | ((address[3] & 255) << 24) | ((address[2] & 255) << 16) | ((address[1] & 255) << 8);
        } catch (UnknownHostException unused) {
            return -1;
        }
    }

    static String b(String str) {
        int indexOf = str.indexOf(HttpConstant.SCHEME_SPLIT);
        if (indexOf > 0) {
            str = str.substring(indexOf + 3);
        }
        int indexOf2 = str.indexOf(58);
        if (indexOf2 >= 0) {
            str = str.substring(0, indexOf2);
        }
        int indexOf3 = str.indexOf(47);
        if (indexOf3 >= 0) {
            str = str.substring(0, indexOf3);
        }
        int indexOf4 = str.indexOf(63);
        return indexOf4 >= 0 ? str.substring(0, indexOf4) : str;
    }
}
