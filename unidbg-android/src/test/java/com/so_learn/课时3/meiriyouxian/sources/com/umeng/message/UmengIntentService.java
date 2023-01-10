package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.util.TimedRemoteCaller;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.entity.UMessage;
import com.umeng.message.service.UMJobIntentService;
import java.util.Map;
import org.json.JSONObject;

public class UmengIntentService extends UmengBaseIntentService {
    private static final String a = UmengIntentService.class.getName();

    /* JADX DEBUG: Multi-variable search result rejected for r11v0, resolved type: com.umeng.message.UmengIntentService */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // com.umeng.message.UmengBaseIntentService, com.taobao.agoo.TaobaoBaseIntentService
    public void onMessage(Context context, Intent intent) {
        try {
            MLog.i(a, "--->>> UmengIntentService onMessage");
            super.onMessage(context, intent);
            try {
                String stringExtra = intent.getStringExtra("body");
                String stringExtra2 = intent.getStringExtra("id");
                String stringExtra3 = intent.getStringExtra("task_id");
                UMessage uMessage = new UMessage(new JSONObject(stringExtra));
                if (UMessage.DISPLAY_TYPE_PULLAPP.equals(uMessage.display_type)) {
                    if (uMessage.isAction) {
                        uMessage.pulled_service = UmengMessageDeviceConfig.getServiceName(this, uMessage.pulled_service, uMessage.pulled_package);
                    }
                    if (UmengMessageDeviceConfig.isServiceWork(context, uMessage.pulled_service, uMessage.pulled_package)) {
                        UTrack.getInstance(context).trackMsgPulled(uMessage, 52);
                    } else if (!UmengMessageDeviceConfig.getDataData(uMessage.pulled_package)) {
                        UTrack.getInstance(context).trackMsgPulled(uMessage, 53);
                    } else {
                        Intent intent2 = new Intent();
                        intent2.setClassName(uMessage.pulled_package, uMessage.pulled_service);
                        a(intent2, uMessage);
                        ThreadPoolExecutorFactory.execute(new AnonymousClass1(context, uMessage));
                    }
                } else if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                    MLog.i(a, "--->>> UmengIntentSerivce display_type is DISPLAY_TYPE_NOTIFICATIONPULLAPP");
                    Intent intent3 = new Intent();
                    intent3.setPackage(context.getPackageName());
                    intent3.setAction(MsgConstant.MESSAGE_MESSAGE_HANDLER_ACTION);
                    intent3.putExtra("body", stringExtra);
                    intent3.putExtra("id", stringExtra2);
                    intent3.putExtra("task_id", stringExtra3);
                    UMJobIntentService.enqueueWork(context, UmengMessageCallbackHandlerService.class, intent3);
                } else {
                    String pushIntentServiceClass = MessageSharedPrefs.getInstance(context).getPushIntentServiceClass();
                    if (!pushIntentServiceClass.equalsIgnoreCase("")) {
                        String str = a;
                        MLog.i(str, "--->>> UmengIntentSerivce display_type is " + pushIntentServiceClass + " service");
                        Intent intent4 = new Intent();
                        intent4.setClassName(context, pushIntentServiceClass);
                        intent4.setPackage(context.getPackageName());
                        intent4.putExtra("body", stringExtra);
                        intent4.putExtra("id", stringExtra2);
                        intent4.putExtra("task_id", stringExtra3);
                        UMJobIntentService.enqueueWork(context, pushIntentServiceClass, intent4);
                        return;
                    }
                    MLog.i(a, "--->>> UmengIntentSerivce display_type is else");
                    Intent intent5 = new Intent();
                    intent5.setPackage(context.getPackageName());
                    intent5.setAction(MsgConstant.MESSAGE_MESSAGE_HANDLER_ACTION);
                    intent5.putExtra("body", stringExtra);
                    intent5.putExtra("id", stringExtra2);
                    intent5.putExtra("task_id", stringExtra3);
                    UMJobIntentService.enqueueWork(context, UmengMessageCallbackHandlerService.class, intent5);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: com.umeng.message.UmengIntentService$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ UMessage b;

        AnonymousClass1(Context context, UMessage uMessage) {
            this.a = context;
            this.b = uMessage;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (UmengMessageDeviceConfig.isServiceWork(this.a, this.b.pulled_service, this.b.pulled_package)) {
                UTrack.getInstance(this.a).trackMsgPulled(this.b, 51);
            } else {
                UTrack.getInstance(this.a).trackMsgPulled(this.b, 50);
            }
        }
    }

    private Intent a(Intent intent, UMessage uMessage) {
        if (!(intent == null || uMessage == null || uMessage.extra == null)) {
            for (Map.Entry<String, String> entry : uMessage.extra.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key != null) {
                    intent.putExtra(key, value);
                }
            }
        }
        return intent;
    }
}
