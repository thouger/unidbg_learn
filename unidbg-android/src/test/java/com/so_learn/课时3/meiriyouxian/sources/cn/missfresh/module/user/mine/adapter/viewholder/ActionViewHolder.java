package cn.missfresh.module.user.mine.adapter.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import cn.missfresh.lib.image.c;
import cn.missfresh.module.base.bean.MsgActionListBean$MsgActionBean;
import cn.missfresh.module.base.common.holder.MultiViewHolder;
import cn.missfresh.module.base.common.interfaces.r;
import cn.missfresh.module.base.common.interfaces.t;
import cn.missfresh.module.base.support.RoundImageView;
import cn.missfresh.module.base.utils.ap;
import cn.missfresh.module.user.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ActionViewHolder extends MultiViewHolder {
    private TextView a;
    private TextView b;
    private TextView c;
    private RoundImageView d;

    public ActionViewHolder(View view, r rVar) {
        super(view);
        AppMethodBeat.i(8750, false);
        this.a = (TextView) view.findViewById(R.id.time_tv);
        this.b = (TextView) view.findViewById(R.id.action_title_tv);
        this.c = (TextView) view.findViewById(R.id.action_desc_tv);
        this.d = (RoundImageView) view.findViewById(R.id.action_banner_image);
        ap.a(new AnonymousClass1(rVar), view);
        AppMethodBeat.o(8750);
    }

    /* renamed from: cn.missfresh.module.user.mine.adapter.viewholder.ActionViewHolder$1  reason: invalid class name */
    class AnonymousClass1 implements ap.a {
        final /* synthetic */ r a;

        AnonymousClass1(r rVar) {
            this.a = rVar;
        }

        @Override // cn.missfresh.module.base.utils.ap.a
        public void a(View view) {
            AppMethodBeat.i(8742, false);
            if (!(this.a == null || ActionViewHolder.this.getAdapterPosition() == -1)) {
                this.a.a(ActionViewHolder.this.getAdapterPosition(), ActionViewHolder.this.getItemViewType(), null);
            }
            AppMethodBeat.o(8742);
        }
    }

    @Override // cn.missfresh.module.base.common.holder.MultiViewHolder
    public void a(t tVar) {
        int i = 0;
        AppMethodBeat.i(8753, false);
        if (tVar instanceof MsgActionListBean$MsgActionBean) {
            MsgActionListBean$MsgActionBean msgActionListBean$MsgActionBean = (MsgActionListBean$MsgActionBean) tVar;
            this.a.setText(msgActionListBean$MsgActionBean.timeDesc);
            TextView textView = this.a;
            if (TextUtils.isEmpty(msgActionListBean$MsgActionBean.timeDesc)) {
                i = 8;
            }
            textView.setVisibility(i);
            this.b.setText(msgActionListBean$MsgActionBean.title);
            this.c.setText(msgActionListBean$MsgActionBean.content);
            c.a(this.d).a(msgActionListBean$MsgActionBean.image).a(R.drawable.ic_default_banner).b(R.drawable.ic_default_banner).a(this.d);
        }
        AppMethodBeat.o(8753);
    }
}
