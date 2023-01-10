package cn.missfresh.module.base.payment.recharge.api;

import cn.missfresh.module.base.bean.PayInfo;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.payment.recharge.bean.RechargStatusBean;
import cn.missfresh.module.base.payment.recharge.bean.StoreCardBean;
import cn.missfresh.module.base.payment.recharge.bean.StoreCreateOrderBean;
import io.reactivex.q;
import java.util.List;
import java.util.Map;
import retrofit2.a.a;
import retrofit2.a.o;

public interface RechargApi {
    @o(a = RechargApiConst.CHECK_PHHONE)
    q<String> checkPhone(@a RequestParam<Map> requestParam);

    @o(a = RechargApiConst.RECHARG_STATUS)
    @cn.missfresh.lib.a.a(a = "data")
    q<RechargStatusBean> checkRechargStatus(@a RequestParam<RechargRequestParam> requestParam);

    @o(a = "rechargecard/order/create")
    @cn.missfresh.lib.a.a(a = "data")
    q<StoreCreateOrderBean> requestCreateOrder(@a RequestParam<Map> requestParam);

    @o(a = "ark/prepay")
    @cn.missfresh.lib.a.a(a = "data")
    q<PayInfo> requestOrderPrePay(@a RequestParam<OrderPrePayRequestBean> requestParam);

    @o(a = "rechargecard/list")
    @cn.missfresh.lib.a.a(a = "data")
    q<List<StoreCardBean>> requestStoreCards();
}
