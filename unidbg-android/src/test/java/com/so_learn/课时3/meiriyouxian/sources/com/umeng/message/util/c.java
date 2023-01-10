package com.umeng.message.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Build;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.proguard.h;
import java.net.URLEncoder;

/* compiled from: NetworkHelper */
public class c {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    private static final String i = c.class.getName();
    private final int d = 1;
    private String e;
    private String f = "10.0.0.172";
    private int g = 80;
    private Context h;

    public c(Context context) {
        this.h = context;
        this.e = a(context);
    }

    private boolean a() {
        String extraInfo;
        if (this.h.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.h.getPackageName()) != 0) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.h.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (activeNetworkInfo == null || activeNetworkInfo.getType() == 1 || (extraInfo = activeNetworkInfo.getExtraInfo()) == null || (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap") && !extraInfo.equals("uniwap"))) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] a(byte[] r8, java.lang.String r9) {
        /*
            r7 = this;
            r0 = 1
            r1 = 0
            r2 = 0
            java.net.URL r3 = new java.net.URL     // Catch:{ Exception -> 0x0063 }
            r3.<init>(r9)     // Catch:{ Exception -> 0x0063 }
            java.net.URLConnection r9 = r3.openConnection()     // Catch:{ Exception -> 0x0063 }
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ Exception -> 0x0063 }
            java.lang.String r3 = "POST"
            r9.setRequestMethod(r3)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r3 = 10000(0x2710, float:1.4013E-41)
            r9.setReadTimeout(r3)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r9.setConnectTimeout(r3)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            java.lang.String r3 = "Msg-Type"
            java.lang.String r4 = "envelope"
            r9.setRequestProperty(r3, r4)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r9.setDoOutput(r0)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r9.setChunkedStreamingMode(r2)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            java.io.OutputStream r4 = r9.getOutputStream()     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r3.write(r8)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r3.flush()     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r3.close()     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            java.io.BufferedInputStream r8 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            java.io.InputStream r3 = r9.getInputStream()     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r8.<init>(r3)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            byte[] r1 = com.umeng.message.proguard.h.a(r8)     // Catch:{ all -> 0x0054 }
            com.umeng.message.proguard.h.a(r8)
            if (r9 == 0) goto L_0x0052
            r9.disconnect()
        L_0x0052:
            r9 = r1
            goto L_0x008c
        L_0x0054:
            r3 = move-exception
            com.umeng.message.proguard.h.a(r8)
            throw r3
        L_0x0059:
            r8 = move-exception
            goto L_0x008d
        L_0x005b:
            r8 = move-exception
            r6 = r1
            r1 = r9
            r9 = r6
            goto L_0x0065
        L_0x0060:
            r8 = move-exception
            r9 = r1
            goto L_0x008d
        L_0x0063:
            r8 = move-exception
            r9 = r1
        L_0x0065:
            java.lang.String r3 = com.umeng.message.util.c.i     // Catch:{ all -> 0x0060 }
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch:{ all -> 0x0060 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0060 }
            r4.<init>()     // Catch:{ all -> 0x0060 }
            java.lang.String r5 = "sendMessage:"
            r4.append(r5)     // Catch:{ all -> 0x0060 }
            java.lang.String r5 = r8.getMessage()     // Catch:{ all -> 0x0060 }
            r4.append(r5)     // Catch:{ all -> 0x0060 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0060 }
            r0[r2] = r4     // Catch:{ all -> 0x0060 }
            com.umeng.commonsdk.debug.UMLog.mutlInfo(r3, r2, r0)     // Catch:{ all -> 0x0060 }
            r8.printStackTrace()     // Catch:{ all -> 0x0060 }
            if (r1 == 0) goto L_0x008c
            r1.disconnect()
        L_0x008c:
            return r9
        L_0x008d:
            if (r9 == 0) goto L_0x0092
            r9.disconnect()
        L_0x0092:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.util.c.a(byte[], java.lang.String):byte[]");
    }

    private String a(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(MsgConstant.SDK_VERSION);
        stringBuffer.append(NotificationIconUtil.SPLIT_CHAR);
        stringBuffer.append(MsgConstant.SDK_VERSION);
        stringBuffer.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
        try {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(UmengMessageDeviceConfig.getApplicationLable(context));
            stringBuffer2.append(NotificationIconUtil.SPLIT_CHAR);
            stringBuffer2.append(UmengMessageDeviceConfig.getAppVersionName(context));
            stringBuffer2.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            stringBuffer2.append(Build.MODEL);
            stringBuffer2.append(NotificationIconUtil.SPLIT_CHAR);
            stringBuffer2.append(Build.VERSION.RELEASE);
            stringBuffer2.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            stringBuffer2.append(h.a(PushAgent.getInstance(context).getMessageAppkey()));
            stringBuffer.append(URLEncoder.encode(stringBuffer2.toString(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
