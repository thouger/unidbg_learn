package cn.missfresh.module.base.datastatistics;

import cn.missfresh.module.base.datastatistics.bean.DataStatisticsMryxBean;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import io.reactivex.q;
import retrofit2.a.a;
import retrofit2.a.o;

/* compiled from: NewDataStatisticsAPI2 */
public interface b {
    @o(a = NotificationIconUtil.SPLIT_CHAR)
    q<String> a(@a DataStatisticsMryxBean dataStatisticsMryxBean);
}
