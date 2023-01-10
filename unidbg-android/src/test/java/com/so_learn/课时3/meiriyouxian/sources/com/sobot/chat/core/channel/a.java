package com.sobot.chat.core.channel;

import android.content.Context;
import android.telecom.Logging.Session;
import android.text.TextUtils;
import com.huawei.hms.support.api.push.PushReceiver;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.api.model.SobotMsgCenterModel;
import com.sobot.chat.api.model.ZhiChiPushMessage;
import com.sobot.chat.utils.ZhiChiConfig;
import com.sobot.chat.utils.s;
import com.sobot.chat.utils.u;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SobotMsgManager */
public class a {
    private static a a;
    private Context b;
    private ZhiChiApi c = null;
    private u d;
    private HashMap<String, ZhiChiConfig> e = new HashMap<>();
    private ZhiChiConfig f = new ZhiChiConfig();

    private a(Context context) {
        this.b = context;
        this.d = u.a(context.getApplicationContext());
    }

    public static a a(Context context) {
        if (a == null) {
            a = new a(context.getApplicationContext());
        }
        return a;
    }

    public void a(Context context, String str, String str2) {
        try {
            a(context).a().config(null, str);
        } catch (Exception unused) {
        }
    }

    public ZhiChiApi a() {
        if (this.c == null) {
            synchronized (a.class) {
                if (this.c == null) {
                    this.c = com.sobot.chat.api.a.a(this.b);
                }
            }
        }
        return this.c;
    }

    public ZhiChiConfig a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ZhiChiConfig();
        }
        return this.f;
    }

    public void b() {
        this.f.clearCache();
    }

    public int a(String str, boolean z, String str2) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        if (str2 == null) {
            str2 = "";
        }
        SobotMsgCenterModel sobotMsgCenterModel = (SobotMsgCenterModel) this.d.b(a(str, str2));
        if (sobotMsgCenterModel == null) {
            return 0;
        }
        int unreadCount = sobotMsgCenterModel.getUnreadCount();
        if (z) {
            sobotMsgCenterModel.setUnreadCount(0);
            this.d.a(a(str, str2), sobotMsgCenterModel);
        }
        return unreadCount;
    }

    public int a(ZhiChiPushMessage zhiChiPushMessage, String str, String str2) {
        int i = 0;
        if (zhiChiPushMessage != null && !TextUtils.isEmpty(zhiChiPushMessage.getAppId())) {
            String appId = zhiChiPushMessage.getAppId();
            if (str2 == null) {
                str2 = "";
            }
            SobotMsgCenterModel sobotMsgCenterModel = (SobotMsgCenterModel) this.d.b(a(appId, str2));
            if (sobotMsgCenterModel != null) {
                i = sobotMsgCenterModel.getUnreadCount() + 1;
                sobotMsgCenterModel.setUnreadCount(i);
                sobotMsgCenterModel.setSenderName(zhiChiPushMessage.getAname());
                sobotMsgCenterModel.setSenderFace(zhiChiPushMessage.getAface());
                sobotMsgCenterModel.setLastMsg(c(zhiChiPushMessage.getContent()));
                sobotMsgCenterModel.setLastDateTime(str);
                this.d.a(a(appId, str2), sobotMsgCenterModel);
                Context context = this.b;
                if (context != null) {
                    s.a(context, "sobot_last_msg_content", sobotMsgCenterModel.getLastMsg());
                }
            }
        }
        return i;
    }

    public void b(Context context, String str, String str2) {
        if (context != null && !TextUtils.isEmpty(str)) {
            if (str2 == null) {
                str2 = "";
            }
            u a2 = u.a(context);
            ArrayList arrayList = (ArrayList) a2.b(b(str2));
            if (arrayList != null && arrayList.size() > 0) {
                arrayList.remove(str);
                a2.a(b(str2), arrayList);
            }
        }
    }

    private String c(String str) {
        String str2;
        int i;
        try {
            JSONObject jSONObject = new JSONObject(str);
            str2 = jSONObject.optString("msg");
            i = jSONObject.optInt(PushReceiver.PushMessageThread.MSGTYPE);
        } catch (JSONException e) {
            e.printStackTrace();
            str2 = "";
            i = -1;
        }
        if (i == -1 || TextUtils.isEmpty(str2)) {
            return str2;
        }
        if (!(i == 4 || i == 5)) {
            if (i == 1) {
                return "[\u56fe\u7247]";
            }
            if (i != 0) {
                return str;
            }
        }
        return str2;
    }

    public static String a(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        if (str == null) {
            str = "";
        }
        return str + Session.SESSION_SEPARATION_CHAR_CHILD + str2 + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_msg_center_data";
    }

    public static String b(String str) {
        if (str == null) {
            str = "";
        }
        return str + Session.SESSION_SEPARATION_CHAR_CHILD + "sobot_msg_center_list_data";
    }
}
