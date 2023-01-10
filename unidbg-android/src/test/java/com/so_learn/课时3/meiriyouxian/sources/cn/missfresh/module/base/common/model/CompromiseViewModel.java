package cn.missfresh.module.base.common.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cn.missfresh.module.mvvm.BaseViewModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class CompromiseViewModel extends BaseViewModel {
    private MutableLiveData<String> a = new MutableLiveData<>();

    public CompromiseViewModel() {
        AppMethodBeat.i(12239, false);
        AppMethodBeat.o(12239);
    }

    public LiveData<String> a() {
        return this.a;
    }

    public void a(String str) {
        AppMethodBeat.i(12240, false);
        this.a.setValue(str);
        AppMethodBeat.o(12240);
    }
}
