package cn.missfresh.picture.internal.ui.widget;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.appcompat.widget.AppCompatImageView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class RoundedRectangleImageView extends AppCompatImageView {
    private float a;
    private Path b;
    private RectF c;

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(19062, false);
        super.onMeasure(i, i2);
        this.c.set(0.0f, 0.0f, (float) getMeasuredWidth(), (float) getMeasuredHeight());
        Path path = this.b;
        RectF rectF = this.c;
        float f = this.a;
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
        AppMethodBeat.o(19062);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        AppMethodBeat.i(19063, false);
        canvas.clipPath(this.b);
        super.onDraw(canvas);
        AppMethodBeat.o(19063);
    }
}
