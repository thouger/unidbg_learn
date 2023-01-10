package cn.missfresh.module.base.common.listener;

import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.base.bean.SelectSkuUiData;
import cn.missfresh.module.base.common.adapter.SelectServiceAdapter;
import cn.missfresh.module.base.common.interfaces.l;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SelectSkuServiceItemClickListener */
public class d implements SelectServiceAdapter.a {
    SelectSkuUiData a;
    SelectServiceAdapter b;
    String c;
    l d;

    public d(SelectSkuUiData selectSkuUiData, SelectServiceAdapter selectServiceAdapter, String str, l lVar) {
        this.a = selectSkuUiData;
        this.b = selectServiceAdapter;
        this.c = str;
        this.d = lVar;
    }

    @Override // cn.missfresh.module.base.common.adapter.SelectServiceAdapter.a
    public void a(int i) {
        AppMethodBeat.i(12086, false);
        for (ProductsEntity.SpuInfoBean.ServiceListBean.SubListBeanX subListBeanX : this.b.a()) {
            if (subListBeanX.getServiceId() != this.b.a().get(i).getServiceId()) {
                subListBeanX.setStatus(0);
            } else if (this.b.a().get(i).getStatus() == 0) {
                this.b.a().get(i).setStatus(1);
                this.a.getServiceResult().put(this.c, this.b.a().get(i));
            } else if (this.b.a().get(i).getStatus() == 1) {
                this.b.a().get(i).setStatus(0);
                this.a.getServiceResult().remove(this.c);
            }
        }
        this.a.getSelectServiceAdapters().get(this.c).notifyDataSetChanged();
        this.d.a(true);
        AppMethodBeat.o(12086);
    }
}
