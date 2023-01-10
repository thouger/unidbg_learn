package cn.missfresh.module.base.support.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.config.b;
import cn.missfresh.module.base.manager.bean.UserInfo;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.utils.ac;
import cn.missfresh.module.base.utils.l;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class MiniRoutineHelper {
    private Context a;

    public MiniRoutineHelper(Context context) {
        this.a = context;
    }

    public void a(String str, String str2, String str3, String str4, String str5, Bitmap bitmap) {
        AppMethodBeat.i(22429, false);
        a(str, str2, str3, str4, str5, bitmap, null);
        AppMethodBeat.o(22429);
    }

    public void a(String str, String str2, String str3, String str4, String str5, Bitmap bitmap, byte[] bArr) {
        String str6;
        AppMethodBeat.i(22431, false);
        Context context = this.a;
        if (context == null) {
            ac.a("\u5c0f\u7a0b\u5e8f\u5206\u4eab", "\u5206\u4eab", "mContext == null");
            AppMethodBeat.o(22431);
            return;
        }
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(context, "wx31562d0a467cb40d", true);
        WXMiniProgramObject wXMiniProgramObject = new WXMiniProgramObject();
        wXMiniProgramObject.webpageUrl = str;
        wXMiniProgramObject.userName = str2;
        wXMiniProgramObject.miniprogramType = b.c;
        UserInfo p = e.p();
        if (p == null || p.getUser_id() <= 0 || TextUtils.isEmpty(str3) || str3.contains("mu=")) {
            str6 = "";
        } else {
            String str7 = "mu=" + p.getUser_id();
            if (str3.contains("?")) {
                str6 = "&" + str7;
            } else {
                str6 = "?" + str7;
            }
        }
        wXMiniProgramObject.path = str3 + str6;
        wXMiniProgramObject.withShareTicket = true;
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXMiniProgramObject);
        wXMediaMessage.title = str4;
        wXMediaMessage.description = str5;
        l lVar = new l();
        if (bArr != null && bArr.length <= 131072) {
            wXMediaMessage.thumbData = bArr;
        } else if (bitmap != null) {
            wXMediaMessage.thumbData = lVar.b(bitmap, 131072);
        } else {
            Bitmap decodeResource = BitmapFactory.decodeResource(this.a.getResources(), R.mipmap.ic_launcher);
            Bitmap a = lVar.a(decodeResource);
            decodeResource.recycle();
            wXMediaMessage.thumbData = lVar.a(a, true);
        }
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = a("webpage");
        req.message = wXMediaMessage;
        req.scene = 0;
        createWXAPI.sendReq(req);
        AppMethodBeat.o(22431);
    }

    private String a(String str) {
        String str2;
        AppMethodBeat.i(22433, false);
        if (str == null) {
            str2 = String.valueOf(System.currentTimeMillis());
        } else {
            str2 = str + System.currentTimeMillis();
        }
        AppMethodBeat.o(22433);
        return str2;
    }

    public static boolean a(Context context) {
        AppMethodBeat.i(22434, false);
        if (!WXAPIFactory.createWXAPI(context, "wx31562d0a467cb40d", true).isWXAppInstalled()) {
            a.a(context.getString(R.string.wechat_client_inavailable));
            AppMethodBeat.o(22434);
            return false;
        }
        AppMethodBeat.o(22434);
        return true;
    }
}
