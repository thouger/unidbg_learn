package cn.missfresh.module.base.support;

import android.graphics.Paint;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class AlignTextView extends TextView {
    private float a;
    private float b;
    private int c;
    private List<String> d;
    private List<Integer> e;
    private Align f;
    private boolean g;
    private float h;
    private float i;
    private int j;
    private int k;
    private int l;
    private boolean m;

    public enum Align {
        ALIGN_LEFT,
        ALIGN_CENTER,
        ALIGN_RIGHT;

        public static Align valueOf(String str) {
            AppMethodBeat.i(19845, false);
            Align align = (Align) Enum.valueOf(Align.class, str);
            AppMethodBeat.o(19845);
            return align;
        }

        static {
            AppMethodBeat.i(19847, false);
            AppMethodBeat.o(19847);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        AppMethodBeat.i(19856, false);
        super.onLayout(z, i, i2, i3, i4);
        if (this.g) {
            this.c = getMeasuredWidth();
            String charSequence = getText().toString();
            TextPaint paint = getPaint();
            this.d.clear();
            this.e.clear();
            for (String str : charSequence.split("\\n")) {
                a(paint, str);
            }
            a(charSequence, paint.getTextSize(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
            this.a = (((float) this.j) * 1.0f) / ((float) this.k);
            float f = this.a;
            this.b = ((this.h - 1.0f) * f) + this.i;
            int size = (int) ((this.b + f) * ((float) (this.d.size() - this.k)));
            this.m = true;
            setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), this.l + size);
            this.g = false;
        }
        AppMethodBeat.o(19856);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0096, code lost:
        if (r16.f == cn.missfresh.module.base.support.AlignTextView.Align.ALIGN_RIGHT) goto L_0x0090;
     */
    @Override // android.widget.TextView, android.view.View
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDraw(android.graphics.Canvas r17) {
        /*
        // Method dump skipped, instructions count: 208
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.support.AlignTextView.onDraw(android.graphics.Canvas):void");
    }

    public void setAlign(Align align) {
        AppMethodBeat.i(19863, false);
        this.f = align;
        invalidate();
        AppMethodBeat.o(19863);
    }

    private void a(Paint paint, String str) {
        int i;
        int i2 = 0;
        AppMethodBeat.i(19865, false);
        if (str.length() == 0) {
            this.d.add("\n");
            AppMethodBeat.o(19865);
            return;
        }
        int measureText = (int) (((float) this.c) / paint.measureText("\u4e2d"));
        int i3 = measureText + 1;
        StringBuilder sb = new StringBuilder(str.substring(0, Math.min(i3, str.length())));
        while (true) {
            if (i3 >= str.length()) {
                break;
            }
            if (paint.measureText(str.substring(i2, i3 + 1)) > ((float) this.c)) {
                this.d.add(sb.toString());
                sb = new StringBuilder();
                if (str.length() - i3 <= measureText) {
                    this.d.add(str.substring(i3));
                    break;
                }
                int i4 = i3 + measureText;
                sb.append(str.substring(i3, i4));
                i = i4 - 1;
            } else {
                sb.append(str.charAt(i3));
                i3 = i2;
                i = i3;
            }
            i3 = i + 1;
            i2 = i3;
        }
        if (sb.length() > 0) {
            this.d.add(sb.toString());
        }
        this.e.add(Integer.valueOf(this.d.size() - 1));
        AppMethodBeat.o(19865);
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        AppMethodBeat.i(19868, false);
        this.g = true;
        super.setText(charSequence, bufferType);
        AppMethodBeat.o(19868);
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        AppMethodBeat.i(19871, false);
        if (!this.m) {
            this.l = i4;
        }
        this.m = false;
        super.setPadding(i, i2, i3, i4);
        AppMethodBeat.o(19871);
    }

    private void a(String str, float f, int i) {
        AppMethodBeat.i(19875, false);
        TextView textView = new TextView(getContext());
        textView.setText(str);
        textView.setTextSize(0, f);
        textView.measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.k = textView.getLineCount();
        this.j = textView.getMeasuredHeight();
        AppMethodBeat.o(19875);
    }
}
