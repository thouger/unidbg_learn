package cn.missfresh.module.base.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import androidx.lifecycle.Lifecycle;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.bean.CSLineBean;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.support.modelsupport.CSLineModel;
import cn.missfresh.module.mvp.mvp.interfaces.IModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.fastjson.JSON;
import com.huawei.hms.framework.common.ContainerUtils;
import com.sobot.chat.api.apiUtils.GsonUtil;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.SobotTransferAction;
import com.sobot.chat.listener.c;
import com.sobot.chat.utils.k;
import com.sobot.chat.utils.z;
import java.util.ArrayList;

public class SobotCustomerServiceHelper {
    private static String a = "SobotCustomerServiceHelper";
    private Context b;
    private ProgressDialog c;
    private boolean d = false;
    private CSLineModel e = new CSLineModel();

    public interface a {
        void a(String str);
    }

    static /* synthetic */ String a(SobotCustomerServiceHelper sobotCustomerServiceHelper, String str) {
        AppMethodBeat.i(23441, false);
        String a2 = sobotCustomerServiceHelper.a(str);
        AppMethodBeat.o(23441);
        return a2;
    }

    static /* synthetic */ void a(SobotCustomerServiceHelper sobotCustomerServiceHelper, a aVar, String str) {
        AppMethodBeat.i(23438, false);
        sobotCustomerServiceHelper.a(aVar, str);
        AppMethodBeat.o(23438);
    }

    static /* synthetic */ void a(SobotCustomerServiceHelper sobotCustomerServiceHelper, String str, String str2) {
        AppMethodBeat.i(23440, false);
        sobotCustomerServiceHelper.a(str, str2);
        AppMethodBeat.o(23440);
    }

    static /* synthetic */ String b(SobotCustomerServiceHelper sobotCustomerServiceHelper, String str, String str2) {
        AppMethodBeat.i(23442, false);
        String c = sobotCustomerServiceHelper.c(str, str2);
        AppMethodBeat.o(23442);
        return c;
    }

    static /* synthetic */ void b(SobotCustomerServiceHelper sobotCustomerServiceHelper) {
        AppMethodBeat.i(23439, false);
        sobotCustomerServiceHelper.b();
        AppMethodBeat.o(23439);
    }

    public SobotCustomerServiceHelper(Context context) {
        AppMethodBeat.i(23429, false);
        this.b = context;
        AppMethodBeat.o(23429);
    }

    public void a(Lifecycle lifecycle, boolean z, String str, a aVar) {
        AppMethodBeat.i(23430, false);
        a();
        this.d = z;
        String str2 = "";
        if (!(e.p() == null || e.p().getUser_id() == -1 || e.p().getUser_id() == 0)) {
            str2 = e.p().getUser_id() + str2;
        }
        this.e.a(str2, str, f.a(), new AnonymousClass1(aVar, lifecycle));
        AppMethodBeat.o(23430);
    }

    /* renamed from: cn.missfresh.module.base.utils.SobotCustomerServiceHelper$1  reason: invalid class name */
    class AnonymousClass1 implements IModel.a {
        final /* synthetic */ a a;
        final /* synthetic */ Lifecycle b;

        public void b() {
        }

        AnonymousClass1(a aVar, Lifecycle lifecycle) {
            this.a = aVar;
            this.b = lifecycle;
        }

        public void a() {
            AppMethodBeat.i(23425, false);
            CSLineBean a = SobotCustomerServiceHelper.this.e.a();
            String str = "";
            if (a == null) {
                SobotCustomerServiceHelper.a(SobotCustomerServiceHelper.this, this.a, str);
                SobotCustomerServiceHelper.b(SobotCustomerServiceHelper.this);
            } else if (a.goZc == 1) {
                SobotCustomerServiceHelper.a(SobotCustomerServiceHelper.this, a.skillGroupCode, a.spareGroupCode);
            } else {
                SobotCustomerServiceHelper sobotCustomerServiceHelper = SobotCustomerServiceHelper.this;
                a aVar = this.a;
                if (!b.a(a.desc)) {
                    str = a.desc;
                }
                SobotCustomerServiceHelper.a(sobotCustomerServiceHelper, aVar, str);
            }
            AppMethodBeat.o(23425);
        }

        public void a(int i, String str) {
            AppMethodBeat.i(23426, false);
            SobotCustomerServiceHelper.a(SobotCustomerServiceHelper.this, this.a, "");
            SobotCustomerServiceHelper.b(SobotCustomerServiceHelper.this);
            AppMethodBeat.o(23426);
        }

        public Lifecycle c() {
            return this.b;
        }
    }

    private void a(a aVar, String str) {
        AppMethodBeat.i(23431, false);
        b();
        if (aVar != null) {
            if (b.a(str)) {
                aVar.a("\u975e\u5e38\u62b1\u6b49\uff0c\u7531\u4e8e\u7cfb\u7edf\u5347\u7ea7\uff0c\u6682\u65f6\u65e0\u6cd5\u8054\u7cfb\u5728\u7ebf\u5ba2\u670d\uff0c\u8bf7\u60a8\u7a0d\u540e\u518d\u6b21\u5c1d\u8bd5\uff0c\u6216\u8054\u7cfb\u5ba2\u670d\u70ed\u7ebf10106066");
            } else {
                aVar.a(str);
            }
        }
        AppMethodBeat.o(23431);
    }

    private void a(String str, String str2) {
        AppMethodBeat.i(23432, false);
        k.a(z.a().e());
        com.sobot.chat.b.a(this.b, b(str, str2));
        b();
        AppMethodBeat.o(23432);
    }

    private Information b(String str, String str2) {
        String str3;
        AppMethodBeat.i(23433, false);
        Information information = new Information();
        information.setAppkey("467b3825638b41198f757326e05b8823");
        com.sobot.chat.b.a(this.b.getApplicationContext(), true, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        information.setTel(e.p().getPhone_number());
        try {
            if (e.p().getUser_id() == -1) {
                str3 = "";
            } else {
                str3 = String.valueOf(e.p().getUser_id());
            }
            information.setUid(str3);
        } catch (Exception unused) {
            information.setUid("");
        }
        if (this.d) {
            information.setInitModeType(2);
        }
        information.setArtificialIntelligence(false);
        information.setArtificialIntelligenceNum(20);
        information.setShowSatisfaction(false);
        ArrayList arrayList = new ArrayList();
        if (!b.a(str)) {
            arrayList.add(new SobotTransferAction.Builder().overflow().designatedSkillId(str).conditionIntelligentudgement().Build());
        }
        if (!b.a(str2)) {
            arrayList.add(new SobotTransferAction.Builder().overflow().designatedSkillId(str2).conditionIntelligentudgement().Build());
        }
        if (!b.a(arrayList)) {
            information.setTransferAction(GsonUtil.praseList2Json(arrayList).toString());
        }
        information.setIsVip(e.aa() > 0 ? "1" : "0");
        information.setVip_level("c1ce3e0ce15d45baad7aa5c8a3fde11f");
        com.sobot.chat.b.a(new AnonymousClass2());
        AppMethodBeat.o(23433);
        return information;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.utils.SobotCustomerServiceHelper$2  reason: invalid class name */
    public class AnonymousClass2 implements c {
        @Override // com.sobot.chat.listener.c
        public boolean b(String str) {
            return false;
        }

        AnonymousClass2() {
        }

        @Override // com.sobot.chat.listener.c
        public boolean a(String str) {
            int i = 0;
            AppMethodBeat.i(23427, false);
            if (!b.a(str)) {
                String a = SobotCustomerServiceHelper.a(SobotCustomerServiceHelper.this, str);
                if ("mainApplyingGoodsList".equals(a)) {
                    String b = SobotCustomerServiceHelper.b(SobotCustomerServiceHelper.this, str, "initialIndex");
                    if (!TextUtils.isEmpty(b)) {
                        i = Integer.parseInt(b);
                    }
                    j.d("mainApplyingGoodsList").withInt("initialIndex", i).navigation();
                } else if ("order".equals(a)) {
                    j.a(SobotCustomerServiceHelper.b(SobotCustomerServiceHelper.this, str, "orderId"), SobotCustomerServiceHelper.b(SobotCustomerServiceHelper.this, str, "orderNo"), false, "", "", false, 0, Integer.parseInt(SobotCustomerServiceHelper.b(SobotCustomerServiceHelper.this, str, "action")));
                } else if ("exchangeCoupon".equals(a)) {
                    Postcard withString = com.alibaba.android.arouter.b.a.a().a("/flutter/router").withString("Router", "checkoutCoupon");
                    withString.withString("chromeType", cn.missfresh.module.base.common.config.a.b + "").navigation();
                } else if ("openbill".equals(a)) {
                    com.alibaba.android.arouter.b.a.a().a("/order/invocie").navigation();
                } else {
                    try {
                        j.a((BannerEntity) JSON.parseObject(str, BannerEntity.class), ((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getTopActivity(), (Boolean) false);
                    } catch (Exception unused) {
                        cn.missfresh.module.base.h5.a.a.a(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getTopActivity(), "", str);
                    }
                }
                AppMethodBeat.o(23427);
                return true;
            }
            AppMethodBeat.o(23427);
            return false;
        }

        @Override // com.sobot.chat.listener.c
        public boolean c(String str) {
            AppMethodBeat.i(23428, false);
            if (!b.a(str)) {
                q.a(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getTopActivity(), str);
                AppMethodBeat.o(23428);
                return true;
            }
            AppMethodBeat.o(23428);
            return false;
        }
    }

    private String a(String str) {
        AppMethodBeat.i(23434, false);
        try {
            if (!TextUtils.isEmpty(str)) {
                if (str.contains("skip?type")) {
                    String[] split = str.split("\\?");
                    if (split != null) {
                        if (split.length == 2) {
                            String c = c(split[1], "type");
                            AppMethodBeat.o(23434);
                            return c;
                        }
                    }
                    AppMethodBeat.o(23434);
                    return null;
                }
            }
            AppMethodBeat.o(23434);
            return null;
        } catch (Exception unused) {
            AppMethodBeat.o(23434);
            return null;
        }
    }

    private String c(String str, String str2) {
        AppMethodBeat.i(23435, false);
        try {
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(str2)) {
                    String[] split = str.split("&");
                    if (split != null) {
                        if (split.length != 0) {
                            for (String str3 : split) {
                                String[] split2 = str3.split(ContainerUtils.KEY_VALUE_DELIMITER);
                                if (str2.equals(split2[0])) {
                                    String str4 = split2[1];
                                    AppMethodBeat.o(23435);
                                    return str4;
                                }
                            }
                            AppMethodBeat.o(23435);
                            return null;
                        }
                    }
                    AppMethodBeat.o(23435);
                    return null;
                }
            }
            AppMethodBeat.o(23435);
            return null;
        } catch (Exception unused) {
        }
    }

    private void a() {
        AppMethodBeat.i(23436, false);
        if (this.c == null) {
            this.c = new ProgressDialog(this.b);
        }
        if (!this.c.isShowing()) {
            this.c.show();
        }
        AppMethodBeat.o(23436);
    }

    private void b() {
        AppMethodBeat.i(23437, false);
        ProgressDialog progressDialog = this.c;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.c.dismiss();
        }
        AppMethodBeat.o(23437);
    }
}
