package com.sobot.chat.widget.image;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Checkable;
import android.widget.ImageView;
import com.sobot.chat.widget.image.b;

public class SobotRCImageView extends ImageView implements Checkable, a {
    b a;

    public SobotRCImageView(Context context) {
        this(context, null);
    }

    public SobotRCImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SobotRCImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new b();
        this.a.a(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.a.a(this, i, i2);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (this.a.i) {
            canvas.save();
            canvas.clipPath(this.a.b);
            super.draw(canvas);
            canvas.restore();
            return;
        }
        super.draw(canvas);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        canvas.saveLayer(this.a.k, null, 31);
        super.onDraw(canvas);
        this.a.a(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0 && !this.a.j.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return false;
        }
        if (action == 0 || action == 1) {
            refreshDrawableState();
        } else if (action == 3) {
            setPressed(false);
            refreshDrawableState();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setClipBackground(boolean z) {
        this.a.i = z;
        invalidate();
    }

    public void setRoundAsCircle(boolean z) {
        this.a.d = z;
        invalidate();
    }

    public void setRadius(int i) {
        for (int i2 = 0; i2 < this.a.a.length; i2++) {
            this.a.a[i2] = (float) i;
        }
        invalidate();
    }

    public void setTopLeftRadius(int i) {
        float f = (float) i;
        this.a.a[0] = f;
        this.a.a[1] = f;
        invalidate();
    }

    public void setTopRightRadius(int i) {
        float f = (float) i;
        this.a.a[2] = f;
        this.a.a[3] = f;
        invalidate();
    }

    public void setBottomLeftRadius(int i) {
        float f = (float) i;
        this.a.a[6] = f;
        this.a.a[7] = f;
        invalidate();
    }

    public void setBottomRightRadius(int i) {
        float f = (float) i;
        this.a.a[4] = f;
        this.a.a[5] = f;
        invalidate();
    }

    public void setStrokeWidth(int i) {
        this.a.h = i;
        invalidate();
    }

    @Override // com.sobot.chat.widget.image.a
    public void setStrokeColor(int i) {
        this.a.f = i;
        invalidate();
    }

    @Override // android.view.View
    public void invalidate() {
        b bVar = this.a;
        if (bVar != null) {
            bVar.a(this);
        }
        super.invalidate();
    }

    public float getTopLeftRadius() {
        return this.a.a[0];
    }

    public float getTopRightRadius() {
        return this.a.a[2];
    }

    public float getBottomLeftRadius() {
        return this.a.a[4];
    }

    public float getBottomRightRadius() {
        return this.a.a[6];
    }

    public int getStrokeWidth() {
        return this.a.h;
    }

    public int getStrokeColor() {
        return this.a.f;
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.a.b(this);
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.a.l != z) {
            this.a.l = z;
            refreshDrawableState();
            if (this.a.m != null) {
                this.a.m.a(this, this.a.l);
            }
        }
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.a.l;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.a.l);
    }

    public void setOnCheckedChangeListener(b.a aVar) {
        this.a.m = aVar;
    }
}
