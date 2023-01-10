package cn.missfresh.module.user.address.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.address.bean.SupporCityBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public class SearchCityAddressAdapter extends RecyclerView.Adapter<ViewHolder> implements Filterable {
    private LayoutInflater a;
    private List<SupporCityBean> b = new ArrayList();
    private List<SupporCityBean> c = new ArrayList();
    private a d;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(3368, false);
        a((ViewHolder) viewHolder, i);
        AppMethodBeat.o(3368);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(3372, false);
        ViewHolder a = a(viewGroup, i);
        AppMethodBeat.o(3372);
        return a;
    }

    public SearchCityAddressAdapter(Context context) {
        AppMethodBeat.i(3348, false);
        this.a = LayoutInflater.from(context);
        AppMethodBeat.o(3348);
    }

    public void a(List<SupporCityBean> list) {
        AppMethodBeat.i(3351, false);
        this.b.clear();
        this.c.clear();
        if (list != null) {
            this.b.addAll(list);
            this.c.addAll(list);
        }
        notifyDataSetChanged();
        AppMethodBeat.o(3351);
    }

    public ViewHolder a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(3355, false);
        ViewHolder viewHolder = new ViewHolder(this.a.inflate(R.layout.meituan_item_select_city, viewGroup, false));
        AppMethodBeat.o(3355);
        return viewHolder;
    }

    public void a(ViewHolder viewHolder, int i) {
        AppMethodBeat.i(3357, false);
        if (this.c.size() - 1 >= i) {
            viewHolder.a.setText(this.c.get(i).getName());
            viewHolder.a.setOnClickListener(new 1(this, i));
        }
        AppMethodBeat.o(3357);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        AppMethodBeat.i(3360, false);
        List<SupporCityBean> list = this.c;
        if (!(list == null || list.size() == 0)) {
            i = this.c.size();
        }
        AppMethodBeat.o(3360);
        return i;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        AppMethodBeat.i(3364, false);
        2 r1 = new 2(this);
        AppMethodBeat.o(3364);
        return r1;
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView a;

        public ViewHolder(View view) {
            super(view);
            AppMethodBeat.i(3343, false);
            this.a = (TextView) view.findViewById(R.id.tvCity);
            AppMethodBeat.o(3343);
        }
    }
}
