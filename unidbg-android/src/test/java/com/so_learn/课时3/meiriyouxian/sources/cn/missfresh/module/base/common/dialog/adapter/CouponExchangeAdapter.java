package cn.missfresh.module.base.common.dialog.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.dialog.bean.CouponProduct;
import cn.missfresh.module.base.common.holder.ProductViewHolder;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class CouponExchangeAdapter extends RecyclerView.Adapter<CouponViewHolder> {
    private List<CouponProduct> a;
    private String b = "";
    private Context c;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(11513, false);
        a((CouponViewHolder) viewHolder, i);
        AppMethodBeat.o(11513);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(11514, false);
        CouponViewHolder a = a(viewGroup, i);
        AppMethodBeat.o(11514);
        return a;
    }

    public CouponViewHolder a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(11502, false);
        View inflate = View.inflate(viewGroup.getContext(), R.layout.item_coupon_exchange, null);
        if (this.c == null) {
            this.c = viewGroup.getContext();
        }
        CouponViewHolder couponViewHolder = new CouponViewHolder(inflate, -1);
        AppMethodBeat.o(11502);
        return couponViewHolder;
    }

    public void a(CouponViewHolder couponViewHolder, int i) {
        AppMethodBeat.i(11505, false);
        couponViewHolder.a(i);
        AppMethodBeat.o(11505);
    }

    public void a(List<CouponProduct> list) {
        AppMethodBeat.i(11507, false);
        if (list == null) {
            AppMethodBeat.o(11507);
            return;
        }
        this.a = list;
        notifyDataSetChanged();
        AppMethodBeat.o(11507);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        AppMethodBeat.i(11508, false);
        List<CouponProduct> list = this.a;
        if (list != null) {
            i = list.size();
        }
        AppMethodBeat.o(11508);
        return i;
    }

    public String a() {
        AppMethodBeat.i(11510, false);
        String str = TextUtils.isEmpty(this.b) ? "" : this.b;
        AppMethodBeat.o(11510);
        return str;
    }

    /* access modifiers changed from: package-private */
    public class CouponViewHolder extends ProductViewHolder {
        private ImageView l;

        public CouponViewHolder(View view, int i) {
            super(view, i);
            AppMethodBeat.i(11496, false);
            this.l = (ImageView) view.findViewById(R.id.select_status_img);
            AppMethodBeat.o(11496);
        }

        public void a(int i) {
            AppMethodBeat.i(11498, false);
            CouponProduct couponProduct = (CouponProduct) CouponExchangeAdapter.this.a.get(i);
            if (couponProduct.isSelected) {
                this.l.setImageDrawable(ContextCompat.getDrawable(CouponExchangeAdapter.this.c, R.drawable.img_coupon_selected));
                CouponExchangeAdapter couponExchangeAdapter = CouponExchangeAdapter.this;
                couponExchangeAdapter.b = ((CouponProduct) couponExchangeAdapter.a.get(i)).productsEntity.getSku();
            } else {
                this.l.setImageDrawable(ContextCompat.getDrawable(CouponExchangeAdapter.this.c, R.drawable.img_coupon_unselected));
            }
            if (i() != null) {
                i().setShow(false);
            }
            a(couponProduct.productsEntity);
            AppMethodBeat.o(11498);
        }

        @Override // cn.missfresh.module.base.common.holder.ProductViewHolder, android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(11500, false);
            for (int i = 0; i < CouponExchangeAdapter.this.a.size(); i++) {
                if (getAdapterPosition() == i) {
                    ((CouponProduct) CouponExchangeAdapter.this.a.get(i)).isSelected = true;
                } else {
                    ((CouponProduct) CouponExchangeAdapter.this.a.get(i)).isSelected = false;
                }
            }
            CouponExchangeAdapter.this.notifyDataSetChanged();
            AppMethodBeat.o(11500);
        }
    }
}
