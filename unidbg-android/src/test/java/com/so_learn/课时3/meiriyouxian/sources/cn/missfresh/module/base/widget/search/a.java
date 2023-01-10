package cn.missfresh.module.base.widget.search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

/* compiled from: SimpleTextAdapter */
class a extends BaseAdapter<String> {
    private int d;

    public a(Context context, int i, List<String> list) {
        super(context, list);
        this.d = i;
    }

    @Override // cn.missfresh.module.base.widget.search.BannerView.a
    public View a(ViewGroup viewGroup) {
        AppMethodBeat.i(24293, false);
        View inflate = this.b.inflate(this.d, viewGroup, false);
        AppMethodBeat.o(24293);
        return inflate;
    }

    @Override // cn.missfresh.module.base.widget.search.BannerView.a
    public void a(View view, int i) {
        AppMethodBeat.i(24294, false);
        ((TextView) view).setText((CharSequence) this.a.get(i));
        AppMethodBeat.o(24294);
    }
}
