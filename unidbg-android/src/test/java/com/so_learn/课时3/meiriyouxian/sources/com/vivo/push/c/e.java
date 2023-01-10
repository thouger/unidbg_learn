package com.vivo.push.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.j;
import com.vivo.push.g;
import com.vivo.push.sdk.a;
import com.vivo.push.util.n;
import com.vivo.push.util.r;
import java.util.List;

/* compiled from: OnChangePushStatusReceiveTask */
public final class e extends com.vivo.push.e {
    public e(g gVar) {
        super(gVar);
    }

    public static boolean a(Context context) {
        AppMethodBeat.i(521, false);
        Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT);
        if (queryIntentServices == null || queryIntentServices.size() <= 0) {
            n.a("OnChangePushStatusTask", "enableService error: can not find push service.");
            AppMethodBeat.o(521);
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, queryIntentServices.get(0).serviceInfo.name);
        if (packageManager.getComponentEnabledSetting(componentName) != 1) {
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
            n.d("OnChangePushStatusTask", "enableService push service.");
            AppMethodBeat.o(521);
            return true;
        }
        n.d("OnChangePushStatusTask", "push service has enabled");
        AppMethodBeat.o(521);
        return false;
    }

    public static boolean b(Context context) {
        AppMethodBeat.i(524, false);
        Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT);
        if (queryIntentServices == null || queryIntentServices.size() <= 0) {
            n.a("OnChangePushStatusTask", "disableService error: can not find push service.");
            AppMethodBeat.o(524);
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, queryIntentServices.get(0).serviceInfo.name);
        if (packageManager.getComponentEnabledSetting(componentName) != 2) {
            packageManager.setComponentEnabledSetting(componentName, 2, 1);
            n.d("OnChangePushStatusTask", "disableService push service.");
            AppMethodBeat.o(524);
            return true;
        }
        n.d("OnChangePushStatusTask", "push service has disabled");
        AppMethodBeat.o(524);
        return false;
    }

    private static List<ResolveInfo> c(Context context) {
        List<ResolveInfo> list;
        AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_SUPPORT_DISCLAIMER, false);
        Intent intent = new Intent("com.vivo.pushservice.action.RECEIVE");
        intent.setPackage(context.getPackageName());
        try {
            list = context.getPackageManager().queryBroadcastReceivers(intent, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT);
        } catch (Exception unused) {
            list = null;
        }
        if (list == null || list.size() <= 0) {
            Intent intent2 = new Intent("com.vivo.pushclient.action.RECEIVE");
            intent2.setPackage(context.getPackageName());
            try {
                list = context.getPackageManager().queryBroadcastReceivers(intent2, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT);
            } catch (Exception unused2) {
            }
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_SUPPORT_DISCLAIMER);
        return list;
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_CUSTOM_LIST_CONFIRMATION, false);
        if (this.a.getPackageName().equals(r.b(this.a))) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_CUSTOM_LIST_CONFIRMATION);
            return;
        }
        j jVar = (j) gVar;
        int i = jVar.c;
        int i2 = jVar.d;
        n.d("OnChangePushStatusTask", "OnChangePushStatusTask serviceStatus is " + i + " ; receiverStatus is " + i2);
        if (i == 2) {
            b(this.a);
        } else if (i == 1) {
            a(this.a);
        } else if (i == 0) {
            Context context = this.a;
            Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
            intent.setPackage(context.getPackageName());
            List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT);
            if (queryIntentServices == null || queryIntentServices.size() <= 0) {
                n.a("OnChangePushStatusTask", "defaultService error: can not find push service.");
            } else {
                PackageManager packageManager = context.getPackageManager();
                ComponentName componentName = new ComponentName(context, queryIntentServices.get(0).serviceInfo.name);
                if (packageManager.getComponentEnabledSetting(componentName) != 0) {
                    packageManager.setComponentEnabledSetting(componentName, 0, 1);
                    n.d("OnChangePushStatusTask", "defaultService push service.");
                } else {
                    n.d("OnChangePushStatusTask", "push service has defaulted");
                }
            }
        }
        if (i2 == 2) {
            Context context2 = this.a;
            List<ResolveInfo> c = c(context2);
            if (c == null || c.size() <= 0) {
                n.a("OnChangePushStatusTask", "disableReceiver error: can not find push service.");
            } else {
                String str = c.get(0).activityInfo.name;
                if (TextUtils.isEmpty(str)) {
                    n.d("OnChangePushStatusTask", "disableReceiver error: className is null. ");
                } else {
                    PackageManager packageManager2 = context2.getPackageManager();
                    ComponentName componentName2 = new ComponentName(context2, str);
                    if (packageManager2.getComponentEnabledSetting(componentName2) != 2) {
                        packageManager2.setComponentEnabledSetting(componentName2, 2, 1);
                        n.d("OnChangePushStatusTask", "push service disableReceiver ");
                    } else {
                        n.d("OnChangePushStatusTask", "push service has disableReceiver ");
                    }
                }
            }
            a.a().c = null;
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_CUSTOM_LIST_CONFIRMATION);
        } else if (i2 == 1) {
            Context context3 = this.a;
            List<ResolveInfo> c2 = c(context3);
            if (c2 == null || c2.size() <= 0) {
                n.a("OnChangePushStatusTask", "enableReceiver error: can not find push service.");
                AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_CUSTOM_LIST_CONFIRMATION);
                return;
            }
            String str2 = c2.get(0).activityInfo.name;
            if (TextUtils.isEmpty(str2)) {
                n.d("OnChangePushStatusTask", "enableReceiver error: className is null. ");
                AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_CUSTOM_LIST_CONFIRMATION);
                return;
            }
            PackageManager packageManager3 = context3.getPackageManager();
            ComponentName componentName3 = new ComponentName(context3, str2);
            if (packageManager3.getComponentEnabledSetting(componentName3) != 1) {
                packageManager3.setComponentEnabledSetting(componentName3, 1, 1);
                n.d("OnChangePushStatusTask", "push service enableReceiver ");
                AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_CUSTOM_LIST_CONFIRMATION);
                return;
            }
            n.d("OnChangePushStatusTask", "push service has enableReceiver ");
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_CUSTOM_LIST_CONFIRMATION);
        } else {
            if (i2 == 0) {
                Context context4 = this.a;
                List<ResolveInfo> c3 = c(context4);
                if (c3 == null || c3.size() <= 0) {
                    n.a("OnChangePushStatusTask", "defaultReceiver error: can not find push service.");
                    AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_CUSTOM_LIST_CONFIRMATION);
                    return;
                }
                String str3 = c3.get(0).activityInfo.name;
                if (TextUtils.isEmpty(str3)) {
                    n.d("OnChangePushStatusTask", "defaultReceiver error: className is null. ");
                    AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_CUSTOM_LIST_CONFIRMATION);
                    return;
                }
                PackageManager packageManager4 = context4.getPackageManager();
                ComponentName componentName4 = new ComponentName(context4, str3);
                if (packageManager4.getComponentEnabledSetting(componentName4) != 0) {
                    packageManager4.setComponentEnabledSetting(componentName4, 0, 1);
                    n.d("OnChangePushStatusTask", "push service defaultReceiver ");
                    AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_CUSTOM_LIST_CONFIRMATION);
                    return;
                }
                n.d("OnChangePushStatusTask", "push service has defaulted");
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_CUSTOM_LIST_CONFIRMATION);
        }
    }
}
