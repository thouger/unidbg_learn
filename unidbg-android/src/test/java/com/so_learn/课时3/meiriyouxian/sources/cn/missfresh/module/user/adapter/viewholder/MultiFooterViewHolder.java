package cn.missfresh.module.user.adapter.viewholder;

import android.view.View;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.holder.MultiViewHolder;
import cn.missfresh.module.base.common.interfaces.t;
import cn.missfresh.module.user.bean.MultiFooterBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class MultiFooterViewHolder extends MultiViewHolder {
    private View a;
    private View b;
    private View c;

    public MultiFooterViewHolder(View view) {
        super(view);
        AppMethodBeat.i(3084, false);
        this.a = view;
        this.b = view.findViewById(R.id.tv_no_more_data);
        this.c = view.findViewById(R.id.pb_loading);
        AppMethodBeat.o(3084);
    }

    @Override // cn.missfresh.module.base.common.holder.MultiViewHolder
    public void a(t tVar) {
        AppMethodBeat.i(3091, false);
        if (tVar instanceof MultiFooterBean) {
            MultiFooterBean multiFooterBean = (MultiFooterBean) tVar;
            if (multiFooterBean.noMoreData == 1) {
                this.a.setVisibility(0);
                this.b.setVisibility(0);
                this.c.setVisibility(8);
            } else if (multiFooterBean.noMoreData == 0) {
                this.a.setVisibility(0);
                this.c.setVisibility(0);
                this.b.setVisibility(8);
            } else {
                this.a.setVisibility(8);
            }
        }
        AppMethodBeat.o(3091);
    }
}
