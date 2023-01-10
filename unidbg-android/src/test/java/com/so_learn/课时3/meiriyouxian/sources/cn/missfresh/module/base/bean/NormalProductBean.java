package cn.missfresh.module.base.bean;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class NormalProductBean extends BaseCellBean implements IProductCell {
    private int dataSourceType;
    private String firstProductImage;
    private ProductsEntity normalProducts;
    private String recommendRequestId;
    private int secondCategoryType;
    private String skuFromSource;
    private String skuType;

    public int getDataSourceType() {
        return this.dataSourceType;
    }

    public void setDataSourceType(int i) {
        this.dataSourceType = i;
    }

    public ProductsEntity getNormalProducts() {
        return this.normalProducts;
    }

    public void setNormalProducts(ProductsEntity productsEntity) {
        this.normalProducts = productsEntity;
    }

    public String getSku() {
        AppMethodBeat.i(5210, false);
        ProductsEntity productsEntity = this.normalProducts;
        String sku = productsEntity == null ? "" : productsEntity.getSku();
        AppMethodBeat.o(5210);
        return sku;
    }

    public boolean isSellOut() {
        boolean z = false;
        AppMethodBeat.i(5213, false);
        ProductsEntity productsEntity = this.normalProducts;
        if (productsEntity != null) {
            z = productsEntity.getSell_out();
        }
        AppMethodBeat.o(5213);
        return z;
    }

    public String getProductName() {
        AppMethodBeat.i(5215, false);
        ProductsEntity productsEntity = this.normalProducts;
        String name = productsEntity != null ? productsEntity.getName() : "";
        AppMethodBeat.o(5215);
        return name;
    }

    public void setFirstProductImage(String str) {
        this.firstProductImage = str;
    }

    public String getDisCountDesc() {
        AppMethodBeat.i(5218, false);
        ProductsEntity productsEntity = this.normalProducts;
        if (productsEntity != null) {
            String discount_desc = productsEntity.getDiscount_desc();
            AppMethodBeat.o(5218);
            return discount_desc;
        }
        AppMethodBeat.o(5218);
        return "";
    }

    public String getFristProductImageUrl() {
        return this.firstProductImage;
    }

    public String getSkuType() {
        return this.skuType;
    }

    public String getRecommendRequestId() {
        String str = this.recommendRequestId;
        return str == null ? "" : str;
    }

    public void setSkuType(String str) {
        this.skuType = str;
    }

    public void setRecommendRequestId(String str) {
        this.recommendRequestId = str;
    }

    public int getSecondCategoryType() {
        return this.secondCategoryType;
    }

    public void setSecondCategoryType(int i) {
        this.secondCategoryType = i;
    }

    public String getSkuFromSource() {
        return this.skuFromSource;
    }

    public void setSkuFromSource(String str) {
        this.skuFromSource = str;
    }

    public String getSkuCategory() {
        AppMethodBeat.i(5235, false);
        ProductsEntity productsEntity = this.normalProducts;
        String skuCategory = productsEntity == null ? "" : productsEntity.getSkuCategory();
        AppMethodBeat.o(5235);
        return skuCategory;
    }

    public void setSubscribe(boolean z) {
        AppMethodBeat.i(5237, false);
        ProductsEntity productsEntity = this.normalProducts;
        if (productsEntity != null) {
            productsEntity.setSubscibe(z);
        }
        AppMethodBeat.o(5237);
    }

    public void setPosInProductList(int i) {
        AppMethodBeat.i(5240, false);
        ProductsEntity productsEntity = this.normalProducts;
        if (productsEntity != null) {
            productsEntity.setPosInProductList(i);
        }
        AppMethodBeat.o(5240);
    }

    public int getPosInProductList() {
        AppMethodBeat.i(5242, false);
        ProductsEntity productsEntity = this.normalProducts;
        if (productsEntity == null) {
            AppMethodBeat.o(5242);
            return -1;
        }
        int posInProductList = productsEntity.getPosInProductList();
        AppMethodBeat.o(5242);
        return posInProductList;
    }

    public boolean isQualified() {
        AppMethodBeat.i(5245, false);
        if (this.normalProducts == null) {
            AppMethodBeat.o(5245);
            return false;
        }
        boolean isQualified = NormalProductBean.super.isQualified();
        AppMethodBeat.o(5245);
        return isQualified;
    }
}
