package cn.missfresh.module.base.common.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import cn.missfresh.basiclib.utils.c;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.event.ProductPageClickEvent;
import cn.missfresh.module.base.common.interfaces.g;
import cn.missfresh.module.base.common.interfaces.i;
import cn.missfresh.module.base.common.interfaces.j;
import cn.missfresh.module.base.common.interfaces.p;
import cn.missfresh.module.base.common.interfaces.q;
import cn.missfresh.module.base.widget.MFPriceView;
import cn.missfresh.module.base.widget.ProductBuyView;
import cn.missfresh.module.base.widget.ProductPictureLayout;
import cn.missfresh.module.base.widget.ProductTitleView;
import cn.missfresh.module.base.widget.PromotionDeliveryTagView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ProductViewHolder extends BaseProductViewHolder implements p, q {
    private ProductPictureLayout a;
    private ProductTitleView l;
    private MFPriceView m;
    private ProductBuyView n;
    private PromotionDeliveryTagView o;
    private ImageView p;
    private ImageView q;
    private int r;

    @Override // cn.missfresh.module.base.common.interfaces.f
    public p j() {
        return this;
    }

    @Override // cn.missfresh.module.base.common.interfaces.f
    public q k() {
        return this;
    }

    public ProductViewHolder(View view, int i) {
        super(view);
        AppMethodBeat.i(12035, false);
        this.a = (ProductPictureLayout) view.findViewById(R.id.ppl_image);
        this.l = (ProductTitleView) view.findViewById(R.id.ptv_title);
        this.m = (MFPriceView) view.findViewById(R.id.ppv_price);
        this.n = (ProductBuyView) view.findViewById(R.id.pbv_buy);
        this.o = (PromotionDeliveryTagView) view.findViewById(R.id.pdt_promotion);
        this.p = (ImageView) view.findViewById(R.id.tag1);
        this.q = (ImageView) view.findViewById(R.id.tag2);
        this.r = i;
        this.l.setTitleTextSize(13);
        this.l.setSubTitleTextSize(10);
        AppMethodBeat.o(12035);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(12046, false);
        if (this.b != null) {
            ProductPageClickEvent productPageClickEvent = new ProductPageClickEvent(7, this.r);
            productPageClickEvent.setSku(this.b.getSku());
            productPageClickEvent.setPos(this.b.getPosInProductList());
            productPageClickEvent.setName(this.b.getName());
            productPageClickEvent.setProductSellout(this.b.getSell_out());
            productPageClickEvent.setThemeTag(this.b.getThemeTag());
            productPageClickEvent.setProductTag(this.b.getProductTag());
            c.a().a(productPageClickEvent);
        }
        AppMethodBeat.o(12046);
    }

    @Override // cn.missfresh.module.base.common.interfaces.f
    public MFPriceView f() {
        return this.m;
    }

    @Override // cn.missfresh.module.base.common.interfaces.f
    public i g() {
        return this.l;
    }

    @Override // cn.missfresh.module.base.common.interfaces.f
    public g h() {
        return this.a;
    }

    @Override // cn.missfresh.module.base.common.interfaces.f
    public cn.missfresh.module.base.common.interfaces.c i() {
        return this.n;
    }

    @Override // cn.missfresh.module.base.common.interfaces.f
    public j l() {
        return this.o;
    }

    @Override // cn.missfresh.module.base.common.interfaces.p
    public void b(String str) {
        AppMethodBeat.i(12051, false);
        if (this.p == null) {
            AppMethodBeat.o(12051);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            this.p.setVisibility(8);
        } else {
            this.p.setVisibility(0);
            cn.missfresh.lib.image.c.a(this.p).a(str).a(this.p);
        }
        AppMethodBeat.o(12051);
    }

    @Override // cn.missfresh.module.base.common.interfaces.q
    public void c(String str) {
        AppMethodBeat.i(12052, false);
        if (this.q == null) {
            AppMethodBeat.o(12052);
            return;
        }
        if (TextUtils.isEmpty(str)) {
            this.q.setVisibility(8);
        } else {
            this.q.setVisibility(0);
            cn.missfresh.lib.image.c.a(this.q).a(str).a(this.q);
        }
        AppMethodBeat.o(12052);
    }
}
