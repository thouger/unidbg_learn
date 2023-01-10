package com.vivo.push.c;

import android.content.Context;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.u;
import com.vivo.push.b.x;
import com.vivo.push.d;
import com.vivo.push.g;
import com.vivo.push.util.NotifyAdapterUtil;
import com.vivo.push.util.n;
import com.vivo.push.util.y;
import java.util.HashMap;

/* compiled from: OnUndoMsgReceiveTask */
public final class r extends o {
    public r(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.BACKUP_SETTINGS, false);
        u uVar = (u) gVar;
        if (d.a().f) {
            if (!a(y.d(this.a), uVar.c != -1 ? String.valueOf(uVar.c) : null, uVar.d)) {
                n.d("OnUndoMsgTask", " vertify msg is error ");
                x xVar = new x(1021);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("messageID", String.valueOf(uVar.e));
                String b = y.b(this.a, this.a.getPackageName());
                if (!TextUtils.isEmpty(b)) {
                    hashMap.put("remoteAppId", b);
                }
                xVar.c = hashMap;
                d.a().a(xVar);
                AppMethodBeat.o(MetricsProto.MetricsEvent.BACKUP_SETTINGS);
                return;
            }
        }
        boolean repealNotifyById = NotifyAdapterUtil.repealNotifyById(this.a, uVar.c);
        n.d("OnUndoMsgTask", "undo message " + uVar.c + ", " + repealNotifyById);
        if (repealNotifyById) {
            Context context = this.a;
            n.b(context, "\u56de\u6536client\u901a\u77e5\u6210\u529f, \u4e0a\u62a5\u57cb\u70b9 1031, messageId = " + uVar.c);
            Context context2 = this.a;
            long j = uVar.c;
            n.d("ClientReportUtil", "report message: " + j + ", reportType: 1031");
            x xVar2 = new x(1031);
            HashMap<String, String> hashMap2 = new HashMap<>();
            hashMap2.put("messageID", String.valueOf(j));
            String b2 = y.b(context2, context2.getPackageName());
            if (!TextUtils.isEmpty(b2)) {
                hashMap2.put("remoteAppId", b2);
            }
            xVar2.c = hashMap2;
            d.a().a(xVar2);
            AppMethodBeat.o(MetricsProto.MetricsEvent.BACKUP_SETTINGS);
            return;
        }
        n.d("OnUndoMsgTask", "undo message fail\uff0cmessageId = " + uVar.c);
        Context context3 = this.a;
        n.c(context3, "\u56de\u6536client\u901a\u77e5\u5931\u8d25\uff0cmessageId = " + uVar.c);
        AppMethodBeat.o(MetricsProto.MetricsEvent.BACKUP_SETTINGS);
    }
}
