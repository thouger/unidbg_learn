package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.commonsdk.debug.UMLog;
import org.repackage.com.a.a.a.a;

/* compiled from: OppoDeviceIdSupplier */
public class ad implements y {
    private boolean a = false;

    @Override // com.umeng.analytics.pro.y
    public String a(Context context) {
        try {
            if (!this.a) {
                a.a(context);
                this.a = true;
            }
            if (a.a()) {
                return a.b(context);
            }
            UMLog.mutlInfo(2, "\u5f53\u524d\u8bbe\u5907\u4e0d\u652f\u6301\u83b7\u53d6OAID");
            return null;
        } catch (Exception unused) {
            UMLog.mutlInfo(2, "\u672a\u68c0\u6d4b\u5230\u60a8\u96c6\u6210OAID SDK\u5305");
            return null;
        }
    }
}
