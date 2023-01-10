package com.vivo.push.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.c.o;
import com.vivo.push.d;
import com.vivo.push.e;
import com.vivo.push.f;
import com.vivo.push.g;
import com.vivo.push.model.UnvarnishedMessage;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.n;
import com.vivo.push.util.r;
import java.util.List;

public abstract class BasePushMessageReceiver extends BroadcastReceiver implements PushMessageCallback {
    public static final String TAG = "PushMessageReceiver";

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onBind(Context context, int i, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onDelAlias(Context context, int i, List<String> list, List<String> list2, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onDelTags(Context context, int i, List<String> list, List<String> list2, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    @Deprecated
    public void onListTags(Context context, int i, List<String> list, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onLog(Context context, String str, int i, boolean z) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    @Deprecated
    public void onPublish(Context context, int i, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onSetAlias(Context context, int i, List<String> list, List<String> list2, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onSetTags(Context context, int i, List<String> list, List<String> list2, String str) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onTransmissionMessage(Context context, UnvarnishedMessage unvarnishedMessage) {
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public void onUnBind(Context context, int i, String str) {
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Context applicationContext = ContextDelegate.getContext(context).getApplicationContext();
        d.a().a(applicationContext);
        try {
            int intExtra = intent.getIntExtra("method", -1);
            String stringExtra = intent.getStringExtra("req_id");
            n.d(TAG, "PushMessageReceiver " + applicationContext.getPackageName() + " ; type = " + intExtra + " ; requestId = " + stringExtra);
            try {
                d a = d.a();
                g createReceiverCommand = a.k.createReceiverCommand(intent);
                Context context2 = d.a().e;
                if (createReceiverCommand == null) {
                    n.a("PushClientManager", "sendCommand, null command!");
                    if (context2 != null) {
                        n.c(context2, "[\u6267\u884c\u6307\u4ee4\u5931\u8d25]\u6307\u4ee4\u7a7a\uff01");
                        return;
                    }
                    return;
                }
                o createReceiveTask = a.k.createReceiveTask(createReceiverCommand);
                if (createReceiveTask == null) {
                    n.a("PushClientManager", "sendCommand, null command task! pushCommand = " + createReceiverCommand);
                    if (context2 != null) {
                        n.c(context2, "[\u6267\u884c\u6307\u4ee4\u5931\u8d25]\u6307\u4ee4" + createReceiverCommand + "\u4efb\u52a1\u7a7a\uff01");
                        return;
                    }
                    return;
                }
                if (context2 != null && !(createReceiverCommand instanceof com.vivo.push.b.n)) {
                    n.a(context2, "[\u63a5\u6536\u6307\u4ee4]" + createReceiverCommand);
                }
                createReceiveTask.a(this);
                f.a((e) createReceiveTask);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e2) {
            n.b(TAG, "get method error", e2);
        }
    }

    @Override // com.vivo.push.sdk.PushMessageCallback
    public boolean isAllowNet(Context context) {
        if (context == null) {
            n.a(TAG, "isAllowNet sContext is null");
            return false;
        }
        String packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName)) {
            n.a(TAG, "isAllowNet pkgName is null");
            return false;
        }
        Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
        intent.setPackage(packageName);
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT);
        if (queryIntentServices != null && queryIntentServices.size() > 0) {
            return r.a(context, packageName);
        }
        n.a(TAG, "this is client sdk");
        return true;
    }
}
