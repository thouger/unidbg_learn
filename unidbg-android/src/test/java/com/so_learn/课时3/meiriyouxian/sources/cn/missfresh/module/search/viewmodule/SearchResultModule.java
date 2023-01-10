package cn.missfresh.module.search.viewmodule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cn.missfresh.module.base.bean.BasinessForm;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.mvvm.BaseViewModel;
import cn.missfresh.module.search.a.c;
import cn.missfresh.module.search.adapter.bean.QuickFilterBean;
import cn.missfresh.module.search.adapter.bean.SearchBaseBean;
import cn.missfresh.module.search.adapter.bean.SearchBusinessForm;
import cn.missfresh.module.search.adapter.bean.SearchEmptyBean;
import cn.missfresh.module.search.adapter.bean.SearchLikeTitleBean;
import cn.missfresh.module.search.adapter.bean.SearchResultBean;
import cn.missfresh.module.search.adapter.bean.SearchSellerTypePos;
import cn.missfresh.module.search.adapter.bean.SearchSloganBean;
import cn.missfresh.module.search.b.b;
import cn.missfresh.module.search.c.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.reactivex.c.h;
import io.reactivex.u;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResultModule extends BaseViewModel {
    public String a;
    public String b;
    public String c;
    public int d = Integer.MAX_VALUE;
    public String e;
    public String f = "";
    public int g;
    public int h = 0;
    public boolean i;
    public boolean j;
    private boolean k;
    private MutableLiveData<Integer> l = new MutableLiveData<>();
    private MutableLiveData<String> m = new MutableLiveData<>();
    private MutableLiveData<List<SearchBaseBean>> n = new MutableLiveData<>();
    private MutableLiveData<Map<String, String>> o = new MutableLiveData<>();
    private MutableLiveData<List<QuickFilterBean>> p = new MutableLiveData<>();
    private MutableLiveData<List<BasinessForm>> q = new MutableLiveData<>();
    private MutableLiveData<List<SearchSellerTypePos>> r = new MutableLiveData<>();
    private a s = ((a) com.alibaba.android.arouter.b.a.a().a("/search/interceptor").navigation());
    private h<SearchResultBean.SearchResultListBean, List<SearchBaseBean>> t = new AnonymousClass2();

    public SearchResultModule() {
        AppMethodBeat.i(12127, false);
        AppMethodBeat.o(12127);
    }

    public LiveData<String> a() {
        return this.m;
    }

    public LiveData<Integer> b() {
        return this.l;
    }

    public LiveData<List<SearchBaseBean>> c() {
        return this.n;
    }

    public LiveData<Map<String, String>> d() {
        return this.o;
    }

    public LiveData<List<QuickFilterBean>> e() {
        return this.p;
    }

    public LiveData<List<BasinessForm>> f() {
        return this.q;
    }

    public MutableLiveData<List<SearchSellerTypePos>> g() {
        return this.r;
    }

    public void a(int i, String str) {
        boolean z = false;
        AppMethodBeat.i(12128, false);
        if (i == 1 || i == 2) {
            z = true;
        }
        this.k = z;
        HashMap hashMap = new HashMap();
        hashMap.put("quickFilterId", str);
        hashMap.put("sortType", Integer.valueOf(this.h));
        hashMap.put("filterType", Integer.valueOf(i));
        hashMap.put("keyword", this.e);
        hashMap.put("sellerType", Integer.valueOf(g.a().l()));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("param", hashMap);
        b.a().b(hashMap2).b((h) this.t).a((u) new cn.missfresh.module.base.network.h()).subscribe(new AnonymousClass1(this));
        AppMethodBeat.o(12128);
    }

    /* renamed from: cn.missfresh.module.search.viewmodule.SearchResultModule$1  reason: invalid class name */
    class AnonymousClass1 extends i<List<SearchBaseBean>> {
        @Override // io.reactivex.v
        public void onComplete() {
        }

        AnonymousClass1(cn.missfresh.module.mvp.a.a aVar) {
            super(aVar);
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(12124, false);
            a((List) obj);
            AppMethodBeat.o(12124);
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            AppMethodBeat.i(12122, false);
            SearchResultModule searchResultModule = SearchResultModule.this;
            searchResultModule.a = "";
            searchResultModule.b = "";
            searchResultModule.l.setValue(1);
            SearchResultModule.this.m.setValue(str);
            SearchResultModule.this.d = Integer.MAX_VALUE;
            AppMethodBeat.o(12122);
        }

        public void a(List<SearchBaseBean> list) {
            AppMethodBeat.i(12123, false);
            SearchResultModule.this.l.setValue(0);
            SearchResultModule.this.n.setValue(list);
            AppMethodBeat.o(12123);
        }
    }

    /* renamed from: cn.missfresh.module.search.viewmodule.SearchResultModule$2  reason: invalid class name */
    class AnonymousClass2 implements h<SearchResultBean.SearchResultListBean, List<SearchBaseBean>> {
        AnonymousClass2() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(12126, false);
            List<SearchBaseBean> a = a((SearchResultBean.SearchResultListBean) obj);
            AppMethodBeat.o(12126);
            return a;
        }

        public List<SearchBaseBean> a(SearchResultBean.SearchResultListBean searchResultListBean) {
            int i = 0;
            AppMethodBeat.i(12125, false);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            HashMap hashMap = new HashMap(2);
            if (searchResultListBean != null) {
                hashMap.put("filterType", String.valueOf(searchResultListBean.showFilter));
                hashMap.put("filterText", searchResultListBean.chromeFilterContent);
                if (!cn.missfresh.utils.b.a(searchResultListBean.cue) || !cn.missfresh.utils.b.a(searchResultListBean.slogan)) {
                    SearchSloganBean searchSloganBean = new SearchSloganBean(searchResultListBean.slogan, searchResultListBean.cue);
                    if (cn.missfresh.utils.b.a(searchResultListBean.searchItemList) || cn.missfresh.utils.b.a(((SearchResultBean.SearchResultItemBean) searchResultListBean.searchItemList.get(0)).getSearchList())) {
                        searchSloganBean.setType(7);
                    } else {
                        searchSloganBean.setType(4);
                    }
                    arrayList.add(searchSloganBean);
                }
                if (!cn.missfresh.utils.b.a(searchResultListBean.searchItemList)) {
                    SearchResultModule.this.b = searchResultListBean.searchRequestId;
                    int i2 = 0;
                    int i3 = 0;
                    while (i2 < searchResultListBean.searchItemList.size()) {
                        if (!cn.missfresh.utils.b.a(((SearchResultBean.SearchResultItemBean) searchResultListBean.searchItemList.get(i2)).getSearchList())) {
                            i3 += ((SearchResultBean.SearchResultItemBean) searchResultListBean.searchItemList.get(i2)).getSearchList().size();
                            SearchBusinessForm searchBusinessForm = new SearchBusinessForm();
                            searchBusinessForm.sellerType = ((SearchResultBean.SearchResultItemBean) searchResultListBean.searchItemList.get(i2)).businessForms.getSellerType();
                            searchBusinessForm.title = ((SearchResultBean.SearchResultItemBean) searchResultListBean.searchItemList.get(i2)).businessForms.getTitle();
                            searchBusinessForm.subTitle = ((SearchResultBean.SearchResultItemBean) searchResultListBean.searchItemList.get(i2)).businessForms.getSubTitle();
                            searchBusinessForm.visible = i2 == 0 ? 1 : 0;
                            arrayList.add(searchBusinessForm);
                            SearchSellerTypePos searchSellerTypePos = new SearchSellerTypePos();
                            searchSellerTypePos.basinessForm = ((SearchResultBean.SearchResultItemBean) searchResultListBean.searchItemList.get(i2)).businessForms;
                            searchSellerTypePos.position = arrayList.size();
                            arrayList2.add(searchSellerTypePos);
                            arrayList.addAll(((SearchResultBean.SearchResultItemBean) searchResultListBean.searchItemList.get(i2)).getSearchList());
                        }
                        i2++;
                    }
                    SearchResultModule.this.r.postValue(arrayList2);
                    i = i3;
                } else {
                    SearchResultModule.this.b = "";
                    arrayList.add(0, new SearchEmptyBean());
                    if (!SearchResultModule.this.k) {
                        hashMap.put("filterType", "0");
                    }
                }
                if (!SearchResultModule.this.f.equals(SearchResultModule.this.e) || SearchResultModule.this.g != g.a().l()) {
                    SearchResultModule.this.o.postValue(hashMap);
                    if (!cn.missfresh.utils.b.a(searchResultListBean.quickFilter)) {
                        SearchResultModule.this.p.postValue(searchResultListBean.quickFilter);
                    } else {
                        SearchResultModule.this.p.postValue(null);
                    }
                }
                if (!cn.missfresh.utils.b.a(searchResultListBean.businessForms)) {
                    SearchResultModule.this.q.postValue(searchResultListBean.businessForms);
                } else {
                    SearchResultModule.this.q.postValue(null);
                }
                SearchResultModule.this.d = arrayList.size();
                if (searchResultListBean.likeResult != null) {
                    if (cn.missfresh.utils.b.a(searchResultListBean.likeResult.getSearchList())) {
                        SearchResultModule.this.d = Integer.MAX_VALUE;
                    } else {
                        arrayList.add(new SearchLikeTitleBean());
                        arrayList.addAll(searchResultListBean.likeResult.getSearchList());
                    }
                    SearchResultModule.this.a = searchResultListBean.likeResult.requestId;
                } else {
                    SearchResultModule searchResultModule = SearchResultModule.this;
                    searchResultModule.d = Integer.MAX_VALUE;
                    searchResultModule.a = "";
                }
                c.b(SearchResultModule.this.e, SearchResultModule.this.b, i, SearchResultModule.this.c);
                SearchResultModule.this.c = searchResultListBean.searchRequestId;
            }
            if (!SearchResultModule.this.s.i()) {
                for (int size = arrayList.size() - 1; size > -1; size--) {
                    if (((SearchBaseBean) arrayList.get(size)).getMultiType() == 1 && ((SearchResultBean) arrayList.get(size)).getSell_out()) {
                        arrayList.remove(size);
                    }
                }
            }
            SearchResultModule searchResultModule2 = SearchResultModule.this;
            searchResultModule2.f = searchResultModule2.e;
            SearchResultModule.this.g = g.a().l();
            AppMethodBeat.o(12125);
            return arrayList;
        }
    }
}
