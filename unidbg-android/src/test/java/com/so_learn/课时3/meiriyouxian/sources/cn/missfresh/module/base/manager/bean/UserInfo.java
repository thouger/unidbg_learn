package cn.missfresh.module.base.manager.bean;

public class UserInfo {
    private boolean active_vip;
    private String bind_phone_text;
    private String bind_phone_tips;
    private String end_time;
    private String first_order_time;
    private boolean has_unread;
    private boolean identity_verified;
    private int is_binding_phone;
    private int is_binding_wx;
    private String is_open;
    private int is_union_account;
    public int is_vip;
    private String log_out_url;
    private int member_level;
    private String nick_name;
    private boolean order_notice;
    private String phone_number;
    private String platform;
    private String portrait;
    private boolean promotion_notice;
    private String register_time;
    private String start_time;
    private String state_autopay;
    public String tab_img;
    public String tab_url;
    private String third_nick_name;
    public String user_icon;
    private int user_id;
    public int user_member_type;
    public String wechatPayIcon;
    public String wechatPayText;

    public interface UserType {
        public static final int UNLOGIN = -1;
        public static final int USER_TYPE_EXPERIENCE_CARD = 2;
        public static final int USER_TYPE_NORMAL = 0;
        public static final int USER_TYPE_OLD = 1;
        public static final int USER_TYPE_VIP = 3;
    }

    public String getIs_open() {
        return this.is_open;
    }

    public void setIs_open(String str) {
        this.is_open = str;
    }

    public String getState_autopay() {
        return this.state_autopay;
    }

    public void setState_autopay(String str) {
        this.state_autopay = str;
    }

    public String getBind_phone_tips() {
        return this.bind_phone_tips;
    }

    public void setBind_phone_tips(String str) {
        this.bind_phone_tips = str;
    }

    public String getBind_phone_text() {
        return this.bind_phone_text;
    }

    public void setBind_phone_text(String str) {
        this.bind_phone_text = str;
    }

    public int getMember_level() {
        return this.member_level;
    }

    public void setMember_level(int i) {
        this.member_level = i;
    }

    public String getTab_img() {
        return this.tab_img;
    }

    public void setTab_img(String str) {
        this.tab_img = str;
    }

    public String getTab_url() {
        return this.tab_url;
    }

    public void setTab_url(String str) {
        this.tab_url = str;
    }

    public int getIs_union_account() {
        return this.is_union_account;
    }

    public void setIs_union_account(int i) {
        this.is_union_account = i;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public void setPhone_number(String str) {
        this.phone_number = str;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public void setUser_id(int i) {
        this.user_id = i;
    }

    public boolean isPromotion_notice() {
        return this.promotion_notice;
    }

    public void setPromotion_notice(boolean z) {
        this.promotion_notice = z;
    }

    public boolean isOrder_notice() {
        return this.order_notice;
    }

    public void setOrder_notice(boolean z) {
        this.order_notice = z;
    }

    public String getNick_name() {
        return this.nick_name;
    }

    public void setNick_name(String str) {
        this.nick_name = str;
    }

    public String getStart_time() {
        return this.start_time;
    }

    public void setStart_time(String str) {
        this.start_time = str;
    }

    public boolean isHas_unread() {
        return this.has_unread;
    }

    public void setHas_unread(boolean z) {
        this.has_unread = z;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public String getEnd_time() {
        return this.end_time;
    }

    public void setEnd_time(String str) {
        this.end_time = str;
    }

    public String getPortrait() {
        return this.portrait;
    }

    public void setPortrait(String str) {
        this.portrait = str;
    }

    public boolean isActive_vip() {
        return this.active_vip;
    }

    public void setActive_vip(boolean z) {
        this.active_vip = z;
    }

    public boolean is_vip() {
        return this.is_vip == 1;
    }

    public void setIs_vip(boolean z) {
        this.is_vip = z ? 1 : 0;
    }

    public int getIs_binding_wx() {
        return this.is_binding_wx;
    }

    public void setIs_binding_wx(int i) {
        this.is_binding_wx = i;
    }

    public int getIs_binding_phone() {
        return this.is_binding_phone;
    }

    public void setIs_binding_phone(int i) {
        this.is_binding_phone = i;
    }

    public String getRegister_time() {
        return this.register_time;
    }

    public void setRegister_time(String str) {
        this.register_time = str;
    }

    public String getFirst_order_time() {
        return this.first_order_time;
    }

    public void setFirst_order_time(String str) {
        this.first_order_time = str;
    }

    public String getLog_out_url() {
        return this.log_out_url;
    }

    public void setLog_out_url(String str) {
        this.log_out_url = str;
    }

    public String getThird_nick_name() {
        return this.third_nick_name;
    }

    public void setThird_nick_name(String str) {
        this.third_nick_name = str;
    }

    public boolean isIdentity_verified() {
        return this.identity_verified;
    }

    public void setIdentity_verified(boolean z) {
        this.identity_verified = z;
    }
}
