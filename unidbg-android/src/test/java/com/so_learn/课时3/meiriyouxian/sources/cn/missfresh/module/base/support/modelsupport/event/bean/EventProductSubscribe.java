package cn.missfresh.module.base.support.modelsupport.event.bean;

public class EventProductSubscribe {
    private String productSku;

    public String getProductSku() {
        return this.productSku;
    }

    public EventProductSubscribe(String str) {
        this.productSku = str;
    }
}
