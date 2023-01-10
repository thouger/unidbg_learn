package cn.missfresh.module.base.support.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class MaxHeightRecyclerView extends RecyclerView {
    private static int[] a = {16843040};
    private int b;

    public MaxHeightRecyclerView(Context context) {
        super(context);
    }

    public MaxHeightRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(22749, false);
        a(context, attributeSet);
        AppMethodBeat.o(22749);
    }

    public MaxHeightRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(22750, false);
        a(context, attributeSet);
        AppMethodBeat.o(22750);
    }

    private void a(Context context, AttributeSet attributeSet) {
        AppMethodBeat.i(22752, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a);
        this.b = obtainStyledAttributes.getLayoutDimension(0, this.b);
        obtainStyledAttributes.recycle();
        AppMethodBeat.o(22752);
    }

    public void setMaxHeight(int i) {
        AppMethodBeat.i(22754, false);
        this.b = i;
        requestLayout();
        AppMethodBeat.o(22754);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(22755, false);
        int i3 = this.b;
        if (i3 > 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
        AppMethodBeat.o(22755);
    }
}
