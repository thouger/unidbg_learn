package cn.missfresh.module.base.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiEnterpriseConfig;
import android.telephony.TelephonyManager;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

/* compiled from: NetWorkUtil */
public class af {
    private static boolean a = false;
    private static boolean b = false;
    private static volatile String c = "\u672a\u77e5";
    private static String d = null;
    private static String e = null;
    private static int f = 3;
    private static int g;

    private static int a(int i) {
        int i2 = -101;
        if (i != -101) {
            i2 = -1;
            if (i != -1) {
                switch (i) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return 1;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return 2;
                    case 13:
                        return 3;
                    default:
                        return 0;
                }
            }
        }
        return i2;
    }

    public static String a() {
        AppMethodBeat.i(23367, false);
        if (!e.a) {
            AppMethodBeat.o(23367);
            return "";
        } else if (r.q() != null) {
            String a2 = a(r.q().getApplication());
            AppMethodBeat.o(23367);
            return a2;
        } else {
            AppMethodBeat.o(23367);
            return "";
        }
    }

    public static String a(Context context) {
        AppMethodBeat.i(23368, false);
        if (!e.a) {
            AppMethodBeat.o(23368);
            return "";
        }
        String str = b(context) + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + d(context);
        AppMethodBeat.o(23368);
        return str;
    }

    public static String b(Context context) {
        AppMethodBeat.i(23369, false);
        if (!e.a) {
            String str = c;
            AppMethodBeat.o(23369);
            return str;
        } else if ("\u672a\u77e5".equals(c)) {
            String str2 = c;
            AppMethodBeat.o(23369);
            return str2;
        } else if (!new c().a("android.permission.READ_PHONE_STATE")) {
            String str3 = c;
            AppMethodBeat.o(23369);
            return str3;
        } else {
            int i = g;
            if (i > f) {
                String str4 = c;
                AppMethodBeat.o(23369);
                return str4;
            }
            g = i + 1;
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    if (b.a(d)) {
                        d = telephonyManager.getSubscriberId();
                    }
                    if (d == null) {
                        if (5 == telephonyManager.getSimState()) {
                            if (b.a(e)) {
                                e = telephonyManager.getSimOperator();
                            }
                            if (e == null) {
                                e = "\u672a\u77e5";
                                d = "\u672a\u77e5";
                            } else if (e.equals("46000") || e.equals("46002") || e.equals("46007")) {
                                c = "\u4e2d\u56fd\u79fb\u52a8";
                            } else if (e.equals("46001")) {
                                c = "\u4e2d\u56fd\u8054\u901a";
                            } else if (e.equals("46003")) {
                                c = "\u4e2d\u56fd\u7535\u4fe1";
                            }
                        }
                    } else if (d.startsWith("46000") || d.startsWith("46002") || d.startsWith("46007")) {
                        c = "\u4e2d\u56fd\u79fb\u52a8";
                    } else if (d.startsWith("46001")) {
                        c = "\u4e2d\u56fd\u8054\u901a";
                    } else if (d.startsWith("46003")) {
                        c = "\u4e2d\u56fd\u7535\u4fe1";
                    }
                }
            } catch (Exception unused) {
            }
            String str5 = c;
            AppMethodBeat.o(23369);
            return str5;
        }
    }

    private static String d(Context context) {
        AppMethodBeat.i(23370, false);
        String str = "";
        if (!e.a) {
            AppMethodBeat.o(23370);
            return str;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
            if (networkInfo == null || !networkInfo.isConnected()) {
                a = false;
            } else {
                a = true;
                str = c(context);
            }
            if (networkInfo2 == null || !networkInfo2.isConnected()) {
                b = false;
            } else {
                str = c(context);
                b = true;
            }
        }
        AppMethodBeat.o(23370);
        return str;
    }

    public static String c(Context context) {
        AppMethodBeat.i(23371, false);
        int e2 = e(context);
        String str = "\u672a\u77e5";
        if (e2 == -101) {
            str = "Wi-Fi";
        } else if (e2 == -1) {
            str = "\u65e0";
        } else if (e2 != 0) {
            if (e2 == 1) {
                str = "2G";
            } else if (e2 == 2) {
                str = "3G";
            } else if (e2 == 3) {
                str = "4G";
            }
        }
        AppMethodBeat.o(23371);
        return str;
    }

    private static int e(Context context) {
        int i = 0;
        AppMethodBeat.i(23372, false);
        if (!e.a) {
            AppMethodBeat.o(23372);
            return 0;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable() || !activeNetworkInfo.isConnected()) {
                i = -1;
                int a2 = a(i);
                AppMethodBeat.o(23372);
                return a2;
            }
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                i = -101;
            } else if (type == 0) {
                i = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
            }
            int a2 = a(i);
            AppMethodBeat.o(23372);
            return a2;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
