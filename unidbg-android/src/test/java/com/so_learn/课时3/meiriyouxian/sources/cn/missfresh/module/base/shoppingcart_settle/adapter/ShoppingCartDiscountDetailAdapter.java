package cn.missfresh.module.base.shoppingcart_settle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.holder.EmptViewHolder;
import cn.missfresh.module.base.shoppingcart_settle.bean.BalanceArea;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class ShoppingCartDiscountDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BalanceArea.DiscountItemsBean> a;

    public void a(List<BalanceArea.DiscountItemsBean> list) {
        AppMethodBeat.i(19295, false);
        this.a = list;
        notifyDataSetChanged();
        AppMethodBeat.o(19295);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(19299, false);
        if (i == 1) {
            TitleViewHolder titleViewHolder = new TitleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.settle_item_shoppingcart_discount_head, viewGroup, false));
            AppMethodBeat.o(19299);
            return titleViewHolder;
        } else if (i == 2) {
            ContentViewHolder contentViewHolder = new ContentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.base_item_shoppingcart_discount_content, viewGroup, false));
            AppMethodBeat.o(19299);
            return contentViewHolder;
        } else {
            EmptViewHolder emptViewHolder = new EmptViewHolder(new View(viewGroup.getContext()));
            AppMethodBeat.o(19299);
            return emptViewHolder;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(19303, false);
        if (viewHolder instanceof TitleViewHolder) {
            ((TitleViewHolder) viewHolder).a(this.a.get(i).getGroupTitle(), this.a.get(i).getGroupSubTitle());
        } else if (viewHolder instanceof ContentViewHolder) {
            ((ContentViewHolder) viewHolder).a(this.a.get(i));
        }
        AppMethodBeat.o(19303);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        AppMethodBeat.i(19305, false);
        List<BalanceArea.DiscountItemsBean> list = this.a;
        if (list != null) {
            i = list.size();
        }
        AppMethodBeat.o(19305);
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        AppMethodBeat.i(19307, false);
        int type = this.a.get(i).getType();
        AppMethodBeat.o(19307);
        return type;
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        private TextView a;
        private TextView b;

        public TitleViewHolder(View view) {
            super(view);
            AppMethodBeat.i(19280, false);
            this.a = (TextView) view.findViewById(R.id.tv_title);
            this.b = (TextView) view.findViewById(R.id.tv_sub_title);
            AppMethodBeat.o(19280);
        }

        public void a(String str, String str2) {
            AppMethodBeat.i(19284, false);
            this.a.setText(str);
            this.b.setText(str2);
            AppMethodBeat.o(19284);
        }
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView a;
        private TextView b;
        private View c;

        public ContentViewHolder(View view) {
            super(view);
            AppMethodBeat.i(19270, false);
            this.a = (TextView) view.findViewById(R.id.tv_tag);
            this.b = (TextView) view.findViewById(R.id.tv_price);
            this.c = view.findViewById(R.id.v_divider);
            AppMethodBeat.o(19270);
        }

        public void a(BalanceArea.DiscountItemsBean discountItemsBean) {
            int i = 0;
            AppMethodBeat.i(19273, false);
            if (discountItemsBean != null) {
                this.itemView.setVisibility(0);
                this.a.setText(discountItemsBean.getName());
                aw.a(this.b, discountItemsBean.getAmount(), R.color.color_ff4891, new String[]{"#_$", "#_$"}, true);
                View view = this.c;
                if (!discountItemsBean.isShowDivider()) {
                    i = 8;
                }
                view.setVisibility(i);
            } else {
                this.itemView.setVisibility(8);
            }
            AppMethodBeat.o(19273);
        }
    }
}
