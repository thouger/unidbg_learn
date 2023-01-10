package cn.missfresh.module.base.widget.banner.listener;

import android.widget.ImageView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;

public class BannerDIPageChangedListener extends BannerPageChangedListener {
    private ArrayList<ImageView> a;
    private int[] b;

    public BannerDIPageChangedListener(ArrayList<ImageView> arrayList, int[] iArr) {
        this.a = arrayList;
        this.b = iArr;
    }

    @Override // cn.missfresh.module.base.widget.banner.listener.BannerPageChangedListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        AppMethodBeat.i(23936, false);
        super.onPageScrolled(i, f, i2);
        AppMethodBeat.o(23936);
    }

    @Override // cn.missfresh.module.base.widget.banner.listener.BannerPageChangedListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        AppMethodBeat.i(23937, false);
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            this.a.get(i).setImageResource(this.b[1]);
            if (i != i2) {
                this.a.get(i2).setImageResource(this.b[0]);
            }
        }
        super.onPageSelected(i);
        AppMethodBeat.o(23937);
    }

    @Override // cn.missfresh.module.base.widget.banner.listener.BannerPageChangedListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        AppMethodBeat.i(23938, false);
        super.onPageScrollStateChanged(i);
        AppMethodBeat.o(23938);
    }
}
