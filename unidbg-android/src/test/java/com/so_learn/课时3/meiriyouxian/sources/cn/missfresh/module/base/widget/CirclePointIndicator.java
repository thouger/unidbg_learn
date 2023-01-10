package cn.missfresh.module.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class CirclePointIndicator extends LinearLayout {
    private int a;
    private int b;
    private int c;

    public CirclePointIndicator(Context context) {
        this(context, null);
    }

    public CirclePointIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CirclePointIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = R.drawable.shape_point_indicator_selected;
        this.b = R.drawable.shape_point_indicator_normal;
        this.c = 8;
    }

    public void setDef_width(int i) {
        this.c = i;
    }

    public void setCount(int i) {
        AppMethodBeat.i(23630, false);
        removeAllViews();
        int b = aw.b(this.c);
        int b2 = aw.b(8);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(b, b);
        for (int i2 = 0; i2 < i; i2++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(this.a);
            imageView.setId(i2);
            layoutParams.setMargins(0, 0, b2, 0);
            imageView.setLayoutParams(layoutParams);
            addView(imageView);
        }
        AppMethodBeat.o(23630);
    }

    public void setSelection(int i) {
        AppMethodBeat.i(23631, false);
        int childCount = getChildCount();
        if (childCount <= 0) {
            AppMethodBeat.o(23631);
            return;
        }
        int i2 = i % childCount;
        for (int i3 = 0; i3 < childCount; i3++) {
            ImageView imageView = (ImageView) getChildAt(i3);
            if (imageView.getId() == i2) {
                imageView.setBackgroundResource(this.a);
            } else {
                imageView.setBackgroundResource(this.b);
            }
        }
        AppMethodBeat.o(23631);
    }

    public void setSelctedIndicatorrRes(int i) {
        this.a = i;
    }

    public void setNormalIndicatorRes(int i) {
        this.b = i;
    }
}
