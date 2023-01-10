package cn.missfresh.module.base.bean;

public class ProductGift extends ShoppingCart {
    private boolean isMainProductChecked;
    private GiftProperty noVipFulGift;
    private String p_tag;
    private GiftProperty vipFulGift;

    public boolean isMainProductChecked() {
        return this.isMainProductChecked;
    }

    public void setMainProductChecked(boolean z) {
        this.isMainProductChecked = z;
    }

    public String getP_tag() {
        return this.p_tag;
    }

    public void setP_tag(String str) {
        this.p_tag = str;
    }

    public GiftProperty getVipFulGift() {
        return this.vipFulGift;
    }

    public void setVipFulGift(GiftProperty giftProperty) {
        this.vipFulGift = giftProperty;
    }

    public GiftProperty getNoVipFulGift() {
        return this.noVipFulGift;
    }

    public void setNoVipFulGift(GiftProperty giftProperty) {
        this.noVipFulGift = giftProperty;
    }

    public static class GiftProperty {
        private int is_enoucgh;
        private String is_only_have;
        private String not_enough;

        public String getNot_enough() {
            return this.not_enough;
        }

        public void setNot_enough(String str) {
            this.not_enough = str;
        }

        public String getIs_only_have() {
            return this.is_only_have;
        }

        public void setIs_only_have(String str) {
            this.is_only_have = str;
        }

        public int getIs_enoucgh() {
            return this.is_enoucgh;
        }

        public void setIs_enoucgh(int i) {
            this.is_enoucgh = i;
        }
    }
}
