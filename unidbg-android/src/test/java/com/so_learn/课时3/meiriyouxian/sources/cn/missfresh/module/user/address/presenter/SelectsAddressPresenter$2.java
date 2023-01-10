package cn.missfresh.module.user.address.presenter;

import cn.missfresh.module.base.bean.TencentSearchData;
import cn.missfresh.module.base.manager.c;
import cn.missfresh.module.base.manager.q;
import cn.missfresh.module.user.address.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import java.util.List;

class SelectsAddressPresenter$2 implements q.b {
    final /* synthetic */ SelectsAddressPresenter a;

    SelectsAddressPresenter$2(SelectsAddressPresenter selectsAddressPresenter) {
        this.a = selectsAddressPresenter;
    }

    @Override // cn.missfresh.module.base.manager.q.b
    public void a(List<TencentSearchData> list) {
        AppMethodBeat.i(4746, false);
        String a = SelectsAddressPresenter.a(this.a);
        d.d(a, " requestLocationName locationSucceed " + SelectsAddressPresenter.b(this.a) + " location time out " + SelectsAddressPresenter.c(this.a));
        if (SelectsAddressPresenter.c(this.a)) {
            a.a("onSearchSucceed", "onSearchSucceed but locationTimeOut");
            AppMethodBeat.o(4746);
            return;
        }
        SelectsAddressPresenter.b(this.a, true);
        this.a.b();
        if (b.a(list)) {
            a.a("onSearchSucceed", "MFCommonUtils.isEmpty(tencentSearchDataList)");
            SelectsAddressPresenter.d(this.a).d();
            SelectsAddressPresenter.d(this.a).c();
            a(1);
            d.d(SelectsAddressPresenter.a(this.a), "search poi null");
        } else {
            TencentSearchData tencentSearchData = list.get(0);
            if (b.a(c.h()) && tencentSearchData.ad_info != null && !b.a(tencentSearchData.ad_info.adcode)) {
                cn.missfresh.module.base.manager.b.d(tencentSearchData.ad_info.adcode);
            }
            SelectsAddressPresenter.d(this.a).a(tencentSearchData.title);
            SelectsAddressPresenter.e(this.a).a(list);
            SelectsAddressPresenter.d(this.a).b(list);
            d.d(SelectsAddressPresenter.a(this.a), "search poi success");
        }
        AppMethodBeat.o(4746);
    }

    @Override // cn.missfresh.module.base.manager.q.b
    public void a(int i) {
        AppMethodBeat.i(4747, false);
        a.a("onSearchFailed", "errorNetwork:" + i);
        SelectsAddressPresenter.d(this.a).d();
        SelectsAddressPresenter.d(this.a).c();
        AppMethodBeat.o(4747);
    }
}
