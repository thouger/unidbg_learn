package cn.missfresh.module.base.utils;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class CommonTools$1$1 implements View.OnClickListener {
    final /* synthetic */ q.AnonymousClass1 a;

    CommonTools$1$1(q.AnonymousClass1 r1) {
        this.a = r1;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(23149, false);
        this.a.a.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + this.a.b)));
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(23149);
    }
}
