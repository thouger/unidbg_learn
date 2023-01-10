package cn.missfresh.module.base.utils.c;

import cn.missfresh.module.base.bean.ProductGift;
import cn.missfresh.module.base.bean.ShoppingCartActive;
import cn.missfresh.module.base.bean.ShoppingCartInActive;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ShoppingCartParser */
public class b {
    public static List<ShoppingCartActive> a(JSONArray jSONArray) {
        ArrayList arrayList;
        Exception e;
        AppMethodBeat.i(23594, false);
        if (jSONArray != null) {
            try {
                arrayList = new ArrayList();
                try {
                    int size = jSONArray.size();
                    for (int i = 0; i < size; i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        if (jSONObject != null) {
                            arrayList.add(b(jSONObject));
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    d.a("ShoppingCartParser", e);
                    AppMethodBeat.o(23594);
                    return arrayList;
                }
            } catch (Exception e3) {
                e = e3;
                arrayList = null;
                d.a("ShoppingCartParser", e);
                AppMethodBeat.o(23594);
                return arrayList;
            }
        } else {
            arrayList = null;
        }
        AppMethodBeat.o(23594);
        return arrayList;
    }

    public static List<ProductGift> b(JSONArray jSONArray) {
        ArrayList arrayList;
        Exception e;
        AppMethodBeat.i(23595, false);
        if (jSONArray != null) {
            try {
                arrayList = new ArrayList();
                try {
                    int size = jSONArray.size();
                    for (int i = 0; i < size; i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        if (jSONObject != null) {
                            arrayList.add(a(jSONObject));
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    d.a("ShoppingCartParser", e);
                    AppMethodBeat.o(23595);
                    return arrayList;
                }
            } catch (Exception e3) {
                e = e3;
                arrayList = null;
                d.a("ShoppingCartParser", e);
                AppMethodBeat.o(23595);
                return arrayList;
            }
        } else {
            arrayList = null;
        }
        AppMethodBeat.o(23595);
        return arrayList;
    }

    private static ShoppingCartActive b(JSONObject jSONObject) {
        AppMethodBeat.i(23596, false);
        ShoppingCartActive shoppingCartActive = (ShoppingCartActive) a(ShoppingCartActive.class, jSONObject);
        if (shoppingCartActive != null) {
            shoppingCartActive.setPresent_num(jSONObject.getIntValue("present_num"));
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("p_product_info");
                if (jSONArray != null) {
                    shoppingCartActive.setProductGifts(b(jSONArray));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(23596);
        return shoppingCartActive;
    }

    public static ProductGift a(JSONObject jSONObject) {
        AppMethodBeat.i(23597, false);
        ProductGift productGift = (ProductGift) a(ProductGift.class, jSONObject);
        ProductGift.GiftProperty giftProperty = (ProductGift.GiftProperty) JSONObject.parseObject(jSONObject.getJSONObject("vip_full_gift").toString(), ProductGift.GiftProperty.class);
        ProductGift.GiftProperty giftProperty2 = (ProductGift.GiftProperty) JSONObject.parseObject(jSONObject.getJSONObject("non_vip_full_gift").toString(), ProductGift.GiftProperty.class);
        if (productGift != null) {
            productGift.setVipFulGift(giftProperty);
            productGift.setNoVipFulGift(giftProperty2);
            productGift.setP_tag(jSONObject.getString("p_tag"));
        }
        AppMethodBeat.o(23597);
        return productGift;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T extends cn.missfresh.module.base.bean.ShoppingCart> T a(java.lang.Class<T> r6, com.alibaba.fastjson.JSONObject r7) {
        /*
        // Method dump skipped, instructions count: 426
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.utils.c.b.a(java.lang.Class, com.alibaba.fastjson.JSONObject):cn.missfresh.module.base.bean.ShoppingCart");
    }

    public static List<ShoppingCartInActive> c(JSONArray jSONArray) {
        ArrayList arrayList;
        Exception e;
        AppMethodBeat.i(23599, false);
        if (jSONArray != null) {
            try {
                arrayList = new ArrayList();
                try {
                    int size = jSONArray.size();
                    for (int i = 0; i < size; i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        if (jSONObject != null) {
                            arrayList.add((ShoppingCartInActive) a(ShoppingCartInActive.class, jSONObject));
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    d.a("ShoppingCartParser", e);
                    AppMethodBeat.o(23599);
                    return arrayList;
                }
            } catch (Exception e3) {
                e = e3;
                arrayList = null;
                d.a("ShoppingCartParser", e);
                AppMethodBeat.o(23599);
                return arrayList;
            }
        } else {
            arrayList = null;
        }
        AppMethodBeat.o(23599);
        return arrayList;
    }
}
