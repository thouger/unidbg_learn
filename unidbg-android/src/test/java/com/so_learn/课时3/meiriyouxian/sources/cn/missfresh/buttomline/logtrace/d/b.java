package cn.missfresh.buttomline.logtrace.d;

import cn.missfresh.basiclib.net.a.a;
import cn.missfresh.buttomline.logtrace.bean.ResponseBean;
import cn.missfresh.utils.a.d;

/* compiled from: BaseAbtestApiResult */
public abstract class b implements a<ResponseBean> {
    /* renamed from: a */
    public void onSuccess(ResponseBean responseBean) {
        d.d("CensusConfig", "BaseAbtestApiResult.onSuccess data=" + responseBean);
    }

    @Override // cn.missfresh.basiclib.net.a.a
    public void onFail(int i, String str) {
        d.d("CensusConfig", "BaseAbtestApiResult.onFail errorMessage=" + str + ",errorCode=" + i);
    }

    @Override // cn.missfresh.basiclib.net.a.a
    public void onComplete() {
        d.d("CensusConfig", "BaseAbtestApiResult.onComplete");
    }
}
