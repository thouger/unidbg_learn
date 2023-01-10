package cn.missfresh.buttomline.abtest.net;

import cn.missfresh.basiclib.net.a.a;
import cn.missfresh.buttomline.abtest.bean.Plan;
import cn.missfresh.buttomline.abtest.log.Logger;
import java.util.List;

public abstract class BaseAbtestApiResult implements a<List<Plan>> {
    public static final int SUCCESS = 0;

    public void onSuccess(List<Plan> list) {
        Logger.i("BaseAbtestApiResult.onSuccess data=" + list);
    }

    @Override // cn.missfresh.basiclib.net.a.a
    public void onFail(int i, String str) {
        Logger.i("BaseAbtestApiResult.onFail errorMessage=" + str + ",errorCode=" + i);
    }

    @Override // cn.missfresh.basiclib.net.a.a
    public void onComplete() {
        Logger.i("BaseAbtestApiResult.onComplete");
    }
}
