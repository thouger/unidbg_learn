package cn.missfresh.module.base.widget.recycleview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected final String a = getClass().getSimpleName();
    protected View b = null;
    protected View c = null;
    protected AdapterView.OnItemClickListener d;
    protected AdapterView.OnItemLongClickListener e;

    public abstract int a();

    public abstract int a(int i);

    /* access modifiers changed from: protected */
    public abstract RecyclerView.ViewHolder a(ViewGroup viewGroup, Context context, int i);

    public abstract void a(RecyclerView.ViewHolder viewHolder, int i, boolean z);

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View view) {
            super(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        View view2;
        if (i == 7 && (view2 = this.b) != null) {
            return new SimpleViewHolder(view2);
        }
        if (i != 8 || (view = this.c) == null) {
            return a(viewGroup, viewGroup.getContext(), i);
        }
        return new SimpleViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = this.b != null ? 1 : 0;
        if (this.c != null) {
            i++;
        }
        return a() + i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.c != null && i == getItemCount() - 1) {
            return 8;
        }
        if (this.b != null && i == 0) {
            return 7;
        }
        if (this.b != null) {
            return a(i - 1);
        }
        return a(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (this.b != null && i == 0) {
            return;
        }
        if (this.c == null || i != getItemCount() - 1) {
            if (this.b != null) {
                i--;
            }
            a(viewHolder, i, true);
            if (this.d != null) {
                viewHolder.itemView.setOnClickListener(new AnonymousClass1(viewHolder, i));
            }
            if (this.e != null) {
                viewHolder.itemView.setOnLongClickListener(new AnonymousClass2(viewHolder, i));
            }
        }
    }

    /* renamed from: cn.missfresh.module.base.widget.recycleview.BaseRecyclerAdapter$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ RecyclerView.ViewHolder a;
        final /* synthetic */ int b;

        AnonymousClass1(RecyclerView.ViewHolder viewHolder, int i) {
            this.a = viewHolder;
            this.b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(24238, false);
            AdapterView.OnItemClickListener onItemClickListener = BaseRecyclerAdapter.this.d;
            View view2 = this.a.itemView;
            int i = this.b;
            onItemClickListener.onItemClick(null, view2, i, (long) i);
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(24238);
        }
    }

    /* renamed from: cn.missfresh.module.base.widget.recycleview.BaseRecyclerAdapter$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnLongClickListener {
        final /* synthetic */ RecyclerView.ViewHolder a;
        final /* synthetic */ int b;

        AnonymousClass2(RecyclerView.ViewHolder viewHolder, int i) {
            this.a = viewHolder;
            this.b = i;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            AppMethodBeat.i(24239, false);
            AdapterView.OnItemLongClickListener onItemLongClickListener = BaseRecyclerAdapter.this.e;
            View view2 = this.a.itemView;
            int i = this.b;
            boolean onItemLongClick = onItemLongClickListener.onItemLongClick(null, view2, i, (long) i);
            AppMethodBeat.o(24239);
            return onItemLongClick;
        }
    }
}
