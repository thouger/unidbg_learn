package cn.missfresh.module.base.common.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class CartLocationModel extends ViewModel {
    private MutableLiveData<int[]> a = new MutableLiveData<>();
    private MutableLiveData<Boolean> b = new MutableLiveData<>();
    private MutableLiveData<Boolean> c = new MutableLiveData<>();
    private MutableLiveData<Boolean> d = new MutableLiveData<>();

    public CartLocationModel() {
        AppMethodBeat.i(12219, false);
        AppMethodBeat.o(12219);
    }

    public LiveData<Boolean> a() {
        return this.c;
    }

    public void a(boolean z) {
        AppMethodBeat.i(12220, false);
        this.d.setValue(Boolean.valueOf(z));
        AppMethodBeat.o(12220);
    }

    public LiveData<Boolean> b() {
        return this.b;
    }

    public void b(boolean z) {
        AppMethodBeat.i(12222, false);
        this.b.setValue(Boolean.valueOf(z));
        AppMethodBeat.o(12222);
    }

    public LiveData<int[]> c() {
        return this.a;
    }

    public void a(int[] iArr) {
        AppMethodBeat.i(12223, false);
        this.a.postValue(iArr);
        AppMethodBeat.o(12223);
    }
}
