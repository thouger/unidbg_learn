package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.commonsdk.debug.UMLog;
import org.repackage.com.vivo.identifier.c;

/* compiled from: VivoDeviceIdSupplier */
public class af implements y {
    @Override // com.umeng.analytics.pro.y
    public String a(Context context) {
        try {
            if (c.a(context)) {
                return c.b(context);
            }
            UMLog.mutlInfo(2, "\u5f53\u524d\u8bbe\u5907\u4e0d\u652f\u6301\u83b7\u53d6OAID");
            return null;
        } catch (Exception unused) {
            UMLog.mutlInfo(2, "\u672a\u68c0\u6d4b\u5230\u60a8\u96c6\u6210OAID SDK\u5305");
            return null;
        }
    }
}
