package cn.missfresh.module.base.common.resourcemanager.bean;

import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.bean.ShareInfo;
import java.io.Serializable;

public class SkipBean implements Serializable {
    public String activeSource;
    public String bgColor;
    public int buttonBgColor;
    public String buttonText;
    public int buttonTextColor;
    public String code;
    public int countdown_num;
    public String dataId;
    public String frameImage;
    public BannerEntity.GoToParam goto_param;
    public String image;
    public int imageType;
    public String link;
    public String name;
    public String path;
    public String promotionId;
    public int redFlag = 0;
    public ResourceData resourceData;
    public ShareInfo share_invite_content;
    public String subText;
    public String text;
    public int textColor;
    public String title;
    public String type;
    public VoucherInfo voucherInfo;

    public static class ResourceData implements Serializable {
        public String activityCode;
        public int bizType;
        public int position;
        public String scene;
        public String subActivityId;
    }

    public static class Type {
        public static final String ADD_ORDER = "ADD_ORDER";
        public static final String COMMUNITY = "COMMUNITY";
        public static final String INTEGRALSHOP = "INTEGRALSHOP";
        public static final String LIVEBROADCASTING = "LIVEBROADCASTING";
        public static final String MYLOCATION = "MYLOCATION";
        public static final String NATIVE = "NATIVE";
        public static final String OPEN_APP = "OPEN_APP";
        public static final String PRODUCT = "PRODUCT";
        public static final String SCAN_CODE = "SCAN_CODE";
        public static final String SERVICE = "SERVICE";
        public static final String SETTINGS = "SETTINGS";
        public static final String STORED = "STORED";
        public static final String THIRDPARTY = "THIRDPARTY";
        public static final String URL = "URL";
        public static final String WEBPROMOTION = "WEBPROMOTION";
        public static final String cart = "cart";
        public static final String category = "category";
        public static final String home = "home";
        public static final String mybalance = "mybalance";
        public static final String mypacket = "mypacket";
        public static final String mypage = "mypage";
    }

    public static class VoucherInfo implements Serializable {
        public int canGiving;
        public int discountPrice;
        public String endTime;
        public String jumpUrl;
        public String marketingGoal;
        public int maxDiscountPrice;
        public int mutex;
        public int packetType;
        public int productLimit;
        public String productValues;
        public String saleGroupType;
        public String source;
        public String startTime;
        public int thresholdPrice;
        public String used;
        public int userVoucherId;
        public String voucherCode;
        public int voucherId;
        public String voucherName;
        public String voucherType;
    }
}
