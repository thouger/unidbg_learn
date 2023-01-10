package com.alipay.b.a.a.e;

import android.content.Context;
import com.alipay.android.phone.mrpc.core.aa;
import com.alipay.android.phone.mrpc.core.h;
import com.alipay.android.phone.mrpc.core.w;
import com.alipay.b.a.a.a.a;
import com.alipay.tscenter.biz.rpc.deviceFp.BugTrackMessageService;
import com.alipay.tscenter.biz.rpc.report.general.DataReportService;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import org.json.JSONObject;

public class b implements a {
    private static b d;
    private static DataReportResult e;
    private w a = null;
    private BugTrackMessageService b = null;
    private DataReportService c = null;

    private b(Context context, String str) {
        aa aaVar = new aa();
        aaVar.a(str);
        this.a = new h(context);
        this.b = (BugTrackMessageService) this.a.a(BugTrackMessageService.class, aaVar);
        this.c = (DataReportService) this.a.a(DataReportService.class, aaVar);
    }

    public static synchronized b a(Context context, String str) {
        b bVar;
        synchronized (b.class) {
            if (d == null) {
                d = new b(context, str);
            }
            bVar = d;
        }
        return bVar;
    }

    @Override // com.alipay.b.a.a.e.a
    public DataReportResult a(DataReportRequest dataReportRequest) {
        if (dataReportRequest == null) {
            return null;
        }
        if (this.c != null) {
            e = null;
            new Thread(new c(this, dataReportRequest)).start();
            int i = 300000;
            while (e == null && i >= 0) {
                Thread.sleep(50);
                i -= 50;
            }
        }
        return e;
    }

    @Override // com.alipay.b.a.a.e.a
    public boolean a(String str) {
        BugTrackMessageService bugTrackMessageService;
        if (a.a(str) || (bugTrackMessageService = this.b) == null) {
            return false;
        }
        String str2 = null;
        try {
            str2 = bugTrackMessageService.logCollect(a.f(str));
        } catch (Throwable unused) {
        }
        if (!a.a(str2)) {
            return ((Boolean) new JSONObject(str2).get("success")).booleanValue();
        }
        return false;
    }
}
