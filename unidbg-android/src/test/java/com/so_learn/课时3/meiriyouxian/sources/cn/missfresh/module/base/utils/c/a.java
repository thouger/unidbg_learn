package cn.missfresh.module.base.utils.c;

import cn.missfresh.module.base.bean.ExtraData;
import cn.missfresh.module.base.bean.ShoppingCart;
import cn.missfresh.module.base.bean.ShoppingCartActive;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/* compiled from: H5EventProductParser */
public class a {
    public ShoppingCart a(String str) throws JSONException {
        boolean z = false;
        AppMethodBeat.i(23593, false);
        JSONObject parseObject = JSON.parseObject(str);
        String string = parseObject.getString("sku");
        String string2 = parseObject.getString("from");
        ShoppingCartActive shoppingCartActive = new ShoppingCartActive();
        shoppingCartActive.setSku(string);
        shoppingCartActive.setFrom(string2);
        shoppingCartActive.setQuantity(parseObject.getIntValue("quantity"));
        shoppingCartActive.setName(parseObject.getString("name"));
        shoppingCartActive.setImage(parseObject.getString("image"));
        if (parseObject.getIntValue("vip_product") == 1) {
            z = true;
        }
        shoppingCartActive.setVip_product(z);
        shoppingCartActive.setNationwide(parseObject.getIntValue("nation_wide"));
        shoppingCartActive.setAddCartSuccessCallback(parseObject.getString("success"));
        shoppingCartActive.setAddCartFailedCallback(parseObject.getString("fail"));
        shoppingCartActive.setExtra((ExtraData) parseObject.getObject("extra", ExtraData.class));
        int intValue = parseObject.getIntValue("stock");
        shoppingCartActive.setProduct_limit(intValue);
        if (!shoppingCartActive.getVip_product()) {
            shoppingCartActive.setLimit(intValue);
        } else if (parseObject.containsKey("seckill_limit")) {
            shoppingCartActive.setLimit(parseObject.getIntValue("seckill_limit"));
        }
        shoppingCartActive.setIsChecked(true);
        AppMethodBeat.o(23593);
        return shoppingCartActive;
    }
}
