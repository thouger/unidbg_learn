package com.sobot.chat.widget.kpswitch.widget.adpater;

import android.content.Context;
import android.media.TtmlUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sobot.chat.utils.q;
import com.sobot.chat.widget.kpswitch.view.ChattingPanelUploadView;
import com.sobot.chat.widget.kpswitch.widget.a.d;
import java.util.ArrayList;

/* compiled from: PlusAdapter */
public class b<T> extends BaseAdapter {
    protected final int a = 2;
    protected final int b;
    protected Context c;
    protected LayoutInflater d;
    protected ArrayList<T> e = new ArrayList<>();
    protected com.sobot.chat.widget.kpswitch.widget.data.b f;
    protected double g;
    protected int h;
    protected int i;
    protected int j;
    protected d k;
    protected ChattingPanelUploadView.a l;

    /* compiled from: PlusAdapter */
    public static class a {
        public View a;
        public LinearLayout b;
        public TextView c;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return (long) i;
    }

    public b(Context context, com.sobot.chat.widget.kpswitch.widget.data.b bVar, ChattingPanelUploadView.a aVar) {
        this.c = context;
        this.d = LayoutInflater.from(context);
        this.f = bVar;
        this.l = aVar;
        this.g = 2.0d;
        int dimension = (int) context.getResources().getDimension(b("sobot_item_plus_size_default"));
        this.j = dimension;
        this.b = dimension;
        this.e.addAll(bVar.a());
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<T> arrayList = this.e;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        ArrayList<T> arrayList = this.e;
        if (arrayList == null) {
            return null;
        }
        return arrayList.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        View view2;
        if (view == null) {
            aVar = new a();
            view2 = this.d.inflate(c("sobot_list_item_plus_menu"), (ViewGroup) null);
            aVar.a = view2;
            aVar.b = (LinearLayout) view2.findViewById(a("sobot_ly_root"));
            aVar.c = (TextView) view2.findViewById(a("sobot_plus_menu"));
            view2.setTag(aVar);
        } else {
            view2 = view;
            aVar = (a) view.getTag();
        }
        a(i, viewGroup, aVar);
        a(aVar, viewGroup);
        return view2;
    }

    /* access modifiers changed from: protected */
    public void a(int i, ViewGroup viewGroup, a aVar) {
        d dVar = this.k;
        if (dVar != null) {
            dVar.a(i, viewGroup, aVar, this.e.get(i));
        }
    }

    /* access modifiers changed from: protected */
    public void a(a aVar, ViewGroup viewGroup) {
        if (this.b != this.j) {
            aVar.c.setLayoutParams(new LinearLayout.LayoutParams(-1, this.j));
        }
        int i = this.h;
        if (i == 0) {
            i = (int) (((double) this.j) * this.g);
        }
        this.h = i;
        int i2 = this.i;
        if (i2 == 0) {
            i2 = this.j;
        }
        this.i = i2;
        aVar.b.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.max(Math.min(((View) viewGroup.getParent()).getMeasuredHeight() / this.f.b(), this.h), this.i)));
    }

    public void a(d dVar) {
        this.k = dVar;
    }

    public int a(String str) {
        return q.a(this.c, "id", str);
    }

    public int b(String str) {
        return q.a(this.c, "dimen", str);
    }

    public int c(String str) {
        return q.a(this.c, TtmlUtils.TAG_LAYOUT, str);
    }
}
