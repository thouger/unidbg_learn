package cn.missfresh.module.base.manager.bean;

import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.bean.OrderTaskBean;
import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.module.base.bean.StoreInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import java.util.List;

public class MineInfo {
    public String balance_content;
    public List<BannerEntity> banner;
    public String bind_phone_text;
    public String bind_phone_tips;
    public String bubble_img_url;
    public int coin_count;
    public String coin_url;
    public int coupon_count;
    public int coupons_flag;
    public OrderTaskBean discountTask;
    public int eval_order_count;
    public int evaluate_flag;
    public List<ExpertInfo> expertInfoList;
    public int haveMessage;
    public int integral_score;
    public String integral_title;
    public String integral_url;
    public String invite_red_img;
    public String invited_img;
    public String invited_text;
    public String is_binding_phone;
    public int is_target_user;
    public boolean is_vip;
    public int last_un_eval_count;
    public String last_un_eval_time;
    public int member_flag;
    public MemberRight member_rights;
    public String member_status;
    public int money;
    public String myBalanceIcon;
    public String myBalanceTitle;
    public int myPageResourceSwitch;
    public String myPageResourceUrl;
    public List<MyService> myService;
    public MineVipInfo my_vip_info;
    public int newArrived;
    public NewUserOrderBonus new_user_order_bonus;
    public int nineRedPointSwitch;
    public List<OrderStatusList> order_status_items;
    public int paid_order_count;
    public int peding_order_count;
    public List<StoreInfo> recharge_cards;
    public String recharge_explain;
    public int red_bag_flag;
    public int red_package_count;
    public Retention retention;
    public ShareInfo share_info;
    public int shipped_order_count;
    public String sign_in_name;
    public List<BannerEntity> social_activities;
    public int task_num;
    public int task_sum;
    public UserMember user_member;
    public VipCardInfo vipCardInfo;
    public VipEquity vip_equity;
    public List<BannerEntity> vip_rights_icon;

    public static class MemberRight {
        public String rights_hope_do;
        public String rights_name;
        public String rights_notify;
    }

    public interface MineOrderStatus {
        public static final String DELAY = "delay";
        public static final String NORMAL = "mormal";
    }

    public static class NewUserOrderBonus {
        public String firstContent;
        public List<OrderListBean> orderList;
        public int orderNum;
        public String secContent;
        public String skipLinks;

        public static class OrderListBean {
            public String content;
            public String imgUrl;
            public int status;
        }
    }

    public static class OrderStatusList {
        public String jumpUrl;
        public String orderNo;
        public long order_id;
        public String order_image;
        public String order_status;
        public String status;
        public String sub_title;
        public String title;
    }

    public static class Retention {
        public String expressNo;
        public String msg;
        public long orderId;
    }

    public static class UserMember {
        public String give_experience_card;
        public String user_member_background;
        public int user_member_type;
        public String vip_card_end_time;
        public String vip_card_logo;
    }

    public static class VipCardInfo {
        public String isOpening;
        public String vipIcon;
        public String vipName;
    }

    public static class VipEquity {
        public String button_type = "1";
        public String equity_hope_do_background;
        public String equity_hope_user_do;
        public String equity_notify;
        public String task_doc;
        public String v_path;
        public String vip_doc;
        public List<VipEquityArea> vip_equity_area;
        public String vip_equity_icon_background;
        public String vip_icon;
        public String vip_skip_url;

        public static class VipEquityArea {
            public String vip_equity_area_desc;
            public String vip_equity_area_icon;
        }
    }

    public boolean getIs_vip() {
        return this.is_vip;
    }

    public String toString() {
        AppMethodBeat.i(15014, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(15014);
        return jSONString;
    }

    public class ExpertInfo {
        private String content;
        private String link;
        private String name;
        private boolean redPoint;

        public ExpertInfo() {
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public String getLink() {
            return this.link;
        }

        public void setLink(String str) {
            this.link = str;
        }

        public boolean isRedPoint() {
            return this.redPoint;
        }

        public void setRedPoint(boolean z) {
            this.redPoint = z;
        }
    }
}
