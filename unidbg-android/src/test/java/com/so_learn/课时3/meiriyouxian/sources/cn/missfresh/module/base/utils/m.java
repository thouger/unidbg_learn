package cn.missfresh.module.base.utils;

import cn.missfresh.module.base.manager.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: BuyVipProductCheckUtil */
public class m {

    /* compiled from: BuyVipProductCheckUtil */
    public interface a {
        void a(String str, String str2, String str3, int i);
    }

    public static boolean a(boolean z, a aVar) {
        AppMethodBeat.i(23132, false);
        if (z) {
            if (!e.o()) {
                if (aVar != null) {
                    aVar.a("\u8be5\u5546\u54c1\u4ec5\u9650\u4f1a\u5458\u7528\u6237\u8d2d\u4e70", "\u53bb\u767b\u5f55", "\u53d6\u6d88", 0);
                }
                AppMethodBeat.o(23132);
                return false;
            } else if (e.aa() == 0) {
                if (aVar != null) {
                    aVar.a("\u8be5\u5546\u54c1\u4ec5\u9650\u4f1a\u5458\u8d2d\u4e70", "\u5f00\u901a\u4f1a\u5458", "\u53d6\u6d88", 1);
                }
                AppMethodBeat.o(23132);
                return false;
            }
        }
        AppMethodBeat.o(23132);
        return true;
    }
}
