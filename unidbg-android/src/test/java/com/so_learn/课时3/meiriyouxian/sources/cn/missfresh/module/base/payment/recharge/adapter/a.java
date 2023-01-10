package cn.missfresh.module.base.payment.recharge.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.payment.recharge.bean.BillingAccount;
import cn.missfresh.module.base.widget.PriceTextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;

/* compiled from: BalanceBillingRecordAdapter */
public class a extends BaseAdapter {
    private final String a = getClass().getSimpleName();
    private cn.missfresh.module.base.payment.recharge.b.a b;
    private LayoutInflater c;

    /* compiled from: BalanceBillingRecordAdapter */
    /* renamed from: cn.missfresh.module.base.payment.recharge.adapter.a$a  reason: collision with other inner class name */
    private class C0028a {
        final /* synthetic */ a a;
        private View b;
        private TextView c;
        private PriceTextView d;
        private TextView e;
        private TextView f;
        private TextView g;

        private C0028a(a aVar) {
            JniLib.cV(this, aVar, 326);
        }
    }

    public void a() {
        JniLib.cV(this, 327);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return JniLib.cI(this, 328);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return JniLib.cL(this, Integer.valueOf(i), 329);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return JniLib.cJ(this, Integer.valueOf(i), 330);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        return JniLib.cI(this, Integer.valueOf(i), 331);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return JniLib.cI(this, 332);
    }

    public a(Context context, cn.missfresh.module.base.payment.recharge.b.a aVar) {
        AppMethodBeat.i(16766, false);
        this.c = LayoutInflater.from(context);
        this.b = aVar;
        AppMethodBeat.o(16766);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        C0028a aVar;
        int i2 = 0;
        AppMethodBeat.i(16780, false);
        int itemViewType = getItemViewType(i);
        if (itemViewType == 1) {
            if (view == null) {
                view = this.c.inflate(R.layout.item_billing_accounts, viewGroup, false);
                aVar = new C0028a();
                aVar.b = view.findViewById(R.id.v_balance_divider);
                aVar.c = (TextView) view.findViewById(R.id.tv_billing_type_name);
                aVar.d = (PriceTextView) view.findViewById(R.id.tv_billing_amount);
                aVar.e = (TextView) view.findViewById(R.id.tv_billing_date);
                aVar.f = (TextView) view.findViewById(R.id.tv_billing_trade_no);
                aVar.g = (TextView) view.findViewById(R.id.tv_billing_type_flag);
                view.setTag(aVar);
            } else {
                aVar = (C0028a) view.getTag();
            }
            BillingAccount.ResultsEntity resultsEntity = this.b.a().results.get(i);
            View view2 = aVar.b;
            if (i != 0) {
                i2 = 8;
            }
            view2.setVisibility(i2);
            aVar.c.setText(resultsEntity.change_type_name);
            aVar.d.setPriceWithRMB(resultsEntity.amount);
            aVar.e.setText(resultsEntity.data);
            aVar.f.setText(resultsEntity.trade_no);
            aVar.g.setText(resultsEntity.seq_flag == 0 ? "+ " : "- ");
        } else if (itemViewType == 2) {
            view = this.c.inflate(R.layout.layout_billing_account_empty, viewGroup, false);
        }
        AppMethodBeat.o(16780);
        return view;
    }
}
