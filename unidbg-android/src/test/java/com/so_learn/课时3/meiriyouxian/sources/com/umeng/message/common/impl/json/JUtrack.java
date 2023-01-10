package com.umeng.message.common.impl.json;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.umeng.analytics.pro.ai;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageCallbackHandlerService;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.c;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.common.inter.IUtrack;
import com.umeng.message.entity.Alias;
import com.umeng.message.proguard.h;
import com.umeng.message.provider.a;
import com.umeng.message.service.UMJobIntentService;
import com.umeng.message.util.HttpRequest;
import com.umeng.message.util.b;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class JUtrack implements IUtrack {
    private static final String a = JUtrack.class.getSimpleName();
    private Context b;

    public JUtrack(Context context) {
        this.b = context.getApplicationContext();
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void sendMsgLog(JSONObject jSONObject, String str, int i) throws Exception {
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(c.c, UmengMessageDeviceConfig.getDINAes(this.b));
        jSONObject3.put(ai.aI, MsgConstant.SDK_VERSION);
        jSONObject3.put("push_switch", UmengMessageDeviceConfig.isNotificationEnabled(this.b));
        jSONObject2.put("header", jSONObject3);
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("ts", jSONObject.getLong("ts"));
        jSONObject4.put("pa", jSONObject.getString("pa"));
        jSONObject4.put("device_token", MessageSharedPrefs.getInstance(this.b).getDeviceToken());
        jSONObject4.put("msg_id", jSONObject.getString("msg_id"));
        jSONObject4.put(MsgConstant.KEY_ACTION_TYPE, jSONObject.getInt(MsgConstant.KEY_ACTION_TYPE));
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject4);
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("push", jSONArray);
        jSONObject2.put("content", jSONObject5);
        if (h.d(this.b)) {
            Context context = this.b;
            UMWorkDispatch.sendEvent(context, 16385, PushAgent.getInstance(context).getUpushLogDataProtocol(), jSONObject2.toString());
            return;
        }
        JSONObject jSONObject6 = new JSONObject();
        jSONObject6.put("jsonHeader", jSONObject3);
        jSONObject6.put("jsonBody", jSONObject5);
        jSONObject6.put("msgId", str);
        jSONObject6.put("actionType", i);
        Intent intent = new Intent();
        intent.setPackage(this.b.getPackageName());
        intent.setAction(MsgConstant.MESSAGE_MESSAGE_SEND_ACTION);
        intent.putExtra(MsgConstant.KEY_UMPX_PATH, MsgConstant.UNPX_PUSH_LOGS);
        intent.putExtra(MsgConstant.KEY_SENDMESSAGE, jSONObject6.toString());
        UMJobIntentService.enqueueWork(this.b, UmengMessageCallbackHandlerService.class, intent);
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void trackAppLaunch(JSONObject jSONObject) throws Exception {
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(c.c, UmengMessageDeviceConfig.getDINAes(this.b));
        jSONObject3.put(ai.aI, MsgConstant.SDK_VERSION);
        jSONObject3.put("push_switch", UmengMessageDeviceConfig.isNotificationEnabled(this.b));
        jSONObject2.put("header", jSONObject3);
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("device_token", MessageSharedPrefs.getInstance(this.b).getDeviceToken());
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject4);
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("push", jSONArray);
        jSONObject2.put("content", jSONObject5);
        if (h.d(this.b)) {
            Context context = this.b;
            UMWorkDispatch.sendEvent(context, 16386, PushAgent.getInstance(context).getUpushLogDataProtocol(), jSONObject2.toString());
            return;
        }
        JSONObject jSONObject6 = new JSONObject();
        jSONObject6.put("jsonHeader", jSONObject3);
        jSONObject6.put("jsonBody", jSONObject5);
        Intent intent = new Intent();
        intent.setPackage(this.b.getPackageName());
        intent.setAction(MsgConstant.MESSAGE_MESSAGE_SEND_ACTION);
        intent.putExtra(MsgConstant.KEY_UMPX_PATH, MsgConstant.UMPX_PUSH_LAUNCH);
        intent.putExtra(MsgConstant.KEY_SENDMESSAGE, jSONObject6.toString());
        UMJobIntentService.enqueueWork(this.b, UmengMessageCallbackHandlerService.class, intent);
        UTrack.appLaunchSending = false;
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void trackRegister(JSONObject jSONObject, String str) throws Exception {
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(c.c, UmengMessageDeviceConfig.getDINAes(this.b));
        jSONObject3.put(ai.aI, MsgConstant.SDK_VERSION);
        jSONObject3.put("push_switch", UmengMessageDeviceConfig.isNotificationEnabled(this.b));
        jSONObject2.put("header", jSONObject3);
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("device_token", MessageSharedPrefs.getInstance(this.b).getDeviceToken());
        jSONObject4.put("old_device_token", str);
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(jSONObject4);
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("push", jSONArray);
        jSONObject2.put("content", jSONObject5);
        if (h.d(this.b)) {
            Context context = this.b;
            UMWorkDispatch.sendEvent(context, 16387, PushAgent.getInstance(context).getUpushLogDataProtocol(), jSONObject2.toString());
        }
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void trackLocation(JSONObject jSONObject) throws Exception {
        JSONObject jSONObject2;
        try {
            jSONObject2 = sendRequest(jSONObject, MsgConstant.LBS_ENDPOINT);
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                throw new Exception(e);
            }
            jSONObject2 = sendRequest(this.b, jSONObject, MsgConstant.LBS_ENDPOINT);
        } catch (Exception e2) {
            e2.printStackTrace();
            return;
        }
        if (jSONObject2 != null && TextUtils.equals(jSONObject2.getString("success"), ITagManager.SUCCESS)) {
            UMLog.mutlInfo(a, 2, "location track success");
        }
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void addAlias(String str, String str2, JSONObject jSONObject, UTrack.ICallBack iCallBack) throws Exception {
        JSONObject jSONObject2;
        String optString = jSONObject.optString("fail", "");
        String optString2 = jSONObject.optString("success", "");
        String str3 = a;
        UMLog.mutlInfo(str3, 2, "keyfail:" + optString + ",keysuccess:" + optString2);
        if (!optString.equals("") || !optString2.equals("")) {
            if (!optString.equals("")) {
                iCallBack.onMessage(false, "alias:" + str + "\u6dfb\u52a0\u5931\u8d25");
                MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 1, optString);
            }
            if (!optString2.equals("")) {
                iCallBack.onMessage(true, "alias:" + str + "\u5df2\u7ecf\u6dfb\u52a0");
                MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 2, optString2);
                return;
            }
            return;
        }
        try {
            jSONObject2 = sendRequest(jSONObject, MsgConstant.ALIAS_ENDPOINT);
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                throw new Exception(e);
            }
            jSONObject2 = sendRequest(this.b, jSONObject, MsgConstant.ALIAS_ENDPOINT);
        }
        if (jSONObject2 == null || !TextUtils.equals(jSONObject2.optString("success", ""), ITagManager.SUCCESS)) {
            MessageSharedPrefs instance = MessageSharedPrefs.getInstance(this.b);
            instance.addAlias(str, str2, 0, 1, "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25alias:" + str + ",type:" + str2 + ",devicetoken:" + MessageSharedPrefs.getInstance(this.b).getDeviceToken());
            StringBuilder sb = new StringBuilder();
            sb.append("alias:");
            sb.append(str);
            sb.append("\u6dfb\u52a0\u5931\u8d25");
            iCallBack.onMessage(false, sb.toString());
            return;
        }
        MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 0, 0, "");
        try {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("interval", jSONObject2.optLong("interval", 0));
            jSONObject3.put("last_requestTime", System.currentTimeMillis());
            MessageSharedPrefs.getInstance(this.b).add_addAliasInterval(jSONObject3.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        iCallBack.onMessage(true, "alias:" + str + "\u6dfb\u52a0\u6210\u529f");
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void setAlias(String str, String str2, JSONObject jSONObject, UTrack.ICallBack iCallBack) throws Exception {
        JSONObject jSONObject2;
        String optString = jSONObject.optString("fail", "");
        String optString2 = jSONObject.optString("success", "");
        String str3 = a;
        UMLog.mutlInfo(str3, 2, "keyfail:" + optString + ",keysuccess:" + optString2);
        if (!optString.equals("") || !optString2.equals("")) {
            if (!optString.equals("")) {
                iCallBack.onMessage(false, "alias:" + str + "\u6dfb\u52a0\u5931\u8d25");
                MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 1, optString);
            }
            if (!optString2.equals("")) {
                iCallBack.onMessage(true, "alias:" + str + "\u5df2\u7ecf\u6dfb\u52a0");
                MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 2, optString2);
                return;
            }
            return;
        }
        try {
            jSONObject2 = sendRequest(jSONObject, MsgConstant.ALIAS_EXCLUSIVE_ENDPOINT);
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                throw new Exception(e);
            }
            jSONObject2 = sendRequest(this.b, jSONObject, MsgConstant.ALIAS_EXCLUSIVE_ENDPOINT);
        }
        if (jSONObject2 == null || !TextUtils.equals(jSONObject2.optString("success", ""), ITagManager.SUCCESS)) {
            MessageSharedPrefs instance = MessageSharedPrefs.getInstance(this.b);
            instance.addAlias(str, str2, 1, 1, "\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25alias:" + str + ",type:" + str2 + ",devicetoken:" + MessageSharedPrefs.getInstance(this.b).getDeviceToken());
            StringBuilder sb = new StringBuilder();
            sb.append("alias:");
            sb.append(str);
            sb.append("\u6dfb\u52a0\u5931\u8d25");
            iCallBack.onMessage(false, sb.toString());
            return;
        }
        MessageSharedPrefs.getInstance(this.b).addAlias(str, str2, 1, 0, "");
        try {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("interval", jSONObject2.optLong("interval", 0));
            jSONObject3.put("last_requestTime", System.currentTimeMillis());
            MessageSharedPrefs.getInstance(this.b).add_setAliasInterval(jSONObject3.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        iCallBack.onMessage(true, "alias:" + str + "\u6dfb\u52a0\u6210\u529f");
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void deleteAlias(String str, String str2, JSONObject jSONObject, UTrack.ICallBack iCallBack) throws Exception {
        JSONObject jSONObject2;
        try {
            jSONObject2 = sendRequest(jSONObject, MsgConstant.DELETE_ALIAS_ENDPOINT);
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                throw new Exception(e);
            }
            jSONObject2 = sendRequest(this.b, jSONObject, MsgConstant.DELETE_ALIAS_ENDPOINT);
        }
        if (jSONObject2 != null && TextUtils.equals(jSONObject2.getString("success"), ITagManager.SUCCESS)) {
            MessageSharedPrefs.getInstance(this.b).removeAlias(0, str, str2);
            MessageSharedPrefs.getInstance(this.b).removeAlias(1, str, str2);
            try {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("interval", jSONObject2.optLong("interval", 0));
                jSONObject3.put("last_requestTime", System.currentTimeMillis());
                MessageSharedPrefs.getInstance(this.b).add_deleteAliasInterval(jSONObject3.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            iCallBack.onMessage(true, "alias:" + str + ",type:" + str2 + "\u5220\u9664\u6210\u529f");
        }
    }

    public static JSONObject sendRequest(JSONObject jSONObject, String str) throws Exception {
        String body = HttpRequest.post(str).acceptJson().contentType(HttpRequest.CONTENT_TYPE_JSON).send(jSONObject.toString()).body("UTF-8");
        String str2 = a;
        UMLog.mutlInfo(str2, 2, "sendRequest() url=" + str + "\n request = " + jSONObject + "\n response = " + body);
        return new JSONObject(body);
    }

    public static JSONObject sendRequest(Context context, JSONObject jSONObject, String str) throws Exception {
        String host = new URL(str).getHost();
        String a2 = b.a(context, host);
        String str2 = a;
        UMLog.mutlInfo(str2, 2, "ip:" + a2);
        if (a2 == null) {
            return null;
        }
        URL url = new URL(str.replaceFirst(host, a2));
        String body = HttpRequest.post(url).acceptJson().contentType(HttpRequest.CONTENT_TYPE_JSON).header(HttpConstant.HOST, host).trustHosts().send(jSONObject.toString()).body("UTF-8");
        String str3 = a;
        UMLog.mutlInfo(str3, 2, "dns-->sendRequest() url=" + url.toString() + "\n request = " + jSONObject + "\n response = " + body);
        return new JSONObject(body);
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void sendAliasFailLog(JSONObject jSONObject) {
        if (MessageSharedPrefs.getInstance(this.b).getDaRegisterSendPolicy() == 1) {
            UMLog.mutlInfo(a, 2, "da_register_policy=1, skip sending da_register info.");
            return;
        }
        try {
            Cursor query = this.b.getContentResolver().query(a.a(this.b).d, new String[]{"message", "time"}, "error=?", new String[]{"1"}, null);
            if (query != null) {
                ArrayList<Alias> arrayList = new ArrayList();
                query.moveToFirst();
                while (!query.isAfterLast()) {
                    String string = query.getString(query.getColumnIndex("message"));
                    long j = query.getLong(query.getColumnIndex("time"));
                    Alias alias = new Alias();
                    alias.aliasMessage = string;
                    alias.aliasTime = j;
                    arrayList.add(alias);
                    query.moveToNext();
                }
                if (query != null) {
                    query.close();
                }
                for (Alias alias2 : arrayList) {
                    a(jSONObject, alias2.aliasMessage, alias2.aliasTime);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.umeng.message.common.inter.IUtrack
    public void sendRegisterLog(JSONObject jSONObject) throws Exception {
        JSONObject jSONObject2;
        int parseInt;
        try {
            jSONObject2 = sendRequest(jSONObject, MsgConstant.ALIAS_LOG);
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                throw new Exception(e);
            }
            jSONObject2 = sendRequest(this.b, jSONObject, MsgConstant.ALIAS_LOG);
        }
        if (jSONObject2 != null && TextUtils.equals(jSONObject2.optString("success", ""), ITagManager.SUCCESS) && (parseInt = Integer.parseInt(jSONObject2.getString("da_register_policy"))) > 0) {
            MessageSharedPrefs.getInstance(this.b).setDaRegisterSendPolicy(parseInt);
        }
    }

    private void a(JSONObject jSONObject, String str, long j) throws Exception {
        JSONObject jSONObject2;
        if (!str.equals("")) {
            jSONObject.put(MsgConstant.KEY_ALIAS_FAIL_LOG, str);
            try {
                jSONObject2 = sendRequest(jSONObject, MsgConstant.ALIAS_LOG);
            } catch (Exception e) {
                if (e.getMessage() == null || !e.getMessage().contains(MsgConstant.HTTPSDNS_ERROR) || !UmengMessageDeviceConfig.isOnline(this.b)) {
                    throw new Exception(e);
                }
                jSONObject2 = sendRequest(this.b, jSONObject, MsgConstant.ALIAS_LOG);
            }
            if (jSONObject2 != null && TextUtils.equals(jSONObject2.optString("success", ""), ITagManager.SUCCESS)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("error", "3");
                this.b.getContentResolver().update(a.a(this.b).d, contentValues, "time=?", new String[]{j + ""});
                int parseInt = Integer.parseInt(jSONObject2.optString("da_register_policy"));
                if (parseInt > 0) {
                    MessageSharedPrefs.getInstance(this.b).setDaRegisterSendPolicy(parseInt);
                }
            }
        }
    }
}
