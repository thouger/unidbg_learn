package com.sobot.chat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.sobot.chat.api.apiUtils.SobotApp;
import com.sobot.chat.api.enumtype.SobotChatStatusMode;
import com.sobot.chat.api.model.CommonModel;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.SobotMsgCenterModel;
import com.sobot.chat.conversation.SobotChatActivity;
import com.sobot.chat.core.channel.a;
import com.sobot.chat.listener.c;
import com.sobot.chat.server.SobotSessionServer;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.s;
import com.sobot.chat.utils.u;
import com.sobot.chat.utils.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: SobotApi */
public class b {
    private static String a = b.class.getSimpleName();

    public static void a(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            String str3 = a;
            Log.e(str3, "initSobotSDK  \u53c2\u6570\u4e3a\u7a7a context:" + context + "  appkey:" + str);
            return;
        }
        SobotApp.setApplicationContext(context);
        s.a(context, str);
        s.a(context, "sobot_appkey_chat", str);
        s.a(context, "sobot_config_initsdk", true);
        s.a(context, "sobot_config_appkey", str);
        if (d.m(context.getApplicationContext())) {
            m.a(d.d(context));
            new Thread(new AnonymousClass1(context, str, str2)).start();
        }
    }

    /* compiled from: SobotApi */
    /* renamed from: com.sobot.chat.b$1  reason: invalid class name */
    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;

        AnonymousClass1(Context context, String str, String str2) {
            this.a = context;
            this.b = str;
            this.c = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.a(this.a).a(this.a, this.b, this.c);
        }
    }

    public static void a(Context context, Information information) {
        if (information == null || context == null) {
            Log.e(a, "Information is Null!");
        } else if (!s.b(context, "sobot_config_initsdk", false)) {
            Log.e(a, "\u8bf7\u5728Application\u4e2d\u8c03\u7528\u3010SobotApi.initSobotSDK()\u3011\u6765\u521d\u59cb\u5316SDK!");
        } else {
            d.a(16, true);
            Intent intent = new Intent(context, SobotChatActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("sobot_bundle_info", information);
            intent.putExtra("sobot_bundle_information", bundle);
            intent.setFlags(268435456);
            context.startActivity(intent);
        }
    }

    public static void a(Context context) {
        if (context != null) {
            if (y.h != null) {
                y.h.a(SobotChatStatusMode.ZCServerConnectOffline);
            }
            a.a(context).a().disconnChannel();
            a.a(context).b();
        }
    }

    public static void b(Context context) {
        s.a(context, "sobot_is_exit", true);
        if (context != null) {
            try {
                a(context);
                context.stopService(new Intent(context, SobotSessionServer.class));
                String b = s.b(context, "sobot_cid_chat", "");
                String b2 = s.b(context, "sobot_uid_chat", "");
                s.c(context, "sobot_wslinkbak_chat");
                s.c(context, "sobot_wslinkdefault_chat");
                s.c(context, "sobot_uid_chat");
                s.c(context, "sobot_cid_chat");
                s.c(context, "sobot_puid_chat");
                s.c(context, "sobot_appkey_chat");
                if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(b2)) {
                    a.a(context).a().out(b, b2, new AnonymousClass2());
                }
            } catch (Exception unused) {
            }
        }
    }

    /* compiled from: SobotApi */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.b$2  reason: invalid class name */
    public static class AnonymousClass2 implements com.sobot.chat.core.http.c.a<CommonModel> {
        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
        }

        AnonymousClass2() {
        }

        public void a(CommonModel commonModel) {
            m.d("\u4e0b\u7ebf\u6210\u529f");
        }
    }

    public static void a(Context context, boolean z, int i, int i2) {
        if (context != null) {
            s.a(context, "sobot_notification_flag_chat", z);
            s.a(context, "sobot_notification_small_icon", i);
            s.a(context, "sobot_notification_large_icon", i2);
        }
    }

    public static void a(c cVar) {
        y.b = cVar;
    }

    public static List<SobotMsgCenterModel> a(Context context, String str) {
        if (context == null) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        u a2 = u.a(context);
        ArrayList arrayList = (ArrayList) a2.b(a.b(str));
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null && arrayList.size() > 0) {
            arrayList2.clear();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                SobotMsgCenterModel sobotMsgCenterModel = (SobotMsgCenterModel) a2.b(a.a((String) it2.next(), str));
                if (sobotMsgCenterModel != null) {
                    arrayList2.add(sobotMsgCenterModel);
                }
            }
        }
        return arrayList2;
    }

    public static boolean a(int i) {
        if (((i - 1) & i) == 0) {
            return a.a(i);
        }
        throw new Resources.NotFoundException("markConfig \u5fc5\u987b\u4e3a2\u7684\u6307\u6570\u6b21\u5e42");
    }
}
