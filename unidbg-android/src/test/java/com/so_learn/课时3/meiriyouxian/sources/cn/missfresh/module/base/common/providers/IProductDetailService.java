package cn.missfresh.module.base.common.providers;

import android.content.Context;
import cn.missfresh.module.base.bean.ProductInfoBean;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface IProductDetailService extends IProvider {

    public interface a {
        void d();

        void e();

        boolean f();
    }

    void commentAddShoppingCart(ProductInfoBean productInfoBean, b bVar);

    void commentSubscribe(String str, c cVar);

    void routerProductDetail(String str, String str2, String str3, long j, String str4);

    void setShareViewData(ProductInfoBean productInfoBean);

    void showProductShareDialog(Context context, String str, a aVar);

    void unRegisterProductShareListener();
}
