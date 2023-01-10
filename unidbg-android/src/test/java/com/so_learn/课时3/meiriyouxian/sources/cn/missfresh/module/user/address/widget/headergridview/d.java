package cn.missfresh.module.user.address.widget.headergridview;

import android.media.AudioFormat;
import android.telephony.ServiceState;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: StickyGridHeadersSimpleAdapterWrapper */
public class d extends BaseAdapter implements a {
    private c a;
    private b[] b;

    public d(c cVar) {
        AppMethodBeat.i(6385, false);
        this.a = cVar;
        cVar.registerDataSetObserver(new a(this, (1) null));
        this.b = a(cVar);
        AppMethodBeat.o(6385);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        AppMethodBeat.i(6386, false);
        int count = this.a.getCount();
        AppMethodBeat.o(6386);
        return count;
    }

    public int a(int i) {
        AppMethodBeat.i(6387, false);
        int a = this.b[i].a();
        AppMethodBeat.o(6387);
        return a;
    }

    public View a(int i, View view, ViewGroup viewGroup) {
        AppMethodBeat.i(6388, false);
        View a = this.a.a(this.b[i].b(), view, viewGroup);
        AppMethodBeat.o(6388);
        return a;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        AppMethodBeat.i(6389, false);
        Object item = this.a.getItem(i);
        AppMethodBeat.o(6389);
        return item;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        AppMethodBeat.i(6390, false);
        long itemId = this.a.getItemId(i);
        AppMethodBeat.o(6390);
        return itemId;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        AppMethodBeat.i(ServiceState.RIL_RADIO_CDMA_TECHNOLOGY_BITMASK, false);
        int itemViewType = this.a.getItemViewType(i);
        AppMethodBeat.o(ServiceState.RIL_RADIO_CDMA_TECHNOLOGY_BITMASK);
        return itemViewType;
    }

    public int a() {
        return this.b.length;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        AppMethodBeat.i(6394, false);
        View view2 = this.a.getView(i, view, viewGroup);
        AppMethodBeat.o(6394);
        return view2;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        AppMethodBeat.i(6395, false);
        int viewTypeCount = this.a.getViewTypeCount();
        AppMethodBeat.o(6395);
        return viewTypeCount;
    }

    /* access modifiers changed from: protected */
    public b[] a(c cVar) {
        AppMethodBeat.i(AudioFormat.CHANNEL_OUT_7POINT1_SURROUND, false);
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cVar.getCount(); i++) {
            long a = cVar.a(i);
            b bVar = (b) hashMap.get(Long.valueOf(a));
            if (bVar == null) {
                bVar = new b(this, i);
                arrayList.add(bVar);
            }
            bVar.c();
            hashMap.put(Long.valueOf(a), bVar);
        }
        b[] bVarArr = (b[]) arrayList.toArray(new b[arrayList.size()]);
        AppMethodBeat.o(AudioFormat.CHANNEL_OUT_7POINT1_SURROUND);
        return bVarArr;
    }
}
