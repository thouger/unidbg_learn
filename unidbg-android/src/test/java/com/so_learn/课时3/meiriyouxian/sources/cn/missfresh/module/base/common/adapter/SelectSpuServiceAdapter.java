package cn.missfresh.module.base.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.SpuParams;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class SelectSpuServiceAdapter extends RecyclerView.Adapter<b> {
    a a;
    private Context b;
    private List<SpuParams.SpuInfoBean.ServiceListBean.SubListBeanX> c;

    public interface a {
        void a(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(10195, false);
        a((b) viewHolder, i);
        AppMethodBeat.o(10195);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(10198, false);
        b a2 = a(viewGroup, i);
        AppMethodBeat.o(10198);
        return a2;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public SelectSpuServiceAdapter(Context context, List<SpuParams.SpuInfoBean.ServiceListBean.SubListBeanX> list) {
        this.b = context;
        this.c = list;
    }

    public List<SpuParams.SpuInfoBean.ServiceListBean.SubListBeanX> a() {
        return this.c;
    }

    public b a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(10186, false);
        b bVar = new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_select_sku_item, viewGroup, false));
        AppMethodBeat.o(10186);
        return bVar;
    }

    public void a(b bVar, int i) {
        AppMethodBeat.i(10190, false);
        bVar.a(this.c.get(i));
        bVar.a(i);
        AppMethodBeat.o(10190);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        AppMethodBeat.i(10192, false);
        int size = this.c.size();
        AppMethodBeat.o(10192);
        return size;
    }

    /* access modifiers changed from: package-private */
    public class b extends RecyclerView.ViewHolder {
        private TextView b;

        public b(View view) {
            super(view);
            AppMethodBeat.i(10165, false);
            this.b = (TextView) view.findViewById(R.id.tv);
            AppMethodBeat.o(10165);
        }

        public void a(SpuParams.SpuInfoBean.ServiceListBean.SubListBeanX subListBeanX) {
            AppMethodBeat.i(10170, false);
            this.b.setText(subListBeanX.getServiceName());
            int status = subListBeanX.getStatus();
            if (status == 0) {
                this.b.setTextColor(SelectSpuServiceAdapter.this.b.getResources().getColor(R.color.order_color_474A4D));
                this.b.setBackgroundResource(R.drawable.product_detail_shape_corner_4_f3f5f6);
            } else if (status == 1) {
                this.b.setTextColor(SelectSpuServiceAdapter.this.b.getResources().getColor(R.color.color_FFFFFF));
                this.b.setBackgroundResource(R.drawable.shape_corners_4_ff4891);
            } else if (status == 2) {
                this.b.setTextColor(SelectSpuServiceAdapter.this.b.getResources().getColor(R.color.color_bbbdc0));
                this.b.setBackgroundResource(R.drawable.product_detail_shape_corner_4_f3f5f6);
            }
            AppMethodBeat.o(10170);
        }

        public void a(int i) {
            AppMethodBeat.i(10173, false);
            this.b.setOnClickListener(new SelectSpuServiceAdapter$ViewHolder$1(this, i));
            AppMethodBeat.o(10173);
        }
    }
}
