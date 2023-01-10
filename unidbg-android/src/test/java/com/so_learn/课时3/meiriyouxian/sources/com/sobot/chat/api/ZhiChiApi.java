package com.sobot.chat.api;

import android.content.Context;
import com.sobot.chat.api.model.BaseCode;
import com.sobot.chat.api.model.CommonModel;
import com.sobot.chat.api.model.CommonModelBase;
import com.sobot.chat.api.model.ConsultingContent;
import com.sobot.chat.api.model.Information;
import com.sobot.chat.api.model.OrderCardContentModel;
import com.sobot.chat.api.model.PostParamModel;
import com.sobot.chat.api.model.SatisfactionSet;
import com.sobot.chat.api.model.SobotCityResult;
import com.sobot.chat.api.model.SobotCommentParam;
import com.sobot.chat.api.model.SobotConnCusParam;
import com.sobot.chat.api.model.SobotLableInfoList;
import com.sobot.chat.api.model.SobotLeaveMsgConfig;
import com.sobot.chat.api.model.SobotLeaveMsgParamModel;
import com.sobot.chat.api.model.SobotLeaveReplyModel;
import com.sobot.chat.api.model.SobotLocationModel;
import com.sobot.chat.api.model.SobotMsgCenterModel;
import com.sobot.chat.api.model.SobotPostMsgTemplate;
import com.sobot.chat.api.model.SobotQueryFormModel;
import com.sobot.chat.api.model.SobotQuestionRecommend;
import com.sobot.chat.api.model.SobotRobot;
import com.sobot.chat.api.model.SobotRobotGuess;
import com.sobot.chat.api.model.SobotUserTicketInfo;
import com.sobot.chat.api.model.SobotUserTicketInfoFlag;
import com.sobot.chat.api.model.StCategoryModel;
import com.sobot.chat.api.model.StDocModel;
import com.sobot.chat.api.model.StHelpDocModel;
import com.sobot.chat.api.model.StUserDealTicketInfo;
import com.sobot.chat.api.model.ZhiChiCidsModel;
import com.sobot.chat.api.model.ZhiChiGroup;
import com.sobot.chat.api.model.ZhiChiHistoryMessage;
import com.sobot.chat.api.model.ZhiChiInitModeBase;
import com.sobot.chat.api.model.ZhiChiMessage;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.api.model.ZhiChiWorkModel;
import com.sobot.chat.core.http.c.a;
import com.sobot.chat.core.http.i.d;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ZhiChiApi {
    void addTicketSatisfactionScoreInfo(Object obj, String str, String str2, String str3, int i, String str4, a<String> aVar);

    d addUploadFileTask(boolean z, String str, String str2, String str3, String str4, String str5);

    void chatSendMsgToRoot(String str, String str2, int i, String str3, String str4, String str5, a<ZhiChiMessageBase> aVar);

    void checkUserTicketInfo(Object obj, String str, String str2, String str3, a<SobotUserTicketInfoFlag> aVar);

    void comment(Object obj, String str, String str2, SobotCommentParam sobotCommentParam, a<CommonModel> aVar);

    void config(Object obj, String str);

    void connChannel(String str, String str2, String str3, String str4, String str5, String str6);

    void connnect(Object obj, SobotConnCusParam sobotConnCusParam, a<ZhiChiMessageBase> aVar);

    void deleteHisMsg(Object obj, String str, a<CommonModelBase> aVar);

    void disconnChannel();

    void fileUploadForPostMsg(Object obj, String str, String str2, String str3, ResultCallBack<ZhiChiMessage> resultCallBack);

    void getCategoryList(Object obj, String str, a<List<StCategoryModel>> aVar);

    void getChatDetailByCid(Object obj, String str, String str2, a<ZhiChiHistoryMessage> aVar);

    void getGroupList(Object obj, String str, String str2, a<ZhiChiGroup> aVar);

    void getHelpDocByCategoryId(Object obj, String str, String str2, a<List<StDocModel>> aVar);

    void getHelpDocByDocId(Object obj, String str, String str2, a<StHelpDocModel> aVar);

    void getLableInfoList(Object obj, String str, a<List<SobotLableInfoList>> aVar);

    void getMsgTemplateConfig(Object obj, String str, String str2, a<SobotLeaveMsgConfig> aVar);

    List<SobotMsgCenterModel> getPlatformList(Object obj, String str, String str2) throws Exception;

    void getRobotSwitchList(Object obj, String str, a<List<SobotRobot>> aVar);

    void getTemplateFieldsInfo(Object obj, String str, String str2, a<SobotLeaveMsgParamModel> aVar);

    void getUserDealTicketInfoList(Object obj, String str, String str2, String str3, a<List<StUserDealTicketInfo>> aVar);

    void getUserTicketInfoList(Object obj, String str, String str2, String str3, a<List<SobotUserTicketInfo>> aVar);

    void getUserTicketReplyInfo(Object obj, String str, String str2, a<List<SobotLeaveReplyModel>> aVar);

    void getWsTemplate(Object obj, String str, a<ArrayList<SobotPostMsgTemplate>> aVar);

    void input(String str, String str2, a<CommonModel> aVar);

    void isWork(String str, String str2, a<ZhiChiWorkModel> aVar);

    void leaveMsg(Object obj, String str, String str2, a<BaseCode> aVar);

    void logCollect(Context context, String str);

    void out(String str, String str2, a<CommonModel> aVar);

    void postMsg(Object obj, PostParamModel postParamModel, a<CommonModelBase> aVar);

    void queryCids(Object obj, String str, long j, a<ZhiChiCidsModel> aVar);

    void queryCity(Object obj, String str, String str2, a<SobotCityResult> aVar);

    void queryFormConfig(Object obj, String str, a<SobotQueryFormModel> aVar);

    void questionRecommend(Object obj, String str, Map<String, String> map, a<SobotQuestionRecommend> aVar);

    void rbAnswerComment(Object obj, String str, String str2, String str3, String str4, String str5, String str6, boolean z, a<CommonModelBase> aVar);

    void reconnectChannel();

    void removeMerchant(Object obj, String str, String str2, SobotMsgCenterModel sobotMsgCenterModel, a<SobotMsgCenterModel> aVar);

    void replyTicketContent(Object obj, String str, String str2, String str3, String str4, String str5, a<String> aVar);

    void robotGuess(Object obj, String str, String str2, String str3, a<SobotRobotGuess> aVar);

    void robotGuide(Object obj, String str, String str2, int i, a<ZhiChiMessageBase> aVar);

    void satisfactionMessage(Object obj, String str, ResultCallBack<SatisfactionSet> resultCallBack);

    void sendCardMsg(ConsultingContent consultingContent, String str, String str2, a<CommonModelBase> aVar);

    void sendFile(String str, String str2, String str3, String str4, ResultCallBack<ZhiChiMessage> resultCallBack);

    void sendLocation(Object obj, SobotLocationModel sobotLocationModel, String str, String str2, a<CommonModelBase> aVar);

    void sendMsgToCoutom(String str, String str2, String str3, a<CommonModelBase> aVar);

    void sendOrderCardMsg(OrderCardContentModel orderCardContentModel, String str, String str2, a<CommonModelBase> aVar);

    void sendVoiceToRobot(String str, String str2, String str3, String str4, String str5, ResultCallBack<ZhiChiMessage> resultCallBack);

    void sobotInit(Object obj, Information information, a<ZhiChiInitModeBase> aVar);

    void submitForm(Object obj, String str, String str2, a<CommonModel> aVar);

    void updateUserTicketReplyInfo(Object obj, String str, String str2, String str3);
}
