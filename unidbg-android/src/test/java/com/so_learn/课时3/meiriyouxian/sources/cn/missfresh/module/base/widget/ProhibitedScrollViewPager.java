package cn.missfresh.module.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ProhibitedScrollViewPager extends ViewPager {
    private boolean a = true;

    public ProhibitedScrollViewPager(Context context) {
        super(context);
    }

    public ProhibitedScrollViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(23850, false);
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        AppMethodBeat.o(23850);
        return dispatchTouchEvent;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(23851, false);
        try {
            if (this.a) {
                boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
                AppMethodBeat.o(23851);
                return onInterceptTouchEvent;
            }
            AppMethodBeat.o(23851);
            return false;
        } catch (Exception unused) {
            AppMethodBeat.o(23851);
            return false;
        }
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        AppMethodBeat.i(23852, false);
        try {
            if (this.a) {
                boolean onTouchEvent = super.onTouchEvent(motionEvent);
                AppMethodBeat.o(23852);
                return onTouchEvent;
            }
            AppMethodBeat.o(23852);
            return true;
        } catch (Exception unused) {
            AppMethodBeat.o(23852);
            return false;
        }
    }

    public void setScroll(boolean z) {
        this.a = z;
    }
}
