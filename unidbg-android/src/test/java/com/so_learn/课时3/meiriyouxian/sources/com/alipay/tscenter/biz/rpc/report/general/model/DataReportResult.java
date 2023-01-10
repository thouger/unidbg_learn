package com.alipay.tscenter.biz.rpc.report.general.model;

import java.io.Serializable;
import java.util.Map;

public class DataReportResult implements Serializable {
    public String resultCode;
    public Map<String, String> resultData;
    public boolean success;
}
