package cn.missfresh.module.base.bean;

public class ProductEntityStatistic {
    private String last_page;
    private String promotion_id;

    public String getPromotion_id() {
        return this.promotion_id;
    }

    public void setPromotion_id(String str) {
        this.promotion_id = str;
    }

    public String getLast_page() {
        return this.last_page;
    }

    public void setLast_page(String str) {
        this.last_page = str;
    }
}
