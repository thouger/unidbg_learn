package cn.missfresh.ui.skeleton.auto;

import android.animation.ValueAnimator;
import android.bluetooth.BluetoothClass;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.telephony.PreciseDisconnectCause;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import androidx.core.content.ContextCompat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;
import java.util.LinkedList;

/* compiled from: SkeletonDelegate */
public class b {
    private ViewGroup a;
    private boolean b = false;
    private int c = 3;
    private int d = -1;
    private int e = Color.GRAY;
    private int f = 1500;
    private float g = 0.0f;
    private boolean h;
    private ValueAnimator i;
    private int[] j;
    private Rect k;
    private LinkedList<a> l;
    private ViewPool m;
    private Paint n;
    private Matrix o = new Matrix();

    private b() {
        AppMethodBeat.i(1794, false);
        AppMethodBeat.o(1794);
    }

    public b(ViewGroup viewGroup) {
        AppMethodBeat.i(BluetoothClass.Device.WEARABLE_WRIST_WATCH, false);
        this.a = viewGroup;
        this.n = new Paint();
        this.n.setStyle(Paint.Style.FILL);
        this.n.setDither(true);
        this.n.setAntiAlias(true);
        this.j = new int[2];
        this.k = new Rect();
        this.l = new LinkedList<>();
        this.m = new ViewPool(10);
        for (int i = 0; i < 10; i++) {
            this.m.a(new a());
        }
        this.d = ContextCompat.getColor(viewGroup.getContext(), R.color.light_transparent);
        this.e = ContextCompat.getColor(viewGroup.getContext(), R.color.dark_transparent);
        this.i = d();
        AppMethodBeat.o(BluetoothClass.Device.WEARABLE_WRIST_WATCH);
    }

    private ValueAnimator d() {
        AppMethodBeat.i(1800, false);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setRepeatCount(-1);
        ofFloat.setDuration((long) this.f);
        ofFloat.setInterpolator(new AnticipateInterpolator());
        AppMethodBeat.o(1800);
        return ofFloat;
    }

    public void a(boolean z) {
        AppMethodBeat.i(PreciseDisconnectCause.UT_OPERATION_NOT_ALLOWED, false);
        this.b = z;
        this.a.setWillNotDraw(!this.b);
        this.a.postInvalidate();
        AppMethodBeat.o(PreciseDisconnectCause.UT_OPERATION_NOT_ALLOWED);
    }

    public boolean a() {
        return this.b;
    }

    public void a(int i) {
        this.c = i;
    }

    public void b(boolean z) {
        this.h = z;
    }

    /* compiled from: SkeletonDelegate */
    /* renamed from: cn.missfresh.ui.skeleton.auto.b$1  reason: invalid class name */
    class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
        AnonymousClass1() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            AppMethodBeat.i(1789, false);
            b.this.g = valueAnimator.getAnimatedFraction();
            b.this.a.postInvalidate();
            AppMethodBeat.o(1789);
        }
    }

    public void b() {
        AppMethodBeat.i(1835, false);
        if (this.h && this.b && !this.i.isStarted()) {
            this.i.addUpdateListener(new AnonymousClass1());
            this.i.start();
            this.a.postInvalidate();
        }
        AppMethodBeat.o(1835);
    }

    public void c() {
        AppMethodBeat.i(1839, false);
        if (this.h) {
            this.i.end();
            this.i.removeAllUpdateListeners();
        }
        AppMethodBeat.o(1839);
    }

    private void e() {
        AppMethodBeat.i(1842, false);
        float width = (float) this.a.getWidth();
        this.o.reset();
        this.o.postTranslate((this.g - 0.5f) * width * 1.5f, 0.0f);
        int i = this.d;
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, width, 0.0f, new int[]{i, this.e, i}, new float[]{0.25f, 0.5f, 0.65f}, Shader.TileMode.CLAMP);
        linearGradient.setLocalMatrix(this.o);
        this.n.setShader(linearGradient);
        AppMethodBeat.o(1842);
    }

    private void a(Canvas canvas, a aVar, int i, int i2) {
        AppMethodBeat.i(1858, false);
        if (!(aVar == null || aVar.b() == null)) {
            View b = aVar.b();
            b.getLocationInWindow(this.j);
            this.k.set(0, 0, b.getWidth(), b.getHeight());
            Rect rect = this.k;
            int[] iArr = this.j;
            rect.offset(iArr[0] - i, iArr[1] - i2);
            canvas.drawRect(this.k, this.n);
        }
        AppMethodBeat.o(1858);
    }

    public void a(Canvas canvas) {
        a a;
        AppMethodBeat.i(1861, false);
        this.a.getLocationInWindow(this.j);
        int[] iArr = this.j;
        int i = iArr[0];
        int i2 = iArr[1];
        if (this.l.isEmpty()) {
            a a2 = this.m.a();
            a2.a(this.a, 0);
            this.l.add(a2);
            e();
            while (true) {
                if (this.l.isEmpty()) {
                    break;
                }
                a removeLast = this.l.removeLast();
                View b = removeLast.b();
                int c = removeLast.c();
                if (c > this.c) {
                    this.l.clear();
                    break;
                }
                if (removeLast.d()) {
                    a(canvas, removeLast, i, i2);
                }
                removeLast.a();
                this.m.a(removeLast);
                if ((b instanceof ViewGroup) && c < this.c) {
                    ViewGroup viewGroup = (ViewGroup) b;
                    for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                        View childAt = viewGroup.getChildAt(i3);
                        if (!(childAt == null || childAt.getVisibility() != 0 || (a = this.m.a()) == null)) {
                            a.a(childAt, c + 1);
                            this.l.add(a);
                        }
                    }
                }
            }
            AppMethodBeat.o(1861);
            return;
        }
        AssertionError assertionError = new AssertionError("View pool is not empty!");
        AppMethodBeat.o(1861);
        throw assertionError;
    }
}
