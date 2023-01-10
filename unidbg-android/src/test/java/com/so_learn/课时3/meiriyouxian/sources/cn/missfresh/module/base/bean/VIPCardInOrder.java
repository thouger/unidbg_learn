package cn.missfresh.module.base.bean;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;

public class VIPCardInOrder {
    public static final String CANCEL_SELECTED_VIP_CARD = "-1";
    public static final String NO_VIP_CARD = "0";
    private int back_cash;
    private String back_doc;
    private String card_doc;
    private int card_price;
    private int card_total;
    private int card_type;
    private String icon_img;
    private int id;
    private int is_default;
    private int max_total;
    private String newicon_img;
    private int ratio;
    private String trade_img;
    private String vip_name;

    public int getBack_cash() {
        return this.back_cash;
    }

    public void setBack_cash(int i) {
        this.back_cash = i;
    }

    public String getBack_doc() {
        return this.back_doc;
    }

    public void setBack_doc(String str) {
        this.back_doc = str;
    }

    public int getCard_price() {
        return this.card_price;
    }

    public void setCard_price(int i) {
        this.card_price = i;
    }

    public int getCard_total() {
        return this.card_total;
    }

    public void setCard_total(int i) {
        this.card_total = i;
    }

    public String getIcon_img() {
        return this.icon_img;
    }

    public void setIcon_img(String str) {
        this.icon_img = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getIdString() {
        AppMethodBeat.i(9810, false);
        String valueOf = String.valueOf(this.id);
        AppMethodBeat.o(9810);
        return valueOf;
    }

    public int getMax_total() {
        return this.max_total;
    }

    public void setMax_total(int i) {
        this.max_total = i;
    }

    public int getRatio() {
        return this.ratio;
    }

    public void setRatio(int i) {
        this.ratio = i;
    }

    public String getVip_name() {
        return this.vip_name;
    }

    public void setVip_name(String str) {
        this.vip_name = str;
    }

    public String getTrade_img() {
        return this.trade_img;
    }

    public void setTrade_img(String str) {
        this.trade_img = str;
    }

    public int getCard_type() {
        return this.card_type;
    }

    public void setCard_type(int i) {
        this.card_type = i;
    }

    public int getIs_default() {
        return this.is_default;
    }

    public void setIs_default(int i) {
        this.is_default = i;
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

    public String toString() {
        AppMethodBeat.i(9840, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(9840);
        return jSONString;
    }
}
