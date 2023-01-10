package com.sobot.chat.adapter;

import android.view.View;
import com.sobot.chat.adapter.g;

/* access modifiers changed from: package-private */
public class SobotPicListAdapter$SobotFileHolder$1 implements View.OnClickListener {
    final /* synthetic */ g.a a;

    SobotPicListAdapter$SobotFileHolder$1(g.a aVar) {
        this.a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.a.f != null) {
            this.a.f.a(view, this.a.g, 1);
        }
    }
}
