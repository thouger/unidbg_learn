package cn.missfresh.module.base.oftenbuy.bean;

import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.util.List;

public class BaseOftenBuyListBean {
    private String favourite_id;
    private String link;
    private String path;
    private List<ProductsEntity> productList;
    private String recommendRequestId;
    private String subHeading;
    private String subTitle;
    private String title;

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getRecommendRequestId() {
        return this.recommendRequestId;
    }

    public void setRecommendRequestId(String str) {
        this.recommendRequestId = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public List<ProductsEntity> getProductList() {
        return this.productList;
    }

    public void setProductList(List<ProductsEntity> list) {
        this.productList = list;
    }

    public String getSubHeading() {
        return this.subHeading;
    }

    public void setSubHeading(String str) {
        this.subHeading = str;
    }

    public String getFavourite_id() {
        return this.favourite_id;
    }

    public void setFavourite_id(String str) {
        this.favourite_id = str;
    }

    public String getSubTitleString() {
        AppMethodBeat.i(16243, false);
        String str = !b.a(this.subTitle) ? this.subTitle : this.subHeading;
        AppMethodBeat.o(16243);
        return str;
    }
}
