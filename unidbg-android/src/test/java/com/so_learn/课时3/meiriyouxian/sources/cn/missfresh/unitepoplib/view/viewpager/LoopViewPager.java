package cn.missfresh.unitepoplib.view.viewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TimedRemoteCaller;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public class LoopViewPager extends ViewPager {
    private static final String a = LoopViewPager.class.getSimpleName();
    private LoopPagerAdapterWrapper b;
    private boolean c = false;
    private boolean d = true;
    private List<ViewPager.OnPageChangeListener> e;
    private boolean f = false;
    private boolean g = true;
    private Handler h = new AnonymousClass1(Looper.getMainLooper());
    private ViewPager.OnPageChangeListener i = new AnonymousClass2();
    private OnDispatchTouchEventListener j;

    public interface OnDispatchTouchEventListener {
        void onDispatchKeyEvent(MotionEvent motionEvent);
    }

    static /* synthetic */ int d(LoopViewPager loopViewPager) {
        AppMethodBeat.i(16020, false);
        int currentItem = super.getCurrentItem();
        AppMethodBeat.o(16020);
        return currentItem;
    }

    static {
        AppMethodBeat.i(16023, false);
        AppMethodBeat.o(16023);
    }

    /* renamed from: cn.missfresh.unitepoplib.view.viewpager.LoopViewPager$1  reason: invalid class name */
    class AnonymousClass1 extends Handler {
        AnonymousClass1(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AppMethodBeat.i(15938, false);
            if (LoopViewPager.this.f && LoopViewPager.this.b.getCount() > 0) {
                LoopViewPager.this.setCurrentItem(LoopViewPager.this.getCurrentItem() + 1);
            }
            AppMethodBeat.o(15938);
        }
    }

    public void setBoundaryCaching(boolean z) {
        AppMethodBeat.i(15954, false);
        this.c = z;
        LoopPagerAdapterWrapper loopPagerAdapterWrapper = this.b;
        if (loopPagerAdapterWrapper != null) {
            loopPagerAdapterWrapper.a(z);
        }
        AppMethodBeat.o(15954);
    }

    public void setLooperPic(boolean z) {
        AppMethodBeat.i(15956, false);
        this.f = z;
        a();
        AppMethodBeat.o(15956);
    }

    public void a() {
        AppMethodBeat.i(15959, false);
        this.h.removeMessages(1001);
        this.h.sendEmptyMessageDelayed(1001, TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS);
        AppMethodBeat.o(15959);
    }

    public void setBoundaryLooping(boolean z) {
        AppMethodBeat.i(15961, false);
        this.d = z;
        LoopPagerAdapterWrapper loopPagerAdapterWrapper = this.b;
        if (loopPagerAdapterWrapper != null) {
            loopPagerAdapterWrapper.b(z);
        }
        AppMethodBeat.o(15961);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setAdapter(PagerAdapter pagerAdapter) {
        AppMethodBeat.i(15963, false);
        this.b = new LoopPagerAdapterWrapper(pagerAdapter);
        this.b.a(this.c);
        this.b.b(this.d);
        super.setAdapter(this.b);
        setCurrentItem(0, false);
        AppMethodBeat.o(15963);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public PagerAdapter getAdapter() {
        AppMethodBeat.i(15966, false);
        LoopPagerAdapterWrapper loopPagerAdapterWrapper = this.b;
        PagerAdapter pagerAdapter = loopPagerAdapterWrapper;
        if (loopPagerAdapterWrapper != null) {
            pagerAdapter = loopPagerAdapterWrapper.b();
        }
        AppMethodBeat.o(15966);
        return pagerAdapter;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public int getCurrentItem() {
        int i = 0;
        AppMethodBeat.i(15967, false);
        LoopPagerAdapterWrapper loopPagerAdapterWrapper = this.b;
        if (loopPagerAdapterWrapper != null) {
            i = loopPagerAdapterWrapper.a(super.getCurrentItem());
        }
        AppMethodBeat.o(15967);
        return i;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i, boolean z) {
        AppMethodBeat.i(15969, false);
        super.setCurrentItem(this.b.b(i), z);
        AppMethodBeat.o(15969);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i) {
        AppMethodBeat.i(15972, false);
        if (getCurrentItem() != i) {
            setCurrentItem(i, true);
        }
        AppMethodBeat.o(15972);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        AppMethodBeat.i(15974, false);
        addOnPageChangeListener(onPageChangeListener);
        AppMethodBeat.o(15974);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        AppMethodBeat.i(15978, false);
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.e.add(onPageChangeListener);
        AppMethodBeat.o(15978);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void removeOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        AppMethodBeat.i(15982, false);
        List<ViewPager.OnPageChangeListener> list = this.e;
        if (list != null) {
            list.remove(onPageChangeListener);
        }
        AppMethodBeat.o(15982);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void clearOnPageChangeListeners() {
        AppMethodBeat.i(15986, false);
        List<ViewPager.OnPageChangeListener> list = this.e;
        if (list != null) {
            list.clear();
        }
        AppMethodBeat.o(15986);
    }

    public LoopViewPager(Context context) {
        super(context);
        AppMethodBeat.i(15989, false);
        a(context);
        AppMethodBeat.o(15989);
    }

    public LoopViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(15991, false);
        a(context);
        AppMethodBeat.o(15991);
    }

    private void a(Context context) {
        AppMethodBeat.i(15993, false);
        ViewPager.OnPageChangeListener onPageChangeListener = this.i;
        if (onPageChangeListener != null) {
            super.removeOnPageChangeListener(onPageChangeListener);
        }
        super.addOnPageChangeListener(this.i);
        AppMethodBeat.o(15993);
    }

    /* renamed from: cn.missfresh.unitepoplib.view.viewpager.LoopViewPager$2  reason: invalid class name */
    class AnonymousClass2 implements ViewPager.OnPageChangeListener {
        private float b = -1.0f;
        private float c = -1.0f;

        AnonymousClass2() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            AppMethodBeat.i(15941, false);
            int a = LoopViewPager.this.b.a(i);
            float f = (float) a;
            if (this.c != f) {
                this.c = f;
                if (LoopViewPager.this.e != null) {
                    for (int i2 = 0; i2 < LoopViewPager.this.e.size(); i2++) {
                        ViewPager.OnPageChangeListener onPageChangeListener = (ViewPager.OnPageChangeListener) LoopViewPager.this.e.get(i2);
                        if (onPageChangeListener != null) {
                            onPageChangeListener.onPageSelected(a);
                        }
                    }
                }
            }
            LoopViewPager.this.a();
            AppMethodBeat.o(15941);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            AppMethodBeat.i(15946, false);
            if (LoopViewPager.this.b != null) {
                int a = LoopViewPager.this.b.a(i);
                if (f == 0.0f && this.b == 0.0f && (i == 0 || i == LoopViewPager.this.b.getCount() - 1)) {
                    LoopViewPager.this.setCurrentItem(a, false);
                }
                i = a;
            }
            this.b = f;
            if (!(LoopViewPager.this.e == null || LoopViewPager.this.b == null)) {
                for (int i3 = 0; i3 < LoopViewPager.this.e.size(); i3++) {
                    ViewPager.OnPageChangeListener onPageChangeListener = (ViewPager.OnPageChangeListener) LoopViewPager.this.e.get(i3);
                    if (onPageChangeListener != null) {
                        if (i != LoopViewPager.this.b.a() - 1) {
                            onPageChangeListener.onPageScrolled(i, f, i2);
                        } else if (((double) f) > 0.5d) {
                            onPageChangeListener.onPageScrolled(0, 0.0f, 0);
                        } else {
                            onPageChangeListener.onPageScrolled(i, 0.0f, 0);
                        }
                    }
                }
            }
            AppMethodBeat.o(15946);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            AppMethodBeat.i(15947, false);
            if (LoopViewPager.this.b != null) {
                int d = LoopViewPager.d(LoopViewPager.this);
                int a = LoopViewPager.this.b.a(d);
                if (i == 0 && (d == 0 || d == LoopViewPager.this.b.getCount() - 1)) {
                    LoopViewPager.this.setCurrentItem(a, false);
                }
            }
            if (LoopViewPager.this.e != null) {
                for (int i2 = 0; i2 < LoopViewPager.this.e.size(); i2++) {
                    ViewPager.OnPageChangeListener onPageChangeListener = (ViewPager.OnPageChangeListener) LoopViewPager.this.e.get(i2);
                    if (onPageChangeListener != null) {
                        onPageChangeListener.onPageScrollStateChanged(i);
                    }
                }
            }
            AppMethodBeat.o(15947);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(15996, false);
        int action = motionEvent.getAction();
        if (action == 0 || action == 2) {
            getParent().requestDisallowInterceptTouchEvent(true);
        } else {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        AppMethodBeat.o(15996);
        return onTouchEvent;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(15999, false);
        OnDispatchTouchEventListener onDispatchTouchEventListener = this.j;
        if (onDispatchTouchEventListener != null) {
            onDispatchTouchEventListener.onDispatchKeyEvent(motionEvent);
        }
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        AppMethodBeat.o(15999);
        return dispatchTouchEvent;
    }

    public void setOnDispatchTouchEventListener(OnDispatchTouchEventListener onDispatchTouchEventListener) {
        this.j = onDispatchTouchEventListener;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        AppMethodBeat.i(16004, false);
        boolean dispatchKeyEvent = super.dispatchKeyEvent(keyEvent);
        AppMethodBeat.o(16004);
        return dispatchKeyEvent;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        AppMethodBeat.i(16007, false);
        boolean onKeyDown = super.onKeyDown(i, keyEvent);
        AppMethodBeat.o(16007);
        return onKeyDown;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        AppMethodBeat.i(16009, false);
        boolean onKeyUp = super.onKeyUp(i, keyEvent);
        AppMethodBeat.o(16009);
        return onKeyUp;
    }

    public void setScrollable(boolean z) {
        this.g = z;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(16013, false);
        if (!this.g) {
            AppMethodBeat.o(16013);
            return false;
        }
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        AppMethodBeat.o(16013);
        return onInterceptTouchEvent;
    }
}
