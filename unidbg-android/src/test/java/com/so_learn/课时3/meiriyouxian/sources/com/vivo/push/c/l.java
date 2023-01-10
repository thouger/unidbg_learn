package com.vivo.push.c;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.PreciseDisconnectCause;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.h;
import com.vivo.push.b.q;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.d;
import com.vivo.push.f;
import com.vivo.push.g;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.util.NotifyAdapterUtil;
import com.vivo.push.util.j;
import com.vivo.push.util.n;
import com.vivo.push.util.o;
import com.vivo.push.util.p;
import com.vivo.push.util.r;
import com.vivo.push.util.y;
import java.util.HashMap;

/* compiled from: OnNotificationArrivedReceiveTask */
public final class l extends o {

    /* compiled from: OnNotificationArrivedReceiveTask */
    public interface a {
        void a();

        void b();
    }

    static /* synthetic */ void a(l lVar, String str, int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_ACCESS_FINE_LOCATION, false);
        lVar.a(str, i);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_ACCESS_FINE_LOCATION);
    }

    public l(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CONTACTS, false);
        if (gVar == null) {
            n.a("OnNotificationArrivedTask", "command is null");
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CONTACTS);
            return;
        }
        boolean isEnablePush = ClientConfigManagerImpl.getInstance(this.a).isEnablePush();
        q qVar = (q) gVar;
        String valueOf = String.valueOf(qVar.e);
        if (!r.a(this.a, this.a.getPackageName(), "com.vivo.pushservice.action.RECEIVE")) {
            a(valueOf, PreciseDisconnectCause.CALL_PULL_OUT_OF_SYNC);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CONTACTS);
            return;
        }
        d.a().a(new h(String.valueOf(qVar.e)));
        n.d("OnNotificationArrivedTask", "PushMessageReceiver " + this.a.getPackageName() + " isEnablePush :" + isEnablePush);
        if (!isEnablePush) {
            a(valueOf, 1020);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CONTACTS);
        } else if (!d.a().f || a(y.d(this.a), qVar.c(), qVar.d)) {
            if (Build.VERSION.SDK_INT >= 24) {
                NotificationManager notificationManager = (NotificationManager) this.a.getSystemService("notification");
                if (notificationManager != null && !notificationManager.areNotificationsEnabled()) {
                    n.b("OnNotificationArrivedTask", "pkg name : " + this.a.getPackageName() + " notify switch is false");
                    n.b(this.a, "\u901a\u77e5\u5f00\u5173\u5173\u95ed\uff0c\u5bfc\u81f4\u901a\u77e5\u65e0\u6cd5\u5c55\u793a\uff0c\u8bf7\u5230\u8bbe\u7f6e\u9875\u6253\u5f00\u5e94\u7528\u901a\u77e5\u5f00\u5173");
                    a(valueOf, 2104);
                    AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CONTACTS);
                    return;
                } else if (Build.VERSION.SDK_INT >= 26 && notificationManager != null) {
                    try {
                        NotificationChannel notificationChannel = notificationManager.getNotificationChannel(NotifyAdapterUtil.PRIMARY_CHANNEL);
                        if (notificationChannel != null && notificationChannel.getImportance() == 0) {
                            n.b("OnNotificationArrivedTask", "pkg name : " + this.a.getPackageName() + " notify channel switch is false");
                            n.b(this.a, "\u901a\u77e5\u901a\u9053\u5f00\u5173\u5173\u95ed\uff0c\u5bfc\u81f4\u901a\u77e5\u65e0\u6cd5\u5c55\u793a\uff0c\u8bf7\u5230\u8bbe\u7f6e\u9875\u6253\u5f00\u5e94\u7528\u901a\u77e5\u5f00\u5173");
                            a(valueOf, 2121);
                            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CONTACTS);
                            return;
                        }
                    } catch (Exception unused) {
                        n.b("OnNotificationArrivedTask", "\u5224\u65ad\u901a\u77e5\u901a\u9053\u51fa\u73b0\u7cfb\u7edf\u9519\u8bef");
                    }
                }
            }
            InsideNotificationItem b = qVar.b();
            if (b != null) {
                int targetType = b.getTargetType();
                String tragetContent = b.getTragetContent();
                n.d("OnNotificationArrivedTask", "targetType is " + targetType + " ; target is " + tragetContent);
                f.b(new AnonymousClass1(b, valueOf, qVar));
                AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CONTACTS);
                return;
            }
            n.a("OnNotificationArrivedTask", "notify is null");
            Context context = this.a;
            n.c(context, "\u901a\u77e5\u5185\u5bb9\u4e3a\u7a7a\uff0c" + qVar.e);
            a(valueOf, 1027);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CONTACTS);
        } else {
            a(valueOf, 1021);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CONTACTS);
        }
    }

    /* compiled from: OnNotificationArrivedReceiveTask */
    /* renamed from: com.vivo.push.c.l$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ InsideNotificationItem a;
        final /* synthetic */ String b;
        final /* synthetic */ q c;

        AnonymousClass1(InsideNotificationItem insideNotificationItem, String str, q qVar) {
            this.a = insideNotificationItem;
            this.b = str;
            this.c = qVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            char c;
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CALENDAR, false);
            if (l.this.c.onNotificationMessageArrived(l.this.a, o.a(this.a))) {
                n.b("OnNotificationArrivedTask", "pkg name : " + l.this.a.getPackageName() + " \u5e94\u7528\u4e3b\u52a8\u62e6\u622a\u901a\u77e5");
                n.b(l.this.a, "\u5e94\u7528\u4e3b\u52a8\u62e6\u622a\u901a\u77e5\uff0c\u5bfc\u81f4\u901a\u77e5\u65e0\u6cd5\u5c55\u793a\uff0c\u5982\u9700\u6253\u5f00\u8bf7\u5728onNotificationMessageArrived\u4e2d\u8fd4\u56defalse");
                l.a(l.this, this.b, 2120);
                AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CALENDAR);
                return;
            }
            j jVar = new j(l.this.a, this.a, this.c.e, l.this.c.isAllowNet(l.this.a), new AnonymousClass1());
            boolean isShowBigPicOnMobileNet = this.a.isShowBigPicOnMobileNet();
            String purePicUrl = this.a.getPurePicUrl();
            if (TextUtils.isEmpty(purePicUrl)) {
                purePicUrl = this.a.getCoverUrl();
            }
            if (!TextUtils.isEmpty(purePicUrl)) {
                n.c("OnNotificationArrivedTask", "showCode=" + isShowBigPicOnMobileNet);
                if (!isShowBigPicOnMobileNet) {
                    n.a(l.this.a, "mobile net unshow");
                    NetworkInfo a = p.a(l.this.a);
                    if (a != null && a.getState() == NetworkInfo.State.CONNECTED) {
                        int type = a.getType();
                        c = type == 1 ? 2 : type == 0 ? (char) 1 : 3;
                    } else {
                        c = 0;
                    }
                    if (c == 1) {
                        purePicUrl = null;
                        this.a.clearCoverUrl();
                        this.a.clearPurePicUrl();
                    }
                } else {
                    n.a(l.this.a, "mobile net show");
                }
            }
            jVar.execute(this.a.getIconUrl(), purePicUrl);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CALENDAR);
        }

        /* compiled from: OnNotificationArrivedReceiveTask */
        /* renamed from: com.vivo.push.c.l$1$1  reason: invalid class name */
        class AnonymousClass1 implements a {
            AnonymousClass1() {
            }

            @Override // com.vivo.push.c.l.a
            public final void a() {
                AppMethodBeat.i(MetricsProto.MetricsEvent.PROVISIONING_COPY_ACCOUNT_STATUS, false);
                l.a(l.this, AnonymousClass1.this.b);
                AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_COPY_ACCOUNT_STATUS);
            }

            @Override // com.vivo.push.c.l.a
            public final void b() {
                AppMethodBeat.i(MetricsProto.MetricsEvent.TEXT_LONGPRESS, false);
                n.b("OnNotificationArrivedTask", "pkg name : " + l.this.a.getPackageName() + " \u901a\u77e5\u5c55\u793a\u5931\u8d25");
                n.b(l.this.a, "\u7cfb\u7edf\u9519\u8bef\u5bfc\u81f4\u901a\u77e5\u5c55\u793a\u5931\u8d25");
                l.a(l.this, AnonymousClass1.this.b, 2119);
                AppMethodBeat.o(MetricsProto.MetricsEvent.TEXT_LONGPRESS);
            }
        }
    }

    private void a(String str, int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_GET_ACCOUNTS, false);
        HashMap hashMap = new HashMap();
        hashMap.put("messageID", str);
        String b = y.b(this.a, this.a.getPackageName());
        if (!TextUtils.isEmpty(b)) {
            hashMap.put("remoteAppId", b);
        }
        com.vivo.push.util.d.a((long) i, hashMap);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_GET_ACCOUNTS);
    }

    static /* synthetic */ void a(l lVar, String str) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_RECORD_AUDIO, false);
        HashMap hashMap = new HashMap();
        hashMap.put("srt", "1");
        hashMap.put(Telephony.CellBroadcasts.V1_MESSAGE_IDENTIFIER, str);
        String b = y.b(lVar.a, lVar.a.getPackageName());
        if (!TextUtils.isEmpty(b)) {
            hashMap.put("app_id", b);
        }
        hashMap.put("type", "1");
        hashMap.put("dtp", "1");
        com.vivo.push.util.d.a(6, hashMap);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_RECORD_AUDIO);
    }
}
