package cn.missfresh.ui.refreshlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.telephony.PreciseDisconnectCause;
import android.util.AttributeSet;
import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MFRefreshLayout extends SmartRefreshLayout {
    public MFRefreshLayout(Context context) {
        super(context);
    }

    public MFRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    @Override // com.scwang.smartrefresh.layout.SmartRefreshLayout, android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        AppMethodBeat.i(PreciseDisconnectCause.LOCAL_CALL_VCC_ON_PROGRESSING, false);
        try {
            boolean drawChild = super.drawChild(canvas, view, j);
            AppMethodBeat.o(PreciseDisconnectCause.LOCAL_CALL_VCC_ON_PROGRESSING);
            return drawChild;
        } catch (Exception unused) {
            AppMethodBeat.o(PreciseDisconnectCause.LOCAL_CALL_VCC_ON_PROGRESSING);
            return true;
        }
    }
}
