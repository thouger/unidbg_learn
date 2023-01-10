package com.sdk.mobile.manager.login.cucc;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;
import com.sdk.base.api.CallBack;
import com.sdk.base.framework.bean.OauthResultMode;

class OauthActivity$2 implements CallBack<Object> {
    final /* synthetic */ OauthActivity this$0;

    OauthActivity$2(OauthActivity oauthActivity) {
        JniLib.cV(this, oauthActivity, 985);
    }

    public void onFailed(int i, int i2, String str, String str2) {
        AppMethodBeat.i(18680, false);
        UiOauthManager.getInstance(this.this$0).setOauthResult(new OauthResultMode(i, str, i2), this.this$0, true);
        AppMethodBeat.o(18680);
    }

    public void onSuccess(int i, String str, int i2, Object obj, String str2) {
        AppMethodBeat.i(18677, false);
        UiOauthManager.getInstance(this.this$0).setOauthResult(new OauthResultMode(i, str, i2, obj, str2), this.this$0, true);
        AppMethodBeat.o(18677);
    }
}
