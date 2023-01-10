package cn.missfresh.module.base.widget.tag;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.widget.FitWidthImageView;
import cn.missfresh.module.base.widget.tag.bean.ProductTagInfoBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.f;

public class MissfreshTagView extends FrameLayout {
    private TextView a;
    private FitWidthImageView b;
    private TextView c;
    private FitWidthImageView d;
    private TextView e;
    private ImageView f;
    private float g;
    private float h;
    private float i;
    private float j;
    private boolean k;
    private boolean l;

    public MissfreshTagView(Context context) {
        this(context, null);
    }

    public MissfreshTagView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(24333, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MissfreshTagView);
        this.g = obtainStyledAttributes.getDimension(R.styleable.MissfreshTagView_top_left_fix_width, (float) f.c(context, 18));
        this.h = obtainStyledAttributes.getDimension(R.styleable.MissfreshTagView_top_right_fix_width, (float) f.c(context, 32));
        this.i = obtainStyledAttributes.getDimension(R.styleable.MissfreshTagView_bottom_lable_margin, (float) f.c(context, 10));
        this.j = obtainStyledAttributes.getDimension(R.styleable.MissfreshTagView_top_image_label_margin, (float) f.c(context, 8));
        this.k = obtainStyledAttributes.getBoolean(R.styleable.MissfreshTagView_top_left_image_label_gone, false);
        this.l = obtainStyledAttributes.getBoolean(R.styleable.MissfreshTagView_bottom_label_ellipsis_count_gone, false);
        obtainStyledAttributes.recycle();
        a(context);
        AppMethodBeat.o(24333);
    }

    private void a(Context context) {
        AppMethodBeat.i(24334, false);
        LayoutInflater.from(context).inflate(R.layout.base_missfresh_tag, this);
        this.a = (TextView) findViewById(R.id.tv_tag_top_left);
        this.b = (FitWidthImageView) findViewById(R.id.iv_tag_top_left);
        this.c = (TextView) findViewById(R.id.tv_tag_top_right);
        this.d = (FitWidthImageView) findViewById(R.id.iv_tag_top_right);
        this.e = (TextView) findViewById(R.id.tv_tag_bottom);
        this.f = (ImageView) findViewById(R.id.iv_tag_bottom);
        this.b.setFixedHeight((int) this.g);
        this.d.setFixedHeight((int) this.h);
        ((FrameLayout.LayoutParams) this.e.getLayoutParams()).leftMargin = (int) this.i;
        ((FrameLayout.LayoutParams) this.b.getLayoutParams()).leftMargin = (int) this.j;
        ((FrameLayout.LayoutParams) this.d.getLayoutParams()).rightMargin = (int) this.j;
        AppMethodBeat.o(24334);
    }

    public void setData(ProductTagInfoBean productTagInfoBean) {
        AppMethodBeat.i(24335, false);
        if (productTagInfoBean == null) {
            setVisibility(8);
            AppMethodBeat.o(24335);
            return;
        }
        setVisibility(0);
        a.a(this.a, this.b, this.k ? null : productTagInfoBean.getTopLeft());
        a.a(this.c, this.d, productTagInfoBean.getTopRight());
        a.a(this.e, this.f, productTagInfoBean.getBottom(), this.l);
        AppMethodBeat.o(24335);
    }
}
