package cn.missfresh.module.base.widget.banner.listener;

import androidx.viewpager.widget.ViewPager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class BannerPageChangedListener implements ViewPager.OnPageChangeListener {
    private ViewPager.OnPageChangeListener a;

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        AppMethodBeat.i(23943, false);
        ViewPager.OnPageChangeListener onPageChangeListener = this.a;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i, f, i2);
        }
        AppMethodBeat.o(23943);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        AppMethodBeat.i(23944, false);
        ViewPager.OnPageChangeListener onPageChangeListener = this.a;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i);
        }
        AppMethodBeat.o(23944);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        AppMethodBeat.i(23945, false);
        ViewPager.OnPageChangeListener onPageChangeListener = this.a;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
        AppMethodBeat.o(23945);
    }

    public void a(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.a = onPageChangeListener;
    }
}
