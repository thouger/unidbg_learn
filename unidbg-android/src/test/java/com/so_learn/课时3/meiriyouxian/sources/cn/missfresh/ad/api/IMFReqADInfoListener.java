package cn.missfresh.ad.api;

import cn.missfresh.ad.a.d;
import cn.missfresh.ad.data.MFADBean;

public interface IMFReqADInfoListener {
    void onReqADInfoFailed(d dVar, String str);

    void onReqADInfoSucceed(MFADBean mFADBean);
}
