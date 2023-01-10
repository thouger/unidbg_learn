package cn.missfresh.module.base.common.dialog;

import android.view.View;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import cn.missfresh.utils.b;
import cn.missfresh.utils.e;

/* access modifiers changed from: package-private */
public class SelectSpuDialog$3 implements View.OnClickListener {
    final /* synthetic */ b a;

    SelectSpuDialog$3(b bVar) {
        this.a = bVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i;
        int i2;
        String str;
        String str2;
        String str3;
        String str4;
        AppMethodBeat.i(11405, false);
        if (!b.a(this.a)) {
            this.a.a("\u8bf7\u9009\u62e9\u89c4\u683c");
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(11405);
        } else if (!b.b(this.a)) {
            this.a.a("\u8bf7\u9009\u62e9\u670d\u52a1");
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(11405);
        } else {
            if (this.a.r == null) {
                str3 = this.a.a.getSku();
                str2 = this.a.a.getName();
                str = this.a.a.getSkuCategory();
                i2 = this.a.a.getStock();
                i = this.a.a.getSeckill_limit();
            } else {
                str3 = this.a.r.getSku();
                str2 = this.a.r.getName();
                str = this.a.r.getSkuCategory();
                i2 = this.a.r.getStock();
                i = this.a.r.getSeckill_limit();
            }
            b bVar = this.a;
            bVar.u = b.a(bVar, str3);
            int parseInt = Integer.parseInt(this.a.m.getText().toString());
            if (parseInt > i2 - this.a.u) {
                a.a("\u8be5\u5546\u54c1\u4ec5\u5269" + i2 + "\u4ef6");
                AppMethodBeat.onClick(this, view);
                AppMethodBeat.o(11405);
            } else if (i == 0 || parseInt <= i - this.a.u) {
                if (!(this.a.r == null || this.a.o == null)) {
                    String trim = this.a.h.getText().toString().trim();
                    if (!e.a(trim)) {
                        str4 = trim.replace("\u5df2\u9009\uff1a", "").trim();
                    } else {
                        str4 = "";
                    }
                    this.a.o.a(this.a.r.getSku(), this.a.r.isSell_out(), str4);
                }
                IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation();
                if (iShoppingCartService2 != null) {
                    if (this.a.s != null) {
                        this.a.s.a(parseInt + this.a.u);
                    }
                    StatisticsManager.m("click_cart", "sku", str3, "skuName", str2, "skuCategory", str);
                    if (b.a(this.a.t)) {
                        iShoppingCartService2.b("", str3, Integer.parseInt(this.a.m.getText().toString()), b.k(this.a));
                    } else {
                        iShoppingCartService2.a("", str3, Integer.parseInt(this.a.m.getText().toString()), b.k(this.a), this.a.t);
                    }
                }
                this.a.dismiss();
                AppMethodBeat.onClick(this, view);
                AppMethodBeat.o(11405);
            } else {
                a.a("\u8be5\u5546\u54c1\u9650\u8d2d" + i + "\u4ef6");
                AppMethodBeat.onClick(this, view);
                AppMethodBeat.o(11405);
            }
        }
    }
}
