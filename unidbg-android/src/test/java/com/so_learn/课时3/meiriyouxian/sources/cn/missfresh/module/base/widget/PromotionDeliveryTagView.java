package cn.missfresh.module.base.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.bean.PromotionDeliveryTag;
import cn.missfresh.module.base.common.interfaces.j;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.util.List;

public class PromotionDeliveryTagView extends FlowLayout implements j {
    private int d;
    private int e;

    public PromotionDeliveryTagView(Context context) {
        this(context, null);
    }

    public PromotionDeliveryTagView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PromotionDeliveryTagView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(23853, false);
        this.d = aw.b(14);
        this.e = aw.b(3);
        b();
        AppMethodBeat.o(23853);
    }

    private void b() {
        AppMethodBeat.i(23854, false);
        setMaxLine(1);
        AppMethodBeat.o(23854);
    }

    /* access modifiers changed from: protected */
    public GradientDrawable a(PromotionDeliveryTag promotionDeliveryTag) {
        AppMethodBeat.i(23855, false);
        if (promotionDeliveryTag == null) {
            AppMethodBeat.o(23855);
            return null;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(1, q.a(promotionDeliveryTag.getBorderColor()));
        gradientDrawable.setCornerRadius((float) aw.b(7));
        AppMethodBeat.o(23855);
        return gradientDrawable;
    }

    @Override // cn.missfresh.module.base.common.interfaces.j
    public void setPromotionTags(List<PromotionDeliveryTag> list) {
        AppMethodBeat.i(23856, false);
        removeAllViews();
        if (list == null || list.isEmpty()) {
            setVisibility(4);
            AppMethodBeat.o(23856);
            return;
        }
        setVisibility(0);
        for (int i = 0; i < list.size(); i++) {
            PromotionDeliveryTag promotionDeliveryTag = list.get(i);
            if (promotionDeliveryTag != null && !b.a(promotionDeliveryTag.getName())) {
                TextView a = a();
                if (i != 0) {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
                    layoutParams.leftMargin = aw.a(getContext(), 3);
                    layoutParams.gravity = 16;
                    a.setLayoutParams(layoutParams);
                }
                a.setText(promotionDeliveryTag.getName());
                a.setTextColor(q.a(promotionDeliveryTag.getNameColor()));
                if (Build.VERSION.SDK_INT >= 16) {
                    a.setBackground(a(promotionDeliveryTag));
                } else {
                    a.setBackgroundDrawable(a(promotionDeliveryTag));
                }
                addView(a);
            }
        }
        AppMethodBeat.o(23856);
    }

    public TextView a() {
        AppMethodBeat.i(23857, false);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, this.d);
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(marginLayoutParams);
        textView.setGravity(17);
        textView.setMaxLines(1);
        int i = this.e;
        textView.setPadding(i, 0, i, 0);
        textView.setTextSize(2, 10.0f);
        AppMethodBeat.o(23857);
        return textView;
    }
}
