package cn.missfresh.module.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.Layout;
import android.text.NoCopySpan;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.AlignmentSpan;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.reflect.Field;

public class ExpandableTextView extends AppCompatTextView {
    public static final String a = "&";
    volatile boolean b;
    boolean c;
    public c d;
    private int e;
    private int f;
    private CharSequence g;
    private SpannableStringBuilder h;
    private SpannableStringBuilder i;
    private boolean j;
    private Animation k;
    private Animation l;
    private int m;
    private int n;
    private boolean o;
    private boolean p;
    private SpannableString q;
    private SpannableString r;
    private String s;
    private String t;
    private int u;
    private int v;
    private View.OnClickListener w;
    private a x;

    public interface a {
        SpannableStringBuilder a(CharSequence charSequence);
    }

    public interface c {
        void a();

        void b();
    }

    @Override // android.widget.TextView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    static /* synthetic */ void a(ExpandableTextView expandableTextView, int i) {
        AppMethodBeat.i(23667, false);
        super.setMaxLines(i);
        AppMethodBeat.o(23667);
    }

    static /* synthetic */ void b(ExpandableTextView expandableTextView, int i) {
        AppMethodBeat.i(23668, false);
        super.setMaxLines(i);
        AppMethodBeat.o(23668);
    }

    static /* synthetic */ void c(ExpandableTextView expandableTextView, int i) {
        AppMethodBeat.i(23669, false);
        super.setMaxLines(i);
        AppMethodBeat.o(23669);
    }

    static /* synthetic */ void d(ExpandableTextView expandableTextView, int i) {
        AppMethodBeat.i(23670, false);
        super.setMaxLines(i);
        AppMethodBeat.o(23670);
    }

    static /* synthetic */ void g(ExpandableTextView expandableTextView) {
        AppMethodBeat.i(23671, false);
        expandableTextView.b();
        AppMethodBeat.o(23671);
    }

    static {
        AppMethodBeat.i(23672, false);
        AppMethodBeat.o(23672);
    }

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExpandableTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(23646, false);
        this.b = false;
        this.c = false;
        this.f = 0;
        this.j = false;
        this.s = " \u5c55\u5f00";
        this.t = " \u6536\u8d77";
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExpandableTextView);
            this.e = obtainStyledAttributes.getInt(R.styleable.ExpandableTextView_maxShowLines, 3);
            this.s = obtainStyledAttributes.getString(R.styleable.ExpandableTextView_openText);
            this.t = obtainStyledAttributes.getString(R.styleable.ExpandableTextView_closeText);
            this.u = obtainStyledAttributes.getColor(R.styleable.ExpandableTextView_openColor, 0);
            this.v = obtainStyledAttributes.getColor(R.styleable.ExpandableTextView_closeColor, 0);
            this.j = obtainStyledAttributes.getBoolean(R.styleable.ExpandableTextView_hasAnimation, false);
            this.p = obtainStyledAttributes.getBoolean(R.styleable.ExpandableTextView_closeInNewLine, true);
            obtainStyledAttributes.recycle();
        }
        a();
        AppMethodBeat.o(23646);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(23647, false);
        super.onMeasure(i, i2);
        this.f = View.MeasureSpec.getSize(i);
        AppMethodBeat.o(23647);
    }

    private void a() {
        AppMethodBeat.i(23648, false);
        if (this.u == 0) {
            this.u = Color.parseColor("#F23030");
        }
        if (this.v == 0) {
            this.v = Color.parseColor("#F23030");
        }
        setMovementMethod(d.a());
        setIncludeFontPadding(false);
        g();
        h();
        AppMethodBeat.o(23648);
    }

    public void setOriginalText(CharSequence charSequence) {
        int length;
        AppMethodBeat.i(23649, false);
        this.g = charSequence;
        this.o = false;
        this.i = new SpannableStringBuilder();
        int i = this.e;
        SpannableStringBuilder a2 = a(charSequence);
        this.h = a(charSequence);
        if (i != -1) {
            Layout a3 = a(a2);
            this.o = a3.getLineCount() > i;
            if (this.o) {
                if (this.p) {
                    this.h.append((CharSequence) "\n");
                }
                SpannableString spannableString = this.r;
                if (spannableString != null) {
                    this.h.append((CharSequence) spannableString);
                }
                int lineEnd = a3.getLineEnd(i - 1);
                if (charSequence.length() <= lineEnd) {
                    this.i = a(charSequence);
                } else {
                    this.i = a(charSequence.subSequence(0, lineEnd));
                }
                SpannableStringBuilder append = a((CharSequence) this.i).append((CharSequence) a);
                SpannableString spannableString2 = this.q;
                if (spannableString2 != null) {
                    append.append((CharSequence) spannableString2);
                }
                Layout a4 = a(append);
                while (a4.getLineCount() > i && (length = this.i.length() - 1) != -1) {
                    if (charSequence.length() <= length) {
                        this.i = a(charSequence);
                    } else {
                        this.i = a(charSequence.subSequence(0, length));
                    }
                    SpannableStringBuilder append2 = a((CharSequence) this.i).append((CharSequence) a);
                    SpannableString spannableString3 = this.q;
                    if (spannableString3 != null) {
                        append2.append((CharSequence) spannableString3);
                    }
                    a4 = a(append2);
                }
                this.n = a4.getHeight() + getPaddingTop() + getPaddingBottom();
                this.i.append((CharSequence) a);
                SpannableString spannableString4 = this.q;
                if (spannableString4 != null) {
                    this.i.append((CharSequence) spannableString4);
                }
            }
        }
        this.c = this.o;
        super.setOnClickListener(new AnonymousClass1());
        if (this.o) {
            setText(this.i);
        } else {
            setText(this.h);
        }
        AppMethodBeat.o(23649);
    }

    /* renamed from: cn.missfresh.module.base.widget.ExpandableTextView$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23633, false);
            if (ExpandableTextView.this.w != null) {
                ExpandableTextView.this.w.onClick(view);
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23633);
        }
    }

    private void b() {
        AppMethodBeat.i(23651, false);
        if (this.o) {
            this.c = !this.c;
            if (this.c) {
                d();
            } else {
                c();
            }
        }
        AppMethodBeat.o(23651);
    }

    public void setHasAnimation(boolean z) {
        this.j = z;
    }

    private void c() {
        AppMethodBeat.i(23652, false);
        if (this.j) {
            this.m = a(this.h).getHeight() + getPaddingTop() + getPaddingBottom();
            e();
        } else {
            a(this, Integer.MAX_VALUE);
            setText(this.h);
            c cVar = this.d;
            if (cVar != null) {
                cVar.a();
            }
        }
        AppMethodBeat.o(23652);
    }

    private void d() {
        AppMethodBeat.i(23653, false);
        if (this.j) {
            f();
        } else {
            b(this, this.e);
            setText(this.i);
            c cVar = this.d;
            if (cVar != null) {
                cVar.b();
            }
        }
        AppMethodBeat.o(23653);
    }

    private void e() {
        AppMethodBeat.i(23654, false);
        if (this.k == null) {
            this.k = new b(this, this.n, this.m);
            this.k.setFillAfter(true);
            this.k.setAnimationListener(new AnonymousClass2());
        }
        if (this.b) {
            AppMethodBeat.o(23654);
            return;
        }
        this.b = true;
        clearAnimation();
        startAnimation(this.k);
        AppMethodBeat.o(23654);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.ExpandableTextView$2  reason: invalid class name */
    public class AnonymousClass2 implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        AnonymousClass2() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            AppMethodBeat.i(23634, false);
            ExpandableTextView.c(ExpandableTextView.this, Integer.MAX_VALUE);
            ExpandableTextView expandableTextView = ExpandableTextView.this;
            expandableTextView.setText(expandableTextView.h);
            AppMethodBeat.o(23634);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            AppMethodBeat.i(23635, false);
            ExpandableTextView.this.getLayoutParams().height = ExpandableTextView.this.m;
            ExpandableTextView.this.requestLayout();
            ExpandableTextView.this.b = false;
            AppMethodBeat.o(23635);
        }
    }

    private void f() {
        AppMethodBeat.i(23655, false);
        if (this.l == null) {
            this.l = new b(this, this.m, this.n);
            this.l.setFillAfter(true);
            this.l.setAnimationListener(new AnonymousClass3());
        }
        if (this.b) {
            AppMethodBeat.o(23655);
            return;
        }
        this.b = true;
        clearAnimation();
        startAnimation(this.l);
        AppMethodBeat.o(23655);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.ExpandableTextView$3  reason: invalid class name */
    public class AnonymousClass3 implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        AnonymousClass3() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            AppMethodBeat.i(23636, false);
            ExpandableTextView expandableTextView = ExpandableTextView.this;
            expandableTextView.b = false;
            ExpandableTextView.d(expandableTextView, expandableTextView.e);
            ExpandableTextView expandableTextView2 = ExpandableTextView.this;
            expandableTextView2.setText(expandableTextView2.i);
            ExpandableTextView.this.getLayoutParams().height = ExpandableTextView.this.n;
            ExpandableTextView.this.requestLayout();
            AppMethodBeat.o(23636);
        }
    }

    private Layout a(SpannableStringBuilder spannableStringBuilder) {
        AppMethodBeat.i(23656, false);
        int paddingLeft = (this.f - getPaddingLeft()) - getPaddingRight();
        if (Build.VERSION.SDK_INT >= 23) {
            StaticLayout.Builder obtain = StaticLayout.Builder.obtain(spannableStringBuilder, 0, spannableStringBuilder.length(), getPaint(), paddingLeft);
            obtain.setAlignment(Layout.Alignment.ALIGN_NORMAL);
            obtain.setIncludePad(getIncludeFontPadding());
            obtain.setLineSpacing(getLineSpacingExtra(), getLineSpacingMultiplier());
            StaticLayout build = obtain.build();
            AppMethodBeat.o(23656);
            return build;
        } else if (Build.VERSION.SDK_INT >= 16) {
            StaticLayout staticLayout = new StaticLayout(spannableStringBuilder, getPaint(), paddingLeft, Layout.Alignment.ALIGN_NORMAL, getLineSpacingMultiplier(), getLineSpacingExtra(), getIncludeFontPadding());
            AppMethodBeat.o(23656);
            return staticLayout;
        } else {
            StaticLayout staticLayout2 = new StaticLayout(spannableStringBuilder, getPaint(), paddingLeft, Layout.Alignment.ALIGN_NORMAL, a("mSpacingMult", 1.0f), a("mSpacingAdd", 0.0f), getIncludeFontPadding());
            AppMethodBeat.o(23656);
            return staticLayout2;
        }
    }

    private float a(String str, float f) {
        int i = 0;
        AppMethodBeat.i(23657, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(23657);
            return f;
        }
        try {
            Field[] declaredFields = getClass().getDeclaredFields();
            int length = declaredFields.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                Field field = declaredFields[i];
                if (TextUtils.equals(str, field.getName())) {
                    f = field.getFloat(this);
                    break;
                }
                i++;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(23657);
        return f;
    }

    private SpannableStringBuilder a(CharSequence charSequence) {
        AppMethodBeat.i(23658, false);
        a aVar = this.x;
        SpannableStringBuilder a2 = aVar != null ? aVar.a(charSequence) : null;
        if (a2 == null) {
            a2 = new SpannableStringBuilder(charSequence);
        }
        AppMethodBeat.o(23658);
        return a2;
    }

    @Override // android.widget.TextView
    public void setMaxLines(int i) {
        AppMethodBeat.i(23659, false);
        this.e = i;
        super.setMaxLines(i);
        AppMethodBeat.o(23659);
    }

    public void setOpenSuffix(String str) {
        AppMethodBeat.i(23660, false);
        this.s = str;
        g();
        AppMethodBeat.o(23660);
    }

    public void setOpenSuffixColor(int i) {
        AppMethodBeat.i(23661, false);
        this.u = i;
        g();
        AppMethodBeat.o(23661);
    }

    public void setCloseSuffix(String str) {
        AppMethodBeat.i(23662, false);
        this.t = str;
        h();
        AppMethodBeat.o(23662);
    }

    public void setCloseSuffixColor(int i) {
        AppMethodBeat.i(23663, false);
        this.v = i;
        h();
        AppMethodBeat.o(23663);
    }

    public void setCloseInNewLine(boolean z) {
        AppMethodBeat.i(23664, false);
        this.p = z;
        h();
        AppMethodBeat.o(23664);
    }

    private void g() {
        AppMethodBeat.i(23665, false);
        if (TextUtils.isEmpty(this.s)) {
            this.q = null;
            AppMethodBeat.o(23665);
            return;
        }
        this.q = new SpannableString(this.s);
        this.q.setSpan(new AnonymousClass4(), 0, this.s.length(), 34);
        AppMethodBeat.o(23665);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.ExpandableTextView$4  reason: invalid class name */
    public class AnonymousClass4 extends ClickableSpan {
        AnonymousClass4() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            AppMethodBeat.i(23637, false);
            ExpandableTextView.g(ExpandableTextView.this);
            AppMethodBeat.o(23637);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            AppMethodBeat.i(23638, false);
            super.updateDrawState(textPaint);
            textPaint.setColor(ExpandableTextView.this.u);
            textPaint.setUnderlineText(false);
            AppMethodBeat.o(23638);
        }
    }

    private void h() {
        AppMethodBeat.i(23666, false);
        if (TextUtils.isEmpty(this.t)) {
            this.r = null;
            AppMethodBeat.o(23666);
            return;
        }
        this.r = new SpannableString(this.t);
        this.r.setSpan(new StyleSpan(1), 0, this.t.length(), 33);
        if (this.p) {
            this.r.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), 0, 1, 33);
        }
        this.r.setSpan(new AnonymousClass5(), 1, this.t.length(), 33);
        AppMethodBeat.o(23666);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.widget.ExpandableTextView$5  reason: invalid class name */
    public class AnonymousClass5 extends ClickableSpan {
        AnonymousClass5() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            AppMethodBeat.i(23639, false);
            ExpandableTextView.g(ExpandableTextView.this);
            AppMethodBeat.o(23639);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            AppMethodBeat.i(23640, false);
            super.updateDrawState(textPaint);
            textPaint.setColor(ExpandableTextView.this.v);
            textPaint.setUnderlineText(false);
            AppMethodBeat.o(23640);
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.w = onClickListener;
    }

    public void setOpenAndCloseCallback(c cVar) {
        this.d = cVar;
    }

    public void setCharSequenceToSpannableHandler(a aVar) {
        this.x = aVar;
    }

    /* access modifiers changed from: package-private */
    public class b extends Animation {
        private final View b;
        private final int c;
        private final int d;

        b(View view, int i, int i2) {
            AppMethodBeat.i(23641, false);
            this.b = view;
            this.c = i;
            this.d = i2;
            setDuration(400);
            AppMethodBeat.o(23641);
        }

        /* access modifiers changed from: protected */
        @Override // android.view.animation.Animation
        public void applyTransformation(float f, Transformation transformation) {
            AppMethodBeat.i(23642, false);
            this.b.setScrollY(0);
            ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
            int i = this.d;
            int i2 = this.c;
            layoutParams.height = (int) ((((float) (i - i2)) * f) + ((float) i2));
            this.b.requestLayout();
            AppMethodBeat.o(23642);
        }
    }

    /* access modifiers changed from: private */
    public static class d extends LinkMovementMethod {
        private static boolean a = false;
        private static d b;
        private static Object c = new NoCopySpan.Concrete();

        private d() {
        }

        static {
            AppMethodBeat.i(23645, false);
            AppMethodBeat.o(23645);
        }

        @Override // android.text.method.LinkMovementMethod, android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
        public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
            AppMethodBeat.i(23643, false);
            if (motionEvent.getAction() != 2 || a) {
                boolean onTouchEvent = super.onTouchEvent(textView, spannable, motionEvent);
                AppMethodBeat.o(23643);
                return onTouchEvent;
            }
            AppMethodBeat.o(23643);
            return true;
        }

        public static MovementMethod a() {
            AppMethodBeat.i(23644, false);
            if (b == null) {
                b = new d();
            }
            d dVar = b;
            AppMethodBeat.o(23644);
            return dVar;
        }
    }
}
