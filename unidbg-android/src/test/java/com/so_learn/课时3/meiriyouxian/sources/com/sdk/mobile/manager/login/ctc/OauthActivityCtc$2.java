package com.sdk.mobile.manager.login.ctc;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;
import com.sdk.base.api.CallBack;
import com.sdk.base.framework.bean.OauthResultMode;

class OauthActivityCtc$2 implements CallBack<Object> {
    final /* synthetic */ OauthActivityCtc this$0;

    OauthActivityCtc$2(OauthActivityCtc oauthActivityCtc) {
        JniLib.cV(this, oauthActivityCtc, 982);
    }

    public void onFailed(int i, int i2, String str, String str2) {
        AppMethodBeat.i(18907, false);
        UiOauthManagerCtc.getInstance(this.this$0).setOauthResult(new OauthResultMode(i, str, i2), this.this$0, true);
        AppMethodBeat.o(18907);
    }

    public void onSuccess(int i, String str, int i2, Object obj, String str2) {
        AppMethodBeat.i(18905, false);
        UiOauthManagerCtc.getInstance(this.this$0).setOauthResult(new OauthResultMode(i, str, i2, obj, str2), this.this$0, true);
        AppMethodBeat.o(18905);
    }
}
