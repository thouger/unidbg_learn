package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ZhiChiMessageBase implements Serializable {
    private static final long serialVersionUID = 1;
    private String action;
    private String adminHelloWord;
    private String aface;
    private String aname;
    private ZhiChiReplyAnswer answer;
    private String answerType;
    private String cid;
    private int clickCount;
    private ConsultingContent consultingContent;
    private String content;
    private int count;
    private int currentPageNum;
    private String docId;
    private String docName;
    private boolean guideGroupFlag;
    private int guideGroupNum;
    private String id;
    private boolean isLeaveMsgFlag;
    private boolean isRetractedMsg = false;
    private boolean isShake = false;
    private boolean isShowTransferBtn = false;
    private ArrayList<Suggestions> listSuggestions;
    private String message;
    private String msg;
    private String msgId;
    private int multiDiaRespEnd;
    private String offlineType;
    private OrderCardContentModel orderCardContent;
    private String picurl;
    private int progressBar;
    private String pu;
    private String puid;
    private String question;
    private String queueDoc;
    private String receiver;
    private String receiverFace;
    private String receiverName;
    private String receiverType;
    private int revaluateState = 0;
    private String rictype;
    private ZhiChiHistorySDKMsg sdkMsg;
    private int sendSuccessState = 1;
    private String sender;
    private String senderFace;
    private String senderName;
    private String senderType;
    private String serviceEndPushMsg;
    private String serviceOutDoc;
    private String serviceOutTime;
    private SobotEvaluateModel sobotEvaluateModel = null;
    private SobotKeyWordTransfer sobotKeyWordTransfer = null;
    private String status;
    private String stripe;
    private String[] sugguestions;
    private int sugguestionsFontColor;
    private String t;
    private int transferType;
    private String ts;
    private String url;
    private int ustatus;
    private boolean voideIsPlaying = false;
    private String wayHttp;
    private String wslinkBak;
    private String wslinkDefault;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public SobotKeyWordTransfer getSobotKeyWordTransfer() {
        return this.sobotKeyWordTransfer;
    }

    public void setSobotKeyWordTransfer(SobotKeyWordTransfer sobotKeyWordTransfer) {
        this.sobotKeyWordTransfer = sobotKeyWordTransfer;
    }

    public String getServiceEndPushMsg() {
        return this.serviceEndPushMsg;
    }

    public void setServiceEndPushMsg(String str) {
        this.serviceEndPushMsg = str;
    }

    public String getAface() {
        return this.aface;
    }

    public void setAface(String str) {
        this.aface = str;
    }

    public String getPicurl() {
        return this.picurl;
    }

    public void setPicurl(String str) {
        this.picurl = str;
    }

    public String getRictype() {
        return this.rictype;
    }

    public void setRictype(String str) {
        this.rictype = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String str) {
        this.sender = str;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public void setSenderName(String str) {
        this.senderName = str;
    }

    public String getSenderType() {
        return this.senderType;
    }

    public void setSenderType(String str) {
        this.senderType = str;
    }

    public String getSenderFace() {
        return this.senderFace;
    }

    public void setSenderFace(String str) {
        this.senderFace = str;
    }

    public String getT() {
        return this.t;
    }

    public void setT(String str) {
        this.t = str;
    }

    public String getTs() {
        return this.ts;
    }

    public void setTs(String str) {
        this.ts = str;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(String str) {
        this.receiver = str;
    }

    public String getReceiverName() {
        return this.receiverName;
    }

    public void setReceiverName(String str) {
        this.receiverName = str;
    }

    public String getReceiverType() {
        return this.receiverType;
    }

    public void setReceiverType(String str) {
        this.receiverType = str;
    }

    public String getOfflineType() {
        return this.offlineType;
    }

    public void setOfflineType(String str) {
        this.offlineType = str;
    }

    public String getReceiverFace() {
        return this.receiverFace;
    }

    public void setReceiverFace(String str) {
        this.receiverFace = str;
    }

    public ZhiChiReplyAnswer getAnswer() {
        return this.answer;
    }

    public void setAnswer(ZhiChiReplyAnswer zhiChiReplyAnswer) {
        this.answer = zhiChiReplyAnswer;
    }

    public String[] getSugguestions() {
        return this.sugguestions;
    }

    public void setSugguestions(String[] strArr) {
        this.sugguestions = strArr;
    }

    public String getAnswerType() {
        return this.answerType;
    }

    public void setAnswerType(String str) {
        this.answerType = str;
    }

    public String getStripe() {
        return this.stripe;
    }

    public void setStripe(String str) {
        this.stripe = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getProgressBar() {
        return this.progressBar;
    }

    public void setProgressBar(int i) {
        this.progressBar = i;
    }

    public boolean isVoideIsPlaying() {
        return this.voideIsPlaying;
    }

    public void setVoideIsPlaying(boolean z) {
        this.voideIsPlaying = z;
    }

    public int getSendSuccessState() {
        return this.sendSuccessState;
    }

    public void setSendSuccessState(int i) {
        this.sendSuccessState = i;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public ZhiChiHistorySDKMsg getSdkMsg() {
        return this.sdkMsg;
    }

    public void setSdkMsg(ZhiChiHistorySDKMsg zhiChiHistorySDKMsg) {
        this.sdkMsg = zhiChiHistorySDKMsg;
    }

    public String getPu() {
        return this.pu;
    }

    public void setPu(String str) {
        this.pu = str;
    }

    public String getPuid() {
        return this.puid;
    }

    public void setPuid(String str) {
        this.puid = str;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public String getAname() {
        return this.aname;
    }

    public void setAname(String str) {
        this.aname = str;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String str) {
        this.question = str;
    }

    public ArrayList<Suggestions> getListSuggestions() {
        return this.listSuggestions;
    }

    public void setListSuggestions(ArrayList<Suggestions> arrayList) {
        this.listSuggestions = arrayList;
    }

    public String getWslinkBak() {
        return this.wslinkBak;
    }

    public void setWslinkBak(String str) {
        this.wslinkBak = str;
    }

    public String getWslinkDefault() {
        return this.wslinkDefault;
    }

    public void setWslinkDefault(String str) {
        this.wslinkDefault = str;
    }

    public int getUstatus() {
        return this.ustatus;
    }

    public void setUstatus(int i) {
        this.ustatus = i;
    }

    public int getSugguestionsFontColor() {
        return this.sugguestionsFontColor;
    }

    public void setSugguestionsFontColor(int i) {
        this.sugguestionsFontColor = i;
    }

    public boolean isShake() {
        return this.isShake;
    }

    public void setShake(boolean z) {
        this.isShake = z;
    }

    public boolean isShowTransferBtn() {
        return this.isShowTransferBtn;
    }

    public void setShowTransferBtn(boolean z) {
        this.isShowTransferBtn = z;
    }

    public int getRevaluateState() {
        return this.revaluateState;
    }

    public void setRevaluateState(int i) {
        this.revaluateState = i;
    }

    public String getDocId() {
        return this.docId;
    }

    public void setDocId(String str) {
        this.docId = str;
    }

    public String getDocName() {
        return this.docName;
    }

    public void setDocName(String str) {
        this.docName = str;
    }

    public String getWayHttp() {
        return this.wayHttp;
    }

    public void setWayHttp(String str) {
        this.wayHttp = str;
    }

    public SobotEvaluateModel getSobotEvaluateModel() {
        return this.sobotEvaluateModel;
    }

    public void setSobotEvaluateModel(SobotEvaluateModel sobotEvaluateModel) {
        this.sobotEvaluateModel = sobotEvaluateModel;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String str) {
        this.msgId = str;
    }

    public String getAdminHelloWord() {
        return this.adminHelloWord;
    }

    public void setAdminHelloWord(String str) {
        this.adminHelloWord = str;
    }

    public String getServiceOutTime() {
        return this.serviceOutTime;
    }

    public void setServiceOutTime(String str) {
        this.serviceOutTime = str;
    }

    public String getServiceOutDoc() {
        return this.serviceOutDoc;
    }

    public void setServiceOutDoc(String str) {
        this.serviceOutDoc = str;
    }

    public int getMultiDiaRespEnd() {
        return this.multiDiaRespEnd;
    }

    public void setMultiDiaRespEnd(int i) {
        this.multiDiaRespEnd = i;
    }

    public boolean isRetractedMsg() {
        return this.isRetractedMsg;
    }

    public void setRetractedMsg(boolean z) {
        this.isRetractedMsg = z;
    }

    public String getQueueDoc() {
        return this.queueDoc;
    }

    public void setQueueDoc(String str) {
        this.queueDoc = str;
    }

    public int getTransferType() {
        return this.transferType;
    }

    public void setTransferType(int i) {
        this.transferType = i;
    }

    public boolean isLeaveMsgFlag() {
        return this.isLeaveMsgFlag;
    }

    public void setLeaveMsgFlag(boolean z) {
        this.isLeaveMsgFlag = z;
    }

    public ConsultingContent getConsultingContent() {
        return this.consultingContent;
    }

    public void setConsultingContent(ConsultingContent consultingContent) {
        this.consultingContent = consultingContent;
    }

    public boolean isGuideGroupFlag() {
        return this.guideGroupFlag;
    }

    public void setGuideGroupFlag(boolean z) {
        this.guideGroupFlag = z;
    }

    public int getGuideGroupNum() {
        return this.guideGroupNum;
    }

    public void setGuideGroupNum(int i) {
        this.guideGroupNum = i;
    }

    public int getCurrentPageNum() {
        return this.currentPageNum;
    }

    public void setCurrentPageNum(int i) {
        this.currentPageNum = i;
    }

    public OrderCardContentModel getOrderCardContent() {
        return this.orderCardContent;
    }

    public void setOrderCardContent(OrderCardContentModel orderCardContentModel) {
        this.orderCardContent = orderCardContentModel;
    }

    public int getClickCount() {
        return this.clickCount;
    }

    public void setClickCount(int i) {
        this.clickCount = i;
    }

    public void addClickCount() {
        this.clickCount++;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return "ZhiChiMessageBase{question='" + this.question + DateFormat.QUOTE + ", docId='" + this.docId + DateFormat.QUOTE + ", docName='" + this.docName + DateFormat.QUOTE + ", serviceEndPushMsg='" + this.serviceEndPushMsg + DateFormat.QUOTE + ", isShake=" + this.isShake + ", isShowTransferBtn=" + this.isShowTransferBtn + ", revaluateState=" + this.revaluateState + ", sobotEvaluateModel=" + this.sobotEvaluateModel + ", ustatus=" + this.ustatus + ", id='" + this.id + DateFormat.QUOTE + ", msgId='" + this.msgId + DateFormat.QUOTE + ", cid='" + this.cid + DateFormat.QUOTE + ", action='" + this.action + DateFormat.QUOTE + ", wslinkBak='" + this.wslinkBak + DateFormat.QUOTE + ", wslinkDefault='" + this.wslinkDefault + DateFormat.QUOTE + ", wayHttp='" + this.wayHttp + DateFormat.QUOTE + ", adminHelloWord='" + this.adminHelloWord + DateFormat.QUOTE + ", serviceOutTime='" + this.serviceOutTime + DateFormat.QUOTE + ", serviceOutDoc='" + this.serviceOutDoc + DateFormat.QUOTE + ", queueDoc='" + this.queueDoc + DateFormat.QUOTE + ", url='" + this.url + DateFormat.QUOTE + ", status='" + this.status + DateFormat.QUOTE + ", progressBar=" + this.progressBar + ", sendSuccessState=" + this.sendSuccessState + ", isRetractedMsg=" + this.isRetractedMsg + ", voideIsPlaying=" + this.voideIsPlaying + ", content='" + this.content + DateFormat.QUOTE + ", message='" + this.message + DateFormat.QUOTE + ", msg='" + this.msg + DateFormat.QUOTE + ", sender='" + this.sender + DateFormat.QUOTE + ", senderName='" + this.senderName + DateFormat.QUOTE + ", senderType='" + this.senderType + DateFormat.QUOTE + ", senderFace='" + this.senderFace + DateFormat.QUOTE + ", t='" + this.t + DateFormat.QUOTE + ", ts='" + this.ts + DateFormat.QUOTE + ", sdkMsg=" + this.sdkMsg + ", sugguestionsFontColor=" + this.sugguestionsFontColor + ", multiDiaRespEnd=" + this.multiDiaRespEnd + ", clickCount=" + this.clickCount + ", receiver='" + this.receiver + DateFormat.QUOTE + ", receiverName='" + this.receiverName + DateFormat.QUOTE + ", receiverType='" + this.receiverType + DateFormat.QUOTE + ", offlineType='" + this.offlineType + DateFormat.QUOTE + ", receiverFace='" + this.receiverFace + DateFormat.QUOTE + ", answer=" + this.answer + ", sugguestions=" + Arrays.toString(this.sugguestions) + ", answerType='" + this.answerType + DateFormat.QUOTE + ", stripe='" + this.stripe + DateFormat.QUOTE + ", listSuggestions=" + this.listSuggestions + ", picurl='" + this.picurl + DateFormat.QUOTE + ", rictype='" + this.rictype + DateFormat.QUOTE + ", pu='" + this.pu + DateFormat.QUOTE + ", puid='" + this.puid + DateFormat.QUOTE + ", count=" + this.count + ", aname='" + this.aname + DateFormat.QUOTE + ", aface='" + this.aface + DateFormat.QUOTE + ", consultingContent=" + this.consultingContent + ", orderCardContent=" + this.orderCardContent + ", sobotKeyWordTransfer=" + this.sobotKeyWordTransfer + ", transferType=" + this.transferType + ", isLeaveMsgFlag=" + this.isLeaveMsgFlag + ", guideGroupFlag=" + this.guideGroupFlag + ", guideGroupNum=" + this.guideGroupNum + ", currentPageNum=" + this.currentPageNum + '}';
    }
}
