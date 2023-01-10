package cn.missfresh.module.base.support.transformer;

import android.os.Build;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ScaleTransformer implements ViewPager.PageTransformer {
    private float a;

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        AppMethodBeat.i(22667, false);
        if (f < -1.0f || f > 1.0f) {
            view.setScaleX(this.a);
            view.setScaleY(this.a);
        } else {
            view.setScaleX(1.0f - (Math.abs(f) * (1.0f - this.a)));
            view.setScaleY(1.0f - (Math.abs(f) * (1.0f - this.a)));
        }
        if (Build.VERSION.SDK_INT < 19) {
            view.getParent().requestLayout();
        }
        AppMethodBeat.o(22667);
    }
}
