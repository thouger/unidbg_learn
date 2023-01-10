package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiEnterpriseConfig;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.ULog;

/* compiled from: BaseStationUtils */
public class b {
    private static final String b = "BaseStationUtils";
    private static boolean c;
    private static Context d;
    PhoneStateListener a;
    private TelephonyManager e;

    /* synthetic */ b(Context context, AnonymousClass1 r2) {
        this(context);
    }

    private b(Context context) {
        this.a = new AnonymousClass1();
        if (context != null) {
            try {
                this.e = (TelephonyManager) context.getSystemService("phone");
            } catch (Throwable unused) {
            }
        }
    }

    /* compiled from: BaseStationUtils */
    private static class a {
        private static final b a = new b(b.d, null);

        private a() {
        }
    }

    public static b a(Context context) {
        if (d == null && context != null) {
            d = context.getApplicationContext();
        }
        return a.a;
    }

    public synchronized boolean a() {
        return c;
    }

    /* compiled from: BaseStationUtils */
    /* renamed from: com.umeng.commonsdk.internal.utils.b$1  reason: invalid class name */
    class AnonymousClass1 extends PhoneStateListener {
        AnonymousClass1() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            String str;
            super.onSignalStrengthsChanged(signalStrength);
            ULog.e(b.b, "base station onSignalStrengthsChanged");
            try {
                b.this.e = (TelephonyManager) b.d.getSystemService("phone");
                String[] split = signalStrength.toString().split(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
                String str2 = null;
                if (b.this.e != null && b.this.e.getNetworkType() == 13) {
                    str = "" + Integer.parseInt(split[9]);
                } else if (b.this.e == null || !(b.this.e.getNetworkType() == 8 || b.this.e.getNetworkType() == 10 || b.this.e.getNetworkType() == 9 || b.this.e.getNetworkType() == 3)) {
                    str = ((signalStrength.getGsmSignalStrength() * 2) + PackageManager.INSTALL_FAILED_NO_MATCHING_ABIS) + "";
                } else {
                    String e = b.this.e();
                    if (!TextUtils.isEmpty(e) && e.equals("\u4e2d\u56fd\u79fb\u52a8")) {
                        str2 = "0";
                    } else if (!TextUtils.isEmpty(e) && e.equals("\u4e2d\u56fd\u8054\u901a")) {
                        str2 = signalStrength.getCdmaDbm() + "";
                    } else if (!TextUtils.isEmpty(e) && e.equals("\u4e2d\u56fd\u7535\u4fe1")) {
                        str2 = signalStrength.getEvdoDbm() + "";
                    }
                    str = str2;
                }
                ULog.e(b.b, "stationStrength is " + str);
                if (!TextUtils.isEmpty(str)) {
                    try {
                        UMWorkDispatch.sendEvent(b.d, 32772, com.umeng.commonsdk.internal.b.a(b.d).a(), str);
                    } catch (Throwable unused) {
                    }
                }
                b.this.c();
            } catch (Exception unused2) {
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String e() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) d.getSystemService("phone");
            if (telephonyManager == null) {
                return null;
            }
            String simOperator = telephonyManager.getSimOperator();
            if (TextUtils.isEmpty(simOperator)) {
                return null;
            }
            if (!simOperator.equals("46000")) {
                if (!simOperator.equals("46002")) {
                    if (simOperator.equals("46001")) {
                        return "\u4e2d\u56fd\u8054\u901a";
                    }
                    if (simOperator.equals("46003")) {
                        return "\u4e2d\u56fd\u7535\u4fe1";
                    }
                    return null;
                }
            }
            return "\u4e2d\u56fd\u79fb\u52a8";
        } catch (Throwable unused) {
            return null;
        }
    }

    public synchronized void b() {
        ULog.e(b, "base station registerListener");
        try {
            if (this.e != null) {
                this.e.listen(this.a, 256);
            }
            c = true;
        } catch (Throwable unused) {
        }
    }

    public synchronized void c() {
        ULog.e(b, "base station unRegisterListener");
        try {
            if (this.e != null) {
                this.e.listen(this.a, 0);
            }
            c = false;
        } catch (Throwable unused) {
        }
    }
}
