package cn.missfresh.buttomline.logtrace.d;

import cn.missfresh.buttomline.logtrace.bean.RequestBean;
import cn.missfresh.buttomline.logtrace.bean.ResponseBean;
import cn.missfresh.lib.a.a;
import io.reactivex.q;
import retrofit2.a.i;
import retrofit2.a.o;

/* compiled from: LogTraceApi */
public interface c {
    @o(a = "/api/logtrace/collect")
    @a(a = "data", c = "message")
    q<ResponseBean> a(@i(a = "request-id") String str, @i(a = "encrypted") String str2, @i(a = "sign") String str3, @i(a = "gzip") String str4, @retrofit2.a.a RequestBean requestBean);
}
