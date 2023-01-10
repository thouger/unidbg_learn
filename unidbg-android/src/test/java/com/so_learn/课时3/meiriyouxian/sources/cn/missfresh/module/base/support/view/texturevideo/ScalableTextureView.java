package cn.missfresh.module.base.support.view.texturevideo;

import android.graphics.Matrix;
import android.view.TextureView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;

public abstract class ScalableTextureView extends TextureView {
    private static final String a = ScalableTextureView.class.getSimpleName();
    private Integer b;
    private Integer c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private int j;
    private int k;
    private final Matrix l;
    private ScaleType m;

    public enum ScaleType {
        CENTER_CROP,
        TOP,
        BOTTOM,
        FILL;

        public static ScaleType valueOf(String str) {
            AppMethodBeat.i(22934, false);
            ScaleType scaleType = (ScaleType) Enum.valueOf(ScaleType.class, str);
            AppMethodBeat.o(22934);
            return scaleType;
        }

        static {
            AppMethodBeat.i(22935, false);
            AppMethodBeat.o(22935);
        }
    }

    public void setScaleType(ScaleType scaleType) {
        this.m = scaleType;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        String str = a;
        d.d(str, "onMeasure, mContentoWidth " + this.b + ", mContentHeight " + this.c);
        if (this.b != null && this.c != null) {
            a();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x017e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
        // Method dump skipped, instructions count: 417
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.support.view.texturevideo.ScalableTextureView.a():void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.view.texturevideo.ScalableTextureView$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ScaleType.values().length];

        static {
            AppMethodBeat.i(22932, false);
            try {
                a[ScaleType.FILL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ScaleType.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ScaleType.CENTER_CROP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ScaleType.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            AppMethodBeat.o(22932);
        }
    }

    private void b() {
        String str = a;
        d.d(str, ">> updateMatrixScaleRotate, mContentRotation " + this.h + ", mContentScaleMultiplier " + this.i + ", mPivotPointX " + this.d + ", mPivotPointY " + this.e);
        this.l.reset();
        Matrix matrix = this.l;
        float f = this.f;
        float f2 = this.i;
        matrix.setScale(f * f2, this.g * f2, this.d, this.e);
        this.l.postRotate(this.h, this.d, this.e);
        setTransform(this.l);
        String str2 = a;
        d.d(str2, "<< updateMatrixScaleRotate, mContentRotation " + this.h + ", mContentScaleMultiplier " + this.i + ", mPivotPointX " + this.d + ", mPivotPointY " + this.e);
    }

    private void c() {
        String str = a;
        d.d(str, "updateMatrixTranslate, mContentX " + this.j + ", mContentY " + this.k);
        float f = this.f;
        float f2 = this.i;
        float f3 = this.g * f2;
        this.l.reset();
        this.l.setScale(f * f2, f3, this.d, this.e);
        this.l.postTranslate((float) this.j, (float) this.k);
        setTransform(this.l);
    }

    @Override // android.view.View
    public void setRotation(float f) {
        String str = a;
        d.d(str, "setRotation, degrees " + f + ", mPivotPointX " + this.d + ", mPivotPointY " + this.e);
        this.h = f;
        b();
    }

    @Override // android.view.View
    public float getRotation() {
        return this.h;
    }

    @Override // android.view.View
    public void setPivotX(float f) {
        String str = a;
        d.d(str, "setPivotX, pivotX " + f);
        this.d = f;
    }

    @Override // android.view.View
    public void setPivotY(float f) {
        String str = a;
        d.d(str, "setPivotY, pivotY " + f);
        this.e = f;
    }

    @Override // android.view.View
    public float getPivotX() {
        return this.d;
    }

    @Override // android.view.View
    public float getPivotY() {
        return this.e;
    }

    public float getContentAspectRatio() {
        Integer num = this.b;
        if (num == null || this.c == null) {
            return 0.0f;
        }
        return ((float) num.intValue()) / ((float) this.c.intValue());
    }

    public final void setContentX(float f) {
        this.j = ((int) f) - ((getMeasuredWidth() - getScaledContentWidth().intValue()) / 2);
        c();
    }

    public final void setContentY(float f) {
        this.k = ((int) f) - ((getMeasuredHeight() - getScaledContentHeight().intValue()) / 2);
        c();
    }

    /* access modifiers changed from: protected */
    public final float getContentX() {
        return (float) this.j;
    }

    /* access modifiers changed from: protected */
    public final float getContentY() {
        return (float) this.k;
    }

    public Integer getScaledContentWidth() {
        return Integer.valueOf((int) (this.f * this.i * ((float) getMeasuredWidth())));
    }

    public Integer getScaledContentHeight() {
        return Integer.valueOf((int) (this.g * this.i * ((float) getMeasuredHeight())));
    }

    public float getContentScale() {
        return this.i;
    }

    public void setContentScale(float f) {
        String str = a;
        d.d(str, "setContentScale, contentScale " + f);
        this.i = f;
        b();
    }

    /* access modifiers changed from: protected */
    public final void setContentHeight(int i) {
        this.c = Integer.valueOf(i);
    }

    /* access modifiers changed from: protected */
    public final Integer getContentHeight() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public final void setContentWidth(int i) {
        this.b = Integer.valueOf(i);
    }

    /* access modifiers changed from: protected */
    public final Integer getContentWidth() {
        return this.b;
    }
}
