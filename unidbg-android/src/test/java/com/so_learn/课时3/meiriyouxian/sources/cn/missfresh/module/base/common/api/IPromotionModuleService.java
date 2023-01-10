package cn.missfresh.module.base.common.api;

import android.app.Application;
import androidx.fragment.app.Fragment;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface IPromotionModuleService extends IProvider {
    void initARouter(Application application);

    boolean isIntegralFragment(Fragment fragment);
}
