package cn.missfresh.module.base.bean;

import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.Serializable;

public class OrderProduct extends ShoppingCartActive implements Serializable {
    private String arrive_type;
    private String better_balance_name;
    private int better_balance_name_color;
    private int better_balance_price;
    private int better_balance_price_color;
    private String exchange_value;
    private int exchange_value_color;
    private String group_internal_id;
    private int is_present;
    private int order_type;
    public String prec;
    private String presale_img_url;
    private String presale_text;
    private int strike;
    private String strike_balance_name;
    private int strike_balance_name_color;
    private int strike_balance_price;
    private int strike_balance_price_color;

    public String getBetter_balance_name() {
        return this.better_balance_name;
    }

    public void setBetter_balance_name(String str) {
        this.better_balance_name = str;
    }

    public String getGroup_internal_id() {
        return this.group_internal_id;
    }

    public void setGroup_internal_id(String str) {
        this.group_internal_id = str;
    }

    public int getBetter_balance_name_color() {
        return this.better_balance_name_color;
    }

    public void setBetter_balance_name_color(int i) {
        this.better_balance_name_color = i;
    }

    public int getFormatBetterBalanceNameColor() {
        AppMethodBeat.i(5384, false);
        int a = q.a(this.better_balance_name_color);
        AppMethodBeat.o(5384);
        return a;
    }

    public int getBetter_balance_price() {
        return this.better_balance_price;
    }

    public void setBetter_balance_price(int i) {
        this.better_balance_price = i;
    }

    public int getOrder_type() {
        return this.order_type;
    }

    public void setOrder_type(int i) {
        this.order_type = i;
    }

    public int getBetter_balance_price_color() {
        return this.better_balance_price_color;
    }

    public void setBetter_balance_price_color(int i) {
        this.better_balance_price_color = i;
    }

    public int getFormatBetterBalancePriceColor() {
        AppMethodBeat.i(5393, false);
        int a = q.a(this.better_balance_price_color);
        AppMethodBeat.o(5393);
        return a;
    }

    public String getStrike_balance_name() {
        return this.strike_balance_name;
    }

    public void setStrike_balance_name(String str) {
        this.strike_balance_name = str;
    }

    public int getStrike_balance_name_color() {
        return this.strike_balance_name_color;
    }

    public void setStrike_balance_name_color(int i) {
        this.strike_balance_name_color = i;
    }

    public int getFormatStrikeBalanceNameColor() {
        AppMethodBeat.i(5397, false);
        int a = q.a(this.strike_balance_name_color);
        AppMethodBeat.o(5397);
        return a;
    }

    public int getStrike_balance_price() {
        return this.strike_balance_price;
    }

    public void setStrike_balance_price(int i) {
        this.strike_balance_price = i;
    }

    public int getStrike_balance_price_color() {
        return this.strike_balance_price_color;
    }

    public void setStrike_balance_price_color(int i) {
        this.strike_balance_price_color = i;
    }

    public int getFormatStrikeBalancePriceColor() {
        AppMethodBeat.i(5403, false);
        int a = q.a(this.strike_balance_price_color);
        AppMethodBeat.o(5403);
        return a;
    }

    public int getStrike() {
        return this.strike;
    }

    public void setStrike(int i) {
        this.strike = i;
    }

    public String getExchange_value() {
        return this.exchange_value;
    }

    public void setExchange_value(String str) {
        this.exchange_value = str;
    }

    public int getExchange_value_color() {
        return this.exchange_value_color;
    }

    public void setExchange_value_color(int i) {
        this.exchange_value_color = i;
    }

    public int getFormateExchangevalueColor() {
        AppMethodBeat.i(5413, false);
        int a = q.a(this.exchange_value_color);
        AppMethodBeat.o(5413);
        return a;
    }

    public String getPresale_img_url() {
        return this.presale_img_url;
    }

    public void setPresale_img_url(String str) {
        this.presale_img_url = str;
    }

    public int getIs_present() {
        return this.is_present;
    }

    public void setIs_present(int i) {
        this.is_present = i;
    }

    public String getPresale_text() {
        return this.presale_text;
    }

    public void setPresale_text(String str) {
        this.presale_text = str;
    }

    public String getArrive_type() {
        return this.arrive_type;
    }

    public void setArrive_type(String str) {
        this.arrive_type = str;
    }
}
