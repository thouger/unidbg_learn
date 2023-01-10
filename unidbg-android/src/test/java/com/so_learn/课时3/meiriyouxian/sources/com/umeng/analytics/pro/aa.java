package com.umeng.analytics.pro;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.message.MsgConstant;

/* compiled from: DeviceIdSupplier */
public class aa {
    public static y a(Context context) {
        String str = Build.BRAND;
        ah.a("Device", "Brand", str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.equalsIgnoreCase("huawei") || str.equalsIgnoreCase("honor") || str.equalsIgnoreCase("\u534e\u4e3a")) {
            return new ab();
        }
        if (str.equalsIgnoreCase(MsgConstant.MESSAGE_SYSTEM_SOURCE_XIAOMI) || str.equalsIgnoreCase("redmi") || str.equalsIgnoreCase("meitu") || str.equalsIgnoreCase("\u5c0f\u7c73")) {
            return new ag();
        }
        if (str.equalsIgnoreCase("vivo")) {
            return new af();
        }
        if (str.equalsIgnoreCase("oppo") || str.equalsIgnoreCase("oneplus")) {
            return new ad();
        }
        if (str.equalsIgnoreCase("lenovo") || str.equalsIgnoreCase("zuk")) {
            return new ac();
        }
        if (Build.MANUFACTURER.equalsIgnoreCase("SAMSUNG")) {
            return new ae();
        }
        return null;
    }
}
