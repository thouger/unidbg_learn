package cn.missfresh.module.user.mine.model;

import androidx.lifecycle.MutableLiveData;
import cn.missfresh.module.mvvm.BaseViewModel;
import cn.missfresh.module.user.mine.bean.PrivacyNoticeData;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class FlutterMineModel extends BaseViewModel {
    private MutableLiveData<PrivacyNoticeData> a = new MutableLiveData<>();

    public FlutterMineModel() {
        AppMethodBeat.i(8823, false);
        AppMethodBeat.o(8823);
    }
}
