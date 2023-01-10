package cn.missfresh.risk.bean;

import java.util.List;

public class RiskResultCcgBean {
    private String field;
    private List<String> hitKeyWords;
    private boolean isRisk;
    private String key;
    private String keyword;
    private String path;
    private String type;

    public String getType() {
        String str = this.type;
        return str != null ? str : "";
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getPath() {
        String str = this.path;
        return str != null ? str : "";
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getKeyword() {
        String str = this.keyword;
        return str != null ? str : "";
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    public String getKey() {
        String str = this.key;
        return str != null ? str : "";
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getField() {
        String str = this.field;
        return str != null ? str : "";
    }

    public void setField(String str) {
        this.field = str;
    }

    public boolean isRisk() {
        return this.isRisk;
    }

    public void setRisk(boolean z) {
        this.isRisk = z;
    }

    public void setHitKeyWords(List<String> list) {
        this.hitKeyWords = list;
    }

    public List<String> getHitKeyWords() {
        return this.hitKeyWords;
    }
}
