package cn.missfresh.pushlib;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import java.util.Map;
import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.mezu.MeizuRegister;
import org.android.agoo.oppo.OppoRegister;
import org.android.agoo.vivo.VivoRegister;
import org.android.agoo.xiaomi.MiPushRegistar;
import org.json.JSONException;

/* compiled from: MFPushManager */
public class d {
    private static d a;
    private PushAgent b;
    private b c;
    private Context d;
    private Handler e;
    private String f;

    static /* synthetic */ void a(d dVar, Context context, UMessage uMessage) {
        AppMethodBeat.i(22167, false);
        dVar.a(context, uMessage);
        AppMethodBeat.o(22167);
    }

    private d(Context context) {
        this.d = context;
    }

    private d() {
    }

    public static d a(Context context) {
        AppMethodBeat.i(22153, false);
        if (a == null) {
            synchronized (d.class) {
                try {
                    if (a == null) {
                        a = new d(context);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(22153);
                    throw th;
                }
            }
        }
        d dVar = a;
        AppMethodBeat.o(22153);
        return dVar;
    }

    private void b(c cVar) {
        AppMethodBeat.i(22154, false);
        UMConfigure.init(this.d, cVar.a(), cVar.c(), 1, cVar.b());
        UMConfigure.setLogEnabled(cVar.j());
        UMConfigure.setEncryptEnabled(cVar.k());
        if (!cVar.d().isEmpty() && !cVar.e().isEmpty()) {
            MiPushRegistar.register(this.d, cVar.d(), cVar.e());
        }
        if (!cVar.f().isEmpty() && !cVar.g().isEmpty()) {
            MeizuRegister.register(this.d, cVar.f(), cVar.g());
        }
        if (!cVar.h().isEmpty() && !cVar.i().isEmpty()) {
            OppoRegister.register(this.d, cVar.h(), cVar.i());
        }
        HuaWeiRegister.register((Application) this.d);
        VivoRegister.register(this.d);
        AppMethodBeat.o(22154);
    }

    public void a(c cVar) {
        AppMethodBeat.i(22156, false);
        this.b = PushAgent.getInstance(this.d);
        this.b.setNotificationPlaySound(0);
        this.b.setNotificationPlayLights(0);
        this.b.setNotificationPlayVibrate(0);
        this.b.setDisplayNotificationNumber(8);
        this.e = new Handler(this.d.getMainLooper());
        this.b.setMessageHandler(new AnonymousClass1());
        this.b.register(new AnonymousClass2());
        this.b.setNotificationClickHandler(new AnonymousClass3());
        b(cVar);
        AppMethodBeat.o(22156);
    }

    /* compiled from: MFPushManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.pushlib.d$1  reason: invalid class name */
    public class AnonymousClass1 extends UmengMessageHandler {
        AnonymousClass1() {
        }

        /* compiled from: MFPushManager */
        /* renamed from: cn.missfresh.pushlib.d$1$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ Context a;
            final /* synthetic */ UMessage b;

            AnonymousClass1(Context context, UMessage uMessage) {
                this.a = context;
                this.b = uMessage;
            }

            @Override // java.lang.Runnable
            public void run() {
                AppMethodBeat.i(22132, false);
                UTrack.getInstance(this.a).trackMsgClick(this.b);
                AppMethodBeat.o(22132);
            }
        }

        @Override // com.umeng.message.UmengMessageHandler
        public void dealWithCustomMessage(Context context, UMessage uMessage) {
            AppMethodBeat.i(22137, false);
            if (d.this.e != null) {
                d.this.e.post(new AnonymousClass1(context, uMessage));
            }
            AppMethodBeat.o(22137);
        }

        @Override // com.umeng.message.UmengMessageHandler
        public Notification getNotification(Context context, UMessage uMessage) {
            AppMethodBeat.i(22140, false);
            if (uMessage.builder_id != 1) {
                Notification notification = super.getNotification(context, uMessage);
                AppMethodBeat.o(22140);
                return notification;
            }
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    NotificationChannel notificationChannel = new NotificationChannel("ChannelId", "MissFresh", 3);
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.GREEN);
                    notificationChannel.enableVibration(true);
                    ((NotificationManager) context.getSystemService("notification")).createNotificationChannel(notificationChannel);
                    Notification.Builder builder = new Notification.Builder(context, "ChannelId");
                    builder.setContentTitle(uMessage.title).setContentText(uMessage.text).setSmallIcon(getSmallIconId(context, uMessage)).setTicker(uMessage.ticker).setAutoCancel(true).setWhen(System.currentTimeMillis()).setLargeIcon(getLargeIcon(context, uMessage)).setShowWhen(true);
                    if (!TextUtils.isEmpty(uMessage.expand_image) && d.this.c != null) {
                        d.this.c.a(builder, uMessage.expand_image);
                    }
                    Notification build = builder.build();
                    AppMethodBeat.o(22140);
                    return build;
                }
                Notification notification2 = super.getNotification(context, uMessage);
                AppMethodBeat.o(22140);
                return notification2;
            } catch (Exception unused) {
                Notification notification3 = super.getNotification(context, uMessage);
                AppMethodBeat.o(22140);
                return notification3;
            }
        }
    }

    /* compiled from: MFPushManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.pushlib.d$2  reason: invalid class name */
    public class AnonymousClass2 implements IUmengRegisterCallback {
        AnonymousClass2() {
        }

        @Override // com.umeng.message.IUmengRegisterCallback
        public void onSuccess(String str) {
            AppMethodBeat.i(22141, false);
            d.this.f = str;
            if (d.this.c != null) {
                d.this.c.a(str);
            }
            AppMethodBeat.o(22141);
        }

        @Override // com.umeng.message.IUmengRegisterCallback
        public void onFailure(String str, String str2) {
            AppMethodBeat.i(22143, false);
            d.this.f = null;
            if (d.this.c != null) {
                d.this.c.a(str, str2);
            }
            AppMethodBeat.o(22143);
        }
    }

    /* compiled from: MFPushManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.pushlib.d$3  reason: invalid class name */
    public class AnonymousClass3 extends UmengNotificationClickHandler {
        AnonymousClass3() {
        }

        @Override // com.umeng.message.UmengNotificationClickHandler
        public void openUrl(Context context, UMessage uMessage) {
            AppMethodBeat.i(22145, false);
            super.openUrl(context, uMessage);
            d.a(d.this, context, uMessage);
            AppMethodBeat.o(22145);
        }

        @Override // com.umeng.message.UmengNotificationClickHandler
        public void openActivity(Context context, UMessage uMessage) {
            AppMethodBeat.i(22146, false);
            super.openActivity(context, uMessage);
            d.a(d.this, context, uMessage);
            AppMethodBeat.o(22146);
        }

        @Override // com.umeng.message.UmengNotificationClickHandler
        public void launchApp(Context context, UMessage uMessage) {
            AppMethodBeat.i(22147, false);
            super.launchApp(context, uMessage);
            d.a(d.this, context, uMessage);
            AppMethodBeat.o(22147);
        }

        @Override // com.umeng.message.UmengNotificationClickHandler
        public void dealWithCustomAction(Context context, UMessage uMessage) {
            AppMethodBeat.i(22149, false);
            d.a(d.this, context, uMessage);
            AppMethodBeat.o(22149);
        }
    }

    public String a() {
        return this.f;
    }

    private void a(Context context, UMessage uMessage) {
        AppMethodBeat.i(22157, false);
        try {
            this.c.a(context, a(uMessage));
        } catch (JSONException unused) {
            this.c.a(context, (Bundle) null);
        }
        AppMethodBeat.o(22157);
    }

    public void a(String str, String str2, a aVar) {
        AppMethodBeat.i(22160, false);
        if (TextUtils.isEmpty(str2)) {
            AppMethodBeat.o(22160);
            return;
        }
        this.b.addAlias(str2, str, new AnonymousClass4(aVar));
        AppMethodBeat.o(22160);
    }

    /* compiled from: MFPushManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.pushlib.d$4  reason: invalid class name */
    public class AnonymousClass4 implements UTrack.ICallBack {
        final /* synthetic */ a a;

        AnonymousClass4(a aVar) {
            this.a = aVar;
        }

        @Override // com.umeng.message.UTrack.ICallBack
        public void onMessage(boolean z, String str) {
            AppMethodBeat.i(22150, false);
            a aVar = this.a;
            if (aVar != null) {
                aVar.a(z, str);
            }
            AppMethodBeat.o(22150);
        }
    }

    /* compiled from: MFPushManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.pushlib.d$5  reason: invalid class name */
    public class AnonymousClass5 implements UTrack.ICallBack {
        @Override // com.umeng.message.UTrack.ICallBack
        public void onMessage(boolean z, String str) {
        }

        AnonymousClass5() {
        }
    }

    public void a(String str, String str2) {
        AppMethodBeat.i(22162, false);
        this.b.deleteAlias(str2, str, new AnonymousClass5());
        AppMethodBeat.o(22162);
    }

    private Bundle a(UMessage uMessage) throws JSONException {
        AppMethodBeat.i(22164, false);
        if (uMessage == null) {
            AppMethodBeat.o(22164);
            return null;
        }
        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> entry : uMessage.extra.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        AppMethodBeat.o(22164);
        return bundle;
    }

    public void a(b bVar) {
        this.c = bVar;
    }
}
