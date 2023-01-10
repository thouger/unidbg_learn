package cn.missfresh.ui.rclayout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;
import com.android.internal.logging.nano.MetricsProto;

/* compiled from: RCHelper */
public class a {
    public float[] a = new float[8];
    public Path b;
    public Paint c;
    public boolean d = false;
    public int e;
    public int f;
    public ColorStateList g;
    public int h;
    public boolean i;
    public Region j;
    public RectF k;

    public a() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_AUTOFILL_NUM_IDS, false);
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_AUTOFILL_NUM_IDS);
    }

    public void a(Context context, AttributeSet attributeSet) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.METRICS_CHECKPOINT, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RCLayoutAttrs);
        this.d = obtainStyledAttributes.getBoolean(R.styleable.RCLayoutAttrs_rcl_round_as_circle, false);
        this.g = obtainStyledAttributes.getColorStateList(R.styleable.RCLayoutAttrs_rcl_stroke_color);
        ColorStateList colorStateList = this.g;
        if (colorStateList != null) {
            this.f = colorStateList.getDefaultColor();
            this.e = this.g.getDefaultColor();
        } else {
            this.f = -1;
            this.e = -1;
        }
        this.h = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RCLayoutAttrs_rcl_stroke_width, 0);
        this.i = obtainStyledAttributes.getBoolean(R.styleable.RCLayoutAttrs_rcl_clip_background, false);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RCLayoutAttrs_rcl_round_corner, 0);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RCLayoutAttrs_rcl_round_corner_top_left, dimensionPixelSize);
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RCLayoutAttrs_rcl_round_corner_top_right, dimensionPixelSize);
        int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RCLayoutAttrs_rcl_round_corner_bottom_left, dimensionPixelSize);
        int dimensionPixelSize5 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RCLayoutAttrs_rcl_round_corner_bottom_right, dimensionPixelSize);
        obtainStyledAttributes.recycle();
        float[] fArr = this.a;
        float f = (float) dimensionPixelSize2;
        fArr[0] = f;
        fArr[1] = f;
        float f2 = (float) dimensionPixelSize3;
        fArr[2] = f2;
        fArr[3] = f2;
        float f3 = (float) dimensionPixelSize5;
        fArr[4] = f3;
        fArr[5] = f3;
        float f4 = (float) dimensionPixelSize4;
        fArr[6] = f4;
        fArr[7] = f4;
        this.k = new RectF();
        this.b = new Path();
        this.j = new Region();
        this.c = new Paint();
        this.c.setColor(-1);
        this.c.setAntiAlias(true);
        AppMethodBeat.o(MetricsProto.MetricsEvent.METRICS_CHECKPOINT);
    }

    public void a(View view, int i, int i2) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_QS_CLICK, false);
        this.k.set(0.0f, 0.0f, (float) i, (float) i2);
        a(view);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_QS_CLICK);
    }

    public void a(View view) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_QS_VALUE, false);
        int width = (int) this.k.width();
        int height = (int) this.k.height();
        RectF rectF = new RectF();
        rectF.left = (float) view.getPaddingLeft();
        rectF.top = (float) view.getPaddingTop();
        rectF.right = (float) (width - view.getPaddingRight());
        rectF.bottom = (float) (height - view.getPaddingBottom());
        this.b.reset();
        if (this.d) {
            float height2 = (rectF.width() >= rectF.height() ? rectF.height() : rectF.width()) / 2.0f;
            PointF pointF = new PointF((float) (width / 2), (float) (height / 2));
            if (Build.VERSION.SDK_INT <= 27) {
                this.b.addCircle(pointF.x, pointF.y, height2, Path.Direction.CW);
                this.b.moveTo(0.0f, 0.0f);
                this.b.moveTo((float) width, (float) height);
            } else {
                float f = (((float) height) / 2.0f) - height2;
                this.b.moveTo(rectF.left, f);
                this.b.addCircle(pointF.x, f + height2, height2, Path.Direction.CW);
            }
        } else {
            this.b.addRoundRect(rectF, this.a, Path.Direction.CW);
        }
        this.j.setPath(this.b, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_QS_VALUE);
    }

    public void a(Canvas canvas) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_NAV_BUTTON_EVENT, false);
        if (this.h > 0) {
            this.c.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            this.c.setColor(-1);
            this.c.setStrokeWidth((float) (this.h * 2));
            this.c.setStyle(Paint.Style.STROKE);
            canvas.drawPath(this.b, this.c);
            this.c.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            this.c.setColor(this.f);
            this.c.setStyle(Paint.Style.STROKE);
            canvas.drawPath(this.b, this.c);
        }
        this.c.setColor(-1);
        this.c.setStyle(Paint.Style.FILL);
        if (Build.VERSION.SDK_INT <= 27) {
            this.c.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawPath(this.b, this.c);
        } else {
            this.c.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            Path path = new Path();
            path.addRect(0.0f, 0.0f, (float) ((int) this.k.width()), (float) ((int) this.k.height()), Path.Direction.CW);
            path.op(this.b, Path.Op.DIFFERENCE);
            canvas.drawPath(path, this.c);
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_NAV_BUTTON_EVENT);
    }
}
