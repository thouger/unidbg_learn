package cn.missfresh.ui.skeleton.auto.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;
import cn.missfresh.ui.skeleton.auto.b;

public class AutoSkeletonLinearLayout extends LinearLayout {
    private b a;

    public AutoSkeletonLinearLayout(Context context) {
        super(context);
        AppMethodBeat.i(1962, false);
        a(context, null);
        AppMethodBeat.o(1962);
    }

    public AutoSkeletonLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(1964, false);
        a(context, attributeSet);
        AppMethodBeat.o(1964);
    }

    public AutoSkeletonLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(1966, false);
        a(context, attributeSet);
        AppMethodBeat.o(1966);
    }

    private void a(Context context, AttributeSet attributeSet) {
        boolean z = false;
        AppMethodBeat.i(1970, false);
        this.a = new b(this);
        this.a.a(true);
        int i = 3;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Skeleton);
            z = obtainStyledAttributes.getBoolean(R.styleable.Skeleton_anim_enable, false);
            i = obtainStyledAttributes.getInt(R.styleable.Skeleton_skeleton_depth, 3);
            obtainStyledAttributes.recycle();
        }
        this.a.a(i);
        this.a.b(z);
        AppMethodBeat.o(1970);
    }

    public void setDelegate(b bVar) {
        AppMethodBeat.i(1973, false);
        a();
        this.a = bVar;
        AppMethodBeat.o(1973);
    }

    public void setAnimEnable(boolean z) {
        AppMethodBeat.i(1974, false);
        this.a.b(z);
        AppMethodBeat.o(1974);
    }

    public void a() {
        AppMethodBeat.i(1977, false);
        this.a.c();
        this.a.a(false);
        AppMethodBeat.o(1977);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        AppMethodBeat.i(1983, false);
        if (!this.a.a()) {
            super.dispatchDraw(canvas);
        }
        AppMethodBeat.o(1983);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        AppMethodBeat.i(1987, false);
        if (!this.a.a()) {
            super.onDraw(canvas);
        } else {
            this.a.a(canvas);
        }
        AppMethodBeat.o(1987);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        AppMethodBeat.i(1990, false);
        super.onAttachedToWindow();
        this.a.b();
        AppMethodBeat.o(1990);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AppMethodBeat.i(1992, false);
        this.a.c();
        super.onDetachedFromWindow();
        AppMethodBeat.o(1992);
    }
}
