package cn.missfresh.module.base.support;

import android.text.style.URLSpan;
import android.view.View;
import cn.missfresh.module.base.h5.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ExtendUrlSpan extends URLSpan {
    @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
    public void onClick(View view) {
        AppMethodBeat.i(19950, false);
        a.a(view.getContext(), "", getURL());
        AppMethodBeat.o(19950);
    }
}
