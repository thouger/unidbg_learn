package cn.missfresh.module.base.support.transformer;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class AlphaTranformer implements ViewPager.PageTransformer {
    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        AppMethodBeat.i(22663, false);
        int i = (f > -1.0f ? 1 : (f == -1.0f ? 0 : -1));
        view.setTranslationX((-f) * ((float) view.getWidth()));
        view.setAlpha((i < 0 || f > 1.0f) ? 0.0f : 1.0f - Math.abs(f));
        AppMethodBeat.o(22663);
    }
}
