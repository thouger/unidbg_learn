package cn.missfresh.module.base.payment.recharge.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.payment.recharge.bean.StoreCardBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;

/* compiled from: RechargeHolderView */
public class a implements cn.missfresh.module.base.widget.banner.view.a<StoreCardBean> {
    private RechargeCardView a;

    public a() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_UNKNOWN));
    }

    public void a(Context context, int i, StoreCardBean storeCardBean) {
        JniLib.cV(this, context, Integer.valueOf(i), storeCardBean, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_UNKNOWN));
    }

    @Override // cn.missfresh.module.base.widget.banner.view.a
    public /* synthetic */ void a(Context context, int i, Object obj) {
        AppMethodBeat.i(18532, false);
        a(context, i, (StoreCardBean) obj);
        AppMethodBeat.o(18532);
    }

    @Override // cn.missfresh.module.base.widget.banner.view.a
    public View a(Context context) {
        AppMethodBeat.i(18530, false);
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recharge_card, (ViewGroup) null);
        this.a = (RechargeCardView) inflate.findViewById(R.id.cv_recharge_card);
        AppMethodBeat.o(18530);
        return inflate;
    }
}
