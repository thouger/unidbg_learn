package cn.missfresh.module.base.datastatistics;

import cn.missfresh.module.base.datastatistics.bean.DataStatisticsMryxBean;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import io.reactivex.q;
import retrofit2.a.o;

/* compiled from: NewDataStatisticsAPI */
public interface a {
    @o(a = NotificationIconUtil.SPLIT_CHAR)
    q<String> a(@retrofit2.a.a DataStatisticsMryxBean dataStatisticsMryxBean);
}
