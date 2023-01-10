package cn.missfresh.module.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    protected List<List<View>> a;
    protected List<Integer> b;
    protected List<Integer> c;
    private List<View> d;
    private int e;
    private int f;

    public FlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(23680, false);
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.f = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TagFlowLayout);
        this.e = obtainStyledAttributes.getInt(R.styleable.TagFlowLayout_gravity, -1);
        this.f = obtainStyledAttributes.getInt(R.styleable.TagFlowLayout_max_line, 0);
        obtainStyledAttributes.recycle();
        AppMethodBeat.o(23680);
    }

    public FlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlowLayout(Context context) {
        this(context, null);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        AppMethodBeat.i(23681, false);
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.a.clear();
        this.b.clear();
        this.c.clear();
        this.d.clear();
        int childCount = getChildCount();
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            if (i5 >= childCount) {
                i3 = size2;
                break;
            }
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() == 8) {
                i3 = size2;
            } else {
                measureChild(childAt, i, i2);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                i3 = size2;
                int measuredHeight = childAt.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                if (i6 + measuredWidth > (size - getPaddingLeft()) - getPaddingRight()) {
                    i7 = Math.max(i7, i6);
                    i8 += i9;
                    this.c.add(Integer.valueOf(i6));
                    this.b.add(Integer.valueOf(i9));
                    this.a.add(this.d);
                    if (this.f > 0 && this.a.size() == this.f) {
                        break;
                    }
                    this.d = new ArrayList();
                    i6 = 0;
                    i9 = 0;
                }
                this.d.add(childAt);
                i6 += measuredWidth;
                int max = Math.max(i9, measuredHeight);
                if (i5 == childCount - 1) {
                    this.c.add(Integer.valueOf(i6));
                    this.b.add(Integer.valueOf(max));
                    this.a.add(this.d);
                    i8 += max;
                    i9 = max;
                    i7 = Math.max(i7, i6);
                } else {
                    i9 = max;
                }
            }
            i5++;
            size2 = i3;
        }
        if (mode != 1073741824) {
            size = i7 + getPaddingLeft() + getPaddingRight();
        }
        if (mode2 == 1073741824) {
            i4 = i3;
        } else {
            i4 = i8 + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(size, i4);
        AppMethodBeat.o(23681);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        AppMethodBeat.i(23682, false);
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        getPaddingLeft();
        int paddingTop = getPaddingTop();
        int size = this.a.size();
        int i5 = paddingTop;
        for (int i6 = 0; i6 < size; i6++) {
            this.d = this.a.get(i6);
            int a = a(i6, width);
            for (int i7 = 0; i7 < this.d.size(); i7++) {
                View view = this.d.get(i7);
                if (view.getVisibility() != 8) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                    int i8 = marginLayoutParams.leftMargin + a;
                    int i9 = marginLayoutParams.topMargin + i5;
                    view.layout(i8, i9, view.getMeasuredWidth() + i8, view.getMeasuredHeight() + i9);
                    a += view.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                }
            }
            i5 += this.b.get(i6).intValue();
        }
        AppMethodBeat.o(23682);
    }

    private int a(int i, int i2) {
        int i3;
        int i4;
        int i5;
        AppMethodBeat.i(23683, false);
        int i6 = this.e;
        if (i6 == 0) {
            i4 = (i2 - this.c.get(i).intValue()) / 2;
            i5 = getPaddingLeft();
        } else if (i6 != 1) {
            i3 = getPaddingLeft();
            AppMethodBeat.o(23683);
            return i3;
        } else {
            i4 = i2 - this.c.get(i).intValue();
            i5 = getPaddingLeft();
        }
        i3 = i5 + i4;
        AppMethodBeat.o(23683);
        return i3;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        AppMethodBeat.i(23684, false);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
        AppMethodBeat.o(23684);
        return marginLayoutParams;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        AppMethodBeat.i(23685, false);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
        AppMethodBeat.o(23685);
        return marginLayoutParams;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        AppMethodBeat.i(23686, false);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(layoutParams);
        AppMethodBeat.o(23686);
        return marginLayoutParams;
    }

    public void setMaxLine(int i) {
        this.f = i;
    }
}
