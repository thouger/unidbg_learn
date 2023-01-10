package com.vivo.push.b;

import android.view.WindowManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.umeng.message.proguard.l;
import com.vivo.push.a;
import com.vivo.push.g;
import java.util.HashMap;

/* compiled from: ReporterCommand */
public final class x extends g {
    public HashMap<String, String> c;
    public long d;

    public x() {
        super(WindowManager.LayoutParams.TYPE_INPUT_METHOD_DIALOG);
    }

    public x(long j) {
        this();
        this.d = j;
    }

    @Override // com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2399, false);
        aVar.a("ReporterCommand.EXTRA_PARAMS", this.c);
        aVar.a("ReporterCommand.EXTRA_REPORTER_TYPE", this.d);
        AppMethodBeat.o(2399);
    }

    @Override // com.vivo.push.g
    public final String toString() {
        AppMethodBeat.i(2408, false);
        String str = "ReporterCommand\uff08" + this.d + l.t;
        AppMethodBeat.o(2408);
        return str;
    }

    @Override // com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2404, false);
        this.c = (HashMap) (aVar.a == null ? null : aVar.a.getSerializable("ReporterCommand.EXTRA_PARAMS"));
        this.d = aVar.b("ReporterCommand.EXTRA_REPORTER_TYPE", this.d);
        AppMethodBeat.o(2404);
    }
}
