package cn.missfresh.module.base.support;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

@Deprecated
public class RoundImageView extends AppCompatImageView {
    private int a;
    private int b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private Paint i;
    private Paint j;
    private float k;
    private Matrix l;
    private BitmapShader m;
    private int n;
    private RectF o;
    private Path p;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(20061, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundImageView, i, 0);
        this.a = obtainStyledAttributes.getInt(R.styleable.RoundImageView_round_type, 2);
        this.b = obtainStyledAttributes.getColor(R.styleable.RoundImageView_round_border_color, -1);
        this.c = obtainStyledAttributes.getDimension(R.styleable.RoundImageView_round_border_width, 0.0f);
        this.d = obtainStyledAttributes.getDimension(R.styleable.RoundImageView_round_corner_radius, (float) a(10));
        this.e = obtainStyledAttributes.getDimension(R.styleable.RoundImageView_leftTop_corner_radius, 0.0f);
        this.g = obtainStyledAttributes.getDimension(R.styleable.RoundImageView_leftBottom_corner_radius, 0.0f);
        this.f = obtainStyledAttributes.getDimension(R.styleable.RoundImageView_rightTop_corner_radius, 0.0f);
        this.h = obtainStyledAttributes.getDimension(R.styleable.RoundImageView_rightBottom_corner_radius, 0.0f);
        obtainStyledAttributes.recycle();
        a();
        AppMethodBeat.o(20061);
    }

    private void a() {
        AppMethodBeat.i(20064, false);
        this.p = new Path();
        this.l = new Matrix();
        this.i = new Paint();
        this.i.setAntiAlias(true);
        this.j = new Paint();
        this.j.setAntiAlias(true);
        this.j.setStyle(Paint.Style.STROKE);
        AppMethodBeat.o(20064);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(20066, false);
        super.onMeasure(i, i2);
        if (this.a == 0) {
            this.n = Math.min(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
            int i3 = this.n;
            this.k = (((float) i3) / 2.0f) - (this.c / 2.0f);
            setMeasuredDimension(i3, i3);
        }
        AppMethodBeat.o(20066);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        AppMethodBeat.i(20069, false);
        super.onSizeChanged(i, i2, i3, i4);
        int i5 = this.a;
        if (i5 == 1 || i5 == 2) {
            float f = this.c;
            this.o = new RectF(f / 2.0f, f / 2.0f, ((float) i) - (f / 2.0f), ((float) i2) - (f / 2.0f));
        }
        AppMethodBeat.o(20069);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        AppMethodBeat.i(20072, false);
        this.j.setColor(this.b);
        this.j.setStrokeWidth(this.c);
        if (getDrawable() == null) {
            AppMethodBeat.o(20072);
            return;
        }
        c();
        int i = this.a;
        if (i == 1) {
            b();
            canvas.drawPath(this.p, this.i);
            canvas.drawPath(this.p, this.j);
        } else if (i == 0) {
            float f = this.k;
            float f2 = this.c;
            canvas.drawCircle((f2 / 2.0f) + f, (f2 / 2.0f) + f, f, this.i);
            float f3 = this.k;
            float f4 = this.c;
            canvas.drawCircle((f4 / 2.0f) + f3, (f4 / 2.0f) + f3, f3, this.j);
        } else {
            canvas.drawOval(this.o, this.i);
            canvas.drawOval(this.o, this.j);
        }
        AppMethodBeat.o(20072);
    }

    private void b() {
        AppMethodBeat.i(20075, false);
        this.p.reset();
        if (this.e == 0.0f && this.g == 0.0f && this.f == 0.0f && this.h == 0.0f) {
            Path path = this.p;
            RectF rectF = this.o;
            float f = this.d;
            path.addRoundRect(rectF, new float[]{f, f, f, f, f, f, f, f}, Path.Direction.CW);
        } else {
            Path path2 = this.p;
            RectF rectF2 = this.o;
            float f2 = this.e;
            float f3 = this.f;
            float f4 = this.h;
            float f5 = this.g;
            path2.addRoundRect(rectF2, new float[]{f2, f2, f3, f3, f4, f4, f5, f5}, Path.Direction.CW);
        }
        AppMethodBeat.o(20075);
    }

    private void c() {
        AppMethodBeat.i(20079, false);
        Drawable drawable = getDrawable();
        if (drawable == null) {
            AppMethodBeat.o(20079);
            return;
        }
        Bitmap a = a(drawable);
        this.m = new BitmapShader(a, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        int i = this.a;
        float f = 1.0f;
        if (i == 0) {
            f = (((float) this.n) * 1.0f) / ((float) Math.min(a.getWidth(), a.getHeight()));
            this.l.setTranslate(-(((((float) a.getWidth()) * f) - ((float) this.n)) / 2.0f), -(((((float) a.getHeight()) * f) - ((float) this.n)) / 2.0f));
        } else if ((i == 1 || i == 2) && !(a.getWidth() == getWidth() && a.getHeight() == getHeight())) {
            f = Math.max((((float) getWidth()) * 1.0f) / ((float) a.getWidth()), (((float) getHeight()) * 1.0f) / ((float) a.getHeight()));
            this.l.setTranslate(-(((((float) a.getWidth()) * f) - ((float) getWidth())) / 2.0f), -(((((float) a.getHeight()) * f) - ((float) getHeight())) / 2.0f));
        }
        this.l.preScale(f, f);
        this.m.setLocalMatrix(this.l);
        this.m.setLocalMatrix(this.l);
        this.i.setShader(this.m);
        AppMethodBeat.o(20079);
    }

    private Bitmap a(Drawable drawable) {
        AppMethodBeat.i(20082, false);
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            AppMethodBeat.o(20082);
            return bitmap;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        AppMethodBeat.o(20082);
        return createBitmap;
    }

    private int a(int i) {
        AppMethodBeat.i(20101, false);
        int applyDimension = (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
        AppMethodBeat.o(20101);
        return applyDimension;
    }
}
