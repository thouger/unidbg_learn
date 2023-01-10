package com.sobot.chat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.sobot.chat.R;
import com.sobot.chat.utils.q;
import java.util.Locale;

public class PagerSlidingTab extends HorizontalScrollView {
    private static final int[] b = {16842901, 16842904};
    private int A;
    private int B;
    private Typeface C;
    private int D;
    private int E;
    private int F;
    private Locale G;
    public ViewPager.OnPageChangeListener a;
    private LinearLayout.LayoutParams c;
    private LinearLayout.LayoutParams d;
    private final b e;
    private LinearLayout f;
    private ViewPager g;
    private int h;
    private int i;
    private float j;
    private Paint k;
    private Paint l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private boolean r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    public interface a {
        int a(int i);
    }

    public PagerSlidingTab(Context context) {
        this(context, null);
    }

    public PagerSlidingTab(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PagerSlidingTab(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new b(this, null);
        this.i = 0;
        this.j = 0.0f;
        this.m = false;
        this.n = -16142672;
        this.o = 0;
        this.p = 0;
        this.q = false;
        this.r = true;
        this.s = 52;
        this.t = 3;
        this.u = 2;
        this.v = 12;
        this.w = 14;
        this.x = 1;
        this.y = 4;
        this.z = 14;
        this.A = -5458492;
        this.B = -11445636;
        this.C = null;
        this.D = 1;
        this.E = 0;
        this.F = 0;
        setFillViewport(true);
        setWillNotDraw(false);
        this.f = new LinearLayout(context);
        this.f.setOrientation(0);
        this.f.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.s = (int) TypedValue.applyDimension(1, (float) this.s, displayMetrics);
        this.t = (int) TypedValue.applyDimension(1, (float) this.t, displayMetrics);
        this.u = (int) TypedValue.applyDimension(1, (float) this.u, displayMetrics);
        this.v = (int) TypedValue.applyDimension(1, (float) this.v, displayMetrics);
        this.w = (int) TypedValue.applyDimension(1, (float) this.w, displayMetrics);
        this.x = (int) TypedValue.applyDimension(1, (float) this.x, displayMetrics);
        this.z = (int) TypedValue.applyDimension(1, (float) this.z, displayMetrics);
        this.y = (int) TypedValue.applyDimension(1, (float) this.y, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b);
        this.z = obtainStyledAttributes.getDimensionPixelSize(0, this.z);
        this.A = obtainStyledAttributes.getColor(1, this.A);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R.styleable.PagerSlidingTab);
        this.B = obtainStyledAttributes2.getColor(R.styleable.PagerSlidingTab_curTabTextColor, this.B);
        this.A = obtainStyledAttributes2.getColor(R.styleable.PagerSlidingTab_tabTextColor, this.A);
        this.n = obtainStyledAttributes2.getColor(R.styleable.PagerSlidingTab_indicatorColor, this.n);
        this.o = obtainStyledAttributes2.getColor(R.styleable.PagerSlidingTab_underlineColor, this.o);
        this.p = obtainStyledAttributes2.getColor(R.styleable.PagerSlidingTab_sobotdividerColor, this.p);
        this.t = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTab_indicatorHeight, this.t);
        this.u = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTab_underlineHeight, this.u);
        this.v = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTab_pst_dividerPadding, this.v);
        this.w = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTab_tabPaddingLeftRight, this.w);
        this.F = obtainStyledAttributes2.getResourceId(R.styleable.PagerSlidingTab_tabBackground, this.F);
        this.q = obtainStyledAttributes2.getBoolean(R.styleable.PagerSlidingTab_shouldExpand, this.q);
        this.s = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.PagerSlidingTab_scrollOffset, this.s);
        this.r = obtainStyledAttributes2.getBoolean(R.styleable.PagerSlidingTab_pst_textAllCaps, this.r);
        obtainStyledAttributes2.recycle();
        this.F = q.e(getContext(), "sobot_background_tab");
        this.k = new Paint();
        this.k.setAntiAlias(true);
        this.k.setStyle(Paint.Style.FILL);
        this.l = new Paint();
        this.l.setAntiAlias(true);
        this.l.setStrokeWidth((float) this.x);
        this.c = new LinearLayout.LayoutParams(0, -1, 1.0f);
        this.d = new LinearLayout.LayoutParams(0, -1, 1.0f);
        if (this.G == null) {
            this.G = getResources().getConfiguration().locale;
        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.g = viewPager;
        if (viewPager.getAdapter() != null) {
            viewPager.setOnPageChangeListener(this.e);
            a();
            return;
        }
        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.a = onPageChangeListener;
    }

    public void a() {
        this.f.removeAllViews();
        this.h = this.g.getAdapter().getCount();
        for (int i = 0; i < this.h; i++) {
            if (this.g.getAdapter() instanceof a) {
                a(i, ((a) this.g.getAdapter()).a(i));
            } else {
                a(i, this.g.getAdapter().getPageTitle(i).toString());
            }
        }
        b();
        this.m = false;
        getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass1());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.PagerSlidingTab$1  reason: invalid class name */
    public class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        AnonymousClass1() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            PagerSlidingTab.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            PagerSlidingTab pagerSlidingTab = PagerSlidingTab.this;
            pagerSlidingTab.i = pagerSlidingTab.g.getCurrentItem();
            PagerSlidingTab pagerSlidingTab2 = PagerSlidingTab.this;
            pagerSlidingTab2.b(pagerSlidingTab2.i, 0);
        }
    }

    private void a(int i, String str) {
        TextView textView = new TextView(getContext());
        textView.setText(str);
        textView.setFocusable(true);
        textView.setGravity(17);
        textView.setSingleLine();
        textView.setTypeface(Typeface.defaultFromStyle(1));
        textView.setOnClickListener(new AnonymousClass2(i));
        this.f.addView(textView);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.PagerSlidingTab$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ int a;

        AnonymousClass2(int i) {
            this.a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PagerSlidingTab.this.g.setCurrentItem(this.a);
        }
    }

    private void a(int i, int i2) {
        ImageButton imageButton = new ImageButton(getContext());
        imageButton.setFocusable(true);
        imageButton.setImageResource(i2);
        imageButton.setOnClickListener(new AnonymousClass3(i));
        this.f.addView(imageButton);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.PagerSlidingTab$3  reason: invalid class name */
    public class AnonymousClass3 implements View.OnClickListener {
        final /* synthetic */ int a;

        AnonymousClass3(int i) {
            this.a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PagerSlidingTab.this.g.setCurrentItem(this.a);
        }
    }

    private void b() {
        int i = 0;
        while (i < this.h) {
            View childAt = this.f.getChildAt(i);
            childAt.setLayoutParams(this.c);
            childAt.setBackgroundResource(this.F);
            if (this.q) {
                childAt.setPadding(0, 0, 0, 0);
            } else {
                int i2 = this.w;
                childAt.setPadding(i2, 0, i2, 0);
            }
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                textView.setTextSize(0, (float) this.z);
                textView.setTypeface(this.C, this.D);
                textView.setTextColor(i == 0 ? this.B : this.A);
                textView.setText(textView.getText().toString());
            }
            i++;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.q && View.MeasureSpec.getMode(i) != 0) {
            int measuredWidth = getMeasuredWidth();
            int i3 = 0;
            for (int i4 = 0; i4 < this.h; i4++) {
                i3 += this.f.getChildAt(i4).getMeasuredWidth();
            }
            if (!this.m && i3 > 0 && measuredWidth > 0) {
                if (i3 <= measuredWidth) {
                    for (int i5 = 0; i5 < this.h; i5++) {
                        this.f.getChildAt(i5).setLayoutParams(this.d);
                    }
                }
                this.m = true;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(int i, int i2) {
        if (this.h != 0) {
            int left = this.f.getChildAt(i).getLeft() + i2;
            if (i > 0 || i2 > 0) {
                left -= this.s;
            }
            if (left != this.E) {
                this.E = left;
                scrollTo(left, 0);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        if (!(isInEditMode() || this.h == 0)) {
            int height = getHeight();
            this.k.setStrokeCap(Paint.Cap.ROUND);
            this.k.setColor(this.n);
            View childAt = this.f.getChildAt(this.i);
            float left = (float) childAt.getLeft();
            float right = (float) childAt.getRight();
            if (this.j > 0.0f && (i = this.i) < this.h - 1) {
                View childAt2 = this.f.getChildAt(i + 1);
                float f = this.j;
                left = (((float) childAt2.getLeft()) * f) + ((1.0f - f) * left);
                right = (((float) childAt2.getRight()) * f) + ((1.0f - f) * right);
            }
            float width = (float) childAt.getWidth();
            if (Build.VERSION.SDK_INT > 21) {
                int i2 = this.y;
                canvas.drawRoundRect(left + ((40.0f * width) / 70.0f), (float) ((height - this.t) - i2), right - ((width * 4.0f) / 7.0f), (float) (height - i2), 20.0f, 20.0f, this.k);
            } else {
                float f2 = (width * 3.0f) / 7.0f;
                int i3 = this.y;
                canvas.drawRoundRect(new RectF(left + f2, (float) ((height - this.t) - i3), right - f2, (float) (height - i3)), 20.0f, 20.0f, this.k);
            }
            this.k.setColor(this.o);
            canvas.drawRect(0.0f, (float) (height - this.u), (float) this.f.getWidth(), (float) height, this.k);
            this.l.setColor(this.p);
            for (int i4 = 0; i4 < this.h - 1; i4++) {
                View childAt3 = this.f.getChildAt(i4);
                canvas.drawLine((float) childAt3.getRight(), (float) this.v, (float) childAt3.getRight(), (float) (height - this.v), this.l);
            }
        }
    }

    private class b implements ViewPager.OnPageChangeListener {
        private b() {
        }

        /* synthetic */ b(PagerSlidingTab pagerSlidingTab, AnonymousClass1 r2) {
            this();
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            PagerSlidingTab.this.i = i;
            PagerSlidingTab.this.j = f;
            PagerSlidingTab pagerSlidingTab = PagerSlidingTab.this;
            pagerSlidingTab.b(i, (int) (((float) pagerSlidingTab.f.getChildAt(i).getWidth()) * f));
            PagerSlidingTab.this.invalidate();
            if (PagerSlidingTab.this.a != null) {
                PagerSlidingTab.this.a.onPageScrolled(i, f, i2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                PagerSlidingTab pagerSlidingTab = PagerSlidingTab.this;
                pagerSlidingTab.b(pagerSlidingTab.g.getCurrentItem(), 0);
            }
            if (PagerSlidingTab.this.a != null) {
                PagerSlidingTab.this.a.onPageScrollStateChanged(i);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            if (PagerSlidingTab.this.a != null) {
                PagerSlidingTab.this.a.onPageSelected(i);
            }
            int i2 = 0;
            while (i2 < PagerSlidingTab.this.h) {
                View childAt = PagerSlidingTab.this.f.getChildAt(i2);
                if (childAt instanceof TextView) {
                    ((TextView) childAt).setTextColor(i2 == PagerSlidingTab.this.g.getCurrentItem() ? PagerSlidingTab.this.B : PagerSlidingTab.this.A);
                }
                i2++;
            }
        }
    }

    public void setIndicatorColor(int i) {
        this.n = i;
        invalidate();
    }

    public void setIndicatorColorResource(int i) {
        this.n = getResources().getColor(i);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.n;
    }

    public void setIndicatorHeight(int i) {
        this.t = i;
        invalidate();
    }

    public int getIndicatorHeight() {
        return this.t;
    }

    public void setUnderlineColor(int i) {
        this.o = i;
        invalidate();
    }

    public void setUnderlineColorResource(int i) {
        this.o = getResources().getColor(i);
        invalidate();
    }

    public int getUnderlineColor() {
        return this.o;
    }

    public void setDividerColor(int i) {
        this.p = i;
        invalidate();
    }

    public void setDividerColorResource(int i) {
        this.p = getResources().getColor(i);
        invalidate();
    }

    public int getDividerColor() {
        return this.p;
    }

    public void setUnderlineHeight(int i) {
        this.u = i;
        invalidate();
    }

    public int getUnderlineHeight() {
        return this.u;
    }

    public void setDividerPadding(int i) {
        this.v = i;
        invalidate();
    }

    public int getDividerPadding() {
        return this.v;
    }

    public void setScrollOffset(int i) {
        this.s = i;
        invalidate();
    }

    public int getScrollOffset() {
        return this.s;
    }

    public void setShouldExpand(boolean z) {
        this.q = z;
        requestLayout();
    }

    public boolean getShouldExpand() {
        return this.q;
    }

    public void setAllCaps(boolean z) {
        this.r = z;
    }

    public void setTextSize(int i) {
        this.z = i;
        b();
    }

    public int getTextSize() {
        return this.z;
    }

    public void setTextColor(int i) {
        this.A = i;
        b();
    }

    public void setTextColorResource(int i) {
        this.A = getResources().getColor(i);
        b();
    }

    public int getTextColor() {
        return this.A;
    }

    public void setTabBackground(int i) {
        this.F = i;
    }

    public int getTabBackground() {
        return this.F;
    }

    public void setTabPaddingLeftRight(int i) {
        this.w = i;
        b();
    }

    public int getTabPaddingLeftRight() {
        return this.w;
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.i = savedState.a;
        requestLayout();
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.i;
        return savedState;
    }

    /* access modifiers changed from: package-private */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass1();
        int a;

        /* synthetic */ SavedState(Parcel parcel, AnonymousClass1 r2) {
            this(parcel);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }

        /* renamed from: com.sobot.chat.widget.PagerSlidingTab$SavedState$1  reason: invalid class name */
        static class AnonymousClass1 implements Parcelable.Creator<SavedState> {
            AnonymousClass1() {
            }

            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }
    }
}
