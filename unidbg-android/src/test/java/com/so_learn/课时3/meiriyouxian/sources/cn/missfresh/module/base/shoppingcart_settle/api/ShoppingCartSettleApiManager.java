package cn.missfresh.module.base.shoppingcart_settle.api;

import cn.missfresh.basiclib.net.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import retrofit2.adapter.rxjava2.g;
import retrofit2.r;

public final class ShoppingCartSettleApiManager {
    private static ShoppingCartSettleApi sShoppingCartSettleApi = null;
    private static String sShoppingCartSettleApiUrl = "https://as-vip.missfresh.cn/";

    public static ShoppingCartSettleApi getShoppingCartSettleApi() {
        AppMethodBeat.i(19321, false);
        if (sShoppingCartSettleApi == null) {
            synchronized (ShoppingCartSettleApiManager.class) {
                try {
                    if (sShoppingCartSettleApi == null) {
                        r.a a = a.a().c().a(sShoppingCartSettleApiUrl);
                        a.a(g.a());
                        a.a(cn.missfresh.lib.b.a.a());
                        sShoppingCartSettleApi = (ShoppingCartSettleApi) a.a().a(ShoppingCartSettleApi.class);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(19321);
                    throw th;
                }
            }
        }
        ShoppingCartSettleApi shoppingCartSettleApi = sShoppingCartSettleApi;
        AppMethodBeat.o(19321);
        return shoppingCartSettleApi;
    }

    public static void changeShoppingCartSettleApi(String str) {
        sShoppingCartSettleApiUrl = str;
        sShoppingCartSettleApi = null;
    }
}
