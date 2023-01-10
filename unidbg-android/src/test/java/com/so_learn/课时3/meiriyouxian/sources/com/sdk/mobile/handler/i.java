package com.sdk.mobile.handler;

import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.mobile.manager.login.cucc.OauthActivity;

public final class i implements UiHandler {
    private OauthActivity a;

    public i(OauthActivity oauthActivity) {
        this.a = oauthActivity;
    }

    public final void disMiss() {
        AppMethodBeat.i(18288, false);
        OauthActivity oauthActivity = this.a;
        if (oauthActivity == null) {
            AppMethodBeat.o(18288);
            return;
        }
        oauthActivity.disMiss();
        AppMethodBeat.o(18288);
    }

    public final View findViewById(int i) {
        AppMethodBeat.i(18298, false);
        OauthActivity oauthActivity = this.a;
        View findViewById = oauthActivity == null ? null : oauthActivity.findViewById(i);
        AppMethodBeat.o(18298);
        return findViewById;
    }

    public final void finish() {
        AppMethodBeat.i(18291, false);
        OauthActivity oauthActivity = this.a;
        if (oauthActivity == null) {
            AppMethodBeat.o(18291);
            return;
        }
        oauthActivity.finish();
        AppMethodBeat.o(18291);
    }

    public final String getMobile() {
        AppMethodBeat.i(18285, false);
        OauthActivity oauthActivity = this.a;
        String mobile = oauthActivity == null ? null : oauthActivity.getMobile();
        AppMethodBeat.o(18285);
        return mobile;
    }

    public final void login() {
        AppMethodBeat.i(18294, false);
        OauthActivity oauthActivity = this.a;
        if (oauthActivity == null) {
            AppMethodBeat.o(18294);
            return;
        }
        oauthActivity.login();
        AppMethodBeat.o(18294);
    }
}
