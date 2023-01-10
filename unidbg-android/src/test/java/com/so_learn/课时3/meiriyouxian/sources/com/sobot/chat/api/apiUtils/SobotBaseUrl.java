package com.sobot.chat.api.apiUtils;

import android.text.TextUtils;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;

public class SobotBaseUrl {
    private static String api_host = null;
    private static final String baseHost = "https://api.sobot.com/";
    public static final String defaultHostname = "api.sobot.com";
    private static String mHost;

    @Deprecated
    public static void setHost(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!str.endsWith(NotificationIconUtil.SPLIT_CHAR)) {
            mHost = str + NotificationIconUtil.SPLIT_CHAR;
            api_host = mHost;
            return;
        }
        mHost = str;
        api_host = mHost;
    }

    public static void setApi_Host(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!str.endsWith(NotificationIconUtil.SPLIT_CHAR)) {
            api_host = str + NotificationIconUtil.SPLIT_CHAR;
            mHost = api_host;
            return;
        }
        api_host = str;
        mHost = api_host;
    }

    @Deprecated
    public static String getHost() {
        return !TextUtils.isEmpty(mHost) ? mHost : baseHost;
    }

    public static String getApi_Host() {
        return !TextUtils.isEmpty(api_host) ? api_host : baseHost;
    }

    public static String getBaseIp() {
        return getApi_Host() + "chat-sdk/sdk/user/v1/";
    }

    public static String getBaseIp3() {
        return getApi_Host() + "chat-sdk/sdk/user/v2/";
    }
}
