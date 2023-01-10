package cn.missfresh.module.base.network.a;

import android.text.TextUtils;
import cn.missfresh.module.base.bean.UserLocationBean;
import cn.missfresh.module.base.manager.b;
import cn.missfresh.module.base.manager.c;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.module.base.utils.v;
import cn.missfresh.risk.h;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.bangcle.andjni.JniLib;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: PostBodyInterceptor */
public class e implements Interceptor {
    public e() {
        JniLib.cV(this, 45);
    }

    private void a(Request request, Request.Builder builder) throws IOException {
        JniLib.cV(this, request, builder, 46);
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        return (Response) JniLib.cL(this, chain, 44);
    }

    public static HashMap<String, Object> a(HttpUrl httpUrl) {
        List parseArray;
        AppMethodBeat.i(15927, false);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("accessToken", cn.missfresh.module.base.manager.e.n());
        hashMap.put("addressCode", c.h());
        hashMap.put("stationCode", c.o());
        hashMap.put("bigWarehouse", c.p());
        hashMap.put("sellerId", c.r());
        try {
            if (!TextUtils.isEmpty(c.s()) && (parseArray = JSON.parseArray(c.s(), UserLocationBean.SellerInfo.class)) != null) {
                hashMap.put("sellerInfoList", parseArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int l = c.l();
        int l2 = b.l();
        if (l == 0 && l2 == 2) {
            l = 3;
        }
        hashMap.put("deliveryType", String.valueOf(l));
        hashMap.put("platform", "android");
        hashMap.put("version", d.a().e(httpUrl));
        hashMap.put("tdk", "");
        hashMap.put(com.umeng.analytics.pro.c.aw, r.l());
        hashMap.put("screenHeight", String.valueOf(aw.b()));
        hashMap.put("screenWidth", String.valueOf(aw.a()));
        hashMap.put("androidChannelValue", cn.missfresh.module.base.manager.e.s());
        hashMap.put("androidId", r.i());
        hashMap.put("devtk", h.b());
        hashMap.put("oaid", r.c);
        hashMap.put("vaid", r.a);
        hashMap.put("aaid", r.b);
        hashMap.put("deviceCenterId", v.a());
        AppMethodBeat.o(15927);
        return hashMap;
    }
}
