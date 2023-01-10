package com.sobot.chat.widget.kpswitch.widget.adpater;

import android.content.Context;
import android.media.TtmlUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sobot.chat.utils.q;
import com.sobot.chat.widget.kpswitch.widget.a.b;
import com.sobot.chat.widget.kpswitch.widget.data.EmoticonPageEntity;
import java.util.ArrayList;

/* compiled from: EmoticonsAdapter */
public class a<T> extends BaseAdapter {
    protected final int a = 2;
    protected final int b;
    protected Context c;
    protected LayoutInflater d;
    protected ArrayList<T> e = new ArrayList<>();
    protected EmoticonPageEntity f;
    protected double g;
    protected int h;
    protected int i;
    protected int j;
    protected int k;
    protected b l;
    protected com.sobot.chat.widget.kpswitch.widget.a.a m;

    /* compiled from: EmoticonsAdapter */
    /* renamed from: com.sobot.chat.widget.kpswitch.widget.adpater.a$a  reason: collision with other inner class name */
    public static class C0147a {
        public View a;
        public LinearLayout b;
        public ImageView c;
        public TextView d;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return (long) i;
    }

    public a(Context context, EmoticonPageEntity emoticonPageEntity, com.sobot.chat.widget.kpswitch.widget.a.a aVar) {
        this.c = context;
        this.d = LayoutInflater.from(context);
        this.f = emoticonPageEntity;
        this.m = aVar;
        this.g = 2.0d;
        this.k = -1;
        int dimension = (int) context.getResources().getDimension(b("sobot_item_emoticon_size_default"));
        this.j = dimension;
        this.b = dimension;
        this.e.addAll(emoticonPageEntity.a());
        a(emoticonPageEntity);
    }

    private void a(EmoticonPageEntity emoticonPageEntity) {
        EmoticonPageEntity.DelBtnStatus d = emoticonPageEntity.d();
        if (!EmoticonPageEntity.DelBtnStatus.GONE.equals(d)) {
            if (EmoticonPageEntity.DelBtnStatus.FOLLOW.equals(d)) {
                this.k = getCount();
                this.e.add(null);
            } else if (EmoticonPageEntity.DelBtnStatus.LAST.equals(d)) {
                int b = emoticonPageEntity.b() * emoticonPageEntity.c();
                while (getCount() < b) {
                    this.e.add(null);
                }
                this.k = getCount() - 1;
            }
        }
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
        C0147a aVar;
        View view2;
        if (view == null) {
            aVar = new C0147a();
            view2 = this.d.inflate(c("sobot_list_item_emoticon"), (ViewGroup) null);
            aVar.a = view2;
            aVar.b = (LinearLayout) view2.findViewById(a("sobot_ly_root"));
            aVar.c = (ImageView) view2.findViewById(a("sobot_iv_emoticon"));
            aVar.d = (TextView) view2.findViewById(a("sobot_tv_emoticon"));
            view2.setTag(aVar);
        } else {
            view2 = view;
            aVar = (C0147a) view.getTag();
        }
        a(i, viewGroup, aVar);
        a(aVar, viewGroup);
        return view2;
    }

    /* access modifiers changed from: protected */
    public void a(int i, ViewGroup viewGroup, C0147a aVar) {
        b bVar = this.l;
        if (bVar != null) {
            bVar.a(i, viewGroup, aVar, this.e.get(i), i == this.k);
        }
    }

    /* access modifiers changed from: protected */
    public void a(C0147a aVar, ViewGroup viewGroup) {
        if (this.b != this.j) {
            aVar.c.setLayoutParams(new LinearLayout.LayoutParams(-1, this.j));
            aVar.d.setLayoutParams(new LinearLayout.LayoutParams(-1, this.j));
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

    public void a(b bVar) {
        this.l = bVar;
    }

    public void a(double d) {
        this.g = d;
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
