package cn.missfresh.basiclib.ui.permission;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.basiclib.R;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class InformAdapter extends RecyclerView.Adapter<InformViewHolder> {
    private List<String> a;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(4586, false);
        a((InformViewHolder) viewHolder, i);
        AppMethodBeat.o(4586);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(4589, false);
        InformViewHolder a = a(viewGroup, i);
        AppMethodBeat.o(4589);
        return a;
    }

    public void a(List<String> list) {
        this.a = list;
    }

    public InformViewHolder a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(4576, false);
        InformViewHolder informViewHolder = new InformViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_permission, viewGroup, false));
        AppMethodBeat.o(4576);
        return informViewHolder;
    }

    public void a(InformViewHolder informViewHolder, int i) {
        AppMethodBeat.i(4579, false);
        informViewHolder.a(a(i));
        AppMethodBeat.o(4579);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        AppMethodBeat.i(4582, false);
        List<String> list = this.a;
        if (list != null) {
            i = list.size();
        }
        AppMethodBeat.o(4582);
        return i;
    }

    public String a(int i) {
        AppMethodBeat.i(4583, false);
        if (i < 0 || i > getItemCount()) {
            AppMethodBeat.o(4583);
            return "";
        }
        String str = this.a.get(i);
        AppMethodBeat.o(4583);
        return str;
    }

    public static class InformViewHolder extends RecyclerView.ViewHolder {
        private ImageView a;
        private TextView b;

        public InformViewHolder(View view) {
            super(view);
            AppMethodBeat.i(4512, false);
            this.a = (ImageView) view.findViewById(R.id.iv_permission);
            this.b = (TextView) view.findViewById(R.id.tv_name);
            AppMethodBeat.o(4512);
        }

        public void a(String str) {
            AppMethodBeat.i(4515, false);
            this.a.setImageResource(c.b(str));
            this.b.setText(c.a(str));
            AppMethodBeat.o(4515);
        }
    }
}
