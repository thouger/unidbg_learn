package com.sobot.chat.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.sobot.chat.api.apiUtils.GsonUtil;
import com.sobot.chat.api.apiUtils.ZhiChiConstants;
import com.sobot.chat.api.model.ZhiChiPushMessage;
import com.sobot.chat.core.a.a.j;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Util */
public class af {
    public static boolean a;

    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    public static String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("msgId", str);
            jSONObject.put("type", 301);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String b(String str) {
        try {
            return new JSONObject(str).optString("msgId");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String c(String str) {
        return str + System.currentTimeMillis();
    }

    public static void a(Context context, int i) {
        Intent intent = new Intent();
        intent.setAction(ZhiChiConstants.SOBOT_CHANNEL_STATUS_CHANGE);
        intent.putExtra("connStatus", i);
        d.a(context, intent);
    }

    public static void a(Context context, j jVar) {
        a(context, jVar.b());
    }

    public static void a(Context context, String str) {
        m.d("======\u6536\u5230\u5ba2\u670d\u6d88\u606f=========" + str);
        ZhiChiPushMessage jsonToZhiChiPushMessage = GsonUtil.jsonToZhiChiPushMessage(str);
        if (jsonToZhiChiPushMessage != null) {
            Intent intent = new Intent();
            LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context.getApplicationContext());
            intent.setAction(ZhiChiConstants.receiveMessageBrocast);
            intent.putExtra("msgContent", str);
            intent.putExtra(ZhiChiConstants.ZHICHI_PUSH_MESSAGE, jsonToZhiChiPushMessage);
            instance.sendBroadcast(intent);
        }
    }

    public static void a(String str, String str2) {
        if (a) {
            Log.i(str, str2);
        }
    }
}
