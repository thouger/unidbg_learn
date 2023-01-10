package cn.missfresh.module.base.payment.recharge.bean;

import com.bangcle.andjni.JniLib;
import com.taobao.accs.flowcontrol.FlowControl;

public class RechargStatusBean {
    private int status;
    private String toastMsg;

    public RechargStatusBean() {
        JniLib.cV(this, Integer.valueOf((int) FlowControl.STATUS_FLOW_CTRL_CUR));
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getToastMsg() {
        return this.toastMsg;
    }

    public void setToastMsg(String str) {
        this.toastMsg = str;
    }
}
