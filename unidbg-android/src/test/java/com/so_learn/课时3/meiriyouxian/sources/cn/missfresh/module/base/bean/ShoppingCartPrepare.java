package cn.missfresh.module.base.bean;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import java.util.List;

public class ShoppingCartPrepare {
    public static final int QUERY_TYPE_NORMAL = 2;
    public static final int QUERY_TYPE_PRODUCT = 1;
    public static final int QUERY_TYPE_RED_PACKET = 3;
    public static final int QUERY_TYPE_RED_PACKET_ADD_ITEM = 5;
    public static final int QUERY_TYPE_VIP_CARD = 4;
    public boolean is_vip;

    public static class DefaultTime {
        public int day;
        public String time;
    }

    public static class Otd extends BaseOtd {
        public List<OtdEntity> time;
    }

    public static class Products {
        public String arrive_time_text;
        public String arrive_time_type;
        public List<OrderProduct> failed;
        public String failedProductJson;
        public int has_shipping_extra_fee;
        public int nationwide;
        public String notice_remind;
        public List<Otd> otd;
        public OtdResult otdResult;
        public List<OrderProduct> success;
        public String successProductJson;
        public int time_select;
    }

    public String toString() {
        AppMethodBeat.i(7829, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(7829);
        return jSONString;
    }

    public static class OtdResult extends BaseOtd {
        public OtdEntity time;

        public String getFullTime() {
            AppMethodBeat.i(7734, false);
            if (this.time == null) {
                String str = this.label;
                AppMethodBeat.o(7734);
                return str;
            }
            String str2 = this.label + this.time.value;
            AppMethodBeat.o(7734);
            return str2;
        }
    }

    public static class BaseOtd {
        public int day;
        public String delay_remind;
        public String label;
        public int type;

        public String toString() {
            AppMethodBeat.i(7703, false);
            String jSONString = JSON.toJSONString(this);
            AppMethodBeat.o(7703);
            return jSONString;
        }
    }

    public static class OtdEntity {
        public String key;
        public String range_id;
        public String shipping_extra_fee;
        public String value;

        public String toString() {
            AppMethodBeat.i(7723, false);
            String jSONString = JSON.toJSONString(this);
            AppMethodBeat.o(7723);
            return jSONString;
        }
    }

    public static class VIPCardInfo {
        private int back_cash;
        private String back_cash_text;
        private String card_doc;
        private String card_text;
        private String icon_img;
        private int is_opened;
        private String newicon_img;
        private int show_card_list;
        private String status_text;
        private String trade_img;

        public int getIs_opened() {
            return this.is_opened;
        }

        public void setIs_opened(int i) {
            this.is_opened = i;
        }

        public String getIcon_img() {
            return this.icon_img;
        }

        public void setIcon_img(String str) {
            this.icon_img = str;
        }

        public String getCard_text() {
            return this.card_text;
        }

        public void setCard_text(String str) {
            this.card_text = str;
        }

        public int getBack_cash() {
            return this.back_cash;
        }

        public void setBack_cash(int i) {
            this.back_cash = i;
        }

        public String getBack_cash_text() {
            return this.back_cash_text;
        }

        public void setBack_cash_text(String str) {
            this.back_cash_text = str;
        }

        public String getStatus_text() {
            return this.status_text;
        }

        public void setStatus_text(String str) {
            this.status_text = str;
        }

        public int getShow_card_list() {
            return this.show_card_list;
        }

        public void setShow_card_list(int i) {
            this.show_card_list = i;
        }

        public String getTrade_img() {
            return this.trade_img;
        }

        public void setTrade_img(String str) {
            this.trade_img = str;
        }

        public String getNewicon_img() {
            return this.newicon_img;
        }

        public void setNewicon_img(String str) {
            this.newicon_img = str;
        }

        public String getCard_doc() {
            return this.card_doc;
        }

        public void setCard_doc(String str) {
            this.card_doc = str;
        }
    }
}
