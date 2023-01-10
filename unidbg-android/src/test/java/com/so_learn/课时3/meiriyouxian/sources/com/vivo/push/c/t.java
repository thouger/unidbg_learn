package com.vivo.push.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.c;
import com.vivo.push.b.w;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.d;
import com.vivo.push.e;
import com.vivo.push.g;
import com.vivo.push.model.b;
import com.vivo.push.util.a;
import com.vivo.push.util.n;
import com.vivo.push.util.q;
import com.vivo.push.util.r;
import java.util.List;

/* compiled from: SendCommandTask */
public final class t extends e {
    public t(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_GESTURE_LENGTH, false);
        if (this.a == null) {
            n.d("SendCommandTask", "SendCommandTask " + gVar + " ; mContext is Null");
            AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_GESTURE_LENGTH);
        } else if (gVar == null) {
            n.d("SendCommandTask", "SendCommandTask pushCommand is Null");
            AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_GESTURE_LENGTH);
        } else {
            b a = r.a(this.a);
            int i = gVar.a;
            if (i != 0) {
                if (i == 2009) {
                    n.a(ClientConfigManagerImpl.getInstance(this.a).isDebug());
                    if (n.a()) {
                        d.a().g.a();
                        a aVar = new a();
                        aVar.a(this.a, "com.vivo.push_preferences.hybridapptoken_v1");
                        aVar.a();
                        a aVar2 = new a();
                        aVar2.a(this.a, "com.vivo.push_preferences.appconfig_v1");
                        aVar2.a();
                        if (!d.a().j) {
                            ClientConfigManagerImpl.getInstance(this.a).clearPush();
                        }
                    }
                } else if (i != 2011) {
                    switch (i) {
                        case 2002:
                        case 2003:
                        case 2004:
                        case 2005:
                            if (a == null || a.e) {
                                d.a().a(((c) gVar).e, 1005);
                                break;
                            } else {
                                c cVar = (c) gVar;
                                int a2 = q.a(cVar);
                                if (a2 != 0) {
                                    d.a().a(cVar.e, a2);
                                    AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_GESTURE_LENGTH);
                                    return;
                                }
                            }
                            break;
                    }
                } else {
                    n.a(ClientConfigManagerImpl.getInstance(this.a).isDebug(((w) gVar).c));
                }
            } else if (d.a().j) {
                Context context = this.a;
                Intent intent = new Intent();
                intent.setPackage(context.getPackageName());
                intent.setClassName(context.getPackageName(), "com.vivo.push.sdk.service.CommandService");
                List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
                if (queryIntentServices == null || queryIntentServices.size() <= 0) {
                    n.d("ModuleUtil", "disableDeprecatedService is null");
                } else {
                    PackageManager packageManager = context.getPackageManager();
                    ComponentName componentName = new ComponentName(context, queryIntentServices.get(0).serviceInfo.name);
                    if (packageManager.getComponentEnabledSetting(componentName) != 2) {
                        packageManager.setComponentEnabledSetting(componentName, 2, 1);
                    }
                }
                Context context2 = this.a;
                Intent intent2 = new Intent();
                intent2.setPackage(context2.getPackageName());
                intent2.setClassName(context2.getPackageName(), "com.vivo.push.sdk.service.LinkProxyActivity");
                List<ResolveInfo> queryIntentActivities = context2.getPackageManager().queryIntentActivities(intent2, 128);
                if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
                    n.d("ModuleUtil", "disableDeprecatedActivity is null");
                } else {
                    PackageManager packageManager2 = context2.getPackageManager();
                    ComponentName componentName2 = new ComponentName(context2, queryIntentActivities.get(0).activityInfo.name);
                    if (packageManager2.getComponentEnabledSetting(componentName2) != 2) {
                        packageManager2.setComponentEnabledSetting(componentName2, 2, 1);
                    }
                }
            }
            if (a == null) {
                n.d("SendCommandTask", "SendCommandTask " + gVar + " ; pushPkgInfo is Null");
                AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_GESTURE_LENGTH);
                return;
            }
            String str = a.a;
            if (a.e) {
                d.a().a(((c) gVar).e, 1004);
                gVar = new com.vivo.push.b.e();
                n.d("SendCommandTask", "SendCommandTask " + gVar + " ; pkgName is InBlackList ");
            }
            com.vivo.push.a.a.a(this.a, str, gVar);
            AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_GESTURE_LENGTH);
        }
    }
}
