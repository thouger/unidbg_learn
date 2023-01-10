package cn.missfresh.picture.internal.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.res.ResourcesCompat;
import cn.missfresh.picture.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class CheckView extends View {
    private boolean a;
    private boolean b;
    private int c;
    private Paint d;
    private Paint e;
    private TextPaint f;
    private Paint g;
    private Drawable h;
    private float i;
    private Rect j;
    private boolean k = true;

    public CheckView(Context context) {
        super(context);
        AppMethodBeat.i(18967, false);
        a(context);
        AppMethodBeat.o(18967);
    }

    public CheckView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(18969, false);
        a(context);
        AppMethodBeat.o(18969);
    }

    public CheckView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(18970, false);
        a(context);
        AppMethodBeat.o(18970);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(18973, false);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (this.i * 36.0f), 1073741824);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
        AppMethodBeat.o(18973);
    }

    private void a(Context context) {
        AppMethodBeat.i(18975, false);
        this.i = context.getResources().getDisplayMetrics().density;
        this.d = new Paint();
        this.d.setAntiAlias(true);
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        this.d.setStrokeWidth(this.i * 1.5f);
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.item_checkCircle_borderColor});
        int color = obtainStyledAttributes.getColor(0, ResourcesCompat.getColor(getResources(), R.color.item_checkCircle_borderColor, getContext().getTheme()));
        obtainStyledAttributes.recycle();
        this.d.setColor(color);
        this.h = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_check_white_18dp, context.getTheme());
        AppMethodBeat.o(18975);
    }

    public void setChecked(boolean z) {
        AppMethodBeat.i(18978, false);
        if (!this.a) {
            this.b = z;
            invalidate();
            AppMethodBeat.o(18978);
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("CheckView is countable, call setCheckedNum() instead.");
        AppMethodBeat.o(18978);
        throw illegalStateException;
    }

    public void setCountable(boolean z) {
        this.a = z;
    }

    public void setCheckedNum(int i) {
        AppMethodBeat.i(18983, false);
        if (!this.a) {
            IllegalStateException illegalStateException = new IllegalStateException("CheckView is not countable, call setChecked() instead.");
            AppMethodBeat.o(18983);
            throw illegalStateException;
        } else if (i == Integer.MIN_VALUE || i > 0) {
            this.c = i;
            invalidate();
            AppMethodBeat.o(18983);
        } else {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("checked num can't be negative.");
            AppMethodBeat.o(18983);
            throw illegalArgumentException;
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        AppMethodBeat.i(18987, false);
        if (this.k != z) {
            this.k = z;
            invalidate();
        }
        AppMethodBeat.o(18987);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        AppMethodBeat.i(18990, false);
        super.onDraw(canvas);
        a();
        float f = this.i;
        canvas.drawCircle((f * 36.0f) / 2.0f, (f * 36.0f) / 2.0f, f * 12.25f, this.g);
        float f2 = this.i;
        canvas.drawCircle((f2 * 36.0f) / 2.0f, (f2 * 36.0f) / 2.0f, f2 * 10.5f, this.d);
        if (this.a) {
            if (this.c != Integer.MIN_VALUE) {
                b();
                float f3 = this.i;
                canvas.drawCircle((f3 * 36.0f) / 2.0f, (36.0f * f3) / 2.0f, f3 * 11.5f, this.e);
                c();
                String valueOf = String.valueOf(this.c);
                canvas.drawText(valueOf, (float) (((int) (((float) canvas.getWidth()) - this.f.measureText(valueOf))) / 2), (float) (((int) ((((float) canvas.getHeight()) - this.f.descent()) - this.f.ascent())) / 2), this.f);
            }
        } else if (this.b) {
            b();
            float f4 = this.i;
            canvas.drawCircle((f4 * 36.0f) / 2.0f, (36.0f * f4) / 2.0f, f4 * 11.5f, this.e);
            this.h.setBounds(getCheckRect());
            this.h.draw(canvas);
        }
        setAlpha(this.k ? 1.0f : 0.5f);
        AppMethodBeat.o(18990);
    }

    private void a() {
        AppMethodBeat.i(18992, false);
        if (this.g == null) {
            this.g = new Paint();
            this.g.setAntiAlias(true);
            Paint paint = this.g;
            float f = this.i;
            paint.setShader(new RadialGradient((f * 36.0f) / 2.0f, (36.0f * f) / 2.0f, 12.25f * f, new int[]{Color.parseColor("#00000000"), Color.parseColor("#0D000000"), Color.parseColor("#0D000000"), Color.parseColor("#00000000")}, new float[]{0.71428573f, 0.79591835f, 0.9183673f, 1.0f}, Shader.TileMode.CLAMP));
        }
        AppMethodBeat.o(18992);
    }

    private void b() {
        AppMethodBeat.i(18993, false);
        if (this.e == null) {
            this.e = new Paint();
            this.e.setAntiAlias(true);
            this.e.setStyle(Paint.Style.FILL);
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{R.attr.item_checkCircle_backgroundColor});
            int color = obtainStyledAttributes.getColor(0, ResourcesCompat.getColor(getResources(), R.color.item_checkCircle_backgroundColor, getContext().getTheme()));
            obtainStyledAttributes.recycle();
            this.e.setColor(color);
        }
        AppMethodBeat.o(18993);
    }

    private void c() {
        AppMethodBeat.i(18995, false);
        if (this.f == null) {
            this.f = new TextPaint();
            this.f.setAntiAlias(true);
            this.f.setColor(-1);
            this.f.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
            this.f.setTextSize(this.i * 12.0f);
        }
        AppMethodBeat.o(18995);
    }

    private Rect getCheckRect() {
        AppMethodBeat.i(18998, false);
        if (this.j == null) {
            float f = this.i;
            int i = (int) (((f * 36.0f) / 2.0f) - ((14.0f * f) / 2.0f));
            float f2 = (float) i;
            this.j = new Rect(i, i, (int) ((f * 36.0f) - f2), (int) ((f * 36.0f) - f2));
        }
        Rect rect = this.j;
        AppMethodBeat.o(18998);
        return rect;
    }
}
