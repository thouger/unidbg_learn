package cn.missfresh.module.base.widget.tag;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.widget.tag.adapter.ClassfityTagAdapter;
import cn.missfresh.module.base.widget.tag.bean.TagInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.google.android.flexbox.FlexboxLayoutManager;
import java.util.List;

public class ClassifyPriceLineTag extends RecyclerView {
    private int a;
    private FlexboxLayoutManager b;
    private ClassfityTagAdapter c;
    private Paint d;

    public ClassifyPriceLineTag(Context context) {
        this(context, null);
    }

    public ClassifyPriceLineTag(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(24326, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MissfreshPriceLineTag);
        this.a = obtainStyledAttributes.getInt(R.styleable.MissfreshPriceLineTag_maxLines, 1);
        obtainStyledAttributes.recycle();
        a(context);
        AppMethodBeat.o(24326);
    }

    private void a(Context context) {
        AppMethodBeat.i(24327, false);
        this.d = new Paint();
        this.b = new FlexboxLayoutManager(context);
        this.b.d(1);
        this.b.c(0);
        this.b.f(2);
        this.b.e(0);
        setLayoutManager(this.b);
        this.c = new ClassfityTagAdapter(context);
        setAdapter(this.c);
        AppMethodBeat.o(24327);
    }

    public void setMaxLines(int i) {
        this.a = i;
    }

    public void a(List<TagInfo> list, int i, int i2, boolean z) {
        int i3 = 0;
        AppMethodBeat.i(24328, false);
        this.d.setTextSize((float) i2);
        if (b.a(list)) {
            i3 = z ? 4 : 8;
        }
        setVisibility(i3);
        getLayoutParams().height = this.a * i;
        setLayoutManager(this.b);
        this.c.a(list, i);
        AppMethodBeat.o(24328);
    }
}
