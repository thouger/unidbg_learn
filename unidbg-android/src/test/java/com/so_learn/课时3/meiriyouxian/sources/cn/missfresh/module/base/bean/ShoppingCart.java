package cn.missfresh.module.base.bean;

import cn.missfresh.module.base.bean.MissFreshProduct;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Transient;
import java.util.List;

public class ShoppingCart {
    public static final int show_type_delete = 2;
    public static final int show_type_hide = 0;
    public static final int show_type_normal = 1;
    @JSONField(serialize = false)
    private String addCartFailedCallback;
    @JSONField(serialize = false)
    private String addCartSuccessCallback;
    @Transient
    private int buy_permission;
    @Transient
    private int can_use_product_voucher;
    @Transient
    private String cut_remind;
    @Transient
    private DiscountDesc discountDesc;
    @Transient
    protected String error_code;
    @JSONField(serialize = false)
    private ExtraData extra;
    @JSONField(serialize = false)
    private String from;
    @Transient
    private MissFreshProduct.GiftCheckedInfo giftCheckedInfo;
    @Transient
    private String image;
    @Transient
    protected boolean isActiveItem;
    protected boolean isChecked;
    @Transient
    private int limit;
    @Transient
    private String name;
    @Transient
    private int nationwide;
    @Transient
    private PricePro nonVipPP;
    @Transient
    private int present_num;
    @Transient
    private int price;
    @Transient
    private List<ProductGift> productGifts;
    @Transient
    private int product_limit;
    @Transient
    private List<MissFreshProduct.ProductTag> product_tags;
    @Transient
    private String promote_tag;
    @Transient
    public String promote_tag_new;
    @Transient
    private String promotion_tag;
    @Transient
    private List<String> prompt_img_list;
    private int quantity;
    @Transient
    private List<Integer> serviceIds;
    @Id
    @NoAutoIncrement
    private String sku;
    @Transient
    private int stock;
    @Transient
    private String tip_msg;
    @Transient
    private String trans_type;
    @Transient
    private String unit;
    @Transient
    private PricePro vipPP;
    private VipTag vipTag;
    @Transient
    private int vip_exclusive;
    @Transient
    private boolean vip_product;
    @Transient
    private String voucher_tag;
    @Transient
    private List<String> vp_prompt_img_list;

    public interface IGroupTitleClkTo {
        public static final int toAddOnPage = 1;
        public static final int toHomePage = 0;
    }

    public String getCut_remind() {
        return this.cut_remind;
    }

    public void setCut_remind(String str) {
        this.cut_remind = str;
    }

    public List<ProductGift> getProductGifts() {
        return this.productGifts;
    }

    public void setProductGifts(List<ProductGift> list) {
        this.productGifts = list;
    }

    public MissFreshProduct.GiftCheckedInfo getGiftCheckedInfo() {
        return this.giftCheckedInfo;
    }

    public void setGiftCheckedInfo(MissFreshProduct.GiftCheckedInfo giftCheckedInfo) {
        this.giftCheckedInfo = giftCheckedInfo;
    }

    public PricePro getNonVipPP() {
        return this.nonVipPP;
    }

    public void setNonVipPP(PricePro pricePro) {
        this.nonVipPP = pricePro;
    }

    public PricePro getVipPP() {
        return this.vipPP;
    }

    public void setVipPP(PricePro pricePro) {
        this.vipPP = pricePro;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public int getNationwide() {
        return this.nationwide;
    }

    public void setNationwide(int i) {
        this.nationwide = i;
    }

    public boolean isNationWide() {
        return this.nationwide == 1;
    }

    public int getBuy_permission() {
        return this.buy_permission;
    }

    public void setBuy_permission(int i) {
        this.buy_permission = i;
    }

    public boolean getVip_product() {
        return this.vip_product;
    }

    public void setVip_product(boolean z) {
        this.vip_product = z;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String str) {
        this.sku = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public int getProductLimit() {
        AppMethodBeat.i(7075, false);
        int product_limit = !this.vip_product ? this.limit : getProduct_limit();
        AppMethodBeat.o(7075);
        return product_limit;
    }

    public int getProduct_limit() {
        int i = this.limit;
        int i2 = this.product_limit;
        return i > i2 ? i2 : i;
    }

    public void setProduct_limit(int i) {
        this.product_limit = i;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public boolean isActiveItem() {
        return this.isActiveItem;
    }

    public void setIsActiveItem(boolean z) {
        this.isActiveItem = z;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setIsChecked(boolean z) {
        this.isChecked = z;
    }

    public String getError_code() {
        return this.error_code;
    }

    public void setError_code(String str) {
        this.error_code = str;
    }

    public String toString() {
        AppMethodBeat.i(7095, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(7095);
        return jSONString;
    }

    public int getSelectPrice() {
        AppMethodBeat.i(7097, false);
        int non_vip_price_down_price = this.quantity * getNon_vip_price_down_price();
        AppMethodBeat.o(7097);
        return non_vip_price_down_price;
    }

    public int getSelectVipPrice() {
        AppMethodBeat.i(7101, false);
        int vip_price_down_price = this.quantity * getVip_price_down_price();
        AppMethodBeat.o(7101);
        return vip_price_down_price;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public int getLimit() {
        return this.limit;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int i) {
        this.stock = i;
    }

    public String getTip_msg() {
        return this.tip_msg;
    }

    public void setTip_msg(String str) {
        this.tip_msg = str;
    }

    public void setLimit(int i) {
        this.limit = i;
    }

    public ExtraData getExtra() {
        return this.extra;
    }

    public void setExtra(ExtraData extraData) {
        this.extra = extraData;
    }

    public String getVip_price_up_name() {
        PricePro pricePro = this.vipPP;
        return (pricePro == null || pricePro.price_up == null) ? "" : this.vipPP.price_up.name;
    }

    public int getVip_price_up_price() {
        PricePro pricePro = this.vipPP;
        if (pricePro == null || pricePro.price_up == null) {
            return 0;
        }
        return this.vipPP.price_up.price;
    }

    public int getVipPriceUpShowTag() {
        PricePro pricePro = this.vipPP;
        if (pricePro == null || pricePro.price_up == null) {
            return 0;
        }
        return this.vipPP.price_up.showTag;
    }

    public boolean showShoppingCartPic() {
        AppMethodBeat.i(7116, false);
        if (getVipPriceDownShowTag() == 1 && getVip_price_down_show_type() == 1) {
            AppMethodBeat.o(7116);
            return true;
        }
        AppMethodBeat.o(7116);
        return false;
    }

    public int getVip_price_up_color() {
        AppMethodBeat.i(7118, false);
        PricePro pricePro = this.vipPP;
        if (pricePro == null || pricePro.price_up == null) {
            AppMethodBeat.o(7118);
            return 0;
        }
        int price_color = this.vipPP.price_up.getPrice_color();
        AppMethodBeat.o(7118);
        return price_color;
    }

    public int getVip_name_up_color() {
        AppMethodBeat.i(7120, false);
        PricePro pricePro = this.vipPP;
        if (pricePro == null || pricePro.price_up == null) {
            AppMethodBeat.o(7120);
            return 0;
        }
        int name_color = this.vipPP.price_up.getName_color();
        AppMethodBeat.o(7120);
        return name_color;
    }

    public int getVip_price_up_price_color() {
        AppMethodBeat.i(7123, false);
        PricePro pricePro = this.vipPP;
        if (pricePro == null || pricePro.price_up == null) {
            AppMethodBeat.o(7123);
            return 0;
        }
        int price_color = this.vipPP.price_up.getPrice_color();
        AppMethodBeat.o(7123);
        return price_color;
    }

    public int getVip_price_up_show_type() {
        PricePro pricePro = this.vipPP;
        if (pricePro == null || pricePro.price_up == null) {
            return 0;
        }
        return this.vipPP.price_up.show_type;
    }

    public String getVip_price_down_name() {
        PricePro pricePro = this.vipPP;
        return (pricePro == null || pricePro.price_down == null) ? "" : this.vipPP.price_down.name;
    }

    public int getVip_price_down_price() {
        PricePro pricePro = this.vipPP;
        if (pricePro == null || pricePro.price_down == null) {
            return 0;
        }
        return this.vipPP.price_down.price;
    }

    public int getVipPriceDownShowTag() {
        PricePro pricePro = this.vipPP;
        if (pricePro == null || pricePro.price_down == null) {
            return 0;
        }
        return this.vipPP.price_down.showTag;
    }

    public int getVip_price_down_color() {
        AppMethodBeat.i(7129, false);
        PricePro pricePro = this.vipPP;
        if (pricePro == null || pricePro.price_down == null) {
            AppMethodBeat.o(7129);
            return 0;
        }
        int price_color = this.vipPP.price_down.getPrice_color();
        AppMethodBeat.o(7129);
        return price_color;
    }

    public int getVip_name_down_color() {
        AppMethodBeat.i(7132, false);
        PricePro pricePro = this.vipPP;
        if (pricePro == null || pricePro.price_down == null) {
            AppMethodBeat.o(7132);
            return 0;
        }
        int name_color = this.vipPP.price_down.getName_color();
        AppMethodBeat.o(7132);
        return name_color;
    }

    public int getVip_price_down_price_color() {
        AppMethodBeat.i(7134, false);
        PricePro pricePro = this.vipPP;
        if (pricePro == null || pricePro.price_down == null) {
            AppMethodBeat.o(7134);
            return 0;
        }
        int price_color = this.vipPP.price_down.getPrice_color();
        AppMethodBeat.o(7134);
        return price_color;
    }

    public int getVip_price_down_show_type() {
        PricePro pricePro = this.vipPP;
        if (pricePro == null || pricePro.price_down == null) {
            return 0;
        }
        return this.vipPP.price_down.show_type;
    }

    public String getNon_vip_price_up_name() {
        PricePro pricePro = this.nonVipPP;
        return (pricePro == null || pricePro.price_up == null) ? "" : this.nonVipPP.price_up.name;
    }

    public int getNon_vip_price_up_price() {
        PricePro pricePro = this.nonVipPP;
        if (pricePro == null || pricePro.price_up == null) {
            return 0;
        }
        return this.nonVipPP.price_up.price;
    }

    public int getNonPriceUpShowTag() {
        PricePro pricePro = this.nonVipPP;
        if (pricePro == null || pricePro.price_up == null) {
            return 0;
        }
        return this.nonVipPP.price_up.showTag;
    }

    public int getNon_vip_price_up_color() {
        AppMethodBeat.i(7140, false);
        PricePro pricePro = this.nonVipPP;
        if (pricePro == null || pricePro.price_up == null) {
            AppMethodBeat.o(7140);
            return 0;
        }
        int price_color = this.nonVipPP.price_up.getPrice_color();
        AppMethodBeat.o(7140);
        return price_color;
    }

    public int getNon_vip_name_up_color() {
        AppMethodBeat.i(7142, false);
        PricePro pricePro = this.nonVipPP;
        if (pricePro == null || pricePro.price_up == null) {
            AppMethodBeat.o(7142);
            return 0;
        }
        int name_color = this.nonVipPP.price_up.getName_color();
        AppMethodBeat.o(7142);
        return name_color;
    }

    public int getNon_vip_price_up_show_type() {
        PricePro pricePro = this.nonVipPP;
        if (pricePro == null || pricePro.price_up == null) {
            return 0;
        }
        return this.nonVipPP.price_up.show_type;
    }

    public String getNon_vip_price_down_name() {
        PricePro pricePro = this.nonVipPP;
        return (pricePro == null || pricePro.price_down == null) ? "" : this.nonVipPP.price_down.name;
    }

    public int getNon_vip_price_down_price() {
        PricePro pricePro = this.nonVipPP;
        if (pricePro == null || pricePro.price_down == null) {
            return 0;
        }
        return this.nonVipPP.price_down.price;
    }

    public int getNonPriceDownShowTag() {
        PricePro pricePro = this.nonVipPP;
        if (pricePro == null || pricePro.price_down == null) {
            return 0;
        }
        return this.nonVipPP.price_down.showTag;
    }

    public int getNon_vip_price_down_color() {
        AppMethodBeat.i(7152, false);
        PricePro pricePro = this.nonVipPP;
        if (pricePro == null || pricePro.price_down == null) {
            AppMethodBeat.o(7152);
            return 0;
        }
        int price_color = this.nonVipPP.price_down.getPrice_color();
        AppMethodBeat.o(7152);
        return price_color;
    }

    public int getNon_vip_name_down_color() {
        AppMethodBeat.i(7155, false);
        PricePro pricePro = this.nonVipPP;
        if (pricePro == null || pricePro.price_down == null) {
            AppMethodBeat.o(7155);
            return 0;
        }
        int name_color = this.nonVipPP.price_down.getName_color();
        AppMethodBeat.o(7155);
        return name_color;
    }

    public int getNon_vip_price_down_show_type() {
        PricePro pricePro = this.nonVipPP;
        if (pricePro == null || pricePro.price_down == null) {
            return 0;
        }
        return this.nonVipPP.price_down.show_type;
    }

    public String getPromotion_tag() {
        return this.promotion_tag;
    }

    public void setPromotion_tag(String str) {
        this.promotion_tag = str;
    }

    public List<MissFreshProduct.ProductTag> getProduct_tags() {
        return this.product_tags;
    }

    public void setProduct_tags(List<MissFreshProduct.ProductTag> list) {
        this.product_tags = list;
    }

    public int getCan_use_product_voucher() {
        return this.can_use_product_voucher;
    }

    public void setCan_use_product_voucher(int i) {
        this.can_use_product_voucher = i;
    }

    public String getVoucher_tag() {
        return this.voucher_tag;
    }

    public void setVoucher_tag(String str) {
        this.voucher_tag = str;
    }

    public String getPromote_tag() {
        return this.promote_tag;
    }

    public void setPromote_tag(String str) {
        this.promote_tag = str;
    }

    public List<String> getPrompt_img_list() {
        return this.prompt_img_list;
    }

    public void setPrompt_img_list(List<String> list) {
        this.prompt_img_list = list;
    }

    public List<String> getVp_prompt_img_list() {
        return this.vp_prompt_img_list;
    }

    public void setVp_prompt_img_list(List<String> list) {
        this.vp_prompt_img_list = list;
    }

    public String getTrans_type() {
        return this.trans_type;
    }

    public void setTrans_type(String str) {
        this.trans_type = str;
    }

    public int getPresent_num() {
        return this.present_num;
    }

    public void setPresent_num(int i) {
        this.present_num = i;
    }

    public int getVip_exclusive() {
        return this.vip_exclusive;
    }

    public void setVip_exclusive(int i) {
        this.vip_exclusive = i;
    }

    public boolean isVipPrice() {
        boolean z = false;
        AppMethodBeat.i(7471, false);
        if (g.a().b() || e.z() == PriceAndPromotion.balance_type_vip) {
            z = true;
        }
        AppMethodBeat.o(7471);
        return z;
    }

    public boolean equals(Object obj) {
        AppMethodBeat.i(7473, false);
        if (this == obj) {
            AppMethodBeat.o(7473);
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            AppMethodBeat.o(7473);
            return false;
        } else {
            ShoppingCart shoppingCart = (ShoppingCart) obj;
            if (b.a(this.sku)) {
                AppMethodBeat.o(7473);
                return false;
            }
            boolean equals = this.sku.equals(shoppingCart.sku);
            AppMethodBeat.o(7473);
            return equals;
        }
    }

    public int hashCode() {
        AppMethodBeat.i(7475, false);
        int hashCode = this.sku.hashCode();
        AppMethodBeat.o(7475);
        return hashCode;
    }

    public DiscountDesc getDiscountDesc() {
        return this.discountDesc;
    }

    public void setDiscountDesc(DiscountDesc discountDesc) {
        this.discountDesc = discountDesc;
    }

    public List<Integer> getServiceIds() {
        return this.serviceIds;
    }

    public void setServiceIds(List<Integer> list) {
        this.serviceIds = list;
    }

    public enum ProductType {
        NORMAL(0),
        NationWide(1);
        
        private int mType;

        public static ProductType valueOf(String str) {
            AppMethodBeat.i(7042, false);
            ProductType productType = (ProductType) Enum.valueOf(ProductType.class, str);
            AppMethodBeat.o(7042);
            return productType;
        }

        static {
            AppMethodBeat.i(7046, false);
            AppMethodBeat.o(7046);
        }

        private ProductType(int i) {
            this.mType = i;
        }

        public int getmType() {
            return this.mType;
        }
    }

    public VipTag getVipTag() {
        return this.vipTag;
    }

    public void setVipTag(VipTag vipTag) {
        this.vipTag = vipTag;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public String getAddCartSuccessCallback() {
        return this.addCartSuccessCallback;
    }

    public void setAddCartSuccessCallback(String str) {
        this.addCartSuccessCallback = str;
    }

    public String getAddCartFailedCallback() {
        return this.addCartFailedCallback;
    }

    public void setAddCartFailedCallback(String str) {
        this.addCartFailedCallback = str;
    }
}
