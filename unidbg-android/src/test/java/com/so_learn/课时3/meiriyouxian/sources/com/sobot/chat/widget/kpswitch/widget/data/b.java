package com.sobot.chat.widget.kpswitch.widget.data;

import android.view.View;
import android.view.ViewGroup;
import com.sobot.chat.widget.kpswitch.view.plus.SobotPlusPageView;
import java.util.List;

/* compiled from: PlusPageEntity */
public class b<T> extends a<b> {
    private List<T> c;
    private int d;
    private int e;

    public List<T> a() {
        return this.c;
    }

    public void a(List<T> list) {
        this.c = list;
    }

    public int b() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public int c() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public View a(ViewGroup viewGroup, int i, b bVar) {
        if (this.b != null) {
            return this.b.a(viewGroup, i, this);
        }
        if (e() == null) {
            SobotPlusPageView sobotPlusPageView = new SobotPlusPageView(viewGroup.getContext());
            sobotPlusPageView.setNumColumns(this.e);
            a(sobotPlusPageView);
        }
        return e();
    }
}
