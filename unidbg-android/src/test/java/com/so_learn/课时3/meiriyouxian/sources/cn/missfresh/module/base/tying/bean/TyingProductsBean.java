package cn.missfresh.module.base.tying.bean;

import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.util.List;

public class TyingProductsBean {
    private String channel;
    private int position;
    List<ProductsEntity> productList;
    private String recommendRequestId;
    private String title;
    private String tyingSku;

    public List<ProductsEntity> getProductList() {
        return this.productList;
    }

    public void setProductList(List<ProductsEntity> list) {
        this.productList = list;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getPosition() {
        return this.position;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getTyingSku() {
        return this.tyingSku;
    }

    public void setTyingSku(String str) {
        this.tyingSku = str;
    }

    public String getRecommendRequestId() {
        return this.recommendRequestId;
    }

    public void setRecommendRequestId(String str) {
        this.recommendRequestId = str;
    }

    public String getExposureRequestId() {
        AppMethodBeat.i(22997, false);
        if (b.a(this.productList)) {
            AppMethodBeat.o(22997);
            return "";
        }
        String requestId = this.productList.get(0).getRequestId();
        AppMethodBeat.o(22997);
        return requestId;
    }
}
