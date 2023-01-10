package com.sina.weibo.sdk.statistic;

import android.content.Context;
import com.sina.weibo.sdk.a.d;
import java.util.Map;

public class WBAgent {
    public static void onEvent(Object obj, String str) {
        onEvent(obj, str, null);
    }

    public static void onEvent(Object obj, String str, Map<String, String> map) {
        if (obj == null) {
            d.c("WBAgent", "unexpected null page or activity in onEvent");
        } else if (str == null) {
            d.c("WBAgent", "unexpected null eventId in onEvent");
        } else {
            if (obj instanceof Context) {
                obj = obj.getClass().getName();
            }
            WBAgentHandler.a().onEvent((String) obj, str, map);
        }
    }
}
