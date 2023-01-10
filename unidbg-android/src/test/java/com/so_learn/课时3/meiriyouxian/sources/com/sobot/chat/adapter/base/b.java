package com.sobot.chat.adapter.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sobot.chat.utils.q;
import java.util.List;

/* compiled from: SobotBaseGvAdapter */
public abstract class b<T> extends a {
    protected LayoutInflater a;

    /* access modifiers changed from: protected */
    public abstract a a(Context context, View view);

    /* access modifiers changed from: protected */
    public abstract String a();

    public b(Context context, List<T> list) {
        super(context, list);
        this.a = LayoutInflater.from(context);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: com.sobot.chat.adapter.base.b$a */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = this.a.inflate(q.a(this.c, a()), (ViewGroup) null);
            a a2 = a(this.c, view);
            view.setTag(a2);
            aVar = a2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a(this.b.get(i), i);
        return view;
    }

    /* compiled from: SobotBaseGvAdapter */
    public static abstract class a<T> {
        protected Context a;
        protected View b;

        public abstract void a(T t, int i);

        public a(Context context, View view) {
            this.a = context;
            this.b = view;
        }
    }
}
