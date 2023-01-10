package cn.missfresh.module.base.bean;

import cn.missfresh.module.base.bean.TencentSearchData;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;
import com.lidroid.xutils.db.annotation.Transient;

@Table(name = "history_suggestion_address")
public class TencentSuggestionAddress {
    public String adcode;
    public String address;
    public String category;
    public String city;
    public String distance;
    public String district;
    public String id;
    @JSONField(serialize = false)
    public float lat = -1.0f;
    @JSONField(serialize = false)
    public float lng = -1.0f;
    @Transient
    public TencentSearchData.Location location;
    public String province;
    @Id
    public int sq_id;
    public String title;

    public void splitLocation() {
        TencentSearchData.Location location = this.location;
        if (location != null) {
            this.lat = location.lat;
            this.lng = this.location.lng;
        }
    }

    public String getStatisticResult() {
        AppMethodBeat.i(9597, false);
        String str = this.address + this.title;
        AppMethodBeat.o(9597);
        return str;
    }

    public boolean isLatLngAvalible() {
        return this.lat > 0.0f && this.lng > 0.0f;
    }

    public String toString() {
        AppMethodBeat.i(9603, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(9603);
        return jSONString;
    }
}
