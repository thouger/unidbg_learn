package com.umeng.commonsdk.statistics;

import com.umeng.commonsdk.statistics.common.ULog;

public class AnalyticsConstants {
    public static String[] APPLOG_URL_LIST = null;
    public static final String CFG_FIELD_KEY = "cfgfd";
    public static boolean CHECK_DEVICE = true;
    public static final String LOG_TAG = "MobclickAgent";
    public static final String OS = "Android";
    public static final String SDK_TYPE = "Android";
    public static boolean SUB_PROCESS_EVENT = false;
    public static final boolean UM_DEBUG = ULog.DEBUG;
    public static final String ZERO_RESPONSE_FLAG = "iscfg";
    private static int commonDeviceType = 1;

    static {
        APPLOG_URL_LIST = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
    }

    public static void setDeviceType(int i) {
        commonDeviceType = i;
    }

    public static synchronized int getDeviceType() {
        int i;
        synchronized (AnalyticsConstants.class) {
            i = commonDeviceType;
        }
        return i;
    }
}
