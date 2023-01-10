package cn.missfresh.module.base.bean;

import cn.missfresh.module.base.bean.MissFreshProduct;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class RecommendProduct extends ProductsEntity {
    private List<ProductsEntity> products;
    private TitleInfo title_info;

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public int getBuy_permission() {
        return 0;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getCart_btn_name() {
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public int getCount() {
        return 0;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getImage() {
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public int getMarket_price() {
        return 0;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public int getNationwide() {
        return 0;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public PricePro getNon_vip_price_pro() {
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public int getOrdering() {
        return 0;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getPermission_msg() {
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public int getPrice() {
        return 0;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public int getProductLimit() {
        return 0;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public int getProduct_chrome() {
        return 0;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getProduct_group_name() {
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getProduct_tag_name() {
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public List<MissFreshProduct.ProductTag> getProduct_tags() {
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getPromote_tag() {
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public int getSeckill_limit() {
        return 0;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public boolean getSell_out() {
        return false;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getSku() {
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public int getStock() {
        return 0;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getSubtitle() {
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getUnit() {
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public int getVip_price() {
        return 0;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public PricePro getVip_price_pro() {
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public boolean getVip_product() {
        return false;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public boolean isActEventValid() {
        return false;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public boolean isNationwide() {
        return false;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public boolean isPromotionProduct() {
        return false;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public boolean isSubscibe() {
        return false;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setBuy_permission(int i) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setCart_btn_name(String str) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setCode(String str) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setCount(int i) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setImage(String str) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setMarket_price(int i) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setNationwide(int i) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setNon_vip_price_pro(PricePro pricePro) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setOrdering(int i) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setPermission_msg(String str) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setPrice(int i) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setProduct_chrome(int i) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setProduct_group_name(String str) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setProduct_tag_name(String str) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setProduct_tags(List<MissFreshProduct.ProductTag> list) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setSeckill_limit(int i) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setSell_out(boolean z) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setSku(String str) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setStock(int i) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setSubscibe(boolean z) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setSubtitle(String str) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setUnit(String str) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setVip_price(int i) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setVip_price_pro(PricePro pricePro) {
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setVip_product(boolean z) {
    }

    public TitleInfo getTitle_info() {
        return this.title_info;
    }

    public void setTitle_info(TitleInfo titleInfo) {
        this.title_info = titleInfo;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getLeft_image() {
        AppMethodBeat.i(6687, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            String left_image = titleInfo.getLeft_image();
            AppMethodBeat.o(6687);
            return left_image;
        }
        AppMethodBeat.o(6687);
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setLeft_image(String str) {
        AppMethodBeat.i(6689, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            titleInfo.setLeft_image(str);
        }
        AppMethodBeat.o(6689);
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getRight_image() {
        AppMethodBeat.i(6692, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            String right_image = titleInfo.getRight_image();
            AppMethodBeat.o(6692);
            return right_image;
        }
        AppMethodBeat.o(6692);
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setRight_image(String str) {
        AppMethodBeat.i(6694, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            titleInfo.setRight_image(str);
        }
        AppMethodBeat.o(6694);
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public int getFont_color() {
        AppMethodBeat.i(6695, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            int font_color = titleInfo.getFont_color();
            AppMethodBeat.o(6695);
            return font_color;
        }
        AppMethodBeat.o(6695);
        return 0;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setFont_color(int i) {
        AppMethodBeat.i(6698, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            titleInfo.setFont_color(i);
        }
        AppMethodBeat.o(6698);
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getType() {
        AppMethodBeat.i(6708, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            String type = titleInfo.getType();
            AppMethodBeat.o(6708);
            return type;
        }
        AppMethodBeat.o(6708);
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setType(String str) {
        AppMethodBeat.i(6711, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            titleInfo.setType(str);
        }
        AppMethodBeat.o(6711);
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public List<BannerEntity> getBanner() {
        AppMethodBeat.i(6714, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            List<BannerEntity> banner = titleInfo.getBanner();
            AppMethodBeat.o(6714);
            return banner;
        }
        AppMethodBeat.o(6714);
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setBanner(List<BannerEntity> list) {
        AppMethodBeat.i(6716, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            titleInfo.setBanner(list);
        }
        AppMethodBeat.o(6716);
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setSeckill_bgimg_url(String str) {
        AppMethodBeat.i(6719, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            titleInfo.setSeckill_bgimg_url(str);
        }
        AppMethodBeat.o(6719);
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getSeckill_bgimg_url() {
        AppMethodBeat.i(6722, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            String seckill_bgimg_url = titleInfo.getSeckill_bgimg_url();
            AppMethodBeat.o(6722);
            return seckill_bgimg_url;
        }
        AppMethodBeat.o(6722);
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getName() {
        AppMethodBeat.i(6733, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            String name = titleInfo.getName();
            AppMethodBeat.o(6733);
            return name;
        }
        AppMethodBeat.o(6733);
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setName(String str) {
        AppMethodBeat.i(6736, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            titleInfo.setName(str);
        }
        AppMethodBeat.o(6736);
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getCode() {
        AppMethodBeat.i(6774, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            titleInfo.getCode();
        }
        AppMethodBeat.o(6774);
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public int getAct_end_time() {
        AppMethodBeat.i(6791, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            int act_end_time = titleInfo.getAct_end_time();
            AppMethodBeat.o(6791);
            return act_end_time;
        }
        AppMethodBeat.o(6791);
        return 0;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setAct_end_time(int i) {
        AppMethodBeat.i(6796, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            titleInfo.setAct_end_time(i);
        }
        AppMethodBeat.o(6796);
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getSecond_title() {
        AppMethodBeat.i(6800, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            String second_title = titleInfo.getSecond_title();
            AppMethodBeat.o(6800);
            return second_title;
        }
        AppMethodBeat.o(6800);
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setSecond_title(String str) {
        AppMethodBeat.i(6801, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            titleInfo.setSecond_title(str);
        }
        AppMethodBeat.o(6801);
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public String getGroup_img() {
        AppMethodBeat.i(6810, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            String group_img = titleInfo.getGroup_img();
            AppMethodBeat.o(6810);
            return group_img;
        }
        AppMethodBeat.o(6810);
        return null;
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public void setGroup_img(String str) {
        AppMethodBeat.i(6812, false);
        TitleInfo titleInfo = this.title_info;
        if (titleInfo != null) {
            titleInfo.setGroup_img(str);
        }
        AppMethodBeat.o(6812);
    }

    @Override // cn.missfresh.module.base.bean.ProductsEntity
    public List<ProductsEntity> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductsEntity> list) {
        this.products = list;
    }
}
