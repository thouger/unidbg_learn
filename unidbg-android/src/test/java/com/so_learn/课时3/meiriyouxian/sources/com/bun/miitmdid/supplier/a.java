package com.bun.miitmdid.supplier;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.unionpay.tsmservice.mi.data.Constant;

public class a {

    /* renamed from: com.bun.miitmdid.supplier.a$a  reason: collision with other inner class name */
    public enum EnumC0069a {
        UNSUPPORT(-1, "unsupport"),
        HUA_WEI(0, "HUAWEI"),
        XIAOMI(1, Constant.DEVICE_XIAOMI),
        VIVO(2, "vivo"),
        OPPO(3, "oppo"),
        MOTO(4, "motorola"),
        LENOVO(5, "lenovo"),
        ASUS(6, "asus"),
        SAMSUNG(7, "samsung"),
        MEIZU(8, "meizu"),
        ALPS(9, "alps"),
        NUBIA(10, "nubia");
        
        private int m;
        private String n;

        static {
            AppMethodBeat.i(5334, false);
            AppMethodBeat.o(5334);
        }

        private EnumC0069a(int i, String str) {
            this.m = i;
            this.n = str;
        }

        public static native EnumC0069a a(String str);

        public static native EnumC0069a valueOf(String str);
    }
}
