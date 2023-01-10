package cn.missfresh.module.base.common.providers;

import androidx.lifecycle.MutableLiveData;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface IRecommendSwitchService extends IProvider {
    MutableLiveData<Boolean> a();
}
