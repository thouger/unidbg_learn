package cn.missfresh.module.base.payment.recharge.bean;

import com.bangcle.andjni.JniLib;
import com.tencent.smtt.sdk.TbsListener;
import java.util.List;

public class BillingAccount {
    public int counts;
    public int page_no;
    public int page_num;
    public int page_size;
    public List<ResultsEntity> results;

    public static class ResultsEntity {
        public int amount;
        public String change_type;
        public String change_type_name;
        public String data;
        public int id;
        public String note;
        public int seq_flag;
        public int total_amount;
        public String trade_no;

        public ResultsEntity() {
            JniLib.cV(this, 412);
        }
    }

    public BillingAccount() {
        JniLib.cV(this, Integer.valueOf((int) TbsListener.ErrorCode.INFO_TEMP_CORE_EXIST_CONF_ERROR));
    }

    public boolean canLoadMore() {
        return JniLib.cZ(this, Integer.valueOf((int) TbsListener.ErrorCode.INFO_MISS_SDKEXTENSION_JAR_WITH_FUSION_DEX));
    }

    public String getPageNo() {
        return (String) JniLib.cL(this, Integer.valueOf((int) TbsListener.ErrorCode.INFO_SDKINIT_IS_SYS_FORCED));
    }

    public String getPageNum() {
        return (String) JniLib.cL(this, Integer.valueOf((int) TbsListener.ErrorCode.INFO_INITX5_FALSE_DEFAULT));
    }

    public void updateData(BillingAccount billingAccount) {
        JniLib.cV(this, billingAccount, 416);
    }
}
