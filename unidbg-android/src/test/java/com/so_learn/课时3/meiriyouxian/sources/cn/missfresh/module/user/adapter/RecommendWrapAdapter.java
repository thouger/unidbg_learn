package cn.missfresh.module.user.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;

public class RecommendWrapAdapter<T extends RecyclerView.Adapter> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final T a;
    private boolean b;
    private ArrayList<RecommendWrapAdapter<T>.a> c;
    private ArrayList<RecommendWrapAdapter<T>.a> d;

    private boolean a(int i) {
        boolean z = false;
        AppMethodBeat.i(3026, false);
        if (i >= -1024 && i < this.c.size() - 1024) {
            z = true;
        }
        AppMethodBeat.o(3026);
        return z;
    }

    private boolean b(int i) {
        boolean z = false;
        AppMethodBeat.i(3030, false);
        if (i >= -2048 && i < this.d.size() - 2048) {
            z = true;
        }
        AppMethodBeat.o(3030);
        return z;
    }

    private boolean c(int i) {
        boolean z = false;
        AppMethodBeat.i(3035, false);
        if (i < this.c.size()) {
            z = true;
        }
        AppMethodBeat.o(3035);
        return z;
    }

    private boolean d(int i) {
        boolean z = false;
        AppMethodBeat.i(3040, false);
        if (i >= this.c.size() + this.a.getItemCount()) {
            z = true;
        }
        AppMethodBeat.o(3040);
        return z;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(3044, false);
        if (a(i)) {
            RecyclerView.ViewHolder a = a(this.c.get(Math.abs(i + 1024)).a);
            AppMethodBeat.o(3044);
            return a;
        } else if (b(i)) {
            RecyclerView.ViewHolder a2 = a(this.d.get(Math.abs(i + 2048)).a);
            AppMethodBeat.o(3044);
            return a2;
        } else {
            RecyclerView.ViewHolder onCreateViewHolder = this.a.onCreateViewHolder(viewGroup, i);
            AppMethodBeat.o(3044);
            return onCreateViewHolder;
        }
    }

    private RecyclerView.ViewHolder a(View view) {
        AppMethodBeat.i(3052, false);
        if (this.b) {
            StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(-1, -2);
            layoutParams.setFullSpan(true);
            view.setLayoutParams(layoutParams);
        }
        AnonymousClass1 r1 = new AnonymousClass1(view);
        AppMethodBeat.o(3052);
        return r1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.adapter.RecommendWrapAdapter$1  reason: invalid class name */
    public class AnonymousClass1 extends RecyclerView.ViewHolder {
        AnonymousClass1(View view) {
            super(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(3058, false);
        if (i >= this.c.size() && i < this.c.size() + this.a.getItemCount()) {
            this.a.onBindViewHolder(viewHolder, i - this.c.size());
        }
        AppMethodBeat.o(3058);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        AppMethodBeat.i(3062, false);
        int size = this.c.size() + this.a.getItemCount() + this.d.size();
        AppMethodBeat.o(3062);
        return size;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        AppMethodBeat.i(3069, false);
        if (c(i)) {
            int i2 = this.c.get(i).b;
            AppMethodBeat.o(3069);
            return i2;
        } else if (d(i)) {
            int i3 = this.d.get((i - this.c.size()) - this.a.getItemCount()).b;
            AppMethodBeat.o(3069);
            return i3;
        } else {
            int itemViewType = this.a.getItemViewType(i - this.c.size());
            AppMethodBeat.o(3069);
            return itemViewType;
        }
    }
}
