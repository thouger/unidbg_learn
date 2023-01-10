package cn.missfresh.module.base.common.providers;

import androidx.fragment.app.FragmentActivity;
import cn.missfresh.module.mvp.mvp.interfaces.IModel;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface IOrderListService extends IProvider {
    void cancelOrder(FragmentActivity fragmentActivity, String str, String str2, boolean z);

    void receive(IModel.a aVar, String str);

    void receiveCash(String str);
}
