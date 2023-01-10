package cn.missfresh.module.base.widget.banner.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.widget.banner.view.a;
import cn.missfresh.module.base.widget.banner.view.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public class BannerPageAdapter<T> extends RecyclingPageAdapter {
    protected List<T> a;
    protected c b;
    private View.OnClickListener c;
    private ImageView d;
    private boolean e = false;
    private List<View> f = new ArrayList();

    public BannerPageAdapter(c cVar, List<T> list) {
        AppMethodBeat.i(23901, false);
        this.b = cVar;
        this.a = list;
        AppMethodBeat.o(23901);
    }

    @Override // cn.missfresh.module.base.widget.banner.adapter.RecyclingPageAdapter
    public View a(int i, View view, ViewGroup viewGroup) {
        a aVar;
        AppMethodBeat.i(23902, false);
        if (view == null) {
            a aVar2 = (a) this.b.b();
            View a = aVar2.a(viewGroup.getContext());
            a.setTag(R.id.cb_item_tag, aVar2);
            if (this.e) {
                this.f.add(a);
            }
            aVar = aVar2;
            view = a;
        } else {
            aVar = (a) view.getTag(R.id.cb_item_tag);
        }
        View.OnClickListener onClickListener = this.c;
        if (onClickListener != null) {
            view.setOnClickListener(onClickListener);
        }
        List<T> list = this.a;
        if (list != null && !list.isEmpty()) {
            aVar.a(viewGroup.getContext(), i, this.a.get(i));
        }
        AppMethodBeat.o(23902);
        return view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        int i = 0;
        AppMethodBeat.i(23903, false);
        List<T> list = this.a;
        if (list != null) {
            i = list.size();
        }
        AppMethodBeat.o(23903);
        return i;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        AppMethodBeat.i(23904, false);
        super.setPrimaryItem(viewGroup, i, obj);
        if (obj instanceof ImageView) {
            this.d = (ImageView) obj;
        }
        AppMethodBeat.o(23904);
    }

    public ImageView a() {
        return this.d;
    }

    public List<View> b() {
        return this.f;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void a(View.OnClickListener onClickListener) {
        this.c = onClickListener;
    }
}
