package cn.missfresh.unitepoplib.factory;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public enum MoudleType {
    CUSTOM_DIALOG(-1, "\u4e0d\u4f7f\u7528\u6a21\u677f"),
    PIC_DIALOG(0, "\u56fe\u7247\u3001\u5173\u95ed\u6309\u94ae\u5f39\u6846"),
    ALERT_TWO_BTN_DIALOG(1, "\u5305\u542b\u786e\u5b9a\u548c\u53d6\u6d88\u6309\u94ae\u7684\u6587\u6848\u5f39\u6846");
    
    private String desc;
    private int type;

    public static MoudleType valueOf(String str) {
        AppMethodBeat.i(15506, false);
        MoudleType moudleType = (MoudleType) Enum.valueOf(MoudleType.class, str);
        AppMethodBeat.o(15506);
        return moudleType;
    }

    static {
        AppMethodBeat.i(15520, false);
        AppMethodBeat.o(15520);
    }

    private MoudleType(int i, String str) {
        this.type = i;
        this.desc = str;
    }

    public static boolean containType(int i) {
        MoudleType[] values;
        AppMethodBeat.i(15509, false);
        for (MoudleType moudleType : values()) {
            if (moudleType.type == i) {
                AppMethodBeat.o(15509);
                return true;
            }
        }
        AppMethodBeat.o(15509);
        return false;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }
}
