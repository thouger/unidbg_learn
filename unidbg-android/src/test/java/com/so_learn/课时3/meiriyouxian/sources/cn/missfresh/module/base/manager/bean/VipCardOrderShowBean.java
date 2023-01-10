package cn.missfresh.module.base.manager.bean;

import java.util.List;

public class VipCardOrderShowBean {
    private int home_page;
    private List<InfoBean> info;
    private int other_page;

    public int getHome_page() {
        return this.home_page;
    }

    public void setHome_page(int i) {
        this.home_page = i;
    }

    public int getOther_page() {
        return this.other_page;
    }

    public void setOther_page(int i) {
        this.other_page = i;
    }

    public List<InfoBean> getInfo() {
        return this.info;
    }

    public void setInfo(List<InfoBean> list) {
        this.info = list;
    }

    public static class InfoBean {
        private int home_page;
        private String img;
        private int other_page;
        private String text;

        public String getImg() {
            return this.img;
        }

        public void setImg(String str) {
            this.img = str;
        }

        public String getText() {
            return this.text;
        }

        public void setText(String str) {
            this.text = str;
        }

        public int getHome_page() {
            return this.home_page;
        }

        public void setHome_page(int i) {
            this.home_page = i;
        }

        public int getOther_page() {
            return this.other_page;
        }

        public void setOther_page(int i) {
            this.other_page = i;
        }
    }
}
