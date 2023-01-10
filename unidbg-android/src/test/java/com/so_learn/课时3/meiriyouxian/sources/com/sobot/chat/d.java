package com.sobot.chat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.api.enumtype.SobotChatStatusMode;
import com.sobot.chat.core.channel.a;
import com.sobot.chat.server.SobotSessionServer;
import com.sobot.chat.utils.ab;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.s;
import com.sobot.chat.utils.y;

/* compiled from: ZCSobotApi */
public class d {
    private static String a = ZhiChiApi.class.getSimpleName();

    public static void a(Context context, String str) {
        m.d("checkIMConnected partnerid=" + str);
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            s.c(applicationContext, "sobot_wayhttp_chat");
            a.a(applicationContext).a().reconnectChannel();
            Intent intent = new Intent(applicationContext, SobotSessionServer.class);
            intent.putExtra("sobot_current_im_partnerid", str);
            ab.a(applicationContext, intent);
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

    public static boolean a(int i) {
        if (((i - 1) & i) == 0) {
            return a.a(i);
        }
        throw new Resources.NotFoundException("markConfig \u5fc5\u987b\u4e3a2\u7684\u6307\u6570\u6b21\u5e42");
    }

    public static void a(int i, boolean z) {
        if (((i - 1) & i) == 0) {
            a.a(i, z);
            return;
        }
        throw new Resources.NotFoundException("markConfig \u5fc5\u987b\u4e3a2\u7684\u6307\u6570\u6b21\u5e42");
    }
}
