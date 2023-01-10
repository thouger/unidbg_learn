package cn.missfresh.module.user.mine.adapter;

import android.view.View;
import cn.missfresh.module.base.bean.BaseMsgBean;
import cn.missfresh.module.base.common.holder.MultiViewHolder;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.adapter.BaseMultiAdapter;
import cn.missfresh.module.user.mine.adapter.viewholder.ActionViewHolder;
import cn.missfresh.module.user.mine.adapter.viewholder.GiftHolderView;
import cn.missfresh.module.user.mine.adapter.viewholder.OrderViewHolder;
import cn.missfresh.module.user.mine.adapter.viewholder.SystemViewHolder;
import cn.missfresh.module.user.mine.adapter.viewholder.VipHolderView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class MsgListAdapter extends BaseMultiAdapter<BaseMsgBean> {
    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.user.adapter.BaseMultiAdapter
    public int a(int i) {
        if (i == 1) {
            return R.layout.item_msg_action;
        }
        if (i == 2) {
            return R.layout.item_msg_order;
        }
        if (i == 3) {
            return R.layout.item_msg_system;
        }
        if (i == 4) {
            return R.layout.item_msg_gift;
        }
        if (i != 5) {
            return R.layout.item_msg_action;
        }
        return R.layout.item_msg_vip;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.user.adapter.BaseMultiAdapter
    public MultiViewHolder a(int i, View view) {
        AppMethodBeat.i(8736, false);
        if (i == 1) {
            ActionViewHolder actionViewHolder = new ActionViewHolder(view, this.a);
            AppMethodBeat.o(8736);
            return actionViewHolder;
        } else if (i == 2) {
            OrderViewHolder orderViewHolder = new OrderViewHolder(view);
            AppMethodBeat.o(8736);
            return orderViewHolder;
        } else if (i == 3) {
            SystemViewHolder systemViewHolder = new SystemViewHolder(view);
            AppMethodBeat.o(8736);
            return systemViewHolder;
        } else if (i == 4) {
            GiftHolderView giftHolderView = new GiftHolderView(view);
            AppMethodBeat.o(8736);
            return giftHolderView;
        } else if (i != 5) {
            ActionViewHolder actionViewHolder2 = new ActionViewHolder(view, this.a);
            AppMethodBeat.o(8736);
            return actionViewHolder2;
        } else {
            VipHolderView vipHolderView = new VipHolderView(view);
            AppMethodBeat.o(8736);
            return vipHolderView;
        }
    }
}
