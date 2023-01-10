package cn.missfresh.module.base.network.api;

import cn.missfresh.lib.a.a;
import cn.missfresh.module.base.bean.AutoPayInfo;
import cn.missfresh.module.base.bean.CloseAutoPay;
import cn.missfresh.module.base.bean.MsgActionListBean;
import cn.missfresh.module.base.bean.ProductDetail;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.common.dialog.bean.CouponExchangeBean;
import cn.missfresh.module.base.network.bean.TokenResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.reactivex.q;
import java.util.Map;
import retrofit2.a.f;
import retrofit2.a.o;
import retrofit2.a.t;

public interface MFApi {
    @o(a = "/v1/customer/voucher/exchange/product")
    @a(a = "msg")
    q<String> exchangeCoupon(@retrofit2.a.a RequestParam<CouponExchangeBean> requestParam);

    @o(a = "/as/home/message/getMessageInfo")
    @a(a = "data")
    q<MsgActionListBean> getActionMsgListForNet(@retrofit2.a.a Map<String, Object> map);

    @f(a = "v1/customer/address/list")
    @a(a = "data")
    q<Object> getAddressList();

    @f(a = "/as/member/autoPayInfo")
    q<AutoPayInfo> getAutoPayInfo();

    @f(a = "/as/member/closeAutoPay")
    q<CloseAutoPay> getCloseAutoPay();

    @o(a = "/as/item/getReSkus")
    @a(a = "data")
    q<ProductsEntity> getCouponExchangeList(@retrofit2.a.a Map<String, String> map);

    @f(a = "/as/disc/user/subscribe")
    @a(c = "msg")
    q<String> getFindUserAttent(@t(a = "authorId") String str, @t(a = "toSubscribe") String str2);

    @f(a = "/as/disc/article/collection")
    @a(c = "msg")
    q<String> getFindUserCollect(@t(a = "articleId") String str, @t(a = "toCollect") String str2);

    @o(a = "/v1/customer/godVoucher/send")
    @a(a = "data")
    q<String> getNewRedpackage(@retrofit2.a.a Map<String, Object> map);

    @o(a = "/as/item/productDetail")
    @a(a = "data")
    q<ProductDetail> getProductDetail(@retrofit2.a.a JSON json);

    @o(a = "/as/disc/comment/add")
    @a(a = "data")
    q<String> pushFindComment(@retrofit2.a.a Map<String, String> map);

    @o(a = "/as/disc/article/share/count")
    q<String> statisticShare(@retrofit2.a.a Map<String, String> map);

    @o(a = "refund/upload_token")
    q<TokenResult> uploadToken(@retrofit2.a.a JSONObject jSONObject);
}
