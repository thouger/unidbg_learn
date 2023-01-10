package cn.missfresh.module.base.bean;

import com.lidroid.xutils.db.annotation.Table;

@Table(name = "buycar_inactive_new")
public class ShoppingCartInActive extends ShoppingCart {
    public ShoppingCartInActive() {
        this.isActiveItem = false;
        this.isChecked = false;
    }
}
