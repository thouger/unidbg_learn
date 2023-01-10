package cn.missfresh.module.base.bean;

import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;

public class ProductDetail implements BaseSubscibe {
    public BannerEntity adInfo;
    private String brand;
    private int buy_permission;
    private String buyerDescription;
    private String buyerName;
    private String buyerUrl;
    private String cart_btn_name;
    private String cart_image;
    private String country;
    private int delivery_mode;
    private String delivery_mode_name;
    private List<String> description;
    private Discount discountDetail;
    private ExcgangeIntegralBean excgange_integral;
    private int exchange_locked;
    private String exchange_locked_text;
    private String exchange_out_text;
    private String exchange_text;
    private FightGroupInfo fightGroupInfo;
    private List<String> images;
    private List<InstructionEntity> instruction;
    private int isNewItemShare;
    private boolean isSubscibe;
    private int is_exchange_product;
    private int is_present;
    private boolean is_vip;
    private int market_price;
    private String material;
    private int member_level;
    private String name;
    private int nationwide;
    private NewItemShareInfo newItemShareInfo;
    private PricePro non_vip_price_pro;
    private String pack;
    private String permission_msg;
    private String positiveRatio;
    private int price;
    private PricePro pricePro;
    private List<ProductFingerprintsBean> productFingerprints;
    private String product_detail_share_img;
    private String product_integral_type;
    private String product_share_icon;
    private ProductShareInfoV2 product_share_info_v2;
    private String product_share_text;
    private String promote_tag;
    private String promote_tag_new;
    private List<String> promotion;
    private List<PromotionShow> promotionShow;
    private List<PromotionTag> promotionTags;
    private List<QAInfo> qa_info;
    private int quantity;
    private String safe_url;
    private String sales_volume;
    private SecKillProductInfo secKillProductInfo;
    private int seckill_limit;
    private String securityText;
    private String securityTitle;
    public ShareInfo share_info;
    private SimilarData similarData;
    private String sku;
    private String skuCategory;
    private String skuProp;
    private ProductsEntity.SpuInfoBean spuInfo;
    private int stock;
    private String storage_code;
    private String storage_method;
    private String storage_time;
    private String subtitle;
    private String unit;
    private VipTag vipTag;
    private VIPButton vip_button;
    public VipCard vip_card;
    private int vip_exclusive;
    private int vip_price;
    private PricePro vip_price_pro;
    private boolean vip_product;
    private String weight;

    public SimilarData getSimilarData() {
        return this.similarData;
    }

    public void setSimilarData(SimilarData similarData) {
        this.similarData = similarData;
    }

    public String getBuyerName() {
        return this.buyerName;
    }

    public void setBuyerName(String str) {
        this.buyerName = str;
    }

    public String getBuyerUrl() {
        return this.buyerUrl;
    }

    public void setBuyerUrl(String str) {
        this.buyerUrl = str;
    }

    public String getBuyerDescription() {
        return this.buyerDescription;
    }

    public void setBuyerDescription(String str) {
        this.buyerDescription = str;
    }

    public String getSkuCategory() {
        return this.skuCategory;
    }

    public void setSkuCategory(String str) {
        this.skuCategory = str;
    }

    public String getProduct_share_icon() {
        return this.product_share_icon;
    }

    public void setProduct_share_icon(String str) {
        this.product_share_icon = str;
    }

    public NewItemShareInfo getNewItemShareInfo() {
        return this.newItemShareInfo;
    }

    public void setNewItemShareInfo(NewItemShareInfo newItemShareInfo) {
        this.newItemShareInfo = newItemShareInfo;
    }

    public String getPositiveRatio() {
        return this.positiveRatio;
    }

    public void setPositiveRatio(String str) {
        this.positiveRatio = str;
    }

    public int getIsNewItemShare() {
        return this.isNewItemShare;
    }

    public void setIsNewItemShare(int i) {
        this.isNewItemShare = i;
    }

    public String getProduct_integral_type() {
        return this.product_integral_type;
    }

    public int getVip_exclusive() {
        return this.vip_exclusive;
    }

    public void setVip_exclusive(int i) {
        this.vip_exclusive = i;
    }

    public String getCart_btn_name() {
        return this.cart_btn_name;
    }

    public void setCart_btn_name(String str) {
        this.cart_btn_name = str;
    }

    public int getIs_present() {
        return this.is_present;
    }

    public void setIs_present(int i) {
        this.is_present = i;
    }

    public void setProduct_integral_type(String str) {
        this.product_integral_type = str;
    }

    public String getProduct_detail_share_img() {
        return this.product_detail_share_img;
    }

    public void setProduct_detail_share_img(String str) {
        this.product_detail_share_img = str;
    }

    public String getProduct_share_text() {
        return this.product_share_text;
    }

    public void setProduct_share_text(String str) {
        this.product_share_text = str;
    }

    public String getPermission_msg() {
        return this.permission_msg;
    }

    public void setPermission_msg(String str) {
        this.permission_msg = str;
    }

    public int getMember_level() {
        return this.member_level;
    }

    public void setMember_level(int i) {
        this.member_level = i;
    }

    public int getBuy_permission() {
        return this.buy_permission;
    }

    public void setBuy_permission(int i) {
        this.buy_permission = i;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String str) {
        this.weight = str;
    }

    public int getDelivery_mode() {
        return this.delivery_mode;
    }

    public void setDelivery_mode(int i) {
        this.delivery_mode = i;
    }

    public int getNationwide() {
        return this.nationwide;
    }

    public void setNationwide(int i) {
        this.nationwide = i;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSubscibe(boolean z) {
        this.isSubscibe = z;
    }

    public boolean isSubscibe() {
        return this.isSubscibe;
    }

    public void setSku(String str) {
        this.sku = str;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public int getProductLimit() {
        AppMethodBeat.i(6261, false);
        if (!this.vip_product) {
            int i = this.stock;
            AppMethodBeat.o(6261);
            return i;
        }
        int seckill_limit = getSeckill_limit();
        AppMethodBeat.o(6261);
        return seckill_limit;
    }

    public boolean isPromotionProduct() {
        return this.price != this.vip_price;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int i) {
        this.stock = i;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String str) {
        this.material = str;
    }

    public String getStorage_code() {
        return this.storage_code;
    }

    public void setStorage_code(String str) {
        this.storage_code = str;
    }

    public int getMarket_price() {
        return this.market_price;
    }

    public void setMarket_price(int i) {
        this.market_price = i;
    }

    public String getStorage_time() {
        return this.storage_time;
    }

    public void setStorage_time(String str) {
        this.storage_time = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getStorage_method() {
        return this.storage_method;
    }

    public void setStorage_method(String str) {
        this.storage_method = str;
    }

    public String getSales_volume() {
        return this.sales_volume;
    }

    public void setSales_volume(String str) {
        this.sales_volume = str;
    }

    public String getPack() {
        return this.pack;
    }

    public void setPack(String str) {
        this.pack = str;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public List<String> getImages() {
        return this.images;
    }

    public void setImages(List<String> list) {
        this.images = list;
    }

    public List<String> getDescription() {
        return this.description;
    }

    public void setDescription(List<String> list) {
        this.description = list;
    }

    public List<InstructionEntity> getInstruction() {
        return this.instruction;
    }

    public void setInstruction(List<InstructionEntity> list) {
        this.instruction = list;
    }

    public List<String> getPromotion() {
        return this.promotion;
    }

    public void setPromotion(List<String> list) {
        this.promotion = list;
    }

    public String getCart_image() {
        return this.cart_image;
    }

    public void setCart_image(String str) {
        this.cart_image = str;
    }

    public String getDelivery_mode_name() {
        return this.delivery_mode_name;
    }

    public void setDelivery_mode_name(String str) {
        this.delivery_mode_name = str;
    }

    public boolean is_vip() {
        return this.is_vip;
    }

    public void setIs_vip(boolean z) {
        this.is_vip = z;
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

    public int getSeckill_limit() {
        int i = this.seckill_limit;
        int i2 = this.stock;
        return i > i2 ? i2 : i;
    }

    public void setSeckill_limit(int i) {
        this.seckill_limit = i;
    }

    public boolean isVip_product() {
        return this.vip_product;
    }

    public void setVip_product(boolean z) {
        this.vip_product = z;
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

    public String toString() {
        AppMethodBeat.i(6299, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(6299);
        return jSONString;
    }

    public ExcgangeIntegralBean getExcgange_integral() {
        return this.excgange_integral;
    }

    public void setExcgange_integral(ExcgangeIntegralBean excgangeIntegralBean) {
        this.excgange_integral = excgangeIntegralBean;
    }

    public int getExchange_locked() {
        return this.exchange_locked;
    }

    public void setExchange_locked(int i) {
        this.exchange_locked = i;
    }

    public String getExchange_locked_text() {
        return this.exchange_locked_text;
    }

    public void setExchange_locked_text(String str) {
        this.exchange_locked_text = str;
    }

    public String getExchange_text() {
        return this.exchange_text;
    }

    public void setExchange_text(String str) {
        this.exchange_text = str;
    }

    public String getExchange_out_text() {
        return this.exchange_out_text;
    }

    public void setExchange_out_text(String str) {
        this.exchange_out_text = str;
    }

    public int getIs_exchange_product() {
        return this.is_exchange_product;
    }

    public void setIs_exchange_product(int i) {
        this.is_exchange_product = i;
    }

    public boolean isHasInstruction() {
        boolean z = false;
        AppMethodBeat.i(6304, false);
        if (getProduct_share_info_v2() != null && getProduct_share_info_v2().getProduct_detail_is_show() == 1 && !b.a(getInstruction())) {
            z = true;
        }
        AppMethodBeat.o(6304);
        return z;
    }

    public boolean isHasSpecification() {
        boolean z = false;
        AppMethodBeat.i(6305, false);
        if (getProduct_share_info_v2() != null && getProduct_share_info_v2().getProduct_rule_is_show() == 1) {
            z = true;
        }
        AppMethodBeat.o(6305);
        return z;
    }

    public ProductShareInfoV2 getProduct_share_info_v2() {
        return this.product_share_info_v2;
    }

    public void setProduct_share_info_v2(ProductShareInfoV2 productShareInfoV2) {
        this.product_share_info_v2 = productShareInfoV2;
    }

    public String getPromote_tag() {
        return this.promote_tag;
    }

    public void setPromote_tag(String str) {
        this.promote_tag = str;
    }

    public String getSafe_url() {
        return this.safe_url;
    }

    public void setShare_info(ShareInfo shareInfo) {
        this.share_info = shareInfo;
    }

    public void setQa_info(List<QAInfo> list) {
        this.qa_info = list;
    }

    public List<QAInfo> getQa_info() {
        return this.qa_info;
    }

    public String getPromote_tag_new() {
        return this.promote_tag_new;
    }

    public void setPromote_tag_new(String str) {
        this.promote_tag_new = str;
    }

    public List<PromotionShow> getPromotionShow() {
        return this.promotionShow;
    }

    public void setPromotionShow(List<PromotionShow> list) {
        this.promotionShow = list;
    }

    public static class InstructionEntity {
        private String description;
        private String image;
        private int ordering;
        private String title;
        private int type = 0;
        private String video;

        public int getOrdering() {
            return this.ordering;
        }

        public void setOrdering(int i) {
            this.ordering = i;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String str) {
            this.image = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public String getVideo() {
            return this.video;
        }

        public void setVideo(String str) {
            this.video = str;
        }

        public String toString() {
            AppMethodBeat.i(5885, false);
            String jSONString = JSON.toJSONString(this);
            AppMethodBeat.o(5885);
            return jSONString;
        }
    }

    public static class ExcgangeIntegralBean {
        private String exchange_goods_value;
        private int exchange_goods_value_color;
        private int exchange_goods_value_strike;
        private int integral;
        private int integral_color;
        private String integral_text;
        private int integral_text_color;

        public String getExchange_goods_value() {
            return this.exchange_goods_value;
        }

        public void setExchange_goods_value(String str) {
            this.exchange_goods_value = str;
        }

        public int getExchange_goods_value_color() {
            return this.exchange_goods_value_color;
        }

        public void setExchange_goods_value_color(int i) {
            this.exchange_goods_value_color = i;
        }

        public int getExchange_goods_value_strike() {
            return this.exchange_goods_value_strike;
        }

        public void setExchange_goods_value_strike(int i) {
            this.exchange_goods_value_strike = i;
        }

        public int getIntegral() {
            return this.integral;
        }

        public void setIntegral(int i) {
            this.integral = i;
        }

        public int getIntegral_color() {
            return this.integral_color;
        }

        public void setIntegral_color(int i) {
            this.integral_color = i;
        }

        public String getIntegral_text() {
            return this.integral_text;
        }

        public void setIntegral_text(String str) {
            this.integral_text = str;
        }

        public int getIntegral_text_color() {
            return this.integral_text_color;
        }

        public void setIntegral_text_color(int i) {
            this.integral_text_color = i;
        }
    }

    public static class ProductShareInfoV2 {
        private String benefit_get_txt;
        private int benefit_status;
        private int preview_need_qrcode;
        private int preview_show;
        private String prodcut_share_bg_img;
        private int prodcut_share_bg_login_color;
        private String prodcut_share_bg_login_text;
        private String prodcut_share_bg_text;
        private int prodcut_share_bg_text_color;
        private String product_bright_spot;
        private int product_detail_is_show;
        private String product_integarl_explain;
        private String product_integarl_icon;
        private String product_integarl_text;
        private int product_rule_is_show;
        private String product_share_backup_up;
        private int product_share_need_price;
        private int qr_size;
        private int qr_x;
        private int qr_y;
        private String share_bg_sub_title;
        private String share_bg_title;
        private String share_bt_bg_img;
        private String share_product_img;
        private String share_product_text;
        private List<String> share_s_p_img;
        private String share_s_p_txt;
        private String show_share_img;
        private String user_get_coupon_tost_text;

        public String getProduct_integarl_icon() {
            return this.product_integarl_icon;
        }

        public void setProduct_integarl_icon(String str) {
            this.product_integarl_icon = str;
        }

        public String getProduct_integarl_text() {
            return this.product_integarl_text;
        }

        public void setProduct_integarl_text(String str) {
            this.product_integarl_text = str;
        }

        public String getShare_product_img() {
            return this.share_product_img;
        }

        public void setShare_product_img(String str) {
            this.share_product_img = str;
        }

        public String getShare_product_text() {
            return this.share_product_text;
        }

        public void setShare_product_text(String str) {
            this.share_product_text = str;
        }

        public String getProduct_bright_spot() {
            return this.product_bright_spot;
        }

        public void setProduct_bright_spot(String str) {
            this.product_bright_spot = str;
        }

        public String getShow_share_img() {
            return this.show_share_img;
        }

        public void setShow_share_img(String str) {
            this.show_share_img = str;
        }

        public int getProduct_rule_is_show() {
            return this.product_rule_is_show;
        }

        public void setProduct_rule_is_show(int i) {
            this.product_rule_is_show = i;
        }

        public int getProduct_detail_is_show() {
            return this.product_detail_is_show;
        }

        public void setProduct_detail_is_show(int i) {
            this.product_detail_is_show = i;
        }

        public String getProdcut_share_bg_img() {
            return this.prodcut_share_bg_img;
        }

        public void setProdcut_share_bg_img(String str) {
            this.prodcut_share_bg_img = str;
        }

        public String getProdcut_share_bg_text() {
            return this.prodcut_share_bg_text;
        }

        public void setProdcut_share_bg_text(String str) {
            this.prodcut_share_bg_text = str;
        }

        public int getProdcut_share_bg_text_color() {
            return this.prodcut_share_bg_text_color;
        }

        public void setProdcut_share_bg_text_color(int i) {
            this.prodcut_share_bg_text_color = i;
        }

        public String getProdcut_share_bg_login_text() {
            return this.prodcut_share_bg_login_text;
        }

        public void setProdcut_share_bg_login_text(String str) {
            this.prodcut_share_bg_login_text = str;
        }

        public int getProdcut_share_bg_login_color() {
            return this.prodcut_share_bg_login_color;
        }

        public void setProdcut_share_bg_login_color(int i) {
            this.prodcut_share_bg_login_color = i;
        }

        public int getProduct_share_need_price() {
            return this.product_share_need_price;
        }

        public void setProduct_share_need_price(int i) {
            this.product_share_need_price = i;
        }

        public int getPreview_need_qrcode() {
            return this.preview_need_qrcode;
        }

        public void setPreview_need_qrcode(int i) {
            this.preview_need_qrcode = i;
        }

        public String getUser_get_coupon_tost_text() {
            return this.user_get_coupon_tost_text;
        }

        public void setUser_get_coupon_tost_text(String str) {
            this.user_get_coupon_tost_text = str;
        }

        public String getProduct_integarl_explain() {
            return this.product_integarl_explain;
        }

        public void setProduct_integarl_explain(String str) {
            this.product_integarl_explain = str;
        }

        public int getPreview_show() {
            return this.preview_show;
        }

        public void setPreview_show(int i) {
            this.preview_show = i;
        }

        public String getShare_bg_title() {
            return this.share_bg_title;
        }

        public void setShare_bg_title(String str) {
            this.share_bg_title = str;
        }

        public int getQr_x() {
            return this.qr_x;
        }

        public void setQr_x(int i) {
            this.qr_x = i;
        }

        public int getQr_y() {
            return this.qr_y;
        }

        public void setQr_y(int i) {
            this.qr_y = i;
        }

        public String getShare_bt_bg_img() {
            return this.share_bt_bg_img;
        }

        public void setShare_bt_bg_img(String str) {
            this.share_bt_bg_img = str;
        }

        public List<String> getShare_s_p_img() {
            return this.share_s_p_img;
        }

        public void setShare_s_p_img(List<String> list) {
            this.share_s_p_img = list;
        }

        public String getShare_s_p_txt() {
            return this.share_s_p_txt;
        }

        public void setShare_s_p_txt(String str) {
            this.share_s_p_txt = str;
        }

        public int getBenefit_status() {
            return this.benefit_status;
        }

        public void setBenefit_status(int i) {
            this.benefit_status = i;
        }

        public String getBenefit_get_txt() {
            return this.benefit_get_txt;
        }

        public void setBenefit_get_txt(String str) {
            this.benefit_get_txt = str;
        }

        public String getProduct_share_backup_up() {
            return this.product_share_backup_up;
        }

        public void setProduct_share_backup_up(String str) {
            this.product_share_backup_up = str;
        }

        public int getQr_size() {
            return this.qr_size;
        }

        public void setQr_size(int i) {
            this.qr_size = i;
        }

        public String getShare_bg_sub_title() {
            return this.share_bg_sub_title;
        }

        public void setShare_bg_sub_title(String str) {
            this.share_bg_sub_title = str;
        }
    }

    public static class QAInfo {
        private String picUrl;

        public String getPicUrl() {
            return this.picUrl;
        }

        public void setPicUrl(String str) {
            this.picUrl = str;
        }
    }

    public static class VipCard {
        public String back_cash_text;
        public String buttonContent;
        public String icon_img;
        public int is_opened;

        public boolean isOpen() {
            return this.is_opened == 1;
        }
    }

    public static class ProductFingerprintsBean {
        private String securityDetailUrl;
        private String securityTagName;
        private String securityTagUrl;

        public String getSecurityTagName() {
            return this.securityTagName;
        }

        public void setSecurityTagName(String str) {
            this.securityTagName = str;
        }

        public String getSecurityDetailUrl() {
            return this.securityDetailUrl;
        }

        public void setSecurityDetailUrl(String str) {
            this.securityDetailUrl = str;
        }

        public String getSecurityTagUrl() {
            return this.securityTagUrl;
        }

        public void setSecurityTagUrl(String str) {
            this.securityTagUrl = str;
        }
    }

    public String getSecurityTitle() {
        return this.securityTitle;
    }

    public void setSecurityTitle(String str) {
        this.securityTitle = str;
    }

    public String getSecurityText() {
        return this.securityText;
    }

    public void setSecurityText(String str) {
        this.securityText = str;
    }

    public List<ProductFingerprintsBean> getProductFingerprints() {
        return this.productFingerprints;
    }

    public void setProductFingerprints(List<ProductFingerprintsBean> list) {
        this.productFingerprints = list;
    }

    public SecKillProductInfo getSecKillProductInfo() {
        return this.secKillProductInfo;
    }

    public void setSecKillProductInfo(SecKillProductInfo secKillProductInfo) {
        this.secKillProductInfo = secKillProductInfo;
    }

    public static class NewItemShareInfo {
        private String shareBounceImg;
        private String shareRule;
        private String shareShowImg;
        private String shareShowText;
        private String shareSuccessImg;

        public String getShareShowText() {
            return this.shareShowText;
        }

        public void setShareShowText(String str) {
            this.shareShowText = str;
        }

        public String getShareShowImg() {
            return this.shareShowImg;
        }

        public void setShareShowImg(String str) {
            this.shareShowImg = str;
        }

        public String getShareRule() {
            return this.shareRule;
        }

        public void setShareRule(String str) {
            this.shareRule = str;
        }

        public String getShareBounceImg() {
            return this.shareBounceImg;
        }

        public void setShareBounceImg(String str) {
            this.shareBounceImg = str;
        }

        public String getShareSuccessImg() {
            return this.shareSuccessImg;
        }

        public void setShareSuccessImg(String str) {
            this.shareSuccessImg = str;
        }
    }

    public static class SecKillProductInfo {
        public static final int SECKILL_STATUS_FINISHED = 3;
        public static final int SECKILL_STATUS_IN_PROGREESS = 2;
        public static final int SECKILL_STATUS_NONE = 0;
        public static final int SECKILL_STATUS_PENDDING = 1;
        private String secKillLeftText;
        private String secKillRightText;
        private int secKillStatus;
        private long times;

        public int getSecKillStatus() {
            return this.secKillStatus;
        }

        public void setSecKillStatus(int i) {
            this.secKillStatus = i;
        }

        public String getSecKillLeftText() {
            return this.secKillLeftText;
        }

        public void setSecKillLeftText(String str) {
            this.secKillLeftText = str;
        }

        public long getTimes() {
            return this.times;
        }

        public void setTimes(long j) {
            this.times = j;
        }

        public String getSecKillRightText() {
            return this.secKillRightText;
        }

        public void setSecKillRightText(String str) {
            this.secKillRightText = str;
        }

        public boolean checkIsSeckillAvailable() {
            int i = this.secKillStatus;
            return (i == 0 || i == 3) ? false : true;
        }

        public boolean isStated() {
            return 2 == this.secKillStatus;
        }

        public boolean isPendding() {
            return 1 == this.secKillStatus;
        }
    }

    public static class PromotionShow {
        public static final String FULL_DISCOUNT = "FullDiscount";
        public static final String FULL_REDUCTION = "FullReduction";
        public static final String GET_TICKET = "voucher";
        private boolean canUseVoucher;
        private String icon;
        private String name;
        private String type;
        private String unUseVoucherImg;
        private List<String> value;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public List<String> getValue() {
            return this.value;
        }

        public void setValue(List<String> list) {
            this.value = list;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getIcon() {
            return this.icon;
        }

        public void setIcon(String str) {
            this.icon = str;
        }

        public void setUnUseVoucherImg(String str) {
            this.unUseVoucherImg = str;
        }

        public String getUnUseVoucherImg() {
            return this.unUseVoucherImg;
        }

        public void setCanUseVoucher(boolean z) {
            this.canUseVoucher = z;
        }

        public boolean isCanUseVoucher() {
            return this.canUseVoucher;
        }
    }

    public PricePro getPricePro() {
        return this.pricePro;
    }

    public void setPricePro(PricePro pricePro) {
        this.pricePro = pricePro;
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

    public Discount getDiscountDetail() {
        return this.discountDetail;
    }

    public void setDiscountDetail(Discount discount) {
        this.discountDetail = discount;
    }

    public VIPButton getVip_button() {
        return this.vip_button;
    }

    public void setVip_button(VIPButton vIPButton) {
        this.vip_button = vIPButton;
    }

    public static class VIPButton {
        private String buttonContent;
        private String iconImg;

        public String getIconImg() {
            return this.iconImg;
        }

        public void setIconImg(String str) {
            this.iconImg = str;
        }

        public String getButtonContent() {
            return this.buttonContent;
        }

        public void setButtonContent(String str) {
            this.buttonContent = str;
        }
    }

    public FightGroupInfo getFightGroupInfo() {
        return this.fightGroupInfo;
    }

    public void setFightGroupInfo(FightGroupInfo fightGroupInfo) {
        this.fightGroupInfo = fightGroupInfo;
    }

    public boolean isSpu() {
        return this.spuInfo != null;
    }

    public String getSkuProp() {
        return this.skuProp;
    }

    public void setSkuProp(String str) {
        this.skuProp = str;
    }

    public ProductsEntity.SpuInfoBean getSpuInfo() {
        return this.spuInfo;
    }

    public void setSpuInfo(ProductsEntity.SpuInfoBean spuInfoBean) {
        this.spuInfo = spuInfoBean;
    }

    public static class SimilarData {
        private List<ProductsEntity> products;
        public String requestId;

        public List<ProductsEntity> getProducts() {
            AppMethodBeat.i(6094, false);
            if (this.products == null) {
                this.products = new ArrayList();
            }
            List<ProductsEntity> list = this.products;
            AppMethodBeat.o(6094);
            return list;
        }

        public void setProducts(List<ProductsEntity> list) {
            this.products = list;
        }
    }
}
