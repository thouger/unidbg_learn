package cn.missfresh.module.user.mine.qucikpay.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.order.orderdetails_v2.bean.QuickPayListItemBean;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.mine.qucikpay.adapter.viewholder.QuickPayViewHolder;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class QuickPayAdapter extends RecyclerView.Adapter<QuickPayViewHolder> {
    private List<QuickPayListItemBean> a;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(9014, false);
        a((QuickPayViewHolder) viewHolder, i);
        AppMethodBeat.o(9014);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(9015, false);
        QuickPayViewHolder a = a(viewGroup, i);
        AppMethodBeat.o(9015);
        return a;
    }

    public void a(List<QuickPayListItemBean> list) {
        AppMethodBeat.i(9002, false);
        this.a = list;
        notifyDataSetChanged();
        AppMethodBeat.o(9002);
    }

    public QuickPayViewHolder a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(9006, false);
        QuickPayViewHolder quickPayViewHolder = new QuickPayViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item_quick_pay, viewGroup, false));
        AppMethodBeat.o(9006);
        return quickPayViewHolder;
    }

    public void a(QuickPayViewHolder quickPayViewHolder, int i) {
        AppMethodBeat.i(9009, false);
        quickPayViewHolder.a(this.a.get(i));
        AppMethodBeat.o(9009);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        AppMethodBeat.i(9011, false);
        List<QuickPayListItemBean> list = this.a;
        if (list != null) {
            i = list.size();
        }
        AppMethodBeat.o(9011);
        return i;
    }
}
