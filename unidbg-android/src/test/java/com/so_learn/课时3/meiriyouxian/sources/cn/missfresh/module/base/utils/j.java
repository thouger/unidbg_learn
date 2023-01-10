package cn.missfresh.module.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.AddResult;
import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.bean.EventVipChanged;
import cn.missfresh.module.base.bean.NationWideBuyType;
import cn.missfresh.module.base.bean.OrderChromeInfo;
import cn.missfresh.module.base.bean.ParamsBean;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.base.bean.ShoppingCart;
import cn.missfresh.module.base.bean.ShoppingCartInfoCache;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.common.config.BottomTabEnum;
import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.common.providers.IProductDetailService;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.common.resourcemanager.bean.SkipBean;
import cn.missfresh.module.base.helper.f;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.manager.c;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.module.base.manager.j;
import cn.missfresh.module.base.span.ProtocolClickableSpan;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.android.arouter.b.a;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.fastjson.JSONObject;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.support.api.push.PushReceiver;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import de.greenrobot.event.EventBus;
import io.reactivex.q;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: AppUtil */
public class j {
    private static IShoppingCartService2 a;

    public static String a(String str) {
        AppMethodBeat.i(23054, false);
        String str2 = null;
        try {
            ApplicationInfo applicationInfo = ((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication().getPackageManager().getApplicationInfo(((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication().getPackageName(), 128);
            if (applicationInfo != null) {
                str2 = applicationInfo.metaData.getString(str);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(23054);
        return str2;
    }

    public static String a(String str, Object... objArr) {
        AppMethodBeat.i(23055, false);
        if (b.a(str) || objArr == null || objArr.length % 2 != 0) {
            AppMethodBeat.o(23055);
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        if (str.contains("?")) {
            sb.append("&");
        } else {
            sb.append("?");
        }
        for (int i = 0; i < objArr.length; i += 2) {
            if (i != 0) {
                sb.append("&");
            }
            sb.append(objArr[i]);
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append(objArr[i + 1]);
        }
        String sb2 = sb.toString();
        AppMethodBeat.o(23055);
        return sb2;
    }

    public static String a(String str, int i) {
        AppMethodBeat.i(23056, false);
        String str2 = str + "?iopcmd=thumbnail&type=1&scale=" + i;
        AppMethodBeat.o(23056);
        return str2;
    }

    public static synchronized void a(boolean z) {
        synchronized (j.class) {
            AppMethodBeat.i(23057, false);
            if (z != e.A()) {
                e.j(z);
                g.a().a(z);
                EventBus.getDefault().post(new EventVipChanged(z));
            }
            AppMethodBeat.o(23057);
        }
    }

    public static void a(BannerEntity bannerEntity) {
        AppMethodBeat.i(23058, false);
        a(bannerEntity, (Activity) null, "", "", "", "", (Map<String, String>) null, false);
        AppMethodBeat.o(23058);
    }

    public static void a(BannerEntity bannerEntity, Activity activity, Boolean bool) {
        AppMethodBeat.i(23059, false);
        a(bannerEntity, activity, "", "", "", "", (Map<String, String>) null, bool.booleanValue());
        AppMethodBeat.o(23059);
    }

    public static void a(BannerEntity bannerEntity, Activity activity, String str, String str2) {
        AppMethodBeat.i(23060, false);
        a(bannerEntity, activity, str, str2, "");
        AppMethodBeat.o(23060);
    }

    public static void a(BannerEntity bannerEntity, String str, String str2, String str3) {
        AppMethodBeat.i(23061, false);
        a(bannerEntity, (Activity) null, str, str2, str3, "", (Map<String, String>) null, false);
        AppMethodBeat.o(23061);
    }

    public static void a(BannerEntity bannerEntity, Activity activity, String str, String str2, String str3) {
        AppMethodBeat.i(23062, false);
        a(bannerEntity, activity, str, str2, str3, "", (Map<String, String>) null, false);
        AppMethodBeat.o(23062);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static void a(BannerEntity bannerEntity, Activity activity, String str, String str2, String str3, String str4, Map<String, String> map, boolean z) {
        char c;
        String str5;
        String str6;
        int i;
        int intValue;
        AppMethodBeat.i(23064, false);
        if (bannerEntity != null) {
            d.c("Wz", "bannerEntity = " + bannerEntity.getBizFingerprintType());
            String type = bannerEntity.getType();
            if (!b.a(type)) {
                type = type.toUpperCase();
            }
            String link = bannerEntity.getLink();
            switch (type.hashCode()) {
                case -2059933274:
                    if (type.equals("ORDER_CONFIRMATION")) {
                        c = '!';
                        break;
                    }
                    c = '\uffff';
                    break;
                case -2005881285:
                    if (type.equals("MYPAGE")) {
                        c = '\n';
                        break;
                    }
                    c = '\uffff';
                    break;
                case -1983837665:
                    if (type.equals("GOODSAPPLYSALE")) {
                        c = ',';
                        break;
                    }
                    c = '\uffff';
                    break;
                case -1899237172:
                    if (type.equals("ORDERLIST")) {
                        c = 25;
                        break;
                    }
                    c = '\uffff';
                    break;
                case -1881484424:
                    if (type.equals("REFUND")) {
                        c = 22;
                        break;
                    }
                    c = '\uffff';
                    break;
                case -1838737501:
                    if (type.equals(SkipBean.Type.STORED)) {
                        c = '#';
                        break;
                    }
                    c = '\uffff';
                    break;
                case -1663311156:
                    if (type.equals("BIND_PHONE")) {
                        c = '\"';
                        break;
                    }
                    c = '\uffff';
                    break;
                case -1629579888:
                    if (type.equals("DISCOVERY")) {
                        c = 19;
                        break;
                    }
                    c = '\uffff';
                    break;
                case -1571290826:
                    if (type.equals("PRESENT_VIP_CARD")) {
                        c = 17;
                        break;
                    }
                    c = '\uffff';
                    break;
                case -1373502889:
                    if (type.equals("MINECOUPONLIST")) {
                        c = '+';
                        break;
                    }
                    c = '\uffff';
                    break;
                case -1237049959:
                    if (type.equals("COOKDETAIL")) {
                        c = 31;
                        break;
                    }
                    c = '\uffff';
                    break;
                case -1007141701:
                    if (type.equals("INTEGRALACTIVITY")) {
                        c = '\r';
                        break;
                    }
                    c = '\uffff';
                    break;
                case -888850932:
                    if (type.equals("DAREN_AUTHOR")) {
                        c = DateFormat.QUOTE;
                        break;
                    }
                    c = '\uffff';
                    break;
                case -864946288:
                    if (type.equals("MYBALANCE")) {
                        c = 7;
                        break;
                    }
                    c = '\uffff';
                    break;
                case -817746734:
                    if (type.equals("DAREN_DETAIL")) {
                        c = '&';
                        break;
                    }
                    c = '\uffff';
                    break;
                case -776958562:
                    if (type.equals("PUSH_SIMILAR_VIEW")) {
                        c = 29;
                        break;
                    }
                    c = '\uffff';
                    break;
                case -728446820:
                    if (type.equals("TALENT_PUBLISH")) {
                        c = '(';
                        break;
                    }
                    c = '\uffff';
                    break;
                case -707557346:
                    if (type.equals("SUPER_MARKET_HOME")) {
                        c = ' ';
                        break;
                    }
                    c = '\uffff';
                    break;
                case -535721673:
                    if (type.equals("PUSH_SEARCH_VIEW")) {
                        c = 28;
                        break;
                    }
                    c = '\uffff';
                    break;
                case -497753438:
                    if (type.equals(SkipBean.Type.INTEGRALSHOP)) {
                        c = 14;
                        break;
                    }
                    c = '\uffff';
                    break;
                case -378938328:
                    if (type.equals("FIRST_SHOPPING_CART")) {
                        c = '*';
                        break;
                    }
                    c = '\uffff';
                    break;
                case 84303:
                    if (type.equals(SkipBean.Type.URL)) {
                        c = 1;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 84989:
                    if (type.equals("VIP")) {
                        c = 11;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 2223327:
                    if (type.equals("HOME")) {
                        c = 3;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 2448015:
                    if (type.equals("PAGE")) {
                        c = 2;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 278398289:
                    if (type.equals("OPENBILL")) {
                        c = 23;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 408508623:
                    if (type.equals(SkipBean.Type.PRODUCT)) {
                        c = 0;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 426108077:
                    if (type.equals("categoryPage")) {
                        c = 4;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 429581522:
                    if (type.equals("MYCOUPON")) {
                        c = '\b';
                        break;
                    }
                    c = '\uffff';
                    break;
                case 460086252:
                    if (type.equals("SECONDARY_SHOPPING_CART")) {
                        c = 26;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 788289844:
                    if (type.equals("MYPACKET")) {
                        c = '\t';
                        break;
                    }
                    c = '\uffff';
                    break;
                case 812095439:
                    if (type.equals(SkipBean.Type.WEBPROMOTION)) {
                        c = 6;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 833137918:
                    if (type.equals("CATEGORY")) {
                        c = 21;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 914384604:
                    if (type.equals("RECHARGEGIFTS")) {
                        c = 15;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 957982063:
                    if (type.equals("TIPDELIVERY")) {
                        c = 27;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1181776013:
                    if (type.equals("VIPCARD")) {
                        c = 16;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1251256962:
                    if (type.equals("ORDER_DETAIL")) {
                        c = '%';
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1306345417:
                    if (type.equals(SkipBean.Type.COMMUNITY)) {
                        c = 20;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1412457040:
                    if (type.equals("FOOD-UPLOAD")) {
                        c = 30;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1456933091:
                    if (type.equals("CHANNEL")) {
                        c = 5;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1501493869:
                    if (type.equals(SkipBean.Type.LIVEBROADCASTING)) {
                        c = '$';
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1884763460:
                    if (type.equals("MEMBERCARDPLUS")) {
                        c = 18;
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1925798451:
                    if (type.equals("ADDONS")) {
                        c = ')';
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1926325204:
                    if (type.equals("ADVERT")) {
                        c = '\f';
                        break;
                    }
                    c = '\uffff';
                    break;
                case 1963067591:
                    if (type.equals("INVOICEHISTORY")) {
                        c = 24;
                        break;
                    }
                    c = '\uffff';
                    break;
                default:
                    c = '\uffff';
                    break;
            }
            String str7 = "";
            switch (c) {
                case 0:
                    if (!b.a(link)) {
                        BannerEntity.GoToParam goto_param = bannerEntity.getGoto_param();
                        if (goto_param == null) {
                            goto_param = new BannerEntity.GoToParam();
                            goto_param.setProduct_type(0);
                        }
                        HashMap hashMap = new HashMap();
                        hashMap.put("channel", str);
                        hashMap.put("last_page", "page_" + bannerEntity.getName());
                        if (map != null && map.containsKey("lastPage")) {
                            hashMap.put("last_page", map.get("lastPage"));
                        }
                        if (goto_param.getProduct_type() != 0) {
                            a.a().a("/promotion/exchange_product").withString("sku", link).withStringArrayList("statistic_params", q.a(map)).navigation();
                            break;
                        } else {
                            new ArrayList();
                            if (hashMap.size() > 0) {
                                q.a((Map<String, String>) hashMap);
                            }
                            ((IProductDetailService) a.a().a("/product/product_detail_service").navigation()).routerProductDetail(link, "", "", 0, bannerEntity.getActiveSource());
                            break;
                        }
                    }
                    break;
                case 1:
                    if (!b.a(link)) {
                        a.a().a("/promotion/new_h5event").withString("H5_channel", str7).withString("h5_title", bannerEntity.getName()).withString("h5_url", link).withString("H5_promotion_id", str7).withString("STATIC_LABEL", str7).withStringArrayList("STATISTIC_PARAMS", q.a(map)).withString("STATISTIC_POSITION", str7).withString("FROM_SOURCE", str7).navigation();
                        break;
                    }
                    break;
                case 2:
                case 3:
                    a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.INDEX.getPos()).withAction("action_nothing").addFlags(67108864).navigation();
                    break;
                case 4:
                    a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.CLASSIFY.getPos()).withAction("action_nothing").addFlags(67108864).navigation();
                    break;
                case 5:
                    if (("event_page".equals(str2) || "advertising".equals(str2) || "vip_page".equals(str2)) && !b.a(link)) {
                        a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.INDEX.getPos()).withString("INTENT_EXTRA_SUBPOSITION", link).addFlags(67108864).navigation();
                        break;
                    }
                case 6:
                    if (!b.a(link)) {
                        if (b.a(bannerEntity.getName())) {
                            str5 = str7;
                        } else {
                            str5 = "page_" + bannerEntity.getName();
                        }
                        if (SkipBean.Type.mybalance.equals(str3)) {
                            str6 = "jsd-hb-mybalance";
                        } else if ("integral_banner".equals(str3)) {
                            str6 = "jsd-hb-welfare";
                        } else {
                            if ("opening_ad".equals(str3)) {
                                if (!b.a(bannerEntity.getName())) {
                                    str5 = "bomb_" + bannerEntity.getName();
                                    str6 = str7;
                                }
                            } else if (!"pop_task".equals(str3)) {
                                str6 = str;
                            } else if (!b.a(bannerEntity.getName())) {
                                str5 = "pop_task_" + bannerEntity.getName();
                                str6 = str7;
                            }
                            str5 = str7;
                            str6 = str7;
                        }
                        a.a().a("/promotion/new_h5event").withString("H5_channel", str).withString("h5_title", str7).withString("h5_url", link).withString("H5_promotion_id", bannerEntity.getPromotion_id()).withString("STATIC_LABEL", str7).withSerializable("share_info", bannerEntity.getShare_invite_content()).withStringArrayList("STATISTIC_PARAMS", q.a((Map<String, String>) cn.missfresh.module.base.h5.b.a.a(str6, str5))).withString("STATISTIC_POSITION", str4).withString("FROM_SOURCE", (TextUtils.isEmpty(bannerEntity.getName()) || !"\u4fbf\u5229\u8d2d".equals(bannerEntity.getName())) ? str7 : "blg").navigation();
                        break;
                    }
                    break;
                case 7:
                    if (a()) {
                        if (!f.c()) {
                            a.a().a("/base/mybalance").navigation();
                            break;
                        } else {
                            a.a().a("/flutter/router").withString("Router", "balance").navigation();
                            break;
                        }
                    }
                    break;
                case '\b':
                case '\t':
                    if (a()) {
                        a.a().a("/flutter/router").withString("Router", "checkoutCoupon").withString("voucher_type", "2").navigation();
                        break;
                    }
                    break;
                case '\n':
                    a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.INDIVIDUAL_CENTER.getPos()).withAction("action_nothing").addFlags(67108864).navigation();
                    break;
                case 11:
                    a.a().a("/promotion/new_h5event").withString("H5_channel", str7).withString("h5_title", str7).withString("h5_url", i.cE).withString("H5_promotion_id", str7).withString("STATIC_LABEL", str7).withStringArrayList("STATISTIC_PARAMS", q.a(map)).withString("STATISTIC_POSITION", str7).withString("FROM_SOURCE", str7).navigation();
                    break;
                case '\f':
                    a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.INDEX.getPos()).withAction("action_type_addialog").addFlags(67108864).navigation();
                    break;
                case '\r':
                    a.a().a("/promotion/special_event").navigation();
                    break;
                case 14:
                    a.a().a("/promotion/integral_activity").withFlags(268435456).navigation();
                    break;
                case 15:
                    a.a().a("/promotion/recharge_integral").navigation();
                    break;
                case 16:
                    if (a()) {
                        a.a().a("/promotion/new_h5event").withString("H5_channel", str7).withString("h5_title", str7).withString("h5_url", i.cF).withString("H5_promotion_id", str7).withString("STATIC_LABEL", str7).withStringArrayList("STATISTIC_PARAMS", q.a(map)).withString("STATISTIC_POSITION", str7).withString("FROM_SOURCE", str7).navigation();
                        break;
                    }
                    break;
                case 17:
                    if (a()) {
                        a.a().a("/user/vip_experience").navigation();
                        break;
                    }
                    break;
                case 18:
                    if (a()) {
                        a.a().a("/promotion/new_h5event").withString("H5_channel", str7).withString("h5_title", str7).withString("h5_url", i.cE).withString("H5_promotion_id", str7).withString("STATIC_LABEL", str7).withStringArrayList("STATISTIC_PARAMS", q.a(map)).withString("STATISTIC_POSITION", str7).withString("FROM_SOURCE", str7).navigation();
                        break;
                    }
                    break;
                case 19:
                case 20:
                    if (e.T()) {
                        a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.DELICACY.getPos()).withAction("action_nothing").addFlags(67108864).navigation();
                        break;
                    }
                    break;
                case 22:
                    d("orderList").withInt("PARAM_POS", -1).navigation();
                    break;
                case 23:
                    a.a().a("/flutter/router").withString("Router", "invoiceOrder").withInt("taxRate", 1).navigation();
                    break;
                case 24:
                    a.a().a("/flutter/router").withString("Router", "invoiceList").navigation();
                    break;
                case 25:
                    a.a().a("/flutter/router").withString("Router", "orderList").withInt("tabIndex", 0).withBoolean("isNeedJumpToMain", false).navigation();
                    break;
                case 26:
                    c("/order/shoppingcart_activity").withInt("fromType", 1).navigation();
                    break;
                case 27:
                    if (!b.a(bannerEntity.getGoto_param().getOrder_id())) {
                        a.a().a("/flutter/router").withString("Router", "reward").withString("orderId", bannerEntity.getGoto_param().getOrder_id()).withString("rewardType", "0").navigation();
                        break;
                    }
                    break;
                case 28:
                    if (bannerEntity.getGoto_param() != null) {
                        a.a().a("/search/home").withString("hot_word", bannerEntity.getGoto_param().getKeyword()).navigation();
                        break;
                    }
                    break;
                case 29:
                    if (!b.a(link)) {
                        a.a().a("/order/shoppingcart_like").withString("sku", link).navigation();
                        break;
                    }
                    break;
                case 30:
                    a(activity, bannerEntity.getGoto_param_map());
                    break;
                case 31:
                    try {
                        f.a(Long.parseLong(link), "home");
                        break;
                    } catch (Exception e) {
                        d.b("COOKDETAIL", e.getMessage());
                        break;
                    }
                case ' ':
                    a.a().a("/home/market_second").withInt("bizFingerprintType", bannerEntity.getBizFingerprintType()).navigation();
                    break;
                case '!':
                    if (a() && !b.a(bannerEntity.orderConfirmationParam)) {
                        BannerEntity.GoToParam goto_param2 = bannerEntity.getGoto_param();
                        Postcard withInt = d("orderFill").withString("fromOrderConfirmType", (goto_param2 == null || b.a(goto_param2.getFrom())) ? "h5" : goto_param2.getFrom()).withString("from", (goto_param2 == null || b.a(goto_param2.getFrom())) ? "h5" : goto_param2.getFrom()).withString("settleFrom", goto_param2 != null ? goto_param2.getSettleFrom() : str7).withString("productsJson", bannerEntity.orderConfirmationParam).withString("activeSource", bannerEntity.getActiveSource()).withInt("balanceCount", goto_param2 != null ? goto_param2.getBalanceCount() : 0).withInt("saleEvent", goto_param2 != null ? goto_param2.getSaleEvent() : 0);
                        if (goto_param2 != null) {
                            str7 = goto_param2.getSettleFrom();
                        }
                        withInt.withString("page_from", str7).navigation();
                        break;
                    }
                    break;
                case '\"':
                    o.a(false, j.class.getSimpleName());
                    break;
                case '#':
                    if (a()) {
                        BannerEntity.GoToParam goto_param3 = bannerEntity.getGoto_param();
                        if (goto_param3 != null) {
                            String selectIndex = goto_param3.getSelectIndex();
                            i = !TextUtils.isEmpty(selectIndex) ? Integer.parseInt(selectIndex) : 0;
                            str7 = goto_param3.getRechargeExplain();
                        } else {
                            i = 0;
                        }
                        if (!f.c()) {
                            a.a().a("/base/stored_value").withString("rechargeExplain", str7).withInt("selectIndex", i).navigation();
                            break;
                        } else {
                            a.a().a("/flutter/router").withString("Router", "recharge").withBoolean("NeedRefresh", true).withString("rechargeExplain", str7).withString("selectIndex", String.valueOf(i)).navigation();
                            break;
                        }
                    }
                    break;
                case '$':
                    IWXAPI createWXAPI = WXAPIFactory.createWXAPI(((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication(), "wx31562d0a467cb40d");
                    WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                    req.userName = "gh_05c85a53c7ee";
                    req.path = bannerEntity.getLink();
                    req.miniprogramType = cn.missfresh.module.base.common.config.b.c;
                    createWXAPI.sendReq(req);
                    break;
                case '%':
                    try {
                        if (a() && !cn.missfresh.utils.e.a(bannerEntity.getLink())) {
                            String str8 = "0";
                            String[] split = bannerEntity.getLink().split("&");
                            if (split.length > 0) {
                                String[] split2 = split[0].split(ContainerUtils.KEY_VALUE_DELIMITER);
                                if (split2.length > 1) {
                                    str7 = split2[1];
                                }
                            }
                            if (split.length > 1) {
                                String[] split3 = split[1].split(ContainerUtils.KEY_VALUE_DELIMITER);
                                if (split3.length > 1) {
                                    str8 = split3[1];
                                }
                            }
                            a(str8, str7, false, "", "", 0);
                            break;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        break;
                    }
                case '&':
                    if (bannerEntity.getGoto_param_map() != null) {
                        JSONObject goto_param_map = bannerEntity.getGoto_param_map();
                        d.c("Banner", " goto param " + goto_param_map.toJSONString());
                        long longValue = goto_param_map.getLong("cookId").longValue();
                        String string = goto_param_map.getString(PushReceiver.PushMessageThread.MSGTYPE);
                        int intValue2 = goto_param_map.getInteger("detailType").intValue();
                        if (longValue <= 0) {
                            AppMethodBeat.o(23064);
                            return;
                        } else if (1 == intValue2) {
                            f.b(longValue, string);
                        } else if (2 == intValue2) {
                            f.a(longValue, string);
                        }
                    }
                case '\'':
                    if (bannerEntity.getGoto_param_map() != null && (intValue = bannerEntity.getGoto_param_map().getInteger("userId").intValue()) > 0) {
                        f.a(intValue, 0);
                        break;
                    }
                case '(':
                    f.a(null);
                    break;
                case ')':
                    JSONObject goto_param_map2 = bannerEntity.getGoto_param_map();
                    int i2 = -1;
                    int i3 = -1;
                    if (goto_param_map2 != null) {
                        if (goto_param_map2.containsKey("promotionId")) {
                            i2 = goto_param_map2.getIntValue("promotionId");
                        }
                        if (goto_param_map2.containsKey("exchangeType")) {
                            i3 = goto_param_map2.getIntValue("exchangeType");
                        }
                    }
                    Postcard withInt2 = a.a().a("/flutter/router").withString("Router", "addonitem2").withString("makeOrderType", goto_param_map2 != null ? goto_param_map2.getString("makeOrderType") : str7).withString("saleGroupType", goto_param_map2 != null ? goto_param_map2.getString("saleGroupType") : str7).withString("voucherId", goto_param_map2 != null ? goto_param_map2.getString("voucherId") : str7).withInt("promotionId", i2).withInt("exchangeType", i3);
                    if (goto_param_map2 != null) {
                        str7 = goto_param_map2.getString("from");
                    }
                    withInt2.withString("from", str7).navigation();
                    break;
                case '*':
                    a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.CART.getPos()).withAction("action_nothing").addFlags(67108864).navigation();
                    break;
                case '+':
                    if (a()) {
                        JSONObject goto_param_map3 = bannerEntity.getGoto_param_map();
                        a.a().a("/flutter/router").withString("Router", "checkoutCoupon").withString("voucher_type", goto_param_map3 != null ? goto_param_map3.getString("voucherType") : "2").navigation();
                        break;
                    }
                    break;
                case ',':
                    if (a()) {
                        String str9 = null;
                        String str10 = null;
                        JSONObject goto_param_map4 = bannerEntity.getGoto_param_map();
                        if (goto_param_map4 != null) {
                            str7 = goto_param_map4.getString("orderNo");
                            str9 = goto_param_map4.getString("scrollToHistory");
                            str10 = goto_param_map4.getString("from");
                        }
                        a.a().a("/flutter/router").withString("Router", "goodsApplySale").withString("orderNo", str7).withString("scrollToHistory", str9).withString("from", str10).navigation();
                        break;
                    }
                    break;
            }
        }
        AppMethodBeat.o(23064);
    }

    private static void a(Activity activity, JSONObject jSONObject) {
        boolean z = false;
        AppMethodBeat.i(23065, false);
        if (jSONObject != null) {
            Boolean bool = jSONObject.getBoolean("edit");
            if (bool != null) {
                z = bool.booleanValue();
            }
            Long l = jSONObject.getLong("workId");
            long longValue = l != null ? l.longValue() : -1;
            Long l2 = jSONObject.getLong("cookId");
            long longValue2 = l2 != null ? l2.longValue() : -1;
            Long l3 = jSONObject.getLong("activityId");
            long longValue3 = l3 != null ? l3.longValue() : -1;
            if (activity instanceof j.a) {
                cn.missfresh.module.base.manager.j.a("page_food_vote", (j.a) activity);
            }
            if (z) {
                f.a(null);
            } else {
                if (longValue <= 0) {
                    longValue = -1;
                }
                f.a(longValue, longValue3 > 0 ? longValue3 : -1, longValue2 > 0 ? longValue2 : -1, 2);
            }
        }
        AppMethodBeat.o(23065);
    }

    public static void a(Activity activity, String str) {
        AppMethodBeat.i(23066, false);
        if (SkipBean.Type.mybalance.equalsIgnoreCase(str)) {
            if (a()) {
                if (f.c()) {
                    a.a().a("/flutter/router").withString("Router", "balance").navigation(activity, 10);
                } else {
                    a.a().a("/base/mybalance").navigation(activity, 10);
                }
            }
        } else if ("exchange".equalsIgnoreCase(str)) {
            cn.missfresh.module.base.h5.a.a.a(activity, "", i.cE);
        } else if ("evaluateList".equalsIgnoreCase(str)) {
            if (e.o()) {
                a.a().a("/flutter/router").withString("Router", "orderList").withInt("tabIndex", 4).withBoolean("isNeedJumpToMain", false).navigation();
            }
        } else if ("home".equalsIgnoreCase(str)) {
            a(activity, BottomTabEnum.INDEX.getPos(), false);
        }
        AppMethodBeat.o(23066);
    }

    public static boolean a() {
        AppMethodBeat.i(23067, false);
        if (e.o()) {
            AppMethodBeat.o(23067);
            return true;
        }
        o.a(1);
        AppMethodBeat.o(23067);
        return false;
    }

    public static void a(String str, int i, boolean z) {
        AppMethodBeat.i(23070, false);
        a("", str).a(io.reactivex.a.b.a.a()).subscribe(new AnonymousClass1(null));
        AppMethodBeat.o(23070);
    }

    /* compiled from: AppUtil */
    /* renamed from: cn.missfresh.module.base.utils.j$1  reason: invalid class name */
    static class AnonymousClass1 extends cn.missfresh.module.base.network.i<AddResult> {
        public void a(AddResult addResult) {
        }

        @Override // io.reactivex.v
        public void onComplete() {
        }

        AnonymousClass1(cn.missfresh.module.mvp.a.a aVar) {
            super(aVar);
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(23050, false);
            a((AddResult) obj);
            AppMethodBeat.o(23050);
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            AppMethodBeat.i(23049, false);
            d.d("tag", "addProductToSCByExchange, code=" + i + ", " + str);
            AppMethodBeat.o(23049);
        }
    }

    public static q<AddResult> a(String str, String str2) {
        AppMethodBeat.i(23071, false);
        q<AddResult> a2 = a(str, str2, 1);
        AppMethodBeat.o(23071);
        return a2;
    }

    public static q<AddResult> a(String str, String str2, int i) {
        AppMethodBeat.i(23072, false);
        if (b.a(str2)) {
            q<AddResult> d = q.d();
            AppMethodBeat.o(23072);
            return d;
        }
        b();
        IShoppingCartService2 iShoppingCartService2 = a;
        if (iShoppingCartService2 != null) {
            q<AddResult> a2 = iShoppingCartService2.a(str, str2, 1);
            AppMethodBeat.o(23072);
            return a2;
        }
        q<AddResult> d2 = q.d();
        AppMethodBeat.o(23072);
        return d2;
    }

    public static void b() {
        AppMethodBeat.i(23073, false);
        if (a == null) {
            a = (IShoppingCartService2) a.a().a("/order/new_shoppingcart_service").navigation();
        }
        AppMethodBeat.o(23073);
    }

    public static boolean a(ProductsEntity productsEntity, String str) {
        int i = 0;
        AppMethodBeat.i(23074, false);
        if (productsEntity == null || b.a(productsEntity.getSku())) {
            AppMethodBeat.o(23074);
            return false;
        }
        b();
        IShoppingCartService2 iShoppingCartService2 = a;
        if (iShoppingCartService2 != null) {
            i = iShoppingCartService2.b(productsEntity.getSku());
        }
        boolean a2 = a(g.a().c(), productsEntity.getBuy_permission(), str, i + 1, productsEntity.getProductLimit(), productsEntity.getVip_product(), productsEntity.getSeckill_limit());
        AppMethodBeat.o(23074);
        return a2;
    }

    public static boolean a(int i, int i2, String str, int i3, int i4, boolean z, int i5) {
        AppMethodBeat.i(23075, false);
        if (i2 > 0 || z) {
            if (i2 > i && !"notCheck".equals(str)) {
                if (b.a(str)) {
                    str = "\u6b63\u5f0f\u4f1a\u5458\u4e13\u4eab\uff0c\u5347\u7ea7\u540e\u53ef\u8d2d\u4e70";
                }
                cn.missfresh.ui.a.a.a(str);
                AppMethodBeat.o(23075);
                return false;
            } else if (z && i3 > i5) {
                cn.missfresh.ui.a.a.a(String.format("\u8be5\u5546\u54c1\u9650\u8d2d%s\u4ef6", Integer.valueOf(i5)));
                AppMethodBeat.o(23075);
                return false;
            }
        }
        if (i3 > i4) {
            cn.missfresh.ui.a.a.a(String.format("\u8be5\u5546\u54c1\u53ea\u5269%s\u4ef6", Integer.valueOf(i4)));
            AppMethodBeat.o(23075);
            return false;
        }
        AppMethodBeat.o(23075);
        return true;
    }

    public static boolean a(int i, int i2, int i3) {
        AppMethodBeat.i(23076, false);
        if (i3 != 0 && i3 < i2 && i > i3) {
            cn.missfresh.ui.a.a.a(String.format("\u8be5\u5546\u54c1\u9650\u8d2d%s\u4ef6", Integer.valueOf(i3)));
            AppMethodBeat.o(23076);
            return false;
        } else if (i > i2) {
            cn.missfresh.ui.a.a.a(String.format("\u8be5\u5546\u54c1\u53ea\u5269%s\u4ef6", Integer.valueOf(i2)));
            AppMethodBeat.o(23076);
            return false;
        } else {
            AppMethodBeat.o(23076);
            return true;
        }
    }

    public static String c() {
        AppMethodBeat.i(23079, false);
        String string = ((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication().getResources().getString(R.string.net_work_error);
        AppMethodBeat.o(23079);
        return string;
    }

    public static int a(int i, int i2, OrderChromeInfo orderChromeInfo) {
        AppMethodBeat.i(23080, false);
        if (i == NationWideBuyType.NONE.getType()) {
            AppMethodBeat.o(23080);
            return -1;
        } else if (i2 == 1) {
            AppMethodBeat.o(23080);
            return i2;
        } else if (i2 == 2) {
            AppMethodBeat.o(23080);
            return i2;
        } else if (i2 == 5) {
            AppMethodBeat.o(23080);
            return i2;
        } else if (!c.c()) {
            AppMethodBeat.o(23080);
            return 272;
        } else if (orderChromeInfo.delivery_time <= 7200) {
            AppMethodBeat.o(23080);
            return 273;
        } else {
            AppMethodBeat.o(23080);
            return 274;
        }
    }

    public static ShoppingCartInfoCache b(String str) {
        AppMethodBeat.i(23081, false);
        ShoppingCartInfoCache shoppingCartInfoCache = new ShoppingCartInfoCache();
        b();
        IShoppingCartService2 iShoppingCartService2 = a;
        if (iShoppingCartService2 != null) {
            shoppingCartInfoCache.totalQuantity = iShoppingCartService2.b(str);
        }
        AppMethodBeat.o(23081);
        return shoppingCartInfoCache;
    }

    public static void a(Activity activity, Uri uri) {
        AppMethodBeat.i(23082, false);
        try {
            Set<String> queryParameterNames = uri.getQueryParameterNames();
            Bundle bundle = null;
            if (queryParameterNames != null && queryParameterNames.size() > 0) {
                bundle = new Bundle();
                for (String str : queryParameterNames) {
                    bundle.putString(str, uri.getQueryParameter(str));
                }
            }
            a(activity, bundle, 2);
        } catch (Exception e) {
            d.a("AppUtil", e);
        }
        AppMethodBeat.o(23082);
    }

    public static List<? extends ShoppingCart> a(List<? extends ShoppingCart> list) {
        AppMethodBeat.i(23083, false);
        if (!b.a(list)) {
            for (ShoppingCart shoppingCart : list) {
                shoppingCart.setQuantity(shoppingCart.getQuantity());
            }
        }
        AppMethodBeat.o(23083);
        return list;
    }

    public static boolean a(boolean z, Intent intent) {
        boolean z2 = false;
        AppMethodBeat.i(23085, false);
        if (intent == null) {
            AppMethodBeat.o(23085);
            return false;
        }
        if (!z && intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(intent.getAction())) {
            z2 = true;
        }
        AppMethodBeat.o(23085);
        return z2;
    }

    public static void a(Activity activity) {
        AppMethodBeat.i(23086, false);
        if (!q.a(activity)) {
            AppMethodBeat.o(23086);
            return;
        }
        a.a().a("/promotion/user_protocol").withString("h5_url", i.bW).navigation();
        AppMethodBeat.o(23086);
    }

    public static void b(Activity activity) {
        AppMethodBeat.i(23087, false);
        if (!q.a(activity)) {
            AppMethodBeat.o(23087);
            return;
        }
        a.a().a("/promotion/user_protocol").withString("h5_url", i.bY).navigation();
        AppMethodBeat.o(23087);
    }

    public static void a(Activity activity, TextView textView, String str) {
        AppMethodBeat.i(23088, false);
        if (textView == null || b.a(str) || !q.a(activity)) {
            AppMethodBeat.o(23088);
            return;
        }
        a(activity, textView, str, activity.getString(R.string.user_privacy_protocal_desc), activity.getString(R.string.missfresh_privacy_protocal_desc));
        AppMethodBeat.o(23088);
    }

    public static void c(Activity activity) {
        AppMethodBeat.i(23089, false);
        if (!q.a(activity)) {
            AppMethodBeat.o(23089);
            return;
        }
        a.a().a("/promotion/user_protocol").withString("h5_url", i.bX).navigation();
        AppMethodBeat.o(23089);
    }

    public static void b(Activity activity, TextView textView, String str) {
        AppMethodBeat.i(23090, false);
        if (textView == null || b.a(str) || !q.a(activity)) {
            AppMethodBeat.o(23090);
            return;
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        Matcher matcher = Pattern.compile(activity.getString(R.string.user_stored_content_desc)).matcher(str);
        SpannableString spannableString = new SpannableString(str);
        while (matcher.find()) {
            spannableString.setSpan(new AnonymousClass2(activity), matcher.start(), matcher.end(), 33);
        }
        textView.setText(spannableString);
        AppMethodBeat.o(23090);
    }

    /* compiled from: AppUtil */
    /* renamed from: cn.missfresh.module.base.utils.j$2  reason: invalid class name */
    static class AnonymousClass2 extends ClickableSpan {
        final /* synthetic */ Activity a;

        AnonymousClass2(Activity activity) {
            this.a = activity;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            AppMethodBeat.i(23051, false);
            j.c(this.a);
            AppMethodBeat.o(23051);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            AppMethodBeat.i(23052, false);
            super.updateDrawState(textPaint);
            AppMethodBeat.o(23052);
        }
    }

    public static void a(Activity activity, TextView textView, String str, String str2, String str3) {
        AppMethodBeat.i(23091, false);
        a(activity, textView, str, str2, str3, 0);
        AppMethodBeat.o(23091);
    }

    public static void a(Activity activity, TextView textView, String str, String str2, String str3, int i) {
        AppMethodBeat.i(23092, false);
        if (textView == null || b.a(str) || !q.a(activity)) {
            AppMethodBeat.o(23092);
            return;
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        Matcher matcher = Pattern.compile(str2).matcher(str);
        Matcher matcher2 = Pattern.compile(str3).matcher(str);
        SpannableString spannableString = new SpannableString(str);
        if (i == 1) {
            spannableString.setSpan(new StyleSpan(1), 108, 119, 33);
        }
        a(activity, matcher, spannableString, 0);
        a(activity, matcher2, spannableString, 1);
        textView.setText(spannableString);
        AppMethodBeat.o(23092);
    }

    private static void a(Activity activity, Matcher matcher, SpannableString spannableString, int i) {
        AppMethodBeat.i(23094, false);
        while (matcher.find()) {
            spannableString.setSpan(new ProtocolClickableSpan(i, new AnonymousClass3(activity)), matcher.start(), matcher.end(), 33);
        }
        AppMethodBeat.o(23094);
    }

    /* compiled from: AppUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.utils.j$3  reason: invalid class name */
    public static class AnonymousClass3 implements ProtocolClickableSpan.a {
        final /* synthetic */ Activity a;

        AnonymousClass3(Activity activity) {
            this.a = activity;
        }

        @Override // cn.missfresh.module.base.span.ProtocolClickableSpan.a
        public void a(int i) {
            AppMethodBeat.i(23053, false);
            if (i == 0) {
                j.a(this.a);
            } else if (1 == i) {
                j.b(this.a);
            }
            AppMethodBeat.o(23053);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
        if (r6.equals(android.Manifest.permission.READ_CONTACTS) != false) goto L_0x009a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r5, java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 332
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.utils.j.a(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
        if (r6.equals(android.Manifest.permission.READ_CONTACTS) != false) goto L_0x009a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(android.content.Context r5, java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 332
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.utils.j.b(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
        if (r8.equals(android.Manifest.permission.READ_CONTACTS) != false) goto L_0x0054;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int c(android.content.Context r7, java.lang.String r8) {
        /*
            r7 = 0
            r0 = 23097(0x5a39, float:3.2366E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r7)
            boolean r1 = android.text.TextUtils.isEmpty(r8)
            if (r1 == 0) goto L_0x0010
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r7
        L_0x0010:
            r1 = -1
            int r2 = r8.hashCode()
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            switch(r2) {
                case -1888586689: goto L_0x0048;
                case -63024214: goto L_0x003d;
                case -5573545: goto L_0x0032;
                case 214526995: goto L_0x0027;
                case 1977429404: goto L_0x001d;
                default: goto L_0x001c;
            }
        L_0x001c:
            goto L_0x0053
        L_0x001d:
            java.lang.String r2 = "android.permission.READ_CONTACTS"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x0053
            goto L_0x0054
        L_0x0027:
            java.lang.String r7 = "android.permission.WRITE_CONTACTS"
            boolean r7 = r8.equals(r7)
            if (r7 == 0) goto L_0x0053
            r7 = r6
            goto L_0x0054
        L_0x0032:
            java.lang.String r7 = "android.permission.READ_PHONE_STATE"
            boolean r7 = r8.equals(r7)
            if (r7 == 0) goto L_0x0053
            r7 = r3
            goto L_0x0054
        L_0x003d:
            java.lang.String r7 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r7 = r8.equals(r7)
            if (r7 == 0) goto L_0x0053
            r7 = r4
            goto L_0x0054
        L_0x0048:
            java.lang.String r7 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r7 = r8.equals(r7)
            if (r7 == 0) goto L_0x0053
            r7 = r5
            goto L_0x0054
        L_0x0053:
            r7 = r1
        L_0x0054:
            if (r7 == 0) goto L_0x0072
            if (r7 == r6) goto L_0x0072
            if (r7 == r5) goto L_0x006c
            if (r7 == r4) goto L_0x006c
            if (r7 == r3) goto L_0x0066
            int r7 = cn.missfresh.basiclib.utils.permission.c.b(r8)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r7
        L_0x0066:
            int r7 = cn.missfresh.module.base.R.drawable.icon_permission_phone
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r7
        L_0x006c:
            int r7 = cn.missfresh.module.base.R.drawable.icon_permission_location
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r7
        L_0x0072:
            int r7 = cn.missfresh.module.base.R.drawable.icon_permission_storage
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r7
            switch-data {-1888586689->0x0048, -63024214->0x003d, -5573545->0x0032, 214526995->0x0027, 1977429404->0x001d, }
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.utils.j.c(android.content.Context, java.lang.String):int");
    }

    public static void a(Activity activity, BannerEntity bannerEntity, ParamsBean paramsBean, String str, String str2) {
        AppMethodBeat.i(23098, false);
        if (bannerEntity == null) {
            AppMethodBeat.o(23098);
            return;
        }
        if (!"NEWUSER".equalsIgnoreCase(bannerEntity.getType())) {
            a(bannerEntity, activity, str, str2);
        } else if (paramsBean != null && !b.a(paramsBean.getUrl())) {
            if (!e.o()) {
                a.a().a("/main/new_talent").withString("EXTRAL_NEW_TALENT_TITLE", bannerEntity.getName()).withString("EXTRAL_IMG_URL", paramsBean.getImage()).withString("EXTRAL_H5_URL", paramsBean.getUrl()).withString("EXTRAL_LOGIN_DESCRIPT", "").navigation();
            } else {
                cn.missfresh.module.base.h5.a.a.a(activity, "", bannerEntity.getName(), paramsBean.getUrl(), "", null, "", null);
            }
        }
        AppMethodBeat.o(23098);
    }

    public static int d() {
        AppMethodBeat.i(23099, false);
        b();
        IShoppingCartService2 iShoppingCartService2 = a;
        if (iShoppingCartService2 != null) {
            int a2 = iShoppingCartService2.a();
            AppMethodBeat.o(23099);
            return a2;
        }
        AppMethodBeat.o(23099);
        return 0;
    }

    public static boolean b(int i, int i2, int i3) {
        AppMethodBeat.i(23100, false);
        if (i3 != 0 && i3 < i2 && i > i3) {
            cn.missfresh.ui.a.a.a(String.format("\u62b1\u6b49\uff0c\u6570\u91cf\u6709\u9650\uff0c\u60a8\u6bcf\u5929\u4ec5\u80fd\u8d2d\u4e70%s\u4ef6", Integer.valueOf(i3)));
            AppMethodBeat.o(23100);
            return false;
        } else if (i > i2) {
            cn.missfresh.ui.a.a.a(String.format("\u53ea\u5269%s\u4efd\u5566", Integer.valueOf(i2)));
            AppMethodBeat.o(23100);
            return false;
        } else {
            AppMethodBeat.o(23100);
            return true;
        }
    }

    public static String a(Context context) {
        AppMethodBeat.i(23101, false);
        String a2 = al.a(context);
        AppMethodBeat.o(23101);
        return a2;
    }

    public static double b(Context context) {
        AppMethodBeat.i(23102, false);
        try {
            double refreshRate = (double) ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRefreshRate();
            AppMethodBeat.o(23102);
            return refreshRate;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(23102);
            return 60.0d;
        }
    }

    public static void a(Activity activity, int i, boolean z) {
        AppMethodBeat.i(23103, false);
        a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", i).withAction(z ? "action_refresh_shelf" : "action_nothing").addFlags(67108864).navigation();
        AppMethodBeat.o(23103);
    }

    public static void a(Context context, Bundle bundle, int i) {
        AppMethodBeat.i(23104, false);
        a.a().a("/main/mall").withInt("skipToType", i).with(bundle).addFlags(268435456).navigation();
        AppMethodBeat.o(23104);
    }

    public static void c(Context context) {
        AppMethodBeat.i(23105, false);
        a.a().a("/main/mall").addFlags(67108864).navigation();
        AppMethodBeat.o(23105);
    }

    public static Postcard c(String str) {
        AppMethodBeat.i(23106, false);
        if ("/order/confirmation".equals(str)) {
            Postcard d = d("orderFill");
            AppMethodBeat.o(23106);
            return d;
        } else if ("/product/product_detail".equals(str)) {
            Postcard d2 = d("productDetail");
            AppMethodBeat.o(23106);
            return d2;
        } else if ("/food/note_detail".equals(str)) {
            Postcard d3 = d("talentplanDetail");
            AppMethodBeat.o(23106);
            return d3;
        } else {
            if ("/user/address_list".equals(str)) {
                if (f.v()) {
                    Postcard d4 = d("address");
                    AppMethodBeat.o(23106);
                    return d4;
                }
            } else if ("/order/shoppingcart_activity".equals(str)) {
                Postcard d5 = d("shopCart");
                d5.withString("pageType", "second");
                AppMethodBeat.o(23106);
                return d5;
            } else if ("/order/order_list".equals(str)) {
                Postcard d6 = d("orderList");
                AppMethodBeat.o(23106);
                return d6;
            } else if ("/order/refund".equals(str)) {
                Postcard d7 = d("mainApplyingGoodsList");
                AppMethodBeat.o(23106);
                return d7;
            } else if ("/base/mybalance".equals(str)) {
                if (f.c()) {
                    Postcard d8 = d("balance");
                    AppMethodBeat.o(23106);
                    return d8;
                }
            } else if ("/promotion/voucher_mine".equals(str)) {
                Postcard d9 = d("checkoutCoupon");
                AppMethodBeat.o(23106);
                return d9;
            } else if ("/order/orderdetail".equals(str)) {
                Postcard d10 = d("orderDetail");
                AppMethodBeat.o(23106);
                return d10;
            }
            Postcard a2 = a.a().a(str);
            AppMethodBeat.o(23106);
            return a2;
        }
    }

    public static Postcard d(String str) {
        AppMethodBeat.i(23107, false);
        Postcard a2 = a.a().a("/flutter/router");
        if (str.equals("checkoutCoupon")) {
            a2.withString("chromeType", cn.missfresh.module.base.common.config.a.b + "");
        }
        a2.withString("Router", str);
        AppMethodBeat.o(23107);
        return a2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x006a, code lost:
        if (r9 == null) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x006c, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0079, code lost:
        if (r9 != null) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0082, code lost:
        if (r10 == null) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008e, code lost:
        if (r10 != null) goto L_0x0090;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0090, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0093, code lost:
        cn.missfresh.sherlock.trace.core.AppMethodBeat.o(23108);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0096, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String[] b(android.app.Activity r9, android.net.Uri r10) {
        /*
            r0 = 0
            r1 = 23108(0x5a44, float:3.2381E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]
            android.content.ContentResolver r9 = r9.getContentResolver()
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r3 = r9
            r4 = r10
            android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)
            if (r10 == 0) goto L_0x008e
            r10.moveToFirst()     // Catch:{ Exception -> 0x007e }
            java.lang.String r3 = "display_name"
            int r3 = r10.getColumnIndex(r3)     // Catch:{ Exception -> 0x007e }
            java.lang.String r3 = r10.getString(r3)     // Catch:{ Exception -> 0x007e }
            r2[r0] = r3     // Catch:{ Exception -> 0x007e }
            java.lang.String r0 = "_id"
            int r0 = r10.getColumnIndex(r0)     // Catch:{ Exception -> 0x007e }
            java.lang.String r0 = r10.getString(r0)     // Catch:{ Exception -> 0x007e }
            android.net.Uri r4 = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI     // Catch:{ Exception -> 0x007e }
            r5 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x007e }
            r3.<init>()     // Catch:{ Exception -> 0x007e }
            java.lang.String r6 = "contact_id="
            r3.append(r6)     // Catch:{ Exception -> 0x007e }
            r3.append(r0)     // Catch:{ Exception -> 0x007e }
            java.lang.String r6 = r3.toString()     // Catch:{ Exception -> 0x007e }
            r7 = 0
            r8 = 0
            r3 = r9
            android.database.Cursor r9 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x007e }
            if (r9 == 0) goto L_0x0079
            r9.moveToFirst()     // Catch:{ Exception -> 0x0066 }
            java.lang.String r0 = "data1"
            int r0 = r9.getColumnIndex(r0)     // Catch:{ Exception -> 0x0066 }
            r3 = 1
            java.lang.String r0 = r9.getString(r0)     // Catch:{ Exception -> 0x0066 }
            r2[r3] = r0     // Catch:{ Exception -> 0x0066 }
            goto L_0x0079
        L_0x0064:
            r0 = move-exception
            goto L_0x0070
        L_0x0066:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0064 }
            if (r9 == 0) goto L_0x008e
        L_0x006c:
            r9.close()
            goto L_0x008e
        L_0x0070:
            if (r9 == 0) goto L_0x0075
            r9.close()
        L_0x0075:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r0
        L_0x0079:
            if (r9 == 0) goto L_0x008e
            goto L_0x006c
        L_0x007c:
            r9 = move-exception
            goto L_0x0085
        L_0x007e:
            r9 = move-exception
            r9.printStackTrace()     // Catch:{ all -> 0x007c }
            if (r10 == 0) goto L_0x0093
            goto L_0x0090
        L_0x0085:
            if (r10 == 0) goto L_0x008a
            r10.close()
        L_0x008a:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r9
        L_0x008e:
            if (r10 == 0) goto L_0x0093
        L_0x0090:
            r10.close()
        L_0x0093:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.utils.j.b(android.app.Activity, android.net.Uri):java.lang.String[]");
    }

    public static void a(String str, String str2, boolean z, String str3, String str4, int i) {
        AppMethodBeat.i(23109, false);
        a(str, str2, z, str3, str4, false, i);
        AppMethodBeat.o(23109);
    }

    public static void a(String str, String str2, boolean z, String str3, String str4, boolean z2, int i) {
        AppMethodBeat.i(23110, false);
        a(str, str2, z, str3, str4, false, i, 0);
        AppMethodBeat.o(23110);
    }

    public static void a(String str, String str2, boolean z, String str3, String str4, boolean z2, int i, int i2) {
        AppMethodBeat.i(23111, false);
        d("orderDetail").withString("orderId", str).withString("orderNo", str2).withBoolean("needPay", z).withString("orderFromType", str4).withString("activeSource", str3).withBoolean("isNeedJumpToMain", z2).withInt("autoPayType", i).withInt("actionType", i2).navigation();
        AppMethodBeat.o(23111);
    }
}
