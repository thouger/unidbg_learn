package cn.missfresh.module.base.support.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class VerticalViewPager extends ViewPager {
    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public VerticalViewPager(Context context) {
        super(context);
        AppMethodBeat.i(22815, false);
        a();
        AppMethodBeat.o(22815);
    }

    public VerticalViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(22816, false);
        a();
        AppMethodBeat.o(22816);
    }

    private void a() {
        AppMethodBeat.i(22817, false);
        setPageTransformer(true, new a());
        setOverScrollMode(2);
        AppMethodBeat.o(22817);
    }

    /* access modifiers changed from: private */
    public class a implements ViewPager.PageTransformer {
        private a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.PageTransformer
        public void transformPage(View view, float f) {
            AppMethodBeat.i(22814, false);
            if (f < -1.0f) {
                view.setAlpha(0.0f);
            } else if (f <= 1.0f) {
                view.setAlpha(1.0f);
                view.setTranslationX(((float) view.getWidth()) * (-f));
                view.setTranslationY(f * ((float) view.getHeight()));
            } else {
                view.setAlpha(0.0f);
            }
            AppMethodBeat.o(22814);
        }
    }
}
