package cn.missfresh.module.base.support;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SquareImageView extends ImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(20110, false);
        super.onMeasure(i, i2);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
        AppMethodBeat.o(20110);
    }
}
