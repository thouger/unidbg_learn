package cn.missfresh.module.base.common.a;

import cn.missfresh.buttomline.abtest.inf.ABConfig;
import com.tencent.connect.common.Constants;

/* compiled from: MainUserPrivacyProtocolAbTestConf */
public class b implements ABConfig {
    public static boolean a() {
        return true;
    }

    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String getTag() {
        return "MainUserPrivacyProtocolAbTestConf";
    }

    @Override // cn.missfresh.buttomline.abtest.inf.ABConfig
    public String[] getCaseIds() {
        return new String[]{Constants.VIA_REPORT_TYPE_SHARE_TO_QZONE};
    }
}
