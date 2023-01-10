package cn.missfresh.module.base.manager;

import android.os.Handler;
import android.text.TextUtils;
import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.manager.bean.VipCardOrderShowBean;
import cn.missfresh.module.base.network.c;
import cn.missfresh.module.base.network.m;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSON;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* compiled from: VipCardPurchaseFloatTipManager */
public class u implements Runnable {
    private static final u c = new u();
    private String a = getClass().getSimpleName();
    private a b = new a(this, null);
    private boolean d = false;
    private Handler e = new Handler();
    private Random f = new Random();
    private int g;

    static /* synthetic */ void c(u uVar) {
        AppMethodBeat.i(14986, false);
        uVar.e();
        AppMethodBeat.o(14986);
    }

    static /* synthetic */ void d(u uVar) {
        AppMethodBeat.i(14989, false);
        uVar.d();
        AppMethodBeat.o(14989);
    }

    static /* synthetic */ void e(u uVar) {
        AppMethodBeat.i(14991, false);
        uVar.f();
        AppMethodBeat.o(14991);
    }

    static {
        AppMethodBeat.i(14993, false);
        AppMethodBeat.o(14993);
    }

    public static u a() {
        return c;
    }

    private u() {
        AppMethodBeat.i(14968, false);
        d();
        AppMethodBeat.o(14968);
    }

    /* compiled from: VipCardPurchaseFloatTipManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.manager.u$1  reason: invalid class name */
    public class AnonymousClass1 extends m {
        AnonymousClass1() {
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(14950, false);
            if (!TextUtils.isEmpty(str)) {
                d.c(u.this.a + "miss---", str);
                VipCardOrderShowBean vipCardOrderShowBean = (VipCardOrderShowBean) JSON.parseObject(str, VipCardOrderShowBean.class);
                u.this.b.a(vipCardOrderShowBean.getInfo());
                u.this.b.a(vipCardOrderShowBean.getHome_page());
                u.this.b.b(vipCardOrderShowBean.getOther_page());
                u.c(u.this);
            }
            AppMethodBeat.o(14950);
        }
    }

    private void d() {
        AppMethodBeat.i(14970, false);
        d.d(this.a, "===============\u4f1a\u5458\u5361\u8d2d\u4e70\u95f4\u9694\u7f51\u7edc\u6570\u636e\u8bf7\u6c42\u6b63\u5728\u8fdb\u884c===============");
        c.a(this.a, i.f1313cn, (Map<String, String>) null, new AnonymousClass1());
        AppMethodBeat.o(14970);
    }

    public void b() {
        AppMethodBeat.i(14972, false);
        this.g++;
        e();
        AppMethodBeat.o(14972);
    }

    private void e() {
        AppMethodBeat.i(14974, false);
        if (!this.d) {
            this.d = true;
            this.e.postDelayed(this, 3000);
            d.d(this.a, "===============\u4f1a\u5458\u5361\u8d2d\u4e70\u95f4\u9694\u63d0\u793a\u5df2\u542f\u52a8===============");
        }
        AppMethodBeat.o(14974);
    }

    private void f() {
        AppMethodBeat.i(14977, false);
        this.d = false;
        this.e.removeCallbacks(this);
        c.a(this.a);
        d.d(this.a, "===============\u4f1a\u5458\u5361\u8d2d\u4e70\u95f4\u9694\u63d0\u793a\u5df2\u5173\u95ed===============");
        AppMethodBeat.o(14977);
    }

    public void c() {
        AppMethodBeat.i(14979, false);
        this.g--;
        if (this.g == 0) {
            e();
        }
        AppMethodBeat.o(14979);
    }

    @Override // java.lang.Runnable
    public void run() {
        AppMethodBeat.i(14981, false);
        VipCardOrderShowBean.InfoBean a2 = this.b.a();
        if (a2 != null) {
            EventBus.getDefault().post(a2);
        }
        if (this.d) {
            this.e.postDelayed(this, (long) ((this.f.nextInt(3) + 3 + 3) * 1000));
        }
        AppMethodBeat.o(14981);
    }

    /* compiled from: VipCardPurchaseFloatTipManager */
    /* access modifiers changed from: private */
    public class a {
        List<VipCardOrderShowBean.InfoBean> a;
        private int c;
        private int d;

        private a() {
            AppMethodBeat.i(14956, false);
            this.a = new ArrayList();
            AppMethodBeat.o(14956);
        }

        /* synthetic */ a(u uVar, AnonymousClass1 r2) {
            this();
        }

        public void a(int i) {
            this.c = i;
        }

        public void b(int i) {
            this.d = i;
        }

        public void a(List<VipCardOrderShowBean.InfoBean> list) {
            AppMethodBeat.i(14961, false);
            if (list != null && !list.isEmpty()) {
                this.a.addAll(list);
            }
            AppMethodBeat.o(14961);
        }

        public VipCardOrderShowBean.InfoBean a() {
            VipCardOrderShowBean.InfoBean infoBean;
            AppMethodBeat.i(14963, false);
            d.d(u.this.a, "===============\u4f1a\u5458\u5361\u8d2d\u4e70\u95f4\u9694\u63d0\u793a\u6b63\u5728\u8fd0\u884c===============");
            if (this.a.size() > 0) {
                int size = this.a.size() - 1;
                infoBean = this.a.get(size);
                infoBean.setHome_page(this.c);
                infoBean.setOther_page(this.d);
                this.a.remove(size);
                if (size <= 9) {
                    u.d(u.this);
                }
            } else {
                infoBean = new VipCardOrderShowBean.InfoBean();
                infoBean.setHome_page(0);
                infoBean.setOther_page(0);
                u.e(u.this);
            }
            AppMethodBeat.o(14963);
            return infoBean;
        }
    }
}
