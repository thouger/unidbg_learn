package com.sdk.mobile.handler.a;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.mobile.handler.UiHandler;
import com.sdk.mobile.manager.login.ctc.OauthActivityCtc;

public final class e implements UiHandler {
    private OauthActivityCtc a;

    public e(OauthActivityCtc oauthActivityCtc) {
        this.a = oauthActivityCtc;
    }

    public final void disMiss() {
        AppMethodBeat.i(18380, false);
        OauthActivityCtc oauthActivityCtc = this.a;
        if (oauthActivityCtc == null) {
            AppMethodBeat.o(18380);
            return;
        }
        oauthActivityCtc.disMiss();
        AppMethodBeat.o(18380);
    }

    public final View findViewById(int i) {
        AppMethodBeat.i(18389, false);
        OauthActivityCtc oauthActivityCtc = this.a;
        View findViewById = oauthActivityCtc == null ? null : oauthActivityCtc.findViewById(i);
        AppMethodBeat.o(18389);
        return findViewById;
    }

    public final void finish() {
        AppMethodBeat.i(18384, false);
        OauthActivityCtc oauthActivityCtc = this.a;
        if (oauthActivityCtc == null) {
            AppMethodBeat.o(18384);
            return;
        }
        oauthActivityCtc.finish();
        AppMethodBeat.o(18384);
    }

    public final String getMobile() {
        AppMethodBeat.i(18378, false);
        OauthActivityCtc oauthActivityCtc = this.a;
        String mobile = oauthActivityCtc == null ? null : oauthActivityCtc.getMobile();
        AppMethodBeat.o(18378);
        return mobile;
    }

    public final void login() {
        AppMethodBeat.i(18387, false);
        OauthActivityCtc oauthActivityCtc = this.a;
        if (oauthActivityCtc == null) {
            AppMethodBeat.o(18387);
            return;
        }
        oauthActivityCtc.login();
        AppMethodBeat.o(18387);
    }
}
