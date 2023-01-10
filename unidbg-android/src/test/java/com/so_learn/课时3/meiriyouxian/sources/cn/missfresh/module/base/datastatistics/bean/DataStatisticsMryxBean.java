package cn.missfresh.module.base.datastatistics.bean;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.umeng.analytics.pro.ai;
import java.util.HashMap;
import java.util.Map;

public class DataStatisticsMryxBean {
    private String event;
    private Map<String, String> info;
    private String lable;
    private Map param;

    public void setInfo(Map map) {
        this.info = map;
    }

    public Map getInfo() {
        return this.info;
    }

    public void setInfoData(String str, String str2, String str3, String str4, String str5) {
        AppMethodBeat.i(12730, false);
        if (this.info == null) {
            this.info = new HashMap();
        }
        this.info.clear();
        this.info.put("label", str);
        this.info.put("event", str2);
        this.info.put("button", str4);
        this.info.put("event_type", str5);
        this.info.put(ai.e, str3);
        AppMethodBeat.o(12730);
    }

    public Map getParam() {
        return this.param;
    }

    public void setParam(Map map) {
        AppMethodBeat.i(12735, false);
        this.param = map;
        if (this.info == null) {
            this.info = new HashMap();
        }
        if (b.a(this.info.get(ai.e))) {
            String str = "";
            if (map != null) {
                if (map.get(ai.e) != null) {
                    str = String.valueOf(map.get(ai.e));
                }
                map.remove(ai.e);
            }
            this.info.put(ai.e, str);
        }
        AppMethodBeat.o(12735);
    }
}
