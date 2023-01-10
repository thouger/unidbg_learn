package cn.missfresh.module.base.common.resourcemanager.api;

import cn.missfresh.lib.a.a;
import cn.missfresh.module.base.common.resourcemanager.bean.SkipWrapBean;
import io.reactivex.q;
import java.util.List;
import java.util.Map;
import retrofit2.a.o;

public interface RMApi {
    @o(a = "/ms/rmp/getResourceList")
    @a(a = "data")
    q<List<SkipWrapBean>> getResourceList(@retrofit2.a.a Map<String, Object> map);
}
