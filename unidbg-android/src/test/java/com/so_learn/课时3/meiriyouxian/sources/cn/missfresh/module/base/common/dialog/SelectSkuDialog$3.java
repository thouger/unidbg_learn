package cn.missfresh.module.base.common.dialog;

import android.view.View;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import cn.missfresh.utils.b;

/* access modifiers changed from: package-private */
public class SelectSkuDialog$3 implements View.OnClickListener {
    final /* synthetic */ a a;

    SelectSkuDialog$3(a aVar) {
        this.a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i;
        int i2;
        String str;
        String str2;
        String str3;
        String str4;
        AppMethodBeat.i(11309, false);
        if (!a.a(this.a)) {
            this.a.a("\u8bf7\u9009\u62e9\u89c4\u683c");
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(11309);
        } else if (!a.b(this.a)) {
            this.a.a("\u8bf7\u9009\u62e9\u670d\u52a1");
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(11309);
        } else {
            if (this.a.u == null) {
                str3 = this.a.a.getSku();
                str2 = this.a.a.getName();
                str = this.a.a.getSkuCategory();
                i2 = this.a.a.getStock();
                i = this.a.a.getSeckill_limit();
            } else {
                str3 = this.a.u.getSku();
                str2 = this.a.u.getName();
                str = this.a.u.getSkuCategory();
                i2 = this.a.u.getStock();
                i = this.a.u.getSeckill_limit();
            }
            a aVar = this.a;
            aVar.y = a.a(aVar, str3);
            int parseInt = Integer.parseInt(this.a.o.getText().toString());
            if (parseInt > i2 - this.a.y) {
                a.a("\u8be5\u5546\u54c1\u4ec5\u5269" + i2 + "\u4ef6");
                AppMethodBeat.onClick(this, view);
                AppMethodBeat.o(11309);
            } else if (i == 0 || parseInt <= i - this.a.y) {
                if (!(this.a.u == null || this.a.q == null)) {
                    String trim = this.a.i.getText().toString().trim();
                    if (!b.a(trim)) {
                        str4 = trim.replace("\u5df2\u9009\uff1a", "").trim();
                    } else {
                        str4 = "";
                    }
                    this.a.q.a(this.a.u.getSku(), this.a.u.isSell_out(), str4);
                }
                IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation();
                if (iShoppingCartService2 != null) {
                    if (this.a.v != null) {
                        this.a.v.a(this.a.y + parseInt);
                    }
                    if (this.a.w != null) {
                        this.a.w.a(parseInt + this.a.y);
                    }
                    StatisticsManager.m("click_cart", "sku", str3, "skuName", str2, "skuCategory", str);
                    if (b.a(this.a.x)) {
                        iShoppingCartService2.b("", str3, Integer.parseInt(this.a.o.getText().toString()), a.l(this.a));
                    } else {
                        iShoppingCartService2.a("", str3, Integer.parseInt(this.a.o.getText().toString()), a.l(this.a), this.a.x);
                    }
                }
                this.a.dismiss();
                AppMethodBeat.onClick(this, view);
                AppMethodBeat.o(11309);
            } else {
                a.a("\u8be5\u5546\u54c1\u9650\u8d2d" + i + "\u4ef6");
                AppMethodBeat.onClick(this, view);
                AppMethodBeat.o(11309);
            }
        }
    }
}
