package cn.missfresh.module.search.viewmodule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cn.missfresh.module.mvvm.BaseViewModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SearchChangeModule extends BaseViewModel {
    private MutableLiveData<String> a = new MutableLiveData<>();
    private MutableLiveData<Integer> b = new MutableLiveData<>();

    public SearchChangeModule() {
        AppMethodBeat.i(12091, false);
        AppMethodBeat.o(12091);
    }

    public LiveData<String> a() {
        return this.a;
    }

    public void a(String str) {
        AppMethodBeat.i(12093, false);
        this.a.setValue(str);
        AppMethodBeat.o(12093);
    }

    public LiveData<Integer> b() {
        return this.b;
    }

    public void c() {
        AppMethodBeat.i(12095, false);
        this.b.setValue(2);
        AppMethodBeat.o(12095);
    }

    public void d() {
        AppMethodBeat.i(12097, false);
        this.b.setValue(1);
        AppMethodBeat.o(12097);
    }

    public void e() {
        AppMethodBeat.i(12098, false);
        this.b.setValue(0);
        AppMethodBeat.o(12098);
    }
}
