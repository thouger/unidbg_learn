package cn.missfresh.module.base.common.providers;

import androidx.fragment.app.FragmentActivity;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface IPreClassifyService extends IProvider {
    void preRequest(FragmentActivity fragmentActivity);
}
