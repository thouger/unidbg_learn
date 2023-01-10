package cn.missfresh.module.base.manager;

import android.content.Context;
import android.mtp.MtpConstants;
import android.os.SystemClock;
import android.text.TextUtils;
import cn.missfresh.module.base.bean.ConfigurationBean;
import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.common.config.c;
import cn.missfresh.module.base.common.event.m;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.manager.bean.MineInfo;
import cn.missfresh.module.base.manager.bean.UserInfo;
import cn.missfresh.sherlock.Sherlock;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.android.arouter.b.a;
import com.alibaba.fastjson.JSONObject;
import com.umeng.message.common.inter.ITagManager;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* compiled from: ConfigManager */
public class e {
    public static boolean a;
    private static e b;
    private static i c;
    private static volatile HashMap<String, String> d;
    private static boolean e;

    public static boolean A() {
        return true;
    }

    public static boolean T() {
        return true;
    }

    private e(Context context) {
        boolean z = false;
        AppMethodBeat.i(14084, false);
        c = new i(context, "mryx_user");
        a = ad() == 1 ? true : z;
        AppMethodBeat.o(14084);
    }

    public static synchronized void a(Context context) {
        synchronized (e.class) {
            AppMethodBeat.i(14086, false);
            if (b == null) {
                b = new e(context);
            }
            AppMethodBeat.o(14086);
        }
    }

    public static void a() {
        AppMethodBeat.i(14088, false);
        c.a();
        AppMethodBeat.o(14088);
    }

    public static boolean b() {
        AppMethodBeat.i(14090, false);
        boolean booleanValue = Boolean.valueOf(c("showSuperOptions", String.valueOf(((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getShowSuperOptions()))).booleanValue();
        AppMethodBeat.o(14090);
        return booleanValue;
    }

    public static void a(String str) {
        AppMethodBeat.i(14092, false);
        b("originChannel", str);
        AppMethodBeat.o(14092);
    }

    public static String c() {
        AppMethodBeat.i(14094, false);
        String valueOf = String.valueOf(c("originChannel", ""));
        AppMethodBeat.o(14094);
        return valueOf;
    }

    public static void b(String str) {
        AppMethodBeat.i(14095, false);
        b("channelTaskId", str);
        AppMethodBeat.o(14095);
    }

    public static String d() {
        AppMethodBeat.i(14096, false);
        String valueOf = String.valueOf(c("channelTaskId", ""));
        AppMethodBeat.o(14096);
        return valueOf;
    }

    public static void a(boolean z) {
        AppMethodBeat.i(14097, false);
        b("showSuperOptions", String.valueOf(z));
        AppMethodBeat.o(14097);
    }

    public static boolean e() {
        AppMethodBeat.i(14098, false);
        boolean booleanValue = Boolean.valueOf(c("ifLogEnable", ITagManager.STATUS_FALSE)).booleanValue();
        AppMethodBeat.o(14098);
        return booleanValue;
    }

    public static void b(boolean z) {
        AppMethodBeat.i(14099, false);
        b("ifLogEnable", String.valueOf(z));
        AppMethodBeat.o(14099);
    }

    public static boolean f() {
        AppMethodBeat.i(14100, false);
        boolean booleanValue = Boolean.valueOf(c("ifLog2SD", ITagManager.STATUS_FALSE)).booleanValue();
        AppMethodBeat.o(14100);
        return booleanValue;
    }

    public static void c(boolean z) {
        AppMethodBeat.i(14101, false);
        b("ifLog2SD", String.valueOf(z));
        AppMethodBeat.o(14101);
    }

    public static boolean g() {
        AppMethodBeat.i(14102, false);
        boolean booleanValue = Boolean.valueOf(c("customServerEnable", ITagManager.STATUS_FALSE)).booleanValue();
        AppMethodBeat.o(14102);
        return booleanValue;
    }

    public static void d(boolean z) {
        AppMethodBeat.i(14103, false);
        b("customServerEnable", String.valueOf(z));
        AppMethodBeat.o(14103);
    }

    public static String h() {
        AppMethodBeat.i(14105, false);
        String c2 = c("customServer", "");
        AppMethodBeat.o(14105);
        return c2;
    }

    public static void c(String str) {
        AppMethodBeat.i(14106, false);
        b("customServer", str);
        AppMethodBeat.o(14106);
    }

    public static boolean i() {
        AppMethodBeat.i(14107, false);
        boolean booleanValue = Boolean.valueOf(c("flutterProxyEnable", ITagManager.STATUS_FALSE)).booleanValue();
        AppMethodBeat.o(14107);
        return booleanValue;
    }

    public static void e(boolean z) {
        AppMethodBeat.i(14108, false);
        b("flutterProxyEnable", String.valueOf(z));
        AppMethodBeat.o(14108);
    }

    public static String j() {
        AppMethodBeat.i(14109, false);
        String c2 = c("flutterProxy", "");
        AppMethodBeat.o(14109);
        return c2;
    }

    public static void d(String str) {
        AppMethodBeat.i(14110, false);
        b("flutterProxy", str);
        AppMethodBeat.o(14110);
    }

    public static void e(String str) {
        AppMethodBeat.i(14111, false);
        b("imAccount", str);
        AppMethodBeat.o(14111);
    }

    public static String k() {
        AppMethodBeat.i(14112, false);
        String c2 = c("imAccount", "");
        AppMethodBeat.o(14112);
        return c2;
    }

    public static void f(String str) {
        AppMethodBeat.i(14113, false);
        b("imSignature", str);
        AppMethodBeat.o(14113);
    }

    public static String l() {
        AppMethodBeat.i(14114, false);
        String c2 = c("imSignature", "");
        AppMethodBeat.o(14114);
        return c2;
    }

    public static boolean m() {
        AppMethodBeat.i(14115, false);
        boolean booleanValue = Boolean.valueOf(c("showRequestParams", ITagManager.STATUS_FALSE)).booleanValue();
        AppMethodBeat.o(14115);
        return booleanValue;
    }

    public static void f(boolean z) {
        AppMethodBeat.i(14116, false);
        b("showRequestParams", String.valueOf(z));
        AppMethodBeat.o(14116);
    }

    public static String n() {
        AppMethodBeat.i(14117, false);
        String a2 = c.a("user_token", "");
        AppMethodBeat.o(14117);
        return a2;
    }

    public static void g(String str) {
        AppMethodBeat.i(14118, false);
        d.d("ConfigManager", "setToken... accessToken:" + str);
        c.b("user_token", str);
        AppMethodBeat.o(14118);
    }

    public static boolean o() {
        AppMethodBeat.i(14119, false);
        boolean z = !b.a(n());
        AppMethodBeat.o(14119);
        return z;
    }

    public static UserInfo p() {
        int i = 0;
        AppMethodBeat.i(14120, false);
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone_number(c.a("user_phone_number", ""));
        userInfo.setUser_id(c.a("user_id", -1));
        userInfo.setPromotion_notice(c.a("user_promotion_notice", false));
        userInfo.setOrder_notice(c.a("user_order_notice", false));
        userInfo.setStart_time(c.a("user_start_time", ""));
        userInfo.setHas_unread(c.a("user_has_unread", false));
        userInfo.setPlatform(c.a("user_platform", ""));
        userInfo.setEnd_time(c.a("user_end_time", ""));
        userInfo.setPortrait(c.a("user_portrait", ""));
        userInfo.setNick_name(c.a("user_nick_name", ""));
        userInfo.setActive_vip(c.a("user_active_vip", false));
        userInfo.setIs_vip(c.a("user_is_vip", false));
        userInfo.setTab_img(c.a("user_tab_img", ""));
        userInfo.setTab_url(c.a("user_tab_url", ""));
        userInfo.user_member_type = c.a("user_member_type", 0);
        userInfo.setState_autopay(c.a("state_autopay", "1"));
        userInfo.setIs_open(c.a("is_open", "0"));
        userInfo.setIs_union_account(c.a("is_union_account", 0));
        if (userInfo.getUser_id() > 0) {
            i iVar = c;
            if (iVar.a(userInfo.getUser_id() + "is_have_bind_phone", false)) {
                i = 1;
            }
        }
        userInfo.setIs_binding_phone(i);
        AppMethodBeat.o(14120);
        return userInfo;
    }

    public static void h(String str) {
        AppMethodBeat.i(14126, false);
        c.b("product_info", str);
        AppMethodBeat.o(14126);
    }

    public static String q() {
        AppMethodBeat.i(14127, false);
        String a2 = c.a("product_info", "");
        AppMethodBeat.o(14127);
        return a2;
    }

    public static void a(UserInfo userInfo) {
        boolean z = false;
        AppMethodBeat.i(14131, false);
        d.d("ConfigManager", "setUserInfo... userInfo:" + userInfo);
        if (userInfo != null) {
            c.b("user_phone_number", userInfo.getPhone_number());
            c.b("user_id", userInfo.getUser_id());
            Sherlock.setUerId(String.valueOf(userInfo.getUser_id()));
            c.b("user_promotion_notice", userInfo.isPromotion_notice());
            c.b("user_order_notice", userInfo.isOrder_notice());
            c.b("user_start_time", userInfo.getStart_time());
            c.b("user_has_unread", userInfo.isHas_unread());
            c.b("user_platform", userInfo.getPlatform());
            c.b("user_end_time", userInfo.getEnd_time());
            c.b("user_portrait", userInfo.getPortrait());
            c.b("user_nick_name", userInfo.getNick_name());
            c.b("user_active_vip", userInfo.isActive_vip());
            c.b("user_tab_img", userInfo.tab_img);
            c.b("user_tab_url", userInfo.tab_url);
            c.b("user_is_vip", userInfo.is_vip());
            c.b("is_open", userInfo.getIs_open());
            c.b("state_autopay", userInfo.getState_autopay());
            b(userInfo.user_member_type);
            h(userInfo.getIs_binding_wx() == 1);
            if (userInfo.getIs_binding_phone() == 1) {
                z = true;
            }
            i(z);
            m(userInfo.getBind_phone_tips());
            n(userInfo.getBind_phone_text());
            c.b("is_union_account", userInfo.getIs_union_account());
            c.b("registerTime_ever", userInfo.getRegister_time());
            c.b("firstPayTime_ever", userInfo.getFirst_order_time());
        } else {
            h(false);
            i(false);
            m("");
            n("");
            c.b("user_phone_number", "");
            c.b("user_id", -1);
            c.b("user_promotion_notice", false);
            c.b("user_order_notice", false);
            c.b("user_start_time", "");
            c.b("user_has_unread", false);
            c.b("user_platform", "");
            c.b("user_end_time", "");
            c.b("user_portrait", "");
            c.b("user_nick_name", "");
            c.b("user_active_vip", false);
            c.b("user_tab_img", "");
            c.a("user_tab_url", "");
            c.b("user_is_vip", false);
            c.b("is_auto_pay", 1);
            c.b("is_union_account", 0);
            b(0);
        }
        AppMethodBeat.o(14131);
    }

    public static void a(MineInfo.UserMember userMember) {
        AppMethodBeat.i(14133, false);
        if (userMember != null) {
            b(userMember.user_member_type);
            c.b("user_member_background", userMember.user_member_background);
            c.b("vip_card_logo", userMember.vip_card_logo);
            c.b("vip_card_end_time", userMember.vip_card_end_time);
            c.b("give_experience_card", userMember.give_experience_card);
        } else {
            b(0);
            c.b("user_member_background", "");
            c.b("vip_card_logo", "");
            c.b("vip_card_end_time", "");
            c.b("give_experience_card", "");
        }
        AppMethodBeat.o(14133);
    }

    public static void r() {
        AppMethodBeat.i(14134, false);
        boolean o = o();
        g("");
        a(0, true);
        a((UserInfo) null);
        a((MineInfo.UserMember) null);
        n("");
        c.n();
        c.q().b((UserAddress) null);
        cn.missfresh.module.base.support.share.a.b(((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication());
        p(false);
        if (o) {
            o.c(400);
            cn.missfresh.module.base.push.b.b(((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication());
        }
        com.sobot.chat.b.b(((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication());
        AppMethodBeat.o(14134);
    }

    public static void g(boolean z) {
        AppMethodBeat.i(14137, false);
        c.b("is_first_start_fix", z);
        AppMethodBeat.o(14137);
    }

    public static String s() {
        AppMethodBeat.i(14139, false);
        String a2 = c.a("appInstallChannel", "");
        AppMethodBeat.o(14139);
        return a2;
    }

    public static void i(String str) {
        AppMethodBeat.i(14140, false);
        c.b("appInstallChannel", str);
        AppMethodBeat.o(14140);
    }

    public static String t() {
        AppMethodBeat.i(14141, false);
        String a2 = c.a("appVerCode", "");
        AppMethodBeat.o(14141);
        return a2;
    }

    public static void j(String str) {
        AppMethodBeat.i(14142, false);
        c.b("appVerCode", str);
        AppMethodBeat.o(14142);
    }

    public static boolean u() {
        AppMethodBeat.i(14143, false);
        boolean a2 = c.a("is_first_start");
        AppMethodBeat.o(14143);
        return a2;
    }

    public static boolean v() {
        AppMethodBeat.i(14146, false);
        int user_id = p().getUser_id();
        if (user_id > 0) {
            i iVar = c;
            boolean a2 = iVar.a(user_id + "is_have_bind_wx", false);
            AppMethodBeat.o(14146);
            return a2;
        }
        AppMethodBeat.o(14146);
        return false;
    }

    public static void h(boolean z) {
        AppMethodBeat.i(14147, false);
        int user_id = p().getUser_id();
        if (user_id > 0) {
            i iVar = c;
            iVar.b(user_id + "is_have_bind_wx", z);
        }
        AppMethodBeat.o(14147);
    }

    public static String w() {
        AppMethodBeat.i(14148, false);
        String a2 = c.a("bind_wx_msg", "");
        AppMethodBeat.o(14148);
        return a2;
    }

    public static void k(String str) {
        AppMethodBeat.i(14149, false);
        c.b("bind_wx_msg", str);
        AppMethodBeat.o(14149);
    }

    public static String x() {
        AppMethodBeat.i(14150, false);
        String a2 = c.a("bind_phone_msg", "");
        AppMethodBeat.o(14150);
        return a2;
    }

    public static void l(String str) {
        AppMethodBeat.i(14151, false);
        c.b("bind_phone_msg", str);
        AppMethodBeat.o(14151);
    }

    public static void m(String str) {
        AppMethodBeat.i(14153, false);
        c.b("mine_bind_phone_tips", str);
        AppMethodBeat.o(14153);
    }

    public static void n(String str) {
        AppMethodBeat.i(14156, false);
        c.b("bind_phone_text", str);
        AppMethodBeat.o(14156);
    }

    public static boolean y() {
        AppMethodBeat.i(14157, false);
        int user_id = p().getUser_id();
        if (user_id > 0) {
            i iVar = c;
            boolean a2 = iVar.a(user_id + "is_have_bind_phone", false);
            AppMethodBeat.o(14157);
            return a2;
        }
        AppMethodBeat.o(14157);
        return false;
    }

    public static void i(boolean z) {
        AppMethodBeat.i(14159, false);
        int user_id = p().getUser_id();
        if (user_id > 0) {
            i iVar = c;
            iVar.b(user_id + "is_have_bind_phone", z);
        }
        AppMethodBeat.o(14159);
    }

    public static int z() {
        AppMethodBeat.i(14160, false);
        int a2 = c.a("balanceType", 1);
        d.d("ConfigManager", "getShoppingCartBalanType...rs:" + a2);
        AppMethodBeat.o(14160);
        return a2;
    }

    public static void j(boolean z) {
        AppMethodBeat.i(14162, false);
        int user_id = p().getUser_id();
        if (user_id > 0) {
            i iVar = c;
            iVar.b(user_id + "_vip_new", z);
        }
        AppMethodBeat.o(14162);
    }

    public static void k(boolean z) {
        AppMethodBeat.i(14164, false);
        int user_id = p().getUser_id();
        if (user_id > 0) {
            i iVar = c;
            iVar.b(user_id + "_if_bind_phone", z);
        }
        AppMethodBeat.o(14164);
    }

    public static String B() {
        AppMethodBeat.i(14165, false);
        String a2 = c.a("PrePlayCompleteVideoUrl", "");
        AppMethodBeat.o(14165);
        return a2;
    }

    public static void o(String str) {
        AppMethodBeat.i(14166, false);
        c.b("PrePlayCompleteVideoUrl", str);
        AppMethodBeat.o(14166);
    }

    public static String C() {
        AppMethodBeat.i(14167, false);
        String a2 = c.a("PrePreCompleteVideoUrl", "");
        AppMethodBeat.o(14167);
        return a2;
    }

    public static void p(String str) {
        AppMethodBeat.i(14168, false);
        c.b("PrePreCompleteVideoUrl", str);
        AppMethodBeat.o(14168);
    }

    public static void l(boolean z) {
        AppMethodBeat.i(14170, false);
        int user_id = p().getUser_id();
        if (user_id > 0) {
            i iVar = c;
            iVar.b(user_id + "_vip_poped", z);
        }
        AppMethodBeat.o(14170);
    }

    public static void q(String str) {
        AppMethodBeat.i(14172, false);
        c.b("bottomPopLink", str);
        AppMethodBeat.o(14172);
    }

    public static int D() {
        AppMethodBeat.i(14173, false);
        int a2 = c.a("UpgradePopCnt", 0);
        AppMethodBeat.o(14173);
        return a2;
    }

    public static void E() {
        AppMethodBeat.i(14174, false);
        c.b("UpgradePopCnt", D() + 1);
        AppMethodBeat.o(14174);
    }

    public static void F() {
        AppMethodBeat.i(14175, false);
        c.b("UpgradePopCnt", 0);
        AppMethodBeat.o(14175);
    }

    public static boolean G() {
        AppMethodBeat.i(14176, false);
        boolean a2 = c.a("isShouldUpgrade", false);
        AppMethodBeat.o(14176);
        return a2;
    }

    public static void m(boolean z) {
        AppMethodBeat.i(14177, false);
        c.b("isShouldUpgrade", z);
        AppMethodBeat.o(14177);
    }

    public static String H() {
        AppMethodBeat.i(14178, false);
        String a2 = c.a("cancelUpdateVersion", "");
        AppMethodBeat.o(14178);
        return a2;
    }

    public static void r(String str) {
        AppMethodBeat.i(14179, false);
        c.b("cancelUpdateVersion", str);
        AppMethodBeat.o(14179);
    }

    public static boolean I() {
        AppMethodBeat.i(14180, false);
        boolean a2 = c.a("isUpgredeRedPointShown", false);
        AppMethodBeat.o(14180);
        return a2;
    }

    public static void n(boolean z) {
        AppMethodBeat.i(14182, false);
        c.b("singlePriceSwitch", z);
        AppMethodBeat.o(14182);
    }

    public static void s(String str) {
        AppMethodBeat.i(14183, false);
        a("searchHisKey", str);
        AppMethodBeat.o(14183);
    }

    public static void t(String str) {
        AppMethodBeat.i(14184, false);
        a("searchFoodHisKey", str);
        AppMethodBeat.o(14184);
    }

    public static void a(String str, String str2) {
        List<String> list;
        AppMethodBeat.i(14185, false);
        if (b.a(str2)) {
            AppMethodBeat.o(14185);
            return;
        }
        List<String> u = u(str);
        u.remove(str2);
        u.add(0, str2);
        int i = 15;
        if ("searchHisKey".equals(str)) {
            i = 50;
        }
        if (u.size() >= i) {
            list = u.subList(0, i);
        } else {
            list = u.subList(0, u.size());
        }
        String jSONString = JSONObject.toJSONString(list);
        d.d("ConfigManager", "addSearchHisKey....key:" + str2 + ",tmpStr:" + jSONString);
        c.b(str, jSONString);
        AppMethodBeat.o(14185);
    }

    public static List<String> J() {
        AppMethodBeat.i(14186, false);
        List<String> u = u("searchHisKey");
        AppMethodBeat.o(14186);
        return u;
    }

    public static List<String> K() {
        AppMethodBeat.i(14187, false);
        List<String> u = u("searchFoodHisKey");
        AppMethodBeat.o(14187);
        return u;
    }

    public static List<String> u(String str) {
        AppMethodBeat.i(14188, false);
        List<String> arrayList = new ArrayList<>();
        String a2 = c.a(str, "");
        if (!b.a(a2)) {
            arrayList = JSONObject.parseArray(a2, String.class);
        }
        d.d("ConfigManager", "getSearchKeyHis...rs:" + arrayList);
        AppMethodBeat.o(14188);
        return arrayList;
    }

    public static void L() {
        AppMethodBeat.i(14189, false);
        v("searchHisKey");
        AppMethodBeat.o(14189);
    }

    public static void M() {
        AppMethodBeat.i(14190, false);
        v("searchFoodHisKey");
        AppMethodBeat.o(14190);
    }

    public static void v(String str) {
        AppMethodBeat.i(14191, false);
        c.b(str, "");
        AppMethodBeat.o(14191);
    }

    public static void o(boolean z) {
        AppMethodBeat.i(14192, false);
        c.b("isShowHongBao", z);
        AppMethodBeat.o(14192);
    }

    public static boolean N() {
        AppMethodBeat.i(14193, false);
        boolean a2 = c.a("isShowHongBao", true);
        AppMethodBeat.o(14193);
        return a2;
    }

    public static boolean w(String str) {
        AppMethodBeat.i(14194, false);
        if (c.a("addate", "").equals(str)) {
            AppMethodBeat.o(14194);
            return true;
        }
        AppMethodBeat.o(14194);
        return false;
    }

    public static void x(String str) {
        AppMethodBeat.i(14195, false);
        c.b("addate", str);
        AppMethodBeat.o(14195);
    }

    public static boolean y(String str) {
        AppMethodBeat.i(14196, false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        i iVar = c;
        String a2 = iVar.a(str + "newUserVoucher", "");
        if (TextUtils.isEmpty(a2) || !simpleDateFormat.format(new Date()).equals(a2)) {
            AppMethodBeat.o(14196);
            return true;
        }
        AppMethodBeat.o(14196);
        return false;
    }

    public static void z(String str) {
        AppMethodBeat.i(14197, false);
        String format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date());
        i iVar = c;
        iVar.b(str + "newUserVoucher", format);
        AppMethodBeat.o(14197);
    }

    public static String O() {
        AppMethodBeat.i(14211, false);
        String a2 = c.a("devide_uniqueId", "");
        AppMethodBeat.o(14211);
        return a2;
    }

    public static void A(String str) {
        AppMethodBeat.i(14212, false);
        c.b("devide_uniqueId", str);
        AppMethodBeat.o(14212);
    }

    public static void p(boolean z) {
        AppMethodBeat.i(14213, false);
        c.b("ShopCartCombined", z);
        AppMethodBeat.o(14213);
    }

    public static void a(int i) {
        AppMethodBeat.i(14216, false);
        i iVar = c;
        iVar.b("VipLevel" + p().getUser_id(), i);
        AppMethodBeat.o(14216);
    }

    public static boolean P() {
        AppMethodBeat.i(14218, false);
        i iVar = c;
        boolean a2 = iVar.a("growthpop" + p().getUser_id(), true);
        AppMethodBeat.o(14218);
        return a2;
    }

    public static void q(boolean z) {
        AppMethodBeat.i(14220, false);
        i iVar = c;
        iVar.b("growthpop" + p().getUser_id(), z);
        AppMethodBeat.o(14220);
    }

    public static String Q() {
        AppMethodBeat.i(14229, false);
        String valueOf = String.valueOf(c("serverHost", "https://as-vip.missfresh.cn"));
        AppMethodBeat.o(14229);
        return valueOf;
    }

    public static void B(String str) {
        AppMethodBeat.i(14231, false);
        b("serverHost", String.valueOf(str));
        AppMethodBeat.o(14231);
    }

    public static String R() {
        AppMethodBeat.i(14232, false);
        String valueOf = String.valueOf(c("customerServerHost", "https://cs.missfresh.cn"));
        AppMethodBeat.o(14232);
        return valueOf;
    }

    public static void C(String str) {
        AppMethodBeat.i(14233, false);
        b("customerServerHost", String.valueOf(str));
        AppMethodBeat.o(14233);
    }

    public static String S() {
        AppMethodBeat.i(14234, false);
        String a2 = c.a("mine_suggestion", "");
        AppMethodBeat.o(14234);
        return a2;
    }

    public static void D(String str) {
        AppMethodBeat.i(14236, false);
        c.b("mine_suggestion", str);
        AppMethodBeat.o(14236);
    }

    public static boolean U() {
        AppMethodBeat.i(14240, false);
        boolean a2 = c.a("delicacyTabShow", true);
        AppMethodBeat.o(14240);
        return a2;
    }

    public static void r(boolean z) {
        AppMethodBeat.i(14241, false);
        c.b("isfristopendaysign", z);
        AppMethodBeat.o(14241);
    }

    public static boolean V() {
        AppMethodBeat.i(14242, false);
        boolean a2 = c.a("isfristopendaysign", true);
        AppMethodBeat.o(14242);
        return a2;
    }

    public static boolean W() {
        AppMethodBeat.i(14245, false);
        boolean a2 = c.a("IfShowMiniCart", false);
        AppMethodBeat.o(14245);
        return a2;
    }

    public static void X() {
        AppMethodBeat.i(14248, false);
        c.b("AppStartCnt", Y() + 1);
        AppMethodBeat.o(14248);
    }

    public static int Y() {
        AppMethodBeat.i(14249, false);
        int a2 = c.a("AppStartCnt", 0);
        AppMethodBeat.o(14249);
        return a2;
    }

    private static void b(String str, String str2) {
        AppMethodBeat.i(14257, false);
        if (d == null) {
            d = aH();
        }
        if (d == null) {
            d = new HashMap<>();
        }
        d.put(str, str2);
        d.d("ConfigManager", "setConfig...key:" + str + ",value:" + str2 + ",map:" + d.toString());
        a(d);
        AppMethodBeat.o(14257);
    }

    private static String c(String str, String str2) {
        AppMethodBeat.i(14258, false);
        if (d == null) {
            d = aH();
        }
        if (!(d == null || d.get(str) == null)) {
            str2 = d.get(str);
        }
        AppMethodBeat.o(14258);
        return str2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0074 A[SYNTHETIC, Splitter:B:26:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008b A[SYNTHETIC, Splitter:B:36:0x008b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.HashMap<java.lang.String, java.lang.String> aH() {
        /*
            java.lang.String r0 = "ConfigManager"
            r1 = 0
            r2 = 14259(0x37b3, float:1.9981E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r2, r1)
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            java.io.File r4 = new java.io.File
            java.lang.String r5 = cn.missfresh.module.base.common.config.c.h
            r4.<init>(r5)
            boolean r5 = r4.exists()
            if (r5 != 0) goto L_0x001f
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r2)
            return r3
        L_0x001f:
            r5 = 0
            java.io.FileReader r6 = new java.io.FileReader     // Catch:{ Exception -> 0x006e }
            r6.<init>(r4)     // Catch:{ Exception -> 0x006e }
            r4 = 200(0xc8, float:2.8E-43)
            char[] r4 = new char[r4]     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            r5.<init>()     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            int r7 = r6.read(r4)     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
        L_0x0032:
            if (r7 <= 0) goto L_0x003c
            r5.append(r4, r1, r7)     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            int r7 = r6.read(r4)     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            goto L_0x0032
        L_0x003c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            r1.<init>()     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            java.lang.String r4 = "getCfgMap...content:"
            r1.append(r4)     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            r1.append(r5)     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            cn.missfresh.utils.a.d.d(r0, r1)     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            java.lang.String r1 = r5.toString()     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            java.lang.Class<java.util.HashMap> r4 = java.util.HashMap.class
            java.lang.Object r1 = com.alibaba.fastjson.JSON.parseObject(r1, r4)     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            java.util.HashMap r1 = (java.util.HashMap) r1     // Catch:{ Exception -> 0x0068, all -> 0x0066 }
            r6.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x007d
        L_0x0061:
            r3 = move-exception
            cn.missfresh.utils.a.d.a(r0, r3)
            goto L_0x007d
        L_0x0066:
            r1 = move-exception
            goto L_0x0089
        L_0x0068:
            r1 = move-exception
            r5 = r6
            goto L_0x006f
        L_0x006b:
            r1 = move-exception
            r6 = r5
            goto L_0x0089
        L_0x006e:
            r1 = move-exception
        L_0x006f:
            cn.missfresh.utils.a.d.a(r0, r1)     // Catch:{ all -> 0x006b }
            if (r5 == 0) goto L_0x007c
            r5.close()     // Catch:{ IOException -> 0x0078 }
            goto L_0x007c
        L_0x0078:
            r1 = move-exception
            cn.missfresh.utils.a.d.a(r0, r1)
        L_0x007c:
            r1 = r3
        L_0x007d:
            if (r1 == 0) goto L_0x0080
            goto L_0x0085
        L_0x0080:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
        L_0x0085:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r2)
            return r1
        L_0x0089:
            if (r6 == 0) goto L_0x0093
            r6.close()     // Catch:{ IOException -> 0x008f }
            goto L_0x0093
        L_0x008f:
            r3 = move-exception
            cn.missfresh.utils.a.d.a(r0, r3)
        L_0x0093:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.manager.e.aH():java.util.HashMap");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0072 A[SYNTHETIC, Splitter:B:29:0x0072] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.util.HashMap<java.lang.String, java.lang.String> r5) {
        /*
            r0 = 14260(0x37b4, float:1.9983E-41)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            if (r5 == 0) goto L_0x007e
            int r1 = r5.size()
            if (r1 != 0) goto L_0x0010
            goto L_0x007e
        L_0x0010:
            java.lang.String r5 = com.alibaba.fastjson.JSON.toJSONString(r5)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "saveCfgMap...content:"
            r1.append(r2)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "ConfigManager"
            cn.missfresh.utils.a.d.d(r2, r1)
            java.io.File r1 = new java.io.File
            java.lang.String r3 = cn.missfresh.module.base.common.config.c.h
            r1.<init>(r3)
            boolean r3 = r1.exists()
            if (r3 != 0) goto L_0x0040
            java.io.File r3 = r1.getParentFile()
            r3.mkdirs()
        L_0x0040:
            r3 = 0
            r1.deleteOnExit()     // Catch:{ Exception -> 0x005e }
            r1.createNewFile()     // Catch:{ Exception -> 0x005e }
            java.io.FileWriter r4 = new java.io.FileWriter     // Catch:{ Exception -> 0x005e }
            r4.<init>(r1)     // Catch:{ Exception -> 0x005e }
            r4.write(r5)     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r4.flush()     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r4.close()     // Catch:{ IOException -> 0x0068 }
            goto L_0x006c
        L_0x0056:
            r5 = move-exception
            r3 = r4
            goto L_0x0070
        L_0x0059:
            r5 = move-exception
            r3 = r4
            goto L_0x005f
        L_0x005c:
            r5 = move-exception
            goto L_0x0070
        L_0x005e:
            r5 = move-exception
        L_0x005f:
            cn.missfresh.utils.a.d.a(r2, r5)     // Catch:{ all -> 0x005c }
            if (r3 == 0) goto L_0x006c
            r3.close()
            goto L_0x006c
        L_0x0068:
            r5 = move-exception
            cn.missfresh.utils.a.d.a(r2, r5)
        L_0x006c:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x0070:
            if (r3 == 0) goto L_0x007a
            r3.close()     // Catch:{ IOException -> 0x0076 }
            goto L_0x007a
        L_0x0076:
            r1 = move-exception
            cn.missfresh.utils.a.d.a(r2, r1)
        L_0x007a:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            throw r5
        L_0x007e:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.manager.e.a(java.util.HashMap):void");
    }

    public static void Z() {
        AppMethodBeat.i(14262, false);
        new File(c.h).delete();
        AppMethodBeat.o(14262);
    }

    public static synchronized int aa() {
        synchronized (e.class) {
            AppMethodBeat.i(14263, false);
            if (o()) {
                int aI = aI();
                AppMethodBeat.o(14263);
                return aI;
            }
            AppMethodBeat.o(14263);
            return -1;
        }
    }

    public static synchronized void b(int i) {
        synchronized (e.class) {
            AppMethodBeat.i(14264, false);
            a(i, false);
            AppMethodBeat.o(14264);
        }
    }

    private static int aI() {
        AppMethodBeat.i(14266, false);
        int a2 = c.a("user_member_type", 0);
        AppMethodBeat.o(14266);
        return a2;
    }

    public static void a(int i, boolean z) {
        AppMethodBeat.i(14268, false);
        if (aI() != i) {
            if (!z) {
                if (i == 0) {
                    E("-1");
                }
                EventBus.getDefault().post(new m(i));
            }
            c.b("user_member_type", i);
        }
        AppMethodBeat.o(14268);
    }

    public static void E(String str) {
        AppMethodBeat.i(14272, false);
        c.b("shopping_card_id", str);
        AppMethodBeat.o(14272);
    }

    public static void F(String str) {
        AppMethodBeat.i(14274, false);
        c.b("wechat_pay_text", str);
        AppMethodBeat.o(14274);
    }

    public static String ab() {
        AppMethodBeat.i(14276, false);
        String a2 = c.a("wechat_pay_text", "");
        AppMethodBeat.o(14276);
        return a2;
    }

    public static void G(String str) {
        AppMethodBeat.i(14277, false);
        c.b("wechat_pay_icon", str);
        AppMethodBeat.o(14277);
    }

    public static String ac() {
        AppMethodBeat.i(14279, false);
        String a2 = c.a("wechat_pay_icon", "");
        AppMethodBeat.o(14279);
        return a2;
    }

    public static void c(int i) {
        AppMethodBeat.i(14281, false);
        c.b("main_user_privacy_protocol_state", i);
        if (i == 1) {
            a = true;
        }
        AppMethodBeat.o(14281);
    }

    public static int ad() {
        AppMethodBeat.i(14282, false);
        int a2 = c.a("main_user_privacy_protocol_state", 0);
        AppMethodBeat.o(14282);
        return a2;
    }

    public static boolean ae() {
        AppMethodBeat.i(14283, false);
        boolean a2 = c.a("login_by_weixin", false);
        AppMethodBeat.o(14283);
        return a2;
    }

    public static void s(boolean z) {
        AppMethodBeat.i(14284, false);
        c.b("login_by_weixin", z);
        AppMethodBeat.o(14284);
    }

    public static void H(String str) {
        AppMethodBeat.i(14290, false);
        c.b("discovery_comment_content", str);
        AppMethodBeat.o(14290);
    }

    public static void d(int i) {
        AppMethodBeat.i(14292, false);
        c.b("agree_privacy_protocol", i);
        AppMethodBeat.o(14292);
    }

    public static void a(long j) {
        AppMethodBeat.i(14294, false);
        c.a("privacy_show_ver", j);
        AppMethodBeat.o(14294);
    }

    public static long af() {
        AppMethodBeat.i(14295, false);
        long b2 = c.b("privacy_show_ver", 0L);
        AppMethodBeat.o(14295);
        return b2;
    }

    public static void ag() {
        AppMethodBeat.i(14297, false);
        c.b("firstTdInit", String.valueOf(SystemClock.elapsedRealtime()));
        AppMethodBeat.o(14297);
    }

    public static void ah() {
        AppMethodBeat.i(14298, false);
        c.b("imeiStatistic", true);
        AppMethodBeat.o(14298);
    }

    public static boolean ai() {
        AppMethodBeat.i(14299, false);
        boolean a2 = c.a("imeiStatistic", false);
        AppMethodBeat.o(14299);
        return a2;
    }

    public static void a(ConfigurationBean configurationBean) {
        boolean z = false;
        AppMethodBeat.i(14300, false);
        if (configurationBean != null) {
            b(configurationBean.userMemberType);
            t(configurationBean.logOutSwitch == 1);
            F(configurationBean.showSearchSizer == 1);
            cn.missfresh.module.base.common.c.a.b = configurationBean.privacyAgreement != null ? configurationBean.privacyAgreement.denyAction : 0;
            i(configurationBean.bindingPhone == 1);
            h(configurationBean.bindingWx == 1);
            l(configurationBean.bindPhoneTips);
            k(configurationBean.bindWxTips);
            g a2 = g.a();
            if (configurationBean.screenshotFlag == 1) {
                z = true;
            }
            a2.c(z);
            g.a().a(configurationBean.screenshotUrl);
            if (configurationBean.payInfo != null) {
                G(configurationBean.payInfo.wechatPayIcon);
                F(configurationBean.payInfo.wechatPayText);
            }
            if (configurationBean.imArea != null) {
                e(configurationBean.imArea.account);
                f(configurationBean.imArea.signature);
            }
            RedDotManager.a().b = configurationBean.newArrived;
            RedDotManager.a().g = configurationBean.myselfRedPointSwitch;
            RedDotManager.a().b();
            if (configurationBean.abTest != null) {
                e(configurationBean.abTest.category);
            }
        }
        AppMethodBeat.o(14300);
    }

    public static void t(boolean z) {
        AppMethodBeat.i(14302, false);
        c.b("log_out_switch", z);
        AppMethodBeat.o(14302);
    }

    public static boolean aj() {
        AppMethodBeat.i(14304, false);
        boolean a2 = c.a("log_out_switch", false);
        AppMethodBeat.o(14304);
        return a2;
    }

    public static void I(String str) {
        AppMethodBeat.i(14306, false);
        c.b(com.umeng.analytics.pro.c.K, str);
        AppMethodBeat.o(14306);
    }

    public static String ak() {
        AppMethodBeat.i(14308, false);
        String a2 = c.a(com.umeng.analytics.pro.c.K, "");
        AppMethodBeat.o(14308);
        return a2;
    }

    public static void u(boolean z) {
        AppMethodBeat.i(14310, false);
        try {
            c.b("small_diamond", z);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        AppMethodBeat.o(14310);
    }

    public static boolean al() {
        AppMethodBeat.i(14311, false);
        boolean a2 = c.a("small_diamond", true);
        AppMethodBeat.o(14311);
        return a2;
    }

    public static void v(boolean z) {
        AppMethodBeat.i(14313, false);
        try {
            c.b("click_flag", z);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        AppMethodBeat.o(14313);
    }

    public static boolean am() {
        AppMethodBeat.i(14315, false);
        boolean a2 = c.a("click_flag", false);
        AppMethodBeat.o(14315);
        return a2;
    }

    private static void F(boolean z) {
        AppMethodBeat.i(14316, false);
        try {
            c.b("show_search_size", z);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        AppMethodBeat.o(14316);
    }

    public static void J(String str) {
        AppMethodBeat.i(14320, false);
        c.b("path_version", str);
        AppMethodBeat.o(14320);
    }

    public static String an() {
        AppMethodBeat.i(14321, false);
        String a2 = c.a("path_version", "");
        AppMethodBeat.o(14321);
        return a2;
    }

    public static boolean ao() {
        AppMethodBeat.i(14333, false);
        boolean a2 = c.a("product_classify_first_viste", true);
        AppMethodBeat.o(14333);
        return a2;
    }

    public static void w(boolean z) {
        AppMethodBeat.i(14335, false);
        c.b("product_classify_first_viste", z);
        AppMethodBeat.o(14335);
    }

    public static boolean ap() {
        AppMethodBeat.i(MtpConstants.FORMAT_DEFINED, false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String a2 = c.a("classify_top_label_dialog_show_time", "");
        if (TextUtils.isEmpty(a2) || !simpleDateFormat.format(new Date()).equals(a2)) {
            AppMethodBeat.o(MtpConstants.FORMAT_DEFINED);
            return true;
        }
        AppMethodBeat.o(MtpConstants.FORMAT_DEFINED);
        return false;
    }

    public static void K(String str) {
        AppMethodBeat.i(MtpConstants.FORMAT_TIFF_EP, false);
        c.b("classify_top_label_dialog_show_time", str);
        AppMethodBeat.o(MtpConstants.FORMAT_TIFF_EP);
    }

    public static void e(int i) {
        AppMethodBeat.i(MtpConstants.FORMAT_BMP, false);
        c.b("classify_top_label_dialog_ab", i);
        AppMethodBeat.o(MtpConstants.FORMAT_BMP);
    }

    public static int aq() {
        AppMethodBeat.i(14342, false);
        int a2 = c.a("classify_top_label_dialog_ab", 0);
        AppMethodBeat.o(14342);
        return a2;
    }

    public static void x(boolean z) {
        AppMethodBeat.i(14345, false);
        c.c("phone_storage_permission", z);
        AppMethodBeat.o(14345);
    }

    public static boolean ar() {
        AppMethodBeat.i(14348, false);
        boolean a2 = c.a("is_has_request_permission", false);
        AppMethodBeat.o(14348);
        return a2;
    }

    public static void y(boolean z) {
        AppMethodBeat.i(14350, false);
        c.b("is_has_request_permission", z);
        AppMethodBeat.o(14350);
    }

    public static boolean b(Context context) {
        boolean z = false;
        AppMethodBeat.i(MtpConstants.FORMAT_DNG, false);
        if (context == null || context.getApplicationInfo() == null) {
            AppMethodBeat.o(MtpConstants.FORMAT_DNG);
            return true;
        }
        if ((context.getApplicationInfo().flags & 2) != 0) {
            z = true;
        }
        AppMethodBeat.o(MtpConstants.FORMAT_DNG);
        return z;
    }

    public static void z(boolean z) {
        e = z;
    }

    public static boolean as() {
        return e;
    }

    public static String at() {
        AppMethodBeat.i(14356, false);
        if (e) {
            String c2 = c("reportTest", "https://dc-eventlog.missfresh.cn/");
            AppMethodBeat.o(14356);
            return c2;
        }
        AppMethodBeat.o(14356);
        return "https://dc-testlog.missfresh.cn/";
    }

    public static void L(String str) {
        AppMethodBeat.i(14358, false);
        b("reportTest", str);
        AppMethodBeat.o(14358);
    }

    public static void f(int i) {
        AppMethodBeat.i(14359, false);
        c.b("statisticsIndex", i);
        AppMethodBeat.o(14359);
    }

    public static int au() {
        AppMethodBeat.i(14361, false);
        int a2 = c.a("statisticsIndex", 0);
        AppMethodBeat.o(14361);
        return a2;
    }

    public static void b(long j) {
        AppMethodBeat.i(14363, false);
        c.a("statisticsTime", j);
        AppMethodBeat.o(14363);
    }

    public static long av() {
        AppMethodBeat.i(14364, false);
        long b2 = c.b("statisticsTime", 0L);
        AppMethodBeat.o(14364);
        return b2;
    }

    public static void A(boolean z) {
        AppMethodBeat.i(14370, false);
        c.b("recommend_user_config", z);
        AppMethodBeat.o(14370);
    }

    public static boolean aw() {
        AppMethodBeat.i(14371, false);
        if (o()) {
            boolean a2 = c.a("recommend_user_config", true);
            AppMethodBeat.o(14371);
            return a2;
        }
        AppMethodBeat.o(14371);
        return true;
    }

    public static void g(int i) {
        AppMethodBeat.i(14372, false);
        c.b("close_evaluate_count", i);
        AppMethodBeat.o(14372);
    }

    public static int ax() {
        AppMethodBeat.i(14374, false);
        int a2 = c.a("close_evaluate_count", 0);
        AppMethodBeat.o(14374);
        return a2;
    }

    public static void c(long j) {
        AppMethodBeat.i(14376, false);
        c.a("close_evaluate_data", j);
        AppMethodBeat.o(14376);
    }

    public static long ay() {
        AppMethodBeat.i(14378, false);
        long b2 = c.b("close_evaluate_data", 0L);
        AppMethodBeat.o(14378);
        return b2;
    }

    public static void B(boolean z) {
        AppMethodBeat.i(14382, false);
        c.b("clicked_mine", z);
        AppMethodBeat.o(14382);
    }

    public static boolean az() {
        AppMethodBeat.i(14383, false);
        boolean a2 = c.a("clicked_mine", false);
        AppMethodBeat.o(14383);
        return a2;
    }

    public static long aA() {
        AppMethodBeat.i(14385, false);
        long b2 = c.b("ShowPushNoticeTime", 0L);
        AppMethodBeat.o(14385);
        return b2;
    }

    public static void aB() {
        AppMethodBeat.i(14386, false);
        c.b("search_result_fast_dot_click", true);
        AppMethodBeat.o(14386);
    }

    public static void d(long j) {
        AppMethodBeat.i(14388, false);
        c.a("ShowPushNoticeTime", j);
        AppMethodBeat.o(14388);
    }

    public static boolean aC() {
        AppMethodBeat.i(14390, false);
        boolean a2 = c.a("search_result_fast_dot_click", false);
        AppMethodBeat.o(14390);
        return a2;
    }

    public static void C(boolean z) {
        AppMethodBeat.i(14396, false);
        c.c("setAliContractPayNotice", z);
        AppMethodBeat.o(14396);
    }

    public static String aD() {
        AppMethodBeat.i(14399, false);
        String a2 = c.a("deviceInfoToken", "");
        AppMethodBeat.o(14399);
        return a2;
    }

    public static void e(long j) {
        AppMethodBeat.i(14400, false);
        i iVar = c;
        iVar.b("deviceInfoToken", j + "");
        AppMethodBeat.o(14400);
    }

    public static boolean aE() {
        AppMethodBeat.i(14401, false);
        boolean a2 = c.a("isOAIDException", false);
        AppMethodBeat.o(14401);
        return a2;
    }

    public static void D(boolean z) {
        AppMethodBeat.i(14402, false);
        c.b("isOAIDException", z);
        AppMethodBeat.o(14402);
    }

    public static void f(long j) {
        AppMethodBeat.i(14404, false);
        c.a("mine_skip_time", j);
        AppMethodBeat.o(14404);
    }

    public static long aF() {
        AppMethodBeat.i(14405, false);
        long b2 = c.b("mine_skip_time", 0L);
        AppMethodBeat.o(14405);
        return b2;
    }

    public static boolean aG() {
        AppMethodBeat.i(14407, false);
        boolean a2 = c.a("isFirstRequestLocationPermission", true);
        AppMethodBeat.o(14407);
        return a2;
    }

    public static void E(boolean z) {
        AppMethodBeat.i(14410, false);
        c.b("isFirstRequestLocationPermission", z);
        AppMethodBeat.o(14410);
    }
}
