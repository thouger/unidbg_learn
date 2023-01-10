package com.vivo.push.c;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.huawei.hms.support.api.push.pushselfshow.entity.PushSelfShowMessage;
import com.vivo.push.b.p;
import com.vivo.push.b.x;
import com.vivo.push.d;
import com.vivo.push.f;
import com.vivo.push.g;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.util.NotifyAdapterUtil;
import com.vivo.push.util.n;
import com.vivo.push.util.o;
import com.vivo.push.util.y;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: OnNotificationClickTask */
public final class m extends o {
    static /* synthetic */ Intent a(Intent intent, Map map) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_BATTERY_DENY, false);
        Intent b = b(intent, map);
        AppMethodBeat.o(MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_BATTERY_DENY);
        return b;
    }

    public m(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        String str;
        boolean z = false;
        AppMethodBeat.i(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY, false);
        p pVar = (p) gVar;
        InsideNotificationItem insideNotificationItem = pVar.f;
        if (insideNotificationItem == null) {
            n.d("OnNotificationClickTask", "current notification item is null");
            AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY);
            return;
        }
        UPSNotificationMessage a = o.a(insideNotificationItem);
        String str2 = pVar.c;
        boolean equals = this.a.getPackageName().equals(str2);
        if (equals) {
            NotifyAdapterUtil.cancelNotify(this.a);
        }
        if (pVar.d != null) {
            str2 = pVar.d;
        }
        if (equals) {
            x xVar = new x(1030);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("type", "2");
            hashMap.put("messageID", String.valueOf(pVar.e));
            hashMap.put("platform", str2);
            String b = y.b(this.a, str2);
            if (!TextUtils.isEmpty(b)) {
                hashMap.put("remoteAppId", b);
            } else {
                hashMap.put(PushSelfShowMessage.MSG_AP, str2);
            }
            xVar.c = hashMap;
            d.a().a(xVar);
            n.d("OnNotificationClickTask", "notification is clicked by skip type[" + a.getSkipType() + "]");
            int skipType = a.getSkipType();
            if (skipType == 1) {
                new Thread(new AnonymousClass5(this.a, pVar.d, a.getParams())).start();
                f.b(new AnonymousClass1(a));
                AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY);
            } else if (skipType == 2) {
                String skipContent = a.getSkipContent();
                if (skipContent.startsWith("http://") || skipContent.startsWith("https://")) {
                    z = true;
                }
                if (z) {
                    Uri parse = Uri.parse(skipContent);
                    Intent intent = new Intent("android.intent.action.VIEW", parse);
                    intent.setFlags(268435456);
                    b(intent, a.getParams());
                    try {
                        this.a.startActivity(intent);
                    } catch (Exception unused) {
                        n.a("OnNotificationClickTask", "startActivity error : " + parse);
                    }
                } else {
                    n.a("OnNotificationClickTask", "url not legal");
                }
                f.b(new AnonymousClass2(a));
                AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY);
            } else if (skipType == 3) {
                f.b(new AnonymousClass3(a));
                AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY);
            } else if (skipType != 4) {
                n.a("OnNotificationClickTask", "illegitmacy skip type error : " + a.getSkipType());
                AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY);
            } else {
                String skipContent2 = a.getSkipContent();
                try {
                    Intent parseUri = Intent.parseUri(skipContent2, 1);
                    String str3 = parseUri.getPackage();
                    String str4 = null;
                    if (pVar.d != null) {
                        if (TextUtils.isEmpty(str3) || pVar.d.equals(str3)) {
                            if (parseUri.getComponent() != null) {
                                str4 = parseUri.getComponent().getPackageName();
                            }
                            if (!TextUtils.isEmpty(str4) && !pVar.d.equals(str4)) {
                                n.a("OnNotificationClickTask", "open activity component error : local pkgName getOpenPkgName is " + pVar.d + "; but remote pkgName is " + str4);
                                AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY);
                                return;
                            }
                        } else {
                            n.a("OnNotificationClickTask", "open activity error : local pkgName getOpenPkgName is " + pVar.d + "; but remote pkgName is " + str3);
                            AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY);
                            return;
                        }
                    } else if (TextUtils.isEmpty(str3) || this.a.getPackageName().equals(str3)) {
                        if (parseUri.getComponent() != null) {
                            str4 = parseUri.getComponent().getPackageName();
                        }
                        if (!TextUtils.isEmpty(str4) && !this.a.getPackageName().equals(str4)) {
                            n.a("OnNotificationClickTask", "open activity component error : local pkgName is " + this.a.getPackageName() + "; but remote pkgName is " + str4);
                            AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY);
                            return;
                        }
                    } else {
                        n.a("OnNotificationClickTask", "open activity error : local pkgName is " + this.a.getPackageName() + "; but remote pkgName is " + str3);
                        AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY);
                        return;
                    }
                    if (pVar.d != null) {
                        str = pVar.d;
                    } else {
                        str = this.a.getPackageName();
                    }
                    parseUri.setPackage(str);
                    parseUri.addFlags(268435456);
                    b(parseUri, a.getParams());
                    this.a.startActivity(parseUri);
                } catch (Exception e) {
                    n.a("OnNotificationClickTask", "open activity error : " + skipContent2, e);
                }
                f.b(new AnonymousClass4(a));
                AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY);
            }
        } else {
            n.a("OnNotificationClickTask", "notify is " + a + " ; isMatch is " + equals);
            AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY);
        }
    }

    /* compiled from: OnNotificationClickTask */
    /* renamed from: com.vivo.push.c.m$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ UPSNotificationMessage a;

        AnonymousClass1(UPSNotificationMessage uPSNotificationMessage) {
            this.a = uPSNotificationMessage;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_READ_CALL_LOG, false);
            if (m.this.c != null) {
                m.this.c.onNotificationMessageClicked(m.this.a, this.a);
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_READ_CALL_LOG);
        }
    }

    /* compiled from: OnNotificationClickTask */
    /* renamed from: com.vivo.push.c.m$2  reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ UPSNotificationMessage a;

        AnonymousClass2(UPSNotificationMessage uPSNotificationMessage) {
            this.a = uPSNotificationMessage;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_WRITE_CALL_LOG, false);
            if (m.this.c != null) {
                m.this.c.onNotificationMessageClicked(m.this.a, this.a);
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_WRITE_CALL_LOG);
        }
    }

    /* compiled from: OnNotificationClickTask */
    /* renamed from: com.vivo.push.c.m$3  reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ UPSNotificationMessage a;

        AnonymousClass3(UPSNotificationMessage uPSNotificationMessage) {
            this.a = uPSNotificationMessage;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_USE_SIP, false);
            if (m.this.c != null) {
                m.this.c.onNotificationMessageClicked(m.this.a, this.a);
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_USE_SIP);
        }
    }

    /* compiled from: OnNotificationClickTask */
    /* renamed from: com.vivo.push.c.m$4  reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ UPSNotificationMessage a;

        AnonymousClass4(UPSNotificationMessage uPSNotificationMessage) {
            this.a = uPSNotificationMessage;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_PROCESS_OUTGOING_CALLS, false);
            if (m.this.c != null) {
                m.this.c.onNotificationMessageClicked(m.this.a, this.a);
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_PROCESS_OUTGOING_CALLS);
        }
    }

    /* compiled from: OnNotificationClickTask */
    /* renamed from: com.vivo.push.c.m$5  reason: invalid class name */
    class AnonymousClass5 implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;
        final /* synthetic */ Map c;

        AnonymousClass5(Context context, String str, Map map) {
            this.a = context;
            this.b = str;
            this.c = map;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_READ_CELL_BROADCASTS, false);
            String packageName = this.a.getPackageName();
            if (!TextUtils.isEmpty(this.b)) {
                packageName = this.b;
            }
            try {
                List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) this.a.getSystemService("activity")).getRunningTasks(100);
                if (runningTasks != null) {
                    for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {
                        ComponentName componentName = runningTaskInfo.topActivity;
                        if (componentName.getPackageName().equals(packageName)) {
                            n.d("OnNotificationClickTask", "topClassName=" + componentName.getClassName());
                            Intent intent = new Intent();
                            intent.setComponent(componentName);
                            intent.setFlags(270532608);
                            m.a(intent, this.c);
                            this.a.startActivity(intent);
                            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_READ_CELL_BROADCASTS);
                            return;
                        }
                    }
                }
            } catch (Exception e) {
                n.a("OnNotificationClickTask", "start recentIntent is error", e);
            }
            Intent launchIntentForPackage = this.a.getPackageManager().getLaunchIntentForPackage(!TextUtils.isEmpty(this.b) ? this.b : this.a.getPackageName());
            if (launchIntentForPackage != null) {
                launchIntentForPackage.setFlags(268435456);
                m.a(launchIntentForPackage, this.c);
                this.a.startActivity(launchIntentForPackage);
                AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_READ_CELL_BROADCASTS);
                return;
            }
            n.a("OnNotificationClickTask", "LaunchIntent is null");
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_READ_CELL_BROADCASTS);
        }
    }

    private static Intent b(Intent intent, Map<String, String> map) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.SETTINGS_GESTURE_PICKUP, false);
        if (map == null || map.entrySet() == null) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_GESTURE_PICKUP);
            return intent;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!(entry == null || entry.getKey() == null)) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.SETTINGS_GESTURE_PICKUP);
        return intent;
    }
}
