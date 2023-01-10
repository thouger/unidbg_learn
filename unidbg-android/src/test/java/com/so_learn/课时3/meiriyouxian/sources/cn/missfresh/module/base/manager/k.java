package cn.missfresh.module.base.manager;

import androidx.lifecycle.MutableLiveData;
import cn.missfresh.module.base.common.providers.IRecommendSwitchService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: RecommendManager */
public class k {
    private k() {
    }

    /* compiled from: RecommendManager */
    private static class b {
        private static k a = new k();

        static {
            AppMethodBeat.i(14526, false);
            AppMethodBeat.o(14526);
        }
    }

    public static k a() {
        AppMethodBeat.i(14527, false);
        k kVar = b.a;
        AppMethodBeat.o(14527);
        return kVar;
    }

    /* compiled from: RecommendManager */
    private static class a {
        private static IRecommendSwitchService a = ((IRecommendSwitchService) com.alibaba.android.arouter.b.a.a().a("/user/recommend_service").navigation());

        static {
            AppMethodBeat.i(14525, false);
            AppMethodBeat.o(14525);
        }
    }

    public MutableLiveData<Boolean> b() {
        AppMethodBeat.i(14528, false);
        if (a.a != null) {
            MutableLiveData<Boolean> a2 = a.a.a();
            AppMethodBeat.o(14528);
            return a2;
        }
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        AppMethodBeat.o(14528);
        return mutableLiveData;
    }

    public void a(boolean z) {
        AppMethodBeat.i(14529, false);
        if (a.a == null) {
            AppMethodBeat.o(14529);
            return;
        }
        if (a.a.a() != null) {
            a.a.a().setValue(Boolean.valueOf(z));
        }
        AppMethodBeat.o(14529);
    }
}
