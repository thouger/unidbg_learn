package cn.missfresh.module.search.interfaces;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import cn.missfresh.module.base.bean.ProductsEntity;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface ISearchInterceptor extends IProvider {
    ISearchInterceptor a(boolean z);

    void a();

    void a(FragmentActivity fragmentActivity, int i, String str, int i2);

    ISearchInterceptor b(boolean z);

    void b();

    LiveData<ProductsEntity> c();

    ISearchInterceptor c(boolean z);

    LiveData<Integer> d();

    ISearchInterceptor d(boolean z);

    ISearchInterceptor e(boolean z);

    ISearchInterceptor f(boolean z);

    ISearchInterceptor g(boolean z);

    ISearchInterceptor h(boolean z);
}
