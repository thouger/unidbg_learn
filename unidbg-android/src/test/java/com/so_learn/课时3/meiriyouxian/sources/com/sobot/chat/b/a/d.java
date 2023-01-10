package com.sobot.chat.b.a;

import android.app.Activity;
import android.graphics.Rect;
import android.text.TextUtils;
import com.sobot.chat.b.a;
import com.sobot.chat.b.b.b;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;

/* compiled from: OppoNotchScreen */
public class d implements a {
    @Override // com.sobot.chat.b.a
    @Deprecated
    public void b(Activity activity) {
    }

    private static String a() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls.newInstance(), "ro.oppo.screen.heteromorphism");
        } catch (Throwable unused) {
            return "";
        }
    }

    @Override // com.sobot.chat.b.a
    public boolean a(Activity activity) {
        try {
            return activity.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.sobot.chat.b.a
    public void a(Activity activity, a.c cVar) {
        int i;
        int i2;
        int i3;
        int i4;
        try {
            String a = a();
            if (!TextUtils.isEmpty(a)) {
                String[] split = a.split(":");
                String[] split2 = split[0].split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                String[] split3 = split[1].split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                if (b.a(activity)) {
                    i = Integer.valueOf(split2[0]).intValue();
                    i3 = Integer.valueOf(split2[1]).intValue();
                    i2 = Integer.valueOf(split3[0]).intValue();
                    i4 = Integer.valueOf(split3[1]).intValue();
                } else {
                    i = Integer.valueOf(split2[1]).intValue();
                    i3 = Integer.valueOf(split2[0]).intValue();
                    int intValue = Integer.valueOf(split3[1]).intValue();
                    i4 = Integer.valueOf(split3[0]).intValue();
                    i2 = intValue;
                }
                Rect rect = new Rect(i, i3, i2, i4);
                ArrayList arrayList = new ArrayList();
                arrayList.add(rect);
                cVar.a(arrayList);
            }
        } catch (Throwable unused) {
            cVar.a(null);
        }
    }
}
