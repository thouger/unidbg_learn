package cn.missfresh.module.base.common.adapter;

import android.os.health.UidHealthStats;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.BasinessForm;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.util.List;

public class MultiFormatAdapter extends RecyclerView.Adapter<TabHolder> {
    private List<BasinessForm> a;
    private int b;
    private int c;
    private boolean d;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(UidHealthStats.MEASUREMENT_WIFI_FULL_LOCK_MS, false);
        a((TabHolder) viewHolder, i);
        AppMethodBeat.o(UidHealthStats.MEASUREMENT_WIFI_FULL_LOCK_MS);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(10031, false);
        TabHolder a = a(viewGroup, i);
        AppMethodBeat.o(10031);
        return a;
    }

    public MultiFormatAdapter() {
        this.b = 0;
        this.c = 0;
        this.d = false;
        this.b = 0;
    }

    public MultiFormatAdapter(boolean z) {
        this.b = 0;
        this.c = 0;
        this.d = false;
        this.d = z;
        this.b = 0;
    }

    public void a(List<BasinessForm> list, int i, int i2) {
        AppMethodBeat.i(10010, false);
        this.a = list;
        this.b = i;
        this.c = i2;
        notifyDataSetChanged();
        AppMethodBeat.o(10010);
    }

    public TabHolder a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(10013, false);
        if (this.d) {
            TabHolder tabHolder = new TabHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_multi_format_search_result, viewGroup, false));
            AppMethodBeat.o(10013);
            return tabHolder;
        }
        TabHolder tabHolder2 = new TabHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_multi_format, viewGroup, false));
        AppMethodBeat.o(10013);
        return tabHolder2;
    }

    public void a(TabHolder tabHolder, int i) {
        AppMethodBeat.i(10017, false);
        TabHolder.a(tabHolder, a(i), i);
        AppMethodBeat.o(10017);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        AppMethodBeat.i(10019, false);
        List<BasinessForm> list = this.a;
        if (list != null) {
            i = list.size();
        }
        AppMethodBeat.o(10019);
        return i;
    }

    public BasinessForm a(int i) {
        AppMethodBeat.i(UidHealthStats.MEASUREMENT_BLUETOOTH_IDLE_MS, false);
        if (i < 0 || i >= getItemCount()) {
            AppMethodBeat.o(UidHealthStats.MEASUREMENT_BLUETOOTH_IDLE_MS);
            return null;
        }
        BasinessForm basinessForm = this.a.get(i);
        AppMethodBeat.o(UidHealthStats.MEASUREMENT_BLUETOOTH_IDLE_MS);
        return basinessForm;
    }

    public class TabHolder extends RecyclerView.ViewHolder {
        private TextView b;
        private TextView c;
        private TextView d;
        private TextView e;
        private LinearLayout f;
        private LinearLayout g;
        private View h;

        static /* synthetic */ void a(TabHolder tabHolder, BasinessForm basinessForm, int i) {
            AppMethodBeat.i(9996, false);
            tabHolder.a(basinessForm, i);
            AppMethodBeat.o(9996);
        }

        TabHolder(View view) {
            super(view);
            AppMethodBeat.i(9989, false);
            this.b = (TextView) view.findViewById(R.id.tv_muti_format_title_selected);
            this.c = (TextView) view.findViewById(R.id.tv_muti_format_subtitle_selected);
            this.d = (TextView) view.findViewById(R.id.tv_muti_format_title_unselected);
            this.e = (TextView) view.findViewById(R.id.tv_muti_format_subtitle_unselected);
            this.f = (LinearLayout) view.findViewById(R.id.selected_layout);
            this.g = (LinearLayout) view.findViewById(R.id.unselected_layout);
            this.h = view.findViewById(R.id.v_divider);
            AppMethodBeat.o(9989);
        }

        private void a(BasinessForm basinessForm, int i) {
            AppMethodBeat.i(9993, false);
            if (basinessForm == null) {
                this.itemView.setVisibility(8);
                AppMethodBeat.o(9993);
                return;
            }
            if (i == 0) {
                this.h.setVisibility(8);
            } else {
                this.h.setVisibility(0);
            }
            this.itemView.setVisibility(0);
            this.b.setText(basinessForm.getTitle());
            this.c.setText(basinessForm.getSubTitle());
            this.d.setText(basinessForm.getTitle());
            this.e.setText(basinessForm.getSubTitle());
            if (b.a(basinessForm.getSubTitle())) {
                this.c.setVisibility(8);
                this.e.setVisibility(8);
            } else {
                this.c.setVisibility(0);
                this.e.setVisibility(0);
            }
            if (MultiFormatAdapter.this.c == 1) {
                this.b.setTextColor(ContextCompat.getColor(this.itemView.getContext(), R.color.white));
                this.c.setTextColor(ContextCompat.getColor(this.itemView.getContext(), R.color.white));
                this.d.setTextColor(ContextCompat.getColor(this.itemView.getContext(), R.color.color_white_80));
                this.e.setTextColor(ContextCompat.getColor(this.itemView.getContext(), R.color.color_white_80));
                this.h.setBackgroundColor(-1);
            } else {
                this.b.setTextColor(q.a(basinessForm.getSelectedColor()));
                this.c.setTextColor(q.a(basinessForm.getSubTitleSelectedColor()));
                this.d.setTextColor(q.a(basinessForm.getUnSelectedColor()));
                this.e.setTextColor(q.a(basinessForm.getSubTitleUnselectedColor()));
                this.h.setBackgroundColor(this.itemView.getResources().getColor(R.color.color_4D96));
            }
            if (MultiFormatAdapter.this.b == getAdapterPosition()) {
                this.f.setVisibility(0);
                this.g.setVisibility(8);
            } else {
                this.f.setVisibility(8);
                this.g.setVisibility(0);
            }
            AppMethodBeat.o(9993);
        }
    }

    public int a() {
        return this.b;
    }

    public void b(int i) {
        int i2;
        AppMethodBeat.i(10026, false);
        if (i < 0 || i >= getItemCount() || (i2 = this.b) == i) {
            AppMethodBeat.o(10026);
            return;
        }
        this.b = i;
        notifyItemChanged(i2);
        notifyItemChanged(this.b);
        AppMethodBeat.o(10026);
    }
}
