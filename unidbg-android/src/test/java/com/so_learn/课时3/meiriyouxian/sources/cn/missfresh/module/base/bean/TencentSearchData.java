package cn.missfresh.module.base.bean;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.xiaomi.mipush.sdk.Constants;

public class TencentSearchData {
    public double _distance;
    public AddressInfo ad_info;
    public String address;
    public String category;
    public String distance;
    public String id;
    public Location location;
    public String title;

    public String getDetailAddress() {
        String str;
        AppMethodBeat.i(9586, false);
        if (this.ad_info == null) {
            str = this.title;
        } else {
            str = this.ad_info.province + this.ad_info.city + this.ad_info.district + this.title;
        }
        AppMethodBeat.o(9586);
        return str;
    }

    public String toString() {
        AppMethodBeat.i(9588, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(9588);
        return jSONString;
    }

    public static class Location {
        public float lat;
        public float lng;

        public String toString() {
            AppMethodBeat.i(9574, false);
            String jSONString = JSON.toJSONString(this);
            AppMethodBeat.o(9574);
            return jSONString;
        }

        public String getStrLocation() {
            AppMethodBeat.i(9578, false);
            String str = this.lat + Constants.ACCEPT_TIME_SEPARATOR_SP + this.lng;
            AppMethodBeat.o(9578);
            return str;
        }
    }

    public class AddressInfo {
        public String adcode;
        public String city;
        public String district;
        public String province;

        public AddressInfo() {
        }

        public String toString() {
            AppMethodBeat.i(9566, false);
            String jSONString = JSON.toJSONString(this);
            AppMethodBeat.o(9566);
            return jSONString;
        }
    }
}
