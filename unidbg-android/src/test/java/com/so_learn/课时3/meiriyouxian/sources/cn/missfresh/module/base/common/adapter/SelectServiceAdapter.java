package cn.missfresh.module.base.common.adapter;

import android.content.Context;
import android.os.health.UidHealthStats;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class SelectServiceAdapter extends RecyclerView.Adapter<b> {
    a a;
    private Context b;
    private List<ProductsEntity.SpuInfoBean.ServiceListBean.SubListBeanX> c;

    public interface a {
        void a(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(10075, false);
        a((b) viewHolder, i);
        AppMethodBeat.o(10075);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(10079, false);
        b a2 = a(viewGroup, i);
        AppMethodBeat.o(10079);
        return a2;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public SelectServiceAdapter(Context context, List<ProductsEntity.SpuInfoBean.ServiceListBean.SubListBeanX> list) {
        this.b = context;
        this.c = list;
    }

    public List<ProductsEntity.SpuInfoBean.ServiceListBean.SubListBeanX> a() {
        return this.c;
    }

    public b a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(10069, false);
        b bVar = new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_select_sku_item, viewGroup, false));
        AppMethodBeat.o(10069);
        return bVar;
    }

    public void a(b bVar, int i) {
        AppMethodBeat.i(10070, false);
        bVar.a(this.c.get(i));
        bVar.a(i);
        AppMethodBeat.o(10070);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        AppMethodBeat.i(10072, false);
        int size = this.c.size();
        AppMethodBeat.o(10072);
        return size;
    }

    /* access modifiers changed from: package-private */
    public class b extends RecyclerView.ViewHolder {
        private TextView b;

        public b(View view) {
            super(view);
            AppMethodBeat.i(UidHealthStats.MEASUREMENT_BLUETOOTH_TX_BYTES, false);
            this.b = (TextView) view.findViewById(R.id.tv);
            AppMethodBeat.o(UidHealthStats.MEASUREMENT_BLUETOOTH_TX_BYTES);
        }

        public void a(ProductsEntity.SpuInfoBean.ServiceListBean.SubListBeanX subListBeanX) {
            AppMethodBeat.i(UidHealthStats.MEASUREMENT_WIFI_TX_PACKETS, false);
            this.b.setText(subListBeanX.getServiceName());
            int status = subListBeanX.getStatus();
            if (status == 0) {
                this.b.setTextColor(SelectServiceAdapter.this.b.getResources().getColor(R.color.order_color_474A4D));
                this.b.setBackgroundResource(R.drawable.product_detail_shape_corner_4_f3f5f6);
            } else if (status == 1) {
                this.b.setTextColor(SelectServiceAdapter.this.b.getResources().getColor(R.color.color_FFFFFF));
                this.b.setBackgroundResource(R.drawable.shape_corners_4_ff4891);
            } else if (status == 2) {
                this.b.setTextColor(SelectServiceAdapter.this.b.getResources().getColor(R.color.color_bbbdc0));
                this.b.setBackgroundResource(R.drawable.product_detail_shape_corner_4_f3f5f6);
            }
            AppMethodBeat.o(UidHealthStats.MEASUREMENT_WIFI_TX_PACKETS);
        }

        public void a(int i) {
            AppMethodBeat.i(UidHealthStats.TIMER_MOBILE_RADIO_ACTIVE, false);
            this.b.setOnClickListener(new SelectServiceAdapter$ViewHolder$1(this, i));
            AppMethodBeat.o(UidHealthStats.TIMER_MOBILE_RADIO_ACTIVE);
        }
    }
}
