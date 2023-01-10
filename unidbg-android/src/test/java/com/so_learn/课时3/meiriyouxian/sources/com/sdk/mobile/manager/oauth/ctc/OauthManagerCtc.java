package com.sdk.mobile.manager.oauth.ctc;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.api.CallBack;
import com.sdk.base.framework.a.a.c;
import com.sdk.base.module.manager.SDKManager;
import com.sdk.mobile.handler.a.a;
import com.sdk.mobile.manager.oauth.cucc.OauthManager;

public class OauthManagerCtc extends SDKManager {
    private static volatile OauthManagerCtc manager;
    private Context mContext;

    private OauthManagerCtc(Context context) {
        this.mContext = context;
    }

    public static OauthManagerCtc getInstance(Context context) {
        AppMethodBeat.i(18487, false);
        if (manager == null) {
            synchronized (OauthManager.class) {
                try {
                    if (manager == null) {
                        manager = new OauthManagerCtc(context);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(18487);
                    throw th;
                }
            }
        }
        OauthManagerCtc oauthManagerCtc = manager;
        AppMethodBeat.o(18487);
        return oauthManagerCtc;
    }

    public <T> void getAuthoriseCode(int i, CallBack<T> callBack) {
        AppMethodBeat.i(18490, false);
        new a(this.mContext, i, callBack).a(1);
        AppMethodBeat.o(18490);
    }

    public <T> void getMobileForCode(String str, int i, CallBack<T> callBack) {
        AppMethodBeat.i(18493, false);
        if (c.a(str).booleanValue()) {
            toFailed(callBack, 101001, "\u6388\u6743\u7801\u4e0d\u80fd\u4e3a\u7a7a");
        } else {
            new a(this.mContext, i, callBack).a(str, (String) null);
        }
        AppMethodBeat.o(18493);
    }

    public <T> void getMobileForCode(String str, String str2, int i, CallBack<T> callBack) {
        int i2;
        String str3;
        AppMethodBeat.i(18495, false);
        if (c.a(str).booleanValue()) {
            i2 = 101001;
            str3 = "\u6388\u6743\u7801\u4e0d\u80fd\u4e3a\u7a7a";
        } else if (c.a(str2).booleanValue()) {
            i2 = 101002;
            str3 = "\u8ba4\u8bc1\u7684\u624b\u673a\u53f7\u4e0d\u80fd\u4e3a\u7a7a";
        } else {
            new a(this.mContext, i, callBack).a(str, str2);
            AppMethodBeat.o(18495);
        }
        toFailed(callBack, i2, str3);
        AppMethodBeat.o(18495);
    }
}
