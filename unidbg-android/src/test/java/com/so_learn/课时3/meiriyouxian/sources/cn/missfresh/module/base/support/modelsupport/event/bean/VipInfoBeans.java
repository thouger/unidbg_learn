package cn.missfresh.module.base.support.modelsupport.event.bean;

import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.bean.ProductsEntity;
import java.util.List;

public class VipInfoBeans {

    public static class IntegralPageInfo {
        private List<BannerEntity> banner;
        private String exchange_img;
        private String exchange_lock_img;
        private List<IconInfo> icon_info;
        private int integral_price_color;
        private boolean is_vip;
        private int member_level;
        MyVipInfo my_vip_info;
        private List<List<IntegralProduct>> products;
        private String sell_out_img;
        private VipCardInfo vipCardInfo;

        public VipCardInfo getVipCardInfo() {
            return this.vipCardInfo;
        }

        public void setVipCardInfo(VipCardInfo vipCardInfo) {
            this.vipCardInfo = vipCardInfo;
        }

        public int getIntegral_price_color() {
            return this.integral_price_color;
        }

        public void setIntegral_price_color(int i) {
            this.integral_price_color = i;
        }

        public String getExchange_img() {
            return this.exchange_img;
        }

        public void setExchange_img(String str) {
            this.exchange_img = str;
        }

        public String getSell_out_img() {
            return this.sell_out_img;
        }

        public void setSell_out_img(String str) {
            this.sell_out_img = str;
        }

        public String getExchange_lock_img() {
            return this.exchange_lock_img;
        }

        public void setExchange_lock_img(String str) {
            this.exchange_lock_img = str;
        }

        public List<IconInfo> getIcon_info() {
            return this.icon_info;
        }

        public void setIcon_info(List<IconInfo> list) {
            this.icon_info = list;
        }

        public int getMember_level() {
            return this.member_level;
        }

        public void setMember_level(int i) {
            this.member_level = i;
        }

        public MyVipInfo getMy_vip_info() {
            return this.my_vip_info;
        }

        public void setMy_vip_info(MyVipInfo myVipInfo) {
            this.my_vip_info = myVipInfo;
        }

        public List<BannerEntity> getBanner() {
            return this.banner;
        }

        public void setBanner(List<BannerEntity> list) {
            this.banner = list;
        }

        public List<List<IntegralProduct>> getProducts() {
            return this.products;
        }

        public void setProducts(List<List<IntegralProduct>> list) {
            this.products = list;
        }

        public boolean is_vip() {
            return this.is_vip;
        }

        public void setIs_vip(boolean z) {
            this.is_vip = z;
        }
    }

    public static class MyVipInfo {
        private String integral_bg_img;
        private String integral_rule_detail_img;
        private String integral_rule_img_url;
        private int my_integral;
        private String my_integral_rules;
        private int my_total_integral;
        private int my_vip_level;
        private List<VipLevel> vip_level;
        private String vip_title;
        private String vip_title_img;
        private String vip_title_img_text;

        public String getIntegral_bg_img() {
            return this.integral_bg_img;
        }

        public void setIntegral_bg_img(String str) {
            this.integral_bg_img = str;
        }

        public String getVip_title() {
            return this.vip_title;
        }

        public void setVip_title(String str) {
            this.vip_title = str;
        }

        public int getMy_total_integral() {
            return this.my_total_integral;
        }

        public void setMy_total_integral(int i) {
            this.my_total_integral = i;
        }

        public String getVip_title_img() {
            return this.vip_title_img;
        }

        public void setVip_title_img(String str) {
            this.vip_title_img = str;
        }

        public String getVip_title_img_text() {
            return this.vip_title_img_text;
        }

        public void setVip_title_img_text(String str) {
            this.vip_title_img_text = str;
        }

        public int getMy_integral() {
            return this.my_integral;
        }

        public void setMy_integral(int i) {
            this.my_integral = i;
        }

        public int getMy_vip_level() {
            return this.my_vip_level;
        }

        public void setMy_vip_level(int i) {
            this.my_vip_level = i;
        }

        public String getMy_integral_rules() {
            return this.my_integral_rules;
        }

        public void setMy_integral_rules(String str) {
            this.my_integral_rules = str;
        }

        public String getIntegral_rule_img_url() {
            return this.integral_rule_img_url;
        }

        public void setIntegral_rule_img_url(String str) {
            this.integral_rule_img_url = str;
        }

        public String getIntegral_rule_detail_img() {
            return this.integral_rule_detail_img;
        }

        public void setIntegral_rule_detail_img(String str) {
            this.integral_rule_detail_img = str;
        }

        public List<VipLevel> getVip_level() {
            return this.vip_level;
        }

        public void setVip_level(List<VipLevel> list) {
            this.vip_level = list;
        }
    }

    public static class VipLevel {
        private String level_growth_text;
        private int level_growth_text_color;
        private String level_growth_title;
        private String level_image_gray;
        private String level_image_v_white;
        private String level_image_v_yellow;
        private String level_image_yellow;
        private int level_integral;
        private String level_text;

        public String getLevel_image_v_white() {
            return this.level_image_v_white;
        }

        public void setLevel_image_v_white(String str) {
            this.level_image_v_white = str;
        }

        public String getLevel_image_v_yellow() {
            return this.level_image_v_yellow;
        }

        public void setLevel_image_v_yellow(String str) {
            this.level_image_v_yellow = str;
        }

        public String getLevel_growth_text() {
            return this.level_growth_text;
        }

        public void setLevel_growth_text(String str) {
            this.level_growth_text = str;
        }

        public int getLevel_growth_text_color() {
            return this.level_growth_text_color;
        }

        public void setLevel_growth_text_color(int i) {
            this.level_growth_text_color = i;
        }

        public String getLevel_image_yellow() {
            return this.level_image_yellow;
        }

        public void setLevel_image_yellow(String str) {
            this.level_image_yellow = str;
        }

        public String getLevel_image_gray() {
            return this.level_image_gray;
        }

        public void setLevel_image_gray(String str) {
            this.level_image_gray = str;
        }

        public String getLevel_text() {
            return this.level_text;
        }

        public String getLevel_growth_title() {
            return this.level_growth_title;
        }

        public void setLevel_growth_title(String str) {
            this.level_growth_title = str;
        }

        public void setLevel_text(String str) {
            this.level_text = str;
        }

        public int getLevel_integral() {
            return this.level_integral;
        }

        public void setLevel_integral(int i) {
            this.level_integral = i;
        }
    }

    public static class IntegralProduct extends ProductsEntity {
        private int exchange_limit_day;
        private String integral_price;
        private int integral_product_type;
        private int product_need_level;

        public interface IproductType {
            public static final int chuZhiKa = 4;
            public static final int normal = 0;
            public static final int shiWu = 2;
            public static final int thirdPartTicket = 5;
            public static final int youHuiQuan = 1;
            public static final int zheKouKa = 3;
        }

        public String getIntegral_price() {
            return this.integral_price;
        }

        public void setIntegral_price(String str) {
            this.integral_price = str;
        }

        public int getExchange_limit_day() {
            return this.exchange_limit_day;
        }

        public void setExchange_limit_day(int i) {
            this.exchange_limit_day = i;
        }

        public int getIntegral_product_type() {
            return this.integral_product_type;
        }

        public void setIntegral_product_type(int i) {
            this.integral_product_type = i;
        }

        public int getProduct_need_level() {
            return this.product_need_level;
        }

        public void setProduct_need_level(int i) {
            this.product_need_level = i;
        }
    }

    public static class CheckResSuc {
        private String description;
        private String tip;

        public String getTip() {
            return this.tip;
        }

        public void setTip(String str) {
            this.tip = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            this.description = str;
        }
    }

    public static class VirtualProBuySuc {
        private String context;
        private String header;
        private String product_sku;

        public String getHeader() {
            return this.header;
        }

        public void setHeader(String str) {
            this.header = str;
        }

        public String getContext() {
            return this.context;
        }

        public void setContext(String str) {
            this.context = str;
        }

        public String getProduct_sku() {
            return this.product_sku;
        }

        public void setProduct_sku(String str) {
            this.product_sku = str;
        }
    }

    public static class VipCardInfo {
        private String ratioText;
        private String vipBgImg;
        private String vipName;

        public String getVipName() {
            return this.vipName;
        }

        public void setVipName(String str) {
            this.vipName = str;
        }

        public String getRatioText() {
            return this.ratioText;
        }

        public void setRatioText(String str) {
            this.ratioText = str;
        }

        public String getVipBgImg() {
            return this.vipBgImg;
        }

        public void setVipBgImg(String str) {
            this.vipBgImg = str;
        }
    }

    public static class IconInfo {
        private int all_mission_num;
        private int complete_num;
        private String icon_img;
        private String icon_tag_get_img;
        private String icon_text;
        private int icon_text_color;
        private int icon_type;
        private boolean is_can_open;
        private String mission_tag_get_img;
        private String mission_tag_open_img;
        private long task_id;
        private String to_link;

        public interface IconType {
            public static final int CHARGE_GIVE_INTEGRAL = 3;
            public static final int FREE_BOX = 1;
            public static final int H5_URL = 6;
            public static final int INVITE_REDPACKET = 4;
            public static final int SHARE_FOR_GIFT = 5;
            public static final int TASK_BOX = 2;
            public static final int VIP_CARD = 8;
            public static final int WEB_PROMOTION = 7;
        }

        public int getComplete_num() {
            return this.complete_num;
        }

        public void setComplete_num(int i) {
            this.complete_num = i;
        }

        public int getAll_mission_num() {
            return this.all_mission_num;
        }

        public void setAll_mission_num(int i) {
            this.all_mission_num = i;
        }

        public String getMission_tag_get_img() {
            return this.mission_tag_get_img;
        }

        public void setMission_tag_get_img(String str) {
            this.mission_tag_get_img = str;
        }

        public String getMission_tag_open_img() {
            return this.mission_tag_open_img;
        }

        public void setMission_tag_open_img(String str) {
            this.mission_tag_open_img = str;
        }

        public String getTo_link() {
            return this.to_link;
        }

        public void setTo_link(String str) {
            this.to_link = str;
        }

        public String getIcon_img() {
            return this.icon_img;
        }

        public void setIcon_img(String str) {
            this.icon_img = str;
        }

        public String getIcon_text() {
            return this.icon_text;
        }

        public void setIcon_text(String str) {
            this.icon_text = str;
        }

        public int getIcon_text_color() {
            return this.icon_text_color;
        }

        public void setIcon_text_color(int i) {
            this.icon_text_color = i;
        }

        public int getIcon_type() {
            return this.icon_type;
        }

        public void setIcon_type(int i) {
            this.icon_type = i;
        }

        public boolean is_can_open() {
            return this.is_can_open;
        }

        public void setIs_can_open(boolean z) {
            this.is_can_open = z;
        }

        public long getTask_id() {
            return this.task_id;
        }

        public void setTask_id(long j) {
            this.task_id = j;
        }

        public String getIcon_tag_get_img() {
            return this.icon_tag_get_img;
        }

        public void setIcon_tag_get_img(String str) {
            this.icon_tag_get_img = str;
        }
    }
}
