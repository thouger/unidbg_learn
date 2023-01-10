package cn.missfresh.module.user.address.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.user.address.adapter.viewholder.ViewHolder;
import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context a;
    protected int b;
    protected List<T> c;
    protected LayoutInflater d;
    protected ViewGroup e;
    private b f;

    public abstract void a(ViewHolder viewHolder, T t);

    /* access modifiers changed from: protected */
    public boolean a(int i) {
        return true;
    }

    public CommonAdapter a(b bVar) {
        this.f = bVar;
        return this;
    }

    public CommonAdapter(Context context, int i, List<T> list) {
        this.a = context;
        this.d = LayoutInflater.from(context);
        this.b = i;
        this.c = list;
    }

    /* renamed from: a */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder a = ViewHolder.a(this.a, null, viewGroup, this.b, -1);
        if (this.e == null) {
            this.e = viewGroup;
        }
        return a;
    }

    /* access modifiers changed from: protected */
    public int a(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }

    /* renamed from: a */
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.b(i);
        a(i, viewHolder);
        a(viewHolder, this.c.get(i));
    }

    /* access modifiers changed from: protected */
    public void a(int i, ViewHolder viewHolder) {
        if (a(getItemViewType(i))) {
            viewHolder.a().setOnClickListener(new 3(this, i));
            viewHolder.a().setOnLongClickListener(new 1(this, viewHolder));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<T> list = this.c;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void a(List<T> list) {
        List<T> list2 = this.c;
        if (list2 == null) {
            this.c = list;
        } else if (list != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(list);
            this.c.clear();
            this.c.addAll(arrayList);
        } else {
            list2.clear();
        }
        notifyDataSetChanged();
    }
}
