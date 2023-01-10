package cn.missfresh.module.base.support;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class FullScreenVideoView extends VideoView {
    public FullScreenVideoView(Context context) {
        super(context);
    }

    public FullScreenVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FullScreenVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(19956, false);
        int defaultSize = getDefaultSize(0, i);
        setMeasuredDimension(defaultSize, (int) ((((double) (defaultSize * 16)) * 1.0d) / 9.0d));
        AppMethodBeat.o(19956);
    }
}
