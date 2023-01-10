package cn.missfresh.application;

import com.tencent.tinker.loader.app.TinkerApplication;

public class MissFreshApplication extends TinkerApplication {
    public MissFreshApplication() {
        super(15, "cn.missfresh.application.MissFreshApplicationLike", "cn.missfresh.hotfix.MFTinkerLoader", false);
    }
}
