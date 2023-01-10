package cn.missfresh.module.base.oftenbuy.holder;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.listener.a;
import cn.missfresh.module.base.oftenbuy.adapter.BaseProductOftenBuyAdapter;
import cn.missfresh.module.base.oftenbuy.bean.BaseOftenBuyListBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class BaseProductOftenBuyListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView a;
    private TextView b;
    private RecyclerView c;
    private BaseProductOftenBuyAdapter d;
    private BaseOftenBuyListBean e;
    private View f;
    private String g;
    private String h;
    private int i;
    private int j;

    public BaseProductOftenBuyListHolder(View view, a aVar, int i, cn.missfresh.module.base.oftenbuy.b.a aVar2) {
        super(view);
        AppMethodBeat.i(16285, false);
        this.i = i;
        this.a = (TextView) view.findViewById(R.id.title_tv);
        this.b = (TextView) view.findViewById(R.id.sub_title_tv);
        this.c = (RecyclerView) view.findViewById(R.id.product_list);
        this.f = view.findViewById(R.id.v_bg);
        this.d = new BaseProductOftenBuyAdapter(aVar, i, aVar2);
        this.c.setAdapter(this.d);
        this.f.setOnClickListener(this);
        AppMethodBeat.o(16285);
    }

    public void a(BaseOftenBuyListBean baseOftenBuyListBean, int i) {
        AppMethodBeat.i(16286, false);
        this.e = baseOftenBuyListBean;
        this.j = i;
        if (baseOftenBuyListBean != null) {
            this.g = baseOftenBuyListBean.getFavourite_id();
            this.h = baseOftenBuyListBean.getRecommendRequestId();
            this.itemView.setVisibility(0);
            this.a.setText(this.e.getTitle());
            this.b.setText(this.e.getSubTitleString());
            this.d.a(this.e.getProductList(), i, baseOftenBuyListBean.getFavourite_id(), baseOftenBuyListBean.getRecommendRequestId(), this.i);
        } else {
            this.g = "";
            this.h = "";
            this.itemView.setVisibility(8);
        }
        AppMethodBeat.o(16286);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(16287, false);
        if (this.e == null) {
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(16287);
            return;
        }
        com.alibaba.android.arouter.b.a.a().a("/promotion/new_h5event").withString("h5_url", cn.missfresh.module.base.oftenbuy.a.a.a(this.e.getLink(), this.i)).navigation();
        int i = this.i;
        if (1 == i) {
            cn.missfresh.module.base.oftenbuy.c.a.b(this.g, this.h, "", 1, 0, this.j, -1);
        } else if (2 == i) {
            cn.missfresh.module.base.oftenbuy.c.a.a(this.g, this.h, "", 1, 0, this.j, -1);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(16287);
    }
}
