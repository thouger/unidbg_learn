package cn.missfresh.module.base.widget.banner.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.TimedRemoteCaller;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.listener.b;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.widget.banner.adapter.BannerPageAdapter;
import cn.missfresh.module.base.widget.banner.listener.BannerDIPageChangedListener;
import cn.missfresh.module.base.widget.banner.listener.BannerPNPageChangedListener;
import cn.missfresh.module.base.widget.banner.listener.BannerPageChangedListener;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ConvenientBanner<T> extends LinearLayout {
    private c a;
    private List<T> b;
    private int[] c;
    private ArrayList<ImageView> d;
    private BannerPageChangedListener e;
    private ViewPager.OnPageChangeListener f;
    private b g;
    private BannerPageAdapter h;
    private BannerViewPager i;
    private ViewPagerScroller j;
    private LinearLayout k;
    private long l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private int q;
    private boolean r;
    private boolean s;
    private int t;
    private int u;
    private Handler v;
    private Runnable w;

    /* renamed from: cn.missfresh.module.base.widget.banner.view.ConvenientBanner$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            AppMethodBeat.i(23960, false);
            if (ConvenientBanner.this.i != null && ConvenientBanner.this.m && !cn.missfresh.utils.b.a(ConvenientBanner.this.b)) {
                if (ConvenientBanner.this.p) {
                    i = ConvenientBanner.this.i.getCurrentItem() + 1;
                } else {
                    i = (ConvenientBanner.this.i.getCurrentItem() + 1) % ConvenientBanner.this.b.size();
                }
                ConvenientBanner.this.i.setCurrentItem(i);
                ConvenientBanner.this.k.requestLayout();
                ConvenientBanner.this.v.postDelayed(ConvenientBanner.this.w, ConvenientBanner.this.l);
            }
            AppMethodBeat.o(23960);
        }
    }

    public ConvenientBanner(Context context, boolean z) {
        this(context, (AttributeSet) null);
        this.p = z;
    }

    public ConvenientBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(23965, false);
        this.d = new ArrayList<>();
        this.n = false;
        this.o = true;
        this.p = true;
        this.q = -1;
        this.r = false;
        this.s = false;
        this.u = 18;
        this.v = new Handler();
        this.w = new AnonymousClass1();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BannerView);
        this.p = obtainStyledAttributes.getBoolean(R.styleable.BannerView_canLoop, true);
        this.r = obtainStyledAttributes.getBoolean(R.styleable.BannerView_showOthers, false);
        this.q = obtainStyledAttributes.getResourceId(R.styleable.BannerView_customRestrainLayout, -1);
        this.s = obtainStyledAttributes.getBoolean(R.styleable.BannerView_indicatorDownViewPager, false);
        this.t = obtainStyledAttributes.getDimensionPixelSize(R.styleable.BannerView_showOthersSize, 20);
        obtainStyledAttributes.recycle();
        a(context);
        AppMethodBeat.o(23965);
    }

    private void a(Context context) {
        View view;
        AppMethodBeat.i(23966, false);
        if (this.q > 0) {
            view = LayoutInflater.from(context).inflate(this.q, (ViewGroup) this, true);
        } else if (this.s) {
            view = LayoutInflater.from(context).inflate(R.layout.include_banner_indicator_down_view_page, (ViewGroup) this, true);
        } else if (!this.r) {
            view = LayoutInflater.from(context).inflate(R.layout.include_banner_view_pager, (ViewGroup) this, true);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.include_banner_view_pager2, (ViewGroup) this, true);
        }
        this.i = (BannerViewPager) view.findViewById(R.id.cbLoopViewPager);
        if (this.r && this.q <= 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.i.getLayoutParams();
            int i = this.t;
            layoutParams.setMargins(i, 0, i, 0);
            this.i.setLayoutParams(layoutParams);
            this.i.setPageMargin(aw.b(10));
            this.i.setOffscreenPageLimit(3);
        }
        this.k = (LinearLayout) view.findViewById(R.id.loPageTurningPoint);
        e();
        AppMethodBeat.o(23966);
    }

    public List<T> getData() {
        return this.b;
    }

    public void a() {
        AppMethodBeat.i(23968, false);
        int i = this.u;
        if (i == 18) {
            int[] iArr = this.c;
            if (iArr != null) {
                a(iArr);
            }
        } else if (i == 19) {
            b();
        }
        AppMethodBeat.o(23968);
    }

    public ConvenientBanner a(c cVar, List<T> list, b bVar) {
        AppMethodBeat.i(23969, false);
        this.b = list;
        boolean z = this.p;
        List<T> list2 = this.b;
        if (list2 == null || list2.size() <= 1) {
            z = false;
        }
        this.a = cVar;
        this.h = new BannerPageAdapter(cVar, this.b);
        a(bVar);
        this.i.a(this.h, z);
        this.i.setBoundaryCaching(true);
        a();
        AppMethodBeat.o(23969);
        return this;
    }

    public void setTransformer(ViewPager.PageTransformer pageTransformer) {
        AppMethodBeat.i(23970, false);
        this.i.setPageTransformer(true, pageTransformer);
        AppMethodBeat.o(23970);
    }

    public ConvenientBanner a(boolean z) {
        int i = 0;
        AppMethodBeat.i(23972, false);
        LinearLayout linearLayout = this.k;
        if (!z) {
            i = 8;
        }
        linearLayout.setVisibility(i);
        AppMethodBeat.o(23972);
        return this;
    }

    public ConvenientBanner b() {
        AppMethodBeat.i(23973, false);
        ConvenientBanner a = a(-1);
        AppMethodBeat.o(23973);
        return a;
    }

    public ConvenientBanner a(int i) {
        View view;
        AppMethodBeat.i(23974, false);
        this.u = 19;
        this.k.removeAllViews();
        this.k.setGravity(5);
        if (this.b == null) {
            AppMethodBeat.o(23974);
            return this;
        }
        a(true);
        if (i == -1) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.layout_page_num_indicator, (ViewGroup) this.k, false);
        } else {
            view = LayoutInflater.from(getContext()).inflate(i, (ViewGroup) this.k, false);
        }
        this.k.addView(view);
        this.e = new BannerPNPageChangedListener((TextView) view.findViewById(R.id.tv_page_num), String.valueOf(this.b.size()));
        this.i.setOnPageChangeListener(this.e);
        this.e.onPageSelected(this.i.getCurrentItem());
        ViewPager.OnPageChangeListener onPageChangeListener = this.f;
        if (onPageChangeListener != null) {
            this.e.a(onPageChangeListener);
        }
        AppMethodBeat.o(23974);
        return this;
    }

    public ConvenientBanner a(int[] iArr) {
        AppMethodBeat.i(23975, false);
        this.u = 18;
        this.k.removeAllViews();
        this.k.setGravity(17);
        this.d.clear();
        this.c = iArr;
        if (this.b == null) {
            AppMethodBeat.o(23975);
            return this;
        }
        a(true);
        for (int i = 0; i < this.b.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setPadding(5, 0, 5, 0);
            if (this.d.isEmpty()) {
                imageView.setImageResource(iArr[1]);
            } else {
                imageView.setImageResource(iArr[0]);
            }
            this.d.add(imageView);
            this.k.addView(imageView);
        }
        this.e = new BannerDIPageChangedListener(this.d, iArr);
        this.i.setOnPageChangeListener(this.e);
        this.e.onPageSelected(this.i.getCurrentItem());
        ViewPager.OnPageChangeListener onPageChangeListener = this.f;
        if (onPageChangeListener != null) {
            this.e.a(onPageChangeListener);
        }
        AppMethodBeat.o(23975);
        return this;
    }

    public ConvenientBanner a(long j) {
        AppMethodBeat.i(23977, false);
        if (this.m) {
            c();
        }
        this.n = true;
        this.l = j;
        this.m = true;
        this.v.postDelayed(this.w, j);
        AppMethodBeat.o(23977);
        return this;
    }

    public void c() {
        AppMethodBeat.i(23978, false);
        this.m = false;
        Handler handler = this.v;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        AppMethodBeat.o(23978);
    }

    private void e() {
        AppMethodBeat.i(23980, false);
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            this.j = new ViewPagerScroller(this.i.getContext());
            declaredField.set(this.i, this.j);
            this.i.setScroller(this.j);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
        AppMethodBeat.o(23980);
    }

    public void setManualPageable(boolean z) {
        AppMethodBeat.i(23982, false);
        this.i.setIsCanScroll(z);
        AppMethodBeat.o(23982);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(23983, false);
        int action = motionEvent.getAction();
        if (action == 1 || action == 3 || action == 4) {
            if (this.n) {
                a(this.l);
            }
        } else if (action == 0 && this.n) {
            c();
        }
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        AppMethodBeat.o(23983);
        return dispatchTouchEvent;
    }

    public int getCurrentItem() {
        AppMethodBeat.i(23984, false);
        BannerViewPager bannerViewPager = this.i;
        if (bannerViewPager != null) {
            int currentItem = bannerViewPager.getCurrentItem();
            AppMethodBeat.o(23984);
            return currentItem;
        }
        AppMethodBeat.o(23984);
        return -1;
    }

    public void setcurrentitem(int i) {
        AppMethodBeat.i(23985, false);
        BannerViewPager bannerViewPager = this.i;
        if (bannerViewPager != null) {
            bannerViewPager.setCurrentItem(i);
        }
        AppMethodBeat.o(23985);
    }

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return this.f;
    }

    public ConvenientBanner a(ViewPager.OnPageChangeListener onPageChangeListener) {
        AppMethodBeat.i(23986, false);
        this.f = onPageChangeListener;
        BannerPageChangedListener bannerPageChangedListener = this.e;
        if (bannerPageChangedListener != null) {
            bannerPageChangedListener.a(onPageChangeListener);
        } else {
            this.i.setOnPageChangeListener(onPageChangeListener);
        }
        AppMethodBeat.o(23986);
        return this;
    }

    public boolean d() {
        AppMethodBeat.i(23987, false);
        boolean a = this.i.a();
        AppMethodBeat.o(23987);
        return a;
    }

    public ConvenientBanner a(b bVar) {
        AppMethodBeat.i(23988, false);
        if (bVar == null) {
            this.h.a((View.OnClickListener) null);
            AppMethodBeat.o(23988);
            return this;
        }
        this.g = bVar;
        this.h.a(new AnonymousClass2());
        AppMethodBeat.o(23988);
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.banner.view.ConvenientBanner$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23961, false);
            ConvenientBanner.this.g.a(ConvenientBanner.this.getCurrentItem());
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23961);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        AppMethodBeat.i(23989, false);
        super.onAttachedToWindow();
        if (this.n) {
            a((long) TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS);
        }
        AppMethodBeat.o(23989);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AppMethodBeat.i(23990, false);
        super.onDetachedFromWindow();
        c();
        AppMethodBeat.o(23990);
    }

    public int getScrollDuration() {
        AppMethodBeat.i(23991, false);
        int a = this.j.a();
        AppMethodBeat.o(23991);
        return a;
    }

    public void setScrollDuration(int i) {
        AppMethodBeat.i(23992, false);
        this.j.a(i);
        AppMethodBeat.o(23992);
    }

    public ImageView getCurrentView() {
        AppMethodBeat.i(23993, false);
        BannerPageAdapter bannerPageAdapter = this.h;
        if (bannerPageAdapter != null) {
            ImageView a = bannerPageAdapter.a();
            AppMethodBeat.o(23993);
            return a;
        }
        AppMethodBeat.o(23993);
        return null;
    }

    public void setNeedCollectView(boolean z) {
        AppMethodBeat.i(23994, false);
        BannerPageAdapter bannerPageAdapter = this.h;
        if (bannerPageAdapter != null) {
            bannerPageAdapter.a(z);
        }
        AppMethodBeat.o(23994);
    }

    public List<View> getChildViews() {
        AppMethodBeat.i(23995, false);
        BannerPageAdapter bannerPageAdapter = this.h;
        if (bannerPageAdapter != null) {
            List<View> b = bannerPageAdapter.b();
            AppMethodBeat.o(23995);
            return b;
        }
        AppMethodBeat.o(23995);
        return null;
    }

    public BannerViewPager getViewPager() {
        return this.i;
    }

    public enum PageIndicatorAlign {
        ALIGN_PARENT_LEFT,
        ALIGN_PARENT_RIGHT,
        CENTER_HORIZONTAL;

        public static PageIndicatorAlign valueOf(String str) {
            AppMethodBeat.i(23963, false);
            PageIndicatorAlign pageIndicatorAlign = (PageIndicatorAlign) Enum.valueOf(PageIndicatorAlign.class, str);
            AppMethodBeat.o(23963);
            return pageIndicatorAlign;
        }

        static {
            AppMethodBeat.i(23964, false);
            AppMethodBeat.o(23964);
        }
    }
}
