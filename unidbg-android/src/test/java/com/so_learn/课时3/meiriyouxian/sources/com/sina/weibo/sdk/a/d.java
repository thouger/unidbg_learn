package com.sina.weibo.sdk.a;

import android.util.Log;
import com.umeng.message.proguard.l;

/* compiled from: LogUtil */
public class d {
    public static boolean a;

    public static void a(String str, String str2) {
        if (a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            StringBuilder sb = new StringBuilder(String.valueOf(String.valueOf(stackTraceElement.getFileName()) + l.s + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()));
            sb.append(": ");
            sb.append(str2);
            Log.d(str, sb.toString());
        }
    }

    public static void b(String str, String str2) {
        if (a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            StringBuilder sb = new StringBuilder(String.valueOf(String.valueOf(stackTraceElement.getFileName()) + l.s + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()));
            sb.append(": ");
            sb.append(str2);
            Log.i(str, sb.toString());
        }
    }

    public static void c(String str, String str2) {
        if (a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            StringBuilder sb = new StringBuilder(String.valueOf(String.valueOf(stackTraceElement.getFileName()) + l.s + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()));
            sb.append(": ");
            sb.append(str2);
            Log.e(str, sb.toString());
        }
    }

    public static void d(String str, String str2) {
        if (a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            StringBuilder sb = new StringBuilder(String.valueOf(String.valueOf(stackTraceElement.getFileName()) + l.s + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()));
            sb.append(": ");
            sb.append(str2);
            Log.w(str, sb.toString());
        }
    }

    public static void e(String str, String str2) {
        if (a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            StringBuilder sb = new StringBuilder(String.valueOf(String.valueOf(stackTraceElement.getFileName()) + l.s + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()));
            sb.append(": ");
            sb.append(str2);
            Log.v(str, sb.toString());
        }
    }
}
