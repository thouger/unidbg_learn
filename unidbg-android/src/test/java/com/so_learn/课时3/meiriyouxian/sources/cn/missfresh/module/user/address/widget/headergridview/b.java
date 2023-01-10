package cn.missfresh.module.user.address.widget.headergridview;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: StickyGridHeadersListAdapterWrapper */
public class b extends BaseAdapter implements a {
    private ListAdapter a;
    private DataSetObserver b = new 1(this);

    public int a() {
        return 0;
    }

    public int a(int i) {
        return 0;
    }

    public View a(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public b(ListAdapter listAdapter) {
        AppMethodBeat.i(6378, false);
        this.a = listAdapter;
        listAdapter.registerDataSetObserver(this.b);
        AppMethodBeat.o(6378);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        AppMethodBeat.i(6379, false);
        int count = this.a.getCount();
        AppMethodBeat.o(6379);
        return count;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        AppMethodBeat.i(6380, false);
        Object item = this.a.getItem(i);
        AppMethodBeat.o(6380);
        return item;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        AppMethodBeat.i(6381, false);
        long itemId = this.a.getItemId(i);
        AppMethodBeat.o(6381);
        return itemId;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        AppMethodBeat.i(6382, false);
        View view2 = this.a.getView(i, view, viewGroup);
        AppMethodBeat.o(6382);
        return view2;
    }
}
