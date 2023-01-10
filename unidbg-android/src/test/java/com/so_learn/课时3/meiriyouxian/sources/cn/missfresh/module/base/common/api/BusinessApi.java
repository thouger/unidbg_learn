package cn.missfresh.module.base.common.api;

import cn.missfresh.lib.a.a;
import cn.missfresh.module.base.bean.AdvertInfoBean;
import cn.missfresh.module.base.common.resourcemanager.bean.SkipWrapBean;
import com.alibaba.fastjson.JSONObject;
import io.reactivex.q;
import java.util.List;
import java.util.Map;
import retrofit2.a.f;
import retrofit2.a.k;
import retrofit2.a.o;
import retrofit2.a.t;

public interface BusinessApi {
    @f(a = "/web20/system/advertInfo")
    q<AdvertInfoBean> advertInfo(@t(a = "isShow") String str, @t(a = "isFirstInto") boolean z);

    @o(a = "/ms/rmp/getResourceList")
    @k(a = {"CONNECT_TIMEOUT:3000", "READ_TIMEOUT:3000", "WRITE_TIMEOUT:3000"})
    @a(a = "data")
    q<List<SkipWrapBean>> openApp(@retrofit2.a.a Map<String, Object> map);

    @o(a = "/client_report/app/android/#oldURL")
    q<String> reportClient(@retrofit2.a.a JSONObject jSONObject);

    @o(a = "/as/channel/openApp/device/report/android#oldURL")
    @a(a = "data")
    q<Long> reportDeviceInfo(@retrofit2.a.a JSONObject jSONObject);
}
