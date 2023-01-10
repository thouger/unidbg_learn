package cn.missfresh.module.base.bean;

import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.NoAutoIncrement;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Transient;

@Table(name = "shoppingcart_new")
public class ShoppingCartProductBean implements IShoppingBase {
    @Transient
    private int cellType = 3;
    @Transient
    private int checked;
    @Transient
    private String cutRemind;
    @Transient
    private int dayLimit;
    @Transient
    private String deliveryText;
    @Transient
    private String groupId;
    @Transient
    private String image;
    @Transient
    private boolean isFirst;
    @Transient
    private boolean isLastItem;
    @Transient
    private PriceBean left;
    @Transient
    private String mrdTag;
    @Transient
    private String name;
    @Transient
    private int nationwide;
    @Transient
    private int orderLimit;
    @Transient
    private int productGroupPosition;
    @Transient
    private int productInGroupPosition;
    @Transient
    private String productService;
    @Transient
    private String promotionReminder;
    @Transient
    private String promotionTag;
    private int quantity;
    @Transient
    private PriceBean right;
    @Transient
    private int sellOut;
    @Id
    @NoAutoIncrement
    private String sku;
    private String skuCategory;
    @Transient
    private String spec;
    @Transient
    private int stock;
    @Transient
    private String subtitle;
    @Transient
    private String tag;
    @Transient
    private String tipMsg;
    @Transient
    private String type;

    public ShoppingCartProductBean() {
    }

    public ShoppingCartProductBean(String str, int i, int i2) {
        this.sku = str;
        this.quantity = i;
        this.checked = i2;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String str) {
        this.sku = str;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int i) {
        this.stock = i;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getProductService() {
        return this.productService;
    }

    public void setProductService(String str) {
        this.productService = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public String getTipMsg() {
        return this.tipMsg;
    }

    public void setTipMsg(String str) {
        this.tipMsg = str;
    }

    public String getMrdTag() {
        return this.mrdTag;
    }

    public void setMrdTag(String str) {
        this.mrdTag = str;
    }

    public int getOrderLimit() {
        return this.orderLimit;
    }

    public void setOrderLimit(int i) {
        this.orderLimit = i;
    }

    public int getDayLimit() {
        return this.dayLimit;
    }

    public void setDayLimit(int i) {
        this.dayLimit = i;
    }

    public String getPromotionReminder() {
        return this.promotionReminder;
    }

    public void setPromotionReminder(String str) {
        this.promotionReminder = str;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String getDeliveryText() {
        return this.deliveryText;
    }

    public void setDeliveryText(String str) {
        this.deliveryText = str;
    }

    public PriceBean getLeft() {
        return this.left;
    }

    public void setLeft(PriceBean priceBean) {
        this.left = priceBean;
    }

    public PriceBean getRight() {
        return this.right;
    }

    public void setRight(PriceBean priceBean) {
        this.right = priceBean;
    }

    public String getCutRemind() {
        return this.cutRemind;
    }

    public void setCutRemind(String str) {
        this.cutRemind = str;
    }

    public void setLastItem(boolean z) {
        this.isLastItem = z;
    }

    public boolean isLastItem() {
        return this.isLastItem;
    }

    public int getSellOut() {
        return this.sellOut;
    }

    public void setSellOut(int i) {
        this.sellOut = i;
    }

    public void setChecked(int i) {
        this.checked = i;
    }

    public int getChecked() {
        return this.checked;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public int getNationwide() {
        return this.nationwide;
    }

    public void setNationwide(int i) {
        this.nationwide = i;
    }

    public boolean isFirst() {
        return this.isFirst;
    }

    public void setFirst(boolean z) {
        this.isFirst = z;
    }

    public String getPromotionTag() {
        return this.promotionTag;
    }

    public void setPromotionTag(String str) {
        this.promotionTag = str;
    }

    public String getSpec() {
        return this.spec;
    }

    public void setSpec(String str) {
        this.spec = str;
    }

    public int getProductInGroupPosition() {
        return this.productInGroupPosition;
    }

    public void setProductInGroupPosition(int i) {
        this.productInGroupPosition = i;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public int getCellType() {
        return this.cellType;
    }

    public void setItemCellType(int i) {
        this.cellType = i;
    }

    public String getSkuCategory() {
        return this.skuCategory;
    }

    public void setSkuCategory(String str) {
        this.skuCategory = str;
    }

    public int getProductGroupPosition() {
        return this.productGroupPosition;
    }

    public void setProductGroupPosition(int i) {
        this.productGroupPosition = i;
    }
}
