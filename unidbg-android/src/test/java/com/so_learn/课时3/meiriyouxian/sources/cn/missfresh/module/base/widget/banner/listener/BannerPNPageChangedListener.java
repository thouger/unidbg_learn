package cn.missfresh.module.base.widget.banner.listener;

import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class BannerPNPageChangedListener extends BannerPageChangedListener {
    private TextView a;
    private String b;
    private String c;

    public BannerPNPageChangedListener(TextView textView, String str) {
        AppMethodBeat.i(23939, false);
        this.a = textView;
        this.c = str;
        this.b = textView.getContext().getString(R.string.page_num_indicator);
        AppMethodBeat.o(23939);
    }

    @Override // cn.missfresh.module.base.widget.banner.listener.BannerPageChangedListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        AppMethodBeat.i(23940, false);
        super.onPageScrolled(i, f, i2);
        AppMethodBeat.o(23940);
    }

    @Override // cn.missfresh.module.base.widget.banner.listener.BannerPageChangedListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        AppMethodBeat.i(23941, false);
        int i2 = i + 1;
        this.a.setText(String.format(this.b, String.valueOf(i2), this.c));
        super.onPageSelected(i2);
        AppMethodBeat.o(23941);
    }

    @Override // cn.missfresh.module.base.widget.banner.listener.BannerPageChangedListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        AppMethodBeat.i(23942, false);
        super.onPageScrollStateChanged(i);
        AppMethodBeat.o(23942);
    }
}
