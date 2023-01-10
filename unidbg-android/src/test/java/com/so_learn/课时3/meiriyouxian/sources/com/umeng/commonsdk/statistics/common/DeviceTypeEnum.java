package com.umeng.commonsdk.statistics.common;

import com.taobao.accs.common.Constants;
import com.umeng.message.MsgConstant;
import com.unionpay.tsmservice.mi.data.Constant;

public enum DeviceTypeEnum {
    IMEI(Constants.KEY_IMEI, Constants.KEY_IMEI),
    OAID("oaid", "oaid"),
    ANDROIDID("android_id", "android_id"),
    MAC(Constant.KEY_MAC, Constant.KEY_MAC),
    SERIALNO(MsgConstant.KEY_SERIA_NO, MsgConstant.KEY_SERIA_NO),
    IDFA("idfa", "idfa"),
    DEFAULT("null", "null");
    
    private String description;
    private String deviceIdType;

    private DeviceTypeEnum(String str, String str2) {
        this.deviceIdType = str;
        this.description = str2;
    }

    public String getDeviceIdType() {
        return this.deviceIdType;
    }

    public String getDescription() {
        return this.description;
    }
}
