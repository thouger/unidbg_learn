package cn.missfresh.module.base.shoppingcart_settle.a;

import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.umeng.analytics.pro.ai;

/* compiled from: MiniShoppingCartStastics */
public class a {
    public static void a(int i, int i2) {
        AppMethodBeat.i(19488, false);
        StatisticsManager.e("exposure_settleAccount", ai.e, "settleAccount", "type", Integer.valueOf(i), "settleAccount_type", Integer.valueOf(i2));
        AppMethodBeat.o(19488);
    }

    public static void a(int i, int i2, int i3) {
        AppMethodBeat.i(19490, false);
        StatisticsManager.e("click_settleAccount", ai.e, "settleAccount", "type", Integer.valueOf(i), "settleAccount_type", Integer.valueOf(i2), "click_type", Integer.valueOf(i3));
        AppMethodBeat.o(19490);
    }

    public static void a(int i) {
        AppMethodBeat.i(19492, false);
        StatisticsManager.c("exposure_settleAccount", ai.e, "settleAccount", "settleAccount_type", Integer.valueOf(i));
        AppMethodBeat.o(19492);
    }

    public static void b(int i, int i2) {
        AppMethodBeat.i(19494, false);
        StatisticsManager.c("click_settleAccount", ai.e, "settleAccount", "settleAccount_type", Integer.valueOf(i), "click_type", Integer.valueOf(i2));
        AppMethodBeat.o(19494);
    }

    public static void b(int i) {
        AppMethodBeat.i(19496, false);
        StatisticsManager.p("exposure_settleAccount", ai.e, "settleAccount", "settleAccount_type", Integer.valueOf(i));
        AppMethodBeat.o(19496);
    }

    public static void c(int i, int i2) {
        AppMethodBeat.i(19497, false);
        StatisticsManager.p("click_settleAccount", ai.e, "settleAccount", "settleAccount_type", Integer.valueOf(i), "click_type", Integer.valueOf(i2));
        AppMethodBeat.o(19497);
    }
}
