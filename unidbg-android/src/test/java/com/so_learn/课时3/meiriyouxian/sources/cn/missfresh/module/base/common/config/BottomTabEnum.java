package cn.missfresh.module.base.common.config;

import cn.missfresh.module.base.common.resourcemanager.bean.SkipBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.Serializable;

public enum BottomTabEnum implements Serializable {
    INDEX(0, "\u9996\u9875", "index"),
    CLASSIFY(1, "\u5206\u7c7b", "classify"),
    DELICACY(2, "\u5403\u4ec0\u4e48", "delicacy"),
    CART(3, "\u8d2d\u7269\u8f66", SkipBean.Type.cart),
    INDIVIDUAL_CENTER(4, "\u6211\u7684", "individual_center");
    
    private String label;
    private int pos;
    private String value;

    public static int size() {
        return 5;
    }

    public static BottomTabEnum valueOf(String str) {
        AppMethodBeat.i(10308, false);
        BottomTabEnum bottomTabEnum = (BottomTabEnum) Enum.valueOf(BottomTabEnum.class, str);
        AppMethodBeat.o(10308);
        return bottomTabEnum;
    }

    static {
        AppMethodBeat.i(10317, false);
        AppMethodBeat.o(10317);
    }

    private BottomTabEnum(int i, String str, String str2) {
        this.pos = i;
        this.value = str;
        this.label = str2;
    }

    public int getPos() {
        return this.pos;
    }

    public String getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }
}
