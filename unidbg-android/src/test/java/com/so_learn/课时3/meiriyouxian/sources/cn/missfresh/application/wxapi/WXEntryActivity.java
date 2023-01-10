package cn.missfresh.application.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import cn.missfresh.basiclib.utils.c;
import cn.missfresh.module.base.bean.OpenWxMianMiBean;
import cn.missfresh.module.food.helper.i;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import de.greenrobot.event.EventBus;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI a;
    private long b = 0;

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(6, false);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT < 26) {
            setRequestedOrientation(1);
        }
        try {
            this.a = WXAPIFactory.createWXAPI(this, "wx31562d0a467cb40d", true);
            this.a.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
            d.a("WXEntryActivity", e);
        }
        AppMethodBeat.o(6);
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        AppMethodBeat.i(12, false);
        super.onNewIntent(intent);
        try {
            setIntent(intent);
            this.a.handleIntent(intent, this);
        } catch (Exception e) {
            e.printStackTrace();
            d.a("WXEntryActivity", e);
        }
        AppMethodBeat.o(12);
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
        AppMethodBeat.i(19, false);
        if (baseReq != null) {
            d.d("WXEntryActivity", "onReq " + baseReq.getType());
            int type = baseReq.getType();
            if (type == 3) {
                a();
            } else if (type == 4) {
                a((ShowMessageFromWX.Req) baseReq);
            }
        }
        AppMethodBeat.o(19);
    }

    private void a(ShowMessageFromWX.Req req) {
        AppMethodBeat.i(27, false);
        if (req == null) {
            AppMethodBeat.o(27);
            return;
        }
        d.c("WXEntryActivity", "goToShowMsg ");
        WXMediaMessage wXMediaMessage = req.message;
        if (wXMediaMessage != null) {
            String str = ((WXAppExtendObject) wXMediaMessage.mediaObject).extInfo;
            new i().a(str);
            d.c("WXEntryActivity", "goToShowMsg " + str);
        }
        finish();
        AppMethodBeat.o(27);
    }

    private void a() {
        AppMethodBeat.i(33, false);
        d.c("WXEntryActivity", "goToGetMsg");
        AppMethodBeat.o(33);
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        boolean z = false;
        AppMethodBeat.i(37, false);
        int type = baseResp.getType();
        d.d("WXEntryActivity", type + "---------  code:" + baseResp.errCode + "  transaction:" + baseResp.transaction);
        if (type == 1) {
            a(baseResp);
        } else if (type != 2) {
            if (type == 25) {
                c a = c.a();
                if (baseResp.errCode == 0) {
                    z = true;
                }
                a.a(new OpenWxMianMiBean(z));
            }
        } else if (System.currentTimeMillis() - this.b > 3000) {
            this.b = System.currentTimeMillis();
            EventBus.getDefault().post(baseResp);
        }
        finish();
        AppMethodBeat.o(37);
    }

    private void a(BaseResp baseResp) {
        AppMethodBeat.i(44, false);
        Intent intent = new Intent("mryx_login");
        d.d("WXEntryActivity", "handleAuthResult...." + baseResp.errCode);
        int i = baseResp.errCode;
        if (i != -4) {
            if (i == -2) {
                Intent intent2 = new Intent("mryx_login");
                intent2.putExtra("wx_cancel", true);
                sendBroadcast(intent2);
            } else if (i == 0) {
                intent.putExtra("wx_code", ((SendAuth.Resp) baseResp).code);
                sendBroadcast(intent);
            }
        }
        AppMethodBeat.o(44);
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStart() {
        AppMethodBeat.i(48, false);
        super.onStart();
        d.d("WXEntryActivity", "onStart");
        AppMethodBeat.o(48);
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        AppMethodBeat.i(52, false);
        super.onDestroy();
        d.d("WXEntryActivity", "onDestroy");
        AppMethodBeat.o(52);
    }
}
