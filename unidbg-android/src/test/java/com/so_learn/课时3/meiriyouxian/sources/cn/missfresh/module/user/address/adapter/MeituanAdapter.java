package cn.missfresh.module.user.address.adapter;

import android.content.Context;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.address.adapter.viewholder.ViewHolder;
import cn.missfresh.module.user.address.bean.SupporCityBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class MeituanAdapter extends CommonAdapter<SupporCityBean> {
    @Override // cn.missfresh.module.user.address.adapter.CommonAdapter
    public /* synthetic */ void a(ViewHolder viewHolder, Object obj) {
        AppMethodBeat.i(3264, false);
        a(viewHolder, (SupporCityBean) obj);
        AppMethodBeat.o(3264);
    }

    public MeituanAdapter(Context context, int i, List<SupporCityBean> list) {
        super(context, i, list);
    }

    public void a(ViewHolder viewHolder, SupporCityBean supporCityBean) {
        AppMethodBeat.i(3261, false);
        viewHolder.a(R.id.tvCity, supporCityBean.getName());
        AppMethodBeat.o(3261);
    }
}
