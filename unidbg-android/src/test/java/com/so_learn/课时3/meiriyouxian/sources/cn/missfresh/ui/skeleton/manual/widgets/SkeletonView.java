package cn.missfresh.ui.skeleton.manual.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SkeletonView extends View {
    public SkeletonView(Context context) {
        this(context, null);
    }

    public SkeletonView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkeletonView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(2292, false);
        a.a(this, context, attributeSet, i);
        AppMethodBeat.o(2292);
    }
}
