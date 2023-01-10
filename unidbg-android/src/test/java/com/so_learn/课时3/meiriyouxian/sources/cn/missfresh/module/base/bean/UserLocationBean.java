package cn.missfresh.module.base.bean;

import java.util.List;

public class UserLocationBean {
    public String areaCode;
    public int assistLocationFlag;
    public String bigWarehouse;
    public String city;
    public int deliveryType;
    public String district;
    public String imgUrl;
    public String lat;
    public String lng;
    public int myselfRedPointSwitch;
    public int regionId;
    public String sellerId;
    public List<SellerInfo> sellerInfoList;
    public String stationCode;
    public String stationName;
    public int type;
    public String whiteChromeImageUrl;

    public static class SellerInfo {
        public long sellerId;
        public int sellerType;
    }
}
