package com.vivo.push.util;

import android.provider.Telephony;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.b.x;
import java.util.HashMap;

/* compiled from: ClientReportUtil */
public final class d {
    public static boolean a(long j, HashMap<String, String> hashMap) {
        AppMethodBeat.i(1079, false);
        x xVar = new x(j);
        xVar.c = hashMap;
        if (xVar.c == null) {
            n.d("ReporterCommand", "reportParams is empty");
        } else {
            StringBuilder sb = new StringBuilder("report message reportType:");
            sb.append(xVar.d);
            sb.append(",msgId:");
            String str = xVar.c.get("messageID");
            if (TextUtils.isEmpty(str)) {
                str = xVar.c.get(Telephony.CellBroadcasts.V1_MESSAGE_IDENTIFIER);
            }
            sb.append(str);
            n.d("ReporterCommand", sb.toString());
        }
        com.vivo.push.d.a().a(xVar);
        AppMethodBeat.o(1079);
        return true;
    }
}
