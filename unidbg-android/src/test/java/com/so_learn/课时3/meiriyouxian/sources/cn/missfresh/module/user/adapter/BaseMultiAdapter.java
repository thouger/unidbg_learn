package cn.missfresh.module.user.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.holder.MultiViewHolder;
import cn.missfresh.module.base.common.interfaces.r;
import cn.missfresh.module.base.common.interfaces.t;
import cn.missfresh.module.user.adapter.viewholder.MultiFooterViewHolder;
import cn.missfresh.module.user.bean.MultiFooterBean;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseMultiAdapter<T extends t> extends RecyclerView.Adapter<MultiViewHolder> {
    protected r a;
    protected MultiFooterBean b = new MultiFooterBean();
    private List<T> c = new ArrayList();

    /* access modifiers changed from: protected */
    public abstract int a(int i);

    /* access modifiers changed from: protected */
    public abstract MultiViewHolder a(int i, View view);

    public void a(r rVar) {
        this.a = rVar;
    }

    public List<T> a() {
        return this.c;
    }

    public void a(List<T> list, boolean z) {
        this.c.clear();
        if (list != null) {
            this.c.addAll(list);
            this.b.noMoreData = !z ? 1 : 0;
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public MultiViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == this.b.getMultiType()) {
            return new MultiFooterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_homepage_product_footer, viewGroup, false));
        }
        return a(i, LayoutInflater.from(viewGroup.getContext()).inflate(a(i), viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(MultiViewHolder multiViewHolder, int i) {
        if (getItemViewType(i) == this.b.getMultiType()) {
            multiViewHolder.a(this.b);
        } else {
            multiViewHolder.a(this.c.get(i));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i < this.c.size() ? this.c.get(i).getMultiType() : this.b.getMultiType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (a() == null || a().isEmpty()) {
            return 0;
        }
        return this.c.size() + 1;
    }
}
