package cn.missfresh.ui.skeleton.auto.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;
import cn.missfresh.ui.skeleton.auto.b;

public class AutoSkeletonFrameLayout extends FrameLayout {
    private b a;

    public AutoSkeletonFrameLayout(Context context) {
        super(context);
        AppMethodBeat.i(1925, false);
        a(context, null);
        AppMethodBeat.o(1925);
    }

    public AutoSkeletonFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(1928, false);
        a(context, attributeSet);
        AppMethodBeat.o(1928);
    }

    public AutoSkeletonFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(1930, false);
        a(context, attributeSet);
        AppMethodBeat.o(1930);
    }

    private void a(Context context, AttributeSet attributeSet) {
        boolean z = false;
        AppMethodBeat.i(1933, false);
        this.a = new b(this);
        this.a.a(true);
        int i = 3;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(R.styleable.Skeleton);
            z = obtainStyledAttributes.getBoolean(R.styleable.Skeleton_anim_enable, false);
            i = obtainStyledAttributes.getInt(R.styleable.Skeleton_skeleton_depth, 3);
            obtainStyledAttributes.recycle();
        }
        this.a.a(i);
        this.a.b(z);
        AppMethodBeat.o(1933);
    }

    public void setDelegate(b bVar) {
        AppMethodBeat.i(1936, false);
        a();
        this.a = bVar;
        AppMethodBeat.o(1936);
    }

    public void setAnimEnable(boolean z) {
        AppMethodBeat.i(1940, false);
        this.a.b(z);
        AppMethodBeat.o(1940);
    }

    public void a() {
        AppMethodBeat.i(1944, false);
        this.a.c();
        this.a.a(false);
        AppMethodBeat.o(1944);
    }

    public void b() {
        AppMethodBeat.i(1947, false);
        this.a.a(true);
        this.a.b();
        AppMethodBeat.o(1947);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        AppMethodBeat.i(1950, false);
        if (!this.a.a()) {
            super.dispatchDraw(canvas);
        }
        AppMethodBeat.o(1950);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        AppMethodBeat.i(1952, false);
        if (!this.a.a()) {
            super.onDraw(canvas);
        } else {
            this.a.a(canvas);
        }
        AppMethodBeat.o(1952);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        AppMethodBeat.i(1955, false);
        super.onAttachedToWindow();
        this.a.b();
        AppMethodBeat.o(1955);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AppMethodBeat.i(1958, false);
        this.a.c();
        super.onDetachedFromWindow();
        AppMethodBeat.o(1958);
    }
}
