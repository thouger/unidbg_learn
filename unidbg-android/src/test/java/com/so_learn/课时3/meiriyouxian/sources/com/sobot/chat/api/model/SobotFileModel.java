package com.sobot.chat.api.model;

import java.io.Serializable;

public class SobotFileModel implements Serializable {
    private String companyId;
    private String createServiceId;
    private String createTime;
    private String dealLogId;
    private String fileId;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private String ticketId;

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String str) {
        this.companyId = str;
    }

    public String getCreateServiceId() {
        return this.createServiceId;
    }

    public void setCreateServiceId(String str) {
        this.createServiceId = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getDealLogId() {
        return this.dealLogId;
    }

    public void setDealLogId(String str) {
        this.dealLogId = str;
    }

    public String getFileId() {
        return this.fileId;
    }

    public void setFileId(String str) {
        this.fileId = str;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getFileType() {
        return this.fileType;
    }

    public void setFileType(String str) {
        this.fileType = str;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public void setFileUrl(String str) {
        this.fileUrl = str;
    }

    public String getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(String str) {
        this.ticketId = str;
    }
}
