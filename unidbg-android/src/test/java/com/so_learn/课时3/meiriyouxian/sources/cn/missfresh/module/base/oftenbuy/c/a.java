package cn.missfresh.module.base.oftenbuy.c;

import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.umeng.analytics.pro.ai;
import java.util.List;

/* compiled from: OftenBuyStatistics */
public class a {
    public static void a() {
        AppMethodBeat.i(16289, false);
        StatisticsManager.o("exposure_all", ai.e, "recommend_favourite");
        AppMethodBeat.o(16289);
    }

    public static void b() {
        AppMethodBeat.i(16291, false);
        StatisticsManager.o("click_all", ai.e, "recommend_favourite");
        AppMethodBeat.o(16291);
    }

    public static void a(String str, String str2, int i, String str3, String str4, int i2) {
        AppMethodBeat.i(16293, false);
        StatisticsManager.o("click_recommend_favourite", ai.e, "recommend_favourite", "favourite_id", str, "sku", str2, "sku_type", Integer.valueOf(i), "source_request_id", str3, "recommend_request_id", str4, "pos", Integer.valueOf(i2));
        AppMethodBeat.o(16293);
    }

    public static void a(String str, String str2, List<String> list, List<String> list2, List<Integer> list3) {
        AppMethodBeat.i(16294, false);
        StatisticsManager.o("exposure_product", ai.e, "recommend_favourite", "favourite_id", str, "sku", list2, "sku_type", list3, "source_request_id", str2, "recommend_request_id", list);
        AppMethodBeat.o(16294);
    }

    public static void a(String str, String str2, String str3, String str4, int i, int i2) {
        AppMethodBeat.i(16296, false);
        StatisticsManager.o("add_cart", ai.e, "recommend_favourite", "favourite_id", str, "sku", str4, "sku_type", Integer.valueOf(i), "source_request_id", str2, "recommend_request_id", str3, "pos", Integer.valueOf(i2));
        AppMethodBeat.o(16296);
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0071: APUT  (r2v1 java.lang.Object[]), (15 ??[int, float, short, byte, char]), (r5v7 java.lang.Object) */
    public static void a(String str, String str2, String str3, int i, int i2, int i3, int i4) {
        AppMethodBeat.i(16299, false);
        Object[] objArr = new Object[18];
        objArr[0] = ai.e;
        objArr[1] = "recommend_favourite";
        objArr[2] = "favourite_id";
        objArr[3] = str;
        objArr[4] = "click_type";
        objArr[5] = Integer.valueOf(i);
        objArr[6] = "sku_type";
        objArr[7] = Integer.valueOf(i2);
        objArr[8] = "source_request_id";
        objArr[9] = str2;
        objArr[10] = "recommend_request_id";
        objArr[11] = str3;
        objArr[12] = "pos_favourite";
        objArr[13] = Integer.valueOf(i3);
        objArr[14] = "pos";
        objArr[15] = i4 != -1 ? Integer.valueOf(i4) : "";
        objArr[16] = "sku_type";
        objArr[17] = Integer.valueOf(i2);
        StatisticsManager.h("click_recommend_favourite", objArr);
        AppMethodBeat.o(16299);
    }

    public static void a(String str, String str2, String str3, String str4, int i, int i2, int i3) {
        AppMethodBeat.i(16301, false);
        StatisticsManager.h("add_cart", ai.e, "recommend_favourite", "favourite_id", str, "sku", str4, "sku_type", Integer.valueOf(i), "source_request_id", str2, "recommend_request_id", str3, "pos_favourite", Integer.valueOf(i2), "pos", Integer.valueOf(i3));
        AppMethodBeat.o(16301);
    }

    public static void a(String str, List<String> list, List<String> list2, List<Integer> list3, int i, String str2) {
        AppMethodBeat.i(16307, false);
        StatisticsManager.c("exposure_product", ai.e, "recommend_favourite", "favourite_id", str, "sku", list2, "sku_type", list3, "recommend_request_id", list, "pos_favourite", Integer.valueOf(i), "source_request_id", str2);
        AppMethodBeat.o(16307);
    }

    public static void b(String str, String str2, String str3, String str4, int i, int i2, int i3) {
        AppMethodBeat.i(16309, false);
        StatisticsManager.c("add_cart", ai.e, "recommend_favourite", "favourite_id", str, "sku", str4, "sku_type", Integer.valueOf(i), "source_request_id", str2, "recommend_request_id", str3, "pos", Integer.valueOf(i2), "pos_favourite", Integer.valueOf(i3));
        AppMethodBeat.o(16309);
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0062: APUT  (r2v1 java.lang.Object[]), (13 ??[int, float, short, byte, char]), (r4v11 java.lang.Object) */
    public static void b(String str, String str2, String str3, int i, int i2, int i3, int i4) {
        AppMethodBeat.i(16311, false);
        Object[] objArr = new Object[16];
        objArr[0] = ai.e;
        objArr[1] = "recommend_favourite";
        objArr[2] = "favourite_id";
        objArr[3] = str;
        objArr[4] = "click_type";
        objArr[5] = Integer.valueOf(i);
        objArr[6] = "source_request_id";
        objArr[7] = str2;
        objArr[8] = "recommend_request_id";
        objArr[9] = str3;
        objArr[10] = "pos_favourite";
        objArr[11] = Integer.valueOf(i3);
        objArr[12] = "pos";
        objArr[13] = i4 != -1 ? Integer.valueOf(i4) : "";
        objArr[14] = "sku_type";
        objArr[15] = Integer.valueOf(i2);
        StatisticsManager.c("click_recommend_favourite", objArr);
        AppMethodBeat.o(16311);
    }
}
