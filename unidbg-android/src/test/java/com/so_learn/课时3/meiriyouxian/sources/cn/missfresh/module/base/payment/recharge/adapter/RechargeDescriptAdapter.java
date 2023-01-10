package cn.missfresh.module.base.payment.recharge.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;
import java.util.List;

public class RechargeDescriptAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<String> a;
    private LayoutInflater b;

    public void a(ViewHolder viewHolder, int i) {
        JniLib.cV(this, viewHolder, Integer.valueOf(i), 324);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return JniLib.cI(this, 325);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(16802, false);
        a((ViewHolder) viewHolder, i);
        AppMethodBeat.o(16802);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(16804, false);
        ViewHolder a = a(viewGroup, i);
        AppMethodBeat.o(16804);
        return a;
    }

    public ViewHolder a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(16797, false);
        ViewHolder viewHolder = new ViewHolder(this.b.inflate(R.layout.item_recharge_descript, viewGroup, false));
        AppMethodBeat.o(16797);
        return viewHolder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView a;

        public ViewHolder(View view) {
            super(view);
            AppMethodBeat.i(16787, false);
            this.a = (TextView) view.findViewById(R.id.recharget_tv_item_descript);
            AppMethodBeat.o(16787);
        }
    }
}
