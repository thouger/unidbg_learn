package cn.missfresh.module.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class UnuseCouponLayout extends LinearLayout {
    private CheckBox a;
    private View.OnClickListener b;
    private TextView c;
    private TextView d;
    private TextView e;
    private View f;

    public UnuseCouponLayout(Context context) {
        super(context);
    }

    public UnuseCouponLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public UnuseCouponLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onFinishInflate() {
        AppMethodBeat.i(23882, false);
        super.onFinishInflate();
        this.a = (CheckBox) findViewById(R.id.unuseCouponCheckBox);
        this.a.setChecked(false);
        ((ViewGroup) this.a.getParent()).setOnClickListener(new AnonymousClass1());
        this.c = (TextView) findViewById(R.id.tvUnuseCoupon);
        this.d = (TextView) findViewById(R.id.tvRemainCoupon);
        this.e = (TextView) findViewById(R.id.tvCouponExplain);
        this.f = findViewById(R.id.llCouponExplainContainer);
        AppMethodBeat.o(23882);
    }

    /* renamed from: cn.missfresh.module.base.widget.UnuseCouponLayout$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(23881, false);
            if (UnuseCouponLayout.this.b != null) {
                UnuseCouponLayout.this.b.onClick(view);
            }
            UnuseCouponLayout.this.a.setChecked(true);
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(23881);
        }
    }

    public void setUnuseCouponContainerVisibility(int i) {
        AppMethodBeat.i(23885, false);
        setVisibility(i);
        findViewById(R.id.unuseCouponContainer).setVisibility(i);
        AppMethodBeat.o(23885);
    }

    public void setUnuseCouponContent(String str) {
        AppMethodBeat.i(23886, false);
        this.c.setText(str);
        AppMethodBeat.o(23886);
    }

    public void setShowCouponExplainContainer(boolean z) {
        AppMethodBeat.i(23890, false);
        if (z) {
            findViewById(R.id.tvRemainCoupon).setVisibility(0);
        } else {
            findViewById(R.id.tvRemainCoupon).setVisibility(8);
        }
        AppMethodBeat.o(23890);
    }

    public void setOnUnuseButtonClickListenter(View.OnClickListener onClickListener) {
        this.b = onClickListener;
    }
}
