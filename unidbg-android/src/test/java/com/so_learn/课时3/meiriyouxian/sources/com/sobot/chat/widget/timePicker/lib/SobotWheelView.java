package com.sobot.chat.widget.timePicker.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.sobot.chat.widget.timePicker.a.b;
import com.sobot.chat.widget.timePicker.b.c;
import com.sobot.chat.widget.timePicker.c.a;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SobotWheelView extends View {
    int A;
    int B;
    int C;
    int D;
    long E;
    int F;
    private DividerType G;
    private GestureDetector H;
    private boolean I;
    private boolean J;
    private ScheduledFuture<?> K;
    private String L;
    private int M;
    private int N;
    private float O;
    private int P;
    private int Q;
    private int R;
    private float S;
    Context a;
    Handler b;
    c c;
    ScheduledExecutorService d;
    Paint e;
    Paint f;
    Paint g;
    b h;
    int i;
    int j;
    int k;
    float l;
    Typeface m;
    int n;
    int o;
    int p;
    float q;
    boolean r;
    float s;
    float t;
    float u;
    float v;
    int w;
    int x;
    int y;
    int z;

    public enum ACTION {
        CLICK,
        FLING,
        DAGGLE
    }

    public enum DividerType {
        FILL,
        WRAP
    }

    public SobotWheelView(Context context) {
        this(context, null);
    }

    public SobotWheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.I = false;
        this.J = true;
        this.d = Executors.newSingleThreadScheduledExecutor();
        this.m = Typeface.MONOSPACE;
        this.n = -5723992;
        this.o = -14013910;
        this.p = -2763307;
        this.q = 1.6f;
        this.z = 11;
        this.N = 0;
        this.O = 0.0f;
        this.E = 0;
        this.P = 17;
        this.Q = 0;
        this.R = 0;
        this.i = (int) TypedValue.applyDimension(2, 20.0f, getResources().getDisplayMetrics());
        float f = getResources().getDisplayMetrics().density;
        if (f < 1.0f) {
            this.S = 2.4f;
        } else {
            int i = (1.0f > f ? 1 : (1.0f == f ? 0 : -1));
            if (i <= 0 && f < 2.0f) {
                this.S = 3.6f;
            } else if (i <= 0 && f < 2.0f) {
                this.S = 4.5f;
            } else if (2.0f <= f && f < 3.0f) {
                this.S = 6.0f;
            } else if (f >= 3.0f) {
                this.S = f * 2.5f;
            }
        }
        c();
        a(context);
    }

    private void c() {
        float f = this.q;
        if (f < 1.2f) {
            this.q = 1.2f;
        } else if (f > 2.0f) {
            this.q = 2.0f;
        }
    }

    private void a(Context context) {
        this.a = context;
        this.b = new c(this);
        this.H = new GestureDetector(context, new b(this));
        this.H.setIsLongpressEnabled(false);
        this.r = true;
        this.v = 0.0f;
        this.w = -1;
        d();
    }

    private void d() {
        this.e = new Paint();
        this.e.setColor(this.n);
        this.e.setAntiAlias(true);
        this.e.setTypeface(this.m);
        this.e.setTextSize((float) this.i);
        this.f = new Paint();
        this.f.setColor(this.o);
        this.f.setAntiAlias(true);
        this.f.setTextScaleX(1.1f);
        this.f.setTypeface(this.m);
        this.f.setTextSize((float) this.i);
        this.g = new Paint();
        this.g.setColor(this.p);
        this.g.setAntiAlias(true);
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
    }

    private void e() {
        if (this.h != null) {
            f();
            this.C = (int) (this.l * ((float) (this.z - 1)));
            int i = this.C;
            this.A = (int) (((double) (i * 2)) / 3.141592653589793d);
            this.D = (int) (((double) i) / 3.141592653589793d);
            this.B = View.MeasureSpec.getSize(this.F);
            int i2 = this.A;
            float f = this.l;
            this.s = (((float) i2) - f) / 2.0f;
            this.t = (((float) i2) + f) / 2.0f;
            this.u = (this.t - ((f - ((float) this.k)) / 2.0f)) - this.S;
            if (this.w == -1) {
                if (this.r) {
                    this.w = (this.h.a() + 1) / 2;
                } else {
                    this.w = 0;
                }
            }
            this.x = this.w;
        }
    }

    private void f() {
        Rect rect = new Rect();
        for (int i = 0; i < this.h.a(); i++) {
            String a = a(this.h.a(i));
            this.f.getTextBounds(a, 0, a.length(), rect);
            int width = rect.width();
            if (width > this.j) {
                this.j = width;
            }
            this.f.getTextBounds("\u661f\u671f", 0, 2, rect);
            this.k = rect.height() + 2;
        }
        this.l = this.q * ((float) this.k);
    }

    /* access modifiers changed from: package-private */
    public void a(ACTION action) {
        a();
        if (action == ACTION.FLING || action == ACTION.DAGGLE) {
            float f = this.v;
            float f2 = this.l;
            this.N = (int) (((f % f2) + f2) % f2);
            int i = this.N;
            if (((float) i) > f2 / 2.0f) {
                this.N = (int) (f2 - ((float) i));
            } else {
                this.N = -i;
            }
        }
        this.K = this.d.scheduleWithFixedDelay(new e(this, this.N), 0, 10, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: protected */
    public final void a(float f) {
        a();
        this.K = this.d.scheduleWithFixedDelay(new a(this, f), 0, 5, TimeUnit.MILLISECONDS);
    }

    public void a() {
        ScheduledFuture<?> scheduledFuture = this.K;
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            this.K.cancel(true);
            this.K = null;
        }
    }

    public final void setCyclic(boolean z) {
        this.r = z;
    }

    public final void setTypeface(Typeface typeface) {
        this.m = typeface;
        this.e.setTypeface(this.m);
        this.f.setTypeface(this.m);
    }

    public final void setTextSize(float f) {
        if (f > 0.0f) {
            this.i = (int) (this.a.getResources().getDisplayMetrics().density * f);
            this.e.setTextSize((float) this.i);
            this.f.setTextSize((float) this.i);
        }
    }

    public final void setCurrentItem(int i) {
        this.M = i;
        this.w = i;
        this.v = 0.0f;
        invalidate();
    }

    public final void setOnItemSelectedListener(c cVar) {
        this.c = cVar;
    }

    public final void setAdapter(b bVar) {
        this.h = bVar;
        e();
        invalidate();
    }

    public final b getAdapter() {
        return this.h;
    }

    public final int getCurrentItem() {
        return this.M;
    }

    /* access modifiers changed from: protected */
    public final void b() {
        if (this.c != null) {
            postDelayed(new d(this), 200);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Object[] objArr;
        boolean z;
        String str;
        int i;
        if (this.h != null) {
            boolean z2 = false;
            if (this.w < 0) {
                this.w = 0;
            }
            if (this.w >= this.h.a()) {
                this.w = this.h.a() - 1;
            }
            Object[] objArr2 = new Object[this.z];
            this.y = (int) (this.v / this.l);
            try {
                this.x = this.w + (this.y % this.h.a());
            } catch (ArithmeticException unused) {
                Log.e("WheelView", "\u51fa\u9519\u4e86\uff01adapter.getItemsCount() == 0\uff0c\u8054\u52a8\u6570\u636e\u4e0d\u5339\u914d");
            }
            if (!this.r) {
                if (this.x < 0) {
                    this.x = 0;
                }
                if (this.x > this.h.a() - 1) {
                    this.x = this.h.a() - 1;
                }
            } else {
                if (this.x < 0) {
                    this.x = this.h.a() + this.x;
                }
                if (this.x > this.h.a() - 1) {
                    this.x -= this.h.a();
                }
            }
            float f = this.v % this.l;
            int i2 = 0;
            while (true) {
                int i3 = this.z;
                if (i2 >= i3) {
                    break;
                }
                int i4 = this.x - ((i3 / 2) - i2);
                if (this.r) {
                    objArr2[i2] = this.h.a(a(i4));
                } else if (i4 < 0) {
                    objArr2[i2] = "";
                } else if (i4 > this.h.a() - 1) {
                    objArr2[i2] = "";
                } else {
                    objArr2[i2] = this.h.a(i4);
                }
                i2++;
            }
            if (this.G == DividerType.WRAP) {
                if (TextUtils.isEmpty(this.L)) {
                    i = (this.B - this.j) / 2;
                } else {
                    i = (this.B - this.j) / 4;
                }
                float f2 = (float) (i - 12);
                if (f2 <= 0.0f) {
                    f2 = 10.0f;
                }
                float f3 = ((float) this.B) - f2;
                float f4 = this.s;
                canvas.drawLine(f2, f4, f3, f4, this.g);
                float f5 = this.t;
                canvas.drawLine(f2, f5, f3, f5, this.g);
            } else {
                float f6 = this.s;
                canvas.drawLine(0.0f, f6, (float) this.B, f6, this.g);
                float f7 = this.t;
                canvas.drawLine(0.0f, f7, (float) this.B, f7, this.g);
            }
            if (!TextUtils.isEmpty(this.L) && this.J) {
                canvas.drawText(this.L, ((float) (this.B - a(this.f, this.L))) - this.S, this.u, this.f);
            }
            int i5 = 0;
            while (i5 < this.z) {
                canvas.save();
                double d = (double) (((this.l * ((float) i5)) - f) / ((float) this.D));
                float f8 = (float) (90.0d - ((d / 3.141592653589793d) * 180.0d));
                if (f8 >= 90.0f || f8 <= -90.0f) {
                    z = z2;
                    objArr = objArr2;
                    canvas.restore();
                } else {
                    if (this.J || TextUtils.isEmpty(this.L) || TextUtils.isEmpty(a(objArr2[i5]))) {
                        str = a(objArr2[i5]);
                    } else {
                        str = a(objArr2[i5]) + this.L;
                    }
                    a(str);
                    b(str);
                    c(str);
                    float cos = (float) ((((double) this.D) - (Math.cos(d) * ((double) this.D))) - ((Math.sin(d) * ((double) this.k)) / 2.0d));
                    canvas.translate(0.0f, cos);
                    canvas.scale(1.0f, (float) Math.sin(d));
                    float f9 = this.s;
                    if (cos > f9 || ((float) this.k) + cos < f9) {
                        objArr = objArr2;
                        float f10 = this.t;
                        if (cos > f10 || ((float) this.k) + cos < f10) {
                            if (cos >= this.s) {
                                int i6 = this.k;
                                if (((float) i6) + cos <= this.t) {
                                    canvas.drawText(str, (float) this.Q, ((float) i6) - this.S, this.f);
                                    this.M = this.h.a((b) objArr[i5]);
                                }
                            }
                            canvas.save();
                            z = false;
                            canvas.clipRect(0, 0, this.B, (int) this.l);
                            canvas.scale(1.0f, ((float) Math.sin(d)) * 0.8f);
                            canvas.drawText(str, (float) this.R, (float) this.k, this.e);
                            canvas.restore();
                            canvas.restore();
                            this.f.setTextSize((float) this.i);
                        } else {
                            canvas.save();
                            canvas.clipRect(0.0f, 0.0f, (float) this.B, this.t - cos);
                            canvas.scale(1.0f, ((float) Math.sin(d)) * 1.0f);
                            canvas.drawText(str, (float) this.Q, ((float) this.k) - this.S, this.f);
                            canvas.restore();
                            canvas.save();
                            canvas.clipRect(0.0f, this.t - cos, (float) this.B, (float) ((int) this.l));
                            canvas.scale(1.0f, ((float) Math.sin(d)) * 0.8f);
                            canvas.drawText(str, (float) this.R, (float) this.k, this.e);
                            canvas.restore();
                        }
                    } else {
                        canvas.save();
                        canvas.clipRect(0.0f, 0.0f, (float) this.B, this.s - cos);
                        objArr = objArr2;
                        canvas.scale(1.0f, ((float) Math.sin(d)) * 0.8f);
                        canvas.drawText(str, (float) this.R, (float) this.k, this.e);
                        canvas.restore();
                        canvas.save();
                        canvas.clipRect(0.0f, this.s - cos, (float) this.B, (float) ((int) this.l));
                        canvas.scale(1.0f, ((float) Math.sin(d)) * 1.0f);
                        canvas.drawText(str, (float) this.Q, ((float) this.k) - this.S, this.f);
                        canvas.restore();
                    }
                    z = false;
                    canvas.restore();
                    this.f.setTextSize((float) this.i);
                }
                i5++;
                z2 = z;
                objArr2 = objArr;
            }
        }
    }

    private void a(String str) {
        Rect rect = new Rect();
        this.f.getTextBounds(str, 0, str.length(), rect);
        int i = this.i;
        for (int width = rect.width(); width > this.B; width = rect.width()) {
            i--;
            this.f.setTextSize((float) i);
            this.f.getTextBounds(str, 0, str.length(), rect);
        }
        this.e.setTextSize((float) i);
    }

    private int a(int i) {
        if (i < 0) {
            return a(i + this.h.a());
        }
        return i > this.h.a() + -1 ? a(i - this.h.a()) : i;
    }

    private String a(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof a) {
            return ((a) obj).a();
        }
        if (obj instanceof Integer) {
            return String.format(Locale.getDefault(), "%02d", Integer.valueOf(((Integer) obj).intValue()));
        }
        return obj.toString();
    }

    private void b(String str) {
        String str2;
        Rect rect = new Rect();
        this.f.getTextBounds(str, 0, str.length(), rect);
        int i = this.P;
        if (i == 3) {
            this.Q = 0;
        } else if (i == 5) {
            this.Q = (this.B - rect.width()) - ((int) this.S);
        } else if (i == 17) {
            if (this.I || (str2 = this.L) == null || str2.equals("") || !this.J) {
                this.Q = (int) (((double) (this.B - rect.width())) * 0.5d);
            } else {
                this.Q = (int) (((double) (this.B - rect.width())) * 0.25d);
            }
        }
    }

    private void c(String str) {
        String str2;
        Rect rect = new Rect();
        this.e.getTextBounds(str, 0, str.length(), rect);
        int i = this.P;
        if (i == 3) {
            this.R = 0;
        } else if (i == 5) {
            this.R = (this.B - rect.width()) - ((int) this.S);
        } else if (i == 17) {
            if (this.I || (str2 = this.L) == null || str2.equals("") || !this.J) {
                this.R = (int) (((double) (this.B - rect.width())) * 0.5d);
            } else {
                this.R = (int) (((double) (this.B - rect.width())) * 0.25d);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        this.F = i;
        e();
        setMeasuredDimension(this.B, this.A);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = this.H.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.E = System.currentTimeMillis();
            a();
            this.O = motionEvent.getRawY();
        } else if (action == 2) {
            float rawY = this.O - motionEvent.getRawY();
            this.O = motionEvent.getRawY();
            this.v += rawY;
            if (!this.r) {
                float f = ((float) (-this.w)) * this.l;
                float f2 = this.l;
                float a = ((float) ((this.h.a() - 1) - this.w)) * f2;
                float f3 = this.v;
                if (((double) f3) - (((double) f2) * 0.25d) < ((double) f)) {
                    f = f3 - rawY;
                } else if (((double) f3) + (((double) f2) * 0.25d) > ((double) a)) {
                    a = f3 - rawY;
                }
                float f4 = this.v;
                if (f4 < f) {
                    this.v = (float) ((int) f);
                } else if (f4 > a) {
                    this.v = (float) ((int) a);
                }
            }
        } else if (!onTouchEvent) {
            float y = motionEvent.getY();
            int i = this.D;
            float f5 = this.l;
            this.N = (int) ((((float) (((int) (((Math.acos((double) ((((float) i) - y) / ((float) i))) * ((double) this.D)) + ((double) (f5 / 2.0f))) / ((double) f5))) - (this.z / 2))) * f5) - (((this.v % f5) + f5) % f5));
            if (System.currentTimeMillis() - this.E > 120) {
                a(ACTION.DAGGLE);
            } else {
                a(ACTION.CLICK);
            }
        }
        invalidate();
        return true;
    }

    public int getItemsCount() {
        b bVar = this.h;
        if (bVar != null) {
            return bVar.a();
        }
        return 0;
    }

    public void setLabel(String str) {
        this.L = str;
    }

    public void a(Boolean bool) {
        this.J = bool.booleanValue();
    }

    public void setGravity(int i) {
        this.P = i;
    }

    public int a(Paint paint, String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int length = str.length();
        float[] fArr = new float[length];
        paint.getTextWidths(str, fArr);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += (int) Math.ceil((double) fArr[i2]);
        }
        return i;
    }

    public void setIsOptions(boolean z) {
        this.I = z;
    }

    public void setTextColorOut(int i) {
        if (i != 0) {
            this.n = i;
            this.e.setColor(this.n);
        }
    }

    public void setTextColorCenter(int i) {
        if (i != 0) {
            this.o = i;
            this.f.setColor(this.o);
        }
    }

    public void setDividerColor(int i) {
        if (i != 0) {
            this.p = i;
            this.g.setColor(this.p);
        }
    }

    public void setDividerType(DividerType dividerType) {
        this.G = dividerType;
    }

    public void setLineSpacingMultiplier(float f) {
        if (f != 0.0f) {
            this.q = f;
            c();
        }
    }
}
