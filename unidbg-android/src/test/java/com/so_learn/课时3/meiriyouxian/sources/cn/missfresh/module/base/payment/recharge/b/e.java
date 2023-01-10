package cn.missfresh.module.base.payment.recharge.b;

import android.content.Intent;
import cn.missfresh.module.base.bean.StoreInfo;
import cn.missfresh.module.base.payment.pwd.view.c;
import cn.missfresh.module.base.payment.recharge.bean.RechargeCard;
import com.bangcle.andjni.JniLib;

/* compiled from: RechargeProxy */
public class e implements c, b {
    private cn.missfresh.module.base.payment.pwd.a.a a;
    private a b;

    /* compiled from: RechargeProxy */
    public interface a {
        void K_();

        StoreInfo a();

        void a(RechargeCard rechargeCard);

        void c_(String str);

        void g_();

        void l();
    }

    public e(a aVar) {
        JniLib.cV(this, aVar, 410);
    }

    @Override // cn.missfresh.module.base.payment.recharge.b.b
    public void a() {
        JniLib.cV(this, 404);
    }

    @Override // cn.missfresh.module.base.payment.recharge.b.b
    public void a(int i, int i2, Intent intent) {
        JniLib.cV(this, Integer.valueOf(i), Integer.valueOf(i2), intent, 405);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.c
    public void a(boolean z, String str, String str2) {
        JniLib.cV(this, Boolean.valueOf(z), str, str2, 406);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.c
    public void a(boolean z, boolean z2, String str, String str2, String str3) {
        JniLib.cV(this, Boolean.valueOf(z), Boolean.valueOf(z2), str, str2, str3, 407);
    }

    @Override // cn.missfresh.module.base.payment.recharge.b.b
    public void b() {
        JniLib.cV(this, 408);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.c
    public void b_(String str) {
        JniLib.cV(this, str, 409);
    }
}
