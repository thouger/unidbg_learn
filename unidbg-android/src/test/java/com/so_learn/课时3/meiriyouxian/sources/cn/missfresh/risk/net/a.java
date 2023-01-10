package cn.missfresh.risk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.security.keystore.KeyProperties;
import android.telephony.PreciseDisconnectCause;
import android.telephony.TelephonyManager;
import cn.missfresh.risk.bean.NetBean;
import cn.missfresh.risk.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.xiaomi.mipush.sdk.Constants;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/* compiled from: NetWorkUtil */
public class a {
    public static String a(Context context) {
        String str = KeyProperties.DIGEST_NONE;
        AppMethodBeat.i(2220, false);
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                AppMethodBeat.o(2220);
                return str;
            }
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                str = "wifi";
            } else if (type == 0) {
                int subtype = activeNetworkInfo.getSubtype();
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                str = (subtype != 13 || telephonyManager.isNetworkRoaming()) ? (subtype == 3 || subtype == 8 || (subtype == 5 && !telephonyManager.isNetworkRoaming())) ? "3G" : (subtype == 1 || subtype == 2 || (subtype == 4 && !telephonyManager.isNetworkRoaming())) ? "2G" : "NO DISPLAY" : "4G";
            }
            AppMethodBeat.o(2220);
            return str;
        } catch (Exception unused) {
        }
    }

    public static WifiInfo b(Context context) {
        AppMethodBeat.i(2228, false);
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
            AppMethodBeat.o(2228);
            return connectionInfo;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(2228);
            return null;
        }
    }

    public static String c(Context context) {
        String str = "";
        AppMethodBeat.i(2229, false);
        try {
            WifiInfo b = b(context);
            if (b != null) {
                str = b.getSSID().replace("\"", str);
            }
            AppMethodBeat.o(2229);
            return str;
        } catch (Exception unused) {
            AppMethodBeat.o(2229);
            return str;
        }
    }

    public static String d(Context context) {
        String str = "";
        AppMethodBeat.i(2233, false);
        try {
            WifiInfo b = b(context);
            if (b != null) {
                str = b.getBSSID();
            }
            AppMethodBeat.o(2233);
            return str;
        } catch (Exception unused) {
            AppMethodBeat.o(2233);
            return str;
        }
    }

    public static String a() {
        AppMethodBeat.i(2277, false);
        try {
            c.a a = c.a("getprop dhcp.eth0.dns1", false);
            if (a != null && a.a == 0) {
                String str = a.c;
                AppMethodBeat.o(2277);
                return str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(2277);
        return "unknow";
    }

    public static List<NetBean> b() {
        String[] split;
        AppMethodBeat.i(2281, false);
        try {
            ArrayList arrayList = new ArrayList();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            int i = 0;
            while (networkInterfaces != null && networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement != null && nextElement.getInetAddresses().hasMoreElements()) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement2 = inetAddresses.nextElement();
                        if (nextElement2.getCanonicalHostName() != null && nextElement2.getCanonicalHostName().contains("%") && (split = nextElement2.getCanonicalHostName().split("%")) != null && split.length > 1) {
                            arrayList.add(new NetBean(split[1], split[0]));
                            i++;
                            if (i >= cn.missfresh.risk.b.a.c) {
                                break;
                            }
                        }
                    }
                }
                if (i >= cn.missfresh.risk.b.a.b) {
                    break;
                }
            }
            AppMethodBeat.o(2281);
            return arrayList;
        } catch (SocketException e) {
            e.printStackTrace();
            AppMethodBeat.o(2281);
            return null;
        }
    }

    public static String e(Context context) {
        AppMethodBeat.i(2285, false);
        String str = "unknow";
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (!wifiManager.isWifiEnabled()) {
                AppMethodBeat.o(2285);
                return str;
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo == null) {
                AppMethodBeat.o(2285);
                return str;
            }
            int ipAddress = connectionInfo.getIpAddress();
            if (ipAddress == 0) {
                AppMethodBeat.o(2285);
                return str;
            }
            str = a(ipAddress);
            if (str == null || str.startsWith("0")) {
                AppMethodBeat.o(2285);
                return str;
            }
            AppMethodBeat.o(2285);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String f(Context context) {
        AppMethodBeat.i(2289, false);
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        new ArrayList();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == 0) {
                try {
                    Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                    while (networkInterfaces != null && networkInterfaces.hasMoreElements()) {
                        Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                        while (true) {
                            if (inetAddresses.hasMoreElements()) {
                                InetAddress nextElement = inetAddresses.nextElement();
                                if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                                    String hostAddress = nextElement.getHostAddress();
                                    AppMethodBeat.o(2289);
                                    return hostAddress;
                                }
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }
            } else if (activeNetworkInfo.getType() == 1) {
                String a = a(((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress());
                AppMethodBeat.o(2289);
                return a;
            }
        }
        AppMethodBeat.o(2289);
        return null;
    }

    public static List<String> g(Context context) {
        int i = 0;
        AppMethodBeat.i(2294, false);
        ArrayList arrayList = new ArrayList();
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            wifiManager.startScan();
            for (ScanResult scanResult : wifiManager.getScanResults()) {
                String str = scanResult.BSSID;
                String str2 = scanResult.SSID;
                int i2 = scanResult.level;
                arrayList.add(scanResult.BSSID + Constants.ACCEPT_TIME_SEPARATOR_SP + scanResult.level);
                i++;
                if (i >= 20) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(2294);
        return arrayList;
    }

    public static String a(int i) {
        AppMethodBeat.i(PreciseDisconnectCause.SUPP_SVC_FAILED, false);
        String str = (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
        AppMethodBeat.o(PreciseDisconnectCause.SUPP_SVC_FAILED);
        return str;
    }
}
