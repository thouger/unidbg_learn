package cn.missfresh.module.base.widget.banner.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.missfresh.module.base.widget.banner.adapter.LoopPagerAdapterWraper;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class BannerViewPager extends ViewPager {
    private ViewPager.OnPageChangeListener a;
    private LoopPagerAdapterWraper b;
    private ViewPagerScroller c;
    private boolean d;
    private boolean e;
    private boolean f;
    private ViewPager.OnPageChangeListener g;

    static /* synthetic */ int d(BannerViewPager bannerViewPager) {
        AppMethodBeat.i(23959, false);
        int currentItem = super.getCurrentItem();
        AppMethodBeat.o(23959);
        return currentItem;
    }

    /* renamed from: cn.missfresh.module.base.widget.banner.view.BannerViewPager$1  reason: invalid class name */
    class AnonymousClass1 implements ViewPager.OnPageChangeListener {
        private float b = -1.0f;
        private float c = -1.0f;

        AnonymousClass1() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            AppMethodBeat.i(23946, false);
            if (BannerViewPager.this.b != null) {
                int a = BannerViewPager.this.b.a(i);
                if (BannerViewPager.this.e && f == 0.0f && this.b == 0.0f && (i == 0 || i == BannerViewPager.this.b.getCount() - 1)) {
                    BannerViewPager.this.setCurrentItem(a, false);
                }
                i = a;
            }
            this.b = f;
            if (BannerViewPager.this.a != null) {
                if (BannerViewPager.this.b != null && i != BannerViewPager.this.b.a() - 1) {
                    BannerViewPager.this.a.onPageScrolled(i, f, i2);
                } else if (((double) f) > 0.5d) {
                    BannerViewPager.this.a.onPageScrolled(0, 0.0f, 0);
                } else {
                    BannerViewPager.this.a.onPageScrolled(i, 0.0f, 0);
                }
            }
            AppMethodBeat.o(23946);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            AppMethodBeat.i(23947, false);
            int a = BannerViewPager.this.b.a(i);
            float f = (float) a;
            if (this.c != f) {
                this.c = f;
                if (BannerViewPager.this.a != null) {
                    BannerViewPager.this.a.onPageSelected(a);
                }
            }
            AppMethodBeat.o(23947);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            AppMethodBeat.i(23948, false);
            if (BannerViewPager.this.b != null) {
                int d = BannerViewPager.d(BannerViewPager.this);
                int a = BannerViewPager.this.b.a(d);
                if (BannerViewPager.this.e && i == 0 && (d == 0 || d == BannerViewPager.this.b.getCount() - 1)) {
                    BannerViewPager.this.setCurrentItem(a, false);
                }
            }
            if (BannerViewPager.this.a != null) {
                BannerViewPager.this.a.onPageScrollStateChanged(i);
            }
            AppMethodBeat.o(23948);
        }
    }

    public BannerViewPager(Context context) {
        this(context, null);
    }

    public BannerViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(23949, false);
        this.d = true;
        this.e = true;
        this.f = true;
        this.g = new AnonymousClass1();
        b();
        AppMethodBeat.o(23949);
    }

    public void setIsCanScroll(boolean z) {
        this.f = z;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        AppMethodBeat.i(23950, false);
        if (this.f && super.onTouchEvent(motionEvent)) {
            z = true;
        }
        AppMethodBeat.o(23950);
        return z;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        AppMethodBeat.i(23951, false);
        if (this.f && super.onInterceptTouchEvent(motionEvent)) {
            z = true;
        }
        AppMethodBeat.o(23951);
        return z;
    }

    public void setBoundaryCaching(boolean z) {
        AppMethodBeat.i(23952, false);
        this.d = z;
        LoopPagerAdapterWraper loopPagerAdapterWraper = this.b;
        if (loopPagerAdapterWraper != null) {
            loopPagerAdapterWraper.a(z);
        }
        AppMethodBeat.o(23952);
    }

    public void a(PagerAdapter pagerAdapter, boolean z) {
        AppMethodBeat.i(23953, false);
        this.b = new LoopPagerAdapterWraper(pagerAdapter, z);
        this.b.a(this.d);
        setAdapter(this.b);
        setCurrentItem(0, false);
        AppMethodBeat.o(23953);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public PagerAdapter getAdapter() {
        AppMethodBeat.i(23954, false);
        LoopPagerAdapterWraper loopPagerAdapterWraper = this.b;
        PagerAdapter pagerAdapter = loopPagerAdapterWraper;
        if (loopPagerAdapterWraper != null) {
            pagerAdapter = loopPagerAdapterWraper.b();
        }
        AppMethodBeat.o(23954);
        return pagerAdapter;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public int getCurrentItem() {
        int i = 0;
        AppMethodBeat.i(23955, false);
        LoopPagerAdapterWraper loopPagerAdapterWraper = this.b;
        if (loopPagerAdapterWraper != null) {
            i = loopPagerAdapterWraper.a(super.getCurrentItem());
        }
        AppMethodBeat.o(23955);
        return i;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i) {
        AppMethodBeat.i(23956, false);
        if (getCurrentItem() != i) {
            setCurrentItem(i, true);
        }
        AppMethodBeat.o(23956);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i, boolean z) {
        int i2 = 0;
        AppMethodBeat.i(23957, false);
        try {
            if (this.b != null) {
                i2 = this.b.b(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setCurrentItem(i2, z);
        AppMethodBeat.o(23957);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.a = onPageChangeListener;
    }

    private void b() {
        AppMethodBeat.i(23958, false);
        super.setOnPageChangeListener(this.g);
        AppMethodBeat.o(23958);
    }

    public void setScroller(ViewPagerScroller viewPagerScroller) {
        this.c = viewPagerScroller;
    }

    public boolean a() {
        return this.e;
    }
}
