package cn.missfresh.flutter.feature;

import androidx.fragment.app.FragmentActivity;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.module.food.bean.FoodHomeShareInfoBean;
import cn.missfresh.module.food.bean.PublishShareReportBean;
import cn.missfresh.module.food.helper.f;
import cn.missfresh.module.food.providers.IFoodService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;
import com.alibaba.fastjson.JSON;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;
import java.util.Map;

/* compiled from: TalentFlutterFeature */
public class g extends b {
    private FragmentActivity d;
    private IFoodService e = ((IFoodService) a.a().a("/food/service").navigation());

    public g(FragmentActivity fragmentActivity, MethodChannel methodChannel) {
        super(fragmentActivity, methodChannel);
        AppMethodBeat.i(21187, false);
        this.d = fragmentActivity;
        AppMethodBeat.o(21187);
    }

    @Override // cn.missfresh.flutter.feature.b
    public void a(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(21190, false);
        if (methodCall.method.equals("talentDetailShare")) {
            c(methodCall);
        } else if (methodCall.method.equals("hideTalentDetailShare")) {
            this.e.a(b());
        } else if (methodCall.method.equals("talentDetailNotification")) {
            b(methodCall);
        } else if ("talentHomeShare".equals(methodCall.method)) {
            a(methodCall);
        }
        AppMethodBeat.o(21190);
    }

    private void a(MethodCall methodCall) {
        AppMethodBeat.i(21192, false);
        this.e.a(b(), ((Integer) methodCall.argument("cookId")).intValue(), (String) methodCall.argument("itemInfo"));
        AppMethodBeat.o(21192);
    }

    private void b(MethodCall methodCall) {
        boolean z = false;
        AppMethodBeat.i(21194, false);
        String str = (String) methodCall.argument("type");
        if ("AttentionNotification".equals(str)) {
            this.e.a(((Integer) methodCall.argument("authorID")).intValue(), ((Integer) methodCall.argument("relation")).intValue());
        } else if ("RecipeCollectNotification".equals(str)) {
            int intValue = ((Integer) methodCall.argument("cookId")).intValue();
            int intValue2 = ((Integer) methodCall.argument("status")).intValue();
            IFoodService iFoodService = this.e;
            long j = (long) intValue;
            if (intValue2 == 0) {
                z = true;
            }
            iFoodService.b(j, z);
        } else if ("RecipeLikeNotification".equals(str)) {
            int intValue3 = ((Integer) methodCall.argument("cookId")).intValue();
            int intValue4 = ((Integer) methodCall.argument("status")).intValue();
            IFoodService iFoodService2 = this.e;
            long j2 = (long) intValue3;
            if (intValue4 == 0) {
                z = true;
            }
            iFoodService2.a(j2, z);
        }
        AppMethodBeat.o(21194);
    }

    private void c(MethodCall methodCall) {
        AppMethodBeat.i(21196, false);
        if (!e.o()) {
            o.a(1);
            AppMethodBeat.o(21196);
            return;
        }
        Map map = (Map) methodCall.arguments;
        FoodHomeShareInfoBean foodHomeShareInfoBean = (FoodHomeShareInfoBean) JSON.parseObject(JSON.toJSONString(map.get("shareInfo")), FoodHomeShareInfoBean.class);
        int intValue = ((Integer) map.get("cookID")).intValue();
        int intValue2 = ((Integer) map.get("contentType")).intValue();
        if (foodHomeShareInfoBean == null || foodHomeShareInfoBean.shareContent == null) {
            a();
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("cookId", Integer.valueOf(intValue));
            RequestParam requestParam = new RequestParam();
            requestParam.setParam(hashMap);
            this.e.a(requestParam);
            f.b(intValue2, (long) intValue);
            this.e.a(this.d, foodHomeShareInfoBean.shareContent, cn.missfresh.utils.a.a(foodHomeShareInfoBean.channelList) ? null : (PublishShareReportBean) foodHomeShareInfoBean.channelList.get(0), foodHomeShareInfoBean.shareTopTitle, false, new AnonymousClass1(intValue2, foodHomeShareInfoBean));
        }
        AppMethodBeat.o(21196);
    }

    /* compiled from: TalentFlutterFeature */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.flutter.feature.g$1  reason: invalid class name */
    public class AnonymousClass1 implements BaseTipDialog.a {
        final /* synthetic */ int a;
        final /* synthetic */ FoodHomeShareInfoBean b;

        AnonymousClass1(int i, FoodHomeShareInfoBean foodHomeShareInfoBean) {
            this.a = i;
            this.b = foodHomeShareInfoBean;
        }

        @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog.a
        public void a(int i, Object obj) {
            AppMethodBeat.i(21182, false);
            if (i == 0) {
                f.c(this.a);
            } else if (i == 1) {
                f.b(this.a);
            } else if (i == 4 && !cn.missfresh.utils.a.a(this.b.channelList)) {
                a.a().a("/promotion/new_h5event").withString("h5_url", ((PublishShareReportBean) this.b.channelList.get(0)).getJumpUrl()).navigation();
            }
            AppMethodBeat.o(21182);
        }
    }

    private void a() {
        AppMethodBeat.i(21197, false);
        cn.missfresh.ui.a.a.a("\u5206\u4eab\u5931\u8d25");
        AppMethodBeat.o(21197);
    }
}
