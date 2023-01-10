package cn.missfresh.module.base.common.event;

import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.bean.ParamsBean;
import java.io.Serializable;

public class ProductPageClickEvent implements Serializable {
    private String image;
    private BannerEntity mBannerEntity;
    private int mCategoryType;
    private int mChannelType;
    private String mInternalId;
    private boolean mIsHorizontal;
    private boolean mIsProductSellout;
    private String mLink;
    private String mName;
    private int mPagePosition;
    private ParamsBean mParams;
    private int mPos;
    private String mPromotionId;
    private String mRecommendRequestId;
    private String mSecondChannelId;
    private String mSecondChannelName;
    private String mSku;
    private String mSkuCategory;
    private String mSkuFromSource;
    private int mType;
    private String productTag;
    private String skuType;
    private String themeTag;

    public ProductPageClickEvent(int i, int i2) {
        this.themeTag = "";
        this.productTag = "";
        this.mType = i;
        this.mPagePosition = i2;
    }

    public ProductPageClickEvent(int i, int i2, BannerEntity bannerEntity) {
        this(i, i2);
        this.mBannerEntity = bannerEntity;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public BannerEntity getBannerEntity() {
        return this.mBannerEntity;
    }

    public void setBannerEntity(BannerEntity bannerEntity) {
        this.mBannerEntity = bannerEntity;
    }

    public int getPos() {
        return this.mPos;
    }

    public void setPos(int i) {
        this.mPos = i;
    }

    public ParamsBean getParams() {
        return this.mParams;
    }

    public void setParams(ParamsBean paramsBean) {
        this.mParams = paramsBean;
    }

    public String getLink() {
        return this.mLink;
    }

    public void setLink(String str) {
        this.mLink = str;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getInternalId() {
        return this.mInternalId;
    }

    public void setInternalId(String str) {
        this.mInternalId = str;
    }

    public boolean isProductSellout() {
        return this.mIsProductSellout;
    }

    public void setProductSellout(boolean z) {
        this.mIsProductSellout = z;
    }

    public String getSku() {
        return this.mSku;
    }

    public void setSku(String str) {
        this.mSku = str;
    }

    public int getPagePosition() {
        return this.mPagePosition;
    }

    public void setPagePosition(int i) {
        this.mPagePosition = i;
    }

    public boolean isHorizontal() {
        return this.mIsHorizontal;
    }

    public void setHorizontal(boolean z) {
        this.mIsHorizontal = z;
    }

    public String getSecondChannelId() {
        return this.mSecondChannelId;
    }

    public void setSecondChannelId(String str) {
        this.mSecondChannelId = str;
    }

    public String getRecommendRequestId() {
        return this.mRecommendRequestId;
    }

    public void setRecommendRequestId(String str) {
        this.mRecommendRequestId = str;
    }

    public String getPromotionId() {
        return this.mPromotionId;
    }

    public void setPromotionId(String str) {
        this.mPromotionId = str;
    }

    public int getChannelType() {
        return this.mChannelType;
    }

    public void setChannelType(int i) {
        this.mChannelType = i;
    }

    public int getCategoryType() {
        return this.mCategoryType;
    }

    public void setCategoryType(int i) {
        this.mCategoryType = i;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getSecondChannelName() {
        return this.mSecondChannelName;
    }

    public void setSecondChannelName(String str) {
        this.mSecondChannelName = str;
    }

    public String getSkuCategory() {
        return this.mSkuCategory;
    }

    public void setSkuCategory(String str) {
        this.mSkuCategory = str;
    }

    public String getSkuFromSource() {
        return this.mSkuFromSource;
    }

    public void setSkuFromSource(String str) {
        this.mSkuFromSource = str;
    }

    public String getSkuType() {
        return this.skuType;
    }

    public void setSkuType(String str) {
        this.skuType = str;
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
}
