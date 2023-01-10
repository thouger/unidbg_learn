package cn.missfresh.module.base.common.dialog.bean;

import cn.missfresh.module.base.bean.ProductsEntity;

public class CouponProduct {
    public boolean isSelected = false;
    public ProductsEntity productsEntity;

    public CouponProduct(ProductsEntity productsEntity, boolean z) {
        this.productsEntity = productsEntity;
        this.isSelected = z;
    }
}
