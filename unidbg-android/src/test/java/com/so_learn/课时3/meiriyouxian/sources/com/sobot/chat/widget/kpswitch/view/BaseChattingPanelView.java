package com.sobot.chat.widget.kpswitch.view;

import android.content.Context;
import android.media.TtmlUtils;
import android.os.Bundle;
import android.view.View;
import com.sobot.chat.utils.q;

public abstract class BaseChattingPanelView {
    protected Context a = null;
    private View b = null;

    public interface a {
    }

    public abstract View a();

    public void a(Bundle bundle) {
    }

    public abstract void a(a aVar);

    public abstract void b();

    public abstract String d();

    public BaseChattingPanelView(Context context) {
        this.a = context;
        this.b = a();
        this.b.setTag(d());
    }

    public View c() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public int a(String str) {
        return q.a(this.a, "id", str);
    }

    /* access modifiers changed from: protected */
    public int b(String str) {
        return q.a(this.a, TtmlUtils.TAG_LAYOUT, str);
    }

    /* access modifiers changed from: protected */
    public int c(String str) {
        return q.a(this.a, "drawable", str);
    }

    /* access modifiers changed from: protected */
    public int d(String str) {
        return q.a(this.a, "integer", str);
    }

    /* access modifiers changed from: protected */
    public int e(String str) {
        return this.a.getResources().getInteger(d(str));
    }

    /* access modifiers changed from: protected */
    public String f(String str) {
        return q.f(this.a, str);
    }
}
