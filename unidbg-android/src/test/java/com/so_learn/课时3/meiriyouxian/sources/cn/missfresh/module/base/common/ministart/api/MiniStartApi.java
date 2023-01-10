package cn.missfresh.module.base.common.ministart.api;

import cn.missfresh.lib.a.a;
import cn.missfresh.module.base.bean.UserConfig;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.common.ministart.bean.RefreshTokenBean;
import io.reactivex.q;
import java.util.Map;
import retrofit2.a.o;

public interface MiniStartApi {
    @o(a = MiniStartApiConst.GET_USER_CONFIG)
    @a(a = "data")
    q<UserConfig> getUserConfig(@retrofit2.a.a RequestParam<Map> requestParam);

    @o(a = MiniStartApiConst.REFRESH_TOKEN)
    @a(a = "data")
    q<RefreshTokenBean> requestRefreshToken();

    @o(a = MiniStartApiConst.SET_USER_CONFIG)
    @a(a = "data")
    q<Object> setUserConfig(@retrofit2.a.a RequestParam<Map> requestParam);
}
