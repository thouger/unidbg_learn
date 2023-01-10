package cn.missfresh.ui.titlebar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TabWidget;
import androidx.appcompat.widget.AppCompatTextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class BadgeView extends AppCompatTextView {
    private static final int a = Color.parseColor("#ff4891");
    private static Animation b;
    private static Animation c;
    private Context d;
    private View e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private ShapeDrawable k;
    private int l;

    static {
        AppMethodBeat.i(2622, false);
        AppMethodBeat.o(2622);
    }

    public BadgeView(Context context) {
        this(context, (AttributeSet) null, 16842884);
        AppMethodBeat.i(2565, false);
        AppMethodBeat.o(2565);
    }

    public BadgeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public BadgeView(Context context, View view) {
        this(context, null, 16842884, view, 0);
    }

    public BadgeView(Context context, TabWidget tabWidget, int i) {
        this(context, null, 16842884, tabWidget, i);
    }

    public BadgeView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, null, 0);
    }

    public BadgeView(Context context, AttributeSet attributeSet, int i, View view, int i2) {
        super(context, attributeSet, i);
        AppMethodBeat.i(2570, false);
        a(context, view, i2);
        AppMethodBeat.o(2570);
    }

    private void a(Context context, View view, int i) {
        AppMethodBeat.i(2573, false);
        this.d = context;
        this.e = view;
        this.l = i;
        this.f = 2;
        this.g = a(5);
        this.h = this.g;
        this.i = a;
        setTypeface(Typeface.DEFAULT_BOLD);
        int a2 = a(4);
        setPadding(a2, 0, a2, 0);
        setTextColor(-1);
        b = new AlphaAnimation(0.0f, 1.0f);
        b.setInterpolator(new DecelerateInterpolator());
        b.setDuration(200);
        c = new AlphaAnimation(1.0f, 0.0f);
        c.setInterpolator(new AccelerateInterpolator());
        c.setDuration(200);
        this.j = false;
        View view2 = this.e;
        if (view2 != null) {
            a(view2);
        } else {
            a();
        }
        AppMethodBeat.o(2573);
    }

    private void a(View view) {
        AppMethodBeat.i(2575, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewParent parent = view.getParent();
        FrameLayout frameLayout = new FrameLayout(this.d);
        if (view instanceof TabWidget) {
            View childTabViewAt = ((TabWidget) view).getChildTabViewAt(this.l);
            this.e = childTabViewAt;
            ((ViewGroup) childTabViewAt).addView(frameLayout, new ViewGroup.LayoutParams(-1, -1));
            setVisibility(8);
            frameLayout.addView(this);
        } else {
            ViewGroup viewGroup = (ViewGroup) parent;
            int indexOfChild = viewGroup.indexOfChild(view);
            viewGroup.removeView(view);
            viewGroup.addView(frameLayout, indexOfChild, layoutParams);
            frameLayout.addView(view);
            setVisibility(8);
            frameLayout.addView(this);
            viewGroup.invalidate();
        }
        AppMethodBeat.o(2575);
    }

    public void a() {
        AppMethodBeat.i(2576, false);
        a(false, null);
        AppMethodBeat.o(2576);
    }

    private void a(boolean z, Animation animation) {
        AppMethodBeat.i(2589, false);
        if (getBackground() == null) {
            if (this.k == null) {
                this.k = getDefaultBackground();
            }
            setBackgroundDrawable(this.k);
        }
        if (this.e != null) {
            b();
        }
        if (z) {
            startAnimation(animation);
        }
        setVisibility(0);
        this.j = true;
        AppMethodBeat.o(2589);
    }

    private ShapeDrawable getDefaultBackground() {
        AppMethodBeat.i(2598, false);
        float a2 = (float) a(8);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{a2, a2, a2, a2, a2, a2, a2, a2}, null, null));
        shapeDrawable.getPaint().setColor(this.i);
        AppMethodBeat.o(2598);
        return shapeDrawable;
    }

    private void b() {
        AppMethodBeat.i(2601, false);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        int i = this.f;
        if (i == 1) {
            layoutParams.gravity = 51;
            layoutParams.setMargins(this.g, this.h, 0, 0);
        } else if (i == 2) {
            layoutParams.gravity = 53;
            layoutParams.setMargins(0, this.h, this.g, 0);
        } else if (i == 3) {
            layoutParams.gravity = 83;
            layoutParams.setMargins(this.g, 0, 0, this.h);
        } else if (i == 4) {
            layoutParams.gravity = 85;
            layoutParams.setMargins(0, 0, this.g, this.h);
        } else if (i == 5) {
            layoutParams.gravity = 17;
            layoutParams.setMargins(0, 0, 0, 0);
        }
        setLayoutParams(layoutParams);
        AppMethodBeat.o(2601);
    }

    public View getTarget() {
        return this.e;
    }

    @Override // android.view.View
    public boolean isShown() {
        return this.j;
    }

    public int getBadgePosition() {
        return this.f;
    }

    public void setBadgePosition(int i) {
        this.f = i;
    }

    public int getHorizontalBadgeMargin() {
        return this.g;
    }

    public int getVerticalBadgeMargin() {
        return this.h;
    }

    public void setBadgeMargin(int i) {
        this.g = i;
        this.h = i;
    }

    public int getBadgeBackgroundColor() {
        return this.i;
    }

    public void setBadgeBackgroundColor(int i) {
        AppMethodBeat.i(2617, false);
        this.i = i;
        this.k = getDefaultBackground();
        setBackgroundDrawable(this.k);
        AppMethodBeat.o(2617);
    }

    private int a(int i) {
        AppMethodBeat.i(2620, false);
        int applyDimension = (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
        AppMethodBeat.o(2620);
        return applyDimension;
    }
}
