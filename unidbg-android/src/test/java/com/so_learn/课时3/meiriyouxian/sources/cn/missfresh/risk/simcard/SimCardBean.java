package cn.missfresh.risk.simcard;

import android.util.Log;
import cn.missfresh.risk.base.BaseBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.xiaomi.mipush.sdk.Constants;
import org.json.JSONObject;

public class SimCardBean extends BaseBean {
    private static final String TAG = SimCardBean.class.getSimpleName();
    private String isHaveCard;
    private String isTwoCard;
    private String meid;
    private String operator;
    private String sim1Imei;
    private String sim1Imsi;
    private String sim1ImsiOperator;
    private String sim1Ready;
    private String sim2Imei;
    private String sim2Imsi;
    private String sim2ImsiOperator;
    private String sim2Ready;
    private String simSlotIndex;

    static {
        AppMethodBeat.i(2602, false);
        AppMethodBeat.o(2602);
    }

    public String getSim1Imei() {
        return this.sim1Imei;
    }

    public void setSim1Imei(String str) {
        this.sim1Imei = str;
    }

    public String getSim2Imei() {
        return this.sim2Imei;
    }

    public void setSim2Imei(String str) {
        this.sim2Imei = str;
    }

    public String getSim1Imsi() {
        return this.sim1Imsi;
    }

    public void setSim1Imsi(String str) {
        this.sim1Imsi = str;
    }

    public String getSim2Imsi() {
        return this.sim2Imsi;
    }

    public void setSim2Imsi(String str) {
        this.sim2Imsi = str;
    }

    public String getSimSlotIndex() {
        return this.simSlotIndex;
    }

    public void setSimSlotIndex(String str) {
        this.simSlotIndex = str;
    }

    public String getMeid() {
        return this.meid;
    }

    public void setMeid(String str) {
        this.meid = str;
    }

    public String getSim1ImsiOperator() {
        return this.sim1ImsiOperator;
    }

    public void setSim1ImsiOperator(String str) {
        this.sim1ImsiOperator = str;
    }

    public String getSim2ImsiOperator() {
        return this.sim2ImsiOperator;
    }

    public void setSim2ImsiOperator(String str) {
        this.sim2ImsiOperator = str;
    }

    public String getSim1Ready() {
        return this.sim1Ready;
    }

    public void setSim1Ready(String str) {
        this.sim1Ready = str;
    }

    public String getSim2Ready() {
        return this.sim2Ready;
    }

    public void setSim2Ready(String str) {
        this.sim2Ready = str;
    }

    public String getIsTwoCard() {
        return this.isTwoCard;
    }

    public void setIsTwoCard(String str) {
        this.isTwoCard = str;
    }

    public String getIsHaveCard() {
        return this.isHaveCard;
    }

    public void setIsHaveCard(String str) {
        this.isHaveCard = str;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String str) {
        this.operator = str;
    }

    public String getSimCardState() {
        AppMethodBeat.i(2597, false);
        String str = this.sim1Ready + Constants.ACCEPT_TIME_SEPARATOR_SP + this.sim2Ready;
        AppMethodBeat.o(2597);
        return str;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.risk.base.BaseBean
    public JSONObject toJSONObject() {
        AppMethodBeat.i(2600, false);
        try {
            this.jsonObject.put("sim1Imei", isEmpty(this.sim1Imei));
            this.jsonObject.put("sim2Imei", isEmpty(this.sim2Imei));
            this.jsonObject.put("sim1Imsi", isEmpty(this.sim1Imsi));
            this.jsonObject.put("sim2Imsi", isEmpty(this.sim2Imsi));
            this.jsonObject.put("simSlotIndex", isEmpty(this.simSlotIndex));
            this.jsonObject.put("meid", isEmpty(this.meid));
            this.jsonObject.put("sim1ImsiOperator", isEmpty(this.sim1ImsiOperator));
            this.jsonObject.put("sim2ImsiOperator", isEmpty(this.sim2ImsiOperator));
            this.jsonObject.put("sim1Ready", isEmpty(this.sim1Ready));
            this.jsonObject.put("sim2Ready", isEmpty(this.sim2Ready));
            this.jsonObject.put("isTwoCard", isEmpty(this.isTwoCard));
            this.jsonObject.put("isHaveCard", isEmpty(this.isHaveCard));
            this.jsonObject.put("operator", isEmpty(this.operator));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        JSONObject jSONObject = super.toJSONObject();
        AppMethodBeat.o(2600);
        return jSONObject;
    }
}
