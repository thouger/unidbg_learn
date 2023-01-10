package cn.missfresh.module.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.MFPriceParamBean;
import cn.missfresh.module.base.bean.PriceInfoBean;
import cn.missfresh.module.base.helper.i;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class MFPriceView extends RelativeLayout {
    private View a;
    private View b;
    private TextView c;
    private TextView d;
    private View e;
    private View f;
    private TextView g;
    private TextView h;
    private TextView i;
    private int j;
    private MFPriceParamBean k;

    public MFPriceView(Context context) {
        this(context, null);
    }

    public MFPriceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(23716, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MFPriceView);
        this.j = obtainStyledAttributes.getInt(R.styleable.MFPriceView_price_type, 0);
        obtainStyledAttributes.recycle();
        int i = this.j;
        if (i == 0) {
            LayoutInflater.from(context).inflate(R.layout.price_ui_top_bottom, (ViewGroup) this, true);
        } else if (i == 1) {
            LayoutInflater.from(context).inflate(R.layout.price_ui_top_left_right, (ViewGroup) this, true);
        } else if (i == 2) {
            LayoutInflater.from(context).inflate(R.layout.price_ui_top_bottom2, (ViewGroup) this, true);
        } else if (i == 4) {
            LayoutInflater.from(context).inflate(R.layout.price_ui_top_bottom3, (ViewGroup) this, true);
        } else if (i == 5) {
            LayoutInflater.from(context).inflate(R.layout.price_ui_top_bottom4, (ViewGroup) this, true);
        }
        a();
        AppMethodBeat.o(23716);
    }

    private void a() {
        AppMethodBeat.i(23717, false);
        this.a = findViewById(R.id.vip_container_root);
        this.b = findViewById(R.id.vip_container);
        this.c = (TextView) findViewById(R.id.tv_vip_rmb);
        this.d = (TextView) findViewById(R.id.tv_vip_price);
        this.e = findViewById(R.id.space);
        this.f = findViewById(R.id.common_price_layout);
        this.g = (TextView) findViewById(R.id.tv_common_rmb);
        this.h = (TextView) findViewById(R.id.tv_common_price);
        this.i = (TextView) findViewById(R.id.tv_line_price);
        AppMethodBeat.o(23717);
    }

    public void a(PriceInfoBean priceInfoBean) {
        View view;
        TextView textView;
        TextView textView2;
        AppMethodBeat.i(23718, false);
        if (priceInfoBean == null) {
            View view2 = this.a;
            if (view2 != null) {
                view2.setVisibility(8);
            }
            View view3 = this.f;
            if (view3 != null) {
                view3.setVisibility(8);
            }
            TextView textView3 = this.h;
            if (textView3 != null) {
                textView3.setVisibility(8);
            }
            TextView textView4 = this.g;
            if (textView4 != null) {
                textView4.setVisibility(8);
            }
            TextView textView5 = this.i;
            if (textView5 != null) {
                textView5.setVisibility(8);
            }
            AppMethodBeat.o(23718);
            return;
        }
        b(priceInfoBean);
        if (this.b == null || this.c == null || this.d == null || priceInfoBean.vipPrice == null) {
            View view4 = this.a;
            if (view4 != null) {
                view4.setVisibility(8);
            }
            View view5 = this.b;
            if (view5 != null) {
                view5.setVisibility(8);
            }
        } else {
            View view6 = this.a;
            if (view6 != null) {
                view6.setVisibility(0);
            }
            this.b.setVisibility(0);
            this.c.setTextColor(q.a(priceInfoBean.vipPrice.color));
            this.d.setTextColor(q.a(priceInfoBean.vipPrice.color));
            this.d.setText(at.b(priceInfoBean.vipPrice.price));
            this.d.setTextSize((float) this.k.vipTextSize);
        }
        if (this.h == null || priceInfoBean.commonPrice == null) {
            View view7 = this.f;
            if (view7 != null) {
                view7.setVisibility(8);
            }
            TextView textView6 = this.h;
            if (textView6 != null) {
                textView6.setVisibility(8);
                TextView textView7 = this.g;
                if (textView7 != null) {
                    textView7.setVisibility(8);
                }
            }
        } else {
            View view8 = this.f;
            if (view8 != null) {
                view8.setVisibility(0);
            }
            this.h.setVisibility(0);
            this.h.setTextColor(q.a(priceInfoBean.commonPrice.color));
            this.h.setText(at.b(priceInfoBean.commonPrice.price));
            View view9 = this.b;
            if (view9 == null || view9.getVisibility() != 0) {
                if (this.j == 1) {
                    TextView textView8 = this.g;
                    if (textView8 != null) {
                        textView8.setVisibility(0);
                        this.g.setTextSize(12.0f);
                        this.g.setTextColor(q.a(priceInfoBean.commonPrice.color));
                    }
                } else {
                    TextView textView9 = this.g;
                    if (textView9 != null) {
                        textView9.setVisibility(0);
                        this.g.setTextSize(10.0f);
                        this.g.setTextColor(q.a(priceInfoBean.commonPrice.color));
                    }
                }
                this.h.setTextSize((float) this.k.commomTextSize);
            } else {
                TextView textView10 = this.g;
                if (textView10 != null) {
                    textView10.setVisibility(0);
                    this.g.setTextSize(10.0f);
                    this.g.setTextColor(q.a(priceInfoBean.commonPrice.color));
                }
                this.h.setTextSize((float) this.k.commomTextSize);
            }
        }
        if (this.i == null || priceInfoBean.linePrice == null) {
            TextView textView11 = this.i;
            if (textView11 != null) {
                textView11.setVisibility(8);
            }
        } else {
            this.i.setVisibility(0);
            this.i.setTextColor(q.a(priceInfoBean.linePrice.color));
            this.i.getPaint().setFlags(16);
            TextView textView12 = this.i;
            textView12.setText(i.a + at.b(priceInfoBean.linePrice.price));
            this.i.setTextSize((float) this.k.lineTextSize);
        }
        if (this.e == null || (((view = this.b) == null || view.getVisibility() != 8) && ((textView = this.h) == null || textView.getVisibility() != 8 || (textView2 = this.i) == null || textView2.getVisibility() != 8))) {
            View view10 = this.e;
            if (view10 != null) {
                view10.setVisibility(0);
            }
        } else {
            this.e.setVisibility(8);
        }
        AppMethodBeat.o(23718);
    }

    private void b(PriceInfoBean priceInfoBean) {
        AppMethodBeat.i(23719, false);
        this.k = new MFPriceParamBean();
        int i = this.j;
        if (i == 0 || i == 1) {
            if (priceInfoBean.vip == 1 && priceInfoBean.vipPrice != null) {
                MFPriceParamBean mFPriceParamBean = this.k;
                mFPriceParamBean.vipTextSize = 14;
                mFPriceParamBean.commomTextSize = 12;
                mFPriceParamBean.lineTextSize = 12;
            } else if (priceInfoBean.vip == 1) {
                MFPriceParamBean mFPriceParamBean2 = this.k;
                mFPriceParamBean2.vipTextSize = 12;
                mFPriceParamBean2.commomTextSize = 14;
                mFPriceParamBean2.lineTextSize = 12;
            } else {
                MFPriceParamBean mFPriceParamBean3 = this.k;
                mFPriceParamBean3.vipTextSize = 12;
                mFPriceParamBean3.commomTextSize = 14;
                mFPriceParamBean3.lineTextSize = 12;
            }
        } else if (i == 2) {
            MFPriceParamBean mFPriceParamBean4 = this.k;
            mFPriceParamBean4.vipTextSize = 12;
            mFPriceParamBean4.commomTextSize = 12;
            mFPriceParamBean4.lineTextSize = 12;
        } else if (i == 4) {
            MFPriceParamBean mFPriceParamBean5 = this.k;
            mFPriceParamBean5.vipTextSize = 13;
            mFPriceParamBean5.commomTextSize = 13;
            mFPriceParamBean5.lineTextSize = 10;
        } else if (i == 5) {
            MFPriceParamBean mFPriceParamBean6 = this.k;
            mFPriceParamBean6.vipTextSize = 12;
            mFPriceParamBean6.commomTextSize = 12;
            mFPriceParamBean6.lineTextSize = 10;
        }
        AppMethodBeat.o(23719);
    }
}
