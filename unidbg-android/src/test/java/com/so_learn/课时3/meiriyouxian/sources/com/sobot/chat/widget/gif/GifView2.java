package com.sobot.chat.widget.gif;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.sobot.chat.core.a;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.n;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class GifView2 extends View implements View.OnTouchListener {
    volatile boolean a;
    a b;
    private final int c;
    private int d;
    private Movie e;
    private long f;
    private int g;
    private float h;
    private float i;
    private float j;
    private int k;
    private int l;
    private boolean m;
    private boolean n;
    private int o;
    private double p;
    private double q;
    private double r;
    private double s;
    private double t;
    private double u;
    private double v;
    private double w;
    private boolean x;
    private String y;

    public interface a {
        void a(String str);
    }

    public GifView2(Context context) {
        this(context, null);
    }

    public GifView2(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GifView2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 1000;
        this.d = 0;
        this.f = 0;
        this.m = true;
        this.n = false;
        this.o = 0;
        this.p = 0.0d;
        this.q = 0.0d;
        this.r = 0.0d;
        this.s = 0.0d;
        this.t = 0.0d;
        this.u = 0.0d;
        this.v = 0.0d;
        this.w = 0.0d;
        this.x = false;
        setOnTouchListener(this);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0097  */
    @Override // android.view.View
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r9, int r10) {
        /*
        // Method dump skipped, instructions count: 260
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.widget.gif.GifView2.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.h = ((float) (getWidth() - this.k)) / 2.0f;
        this.i = ((float) (getHeight() - this.l)) / 2.0f;
        this.m = getVisibility() == 0;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.e == null) {
            return;
        }
        if (!this.a) {
            e();
            a(canvas);
            d();
            return;
        }
        a(canvas);
    }

    private void d() {
        if (!this.m) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            invalidate();
        }
    }

    private void e() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.f == 0) {
            this.f = uptimeMillis;
        }
        long duration = (long) this.e.duration();
        if (duration == 0) {
            duration = 1000;
        }
        this.g = (int) ((uptimeMillis - this.f) % duration);
    }

    private void a(Canvas canvas) {
        int i = this.l;
        if (i == 0 || i == 0) {
            canvas.restore();
            return;
        }
        this.e.setTime(this.g);
        canvas.save();
        float f = this.j;
        canvas.scale(f, f);
        Movie movie = this.e;
        float f2 = this.h;
        float f3 = this.j;
        movie.draw(canvas, f2 / f3, this.i / f3);
        canvas.restore();
    }

    @Override // android.view.View
    public void onScreenStateChanged(int i) {
        super.onScreenStateChanged(i);
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        this.m = z;
        d();
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.m = i == 0;
        d();
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.m = i == 0;
        d();
    }

    public void a() {
        if (!this.a) {
            this.a = true;
            invalidate();
        }
    }

    public int getMovieMovieResourceId() {
        return this.d;
    }

    public void setGifImage(FileInputStream fileInputStream) {
        if (fileInputStream != null) {
            this.e = Movie.decodeStream(fileInputStream);
        }
        requestLayout();
    }

    private void b(float f, float f2) {
        if (((double) getScaleX()) > 1.0d) {
            float pivotX = getPivotX() + f;
            float pivotY = getPivotY() + f2;
            Log.e("lawwingLog", "setPivotX:" + pivotX + "  setPivotY:" + pivotY + "  getWidth:" + getWidth() + "  getHeight:" + getHeight());
            int i = (pivotX > 0.0f ? 1 : (pivotX == 0.0f ? 0 : -1));
            if (i < 0 && pivotY < 0.0f) {
                pivotY = 0.0f;
                pivotX = 0.0f;
            } else if (pivotX > 0.0f && pivotY < 0.0f) {
                if (pivotX > ((float) getWidth())) {
                    pivotX = (float) getWidth();
                }
                pivotY = 0.0f;
            } else if (i >= 0 || pivotY <= 0.0f) {
                if (pivotX > ((float) getWidth())) {
                    pivotX = (float) getWidth();
                }
                if (pivotY > ((float) getHeight())) {
                    pivotY = (float) getHeight();
                }
            } else {
                if (pivotY > ((float) getHeight())) {
                    pivotY = (float) getHeight();
                }
                pivotX = 0.0f;
            }
            a(pivotX, pivotY);
        }
    }

    private double a(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != 2) {
            return 0.0d;
        }
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return Math.sqrt((double) ((x * x) + (y * y)));
    }

    public void a(float f, float f2) {
        setPivotX(f);
        setPivotY(f2);
    }

    public void setScale(float f) {
        setScaleX(f);
        setScaleY(f);
    }

    public void b() {
        setScaleX(1.0f);
        setScaleY(1.0f);
        a((float) (getWidth() / 2), (float) (getHeight() / 2));
    }

    public void setIsCanTouch(boolean z) {
        this.n = z;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.n) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.o = 1;
            this.t = (double) motionEvent.getX();
            this.u = (double) motionEvent.getY();
        } else if (action == 1) {
            this.o = 0;
            this.t = 0.0d;
            this.u = 0.0d;
            if (getScaleX() < 1.0f) {
                b();
            }
            this.x = false;
        } else if (action != 2) {
            if (action == 5) {
                this.p = a(motionEvent);
                this.o++;
                if (this.o >= 2) {
                    this.x = true;
                }
            } else if (action == 6) {
                this.o--;
            }
        } else if (this.o == 1 && !this.x) {
            float x = (float) (this.t - ((double) motionEvent.getX()));
            float y = (float) (this.u - ((double) motionEvent.getY()));
            this.r = (double) motionEvent.getX();
            this.s = (double) motionEvent.getY();
            this.v = (double) motionEvent.getRawX();
            this.w = (double) motionEvent.getRawY();
            b(x, y);
        } else if (this.o == 2) {
            this.q = a(motionEvent);
            float scaleX = (float) (((double) getScaleX()) + ((this.q - this.p) / ((double) getWidth())));
            if (scaleX > 0.5f && scaleX < 3.0f) {
                setScale(scaleX);
            } else if (scaleX < 0.5f) {
                setScale(0.5f);
            }
        }
        return true;
    }

    public File a(Context context) {
        return a(context, "images");
    }

    public File a(Context context, String str) {
        if (c()) {
            return context.getExternalFilesDir(str);
        }
        return context.getFilesDir();
    }

    public boolean c() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public void setLoadFinishListener(a aVar) {
        this.b = aVar;
    }

    public void a(FileInputStream fileInputStream, String str) {
        setGifImage(fileInputStream);
        this.y = str;
        Movie movie = this.e;
        if (movie == null || movie.width() == 0 || this.e.height() == 0) {
            a(str, new File(a(getContext()), n.a(str)), this);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.gif.GifView2$1  reason: invalid class name */
    public class AnonymousClass1 implements a.AbstractC0142a {
        AnonymousClass1() {
        }

        @Override // com.sobot.chat.core.a.AbstractC0142a
        public void a(File file) {
            m.d("down load onSuccess gif" + file.getAbsolutePath());
            if (GifView2.this.b != null) {
                GifView2.this.b.a(file.getAbsolutePath());
            }
        }

        @Override // com.sobot.chat.core.a.AbstractC0142a
        public void a(Exception exc, String str, int i) {
            m.b("\u56fe\u7247\u4e0b\u8f7d\u5931\u8d25:" + str, exc);
        }

        @Override // com.sobot.chat.core.a.AbstractC0142a
        public void a(int i) {
            m.d("gif\u56fe\u7247\u4e0b\u8f7d\u8fdb\u5ea6:" + i);
        }
    }

    public void a(String str, File file, GifView2 gifView2) {
        com.sobot.chat.core.a.a().a(str, file, (Map<String, String>) null, new AnonymousClass1());
    }
}
