package cn.missfresh.module.base.bean;

import com.lidroid.xutils.db.annotation.Table;

@Table(name = "buycar_active_new")
public class ShoppingCartActive extends ShoppingCart {
    public ShoppingCartActive() {
        this.isActiveItem = true;
        this.isChecked = true;
    }
}
