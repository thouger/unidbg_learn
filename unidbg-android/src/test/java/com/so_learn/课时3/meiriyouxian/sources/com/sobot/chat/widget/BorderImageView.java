package com.sobot.chat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

public class BorderImageView extends ImageView {
    private int a = Color.parseColor("#dcdcdc");

    public BorderImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect clipBounds = canvas.getClipBounds();
        Paint paint = new Paint();
        paint.setColor(this.a);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(clipBounds, paint);
    }
}
