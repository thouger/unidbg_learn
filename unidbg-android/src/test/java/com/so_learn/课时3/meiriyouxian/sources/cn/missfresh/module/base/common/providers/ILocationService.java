package cn.missfresh.module.base.common.providers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cn.missfresh.location_api.StickyLiveData;
import cn.missfresh.module.base.bean.ConfigurationBean;
import cn.missfresh.module.base.bean.EventAppAddress;
import cn.missfresh.module.base.bean.TencentSearchData;
import com.alibaba.android.arouter.facade.template.IProvider;
import java.util.List;

public interface ILocationService extends IProvider {

    public interface a {
        void a(boolean z, String str, List<TencentSearchData> list);
    }

    cn.missfresh.location.a a();

    void a(int i);

    void a(String str, String str2, a aVar);

    LiveData<Integer> b();

    void b(int i);

    LiveData<ConfigurationBean.UpgradeBean> c();

    StickyLiveData<EventAppAddress> d();

    LiveData<String> e();

    LiveData<List<String>> f();

    LiveData<ConfigurationBean> g();

    MutableLiveData<String> h();
}
