package cn.missfresh.module.base.support;

import android.content.Context;
import android.util.AttributeSet;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.roundiv.MFRoundedImageView;

public class SquareRoundImageView extends MFRoundedImageView {
    public SquareRoundImageView(Context context) {
        super(context);
    }

    public SquareRoundImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquareRoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(20120, false);
        super.onMeasure(i, i2);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
        AppMethodBeat.o(20120);
    }
}
