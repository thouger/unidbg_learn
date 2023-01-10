package cn.missfresh.module.base.common.resourcemanager.strategy;

import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.common.config.b;
import cn.missfresh.module.base.common.resourcemanager.bean.SkipBean;
import cn.missfresh.module.base.common.resourcemanager.strategy.api.IResourceStrategy;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Map;

/* compiled from: ExternalResourceStrategy */
public class a implements IResourceStrategy {
    @Override // cn.missfresh.module.base.common.resourcemanager.strategy.api.IResourceStrategy
    public void skip(SkipBean skipBean, Map<String, Object> map) {
        AppMethodBeat.i(12291, false);
        if (skipBean == null || skipBean.type == null) {
            AppMethodBeat.o(12291);
            return;
        }
        String str = skipBean.type;
        char c = '\uffff';
        int hashCode = str.hashCode();
        if (hashCode != -455292449) {
            if (hashCode == 1501493869 && str.equals(SkipBean.Type.LIVEBROADCASTING)) {
                c = 0;
            }
        } else if (str.equals(SkipBean.Type.THIRDPARTY)) {
            c = 1;
        }
        if (c == 0) {
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getApplication(), "wx31562d0a467cb40d");
            WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
            req.userName = "gh_05c85a53c7ee";
            req.path = skipBean.link;
            req.miniprogramType = b.c;
            createWXAPI.sendReq(req);
        } else if (c == 1) {
            if (cn.missfresh.utils.b.a(skipBean.link)) {
                AppMethodBeat.o(12291);
                return;
            }
            IWXAPI createWXAPI2 = WXAPIFactory.createWXAPI(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getApplication(), "wx31562d0a467cb40d");
            String[] split = skipBean.link.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
            if (split == null || split.length != 2) {
                AppMethodBeat.o(12291);
                return;
            }
            WXLaunchMiniProgram.Req req2 = new WXLaunchMiniProgram.Req();
            req2.userName = split[1];
            req2.path = split[0];
            req2.miniprogramType = b.c;
            createWXAPI2.sendReq(req2);
        }
        AppMethodBeat.o(12291);
    }
}
