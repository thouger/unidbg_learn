package cn.missfresh.module.base.support.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class MaxHeightLinearLayout extends LinearLayout {
    private static int[] a = {16843040};
    private int b;

    public MaxHeightLinearLayout(Context context) {
        this(context, null);
    }

    public MaxHeightLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaxHeightLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(22743, false);
        a(context, attributeSet);
        AppMethodBeat.o(22743);
    }

    private void a(Context context, AttributeSet attributeSet) {
        AppMethodBeat.i(22744, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a);
        this.b = obtainStyledAttributes.getLayoutDimension(0, this.b);
        obtainStyledAttributes.recycle();
        AppMethodBeat.o(22744);
    }

    public void setMaxHeight(int i) {
        AppMethodBeat.i(22746, false);
        this.b = i;
        requestLayout();
        AppMethodBeat.o(22746);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(22747, false);
        if (this.b > 0) {
            int size = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            if (mode == Integer.MIN_VALUE) {
                i2 = View.MeasureSpec.makeMeasureSpec(Math.min(size, this.b), Integer.MIN_VALUE);
            } else if (mode == 0) {
                i2 = View.MeasureSpec.makeMeasureSpec(this.b, Integer.MIN_VALUE);
            } else if (mode == 1073741824) {
                i2 = View.MeasureSpec.makeMeasureSpec(Math.min(size, this.b), 1073741824);
            }
        }
        super.onMeasure(i, i2);
        AppMethodBeat.o(22747);
    }
}
