package com.alipay.sdk.util;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.a.a;
import com.alipay.sdk.app.EnvUtils;

public class k {
    public static String a(Context context) {
        if (EnvUtils.a()) {
            return "https://mobilegw.alipaydev.com/mgw.htm";
        }
        if (context == null) {
            return a.a;
        }
        String str = a.a;
        return TextUtils.isEmpty(str) ? a.a : str;
    }
}
