package com.umeng.commonsdk;

import android.content.Context;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.utils.UMUtils;
import java.lang.reflect.Method;

/* compiled from: UMInnerManager */
public class a {
    private static Class<?> a;
    private static Method b;

    static {
        try {
            Class<?> cls = Class.forName("com.umeng.commonsdk.UMInnerImpl");
            if (cls != null) {
                a = cls;
                Method declaredMethod = a.getDeclaredMethod("initAndSendInternal", Context.class);
                if (declaredMethod != null) {
                    b = declaredMethod;
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context) {
        Method method;
        if (context != null && UMUtils.isMainProgress(context)) {
            if (SdkVersion.SDK_TYPE != 1) {
                Class<?> cls = a;
                if (cls != null && (method = b) != null) {
                    try {
                        method.invoke(cls, context);
                    } catch (Throwable unused) {
                    }
                }
            } else {
                UMConfigureInternation.sendInternal(context);
            }
        }
    }
}
