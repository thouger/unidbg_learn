package cn.missfresh.module.user.mine.a;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import cn.missfresh.module.base.common.providers.IRecommendSwitchService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: RecommendSwitchService */
public class a implements IRecommendSwitchService {
    private MutableLiveData<Boolean> a = new MutableLiveData<>();

    public void init(Context context) {
    }

    public a() {
        AppMethodBeat.i(8799, false);
        AppMethodBeat.o(8799);
    }

    @Override // cn.missfresh.module.base.common.providers.IRecommendSwitchService
    public MutableLiveData<Boolean> a() {
        return this.a;
    }
}
