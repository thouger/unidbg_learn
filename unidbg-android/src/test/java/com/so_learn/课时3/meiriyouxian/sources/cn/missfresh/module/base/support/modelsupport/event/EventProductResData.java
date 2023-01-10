package cn.missfresh.module.base.support.modelsupport.event;

import cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent;
import cn.missfresh.module.base.support.modelsupport.event.base.BaseResEvent;
import cn.missfresh.module.base.support.modelsupport.event.bean.ProductBeans;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class EventProductResData extends BaseResEvent {
    private ProductBeans.PreSaleProductDetail preSaleProductDetail = new ProductBeans.PreSaleProductDetail();
    private ProductBeans.GetPreSaleListRes toGetPreSaleListRes = new ProductBeans.GetPreSaleListRes();

    public EventProductResData(BaseReqEvent baseReqEvent) {
        super(baseReqEvent);
        AppMethodBeat.i(22048, false);
        AppMethodBeat.o(22048);
    }

    public ProductBeans.GetPreSaleListRes getToGetPreSaleListRes() {
        return this.toGetPreSaleListRes;
    }

    public void setPreSaleListRes(ProductBeans.GetPreSaleListRes getPreSaleListRes) {
        this.toGetPreSaleListRes = getPreSaleListRes;
    }

    public ProductBeans.PreSaleProductDetail getPreSaleProductDetail() {
        return this.preSaleProductDetail;
    }

    public void setPreSaleProductDetail(ProductBeans.PreSaleProductDetail preSaleProductDetail) {
        this.preSaleProductDetail = preSaleProductDetail;
    }
}
