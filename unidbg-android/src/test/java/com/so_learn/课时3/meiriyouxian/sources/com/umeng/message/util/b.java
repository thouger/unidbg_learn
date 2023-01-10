package com.umeng.message.util;

import android.content.Context;
import com.alibaba.sdk.android.httpdns.HttpDns;
import com.alibaba.sdk.android.httpdns.HttpDnsService;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.MsgConstant;

/* compiled from: HttpDnsManager */
public class b {
    private static final String a = b.class.getSimpleName();
    private static final int b = 100;

    public static void a(Context context) {
        HttpDns.getService(context, MsgConstant.ACCOUNT_ID).setExpiredIPEnabled(true);
    }

    public static String a(Context context, String str) {
        String str2 = null;
        try {
            HttpDnsService service = HttpDns.getService(context, MsgConstant.ACCOUNT_ID);
            do {
                str2 = service.getIpByHostAsync(str);
                Thread.sleep(100);
            } while (str2 == null);
        } catch (InterruptedException unused) {
            UMLog.mutlInfo(a, 0, "Thread interrupted exception");
        }
        return str2;
    }
}
