package cn.missfresh.buttomline.abtest.net;

import cn.missfresh.buttomline.abtest.bean.AbtestRequestDataBean;
import cn.missfresh.buttomline.abtest.bean.Data;
import cn.missfresh.buttomline.abtest.bean.Plan;
import cn.missfresh.lib.a.a;
import io.reactivex.q;
import java.util.List;
import retrofit2.a.i;
import retrofit2.a.o;

public interface AbtestApi {
    @o(a = "/abtest/getData")
    @a(a = "data")
    q<Data> getAbtestData(@retrofit2.a.a AbtestRequestDataBean abtestRequestDataBean);

    @o(a = "/api/abtest/config")
    @a(a = "data", c = "message")
    q<List<Plan>> getAbtestResult(@i(a = "request-id") String str, @i(a = "encrypted") String str2, @i(a = "sign") String str3, @i(a = "gzip") String str4, @retrofit2.a.a AbtestRequestParam abtestRequestParam);
}
