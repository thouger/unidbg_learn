package cn.missfresh.module.user.address.model;

import cn.missfresh.module.base.bean.TencentSearchData;
import cn.missfresh.module.base.network.j;
import cn.missfresh.module.mvp.mvp.impl.MVPModel;
import cn.missfresh.module.mvp.mvp.interfaces.IModel;
import cn.missfresh.module.user.address.api.SupportCityOptApiManager;
import cn.missfresh.module.user.address.bean.AreaBean;
import cn.missfresh.module.user.address.bean.SupporCityBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public class SupportCityOptModel extends MVPModel {
    public List<SupporCityBean> a = new ArrayList();
    public List<SupporCityBean> b = new ArrayList();
    public String c;
    private List<TencentSearchData> d = new ArrayList();
    private String e = "";

    public SupportCityOptModel() {
        AppMethodBeat.i(4048, false);
        AppMethodBeat.o(4048);
    }

    /* renamed from: cn.missfresh.module.user.address.model.SupportCityOptModel$1  reason: invalid class name */
    class AnonymousClass1 extends j<AreaBean> {
        final /* synthetic */ IModel.a a;

        AnonymousClass1(IModel.a aVar) {
            this.a = aVar;
        }

        @Override // cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(4039, false);
            a((AreaBean) obj);
            AppMethodBeat.o(4039);
        }

        @Override // cn.missfresh.module.base.network.j
        public void a(int i, String str) {
            AppMethodBeat.i(4031, false);
            this.a.a(i, str);
            this.a.b();
            AppMethodBeat.o(4031);
        }

        public void a(AreaBean areaBean) {
            AppMethodBeat.i(4036, false);
            SupportCityOptModel.this.a = areaBean.getChromeCity().getCityList();
            SupportCityOptModel.this.c = areaBean.getAllCityLabel();
            for (int i = 0; i < areaBean.getAllCity().size(); i++) {
                SupportCityOptModel.this.b.addAll(((AreaBean.AllCityBean) areaBean.getAllCity().get(i)).getCityList());
            }
            if (!(areaBean == null || areaBean.getChromeCity() == null)) {
                SupportCityOptModel.this.e = areaBean.getChromeCity().getLabel();
            }
            this.a.a();
            this.a.b();
            AppMethodBeat.o(4036);
        }
    }

    public void a(IModel.a aVar) {
        AppMethodBeat.i(4054, false);
        a(SupportCityOptApiManager.getSupportCityOptApi().requestAreaList(), new AnonymousClass1(aVar), aVar.c());
        AppMethodBeat.o(4054);
    }

    public String a() {
        return this.e;
    }

    public void a(List<TencentSearchData> list) {
        this.d = list;
    }

    public List<TencentSearchData> b() {
        return this.d;
    }
}
