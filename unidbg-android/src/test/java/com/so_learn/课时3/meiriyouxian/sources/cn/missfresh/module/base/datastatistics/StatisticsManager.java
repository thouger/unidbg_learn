package cn.missfresh.module.base.datastatistics;

import android.content.Context;
import android.media.midi.MidiDeviceInfo;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.basiclib.net.c;
import cn.missfresh.datastatistics.a;
import cn.missfresh.module.base.common.resourcemanager.bean.SkipBean;
import cn.missfresh.module.base.datastatistics.a.f;
import cn.missfresh.module.base.datastatistics.a.g;
import cn.missfresh.module.base.datastatistics.bean.DataStatisticsMryxBean;
import cn.missfresh.module.base.datastatistics.bean.StatisticsParams;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;
import com.huawei.hms.support.api.push.pushselfshow.click.SelfShowType;
import com.umeng.analytics.pro.ai;
import java.util.HashMap;
import java.util.Map;

public class StatisticsManager extends a {
    private static String a = "";

    @Deprecated
    public static void onNewEventToMRYX(String str, String str2, Map map) {
        AppMethodBeat.i(12484, false);
        if (!e.a) {
            AppMethodBeat.o(12484);
            return;
        }
        a(str, str2, "", "", "", map, false);
        AppMethodBeat.o(12484);
    }

    @Deprecated
    public static void onNewEventToMRYX2(String str, String str2, Map map) {
        AppMethodBeat.i(12485, false);
        if (!e.a) {
            AppMethodBeat.o(12485);
            return;
        }
        a(str, str2, "", "", "", map, true);
        AppMethodBeat.o(12485);
    }

    public static void onNewEventToMRYXOpt(String str, String str2, String str3, String str4, String str5, Map map) {
        AppMethodBeat.i(12486, false);
        if (!e.a) {
            AppMethodBeat.o(12486);
            return;
        }
        a(str, str2, str3, str4, str5, map, false);
        AppMethodBeat.o(12486);
    }

    public static void onNewEventToMRYX2Opt(String str, String str2, String str3, String str4, String str5, Map map) {
        AppMethodBeat.i(12487, false);
        if (!e.a) {
            AppMethodBeat.o(12487);
            return;
        }
        a(str, str2, str3, str4, str5, map, true);
        AppMethodBeat.o(12487);
    }

    private static void a(String str, String str2, String str3, String str4, String str5, Map map, boolean z) {
        AppMethodBeat.i(12488, false);
        if (!e.a) {
            AppMethodBeat.o(12488);
            return;
        }
        HashMap hashMap = new HashMap();
        g.a(str, str2, map, new AnonymousClass1(hashMap));
        if (!hashMap.isEmpty()) {
            if (map != null) {
                map.putAll(hashMap);
            } else {
                map = hashMap;
            }
        }
        if (map == null) {
            map = new HashMap();
        }
        String a2 = a(str, str2, map);
        map.put("spm", a2);
        map.put("pre_spm", a);
        a = a2;
        if (!z) {
            try {
                d.a().a(a(str, str2, str3, str4, str5, map)).b(io.reactivex.f.a.b()).a(io.reactivex.a.b.a.a()).subscribe(new c(null));
            } catch (Exception e) {
                d.a("StatisticsManager", e);
            }
        } else {
            try {
                c.a().a(a(str, str2, str3, str4, str5, map)).b(io.reactivex.f.a.b()).a(io.reactivex.a.b.a.a()).subscribe(new c(null));
            } catch (Exception e2) {
                d.a("StatisticsManager", e2);
            }
        }
        AppMethodBeat.o(12488);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.datastatistics.StatisticsManager$1  reason: invalid class name */
    public static class AnonymousClass1 implements f.a {
        final /* synthetic */ Map a;

        AnonymousClass1(Map map) {
            this.a = map;
        }

        @Override // cn.missfresh.module.base.datastatistics.a.f.a
        public void a(Map<String, String> map) {
            AppMethodBeat.i(12477, false);
            if (map != null) {
                this.a.putAll(map);
            }
            AppMethodBeat.o(12477);
        }
    }

    private static DataStatisticsMryxBean a(String str, String str2, String str3, String str4, String str5, Map map) {
        AppMethodBeat.i(12489, false);
        DataStatisticsMryxBean dataStatisticsMryxBean = new DataStatisticsMryxBean();
        dataStatisticsMryxBean.setInfoData(str2, str, str3, str4, str5);
        if (map != null && !map.isEmpty()) {
            dataStatisticsMryxBean.setParam(map);
        }
        AppMethodBeat.o(12489);
        return dataStatisticsMryxBean;
    }

    public static Map<String, Object> a(Object... objArr) {
        HashMap hashMap;
        AppMethodBeat.i(12490, false);
        if (objArr == null || objArr.length % 2 != 0) {
            hashMap = null;
        } else {
            hashMap = new HashMap();
            for (int i = 0; i < objArr.length; i += 2) {
                int i2 = i + 1;
                if (objArr[i2] == null) {
                    hashMap.put(objArr[i].toString(), "");
                } else {
                    hashMap.put(objArr[i].toString(), objArr[i2]);
                }
            }
        }
        AppMethodBeat.o(12490);
        return hashMap;
    }

    public static void a(String str, Object... objArr) {
        AppMethodBeat.i(12492, false);
        onNewEventToMRYX(str, MidiDeviceInfo.PROPERTY_PRODUCT, a(objArr));
        AppMethodBeat.o(12492);
    }

    public static void a(Context context, String str, Map map) {
        AppMethodBeat.i(12493, false);
        onNewEventToMRYX(str, SkipBean.Type.cart, map);
        AppMethodBeat.o(12493);
    }

    public static void b(String str, Object... objArr) {
        AppMethodBeat.i(12495, false);
        onNewEventToMRYX(str, "order", a(objArr));
        AppMethodBeat.o(12495);
    }

    public static void b(Context context, String str, Map map) {
        AppMethodBeat.i(12499, false);
        onNewEventToMRYX(str, "productCoupon", map);
        AppMethodBeat.o(12499);
    }

    public static void c(Context context, String str, Map map) {
        AppMethodBeat.i(12501, false);
        map.put("businessForm_type", Integer.valueOf(cn.missfresh.module.base.manager.g.a().j()));
        onNewEventToMRYX(str, "home", map);
        AppMethodBeat.o(12501);
    }

    public static void c(String str, Object... objArr) {
        AppMethodBeat.i(12502, false);
        Map<String, Object> a2 = a(objArr);
        a2.put("businessForm_type", Integer.valueOf(cn.missfresh.module.base.manager.g.a().j()));
        onNewEventToMRYX(str, "home", a2);
        AppMethodBeat.o(12502);
    }

    public static void a(String str, String str2, Object... objArr) {
        AppMethodBeat.i(12503, false);
        onNewEventToMRYX(str2, str, a(objArr));
        AppMethodBeat.o(12503);
    }

    public static void d(String str, Object... objArr) {
        AppMethodBeat.i(12504, false);
        Map<String, Object> a2 = a(objArr);
        a2.put("businessForm_type", Integer.valueOf(cn.missfresh.module.base.manager.g.a().j()));
        onNewEventToMRYX(str, "home", a2);
        AppMethodBeat.o(12504);
    }

    public static void e(String str, Object... objArr) {
        AppMethodBeat.i(12506, false);
        Map<String, Object> a2 = a(objArr);
        a2.put("businessForm_type", Integer.valueOf(cn.missfresh.module.base.manager.g.a().k()));
        onNewEventToMRYX(str, "category", a2);
        AppMethodBeat.o(12506);
    }

    public static void f(String str, Object... objArr) {
        AppMethodBeat.i(12510, false);
        onNewEventToMRYX(str, SelfShowType.PUSH_CMD_APP, a(objArr));
        AppMethodBeat.o(12510);
    }

    public static void d(Context context, String str, Map map) {
        AppMethodBeat.i(12512, false);
        onNewEventToMRYX(str, SelfShowType.PUSH_CMD_APP, map);
        AppMethodBeat.o(12512);
    }

    public static void e(Context context, String str, Map map) {
        AppMethodBeat.i(12513, false);
        onNewEventToMRYX(str, "setting_accountSecurity", map);
        AppMethodBeat.o(12513);
    }

    public static void b(String str) {
        AppMethodBeat.i(12517, false);
        onNewEventToMRYX(str, "login", null);
        AppMethodBeat.o(12517);
    }

    public static void g(String str, Object... objArr) {
        AppMethodBeat.i(12519, false);
        onNewEventToMRYX(str, "personal", a(objArr));
        AppMethodBeat.o(12519);
    }

    public static void h(String str, Object... objArr) {
        AppMethodBeat.i(12523, false);
        onNewEventToMRYX(str, SkipBean.Type.cart, a(objArr));
        AppMethodBeat.o(12523);
    }

    public static void f(Context context, String str, Map map) {
        AppMethodBeat.i(12532, false);
        onNewEventToMRYX(str, "edit_address", map);
        AppMethodBeat.o(12532);
    }

    public static void i(String str, Object... objArr) {
        AppMethodBeat.i(12535, false);
        onNewEventToMRYX(str, "pay_success", a(objArr));
        AppMethodBeat.o(12535);
    }

    public static void j(String str, Object... objArr) {
        AppMethodBeat.i(12538, false);
        onNewEventToMRYX(str, "order_list", a(objArr));
        AppMethodBeat.o(12538);
    }

    public static void k(String str, Object... objArr) {
        AppMethodBeat.i(12544, false);
        onNewEventToMRYX(str, "after_product", a(objArr));
        AppMethodBeat.o(12544);
    }

    public static void l(String str, Object... objArr) {
        AppMethodBeat.i(12548, false);
        onNewEventToMRYX(str, "cancel_order", a(objArr));
        AppMethodBeat.o(12548);
    }

    public static void m(String str, Object... objArr) {
        AppMethodBeat.i(12550, false);
        onNewEventToMRYX(str, "select_specificationPop", a(objArr));
        AppMethodBeat.o(12550);
    }

    public static void n(String str, Object... objArr) {
        AppMethodBeat.i(12553, false);
        onNewEventToMRYX(str, "message", a(objArr));
        AppMethodBeat.o(12553);
    }

    public static void o(String str, Object... objArr) {
        AppMethodBeat.i(12555, false);
        onNewEventToMRYX(str, "search", a(objArr));
        AppMethodBeat.o(12555);
    }

    public static void p(String str, Object... objArr) {
        AppMethodBeat.i(12556, false);
        if (cn.missfresh.module.base.manager.g.a().l() == 0) {
            Map<String, Object> a2 = a(objArr);
            a2.put("page_businessForm_type", Integer.valueOf(cn.missfresh.module.base.manager.g.a().l()));
            onNewEventToMRYX(str, "search_result", a2);
        } else {
            Map<String, Object> a3 = a(objArr);
            a3.put("businessForm_type", Integer.valueOf(cn.missfresh.module.base.manager.g.a().l()));
            a3.put("page_businessForm_type", Integer.valueOf(cn.missfresh.module.base.manager.g.a().l()));
            onNewEventToMRYX(str, "search_result", a3);
        }
        AppMethodBeat.o(12556);
    }

    public static void q(String str, Object... objArr) {
        AppMethodBeat.i(12557, false);
        if (cn.missfresh.module.base.manager.g.a().l() == 0) {
            Map<String, Object> a2 = a(objArr);
            a2.put("page_businessForm_type", Integer.valueOf(cn.missfresh.module.base.manager.g.a().l()));
            onNewEventToMRYX(str, "search_result", a2);
        } else {
            Map<String, Object> a3 = a(objArr);
            a3.put("businessForm_type", Integer.valueOf(cn.missfresh.module.base.manager.g.a().l()));
            a3.put("page_businessForm_type", Integer.valueOf(cn.missfresh.module.base.manager.g.a().l()));
            onNewEventToMRYX(str, "search_result", a3);
        }
        AppMethodBeat.o(12557);
    }

    public static void r(String str, Object... objArr) {
        AppMethodBeat.i(12559, false);
        onNewEventToMRYX(str, "account", a(objArr));
        AppMethodBeat.o(12559);
    }

    public static void s(String str, Object... objArr) {
        AppMethodBeat.i(12563, false);
        onNewEventToMRYX(str, "home_select_address", a(objArr));
        AppMethodBeat.o(12563);
    }

    public static void t(String str, Object... objArr) {
        AppMethodBeat.i(12566, false);
        onNewEventToMRYX(str, "add_address", a(objArr));
        AppMethodBeat.o(12566);
    }

    public static void u(String str, Object... objArr) {
        AppMethodBeat.i(12568, false);
        onNewEventToMRYX(str, "add_search_address", a(objArr));
        AppMethodBeat.o(12568);
    }

    public static void v(String str, Object... objArr) {
        AppMethodBeat.i(12570, false);
        onNewEventToMRYX(str, "add_position_address", a(objArr));
        AppMethodBeat.o(12570);
    }

    public static void w(String str, Object... objArr) {
        AppMethodBeat.i(12572, false);
        onNewEventToMRYX(str, "select_city", a(objArr));
        AppMethodBeat.o(12572);
    }

    public static void x(String str, Object... objArr) {
        AppMethodBeat.i(12575, false);
        onNewEventToMRYX(str, "order_select_address", a(objArr));
        AppMethodBeat.o(12575);
    }

    public static void y(String str, Object... objArr) {
        AppMethodBeat.i(12578, false);
        onNewEventToMRYX(str, "bro_gift", a(objArr));
        AppMethodBeat.o(12578);
    }

    public static void z(String str, Object... objArr) {
        AppMethodBeat.i(12581, false);
        onNewEventToMRYX(str, "privacy_pop", a(objArr));
        AppMethodBeat.o(12581);
    }

    public static void a(String str, Map map) {
        AppMethodBeat.i(12584, false);
        if (b.a(str)) {
            AppMethodBeat.o(12584);
            return;
        }
        if (map == null) {
            map = new HashMap(1);
        }
        onNewEventToMRYX(str, "personal", map);
        AppMethodBeat.o(12584);
    }

    public static void A(String str, Object... objArr) {
        AppMethodBeat.i(12586, false);
        onNewEventToMRYX(str, "find_similar", a(objArr));
        AppMethodBeat.o(12586);
    }

    public static void B(String str, Object... objArr) {
        AppMethodBeat.i(12588, false);
        onNewEventToMRYX(str, "reminder_pop", a(objArr));
        AppMethodBeat.o(12588);
    }

    public static void C(String str, Object... objArr) {
        AppMethodBeat.i(12599, false);
        onNewEventToMRYX(str, "after_sku", a(objArr));
        AppMethodBeat.o(12599);
    }

    public static void D(String str, Object... objArr) {
        AppMethodBeat.i(12601, false);
        onNewEventToMRYX(str, "food_works_edittext", a(objArr));
        AppMethodBeat.o(12601);
    }

    public static void E(String str, Object... objArr) {
        AppMethodBeat.i(12603, false);
        onNewEventToMRYX(str, "food_works_uploadpic", a(objArr));
        AppMethodBeat.o(12603);
    }

    public static void F(String str, Object... objArr) {
        AppMethodBeat.i(12605, false);
        onNewEventToMRYX(str, "food_sku_classify", a(objArr));
        AppMethodBeat.o(12605);
    }

    public static void G(String str, Object... objArr) {
        AppMethodBeat.i(12607, false);
        onNewEventToMRYX(str, "food_sku_searchresult", a(objArr));
        AppMethodBeat.o(12607);
    }

    public static void H(String str, Object... objArr) {
        AppMethodBeat.i(12609, false);
        onNewEventToMRYX(str, "food_share", a(objArr));
        AppMethodBeat.o(12609);
    }

    public static void c(String str) {
        AppMethodBeat.i(12612, false);
        if (b.a(str)) {
            AppMethodBeat.o(12612);
            return;
        }
        onNewEventToMRYX("page_show", str, null);
        AppMethodBeat.o(12612);
    }

    public static void d(String str) {
        AppMethodBeat.i(12615, false);
        if (b.a(str)) {
            AppMethodBeat.o(12615);
        } else if (str.equals("home")) {
            HashMap hashMap = new HashMap();
            hashMap.put("businessForm_type", Integer.valueOf(cn.missfresh.module.base.manager.g.a().j()));
            onNewEventToMRYX("page_exit", str, hashMap);
            AppMethodBeat.o(12615);
        } else if (str.equals("category") || str.equals("navi_category")) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put("businessForm_type", Integer.valueOf(cn.missfresh.module.base.manager.g.a().k()));
            onNewEventToMRYX("page_exit", str, hashMap2);
            AppMethodBeat.o(12615);
        } else {
            onNewEventToMRYX("page_exit", str, null);
            AppMethodBeat.o(12615);
        }
    }

    public static void b(String str, Map map) {
        AppMethodBeat.i(12617, false);
        if (b.a(str)) {
            AppMethodBeat.o(12617);
            return;
        }
        if (map == null) {
            map = new HashMap(1);
        }
        onNewEventToMRYX("page_view", str, map);
        AppMethodBeat.o(12617);
    }

    public static void c(String str, Map map) {
        AppMethodBeat.i(12619, false);
        if (b.a(str)) {
            AppMethodBeat.o(12619);
            return;
        }
        if (map == null) {
            map = new HashMap(1);
        }
        onNewEventToMRYX(str, "market_comment", map);
        AppMethodBeat.o(12619);
    }

    public static void I(String str, Object... objArr) {
        AppMethodBeat.i(12623, false);
        onNewEventToMRYX(str, "redPacket_list", a(objArr));
        AppMethodBeat.o(12623);
    }

    public static void J(String str, Object... objArr) {
        AppMethodBeat.i(12629, false);
        onNewEventToMRYX(str, "new_enjoy", a(objArr));
        AppMethodBeat.o(12629);
    }

    public static void K(String str, Object... objArr) {
        AppMethodBeat.i(12632, false);
        onNewEventToMRYX(str, "food_menu_detail", a(objArr));
        AppMethodBeat.o(12632);
    }

    public static void L(String str, Object... objArr) {
        AppMethodBeat.i(12634, false);
        onNewEventToMRYX(str, "food_search_input", a(objArr));
        AppMethodBeat.o(12634);
    }

    public static void M(String str, Object... objArr) {
        AppMethodBeat.i(12637, false);
        onNewEventToMRYX(str, "food_search_result", a(objArr));
        AppMethodBeat.o(12637);
    }

    public static void N(String str, Object... objArr) {
        AppMethodBeat.i(12639, false);
        onNewEventToMRYX(str, "food_category", a(objArr));
        AppMethodBeat.o(12639);
    }

    public static void O(String str, Object... objArr) {
        AppMethodBeat.i(12641, false);
        onNewEventToMRYX(str, "food_author", a(objArr));
        AppMethodBeat.o(12641);
    }

    public static void P(String str, Object... objArr) {
        AppMethodBeat.i(12644, false);
        onNewEventToMRYX(str, "food_personal", a(objArr));
        AppMethodBeat.o(12644);
    }

    public static void Q(String str, Object... objArr) {
        AppMethodBeat.i(12646, false);
        onNewEventToMRYX(str, "food_home", a(objArr));
        AppMethodBeat.o(12646);
    }

    public static void R(String str, Object... objArr) {
        AppMethodBeat.i(12648, false);
        onNewEventToMRYX(str, "food_pic_detail", a(objArr));
        AppMethodBeat.o(12648);
    }

    public static void S(String str, Object... objArr) {
        AppMethodBeat.i(12650, false);
        onNewEventToMRYX(str, "food_works_list", a(objArr));
        AppMethodBeat.o(12650);
    }

    public static void T(String str, Object... objArr) {
        AppMethodBeat.i(12652, false);
        onNewEventToMRYX(str, "activity_new_flashsale", a(objArr));
        AppMethodBeat.o(12652);
    }

    public static void U(String str, Object... objArr) {
        AppMethodBeat.i(12657, false);
        onNewEventToMRYX(str, "activity_colpraise_share", a(objArr));
        AppMethodBeat.o(12657);
    }

    public static void V(String str, Object... objArr) {
        AppMethodBeat.i(12659, false);
        onNewEventToMRYX(str, "food_works_uploadpic", a(objArr));
        AppMethodBeat.o(12659);
    }

    public static void W(String str, Object... objArr) {
        AppMethodBeat.i(12660, false);
        onNewEventToMRYX(str, "activity_colpraise_posters", a(objArr));
        AppMethodBeat.o(12660);
    }

    public static void b(String str, String str2, Object... objArr) {
        AppMethodBeat.i(12661, false);
        onNewEventToMRYX(str, str2, a(objArr));
        AppMethodBeat.o(12661);
    }

    public static void X(String str, Object... objArr) {
        AppMethodBeat.i(12663, false);
        onNewEventToMRYX(str, "push_register", a(objArr));
        AppMethodBeat.o(12663);
    }

    public static void Y(String str, Object... objArr) {
        AppMethodBeat.i(12665, false);
        Map<String, Object> a2 = a(objArr);
        a2.put("businessForm_type", Integer.valueOf(cn.missfresh.module.base.manager.g.a().k()));
        onNewEventToMRYX(str, "category", a2);
        AppMethodBeat.o(12665);
    }

    public static void Z(String str, Object... objArr) {
        AppMethodBeat.i(12667, false);
        onNewEventToMRYX(str, "risk", a(objArr));
        AppMethodBeat.o(12667);
    }

    public static void aa(String str, Object... objArr) {
        AppMethodBeat.i(12668, false);
        onNewEventToMRYX(str, "comment_pop", a(objArr));
        AppMethodBeat.o(12668);
    }

    public static void ab(String str, Object... objArr) {
        AppMethodBeat.i(12670, false);
        onNewEventToMRYX(str, "personal", a(objArr));
        AppMethodBeat.o(12670);
    }

    public static void a() {
        AppMethodBeat.i(12673, false);
        onNewEventToMRYX("get_position", SelfShowType.PUSH_CMD_APP, null);
        AppMethodBeat.o(12673);
    }

    public static void ac(String str, Object... objArr) {
        AppMethodBeat.i(12675, false);
        onNewEventToMRYX(str, "iphoneNum_binding", a(objArr));
        AppMethodBeat.o(12675);
    }

    public static void ad(String str, Object... objArr) {
        AppMethodBeat.i(12677, false);
        onNewEventToMRYX(str, "reward_pop", a(objArr));
        AppMethodBeat.o(12677);
    }

    public static void ae(String str, Object... objArr) {
        AppMethodBeat.i(12681, false);
        onNewEventToMRYX(str, "reward_pay_pop", a(objArr));
        AppMethodBeat.o(12681);
    }

    public static void af(String str, Object... objArr) {
        AppMethodBeat.i(12684, false);
        onNewEventToMRYX(str, "reward_share_pop", a(objArr));
        AppMethodBeat.o(12684);
    }

    public static void b() {
        AppMethodBeat.i(12686, false);
        onNewEventToMRYX("exposure_question", "service", null);
        AppMethodBeat.o(12686);
    }

    public static void a(int i) {
        AppMethodBeat.i(12690, false);
        HashMap hashMap = new HashMap();
        hashMap.put("type", Integer.valueOf(i));
        onNewEventToMRYX("click_order", "service_order", hashMap);
        AppMethodBeat.o(12690);
    }

    public static void c() {
        AppMethodBeat.i(12693, false);
        onNewEventToMRYX("click_nochoose", "service_order", null);
        AppMethodBeat.o(12693);
    }

    public static void ag(String str, Object... objArr) {
        AppMethodBeat.i(12695, false);
        onNewEventToMRYX(str, "sighn_in", a(objArr));
        AppMethodBeat.o(12695);
    }

    public static void c(String str, String str2, Object... objArr) {
        AppMethodBeat.i(12696, false);
        Map<String, Object> a2 = a(objArr);
        d.c("FOOD_DATA", "Label: " + str + " Event: " + str2 + " Params: " + JSON.toJSONString(a2));
        onNewEventToMRYX(str2, str, a2);
        AppMethodBeat.o(12696);
    }

    public static void ah(String str, Object... objArr) {
        AppMethodBeat.i(12702, false);
        onNewEventToMRYX(str, "setting_no_pwd", a(objArr));
        AppMethodBeat.o(12702);
    }

    public static void ai(String str, Object... objArr) {
        AppMethodBeat.i(12703, false);
        onNewEventToMRYX(str, "setting_accountSecurity", a(objArr));
        AppMethodBeat.o(12703);
    }

    public static void aj(String str, Object... objArr) {
        AppMethodBeat.i(12705, false);
        onNewEventToMRYX(str, "service", a(objArr));
        AppMethodBeat.o(12705);
    }

    public static void a(Map map, String str, boolean z, Object... objArr) {
        HashMap hashMap;
        AppMethodBeat.i(12708, false);
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(map);
        }
        Object obj = hashMap.get("label");
        Object obj2 = hashMap.get(ai.e);
        Object obj3 = hashMap.get("button");
        Object obj4 = hashMap.get("event_type");
        hashMap.remove("label");
        hashMap.remove(ai.e);
        if (objArr != null && objArr.length > 0) {
            hashMap.putAll(a(objArr));
        }
        String str2 = "";
        String valueOf = obj != null ? String.valueOf(obj) : str2;
        String valueOf2 = obj2 != null ? String.valueOf(obj2) : str2;
        String valueOf3 = obj3 != null ? String.valueOf(obj3) : str2;
        if (obj4 != null) {
            str2 = String.valueOf(obj4);
        }
        if (!z) {
            hashMap = new HashMap();
        }
        onNewEventToMRYXOpt(str, valueOf, valueOf2, valueOf3, str2, hashMap);
        AppMethodBeat.o(12708);
    }

    public static void b(Map map, String str, boolean z, Object... objArr) {
        HashMap hashMap;
        AppMethodBeat.i(12710, false);
        if (map == null) {
            hashMap = new HashMap();
        } else {
            hashMap = new HashMap(map);
        }
        Object obj = hashMap.get("label");
        Object obj2 = hashMap.get(ai.e);
        Object obj3 = hashMap.get("button");
        Object obj4 = hashMap.get("event_type");
        hashMap.remove("label");
        hashMap.remove(ai.e);
        if (objArr != null && objArr.length > 0) {
            hashMap.putAll(a(objArr));
        }
        String str2 = "";
        String valueOf = obj != null ? String.valueOf(obj) : str2;
        String valueOf2 = obj2 != null ? String.valueOf(obj2) : str2;
        String valueOf3 = obj3 != null ? String.valueOf(obj3) : str2;
        if (obj4 != null) {
            str2 = String.valueOf(obj4);
        }
        if (!z) {
            hashMap = new HashMap();
        }
        onNewEventToMRYXOpt(str, valueOf, valueOf2, valueOf3, str2, hashMap);
        AppMethodBeat.o(12710);
    }

    public static void e(String str) {
        a = str;
    }

    public static String d() {
        return a;
    }

    private static String a(String str, String str2, Map map) {
        AppMethodBeat.i(12718, false);
        Object obj = null;
        Object obj2 = map.containsKey(ai.e) ? map.get(ai.e) : null;
        if (map.containsKey("pos")) {
            obj = map.get("pos");
        }
        Object obj3 = "-";
        if (obj2 == null) {
            obj2 = obj3;
        }
        if (obj != null) {
            obj3 = obj;
        }
        String str3 = "missfresh." + str2 + "." + str + "." + obj2 + "." + obj3;
        AppMethodBeat.o(12718);
        return str3;
    }

    public static void onEventToMRYX(View view) {
        AppMethodBeat.i(12721, false);
        Object tag = view.getTag(-512);
        if (tag instanceof StatisticsParams) {
            HashMap hashMap = new HashMap();
            StatisticsParams statisticsParams = (StatisticsParams) tag;
            if (!TextUtils.isEmpty(statisticsParams.position_key)) {
                ViewParent parent = view.getParent();
                while (!(parent instanceof RecyclerView)) {
                    view = (View) parent;
                    parent = parent.getParent();
                }
                hashMap.put(statisticsParams.position_key, Integer.valueOf(((RecyclerView) parent).getChildViewHolder(view).getAdapterPosition()));
            }
            hashMap.put(ai.e, statisticsParams.module);
            String[] split = statisticsParams.servicePath.split("\\|");
            String[] split2 = statisticsParams.from_value.split("\\|");
            String[] strArr = new String[split.length];
            for (int i = 0; i < split.length; i++) {
                strArr[i] = split2[i].trim() + split[i].trim();
            }
            hashMap.put("requestId", a(strArr));
            a(statisticsParams.eventId, statisticsParams.label, statisticsParams.module, "", "", hashMap, true);
        }
        AppMethodBeat.o(12721);
    }
}
