package cn.missfresh.application.wxapi;

import android.content.Intent;
import android.os.Bundle;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.common.config.a;
import cn.missfresh.module.base.utils.ac;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessView;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.mipush.sdk.Constants;

public class WXPayEntryActivity extends BaseFragmentActivity implements IWXAPIEventHandler {
    private final String a = "WXPayEntryActivity";
    private IWXAPI j;

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.application.wxapi.WXPayEntryActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        WXPayEntryActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: cn.missfresh.application.wxapi.WXPayEntryActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(7, false);
        super.onCreate(bundle);
        try {
            this.j = WXAPIFactory.createWXAPI(this, a.a);
            this.j.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(7);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onNewIntent(Intent intent) {
        AppMethodBeat.i(11, false);
        super.onNewIntent(intent);
        setIntent(intent);
        this.j.handleIntent(intent, this);
        AppMethodBeat.o(11);
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        AppMethodBeat.i(26, false);
        if (baseResp.getType() == 5) {
            int i = baseResp.errCode;
            if (!(i == -5 || i == -4 || i == -3)) {
                if (i == -2) {
                    d.b("WXPayEntryActivity", "\u652f\u4ed8\u53d6\u6d88");
                    r();
                } else if (i != -1) {
                    if (i == 0) {
                        l();
                        a();
                    }
                }
                finish();
            }
            r();
            ac.a("\u4ea4\u6613\u94fe\u8def", "\u5fae\u4fe1\u652f\u4ed8\u5931\u8d25", baseResp.errStr + Constants.ACCEPT_TIME_SEPARATOR_SP + baseResp.errCode);
            finish();
        } else if (baseResp.getType() == 26 && baseResp.getType() == 26) {
            WXOpenBusinessView.Resp resp = (WXOpenBusinessView.Resp) baseResp;
            cn.missfresh.ui.a.a.a(String.format("nextMsg=%snerrStr=%snbusinessType=%s", resp.extMsg, baseResp.errStr, resp.businessType));
        }
        AppMethodBeat.o(26);
    }

    private void a() {
        AppMethodBeat.i(31, false);
        sendBroadcast(new Intent("wxpay_finish"));
        finish();
        AppMethodBeat.o(31);
    }

    private void r() {
        AppMethodBeat.i(35, false);
        sendBroadcast(new Intent("wxpay_cancel"));
        finish();
        AppMethodBeat.o(35);
    }
}
