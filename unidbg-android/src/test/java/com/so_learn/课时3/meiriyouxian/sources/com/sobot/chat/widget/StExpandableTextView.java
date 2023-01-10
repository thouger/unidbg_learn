package com.sobot.chat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sobot.chat.R;
import com.sobot.chat.utils.j;
import com.sobot.chat.utils.q;

public class StExpandableTextView extends LinearLayout implements View.OnClickListener {
    private static final String i = StExpandableTextView.class.getSimpleName();
    protected TextView a;
    protected ViewGroup b;
    protected ImageView c;
    protected TextView d;
    protected ViewGroup e;
    protected int f;
    int g;
    int h;
    private boolean j;
    private boolean k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private float q;
    private boolean r;
    private b s;
    private SparseBooleanArray t;
    private int u;
    private boolean v;
    private boolean w;

    public interface b {
        void a(TextView textView, boolean z);
    }

    public StExpandableTextView(Context context) {
        this(context, null);
    }

    public StExpandableTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StExpandableTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f = 0;
        this.k = true;
        a(attributeSet);
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i2) {
        if (i2 != 0) {
            super.setOrientation(i2);
            return;
        }
        throw new IllegalArgumentException("ExpandableTextView only supports Vertical Orientation.");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        a aVar;
        ViewGroup viewGroup;
        if (this.b.getVisibility() == 0) {
            this.k = !this.k;
            c();
            SparseBooleanArray sparseBooleanArray = this.t;
            if (sparseBooleanArray != null) {
                sparseBooleanArray.put(this.u, this.k);
            }
            this.r = true;
            if (this.k) {
                b bVar = this.s;
                if (bVar != null) {
                    bVar.a(this.a, false);
                }
                aVar = new a(this, getHeight(), this.l);
            } else {
                b bVar2 = this.s;
                if (bVar2 != null) {
                    bVar2.a(this.a, true);
                }
                if (this.f == 0 && (viewGroup = this.e) != null) {
                    this.f = viewGroup.getMeasuredHeight();
                }
                aVar = new a(this, getHeight(), ((getHeight() + this.f) + this.m) - this.a.getHeight());
            }
            aVar.setFillAfter(true);
            aVar.setAnimationListener(new AnonymousClass1());
            clearAnimation();
            startAnimation(aVar);
        }
    }

    /* renamed from: com.sobot.chat.widget.StExpandableTextView$1  reason: invalid class name */
    class AnonymousClass1 implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        AnonymousClass1() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            StExpandableTextView.b(StExpandableTextView.this.a, StExpandableTextView.this.q);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            StExpandableTextView.this.clearAnimation();
            StExpandableTextView.this.r = false;
            if (StExpandableTextView.this.s != null) {
                StExpandableTextView.this.s.a(StExpandableTextView.this.a, !StExpandableTextView.this.k);
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.r;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        a();
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (getVisibility() == 8) {
            super.onMeasure(i2, i3);
            return;
        }
        this.j = false;
        this.b.setVisibility(8);
        this.a.setMaxLines(Integer.MAX_VALUE);
        setOtherViewVisibility(0);
        super.onMeasure(i2, i3);
        if (this.a.getLineCount() > this.n || this.w) {
            this.m = a(this.a);
            if (this.k) {
                this.a.setMaxLines(this.n);
                setOtherViewVisibility(8);
            }
            this.b.setVisibility(0);
            super.onMeasure(i2, i3);
            if (this.k) {
                this.a.post(new AnonymousClass2());
                this.l = getMeasuredHeight();
            }
        }
    }

    /* renamed from: com.sobot.chat.widget.StExpandableTextView$2  reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StExpandableTextView stExpandableTextView = StExpandableTextView.this;
            stExpandableTextView.o = stExpandableTextView.getHeight() - StExpandableTextView.this.a.getHeight();
        }
    }

    private void setOtherViewVisibility(int i2) {
        ViewGroup viewGroup = this.e;
        if (viewGroup != null) {
            viewGroup.setVisibility(i2);
        }
    }

    public void setOnExpandStateChangeListener(b bVar) {
        this.s = bVar;
    }

    public void setText(CharSequence charSequence) {
        this.j = true;
        if (TextUtils.isEmpty(charSequence)) {
            this.a.setText("");
        } else {
            j.a(getContext()).a(this.a, charSequence.toString(), q.c(getContext(), "sobot_announcement_title_color_2"), this.v);
        }
        setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
    }

    public CharSequence getText() {
        TextView textView = this.a;
        if (textView == null) {
            return "";
        }
        return textView.getText();
    }

    private void a(AttributeSet attributeSet) {
        this.p = 300;
        this.q = 1.0f;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ExpandableTextView);
        this.n = obtainStyledAttributes.getInt(R.styleable.ExpandableTextView_maxCollapsedLines, 5);
        this.g = obtainStyledAttributes.getResourceId(R.styleable.ExpandableTextView_ExpandStrResId, q.e(getContext(), "sobot_icon_triangle_down"));
        this.h = obtainStyledAttributes.getResourceId(R.styleable.ExpandableTextView_CollapseStrResId, q.e(getContext(), "sobot_icon_triangle_up"));
        obtainStyledAttributes.recycle();
        setOrientation(1);
        setVisibility(8);
        this.v = false;
    }

    private void a() {
        this.a = (TextView) findViewById(q.g(getContext(), "expandable_text"));
        this.b = (ViewGroup) findViewById(q.g(getContext(), "expand_collapse"));
        this.c = (ImageView) findViewById(q.g(getContext(), "expand_image"));
        this.d = (TextView) findViewById(q.g(getContext(), "expand_text_btn"));
        this.e = (ViewGroup) findViewById(q.g(getContext(), "expand_other_groupView"));
        c();
        this.b.setOnClickListener(this);
    }

    private static boolean b() {
        return Build.VERSION.SDK_INT >= 11;
    }

    /* access modifiers changed from: private */
    public static void b(View view, float f) {
        if (b()) {
            view.setAlpha(f);
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(f, f);
        alphaAnimation.setDuration(0);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
    }

    private static int a(TextView textView) {
        return textView.getLayout().getLineTop(textView.getLineCount()) + textView.getCompoundPaddingTop() + textView.getCompoundPaddingBottom();
    }

    class a extends Animation {
        private final View b;
        private final int c;
        private final int d;

        @Override // android.view.animation.Animation
        public boolean willChangeBounds() {
            return true;
        }

        public a(View view, int i, int i2) {
            this.b = view;
            this.c = i;
            this.d = i2;
            setDuration((long) StExpandableTextView.this.p);
        }

        /* access modifiers changed from: protected */
        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            int i = this.d;
            int i2 = this.c;
            int i3 = (int) ((((float) (i - i2)) * f) + ((float) i2));
            StExpandableTextView.this.a.setMaxHeight(i3 - StExpandableTextView.this.o);
            if (Float.compare(StExpandableTextView.this.q, 1.0f) != 0) {
                StExpandableTextView.b(StExpandableTextView.this.a, StExpandableTextView.this.q + (f * (1.0f - StExpandableTextView.this.q)));
            }
            this.b.getLayoutParams().height = i3;
            this.b.requestLayout();
        }

        @Override // android.view.animation.Animation
        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
        }
    }

    private void c() {
        this.c.setSelected(!this.k);
        this.c.setImageResource(this.k ? this.g : this.h);
        ViewGroup viewGroup = this.e;
        if (viewGroup != null) {
            viewGroup.setVisibility(this.k ? 8 : 0);
        }
    }

    public void setLinkBottomLine(boolean z) {
        this.v = z;
    }

    public ImageView getImageView() {
        return this.c;
    }

    public ViewGroup getmOtherView() {
        return this.e;
    }

    public TextView getTextBtn() {
        return this.d;
    }

    public void setHaveFile(boolean z) {
        this.w = z;
        postInvalidate();
    }
}
