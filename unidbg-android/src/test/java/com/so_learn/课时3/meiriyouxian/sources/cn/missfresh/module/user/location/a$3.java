package cn.missfresh.module.user.location;

import cn.missfresh.module.base.bean.TencentSearchData;
import cn.missfresh.module.base.manager.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.xiaomi.mipush.sdk.Constants;
import java.util.List;

/* compiled from: DefaultAddressManager */
class a$3 implements q.b {
    final /* synthetic */ a a;

    a$3(a aVar) {
        this.a = aVar;
    }

    @Override // cn.missfresh.module.base.manager.q.b
    public void a(List<TencentSearchData> list) {
        String str;
        String str2;
        AppMethodBeat.i(7292, false);
        if (b.a(list)) {
            a(1);
            d.d("DefaultAddressManager", "TencentSearchData is null");
            a.a(this.a, 11, "TencentSearchData is null");
        } else {
            TencentSearchData tencentSearchData = list.get(0);
            TencentSearchData.Location location = tencentSearchData.location;
            String str3 = "";
            if (location == null) {
                str = str3;
            } else {
                str = String.valueOf(location.lat);
            }
            if (location == null) {
                str2 = str3;
            } else {
                str2 = String.valueOf(location.lng);
            }
            String str4 = tencentSearchData.ad_info == null ? str3 : tencentSearchData.ad_info.city;
            String str5 = tencentSearchData.ad_info == null ? str3 : tencentSearchData.title;
            if (tencentSearchData.ad_info != null) {
                str3 = tencentSearchData.ad_info.adcode;
            }
            a.a(this.a, str4, str, str2, str5, str3);
            d.d("DefaultAddressManager", "TencentSearchData success");
            a aVar = this.a;
            a.a(aVar, 11, "poi:" + str + Constants.ACCEPT_TIME_SEPARATOR_SP + str2 + Constants.ACCEPT_TIME_SEPARATOR_SP + str3);
        }
        AppMethodBeat.o(7292);
    }

    @Override // cn.missfresh.module.base.manager.q.b
    public void a(int i) {
        AppMethodBeat.i(7294, false);
        a.c(this.a);
        d.d("DefaultAddressManager", "TencentSearchData onSearchFailed");
        a.a(this.a, 11, "onSearchFailed");
        AppMethodBeat.o(7294);
    }
}
