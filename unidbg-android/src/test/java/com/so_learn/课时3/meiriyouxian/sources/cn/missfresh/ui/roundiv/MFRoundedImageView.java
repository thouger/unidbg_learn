package cn.missfresh.ui.roundiv;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.telephony.PreciseDisconnectCause;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;

public class MFRoundedImageView extends AppCompatImageView {
    public static final Shader.TileMode a = Shader.TileMode.CLAMP;
    static final /* synthetic */ boolean b = (!MFRoundedImageView.class.desiredAssertionStatus());
    private static final ImageView.ScaleType[] c = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    private float d;
    private float e;
    private ColorStateList f;
    private boolean g;
    private boolean h;
    private Shader.TileMode i;
    private Shader.TileMode j;
    private int k;
    private Drawable l;
    private Drawable m;
    private ImageView.ScaleType n;

    static {
        AppMethodBeat.i(1384, false);
        AppMethodBeat.o(1384);
    }

    public MFRoundedImageView(Context context) {
        super(context);
        AppMethodBeat.i(1279, false);
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = ColorStateList.valueOf(-16777216);
        this.g = false;
        this.h = false;
        Shader.TileMode tileMode = a;
        this.i = tileMode;
        this.j = tileMode;
        AppMethodBeat.o(1279);
    }

    public MFRoundedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MFRoundedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(1284, false);
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = ColorStateList.valueOf(-16777216);
        this.g = false;
        this.h = false;
        Shader.TileMode tileMode = a;
        this.i = tileMode;
        this.j = tileMode;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MFRoundedImageView, i, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.MFRoundedImageView_android_scaleType, -1);
        if (i2 >= 0) {
            setScaleType(c[i2]);
        } else {
            setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        this.d = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.MFRoundedImageView_riv_corner_radius, -1);
        this.e = (float) obtainStyledAttributes.getDimensionPixelSize(R.styleable.MFRoundedImageView_riv_border_width, -1);
        if (this.d < 0.0f) {
            this.d = 0.0f;
        }
        if (this.e < 0.0f) {
            this.e = 0.0f;
        }
        this.f = obtainStyledAttributes.getColorStateList(R.styleable.MFRoundedImageView_riv_border_color);
        if (this.f == null) {
            this.f = ColorStateList.valueOf(-16777216);
        }
        this.h = obtainStyledAttributes.getBoolean(R.styleable.MFRoundedImageView_riv_mutate_background, false);
        this.g = obtainStyledAttributes.getBoolean(R.styleable.MFRoundedImageView_riv_oval, false);
        int i3 = obtainStyledAttributes.getInt(R.styleable.MFRoundedImageView_riv_tile_mode, -2);
        if (i3 != -2) {
            setTileModeX(a(i3));
            setTileModeY(a(i3));
        }
        int i4 = obtainStyledAttributes.getInt(R.styleable.MFRoundedImageView_riv_tile_mode_x, -2);
        if (i4 != -2) {
            setTileModeX(a(i4));
        }
        int i5 = obtainStyledAttributes.getInt(R.styleable.MFRoundedImageView_riv_tile_mode_y, -2);
        if (i5 != -2) {
            setTileModeY(a(i5));
        }
        b();
        a(true);
        obtainStyledAttributes.recycle();
        AppMethodBeat.o(1284);
    }

    private static Shader.TileMode a(int i) {
        if (i == 0) {
            return Shader.TileMode.CLAMP;
        }
        if (i == 1) {
            return Shader.TileMode.REPEAT;
        }
        if (i != 2) {
            return null;
        }
        return Shader.TileMode.MIRROR;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        AppMethodBeat.i(1290, false);
        super.drawableStateChanged();
        invalidate();
        AppMethodBeat.o(1290);
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.n;
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        AppMethodBeat.i(1297, false);
        if (b || scaleType != null) {
            if (this.n != scaleType) {
                this.n = scaleType;
                switch (scaleType) {
                    case CENTER:
                    case CENTER_CROP:
                    case CENTER_INSIDE:
                    case FIT_CENTER:
                    case FIT_START:
                    case FIT_END:
                    case FIT_XY:
                        super.setScaleType(ImageView.ScaleType.FIT_XY);
                        break;
                    default:
                        super.setScaleType(scaleType);
                        break;
                }
                b();
                a(false);
                invalidate();
            }
            AppMethodBeat.o(1297);
            return;
        }
        AssertionError assertionError = new AssertionError();
        AppMethodBeat.o(1297);
        throw assertionError;
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        AppMethodBeat.i(1301, false);
        this.k = 0;
        this.l = a.a(drawable);
        b();
        super.setImageDrawable(this.l);
        AppMethodBeat.o(1301);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        AppMethodBeat.i(1305, false);
        this.k = 0;
        this.l = a.a(bitmap);
        b();
        super.setImageDrawable(this.l);
        AppMethodBeat.o(1305);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        AppMethodBeat.i(1309, false);
        if (this.k != i) {
            this.k = i;
            this.l = a();
            b();
            super.setImageDrawable(this.l);
        }
        AppMethodBeat.o(1309);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        AppMethodBeat.i(PreciseDisconnectCause.SIP_REQUEST_TIMEOUT, false);
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
        AppMethodBeat.o(PreciseDisconnectCause.SIP_REQUEST_TIMEOUT);
    }

    private Drawable a() {
        AppMethodBeat.i(PreciseDisconnectCause.SIP_BUSY, false);
        Resources resources = getResources();
        Drawable drawable = null;
        if (resources == null) {
            AppMethodBeat.o(PreciseDisconnectCause.SIP_BUSY);
            return null;
        }
        int i = this.k;
        if (i != 0) {
            try {
                drawable = resources.getDrawable(i);
            } catch (Exception unused) {
                this.k = 0;
            }
        }
        Drawable a2 = a.a(drawable);
        AppMethodBeat.o(PreciseDisconnectCause.SIP_BUSY);
        return a2;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        AppMethodBeat.i(PreciseDisconnectCause.SIP_CLIENT_ERROR, false);
        setBackgroundDrawable(drawable);
        AppMethodBeat.o(PreciseDisconnectCause.SIP_CLIENT_ERROR);
    }

    private void b() {
        AppMethodBeat.i(1325, false);
        a(this.l);
        AppMethodBeat.o(1325);
    }

    private void a(boolean z) {
        AppMethodBeat.i(1327, false);
        if (this.h) {
            if (z) {
                this.m = a.a(this.m);
            }
            a(this.m);
        }
        AppMethodBeat.o(1327);
    }

    private void a(Drawable drawable) {
        AppMethodBeat.i(1329, false);
        if (drawable == null) {
            AppMethodBeat.o(1329);
            return;
        }
        if (drawable instanceof a) {
            ((a) drawable).a(this.n).a(this.d).b(this.e).a(this.f).a(this.g).a(this.i).b(this.j);
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                a(layerDrawable.getDrawable(i));
            }
        }
        AppMethodBeat.o(1329);
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.view.View
    @Deprecated
    public void setBackgroundDrawable(Drawable drawable) {
        AppMethodBeat.i(PreciseDisconnectCause.SIP_SERVICE_UNAVAILABLE, false);
        this.m = drawable;
        a(true);
        super.setBackgroundDrawable(this.m);
        AppMethodBeat.o(PreciseDisconnectCause.SIP_SERVICE_UNAVAILABLE);
    }

    public float getCornerRadius() {
        return this.d;
    }

    public void setCornerRadius(int i) {
        AppMethodBeat.i(1334, false);
        setCornerRadius(getResources().getDimension(i));
        AppMethodBeat.o(1334);
    }

    public void setCornerRadius(float f) {
        AppMethodBeat.i(1336, false);
        if (this.d == f) {
            AppMethodBeat.o(1336);
            return;
        }
        this.d = f;
        b();
        a(false);
        AppMethodBeat.o(1336);
    }

    public float getBorderWidth() {
        return this.e;
    }

    public void setBorderWidth(int i) {
        AppMethodBeat.i(PreciseDisconnectCause.EMERGENCY_PERM_FAILURE, false);
        setBorderWidth(getResources().getDimension(i));
        AppMethodBeat.o(PreciseDisconnectCause.EMERGENCY_PERM_FAILURE);
    }

    public void setBorderWidth(float f) {
        AppMethodBeat.i(1347, false);
        if (this.e == f) {
            AppMethodBeat.o(1347);
            return;
        }
        this.e = f;
        b();
        a(false);
        invalidate();
        AppMethodBeat.o(1347);
    }

    public int getBorderColor() {
        AppMethodBeat.i(1350, false);
        int defaultColor = this.f.getDefaultColor();
        AppMethodBeat.o(1350);
        return defaultColor;
    }

    public void setBorderColor(int i) {
        AppMethodBeat.i(1352, false);
        setBorderColor(ColorStateList.valueOf(i));
        AppMethodBeat.o(1352);
    }

    public ColorStateList getBorderColors() {
        return this.f;
    }

    public void setBorderColor(ColorStateList colorStateList) {
        AppMethodBeat.i(1356, false);
        if (this.f.equals(colorStateList)) {
            AppMethodBeat.o(1356);
            return;
        }
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(-16777216);
        }
        this.f = colorStateList;
        b();
        a(false);
        if (this.e > 0.0f) {
            invalidate();
        }
        AppMethodBeat.o(1356);
    }

    public void setOval(boolean z) {
        AppMethodBeat.i(1363, false);
        this.g = z;
        b();
        a(false);
        invalidate();
        AppMethodBeat.o(1363);
    }

    public Shader.TileMode getTileModeX() {
        return this.i;
    }

    public void setTileModeX(Shader.TileMode tileMode) {
        AppMethodBeat.i(1369, false);
        if (this.i == tileMode) {
            AppMethodBeat.o(1369);
            return;
        }
        this.i = tileMode;
        b();
        a(false);
        invalidate();
        AppMethodBeat.o(1369);
    }

    public Shader.TileMode getTileModeY() {
        return this.j;
    }

    public void setTileModeY(Shader.TileMode tileMode) {
        AppMethodBeat.i(1377, false);
        if (this.j == tileMode) {
            AppMethodBeat.o(1377);
            return;
        }
        this.j = tileMode;
        b();
        a(false);
        invalidate();
        AppMethodBeat.o(1377);
    }
}
