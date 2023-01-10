package com.alipay.sdk.app;

import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.support.api.entity.core.JosStatusCodes;

public enum c {
    SUCCEEDED(ConnectionResult.NETWORK_ERROR, "\u5904\u7406\u6210\u529f"),
    FAILED(4000, "\u7cfb\u7edf\u7e41\u5fd9\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5"),
    CANCELED(6001, "\u7528\u6237\u53d6\u6d88"),
    NETWORK_ERROR(6002, "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38"),
    PARAMS_ERROR(4001, "\u53c2\u6570\u9519\u8bef"),
    DOUBLE_REQUEST(5000, "\u91cd\u590d\u8bf7\u6c42"),
    PAY_WAITTING(JosStatusCodes.RTN_CODE_COMMON_ERROR, "\u652f\u4ed8\u7ed3\u679c\u786e\u8ba4\u4e2d");
    
    private int h;
    private String i;

    private c(int i, String str) {
        this.h = i;
        this.i = str;
    }

    public void a(int i) {
        this.h = i;
    }

    public int a() {
        return this.h;
    }

    public void a(String str) {
        this.i = str;
    }

    public String b() {
        return this.i;
    }

    public static c b(int i) {
        if (i == 4001) {
            return PARAMS_ERROR;
        }
        if (i == 5000) {
            return DOUBLE_REQUEST;
        }
        if (i == 8000) {
            return PAY_WAITTING;
        }
        if (i == 9000) {
            return SUCCEEDED;
        }
        if (i == 6001) {
            return CANCELED;
        }
        if (i != 6002) {
            return FAILED;
        }
        return NETWORK_ERROR;
    }
}
