package cn.missfresh.picture.internal.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;

public class PreviewViewPager extends ViewPager {
    public PreviewViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.viewpager.widget.ViewPager
    public boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        boolean z2 = false;
        AppMethodBeat.i(19042, false);
        if (view instanceof ImageViewTouch) {
            if (((ImageViewTouch) view).a(i) || super.canScroll(view, z, i, i2, i3)) {
                z2 = true;
            }
            AppMethodBeat.o(19042);
            return z2;
        }
        boolean canScroll = super.canScroll(view, z, i, i2, i3);
        AppMethodBeat.o(19042);
        return canScroll;
    }
}
