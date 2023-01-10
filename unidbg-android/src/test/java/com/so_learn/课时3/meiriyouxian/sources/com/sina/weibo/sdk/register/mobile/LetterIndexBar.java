package com.sina.weibo.sdk.register.mobile;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.format.DateFormat;
import android.view.View;
import com.sina.weibo.sdk.a.g;

public class LetterIndexBar extends View {
    private int a;
    private Paint b = new Paint();
    private String[] c;
    private boolean[] d;
    private int e = 27;
    private int f;
    private a g;
    private int h;
    private boolean i;
    private RectF j;
    private int k;
    private Drawable l;

    public interface a {
        void a(int i);
    }

    public LetterIndexBar(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.b.setAntiAlias(true);
        this.b.setStyle(Paint.Style.FILL);
        this.b.setColor(-10658467);
        this.k = g.a(getContext(), 13);
    }

    public void setIndexMark(boolean[] zArr) {
        if (zArr != null) {
            this.d = zArr;
            invalidate();
        }
    }

    public void setIndexLetter(String[] strArr) {
        if (strArr != null) {
            this.c = strArr;
            this.e = this.c.length;
            this.f = -1;
            invalidate();
        }
    }

    public void setIndexChangeListener(a aVar) {
        this.g = aVar;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        char c;
        String str;
        super.onDraw(canvas);
        if (this.i) {
            int color = this.b.getColor();
            this.b.setColor(-2005436536);
            canvas.drawRoundRect(this.j, (float) (getMeasuredWidth() / 2), (float) (getMeasuredWidth() / 2), this.b);
            this.b.setColor(color);
        }
        int i = this.k;
        int i2 = this.a;
        if (i > i2) {
            i = i2;
        }
        this.b.setTextSize((float) i);
        int i3 = 0;
        if (this.c == null) {
            char c2 = DateFormat.CAPITAL_AM_PM;
            while (i3 < this.e) {
                int paddingTop = (this.a * i3) + getPaddingTop() + i + this.h;
                boolean[] zArr = this.d;
                if (zArr == null || zArr[i3]) {
                    if (i3 == this.e - 1) {
                        c = c2;
                        str = "#";
                    } else {
                        c = (char) (c2 + 1);
                        str = String.valueOf(c2);
                    }
                    canvas.drawText(str, (float) ((getMeasuredWidth() - ((int) this.b.measureText(str))) / 2), (float) paddingTop, this.b);
                    c2 = c;
                }
                i3++;
            }
            return;
        }
        while (i3 < this.e) {
            int paddingTop2 = (this.a * i3) + getPaddingTop() + i + this.h;
            boolean[] zArr2 = this.d;
            if (zArr2 == null || zArr2[i3]) {
                String str2 = this.c[i3];
                if (str2.equals("")) {
                    int measureText = (int) this.b.measureText("M");
                    int measuredWidth = (getMeasuredWidth() - measureText) / 2;
                    this.l.setBounds(measuredWidth, paddingTop2 - measuredWidth, measureText + measuredWidth, (measureText + paddingTop2) - measuredWidth);
                    this.l.draw(canvas);
                } else {
                    canvas.drawText(str2, (float) ((getMeasuredWidth() - ((int) this.b.measureText(str2))) / 2), (float) paddingTop2, this.b);
                }
            }
            i3++;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        this.a = ((size - getPaddingTop()) - getPaddingBottom()) / this.e;
        this.h = (int) ((((float) this.a) - this.b.getTextSize()) / 2.0f);
        setMeasuredDimension(this.k + getPaddingLeft() + getPaddingRight(), i2);
        this.j = new RectF(0.0f, (float) getPaddingTop(), (float) getMeasuredWidth(), (float) ((size - getPaddingTop()) - getPaddingBottom()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        if (r0 != 4) goto L_0x0043;
     */
    @Override // android.view.View
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getAction()
            r1 = 1
            if (r0 == 0) goto L_0x0017
            if (r0 == r1) goto L_0x0013
            r2 = 2
            if (r0 == r2) goto L_0x0017
            r4 = 3
            if (r0 == r4) goto L_0x0013
            r4 = 4
            if (r0 == r4) goto L_0x0013
            goto L_0x0043
        L_0x0013:
            r4 = 0
            r3.i = r4
            goto L_0x0043
        L_0x0017:
            r3.i = r1
            float r4 = r4.getY()
            int r4 = (int) r4
            int r0 = r3.getPaddingTop()
            int r4 = r4 - r0
            int r0 = r3.a
            int r4 = r4 / r0
            int r0 = r3.f
            if (r4 == r0) goto L_0x0043
            boolean[] r0 = r3.d
            if (r0 == 0) goto L_0x0032
            boolean r0 = r0[r4]
            if (r0 == 0) goto L_0x0043
        L_0x0032:
            int r0 = r3.e
            if (r4 >= r0) goto L_0x0043
            if (r4 < 0) goto L_0x0043
            r3.f = r4
            com.sina.weibo.sdk.register.mobile.LetterIndexBar$a r4 = r3.g
            if (r4 == 0) goto L_0x0043
            int r0 = r3.f
            r4.a(r0)
        L_0x0043:
            r3.invalidate()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.register.mobile.LetterIndexBar.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
