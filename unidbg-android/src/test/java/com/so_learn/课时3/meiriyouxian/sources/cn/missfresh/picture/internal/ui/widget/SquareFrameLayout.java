package cn.missfresh.picture.internal.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SquareFrameLayout extends FrameLayout {
    public SquareFrameLayout(Context context) {
        super(context);
    }

    public SquareFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(19032, false);
        super.onMeasure(i, i);
        AppMethodBeat.o(19032);
    }
}
