package cn.missfresh.module.base.bean;

import java.util.List;

public class TitleInfo implements BaseTitle {
    private int act_end_time;
    private List<BannerEntity> banner;
    private String code;
    private int font_color;
    private String group_img;
    private String left_image;
    private String name;
    private String right_image;
    private String seckill_bgimg_url;
    private String second_title;
    private String type;

    public String getTags() {
        return null;
    }

    public String getGroup_img() {
        return this.group_img;
    }

    public void setGroup_img(String str) {
        this.group_img = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getLeft_image() {
        return this.left_image;
    }

    public void setLeft_image(String str) {
        this.left_image = str;
    }

    public String getRight_image() {
        return this.right_image;
    }

    public void setRight_image(String str) {
        this.right_image = str;
    }

    public int getAct_end_time() {
        return this.act_end_time;
    }

    public void setAct_end_time(int i) {
        this.act_end_time = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getFont_color() {
        return this.font_color;
    }

    public void setFont_color(int i) {
        this.font_color = i;
    }

    public String getSecond_title() {
        return this.second_title;
    }

    public void setSecond_title(String str) {
        this.second_title = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public List<BannerEntity> getBanner() {
        return this.banner;
    }

    public void setBanner(List<BannerEntity> list) {
        this.banner = list;
    }

    public String getSeckill_bgimg_url() {
        return this.seckill_bgimg_url;
    }

    public void setSeckill_bgimg_url(String str) {
        this.seckill_bgimg_url = str;
    }
}
