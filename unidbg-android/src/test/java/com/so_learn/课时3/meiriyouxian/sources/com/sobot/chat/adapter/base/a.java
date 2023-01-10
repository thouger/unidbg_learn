package com.sobot.chat.adapter.base;

import android.content.Context;
import android.widget.BaseAdapter;
import java.util.List;

/* compiled from: SobotBaseAdapter */
public abstract class a<T> extends BaseAdapter {
    protected List<T> b;
    protected Context c;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return (long) i;
    }

    public a(Context context, List<T> list) {
        this.b = list;
        this.c = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.b.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.b.get(i);
    }

    public List<T> d() {
        return this.b;
    }
}
