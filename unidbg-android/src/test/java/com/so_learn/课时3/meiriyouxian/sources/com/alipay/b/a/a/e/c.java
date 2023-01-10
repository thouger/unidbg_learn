package com.alipay.b.a.a.e;

import com.alipay.b.a.a.a.a;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

class c implements Runnable {
    final /* synthetic */ DataReportRequest a;
    final /* synthetic */ b b;

    c(b bVar, DataReportRequest dataReportRequest) {
        this.b = bVar;
        this.a = dataReportRequest;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            DataReportResult unused = b.e = this.b.c.reportData(this.a);
        } catch (Throwable th) {
            DataReportResult unused2 = b.e = new DataReportResult();
            b.e.success = false;
            DataReportResult dataReportResult = b.e;
            dataReportResult.resultCode = "static data rpc upload error, " + a.a(th);
            new StringBuilder("rpc failed:").append(a.a(th));
        }
    }
}
