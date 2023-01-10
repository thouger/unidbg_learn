package cn.missfresh.buttomline.logtrace.b;

import android.os.Process;
import android.text.TextUtils;
import cn.missfresh.basiclib.tool.e;
import cn.missfresh.buttomline.logtrace.a.b;
import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.buttomline.logtrace.e.c;
import cn.missfresh.buttomline.logtrace.e.f;

/* compiled from: ILog */
public abstract class a {
    private int a = 0;
    private int b = 0;
    private String c = (System.currentTimeMillis() + "." + a());
    private final c d = new b();

    /* access modifiers changed from: protected */
    public abstract String a();

    public void a(LogBean logBean) {
        logBean.setSeq_id(cn.missfresh.buttomline.logtrace.e.b.a(cn.missfresh.buttomline.logtrace.a.b(), logBean.getTag()));
        logBean.setNet(f.b(cn.missfresh.buttomline.logtrace.a.b()));
        long currentTimeMillis = System.currentTimeMillis();
        logBean.setCt(String.valueOf(currentTimeMillis));
        logBean.setCt_str(e.a(currentTimeMillis));
        logBean.setApp_pid(String.valueOf(Process.myPid()));
        if (TextUtils.isEmpty(logBean.getUid()) && !TextUtils.isEmpty(cn.missfresh.buttomline.logtrace.b.b())) {
            logBean.setUid(cn.missfresh.buttomline.logtrace.b.b());
        }
        if (TextUtils.isEmpty(logBean.getVid()) && !TextUtils.isEmpty(cn.missfresh.buttomline.logtrace.b.c())) {
            logBean.setVid(cn.missfresh.buttomline.logtrace.b.c());
        }
        logBean.setApp_state(c.a().f(cn.missfresh.buttomline.logtrace.a.b()) ? "1" : "0");
    }

    public void a(String str) {
        synchronized (this.d) {
            if (this.a == 100) {
                this.d.a(this.c);
                this.c = System.currentTimeMillis() + "." + a();
                this.a = 0;
            }
            this.d.a(this.c, str);
            this.a++;
        }
    }

    public void b(String str) {
        synchronized (this.d) {
            if (this.b == 0) {
                this.b = cn.missfresh.buttomline.logtrace.e.b.a().getInt("", 1);
                this.c = this.b + "." + a();
            }
            if (this.a == 100) {
                this.b++;
                this.a = 0;
                if (this.b > 10000) {
                    this.b = 1;
                    this.c = this.b + "." + a();
                    cn.missfresh.buttomline.logtrace.e.b.a().edit().putInt("", this.b).apply();
                }
            }
            this.d.b(this.c, str);
            this.a++;
        }
    }
}
