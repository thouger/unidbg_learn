package cn.missfresh.module.base.common.interfaces;

import cn.missfresh.module.base.bean.ProductsEntity;

/* compiled from: IBuy */
public interface c extends m {

    /* compiled from: IBuy */
    public interface a {
        void a();

        void c(int i);

        void e(int i);
    }

    void a(int i);

    void setBuyViewClickedListener(a aVar);

    void setProduct(ProductsEntity productsEntity);

    void setShowCount(boolean z);
}
