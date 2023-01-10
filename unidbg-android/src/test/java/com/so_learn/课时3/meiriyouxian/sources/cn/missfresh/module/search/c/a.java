package cn.missfresh.module.search.c;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.search.a.b;
import cn.missfresh.module.search.interfaces.ISearchInterceptor;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.xiaomi.mipush.sdk.Constants;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SearchInterceptor */
public class a implements ISearchInterceptor {
    private int a = 0;
    private Map<Integer, b> b = new HashMap();

    public void init(Context context) {
    }

    public a() {
        AppMethodBeat.i(11766, false);
        AppMethodBeat.o(11766);
    }

    public void a(ProductsEntity productsEntity) {
        AppMethodBeat.i(11768, false);
        if (k() != null) {
            k().a(productsEntity);
        }
        AppMethodBeat.o(11768);
    }

    @Override // cn.missfresh.module.search.interfaces.ISearchInterceptor
    public void a(FragmentActivity fragmentActivity, int i, String str, int i2) {
        AppMethodBeat.i(11771, false);
        d.c("TAG", "activity add " + fragmentActivity.getClass().getSimpleName() + Constants.ACCEPT_TIME_SEPARATOR_SP + fragmentActivity);
        this.a = this.a + 1;
        b bVar = new b();
        bVar.a(fragmentActivity, i, str, i2);
        this.b.put(Integer.valueOf(this.a), bVar);
        AppMethodBeat.o(11771);
    }

    @Override // cn.missfresh.module.search.interfaces.ISearchInterceptor
    public void a() {
        AppMethodBeat.i(11772, false);
        b remove = this.b.remove(Integer.valueOf(this.a));
        if (remove != null) {
            remove.d();
        }
        this.a--;
        AppMethodBeat.o(11772);
    }

    @Override // cn.missfresh.module.search.interfaces.ISearchInterceptor
    public void b() {
        AppMethodBeat.i(11773, false);
        if (k() != null) {
            k().a();
        }
        AppMethodBeat.o(11773);
    }

    @Override // cn.missfresh.module.search.interfaces.ISearchInterceptor
    public ISearchInterceptor a(boolean z) {
        AppMethodBeat.i(11775, false);
        if (k() != null) {
            k().g = z;
        }
        AppMethodBeat.o(11775);
        return this;
    }

    @Override // cn.missfresh.module.search.interfaces.ISearchInterceptor
    public ISearchInterceptor b(boolean z) {
        AppMethodBeat.i(11776, false);
        if (k() != null) {
            k().h = z;
        }
        AppMethodBeat.o(11776);
        return this;
    }

    @Override // cn.missfresh.module.search.interfaces.ISearchInterceptor
    public LiveData<ProductsEntity> c() {
        AppMethodBeat.i(11777, false);
        if (k() != null) {
            LiveData<ProductsEntity> b = k().b();
            AppMethodBeat.o(11777);
            return b;
        }
        MutableLiveData mutableLiveData = new MutableLiveData();
        AppMethodBeat.o(11777);
        return mutableLiveData;
    }

    @Override // cn.missfresh.module.search.interfaces.ISearchInterceptor
    public LiveData<Integer> d() {
        AppMethodBeat.i(11780, false);
        if (k() != null) {
            LiveData<Integer> c = k().c();
            AppMethodBeat.o(11780);
            return c;
        }
        MutableLiveData mutableLiveData = new MutableLiveData();
        AppMethodBeat.o(11780);
        return mutableLiveData;
    }

    @Override // cn.missfresh.module.search.interfaces.ISearchInterceptor
    public ISearchInterceptor c(boolean z) {
        AppMethodBeat.i(11782, false);
        if (k() != null) {
            k().b = z;
        }
        AppMethodBeat.o(11782);
        return this;
    }

    public boolean e() {
        boolean z = false;
        AppMethodBeat.i(11783, false);
        if (k() == null || k().b) {
            z = true;
        }
        AppMethodBeat.o(11783);
        return z;
    }

    @Override // cn.missfresh.module.search.interfaces.ISearchInterceptor
    public ISearchInterceptor d(boolean z) {
        AppMethodBeat.i(11784, false);
        if (k() != null) {
            k().a = z;
        }
        AppMethodBeat.o(11784);
        return this;
    }

    public boolean f() {
        boolean z = false;
        AppMethodBeat.i(11786, false);
        if (k() == null || k().a) {
            z = true;
        }
        AppMethodBeat.o(11786);
        return z;
    }

    @Override // cn.missfresh.module.search.interfaces.ISearchInterceptor
    public ISearchInterceptor e(boolean z) {
        AppMethodBeat.i(11788, false);
        if (k() != null) {
            k().d = z;
        }
        AppMethodBeat.o(11788);
        return this;
    }

    public boolean g() {
        boolean z = false;
        AppMethodBeat.i(11790, false);
        if (k() == null || k().d) {
            z = true;
        }
        AppMethodBeat.o(11790);
        return z;
    }

    @Override // cn.missfresh.module.search.interfaces.ISearchInterceptor
    public ISearchInterceptor f(boolean z) {
        AppMethodBeat.i(11791, false);
        if (k() != null) {
            k().e = z;
        }
        AppMethodBeat.o(11791);
        return this;
    }

    @Override // cn.missfresh.module.search.interfaces.ISearchInterceptor
    public ISearchInterceptor g(boolean z) {
        AppMethodBeat.i(11793, false);
        if (k() != null) {
            k().c = z;
        }
        AppMethodBeat.o(11793);
        return this;
    }

    @Override // cn.missfresh.module.search.interfaces.ISearchInterceptor
    public ISearchInterceptor h(boolean z) {
        AppMethodBeat.i(11796, false);
        if (k() != null) {
            k().f = z;
        }
        AppMethodBeat.o(11796);
        return this;
    }

    public boolean h() {
        boolean z = false;
        AppMethodBeat.i(11801, false);
        if (k() == null || k().c) {
            z = true;
        }
        AppMethodBeat.o(11801);
        return z;
    }

    public boolean i() {
        boolean z = false;
        AppMethodBeat.i(11802, false);
        if (k() == null || k().e) {
            z = true;
        }
        AppMethodBeat.o(11802);
        return z;
    }

    public boolean j() {
        boolean z = false;
        AppMethodBeat.i(11803, false);
        if (k() == null || k().g) {
            z = true;
        }
        AppMethodBeat.o(11803);
        return z;
    }

    private b k() {
        AppMethodBeat.i(11805, false);
        b bVar = this.b.get(Integer.valueOf(this.a));
        AppMethodBeat.o(11805);
        return bVar;
    }
}
