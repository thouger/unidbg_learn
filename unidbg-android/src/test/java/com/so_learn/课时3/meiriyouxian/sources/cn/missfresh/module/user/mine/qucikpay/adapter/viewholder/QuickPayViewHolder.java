package cn.missfresh.module.user.mine.qucikpay.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.lib.image.c;
import cn.missfresh.module.order.orderdetails_v2.bean.QuickPayListItemBean;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.mine.qucikpay.viewmodel.QuickPayViewModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class QuickPayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final String a = "0";
    private final String b = "1";
    private final String c = "2";
    private ImageView d;
    private TextView e;
    private TextView f;
    private QuickPayViewModel g;
    private QuickPayListItemBean h;

    public QuickPayViewHolder(View view) {
        super(view);
        AppMethodBeat.i(9017, false);
        this.d = (ImageView) view.findViewById(R.id.user_pai_icon);
        this.e = (TextView) view.findViewById(R.id.user_tv_pay_name);
        this.f = (TextView) view.findViewById(R.id.user_tv_open);
        this.g = (QuickPayViewModel) ViewModelProviders.of((FragmentActivity) view.getContext()).get(QuickPayViewModel.class);
        this.f.setOnClickListener(this);
        AppMethodBeat.o(9017);
    }

    public void a(QuickPayListItemBean quickPayListItemBean) {
        AppMethodBeat.i(9020, false);
        this.h = quickPayListItemBean;
        if (quickPayListItemBean == null) {
            this.itemView.setVisibility(8);
            AppMethodBeat.o(9020);
            return;
        }
        this.itemView.setVisibility(0);
        c.a(this.itemView).a(quickPayListItemBean.getPicUrl()).a(this.d);
        this.e.setText(quickPayListItemBean.getName());
        if ("1".equals(quickPayListItemBean.getContractStatus())) {
            this.f.setText("\u5df2\u5f00\u901a");
        } else {
            this.f.setText("\u672a\u5f00\u901a");
        }
        AppMethodBeat.o(9020);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(9023, false);
        if (this.g == null || this.h == null) {
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(9023);
            return;
        }
        if (view.getId() == R.id.user_tv_open) {
            if (!"1".equals(this.h.getContractStatus())) {
                this.g.a(this.h.getPayType());
            } else {
                this.g.b(this.h.getPayType());
            }
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(9023);
    }
}
