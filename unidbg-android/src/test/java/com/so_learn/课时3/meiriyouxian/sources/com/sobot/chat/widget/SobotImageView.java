package com.sobot.chat.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

public class SobotImageView extends ImageView {
    private int a;
    private int b;
    private boolean c;
    private boolean d;
    private int e;
    private int f;
    private RectF g;
    private Paint h;
    private a i;

    public interface a {
        void a(Drawable drawable);
    }

    public SobotImageView(Context context) {
        this(context, null);
    }

    public SobotImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SobotImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 0;
        this.c = false;
        this.d = false;
        this.e = 0;
        this.f = -1;
        a(context, attributeSet);
        a();
        b();
        this.g = new RectF();
    }

    private void a() {
        this.h = new Paint();
        this.h.setAntiAlias(true);
        this.h.setStyle(Paint.Style.STROKE);
        this.h.setColor(this.f);
        this.h.setStrokeWidth((float) this.e);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.a = 0;
    }

    private int a(float f) {
        return (int) ((f * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setCornerRadius(int i) {
        this.b = a((float) i);
        invalidate();
    }

    public void setIsCircle(boolean z) {
        this.c = z;
        invalidate();
    }

    private void b() {
        int i = this.a;
        if (i != 0) {
            setImageResource(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        setImageDrawable(getResources().getDrawable(i));
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        boolean z = drawable instanceof BitmapDrawable;
        if (z && this.b > 0) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                super.setImageDrawable(a(getContext(), bitmap, (float) this.b));
            } else {
                super.setImageDrawable(drawable);
            }
        } else if (!z || !this.c) {
            super.setImageDrawable(drawable);
        } else {
            Bitmap bitmap2 = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap2 != null) {
                super.setImageDrawable(a(getContext(), bitmap2));
            } else {
                super.setImageDrawable(drawable);
            }
        }
        a(drawable);
    }

    private void a(Drawable drawable) {
        a aVar = this.i;
        if (aVar != null) {
            aVar.a(drawable);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        if (this.c || this.d) {
            setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
            i = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
            i2 = i;
        }
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
            canvas.save();
            canvas.restore();
            if (this.e <= 0) {
                return;
            }
            if (this.c) {
                canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) ((getWidth() / 2) - (this.e / 2)), this.h);
                return;
            }
            this.g.left = 0.0f;
            this.g.top = 0.0f;
            this.g.right = (float) getWidth();
            this.g.bottom = (float) getHeight();
            canvas.drawRoundRect(this.g, (float) this.b, (float) this.b, this.h);
        } catch (Exception unused) {
        }
    }

    public void setDrawableChangedCallback(a aVar) {
        this.i = aVar;
    }

    public static RoundedBitmapDrawable a(Context context, Bitmap bitmap) {
        Bitmap bitmap2;
        if (bitmap.getWidth() >= bitmap.getHeight()) {
            bitmap2 = Bitmap.createBitmap(bitmap, (bitmap.getWidth() / 2) - (bitmap.getHeight() / 2), 0, bitmap.getHeight(), bitmap.getHeight());
        } else {
            bitmap2 = Bitmap.createBitmap(bitmap, 0, (bitmap.getHeight() / 2) - (bitmap.getWidth() / 2), bitmap.getWidth(), bitmap.getWidth());
        }
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap2);
        create.setAntiAlias(true);
        create.setCornerRadius(((float) Math.min(bitmap2.getWidth(), bitmap2.getHeight())) / 2.0f);
        return create;
    }

    public static RoundedBitmapDrawable a(Context context, Bitmap bitmap, float f) {
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        create.setAntiAlias(true);
        create.setCornerRadius(f);
        return create;
    }
}
