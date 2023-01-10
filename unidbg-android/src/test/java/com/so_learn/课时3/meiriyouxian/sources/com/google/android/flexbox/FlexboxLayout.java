package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.google.android.flexbox.c;
import java.util.ArrayList;
import java.util.List;

public class FlexboxLayout extends ViewGroup implements a {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private Drawable g;
    private Drawable h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int[] m;
    private SparseIntArray n;
    private c o;
    private List<b> p;
    private c.a q;

    @Override // com.google.android.flexbox.a
    public int a(View view) {
        return 0;
    }

    @Override // com.google.android.flexbox.a
    public void a(int i, View view) {
    }

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = -1;
        this.o = new c(this);
        this.p = new ArrayList();
        this.q = new c.a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlexboxLayout, i, 0);
        this.a = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_flexDirection, 0);
        this.b = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_flexWrap, 0);
        this.c = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_justifyContent, 0);
        this.d = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_alignItems, 0);
        this.e = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_alignContent, 0);
        this.f = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_maxLine, -1);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawable);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawableHorizontal);
        if (drawable2 != null) {
            setDividerDrawableHorizontal(drawable2);
        }
        Drawable drawable3 = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawableVertical);
        if (drawable3 != null) {
            setDividerDrawableVertical(drawable3);
        }
        int i2 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDivider, 0);
        if (i2 != 0) {
            this.j = i2;
            this.i = i2;
        }
        int i3 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDividerVertical, 0);
        if (i3 != 0) {
            this.j = i3;
        }
        int i4 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDividerHorizontal, 0);
        if (i4 != 0) {
            this.i = i4;
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        if (this.n == null) {
            this.n = new SparseIntArray(getChildCount());
        }
        if (this.o.b(this.n)) {
            this.m = this.o.a(this.n);
        }
        int i3 = this.a;
        if (i3 == 0 || i3 == 1) {
            a(i, i2);
        } else if (i3 == 2 || i3 == 3) {
            b(i, i2);
        } else {
            throw new IllegalStateException("Invalid value for the flex direction is set: " + this.a);
        }
    }

    @Override // com.google.android.flexbox.a
    public int getFlexItemCount() {
        return getChildCount();
    }

    @Override // com.google.android.flexbox.a
    public View a(int i) {
        return getChildAt(i);
    }

    public View c(int i) {
        if (i < 0) {
            return null;
        }
        int[] iArr = this.m;
        if (i >= iArr.length) {
            return null;
        }
        return getChildAt(iArr[i]);
    }

    @Override // com.google.android.flexbox.a
    public View b(int i) {
        return c(i);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.n == null) {
            this.n = new SparseIntArray(getChildCount());
        }
        this.m = this.o.a(view, i, layoutParams, this.n);
        super.addView(view, i, layoutParams);
    }

    private void a(int i, int i2) {
        this.p.clear();
        this.q.a();
        this.o.a(this.q, i, i2);
        this.p = this.q.a;
        this.o.a(i, i2);
        if (this.d == 3) {
            for (b bVar : this.p) {
                int i3 = Integer.MIN_VALUE;
                for (int i4 = 0; i4 < bVar.h; i4++) {
                    View c = c(bVar.o + i4);
                    if (!(c == null || c.getVisibility() == 8)) {
                        LayoutParams layoutParams = (LayoutParams) c.getLayoutParams();
                        if (this.b != 2) {
                            i3 = Math.max(i3, c.getMeasuredHeight() + Math.max(bVar.l - c.getBaseline(), layoutParams.topMargin) + layoutParams.bottomMargin);
                        } else {
                            i3 = Math.max(i3, c.getMeasuredHeight() + layoutParams.topMargin + Math.max((bVar.l - c.getMeasuredHeight()) + c.getBaseline(), layoutParams.bottomMargin));
                        }
                    }
                }
                bVar.g = i3;
            }
        }
        this.o.b(i, i2, getPaddingTop() + getPaddingBottom());
        this.o.a();
        a(this.a, i, i2, this.q.b);
    }

    private void b(int i, int i2) {
        this.p.clear();
        this.q.a();
        this.o.b(this.q, i, i2);
        this.p = this.q.a;
        this.o.a(i, i2);
        this.o.b(i, i2, getPaddingLeft() + getPaddingRight());
        this.o.a();
        a(this.a, i, i2, this.q.b);
    }

    private void a(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (i == 0 || i == 1) {
            i5 = getSumOfCrossSize() + getPaddingTop() + getPaddingBottom();
            i6 = getLargestMainSize();
        } else if (i == 2 || i == 3) {
            i5 = getLargestMainSize();
            i6 = getSumOfCrossSize() + getPaddingLeft() + getPaddingRight();
        } else {
            throw new IllegalArgumentException("Invalid flex direction: " + i);
        }
        if (mode == Integer.MIN_VALUE) {
            if (size < i6) {
                i4 = View.combineMeasuredStates(i4, 16777216);
            } else {
                size = i6;
            }
            i7 = View.resolveSizeAndState(size, i2, i4);
        } else if (mode == 0) {
            i7 = View.resolveSizeAndState(i6, i2, i4);
        } else if (mode == 1073741824) {
            if (size < i6) {
                i4 = View.combineMeasuredStates(i4, 16777216);
            }
            i7 = View.resolveSizeAndState(size, i2, i4);
        } else {
            throw new IllegalStateException("Unknown width mode is set: " + mode);
        }
        if (mode2 == Integer.MIN_VALUE) {
            if (size2 < i5) {
                i4 = View.combineMeasuredStates(i4, 256);
                i5 = size2;
            }
            i8 = View.resolveSizeAndState(i5, i3, i4);
        } else if (mode2 == 0) {
            i8 = View.resolveSizeAndState(i5, i3, i4);
        } else if (mode2 == 1073741824) {
            if (size2 < i5) {
                i4 = View.combineMeasuredStates(i4, 256);
            }
            i8 = View.resolveSizeAndState(size2, i3, i4);
        } else {
            throw new IllegalStateException("Unknown height mode is set: " + mode2);
        }
        setMeasuredDimension(i7, i8);
    }

    @Override // com.google.android.flexbox.a
    public int getLargestMainSize() {
        int i = Integer.MIN_VALUE;
        for (b bVar : this.p) {
            i = Math.max(i, bVar.e);
        }
        return i;
    }

    @Override // com.google.android.flexbox.a
    public int getSumOfCrossSize() {
        int i;
        int i2;
        int size = this.p.size();
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            b bVar = this.p.get(i4);
            if (d(i4)) {
                if (a()) {
                    i2 = this.k;
                } else {
                    i2 = this.l;
                }
                i3 += i2;
            }
            if (f(i4)) {
                if (a()) {
                    i = this.k;
                } else {
                    i = this.l;
                }
                i3 += i;
            }
            i3 += bVar.g;
        }
        return i3;
    }

    @Override // com.google.android.flexbox.a
    public boolean a() {
        int i = this.a;
        return i == 0 || i == 1;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int i5 = this.a;
        boolean z2 = false;
        if (i5 == 0) {
            a(layoutDirection == 1, i, i2, i3, i4);
        } else if (i5 == 1) {
            a(layoutDirection != 1, i, i2, i3, i4);
        } else if (i5 == 2) {
            if (layoutDirection == 1) {
                z2 = true;
            }
            a(this.b == 2 ? !z2 : z2, false, i, i2, i3, i4);
        } else if (i5 == 3) {
            if (layoutDirection == 1) {
                z2 = true;
            }
            a(this.b == 2 ? !z2 : z2, true, i, i2, i3, i4);
        } else {
            throw new IllegalStateException("Invalid flex direction is set: " + this.a);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x013c  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01fb  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0208  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(boolean r29, int r30, int r31, int r32, int r33) {
        /*
        // Method dump skipped, instructions count: 572
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.a(boolean, int, int, int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01f7  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0204  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(boolean r30, boolean r31, int r32, int r33, int r34, int r35) {
        /*
        // Method dump skipped, instructions count: 558
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.a(boolean, boolean, int, int, int, int):void");
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.h != null || this.g != null) {
            if (this.i != 0 || this.j != 0) {
                int layoutDirection = ViewCompat.getLayoutDirection(this);
                int i = this.a;
                boolean z = false;
                boolean z2 = true;
                if (i == 0) {
                    boolean z3 = layoutDirection == 1;
                    if (this.b == 2) {
                        z = true;
                    }
                    a(canvas, z3, z);
                } else if (i == 1) {
                    boolean z4 = layoutDirection != 1;
                    if (this.b == 2) {
                        z = true;
                    }
                    a(canvas, z4, z);
                } else if (i == 2) {
                    if (layoutDirection != 1) {
                        z2 = false;
                    }
                    if (this.b == 2) {
                        z2 = !z2;
                    }
                    b(canvas, z2, false);
                } else if (i == 3) {
                    if (layoutDirection == 1) {
                        z = true;
                    }
                    if (this.b == 2) {
                        z = !z;
                    }
                    b(canvas, z, true);
                }
            }
        }
    }

    private void a(Canvas canvas, boolean z, boolean z2) {
        int i;
        int i2;
        int i3;
        int i4;
        int paddingLeft = getPaddingLeft();
        int max = Math.max(0, (getWidth() - getPaddingRight()) - paddingLeft);
        int size = this.p.size();
        for (int i5 = 0; i5 < size; i5++) {
            b bVar = this.p.get(i5);
            for (int i6 = 0; i6 < bVar.h; i6++) {
                int i7 = bVar.o + i6;
                View c = c(i7);
                if (!(c == null || c.getVisibility() == 8)) {
                    LayoutParams layoutParams = (LayoutParams) c.getLayoutParams();
                    if (c(i7, i6)) {
                        if (z) {
                            i4 = c.getRight() + layoutParams.rightMargin;
                        } else {
                            i4 = (c.getLeft() - layoutParams.leftMargin) - this.l;
                        }
                        a(canvas, i4, bVar.b, bVar.g);
                    }
                    if (i6 == bVar.h - 1 && (this.j & 4) > 0) {
                        if (z) {
                            i3 = (c.getLeft() - layoutParams.leftMargin) - this.l;
                        } else {
                            i3 = c.getRight() + layoutParams.rightMargin;
                        }
                        a(canvas, i3, bVar.b, bVar.g);
                    }
                }
            }
            if (d(i5)) {
                if (z2) {
                    i2 = bVar.d;
                } else {
                    i2 = bVar.b - this.k;
                }
                b(canvas, paddingLeft, i2, max);
            }
            if (f(i5) && (this.i & 4) > 0) {
                if (z2) {
                    i = bVar.b - this.k;
                } else {
                    i = bVar.d;
                }
                b(canvas, paddingLeft, i, max);
            }
        }
    }

    private void b(Canvas canvas, boolean z, boolean z2) {
        int i;
        int i2;
        int i3;
        int i4;
        int paddingTop = getPaddingTop();
        int max = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.p.size();
        for (int i5 = 0; i5 < size; i5++) {
            b bVar = this.p.get(i5);
            for (int i6 = 0; i6 < bVar.h; i6++) {
                int i7 = bVar.o + i6;
                View c = c(i7);
                if (!(c == null || c.getVisibility() == 8)) {
                    LayoutParams layoutParams = (LayoutParams) c.getLayoutParams();
                    if (c(i7, i6)) {
                        if (z2) {
                            i4 = c.getBottom() + layoutParams.bottomMargin;
                        } else {
                            i4 = (c.getTop() - layoutParams.topMargin) - this.k;
                        }
                        b(canvas, bVar.a, i4, bVar.g);
                    }
                    if (i6 == bVar.h - 1 && (this.i & 4) > 0) {
                        if (z2) {
                            i3 = (c.getTop() - layoutParams.topMargin) - this.k;
                        } else {
                            i3 = c.getBottom() + layoutParams.bottomMargin;
                        }
                        b(canvas, bVar.a, i3, bVar.g);
                    }
                }
            }
            if (d(i5)) {
                if (z) {
                    i2 = bVar.c;
                } else {
                    i2 = bVar.a - this.l;
                }
                a(canvas, i2, paddingTop, max);
            }
            if (f(i5) && (this.j & 4) > 0) {
                if (z) {
                    i = bVar.a - this.l;
                } else {
                    i = bVar.c;
                }
                a(canvas, i, paddingTop, max);
            }
        }
    }

    private void a(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.h;
        if (drawable != null) {
            drawable.setBounds(i, i2, this.l + i, i3 + i2);
            this.h.draw(canvas);
        }
    }

    private void b(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.g;
        if (drawable != null) {
            drawable.setBounds(i, i2, i3 + i, this.k + i2);
            this.g.draw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* renamed from: a */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    @Override // com.google.android.flexbox.a
    public int getFlexDirection() {
        return this.a;
    }

    public void setFlexDirection(int i) {
        if (this.a != i) {
            this.a = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public int getFlexWrap() {
        return this.b;
    }

    public void setFlexWrap(int i) {
        if (this.b != i) {
            this.b = i;
            requestLayout();
        }
    }

    public int getJustifyContent() {
        return this.c;
    }

    public void setJustifyContent(int i) {
        if (this.c != i) {
            this.c = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public int getAlignItems() {
        return this.d;
    }

    public void setAlignItems(int i) {
        if (this.d != i) {
            this.d = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public int getAlignContent() {
        return this.e;
    }

    public void setAlignContent(int i) {
        if (this.e != i) {
            this.e = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public int getMaxLine() {
        return this.f;
    }

    public void setMaxLine(int i) {
        if (this.f != i) {
            this.f = i;
            requestLayout();
        }
    }

    public List<b> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.p.size());
        for (b bVar : this.p) {
            if (bVar.c() != 0) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.flexbox.a
    public int a(View view, int i, int i2) {
        int i3;
        int i4 = 0;
        if (a()) {
            if (c(i, i2)) {
                i4 = 0 + this.l;
            }
            if ((this.j & 4) <= 0) {
                return i4;
            }
            i3 = this.l;
        } else {
            if (c(i, i2)) {
                i4 = 0 + this.k;
            }
            if ((this.i & 4) <= 0) {
                return i4;
            }
            i3 = this.k;
        }
        return i4 + i3;
    }

    @Override // com.google.android.flexbox.a
    public void a(b bVar) {
        if (a()) {
            if ((this.j & 4) > 0) {
                bVar.e += this.l;
                bVar.f += this.l;
            }
        } else if ((this.i & 4) > 0) {
            bVar.e += this.k;
            bVar.f += this.k;
        }
    }

    @Override // com.google.android.flexbox.a
    public int a(int i, int i2, int i3) {
        return getChildMeasureSpec(i, i2, i3);
    }

    @Override // com.google.android.flexbox.a
    public int b(int i, int i2, int i3) {
        return getChildMeasureSpec(i, i2, i3);
    }

    @Override // com.google.android.flexbox.a
    public void a(View view, int i, int i2, b bVar) {
        if (!c(i, i2)) {
            return;
        }
        if (a()) {
            bVar.e += this.l;
            bVar.f += this.l;
            return;
        }
        bVar.e += this.k;
        bVar.f += this.k;
    }

    @Override // com.google.android.flexbox.a
    public void setFlexLines(List<b> list) {
        this.p = list;
    }

    @Override // com.google.android.flexbox.a
    public List<b> getFlexLinesInternal() {
        return this.p;
    }

    public Drawable getDividerDrawableHorizontal() {
        return this.g;
    }

    public Drawable getDividerDrawableVertical() {
        return this.h;
    }

    public void setDividerDrawable(Drawable drawable) {
        setDividerDrawableHorizontal(drawable);
        setDividerDrawableVertical(drawable);
    }

    public void setDividerDrawableHorizontal(Drawable drawable) {
        if (drawable != this.g) {
            this.g = drawable;
            if (drawable != null) {
                this.k = drawable.getIntrinsicHeight();
            } else {
                this.k = 0;
            }
            b();
            requestLayout();
        }
    }

    public void setDividerDrawableVertical(Drawable drawable) {
        if (drawable != this.h) {
            this.h = drawable;
            if (drawable != null) {
                this.l = drawable.getIntrinsicWidth();
            } else {
                this.l = 0;
            }
            b();
            requestLayout();
        }
    }

    public int getShowDividerVertical() {
        return this.j;
    }

    public int getShowDividerHorizontal() {
        return this.i;
    }

    public void setShowDivider(int i) {
        setShowDividerVertical(i);
        setShowDividerHorizontal(i);
    }

    public void setShowDividerVertical(int i) {
        if (i != this.j) {
            this.j = i;
            requestLayout();
        }
    }

    public void setShowDividerHorizontal(int i) {
        if (i != this.i) {
            this.i = i;
            requestLayout();
        }
    }

    private void b() {
        if (this.g == null && this.h == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
    }

    private boolean c(int i, int i2) {
        return d(i, i2) ? a() ? (this.j & 1) != 0 : (this.i & 1) != 0 : a() ? (this.j & 2) != 0 : (this.i & 2) != 0;
    }

    private boolean d(int i, int i2) {
        for (int i3 = 1; i3 <= i2; i3++) {
            View c = c(i - i3);
            if (!(c == null || c.getVisibility() == 8)) {
                return false;
            }
        }
        return true;
    }

    private boolean d(int i) {
        if (i < 0 || i >= this.p.size()) {
            return false;
        }
        if (e(i)) {
            if (a()) {
                if ((this.i & 1) != 0) {
                    return true;
                }
                return false;
            } else if ((this.j & 1) != 0) {
                return true;
            } else {
                return false;
            }
        } else if (a()) {
            if ((this.i & 2) != 0) {
                return true;
            }
            return false;
        } else if ((this.j & 2) != 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean e(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (this.p.get(i2).c() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean f(int i) {
        if (i < 0 || i >= this.p.size()) {
            return false;
        }
        for (int i2 = i + 1; i2 < this.p.size(); i2++) {
            if (this.p.get(i2).c() > 0) {
                return false;
            }
        }
        return a() ? (this.i & 4) != 0 : (this.j & 4) != 0;
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new AnonymousClass1();
        private int a = 1;
        private float b = 0.0f;
        private float c = 1.0f;
        private int d = -1;
        private float e = -1.0f;
        private int f = -1;
        private int g = -1;
        private int h = 16777215;
        private int i = 16777215;
        private boolean j;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlexboxLayout_Layout);
            this.a = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_Layout_layout_order, 1);
            this.b = obtainStyledAttributes.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexGrow, 0.0f);
            this.c = obtainStyledAttributes.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexShrink, 1.0f);
            this.d = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_Layout_layout_alignSelf, -1);
            this.e = obtainStyledAttributes.getFraction(R.styleable.FlexboxLayout_Layout_layout_flexBasisPercent, 1, 1, -1.0f);
            this.f = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minWidth, -1);
            this.g = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minHeight, -1);
            this.h = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxWidth, 16777215);
            this.i = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxHeight, 16777215);
            this.j = obtainStyledAttributes.getBoolean(R.styleable.FlexboxLayout_Layout_layout_wrapBefore, false);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.a = layoutParams.a;
            this.b = layoutParams.b;
            this.c = layoutParams.c;
            this.d = layoutParams.d;
            this.e = layoutParams.e;
            this.f = layoutParams.f;
            this.g = layoutParams.g;
            this.h = layoutParams.h;
            this.i = layoutParams.i;
            this.j = layoutParams.j;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        @Override // com.google.android.flexbox.FlexItem
        public int a() {
            return this.width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int b() {
            return this.height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int c() {
            return this.a;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float d() {
            return this.b;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float e() {
            return this.c;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int f() {
            return this.d;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int g() {
            return this.f;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void a(int i) {
            this.f = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int h() {
            return this.g;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void b(int i) {
            this.g = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int i() {
            return this.h;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int j() {
            return this.i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public boolean k() {
            return this.j;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float l() {
            return this.e;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int m() {
            return this.leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int n() {
            return this.topMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int o() {
            return this.rightMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int p() {
            return this.bottomMargin;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeFloat(this.b);
            parcel.writeFloat(this.c);
            parcel.writeInt(this.d);
            parcel.writeFloat(this.e);
            parcel.writeInt(this.f);
            parcel.writeInt(this.g);
            parcel.writeInt(this.h);
            parcel.writeInt(this.i);
            parcel.writeByte(this.j ? (byte) 1 : 0);
            parcel.writeInt(this.bottomMargin);
            parcel.writeInt(this.leftMargin);
            parcel.writeInt(this.rightMargin);
            parcel.writeInt(this.topMargin);
            parcel.writeInt(this.height);
            parcel.writeInt(this.width);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected LayoutParams(Parcel parcel) {
            super(0, 0);
            boolean z = false;
            this.a = parcel.readInt();
            this.b = parcel.readFloat();
            this.c = parcel.readFloat();
            this.d = parcel.readInt();
            this.e = parcel.readFloat();
            this.f = parcel.readInt();
            this.g = parcel.readInt();
            this.h = parcel.readInt();
            this.i = parcel.readInt();
            this.j = parcel.readByte() != 0 ? true : z;
            this.bottomMargin = parcel.readInt();
            this.leftMargin = parcel.readInt();
            this.rightMargin = parcel.readInt();
            this.topMargin = parcel.readInt();
            this.height = parcel.readInt();
            this.width = parcel.readInt();
        }

        /* renamed from: com.google.android.flexbox.FlexboxLayout$LayoutParams$1  reason: invalid class name */
        static class AnonymousClass1 implements Parcelable.Creator<LayoutParams> {
            AnonymousClass1() {
            }

            /* renamed from: a */
            public LayoutParams createFromParcel(Parcel parcel) {
                return new LayoutParams(parcel);
            }

            /* renamed from: a */
            public LayoutParams[] newArray(int i) {
                return new LayoutParams[i];
            }
        }
    }
}
