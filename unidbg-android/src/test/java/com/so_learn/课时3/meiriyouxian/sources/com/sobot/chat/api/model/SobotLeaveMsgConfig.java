package com.sobot.chat.api.model;

import java.io.Serializable;
import java.util.ArrayList;

public class SobotLeaveMsgConfig implements Serializable {
    private String companyId;
    private boolean emailFlag;
    private boolean emailShowFlag;
    private boolean enclosureFlag;
    private boolean enclosureShowFlag;
    private String msgTmp;
    private String msgTxt;
    private boolean telFlag;
    private boolean telShowFlag;
    private String templateDesc;
    private String templateId;
    private String templateName;
    private boolean ticketShowFlag;
    private boolean ticketStartWay;
    private boolean ticketTitleShowFlag;
    private boolean ticketTypeFlag;
    private String ticketTypeId;
    private ArrayList<SobotTypeModel> type;

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String str) {
        this.companyId = str;
    }

    public String getMsgTmp() {
        return this.msgTmp;
    }

    public void setMsgTmp(String str) {
        this.msgTmp = str;
    }

    public String getMsgTxt() {
        return this.msgTxt;
    }

    public void setMsgTxt(String str) {
        this.msgTxt = str;
    }

    public String getTemplateDesc() {
        return this.templateDesc;
    }

    public void setTemplateDesc(String str) {
        this.templateDesc = str;
    }

    public String getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(String str) {
        this.templateId = str;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public String getTicketTypeId() {
        return this.ticketTypeId;
    }

    public void setTicketTypeId(String str) {
        this.ticketTypeId = str;
    }

    public ArrayList<SobotTypeModel> getType() {
        return this.type;
    }

    public void setType(ArrayList<SobotTypeModel> arrayList) {
        this.type = arrayList;
    }

    public boolean isEmailFlag() {
        return this.emailFlag;
    }

    public void setEmailFlag(boolean z) {
        this.emailFlag = z;
    }

    public boolean isEmailShowFlag() {
        return this.emailShowFlag;
    }

    public void setEmailShowFlag(boolean z) {
        this.emailShowFlag = z;
    }

    public boolean isEnclosureFlag() {
        return this.enclosureFlag;
    }

    public void setEnclosureFlag(boolean z) {
        this.enclosureFlag = z;
    }

    public boolean isEnclosureShowFlag() {
        return this.enclosureShowFlag;
    }

    public void setEnclosureShowFlag(boolean z) {
        this.enclosureShowFlag = z;
    }

    public boolean isTelFlag() {
        return this.telFlag;
    }

    public void setTelFlag(boolean z) {
        this.telFlag = z;
    }

    public boolean isTelShowFlag() {
        return this.telShowFlag;
    }

    public void setTelShowFlag(boolean z) {
        this.telShowFlag = z;
    }

    public boolean isTicketShowFlag() {
        return this.ticketShowFlag;
    }

    public void setTicketShowFlag(boolean z) {
        this.ticketShowFlag = z;
    }

    public boolean isTicketStartWay() {
        return this.ticketStartWay;
    }

    public void setTicketStartWay(boolean z) {
        this.ticketStartWay = z;
    }

    public boolean isTicketTypeFlag() {
        return this.ticketTypeFlag;
    }

    public void setTicketTypeFlag(boolean z) {
        this.ticketTypeFlag = z;
    }

    public boolean isTicketTitleShowFlag() {
        return this.ticketTitleShowFlag;
    }

    public void setTicketTitleShowFlag(boolean z) {
        this.ticketTitleShowFlag = z;
    }
}
