package cn.missfresh.module.base.tying;

import cn.missfresh.module.base.common.livedata.MFStickyLiveData;
import cn.missfresh.module.base.tying.bean.TyingProductsBean;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface ITyingProductService extends IProvider {
    void a();

    void a(String str);

    void a(String str, String str2, int i, int i2, int i3, String str3);

    void b();

    MFStickyLiveData<TyingProductsBean> c();
}
