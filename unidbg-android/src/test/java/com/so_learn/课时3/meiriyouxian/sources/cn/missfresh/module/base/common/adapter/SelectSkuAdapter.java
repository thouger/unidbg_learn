package cn.missfresh.module.base.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.SelectSkuProductBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.connect.common.Constants;
import java.util.List;

public class SelectSkuAdapter extends RecyclerView.Adapter<b> {
    a a;
    private Context b;
    private List<SelectSkuProductBean.AttributesEntity.AttributeMembersEntity> c;
    private SelectSkuProductBean.AttributesEntity.AttributeMembersEntity d;

    public interface a {
        void a(int i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(10142, false);
        a((b) viewHolder, i);
        AppMethodBeat.o(10142);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(10145, false);
        b a2 = a(viewGroup, i);
        AppMethodBeat.o(10145);
        return a2;
    }

    public SelectSkuAdapter(Context context, List<SelectSkuProductBean.AttributesEntity.AttributeMembersEntity> list) {
        this.b = context;
        this.c = list;
    }

    public SelectSkuProductBean.AttributesEntity.AttributeMembersEntity a() {
        return this.d;
    }

    public void a(SelectSkuProductBean.AttributesEntity.AttributeMembersEntity attributeMembersEntity) {
        this.d = attributeMembersEntity;
    }

    public List<SelectSkuProductBean.AttributesEntity.AttributeMembersEntity> b() {
        return this.c;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public b a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(10131, false);
        b bVar = new b(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dialog_select_sku_item, viewGroup, false));
        AppMethodBeat.o(10131);
        return bVar;
    }

    public void a(b bVar, int i) {
        AppMethodBeat.i(10134, false);
        bVar.a(this.c.get(i));
        bVar.a(i);
        AppMethodBeat.o(10134);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        AppMethodBeat.i(10138, false);
        int size = this.c.size();
        AppMethodBeat.o(10138);
        return size;
    }

    /* access modifiers changed from: package-private */
    public class b extends RecyclerView.ViewHolder {
        private TextView b;

        public b(View view) {
            super(view);
            AppMethodBeat.i(10099, false);
            this.b = (TextView) view.findViewById(R.id.tv);
            AppMethodBeat.o(10099);
        }

        public void a(SelectSkuProductBean.AttributesEntity.AttributeMembersEntity attributeMembersEntity) {
            AppMethodBeat.i(10101, false);
            this.b.setText(attributeMembersEntity.getName());
            int status = attributeMembersEntity.getStatus();
            if (status == 0) {
                this.b.setTextColor(SelectSkuAdapter.this.b.getResources().getColor(R.color.order_color_474A4D));
                this.b.setBackgroundResource(R.drawable.product_detail_shape_corner_4_f3f5f6);
            } else if (status == 1) {
                this.b.setTextColor(SelectSkuAdapter.this.b.getResources().getColor(R.color.color_FFFFFF));
                this.b.setBackgroundResource(R.drawable.shape_corners_4_ff4891);
            } else if (status == 2) {
                this.b.setTextColor(SelectSkuAdapter.this.b.getResources().getColor(R.color.color_bbbdc0));
                this.b.setBackgroundResource(R.drawable.product_detail_shape_corner_4_f3f5f6);
            }
            AppMethodBeat.o(10101);
        }

        public void a(int i) {
            AppMethodBeat.i(Constants.REQUEST_SEND_TO_MY_COMPUTER, false);
            this.b.setOnClickListener(new SelectSkuAdapter$ViewHolder$1(this, i));
            AppMethodBeat.o(Constants.REQUEST_SEND_TO_MY_COMPUTER);
        }
    }
}
