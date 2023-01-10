package com.sobot.chat.widget.kpswitch.util;

import android.view.View;
import com.sobot.chat.widget.kpswitch.util.a;

/* access modifiers changed from: package-private */
public class KPSwitchConflictUtil$1 implements View.OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ View b;
    final /* synthetic */ a.AbstractC0146a c;

    KPSwitchConflictUtil$1(View view, View view2, a.AbstractC0146a aVar) {
        this.a = view;
        this.b = view2;
        this.c = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        boolean b = a.b(this.a, this.b);
        a.AbstractC0146a aVar = this.c;
        if (aVar != null) {
            aVar.a(view, b);
        }
    }
}
