package cn.missfresh.module.base.manager.bean;

import java.util.List;

public class MineVipInfo {
    private String coupon_title;
    private String crown_icon_path;
    private String integral_text;
    private int my_integral;
    private int my_total_integral;
    private int my_vip_level;
    private String tip_img;
    private List<VipLevelBean> vip_level;
    private String vip_title;
    private String vip_title_img;

    public String getCrown_icon_path() {
        return this.crown_icon_path;
    }

    public void setCrown_icon_path(String str) {
        this.crown_icon_path = str;
    }

    public String getVip_title_img() {
        return this.vip_title_img;
    }

    public void setVip_title_img(String str) {
        this.vip_title_img = str;
    }

    public String getIntegral_text() {
        return this.integral_text;
    }

    public void setIntegral_text(String str) {
        this.integral_text = str;
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

    public String getTip_img() {
        return this.tip_img;
    }

    public void setTip_img(String str) {
        this.tip_img = str;
    }

    public List<VipLevelBean> getVip_level() {
        return this.vip_level;
    }

    public void setVip_level(List<VipLevelBean> list) {
        this.vip_level = list;
    }

    public String getVip_title() {
        return this.vip_title;
    }

    public void setVip_title(String str) {
        this.vip_title = str;
    }

    public String getCoupon_title() {
        return this.coupon_title;
    }

    public void setCoupon_title(String str) {
        this.coupon_title = str;
    }

    public int getMy_total_integral() {
        return this.my_total_integral;
    }

    public void setMy_total_integral(int i) {
        this.my_total_integral = i;
    }

    public static class VipLevelBean {
        private int level;
        private String level_image_gray;
        private String level_image_v_white;
        private String level_image_v_yellow;
        private String level_image_yellow;
        private int level_integral;
        private String level_text;

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

        public String getLevel_text() {
            return this.level_text;
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

        public int getLevel() {
            return this.level;
        }

        public void setLevel(int i) {
            this.level = i;
        }
    }
}
