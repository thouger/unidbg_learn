package cn.missfresh.module.base.manager.bean;

public class UpgradeResContent {
    public static final int UP_TYPE_FORCE = 2;
    public static final int UP_TYPE_NOTHING = 0;
    public static final int UP_TYPE_SUGGEST = 1;
    public HotPatchInfo hotfix;
    private int popup_frequency;
    private String up_to_ver;
    private int upgrade_disabled;
    private int upgrade_type = 0;
    private String url;
    private String ver_desc;

    public static class HotPatchInfo {
        public String patch_url;
        public String patch_version;
        public boolean restart_now;
    }

    public int getPopup_frequency() {
        return this.popup_frequency;
    }

    public void setPopup_frequency(int i) {
        this.popup_frequency = i;
    }

    public int getUpgrade_disabled() {
        return this.upgrade_disabled;
    }

    public void setUpgrade_disabled(int i) {
        this.upgrade_disabled = i;
    }

    public int getUpgrade_type() {
        return this.upgrade_type;
    }

    public void setUpgrade_type(int i) {
        this.upgrade_type = i;
    }

    public String getUp_to_ver() {
        return this.up_to_ver;
    }

    public void setUp_to_ver(String str) {
        this.up_to_ver = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getVer_desc() {
        return this.ver_desc;
    }

    public void setVer_desc(String str) {
        this.ver_desc = str;
    }
}
