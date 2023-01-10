package cn.missfresh.module.user.address.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cn.missfresh.module.user.address.adapter.viewholder.ViewHolder;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public abstract class HeaderRecyclerAndFooterWrapperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected RecyclerView.Adapter a;
    private SparseArrayCompat<SparseArrayCompat> b = new SparseArrayCompat<>();
    private SparseArrayCompat<View> c = new SparseArrayCompat<>();

    /* access modifiers changed from: protected */
    public abstract void a(ViewHolder viewHolder, int i, int i2, Object obj);

    public HeaderRecyclerAndFooterWrapperAdapter(RecyclerView.Adapter adapter) {
        this.a = adapter;
    }

    public int a() {
        return this.b.size();
    }

    public int b() {
        return this.c.size();
    }

    private int c() {
        RecyclerView.Adapter adapter = this.a;
        if (adapter != null) {
            return adapter.getItemCount();
        }
        return 0;
    }

    public boolean a(int i) {
        return a() > i;
    }

    public boolean b(int i) {
        return i >= a() + c();
    }

    public void a(int i, Object obj) {
        SparseArrayCompat sparseArrayCompat = new SparseArrayCompat();
        sparseArrayCompat.put(i, obj);
        SparseArrayCompat<SparseArrayCompat> sparseArrayCompat2 = this.b;
        sparseArrayCompat2.put(sparseArrayCompat2.size() + 1000000, sparseArrayCompat);
    }

    public void a(int i, int i2, Object obj) {
        if (this.b.size() > i) {
            SparseArrayCompat sparseArrayCompat = new SparseArrayCompat();
            sparseArrayCompat.put(i2, obj);
            this.b.setValueAt(i, sparseArrayCompat);
        } else if (this.b.size() == i) {
            a(i2, obj);
        } else {
            a(i2, obj);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (a(i)) {
            return this.b.keyAt(i);
        }
        if (b(i)) {
            return this.c.keyAt((i - a()) - c());
        }
        return super.getItemViewType(i - a());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.b.get(i) != null) {
            return ViewHolder.a(viewGroup.getContext(), null, viewGroup, ((SparseArrayCompat) this.b.get(i)).keyAt(0), -1);
        }
        if (this.c.get(i) != null) {
            return new ViewHolder(viewGroup.getContext(), (View) this.c.get(i));
        }
        return this.a.onCreateViewHolder(viewGroup, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (a(i)) {
            int keyAt = ((SparseArrayCompat) this.b.get(getItemViewType(i))).keyAt(0);
            a((ViewHolder) viewHolder, i, keyAt, ((SparseArrayCompat) this.b.get(getItemViewType(i))).get(keyAt));
        } else if (!b(i)) {
            this.a.onBindViewHolder(viewHolder, i - a());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return c() + a() + b();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.a.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new AnonymousClass1(gridLayoutManager, gridLayoutManager.getSpanSizeLookup()));
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    /* renamed from: cn.missfresh.module.user.address.adapter.HeaderRecyclerAndFooterWrapperAdapter$1  reason: invalid class name */
    class AnonymousClass1 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ GridLayoutManager a;
        final /* synthetic */ GridLayoutManager.SpanSizeLookup b;

        AnonymousClass1(GridLayoutManager gridLayoutManager, GridLayoutManager.SpanSizeLookup spanSizeLookup) {
            this.a = gridLayoutManager;
            this.b = spanSizeLookup;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i) {
            AppMethodBeat.i(3239, false);
            int itemViewType = HeaderRecyclerAndFooterWrapperAdapter.this.getItemViewType(i);
            if (HeaderRecyclerAndFooterWrapperAdapter.this.b.get(itemViewType) != null) {
                int spanCount = this.a.getSpanCount();
                AppMethodBeat.o(3239);
                return spanCount;
            } else if (HeaderRecyclerAndFooterWrapperAdapter.this.c.get(itemViewType) != null) {
                int spanCount2 = this.a.getSpanCount();
                AppMethodBeat.o(3239);
                return spanCount2;
            } else {
                GridLayoutManager.SpanSizeLookup spanSizeLookup = this.b;
                if (spanSizeLookup != null) {
                    int spanSize = spanSizeLookup.getSpanSize(i);
                    AppMethodBeat.o(3239);
                    return spanSize;
                }
                AppMethodBeat.o(3239);
                return 1;
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        ViewGroup.LayoutParams layoutParams;
        this.a.onViewAttachedToWindow(viewHolder);
        int layoutPosition = viewHolder.getLayoutPosition();
        if ((a(layoutPosition) || b(layoutPosition)) && (layoutParams = viewHolder.itemView.getLayoutParams()) != null && (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        }
    }
}
