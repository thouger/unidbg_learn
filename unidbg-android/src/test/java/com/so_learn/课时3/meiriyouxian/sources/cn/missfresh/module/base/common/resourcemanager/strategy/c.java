package cn.missfresh.module.base.common.resourcemanager.strategy;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import cn.missfresh.module.base.common.config.BottomTabEnum;
import cn.missfresh.module.base.common.providers.IProductDetailService;
import cn.missfresh.module.base.common.resourcemanager.bean.SkipBean;
import cn.missfresh.module.base.common.resourcemanager.strategy.api.IResourceStrategy;
import cn.missfresh.module.base.manager.bean.ShopingCartBeans;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.module.base.utils.ad;
import cn.missfresh.module.base.utils.f;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.android.arouter.b.a;
import java.util.Map;

/* compiled from: NativeResourceStrategy */
public class c implements IResourceStrategy {
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // cn.missfresh.module.base.common.resourcemanager.strategy.api.IResourceStrategy
    public void skip(SkipBean skipBean, Map<String, Object> map) {
        char c;
        boolean z = false;
        AppMethodBeat.i(12372, false);
        if (skipBean == null || skipBean.type == null) {
            AppMethodBeat.o(12372);
            return;
        }
        String str = skipBean.type;
        switch (str.hashCode()) {
            case -2077709277:
                if (str.equals(SkipBean.Type.SETTINGS)) {
                    c = 11;
                    break;
                }
                c = '\uffff';
                break;
            case -1999289321:
                if (str.equals(SkipBean.Type.NATIVE)) {
                    c = 15;
                    break;
                }
                c = '\uffff';
                break;
            case -1838737501:
                if (str.equals(SkipBean.Type.STORED)) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case -1592831339:
                if (str.equals(SkipBean.Type.SERVICE)) {
                    c = '\n';
                    break;
                }
                c = '\uffff';
                break;
            case -1512624799:
                if (str.equals(SkipBean.Type.MYLOCATION)) {
                    c = '\f';
                    break;
                }
                c = '\uffff';
                break;
            case -1059210693:
                if (str.equals(SkipBean.Type.mypage)) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case -497753438:
                if (str.equals(SkipBean.Type.INTEGRALSHOP)) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case -252458512:
                if (str.equals(SkipBean.Type.ADD_ORDER)) {
                    c = 16;
                    break;
                }
                c = '\uffff';
                break;
            case 3046176:
                if (str.equals(SkipBean.Type.cart)) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 3208415:
                if (str.equals("home")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 5663028:
                if (str.equals(SkipBean.Type.mypacket)) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 50511102:
                if (str.equals("category")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 279254668:
                if (str.equals(SkipBean.Type.OPEN_APP)) {
                    c = 14;
                    break;
                }
                c = '\uffff';
                break;
            case 408508623:
                if (str.equals(SkipBean.Type.PRODUCT)) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case 643426224:
                if (str.equals(SkipBean.Type.mybalance)) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 990389871:
                if (str.equals(SkipBean.Type.SCAN_CODE)) {
                    c = '\r';
                    break;
                }
                c = '\uffff';
                break;
            case 1306345417:
                if (str.equals(SkipBean.Type.COMMUNITY)) {
                    c = '\t';
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        String str2 = "";
        switch (c) {
            case 0:
                a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.INDEX.getPos()).withAction("action_nothing").addFlags(67108864).navigation();
                break;
            case 1:
                if (j.a()) {
                    if (!f.c()) {
                        a.a().a("/base/stored_value").withString("rechargeExplain", str2).withInt("selectIndex", 0).navigation();
                        break;
                    } else {
                        a.a().a("/flutter/router").withString("Router", "recharge").withBoolean("NeedRefresh", true).navigation();
                        break;
                    }
                }
                break;
            case 2:
                if (j.a()) {
                    if (!f.c()) {
                        a.a().a("/base/mybalance").navigation();
                        break;
                    } else {
                        a.a().a("/flutter/router").withString("Router", "balance").navigation();
                        break;
                    }
                }
                break;
            case 3:
                if (j.a()) {
                    a.a().a("/flutter/router").withString("Router", "checkoutCoupon").withString("voucher_type", "2").withString("chromeType", cn.missfresh.module.base.common.config.a.b + str2).navigation();
                    break;
                }
                break;
            case 4:
                a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.INDIVIDUAL_CENTER.getPos()).withAction("action_nothing").addFlags(67108864).navigation();
                break;
            case 5:
                j.c("/order/shoppingcart_activity").withInt("fromType", 1).navigation();
                break;
            case 6:
                a.a().a("/promotion/integral_activity").withFlags(268435456).navigation();
                break;
            case 7:
                if (!b.a(skipBean.link)) {
                    ((IProductDetailService) a.a().a("/product/product_detail_service").navigation()).routerProductDetail(skipBean.link, null, null, 0, "");
                    break;
                }
                break;
            case '\b':
                String str3 = skipBean.link;
                if (str3 != null && !str3.equals(ShopingCartBeans.SynShooingCartGroupRes.ITrans_type.ALL)) {
                    str2 = str3;
                }
                g.a().c(g.a().j());
                a.a().a("/classify/super_activity").withString("internalId", str2).navigation();
                break;
            case '\t':
                if (e.T()) {
                    a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.DELICACY.getPos()).withAction("action_nothing").addFlags(67108864).navigation();
                    break;
                }
                break;
            case '\n':
                a.a().a("/order/custom_service").navigation();
                break;
            case 11:
                a.a().a("/flutter/router").withString("Router", "settings").navigation();
                break;
            case '\f':
                if (j.a()) {
                    j.c("/user/address_list").withInt("cur_address_id", -1).withInt("addressId", -1).withBoolean("selectable", false).withString("addresses_json", str2).withInt("from", 1).withBoolean("isdatasort", false).navigation();
                    break;
                }
                break;
            case '\r':
                if (j.a()) {
                    a.a().a("/other/scan").withString("fromSource", "scan_app").navigation();
                    break;
                }
                break;
            case 14:
                try {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(skipBean.path));
                    intent.addFlags(268435456);
                    Activity b = cn.missfresh.module.base.manager.a.a().b();
                    if (b != null) {
                        if (b.getPackageManager().queryIntentActivities(intent, 65536).size() > 0) {
                            z = true;
                        }
                        if (!z) {
                            cn.missfresh.ui.a.a.a("\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u53bb\u76f8\u5e94\u7684\u5e94\u7528\u5e02\u573a\u8bc4\u5206");
                            break;
                        } else {
                            b.startActivity(ad.a(b, intent));
                            break;
                        }
                    } else {
                        break;
                    }
                } catch (Exception unused) {
                    break;
                }
            case 15:
                if (!b.a(skipBean.path)) {
                    a.a().a(Uri.parse(skipBean.path)).navigation();
                    break;
                }
                break;
            case 16:
                SkipBean.VoucherInfo voucherInfo = skipBean.voucherInfo;
                if (voucherInfo.packetType == 1) {
                    String str4 = voucherInfo.marketingGoal;
                    String str5 = voucherInfo.jumpUrl;
                    if (str4 == null || str5 == null) {
                        if (j.a()) {
                            a.a().a("/flutter/router").withString("Router", "addonitem2").withString("makeOrderType", "2").withString("saleGroupType", voucherInfo.saleGroupType).withString("voucherId", String.valueOf(voucherInfo.voucherId)).withString("from", "voucherInfo").navigation();
                            break;
                        }
                    } else {
                        AppMethodBeat.o(12372);
                        return;
                    }
                }
                break;
        }
        AppMethodBeat.o(12372);
    }
}
