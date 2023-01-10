package cn.missfresh.module.base.support.modelsupport.event.bean;

import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public class SocialBeans {

    public static class InviteStepInfo {
        public String boon_content;
        public String boon_img;
        public String reward_img;
    }

    public static class InvitedUserInfo {
        public String income_text;
        public int income_text_color;
        public String invite_text;
        public String nick_name;
        public int s;
        public String status_text;
        public String user_avatar;
    }

    public static class MyInviteProfit {
        public String module_title;
        public String profit_package;
        public String profit_vip;
    }

    public static class MyInviteRecord {
        public List<InvitedUserInfo> invited_users;
        public String module_title;
        public int num_color;
        public String rank_sub_title;
        public int text_color;
    }

    public static class InviteFriendsInfo {
        private String banner_url;
        private String incentive_rules;
        private int invited_amount;
        private String invited_desc;
        private int invited_friends_count;
        private ShareInfo share_invite_content;
        private int show_invite_act_rule;

        public int getInvited_amount() {
            return this.invited_amount;
        }

        public void setInvited_amount(int i) {
            this.invited_amount = i;
        }

        public int getInvited_friends_count() {
            return this.invited_friends_count;
        }

        public void setInvited_friends_count(int i) {
            this.invited_friends_count = i;
        }

        public String getBanner_url() {
            return this.banner_url;
        }

        public void setBanner_url(String str) {
            this.banner_url = str;
        }

        public String getIncentive_rules() {
            return this.incentive_rules;
        }

        public void setIncentive_rules(String str) {
            this.incentive_rules = str;
        }

        public ShareInfo getShare_invite_content() {
            return this.share_invite_content;
        }

        public void setShare_invite_content(ShareInfo shareInfo) {
            this.share_invite_content = shareInfo;
        }

        public String getInvited_desc() {
            return this.invited_desc;
        }

        public void setInvited_desc(String str) {
            this.invited_desc = str;
        }

        public int getShow_invite_act_rule() {
            return this.show_invite_act_rule;
        }

        public void setShow_invite_act_rule(int i) {
            this.show_invite_act_rule = i;
        }
    }

    public static class getShareImgUrlRes {
        private shareImgUrlResultObj result;

        public shareImgUrlResultObj getResult() {
            return this.result;
        }

        public void setResult(shareImgUrlResultObj shareimgurlresultobj) {
            this.result = shareimgurlresultobj;
        }
    }

    public static class shareImgUrlResultObj {
        private String shareImgUrl;

        public String getShareImgUrl() {
            return this.shareImgUrl;
        }

        public void setShareImgUrl(String str) {
            this.shareImgUrl = str;
        }
    }

    public static class InviteUserInfo {
        private String award_img;
        private boolean ifSpecialDevide;
        private String invite_text;
        private String nick_name;
        private List<String> presents;
        private String sort;
        private String sort_img;
        private String status_text;
        private String user_avatar;

        public String getSort() {
            return this.sort;
        }

        public void setSort(String str) {
            this.sort = str;
        }

        public String getSort_img() {
            return this.sort_img;
        }

        public void setSort_img(String str) {
            this.sort_img = str;
        }

        public String getAward_img() {
            return this.award_img;
        }

        public void setAward_img(String str) {
            this.award_img = str;
        }

        public String getUser_avatar() {
            return this.user_avatar;
        }

        public void setUser_avatar(String str) {
            this.user_avatar = str;
        }

        public String getNick_name() {
            return this.nick_name;
        }

        public void setNick_name(String str) {
            this.nick_name = str;
        }

        public String getInvite_text() {
            return this.invite_text;
        }

        public void setInvite_text(String str) {
            this.invite_text = str;
        }

        public String getStatus_text() {
            return this.status_text;
        }

        public void setStatus_text(String str) {
            this.status_text = str;
        }

        public List<String> getPresents() {
            return this.presents;
        }

        public void setPresents(List<String> list) {
            this.presents = list;
        }

        public boolean isIfSpecialDevide() {
            return this.ifSpecialDevide;
        }

        public void setIfSpecialDevide(boolean z) {
            this.ifSpecialDevide = z;
        }
    }

    public static class InviteLevelCard {
        private String invate_level_image;
        private int invate_level_num;

        public String getInvate_level_image() {
            return this.invate_level_image;
        }

        public void setInvate_level_image(String str) {
            this.invate_level_image = str;
        }

        public int getInvate_level_num() {
            return this.invate_level_num;
        }

        public void setInvate_level_num(int i) {
            this.invate_level_num = i;
        }
    }

    public static class InviteInfo {
        private List<InviteStepInfo> invate_boon = new ArrayList();
        private List<InviteLevelCard> invate_config = new ArrayList();
        private String invate_history;
        private int invate_level;
        private int invate_num;
        private String invate_title;
        public String module_title;
        public String module_title_down;

        public InviteInfo() {
            AppMethodBeat.i(22185, false);
            AppMethodBeat.o(22185);
        }

        public int getInvate_num() {
            return this.invate_num;
        }

        public void setInvate_num(int i) {
            this.invate_num = i;
        }

        public int getInvate_level() {
            return this.invate_level;
        }

        public void setInvate_level(int i) {
            this.invate_level = i;
        }

        public String getInvate_title() {
            return this.invate_title;
        }

        public void setInvate_title(String str) {
            this.invate_title = str;
        }

        public String getInvate_history() {
            return this.invate_history;
        }

        public void setInvate_history(String str) {
            this.invate_history = str;
        }

        public List<InviteLevelCard> getInvate_config() {
            return this.invate_config;
        }

        public void setInvate_config(List<InviteLevelCard> list) {
            this.invate_config = list;
        }

        public List<InviteStepInfo> getInvate_boon() {
            return this.invate_boon;
        }

        public void setInvate_boon(List<InviteStepInfo> list) {
            this.invate_boon = list;
        }
    }

    public static class PopInviteInfo {
        private List<String> head_images = new ArrayList();
        private String invate_message;
        private int new_invate_cnt;
        private String success_message;

        public PopInviteInfo() {
            AppMethodBeat.i(22209, false);
            AppMethodBeat.o(22209);
        }

        public int getNew_invate_cnt() {
            return this.new_invate_cnt;
        }

        public void setNew_invate_cnt(int i) {
            this.new_invate_cnt = i;
        }

        public String getSuccess_message() {
            return this.success_message;
        }

        public void setSuccess_message(String str) {
            this.success_message = str;
        }

        public String getInvate_message() {
            return this.invate_message;
        }

        public void setInvate_message(String str) {
            this.invate_message = str;
        }

        public List<String> getHead_images() {
            return this.head_images;
        }

        public void setHead_images(List<String> list) {
            this.head_images = list;
        }
    }

    public static class InviteTabInfo {
        private String name;
        private NoRankTip no_rank_tip;
        private String notice_tip;
        private List<InviteUserInfo> rankList = new ArrayList();
        private int type;

        public InviteTabInfo() {
            AppMethodBeat.i(22191, false);
            AppMethodBeat.o(22191);
        }

        public NoRankTip getNo_rank_tip() {
            return this.no_rank_tip;
        }

        public void setNo_rank_tip(NoRankTip noRankTip) {
            this.no_rank_tip = noRankTip;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getNotice_tip() {
            return this.notice_tip;
        }

        public void setNotice_tip(String str) {
            this.notice_tip = str;
        }

        public List<InviteUserInfo> getRankList() {
            return this.rankList;
        }

        public void setRankList(List<InviteUserInfo> list) {
            this.rankList = list;
        }
    }

    public static class NoRankTip {
        private String tip_buttom;
        private String tip_content;
        private String tip_img;

        public String getTip_content() {
            return this.tip_content;
        }

        public void setTip_content(String str) {
            this.tip_content = str;
        }

        public String getTip_buttom() {
            return this.tip_buttom;
        }

        public void setTip_buttom(String str) {
            this.tip_buttom = str;
        }

        public String getTip_img() {
            return this.tip_img;
        }

        public void setTip_img(String str) {
            this.tip_img = str;
        }
    }

    public static class GetInviteMainPageRes {
        private List<BannerEntity> banner = new ArrayList();
        private int image_height;
        private String image_url;
        private int image_width;
        private List<InviteTabInfo> invate_charts = new ArrayList();
        public String invate_gift_pop;
        private InviteInfo invate_info;
        private PopInviteInfo invate_message;
        private String invite_button_img;
        private String invite_float_img;
        private String invite_rule_url;
        private String invite_tip_img;
        public MyInviteRecord my_invite_record;
        public int order_sign_red_flag;
        private String remind_user_stay_text;
        public String rule_pic_url;
        private ShareInfo share_invite_content;
        private int show_new;
        public int show_new_v2;
        public String sms_text;

        public GetInviteMainPageRes() {
            AppMethodBeat.i(22175, false);
            AppMethodBeat.o(22175);
        }

        public String getImage_url() {
            return this.image_url;
        }

        public void setImage_url(String str) {
            this.image_url = str;
        }

        public int getImage_height() {
            return this.image_height;
        }

        public void setImage_height(int i) {
            this.image_height = i;
        }

        public int getImage_width() {
            return this.image_width;
        }

        public void setImage_width(int i) {
            this.image_width = i;
        }

        public String getInvite_rule_url() {
            return this.invite_rule_url;
        }

        public void setInvite_rule_url(String str) {
            this.invite_rule_url = str;
        }

        public List<BannerEntity> getBanner() {
            return this.banner;
        }

        public void setBanner(List<BannerEntity> list) {
            this.banner = list;
        }

        public InviteInfo getInvate_info() {
            return this.invate_info;
        }

        public void setInvate_info(InviteInfo inviteInfo) {
            this.invate_info = inviteInfo;
        }

        public PopInviteInfo getInvate_message() {
            return this.invate_message;
        }

        public void setInvate_message(PopInviteInfo popInviteInfo) {
            this.invate_message = popInviteInfo;
        }

        public List<InviteTabInfo> getInvate_charts() {
            return this.invate_charts;
        }

        public void setInvate_charts(List<InviteTabInfo> list) {
            this.invate_charts = list;
        }

        public ShareInfo getShare_invite_content() {
            return this.share_invite_content;
        }

        public void setShare_invite_content(ShareInfo shareInfo) {
            this.share_invite_content = shareInfo;
        }

        public int getShow_new() {
            return this.show_new;
        }

        public void setShow_new(int i) {
            this.show_new = i;
        }

        public String getInvite_float_img() {
            return this.invite_float_img;
        }

        public void setInvite_float_img(String str) {
            this.invite_float_img = str;
        }

        public String getInvite_tip_img() {
            return this.invite_tip_img;
        }

        public void setInvite_tip_img(String str) {
            this.invite_tip_img = str;
        }

        public String getInvite_button_img() {
            return this.invite_button_img;
        }

        public void setInvite_button_img(String str) {
            this.invite_button_img = str;
        }

        public String getRemind_user_stay_text() {
            return this.remind_user_stay_text;
        }

        public void setRemind_user_stay_text(String str) {
            this.remind_user_stay_text = str;
        }
    }
}
