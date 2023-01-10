package cn.missfresh.module.base.datastatistics.bean;

import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class StatisticsParams {
    public static final int TYPE_CLICK = 2;
    public static final int TYPE_SCROLL_VIEW = 4;
    public static final int TYPE_VIEW = 1;
    public String eventId;
    public String from_value = "";
    public String label;
    public String module;
    public String position_key = "";
    public String servicePath = "";
    public int type;

    public StatisticsParams(int i, String str, String str2, String str3, String str4, String str5, String str6) {
        AppMethodBeat.i(12738, false);
        this.type = i;
        this.eventId = str;
        this.label = str2;
        this.module = str6;
        if (str3 != null) {
            this.servicePath = str3;
        }
        if (str4 != null) {
            this.from_value = str4;
        }
        if (str5 != null) {
            this.position_key = str5;
        }
        AppMethodBeat.o(12738);
    }

    public String toString() {
        AppMethodBeat.i(12739, false);
        String str = "StatisticsParams{type=" + this.type + ", eventId='" + this.eventId + DateFormat.QUOTE + ", label='" + this.label + DateFormat.QUOTE + ", servicePath='" + this.servicePath + DateFormat.QUOTE + ", from='" + this.from_value + DateFormat.QUOTE + ", pos='" + this.position_key + DateFormat.QUOTE + '}';
        AppMethodBeat.o(12739);
        return str;
    }
}
