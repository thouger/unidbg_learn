package com.cmic.sso.wy.utils;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: TimeUtils */
public class w {
    public static String a() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(System.currentTimeMillis()));
    }

    private static String a(Date date) {
        return a("yyyyMMdd", date);
    }

    public static String b() {
        return a(new Date());
    }

    private static String a(String str, Date date) {
        if (date == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return new SimpleDateFormat(str).format(date);
    }

    public static String a(long j) {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(j));
    }
}
