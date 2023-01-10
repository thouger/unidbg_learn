package cn.missfresh.module.search.viewmodule;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.mvvm.BaseViewModel;
import cn.missfresh.module.search.adapter.bean.AssociatedBean;
import cn.missfresh.module.search.adapter.bean.SearchHotBean;
import cn.missfresh.module.search.b.b;
import cn.missfresh.module.search.c.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.umeng.analytics.pro.ai;
import io.reactivex.c.h;
import io.reactivex.u;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SearchModule extends BaseViewModel {
    public String a;
    public boolean b;
    private MutableLiveData<List<String>> c = new MutableLiveData<>();
    private MutableLiveData<List<SearchHotBean>> d = new MutableLiveData<>();
    private MutableLiveData<List<Object>> e = new MutableLiveData<>();
    private MutableLiveData<Boolean> f = new MutableLiveData<>();
    private MutableLiveData<Boolean> g = new MutableLiveData<>();
    private MutableLiveData<SearchHotBean.SearchHotListBean> h = new MutableLiveData<>();
    private a i = ((a) com.alibaba.android.arouter.b.a.a().a("/search/interceptor").navigation());
    private h<AssociatedBean.AssociatedListBean, AssociatedBean.AssociatedListBean> j = new AnonymousClass3();

    public SearchModule() {
        AppMethodBeat.i(12112, false);
        AppMethodBeat.o(12112);
    }

    public LiveData<List<SearchHotBean>> a() {
        return this.d;
    }

    public LiveData<List<Object>> b() {
        return this.e;
    }

    public LiveData<List<String>> c() {
        return this.c;
    }

    public MutableLiveData<Boolean> d() {
        return this.f;
    }

    public MutableLiveData<SearchHotBean.SearchHotListBean> e() {
        return this.h;
    }

    public void f() {
        AppMethodBeat.i(12113, false);
        this.c.setValue(e.J());
        AppMethodBeat.o(12113);
    }

    public MutableLiveData<Boolean> g() {
        return this.g;
    }

    public void a(String str) {
        AppMethodBeat.i(12114, false);
        e.s(str);
        AppMethodBeat.o(12114);
    }

    public void h() {
        AppMethodBeat.i(12115, false);
        e.L();
        this.c.setValue(null);
        AppMethodBeat.o(12115);
    }

    public void i() {
        AppMethodBeat.i(12116, false);
        b.a().a().a((u) new cn.missfresh.module.base.network.h()).subscribe(new AnonymousClass1(this));
        AppMethodBeat.o(12116);
    }

    /* renamed from: cn.missfresh.module.search.viewmodule.SearchModule$1  reason: invalid class name */
    class AnonymousClass1 extends i<SearchHotBean.SearchHotListBean> {
        @Override // io.reactivex.v
        public void onComplete() {
        }

        AnonymousClass1(cn.missfresh.module.mvp.a.a aVar) {
            super(aVar);
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(12104, false);
            a((SearchHotBean.SearchHotListBean) obj);
            AppMethodBeat.o(12104);
        }

        public void a(SearchHotBean.SearchHotListBean searchHotListBean) {
            boolean z = false;
            AppMethodBeat.i(12099, false);
            SearchModule.this.h.setValue(searchHotListBean);
            SearchModule.this.d.setValue(searchHotListBean.getHotWords());
            SearchModule.this.f.setValue(Boolean.valueOf(SearchModule.this.i.j() && searchHotListBean.getAlwaysBuy() != null));
            MutableLiveData mutableLiveData = SearchModule.this.g;
            if (SearchModule.this.i.j() && searchHotListBean.getAlwaysBuy() != null) {
                z = true;
            }
            mutableLiveData.setValue(Boolean.valueOf(z));
            AppMethodBeat.o(12099);
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            AppMethodBeat.i(12102, false);
            SearchModule.this.d.setValue(null);
            AppMethodBeat.o(12102);
        }
    }

    public void b(String str) {
        AppMethodBeat.i(12117, false);
        HashMap hashMap = new HashMap();
        hashMap.put("keyword", str);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("param", hashMap);
        b.a().a(hashMap2).b((h) this.j).a((u) new cn.missfresh.module.base.network.h()).subscribe(new AnonymousClass2(this));
        AppMethodBeat.o(12117);
    }

    /* renamed from: cn.missfresh.module.search.viewmodule.SearchModule$2  reason: invalid class name */
    class AnonymousClass2 extends i<AssociatedBean.AssociatedListBean> {
        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
        }

        @Override // io.reactivex.v
        public void onComplete() {
        }

        AnonymousClass2(cn.missfresh.module.mvp.a.a aVar) {
            super(aVar);
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(12109, false);
            a((AssociatedBean.AssociatedListBean) obj);
            AppMethodBeat.o(12109);
        }

        public void a(AssociatedBean.AssociatedListBean associatedListBean) {
            AppMethodBeat.i(12108, false);
            SearchModule.this.a = associatedListBean.requestId;
            SearchModule.this.e.setValue(associatedListBean.getDataList());
            AppMethodBeat.o(12108);
        }
    }

    /* renamed from: cn.missfresh.module.search.viewmodule.SearchModule$3  reason: invalid class name */
    class AnonymousClass3 implements h<AssociatedBean.AssociatedListBean, AssociatedBean.AssociatedListBean> {
        AnonymousClass3() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(12111, false);
            AssociatedBean.AssociatedListBean a = a((AssociatedBean.AssociatedListBean) obj);
            AppMethodBeat.o(12111);
            return a;
        }

        public AssociatedBean.AssociatedListBean a(AssociatedBean.AssociatedListBean associatedListBean) {
            AppMethodBeat.i(12110, false);
            if (!SearchModule.this.i.e()) {
                associatedListBean.getSearchItemProduct().clear();
            }
            AppMethodBeat.o(12110);
            return associatedListBean;
        }
    }

    public int c(String str) {
        AppMethodBeat.i(12118, false);
        if (this.d.getValue() != null) {
            int size = this.d.getValue().size();
            for (int i = 0; i < size; i++) {
                if (TextUtils.equals(this.d.getValue().get(i).value, str)) {
                    AppMethodBeat.o(12118);
                    return i;
                }
            }
        }
        AppMethodBeat.o(12118);
        return -1;
    }

    public String j() {
        AppMethodBeat.i(12119, false);
        StringBuilder sb = new StringBuilder();
        if (!cn.missfresh.utils.b.a(this.d.getValue())) {
            for (int i = 0; i < this.d.getValue().size(); i++) {
                if (i == this.d.getValue().size() - 1) {
                    sb.append(this.d.getValue().get(i).value);
                } else {
                    sb.append(this.d.getValue().get(i).value);
                    sb.append(',');
                }
            }
            String sb2 = sb.toString();
            AppMethodBeat.o(12119);
            return sb2;
        }
        AppMethodBeat.o(12119);
        return "";
    }

    public void a(List<Integer> list) {
        int intValue;
        AppMethodBeat.i(12120, false);
        if (cn.missfresh.utils.b.a(list)) {
            AppMethodBeat.o(12120);
        } else if (this.h.getValue() == null || this.h.getValue().getAlwaysBuy() == null || cn.missfresh.utils.b.a(this.h.getValue().getAlwaysBuy().getProductList())) {
            AppMethodBeat.o(12120);
        } else {
            List<ProductsEntity> productList = this.h.getValue().getAlwaysBuy().getProductList();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            Iterator<Integer> it2 = list.iterator();
            while (it2.hasNext() && (intValue = it2.next().intValue()) < productList.size()) {
                arrayList.add(productList.get(intValue).getSku());
                arrayList2.add(Integer.valueOf(productList.get(intValue).getSkuType()));
                arrayList3.add(productList.get(intValue).getRequestId());
            }
            cn.missfresh.module.base.oftenbuy.c.a.a(this.h.getValue().getAlwaysBuy().getFavourite_id(), this.h.getValue().getAlwaysBuy().getRecommendRequestId(), arrayList3, arrayList, arrayList2);
            AppMethodBeat.o(12120);
        }
    }

    public void b(List<Integer> list) {
        int intValue;
        AppMethodBeat.i(12121, false);
        if (cn.missfresh.utils.b.a(list)) {
            AppMethodBeat.o(12121);
        } else if (this.h.getValue() == null || this.h.getValue().getHotSales() == null || cn.missfresh.utils.b.a(this.h.getValue().getHotSales().getProductList())) {
            AppMethodBeat.o(12121);
        } else {
            List productList = this.h.getValue().getHotSales().getProductList();
            ArrayList arrayList = new ArrayList();
            Iterator<Integer> it2 = list.iterator();
            while (it2.hasNext() && (intValue = it2.next().intValue()) < productList.size()) {
                arrayList.add(((ProductsEntity) productList.get(intValue)).getSku());
            }
            StatisticsManager.o("exposure_list", ai.e, "search_list", "ListID", this.h.getValue().getHotSales().getFirstCategoryCode(), "sku", arrayList);
            AppMethodBeat.o(12121);
        }
    }
}
