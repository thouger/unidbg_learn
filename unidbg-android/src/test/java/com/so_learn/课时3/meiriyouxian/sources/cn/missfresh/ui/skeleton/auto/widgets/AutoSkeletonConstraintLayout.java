package cn.missfresh.ui.skeleton.auto.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.hardware.camera2.legacy.LegacyCameraDevice;
import android.telephony.PreciseDisconnectCause;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;
import cn.missfresh.ui.skeleton.auto.b;

public class AutoSkeletonConstraintLayout extends ConstraintLayout {
    private b a;

    public AutoSkeletonConstraintLayout(Context context) {
        super(context);
        AppMethodBeat.i(1889, false);
        a(context, null);
        AppMethodBeat.o(1889);
    }

    public AutoSkeletonConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(1892, false);
        a(context, attributeSet);
        AppMethodBeat.o(1892);
    }

    public AutoSkeletonConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(1895, false);
        a(context, attributeSet);
        AppMethodBeat.o(1895);
    }

    private void a(Context context, AttributeSet attributeSet) {
        boolean z = false;
        AppMethodBeat.i(1898, false);
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
        AppMethodBeat.o(1898);
    }

    public void setDelegate(b bVar) {
        AppMethodBeat.i(PreciseDisconnectCause.ECBM_NOT_SUPPORTED, false);
        a();
        this.a = bVar;
        AppMethodBeat.o(PreciseDisconnectCause.ECBM_NOT_SUPPORTED);
    }

    public void setAnimEnable(boolean z) {
        AppMethodBeat.i(1902, false);
        this.a.b(z);
        AppMethodBeat.o(1902);
    }

    public void a() {
        AppMethodBeat.i(1904, false);
        this.a.c();
        this.a.a(false);
        AppMethodBeat.o(1904);
    }

    public void b() {
        AppMethodBeat.i(1908, false);
        this.a.a(true);
        this.a.b();
        AppMethodBeat.o(1908);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        AppMethodBeat.i(1911, false);
        if (!this.a.a()) {
            super.dispatchDraw(canvas);
        }
        AppMethodBeat.o(1911);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        AppMethodBeat.i(1914, false);
        if (!this.a.a()) {
            super.onDraw(canvas);
        } else {
            this.a.a(canvas);
        }
        AppMethodBeat.o(1914);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        AppMethodBeat.i(1918, false);
        super.onAttachedToWindow();
        this.a.b();
        AppMethodBeat.o(1918);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AppMethodBeat.i(LegacyCameraDevice.MAX_DIMEN_FOR_ROUNDING, false);
        this.a.c();
        super.onDetachedFromWindow();
        AppMethodBeat.o(LegacyCameraDevice.MAX_DIMEN_FOR_ROUNDING);
    }
}
