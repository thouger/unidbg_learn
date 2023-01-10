package cn.missfresh.sherlock.to;

public class BusinessBO extends CommonTO {
    private String content;
    private String contentMd5;
    private String name;
    private String type;

    public String getContent() {
        return this.content;
    }

    public String getContentMd5() {
        return this.contentMd5;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setContentMd5(String str) {
        this.contentMd5 = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
