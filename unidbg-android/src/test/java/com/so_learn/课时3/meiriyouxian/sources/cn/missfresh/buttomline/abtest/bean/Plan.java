package cn.missfresh.buttomline.abtest.bean;

import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

public class Plan {
    private String expId;
    private long lastReqTime = 0;
    private int tickTime = 0;
    private String value;

    public long getLastReqTime() {
        return this.lastReqTime;
    }

    public void setLastReqTime(long j) {
        this.lastReqTime = j;
    }

    public String getExpId() {
        return this.expId;
    }

    public void setExpId(String str) {
        this.expId = str;
    }

    public int getValue() {
        AppMethodBeat.i(7296, false);
        int a = b.a(this.value, -1);
        AppMethodBeat.o(7296);
        return a;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public int getTickTime() {
        return this.tickTime;
    }

    public void setTickTime(int i) {
        this.tickTime = i;
    }

    public String toString() {
        AppMethodBeat.i(7302, false);
        String str = "Plan{lastReqTime=" + this.lastReqTime + ", expId='" + this.expId + DateFormat.QUOTE + ", value='" + this.value + DateFormat.QUOTE + ", tickTime=" + this.tickTime + '}';
        AppMethodBeat.o(7302);
        return str;
    }
}
