package cn.missfresh.module.base.utils;

import android.widget.TextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

/* compiled from: TextViewUtil */
public class av {
    public static void a(TextView textView, String str) {
        AppMethodBeat.i(23526, false);
        if (b.a(str)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(str);
        }
        AppMethodBeat.o(23526);
    }
}
