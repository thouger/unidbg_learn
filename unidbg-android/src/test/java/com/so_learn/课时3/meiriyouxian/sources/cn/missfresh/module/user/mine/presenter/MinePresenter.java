package cn.missfresh.module.user.mine.presenter;

import cn.missfresh.module.base.base.b;
import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.common.event.d;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.RedDotManager;
import cn.missfresh.module.base.manager.bean.MineInfo;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.network.c;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.user.mine.model.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.umeng.analytics.pro.ai;
import de.greenrobot.event.Subscribe;
import java.util.Map;
import okhttp3.Request;

public class MinePresenter extends b {
    private a a;
    private cn.missfresh.module.user.mine.view.a b;
    private boolean c;

    @Subscribe
    public void onHandleEvent(cn.missfresh.module.user.a.a aVar) {
    }

    static /* synthetic */ void a(MinePresenter minePresenter, int i) {
        AppMethodBeat.i(8964, false);
        minePresenter.a(i);
        AppMethodBeat.o(8964);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.mine.presenter.MinePresenter$1  reason: invalid class name */
    public class AnonymousClass1 extends m {
        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void b() {
        }

        AnonymousClass1() {
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a() {
            AppMethodBeat.i(8913, false);
            super.a();
            MinePresenter.this.b.a();
            AppMethodBeat.o(8913);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(8915, false);
            super.a(i);
            MinePresenter.this.b.a("\u670d\u52a1\u5668\u51fa\u9519\uff0c\u8bf7\u91cd\u8bd5");
            AppMethodBeat.o(8915);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(8916, false);
            super.a(request, exc);
            MinePresenter.this.b.a("\u7f51\u7edc\u72b6\u6001\u4e0d\u597d\uff0c\u8bf7\u91cd\u8bd5");
            AppMethodBeat.o(8916);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(8919, false);
            super.a(str);
            cn.missfresh.module.base.network.a c = c(str);
            if (c.a == 0) {
                try {
                    MinePresenter.this.c = true;
                    MineInfo mineInfo = (MineInfo) JSON.parseObject(str, MineInfo.class);
                    MinePresenter.this.a.a(false);
                    if (mineInfo != null) {
                        e.n(mineInfo.is_target_user == 1);
                        e.a(mineInfo.user_member);
                        e.m(mineInfo.bind_phone_tips);
                        e.n(mineInfo.bind_phone_text);
                        e.i("1".equals(mineInfo.is_binding_phone));
                        e.n(mineInfo.member_status);
                        MinePresenter.this.a.a(mineInfo);
                        j.a(mineInfo.is_vip);
                        MinePresenter.this.b.a(mineInfo.new_user_order_bonus);
                        MinePresenter.this.b.a(mineInfo);
                        MinePresenter.a(MinePresenter.this, mineInfo.my_vip_info.getMy_vip_level());
                        RedDotManager.a().a = mineInfo.newArrived;
                        RedDotManager.a().c = mineInfo.member_flag;
                        RedDotManager.a().d = mineInfo.red_bag_flag;
                        RedDotManager.a().e = mineInfo.coupons_flag;
                        RedDotManager.a().b();
                        if (mineInfo.vip_equity != null) {
                            StatisticsManager.g("exposure_member", ai.e, "member", "button_type", mineInfo.vip_equity.button_type);
                        }
                    } else {
                        MinePresenter.this.b.a("\u670d\u52a1\u5668\u51fa\u9519\uff0c\u8bf7\u91cd\u8bd5");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MinePresenter.this.b.a("\u670d\u52a1\u5668\u51fa\u9519\uff0c\u8bf7\u91cd\u8bd5");
                }
            } else {
                MinePresenter.this.b.a(c.b);
            }
            AppMethodBeat.o(8919);
        }
    }

    public void a() {
        AppMethodBeat.i(8934, false);
        c.a(this, i.aI, (Map<String, String>) null, new AnonymousClass1());
        AppMethodBeat.o(8934);
    }

    private void a(int i) {
        AppMethodBeat.i(8937, false);
        if (!e.o() || b().my_vip_info == null) {
            AppMethodBeat.o(8937);
            return;
        }
        e.a(b().my_vip_info.getMy_vip_level());
        AppMethodBeat.o(8937);
    }

    @Subscribe
    public void onHandleEvent(d dVar) {
        AppMethodBeat.i(8945, false);
        a();
        AppMethodBeat.o(8945);
    }

    public MineInfo b() {
        AppMethodBeat.i(8947, false);
        MineInfo a = this.a.a();
        AppMethodBeat.o(8947);
        return a;
    }
}
