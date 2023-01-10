package com.hmt.analytics.task;

import android.content.Context;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.hmt.analytics.android.a;
import com.hmt.analytics.util.i;

public class WaTask {
    public static native String check(Context context, String str, String str2, String str3, String str4);

    public static String a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        System.load(str);
        a.a += ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
        String check = check(context.getApplicationContext(), a.B(context), a.C(context), a.f(context), a.g(context));
        a.a += "B";
        i.a(context, "temp_key_from_so", check);
        return check;
    }
}
