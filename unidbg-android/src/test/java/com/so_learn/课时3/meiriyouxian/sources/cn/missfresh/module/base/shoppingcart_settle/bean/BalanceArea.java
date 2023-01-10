package cn.missfresh.module.base.shoppingcart_settle.bean;

import java.util.List;

public class BalanceArea {
    private BalanceAreaBean balanceArea;
    private int code;
    private int index;
    private MakeOrderAreaBean makeOrderArea;
    private ShoppingCartCloseBean popWindow;
    private List<SettleSkuBean> products;
    private int requestId;

    public MakeOrderAreaBean getMakeOrderArea() {
        return this.makeOrderArea;
    }

    public void setMakeOrderArea(MakeOrderAreaBean makeOrderAreaBean) {
        this.makeOrderArea = makeOrderAreaBean;
    }

    public BalanceAreaBean getBalanceArea() {
        return this.balanceArea;
    }

    public void setBalanceArea(BalanceAreaBean balanceAreaBean) {
        this.balanceArea = balanceAreaBean;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }

    public ShoppingCartCloseBean getPopWindow() {
        return this.popWindow;
    }

    public void setPopWindow(ShoppingCartCloseBean shoppingCartCloseBean) {
        this.popWindow = shoppingCartCloseBean;
    }

    public int getRequestId() {
        return this.requestId;
    }

    public void setRequestId(int i) {
        this.requestId = i;
    }

    public List<SettleSkuBean> getProducts() {
        return this.products;
    }

    public void setProducts(List<SettleSkuBean> list) {
        this.products = list;
    }

    public static class MakeOrderAreaBean {
        private int makeOrderType;
        private String text;

        public int getMakeOrderType() {
            return this.makeOrderType;
        }

        public void setMakeOrderType(int i) {
            this.makeOrderType = i;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String str) {
            this.text = str;
        }
    }

    public static class BalanceAreaBean {
        private int balanceCount;
        private String discountAmount;
        private DiscountDetailBean discountDetail;
        private int payAmount;
        private String payTips;

        public int getBalanceCount() {
            return this.balanceCount;
        }

        public void setBalanceCount(int i) {
            this.balanceCount = i;
        }

        public String getPayTips() {
            return this.payTips;
        }

        public void setPayTips(String str) {
            this.payTips = str;
        }

        public int getPayAmount() {
            return this.payAmount;
        }

        public void setPayAmount(int i) {
            this.payAmount = i;
        }

        public String getDiscountAmount() {
            return this.discountAmount;
        }

        public void setDiscountAmount(String str) {
            this.discountAmount = str;
        }

        public DiscountDetailBean getDiscountDetail() {
            return this.discountDetail;
        }

        public void setDiscountDetail(DiscountDetailBean discountDetailBean) {
            this.discountDetail = discountDetailBean;
        }
    }

    public static class DiscountItemsBean {
        private String amount;
        private String groupSubTitle;
        private String groupTitle;
        private boolean isShowDivider;
        private String name;
        private int type = 2;

        public String getGroupSubTitle() {
            return this.groupSubTitle;
        }

        public void setGroupSubTitle(String str) {
            this.groupSubTitle = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getAmount() {
            return this.amount;
        }

        public void setAmount(String str) {
            this.amount = str;
        }

        public String getGroupTitle() {
            return this.groupTitle;
        }

        public void setGroupTitle(String str) {
            this.groupTitle = str;
        }

        public void setShowDivider(boolean z) {
            this.isShowDivider = z;
        }

        public boolean isShowDivider() {
            return this.isShowDivider;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }
    }

    public static class VoucherListBean {
        private String saleGroupId;
        private long voucherId;
        private int voucherType;

        public int getVoucherType() {
            return this.voucherType;
        }

        public void setVoucherType(int i) {
            this.voucherType = i;
        }

        public long getVoucherId() {
            return this.voucherId;
        }

        public void setVoucherId(long j) {
            this.voucherId = j;
        }

        public String getSaleGroupId() {
            return this.saleGroupId;
        }

        public void setSaleGroupId(String str) {
            this.saleGroupId = str;
        }
    }

    public static class DiscountDetailBean {
        private List<DiscountGroupsBean> discountGroups;
        private String subTitle;
        private String title;
        private String totalAmount;
        private String totalTitle;
        private List<VoucherListBean> voucherList;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getSubTitle() {
            return this.subTitle;
        }

        public void setSubTitle(String str) {
            this.subTitle = str;
        }

        public String getTotalTitle() {
            return this.totalTitle;
        }

        public void setTotalTitle(String str) {
            this.totalTitle = str;
        }

        public String getTotalAmount() {
            return this.totalAmount;
        }

        public void setTotalAmount(String str) {
            this.totalAmount = str;
        }

        public List<DiscountGroupsBean> getDiscountGroups() {
            return this.discountGroups;
        }

        public void setDiscountGroups(List<DiscountGroupsBean> list) {
            this.discountGroups = list;
        }

        public List<VoucherListBean> getVoucherList() {
            return this.voucherList;
        }

        public void setVoucherList(List<VoucherListBean> list) {
            this.voucherList = list;
        }
    }

    public static class DiscountGroupsBean {
        private List<DiscountItemsBean> discountItems;
        private String groupSubTitle;
        private String groupTitle;

        public String getGroupSubTitle() {
            return this.groupSubTitle;
        }

        public void setGroupSubTitle(String str) {
            this.groupSubTitle = str;
        }

        public String getGroupTitle() {
            return this.groupTitle;
        }

        public void setGroupTitle(String str) {
            this.groupTitle = str;
        }

        public List<DiscountItemsBean> getDiscountItems() {
            return this.discountItems;
        }

        public void setDiscountItems(List<DiscountItemsBean> list) {
            this.discountItems = list;
        }
    }
}
