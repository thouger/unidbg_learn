package cn.missfresh.module.base.oftenbuy.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.base.common.listener.a;
import cn.missfresh.module.base.oftenbuy.holder.BaseOftenBuyProductHolder;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public class BaseProductOftenBuyAdapter extends RecyclerView.Adapter<BaseOftenBuyProductHolder> {
    private List<ProductsEntity> a = new ArrayList();
    private a b;
    private int c;
    private String d;
    private String e;
    private int f;
    private cn.missfresh.module.base.oftenbuy.b.a g;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(16215, false);
        a((BaseOftenBuyProductHolder) viewHolder, i);
        AppMethodBeat.o(16215);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(16218, false);
        BaseOftenBuyProductHolder a = a(viewGroup, i);
        AppMethodBeat.o(16218);
        return a;
    }

    public BaseProductOftenBuyAdapter(a aVar, int i, cn.missfresh.module.base.oftenbuy.b.a aVar2) {
        AppMethodBeat.i(16207, false);
        this.f = i;
        this.b = aVar;
        this.g = aVar2;
        AppMethodBeat.o(16207);
    }

    public void a(List<ProductsEntity> list, int i, String str, String str2, int i2) {
        AppMethodBeat.i(16208, false);
        this.c = i;
        this.e = str2;
        this.d = str;
        this.f = i2;
        this.a.clear();
        this.a.addAll(list);
        notifyDataSetChanged();
        AppMethodBeat.o(16208);
    }

    public BaseOftenBuyProductHolder a(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(16210, false);
        BaseOftenBuyProductHolder baseOftenBuyProductHolder = new BaseOftenBuyProductHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.base_channel_often_buy_item, viewGroup, false), this.b, this.f, this.g);
        AppMethodBeat.o(16210);
        return baseOftenBuyProductHolder;
    }

    public void a(BaseOftenBuyProductHolder baseOftenBuyProductHolder, int i) {
        AppMethodBeat.i(16211, false);
        baseOftenBuyProductHolder.a(this.a.get(i), this.c, this.d, this.e);
        AppMethodBeat.o(16211);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        AppMethodBeat.i(16213, false);
        List<ProductsEntity> list = this.a;
        if (list != null) {
            i = list.size() > 3 ? 3 : this.a.size();
        }
        AppMethodBeat.o(16213);
        return i;
    }
}
