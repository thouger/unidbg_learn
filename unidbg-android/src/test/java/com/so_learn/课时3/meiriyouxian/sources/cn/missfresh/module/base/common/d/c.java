package cn.missfresh.module.base.common.d;

import cn.missfresh.module.base.bean.ShoppingCart;
import cn.missfresh.module.base.bean.ShoppingCartActive;
import cn.missfresh.module.base.bean.ShoppingCartInActive;
import cn.missfresh.module.base.common.event.g;
import cn.missfresh.module.base.common.event.j;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.l;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: ShoppingCartThreadPool */
public class c {
    private ExecutorService a = Executors.newFixedThreadPool(1);

    public c() {
        AppMethodBeat.i(12262, false);
        AppMethodBeat.o(12262);
    }

    public void a(Runnable runnable) {
        AppMethodBeat.i(12263, false);
        if (runnable != null) {
            this.a.execute(runnable);
        }
        AppMethodBeat.o(12263);
    }

    public a a() {
        AppMethodBeat.i(12264, false);
        a aVar = new a();
        AppMethodBeat.o(12264);
        return aVar;
    }

    /* compiled from: ShoppingCartThreadPool */
    private class a implements Runnable {
        public boolean a;

        private a() {
            AppMethodBeat.i(12256, false);
            this.a = e.o();
            AppMethodBeat.o(12256);
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z = false;
            AppMethodBeat.i(12257, false);
            List<ShoppingCart> a = l.a(ShoppingCartActive.class);
            List a2 = l.a(ShoppingCartInActive.class);
            if (b.a(a)) {
                AppMethodBeat.o(12257);
                return;
            }
            if (a2 == null) {
                a2 = new ArrayList();
            }
            ArrayList arrayList = new ArrayList();
            if (!this.a) {
                boolean z2 = false;
                for (ShoppingCart shoppingCart : a) {
                    if (!shoppingCart.isActiveItem() || !shoppingCart.getVip_product()) {
                        arrayList.add(shoppingCart);
                    } else {
                        a2.add(a(shoppingCart));
                    }
                    if (shoppingCart.isNationWide()) {
                        z = true;
                    } else {
                        z2 = true;
                    }
                }
                l.b(ShoppingCartActive.class);
                l.b(ShoppingCartInActive.class);
                l.a((List<?>) arrayList);
                l.a((List<?>) a2);
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(arrayList);
                arrayList2.addAll(a2);
                EventBus.getDefault().post(new j(arrayList2, -1));
                g gVar = new g(-1);
                gVar.b = l.a();
                gVar.c = cn.missfresh.module.base.b.c.a(z, z2);
                EventBus.getDefault().post(gVar);
                AppMethodBeat.o(12257);
                return;
            }
            AppMethodBeat.o(12257);
        }

        private ShoppingCartInActive a(ShoppingCart shoppingCart) {
            AppMethodBeat.i(12259, false);
            ShoppingCartInActive shoppingCartInActive = new ShoppingCartInActive();
            shoppingCartInActive.setIsChecked(false);
            shoppingCartInActive.setIsActiveItem(false);
            shoppingCartInActive.setSku(shoppingCart.getSku());
            shoppingCartInActive.setName(shoppingCart.getName());
            shoppingCartInActive.setQuantity(shoppingCart.getQuantity());
            shoppingCartInActive.setProduct_limit(shoppingCart.getProduct_limit());
            shoppingCartInActive.setImage(shoppingCart.getImage());
            shoppingCartInActive.setUnit(shoppingCart.getUnit());
            shoppingCartInActive.setVip_product(shoppingCart.getVip_product());
            shoppingCartInActive.setLimit(shoppingCart.getLimit());
            shoppingCartInActive.setVipPP(shoppingCart.getVipPP());
            shoppingCartInActive.setNonVipPP(shoppingCart.getNonVipPP());
            AppMethodBeat.o(12259);
            return shoppingCartInActive;
        }
    }
}
