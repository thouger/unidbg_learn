package cn.missfresh.module.user.login.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.mvvm.BaseViewModel;
import cn.missfresh.module.user.login.api.QuickLoginApiManager;
import cn.missfresh.module.user.login.bean.QuickLoginBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import io.reactivex.f.a;
import java.util.HashMap;

public class QuickLoginViewModel extends BaseViewModel {
    private MutableLiveData<Integer> a = new MutableLiveData<>();

    public QuickLoginViewModel() {
        AppMethodBeat.i(8720, false);
        AppMethodBeat.o(8720);
    }

    public LiveData<Integer> a() {
        return this.a;
    }

    public void a(String str, String str2) {
        AppMethodBeat.i(8727, false);
        HashMap hashMap = new HashMap();
        hashMap.put("thirdToken", str);
        hashMap.put("thirdAccessToken", str2);
        RequestParam requestParam = new RequestParam();
        requestParam.setParam(hashMap);
        QuickLoginApiManager.getQuickLoginApi().loginByQuick(requestParam).b(a.b()).a(io.reactivex.a.b.a.a()).subscribe(new AnonymousClass1(this));
        AppMethodBeat.o(8727);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.login.viewmodel.QuickLoginViewModel$1  reason: invalid class name */
    public class AnonymousClass1 extends i<QuickLoginBean> {
        @Override // io.reactivex.v
        public void onComplete() {
        }

        AnonymousClass1(cn.missfresh.module.mvp.a.a aVar) {
            super(aVar);
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(8716, false);
            a((QuickLoginBean) obj);
            AppMethodBeat.o(8716);
        }

        public void a(QuickLoginBean quickLoginBean) {
            AppMethodBeat.i(8707, false);
            if (quickLoginBean == null || b.a(quickLoginBean.getAccessToken())) {
                QuickLoginViewModel.this.a.setValue(0);
            } else {
                e.g(quickLoginBean.getAccessToken());
                cn.missfresh.module.user.login.b.a.a().a(0);
                QuickLoginViewModel.this.a.setValue(1);
            }
            AppMethodBeat.o(8707);
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            AppMethodBeat.i(8713, false);
            QuickLoginViewModel.this.a.setValue(0);
            cn.missfresh.ui.a.a.a(str);
            cn.missfresh.module.user.login.d.a.c(i + "", str);
            AppMethodBeat.o(8713);
        }
    }
}
