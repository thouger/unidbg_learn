package cn.missfresh.module.base.bean;

import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.Serializable;

public class PromotionTag implements Serializable {
    private int endColor;
    private String name;
    private int nameColor = 0;
    private int startColor;

    public int getNameColor() {
        AppMethodBeat.i(6643, false);
        int a = q.a(this.nameColor);
        AppMethodBeat.o(6643);
        return a;
    }

    public void setNameColor(int i) {
        this.nameColor = i;
    }

    public int getStartColor() {
        AppMethodBeat.i(6648, false);
        int a = q.a(this.startColor);
        AppMethodBeat.o(6648);
        return a;
    }

    public void setStartColor(int i) {
        this.startColor = i;
    }

    public int getEndColor() {
        AppMethodBeat.i(6651, false);
        int a = q.a(this.endColor);
        AppMethodBeat.o(6651);
        return a;
    }

    public void setEndColor(int i) {
        this.endColor = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
