package cn.missfresh.module.base.bean;

import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class DisplayList implements AbsDisplayList {
    private int amount;
    private int amount_color;
    private String amount_text;
    private int can_select;
    private String fee_nick_name;
    private String icon;
    private int is_positive;
    public int need_amount;
    public String need_repay;
    private String postage_tip;
    private int postage_tip_color;
    private int query_type;
    private List<SubPromotion> subPromotion;
    private String tip;
    private int tipColor;
    private String title;
    private int title_color;
    private int total_amount;
    private String type;
    private int with_line;

    public String getValue() {
        return null;
    }

    public boolean isIntegral() {
        return false;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getTitle_color() {
        return this.title_color;
    }

    public void setTitle_color(int i) {
        this.title_color = i;
    }

    public int getFormatTitleColor() {
        AppMethodBeat.i(4574, false);
        int a = q.a(this.title_color);
        AppMethodBeat.o(4574);
        return a;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int i) {
        this.amount = i;
    }

    public int getAmount_color() {
        return this.amount_color;
    }

    public void setAmount_color(int i) {
        this.amount_color = i;
    }

    public int getFormatAmountColor() {
        AppMethodBeat.i(4584, false);
        int a = q.a(this.amount_color);
        AppMethodBeat.o(4584);
        return a;
    }

    public int getIs_positive() {
        return this.is_positive;
    }

    public void setIs_positive(int i) {
        this.is_positive = i;
    }

    public int getWith_line() {
        return this.with_line;
    }

    public void setWith_line(int i) {
        this.with_line = i;
    }

    public String getFee_nick_name() {
        return this.fee_nick_name;
    }

    public void setFee_nick_name(String str) {
        this.fee_nick_name = str;
    }

    public int getCan_select() {
        return this.can_select;
    }

    public void setCan_select(int i) {
        this.can_select = i;
    }

    public int getQuery_type() {
        return this.query_type;
    }

    public void setQuery_type(int i) {
        this.query_type = i;
    }

    public String getAmount_text() {
        return this.amount_text;
    }

    public void setAmount_text(String str) {
        this.amount_text = str;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getPostage_tip() {
        return this.postage_tip;
    }

    public int getTotal_amount() {
        return this.total_amount;
    }

    public void setTotal_amount(int i) {
        this.total_amount = i;
    }

    public void setPostage_tip(String str) {
        this.postage_tip = str;
    }

    public String getTip() {
        return this.tip;
    }

    public void setTip(String str) {
        this.tip = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public int getTipColor() {
        AppMethodBeat.i(4617, false);
        int a = q.a(this.tipColor);
        AppMethodBeat.o(4617);
        return a;
    }

    public void setTipColor(int i) {
        this.tipColor = i;
    }

    public List<SubPromotion> getSubPromotion() {
        return this.subPromotion;
    }

    public String getNeedRepay() {
        return this.need_repay;
    }

    public int getNeedAmount() {
        return this.need_amount;
    }

    public void setSubPromotion(List<SubPromotion> list) {
        this.subPromotion = list;
    }

    public int getPostage_tip_color() {
        AppMethodBeat.i(4632, false);
        int a = q.a(this.postage_tip_color);
        AppMethodBeat.o(4632);
        return a;
    }

    public void setPostage_tip_color(int i) {
        this.postage_tip_color = i;
    }

    public static class SubPromotion implements AbsDisplayList {
        private int amount;
        private int amount_color;
        private String amount_text;
        private int can_select;
        private String fee_nick_name;
        private String icon;
        private int is_positive;
        private String postage_tip;
        private int query_type;
        private String title;
        private int title_color;
        private int total_amount;
        private int with_line;

        public int getNeedAmount() {
            return 0;
        }

        public String getNeedRepay() {
            return "";
        }

        public int getPostage_tip_color() {
            return 0;
        }

        public List<SubPromotion> getSubPromotion() {
            return null;
        }

        public String getTip() {
            return null;
        }

        public int getTipColor() {
            return 0;
        }

        public String getType() {
            return null;
        }

        public String getValue() {
            return null;
        }

        public boolean isIntegral() {
            return false;
        }

        public String getTitle() {
            return this.title;
        }

        public int getFormatTitleColor() {
            AppMethodBeat.i(4502, false);
            int a = q.a(this.title_color);
            AppMethodBeat.o(4502);
            return a;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public int getTitle_color() {
            return this.title_color;
        }

        public void setTitle_color(int i) {
            this.title_color = i;
        }

        public int getAmount() {
            return this.amount;
        }

        public int getFormatAmountColor() {
            AppMethodBeat.i(4508, false);
            int a = q.a(this.amount_color);
            AppMethodBeat.o(4508);
            return a;
        }

        public void setAmount(int i) {
            this.amount = i;
        }

        public int getAmount_color() {
            return this.amount_color;
        }

        public void setAmount_color(int i) {
            this.amount_color = i;
        }

        public int getIs_positive() {
            return this.is_positive;
        }

        public void setIs_positive(int i) {
            this.is_positive = i;
        }

        public int getWith_line() {
            return this.with_line;
        }

        public void setWith_line(int i) {
            this.with_line = i;
        }

        public String getFee_nick_name() {
            return this.fee_nick_name;
        }

        public void setFee_nick_name(String str) {
            this.fee_nick_name = str;
        }

        public int getCan_select() {
            return this.can_select;
        }

        public void setCan_select(int i) {
            this.can_select = i;
        }

        public int getQuery_type() {
            return this.query_type;
        }

        public void setQuery_type(int i) {
            this.query_type = i;
        }

        public String getAmount_text() {
            return this.amount_text;
        }

        public void setAmount_text(String str) {
            this.amount_text = str;
        }

        public String getIcon() {
            return this.icon;
        }

        public void setIcon(String str) {
            this.icon = str;
        }

        public String getPostage_tip() {
            return this.postage_tip;
        }

        public void setPostage_tip(String str) {
            this.postage_tip = str;
        }

        public int getTotal_amount() {
            return this.total_amount;
        }

        public void setTotal_amount(int i) {
            this.total_amount = i;
        }
    }
}
