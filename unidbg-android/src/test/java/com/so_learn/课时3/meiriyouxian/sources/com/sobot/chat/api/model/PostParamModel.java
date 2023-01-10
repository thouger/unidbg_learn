package com.sobot.chat.api.model;

import android.text.format.DateFormat;

public class PostParamModel {
    private String companyId;
    private String customerEmail;
    private String customerPhone;
    private String extendFields;
    private String fileStr;
    private String groupId;
    private String paramsExtends;
    private String partnerId;
    private String templateId;
    private String ticketContent;
    private String ticketTitle;
    private String ticketTypeId;
    private String uid;

    public String getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(String str) {
        this.templateId = str;
    }

    public String toString() {
        return "PostParamModel{uid='" + this.uid + DateFormat.QUOTE + "partnerId='" + this.partnerId + DateFormat.QUOTE + ", ticketContent='" + this.ticketContent + DateFormat.QUOTE + ", customerEmail='" + this.customerEmail + DateFormat.QUOTE + ", customerPhone='" + this.customerPhone + DateFormat.QUOTE + ", companyId='" + this.companyId + DateFormat.QUOTE + ", fileStr='" + this.fileStr + DateFormat.QUOTE + ", ticketTypeId='" + this.ticketTypeId + DateFormat.QUOTE + ", groupId='" + this.groupId + DateFormat.QUOTE + ", extendFields='" + this.extendFields + DateFormat.QUOTE + ", paramsExtends='" + this.extendFields + DateFormat.QUOTE + '}';
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getPartnerId() {
        return this.partnerId;
    }

    public void setPartnerId(String str) {
        this.partnerId = str;
    }

    public String getExtendFields() {
        return this.extendFields;
    }

    public void setExtendFields(String str) {
        this.extendFields = str;
    }

    public String getTicketContent() {
        return this.ticketContent;
    }

    public void setTicketContent(String str) {
        this.ticketContent = str;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }

    public void setCustomerEmail(String str) {
        this.customerEmail = str;
    }

    public String getCustomerPhone() {
        return this.customerPhone;
    }

    public void setCustomerPhone(String str) {
        this.customerPhone = str;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String str) {
        this.companyId = str;
    }

    public String getFileStr() {
        return this.fileStr;
    }

    public void setFileStr(String str) {
        this.fileStr = str;
    }

    public String getTicketTypeId() {
        return this.ticketTypeId;
    }

    public void setTicketTypeId(String str) {
        this.ticketTypeId = str;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public String getTicketTitle() {
        return this.ticketTitle;
    }

    public void setTicketTitle(String str) {
        this.ticketTitle = str;
    }

    public String getParamsExtends() {
        return this.paramsExtends;
    }

    public void setParamsExtends(String str) {
        this.paramsExtends = str;
    }
}
