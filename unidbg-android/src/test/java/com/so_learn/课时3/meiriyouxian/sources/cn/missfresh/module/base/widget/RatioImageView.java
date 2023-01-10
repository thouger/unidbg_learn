package cn.missfresh.module.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class RatioImageView extends AppCompatImageView {
    private String a;
    private int b;
    private int c;

    public RatioImageView(Context context) {
        this(context, null);
    }

    public RatioImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(23858, false);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RatioImageView);
            this.a = obtainStyledAttributes.getString(R.styleable.RatioImageView_ratio);
            this.b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RatioImageView_cut_with, 0);
            this.c = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RatioImageView_cut_height, 0);
            obtainStyledAttributes.recycle();
        }
        AppMethodBeat.o(23858);
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(23859, false);
        super.onMeasure(i, i2);
        int size = View.MeasureSpec.getSize(i) - this.b;
        int size2 = View.MeasureSpec.getSize(i);
        if (!TextUtils.isEmpty(this.a) && this.a.contains(":")) {
            String[] split = this.a.split(":");
            if (split.length == 2) {
                size2 = (Integer.parseInt(split[1]) * size) / Integer.parseInt(split[0]);
            }
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2 - this.c, 1073741824));
        AppMethodBeat.o(23859);
    }
}
