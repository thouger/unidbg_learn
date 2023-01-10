package com.sobot.chat.widget.kpswitch.widget.data;

import android.view.View;
import android.view.ViewGroup;
import com.sobot.chat.widget.kpswitch.widget.a.c;
import com.sobot.chat.widget.kpswitch.widget.data.a;

/* compiled from: PageEntity */
public class a<T extends a> implements c<T> {
    protected View a;
    protected c b;

    public void a(c cVar) {
        this.b = cVar;
    }

    public View e() {
        return this.a;
    }

    public void a(View view) {
        this.a = view;
    }

    @Override // com.sobot.chat.widget.kpswitch.widget.a.c
    public View a(ViewGroup viewGroup, int i, T t) {
        c cVar = this.b;
        if (cVar != null) {
            return cVar.a(viewGroup, i, this);
        }
        return e();
    }
}
