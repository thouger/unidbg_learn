package cn.missfresh.linksdk.a;

import android.net.wifi.WifiEnterpriseConfig;
import android.text.TextUtils;
import cn.missfresh.linksdk.api.MFLinkCallBack;
import cn.missfresh.linksdk.b.a;
import cn.missfresh.linksdk.b.b;
import cn.missfresh.linksdk.bean.AppLinkInfo;
import cn.missfresh.linksdk.bean.IntentData;
import cn.missfresh.linksdk.c.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;

/* compiled from: AppLinkEngineImpl */
class a$1 implements a.a {
    final /* synthetic */ IntentData a;
    final /* synthetic */ MFLinkCallBack b;
    final /* synthetic */ a c;

    a$1(a aVar, IntentData intentData, MFLinkCallBack mFLinkCallBack) {
        this.c = aVar;
        this.a = intentData;
        this.b = mFLinkCallBack;
    }

    public void a(b bVar, String str) {
        AppMethodBeat.i(13764, false);
        c.a("MFLinkAgent", "Request Result : Failed " + bVar + " errorMsg: " + str);
        cn.missfresh.linksdk.c.b.a("AppLink: path is empty !", this.a.getMapParams(), this.b);
        AppMethodBeat.o(13764);
    }

    public void a(String str) {
        AppMethodBeat.i(13767, false);
        try {
            AppLinkInfo appLinkInfo = (AppLinkInfo) JSON.parseObject(str, AppLinkInfo.class);
            if (appLinkInfo == null) {
                c.a("MFLinkAgent", "Request Result: Failed  data format error");
                cn.missfresh.linksdk.c.b.a("Request Result: Failed  data error ; detail is null " + str, this.a.getMapParams(), this.b);
                AppMethodBeat.o(13767);
            } else if (appLinkInfo.getCode() != 0 || TextUtils.isEmpty(appLinkInfo.getData())) {
                c.a("MFLinkAgent", "Request Result: Failed  data error ; detail " + appLinkInfo.toString());
                cn.missfresh.linksdk.c.b.a("Request Result: Failed  data error ; detail " + str, this.a.getMapParams(), this.b);
                AppMethodBeat.o(13767);
            } else {
                a.a(this.c, str, appLinkInfo, this.b, this.a.getUri());
                AppMethodBeat.o(13767);
            }
        } catch (Exception e) {
            c.a("MFLinkAgent", "Request Result: Failed data format error ; detail " + e.getMessage());
            cn.missfresh.linksdk.c.b.a("Request Result: Failed data format error ; detail : " + str + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + e.getMessage(), this.a.getMapParams(), this.b);
        }
    }
}
