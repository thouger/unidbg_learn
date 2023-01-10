package com.umeng.message;

import android.app.Application;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import com.umeng.analytics.pro.ai;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.message.common.a;
import com.umeng.message.common.e;
import com.umeng.message.entity.UMessage;
import com.umeng.message.service.UMJobIntentService;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class UmengMessageCallbackHandlerService extends UMJobIntentService {
    private static final String a = UmengMessageCallbackHandlerService.class.getSimpleName();

    /* access modifiers changed from: protected */
    @Override // com.umeng.message.service.UMJobIntentService, com.umeng.message.service.JobIntentService
    public void a(Intent intent) {
        UHandler uHandler;
        Application a2 = a.a();
        if (a2 == null) {
            UMLog.mutlInfo(a, 2, "context null!");
            return;
        }
        String currentProcessName = UMFrUtils.getCurrentProcessName(a2);
        String str = a;
        UMLog.mutlInfo(str, 2, "\u8fdb\u7a0b\u540d\uff1a" + currentProcessName);
        if (intent != null && intent.getAction() != null) {
            if (intent.getAction().equals(MsgConstant.MESSAGE_REGISTER_CALLBACK_ACTION)) {
                MLog.i("wuchi", "--->>> UmengMessageCallbackHandlerService register callback");
                try {
                    String stringExtra = intent.getStringExtra(MsgConstant.KEY_REGISTRATION_ID);
                    boolean booleanExtra = intent.getBooleanExtra("status", false);
                    String str2 = a;
                    UMLog.mutlInfo(str2, 2, "\u6ce8\u518c\uff1a" + stringExtra + "\uff0c\u72b6\u6001\uff1a" + booleanExtra);
                    IUmengRegisterCallback registerCallback = PushAgent.getInstance(a2).getRegisterCallback();
                    if (booleanExtra) {
                        e.a(new AnonymousClass1(a2, stringExtra, registerCallback));
                    } else if (registerCallback != null) {
                        registerCallback.onFailure(intent.getStringExtra(ai.az), intent.getStringExtra("s1"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (intent.getAction().equals(MsgConstant.MESSAGE_ENABLE_CALLBACK_ACTION)) {
                MLog.i("wuchi", "--->>> UmengMessageCallbackHandlerService enable callback");
                try {
                    boolean booleanExtra2 = intent.getBooleanExtra("status", false);
                    IUmengCallback callback = PushAgent.getInstance(a2).getCallback();
                    String str3 = a;
                    UMLog.mutlInfo(str3, 2, "\u5f00\u542f\u72b6\u6001:" + booleanExtra2);
                    if (booleanExtra2) {
                        if (callback != null) {
                            callback.onSuccess();
                        }
                    } else if (callback != null) {
                        callback.onFailure(intent.getStringExtra(ai.az), intent.getStringExtra("s1"));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if (intent.getAction().equals(MsgConstant.MESSAGE_DISABLE_CALLBACK_ACTION)) {
                MLog.i("wuchi", "--->>> UmengMessageCallbackHandlerService disable callback");
                try {
                    boolean booleanExtra3 = intent.getBooleanExtra("status", false);
                    IUmengCallback callback2 = PushAgent.getInstance(a2).getCallback();
                    String str4 = a;
                    UMLog.mutlInfo(str4, 2, "\u5173\u95ed\u72b6\u6001:" + booleanExtra3);
                    if (booleanExtra3) {
                        if (callback2 != null) {
                            callback2.onSuccess();
                        }
                    } else if (callback2 != null) {
                        callback2.onFailure(intent.getStringExtra(ai.az), intent.getStringExtra("s1"));
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } else if (intent.getAction().equals(MsgConstant.MESSAGE_MESSAGE_HANDLER_ACTION)) {
                MLog.i("wuchi", "--->>> UmengMessageCallbackHandlerService message callback");
                try {
                    UMessage uMessage = new UMessage(new JSONObject(intent.getStringExtra("body")));
                    uMessage.message_id = intent.getStringExtra("id");
                    uMessage.task_id = intent.getStringExtra("task_id");
                    if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                        uHandler = PushAgent.getInstance(a2).getAdHandler();
                    } else {
                        uHandler = PushAgent.getInstance(a2).getMessageHandler();
                    }
                    if (uHandler != null) {
                        uHandler.handleMessage(a2, uMessage);
                    }
                } catch (Exception e4) {
                    if (e4.getMessage() != null) {
                        String str5 = a;
                        UMLog.mutlInfo(str5, 2, "MESSAGE_HANDLER_ACTION:" + e4.getMessage());
                    }
                }
            } else if (intent.getAction().equals(MsgConstant.MESSAGE_MESSAGE_SEND_ACTION)) {
                MLog.i("wuchi", "--->>> UmengMessageCallbackHandlerService send log callback");
                try {
                    String stringExtra2 = intent.getStringExtra(MsgConstant.KEY_SENDMESSAGE);
                    String stringExtra3 = intent.getStringExtra(MsgConstant.KEY_UMPX_PATH);
                    JSONObject jSONObject = new JSONObject(stringExtra2);
                    jSONObject.put("umpxPath", stringExtra3);
                    UMWorkDispatch.sendEvent(a2, 16388, PushAgent.getInstance(a2).getUpushLogDataProtocol(), jSONObject.toString());
                } catch (Exception e5) {
                    if (e5.getMessage() != null) {
                        String str6 = a;
                        UMLog.mutlInfo(str6, 2, "MESSAGE_SEND_ACTION:" + e5.getMessage());
                    }
                }
            }
        }
    }

    /* renamed from: com.umeng.message.UmengMessageCallbackHandlerService$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;
        final /* synthetic */ IUmengRegisterCallback c;

        AnonymousClass1(Context context, String str, IUmengRegisterCallback iUmengRegisterCallback) {
            this.a = context;
            this.b = str;
            this.c = iUmengRegisterCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = "";
            try {
                MessageSharedPrefs instance = MessageSharedPrefs.getInstance(this.a);
                str = instance.getDeviceToken();
                if (!(this.b == null || str == null || this.b.equals(str))) {
                    instance.setHasRegisterSync(false);
                    instance.setDeviceTokenSync(this.b);
                    this.a.getContentResolver().delete(com.umeng.message.provider.a.a(this.a).e, null, null);
                    instance.resetTags();
                }
            } catch (Exception unused) {
            }
            IUmengRegisterCallback iUmengRegisterCallback = this.c;
            if (iUmengRegisterCallback != null) {
                iUmengRegisterCallback.onSuccess(this.b);
            }
            UTrack.getInstance(this.a).a(str);
            PushAgent.getInstance(this.a).onAppStart();
        }
    }

    public static void removeCacheLog(JSONArray jSONArray) {
        try {
            Application a2 = a.a();
            if (a2 == null) {
                UMLog.mutlInfo(a, 2, "removeCacheLog context null!");
            } else if (jSONArray != null) {
                ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                        String optString = jSONObject.optString("msg_id");
                        int optInt = jSONObject.optInt(MsgConstant.KEY_ACTION_TYPE);
                        arrayList.add(ContentProviderOperation.newDelete(com.umeng.message.provider.a.a(a2).f).withSelection("MsgId=? And ActionType=?", new String[]{optString, optInt + ""}).build());
                        if (optInt != 0) {
                            arrayList.add(ContentProviderOperation.newDelete(com.umeng.message.provider.a.a(a2).g).withSelection("MsgId=?", new String[]{optString}).build());
                        }
                    }
                }
                a2.getContentResolver().applyBatch(com.umeng.message.provider.a.a(a2).a, arrayList);
            }
        } catch (Exception e) {
            UMLog.mutlInfo(a, 2, "remove cache log:" + e.getMessage());
        }
    }
}
