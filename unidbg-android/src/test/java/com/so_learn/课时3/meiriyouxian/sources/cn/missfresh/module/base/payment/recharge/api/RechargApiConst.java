package cn.missfresh.module.base.payment.recharge.api;

import com.bangcle.andjni.JniLib;

public class RechargApiConst {
    public static final String CHECK_PHHONE = "v1/recharge/verifyPhoneNum";
    public static final String RECHARG_STATUS = "as/order/check/rechargestatus";

    public RechargApiConst() {
        JniLib.cV(this, 335);
    }
}
