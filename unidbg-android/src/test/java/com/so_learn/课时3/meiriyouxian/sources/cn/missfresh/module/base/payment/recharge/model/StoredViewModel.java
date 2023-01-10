package cn.missfresh.module.base.payment.recharge.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.mvp.a.a;
import cn.missfresh.module.mvvm.BaseViewModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;

public class StoredViewModel extends BaseViewModel {
    private MutableLiveData<String> a = new MutableLiveData<>();
    private MutableLiveData<String> b = new MutableLiveData<>();

    public void a(String str) {
        JniLib.cV(this, str, 428);
    }

    public StoredViewModel() {
        AppMethodBeat.i(17024, false);
        AppMethodBeat.o(17024);
    }

    public LiveData<String> a() {
        return this.a;
    }

    public LiveData<String> b() {
        return this.b;
    }

    /* renamed from: cn.missfresh.module.base.payment.recharge.model.StoredViewModel$1  reason: invalid class name */
    class AnonymousClass1 extends i<String> {
        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            JniLib.cV(this, Integer.valueOf(i), str, 425);
        }

        public void a(String str) {
            JniLib.cV(this, str, 426);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            JniLib.cV(this, 427);
        }

        AnonymousClass1(a aVar) {
            super(aVar);
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(17019, false);
            a((String) obj);
            AppMethodBeat.o(17019);
        }
    }
}
