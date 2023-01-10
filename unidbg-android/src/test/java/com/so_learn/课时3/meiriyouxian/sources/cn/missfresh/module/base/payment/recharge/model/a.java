package cn.missfresh.module.base.payment.recharge.model;

import cn.missfresh.module.base.payment.recharge.bean.Balance;
import cn.missfresh.module.base.payment.recharge.bean.BillingAccount;
import cn.missfresh.module.base.payment.recharge.bean.StoreCardBean;
import com.bangcle.andjni.JniLib;
import java.util.List;

/* compiled from: MyBalanceModel */
public class a {
    private Balance a;
    private BillingAccount b;
    private List<StoreCardBean> c;
    private boolean d;

    public a() {
        JniLib.cV(this, 430);
    }

    public void a(BillingAccount billingAccount) {
        JniLib.cV(this, billingAccount, 429);
    }

    public BillingAccount a() {
        return this.b;
    }

    public Balance b() {
        return this.a;
    }

    public void a(Balance balance) {
        this.a = balance;
    }

    public List<StoreCardBean> c() {
        return this.c;
    }

    public void a(List<StoreCardBean> list) {
        this.c = list;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean d() {
        return this.d;
    }
}
