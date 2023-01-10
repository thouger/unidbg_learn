package cn.missfresh.module.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.interfaces.i;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ProductTitleView extends LinearLayout implements i {
    private TextView a;
    private TextView b;
    private boolean c;
    private boolean d;
    private int e;
    private int f;

    public ProductTitleView(Context context) {
        this(context, null);
    }

    public ProductTitleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProductTitleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(23839, false);
        this.c = true;
        this.d = true;
        this.e = 2;
        this.f = 3;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ProductTitleView);
        this.e = obtainStyledAttributes.getInt(R.styleable.ProductTitleView_customMaxLines, 2);
        this.f = obtainStyledAttributes.getInt(R.styleable.ProductTitleView_subtitleMarginTop, 3);
        obtainStyledAttributes.recycle();
        a(context);
        AppMethodBeat.o(23839);
    }

    private void a(Context context) {
        AppMethodBeat.i(23840, false);
        LayoutInflater.from(context).inflate(R.layout.layout_product_title, (ViewGroup) this, true);
        this.a = (TextView) findViewById(R.id.tv_product_name);
        this.b = (TextView) findViewById(R.id.tv_product_sub_title);
        this.a.setMaxLines(this.e);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.b.getLayoutParams();
        layoutParams.setMargins(0, aw.b(this.f), 0, 0);
        this.b.setLayoutParams(layoutParams);
        AppMethodBeat.o(23840);
    }

    public TextView getTvTitle() {
        return this.a;
    }

    @Override // cn.missfresh.module.base.common.interfaces.i
    public void a(String str, String str2) {
        AppMethodBeat.i(23842, false);
        if (!this.c) {
            AppMethodBeat.o(23842);
            return;
        }
        TextView textView = this.a;
        if (textView != null) {
            q.a(textView, str2, str);
        }
        AppMethodBeat.o(23842);
    }

    public void setTitleTextSize(int i) {
        AppMethodBeat.i(23843, false);
        if (!this.c) {
            AppMethodBeat.o(23843);
            return;
        }
        TextView textView = this.a;
        if (textView != null && i > 0) {
            textView.setTextSize((float) i);
        }
        AppMethodBeat.o(23843);
    }

    @Override // cn.missfresh.module.base.common.interfaces.i
    public void setSubTitle(String str) {
        AppMethodBeat.i(23844, false);
        if (!this.c) {
            AppMethodBeat.o(23844);
            return;
        }
        TextView textView = this.b;
        if (textView != null) {
            textView.setText(str);
        }
        AppMethodBeat.o(23844);
    }

    public void setSubTitleTextSize(int i) {
        AppMethodBeat.i(23845, false);
        if (!this.c) {
            AppMethodBeat.o(23845);
            return;
        }
        TextView textView = this.b;
        if (textView != null && i > 0) {
            textView.setTextSize((float) i);
        }
        AppMethodBeat.o(23845);
    }

    @Override // cn.missfresh.module.base.common.interfaces.m
    public void setShow(boolean z) {
        int i = 0;
        AppMethodBeat.i(23846, false);
        if (this.c == z) {
            AppMethodBeat.o(23846);
            return;
        }
        this.c = z;
        if (!this.c) {
            i = 8;
        }
        setVisibility(i);
        AppMethodBeat.o(23846);
    }

    public void setSubTitleShow(boolean z) {
        TextView textView;
        int i = 0;
        AppMethodBeat.i(23847, false);
        if (!this.c || (textView = this.b) == null || this.d == z) {
            AppMethodBeat.o(23847);
            return;
        }
        this.d = z;
        if (!this.d) {
            i = 8;
        }
        textView.setVisibility(i);
        AppMethodBeat.o(23847);
    }

    public void setTitleLines(int i) {
        AppMethodBeat.i(23848, false);
        this.a.setLines(i);
        this.a.setEllipsize(TextUtils.TruncateAt.END);
        AppMethodBeat.o(23848);
    }

    public void setSubTitleLines(int i) {
        AppMethodBeat.i(23849, false);
        this.b.setLines(i);
        this.b.setEllipsize(TextUtils.TruncateAt.END);
        AppMethodBeat.o(23849);
    }
}
