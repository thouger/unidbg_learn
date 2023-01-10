package cn.missfresh.module.user.mine.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.common.ministart.api.MiniStartApiManager;
import cn.missfresh.module.base.network.h;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.mvp.a.a;
import cn.missfresh.module.mvvm.BaseViewModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.taobao.accs.common.Constants;
import io.reactivex.u;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserConfigViewModel extends BaseViewModel {
    private MutableLiveData<String> a = new MutableLiveData<>();
    private MutableLiveData<Integer> b = new MutableLiveData<>();

    public UserConfigViewModel() {
        AppMethodBeat.i(8880, false);
        AppMethodBeat.o(8880);
    }

    public LiveData<String> a() {
        return this.a;
    }

    public LiveData<Integer> b() {
        return this.b;
    }

    public void a(int i) {
        AppMethodBeat.i(8888, false);
        RequestParam<Map> requestParam = new RequestParam<>();
        HashMap hashMap = new HashMap(3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i));
        hashMap.put("typeId", 1);
        hashMap.put(Constants.KEY_BUSINESSID, 1);
        hashMap.put("statusId", arrayList);
        requestParam.setParam(hashMap);
        MiniStartApiManager.getMiniStartApi().setUserConfig(requestParam).a((u<? super Object, ? extends R>) new h()).subscribe(new AnonymousClass1(this));
        AppMethodBeat.o(8888);
    }

    /* renamed from: cn.missfresh.module.user.mine.model.UserConfigViewModel$1  reason: invalid class name */
    class AnonymousClass1 extends i<Object> {
        @Override // io.reactivex.v
        public void onComplete() {
        }

        AnonymousClass1(a aVar) {
            super(aVar);
        }

        @Override // io.reactivex.v
        public void onNext(Object obj) {
            AppMethodBeat.i(8870, false);
            UserConfigViewModel.this.b.setValue(1);
            AppMethodBeat.o(8870);
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            AppMethodBeat.i(8874, false);
            UserConfigViewModel.this.a.setValue(str);
            AppMethodBeat.o(8874);
        }
    }
}
