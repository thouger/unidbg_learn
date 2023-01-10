package cn.missfresh.module.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.PricePro;
import cn.missfresh.module.base.bean.VipTag;
import cn.missfresh.module.base.common.interfaces.h;
import cn.missfresh.module.base.support.PriceSpanableTextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ProductPriceView extends RelativeLayout implements h {
    private LinearLayout a;
    private PriceSpanableTextView b;
    private PriceSpanableTextView c;
    private int d;
    private String e;
    private boolean f;

    public void setVipLevel(int i) {
    }

    public ProductPriceView(Context context) {
        this(context, null);
    }

    public ProductPriceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(23828, false);
        this.d = 1;
        this.f = true;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ProductPriceView);
        this.d = obtainStyledAttributes.getInt(R.styleable.ProductPriceView_layout_orientation, 1);
        obtainStyledAttributes.recycle();
        if (this.d == 1) {
            LayoutInflater.from(context).inflate(R.layout.homepage_price_view, (ViewGroup) this, true);
        } else {
            LayoutInflater.from(context).inflate(R.layout.homepage_price_view_horizontal, (ViewGroup) this, true);
        }
        a();
        AppMethodBeat.o(23828);
    }

    private void a() {
        AppMethodBeat.i(23829, false);
        this.a = (LinearLayout) findViewById(R.id.product_price_layout);
        this.b = (PriceSpanableTextView) findViewById(R.id.tv_vip_price);
        this.c = (PriceSpanableTextView) findViewById(R.id.tv_no_price);
        this.e = getResources().getString(R.string.RMB);
        AppMethodBeat.o(23829);
    }

    public void setPricePro(PricePro pricePro) {
        AppMethodBeat.i(23830, false);
        if (pricePro == null || !this.f) {
            AppMethodBeat.o(23830);
            return;
        }
        if (pricePro.getNoVip() == null || pricePro.getVip() == null) {
            a(pricePro.getVip() == null ? pricePro.getNoVip() : pricePro.getVip(), this.b);
            this.c.setVisibility(8);
        } else {
            a(pricePro.getNoVip(), this.c);
            a(pricePro.getVip(), this.b);
        }
        AppMethodBeat.o(23830);
    }

    public void setVipTag(VipTag vipTag) {
        AppMethodBeat.i(23831, false);
        setTagInternal(vipTag);
        AppMethodBeat.o(23831);
    }

    private void setTagInternal(VipTag vipTag) {
        AppMethodBeat.i(23832, false);
        PriceSpanableTextView priceSpanableTextView = this.b;
        if (priceSpanableTextView != null) {
            priceSpanableTextView.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_product_vip_tag));
        }
        AppMethodBeat.o(23832);
    }

    private void a(PricePro.Price price, PriceSpanableTextView priceSpanableTextView) {
        AppMethodBeat.i(23833, false);
        if (price != null) {
            priceSpanableTextView.a("", 0, this.e, 0, price.price, 0, price.getColor(), price.getColor(), price.show_type);
            priceSpanableTextView.setVisibility(0);
        } else {
            priceSpanableTextView.setVisibility(8);
        }
        AppMethodBeat.o(23833);
    }

    private void a(PriceSpanableTextView priceSpanableTextView, int i, int i2, boolean z) {
        AppMethodBeat.i(23834, false);
        if (priceSpanableTextView == null) {
            AppMethodBeat.o(23834);
            return;
        }
        priceSpanableTextView.a("", 0, this.e, 0, i, 0, i2, i2, z ? 2 : 1);
        AppMethodBeat.o(23834);
    }

    public void a(PricePro pricePro, VipTag vipTag, boolean z) {
        boolean z2 = false;
        AppMethodBeat.i(23835, false);
        if (pricePro == null || !this.f) {
            AppMethodBeat.o(23835);
            return;
        }
        PriceSpanableTextView priceSpanableTextView = this.b;
        if (priceSpanableTextView != null) {
            priceSpanableTextView.setBackgroundDrawable(null);
        }
        int i = 4;
        if (pricePro.getNoVip() == null) {
            this.c.setVisibility(4);
        } else {
            this.c.setVisibility(0);
            a(this.c, pricePro.getNoVip().price, pricePro.getNoVip().getColor(), pricePro.getNoVip().getShowStyle() == 1);
        }
        if (pricePro.getVip() == null) {
            PriceSpanableTextView priceSpanableTextView2 = this.b;
            if (priceSpanableTextView2 != null) {
                if (z) {
                    i = 8;
                }
                priceSpanableTextView2.setVisibility(i);
            }
        } else {
            PriceSpanableTextView priceSpanableTextView3 = this.b;
            if (priceSpanableTextView3 != null) {
                priceSpanableTextView3.setVisibility(0);
                PriceSpanableTextView priceSpanableTextView4 = this.b;
                int i2 = pricePro.getVip().price;
                int color = pricePro.getVip().getColor();
                if (pricePro.getVip().getShowStyle() == 1) {
                    z2 = true;
                }
                a(priceSpanableTextView4, i2, color, z2);
            }
            if (pricePro.getVip().getShowStyle() == 2) {
                setTagInternal(vipTag);
            } else {
                PriceSpanableTextView priceSpanableTextView5 = this.b;
                if (priceSpanableTextView5 != null) {
                    priceSpanableTextView5.setBackgroundDrawable(null);
                }
            }
        }
        AppMethodBeat.o(23835);
    }

    @Override // cn.missfresh.module.base.common.interfaces.m
    public void setShow(boolean z) {
        AppMethodBeat.i(23836, false);
        if (this.f == z) {
            AppMethodBeat.o(23836);
            return;
        }
        this.f = z;
        if (this.f) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        AppMethodBeat.o(23836);
    }

    public void setVipTextSize(float f) {
        PriceSpanableTextView priceSpanableTextView;
        AppMethodBeat.i(23837, false);
        if (!this.f || (priceSpanableTextView = this.b) == null) {
            AppMethodBeat.o(23837);
            return;
        }
        priceSpanableTextView.setTextSize(2, f);
        AppMethodBeat.o(23837);
    }

    public void setNoVipTextSize(float f) {
        PriceSpanableTextView priceSpanableTextView;
        AppMethodBeat.i(23838, false);
        if (!this.f || (priceSpanableTextView = this.c) == null) {
            AppMethodBeat.o(23838);
            return;
        }
        priceSpanableTextView.setTextSize(2, f);
        AppMethodBeat.o(23838);
    }
}
