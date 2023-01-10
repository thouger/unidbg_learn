package cn.missfresh.module.base.bean;

import java.util.List;

public class ShoppingCartGift {
    private List<BannerEntity> full_img_list;
    private int is_enough;
    private int non_is_enough;
    private String p_goto;
    private String p_tag;
    private String p_title;
    private List<ProductGift> productGifts;
    private int show_type;
    private String vip_p_title;

    public String getP_title() {
        return this.p_title;
    }

    public String getVip_p_title() {
        return this.vip_p_title;
    }

    public void setVip_p_title(String str) {
        this.vip_p_title = str;
    }

    public void setP_title(String str) {
        this.p_title = str;
    }

    public List<ProductGift> getProductGifts() {
        return this.productGifts;
    }

    public <T extends ProductGift> void setProductGifts(List<T> list) {
        this.productGifts = list;
    }

    public String getP_tag() {
        return this.p_tag;
    }

    public void setP_tag(String str) {
        this.p_tag = str;
    }

    public String getP_goto() {
        return this.p_goto;
    }

    public void setP_goto(String str) {
        this.p_goto = str;
    }

    public int getShow_type() {
        return this.show_type;
    }

    public void setShow_type(int i) {
        this.show_type = i;
    }

    public int getIs_enough() {
        return this.is_enough;
    }

    public void setIs_enough(int i) {
        this.is_enough = i;
    }

    public int getNon_is_enough() {
        return this.non_is_enough;
    }

    public void setNon_is_enough(int i) {
        this.non_is_enough = i;
    }

    public void setFull_img_list(List<BannerEntity> list) {
        this.full_img_list = list;
    }

    public List<BannerEntity> getFull_img_list() {
        return this.full_img_list;
    }
}
