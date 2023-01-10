package cn.missfresh.module.base.widget.search;

import android.content.Context;
import android.view.LayoutInflater;
import cn.missfresh.module.base.widget.search.BannerView;
import java.util.List;

public abstract class BaseAdapter<T> extends BannerView.a {
    protected List<T> a;
    protected LayoutInflater b;
    protected Context c;

    public BaseAdapter(Context context, List<T> list) {
        this(context);
        this.a = list;
    }

    public BaseAdapter(Context context) {
        this.c = context;
        this.b = LayoutInflater.from(context);
    }

    public T a(int i) {
        return this.a.get(i);
    }

    @Override // cn.missfresh.module.base.widget.search.BannerView.a
    public int a() {
        List<T> list = this.a;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
