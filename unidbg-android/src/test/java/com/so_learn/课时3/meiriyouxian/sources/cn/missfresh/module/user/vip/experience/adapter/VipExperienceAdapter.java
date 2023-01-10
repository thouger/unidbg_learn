package cn.missfresh.module.user.vip.experience.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.widget.recycleview.BaseRecyclerAdapter;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.vip.experience.bean.VipExperience;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.roundiv.MFRoundedImageView;
import cn.missfresh.utils.b;
import java.util.ArrayList;
import java.util.List;

public class VipExperienceAdapter extends BaseRecyclerAdapter {
    private List<VipExperience.MyInvitesEntity> f = new ArrayList();
    private Context g;
    private LayoutInflater h;

    @Override // cn.missfresh.module.base.widget.recycleview.BaseRecyclerAdapter
    public int a(int i) {
        return 0;
    }

    public VipExperienceAdapter(Context context) {
        AppMethodBeat.i(9841, false);
        this.g = context;
        this.h = LayoutInflater.from(context);
        AppMethodBeat.o(9841);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.widget.recycleview.BaseRecyclerAdapter
    public RecyclerView.ViewHolder a(ViewGroup viewGroup, Context context, int i) {
        AppMethodBeat.i(9843, false);
        a aVar = new a(this.h.inflate(R.layout.item_vip_experiencer, viewGroup, false));
        AppMethodBeat.o(9843);
        return aVar;
    }

    @Override // cn.missfresh.module.base.widget.recycleview.BaseRecyclerAdapter
    public int a() {
        AppMethodBeat.i(9846, false);
        int size = this.f.size();
        AppMethodBeat.o(9846);
        return size;
    }

    @Override // cn.missfresh.module.base.widget.recycleview.BaseRecyclerAdapter
    public void a(RecyclerView.ViewHolder viewHolder, int i, boolean z) {
        AppMethodBeat.i(9852, false);
        if (viewHolder instanceof a) {
            ((a) viewHolder).a(this.f.get(i));
        }
        AppMethodBeat.o(9852);
    }

    public void a(List<VipExperience.MyInvitesEntity> list) {
        AppMethodBeat.i(9857, false);
        this.f.clear();
        if (!b.a(list)) {
            this.f.addAll(list);
        }
        notifyDataSetChanged();
        AppMethodBeat.o(9857);
    }

    class a extends BaseRecyclerAdapter.SimpleViewHolder {
        private MFRoundedImageView c;
        private TextView d;

        public a(View view) {
            super(view);
            AppMethodBeat.i(9828, false);
            if (view != null) {
                this.c = (MFRoundedImageView) view.findViewById(R.id.tv_vip_experiencer_portrait);
                this.d = (TextView) view.findViewById(R.id.tv_vip_experiencer_name);
            }
            AppMethodBeat.o(9828);
        }

        public void a(VipExperience.MyInvitesEntity myInvitesEntity) {
            AppMethodBeat.i(9831, false);
            if (myInvitesEntity.getIsValid()) {
                d.b(VipExperienceAdapter.this.g, myInvitesEntity.getHead_icon(), this.c);
                this.d.setText(myInvitesEntity.getNick_name());
                this.c.setBackgroundResource(R.drawable.ic_portrait_border);
                this.d.setTextColor(VipExperienceAdapter.this.g.getResources().getColor(R.color.black_474346));
            } else {
                this.c.setImageResource(R.drawable.btn_invite_vip_experience);
                this.d.setText(VipExperienceAdapter.this.g.getString(R.string.pending_invites));
                this.c.setBackground(null);
                this.d.setTextColor(VipExperienceAdapter.this.g.getResources().getColor(R.color.gray_96));
            }
            AppMethodBeat.o(9831);
        }
    }
}
