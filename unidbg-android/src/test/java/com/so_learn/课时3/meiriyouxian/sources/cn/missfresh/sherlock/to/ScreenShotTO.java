package cn.missfresh.sherlock.to;

import java.io.Serializable;
import java.util.List;

public class ScreenShotTO extends CommonTO implements Serializable {
    private static final long serialVersionUID = -4421010992622583110L;
    private String file;
    private String path;
    private List<NetworkParamTO> request;

    public String getFile() {
        return this.file;
    }

    public String getPath() {
        return this.path;
    }

    public List<NetworkParamTO> getRequest() {
        return this.request;
    }

    public void setFile(String str) {
        this.file = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setRequest(List<NetworkParamTO> list) {
        this.request = list;
    }
}
