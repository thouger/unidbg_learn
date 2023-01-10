package cn.missfresh.module.base.payment.recharge.model;

import cn.missfresh.module.base.payment.recharge.bean.StoreCardBean;
import cn.missfresh.module.base.payment.recharge.bean.StoreCreateOrderBean;
import com.bangcle.andjni.JniLib;
import java.util.List;

/* compiled from: RechargeCardModel */
public class b {
    private List<StoreCardBean> a;
    private StoreCardBean b;
    private int c;
    private StoreCreateOrderBean d;
    private String e;
    private String f;
    private String g;
    private int h;
    private String i;

    public b() {
        JniLib.cV(this, 431);
    }

    public List<StoreCardBean> a() {
        return this.a;
    }

    public void a(List<StoreCardBean> list) {
        this.a = list;
    }

    public StoreCardBean b() {
        return this.b;
    }

    public void a(StoreCardBean storeCardBean) {
        this.b = storeCardBean;
    }

    public String c() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public void a(int i) {
        this.h = i;
    }

    public int d() {
        return this.h;
    }

    public void b(String str) {
        this.i = str;
    }

    public String e() {
        return this.i;
    }

    public StoreCreateOrderBean f() {
        return this.d;
    }

    public void a(StoreCreateOrderBean storeCreateOrderBean) {
        this.d = storeCreateOrderBean;
    }

    public String g() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }

    public int h() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public String i() {
        return this.g;
    }

    public void d(String str) {
        this.g = str;
    }
}
