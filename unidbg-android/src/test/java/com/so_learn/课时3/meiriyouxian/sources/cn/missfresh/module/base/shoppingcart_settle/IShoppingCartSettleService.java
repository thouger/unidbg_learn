package cn.missfresh.module.base.shoppingcart_settle;

import androidx.lifecycle.LiveData;
import cn.missfresh.module.base.shoppingcart_settle.bean.BalanceArea;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface IShoppingCartSettleService extends IProvider {
    LiveData<BalanceArea> a();

    void a(String str);

    void a(String str, String str2, int i);

    LiveData<Integer> b();

    void c();
}
