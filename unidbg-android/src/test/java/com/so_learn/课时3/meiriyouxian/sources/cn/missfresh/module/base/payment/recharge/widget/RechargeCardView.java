package cn.missfresh.module.base.payment.recharge.widget;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;

public class RechargeCardView extends LinearLayout {
    private final String a;
    private final float b;
    private final float c;
    private final float d;
    private final float e;
    private int f;
    private int g;
    private int h;
    private int i;
    private TextView j;
    private TextView k;

    /* access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        JniLib.cV(this, Boolean.valueOf(z), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_DOWNLOAD_PACKAGE_TASK_MS));
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        JniLib.cV(this, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_INSTALL_PACKAGE_TASK_MS));
    }

    public void setCardBackground(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_CANCELLED));
    }

    public void setRechargeCardDescription(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_ERROR));
    }

    public RechargeCardView(Context context) {
        this(context, null);
    }

    public RechargeCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RechargeCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(18497, false);
        this.a = getClass().getSimpleName();
        this.b = 0.105f;
        this.c = 0.41f;
        this.d = 0.69f;
        this.e = 0.64f;
        a();
        AppMethodBeat.o(18497);
    }

    private void a() {
        AppMethodBeat.i(18501, false);
        setBackgroundResource(R.drawable.bg_recharge_card_big);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        a(layoutParams);
        b(layoutParams);
        AppMethodBeat.o(18501);
    }

    public void setPrice(int i) {
        AppMethodBeat.i(18510, false);
        String str = at.c(i) + getResources().getString(R.string.yuan);
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(40, true), 0, str.length() - 1, 34);
        spannableString.setSpan(new AbsoluteSizeSpan(20, true), str.length() - 1, str.length(), 34);
        this.j.setText(spannableString);
        requestLayout();
        AppMethodBeat.o(18510);
    }

    private void a(ViewGroup.LayoutParams layoutParams) {
        AppMethodBeat.i(18523, false);
        this.j = new TextView(getContext());
        this.j.setSingleLine(true);
        this.j.setTextColor(getResources().getColor(R.color.gray_26));
        this.j.setLayoutParams(layoutParams);
        addView(this.j);
        AppMethodBeat.o(18523);
    }

    private void b(ViewGroup.LayoutParams layoutParams) {
        AppMethodBeat.i(18525, false);
        this.k = new TextView(getContext());
        this.k.setTextSize(11.0f);
        this.k.setMaxWidth(aw.b(235));
        this.k.setTextColor(getResources().getColor(R.color.gray_26));
        this.k.setLayoutParams(layoutParams);
        addView(this.k);
        AppMethodBeat.o(18525);
    }
}
