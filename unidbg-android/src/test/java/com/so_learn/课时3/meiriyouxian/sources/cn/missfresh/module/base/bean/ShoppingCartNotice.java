package cn.missfresh.module.base.bean;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;

@Deprecated
public class ShoppingCartNotice {
    public int productCount;
    public int productPrice;
    public int productVipPrice;

    public ShoppingCartNotice() {
    }

    public ShoppingCartNotice(int i, int i2, int i3) {
        this.productCount = i;
        this.productPrice = i2;
        this.productVipPrice = i3;
    }

    public int getProductCount() {
        return this.productCount;
    }

    public void setProductCount(int i) {
        this.productCount = i;
    }

    public int getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(int i) {
        this.productPrice = i;
    }

    public String toString() {
        AppMethodBeat.i(7698, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(7698);
        return jSONString;
    }
}
