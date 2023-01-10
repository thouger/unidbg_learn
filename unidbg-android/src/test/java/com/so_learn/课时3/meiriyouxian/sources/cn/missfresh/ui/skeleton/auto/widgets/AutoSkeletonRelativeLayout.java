package cn.missfresh.ui.skeleton.auto.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.media.MediaRecorder;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;
import cn.missfresh.ui.skeleton.auto.b;

public class AutoSkeletonRelativeLayout extends RelativeLayout {
    private b a;

    public AutoSkeletonRelativeLayout(Context context) {
        super(context);
        AppMethodBeat.i(1994, false);
        a(context, null);
        AppMethodBeat.o(1994);
    }

    public AutoSkeletonRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(1996, false);
        a(context, attributeSet);
        AppMethodBeat.o(1996);
    }

    public AutoSkeletonRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(MediaRecorder.AudioSource.RADIO_TUNER, false);
        a(context, attributeSet);
        AppMethodBeat.o(MediaRecorder.AudioSource.RADIO_TUNER);
    }

    private void a(Context context, AttributeSet attributeSet) {
        boolean z = false;
        AppMethodBeat.i(2000, false);
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
        AppMethodBeat.o(2000);
    }

    public void setDelegate(b bVar) {
        AppMethodBeat.i(2002, false);
        a();
        this.a = bVar;
        AppMethodBeat.o(2002);
    }

    public void setAnimEnable(boolean z) {
        AppMethodBeat.i(2004, false);
        this.a.b(z);
        AppMethodBeat.o(2004);
    }

    public void a() {
        AppMethodBeat.i(2006, false);
        this.a.c();
        this.a.a(false);
        AppMethodBeat.o(2006);
    }

    public void b() {
        AppMethodBeat.i(2007, false);
        this.a.a(true);
        this.a.b();
        AppMethodBeat.o(2007);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        AppMethodBeat.i(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG, false);
        if (!this.a.a()) {
            super.dispatchDraw(canvas);
        }
        AppMethodBeat.o(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        AppMethodBeat.i(WindowManager.LayoutParams.TYPE_INPUT_METHOD, false);
        if (!this.a.a()) {
            super.onDraw(canvas);
        } else {
            this.a.a(canvas);
        }
        AppMethodBeat.o(WindowManager.LayoutParams.TYPE_INPUT_METHOD);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        AppMethodBeat.i(WindowManager.LayoutParams.TYPE_WALLPAPER, false);
        super.onAttachedToWindow();
        this.a.b();
        AppMethodBeat.o(WindowManager.LayoutParams.TYPE_WALLPAPER);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AppMethodBeat.i(WindowManager.LayoutParams.TYPE_SECURE_SYSTEM_OVERLAY, false);
        this.a.c();
        super.onDetachedFromWindow();
        AppMethodBeat.o(WindowManager.LayoutParams.TYPE_SECURE_SYSTEM_OVERLAY);
    }
}
