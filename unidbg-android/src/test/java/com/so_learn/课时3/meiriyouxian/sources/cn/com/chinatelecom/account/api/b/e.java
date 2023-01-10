package cn.com.chinatelecom.account.api.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.telephony.PreciseDisconnectCause;
import anet.channel.util.HttpConstant;
import cn.com.chinatelecom.account.api.CtAuth;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.net.InetAddress;

public class e extends a {
    private static final String b = e.class.getSimpleName();
    private boolean c = false;
    private ConnectivityManager d = null;
    private ConnectivityManager.NetworkCallback e = null;
    private a f;
    private long g = 0;
    private long h = 0;

    /* access modifiers changed from: package-private */
    /* renamed from: cn.com.chinatelecom.account.api.b.e$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ int a;

        AnonymousClass1(int i) {
            this.a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(8003, false);
            if (this.a > 2500) {
                try {
                    Thread.sleep(2500);
                } catch (Throwable th) {
                    CtAuth.warn(e.b, "timeoutCheckRunnable exception!", th);
                }
                if (!e.this.c) {
                    if (e.this.f != null) {
                        e.this.f.a(80800, "WIFI\u5207\u6362\u8d85\u65f6", 2500);
                    }
                    CtAuth.info(e.b, "\u5207\u6362\u7f51\u7edc\u8d85\u65f6(L)");
                    e.c(e.this);
                    AppMethodBeat.o(8003);
                    return;
                }
            }
            try {
                Thread.sleep((long) (this.a <= 2500 ? this.a : this.a - PreciseDisconnectCause.EPDG_TUNNEL_ESTABLISH_FAILURE));
            } catch (Throwable th2) {
                CtAuth.warn(e.b, "timeoutCheckRunnable exception!", th2);
            }
            if (e.this.f != null) {
                e.this.f.a();
            }
            AppMethodBeat.o(8003);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.com.chinatelecom.account.api.b.e$2  reason: invalid class name */
    public class AnonymousClass2 extends ConnectivityManager.NetworkCallback {
        AnonymousClass2() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            AppMethodBeat.i(8012, false);
            long currentTimeMillis = System.currentTimeMillis();
            e eVar = e.this;
            eVar.g = currentTimeMillis - eVar.h;
            e.this.c = true;
            if (e.this.f != null) {
                e.this.f.a(network, e.this.g);
            }
            if (e.this.d != null) {
                try {
                    e.this.d.unregisterNetworkCallback(this);
                    e.this.d = null;
                } catch (Throwable th) {
                    CtAuth.warn(e.b, "switchToMobileForAboveL", th);
                }
            }
            AppMethodBeat.o(8012);
        }
    }

    public interface a {
        void a();

        void a(int i, String str, long j);

        void a(Network network, long j);
    }

    static {
        AppMethodBeat.i(8071, false);
        AppMethodBeat.o(8071);
    }

    public static int a(String str) {
        int i;
        AppMethodBeat.i(8054, false);
        try {
            byte[] address = InetAddress.getByName(str).getAddress();
            i = (address[0] & 255) | ((address[3] & 255) << 24) | ((address[2] & 255) << 16) | ((address[1] & 255) << 8);
        } catch (Throwable th) {
            CtAuth.warn(b, "When InetAddress.getByName(),throws exception", th);
            i = -1;
        }
        AppMethodBeat.o(8054);
        return i;
    }

    private void a(Context context) {
        AppMethodBeat.i(8045, false);
        this.g = 0;
        this.d = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.h = System.currentTimeMillis();
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        builder.addCapability(12);
        builder.addTransportType(0);
        NetworkRequest build = builder.build();
        this.e = new AnonymousClass2();
        this.d.requestNetwork(build, this.e);
        AppMethodBeat.o(8045);
    }

    public static String b(String str) {
        AppMethodBeat.i(8055, false);
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
        if (indexOf4 >= 0) {
            str = str.substring(0, indexOf4);
        }
        AppMethodBeat.o(8055);
        return str;
    }

    private void b() {
        ConnectivityManager connectivityManager;
        ConnectivityManager.NetworkCallback networkCallback;
        AppMethodBeat.i(8040, false);
        if (!(Build.VERSION.SDK_INT < 21 || (connectivityManager = this.d) == null || (networkCallback = this.e) == null)) {
            try {
                connectivityManager.unregisterNetworkCallback(networkCallback);
            } catch (Throwable th) {
                CtAuth.warn(b, "unregisterNetworkCallback", th);
            }
            this.d = null;
        }
        AppMethodBeat.o(8040);
    }

    private boolean b(Context context, String str) {
        boolean z = false;
        AppMethodBeat.i(8051, false);
        try {
            Class<?> cls = Class.forName("android.net.ConnectivityManager");
            this.g = 0;
            this.h = System.currentTimeMillis();
            this.d = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (this.d.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) != 0) {
                cls.getMethod("startUsingNetworkFeature", Integer.TYPE, String.class).invoke(this.d, 0, "enableHIPRI");
                int i = 0;
                while (true) {
                    if (i >= 5) {
                        break;
                    }
                    try {
                        if (this.d.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                            break;
                        }
                        Thread.sleep(500);
                        i++;
                    } catch (Throwable th) {
                        CtAuth.warn(b, "switchToMobileForUnderL", th);
                    }
                }
            }
            z = ((Boolean) cls.getMethod("requestRouteToHost", Integer.TYPE, Integer.TYPE).invoke(this.d, 5, Integer.valueOf(a(b(str))))).booleanValue();
            this.g = System.currentTimeMillis() - this.h;
            CtAuth.info(b, "Switch network result \uff1a " + z + " (4.x) , expendTime \uff1a" + this.g);
        } catch (Throwable th2) {
            CtAuth.warn(b, "4.x\u7f51\u7edc\u5207\u6362\u5f02\u5e38", th2);
        }
        AppMethodBeat.o(8051);
        return z;
    }

    static /* synthetic */ void c(e eVar) {
        AppMethodBeat.i(8063, false);
        eVar.b();
        AppMethodBeat.o(8063);
    }

    public void a(int i) {
        AppMethodBeat.i(8036, false);
        g.a(new AnonymousClass1(i));
        AppMethodBeat.o(8036);
    }

    public void a(Context context, a aVar) {
        AppMethodBeat.i(8028, false);
        this.f = aVar;
        try {
            a(context);
        } catch (Throwable th) {
            CtAuth.warn(b, "switchToMobileForAboveL", th);
            a aVar2 = this.f;
            if (aVar2 != null) {
                aVar2.a(80801, "WIFI\u5207\u6362\u5f02\u5e38", -1);
            }
        }
        AppMethodBeat.o(8028);
    }

    public boolean a(Context context, String str) {
        AppMethodBeat.i(8032, false);
        boolean b2 = b(context, str);
        AppMethodBeat.o(8032);
        return b2;
    }
}
