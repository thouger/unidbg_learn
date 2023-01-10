package cn.missfresh.module.base.support.modelsupport;

import cn.missfresh.module.base.support.modelsupport.a.a;
import cn.missfresh.module.base.support.modelsupport.a.b;
import cn.missfresh.module.base.support.modelsupport.a.c;
import cn.missfresh.module.base.support.modelsupport.event.EventOrderReqData;
import cn.missfresh.module.base.support.modelsupport.event.EventProductReqData;
import cn.missfresh.module.base.support.modelsupport.event.EventSocialReqData;
import cn.missfresh.module.base.support.modelsupport.event.EventVipReqdata;
import cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class DataHelper {
    private static final String TAG = "DataHelper";
    static DataHelper helper = new DataHelper();
    static boolean inited = false;

    static {
        AppMethodBeat.i(21952, false);
        AppMethodBeat.o(21952);
    }

    public static boolean isInited() {
        return inited;
    }

    public static void init() {
        AppMethodBeat.i(21943, false);
        d.d(TAG, "init...");
        if (!EventBus.getDefault().isRegistered(helper)) {
            EventBus.getDefault().register(helper);
            inited = true;
        }
        AppMethodBeat.o(21943);
    }

    public static void destory() {
        AppMethodBeat.i(21945, false);
        if (EventBus.getDefault().isRegistered(helper)) {
            EventBus.getDefault().unregister(helper);
        }
        AppMethodBeat.o(21945);
    }

    @Subscribe
    public void onHandleEvent(EventVipReqdata eventVipReqdata) {
        AppMethodBeat.i(21946, false);
        d.d(TAG, "handlerReqEvent reqEvent:" + eventVipReqdata);
        new cn.missfresh.module.base.support.modelsupport.a.d().a((BaseReqEvent) eventVipReqdata);
        AppMethodBeat.o(21946);
    }

    @Subscribe
    public void onHandleEvent(EventOrderReqData eventOrderReqData) {
        AppMethodBeat.i(21948, false);
        d.d(TAG, "handlerReqEvent...reqdata:" + eventOrderReqData);
        new a().a((BaseReqEvent) eventOrderReqData);
        AppMethodBeat.o(21948);
    }

    @Subscribe
    public void onHandleEvent(EventProductReqData eventProductReqData) {
        AppMethodBeat.i(21949, false);
        d.d(TAG, "handlerReqEvent...reqdata:" + eventProductReqData);
        new b().a((BaseReqEvent) eventProductReqData);
        AppMethodBeat.o(21949);
    }

    @Subscribe
    public void onHandleEvent(EventSocialReqData eventSocialReqData) {
        AppMethodBeat.i(21950, false);
        d.d(TAG, "handlerReqEvent...reqdata:" + eventSocialReqData);
        new c().a((BaseReqEvent) eventSocialReqData);
        AppMethodBeat.o(21950);
    }
}
