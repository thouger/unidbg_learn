package cn.missfresh.module.base.common.listener;

import cn.missfresh.module.base.bean.SelectSpuUiData;
import cn.missfresh.module.base.bean.SpuParams;
import cn.missfresh.module.base.common.adapter.SelectSpuServiceAdapter;
import cn.missfresh.module.base.common.interfaces.l;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SelectSpuServiceItemClickListener */
public class f implements SelectSpuServiceAdapter.a {
    SelectSpuUiData a;
    SelectSpuServiceAdapter b;
    String c;
    l d;

    public f(SelectSpuUiData selectSpuUiData, SelectSpuServiceAdapter selectSpuServiceAdapter, String str, l lVar) {
        this.a = selectSpuUiData;
        this.b = selectSpuServiceAdapter;
        this.c = str;
        this.d = lVar;
    }

    @Override // cn.missfresh.module.base.common.adapter.SelectSpuServiceAdapter.a
    public void a(int i) {
        AppMethodBeat.i(12090, false);
        for (SpuParams.SpuInfoBean.ServiceListBean.SubListBeanX subListBeanX : this.b.a()) {
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
        AppMethodBeat.o(12090);
    }
}
