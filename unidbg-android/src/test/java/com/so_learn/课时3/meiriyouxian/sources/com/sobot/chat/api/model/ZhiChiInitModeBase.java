package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;

public class ZhiChiInitModeBase implements Serializable {
    private static final long serialVersionUID = 1;
    private int accountStatus;
    private String adminHelloWord;
    private boolean adminHelloWordCountRule;
    private boolean adminHelloWordFlag;
    private boolean adminNoneLineFlag;
    private String adminNonelineTitle;
    private String adminTipTime;
    private String adminTipWord;
    private boolean announceClickFlag = false;
    private String announceClickUrl = "";
    private String announceMsg = "";
    private boolean announceMsgFlag = false;
    private boolean announceTopFlag;
    private String appId;
    private String cid;
    private String companyId;
    private String companyLogo;
    private String companyName;
    private String companyStatus;
    private String currentRobotFlag;
    private boolean customOutTimeFlag;
    private String customerId;
    private boolean emailFlag;
    private boolean emailShowFlag;
    private boolean enclosureFlag;
    private boolean enclosureShowFlag;
    private String groupflag;
    private int guideFlag;
    private int inputTime;
    private int invalidSessionFlag;
    private String isblack;
    private boolean lableLinkFlag;
    private String manualCommentTitle;
    private String manualType;
    private int msgFlag;
    private String msgLeaveContentTxt;
    private String msgLeaveTxt;
    private String msgTmp;
    private boolean msgToTicketFlag;
    private String msgTxt;
    private String offlineMsgAdminId;
    private int offlineMsgConnectFlag;
    private String partnerid;
    private boolean realuateFlag;
    private String robotCommentTitle;
    private String robotHelloWord;
    private boolean robotHelloWordFlag;
    private String robotLogo;
    private String robotName;
    private boolean robotSwitchFlag;
    private String robotUnknownWord;
    private String robotid;
    private boolean serviceEndPushFlag;
    private String serviceEndPushMsg;
    private boolean serviceOutCountRule;
    private boolean serviceOutTimeFlag;
    private boolean smartRouteInfoFlag;
    private boolean telFlag;
    private boolean telShowFlag;
    private boolean ticketShowFlag;
    private boolean ticketStartWay;
    private String type;
    private String uid;
    private String userOutTime;
    private String userOutWord;
    private String userTipTime;
    private String userTipWord;
    private int ustatus;

    @Deprecated
    public String getCurrentRobotFlag() {
        return this.currentRobotFlag;
    }

    @Deprecated
    public void setCurrentRobotFlag(String str) {
        this.currentRobotFlag = str;
        this.robotid = str;
    }

    public void setRobotid(String str) {
        this.robotid = str;
        this.currentRobotFlag = str;
    }

    public String getRobotid() {
        return this.robotid;
    }

    @Deprecated
    public String getUid() {
        return this.uid;
    }

    @Deprecated
    public void setUid(String str) {
        this.uid = str;
        this.partnerid = str;
    }

    public void setPartnerid(String str) {
        this.partnerid = str;
        this.uid = str;
    }

    public String getPartnerid() {
        return this.partnerid;
    }

    public boolean isCustomOutTimeFlag() {
        return this.customOutTimeFlag;
    }

    public void setCustomOutTimeFlag(boolean z) {
        this.customOutTimeFlag = z;
    }

    public boolean isServiceOutTimeFlag() {
        return this.serviceOutTimeFlag;
    }

    public void setServiceOutTimeFlag(boolean z) {
        this.serviceOutTimeFlag = z;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public boolean isRealuateFlag() {
        return this.realuateFlag;
    }

    public void setRealuateFlag(boolean z) {
        this.realuateFlag = z;
    }

    public int getGuideFlag() {
        return this.guideFlag;
    }

    public void setGuideFlag(int i) {
        this.guideFlag = i;
    }

    public int getMsgFlag() {
        return this.msgFlag;
    }

    public void setMsgFlag(int i) {
        this.msgFlag = i;
    }

    public int getInputTime() {
        return this.inputTime;
    }

    public void setInputTime(int i) {
        if (i <= 0) {
            this.inputTime = 1;
        } else {
            this.inputTime = i;
        }
    }

    public int getUstatus() {
        return this.ustatus;
    }

    public void setUstatus(int i) {
        this.ustatus = i;
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

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String str) {
        this.companyId = str;
    }

    public String getManualCommentTitle() {
        return this.manualCommentTitle;
    }

    public void setManualCommentTitle(String str) {
        this.manualCommentTitle = str;
    }

    public String getAdminNonelineTitle() {
        return this.adminNonelineTitle;
    }

    public void setAdminNonelineTitle(String str) {
        this.adminNonelineTitle = str;
    }

    public String getRobotLogo() {
        return this.robotLogo;
    }

    public void setRobotLogo(String str) {
        this.robotLogo = str;
    }

    public String getUserOutWord() {
        return this.userOutWord;
    }

    public void setUserOutWord(String str) {
        this.userOutWord = str;
    }

    public String getAdminHelloWord() {
        return this.adminHelloWord;
    }

    public void setAdminHelloWord(String str) {
        this.adminHelloWord = str;
    }

    public String getUserTipTime() {
        return this.userTipTime;
    }

    public void setUserTipTime(String str) {
        this.userTipTime = str;
    }

    public String getUserTipWord() {
        return this.userTipWord;
    }

    public void setUserTipWord(String str) {
        this.userTipWord = str;
    }

    public String getRobotHelloWord() {
        return this.robotHelloWord;
    }

    public void setRobotHelloWord(String str) {
        this.robotHelloWord = str;
    }

    public String getAdminTipWord() {
        return this.adminTipWord;
    }

    public void setAdminTipWord(String str) {
        this.adminTipWord = str;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String str) {
        this.companyName = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public String getCompanyStatus() {
        return this.companyStatus;
    }

    public void setCompanyStatus(String str) {
        this.companyStatus = str;
    }

    public String getRobotName() {
        return this.robotName;
    }

    public void setRobotName(String str) {
        this.robotName = str;
    }

    public String getIsblack() {
        return this.isblack;
    }

    public void setIsblack(String str) {
        this.isblack = str;
    }

    public String getUserOutTime() {
        return this.userOutTime;
    }

    public void setUserOutTime(String str) {
        this.userOutTime = str;
    }

    public String getRobotUnknownWord() {
        return this.robotUnknownWord;
    }

    public void setRobotUnknownWord(String str) {
        this.robotUnknownWord = str;
    }

    public String getRobotCommentTitle() {
        return this.robotCommentTitle;
    }

    public void setRobotCommentTitle(String str) {
        this.robotCommentTitle = str;
    }

    public String getAdminTipTime() {
        return this.adminTipTime;
    }

    public void setAdminTipTime(String str) {
        this.adminTipTime = str;
    }

    public String getGroupflag() {
        return this.groupflag;
    }

    public void setGroupflag(String str) {
        this.groupflag = str;
    }

    public String getManualType() {
        return this.manualType;
    }

    public void setManualType(String str) {
        this.manualType = str;
    }

    public boolean isAnnounceTopFlag() {
        return this.announceTopFlag;
    }

    public void setAnnounceTopFlag(boolean z) {
        this.announceTopFlag = z;
    }

    public boolean getAnnounceClickFlag() {
        return this.announceClickFlag;
    }

    public void setAnnounceClickFlag(boolean z) {
        this.announceClickFlag = z;
    }

    public String getAnnounceClickUrl() {
        return this.announceClickUrl;
    }

    public void setAnnounceClickUrl(String str) {
        this.announceClickUrl = str;
    }

    public boolean getAnnounceMsgFlag() {
        return this.announceMsgFlag;
    }

    public void setAnnounceMsgFlag(boolean z) {
        this.announceMsgFlag = z;
    }

    public String getAnnounceMsg() {
        return this.announceMsg;
    }

    public void setAnnounceMsg(String str) {
        this.announceMsg = str;
    }

    public String getCompanyLogo() {
        return this.companyLogo;
    }

    public void setCompanyLogo(String str) {
        this.companyLogo = str;
    }

    public boolean isRobotSwitchFlag() {
        return this.robotSwitchFlag;
    }

    public void setRobotSwitchFlag(boolean z) {
        this.robotSwitchFlag = z;
    }

    public boolean isLableLinkFlag() {
        return this.lableLinkFlag;
    }

    public void setLableLinkFlag(boolean z) {
        this.lableLinkFlag = z;
    }

    public int getAccountStatus() {
        return this.accountStatus;
    }

    public void setAccountStatus(int i) {
        this.accountStatus = i;
    }

    public boolean isSmartRouteInfoFlag() {
        return this.smartRouteInfoFlag;
    }

    public void setSmartRouteInfoFlag(boolean z) {
        this.smartRouteInfoFlag = z;
    }

    public boolean isRobotHelloWordFlag() {
        return this.robotHelloWordFlag;
    }

    public void setRobotHelloWordFlag(boolean z) {
        this.robotHelloWordFlag = z;
    }

    public boolean isAdminHelloWordFlag() {
        return this.adminHelloWordFlag;
    }

    public void setAdminHelloWordFlag(boolean z) {
        this.adminHelloWordFlag = z;
    }

    public boolean isServiceEndPushFlag() {
        return this.serviceEndPushFlag;
    }

    public void setServiceEndPushFlag(boolean z) {
        this.serviceEndPushFlag = z;
    }

    public String getServiceEndPushMsg() {
        return this.serviceEndPushMsg;
    }

    public void setServiceEndPushMsg(String str) {
        this.serviceEndPushMsg = str;
    }

    public boolean isAdminNoneLineFlag() {
        return this.adminNoneLineFlag;
    }

    public void setAdminNoneLineFlag(boolean z) {
        this.adminNoneLineFlag = z;
    }

    public boolean isServiceOutCountRule() {
        return this.serviceOutCountRule;
    }

    public void setServiceOutCountRule(boolean z) {
        this.serviceOutCountRule = z;
    }

    public boolean isAdminHelloWordCountRule() {
        return this.adminHelloWordCountRule;
    }

    public void setAdminHelloWordCountRule(boolean z) {
        this.adminHelloWordCountRule = z;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public boolean isMsgToTicketFlag() {
        return this.msgToTicketFlag;
    }

    public void setMsgToTicketFlag(boolean z) {
        this.msgToTicketFlag = z;
    }

    public String getMsgLeaveTxt() {
        return this.msgLeaveTxt;
    }

    public void setMsgLeaveTxt(String str) {
        this.msgLeaveTxt = str;
    }

    public String getMsgLeaveContentTxt() {
        return this.msgLeaveContentTxt;
    }

    public void setMsgLeaveContentTxt(String str) {
        this.msgLeaveContentTxt = str;
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

    public boolean isAnnounceClickFlag() {
        return this.announceClickFlag;
    }

    public boolean isAnnounceMsgFlag() {
        return this.announceMsgFlag;
    }

    public int getOfflineMsgConnectFlag() {
        return this.offlineMsgConnectFlag;
    }

    public void setOfflineMsgConnectFlag(int i) {
        this.offlineMsgConnectFlag = i;
    }

    public String getOfflineMsgAdminId() {
        return this.offlineMsgAdminId;
    }

    public void setOfflineMsgAdminId(String str) {
        this.offlineMsgAdminId = str;
    }

    public int getInvalidSessionFlag() {
        return this.invalidSessionFlag;
    }

    public void setInvalidSessionFlag(int i) {
        this.invalidSessionFlag = i;
    }

    @Override // java.lang.Object
    public String toString() {
        return "ZhiChiInitModeBase{robotid='" + this.robotid + DateFormat.QUOTE + ", partnerid='" + this.partnerid + DateFormat.QUOTE + ", currentRobotFlag='" + this.currentRobotFlag + DateFormat.QUOTE + ", uid='" + this.uid + DateFormat.QUOTE + ", offlineMsgConnectFlag=" + this.offlineMsgConnectFlag + ", offlineMsgAdminId='" + this.offlineMsgAdminId + DateFormat.QUOTE + ", invalidSessionFlag=" + this.invalidSessionFlag + ", appId='" + this.appId + DateFormat.QUOTE + ", robotLogo='" + this.robotLogo + DateFormat.QUOTE + ", manualCommentTitle='" + this.manualCommentTitle + DateFormat.QUOTE + ", msgTmp='" + this.msgTmp + DateFormat.QUOTE + ", type='" + this.type + DateFormat.QUOTE + ", isblack='" + this.isblack + DateFormat.QUOTE + ", robotUnknownWord='" + this.robotUnknownWord + DateFormat.QUOTE + ", groupflag='" + this.groupflag + DateFormat.QUOTE + ", guideFlag=" + this.guideFlag + ", msgTxt='" + this.msgTxt + DateFormat.QUOTE + ", msgFlag=" + this.msgFlag + ", msgToTicketFlag=" + this.msgToTicketFlag + ", msgLeaveTxt='" + this.msgLeaveTxt + DateFormat.QUOTE + ", msgLeaveContentTxt='" + this.msgLeaveContentTxt + DateFormat.QUOTE + ", adminTipTime='" + this.adminTipTime + DateFormat.QUOTE + ", inputTime=" + this.inputTime + ", ustatus=" + this.ustatus + ", companyLogo='" + this.companyLogo + DateFormat.QUOTE + ", companyName='" + this.companyName + DateFormat.QUOTE + ", cid='" + this.cid + DateFormat.QUOTE + ", robotName='" + this.robotName + DateFormat.QUOTE + ", companyStatus='" + this.companyStatus + DateFormat.QUOTE + ", userOutTime='" + this.userOutTime + DateFormat.QUOTE + ", companyId='" + this.companyId + DateFormat.QUOTE + ", robotCommentTitle='" + this.robotCommentTitle + DateFormat.QUOTE + ", manualType='" + this.manualType + DateFormat.QUOTE + ", realuateFlag=" + this.realuateFlag + ", userTipTime='" + this.userTipTime + DateFormat.QUOTE + ", customerId='" + this.customerId + DateFormat.QUOTE + ", robotSwitchFlag=" + this.robotSwitchFlag + ", lableLinkFlag=" + this.lableLinkFlag + ", accountStatus=" + this.accountStatus + ", smartRouteInfoFlag=" + this.smartRouteInfoFlag + ", serviceOutCountRule=" + this.serviceOutCountRule + ", adminHelloWordCountRule=" + this.adminHelloWordCountRule + ", adminHelloWord='" + this.adminHelloWord + DateFormat.QUOTE + ", robotHelloWord='" + this.robotHelloWord + DateFormat.QUOTE + ", userTipWord='" + this.userTipWord + DateFormat.QUOTE + ", adminNonelineTitle='" + this.adminNonelineTitle + DateFormat.QUOTE + ", adminTipWord='" + this.adminTipWord + DateFormat.QUOTE + ", userOutWord='" + this.userOutWord + DateFormat.QUOTE + ", robotHelloWordFlag=" + this.robotHelloWordFlag + ", adminHelloWordFlag=" + this.adminHelloWordFlag + ", adminNoneLineFlag=" + this.adminNoneLineFlag + ", serviceEndPushFlag=" + this.serviceEndPushFlag + ", serviceEndPushMsg='" + this.serviceEndPushMsg + DateFormat.QUOTE + ", emailFlag=" + this.emailFlag + ", emailShowFlag=" + this.emailShowFlag + ", enclosureFlag=" + this.enclosureFlag + ", enclosureShowFlag=" + this.enclosureShowFlag + ", telFlag=" + this.telFlag + ", telShowFlag=" + this.telShowFlag + ", ticketShowFlag=" + this.ticketShowFlag + ", ticketStartWay=" + this.ticketStartWay + ", customOutTimeFlag=" + this.customOutTimeFlag + ", serviceOutTimeFlag=" + this.serviceOutTimeFlag + ", announceTopFlag=" + this.announceTopFlag + ", announceClickFlag=" + this.announceClickFlag + ", announceClickUrl='" + this.announceClickUrl + DateFormat.QUOTE + ", announceMsgFlag=" + this.announceMsgFlag + ", announceMsg='" + this.announceMsg + DateFormat.QUOTE + '}';
    }
}
