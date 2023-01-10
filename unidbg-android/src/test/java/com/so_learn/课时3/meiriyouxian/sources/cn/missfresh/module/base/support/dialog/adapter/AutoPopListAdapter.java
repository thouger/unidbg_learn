package cn.missfresh.module.base.support.dialog.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.lib.image.c;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.AutoPopPicBean;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class AutoPopListAdapter extends RecyclerView.Adapter<a> {
    private List<AutoPopPicBean.VoucherInfoBean> a;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(21894, false);
        a((a) viewHolder, i);
        AppMethodBeat.o(21894);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(21896, false);
        a a2 = a(viewGroup, i);
        AppMethodBeat.o(21896);
        return a2;
    }

    public AutoPopListAdapter(List<AutoPopPicBean.VoucherInfoBean> list) {
        AppMethodBeat.i(21888, false);
        this.a = list;
        notifyDataSetChanged();
        AppMethodBeat.o(21888);
    }

    public a a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(21890, false);
        a aVar = new a(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_voucher_auto_pop, viewGroup, false));
        AppMethodBeat.o(21890);
        return aVar;
    }

    public void a(a aVar, int i) {
        AppMethodBeat.i(21891, false);
        aVar.a(this.a.get(i));
        AppMethodBeat.o(21891);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        AppMethodBeat.i(21892, false);
        List<AutoPopPicBean.VoucherInfoBean> list = this.a;
        if (list != null) {
            i = list.size();
        }
        AppMethodBeat.o(21892);
        return i;
    }

    /* access modifiers changed from: package-private */
    public static class a extends RecyclerView.ViewHolder {
        private ImageView a;
        private LinearLayout b;
        private TextView c;
        private TextView d;
        private TextView e;
        private TextView f;

        public a(View view) {
            super(view);
            AppMethodBeat.i(21885, false);
            this.a = (ImageView) view.findViewById(R.id.iv_product_item_autoPop);
            this.b = (LinearLayout) view.findViewById(R.id.layout_packet_item_autoPop);
            this.c = (TextView) view.findViewById(R.id.tv_price_item_autoPop);
            this.d = (TextView) view.findViewById(R.id.tv_useLimit_item_autoPop);
            this.e = (TextView) view.findViewById(R.id.tv_timeLimit_item_autoPop);
            this.f = (TextView) view.findViewById(R.id.tv_price_item_txt);
            AppMethodBeat.o(21885);
        }

        public void a(AutoPopPicBean.VoucherInfoBean voucherInfoBean) {
            AppMethodBeat.i(21886, false);
            if (voucherInfoBean == null) {
                this.itemView.setVisibility(8);
                AppMethodBeat.o(21886);
                return;
            }
            this.itemView.setVisibility(0);
            if ("packet".equalsIgnoreCase(voucherInfoBean.getVoucherType()) || "postage".equalsIgnoreCase(voucherInfoBean.getVoucherType())) {
                this.a.setVisibility(8);
                this.b.setVisibility(0);
                this.c.setText(at.b(voucherInfoBean.getPrice()));
                if (TextUtils.isEmpty(voucherInfoBean.getEquityTag())) {
                    this.f.setVisibility(8);
                } else {
                    this.f.setVisibility(0);
                    this.f.setText(voucherInfoBean.getEquityTag());
                }
            } else {
                this.a.setVisibility(0);
                this.b.setVisibility(8);
                c.a(this.a).a(voucherInfoBean.getProductImg()).a(this.a);
            }
            this.d.setText(voucherInfoBean.getUseLimit());
            this.e.setText(voucherInfoBean.getTimeLimit());
            AppMethodBeat.o(21886);
        }
    }
}
