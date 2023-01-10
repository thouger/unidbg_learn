package cn.missfresh.risk.api;

import cn.missfresh.risk.bean.RiskCcgReqParam;
import cn.missfresh.risk.bean.RiskJsonBean;
import cn.missfresh.risk.bean.RiskReqParam;
import io.reactivex.q;
import retrofit2.a.a;
import retrofit2.a.i;
import retrofit2.a.o;

public interface RiskApi {
    @o(a = RiskApiConst.RISK_TOKEN)
    q<RiskJsonBean> getRiskToken(@i(a = "User-Agent") String str, @a RequestParam<RiskReqParam> requestParam);

    @o(a = RiskApiConst.RISK_UP_LOAD_CCG)
    q<RiskJsonBean> upRiskCcg(@i(a = "User-Agent") String str, @a RequestParam<RiskCcgReqParam> requestParam);
}
