package cn.missfresh.module.base.manager;

import android.app.Activity;
import android.view.View;
import cn.missfresh.module.base.common.config.BottomTabEnum;
import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.module.base.support.modelsupport.event.EventOrderResData;
import cn.missfresh.module.base.support.modelsupport.event.EventVipResData;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.android.arouter.b.a;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class PopupManager {
    private Activity a;
    private long b;
    private boolean c = false;

    private void a(EventVipResData.VipInfo vipInfo) {
    }

    private PopupManager() {
    }

    @Subscribe
    public void onHandleEvent(EventVipResData eventVipResData) {
        AppMethodBeat.i(14521, false);
        EventBus.getDefault().unregister(this);
        d.d("PopupManager", "onHandleEvent...EventVipResData");
        if (eventVipResData == null || eventVipResData.getOriginReq() == null || eventVipResData.getOriginReq().getActionId() != this.b) {
            AppMethodBeat.o(14521);
            return;
        }
        d.d("PopupManager", "onHandleEvent...2");
        EventVipResData.VipInfo vipInfo = eventVipResData.getVipInfo();
        if (!(vipInfo == null || vipInfo.getVip_progress() == null || (vipInfo.getVip_progress().getCurrent_order_num() != vipInfo.getVip_progress().getVip_order_num() && !this.c))) {
            a(vipInfo);
        }
        AppMethodBeat.o(14521);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onHandleEvent(EventOrderResData eventOrderResData) {
        EventOrderResData.ChargeSucInfo chargeSucInfo;
        AppMethodBeat.i(14523, false);
        int reqDetailType = eventOrderResData.getOriginReq().getReqDetailType();
        if (reqDetailType != 3) {
            if (reqDetailType == 4 && eventOrderResData.getErrCode() == 0) {
                EventOrderResData.VipTipsRes vipTipsRes = eventOrderResData.getVipTipsRes();
                e.l(true);
                if (vipTipsRes != null && vipTipsRes.isNeed_popup()) {
                    e.a(this.a, vipTipsRes, new AnonymousClass2());
                }
            }
        } else if (eventOrderResData.getErrCode() == 0 && (chargeSucInfo = eventOrderResData.getChargeSucInfo()) != null) {
            e.a(this.a, chargeSucInfo.getVip_content(), chargeSucInfo.getAmount_content(), chargeSucInfo.getShare_content());
        }
        AppMethodBeat.o(14523);
    }

    /* renamed from: cn.missfresh.module.base.manager.PopupManager$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(14518, false);
            if ("MainActivity".equals(PopupManager.this.a.getClass().getSimpleName())) {
                a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.INDEX.getPos()).withAction("action_nothing").addFlags(67108864).navigation();
            } else {
                j.a(PopupManager.this.a, BottomTabEnum.INDEX.getPos(), true);
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(14518);
        }
    }
}
