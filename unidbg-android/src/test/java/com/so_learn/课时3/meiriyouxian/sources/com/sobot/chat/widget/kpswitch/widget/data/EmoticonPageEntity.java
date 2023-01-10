package com.sobot.chat.widget.kpswitch.widget.data;

import android.view.View;
import android.view.ViewGroup;
import com.sobot.chat.widget.kpswitch.view.emoticon.EmoticonPageView;
import java.util.List;

public class EmoticonPageEntity<T> extends a<EmoticonPageEntity> {
    private List<T> c;
    private int d;
    private int e;
    private DelBtnStatus f;

    public enum DelBtnStatus {
        GONE,
        FOLLOW,
        LAST;

        public boolean isShow() {
            return !GONE.toString().equals(toString());
        }
    }

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

    public DelBtnStatus d() {
        return this.f;
    }

    public void a(DelBtnStatus delBtnStatus) {
        this.f = delBtnStatus;
    }

    public View a(ViewGroup viewGroup, int i, EmoticonPageEntity emoticonPageEntity) {
        if (this.b != null) {
            return this.b.a(viewGroup, i, this);
        }
        if (e() == null) {
            EmoticonPageView emoticonPageView = new EmoticonPageView(viewGroup.getContext());
            emoticonPageView.setNumColumns(this.e);
            a(emoticonPageView);
        }
        return e();
    }
}
