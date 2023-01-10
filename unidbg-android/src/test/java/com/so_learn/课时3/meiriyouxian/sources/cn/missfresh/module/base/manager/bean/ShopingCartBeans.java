package cn.missfresh.module.base.manager.bean;

import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.bean.ShoppingCartActive;
import cn.missfresh.module.base.bean.ShoppingCartGift;
import cn.missfresh.module.base.bean.ShoppingCartInActive;
import cn.missfresh.module.base.utils.c.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShopingCartBeans {

    public static class SynShooingCartGroupRes {
        private List<ShoppingCartActive> active_item;
        private List<ShoppingCartInActive> inactive_item;
        private int to_addon_order = -1;
        private String trans_title;
        private String trans_type;

        public interface ITrans_type {
            public static final String ALL = "all";
            public static final String OLD = "old";
        }

        public String getTrans_type() {
            return this.trans_type;
        }

        public void setTrans_type(String str) {
            this.trans_type = str;
        }

        public String getTrans_title() {
            return this.trans_title;
        }

        public void setTrans_title(String str) {
            this.trans_title = str;
        }

        public List<ShoppingCartActive> getActive_item() {
            return this.active_item;
        }

        public void setActive_item(List<ShoppingCartActive> list) {
            this.active_item = list;
        }

        public List<ShoppingCartInActive> getInactive_item() {
            return this.inactive_item;
        }

        public void setInactive_item(List<ShoppingCartInActive> list) {
            this.inactive_item = list;
        }

        public int getTo_addon_order() {
            return this.to_addon_order;
        }

        public void setTo_addon_order(int i) {
            this.to_addon_order = i;
        }
    }

    public static List<SynShooingCartGroupRes> parseRes(String str) {
        AppMethodBeat.i(15046, false);
        ArrayList arrayList = new ArrayList();
        JSONArray parseArray = JSON.parseArray(str);
        if (parseArray != null) {
            Iterator it2 = parseArray.iterator();
            while (it2.hasNext()) {
                JSONObject jSONObject = (JSONObject) it2.next();
                SynShooingCartGroupRes synShooingCartGroupRes = new SynShooingCartGroupRes();
                synShooingCartGroupRes.setTrans_title(jSONObject.getString("trans_title"));
                synShooingCartGroupRes.setTrans_type(jSONObject.getString("trans_type"));
                synShooingCartGroupRes.setTo_addon_order(jSONObject.getIntValue("to_addon_order"));
                synShooingCartGroupRes.setActive_item(b.a(jSONObject.getJSONArray("active_item")));
                synShooingCartGroupRes.setInactive_item(b.c(jSONObject.getJSONArray("inactive_item")));
                arrayList.add(synShooingCartGroupRes);
            }
        }
        AppMethodBeat.o(15046);
        return arrayList;
    }

    public static ShoppingCartGift parseGiftRes(String str) {
        AppMethodBeat.i(15048, false);
        JSONObject parseObject = JSON.parseObject(str);
        ShoppingCartGift shoppingCartGift = new ShoppingCartGift();
        shoppingCartGift.setP_tag(parseObject.getString("p_tag"));
        shoppingCartGift.setP_title(parseObject.getString("p_title"));
        shoppingCartGift.setVip_p_title(parseObject.getString("vip_p_title"));
        shoppingCartGift.setP_goto(parseObject.getString("p_goto"));
        shoppingCartGift.setProductGifts(b.b(parseObject.getJSONArray("p_p_list")));
        shoppingCartGift.setShow_type(parseObject.getIntValue("show_type"));
        shoppingCartGift.setIs_enough(parseObject.getIntValue("is_enough"));
        shoppingCartGift.setNon_is_enough(parseObject.getIntValue("non_is_enough"));
        if (!cn.missfresh.utils.b.a(parseObject.getString("full_img_list"))) {
            shoppingCartGift.setFull_img_list(JSONArray.parseArray(parseObject.getString("full_img_list"), BannerEntity.class));
        }
        AppMethodBeat.o(15048);
        return shoppingCartGift;
    }
}
