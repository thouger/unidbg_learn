package cn.missfresh.module.base.bean;

import cn.missfresh.module.base.bean.ProductsEntity;
import java.util.List;

public class ProductInfoBean implements BaseSubscibe {
    public ActivityInfoBean activityInfo;
    public int activityType;
    public List<BannerInfoBean> bannerInfo;
    public List<CatButtonBean> cartButton;
    public long foodId;
    public boolean isSkeletonData;
    public boolean isSubscibe;
    public PosterInfo posterInfo;
    public PriceProBean pricePro;
    public SaleInfo saleInfo;
    public int saleLimit;
    public boolean sale_out;
    public String salesVolume;
    public ShareInfoBean shareInfo;
    public String sku;
    public String skuProp;
    public String skuPropName;
    public ProductsEntity.SpuInfoBean spuInfo;
    public int stock;
    public String subTitle;
    public int subscribeArrivalReminder;
    public String themeTag;
    public String title;
    public String titleTag;
    public String unit;

    public static class ActivityInfoBean {
        public long activeTime;
        public int activityPrice;
        public String noteInfo;
        public String noteTag;
        public int particiapatorsNeeds;
        public int participatorNum;
        public String productNo;
        public int status;
        public String tag;
    }

    public static class CatButtonBean {
        public int arriveType;
        public String link;
        public String name;
        public int price;
        public int type;
    }

    public static class PosterInfo {
        public String backgroundImage;
        public String link;
        public String productImage;
        public QrInfo qrInfo;
        public String subTitle;
        public String title;
    }

    public static class PriceProBean {
        public PriceBean noVip;
        public PriceBean vip;

        public static class PriceBean {
            public int color;
            public int price;
            public int showStyle;
        }
    }

    public static class QrInfo {
        public int qrSize;
        public int qrX;
        public int qrY;
    }

    public static class SaleInfo {
        public int bgColor;
        public int fontColor;
        public int fontSize;
        public String icon;
        public String saleOutRemind;
        public String text;
    }

    public static class ShareInfoBean {
        public MiniAppShareInfoBean miniAppShareInfo;

        public static class MiniAppShareInfoBean {
            public String miniDesc;
            public String miniImgUrl;
            public String miniOriginalId;
            public String miniPath;
            public String miniTitle;
            public String weChatUrl;
        }
    }

    public String getSku() {
        return this.sku;
    }

    public void setSubscibe(boolean z) {
        this.isSubscibe = z;
    }

    public boolean isArrivalReminderSubscribed() {
        return 2 == this.subscribeArrivalReminder;
    }

    public static class BannerInfoBean implements IBannerData {
        public String image;
        public int type;
        public String video;

        public boolean isVideo() {
            return this.type == 1;
        }

        public String getImagePath() {
            return this.image;
        }

        public String getVideo() {
            return this.video;
        }
    }
}
