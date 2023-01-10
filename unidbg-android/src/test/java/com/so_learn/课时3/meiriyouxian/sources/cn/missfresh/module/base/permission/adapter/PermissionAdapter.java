package cn.missfresh.module.base.permission.adapter;

import android.Manifest;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.util.List;

public class PermissionAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<String> a;
    private LayoutInflater b;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(18719, false);
        a((ViewHolder) viewHolder, i);
        AppMethodBeat.o(18719);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(18722, false);
        ViewHolder a = a(viewGroup, i);
        AppMethodBeat.o(18722);
        return a;
    }

    public PermissionAdapter(Context context, List<String> list) {
        AppMethodBeat.i(18701, false);
        this.b = LayoutInflater.from(context);
        this.a = list;
        b(this.a);
        AppMethodBeat.o(18701);
    }

    public void a(List<String> list) {
        AppMethodBeat.i(18704, false);
        this.a = list;
        b(this.a);
        notifyDataSetChanged();
        AppMethodBeat.o(18704);
    }

    private void b(List<String> list) {
        AppMethodBeat.i(18706, false);
        if (!b.a(list) && list.contains(Manifest.permission.ACCESS_FINE_LOCATION) && list.contains(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            list.remove(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        AppMethodBeat.o(18706);
    }

    public ViewHolder a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(18708, false);
        ViewHolder viewHolder = new ViewHolder(this.b.inflate(R.layout.item_app_permission, viewGroup, false));
        AppMethodBeat.o(18708);
        return viewHolder;
    }

    public void a(ViewHolder viewHolder, int i) {
        AppMethodBeat.i(18712, false);
        viewHolder.a(this.a.get(i));
        AppMethodBeat.o(18712);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        AppMethodBeat.i(18715, false);
        List<String> list = this.a;
        if (list != null) {
            i = list.size();
        }
        AppMethodBeat.o(18715);
        return i;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView a;
        TextView b;
        TextView c;

        public ViewHolder(View view) {
            super(view);
            AppMethodBeat.i(18689, false);
            this.a = (ImageView) view.findViewById(R.id.iv_permission);
            this.b = (TextView) view.findViewById(R.id.tv_name);
            this.c = (TextView) view.findViewById(R.id.tv_desc);
            AppMethodBeat.o(18689);
        }

        public void a(String str) {
            AppMethodBeat.i(18692, false);
            this.a.setImageResource(j.c(this.itemView.getContext(), str));
            this.b.setText(j.a(this.itemView.getContext(), str));
            this.c.setText(j.b(this.itemView.getContext(), str));
            AppMethodBeat.o(18692);
        }
    }
}
