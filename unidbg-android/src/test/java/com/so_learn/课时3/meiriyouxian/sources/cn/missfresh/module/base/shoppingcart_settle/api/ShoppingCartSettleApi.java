package cn.missfresh.module.base.shoppingcart_settle.api;

import cn.missfresh.lib.a.a;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.shoppingcart_settle.bean.BalanceArea;
import io.reactivex.q;
import java.util.Map;
import retrofit2.a.o;

public interface ShoppingCartSettleApi {
    @o(a = "as/portal/cart/settle")
    @a(a = "data")
    q<BalanceArea> requestShoppingCartSettle(@retrofit2.a.a RequestParam<Map> requestParam);
}
