package cn.missfresh.module.base.payment.recharge.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.network.d;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;

public class ThirdPaysLayout extends ConstraintLayout implements View.OnClickListener {
    private final String a = "ThirdPaysLayout";
    private View b;
    private View c;
    private View d;
    private ImageView e;
    private ImageView f;
    private ImageView g;
    private TextView h;
    private a i;

    public interface a {
        void e(String str);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        JniLib.cV(this, view, Integer.valueOf((int) MetricsProto.MetricsEvent.TEXT_LONGPRESS));
    }

    public ThirdPaysLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnSelectPayWayListener(a aVar) {
        this.i = aVar;
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onFinishInflate() {
        AppMethodBeat.i(18572, false);
        super.onFinishInflate();
        this.c = findViewById(R.id.ll_ali_pay_container);
        this.b = findViewById(R.id.ll_weixin_pay_container);
        this.d = findViewById(R.id.v_third_pay_divider);
        this.e = (ImageView) findViewById(R.id.cb_order_pay_wx);
        this.f = (ImageView) findViewById(R.id.cb_order_pay_ali);
        this.g = (ImageView) findViewById(R.id.iv_weixin_pay_notice);
        this.h = (TextView) findViewById(R.id.tv_wixin_pay_desc);
        this.h.setText(e.ab());
        d.d(getContext(), e.ac(), this.g);
        if (b.a(e.ab())) {
            this.h.setVisibility(8);
        }
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        a();
        AppMethodBeat.o(18572);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        if (r6.equals("alipay") != false) goto L_0x002c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setPayWay(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = 18581(0x4895, float:2.6038E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            int r2 = r6.hashCode()
            r3 = -1414960566(0xffffffffaba96a4a, float:-1.2037673E-12)
            r4 = 1
            if (r2 == r3) goto L_0x0021
            r0 = 113584679(0x6c52a27, float:7.41651E-35)
            if (r2 == r0) goto L_0x0016
            goto L_0x002b
        L_0x0016:
            java.lang.String r0 = "wxpay"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x002b
            r0 = r4
            goto L_0x002c
        L_0x0021:
            java.lang.String r2 = "alipay"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L_0x002b
            goto L_0x002c
        L_0x002b:
            r0 = -1
        L_0x002c:
            if (r0 == 0) goto L_0x0036
            if (r0 == r4) goto L_0x0033
            int r6 = cn.missfresh.module.base.R.id.iv_weixin_pay
            goto L_0x0038
        L_0x0033:
            int r6 = cn.missfresh.module.base.R.id.iv_weixin_pay
            goto L_0x0038
        L_0x0036:
            int r6 = cn.missfresh.module.base.R.id.iv_alipay
        L_0x0038:
            r5.a(r6)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.payment.recharge.widget.ThirdPaysLayout.setPayWay(java.lang.String):void");
    }

    private void a(int i) {
        String str;
        AppMethodBeat.i(18584, false);
        if (i == R.id.ll_weixin_pay_container) {
            a();
            str = "wxpay";
        } else if (i == R.id.ll_ali_pay_container) {
            b();
            str = "alipay";
        } else {
            str = null;
        }
        a aVar = this.i;
        if (aVar != null) {
            aVar.e(str);
        }
        AppMethodBeat.o(18584);
    }

    private void a() {
        AppMethodBeat.i(18588, false);
        this.e.setImageResource(R.drawable.ic_address_selected);
        this.f.setImageResource(R.drawable.ic_address_normal);
        AppMethodBeat.o(18588);
    }

    private void b() {
        AppMethodBeat.i(18592, false);
        this.e.setImageResource(R.drawable.ic_address_normal);
        this.f.setImageResource(R.drawable.ic_address_selected);
        AppMethodBeat.o(18592);
    }
}
