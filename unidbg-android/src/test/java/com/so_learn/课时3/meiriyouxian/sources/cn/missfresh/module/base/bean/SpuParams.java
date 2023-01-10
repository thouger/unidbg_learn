package cn.missfresh.module.base.bean;

import cn.missfresh.module.base.bean.MissFreshProduct;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.android.internal.logging.nano.MetricsProto;
import java.io.Serializable;
import java.util.List;

public class SpuParams implements BaseSubscibe, BaseTitle, Serializable {
    private int act_end_time;
    private String algoId = "";
    private int backgroud_color;
    private List<BannerEntity> banner;
    public int bottom_margin_height;
    private String brandTag = "";
    private int buy_permission;
    private String cart_btn_name;
    private String cart_image;
    private String code;
    private int count;
    private Discount discount;
    private String discount_desc;
    private boolean emptySpuInfo = true;
    private int font_color;
    private int gray_switch;
    private String group_img;
    private String image;
    private boolean isSubscibe;
    private String left_image;
    private boolean mIsEnd;
    private boolean mIsHead;
    private int mPosInProductList;
    @JSONField(serialize = false)
    public SecondCategory mSecondCategory;
    private int market_price;
    private String name;
    private int nationwide;
    private PricePro non_vip_price_pro;
    private int ordering;
    private String permission_msg;
    private int positionInRecmooend;
    private int preItemBackgroundColor;
    private int price;
    private PricePro pricePro;
    private String productTag = "";
    private String productUrl;
    private int product_chrome;
    private String product_group_name;
    private String product_tag_name;
    private List<MissFreshProduct.ProductTag> product_tags;
    private String promote_tag;
    private String promote_tag_new;
    private List<PromotionDeliveryTag> promotionDeliveryTag;
    private PromotionTag promotionTag;
    private List<PromotionTag> promotionTags;
    private int quantity;
    private String requestId;
    private String right_image;
    private int sales_num;
    private String seckill_bgimg_url;
    private int seckill_limit;
    private String second_title;
    private boolean sell_out;
    private String sku;
    private String skuCategory;
    private String skuProp;
    private SpuInfoBean spuInfo;
    private int stock;
    private String subtitle;
    private String tags;
    private String themeTag = "";
    private String type;
    private String unit;
    private VipTag vipTag;
    private int vip_exclusive;
    private int vip_price;
    private PricePro vip_price_pro;
    private boolean vip_product;

    public interface NationWideProduct {
        public static final int SAME_DAY_DELIVERY = 8;
    }

    public List<SpuParams> getProducts() {
        return null;
    }

    public boolean isAd() {
        return false;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public List<PromotionDeliveryTag> getPromotionDeliveryTag() {
        return this.promotionDeliveryTag;
    }

    public void setPromotionDeliveryTag(List<PromotionDeliveryTag> list) {
        this.promotionDeliveryTag = list;
    }

    public String getThemeTag() {
        return this.themeTag;
    }

    public void setThemeTag(String str) {
        this.themeTag = str;
    }

    public String getProductTag() {
        return this.productTag;
    }

    public void setProductTag(String str) {
        this.productTag = str;
    }

    public String getBrandTag() {
        return this.brandTag;
    }

    public void setBrandTag(String str) {
        this.brandTag = str;
    }

    public int getSales_num() {
        return this.sales_num;
    }

    public void setSales_num(int i) {
        this.sales_num = i;
    }

    public String getLeft_image() {
        return this.left_image;
    }

    public void setLeft_image(String str) {
        this.left_image = str;
    }

    public String getRight_image() {
        return this.right_image;
    }

    public void setRight_image(String str) {
        this.right_image = str;
    }

    public int getFont_color() {
        return this.font_color;
    }

    public String getSkuCategory() {
        return this.skuCategory;
    }

    public void setSkuCategory(String str) {
        this.skuCategory = str;
    }

    public void setFont_color(int i) {
        this.font_color = i;
    }

    public boolean isSubscibe() {
        return this.isSubscibe;
    }

    public void setSubscibe(boolean z) {
        this.isSubscibe = z;
    }

    public String getPermission_msg() {
        return this.permission_msg;
    }

    public void setPermission_msg(String str) {
        this.permission_msg = str;
    }

    public int getBuy_permission() {
        return this.buy_permission;
    }

    public void setBuy_permission(int i) {
        this.buy_permission = i;
    }

    public List<BannerEntity> getBanner() {
        return this.banner;
    }

    public void setBanner(List<BannerEntity> list) {
        this.banner = list;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public boolean isPromotionProduct() {
        return this.price != this.vip_price;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String str) {
        this.sku = str;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getProduct_tag_name() {
        return this.product_tag_name;
    }

    public void setProduct_tag_name(String str) {
        this.product_tag_name = str;
    }

    public String getCart_image() {
        return this.cart_image;
    }

    public void setCart_image(String str) {
        this.cart_image = str;
    }

    public String getProduct_group_name() {
        return this.product_group_name;
    }

    public void setProduct_group_name(String str) {
        this.product_group_name = str;
    }

    public int getOrdering() {
        return this.ordering;
    }

    public void setOrdering(int i) {
        this.ordering = i;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public int getProduct_chrome() {
        return this.product_chrome;
    }

    public void setProduct_chrome(int i) {
        this.product_chrome = i;
    }

    public int getMarket_price() {
        return this.market_price;
    }

    public void setMarket_price(int i) {
        this.market_price = i;
    }

    public boolean getSell_out() {
        return this.sell_out;
    }

    public void setSell_out(boolean z) {
        this.sell_out = z;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public int getProductLimit() {
        AppMethodBeat.i(9224, false);
        if (!this.vip_product) {
            int i = this.stock;
            AppMethodBeat.o(9224);
            return i;
        }
        int seckill_limit = getSeckill_limit();
        AppMethodBeat.o(9224);
        return seckill_limit;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int i) {
        this.stock = i;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public int getVip_price() {
        return this.vip_price;
    }

    public void setVip_price(int i) {
        this.vip_price = i;
    }

    public boolean getVip_product() {
        return this.vip_product;
    }

    public void setVip_product(boolean z) {
        this.vip_product = z;
    }

    public int getSeckill_limit() {
        int i = this.seckill_limit;
        int i2 = this.stock;
        return i > i2 ? i2 : i;
    }

    public void setSeckill_limit(int i) {
        this.seckill_limit = i;
    }

    public boolean isNationwide() {
        return this.nationwide == 1;
    }

    public int getNationwide() {
        return this.nationwide;
    }

    public void setNationwide(int i) {
        this.nationwide = i;
    }

    @Override // java.lang.Object
    public String toString() {
        return this.sku;
    }

    public String toJson() {
        AppMethodBeat.i(9268, false);
        String jSONString = JSONObject.toJSONString(this);
        AppMethodBeat.o(9268);
        return jSONString;
    }

    @Override // java.lang.Object
    public int hashCode() {
        int i = 0;
        AppMethodBeat.i(9271, false);
        String str = this.sku;
        if (str != null) {
            i = str.hashCode();
        }
        int i2 = MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE + i;
        AppMethodBeat.o(9271);
        return i2;
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        AppMethodBeat.i(9274, false);
        if (obj != null) {
            boolean equals = this.sku.equals(obj.toString());
            AppMethodBeat.o(9274);
            return equals;
        }
        AppMethodBeat.o(9274);
        return false;
    }

    public int getAct_end_time() {
        return this.act_end_time;
    }

    public void setAct_end_time(int i) {
        this.act_end_time = i;
    }

    public boolean isActEventValid() {
        return this.act_end_time > 0;
    }

    public String getSecond_title() {
        return this.second_title;
    }

    public void setSecond_title(String str) {
        this.second_title = str;
    }

    public PricePro getVip_price_pro() {
        return this.vip_price_pro;
    }

    public void setVip_price_pro(PricePro pricePro) {
        this.vip_price_pro = pricePro;
    }

    public PricePro getNon_vip_price_pro() {
        return this.non_vip_price_pro;
    }

    public void setNon_vip_price_pro(PricePro pricePro) {
        this.non_vip_price_pro = pricePro;
    }

    public String getGroup_img() {
        return this.group_img;
    }

    public void setGroup_img(String str) {
        this.group_img = str;
    }

    public List<MissFreshProduct.ProductTag> getProduct_tags() {
        return this.product_tags;
    }

    public void setProduct_tags(List<MissFreshProduct.ProductTag> list) {
        this.product_tags = list;
    }

    public String getCart_btn_name() {
        return this.cart_btn_name;
    }

    public void setCart_btn_name(String str) {
        this.cart_btn_name = str;
    }

    public String getPromote_tag() {
        return this.promote_tag;
    }

    public void setPromote_tag(String str) {
        this.promote_tag = str;
    }

    public boolean isHead() {
        return this.mIsHead;
    }

    public boolean isEnd() {
        return this.mIsEnd;
    }

    public void setIsHead() {
        this.mIsHead = true;
    }

    public void setIsEnd() {
        this.mIsEnd = true;
    }

    public int getPreItemBackgroundColor() {
        return this.preItemBackgroundColor;
    }

    public void setPreItemBackgroundColor(int i) {
        this.preItemBackgroundColor = i;
    }

    public int getBackgroud_color() {
        return this.backgroud_color;
    }

    public void setBackgroud_color(int i) {
        this.backgroud_color = i;
    }

    public int getGray_switch() {
        return this.gray_switch;
    }

    public void setGray_switch(int i) {
        this.gray_switch = i;
    }

    public boolean isDividerVisible() {
        return this.gray_switch == 0;
    }

    public SecondCategory getSecondCategory() {
        return this.mSecondCategory;
    }

    public void setSecondCategory(SecondCategory secondCategory) {
        this.mSecondCategory = secondCategory;
    }

    public String getSeckill_bgimg_url() {
        return this.seckill_bgimg_url;
    }

    public void setSeckill_bgimg_url(String str) {
        this.seckill_bgimg_url = str;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String str) {
        this.tags = str;
    }

    public int getVip_exclusive() {
        return this.vip_exclusive;
    }

    public void setVip_exclusive(int i) {
        this.vip_exclusive = i;
    }

    public String getPromote_tag_new() {
        return this.promote_tag_new;
    }

    public void setPromote_tag_new(String str) {
        this.promote_tag_new = str;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public String getDiscount_desc() {
        return this.discount_desc;
    }

    public void setDiscount_desc(String str) {
        this.discount_desc = str;
    }

    public PricePro getOldPricePro() {
        AppMethodBeat.i(9388, false);
        PricePro vip_price_pro = g.a().b() ? getVip_price_pro() : getNon_vip_price_pro();
        AppMethodBeat.o(9388);
        return vip_price_pro;
    }

    public int getPosInProductList() {
        return this.mPosInProductList;
    }

    public void setPosInProductList(int i) {
        this.mPosInProductList = i;
    }

    public PricePro getPricePro() {
        return this.pricePro;
    }

    public void setPricePro(PricePro pricePro) {
        this.pricePro = pricePro;
    }

    public PromotionTag getPromotionTag() {
        return this.promotionTag;
    }

    public void setPromotionTag(PromotionTag promotionTag) {
        this.promotionTag = promotionTag;
    }

    public List<PromotionTag> getPromotionTags() {
        return this.promotionTags;
    }

    public void setPromotionTags(List<PromotionTag> list) {
        this.promotionTags = list;
    }

    public VipTag getVipTag() {
        return this.vipTag;
    }

    public void setVipTag(VipTag vipTag) {
        this.vipTag = vipTag;
    }

    public Discount getDiscount() {
        return this.discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public String getProductUrl() {
        return this.productUrl;
    }

    public void setProductUrl(String str) {
        this.productUrl = str;
    }

    public boolean isPin() {
        AppMethodBeat.i(9424, false);
        boolean z = !b.a(this.productUrl);
        AppMethodBeat.o(9424);
        return z;
    }

    public boolean isSpu() {
        return !this.emptySpuInfo;
    }

    public boolean isEmptySpuInfo() {
        return this.emptySpuInfo;
    }

    public void setEmptySpuInfo(boolean z) {
        this.emptySpuInfo = z;
    }

    public String getSkuProp() {
        return this.skuProp;
    }

    public void setSkuProp(String str) {
        this.skuProp = str;
    }

    public SpuInfoBean getSpuInfo() {
        return this.spuInfo;
    }

    public void setSpuInfo(SpuInfoBean spuInfoBean) {
        this.spuInfo = spuInfoBean;
    }

    public int getPositionInRecmooend() {
        return this.positionInRecmooend;
    }

    public void setPositionInRecmooend(int i) {
        this.positionInRecmooend = i;
    }

    public static class SpuInfoBean {
        private List<PropListBean> propList;
        private List<ServiceListBean> serviceList;
        private List<SkuInfoBean> skuInfo;
        private long spuId;

        public long getSpuId() {
            return this.spuId;
        }

        public void setSpuId(long j) {
            this.spuId = j;
        }

        public List<PropListBean> getPropList() {
            return this.propList;
        }

        public void setPropList(List<PropListBean> list) {
            this.propList = list;
        }

        public List<ServiceListBean> getServiceList() {
            return this.serviceList;
        }

        public void setServiceList(List<ServiceListBean> list) {
            this.serviceList = list;
        }

        public List<SkuInfoBean> getSkuInfo() {
            return this.skuInfo;
        }

        public void setSkuInfo(List<SkuInfoBean> list) {
            this.skuInfo = list;
        }

        public static class PropListBean {
            private String propKey;
            private String propName;
            private List<SubListBean> subList;

            public String getPropKey() {
                return this.propKey;
            }

            public void setPropKey(String str) {
                this.propKey = str;
            }

            public String getPropName() {
                return this.propName;
            }

            public void setPropName(String str) {
                this.propName = str;
            }

            public List<SubListBean> getSubList() {
                return this.subList;
            }

            public void setSubList(List<SubListBean> list) {
                this.subList = list;
            }

            public static class SubListBean {
                private int key;
                private String value;

                public int getKey() {
                    return this.key;
                }

                public void setKey(int i) {
                    this.key = i;
                }

                public String getValue() {
                    return this.value;
                }

                public void setValue(String str) {
                    this.value = str;
                }
            }
        }

        public static class ServiceListBean {
            private String propKey;
            private String propName;
            private List<SubListBeanX> subList;

            public String getPropKey() {
                return this.propKey;
            }

            public void setPropKey(String str) {
                this.propKey = str;
            }

            public String getPropName() {
                return this.propName;
            }

            public void setPropName(String str) {
                this.propName = str;
            }

            public List<SubListBeanX> getSubList() {
                return this.subList;
            }

            public void setSubList(List<SubListBeanX> list) {
                this.subList = list;
            }

            public static class SubListBeanX {
                private int moneyCost;
                private int serviceId;
                private String serviceName;
                private int status;
                private int timeCost;

                public int getStatus() {
                    return this.status;
                }

                public void setStatus(int i) {
                    this.status = i;
                }

                public int getMoneyCost() {
                    return this.moneyCost;
                }

                public void setMoneyCost(int i) {
                    this.moneyCost = i;
                }

                public int getServiceId() {
                    return this.serviceId;
                }

                public void setServiceId(int i) {
                    this.serviceId = i;
                }

                public String getServiceName() {
                    return this.serviceName;
                }

                public void setServiceName(String str) {
                    this.serviceName = str;
                }

                public int getTimeCost() {
                    return this.timeCost;
                }

                public void setTimeCost(int i) {
                    this.timeCost = i;
                }
            }
        }

        public static class SkuInfoBean {
            private String cart_btn_name;
            private String cart_image;
            private String image;
            private String name;
            private PricePro pricePro;
            private PromotionTagBean promotionTag;
            private List<PromotionTagsBean> promotionTags;
            private int seckill_limit;
            private boolean sell_out;
            private String sku;
            private String skuCategory;
            private String skuProp;
            private int stock;
            private String subtitle;
            private VipTag vipTag;
            private int vip_product;

            public String getSkuCategory() {
                return this.skuCategory;
            }

            public void setSkuCategory(String str) {
                this.skuCategory = str;
            }

            public VipTag getVipTag() {
                return this.vipTag;
            }

            public void setVipTag(VipTag vipTag) {
                this.vipTag = vipTag;
            }

            public String getImage() {
                return this.image;
            }

            public void setImage(String str) {
                this.image = str;
            }

            public String getCart_btn_name() {
                return this.cart_btn_name;
            }

            public void setCart_btn_name(String str) {
                this.cart_btn_name = str;
            }

            public boolean isSell_out() {
                return this.sell_out;
            }

            public void setSell_out(boolean z) {
                this.sell_out = z;
            }

            public PromotionTagBean getPromotionTag() {
                return this.promotionTag;
            }

            public void setPromotionTag(PromotionTagBean promotionTagBean) {
                this.promotionTag = promotionTagBean;
            }

            public int getSeckill_limit() {
                return this.seckill_limit;
            }

            public void setSeckill_limit(int i) {
                this.seckill_limit = i;
            }

            public String getSubtitle() {
                return this.subtitle;
            }

            public void setSubtitle(String str) {
                this.subtitle = str;
            }

            public String getCart_image() {
                return this.cart_image;
            }

            public void setCart_image(String str) {
                this.cart_image = str;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public int getVip_product() {
                return this.vip_product;
            }

            public void setVip_product(int i) {
                this.vip_product = i;
            }

            public String getSku() {
                return this.sku;
            }

            public void setSku(String str) {
                this.sku = str;
            }

            public String getSkuProp() {
                return this.skuProp;
            }

            public void setSkuProp(String str) {
                this.skuProp = str;
            }

            public int getStock() {
                return this.stock;
            }

            public void setStock(int i) {
                this.stock = i;
            }

            public PricePro getPricePro() {
                return this.pricePro;
            }

            public void setPricePro(PricePro pricePro) {
                this.pricePro = pricePro;
            }

            public List<PromotionTagsBean> getPromotionTags() {
                return this.promotionTags;
            }

            public void setPromotionTags(List<PromotionTagsBean> list) {
                this.promotionTags = list;
            }

            public static class PromotionTagBean {
                private int endColor;
                private String name;
                private int nameColor;
                private int startColor;

                public int getEndColor() {
                    return this.endColor;
                }

                public void setEndColor(int i) {
                    this.endColor = i;
                }

                public int getNameColor() {
                    return this.nameColor;
                }

                public void setNameColor(int i) {
                    this.nameColor = i;
                }

                public String getName() {
                    return this.name;
                }

                public void setName(String str) {
                    this.name = str;
                }

                public int getStartColor() {
                    return this.startColor;
                }

                public void setStartColor(int i) {
                    this.startColor = i;
                }
            }

            public static class PromotionTagsBean {
                private int bgColor;
                private int borderColor;
                private String name;
                private int nameColor;

                public int getBorderColor() {
                    return this.borderColor;
                }

                public void setBorderColor(int i) {
                    this.borderColor = i;
                }

                public int getBgColor() {
                    return this.bgColor;
                }

                public void setBgColor(int i) {
                    this.bgColor = i;
                }

                public int getNameColor() {
                    return this.nameColor;
                }

                public void setNameColor(int i) {
                    this.nameColor = i;
                }

                public String getName() {
                    return this.name;
                }

                public void setName(String str) {
                    this.name = str;
                }
            }
        }
    }

    public String getAlgoId() {
        return this.algoId;
    }

    public void setAlgoId(String str) {
        this.algoId = str;
    }
}
