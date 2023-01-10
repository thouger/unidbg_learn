package cn.missfresh.module.base.order.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: OrderToast */
public class a extends Dialog {
    public static a a(Context context) {
        AppMethodBeat.i(16313, false);
        a aVar = new a(context);
        AppMethodBeat.o(16313);
        return aVar;
    }

    private a(Context context) {
        this(context, R.style.order_loading_dialog);
    }

    public a(Context context, int i) {
        super(context, i);
        AppMethodBeat.i(16314, false);
        setCancelable(false);
        setContentView(View.inflate(context, R.layout.order_upload_production_loading, null));
        AppMethodBeat.o(16314);
    }
}
