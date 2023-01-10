package com.sobot.chat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.sobot.chat.R;
import com.sobot.chat.utils.m;

public class SobotMHLinearLayout extends LinearLayout {
    private float a;
    private float b;
    private float c;
    private float d;

    public SobotMHLinearLayout(Context context) {
        this(context, null);
    }

    public SobotMHLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SobotMHLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 0.8f;
        this.c = 1.0f;
        a(context, attributeSet);
        this.b = (float) a(context, 0.0f);
        this.d = (float) a(context, 0.0f);
        a();
    }

    public int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SobotMHLinearLayout);
        this.a = obtainStyledAttributes.getFloat(R.styleable.SobotMHLinearLayout_sobot_mhv_HeightRatio, 0.8f);
        this.b = obtainStyledAttributes.getDimension(R.styleable.SobotMHLinearLayout_sobot_mhv_HeightDimen, 0.0f);
        this.c = obtainStyledAttributes.getFloat(R.styleable.SobotMHLinearLayout_sobot_mhH_HeightRatio, 1.0f);
        this.d = obtainStyledAttributes.getDimension(R.styleable.SobotMHLinearLayout_sobot_mhH_HeightDimen, 0.0f);
        obtainStyledAttributes.recycle();
    }

    private void a() {
        float f = this.b;
        if (f <= 0.0f) {
            this.b = this.a * ((float) b(getContext()));
        } else {
            this.b = Math.min(f, this.a * ((float) b(getContext())));
        }
        float f2 = this.d;
        if (f2 <= 0.0f) {
            this.d = this.c * ((float) b(getContext()));
        } else {
            this.d = Math.min(f2, this.c * ((float) b(getContext())));
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        View.MeasureSpec.getSize(i);
        m.c(size + "\t" + this.d);
        if (a(getContext())) {
            i3 = a(mode, size);
        } else {
            i3 = b(mode, size);
        }
        m.c(i3 + "\t" + this.d);
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE));
    }

    private int a(int i, int i2) {
        if (i == 1073741824) {
            float f = this.b;
            if (((float) i2) > f) {
                i2 = (int) f;
            }
        }
        if (i == 0) {
            float f2 = this.b;
            if (((float) i2) > f2) {
                i2 = (int) f2;
            }
        }
        if (i != Integer.MIN_VALUE) {
            return i2;
        }
        float f3 = this.b;
        return ((float) i2) <= f3 ? i2 : (int) f3;
    }

    private int b(int i, int i2) {
        if (i == 1073741824) {
            float f = this.d;
            if (((float) i2) > f) {
                i2 = (int) f;
            }
        }
        if (i == 0) {
            float f2 = this.d;
            if (((float) i2) > f2) {
                i2 = (int) f2;
            }
        }
        if (i != Integer.MIN_VALUE) {
            return i2;
        }
        float f3 = this.d;
        return ((float) i2) <= f3 ? i2 : (int) f3;
    }

    private int b(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
    }

    public static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }
}
