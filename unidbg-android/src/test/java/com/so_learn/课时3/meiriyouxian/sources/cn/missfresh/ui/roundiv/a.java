package cn.missfresh.ui.roundiv;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.ImageView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.ims.ImsReasonInfo;

/* compiled from: RoundedDrawable */
/* access modifiers changed from: package-private */
public class a extends Drawable {
    private final RectF a = new RectF();
    private final RectF b = new RectF();
    private final RectF c = new RectF();
    private final Bitmap d;
    private final Paint e;
    private final int f;
    private final int g;
    private final RectF h = new RectF();
    private final Paint i;
    private final Matrix j = new Matrix();
    private BitmapShader k;
    private Shader.TileMode l = Shader.TileMode.CLAMP;
    private Shader.TileMode m = Shader.TileMode.CLAMP;
    private boolean n = true;
    private float o = 0.0f;
    private boolean p = false;
    private float q = 0.0f;
    private ColorStateList r = ColorStateList.valueOf(-16777216);
    private ImageView.ScaleType s = ImageView.ScaleType.FIT_CENTER;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public a(Bitmap bitmap) {
        AppMethodBeat.i(1395, false);
        this.d = bitmap;
        this.f = bitmap.getWidth();
        this.g = bitmap.getHeight();
        this.c.set(0.0f, 0.0f, (float) this.f, (float) this.g);
        this.e = new Paint();
        this.e.setStyle(Paint.Style.FILL);
        this.e.setAntiAlias(true);
        this.i = new Paint();
        this.i.setStyle(Paint.Style.STROKE);
        this.i.setAntiAlias(true);
        this.i.setColor(this.r.getColorForState(getState(), -16777216));
        this.i.setStrokeWidth(this.q);
        AppMethodBeat.o(1395);
    }

    public static a a(Bitmap bitmap) {
        AppMethodBeat.i(1398, false);
        if (bitmap != null) {
            a aVar = new a(bitmap);
            AppMethodBeat.o(1398);
            return aVar;
        }
        AppMethodBeat.o(1398);
        return null;
    }

    public static Drawable a(Drawable drawable) {
        AppMethodBeat.i(1401, false);
        if (drawable != null) {
            if (drawable instanceof a) {
                AppMethodBeat.o(1401);
                return drawable;
            } else if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    layerDrawable.setDrawableByLayerId(layerDrawable.getId(i), a(layerDrawable.getDrawable(i)));
                }
                AppMethodBeat.o(1401);
                return layerDrawable;
            } else {
                Bitmap b = b(drawable);
                if (b != null) {
                    a aVar = new a(b);
                    AppMethodBeat.o(1401);
                    return aVar;
                }
            }
        }
        AppMethodBeat.o(1401);
        return drawable;
    }

    public static Bitmap b(Drawable drawable) {
        Bitmap bitmap;
        AppMethodBeat.i(ImsReasonInfo.CODE_DATA_DISABLED, false);
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap2 = ((BitmapDrawable) drawable).getBitmap();
            AppMethodBeat.o(ImsReasonInfo.CODE_DATA_DISABLED);
            return bitmap2;
        }
        try {
            bitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 2), Math.max(drawable.getIntrinsicHeight(), 2), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
            bitmap = null;
        }
        AppMethodBeat.o(ImsReasonInfo.CODE_DATA_DISABLED);
        return bitmap;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        AppMethodBeat.i(1409, false);
        boolean isStateful = this.r.isStateful();
        AppMethodBeat.o(1409);
        return isStateful;
    }

    /* access modifiers changed from: protected */
    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        AppMethodBeat.i(1412, false);
        int colorForState = this.r.getColorForState(iArr, 0);
        if (this.i.getColor() != colorForState) {
            this.i.setColor(colorForState);
            AppMethodBeat.o(1412);
            return true;
        }
        boolean onStateChange = super.onStateChange(iArr);
        AppMethodBeat.o(1412);
        return onStateChange;
    }

    /* compiled from: RoundedDrawable */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ui.roundiv.a$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ImageView.ScaleType.values().length];

        static {
            AppMethodBeat.i(1387, false);
            try {
                a[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[ImageView.ScaleType.FIT_START.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            AppMethodBeat.o(1387);
        }
    }

    private void a() {
        float f;
        float f2;
        float f3;
        AppMethodBeat.i(1414, false);
        int i = AnonymousClass1.a[this.s.ordinal()];
        if (i == 1) {
            this.h.set(this.a);
            RectF rectF = this.h;
            float f4 = this.q;
            rectF.inset(f4 / 2.0f, f4 / 2.0f);
            this.j.reset();
            this.j.setTranslate((float) ((int) (((this.h.width() - ((float) this.f)) * 0.5f) + 0.5f)), (float) ((int) (((this.h.height() - ((float) this.g)) * 0.5f) + 0.5f)));
        } else if (i == 2) {
            this.h.set(this.a);
            RectF rectF2 = this.h;
            float f5 = this.q;
            rectF2.inset(f5 / 2.0f, f5 / 2.0f);
            this.j.reset();
            float f6 = 0.0f;
            if (((float) this.f) * this.h.height() > this.h.width() * ((float) this.g)) {
                f2 = this.h.height() / ((float) this.g);
                f = 0.0f;
                f6 = (this.h.width() - (((float) this.f) * f2)) * 0.5f;
            } else {
                f2 = this.h.width() / ((float) this.f);
                f = (this.h.height() - (((float) this.g) * f2)) * 0.5f;
            }
            this.j.setScale(f2, f2);
            Matrix matrix = this.j;
            float f7 = this.q;
            matrix.postTranslate(((float) ((int) (f6 + 0.5f))) + f7, ((float) ((int) (f + 0.5f))) + f7);
        } else if (i == 3) {
            this.j.reset();
            if (((float) this.f) > this.a.width() || ((float) this.g) > this.a.height()) {
                f3 = Math.min(this.a.width() / ((float) this.f), this.a.height() / ((float) this.g));
            } else {
                f3 = 1.0f;
            }
            this.j.setScale(f3, f3);
            this.j.postTranslate((float) ((int) (((this.a.width() - (((float) this.f) * f3)) * 0.5f) + 0.5f)), (float) ((int) (((this.a.height() - (((float) this.g) * f3)) * 0.5f) + 0.5f)));
            this.h.set(this.c);
            this.j.mapRect(this.h);
            RectF rectF3 = this.h;
            float f8 = this.q;
            rectF3.inset(f8 / 2.0f, f8 / 2.0f);
            this.j.setRectToRect(this.c, this.h, Matrix.ScaleToFit.FILL);
        } else if (i == 5) {
            this.h.set(this.c);
            this.j.setRectToRect(this.c, this.a, Matrix.ScaleToFit.END);
            this.j.mapRect(this.h);
            RectF rectF4 = this.h;
            float f9 = this.q;
            rectF4.inset(f9 / 2.0f, f9 / 2.0f);
            this.j.setRectToRect(this.c, this.h, Matrix.ScaleToFit.FILL);
        } else if (i == 6) {
            this.h.set(this.c);
            this.j.setRectToRect(this.c, this.a, Matrix.ScaleToFit.START);
            this.j.mapRect(this.h);
            RectF rectF5 = this.h;
            float f10 = this.q;
            rectF5.inset(f10 / 2.0f, f10 / 2.0f);
            this.j.setRectToRect(this.c, this.h, Matrix.ScaleToFit.FILL);
        } else if (i != 7) {
            this.h.set(this.c);
            this.j.setRectToRect(this.c, this.a, Matrix.ScaleToFit.CENTER);
            this.j.mapRect(this.h);
            RectF rectF6 = this.h;
            float f11 = this.q;
            rectF6.inset(f11 / 2.0f, f11 / 2.0f);
            this.j.setRectToRect(this.c, this.h, Matrix.ScaleToFit.FILL);
        } else {
            this.h.set(this.a);
            RectF rectF7 = this.h;
            float f12 = this.q;
            rectF7.inset(f12 / 2.0f, f12 / 2.0f);
            this.j.reset();
            this.j.setRectToRect(this.c, this.h, Matrix.ScaleToFit.FILL);
        }
        this.b.set(this.h);
        AppMethodBeat.o(1414);
    }

    /* access modifiers changed from: protected */
    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        AppMethodBeat.i(1418, false);
        super.onBoundsChange(rect);
        this.a.set(rect);
        a();
        AppMethodBeat.o(1418);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        AppMethodBeat.i(1422, false);
        if (this.n) {
            this.k = new BitmapShader(this.d, this.l, this.m);
            if (this.l == Shader.TileMode.CLAMP && this.m == Shader.TileMode.CLAMP) {
                this.k.setLocalMatrix(this.j);
            }
            this.e.setShader(this.k);
            this.n = false;
        }
        if (this.p) {
            if (this.q > 0.0f) {
                canvas.drawOval(this.b, this.e);
                canvas.drawOval(this.h, this.i);
            } else {
                canvas.drawOval(this.b, this.e);
            }
        } else if (this.q > 0.0f) {
            canvas.drawRoundRect(this.b, Math.max(this.o, 0.0f), Math.max(this.o, 0.0f), this.e);
            RectF rectF = this.h;
            float f = this.o;
            canvas.drawRoundRect(rectF, f, f, this.i);
        } else {
            RectF rectF2 = this.b;
            float f2 = this.o;
            canvas.drawRoundRect(rectF2, f2, f2, this.e);
        }
        AppMethodBeat.o(1422);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        AppMethodBeat.i(1429, false);
        this.e.setAlpha(i);
        invalidateSelf();
        AppMethodBeat.o(1429);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        AppMethodBeat.i(1432, false);
        this.e.setColorFilter(colorFilter);
        invalidateSelf();
        AppMethodBeat.o(1432);
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        AppMethodBeat.i(1435, false);
        this.e.setDither(z);
        invalidateSelf();
        AppMethodBeat.o(1435);
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        AppMethodBeat.i(1440, false);
        this.e.setFilterBitmap(z);
        invalidateSelf();
        AppMethodBeat.o(1440);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.g;
    }

    public a a(float f) {
        this.o = f;
        return this;
    }

    public a b(float f) {
        AppMethodBeat.i(1456, false);
        this.q = f;
        this.i.setStrokeWidth(this.q);
        AppMethodBeat.o(1456);
        return this;
    }

    public a a(ColorStateList colorStateList) {
        AppMethodBeat.i(1467, false);
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.r = colorStateList;
        this.i.setColor(this.r.getColorForState(getState(), -16777216));
        AppMethodBeat.o(1467);
        return this;
    }

    public a a(boolean z) {
        this.p = z;
        return this;
    }

    public a a(ImageView.ScaleType scaleType) {
        AppMethodBeat.i(1475, false);
        if (scaleType == null) {
            scaleType = ImageView.ScaleType.FIT_CENTER;
        }
        if (this.s != scaleType) {
            this.s = scaleType;
            a();
        }
        AppMethodBeat.o(1475);
        return this;
    }

    public a a(Shader.TileMode tileMode) {
        AppMethodBeat.i(1480, false);
        if (this.l != tileMode) {
            this.l = tileMode;
            this.n = true;
            invalidateSelf();
        }
        AppMethodBeat.o(1480);
        return this;
    }

    public a b(Shader.TileMode tileMode) {
        AppMethodBeat.i(1485, false);
        if (this.m != tileMode) {
            this.m = tileMode;
            this.n = true;
            invalidateSelf();
        }
        AppMethodBeat.o(1485);
        return this;
    }
}
