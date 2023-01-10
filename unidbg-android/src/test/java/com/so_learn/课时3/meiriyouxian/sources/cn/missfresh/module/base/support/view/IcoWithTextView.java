package cn.missfresh.module.base.support.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.OrderAfterPayBean;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.util.List;

public class IcoWithTextView extends LinearLayout {
    public IcoWithTextView(Context context) {
        this(context, null);
    }

    public IcoWithTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(22678, false);
        setOrientation(1);
        AppMethodBeat.o(22678);
    }

    public void setIntrolData(List<OrderAfterPayBean.VipIntrol> list) {
        AppMethodBeat.i(22679, false);
        removeAllViews();
        if (b.a(list)) {
            setVisibility(8);
        } else {
            setVisibility(0);
            for (OrderAfterPayBean.VipIntrol vipIntrol : list) {
                addView(a(vipIntrol));
            }
        }
        AppMethodBeat.o(22679);
    }

    private View a(OrderAfterPayBean.VipIntrol vipIntrol) {
        AppMethodBeat.i(22681, false);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(19);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, aw.b(6), 0, 0);
        linearLayout.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(aw.b(17), aw.b(17));
        layoutParams2.setMargins(0, 0, aw.b(2), 0);
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(layoutParams2);
        TextView textView = new TextView(getContext());
        textView.setGravity(1);
        textView.setMaxLines(1);
        textView.setTextColor(getResources().getColor(R.color.yellow_CB9958));
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setSingleLine();
        textView.setTextSize(2, 12.0f);
        linearLayout.addView(imageView);
        linearLayout.addView(textView);
        d.e(getContext(), vipIntrol.img, imageView);
        textView.setText(vipIntrol.text);
        aw.a(textView, vipIntrol.text, getResources().getColor(R.color.color_ff4891), new String[]{"\u3010", "\u3011"}, true);
        AppMethodBeat.o(22681);
        return linearLayout;
    }
}
