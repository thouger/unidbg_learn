package cn.missfresh.module.base.widget.tag;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.widget.tag.adapter.MissfreshTagAdapter;
import cn.missfresh.module.base.widget.tag.bean.TagInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.google.android.flexbox.FlexboxLayoutManager;
import java.util.Iterator;
import java.util.List;

public class MissfreshPriceLineTag extends RecyclerView {
    private int a;
    private FlexboxLayoutManager b;
    private MissfreshTagAdapter c;
    private Paint d;

    public MissfreshPriceLineTag(Context context) {
        this(context, null);
    }

    public MissfreshPriceLineTag(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(24329, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MissfreshPriceLineTag);
        this.a = obtainStyledAttributes.getInt(R.styleable.MissfreshPriceLineTag_maxLines, 1);
        obtainStyledAttributes.recycle();
        a(context);
        AppMethodBeat.o(24329);
    }

    private void a(Context context) {
        AppMethodBeat.i(24330, false);
        this.d = new Paint();
        this.b = new FlexboxLayoutManager(context);
        this.b.d(1);
        this.b.c(0);
        this.b.f(2);
        this.b.e(0);
        setLayoutManager(this.b);
        this.c = new MissfreshTagAdapter(context);
        setAdapter(this.c);
        AppMethodBeat.o(24330);
    }

    public void setMaxLines(int i) {
        this.a = i;
    }

    public void a(List<TagInfo> list, int i, int i2, boolean z) {
        int i3 = 0;
        AppMethodBeat.i(24331, false);
        this.d.setTextSize((float) i2);
        if (a(list)) {
            i3 = z ? 4 : 8;
        }
        setVisibility(i3);
        getLayoutParams().height = this.a * i;
        setLayoutManager(this.b);
        this.c.a(list, i);
        AppMethodBeat.o(24331);
    }

    private boolean a(List<TagInfo> list) {
        boolean z = false;
        AppMethodBeat.i(24332, false);
        if (b.a(list)) {
            AppMethodBeat.o(24332);
            return true;
        }
        Iterator<TagInfo> it2 = list.iterator();
        while (true) {
            if (it2.hasNext()) {
                if (!TextUtils.isEmpty(it2.next().getTagName())) {
                    break;
                }
            } else {
                z = true;
                break;
            }
        }
        AppMethodBeat.o(24332);
        return z;
    }
}
