package com.sobot.chat.api.model;

import com.sobot.chat.api.apiUtils.GsonUtil;
import com.sobot.chat.api.enumtype.SobotAutoSendMsgMode;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Information implements Serializable {
    private static final long serialVersionUID = 1;
    private String app_key;
    private String appkey;
    private int artificialIntelligenceNum = 1;
    private SobotAutoSendMsgMode autoSendMsgMode = SobotAutoSendMsgMode.Default;
    private boolean canBackWithNotEvaluation;
    private String choose_adminid;
    private ConsultingContent consultingContent = null;
    private ConsultingContent content = null;
    private String customInfo;
    private int custom_title_url;
    private String customerCode;
    private String customerFields;
    private String customer_code;
    private String customer_fields;
    private String email;
    private String equipmentId;
    private String face;
    private int faqId = 0;
    private String group_name;
    private String groupid;
    private String helpCenterTel;
    private String helpCenterTelTitle;
    private boolean hideManualEvaluationLabels;
    private boolean hideMenuCamera;
    private boolean hideMenuFile;
    private boolean hideMenuLeave;
    private boolean hideMenuPicture;
    private boolean hideMenuSatisfaction;
    private boolean hideMenuVedio;
    private boolean hideRototEvaluationLabels;
    private int initModeType = -1;
    private boolean isArtificialIntelligence = false;
    private boolean isCloseInquiryForm = false;
    private boolean isQueueFirst = false;
    private boolean isShow = false;
    private boolean isShowCloseSatisfaction = false;
    private boolean isShowLeftBackPop = false;
    private boolean isShowSatisfaction = false;
    private boolean isUseRobotVoice = false;
    private boolean isUseVoice = true;
    private String isVip;
    private Map<String, String> leaveCusFieldMap;
    private String leaveMsgGroupId;
    private String leaveMsgGuideContent;
    private String leaveMsgTemplateContent;
    private List<SobotLeaveMsgFieldModel> leaveParamsExtends;
    private String leaveTemplateId;
    private String locale;
    private Map<String, String> margs = null;
    private String mulitParams;
    private String multi_params;
    private OrderCardContentModel orderGoodsInfo = null;
    private String params;
    private String partnerid;
    private String qq;
    private Map<String, String> questionRecommendParams = null;
    private boolean queue_first = false;
    private String realname;
    private String receptionistId;
    private String remark;
    private String robotCode = "";
    private String robot_alias;
    private String robot_code;
    private int service_mode = -1;
    private boolean showLeaveDetailBackEvaluate;
    private String skillSetId;
    private String skillSetName;
    private String summaryParams;
    private String summary_params;
    private String tel;
    private int titleImgId = 0;
    private int tranReceptionistFlag;
    private String transferAction;
    private HashSet<String> transferKeyWord = null;
    private String uid;
    private String uname;
    private String user_emails;
    private String user_label;
    private String user_name;
    private String user_nick;
    private String user_tels;
    private String vip_level;
    private String visitTitle;
    private String visitUrl;
    private String visit_title;
    private String visit_url;

    @Deprecated
    public void setAppkey(String str) {
        this.appkey = str;
        this.app_key = str;
    }

    public void setApp_key(String str) {
        this.appkey = str;
        this.app_key = str;
    }

    @Deprecated
    public String getAppkey() {
        return this.appkey;
    }

    public String getApp_key() {
        return this.app_key;
    }

    @Deprecated
    public String getCustomerCode() {
        return this.customerCode;
    }

    @Deprecated
    public void setCustomerCode(String str) {
        this.customerCode = str;
        this.customer_code = str;
    }

    public void setCustomer_code(String str) {
        this.customer_code = str;
        this.customerCode = str;
    }

    public String getCustomer_code() {
        return this.customer_code;
    }

    @Deprecated
    public String getReceptionistId() {
        return this.receptionistId;
    }

    @Deprecated
    public void setReceptionistId(String str) {
        this.receptionistId = str;
        this.choose_adminid = str;
    }

    public void setChoose_adminid(String str) {
        this.choose_adminid = str;
        this.receptionistId = str;
    }

    public String getChoose_adminid() {
        return this.choose_adminid;
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

    @Deprecated
    public String getUname() {
        return this.uname;
    }

    @Deprecated
    public void setUname(String str) {
        this.uname = str;
        this.user_nick = str;
    }

    public void setUser_nick(String str) {
        this.user_nick = str;
        this.uname = str;
    }

    public String getUser_nick() {
        return this.user_nick;
    }

    @Deprecated
    public String getRealname() {
        return this.realname;
    }

    @Deprecated
    public void setRealname(String str) {
        this.realname = str;
        this.user_name = str;
    }

    public void setUser_name(String str) {
        this.user_name = str;
        this.realname = str;
    }

    public String getUser_name() {
        return this.user_name;
    }

    @Deprecated
    public String getTel() {
        return this.tel;
    }

    @Deprecated
    public void setTel(String str) {
        this.tel = str;
        this.user_tels = str;
    }

    public void setUser_tels(String str) {
        this.user_tels = str;
        this.tel = str;
    }

    public String getUser_tels() {
        return this.user_tels;
    }

    @Deprecated
    public String getEmail() {
        return this.email;
    }

    @Deprecated
    public void setEmail(String str) {
        this.email = str;
        this.user_emails = str;
    }

    public void setUser_emails(String str) {
        this.user_emails = str;
        this.email = str;
    }

    public String getUser_emails() {
        return this.user_emails;
    }

    @Deprecated
    public String getCustomInfo() {
        return this.customInfo;
    }

    @Deprecated
    public void setCustomInfo(Map<String, String> map) {
        this.customInfo = GsonUtil.map2Json(map);
        this.params = GsonUtil.map2Json(map);
    }

    @Deprecated
    public void setCustomInfo(String str) {
        this.customInfo = str;
        this.params = str;
    }

    public void setParams(String str) {
        this.params = str;
        this.customInfo = str;
    }

    public void setParams(Map<String, String> map) {
        this.params = GsonUtil.map2Json(map);
        this.customInfo = GsonUtil.map2Json(map);
    }

    public String getParams() {
        return this.params;
    }

    @Deprecated
    public String getSummaryParams() {
        return this.summaryParams;
    }

    @Deprecated
    public void setSummaryParams(Map<String, String> map) {
        this.summaryParams = GsonUtil.map2Json(map);
        this.summary_params = GsonUtil.map2Json(map);
    }

    @Deprecated
    public void setSummaryParams(String str) {
        this.summaryParams = str;
        this.summary_params = str;
    }

    public void setSummary_params(String str) {
        this.summary_params = str;
        this.summaryParams = str;
    }

    public void setSummary_params(Map<String, String> map) {
        this.summary_params = GsonUtil.map2Json(map);
        this.summaryParams = GsonUtil.map2Json(map);
    }

    public String getSummary_params() {
        return this.summary_params;
    }

    @Deprecated
    public String getCustomerFields() {
        return this.customerFields;
    }

    @Deprecated
    public void setCustomerFields(Map<String, String> map) {
        this.customerFields = GsonUtil.map2Json(map);
        this.customer_fields = GsonUtil.map2Json(map);
    }

    @Deprecated
    public void setCustomerFields(String str) {
        this.customerFields = str;
        this.customer_fields = str;
    }

    public void setCustomer_fields(String str) {
        this.customer_fields = str;
        this.customerFields = str;
    }

    public void setCustomer_fields(Map<String, String> map) {
        this.customer_fields = GsonUtil.map2Json(map);
        this.customerFields = GsonUtil.map2Json(map);
    }

    public String getCustomer_fields() {
        return this.customer_fields;
    }

    @Deprecated
    public String getMulitParams() {
        return this.mulitParams;
    }

    @Deprecated
    public void setMulitParams(String str) {
        this.mulitParams = str;
        this.multi_params = str;
    }

    public void setMulti_params(String str) {
        this.multi_params = str;
        this.mulitParams = str;
    }

    public String getMulti_params() {
        return this.multi_params;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String str) {
        this.qq = str;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public String getFace() {
        return this.face;
    }

    public void setFace(String str) {
        this.face = str;
    }

    @Deprecated
    public String getVisitTitle() {
        return this.visitTitle;
    }

    @Deprecated
    public void setVisitTitle(String str) {
        this.visitTitle = str;
        this.visit_title = str;
    }

    public void setVisit_title(String str) {
        this.visit_title = str;
        this.visitTitle = str;
    }

    public String getVisit_title() {
        return this.visit_title;
    }

    @Deprecated
    public String getVisitUrl() {
        return this.visitUrl;
    }

    @Deprecated
    public void setVisitUrl(String str) {
        this.visitUrl = str;
        this.visit_url = str;
    }

    public void setVisit_url(String str) {
        this.visit_url = str;
        this.visitUrl = str;
    }

    public String getVisit_url() {
        return this.visit_url;
    }

    @Deprecated
    public int getInitModeType() {
        return this.initModeType;
    }

    @Deprecated
    public void setInitModeType(int i) {
        this.initModeType = i;
        this.service_mode = i;
    }

    public void setService_mode(int i) {
        this.service_mode = i;
        this.initModeType = i;
    }

    public int getService_mode() {
        return this.service_mode;
    }

    @Deprecated
    public String getSkillSetName() {
        return this.skillSetName;
    }

    @Deprecated
    public void setSkillSetName(String str) {
        this.skillSetName = str;
        this.group_name = str;
    }

    public void setGroup_name(String str) {
        this.group_name = str;
        this.skillSetName = str;
    }

    public String getGroup_name() {
        return this.group_name;
    }

    @Deprecated
    public String getSkillSetId() {
        return this.skillSetId;
    }

    @Deprecated
    public void setSkillSetId(String str) {
        this.skillSetId = str;
        this.groupid = str;
    }

    public void setGroupid(String str) {
        this.groupid = str;
        this.skillSetId = str;
    }

    public String getGroupid() {
        return this.groupid;
    }

    public String getTransferAction() {
        return this.transferAction;
    }

    public void setTransferAction(String str) {
        this.transferAction = str;
    }

    @Deprecated
    public boolean isQueueFirst() {
        return this.isQueueFirst;
    }

    @Deprecated
    public void setQueueFirst(boolean z) {
        this.isQueueFirst = z;
        this.queue_first = z;
    }

    public void setIs_Queue_First(boolean z) {
        this.queue_first = z;
        this.isQueueFirst = z;
    }

    public boolean is_queue_first() {
        return this.queue_first;
    }

    @Deprecated
    public ConsultingContent getConsultingContent() {
        return this.consultingContent;
    }

    @Deprecated
    public void setConsultingContent(ConsultingContent consultingContent) {
        this.consultingContent = consultingContent;
        this.content = consultingContent;
    }

    public void setContent(ConsultingContent consultingContent) {
        this.content = consultingContent;
        this.consultingContent = consultingContent;
    }

    public ConsultingContent getContent() {
        return this.content;
    }

    @Deprecated
    public Map<String, String> getQuestionRecommendParams() {
        return this.questionRecommendParams;
    }

    @Deprecated
    public void setQuestionRecommendParams(Map<String, String> map) {
        this.questionRecommendParams = map;
        this.margs = map;
    }

    public void setMargs(Map<String, String> map) {
        this.margs = map;
        this.questionRecommendParams = map;
    }

    public Map<String, String> getMargs() {
        return this.margs;
    }

    public SobotAutoSendMsgMode getAutoSendMsgMode() {
        return this.autoSendMsgMode;
    }

    public void setAutoSendMsgMode(SobotAutoSendMsgMode sobotAutoSendMsgMode) {
        this.autoSendMsgMode = sobotAutoSendMsgMode;
    }

    @Deprecated
    public int getTitleImgId() {
        return this.titleImgId;
    }

    @Deprecated
    public void setTitleImgId(int i) {
        this.titleImgId = i;
        this.custom_title_url = i;
    }

    public void setCustom_title_url(int i) {
        this.custom_title_url = i;
        this.titleImgId = i;
    }

    public int getCustom_title_url() {
        return this.custom_title_url;
    }

    public OrderCardContentModel getOrderGoodsInfo() {
        return this.orderGoodsInfo;
    }

    public void setOrderGoodsInfo(OrderCardContentModel orderCardContentModel) {
        this.orderGoodsInfo = orderCardContentModel;
    }

    public String getRobot_code() {
        return this.robot_code;
    }

    public void setRobot_code(String str) {
        this.robot_code = str;
        this.robotCode = str;
    }

    public String getRobot_alias() {
        return this.robot_alias;
    }

    public void setRobot_alias(String str) {
        this.robot_alias = str;
    }

    @Deprecated
    public String getRobotCode() {
        return this.robotCode;
    }

    @Deprecated
    public void setRobotCode(String str) {
        this.robotCode = str;
        this.robot_code = str;
    }

    public HashSet<String> getTransferKeyWord() {
        return this.transferKeyWord;
    }

    public void setTransferKeyWord(HashSet<String> hashSet) {
        this.transferKeyWord = hashSet;
    }

    public int getTranReceptionistFlag() {
        return this.tranReceptionistFlag;
    }

    public void setTranReceptionistFlag(int i) {
        this.tranReceptionistFlag = i;
    }

    public String getEquipmentId() {
        return this.equipmentId;
    }

    public void setEquipmentId(String str) {
        this.equipmentId = str;
    }

    public boolean isUseVoice() {
        return this.isUseVoice;
    }

    public void setUseVoice(boolean z) {
        this.isUseVoice = z;
    }

    public int getArtificialIntelligenceNum() {
        return this.artificialIntelligenceNum;
    }

    public void setArtificialIntelligenceNum(int i) {
        this.artificialIntelligenceNum = i;
    }

    public boolean isArtificialIntelligence() {
        return this.isArtificialIntelligence;
    }

    public void setArtificialIntelligence(boolean z) {
        this.isArtificialIntelligence = z;
    }

    public boolean isShowSatisfaction() {
        return this.isShowSatisfaction;
    }

    public void setShowSatisfaction(boolean z) {
        this.isShowSatisfaction = z;
    }

    public boolean isShowCloseBtn() {
        return this.isShow;
    }

    public void setShowCloseBtn(boolean z) {
        this.isShow = z;
    }

    public boolean isShowCloseSatisfaction() {
        return this.isShowCloseSatisfaction;
    }

    public void setShowCloseSatisfaction(boolean z) {
        this.isShowCloseSatisfaction = z;
    }

    public boolean isUseRobotVoice() {
        return this.isUseRobotVoice;
    }

    public void setUseRobotVoice(boolean z) {
        this.isUseRobotVoice = z;
    }

    public String getIsVip() {
        return this.isVip;
    }

    public void setIsVip(String str) {
        this.isVip = str;
    }

    public boolean isShowLeftBackPop() {
        return this.isShowLeftBackPop;
    }

    public void setShowLeftBackPop(boolean z) {
        this.isShowLeftBackPop = z;
    }

    public String getLeaveMsgTemplateContent() {
        return this.leaveMsgTemplateContent;
    }

    public void setLeaveMsgTemplateContent(String str) {
        this.leaveMsgTemplateContent = str;
    }

    public String getLeaveMsgGuideContent() {
        return this.leaveMsgGuideContent;
    }

    public void setLeaveMsgGuideContent(String str) {
        this.leaveMsgGuideContent = str;
    }

    public boolean isCloseInquiryForm() {
        return this.isCloseInquiryForm;
    }

    public void setCloseInquiryForm(boolean z) {
        this.isCloseInquiryForm = z;
    }

    public Map<String, String> getLeaveCusFieldMap() {
        return this.leaveCusFieldMap;
    }

    public void setLeaveCusFieldMap(Map<String, String> map) {
        this.leaveCusFieldMap = map;
    }

    public String getLeaveMsgGroupId() {
        return this.leaveMsgGroupId;
    }

    public void setLeaveMsgGroupId(String str) {
        this.leaveMsgGroupId = str;
    }

    public String getVip_level() {
        return this.vip_level;
    }

    public void setVip_level(String str) {
        this.vip_level = str;
    }

    public String getUser_label() {
        return this.user_label;
    }

    public void setUser_label(String str) {
        this.user_label = str;
    }

    public boolean isHideMenuSatisfaction() {
        return this.hideMenuSatisfaction;
    }

    public void setHideMenuSatisfaction(boolean z) {
        this.hideMenuSatisfaction = z;
    }

    public boolean isHideMenuLeave() {
        return this.hideMenuLeave;
    }

    public void setHideMenuLeave(boolean z) {
        this.hideMenuLeave = z;
    }

    public boolean isHideMenuPicture() {
        return this.hideMenuPicture;
    }

    public void setHideMenuPicture(boolean z) {
        this.hideMenuPicture = z;
    }

    public boolean isHideMenuVedio() {
        return this.hideMenuVedio;
    }

    public void setHideMenuVedio(boolean z) {
        this.hideMenuVedio = z;
    }

    public boolean isHideMenuCamera() {
        return this.hideMenuCamera;
    }

    public void setHideMenuCamera(boolean z) {
        this.hideMenuCamera = z;
    }

    public boolean isHideMenuFile() {
        return this.hideMenuFile;
    }

    public void setHideMenuFile(boolean z) {
        this.hideMenuFile = z;
    }

    public boolean isShowLeaveDetailBackEvaluate() {
        return this.showLeaveDetailBackEvaluate;
    }

    public void setShowLeaveDetailBackEvaluate(boolean z) {
        this.showLeaveDetailBackEvaluate = z;
    }

    public boolean isCanBackWithNotEvaluation() {
        return this.canBackWithNotEvaluation;
    }

    public void setCanBackWithNotEvaluation(boolean z) {
        this.canBackWithNotEvaluation = z;
    }

    public List<SobotLeaveMsgFieldModel> getLeaveParamsExtends() {
        return this.leaveParamsExtends;
    }

    public void setLeaveParamsExtends(List<SobotLeaveMsgFieldModel> list) {
        this.leaveParamsExtends = list;
    }

    public boolean isHideRototEvaluationLabels() {
        return this.hideRototEvaluationLabels;
    }

    public void setHideRototEvaluationLabels(boolean z) {
        this.hideRototEvaluationLabels = z;
    }

    public boolean isHideManualEvaluationLabels() {
        return this.hideManualEvaluationLabels;
    }

    public void setHideManualEvaluationLabels(boolean z) {
        this.hideManualEvaluationLabels = z;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public int getFaqId() {
        return this.faqId;
    }

    public void setFaqId(int i) {
        this.faqId = i;
    }

    public String getHelpCenterTel() {
        return this.helpCenterTel;
    }

    public void setHelpCenterTel(String str) {
        this.helpCenterTel = str;
    }

    public String getHelpCenterTelTitle() {
        return this.helpCenterTelTitle;
    }

    public void setHelpCenterTelTitle(String str) {
        this.helpCenterTelTitle = str;
    }

    public String getLeaveTemplateId() {
        return this.leaveTemplateId;
    }

    public void setLeaveTemplateId(String str) {
        this.leaveTemplateId = str;
    }
}
