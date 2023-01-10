package cn.missfresh.ui.skeleton.manual.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.ViewCompat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;

/* compiled from: ShapeViewProxy */
public class a {
    private static boolean a(float f) {
        return f > 0.0f;
    }

    public static void a(View view, Context context, AttributeSet attributeSet, int i) {
        GradientDrawable gradientDrawable;
        AppMethodBeat.i(2258, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SkeletonView, i, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.SkeletonView_shape, 0);
        int color = obtainStyledAttributes.getColor(R.styleable.SkeletonView_gradientStartColor, 0);
        int color2 = obtainStyledAttributes.getColor(R.styleable.SkeletonView_gradientEndColor, 0);
        int color3 = obtainStyledAttributes.getColor(R.styleable.SkeletonView_gradientCenterColor, 0);
        int i3 = obtainStyledAttributes.getInt(R.styleable.SkeletonView_gradientAngle, 0);
        if (!a(obtainStyledAttributes, R.styleable.SkeletonView_gradientStartColor) || !a(obtainStyledAttributes, R.styleable.SkeletonView_gradientEndColor)) {
            gradientDrawable = new GradientDrawable();
        } else {
            gradientDrawable = new GradientDrawable(a(i3), a(obtainStyledAttributes, R.styleable.SkeletonView_gradientCenterColor) ? new int[]{color, color3, color2} : new int[]{color, color2});
        }
        float dimension = obtainStyledAttributes.getDimension(R.styleable.SkeletonView_cornersRadius, 0.0f);
        float dimension2 = obtainStyledAttributes.getDimension(R.styleable.SkeletonView_cornersTopLeftRadius, 0.0f);
        float dimension3 = obtainStyledAttributes.getDimension(R.styleable.SkeletonView_cornersTopRightRadius, 0.0f);
        float dimension4 = obtainStyledAttributes.getDimension(R.styleable.SkeletonView_cornersBottomLeftRadius, 0.0f);
        float dimension5 = obtainStyledAttributes.getDimension(R.styleable.SkeletonView_cornersBottomRightRadius, 0.0f);
        float dimension6 = obtainStyledAttributes.getDimension(R.styleable.SkeletonView_strokeShapeWidth, 0.0f);
        int color4 = obtainStyledAttributes.getColor(R.styleable.SkeletonView_strokeShapeColor, 0);
        float dimension7 = obtainStyledAttributes.getDimension(R.styleable.SkeletonView_strokeDashGap, 0.0f);
        float dimension8 = obtainStyledAttributes.getDimension(R.styleable.SkeletonView_strokeDashWidth, 0.0f);
        if (a(dimension6) && a(obtainStyledAttributes, R.styleable.SkeletonView_strokeShapeColor)) {
            if (a(dimension7) || a(dimension8)) {
                gradientDrawable.setStroke(Math.round(dimension6), color4, dimension8, dimension7);
            } else {
                gradientDrawable.setStroke(Math.round(dimension6), color4);
            }
        }
        int i4 = obtainStyledAttributes.getInt(R.styleable.SkeletonView_gradientType, -1);
        float f = obtainStyledAttributes.getFloat(R.styleable.SkeletonView_gradientCenterX, 0.0f);
        float f2 = obtainStyledAttributes.getFloat(R.styleable.SkeletonView_gradientCenterY, 0.0f);
        float dimension9 = obtainStyledAttributes.getDimension(R.styleable.SkeletonView_gradientRadius, 0.0f);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.SkeletonView_gradientUseLevel, false);
        int color5 = obtainStyledAttributes.getColor(R.styleable.SkeletonView_solidColor, 0);
        if (a(obtainStyledAttributes, R.styleable.SkeletonView_solidColor)) {
            gradientDrawable.setColor(color5);
        }
        obtainStyledAttributes.recycle();
        if (a((float) i4)) {
            b(gradientDrawable, i4);
            if (a(f) || a(f2)) {
                gradientDrawable.setGradientCenter(f, f2);
            }
            if (a(dimension9)) {
                gradientDrawable.setGradientRadius(dimension9);
            }
            gradientDrawable.setUseLevel(z);
        }
        a(gradientDrawable, i2);
        if (a(dimension)) {
            gradientDrawable.setCornerRadius(dimension);
        } else if (a(dimension2) || a(dimension3) || a(dimension4) || a(dimension5)) {
            gradientDrawable.setCornerRadii(new float[]{dimension2, dimension2, dimension3, dimension3, dimension4, dimension4, dimension5, dimension5});
        }
        ViewCompat.setBackground(view, gradientDrawable);
        AppMethodBeat.o(2258);
    }

    private static boolean a(TypedArray typedArray, int i) {
        AppMethodBeat.i(2268, false);
        boolean hasValue = typedArray.hasValue(i);
        AppMethodBeat.o(2268);
        return hasValue;
    }

    private static void a(GradientDrawable gradientDrawable, int i) {
        AppMethodBeat.i(2272, false);
        if (i == 0) {
            gradientDrawable.setShape(0);
        } else if (i == 1) {
            gradientDrawable.setShape(1);
        } else if (i == 2) {
            gradientDrawable.setShape(2);
        } else if (i == 3) {
            gradientDrawable.setShape(3);
        }
        AppMethodBeat.o(2272);
    }

    private static void b(GradientDrawable gradientDrawable, int i) {
        AppMethodBeat.i(2276, false);
        if (i == 0) {
            gradientDrawable.setGradientType(0);
        } else if (i == 1) {
            gradientDrawable.setGradientType(1);
        } else if (i == 2) {
            gradientDrawable.setGradientType(2);
        }
        AppMethodBeat.o(2276);
    }

    private static GradientDrawable.Orientation a(int i) {
        if (i == 0) {
            return GradientDrawable.Orientation.LEFT_RIGHT;
        }
        if (i == 45) {
            return GradientDrawable.Orientation.BL_TR;
        }
        if (i == 90) {
            return GradientDrawable.Orientation.BOTTOM_TOP;
        }
        if (i == 135) {
            return GradientDrawable.Orientation.BR_TL;
        }
        if (i == 180) {
            return GradientDrawable.Orientation.RIGHT_LEFT;
        }
        if (i == 225) {
            return GradientDrawable.Orientation.TR_BL;
        }
        if (i == 270) {
            return GradientDrawable.Orientation.TOP_BOTTOM;
        }
        if (i != 315) {
            return GradientDrawable.Orientation.TOP_BOTTOM;
        }
        return GradientDrawable.Orientation.TL_BR;
    }
}
