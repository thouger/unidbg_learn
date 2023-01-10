package cn.missfresh.module.base.common.providers;

import android.app.Activity;
import cn.missfresh.module.base.bean.LoginEvent;
import cn.missfresh.module.base.common.livedata.MFStickyLiveData;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface ILoginService extends IProvider {
    MFStickyLiveData<LoginEvent> a();

    void a(int i);

    boolean a(Activity activity);
}
