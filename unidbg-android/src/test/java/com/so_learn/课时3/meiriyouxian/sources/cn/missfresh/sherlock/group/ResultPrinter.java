package cn.missfresh.sherlock.group;

import java.util.Arrays;

public class ResultPrinter {
    private static String arrayToString(Object obj) {
        if (obj instanceof Object[]) {
            return Arrays.deepToString((Object[]) obj);
        }
        if (obj instanceof int[]) {
            return Arrays.toString((int[]) obj);
        }
        if (obj instanceof char[]) {
            return Arrays.toString((char[]) obj);
        }
        if (obj instanceof boolean[]) {
            return Arrays.toString((boolean[]) obj);
        }
        if (obj instanceof byte[]) {
            return Arrays.toString((byte[]) obj);
        }
        if (obj instanceof long[]) {
            return Arrays.toString((long[]) obj);
        }
        if (obj instanceof double[]) {
            return Arrays.toString((double[]) obj);
        }
        if (obj instanceof float[]) {
            return Arrays.toString((float[]) obj);
        }
        return obj instanceof short[] ? Arrays.toString((short[]) obj) : "Unknown type array";
    }

    public static void printWithCustomLogger(String str, String str2, long j, byte b) {
        SherlockLoggerHandler sherlockLoggerHandler = SherlockLoggerHandler.CUSTOM_IMPL;
        sherlockLoggerHandler.log(str, str2, j, String.format("\u21e0 %s[%sms]=\"%s\"", str2, j + "", ((int) b) + ""));
    }

    public static void printWithCustomLogger(String str, String str2, long j, char c) {
        SherlockLoggerHandler sherlockLoggerHandler = SherlockLoggerHandler.CUSTOM_IMPL;
        sherlockLoggerHandler.log(str, str2, j, String.format("\u21e0 %s[%sms]=\"%s\"", str2, j + "", c + ""));
    }

    public static void printWithCustomLogger(String str, String str2, long j, short s) {
        SherlockLoggerHandler sherlockLoggerHandler = SherlockLoggerHandler.CUSTOM_IMPL;
        sherlockLoggerHandler.log(str, str2, j, String.format("\u21e0 %s[%sms]=\"%s\"", str2, j + "", ((int) s) + ""));
    }

    public static void printWithCustomLogger(String str, String str2, long j, int i) {
        SherlockLoggerHandler sherlockLoggerHandler = SherlockLoggerHandler.CUSTOM_IMPL;
        sherlockLoggerHandler.log(str, str2, j, String.format("\u21e0 %s[%sms]=\"%s\"", str2, j + "", i + ""));
    }

    public static void printWithCustomLogger(String str, String str2, long j, boolean z) {
        SherlockLoggerHandler sherlockLoggerHandler = SherlockLoggerHandler.CUSTOM_IMPL;
        sherlockLoggerHandler.log(str, str2, j, String.format("\u21e0 %s[%sms]=\"%s\"", str2, j + "", z + ""));
    }

    public static void printWithCustomLogger(String str, String str2, long j, long j2) {
        SherlockLoggerHandler sherlockLoggerHandler = SherlockLoggerHandler.CUSTOM_IMPL;
        sherlockLoggerHandler.log(str, str2, j, String.format("\u21e0 %s[%sms]=\"%s\"", str2, j + "", j2 + ""));
    }

    public static void printWithCustomLogger(String str, String str2, long j, float f) {
        SherlockLoggerHandler sherlockLoggerHandler = SherlockLoggerHandler.CUSTOM_IMPL;
        sherlockLoggerHandler.log(str, str2, j, String.format("\u21e0 %s[%sms]=\"%s\"", str2, j + "", f + ""));
    }

    public static void printWithCustomLogger(String str, String str2, long j, double d) {
        SherlockLoggerHandler sherlockLoggerHandler = SherlockLoggerHandler.CUSTOM_IMPL;
        sherlockLoggerHandler.log(str, str2, j, String.format("\u21e0 %s[%sms]=\"%s\"", str2, j + "", d + ""));
    }

    public static void printWithCustomLogger(String str, String str2, long j, Object obj) {
        if (obj == null || !obj.getClass().isArray()) {
            SherlockLoggerHandler sherlockLoggerHandler = SherlockLoggerHandler.CUSTOM_IMPL;
            sherlockLoggerHandler.log(str, str2, j, String.format("\u21e0 %s[%sms]=\"%s\"", str2, j + "", obj));
            return;
        }
        SherlockLoggerHandler sherlockLoggerHandler2 = SherlockLoggerHandler.CUSTOM_IMPL;
        sherlockLoggerHandler2.log(str, str2, j, String.format("\u21e0 %s[%sms]=\"%s\"", str2, j + "", arrayToString(obj)));
    }
}
