package com.sobot.chat.api.model;

import java.io.Serializable;

public class ZhiChiUploadAppFileModelResult implements Serializable {
    private String fileLocalPath;
    private String fileUrl;
    private int viewState = 0;

    public int getViewState() {
        return this.viewState;
    }

    public void setViewState(int i) {
        this.viewState = i;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public void setFileUrl(String str) {
        this.fileUrl = str;
    }

    public String getFileLocalPath() {
        return this.fileLocalPath;
    }

    public void setFileLocalPath(String str) {
        this.fileLocalPath = str;
    }
}
